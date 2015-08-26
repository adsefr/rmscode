package com.rms.common.poi.excel.object.sxssf;

import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import com.rms.common.poi.excel.object.AbstractExcelObject;
import com.rms.common.poi.excel.object.ExcelOperator;
import com.rms.common.poi.excel.object.SheetOperator;
import com.rms.common.poi.excel.object.WorkbookHelper;

/**
 *
 * @author ri.meisei
 * @since 2015/03/27
 */
public class SXSSFExcelObject extends AbstractExcelObject {

	private SXSSFWorkbook sxssfWorkbook;

	public SXSSFExcelObject() {

		this(new SXSSFWorkbook());
	}

	public SXSSFExcelObject(SXSSFWorkbook sxssfWorkbook) {

		super(sxssfWorkbook, SpreadsheetVersion.EXCEL2007);

		this.sxssfWorkbook = sxssfWorkbook;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ExcelOperator getExcelOperator() {

		return new SXSSFExcelOperator(this);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final SheetOperator getSheetOperator() {

		Sheet sheet = WorkbookHelper.getSelectedSheet(workbook);
		SXSSFSheetObject sxssfSheetObject = new SXSSFSheetObject(sheet);
		SXSSFSheetOperator sxssfSheetOperator = new SXSSFSheetOperator();
		sxssfSheetOperator.setSheetObject(sxssfSheetObject);
		return sxssfSheetOperator;

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SheetOperator getSheetOperator(String sheetName) {

		Sheet sheet = WorkbookHelper.getSheet(workbook, sheetName);
		SXSSFSheetObject hssfSheetObject = new SXSSFSheetObject(sheet);
		SXSSFSheetOperator hssfSheetOperator = new SXSSFSheetOperator();
		hssfSheetOperator.setSheetObject(hssfSheetObject);
		return hssfSheetOperator;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SheetOperator getSheetOperator(int sheetIndex) {

		Sheet sheet = WorkbookHelper.getSheet(workbook, sheetIndex);
		SXSSFSheetObject hssfSheetObject = new SXSSFSheetObject(sheet);
		SXSSFSheetOperator hssfSheetOperator = new SXSSFSheetOperator();
		hssfSheetOperator.setSheetObject(hssfSheetObject);
		return hssfSheetOperator;
	}

}
