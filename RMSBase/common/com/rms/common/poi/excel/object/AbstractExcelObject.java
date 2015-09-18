package com.rms.common.poi.excel.object;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.rms.base.io.IOFactory;
import com.rms.base.io.util.IOUtil;
import com.rms.base.validate.Assertion;

/**
 *
 * @author ri.meisei
 * @since 2015/03/27
 */
public abstract class AbstractExcelObject implements ExcelObject {

	protected final Workbook workbook;

	protected final SpreadsheetVersion version;

	protected AbstractExcelObject(Workbook workbook, SpreadsheetVersion version) {

		Assertion.assertNotNull("workbook", workbook);
		Assertion.assertNotNull("version", version);

		this.workbook = workbook;
		this.version = version;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int getMaxRowIndex() {

		return version.getMaxRows() - 1;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int getMaxRowNumber() {

		return version.getMaxRows();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int getMaxColumnIndex() {

		return version.getMaxColumns() - 1;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int getMaxColumnNumer() {

		return version.getMaxColumns();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int getCellMaxTextLength() {

		return version.getMaxTextLength();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int getNumberOfSheets() {

		return workbook.getNumberOfSheets();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean hasSheet(String sheetName) {

		Sheet sheet = WorkbookHelper.getSheet(workbook, sheetName);

		return (sheet != null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean hasSheet(int sheetIndex) {

		return (0 <= sheetIndex && sheetIndex < workbook.getNumberOfSheets());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getActiveSheetName() {

		return WorkbookHelper.getActiveSheetName(workbook);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean isValidSheetName(String sheetName) {

		return WorkbookHelper.isValidSheetName(sheetName) && hasSheet(sheetName);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean isValidSheetIndex(int sheetIndex) {

		return (sheetIndex >= 0) && hasSheet(sheetIndex);
	}

	// /**
	// * {@inheritDoc}
	// */
	// @Override
	// public boolean isValidRowIndex(int rowIndex) {
	//
	// return (0 <= rowIndex && rowIndex < version.getMaxRows());
	// }
	//
	// /**
	// * {@inheritDoc}
	// */
	// @Override
	// public boolean isValidColumnIndex(int columnIndex) {
	//
	// return (0 <= columnIndex && columnIndex < version.getMaxColumns());
	// }

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getSheetName(int sheetIndex) {

		return WorkbookHelper.getSheetName(workbook, sheetIndex);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int getSheetIndex(String sheetName) {

		return WorkbookHelper.getSheetIndex(workbook, sheetName);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void write(String filePath) throws IOException {

		OutputStream os = IOFactory.newFileOutputStream(new File(filePath));
		write(os);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void write(File targetFile) throws IOException {

		OutputStream os = IOFactory.newFileOutputStream(targetFile);
		write(os);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void write(OutputStream os) throws IOException {

		try {
			workbook.write(os);
			os.flush();
		} finally {
			IOUtil.close(os);
		}
	}

	Workbook getWorkbook() {

		return workbook;
	}
}
