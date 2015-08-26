package com.rms.base.exception;

/**
 * 操作が失敗した時、発生する例外
 * 
 * @author ri.meisei
 * @since 2013/10/25
 */
class OperationException extends Exception {

	private static final long serialVersionUID = -6291180382666000453L;

	public OperationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {

		super(message, cause, enableSuppression, writableStackTrace);
	}

	public OperationException(String message, Throwable cause) {

		super(message, cause);
	}

	public OperationException(String message) {

		super(message);
	}

	public OperationException(Throwable cause) {

		super(cause);
	}

	public OperationException() {

	}

}
