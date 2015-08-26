package com.rms.base.exception;

/**
 * 
 * 
 * @author ri.meisei
 * @since 2014/02/04
 */
public class RequiredException extends RuntimeException {

	private static final long serialVersionUID = -3452468664653854414L;

	public RequiredException() {

		super();
	}

	public RequiredException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {

		super(message, cause, enableSuppression, writableStackTrace);
	}

	public RequiredException(String message, Throwable cause) {

		super(message, cause);
	}

	public RequiredException(String message) {

		super(message);
	}

	public RequiredException(Throwable cause) {

		super(cause);
	}

}
