/**
 * 
 */
package com.raidentrance.model.context;

import com.raidentrance.connectivity.ServiceException;

/**
 * @author alex
 *
 */
public interface IRestClient {

	public abstract String getEndpoints() throws ServiceException;

}
