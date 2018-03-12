/**
 * 
 */
package com.raidentrance.rest.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author maagapi
 *
 */
public class TickerList {
	@JsonProperty("success")
	private boolean success;

	@JsonProperty("payload")
	private List<Payload> payload;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public List<Payload> getPayload() {
		return payload;
	}

	public void setPayload(List<Payload> payload) {
		this.payload = payload;
	}

	@Override
	public String toString() {
		return "TickerList [success=" + success + ", payload=" + payload + "]";
	}
	
	
	
}
