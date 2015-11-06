package com.rms.base.poi.excel.object;

import java.io.File;

import com.rms.base.exception.LoadFileFailureException;

/**
 *
 * @author ri.meisei
 * @since 2015/03/27
 */
public class ExcelFactory {

	/**
	 * Excel97を新規する場合、利用するメソッド
	 *
	 * @return
	 */
	public static ExcelObject createHSSFExcel() {

		return new HSSFExcelObject();
	}

	/**
	 * Excel2007を新規する場合、利用するメソッド
	 *
	 * @return
	 */
	public static ExcelObject createXSSFExcel() {

		return new XSSFExcelObject();
	}

	/**
	 * Excel2007を新規する場合、利用するメソッド
	 *
	 * @return
	 */
	public static ExcelObject createSXSSFExcel() {

		return new SXSSFExcelObject();
	}

	/**
	 * Excel97をロードする場合、利用するメソッド
	 *
	 * @return
	 * @throws LoadFileFailureException
	 */
	public static ExcelObject loadHSSFExcel(File loadedFile) throws LoadFileFailureException {

		return new HSSFExcelObject(loadedFile);
	}

	/**
	 * Excel2007をロードする場合、利用するメソッド
	 *
	 * @return
	 * @throws LoadFileFailureException
	 */
	public static ExcelObject loadXSSFExcel(File loadedFile) throws LoadFileFailureException {

		return new XSSFExcelObject(loadedFile);
	}

	/**
	 * Excel2007をロードする場合、利用するメソッド
	 *
	 * @return
	 * @throws LoadFileFailureException
	 */
	public static ExcelObject loadSXSSFExcel(File loadedFile) throws LoadFileFailureException {

		return new SXSSFExcelObject(loadedFile);
	}
}
