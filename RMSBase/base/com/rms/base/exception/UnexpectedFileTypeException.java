package com.rms.base.exception;

/**
 * 予想外のタイプのデータが受け取った場合、発生する例外
 *
 * @author ri.meisei
 * @since 2013/11/01
 */
public class UnexpectedFileTypeException extends RuntimeException {

	private static final long serialVersionUID = -916299522488699893L;

	public UnexpectedFileTypeException(String expected, String actual, String filePath) {

		super("expected:" + expected + " actual:" + actual + "[" + filePath + "]");
	}
}
