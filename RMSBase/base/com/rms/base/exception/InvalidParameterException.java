package com.rms.base.exception;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/02/03
 */
public class InvalidParameterException extends InvalidException {

	private static final long serialVersionUID = 82933669978800111L;

	public InvalidParameterException(String message, Throwable cause) {

		super(message, cause);
	}

	public InvalidParameterException(String message) {

		super(message);
	}

	public InvalidParameterException(Throwable cause) {

		super(cause);
	}

}
