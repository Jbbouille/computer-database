package org.excilys.exception;

public class DaoException extends RuntimeException {

	String message;

	public DaoException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
