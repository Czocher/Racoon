package org.czocher.raccoon;

@SuppressWarnings("serial")
public class HTTPException extends Exception {

	private int code;
	private String body;

	public HTTPException(final int code, final String body) {
		super();
		this.code = code;
		this.body = body;
	}

	public int getCode() {
		return code;
	}

	public void setCode(final int code) {
		this.code = code;
	}

	public String getBody() {
		return body;
	}

	public void setBody(final String body) {
		this.body = body;
	}

}
