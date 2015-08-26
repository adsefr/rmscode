package com.rms.common.poi.excel.model;

import com.rms.common.poi.excel.enums.CellType;

/**
 * Cellを表すクラス
 *
 * @author ri.meisei
 * @since 2013/12/19
 */
public class CellModel implements Model {

	/** シート名 */
	private String sheetName = null;

	/** 行位置 */
	private int rowIndex = -1;

	/** 列位置 */
	private int columnIndex = -1;

	/** Cellのタイプ */
	private CellType cellType = null;

	/** Cellの値 */
	private Object value = null;

	/** Cellのフォント情報 */
	private FontModel fontModel = null;

	/** Cellの表示形式情報 */
	private CellStyleModel cellStyleModel = null;

	/**
	 * デフォールトコンストラクター
	 */
	public CellModel() {

		reSet();
	}

	@Override
	public void reSet() {

		/** シート名 */
		sheetName = null;

		/** 行位置 */
		rowIndex = -1;

		/** 列位置 */
		columnIndex = -1;

		/** Cellのタイプ */
		cellType = null;

		/** Cellの値 */
		value = null;

		/** Cellのフォント情報 */
		fontModel = null;

		/** Cellの表示形式情報 */
		cellStyleModel = null;

	}

	@Override
	public CellModel copy() {

		try {
			CellModel cellModel = (CellModel) super.clone();
			if (getFontModel() != null) {
				cellModel.setFontModel(getFontModel().copy());
			}
			if (getCellStyleModel() != null) {
				cellModel.setCellStyleModel(getCellStyleModel().copy());
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
	 * @return rowIndex
	 */
	public int getRowIndex() {

		return rowIndex;
	}

	/**
	 * @param rowIndex
	 *            セットする rowIndex
	 */
	public void setRowIndex(int rowIndex) {

		this.rowIndex = rowIndex;
	}

	/**
	 * @return columnIndex
	 */
	public int getColumnIndex() {

		return columnIndex;
	}

	/**
	 * @param columnIndex
	 *            セットする columnIndex
	 */
	public void setColumnIndex(int columnIndex) {

		this.columnIndex = columnIndex;
	}

	/**
	 * @return cellType
	 */
	public CellType getCellType() {

		return cellType;
	}

	/**
	 * @param cellType
	 *            セットする cellType
	 */
	public void setCellType(CellType cellType) {

		this.cellType = cellType;
	}

	/**
	 * @return value
	 */
	@SuppressWarnings("unchecked")
	public <T> T getValue() {

		if (value == null) {
			return null;
		} else {
			return (T) value;
		}
	}

	/**
	 * @param value
	 *            セットする value
	 */
	public void setValue(Object value) {

		this.value = value;
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

	public static class Builder implements ModelBuilder<CellModel> {

		private final CellModel cellModel = new CellModel();

		Builder() {

		}

		public static Builder builder() {

			return new Builder();
		}

		@Override
		public CellModel build() {

			return cellModel.copy();
		}

		@Override
		public void clear() {

			cellModel.reSet();
		}

		/**
		 * シート名を設定する。
		 */
		public Builder sheetName(String sheetName) {

			cellModel.setSheetName(sheetName);

			return this;
		}

		/**
		 * 行位置を設定する。
		 */
		public Builder rowIndex(int rowIndex) {

			cellModel.setRowIndex(rowIndex);

			return this;
		}

		/**
		 * 列位置を設定する。
		 */
		public Builder columnIndex(int columnIndex) {

			cellModel.setColumnIndex(columnIndex);

			return this;
		}

		/**
		 * Cellのタイプを設定する。
		 */
		public Builder cellType(CellType cellType) {

			cellModel.setCellType(cellType);

			return this;
		}

		/**
		 * Cellの値を設定する。
		 */
		public Builder value(Object value) {

			cellModel.setValue(value);

			return this;
		}

		/**
		 * フォント情報を設定する。
		 */
		public Builder fontModel(FontModel fontModel) {

			cellModel.setFontModel(fontModel);
			return this;
		}

		/**
		 * Cellの表示形式情報を設定する。
		 */
		public Builder cellStyleModel(CellStyleModel cellStyleModel) {

			cellModel.setCellStyleModel(cellStyleModel);

			return this;
		}

	}
}
