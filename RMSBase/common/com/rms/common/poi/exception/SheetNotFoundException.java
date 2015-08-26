package com.rms.common.poi.exception;

import com.rms.base.exception.NotFoundException;

/**
 * 
 * 
 * @author ri.meisei
 * @since 2014/02/04
 */
public class SheetNotFoundException extends NotFoundException {

	private static final long serialVersionUID = -2676666100768141664L;

	public SheetNotFoundException() {

		super();
	}

	public SheetNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {

		super(message, cause, enableSuppression, writableStackTrace);
	}

	public SheetNotFoundException(String message, Throwable cause) {

		super(message, cause);
	}

	public SheetNotFoundException(String message) {

		super(message);
	}

	public SheetNotFoundException(Throwable cause) {

		super(cause);
	}

}
