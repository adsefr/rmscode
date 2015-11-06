package com.rms.base.exception;

class FailureException extends Exception {

	private static final long serialVersionUID = -7887836024551423857L;

	public FailureException() {
		super();
	}

	public FailureException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

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
