/**
 * 
 */
package com.raidentrance.rest.commons;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;

import net.jodah.failsafe.Failsafe;
import net.jodah.failsafe.RetryPolicy;

/**
 * @author raidentrance
 *
 */
public abstract class AbstractClient {
	private String url;
	private String contextPath;
	private RetryPolicy defaultRetryPolicy;

	private static final Logger log = Logger.getLogger(AbstractClient.class.getName());

	public AbstractClient(String url, String contextPath) {
		this.url = url;
		this.contextPath = contextPath;
	}

	public AbstractClient(String url, String contextPath, int maxRetries, int delay, TimeUnit unit) {
		this.url = url;
		this.contextPath = contextPath;
		defaultRetryPolicy = new RetryPolicy()
				.retryIf((Response resp) -> resp.getStatusInfo().getFamily() == Status.Family.SERVER_ERROR)
				.withDelay(delay, unit).withMaxRetries(maxRetries);
	}

	protected WebTarget createClient(String path) {
		String assembledPath = assembleEndpoint(path);
		Client client = ClientBuilder.newClient();
		return client.target(assembledPath);
	}

	private String assembleEndpoint(String path) {
		String endpoint = url.concat(contextPath).concat(path);
		log.info(String.format("Calling endpoint %s", endpoint));
		return endpoint;
	}

	/**
	 * Execute a GET http request over the endpoint received with the content
	 * type specified
	 * 
	 * @param endpoint
	 *            Defines the endpoint that will be executed
	 * @param type
	 *            Defines the content type it can be JSON,XML, etc
	 * @return A response object with the entity response and the Http status
	 */
	protected Response get(String endpoint, String type) {
		WebTarget client = createClient(endpoint);
		Optional<RetryPolicy> result = getDefaultRetryPolicy();
		if (result.isPresent()) {
			return Failsafe.with(result.get()).get((response) -> client.request(type).get());
		} else {
			return client.request(type).get();
		}
	}

	/**
	 * Execute a PUT request over the endpoint received with the content type
	 * specified and sending the object received in the body
	 * 
	 * @param endpoint
	 *            Endpoint will be executed
	 * @param type
	 *            Defines the content type, it can be JSON, XML, etc
	 * @param entity
	 *            Object will be sent in the body of the request
	 * @return A response object with the entity response and the Http status
	 */
	protected Response put(String endpoint, String type, Object entity) {
		WebTarget client = createClient(endpoint);
		Optional<RetryPolicy> result = getDefaultRetryPolicy();
		if (result.isPresent()) {
			return Failsafe.with(result.get()).get((response) -> client.request(type).put(Entity.entity(entity, type)));
		} else {
			return client.request(type).put(Entity.entity(entity, type));
		}
	}

	/**
	 * Execute a POST request over the endpoint received with the content type
	 * specified and sending the object received in the body
	 *
	 * @param endpoint
	 *            Endpoint will be executed
	 * @param type
	 *            Defines the content type, it can be JSON, XML, etc
	 * @param entity
	 *            Object will be sent in the body of the request
	 * @return A response object with the entity response and the Http status
	 */
	protected Response post(String endpoint, String type, Object entity) {
		WebTarget client = createClient(endpoint);
		Optional<RetryPolicy> result = getDefaultRetryPolicy();
		if (result.isPresent()) {
			return Failsafe.with(result.get())
					.get((response) -> client.request(type).post(Entity.entity(entity, type)));
		} else {
			return client.request(type).put(Entity.entity(entity, type));
		}
	}

	/**
	 * If there is a default retry policy it will be returned
	 * 
	 * @return
	 */
	public Optional<RetryPolicy> getDefaultRetryPolicy() {
		return (defaultRetryPolicy != null) ? Optional.of(defaultRetryPolicy) : Optional.empty();
	}

	/**
	 * Modify the current default retry policy
	 * 
	 * @param maxRetries
	 *            Number of times that it will retry until a successful request
	 * @param delay
	 *            The time that will wait until the next attempt
	 * @param unit
	 *            Unit of the time of the delay, it can be in seconds, minutes,
	 *            etc.
	 */
	public void setDefaultRetryPolicy(int maxRetries, int delay, TimeUnit unit) {
		defaultRetryPolicy = new RetryPolicy()
				.retryIf((Response resp) -> resp.getStatusInfo().getFamily() == Status.Family.SERVER_ERROR)
				.withDelay(delay, unit).withMaxRetries(maxRetries);
	}

	/**
	 * Get a Response and Parse to type T. if the response is not 200 throw
	 * Exception
	 *
	 * @param response
	 *            the HTTP response 
	 * @param entityType
	 *            is a generic type that the function will return.
	 * @param <T>
	 *            The Generic Type that the method is returing
	 * @return T
	 * @throws Exception
	 *             if a problem occurs
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */
	protected abstract <T> T parseResponse(Response response,TypeReference<T> entityType) throws Exception;
}