package com.raidentrance.pojo.context;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.raidentrance.connectivity.ServerContext;
import com.raidentrance.connectivity.ServiceException;
import com.raidentrance.endpoints.ApplicationEndpoint;
import com.raidentrance.model.context.IRestClient;

/**
 * @author alex
 *
 */
public class RestClient extends AbstractClient implements IRestClient {

	private static final Logger LOG = LoggerFactory.getLogger(RestClient.class);

	public RestClient(ServerContext context) {
		super(context);
	}

	/* (non-Javadoc)
	 * @see com.raidentrance.pojo.context.IRestClient#getEndpoints()
	 */
	public String getEndpoints()throws ServiceException {
		LOG.info("Get endpoints");
		WebTarget client = createClient(ApplicationEndpoint.getEndpoints());
		Response response = client.request(MediaType.APPLICATION_JSON).get();
		LOG.info("Status {}", response.getStatus());
		String result = "";

		if (Status.OK.getStatusCode() == response.getStatus()) {
			result = response.readEntity(String.class);
		} else {
			throw new ServiceException();
		}
		return result;

	}
}
