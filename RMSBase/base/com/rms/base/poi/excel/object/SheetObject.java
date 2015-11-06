package com.rms.base.poi.excel.object;

import java.util.List;

import com.rms.base.poi.excel.model.CellAreaModel;
import com.rms.base.poi.excel.model.CellModel;
import com.rms.base.poi.excel.model.CellStyleModel;
import com.rms.base.poi.excel.model.FontModel;

/**
 *
 * @author ri.meisei
 * @since 2015/03/27
 */
public interface SheetObject {

	/**
	 * 現在行位置を取得する。<br>
	 *
	 * @return 現在行位置
	 */
	public int getCurrRowIndex();

	/**
	 * 現在列位置を取得する。<br>
	 *
	 * @return 現在列位置
	 */
	public int getCurrColumnIndex();

	/**
	 * 選択されているシートの最後行位置を取得する。
	 *
	 * @return 最後行位置
	 */
	public int getLastRowIndex();

	// /**
	// * 最大行位置を設定する。<br>
	// * maxRowIndex<0の場合や使用可能な最大行を超える場合、IndexOutOfBoundsException例外が発生する。<br>
	// *
	// * @param maxRowIndex
	// * 行位置
	// * @exception IndexOutOfBoundsException
	// */
	// public SheetObject setMaxRowIndex(int maxRowIndex);
	//
	// /**
	// * 最大列位置を設定する。<br>
	// * maxColumnIndex<0の場合や使用可能な最大列を超える場合、IndexOutOfBoundsException例外が発生する。<br>
	// *
	// * @param maxColumnIndex
	// * 列位置
	// * @exception IndexOutOfBoundsException
	// */
	// public SheetObject setMaxColumnIndex(int maxColumnIndex);
	//
	// /**
	// * 最大行位置と最大列位置を設定する。<br>
	// *
	// * maxRowIndex<0の場合や使用可能な最大行を超える場合、IndexOutOfBoundsException例外が発生する。<br>
	// * maxColumnIndex<0の場合や使用可能な最大列を超える場合、IndexOutOfBoundsException例外が発生する。<br>
	// *
	// * @param maxRowIndex
	// * 行位置
	// * @param maxColumnIndex
	// * 列位置
	// * @exception IndexOutOfBoundsException
	// */
	// public SheetObject setMaxIndex(int maxRowIndex, int maxColumnIndex);

	/**
	 * 現在行位置を設定する。<br>
	 * currRowIndex<0の場合やcurrRowIndex>{@code CellCreator.getMaxRowIndex()} 、IndexOutOfBoundsException例外が発生する。<br>
	 *
	 * @param currRowIndex
	 *            行位置
	 * @exception IndexOutOfBoundsException
	 */
	public SheetObject setCurrRowIndex(int currRowIndex);

	/**
	 * 現在列位置を設定する。<br>
	 * currColumnIndex<0の場合やcurrColumnIndex> {@code CellCreator.getMaxColumnIndex()}
	 * の場合、IndexOutOfBoundsException例外が発生する。<br>
	 *
	 * @param currColumnIndex
	 *            列位置
	 * @exception IndexOutOfBoundsException
	 */
	public SheetObject setCurrColumnIndex(int currColumnIndex);

	/**
	 * 現在行位置と現在列位置を設定する。<br>
	 * currRowIndex<0の場合やcurrRowIndex>{@code CellCreator.getMaxRowIndex()} 、IndexOutOfBoundsException例外が発生する。<br>
	 * currColumnIndex<0の場合やcurrColumnIndex> {@code CellCreator.getMaxColumnIndex()}
	 * の場合、IndexOutOfBoundsException例外が発生する。<br>
	 *
	 * @param currRowIndex
	 *            行位置
	 * @param currColumnIndex
	 *            列位置
	 * @exception IndexOutOfBoundsException
	 */
	public SheetObject setCurrentIndex(int currRowIndex, int currColumnIndex);

	/**
	 * 指定の行数を移動する。<br>
	 * rowNumber>0の場合、下に移動する、rowNumber<0の場合、上に移動する。<br>
	 * {@code CellCreator.getMinRowIndex()}<=( {@code CellCreator.getCurrRowIndex()}+rowNumber)<=
	 * {@code CellCreator.getMaxRowIndex()}以外の場合 、IndexOutOfBoundsException例外が発生する。<br>
	 *
	 * @param rowNumber
	 *            移動行数
	 * @exception IndexOutOfBoundsException
	 * @return
	 */
	public SheetObject nextRow(int rowNumber);

