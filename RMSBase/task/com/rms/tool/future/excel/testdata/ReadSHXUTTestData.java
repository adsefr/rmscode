package com.rms.tool.future.excel.testdata;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.rms.base.exception.LoadFileFailureException;
import com.rms.base.io.constant.FileConst.FileType;
import com.rms.base.io.util.FileHelper;
import com.rms.base.logging.Logger;
import com.rms.base.poi.excel.ExcelFactory;
import com.rms.base.poi.excel.model.CellModel;
import com.rms.base.poi.excel.model.RowModel;
import com.rms.base.poi.excel.model.SheetModel;
import com.rms.base.poi.excel.usereventmodel.ExcelReader;
import com.rms.base.util.TextUtil;

/**
 *
 * @author ri.meisei
 * @since 2015/11/05
 */
public class ReadSHXUTTestData {

	private final static Logger LOGGER = Logger.getLogger(ReadSHXUTTestData.class);

	private String TEST_DATA_FILEPATH = "C:/rapp/r-Suite/WEB-INF/test/java/jp/co/sg_systems/wms/sh/common/data";

	private String TEST_DATA_OUTPUT_PATH = "";

	public ReadSHXUTTestData() {
	}

	@Test
	public void execute() throws LoadFileFailureException {

		List<File> fileList = FileHelper.getFileList(TEST_DATA_FILEPATH, ".+(ShX012).+xlsx", FileType.FILE);
		for (File file : fileList) {
			LOGGER.debug(file.getAbsolutePath());
			ExcelReader excelReader = ExcelFactory.loadOOXMLExcel(file);

			readExcelFile(excelReader);
		}
	}

	private void readExcelFile(ExcelReader excelReader) {

		while (excelReader.hasNextSheet()) {
			SheetModel sheetModel = excelReader.nextSheet();

			// System.out.println(sheetModel.toString());

			if (sheetModel.getSheetName().startsWith("testNormal")) {

				TestDataBean testDataBean = convertSheetModelToTestDataBean(sheetModel);

				System.out.println(testDataBean.toString());
			}
		}
	}

	private TestDataBean convertSheetModelToTestDataBean(SheetModel sheetModel) {

		TestDataBean testDataBean = new TestDataBean();

		testDataBean.setTestCaseName(sheetModel.getSheetName());

		String targetName = null;

		List<RowModel> rowModels = sheetModel.getRowModelCollection();
		for (RowModel rowModel : rowModels) {
			List<CellModel> cellmodels = rowModel.getCellModelCollection();
			if (cellmodels.isEmpty()) {
				continue;
			}
			String flagValue = cellmodels.get(0).stringValue();

			if ((flagValue.startsWith("パラメータ名") || TextUtil.isEquals(flagValue, "テーブル名"))) {
				targetName = cellmodels.get(1).getValue();
				if (!testDataBean.getTestDataCollection().containsKey(targetName)) {
					testDataBean.getTestDataCollection().put(targetName, new ArrayList<>());
				}
			} else if (flagValue.startsWith("列名")) {
				for (int i = 1; i < cellmodels.size(); i++) {
					ColumnInfo columnInfo = new ColumnInfo();
					columnInfo.setName(cellmodels.get(i).stringValue());

					testDataBean.getTestDataCollection().get(targetName).add(columnInfo);
				}
			} else if (flagValue.matches("\\d+")) {
				for (int i = 1; i < cellmodels.size(); i++) {
					String columnValue = cellmodels.get(i).stringValue();
					List<ColumnInfo> columnInfos = testDataBean.getTestDataCollection().get(targetName);
					ColumnInfo columnInfo = columnInfos.get(i - 1);
					columnInfo.getValues().add(columnValue);
				}
			}
		}

		return testDataBean;
	}
}
