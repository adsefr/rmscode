package com.rms.common.poi.excel.exception;

import com.rms.base.exception.FailureException;

public class ExcelLoadFailureException extends FailureException {

	public ExcelLoadFailureException(String message, Throwable cause) {

		super(message, cause);
	}

	public ExcelLoadFailureException(String message) {

		super(message);
	}

	public ExcelLoadFailureException(Throwable cause) {

		super(cause);
	}

}
