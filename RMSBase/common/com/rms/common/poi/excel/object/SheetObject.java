package com.rms.common.poi.excel.object;


/**
 *
 * @author ri.meisei
 * @since 2015/03/27
 */
public interface SheetObject {

	void setOwner(ExcelObject excelObject);

	ExcelObject geOwner();
}
