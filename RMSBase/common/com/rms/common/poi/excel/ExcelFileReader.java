package com.rms.common.poi.excel;

import java.io.File;

import com.rms.base.exception.LoadFileFailureException;
import com.rms.base.poi.excel.object.ExcelFactory;
import com.rms.base.poi.excel.object.ExcelObject;

/**
 *
 * @author ri.meisei
 * @since 2015/11/05
 */
public class ExcelFileReader {

	private ExcelObject excelObject;

	public ExcelFileReader(File loadedFile) throws LoadFileFailureException {
		excelObject = ExcelFactory.loadSXSSFExcel(loadedFile);
	}

	public ExcelObject getExcelObject() {

		return excelObject;
	}

}
