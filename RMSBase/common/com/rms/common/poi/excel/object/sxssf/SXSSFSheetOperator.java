package com.rms.common.poi.excel.object.sxssf;

import com.rms.common.poi.excel.model.CellAreaModel;
import com.rms.common.poi.excel.model.CellModel;
import com.rms.common.poi.excel.model.CellStyleModel;
import com.rms.common.poi.excel.model.FontModel;
import com.rms.common.poi.excel.object.AbstractSheetOperator;
import com.rms.common.poi.excel.object.ExcelOperator;
import com.rms.common.poi.excel.object.SheetOperator;

/**
 *
 * @author ri.meisei
 * @since 2015/03/27
 */
public class SXSSFSheetOperator extends AbstractSheetOperator {

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

	private ExcelOperator excelOperator;

	SXSSFSheetOperator() {

	}

	@Override
	public int getMinRowIndex() {

		return minRowIndex;
	}

	@Override
	public int getMinColumnIndex() {

		return minColumnIndex;
	}

	@Override
	public int getMaxRowIndex() {

		return maxRowIndex;
	}

	@Override
	public int getMaxColumnIndex() {

		return maxColumnIndex;
	}

	@Override
	public int getCurrRowIndex() {

		return currRowIndex;
	}

	@Override
	public int getCurrColumnIndex() {

		return currColumnIndex;
	}

	@Override
	public SheetOperator setMaxRowIndex(int maxRowIndex) {

		if (!excelOperator.isValidRowIndex(maxRowIndex)) {
			throw new IndexOutOfBoundsException("maxRowIndex:" + maxRowIndex);
		}

		this.maxRowIndex = maxRowIndex;

		return this;
	}

	@Override
	public SheetOperator setMaxColumnIndex(int maxColumnIndex) {

		if (!excelOperator.isValidColumnIndex(maxColumnIndex)) {
			throw new IndexOutOfBoundsException("maxColumnIndex:" + maxColumnIndex);
		}

		this.maxColumnIndex = maxColumnIndex;

		return this;
	}

	@Override
	public SheetOperator setMaxIndex(int maxRowIndex, int maxColumnIndex) {

		setMaxRowIndex(maxRowIndex);
		setMaxColumnIndex(maxColumnIndex);

		return this;
	}

	@Override
	public SheetOperator setCurrRowIndex(int currRowIndex) {

		if (!excelOperator.isValidRowIndex(currRowIndex) || currRowIndex > getMaxRowIndex()) {
			throw new IndexOutOfBoundsException("rowIndex:" + currRowIndex);
		}

		this.currRowIndex = currRowIndex;

		return this;
	}

	@Override
	public SheetOperator setCurrColumnIndex(int currColumnIndex) {

		if (!excelOperator.isValidRowIndex(currColumnIndex) || currColumnIndex > getMaxColumnIndex()) {
			throw new IndexOutOfBoundsException("columnIndex:" + currColumnIndex);
		}

		this.currColumnIndex = currColumnIndex;

		return this;
	}

	@Override
	public SheetOperator setCurrentIndex(int currRowIndex, int currColumnIndex) {

		setCurrRowIndex(currRowIndex);
		setCurrColumnIndex(currColumnIndex);

		return this;
	}

	@Override
	public SheetOperator nextRow(int rowNumber) {

		int rowIndex = getCurrRowIndex() + rowNumber;

		if (rowIndex < getMinRowIndex() || rowIndex > getMaxRowIndex()) {
			throw new IndexOutOfBoundsException("rowIndex:" + rowIndex);
		}

		setCurrRowIndex(rowIndex);

		return this;
	}

	@Override
	public SheetOperator nextColumn(int columnNumber) {

		int columnIndex = getCurrColumnIndex() + columnNumber;

		if (columnIndex < getMinColumnIndex() || columnIndex > getMaxColumnIndex()) {
			throw new IndexOutOfBoundsException("columnIndex:" + columnIndex);
		}

		setCurrColumnIndex(columnIndex);

		return this;
	}

	@Override
	public SheetOperator add(CellModel... cellModels) {

		excelOperator.setCellModels(cellModels);

		return this;
	}

	@Override
	public SheetOperator add(Object value) {

		add(null, null, value);

		return this;
	}

	@Override
	public SheetOperator add(FontModel fontModel, CellStyleModel cellStyleModel, Object value) {

		CellModel cellModel = new CellModel();
		cellModel.setSheetName(excelOperator.getSelectedSheetName());
		cellModel.setRowIndex(getCurrRowIndex());
		cellModel.setColumnIndex(getCurrColumnIndex());
		cellModel.setValue(value);

		if (fontModel != null) {
			cellModel.setFontModel(fontModel);
		}

		if (cellStyleModel != null) {
			cellModel.setCellStyleModel(cellStyleModel);
		}

		excelOperator.setCellModels(cellModel);

		return this;
	}

	@Override
	public SheetOperator addMergeArea(CellAreaModel cellAreaModel) {

		excelOperator.merge(cellAreaModel);

		return this;
	}

	@Override
	public SheetOperator addMergeArea(int rowNumber, int columnNumber) {

		return addMergeArea(getCurrRowIndex(), getCurrColumnIndex(), rowNumber, columnNumber);
	}

	@Override
	public SheetOperator addMergeArea(int rowIndex, int columnIndex, int rowNumber, int columnNumber) {

		int startRowIndex = rowIndex;
		int startColumnIndex = columnIndex;
		int endRowIndex = rowIndex + Math.max(0, rowNumber - 1);
		int endColumnIndex = getCurrColumnIndex() + Math.max(0, columnNumber - 1);

		if (startRowIndex < getMinRowIndex() || startRowIndex > getMaxRowIndex()) {
			throw new IndexOutOfBoundsException("rowIndex:" + startRowIndex);
		}

		if (endRowIndex < getMinRowIndex() || endRowIndex > getMaxRowIndex()) {
			throw new IndexOutOfBoundsException("rowIndex:" + startRowIndex);
		}

		if (startColumnIndex < getMinColumnIndex() || startColumnIndex > getMaxColumnIndex()) {
			throw new IndexOutOfBoundsException("columnIndex:" + startColumnIndex);
		}

		if (endColumnIndex < getMinColumnIndex() || endColumnIndex > getMaxColumnIndex()) {
			throw new IndexOutOfBoundsException("columnIndex:" + endColumnIndex);
		}

		CellAreaModel cellAreaModel = new CellAreaModel();
		cellAreaModel.setStartRowIndex(Math.min(startRowIndex, endRowIndex));
		cellAreaModel.setEndRowIndex(Math.max(startRowIndex, endRowIndex));
		cellAreaModel.setStartColumnIndex(Math.min(startColumnIndex, endColumnIndex));
		cellAreaModel.setEndColumnIndex(Math.max(startColumnIndex, endColumnIndex));
		cellAreaModel.setSheetName(excelOperator.getSelectedSheetName());

		excelOperator.merge(cellAreaModel);

		return this;
	}
}
