package com.rms.base.exception;

/**
 * 
 * 
 * @author ri.meisei
 * @since 2014/02/04
 */
public class ParameterRequiredException extends RequiredException {

	private static final long serialVersionUID = 6114158172060280642L;

	public ParameterRequiredException(String message, Throwable cause) {

		super(message, cause);
	}

	public ParameterRequiredException(String message) {

		super(message);
	}

	public ParameterRequiredException(Throwable cause) {

		super(cause);
	}

}
