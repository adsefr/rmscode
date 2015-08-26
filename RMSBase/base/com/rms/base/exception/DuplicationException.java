package com.rms.base.exception;

/**
 * 
 * 
 * @author ri.meisei
 * @since 2014/02/04
 */
public class DuplicationException extends RuntimeException {

	private static final long serialVersionUID = -4772023432181924524L;

	public DuplicationException(String message, Throwable cause) {

		super(message, cause);
	}

	public DuplicationException(String message) {

		super(message);
	}

	public DuplicationException(Throwable cause) {

		super(cause);
	}

}
