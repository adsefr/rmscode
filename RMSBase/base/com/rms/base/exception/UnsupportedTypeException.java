package com.rms.base.exception;


/**
 *
 *
 * @author ri.meisei
 * @since 2014/06/06
 */
public class UnsupportedTypeException extends ApplicationException {

	private static final long serialVersionUID = -2824720275256316121L;

	public UnsupportedTypeException(Class<?> clazz) {

		super(clazz.getName());
	}

	public UnsupportedTypeException(String typeName) {

		super(typeName);
	}
}