	/**
	 * 次の列に移動する。<br>
	 * columnNumber>0の場合、右に移動する、columnNumber<0の場合、左に移動する。<br>
	 * {@code CellCreator.getMinColumnIndex()}<=( {@code CellCreator.getCurrColumnIndex()}+columnNumber)<=
	 * {@code CellCreator.getMaxColumnIndex()}以外の場合 、IndexOutOfBoundsException例外が発生する。<br>
	 *
	 *
	 * @param columnNumber
	 *            移動列数
	 * @exception IndexOutOfBoundsException
	 * @return
	 */
	public SheetObject nextColumn(int columnNumber);

	/**
	 * 選択されているシートの行の高さを設定する。<br>
	 *
	 * @param rowIndex
	 *            行位置
	 * @param height
	 *            高さ
	 */
	public void setRowHeight(int rowIndex, short height);

	/**
	 * 選択されているシートの列の幅を設定する。<br>
	 *
	 * @param columnIndex
	 *            列位置
	 * @param width
	 *            幅値
	 */
	public void setColumnWidth(int columnIndex, int width);

	/**
	 * Cell情報を設定する。<br>
	 *
	 * @param cellModels
	 */
	public void setCellModels(CellModel... cellModels);

	/**
	 * Cell情報を設定する。<br>
	 * CellInfoのgetValue()がnullの場合、何もしない<br>
	 *
	 * @param cellModels
	 */
	public void setCellModels(List<CellModel> cellModels);

	/**
	 * Cell情報を設定する。<br>
	 *
	 * @param cellModels
	 *            Cell情報
	 * @return
	 */
	public SheetObject add(CellModel... cellModels);

	/**
	 * 現在の位置に設定するデータを追加する。<br>
	 *
	 * @param value
	 *            データ
	 * @return
	 */
	public SheetObject add(Object value);

	/**
	 * 現在の位置に設定するデータを追加する。<br>
	 *
	 * @param fontModel
	 *            文字情報
	 * @param cellStyleModel
	 *            Cell形式情報
	 * @param value
	 *            データ
	 * @return
	 */
	public SheetObject add(FontModel fontModel, CellStyleModel cellStyleModel, Object value);

	/**
	 * 指定Cellエリアを結合する。<br>
	 *
	 * @param cellAreaModel
	 *            Cellエリア情報
	 * @return
	 */
	public SheetObject addMergeArea(CellAreaModel cellAreaModel);

	/**
	 * 現在位置から、指定数のCellを結合する。<br>
	 * rowNumber<0の場合、上の行をマージする、rowNumber>0の場合、下の行をマージする。<br>
	 * columnNumber<0の場合、左の列をマージする、columnNumber>0の場合、右の列をマージする。<br>
	 * マージエリアが最小位置と最大位置の範囲外の場合、IndexOutOfBoundsException例外が発生する。<br>
	 *
	 * @param rowNumber
	 *            結合の行数
	 * @param columnNumber
	 *            結合の列数
	 * @exception IndexOutOfBoundsException
	 * @return
	 */
	public SheetObject addMergeArea(int rowNumber, int columnNumber);

	/**
	 * 指定Cellエリアを結合する。<br>
	 * rowNumber<0の場合、上の行をマージする、rowNumber>0の場合、下の行をマージする。<br>
	 * columnNumber<0の場合、左の列をマージする、columnNumber>0の場合、右の列をマージする。<br>
	 * マージエリアが最小位置と最大位置の範囲外の場合、IndexOutOfBoundsException例外が発生する。<br>
	 *
	 * @param rowIndex
	 *            開始行位置
	 * @param columnIndex
	 *            開始列位置
	 * @param rowNumber
	 *            結合の行数
	 * @param columnNumber
	 *            結合の列数
	 * @exception IndexOutOfBoundsException
	 * @return
	 */
	public SheetObject addMergeArea(int rowIndex, int columnIndex, int rowNumber, int columnNumber);

	/**
	 * 指定範囲のCellを結合する。<br>
	 *
	 * @param cellAreaModel
	 */
	public void merge(CellAreaModel cellAreaModel);

	/**
	 * 選択されているシートの指定Cellの内容を取得する。<br>
	 *
	 * @param rowIndex
	 *            行位置
	 * @param columnIndex
	 *            例位置
	 * @return 取得内容
	 */
	public CellModel getCellValue(int rowIndex, int columnIndex);

	public void setExcelObject(ExcelObject excelObject);

	public ExcelObject getExcelObject();

}
