package com.rms.tool.future.excel.testdata;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import com.rms.base.constant.Encodes;
import com.rms.base.exception.LoadFileFailureException;
import com.rms.base.io.IOFactory;
import com.rms.base.io.constant.FileConst.FileType;
import com.rms.base.io.util.FileHelper;
import com.rms.base.logging.Logger;
import com.rms.base.poi.excel.ExcelFactory;
import com.rms.base.poi.excel.model.CellModel;
import com.rms.base.poi.excel.model.RowModel;
import com.rms.base.poi.excel.model.SheetModel;
import com.rms.base.poi.excel.usereventmodel.ExcelReader;
import com.rms.base.util.ArrayUtil;
import com.rms.base.util.TextUtil;
import com.rms.tool.future.db.ReadDatabaseERDInfo;
import com.rms.tool.future.erd.bean.TableInfo;

/**
 *
 * @author ri.meisei
 * @since 2015/11/05
 */
public class ReadSHXUTTestData {

	private final static Logger LOGGER = Logger.getLogger(ReadSHXUTTestData.class);

	private String TEST_DATA_FILEPATH = "C:/rapp/r-Suite/WEB-INF/test/java/jp/co/sg_systems/wms/sh/common/data";

	private String TEST_DATA_OUTPUT_PATH = "";

	private Set<TableInfo> tableInfoCollection = null;

	public ReadSHXUTTestData() {
		tableInfoCollection = new ReadDatabaseERDInfo().readDBERDInfo();
	}

	@Test
	public void execute() throws LoadFileFailureException {

		List<File> fileList = FileHelper.getFileList(TEST_DATA_FILEPATH, "WmsShXSyukkoJissekiSakseiYobidasi_単体テスト仕様書.+xlsx", FileType.FILE);
		for (File file : fileList) {

			LOGGER.debug(file.getAbsolutePath());

			ExcelReader excelReader = ExcelFactory.loadOOXMLExcel(file);

			readExcelFile(excelReader);
		}
	}

	private void readExcelFile(ExcelReader excelReader) {

		while (excelReader.hasNextSheet()) {
			SheetModel sheetModel = excelReader.nextSheet();
			if (sheetModel.getSheetName().startsWith("testNormal")) {

				TestDataBean testDataBean = convertSheetModelToTestDataBean(sheetModel);

				LOGGER.debug(testDataBean.toString());

				replaceDBIitemInfo(testDataBean);
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

	private void replaceDBIitemInfo(TestDataBean testDataBean) {

		String path = "C:/document/作業内容/【20151104_PD,PGの品質不良個所の影響調査】/PD,PGの品質不良個所の影響調査_中略項目の確認_SHX011-B01";
		List<File> files = FileHelper.getFileList(path, "[^_testNormal0-9]+txt", FileType.FILE);
		for (File file : files) {
			LOGGER.debug(file.getName());
			String outputFilePath = (file.getAbsolutePath().replace(".txt", "_") + testDataBean.getTestCaseName()) + ".txt";
			File outputFile = new File(outputFilePath);
			try (BufferedReader reader = IOFactory.newBufferedReader(Encodes.CHARSET_SJIS, file); BufferedWriter writer = IOFactory.newBufferedWriter(outputFile, Encodes.CHARSET_SJIS)) {
				String line = null;
				while ((line = reader.readLine()) != null) {
					String replacedLine = replaceItemInfo(line, testDataBean);
					LOGGER.debug(line + "　⇒　" + replacedLine);
					writer.write(replacedLine);
					writer.newLine();
				}
			} catch (IOException e) {
				LOGGER.debug(e);
			}
		}
	}

	private String replaceItemInfo(String line, TestDataBean testDataBean) {

		String temp = line;
		Pattern pattern = Pattern.compile("[a-zA-Z_]+\\.[a-zA-Z0-9_]+");
		Matcher matcher = pattern.matcher(line);

		int index = 0;
		while (matcher.find(index)) {
			int start = matcher.start();
			int end = matcher.end();
			String matched = line.substring(start, end);
			String tableName = matched.substring(0, matched.indexOf("."));
			String columnName = matched.substring(matched.indexOf(".") + 1);
			String nameJP = getTableName(tableName, columnName);
			String valueInfo = getValueInfo(tableName, columnName, testDataBean);
			temp = temp.replace(matched, tableName + "\t" + columnName + "\t" + nameJP + "\t" + valueInfo);
			index = end;
		}

		return temp;
	}

	private String getTableName(String tableName, String columnName) {

		for (TableInfo tableInfo : tableInfoCollection) {
			if (TextUtil.isEquals(tableInfo.getTableName(), tableName)) {
				for (Iterator<com.rms.tool.future.erd.bean.ColumnInfo> iterator = tableInfo.getColumnInfos().iterator(); iterator.hasNext();) {
					com.rms.tool.future.erd.bean.ColumnInfo columnInfo = iterator.next();
					if (TextUtil.isEquals(columnInfo.getColumnName(), columnName)) {
						return tableInfo.getTableNameJP() + "." + columnInfo.getColumnNameJP();
					}

				}
			}
		}
		return tableName + "." + columnName;
	}

	private String getValueInfo(String tableName, String columnName, TestDataBean testDataBean) {

		List<ColumnInfo> columnInfons = testDataBean.getTestDataCollection().get(tableName);
		if (columnInfons == null) {
			return "";
		}
		for (ColumnInfo columnInfo : columnInfons) {
			if (TextUtil.isEquals(columnInfo.getName(), columnName)) {
				return "{" + ArrayUtil.join(columnInfo.getValues(), ", ") + "}";
			}
		}

		return "{}";
	}
}
