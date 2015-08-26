package jp.co.rnai.task.convert.json;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

import org.dom4j.DocumentException;

import com.rms.common.io.IOFactory;
import com.rms.common.json.JsonParser;
import com.rms.common.json.JsonParserFactory;
import com.rms.common.json.exception.JsonParseException;
import com.rms.common.json.model.StructureModel;
import com.rms.common.poi.excel.ExcelOperatorFactory;
import com.rms.common.poi.excel.enums.AlignHType;
import com.rms.common.poi.excel.enums.AlignVType;
import com.rms.common.poi.excel.enums.BoldType;
import com.rms.common.poi.excel.enums.BorderType;
import com.rms.common.poi.excel.model.CellAreaModel;
import com.rms.common.poi.excel.model.CellStyleModel;
import com.rms.common.poi.excel.model.FontModel;
import com.rms.common.poi.excel.object.ExcelOperator;
import com.rms.common.poi.excel.object.SheetOperator;
import com.rms.common.xml.bean.backup.DocumentBean;
import com.rms.common.xml.bean.backup.StatictsInfo;
import com.rms.common.xml.impl.DOM4JXMLParser;

/**
 *
 *
 * @author ri.meisei
 * @since 2013/12/19
 */
public class Json2ExcelConverter {

	public static void main(String[] args) throws JsonParseException, FileNotFoundException {

		String file = "D:/work/convert/json/data/sample.json";
		InputStream inputStream = IOFactory.newFileInputStream(new File(file));
		JsonParser jsonParser = JsonParserFactory.newInstance().createJsonParser(inputStream);
		StructureModel structureModel = jsonParser.parse();
		System.out.println(structureModel.getJsonText());
	}

	public static void convert(String xmlFile, String excelFile) throws DocumentException, IOException {

		DOM4JXMLParser parser = new DOM4JXMLParser();
		DocumentBean documentBean = parser.parse(xmlFile);
		StatictsInfo info = new StatictsInfo();
		info.staticts(documentBean.getRootElementBean());

		ExcelOperator excelOperator = ExcelOperatorFactory.createOOXMLExcelOperator();
		String sheetName = "Xml";
		excelOperator.createSheet(sheetName);
		excelOperator.removeSheetsExclude(sheetName);
		excelOperator.setSelectedSheet(sheetName);

		for (int columnIndex = 0; columnIndex <= Const.END_COLUMN_INDEX; columnIndex++) {
			excelOperator.setColumnWidth(columnIndex, Const.WIDTH_COLUMN);
		}

		printTitleInfo(excelOperator);
		printTableInfo(excelOperator, info);
		excelOperator.write(excelFile);
	}

