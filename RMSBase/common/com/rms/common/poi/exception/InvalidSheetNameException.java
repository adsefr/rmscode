package com.rms.common.poi.exception;

import com.rms.base.exception.InvalidException;

/**
 * 
 * 
 * @author ri.meisei
 * @since 2014/02/03
 */
public class InvalidSheetNameException extends InvalidException {

	private static final long serialVersionUID = 4046022639942781618L;

	public InvalidSheetNameException(String sheetName) {

		super("invalid sheet name:" + sheetName);
	}
}
