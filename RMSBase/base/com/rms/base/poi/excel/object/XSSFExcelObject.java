package com.rms.base.poi.excel.object;

import java.io.File;

import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.rms.base.exception.LoadFileFailureException;

/**
 *
 * @author ri.meisei
 * @since 2015/03/27
 */
public class XSSFExcelObject extends AbstractExcelObject {

	private final SpreadsheetVersion VERSION = SpreadsheetVersion.EXCEL2007;

	private XSSFWorkbook xssfWorkbook;

	public XSSFExcelObject() {
		this.xssfWorkbook = new XSSFWorkbook();
		super.workbook = this.xssfWorkbook;
		super.version = this.VERSION;
	}

	public XSSFExcelObject(File loadedFile) throws LoadFileFailureException {
		this.xssfWorkbook = (XSSFWorkbook) WorkbookHelper.load(loadedFile);
		super.workbook = this.xssfWorkbook;
		super.version = this.VERSION;
		super.loadedFile = loadedFile;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SheetObject getSheetObject(int sheetIndex) {

		Sheet sheet = WorkbookHelper.getSheet(xssfWorkbook, sheetIndex);

		SheetObject sheetObject = new XSSFSheetObject(sheet);
		sheetObject.setExcelObject(this);

		return sheetObject;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SheetObject getSheetObject(String sheetName) {

		Sheet sheet = WorkbookHelper.getSheet(xssfWorkbook, sheetName);

		SheetObject sheetObject = new XSSFSheetObject(sheet);
		sheetObject.setExcelObject(this);

		return sheetObject;
	}
}
