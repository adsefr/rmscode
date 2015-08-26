package com.rms.base.exception;

/**
 * 予想外のデータが受け取った場合、発生する例外
 * 
 * @author ri.meisei
 * @since 2013/11/01
 */
public class UnexpectedDataException extends RuntimeException {

	private static final long serialVersionUID = -916299522488699893L;

	public UnexpectedDataException(String message) {

		super(message);
	}
}
