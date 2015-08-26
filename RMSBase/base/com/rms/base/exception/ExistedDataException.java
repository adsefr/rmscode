package com.rms.base.exception;

/**
 * 
 * 
 * @author ri.meisei
 * @since 2014/02/03
 */
public class ExistedDataException extends RuntimeException {

	private static final long serialVersionUID = 82933669978800111L;

	public ExistedDataException(String message, Throwable cause) {

		super(message, cause);
	}

	public ExistedDataException(String message) {

		super(message);
	}

	public ExistedDataException(Throwable cause) {

		super(cause);
	}

}
