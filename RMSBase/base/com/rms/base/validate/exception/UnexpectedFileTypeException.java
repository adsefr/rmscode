package com.rms.base.validate.exception;

/**
 * 予想外のタイプのデータが受け取った場合、発生する例外
 *
 * @author ri.meisei
 * @since 2013/11/01
 */
public class UnexpectedFileTypeException extends RuntimeException {

	private static final long serialVersionUID = -916299522488699893L;

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public UnexpectedFileTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {

		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public UnexpectedFileTypeException(String message, Throwable cause) {

		super(message, cause);
	}

	/**
	 * @param message
	 */
	public UnexpectedFileTypeException(String message) {

		super(message);
	}

	/**
	 * @param cause
	 */
	public UnexpectedFileTypeException(Throwable cause) {

		super(cause);
	}
}