	private static void printTitleInfo(ExcelOperator excelOperator) {

		CellStyleModel titleStyle = getHeaderCellStyleModel();
		FontModel titleFont = new FontModel();
		titleFont.setBoldType(BoldType.BOLD);
		titleFont.setFontColor(Color.WHITE);// TODO

		SheetOperator sheetOperator = excelOperator.getSheetOperator();
		sheetOperator.setCurrentIndex(Const.START_ROW_INDEX, Const.START_COLUMN_INDEX);

		for (int rowIndex = Const.START_ROW_INDEX; rowIndex < Const.START_ROW_INDEX + 2; rowIndex++) {
			sheetOperator.setCurrentIndex(rowIndex, Const.START_COLUMN_INDEX);
			for (int columnIndex = Const.START_COLUMN_INDEX; columnIndex <= Const.END_COLUMN_INDEX; columnIndex++) {
				sheetOperator.add(titleFont, titleStyle, null).nextColumn(1);
			}
		}

		sheetOperator.setCurrentIndex(Const.START_ROW_INDEX, Const.START_COLUMN_INDEX);

		sheetOperator.add(titleFont, titleStyle, "ファイル名").nextColumn(Const.LABEL_FILE_COLUMNS);
		sheetOperator.add(titleFont, titleStyle, null).nextColumn(Const.VALUE_FILE_COLUMNS);
		sheetOperator.add(titleFont, titleStyle, "作成日").nextColumn(Const.LABEL_DATE_COLUMNS);
		sheetOperator.add(titleFont, titleStyle, null).nextColumn(Const.VALUE_DATE_COLUMNS);
		sheetOperator.add(titleFont, titleStyle, "作成者").nextColumn(Const.LABEL_CREATOR_COLUMNS);
		sheetOperator.add(titleFont, titleStyle, null).nextColumn(Const.VALUE_CREATOR_COLUMNS);

		sheetOperator.setCurrentIndex(Const.START_ROW_INDEX + 1, Const.START_COLUMN_INDEX);
		sheetOperator.add(titleFont, titleStyle, "説明").nextColumn(Const.LABEL_INFO_COLUMNS);
		sheetOperator.add(titleFont, titleStyle, null).nextColumn(Const.VALUE_INFO_COLUMNS);

		sheetOperator.setCurrentIndex(Const.START_ROW_INDEX, Const.START_COLUMN_INDEX);

		CellAreaModel cellAreaModel = new CellAreaModel();
		cellAreaModel.setCellStyleModel(titleStyle);
		cellAreaModel.setSheetName(excelOperator.getSelectedSheetName());

		cellAreaModel.setStartRowIndex(sheetOperator.getCurrRowIndex());
		cellAreaModel.setEndRowIndex(sheetOperator.getCurrRowIndex());

		cellAreaModel.setStartColumnIndex(sheetOperator.getCurrColumnIndex());
		cellAreaModel.setEndColumnIndex(sheetOperator.getCurrColumnIndex() + Const.LABEL_FILE_COLUMNS - 1);
		sheetOperator.addMergeArea(cellAreaModel).nextColumn(Const.LABEL_FILE_COLUMNS);

		cellAreaModel.setStartColumnIndex(sheetOperator.getCurrColumnIndex());
		cellAreaModel.setEndColumnIndex(sheetOperator.getCurrColumnIndex() + Const.VALUE_FILE_COLUMNS - 1);
		sheetOperator.addMergeArea(cellAreaModel).nextColumn(Const.VALUE_FILE_COLUMNS);

		cellAreaModel.setStartColumnIndex(sheetOperator.getCurrColumnIndex());
		cellAreaModel.setEndColumnIndex(sheetOperator.getCurrColumnIndex() + Const.LABEL_DATE_COLUMNS - 1);
		sheetOperator.addMergeArea(cellAreaModel).nextColumn(Const.LABEL_DATE_COLUMNS);

		cellAreaModel.setStartColumnIndex(sheetOperator.getCurrColumnIndex());
		cellAreaModel.setEndColumnIndex(sheetOperator.getCurrColumnIndex() + Const.VALUE_DATE_COLUMNS - 1);
		sheetOperator.addMergeArea(cellAreaModel).nextColumn(Const.VALUE_DATE_COLUMNS);

		cellAreaModel.setStartColumnIndex(sheetOperator.getCurrColumnIndex());
		cellAreaModel.setEndColumnIndex(sheetOperator.getCurrColumnIndex() + Const.LABEL_CREATOR_COLUMNS - 1);
		sheetOperator.addMergeArea(cellAreaModel).nextColumn(Const.LABEL_CREATOR_COLUMNS);

		cellAreaModel.setStartColumnIndex(sheetOperator.getCurrColumnIndex());
		cellAreaModel.setEndColumnIndex(sheetOperator.getCurrColumnIndex() + Const.VALUE_CREATOR_COLUMNS - 1);
		sheetOperator.addMergeArea(cellAreaModel).nextColumn(Const.VALUE_CREATOR_COLUMNS);

		sheetOperator.setCurrentIndex(Const.START_ROW_INDEX + 1, Const.START_COLUMN_INDEX);
		cellAreaModel.setStartRowIndex(sheetOperator.getCurrRowIndex());
		cellAreaModel.setEndRowIndex(sheetOperator.getCurrRowIndex());
		cellAreaModel.setStartColumnIndex(sheetOperator.getCurrColumnIndex());

		cellAreaModel.setStartColumnIndex(sheetOperator.getCurrColumnIndex());
		cellAreaModel.setEndColumnIndex(sheetOperator.getCurrColumnIndex() + Const.LABEL_INFO_COLUMNS - 1);
		sheetOperator.addMergeArea(cellAreaModel).nextColumn(Const.LABEL_INFO_COLUMNS);

		cellAreaModel.setStartColumnIndex(sheetOperator.getCurrColumnIndex());
		cellAreaModel.setEndColumnIndex(sheetOperator.getCurrColumnIndex() + Const.VALUE_INFO_COLUMNS - 1);
		sheetOperator.addMergeArea(cellAreaModel).nextColumn(Const.VALUE_INFO_COLUMNS);
	}

