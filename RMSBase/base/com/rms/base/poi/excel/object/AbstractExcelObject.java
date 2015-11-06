package com.rms.base.poi.excel.object;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.rms.base.io.util.IOUtil;

/**
 *
 * @author ri.meisei
 * @since 2015/03/27
 */
public abstract class AbstractExcelObject implements ExcelObject {

	protected Workbook workbook;

	protected SpreadsheetVersion version;

	protected File loadedFile;

	public AbstractExcelObject() {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getMinRowNumber() {

		return 1;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getMinColumnNumer() {

		return 1;
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
	public final int getMaxColumnNumer() {

		return version.getMaxColumns();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getMinRowIndex() {

		return 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getMinColumnIndex() {

		return 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getMaxRowIndex() {

		return getMaxRowNumber() - 1;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getMaxColumnIndex() {

		return getMaxColumnNumer() - 1;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isValidRowNumber(int rowNumber) {

		return (getMinRowNumber() <= rowNumber && rowNumber <= getMaxRowNumber());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isValidColumnNumber(int columnNumber) {

		return (getMinColumnNumer() <= columnNumber && columnNumber <= getMaxColumnNumer());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isValidRowIndex(int rowIndex) {

		return (getMinRowIndex() <= rowIndex && rowIndex <= getMaxRowIndex());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isValidColumnIndex(int columnIndex) {

		return (getMinColumnIndex() <= columnIndex && columnIndex <= getMaxColumnIndex());
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
	public final String getActiveSheetName() {

		return WorkbookHelper.getActiveSheetName(workbook);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getActiveSheetIndex() {

		return getSheetIndex(getActiveSheetName());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean setActiveSheet(String sheetName) {

		return WorkbookHelper.setSelectedSheet(workbook, sheetName);

	}

	@Override
	public boolean setActiveSheet(int sheetIndex) {

		return WorkbookHelper.setSelectedSheet(workbook, sheetIndex);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean createSheet(String sheetName) {

		Sheet sheet = WorkbookHelper.createSheet(workbook, sheetName);

		return (sheet != null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean createSheet(String sheetName, int sheetIndex) {

		Sheet sheet = WorkbookHelper.createSheet(workbook, sheetName, sheetIndex);

		return (sheet != null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean removeSheet(String sheetName) {

		return WorkbookHelper.removeSheet(workbook, sheetName);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean removeSheet(int sheetIndex) {

		return WorkbookHelper.removeSheet(workbook, sheetIndex);
	}

	// /**
	// * {@inheritDoc}
	// */
	// @Override
	// public boolean removeSheetsExclude(String... sheetNames) {
	//
	// if (ArrayUtil.isEmpty(sheetNames)) {
	// return false;
	// }
	//
	// boolean isExist = false;
	// for (String sheetName : sheetNames) {
	// if (hasSheet(sheetName)) {
	// isExist = true;
	// break;
	// }
	// }
	//
	// if (isExist) {
	// List<String> sheetNameList = Arrays.asList(sheetNames);
	// int number = workbook.getNumberOfSheets();
	// for (int sheetIndex = 0; sheetIndex < number; sheetIndex++) {
	// String sheetName = WorkbookHelper.getSheetName(workbook, sheetIndex);
	// if (!sheetNameList.contains(sheetName)) {
	// WorkbookHelper.removeSheet(workbook, sheetName);
	// }
	// }
	// }
	//
	// return isExist;
	// }
	//
	// /**
	// * {@inheritDoc}
	// */
	// @Override
	// public boolean removeSheetsExclude(int... sheetIndexs) {
	//
	// if (ArrayUtil.isEmpty(sheetIndexs)) {
	// return false;
	// }
	//
	// String[] sheetNames = new String[sheetIndexs.length];
	// for (int i = 0; i < sheetIndexs.length; i++) {
	// sheetNames[i] = getSheetName(sheetIndexs[i]);
	// }
	//
	// return removeSheetsExclude(sheetNames);
	// }
	//
	// /**
	// * {@inheritDoc}
	// */
	// @Override
	// public final void write(String filePath) throws IOException {
	//
	// OutputStream os = IOFactory.newFileOutputStream(new File(filePath));
	// write(os);
	// }
	//
	// /**
	// * {@inheritDoc}
	// */
	// @Override
	// public final void write(File targetFile) throws IOException {
	//
	// OutputStream os = IOFactory.newFileOutputStream(targetFile);
	// write(os);
	// }

	/**
	 * {@inheritDoc}
	 */
	@Override
	public File getloadedFile() {

		return loadedFile;
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

	@Override
	public void close() {

		IOUtil.close(workbook);
	}

}
