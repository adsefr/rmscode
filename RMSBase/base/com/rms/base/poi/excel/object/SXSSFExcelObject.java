package com.rms.base.poi.excel.object;

import java.io.File;

import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.rms.base.exception.LoadFileFailureException;

/**
 *
 * @author ri.meisei
 * @since 2015/03/27
 */
public class SXSSFExcelObject extends AbstractExcelObject {

	private final SpreadsheetVersion VERSION = SpreadsheetVersion.EXCEL2007;

	private SXSSFWorkbook sxssfWorkbook;

	public SXSSFExcelObject() {
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
		this.sxssfWorkbook = new SXSSFWorkbook(xssfWorkbook);
		super.workbook = this.sxssfWorkbook;
		super.version = this.VERSION;

	}

	public SXSSFExcelObject(File loadedFile) throws LoadFileFailureException {
		XSSFWorkbook xssfWorkbook = (XSSFWorkbook) WorkbookHelper.load(loadedFile);
		sxssfWorkbook = new SXSSFWorkbook(xssfWorkbook);
		super.workbook = this.sxssfWorkbook;
		super.version = this.VERSION;
		super.loadedFile = loadedFile;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SheetObject getSheetObject(int sheetIndex) {

		Sheet sheet = WorkbookHelper.getSheet(sxssfWorkbook, sheetIndex);

		SheetObject sheetObject = new SXSSFSheetObject(sheet);
		sheetObject.setExcelObject(this);

		return sheetObject;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SheetObject getSheetObject(String sheetName) {

		Sheet sheet = WorkbookHelper.getSheet(sxssfWorkbook, sheetName);

		SheetObject sheetObject = new SXSSFSheetObject(sheet);
		sheetObject.setExcelObject(this);

		return sheetObject;
	}
}
