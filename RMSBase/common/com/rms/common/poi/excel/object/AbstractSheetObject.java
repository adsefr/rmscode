package com.rms.common.poi.excel.object;

import org.apache.poi.ss.usermodel.Sheet;

/**
 *
 * @author ri.meisei
 * @since 2015/03/27
 */
public class AbstractSheetObject implements SheetObject {

	private ExcelObject owner;

	public AbstractSheetObject(Sheet sheet) {

	}

	@Override
	public final void setOwner(ExcelObject owner) {

		this.owner = owner;
	}

	@Override
	public final ExcelObject geOwner() {

		return owner;
	}

}
