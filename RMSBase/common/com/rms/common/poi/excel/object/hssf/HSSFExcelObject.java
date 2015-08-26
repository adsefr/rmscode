package com.rms.common.poi.excel.object.hssf;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.Sheet;

import com.rms.common.poi.excel.object.AbstractExcelObject;
import com.rms.common.poi.excel.object.ExcelOperator;
import com.rms.common.poi.excel.object.SheetOperator;
import com.rms.common.poi.excel.object.WorkbookHelper;

/**
 *
 * @author ri.meisei
 * @since 2015/03/27
 */
public class HSSFExcelObject extends AbstractExcelObject {

	private final HSSFWorkbook hssfWorkbook;

	public HSSFExcelObject() {

		this(new HSSFWorkbook());
	}

	public HSSFExcelObject(HSSFWorkbook hssfWorkbook) {

		super(hssfWorkbook, SpreadsheetVersion.EXCEL97);

		this.hssfWorkbook = hssfWorkbook;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final ExcelOperator getExcelOperator() {

		return new HSSFExcelOperator(this);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final SheetOperator getSheetOperator() {

		Sheet sheet = WorkbookHelper.getSelectedSheet(workbook);
		HSSFSheetObject hssfSheetObject = new HSSFSheetObject(sheet);
		HSSFSheetOperator hssfSheetOperator = new HSSFSheetOperator();
		hssfSheetOperator.setSheetObject(hssfSheetObject);
		return hssfSheetOperator;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SheetOperator getSheetOperator(String sheetName) {

		Sheet sheet = WorkbookHelper.getSheet(workbook, sheetName);
		HSSFSheetObject hssfSheetObject = new HSSFSheetObject(sheet);
		HSSFSheetOperator hssfSheetOperator = new HSSFSheetOperator();
		hssfSheetOperator.setSheetObject(hssfSheetObject);
		return hssfSheetOperator;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SheetOperator getSheetOperator(int sheetIndex) {

		Sheet sheet = WorkbookHelper.getSheet(workbook, sheetIndex);
		HSSFSheetObject hssfSheetObject = new HSSFSheetObject(sheet);
		HSSFSheetOperator hssfSheetOperator = new HSSFSheetOperator();
		hssfSheetOperator.setSheetObject(hssfSheetObject);
		return hssfSheetOperator;
	}
}
