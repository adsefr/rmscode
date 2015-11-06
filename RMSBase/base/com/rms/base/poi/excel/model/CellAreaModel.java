package com.rms.base.poi.excel.model;

/**
 * Cellの結合エリアを表すクラス
 * 
 * @author ri.meisei
 * @since 2013/12/19
 */
public class CellAreaModel implements Model {

	/** シート名 */
	private String sheetName = null;

	/** 開始行位置 */
	private int startRowIndex = -1;

	/** 終了行位置 */
	private int endRowIndex = -1;

	/** 開始列位置 */
	private int startColumnIndex = -1;

	/** 終了列位置 */
	private int endColumnIndex = -1;

	/** Cellのフォント情報 */
	private FontModel fontModel = null;

	/** 表示形式情報 */
	private CellStyleModel cellStyleModel = null;

	/**
	 * デフォールトコンストラクター
	 */
	public CellAreaModel() {

		reSet();
	}

	@Override
	public void reSet() {

		/** シート名 */
		sheetName = null;

		/** 開始行位置 */
		startRowIndex = -1;

		/** 終了行位置 */
		endRowIndex = -1;

		/** 開始列位置 */
		startColumnIndex = -1;

		/** 終了列位置 */
		endColumnIndex = -1;

		/** Cellのフォント情報 */
		fontModel = null;

		/** 表示形式情報 */
		cellStyleModel = null;
	}

	@Override
	public CellAreaModel copy() {

		try {
			CellAreaModel cellAreaModel = (CellAreaModel) super.clone();
			if (getFontModel() != null) {
				cellAreaModel.setFontModel(getFontModel().copy());
			}
			if (getCellStyleModel() != null) {
				cellAreaModel.setCellStyleModel(getCellStyleModel().copy());
			}
		} catch (CloneNotSupportedException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		return null;

	}

	/**
	 * @return sheetName
	 */
	public String getSheetName() {

		return sheetName;
	}

	/**
	 * @param sheetName
	 *            セットする sheetName
	 */
	public void setSheetName(String sheetName) {

		this.sheetName = sheetName;
	}

	/**
	 * @return startRowIndex
	 */
	public int getStartRowIndex() {

		return startRowIndex;
	}

	/**
	 * @param startRowIndex
	 *            セットする startRowIndex
	 */
	public void setStartRowIndex(int startRowIndex) {

		this.startRowIndex = startRowIndex;
	}

	/**
	 * @return endRowIndex
	 */
	public int getEndRowIndex() {

		return endRowIndex;
	}

	/**
	 * @param endRowIndex
	 *            セットする endRowIndex
	 */
	public void setEndRowIndex(int endRowIndex) {

		this.endRowIndex = endRowIndex;
	}

	/**
	 * @return startColumnIndex
	 */
	public int getStartColumnIndex() {

		return startColumnIndex;
	}

	/**
	 * @param startColumnIndex
	 *            セットする startColumnIndex
	 */
	public void setStartColumnIndex(int startColumnIndex) {

		this.startColumnIndex = startColumnIndex;
	}

	/**
	 * @return endColumnIndex
	 */
	public int getEndColumnIndex() {

		return endColumnIndex;
	}

	/**
	 * @param endColumnIndex
	 *            セットする endColumnIndex
	 */
	public void setEndColumnIndex(int endColumnIndex) {

		this.endColumnIndex = endColumnIndex;
	}

	/**
	 * @return fontModel
	 */
	public FontModel getFontModel() {

		return fontModel;
	}

	/**
	 * @param fontModel
	 *            セットする fontModel
	 */
	public void setFontModel(FontModel fontModel) {

		this.fontModel = fontModel;
	}

	/**
	 * @return cellStyleModel
	 */
	public CellStyleModel getCellStyleModel() {

		return cellStyleModel;
	}

	/**
	 * @param cellStyleModel
	 *            セットする cellStyleModel
	 */
	public void setCellStyleModel(CellStyleModel cellStyleModel) {

		this.cellStyleModel = cellStyleModel;
	}
}