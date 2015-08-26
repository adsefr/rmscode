package jp.co.rnai.task.convert.xml;

import java.awt.Color;
import java.io.IOException;

import org.dom4j.DocumentException;

import com.rms.common.poi.excel.enums.AlignHType;
import com.rms.common.poi.excel.enums.AlignVType;
import com.rms.common.poi.excel.enums.BoldType;
import com.rms.common.poi.excel.enums.BorderType;
import com.rms.common.poi.excel.model.CellStyleModel;
import com.rms.common.poi.excel.model.FontModel;
import com.rms.common.poi.excel.object.ExcelOperator;
import com.rms.common.poi.excel.object.SheetOperator;
import com.rms.common.poi.excel2.CellAreaOperator;
import com.rms.common.poi.excel2.CellOperator;
import com.rms.common.poi.excel2.ExcelFactory;
import com.rms.common.xml.bean.backup.DocumentBean;
import com.rms.common.xml.bean.backup.StatictsInfo;
import com.rms.common.xml.impl.DOM4JXMLParser;

/**
 *
 *
 * @author ri.meisei
 * @since 2013/12/19
 */
public class XML2ExcelConverter {

	public static void main(String[] args) {

		String xmlFile = "D:/work/convert/xml2/main.xml";
		String excelFile = "D:/work/convert/xml2/main.xlsx";
		try {
			convert(xmlFile, excelFile);
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void convert(String xmlFile, String excelFile) throws DocumentException, IOException {

		DOM4JXMLParser parser = new DOM4JXMLParser();
		DocumentBean documentBean = parser.parse(xmlFile);
		StatictsInfo info = new StatictsInfo();
		info.staticts(documentBean.getRootElementBean());

		ExcelOperator excelOperator = ExcelFactory.create();
		SheetOperator sheetOperator = excelOperator.createSheet("XML");
		sheetOperator.setActiveSheet();

		for (int columnIndex = 0; columnIndex <= Const.END_COLUMN_INDEX; columnIndex++) {
			sheetOperator.setColumnWidth(columnIndex, Const.WIDTH_COLUMN);
		}

		printTitleInfo(sheetOperator);
		printTableInfo(sheetOperator, info);
		excelOperator.write(excelFile);
	}

	private static void printTitleInfo(SheetOperator sheetOperator) {

		CellOperator cellOperator = sheetOperator.getCellOperator();
		CellAreaOperator cellAreaOperator = sheetOperator.getCellAreaOperator();

		CellStyleModel titleStyle = getHeaderCellStyleModel();
		FontModel titleFont = new FontModel();
		titleFont.setBoldType(BoldType.BOLD);
		titleFont.setFontColor(Color.WHITE);

		sheetOperator.setCurrentIndex(Const.START_ROW_INDEX, Const.START_COLUMN_INDEX);

		for (int rowIndex = Const.START_ROW_INDEX; rowIndex < Const.START_ROW_INDEX + 2; rowIndex++) {
			sheetOperator.setCurrentIndex(rowIndex, Const.START_COLUMN_INDEX);
			for (int columnIndex = Const.START_COLUMN_INDEX; columnIndex <= Const.END_COLUMN_INDEX; columnIndex++) {
				cellOperator.fontModel(titleFont).cellStyleModel(titleStyle).flush();
				sheetOperator.nextColumn(1);
			}
		}

		sheetOperator.setCurrentIndex(Const.START_ROW_INDEX, Const.START_COLUMN_INDEX);

		cellOperator.fontModel(titleFont).cellStyleModel(titleStyle).value("ファイル名").flush();
		sheetOperator.nextColumn(Const.LABEL_FILE_COLUMNS);
		cellOperator.fontModel(titleFont).cellStyleModel(titleStyle).flush();
		sheetOperator.nextColumn(Const.VALUE_FILE_COLUMNS);
		cellOperator.fontModel(titleFont).cellStyleModel(titleStyle).value("作成日").flush();
		sheetOperator.nextColumn(Const.LABEL_DATE_COLUMNS);
		cellOperator.fontModel(titleFont).cellStyleModel(titleStyle).flush();
		sheetOperator.nextColumn(Const.VALUE_DATE_COLUMNS);
		cellOperator.fontModel(titleFont).cellStyleModel(titleStyle).value("作成者").flush();
		sheetOperator.nextColumn(Const.LABEL_CREATOR_COLUMNS);
		cellOperator.fontModel(titleFont).cellStyleModel(titleStyle).flush();
		sheetOperator.nextColumn(Const.VALUE_CREATOR_COLUMNS);

		sheetOperator.setCurrentIndex(Const.START_ROW_INDEX + 1, Const.START_COLUMN_INDEX);
		cellOperator.fontModel(titleFont).cellStyleModel(titleStyle).value("説明").flush();
		sheetOperator.nextColumn(Const.LABEL_INFO_COLUMNS);
		cellOperator.fontModel(titleFont).cellStyleModel(titleStyle).flush();
		sheetOperator.nextColumn(Const.VALUE_INFO_COLUMNS);

		sheetOperator.setCurrentIndex(Const.START_ROW_INDEX, Const.START_COLUMN_INDEX);

		cellAreaOperator.startRowIndex(sheetOperator.getCurrentRowIndex()).rowNumber(1)
				.startColumnIndex(sheetOperator.getCurrentColumnIndex()).columnNumber(Const.LABEL_FILE_COLUMNS)
				.cellStyleModel(titleStyle).flush();
		sheetOperator.nextColumn(Const.LABEL_FILE_COLUMNS);

		cellAreaOperator.startRowIndex(sheetOperator.getCurrentRowIndex()).rowNumber(1)
				.startColumnIndex(sheetOperator.getCurrentColumnIndex()).columnNumber(Const.VALUE_FILE_COLUMNS)
				.cellStyleModel(titleStyle).flush();
		sheetOperator.nextColumn(Const.VALUE_FILE_COLUMNS);

		cellAreaOperator.startRowIndex(sheetOperator.getCurrentRowIndex()).rowNumber(1)
				.startColumnIndex(sheetOperator.getCurrentColumnIndex()).columnNumber(Const.LABEL_DATE_COLUMNS)
				.cellStyleModel(titleStyle).flush();
		sheetOperator.nextColumn(Const.LABEL_DATE_COLUMNS);

		cellAreaOperator.startRowIndex(sheetOperator.getCurrentRowIndex()).rowNumber(1)
				.startColumnIndex(sheetOperator.getCurrentColumnIndex()).columnNumber(Const.VALUE_DATE_COLUMNS)
				.cellStyleModel(titleStyle).flush();
		sheetOperator.nextColumn(Const.VALUE_DATE_COLUMNS);

		cellAreaOperator.startRowIndex(sheetOperator.getCurrentRowIndex()).rowNumber(1)
				.startColumnIndex(sheetOperator.getCurrentColumnIndex()).columnNumber(Const.LABEL_CREATOR_COLUMNS)
				.cellStyleModel(titleStyle).flush();
		sheetOperator.nextColumn(Const.LABEL_CREATOR_COLUMNS);

		cellAreaOperator.startRowIndex(sheetOperator.getCurrentRowIndex()).rowNumber(1)
				.startColumnIndex(sheetOperator.getCurrentColumnIndex()).columnNumber(Const.VALUE_CREATOR_COLUMNS)
				.cellStyleModel(titleStyle).flush();
		sheetOperator.nextColumn(Const.VALUE_CREATOR_COLUMNS);

		sheetOperator.setCurrentIndex(Const.START_ROW_INDEX + 1, Const.START_COLUMN_INDEX);

		cellAreaOperator.startRowIndex(sheetOperator.getCurrentRowIndex()).rowNumber(1)
				.startColumnIndex(sheetOperator.getCurrentColumnIndex()).columnNumber(Const.LABEL_INFO_COLUMNS)
				.cellStyleModel(titleStyle).flush();
		sheetOperator.nextColumn(Const.LABEL_INFO_COLUMNS);

		cellAreaOperator.startRowIndex(sheetOperator.getCurrentRowIndex()).rowNumber(1)
				.startColumnIndex(sheetOperator.getCurrentColumnIndex()).columnNumber(Const.VALUE_INFO_COLUMNS)
				.cellStyleModel(titleStyle).flush();
		sheetOperator.nextColumn(Const.VALUE_INFO_COLUMNS);
	}

	private static void printTableInfo(SheetOperator sheetOperator, StatictsInfo info) {

		CellStyleModel headerStyle = getTableHeaderCellStyleInfo();
		FontModel headerFont = getTableHeaderFontInfo();

		FontModel itemFont = new FontModel();
		itemFont.setBoldType(BoldType.NORMAL);
		itemFont.setFontColor(Color.WHITE);
		itemFont.setFontSize(Const.ITEM_FONT_SIZE);

		CellOperator cellOperator = sheetOperator.getCellOperator();
		CellAreaOperator cellAreaOperator = sheetOperator.getCellAreaOperator();

		sheetOperator.setCurrentIndex(4, 1);

		for (int columnIndex = Const.START_COLUMN_INDEX; columnIndex <= Const.END_COLUMN_INDEX; columnIndex++) {
			cellOperator.fontModel(headerFont).cellStyleModel(headerStyle).flush();
			sheetOperator.nextColumn(1);
		}

		sheetOperator.setCurrentIndex(4, 1);

		cellOperator.fontModel(headerFont).cellStyleModel(headerStyle).value("No.").flush();
		sheetOperator.nextColumn(Const.LABEL_NO_COLUMNS);
		cellOperator.fontModel(headerFont).cellStyleModel(headerStyle).value("XPATH").flush();
		sheetOperator.nextColumn(Const.LABEL_XPATH_COLUMNS);
		cellOperator.fontModel(headerFont).cellStyleModel(headerStyle).value("ELEMENT").flush();
		sheetOperator.nextColumn(Const.LABEL_ELEMENT_COLUMNS);
		cellOperator.fontModel(headerFont).cellStyleModel(headerStyle).value("ATTR").flush();
		sheetOperator.nextColumn(Const.LABEL_ATTR_COLUMNS);
		cellOperator.fontModel(headerFont).cellStyleModel(headerStyle).value("TIMES").flush();
		sheetOperator.nextColumn(Const.LABEL_ATTR_COLUMNS);
		cellOperator.fontModel(headerFont).cellStyleModel(headerStyle).value("COMMENT").flush();
		sheetOperator.nextColumn(Const.LABEL_COMMENT_COLUMNS);

		sheetOperator.setCurrentIndex(4, 1);

		cellAreaOperator.startRowIndex(sheetOperator.getCurrentRowIndex()).rowNumber(1)
				.startColumnIndex(sheetOperator.getCurrentColumnIndex()).columnNumber(Const.LABEL_NO_COLUMNS)
				.fontModel(headerFont).cellStyleModel(headerStyle).flush();
		sheetOperator.nextColumn(Const.LABEL_NO_COLUMNS);

		cellAreaOperator.startRowIndex(sheetOperator.getCurrentRowIndex()).rowNumber(1)
				.startColumnIndex(sheetOperator.getCurrentColumnIndex()).columnNumber(Const.LABEL_XPATH_COLUMNS)
				.fontModel(headerFont).cellStyleModel(headerStyle).flush();
		sheetOperator.nextColumn(Const.LABEL_XPATH_COLUMNS);

		cellAreaOperator.startRowIndex(sheetOperator.getCurrentRowIndex()).rowNumber(1)
				.startColumnIndex(sheetOperator.getCurrentColumnIndex()).columnNumber(Const.LABEL_ELEMENT_COLUMNS)
				.fontModel(headerFont).cellStyleModel(headerStyle).flush();
		sheetOperator.nextColumn(Const.LABEL_ELEMENT_COLUMNS);

		cellAreaOperator.startRowIndex(sheetOperator.getCurrentRowIndex()).rowNumber(1)
				.startColumnIndex(sheetOperator.getCurrentColumnIndex()).columnNumber(Const.LABEL_ATTR_COLUMNS)
				.fontModel(headerFont).cellStyleModel(headerStyle).flush();
		sheetOperator.nextColumn(Const.LABEL_ATTR_COLUMNS);

		cellAreaOperator.startRowIndex(sheetOperator.getCurrentRowIndex()).rowNumber(1)
				.startColumnIndex(sheetOperator.getCurrentColumnIndex()).columnNumber(Const.LABEL_TIMES_COLUMNS)
				.fontModel(headerFont).cellStyleModel(headerStyle).flush();
		sheetOperator.nextColumn(Const.LABEL_TIMES_COLUMNS);

		cellAreaOperator.startRowIndex(sheetOperator.getCurrentRowIndex()).rowNumber(1)
				.startColumnIndex(sheetOperator.getCurrentColumnIndex()).columnNumber(Const.LABEL_COMMENT_COLUMNS)
				.fontModel(headerFont).cellStyleModel(headerStyle).flush();
		sheetOperator.nextColumn(Const.LABEL_COMMENT_COLUMNS);

		sheetOperator.nextRow(1);
		for (Integer seqNo : info.getSequenceMap().keySet()) {
			String xpath = info.getSequenceMap().get(seqNo);
			String element = info.getStaticsTagNames().get(xpath);
			Integer time = info.getStatictsTagTimes().get(xpath);
			String[] attrs = info.getStaticsTagAttributes().get(xpath).toArray(new String[0]);
			int rowNumber = Math.max(1, attrs.length);
			sheetOperator.setCurrentColumnIndex(Const.START_COLUMN_INDEX);

			cellOperator.fontModel(itemFont).value(seqNo).flush();
			cellAreaOperator.startRowIndex(sheetOperator.getCurrentRowIndex()).rowNumber(rowNumber)
					.startColumnIndex(sheetOperator.getCurrentColumnIndex()).columnNumber(Const.LABEL_NO_COLUMNS)
					.fontModel(itemFont).flush();
			sheetOperator.nextColumn(Const.LABEL_NO_COLUMNS);

			cellOperator.fontModel(itemFont).value(xpath).flush();
			cellAreaOperator.startRowIndex(sheetOperator.getCurrentRowIndex()).rowNumber(rowNumber)
					.startColumnIndex(sheetOperator.getCurrentColumnIndex()).columnNumber(Const.LABEL_XPATH_COLUMNS)
					.fontModel(itemFont).flush();
			sheetOperator.nextColumn(Const.LABEL_XPATH_COLUMNS);

			cellOperator.fontModel(itemFont).value(element).flush();
			cellAreaOperator.startRowIndex(sheetOperator.getCurrentRowIndex()).rowNumber(rowNumber)
					.startColumnIndex(sheetOperator.getCurrentColumnIndex()).columnNumber(Const.LABEL_ELEMENT_COLUMNS)
					.fontModel(itemFont).flush();
			sheetOperator.nextColumn(Const.LABEL_ELEMENT_COLUMNS);

			for (int index = 0; index < rowNumber; index++) {
				if (attrs.length > 0) {
					cellOperator.rowIndex(sheetOperator.getCurrentRowIndex() + index)
							.columnIndex(sheetOperator.getCurrentColumnIndex()).fontModel(itemFont).value(attrs[index])
							.flush();
				}
				cellAreaOperator.startRowIndex(sheetOperator.getCurrentRowIndex() + index).rowNumber(1)
						.startColumnIndex(sheetOperator.getCurrentColumnIndex()).columnNumber(Const.LABEL_ATTR_COLUMNS)
						.fontModel(itemFont).flush();
			}
			sheetOperator.nextColumn(Const.LABEL_ATTR_COLUMNS);

			cellOperator.fontModel(itemFont).value(time).flush();
			cellAreaOperator.startRowIndex(sheetOperator.getCurrentRowIndex()).rowNumber(rowNumber)
					.startColumnIndex(sheetOperator.getCurrentColumnIndex()).columnNumber(Const.LABEL_TIMES_COLUMNS)
					.fontModel(itemFont).flush();
			sheetOperator.nextColumn(Const.LABEL_TIMES_COLUMNS);

			cellOperator.fontModel(itemFont).value("").flush();
			cellAreaOperator.startRowIndex(sheetOperator.getCurrentRowIndex()).rowNumber(rowNumber)
					.startColumnIndex(sheetOperator.getCurrentColumnIndex()).columnNumber(Const.LABEL_COMMENT_COLUMNS)
					.fontModel(itemFont).flush();
			sheetOperator.nextColumn(Const.LABEL_COMMENT_COLUMNS);

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

			for (int index = 0; index < rowNumber; index++) {
				for (int columnIndex = Const.START_COLUMN_INDEX; columnIndex <= Const.END_COLUMN_INDEX; columnIndex++) {
					int rowIndex = sheetOperator.getCurrentRowIndex() + index;
					CellStyleModel styleModel = null;
					if (columnIndex == Const.START_COLUMN_INDEX) {
						styleModel = firstColStyle;

					} else if (columnIndex == Const.END_COLUMN_INDEX) {
						styleModel = lastColStyle;
					} else {
						styleModel = middleColStyle;
					}
					cellOperator.rowIndex(rowIndex).columnIndex(columnIndex).fontModel(itemFont)
							.cellStyleModel(styleModel).flush();
				}
			}

			sheetOperator.nextRow(rowNumber);
		}

		CellStyleModel colStyleModel = new CellStyleModel();
		colStyleModel.setTopBorderType(BorderType.BORDER_THICK);
		for (int columnIndex = Const.START_COLUMN_INDEX; columnIndex <= Const.END_COLUMN_INDEX; columnIndex++) {
			cellOperator.rowIndex(sheetOperator.getCurrentRowIndex()).columnIndex(columnIndex).fontModel(itemFont)
					.cellStyleModel(colStyleModel).flush();
			sheetOperator.nextColumn(1);
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
