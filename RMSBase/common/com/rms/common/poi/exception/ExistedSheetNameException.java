package com.rms.common.poi.exception;

import com.rms.base.exception.ExistedDataException;

/**
 * 
 * 
 * @author ri.meisei
 * @since 2014/02/03
 */
public class ExistedSheetNameException extends ExistedDataException {

	private static final long serialVersionUID = 4046022639942781618L;

	public ExistedSheetNameException(String sheetName) {

		super("invalid sheet name:" + sheetName);
	}
}
