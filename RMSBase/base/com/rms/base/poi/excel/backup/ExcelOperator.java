package com.rms.base.poi.excel.backup;

/**
 * Excelファイルを操作するインタフェース
 *
 * @author ri.meisei
 * @since 2013/12/19
 */
interface ExcelOperator extends SheetOperator {

	/**
	 * 指定のシート名でシートを作成する。<br>
	 *
	 * @param sheetName
	 *            シート名
	 * @return シート作成が成功した場合はtrueを返す。<br>
	 *         シート作成が失敗した場合はfalseを返す。<br>
	 */
	public boolean createSheet(String sheetName);

	/**
	 * 指定位置にシートを作成する。<br>
	 *
	 * @param sheetName
	 *            シート名
	 * @param sheetIndex
	 *            作成位置
	 * @return シート作成が成功した場合はtrueを返す。<br>
	 *         シート作成が失敗した場合はfalseを返す。<br>
	 */
	public boolean createSheet(String sheetName, int sheetIndex);

	/**
	 * 指定シートを選択されるシートに設定する。<br>
	 *
	 * @param sheetName
	 *            シート名
	 * @return true：成功の場合、 false：失敗の場合
	 */
	public boolean setSelectedSheet(String sheetName);

	/**
	 * 選択されるシートを設定する。<br>
	 *
	 * @param sheetIndex
	 *            シート位置
	 * @return true：成功の場合、 false：失敗の場合
	 */
	public boolean setSelectedSheet(int sheetIndex);

	/**
	 * 指定シート名のシートを削除する。<br>
	 *
	 * @param sheetName
	 *            シート名
	 * @return シート名が有効であるかつ存在するかつ削除が成功した場合、trueを返す。<br>
	 *         それ以外の場合、falseを返す。<br>
	 */
	public boolean removeSheet(String sheetName);

	/**
	 * 指定位置のシートを削除する。<br>
	 *
	 * @param sheetIndex
	 *            シート位置index
	 * @return sheetIndexが有効であるかつ削除が成功した場合、trueを返す。<br>
	 *         それ以外の場合、falseを返す。<br>
	 */
	public boolean removeSheet(int sheetIndex);

	/**
	 * 指定シート名以外のシートをすべて削除する。<br>
	 *
	 * @param sheetNames
	 *            シート名
	 * @return 指定シート名は一つ以上が存在するかつ削除が成功した場合、trueを返す。<br>
	 *         それい外の場合、falseを返す。<br>
	 */
	public boolean removeSheetsExclude(String... sheetNames);

	/**
	 * 指定シート位置以外のシートをすべて削除する。<br>
	 *
	 * @param sheetIndexs
	 *            シート位置
	 * @return 指定シート位置は一つ以上が有効するかつ削除が成功した場合、trueを返す。<br>
	 *         それい外の場合、falseを返す。<br>
	 */
	public boolean removeSheetsExclude(int... sheetIndex);

}
