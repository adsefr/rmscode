package com.rms.base.poi.excel.object;

import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;

import com.rms.base.poi.excel.model.CellAreaModel;
import com.rms.base.poi.excel.model.CellModel;
import com.rms.base.poi.excel.model.CellStyleModel;
import com.rms.base.poi.excel.model.FontModel;

/**
 *
 * @author ri.meisei
 * @since 2015/03/27
 */
public class AbstractSheetObject implements SheetObject {

	private Sheet sheet;

	/** 最小行位置 */
	private int minRowIndex;

	/** 最小列位置 */
	private int minColumnIndex;

	/** 最大行位置 */
	private int maxRowIndex;

	/** 最大列位置 */
	private int maxColumnIndex;

	/** 現在行位置 */
	private int currRowIndex;

	/** 現在列位置 */
	private int currColumnIndex;

	private ExcelObject excelObject;

	public AbstractSheetObject(Sheet sheet) {
		this.sheet = sheet;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getCurrRowIndex() {

		return currRowIndex;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getCurrColumnIndex() {

		return currColumnIndex;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getLastRowIndex() {

		return 0;
	}

	// @Override
	// public SheetObject setMaxRowIndex(int maxRowIndex) {
	//
	// if (!excelObject.isValidRowNumber(maxRowIndex)) {
	// throw new IndexOutOfBoundsException("maxRowIndex:" + maxRowIndex);
	// }
	//
	// this.maxRowIndex = maxRowIndex;
	//
	// return this;
	// }
	//
	// @Override
	// public SheetObject setMaxColumnIndex(int maxColumnIndex) {
	//
	// if (!excelObject.isValidColumnNumber(maxColumnIndex)) {
	// throw new IndexOutOfBoundsException("maxColumnIndex:" + maxColumnIndex);
	// }
	//
	// this.maxColumnIndex = maxColumnIndex;
	//
	// return this;
	// }
	//
	// @Override
	// public SheetObject setMaxIndex(int maxRowIndex, int maxColumnIndex) {
	//
	// setMaxRowIndex(maxRowIndex);
	// setMaxColumnIndex(maxColumnIndex);
	//
	// return this;
	// }

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SheetObject setCurrRowIndex(int currRowIndex) {

		if (!excelObject.isValidRowNumber(currRowIndex) || currRowIndex > excelObject.getMaxRowIndex()) {
			throw new IndexOutOfBoundsException("rowIndex:" + currRowIndex);
		}

		this.currRowIndex = currRowIndex;

		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SheetObject setCurrColumnIndex(int currColumnIndex) {

		if (!excelObject.isValidRowNumber(currColumnIndex) || currColumnIndex > excelObject.getMaxColumnIndex()) {
			throw new IndexOutOfBoundsException("columnIndex:" + currColumnIndex);
		}

		this.currColumnIndex = currColumnIndex;

		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SheetObject setCurrentIndex(int currRowIndex, int currColumnIndex) {

		setCurrRowIndex(currRowIndex);
		setCurrColumnIndex(currColumnIndex);

		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SheetObject nextRow(int rowNumber) {

		int rowIndex = getCurrRowIndex() + rowNumber;

		if (rowIndex < excelObject.getMinRowIndex() || rowIndex > excelObject.getMaxRowIndex()) {
			throw new IndexOutOfBoundsException("rowIndex:" + rowIndex);
		}

		setCurrRowIndex(rowIndex);

		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SheetObject nextColumn(int columnNumber) {

		int columnIndex = getCurrColumnIndex() + columnNumber;

		if (columnIndex < excelObject.getMinColumnIndex() || columnIndex > excelObject.getMaxColumnIndex()) {
			throw new IndexOutOfBoundsException("columnIndex:" + columnIndex);
		}

		setCurrColumnIndex(columnIndex);

		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setRowHeight(int rowIndex, short height) {

		// TODO 自動生成されたメソッド・スタブ

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setColumnWidth(int columnIndex, int width) {

		// TODO 自動生成されたメソッド・スタブ

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setCellModels(CellModel... cellModels) {

		// TODO 自動生成されたメソッド・スタブ

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setCellModels(List<CellModel> cellModels) {

		// TODO 自動生成されたメソッド・スタブ

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SheetObject add(CellModel... cellModels) {

		setCellModels(cellModels);

		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SheetObject add(Object value) {

		add(null, null, value);

		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SheetObject add(FontModel fontModel, CellStyleModel cellStyleModel, Object value) {

		CellModel cellModel = new CellModel();
		cellModel.setSheetName(excelObject.getActiveSheetName());
		cellModel.setRowIndex(getCurrRowIndex());
		cellModel.setColumnIndex(getCurrColumnIndex());
		cellModel.setValue(value);

		if (fontModel != null) {
			cellModel.setFontModel(fontModel);
		}

		if (cellStyleModel != null) {
			cellModel.setCellStyleModel(cellStyleModel);
		}

		setCellModels(cellModel);

		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SheetObject addMergeArea(CellAreaModel cellAreaModel) {

		merge(cellAreaModel);

		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SheetObject addMergeArea(int rowNumber, int columnNumber) {

		return addMergeArea(getCurrRowIndex(), getCurrColumnIndex(), rowNumber, columnNumber);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SheetObject addMergeArea(int rowIndex, int columnIndex, int rowNumber, int columnNumber) {

		int startRowIndex = rowIndex;
		int startColumnIndex = columnIndex;
		int endRowIndex = rowIndex + Math.max(0, rowNumber - 1);
		int endColumnIndex = getCurrColumnIndex() + Math.max(0, columnNumber - 1);

		if (startRowIndex < excelObject.getMinRowIndex() || startRowIndex > excelObject.getMaxRowIndex()) {
			throw new IndexOutOfBoundsException("rowIndex:" + startRowIndex);
		}

		if (endRowIndex < excelObject.getMinRowIndex() || endRowIndex > excelObject.getMaxRowIndex()) {
			throw new IndexOutOfBoundsException("rowIndex:" + startRowIndex);
		}

		if (startColumnIndex < excelObject.getMinColumnIndex() || startColumnIndex > excelObject.getMaxColumnIndex()) {
			throw new IndexOutOfBoundsException("columnIndex:" + startColumnIndex);
		}

		if (endColumnIndex < excelObject.getMinColumnIndex() || endColumnIndex > excelObject.getMaxColumnIndex()) {
			throw new IndexOutOfBoundsException("columnIndex:" + endColumnIndex);
		}

		CellAreaModel cellAreaModel = new CellAreaModel();
		cellAreaModel.setStartRowIndex(Math.min(startRowIndex, endRowIndex));
		cellAreaModel.setEndRowIndex(Math.max(startRowIndex, endRowIndex));
		cellAreaModel.setStartColumnIndex(Math.min(startColumnIndex, endColumnIndex));
		cellAreaModel.setEndColumnIndex(Math.max(startColumnIndex, endColumnIndex));
		cellAreaModel.setSheetName(excelObject.getActiveSheetName());

		merge(cellAreaModel);

		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void merge(CellAreaModel cellAreaModel) {

		// TODO 自動生成されたメソッド・スタブ

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CellModel getCellValue(int rowIndex, int columnIndex) {

		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public final void setExcelObject(ExcelObject excelObject) {

		this.excelObject = excelObject;
	}

	@Override
	public final ExcelObject getExcelObject() {

		return excelObject;
	}

}
