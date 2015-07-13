package com.raidentrance.builder;

import com.raidentrance.connectivity.ServerContext;
import com.raidentrance.model.context.IRestClient;
import com.raidentrance.pojo.context.RestClient;

/**
 * @author alex
 *
 */
public class RestClientBuilder {
	public static IRestClient createClient(ServerContext context) {
		return new RestClient(context);
	}
}
