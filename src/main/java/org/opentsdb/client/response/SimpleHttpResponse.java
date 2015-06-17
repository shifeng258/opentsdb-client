package org.opentsdb.client.response;

/**
 * @author argan
 *
 */
public class SimpleHttpResponse {
	private int statusCode;
	private String content;

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