	private static void printTableInfo(ExcelOperator excelOperator, StatictsInfo info) {

		FontModel itemFont = new FontModel();
		itemFont.setBoldType(BoldType.NORMAL);
		itemFont.setFontColor(Color.WHITE);
		itemFont.setFontSize(Const.ITEM_FONT_SIZE);

		CellStyleModel headerStyleInfo = getTableHeaderCellStyleInfo();
		FontModel headerFontInfo = getTableHeaderFontInfo();

		SheetOperator sheetOperator = excelOperator.getSheetOperator();
		sheetOperator.setCurrentIndex(4, 1);
		for (int columnIndex = Const.START_COLUMN_INDEX; columnIndex <= Const.END_COLUMN_INDEX; columnIndex++) {
			sheetOperator.add(headerFontInfo, headerStyleInfo, null).nextColumn(1);
		}

		sheetOperator.setCurrentIndex(4, 1);
		sheetOperator.add(headerFontInfo, headerStyleInfo, "No.").nextColumn(Const.LABEL_NO_COLUMNS);
		sheetOperator.add(headerFontInfo, headerStyleInfo, "XPATH").nextColumn(Const.LABEL_XPATH_COLUMNS);
		sheetOperator.add(headerFontInfo, headerStyleInfo, "ELEMENT").nextColumn(Const.LABEL_ELEMENT_COLUMNS);
		sheetOperator.add(headerFontInfo, headerStyleInfo, "ATTR").nextColumn(Const.LABEL_ATTR_COLUMNS);
		sheetOperator.add(headerFontInfo, headerStyleInfo, "TIMES").nextColumn(Const.LABEL_TIMES_COLUMNS);
		sheetOperator.add(headerFontInfo, headerStyleInfo, "COMMENT").nextColumn(Const.LABEL_COMMENT_COLUMNS);

		sheetOperator.setCurrentIndex(4, 1);
		sheetOperator.addMergeArea(1, Const.LABEL_NO_COLUMNS).nextColumn(Const.LABEL_NO_COLUMNS);
		sheetOperator.addMergeArea(1, Const.LABEL_XPATH_COLUMNS).nextColumn(Const.LABEL_XPATH_COLUMNS);
		sheetOperator.addMergeArea(1, Const.LABEL_ELEMENT_COLUMNS).nextColumn(Const.LABEL_ELEMENT_COLUMNS);
		sheetOperator.addMergeArea(1, Const.LABEL_ATTR_COLUMNS).nextColumn(Const.LABEL_ATTR_COLUMNS);
		sheetOperator.addMergeArea(1, Const.LABEL_TIMES_COLUMNS).nextColumn(Const.LABEL_TIMES_COLUMNS);
		sheetOperator.addMergeArea(1, Const.LABEL_COMMENT_COLUMNS).nextColumn(Const.LABEL_COMMENT_COLUMNS);

		for (Integer seqNo : info.getSequenceMap().keySet()) {
			sheetOperator.nextRow(1).setCurrColumnIndex(1);
			String xpath = info.getSequenceMap().get(seqNo);
			String element = info.getStaticsTagNames().get(xpath);
			Integer time = info.getStatictsTagTimes().get(xpath);
			Set<String> attrs = info.getStaticsTagAttributes().get(xpath);

			sheetOperator.add(itemFont, null, seqNo).nextColumn(Const.LABEL_NO_COLUMNS);
			sheetOperator.add(itemFont, null, xpath).nextColumn(Const.LABEL_XPATH_COLUMNS);
			sheetOperator.add(itemFont, null, element).nextColumn(Const.LABEL_ELEMENT_COLUMNS);
			int attrColumnIndex = sheetOperator.getCurrColumnIndex();
			sheetOperator.add(itemFont, null, "").nextColumn(Const.LABEL_ATTR_COLUMNS);
			sheetOperator.add(itemFont, null, time).nextColumn(Const.LABEL_TIMES_COLUMNS);
			sheetOperator.add(itemFont, null, "").nextColumn(Const.LABEL_COMMENT_COLUMNS);

			int rowNumber = attrs.size();
			sheetOperator.setCurrColumnIndex(1);
			sheetOperator.addMergeArea(rowNumber, Const.LABEL_NO_COLUMNS).nextColumn(Const.LABEL_NO_COLUMNS);
			sheetOperator.addMergeArea(rowNumber, Const.LABEL_XPATH_COLUMNS).nextColumn(Const.LABEL_XPATH_COLUMNS);
			sheetOperator.addMergeArea(rowNumber, Const.LABEL_ELEMENT_COLUMNS).nextColumn(Const.LABEL_ELEMENT_COLUMNS);
			sheetOperator.addMergeArea(1, Const.LABEL_ATTR_COLUMNS).nextColumn(Const.LABEL_ATTR_COLUMNS);
			sheetOperator.addMergeArea(rowNumber, Const.LABEL_TIMES_COLUMNS).nextColumn(Const.LABEL_TIMES_COLUMNS);
			sheetOperator.addMergeArea(rowNumber, Const.LABEL_COMMENT_COLUMNS).nextColumn(Const.LABEL_COMMENT_COLUMNS);

			for (String attr : attrs) {
				sheetOperator.setCurrColumnIndex(attrColumnIndex);
				sheetOperator.add(itemFont, null, attr).nextColumn(Const.LABEL_ATTR_COLUMNS);

				sheetOperator.setCurrColumnIndex(attrColumnIndex);
				sheetOperator.addMergeArea(1, Const.LABEL_ATTR_COLUMNS).nextColumn(Const.LABEL_ATTR_COLUMNS);
				sheetOperator.nextRow(1);
			}

			CellStyleModel firstColStyle = new CellStyleModel();
			firstColStyle.setLeftBorderType(BorderType.BORDER_THICK);
			firstColStyle.setTopBorderType(BorderType.BORDER_THIN);
			firstColStyle.setRightBorderType(BorderType.BORDER_THIN);

			CellStyleModel middleColStyle = new CellStyleModel();
			middleColStyle.setLeftBorderType(BorderType.BORDER_THIN);
			middleColStyle.setTopBorderType(BorderType.BORDER_THIN);
			middleColStyle.setRightBorderType(BorderType.BORDER_THIN);

			CellStyleModel lastColStyle = new CellStyleModel();
			lastColStyle.setLeftBorderType(BorderType.BORDER_THIN);
			lastColStyle.setTopBorderType(BorderType.BORDER_THIN);
			lastColStyle.setRightBorderType(BorderType.BORDER_THICK);

			sheetOperator.nextRow(-rowNumber);
			sheetOperator.setCurrColumnIndex(1);
			for (int i = 0; i < Math.max(1, rowNumber); i++) {
				for (int j = Const.START_COLUMN_INDEX; j <= Const.END_COLUMN_INDEX; j++) {
					if (j == Const.START_COLUMN_INDEX) {
						sheetOperator.add(itemFont, firstColStyle, null).nextColumn(1);
					} else if (j == Const.END_COLUMN_INDEX) {
						sheetOperator.add(itemFont, lastColStyle, null).nextColumn(1);
					} else {
						sheetOperator.add(itemFont, middleColStyle, null).nextColumn(1);
					}
				}
				sheetOperator.nextRow(1);
				sheetOperator.setCurrColumnIndex(1);
			}

			sheetOperator.nextRow(-1);
		}

		sheetOperator.nextRow(1);
		sheetOperator.setCurrColumnIndex(1);

		CellStyleModel colStyleModel = new CellStyleModel();
		colStyleModel.setTopBorderType(BorderType.BORDER_THICK);

		for (int j = Const.START_COLUMN_INDEX; j <= Const.END_COLUMN_INDEX; j++) {
			sheetOperator.add(itemFont, colStyleModel, null).nextColumn(1);
		}
	}

