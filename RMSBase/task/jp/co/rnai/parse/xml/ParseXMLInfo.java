package jp.co.rnai.parse.xml;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.DocumentException;

import com.rms.base.constant.Characters;
import com.rms.base.io.constant.FileConst.FileType;
import com.rms.base.io.util.FileHelper;
import com.rms.base.poi.excel.usermodel.ExcelFactory;
import com.rms.base.util.TextUtil;
import com.rms.common.xml.bean.backup.DocInfo;
import com.rms.common.xml.bean.backup.DocumentInfo;
import com.rms.common.xml.bean.backup.ElementInfo;
import com.rms.common.xml.impl.Dom4jXMLParser2;

public class ParseXMLInfo {

	private static final String XML_FILE_REGEX = ".+\\.(xml|XML)(\\.Meta)?";

	private static final String NAME_FORMAT_STRING = "_SAMPLE_DATA.xlsx";

	private static final String DIR_OUTPUT_INFO_STRING = "D:/work/data/AIP/xml/";

	private static Map<String, POIOperator> excelWriterMap = new HashMap<String, POIOperator>();

	public static void main(String[] args) {

		String dirPath = "D:/work/docment/受領資料/20130918メタデータ・ＰＦ提供資料/";

		FileHelper.delete(DIR_OUTPUT_INFO_STRING, false);
		try {
			ParseXMLInfo.parseXML(dirPath, XML_FILE_REGEX);
		} catch (POIException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	}

	private static void parseXML(String dirPath, String regex) throws POIException, IOException, DocumentException {

		List<File> xmlFileList = FileHelper.getFileList(dirPath, regex, FileType.FILE, true);

		Dom4jXMLParser2 dom4jParser = new Dom4jXMLParser2();

		while (!xmlFileList.isEmpty()) {

			File currFile = xmlFileList.remove(0);

			System.out.println(currFile.getAbsolutePath());

			DocumentInfo documentInfo = dom4jParser.parse(currFile, getEncoding(currFile));

			writeToExcel(documentInfo);
		}

		for (POIOperator operator : excelWriterMap.values()) {
			operator.write();
		}
	}

	private static String getEncoding(File file) {

		String filePath = file.getAbsolutePath();

		if (filePath.contains("aip4jst_782_2013")) {// TODO
			return Characters.ENCODE_SJIS;
		}
		if (filePath.contains("15.J-STAGEメタデータ(XML)")) {
			return Characters.ENCODE_SJIS;
		}
		if (filePath.contains("15.J-STAGEメタデータ(XML)")) {
			return Characters.ENCODE_SJIS;
		}
		if (filePath.contains("16.エルゼビアメタデータ(XML)")) {
			return Characters.ENCODE_UTF8;
		}
		if (filePath.contains("17.AIPメタデータ(XML)")) {
			return Characters.ENCODE_SJIS;
		}
		if (filePath.contains("18-19.Springerメタデータ(XML-PDF)")) {
			return Characters.ENCODE_UTF8;
		}
		if (filePath.contains("20.メタデータ(LCAS)")) {
			return Characters.ENCODE_UTF8;
		}
		if (filePath.contains("25.CAPCASToCデータ")) {
			return Characters.ENCODE_UTF8;
		}
		if (filePath.contains("issue")) {
			return Characters.ENCODE_UTF8;
		}

		return null;
	}

	private static POIOperator getPOIOperator(DocumentInfo documentInfo) throws POIException {

		String key = documentInfo.getSystemID();

		if (TextUtil.isBlank(key)) {
			key = documentInfo.getSchemaLocation();
		}
		if (key.contains("/")) {
			int index = key.lastIndexOf("/");
			key = key.substring(index + 1);
		}

		POIOperator operator = excelWriterMap.get(key);

		if (operator == null) {
			String path = DIR_OUTPUT_INFO_STRING + key + NAME_FORMAT_STRING;
			operator = ExcelFactory.newPOIOperator(path);
			excelWriterMap.put(key, operator);
			createSheet(operator, key);
		}

		return operator;
	}

	private static void createSheet(POIOperator operator, String sheetName) {

		operator.setCurrentSheet(sheetName);
		operator.setAutoFilter(1, 1048576, 1, getHeadInfo().size());
	}

	private static void writeToExcel(DocumentInfo documentInfo) throws POIException {

		POIOperator operator = getPOIOperator(documentInfo);// TODO

		// 一行目にヘッダを書き込む
		if (operator.getLastRowNo() == 0) {
			operator.setCellValues(operator.getFirstRowNo(), getHeadInfo());
		}

		List<String> rowDataList = new ArrayList<String>();

		for (ElementInfo elementInfo : documentInfo.getElementInfoList()) {

			rowDataList.add(documentInfo.getDocmentFilePath());
			rowDataList.add(String.valueOf(elementInfo.getLevel()));
			rowDataList.add(elementInfo.getPath());
			rowDataList.add(String.valueOf(elementInfo.getTimes()));
			rowDataList.add(elementInfo.getName());
			rowDataList.add(elementInfo.getTextValue());

			Map<String, String> attributeMap = elementInfo.getAttribueMap();

			for (String attribute : attributeMap.keySet()) {
				rowDataList.add(attribute);
				rowDataList.add(attributeMap.get(attribute));
			}

			if (operator.isMaxRowNo()) {
				String sheetName = operator.getSheetName(0);
				sheetName += "_" + (operator.getNumberOfSheets() + 1);
				createSheet(operator, sheetName);
				operator.setCellValues(operator.getFirstRowNo(), getHeadInfo());
			}

			if (operator.getLastRowNo() == 1048575) {
				String sheetName = operator.getSheetName(0);
				sheetName = sheetName + "_" + operator.getNumberOfSheets();
				operator.addSheet(sheetName);
				operator.setCurrentSheet(sheetName);
			}
			operator.setCellValues(operator.getLastRowNo() + 1, rowDataList);
			rowDataList.clear();
		}
	}

	private static List<String> getHeadInfo() {

		List<String> headInfoList = new ArrayList<String>();
		headInfoList.add(DocInfo.HEAD_DOC_PATH);
		headInfoList.add(DocInfo.HEAD_ELE_LEVEL);
		headInfoList.add(DocInfo.HEAD_ELE_XPATH);
		headInfoList.add(DocInfo.HEAD_ELE_XPATH_TIMES);
		headInfoList.add(DocInfo.HEAD_ELE_NAME);
		headInfoList.add(DocInfo.HEAD_ELE_TEXT);
		headInfoList.add(DocInfo.HEAD_ELE_ATTR_NAME_01);
		headInfoList.add(DocInfo.HEAD_ELE_ATTR_VALUE_01);
		headInfoList.add(DocInfo.HEAD_ELE_ATTR_NAME_02);
		headInfoList.add(DocInfo.HEAD_ELE_ATTR_VALUE_02);
		headInfoList.add(DocInfo.HEAD_ELE_ATTR_NAME_03);
		headInfoList.add(DocInfo.HEAD_ELE_ATTR_VALUE_03);
		headInfoList.add(DocInfo.HEAD_ELE_ATTR_NAME_04);
		headInfoList.add(DocInfo.HEAD_ELE_ATTR_VALUE_04);
		headInfoList.add(DocInfo.HEAD_ELE_ATTR_NAME_05);
		headInfoList.add(DocInfo.HEAD_ELE_ATTR_VALUE_05);
		headInfoList.add(DocInfo.HEAD_ELE_ATTR_NAME_06);
		headInfoList.add(DocInfo.HEAD_ELE_ATTR_VALUE_06);
		headInfoList.add(DocInfo.HEAD_ELE_ATTR_NAME_07);
		headInfoList.add(DocInfo.HEAD_ELE_ATTR_VALUE_07);
		headInfoList.add(DocInfo.HEAD_ELE_ATTR_NAME_08);
		headInfoList.add(DocInfo.HEAD_ELE_ATTR_VALUE_08);
		headInfoList.add(DocInfo.HEAD_ELE_ATTR_NAME_09);
		headInfoList.add(DocInfo.HEAD_ELE_ATTR_VALUE_09);
		headInfoList.add(DocInfo.HEAD_ELE_ATTR_NAME_10);
		headInfoList.add(DocInfo.HEAD_ELE_ATTR_VALUE_10);
		headInfoList.add(DocInfo.HEAD_ELE_ATTR_NAME_11);
		headInfoList.add(DocInfo.HEAD_ELE_ATTR_VALUE_11);
		headInfoList.add(DocInfo.HEAD_ELE_ATTR_NAME_12);
		headInfoList.add(DocInfo.HEAD_ELE_ATTR_VALUE_12);
		headInfoList.add(DocInfo.HEAD_ELE_ATTR_NAME_13);
		headInfoList.add(DocInfo.HEAD_ELE_ATTR_VALUE_13);
		headInfoList.add(DocInfo.HEAD_ELE_ATTR_NAME_14);
		headInfoList.add(DocInfo.HEAD_ELE_ATTR_VALUE_14);
		headInfoList.add(DocInfo.HEAD_ELE_ATTR_NAME_15);
		headInfoList.add(DocInfo.HEAD_ELE_ATTR_VALUE_15);

		return headInfoList;
	}
}
