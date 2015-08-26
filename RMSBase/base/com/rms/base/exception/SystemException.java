package com.rms.base.exception;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/02/26
 */
public class SystemException extends RuntimeException {

	private static final long serialVersionUID = 7307947720210608274L;

	public SystemException() {

		super();
	}

	public SystemException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {

		super(message, cause, enableSuppression, writableStackTrace);
	}

	public SystemException(String message, Throwable cause) {

		super(message, cause);
	}

	public SystemException(String message) {

		super(message);
	}

	public SystemException(Throwable cause) {

		super(cause);
	}

}
