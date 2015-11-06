package com.rms.base.poi.excel.object;

import java.util.Calendar;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

import com.rms.base.exception.UnexpectedDataException;
import com.rms.base.poi.excel.model.CellModel;

/**
 *
 *
 * @author ri.meisei
 * @since 2013/11/01
 */
class SheetHelper {

	/**
	 * @param sheet
	 * @param rowIndex
	 * @param columnIndex
	 * @param cellStyle
	 * @param value
	 */
	static void setCellValue(Sheet sheet, int rowIndex, int columnIndex, CellStyle cellStyle, Object value) {

		Cell cell = getCell(sheet, rowIndex, columnIndex, true);

		if (cellStyle != null) {
			cell.setCellStyle(cellStyle);
		}

		// TODO cellType未設定
		if (value != null) {
			Class<?> clazz = value.getClass();
			if (String.class == clazz) {
				cell.setCellValue((String) value);
				cell.setCellType(Cell.CELL_TYPE_STRING);
			} else if (Short.class == clazz) {
				cell.setCellValue((Short) value);
				cell.setCellType(Cell.CELL_TYPE_STRING);

			} else if (Character.class == clazz) {
				cell.setCellValue((Character) value);

			} else if (Integer.class == clazz) {
				cell.setCellValue((Integer) value);

			} else if (Long.class == clazz) {
				cell.setCellValue((Long) value);

			} else if (Float.class == clazz) {
				cell.setCellValue((Float) value);

			} else if (Double.class == clazz) {
				cell.setCellValue((Double) value);

			} else if (Boolean.class == clazz) {
				cell.setCellValue((Boolean) value);

			} else if (Date.class == clazz) {
				cell.setCellValue((Date) value);

			} else if (Calendar.class == clazz) {
				cell.setCellValue((Calendar) value);

			} else {
				throw new UnexpectedDataException("unexpected data type." + clazz.getName());
			}
		}

	}

	/**
	 *
	 * @param sheet
	 * @param rowIndex
	 * @param columnIndex
	 * @param value
	 * @return
	 */
	static CellModel getCellValue(Sheet sheet, int rowIndex, int columnIndex) {

		FormulaEvaluator formula = sheet.getWorkbook().getCreationHelper().createFormulaEvaluator();
		CellModel cellModel = null;
		Cell cell = getCell(sheet, rowIndex, columnIndex, false);
		if (cell == null) {
			return null;
		}
		int celltype = cell.getCellType();
		if (Cell.CELL_TYPE_FORMULA == celltype) {
			cellModel = getCellValue(formula.evaluateInCell(cell));
		} else {
			cellModel = getCellValue(cell);
		}

		return cellModel;
	}

	/**
	 *
	 * @param sheet
	 * @param rowIndex
	 * @param columnIndex
	 * @param value
	 * @return
	 */
	static CellModel getCellValue(Cell cell) {

		CellModel cellModel = new CellModel();// TODO
		int celltype = cell.getCellType();
		if (Cell.CELL_TYPE_STRING == celltype) {
			cellModel.setValue(cell.getStringCellValue());
		} else if (Cell.CELL_TYPE_BLANK == celltype) {
			cellModel.setValue("");
		} else if (Cell.CELL_TYPE_BOOLEAN == celltype) {
			cellModel.setValue(cell.getBooleanCellValue());
		} else if (Cell.CELL_TYPE_NUMERIC == celltype) {
			cellModel.setValue(cell.getNumericCellValue());
		} else {
			throw new UnexpectedDataException("");
		}

		return cellModel;
	}

	/**
	 *
	 * @param sheet
	 * @param startRowIndex
	 * @param endRowIndex
	 * @param startColumnIndex
	 * @param endColumnIndex
	 */
	static void merge(Sheet sheet, int startRowIndex, int endRowIndex, int startColumnIndex, int endColumnIndex) {

		CellRangeAddress rangeAddress = new CellRangeAddress(startRowIndex, endRowIndex, startColumnIndex, endColumnIndex);
		sheet.addMergedRegion(rangeAddress);

	}

	/**
	 * @param sheet
	 * @param rowIndex
	 * @param columnIndex
	 * @param cellStyle
	 */
	static void setCellStyle(Sheet sheet, int rowIndex, int columnIndex, CellStyle cellStyle) {

		Cell cell = getCell(sheet, rowIndex, columnIndex, true);
		if (cell == null) {
			System.out.println(rowIndex + " " + columnIndex);
		}
		cell.setCellStyle(cellStyle);
	}

	/**
	 *
	 * @param sheet
	 * @param rowIndex
	 * @param columnIndex
	 */
	static CellStyle getCellStyle(Sheet sheet, int rowIndex, int columnIndex) {

		CellStyle cellStyle = null;

		Cell cell = getCell(sheet, rowIndex, columnIndex, false);
		if (cell != null) {
			cellStyle = cell.getCellStyle();
		}

		return cellStyle;
	}

	/**
	 * シートの指定位置のRowを取得する。<br>
	 * Rowが存在しない場合かつcreateFlgがfalseの場合、nullを返す。<br>
	 * Rowが存在しない場合かつcreateFlgがtrueの場合、新たなRowを作成し、作成したRowを返す。<br>
	 *
	 * @param sheet
	 *            シート
	 * @param rowIndex
	 *            行位置
	 * @param createFlg
	 *            作成フラグ
	 * @return 取得Row
	 */
	static Row getRow(Sheet sheet, int rowIndex, boolean createFlg) {

		Row row = sheet.getRow(rowIndex);
		if (row == null && createFlg) {
			row = sheet.createRow(rowIndex);
		}

		return row;
	}

	/**
	 * シートの指定位置のCellを取得する。<br>
	 * Cellが存在しない場合かつcreateFlgがfalseの場合、nullを返す。<br>
	 * Cellが存在しない場合かつcreateFlgがtrueの場合、新たなCellを作成し、作成したCellを返す。<br>
	 *
	 * @param sheet
	 *            シート
	 * @param rowIndex
	 *            行位置
	 * @param columnIndex
	 *            列位置
	 * @param createFlg
	 *            作成フラグ
	 * @return 取得Cell
	 */
	static Cell getCell(Sheet sheet, int rowIndex, int columnIndex, boolean createFlg) {

		Cell cell = null;

		Row row = getRow(sheet, rowIndex, createFlg);

		if (row != null) {
			cell = row.getCell(columnIndex);

			if (cell == null && createFlg) {
				cell = row.createCell(columnIndex);
			}
		}

		return cell;
	}

	/**
	 *
	 * @param sheet
	 * @param columnIndex
	 * @param width
	 */
	static void setColumnWidth(Sheet sheet, int columnIndex, int width) {

		sheet.setColumnWidth(columnIndex, width * 300);// TODO
	}

	/**
	 *
	 * @param sheet
	 * @param rowIndex
	 * @param height
	 */
	static void setRowHeight(Sheet sheet, int rowIndex, short height) {

		getRow(sheet, rowIndex, true).setHeight(height);
	}
}
