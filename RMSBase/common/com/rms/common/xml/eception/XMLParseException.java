package com.rms.common.xml.eception;

/**
 * 
 * 
 * @author ri.meisei
 * @since 2014/01/21
 */
public class XMLParseException extends Exception {

	private static final long serialVersionUID = -2799275358726727352L;

	public XMLParseException(String message, Throwable cause) {

		super(message, cause);
	}

	public XMLParseException(String message) {

		super(message);
	}

	public XMLParseException(Throwable cause) {

		super(cause);
	}

}
