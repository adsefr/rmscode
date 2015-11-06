package com.rms.base.poi.excel.object;

import java.io.File;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.Sheet;

import com.rms.base.exception.LoadFileFailureException;

/**
 *
 * @author ri.meisei
 * @since 2015/03/27
 */
public class HSSFExcelObject extends AbstractExcelObject {

	private final SpreadsheetVersion VERSION = SpreadsheetVersion.EXCEL97;

	private final HSSFWorkbook hssfWorkbook;

	public HSSFExcelObject() {
		this.hssfWorkbook = new HSSFWorkbook();
		super.workbook = this.hssfWorkbook;
		super.version = this.VERSION;

	}

	public HSSFExcelObject(File loadedFile) throws LoadFileFailureException {
		this.hssfWorkbook = (HSSFWorkbook) WorkbookHelper.load(loadedFile);
		super.workbook = this.hssfWorkbook;
		super.version = this.VERSION;
		super.loadedFile = loadedFile;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SheetObject getSheetObject(int sheetIndex) {

		Sheet sheet = WorkbookHelper.getSheet(hssfWorkbook, sheetIndex);

		SheetObject sheetObject = new HSSFSheetObject(sheet);
		sheetObject.setExcelObject(this);

		return sheetObject;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SheetObject getSheetObject(String sheetName) {

		Sheet sheet = WorkbookHelper.getSheet(hssfWorkbook, sheetName);

		SheetObject sheetObject = new HSSFSheetObject(sheet);
		sheetObject.setExcelObject(this);

		return sheetObject;
	}
}
