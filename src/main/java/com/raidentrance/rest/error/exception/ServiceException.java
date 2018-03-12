/**
 * 
 */
package com.raidentrance.rest.error.exception;

import com.raidentrance.rest.error.model.ErrorMessage;

/**
 * @author raidentrance
 *
 */
public class ServiceException extends Exception {

	private ErrorMessage errorMessage;

	private static final long serialVersionUID = -7898115956660992515L;

	public ServiceException(ErrorMessage errorMessage) {
		this.errorMessage = errorMessage;
	}

	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}

}
