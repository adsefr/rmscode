package com.rms.common.poi.excel.object;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.rms.base.validate.Assertion;
import com.rms.common.poi.excel.exception.ExcelLoadFailureException;
import com.rms.common.poi.excel.object.hssf.HSSFExcelObject;
import com.rms.common.poi.excel.object.sxssf.SXSSFExcelObject;
import com.rms.common.poi.excel.object.xssf.XSSFExcelObject;

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
	public static ExcelObject createExcel97() {

		return new HSSFExcelObject();
	}

	/**
	 * Excel2007を新規する場合、利用するメソッド
	 *
	 * @return
	 */
	public static ExcelObject createExcel2007() {

		return new XSSFExcelObject();
	}

	/**
	 * Excel2007を新規する場合、利用するメソッド
	 *
	 * @return
	 */
	public static ExcelObject createExcel2007Stream() {

		return new SXSSFExcelObject();
	}

	/**
	 * Excelファイルをロードする時、利用するメソッド
	 *
	 * @param filePath
	 * @return
	 * @throws ExcelLoadFailureException
	 */
	public static ExcelObject load(String filePath) throws ExcelLoadFailureException {

		return load(new File(filePath));
	}

	/**
	 * Excelファイルをロードする時、利用するメソッド
	 *
	 * @param file
	 * @return
	 * @throws ExcelLoadFailureException
	 */
	public static ExcelObject load(File file) throws ExcelLoadFailureException {

		try {
			return load(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			throw new ExcelLoadFailureException(e);
		}
	}

	/**
	 * Excelファイルをロードする時、利用するメソッド
	 *
	 * @param inputStream
	 * @return
	 * @throws ExcelLoadFailureException
	 */
	public static ExcelObject load(InputStream inputStream) throws ExcelLoadFailureException {

		try {
			return load(WorkbookFactory.create(inputStream));
		} catch (InvalidFormatException | IOException e) {
			throw new ExcelLoadFailureException(e);
		}
	}

	/**
	 *
	 * @param workbook
	 * @return
	 */
	private static ExcelObject load(Workbook workbook) {

		Assertion.assertNotNull("workbook", workbook);

		if (workbook instanceof HSSFWorkbook) {
			return new HSSFExcelObject((HSSFWorkbook) workbook);
		}

		if (workbook instanceof XSSFWorkbook) {
			return new XSSFExcelObject((XSSFWorkbook) workbook);
		}

		if (workbook instanceof SXSSFWorkbook) {
			return new SXSSFExcelObject((SXSSFWorkbook) workbook);
		}

		return null;
	}
}
