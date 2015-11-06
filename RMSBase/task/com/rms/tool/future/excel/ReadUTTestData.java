package com.rms.tool.future.excel;

import java.io.File;
import java.util.List;

import org.junit.Test;

import com.rms.base.exception.LoadFileFailureException;
import com.rms.base.io.constant.FileConst.FileType;
import com.rms.base.io.util.FileHelper;
import com.rms.base.logging.Logger;
import com.rms.base.poi.excel.object.ExcelObject;
import com.rms.base.poi.excel.object.SheetObject;
import com.rms.common.poi.excel.ExcelFileReader;

/**
 *
 * @author ri.meisei
 * @since 2015/11/05
 */
public class ReadUTTestData {

	private final static Logger LOGGER = Logger.getLogger(ReadUTTestData.class);

	private String TEST_DATA_FILEPATH = "C:/rapp/r-Suite/WEB-INF/test/java/jp/co/sg_systems/wms/sh/common/data";

	private String TEST_DATA_OUTPUT_PATH = "";

	public ReadUTTestData() {
	}

	@Test
	public void execute() {

		List<File> fileList = FileHelper.getFileList(TEST_DATA_FILEPATH, null, FileType.FILE);
		for (File file : fileList) {
			LOGGER.debug(file.getAbsolutePath());
			try {
				ExcelFileReader excelFileReader = new ExcelFileReader(file);
				ExcelObject excelObject = excelFileReader.getExcelObject();
				readExcelFile(excelObject);
				excelObject.close();
			} catch (LoadFileFailureException e) {
				LOGGER.debug(e);
			}
		}
	}

	private void readExcelFile(ExcelObject excelObject) {

		int sheetNumber = excelObject.getNumberOfSheets();
		for (int sheetIndex = 0; sheetIndex < sheetNumber; sheetIndex++) {
			SheetObject sheetObject = excelObject.getSheetObject(sheetIndex);
			LOGGER.debug(excelObject.getloadedFile().getName() + "-" + excelObject.getSheetName(sheetIndex));
		}
	}
}
