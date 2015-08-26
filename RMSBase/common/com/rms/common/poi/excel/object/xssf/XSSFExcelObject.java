package com.rms.common.poi.excel.object.xssf;

import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.rms.common.poi.excel.object.AbstractExcelObject;
import com.rms.common.poi.excel.object.ExcelOperator;
import com.rms.common.poi.excel.object.SheetOperator;
import com.rms.common.poi.excel.object.WorkbookHelper;

/**
 *
 * @author ri.meisei
 * @since 2015/03/27
 */
public class XSSFExcelObject extends AbstractExcelObject {

	private final XSSFWorkbook xssfWorkbook;

	public XSSFExcelObject() {

		this(new XSSFWorkbook());
	}

	public XSSFExcelObject(XSSFWorkbook xssfWorkbook) {

		super(xssfWorkbook, SpreadsheetVersion.EXCEL2007);

		this.xssfWorkbook = (XSSFWorkbook) xssfWorkbook;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final ExcelOperator getExcelOperator() {

		return new XSSFExcelOperator(this);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final SheetOperator getSheetOperator() {

		Sheet sheet = WorkbookHelper.getSelectedSheet(workbook);
		XSSFSheetObject xssfSheetObject = new XSSFSheetObject(sheet);
		XSSFSheetOperator xssfSheetOperator = new XSSFSheetOperator();
		xssfSheetOperator.setSheetObject(xssfSheetObject);
		return xssfSheetOperator;

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SheetOperator getSheetOperator(String sheetName) {

		Sheet sheet = WorkbookHelper.getSheet(workbook, sheetName);
		XSSFSheetObject xssfSheetObject = new XSSFSheetObject(sheet);
		XSSFSheetOperator xssfSheetOperator = new XSSFSheetOperator();
		xssfSheetOperator.setSheetObject(xssfSheetObject);
		return xssfSheetOperator;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SheetOperator getSheetOperator(int sheetIndex) {

		Sheet sheet = WorkbookHelper.getSheet(workbook, sheetIndex);
		XSSFSheetObject xssfSheetObject = new XSSFSheetObject(sheet);
		XSSFSheetOperator xssfSheetOperator = new XSSFSheetOperator();
		xssfSheetOperator.setSheetObject(xssfSheetObject);
		return xssfSheetOperator;
	}

}
