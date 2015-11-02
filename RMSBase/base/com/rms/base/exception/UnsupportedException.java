package com.rms.base.exception;

/**
 *
 * @author ri.meisei
 * @since 2015/11/02
 */
public class UnsupportedException extends ApplicationException {

	public UnsupportedException() {
		super();
	}

	public UnsupportedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UnsupportedException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnsupportedException(String message) {
		super(message);
	}

	public UnsupportedException(Throwable cause) {
		super(cause);
	}
}
