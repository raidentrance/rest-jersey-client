/**
 * 
 */
package com.raidentrance.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author raidentrance
 *
 */
public class Payload {
	@JsonProperty("high")
	private String high;

	@JsonProperty("last")
	private String last;

	@JsonProperty("created_at")
	private String createdAt;

	@JsonProperty("book")
	private String book;

	@JsonProperty("volume")
	private String volume;

	@JsonProperty("vwap")
	private String vwap;

	@JsonProperty("low")
	private String low;

	@JsonProperty("ask")
	private String ask;

	@JsonProperty("bid")
	private String bid;

	public String getHigh() {
		return high;
	}

	public void setHigh(String high) {
		this.high = high;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getBook() {
		return book;
	}

	public void setBook(String book) {
		this.book = book;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getVwap() {
		return vwap;
	}

	public void setVwap(String vwap) {
		this.vwap = vwap;
	}

	public String getLow() {
		return low;
	}

	public void setLow(String low) {
		this.low = low;
	}

	public String getAsk() {
		return ask;
	}

	public void setAsk(String ask) {
		this.ask = ask;
	}

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	@Override
	public String toString() {
		return "Payload [high=" + high + ", last=" + last + ", createdAt=" + createdAt + ", book=" + book + ", volume="
				+ volume + ", vwap=" + vwap + ", low=" + low + ", ask=" + ask + ", bid=" + bid + "]";
	}

}
