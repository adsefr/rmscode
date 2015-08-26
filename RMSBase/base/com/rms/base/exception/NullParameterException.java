package com.rms.base.exception;

/**
 * nullのパラメータを受け取った場合、発生する例外
 * 
 * @author ri.meisei
 * @since 2013/11/01
 */
public class NullParameterException extends UnexpectedDataException {

	private static final long serialVersionUID = -916299522488699893L;

	public NullParameterException(String message) {

		super(message);
	}
}
