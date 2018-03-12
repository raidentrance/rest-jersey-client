/**
 * 
 */
package com.raidentrance.rest.endpoints;

/**
 * @author raidentrance
 *
 */
public class ApplicationEndpoints {
	private static final String TICKER = "/ticker";

	private ApplicationEndpoints() {
	}

	public static String getTickers() {
		return TICKER;
	}

	public static String getTickerByBook(String book) {
		return TICKER.concat("?book=").concat(book);
	}

}
