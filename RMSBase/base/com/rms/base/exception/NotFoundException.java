package com.rms.base.exception;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/02/04
 */
public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = -585734532684629635L;

	public NotFoundException() {

		super();
	}

	public NotFoundException(Throwable cause) {

		super(cause);
	}

	public NotFoundException(String message) {

		super(message);
	}

	public NotFoundException(String message, Throwable cause) {

		super(message, cause);
	}

}
