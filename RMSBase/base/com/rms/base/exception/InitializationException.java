package com.rms.base.exception;

/**
 * 初期化失敗した場合、発生する例外
 *
 * @author ri.meisei
 * @since 2013/11/01
 */
public class InitializationException extends SystemException {

	private static final long serialVersionUID = 3623308652079623785L;

	public InitializationException(String message, Throwable cause) {

		super(message, cause);
	}

	public InitializationException(String message) {

		super(message);
	}

	public InitializationException(Throwable cause) {

		super(cause);
	}

}
