/**
 * 
 */
package com.raidentrance.rest;

import java.io.IOException;
import java.io.StringReader;
import java.util.concurrent.TimeUnit;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.raidentrance.rest.commons.AbstractClient;
import com.raidentrance.rest.endpoints.ApplicationEndpoints;
import com.raidentrance.rest.error.exception.ServiceException;
import com.raidentrance.rest.error.model.ErrorCode;
import com.raidentrance.rest.error.model.ErrorMessage;
import com.raidentrance.rest.model.Payload;
import com.raidentrance.rest.model.Ticker;
import com.raidentrance.rest.model.TickerList;

/**
 * @author maagapi
 *
 */
public class RestClient extends AbstractClient {

	private static final Logger log = LoggerFactory.getLogger(RestClient.class);

	public RestClient(String url, String contextPath) {
		super(url, contextPath);
	}

	public RestClient(String url, String contextPath, int maxRetries, int delay, TimeUnit unit) {
		super(url, contextPath, maxRetries, delay, unit);
	}

	public TickerList getTickers() throws Exception {
		return parseResponse(get(ApplicationEndpoints.getTickers(), MediaType.APPLICATION_JSON),
				new TypeReference<TickerList>() {
				});
	}

	public Ticker getTickerByBook(String book) throws Exception {
		return parseResponse(get(ApplicationEndpoints.getTickerByBook(book), MediaType.APPLICATION_JSON),
				new TypeReference<Ticker>() {
				});
	}

	@Override
	protected <T> T parseResponse(Response response, TypeReference<T> entityType) throws ServiceException {
		int status = response.getStatus();
		log.info("Status {}", status);
		if (response.getStatusInfo().getFamily() == Status.Family.SUCCESSFUL) {
			try {
				return new ObjectMapper().readValue(new StringReader(response.readEntity(String.class)), entityType);
			} catch (IOException e) {
				throw new ServiceException(
						new ErrorMessage(false, new ErrorCode("1000", "Request couldn't be processed")));
			}
		} else {
			throw new ServiceException(response.readEntity(ErrorMessage.class));
		}
	}	
	
	
	public static void main(String[] args) throws Exception {
		RestClient client = new RestClient("https://api.bitso.com", "/v3", 3, 3, TimeUnit.SECONDS);
		 TickerList tickers = client.getTickers();
		log.info("Getting tickers ");
		for (Payload payload : tickers.getPayload()) {
			log.info(payload.toString());
		}
		log.info("Getting ripple ticker");
		Ticker ripple = client.getTickerByBook("xrp_mxn");
		log.info(ripple.toString());
	}
	
}