	private static CellStyleModel getHeaderCellStyleModel() {

		CellStyleModel cellStyleModel = new CellStyleModel();
		cellStyleModel.setForegroundColor(new Color(0, 191, 255));
		cellStyleModel.setAlignHorizontal(AlignHType.HORIZONTAL_LEFT);
		cellStyleModel.setAlignVertical(AlignVType.VERTICAL_CENTER);
		cellStyleModel.setLeftBorderType(BorderType.BORDER_THICK);
		cellStyleModel.setRightBorderType(BorderType.BORDER_THICK);
		cellStyleModel.setTopBorderType(BorderType.BORDER_THICK);
		cellStyleModel.setBottomBorderType(BorderType.BORDER_THICK);
		cellStyleModel.setLeftBorderColor(Color.BLACK);
		cellStyleModel.setRightBorderColor(Color.BLACK);
		cellStyleModel.setTopBorderColor(Color.BLACK);
		cellStyleModel.setBottomBorderColor(Color.BLACK);

		return cellStyleModel;

	}

	private static CellStyleModel getTableHeaderCellStyleInfo() {

		CellStyleModel cellStyleModel = new CellStyleModel();
		cellStyleModel.setForegroundColor(new Color(255, 165, 0));
		cellStyleModel.setAlignHorizontal(AlignHType.HORIZONTAL_LEFT);
		cellStyleModel.setAlignVertical(AlignVType.VERTICAL_CENTER);
		cellStyleModel.setLeftBorderType(BorderType.BORDER_DOUBLE);
		cellStyleModel.setRightBorderType(BorderType.BORDER_DOUBLE);
		cellStyleModel.setTopBorderType(BorderType.BORDER_DOUBLE);
		cellStyleModel.setBottomBorderType(BorderType.BORDER_DOUBLE);

		cellStyleModel.setLeftBorderColor(Color.BLACK);
		cellStyleModel.setRightBorderColor(Color.BLACK);
		cellStyleModel.setTopBorderColor(Color.BLACK);
		cellStyleModel.setBottomBorderColor(Color.BLACK);

		return cellStyleModel;

	}

	private static FontModel getTableHeaderFontInfo() {

		FontModel headerFontModel = new FontModel();
		headerFontModel.setBoldType(BoldType.NORMAL);
		headerFontModel.setFontColor(Color.WHITE);

		return headerFontModel;
	}
}
