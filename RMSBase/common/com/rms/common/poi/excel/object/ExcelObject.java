package com.rms.common.poi.excel.object;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author ri.meisei
 * @since 2015/03/27
 */
public interface ExcelObject {

	/**
	 * {@link ExcelOperator}を取得する。
	 *
	 * @return SheetOperator
	 */
	ExcelOperator getExcelOperator();

	/**
	 * {@link SheetOperator}を取得する。
	 *
	 * @return SheetOperator
	 */
	public SheetOperator getSheetOperator();

	/**
	 * {@link SheetOperator}を取得する。
	 *
	 * @return SheetOperator
	 */
	public SheetOperator getSheetOperator(String sheetName);

	/**
	 * {@link SheetOperator}を取得する。
	 *
	 * @return SheetOperator
	 */
	public SheetOperator getSheetOperator(int sheetIndex);

	/**
	 * シートで使用可能な最大行位置を返す。
	 *
	 * @return 最大行位置
	 */
	public int getMaxRowIndex();

	/**
	 * シートで使用可能な最大行数を返す。
	 *
	 * @return 最大行数 (ExcelOperator.getMaxRowIndex()+1)
	 */
	public int getMaxRowNumber();

	/**
	 * シートで使用可能な最大列位置を返す。
	 *
	 * @return 最大列位置
	 */
	public int getMaxColumnIndex();

	/**
	 * シートで使用可能な最大列数を返す。<br>
	 *
	 * @return 最大列数 (ExcelOperator.getMaxColumnIndex()+1)
	 */
	public int getMaxColumnNumer();

	/**
	 * Cellに書き込む可能な最大文字数を返す。<br>
	 *
	 * @return 最大文字数
	 */
	public int getCellMaxTextLength();

	/**
	 * シート数を返す。
	 *
	 * @return シート数
	 */
	public int getNumberOfSheets();

	/**
	 * シート名が存在するかチェックする。<br>
	 *
	 *
	 * @param sheetName
	 *            チェック対象シート名
	 * @return シート名が存在する場合はtrueを返す。 <br>
	 *         シート名が存在しない場合はfalseを返す。<br>
	 */
	public boolean hasSheet(String sheetName);

	/**
	 * シート位置が存在するかチェックする。<br>
	 *
	 * @param sheetIndex
	 *            チェック対象シート位置
	 * @return シート位置が存在する場合はtrueを返す。 <br>
	 *         シート位置が存在しない場合はfalseを返す。<br>
	 */
	public boolean hasSheet(int sheetIndex);

	/**
	 * 選択されているシート名を取得する。<br>
	 *
	 * @return シート名
	 */
	public String getActiveSheetName();

	/**
	 * シート名の有効性をチェックする。<br>
	 *
	 *
	 * @param sheetName
	 *            チェック対象シート名
	 * @return シート名が有効の場合はtrueを返す。<br>
	 *         シート名が無効の場合はfalseを返す。<br>
	 */
	public boolean isValidSheetName(String sheetName);

	/**
	 * シート位置の有効性をチェックする。<br>
	 *
	 * @param sheetIndex
	 *            チェック対象シート位置
	 * @return シート位置が有効の場合はtrueを返す。<br>
	 *         シート位置が無効の場合はfalseを返す。<br>
	 */
	public boolean isValidSheetIndex(int sheetIndex);

	// /**
	// * 行位置の有効性をチェックする。<br>
	// *
	// * @param rowIndex
	// * 行位置
	// * @return true：有効の場合<br>
	// * false：無効の場合<br>
	// */
	// public boolean isValidRowIndex(int rowIndex);
	//
	// /**
	// * 列位置の有効性をチェックする。<br>
	// *
	// * @param columnIndex
	// * 列位置
	// * @return true：有効の場合<br>
	// * false：無効の場合<br>
	// */
	// public boolean isValidColumnIndex(int columnIndex);

	/**
	 * 指定位置のシート名を取得する。<br>
	 * シート位置が無効の場合、nullを返す。<br>
	 *
	 * @param sheetIndex
	 *            シート位置
	 * @return シート名
	 */
	public String getSheetName(int sheetIndex);

	/**
	 * 指定シート名のシート位置を取得する。<br>
	 * シート名が存在しない場合、-1を返す。<br>
	 *
	 * @param sheetName
	 *            シート名
	 * @return シート位置
	 */
	public int getSheetIndex(String sheetName);

	/**
	 * Excelファイルを指定ファイルに書き込む<br>
	 *
	 * @param filePath
	 *            書き込む対象ファイルパス
	 * @throws IOException
	 */
	public void write(String filePath) throws IOException;

	/**
	 * Excelファイルを指定ファイルに書き込む<br>
	 *
	 * @param filePath
	 *            書き込む対象ファイル
	 * @throws IOException
	 */
	public void write(File targetFile) throws IOException;

	/**
	 * Excelファイルを書き込む<br>
	 *
	 * @param os
	 *            書き込む対象
	 * @throws IOException
	 */
	public void write(OutputStream os) throws IOException;
}
