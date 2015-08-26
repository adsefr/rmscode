package com.rms.base.exception;

import java.io.IOException;

/**
 * 初期化失敗した場合、発生する例外
 * 
 * @author ri.meisei
 * @since 2013/11/01
 */
public class IOInitializationException extends IOException {

	private static final long serialVersionUID = 3623308652079623785L;

	/**
	 *
	 */
	public IOInitializationException() {

		super();
		// TODO 自動生成されたコンストラクター・スタブ
	}

	/**
	 * @param message
	 * @param cause
	 */
	public IOInitializationException(String message, Throwable cause) {

		super(message, cause);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	/**
	 * @param message
	 */
	public IOInitializationException(String message) {

		super(message);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	/**
	 * @param cause
	 */
	public IOInitializationException(Throwable cause) {

		super(cause);
		// TODO 自動生成されたコンストラクター・スタブ
	}

}
