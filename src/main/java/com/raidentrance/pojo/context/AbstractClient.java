package com.raidentrance.pojo.context;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.raidentrance.connectivity.ServerContext;

/**
 * @author alex
 *
 */

public abstract class AbstractClient {
	
	private ServerContext context;

	private static final Logger LOG = LoggerFactory
			.getLogger(AbstractClient.class);

	protected AbstractClient(ServerContext context) {
		this.context = context;
	}

	protected WebTarget createClient(String path) {
		String assembledPath = assembleEndpoint(path);
		LOG.debug("Endpoint URL: " + assembledPath);
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(assembledPath);
		if (context.getUsername() != null && context.getPassword() != null) {
			HttpAuthenticationFeature feature = HttpAuthenticationFeature
					.basic(context.getUsername(), context.getPassword());
			target = target.register(feature);
		}
		return target;
	}

	private String assembleEndpoint(String path) {
		return context.getServerUrl().concat(context.getContextPath())
				.concat(path);
	}

}
