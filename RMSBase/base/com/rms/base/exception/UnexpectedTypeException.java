package com.rms.base.exception;

/**
 * 予想外のタイプのデータが受け取った場合、発生する例外
 * 
 * @author ri.meisei
 * @since 2013/11/01
 */
public class UnexpectedTypeException extends RuntimeException {

	private static final long serialVersionUID = -916299522488699893L;

	public UnexpectedTypeException(String actual) {

		super("type is" + actual);
	}

	public UnexpectedTypeException(String expected, String actual) {

		super("expected:" + expected + " actual:" + actual);
	}
}
