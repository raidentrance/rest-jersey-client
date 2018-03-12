/**
 * 
 */
package com.raidentrance.rest.error.model;

import java.io.Serializable;

/**
 * @author raidentrance
 *
 */
public class ErrorCode implements Serializable {

	private String code;
	private String message;

	private static final long serialVersionUID = 1735206115257033120L;

	public ErrorCode() {

	}

	public ErrorCode(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
