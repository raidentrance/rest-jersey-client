/**
 * 
 */
package com.raidentrance.rest.error.model;

import java.io.Serializable;

/**
 * @author raidentrance
 *
 */
public class ErrorMessage implements Serializable {
	private boolean success;
	private ErrorCode error;

	private static final long serialVersionUID = -8921696489057035324L;

	public ErrorMessage() {

	}

	public ErrorMessage(boolean success, ErrorCode error) {
		super();
		this.success = success;
		this.error = error;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public ErrorCode getError() {
		return error;
	}

	public void setError(ErrorCode error) {
		this.error = error;
	}

}
