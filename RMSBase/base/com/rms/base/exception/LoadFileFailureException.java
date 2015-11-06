package com.rms.base.exception;

public class LoadFileFailureException extends FailureException {

	private static final long serialVersionUID = -7887836024551423857L;

	public LoadFileFailureException() {
		super();
	}

	public LoadFileFailureException(String message, Throwable cause) {
		super(message, cause);
	}

	public LoadFileFailureException(String message) {
		super(message);
	}

	public LoadFileFailureException(Throwable cause) {
		super(cause);
	}
}
