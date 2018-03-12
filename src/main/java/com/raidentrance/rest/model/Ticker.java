/**
 * 
 */
package com.raidentrance.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author raidentrance
 *
 */
public class Ticker {
	@JsonProperty("success")
	private boolean success;

	@JsonProperty("payload")
	private Payload payload;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Payload getPayload() {
		return payload;
	}

	public void setPayload(Payload payload) {
		this.payload = payload;
	}

	@Override
	public String toString() {
		return "Ticker [success=" + success + ", payload=" + payload + "]";
	}

}
