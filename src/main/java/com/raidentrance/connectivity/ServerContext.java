package com.raidentrance.connectivity;

/**
 * @author alex
 *
 */

public class ServerContext {
	private String username;
	private String password;
	private String serverUrl;
	private String contextPath;

	public ServerContext() {
	}

	public ServerContext(String username, String password, String serverUrl,
			String contextPath) {
		super();
		this.username = username;
		this.password = password;
		this.serverUrl = serverUrl;
		this.contextPath = contextPath;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getServerUrl() {
		return serverUrl;
	}

	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}

	public String getContextPath() {
		return contextPath;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}

}
