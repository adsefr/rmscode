package com.rms.base.exception;

public class FailureException extends Exception {

	private static final long serialVersionUID = -7887836024551423857L;

	public FailureException(String message, Throwable cause) {

		super(message, cause);
	}

	public FailureException(String message) {

		super(message);
	}

	public FailureException(Throwable cause) {

		super(cause);
	}

}
