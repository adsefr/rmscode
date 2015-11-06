package com.rms.base.poi.excel.model;

import java.awt.Color;

import com.rms.base.poi.excel.enums.AlignHType;
import com.rms.base.poi.excel.enums.AlignVType;
import com.rms.base.poi.excel.enums.BorderType;
import com.rms.base.poi.excel.enums.FillPatternType;

/**
 * Cellの表示形式情報を表すクラス
 * 
 * @author ri.meisei
 * @since 2013/12/19
 */
public class CellStyleModel implements Model {

	/** 横配置 */
	private AlignHType alignHorizontal = AlignHType.HORIZONTAL_LEFT;

	/** 縦配置 */
	private AlignVType alignVertical = AlignVType.VERTICAL_CENTER;

	/** 前背景色の填充方式 */
	private FillPatternType fillPatternType = FillPatternType.SOLID_FOREGROUND;

	/** 前背景色 */
	private Color foregroundColor = null;

	/** 上罫線の色 */
	private Color topBorderColor = null;

	/** 横の中間罫線の色 */
	private Color hMidBorderColor = null;

	/** 下罫線の色 */
	private Color bottomBorderColor = null;

	/** 左罫線の色 */
	private Color leftBorderColor = null;

	/** 縦の中間罫線の色 */
	private Color vMidBorderColor = null;

	/** 右罫線の色 */
	private Color rightBorderColor = null;

	/** 上罫線 */
	private BorderType topBorderType = BorderType.BORDER_NONE;

	/** 横の中間罫線 */
	private BorderType hMidBorderType = BorderType.BORDER_NONE;

	/** 下罫線 */
	private BorderType bottomBorderType = BorderType.BORDER_NONE;

	/** 左罫線 */
	private BorderType leftBorderType = BorderType.BORDER_NONE;

	/** 縦の中間罫線 */
	private BorderType vMidBorderType = BorderType.BORDER_NONE;

	/** 右罫線 */
	private BorderType rightBorderType = BorderType.BORDER_NONE;

	/** 改行可否フラグ */
	private boolean isWrapText = false;

	/** データ表示形式 */
	private String dataFormat = null;

	public CellStyleModel() {

		reSet();
	}

	@Override
	public void reSet() {

		/** 横配置 */
		alignHorizontal = AlignHType.HORIZONTAL_LEFT;

		/** 縦配置 */
		alignVertical = AlignVType.VERTICAL_CENTER;

		/** 前背景色の填充方式 */
		fillPatternType = FillPatternType.SOLID_FOREGROUND;

		/** 前背景色 */
		foregroundColor = null;

		/** 上罫線の色 */
		topBorderColor = null;

		/** 横の中間罫線の色 */
		hMidBorderColor = null;

		/** 下罫線の色 */
		bottomBorderColor = null;

		/** 左罫線の色 */
		leftBorderColor = null;

		/** 縦の中間罫線の色 */
		vMidBorderColor = null;

		/** 右罫線の色 */
		rightBorderColor = null;

		/** 上罫線 */
		topBorderType = BorderType.BORDER_NONE;

		/** 横の中間罫線 */
		hMidBorderType = BorderType.BORDER_NONE;

		/** 下罫線 */
		bottomBorderType = BorderType.BORDER_NONE;

		/** 左罫線 */
		leftBorderType = BorderType.BORDER_NONE;

		/** 縦の中間罫線 */
		vMidBorderType = BorderType.BORDER_NONE;

		/** 右罫線 */
		rightBorderType = BorderType.BORDER_NONE;

		/** 改行可否フラグ */
		isWrapText = false;

		/** データ表示形式 */
		dataFormat = null;
	}

	@Override
	public CellStyleModel copy() {

		try {
			return (CellStyleModel) super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		return null;
	}

	/*
	 * (非 Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((alignHorizontal == null) ? 0 : alignHorizontal.hashCode());
		result = prime * result + ((alignVertical == null) ? 0 : alignVertical.hashCode());
		result = prime * result + ((bottomBorderColor == null) ? 0 : bottomBorderColor.hashCode());
		result = prime * result + ((bottomBorderType == null) ? 0 : bottomBorderType.hashCode());
		result = prime * result + ((dataFormat == null) ? 0 : dataFormat.hashCode());
		result = prime * result + ((fillPatternType == null) ? 0 : fillPatternType.hashCode());
		result = prime * result + ((foregroundColor == null) ? 0 : foregroundColor.hashCode());
		result = prime * result + ((hMidBorderColor == null) ? 0 : hMidBorderColor.hashCode());
		result = prime * result + ((hMidBorderType == null) ? 0 : hMidBorderType.hashCode());
		result = prime * result + (isWrapText ? 1231 : 1237);
		result = prime * result + ((leftBorderColor == null) ? 0 : leftBorderColor.hashCode());
		result = prime * result + ((leftBorderType == null) ? 0 : leftBorderType.hashCode());
		result = prime * result + ((rightBorderColor == null) ? 0 : rightBorderColor.hashCode());
		result = prime * result + ((rightBorderType == null) ? 0 : rightBorderType.hashCode());
		result = prime * result + ((topBorderColor == null) ? 0 : topBorderColor.hashCode());
		result = prime * result + ((topBorderType == null) ? 0 : topBorderType.hashCode());
		result = prime * result + ((vMidBorderColor == null) ? 0 : vMidBorderColor.hashCode());
		result = prime * result + ((vMidBorderType == null) ? 0 : vMidBorderType.hashCode());
		return result;
	}

	/*
	 * (非 Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof CellStyleModel)) {
			return false;
		}
		CellStyleModel other = (CellStyleModel) obj;
		if (alignHorizontal != other.alignHorizontal) {
			return false;
		}
		if (alignVertical != other.alignVertical) {
			return false;
		}
		if (bottomBorderColor == null) {
			if (other.bottomBorderColor != null) {
				return false;
			}
		} else if (!bottomBorderColor.equals(other.bottomBorderColor)) {
			return false;
		}
		if (bottomBorderType != other.bottomBorderType) {
			return false;
		}
		if (dataFormat == null) {
			if (other.dataFormat != null) {
				return false;
			}
		} else if (!dataFormat.equals(other.dataFormat)) {
			return false;
		}
		if (fillPatternType != other.fillPatternType) {
			return false;
		}
		if (foregroundColor == null) {
			if (other.foregroundColor != null) {
				return false;
			}
		} else if (!foregroundColor.equals(other.foregroundColor)) {
			return false;
		}
		if (hMidBorderColor == null) {
			if (other.hMidBorderColor != null) {
				return false;
			}
		} else if (!hMidBorderColor.equals(other.hMidBorderColor)) {
			return false;
		}
		if (hMidBorderType != other.hMidBorderType) {
			return false;
		}
		if (isWrapText != other.isWrapText) {
			return false;
		}
		if (leftBorderColor == null) {
			if (other.leftBorderColor != null) {
				return false;
			}
		} else if (!leftBorderColor.equals(other.leftBorderColor)) {
			return false;
		}
		if (leftBorderType != other.leftBorderType) {
			return false;
		}
		if (rightBorderColor == null) {
			if (other.rightBorderColor != null) {
				return false;
			}
		} else if (!rightBorderColor.equals(other.rightBorderColor)) {
			return false;
		}
		if (rightBorderType != other.rightBorderType) {
			return false;
		}
		if (topBorderColor == null) {
			if (other.topBorderColor != null) {
				return false;
			}
		} else if (!topBorderColor.equals(other.topBorderColor)) {
			return false;
		}
		if (topBorderType != other.topBorderType) {
			return false;
		}
		if (vMidBorderColor == null) {
			if (other.vMidBorderColor != null) {
				return false;
			}
		} else if (!vMidBorderColor.equals(other.vMidBorderColor)) {
			return false;
		}
		if (vMidBorderType != other.vMidBorderType) {
			return false;
		}
		return true;
	}

	/**
	 * @return alignHorizontal
	 */
	public AlignHType getAlignHorizontal() {

		return alignHorizontal;
	}

	/**
	 * @param alignHorizontal
	 *            セットする alignHorizontal
	 */
	public void setAlignHorizontal(AlignHType alignHorizontal) {

		this.alignHorizontal = alignHorizontal;
	}

	/**
	 * @return alignVertical
	 */
	public AlignVType getAlignVertical() {

		return alignVertical;
	}

	/**
	 * @param alignVertical
	 *            セットする alignVertical
	 */
	public void setAlignVertical(AlignVType alignVertical) {

		this.alignVertical = alignVertical;
	}

	/**
	 * @return fillPatternType
	 */
	public FillPatternType getFillPatternType() {

		return fillPatternType;
	}

	/**
	 * @param fillPatternType
	 *            セットする fillPatternType
	 */
	public void setFillPatternType(FillPatternType fillPatternType) {

		this.fillPatternType = fillPatternType;
	}

	/**
	 * @return foregroundColor
	 */
	public Color getForegroundColor() {

		return foregroundColor;
	}

	/**
	 * @param foregroundColor
	 *            セットする foregroundColor
	 */
	public void setForegroundColor(Color foregroundColor) {

		this.foregroundColor = foregroundColor;
	}

	/**
	 * @return topBorderColor
	 */
	public Color getTopBorderColor() {

		return topBorderColor;
	}

	/**
	 * @param topBorderColor
	 *            セットする topBorderColor
	 */
	public void setTopBorderColor(Color topBorderColor) {

		this.topBorderColor = topBorderColor;
	}

	/**
	 * @return hMidBorderColor
	 */
	public Color getHMidBorderColor() {

		return hMidBorderColor;
	}

	/**
	 * @param hMidBorderColor
	 *            セットする hMidBorderColor
	 */
	public void setHMidBorderColor(Color hMidBorderColor) {

		this.hMidBorderColor = hMidBorderColor;
	}

	/**
	 * @return bottomBorderColor
	 */
	public Color getBottomBorderColor() {

		return bottomBorderColor;
	}

	/**
	 * @param bottomBorderColor
	 *            セットする bottomBorderColor
	 */
	public void setBottomBorderColor(Color bottomBorderColor) {

		this.bottomBorderColor = bottomBorderColor;
	}

	/**
	 * @return leftBorderColor
	 */
	public Color getLeftBorderColor() {

		return leftBorderColor;
	}

	/**
	 * @param leftBorderColor
	 *            セットする leftBorderColor
	 */
	public void setLeftBorderColor(Color leftBorderColor) {

		this.leftBorderColor = leftBorderColor;
	}

	/**
	 * @return vMidBorderColor
	 */
	public Color getVMidBorderColor() {

		return vMidBorderColor;
	}

	/**
	 * @param vMidBorderColor
	 *            セットする vMidBorderColor
	 */
	public void setVMidBorderColor(Color vMidBorderColor) {

		this.vMidBorderColor = vMidBorderColor;
	}

	/**
	 * @return rightBorderColor
	 */
	public Color getRightBorderColor() {

		return rightBorderColor;
	}

	/**
	 * @param rightBorderColor
	 *            セットする rightBorderColor
	 */
	public void setRightBorderColor(Color rightBorderColor) {

		this.rightBorderColor = rightBorderColor;
	}

	/**
	 * @return topBorderType
	 */
	public BorderType getTopBorderType() {

		return topBorderType;
	}

	/**
	 * @param topBorderType
	 *            セットする topBorderType
	 */
	public void setTopBorderType(BorderType topBorderType) {

		this.topBorderType = topBorderType;
	}

	/**
	 * @return hMidBorderType
	 */
	public BorderType getHMidBorderType() {

		return hMidBorderType;
	}

	/**
	 * @param hMidBorderType
	 *            セットする hMidBorderType
	 */
	public void setHMidBorderType(BorderType hMidBorderType) {

		this.hMidBorderType = hMidBorderType;
	}

	/**
	 * @return bottomBorderType
	 */
	public BorderType getBottomBorderType() {

		return bottomBorderType;
	}

	/**
	 * @param bottomBorderType
	 *            セットする bottomBorderType
	 */
	public void setBottomBorderType(BorderType bottomBorderType) {

		this.bottomBorderType = bottomBorderType;
	}

	/**
	 * @return leftBorderType
	 */
	public BorderType getLeftBorderType() {

		return leftBorderType;
	}

	/**
	 * @param leftBorderType
	 *            セットする leftBorderType
	 */
	public void setLeftBorderType(BorderType leftBorderType) {

		this.leftBorderType = leftBorderType;
	}

	/**
	 * @return vMidBorderType
	 */
	public BorderType getVMidBorderType() {

		return vMidBorderType;
	}

	/**
	 * @param vMidBorderType
	 *            セットする vMidBorderType
	 */
	public void setVMidBorderType(BorderType vMidBorderType) {

		this.vMidBorderType = vMidBorderType;
	}

	/**
	 * @return rightBorderType
	 */
	public BorderType getRightBorderType() {

		return rightBorderType;
	}

	/**
	 * @param rightBorderType
	 *            セットする rightBorderType
	 */
	public void setRightBorderType(BorderType rightBorderType) {

		this.rightBorderType = rightBorderType;
	}

	/**
	 * @return isWrapText
	 */
	public boolean isWrapText() {

		return isWrapText;
	}

	/**
	 * @param isWrapText
	 *            セットする isWrapText
	 */
	public void setWrapText(boolean isWrapText) {

		this.isWrapText = isWrapText;
	}

	/**
	 * @return dataFormat
	 */
	public String getDataFormat() {

		return dataFormat;
	}

	/**
	 * @param dataFormat
	 *            セットする dataFormat
	 */
	public void setDataFormat(String dataFormat) {

		this.dataFormat = dataFormat;
	}

	public static class Builder implements ModelBuilder<CellStyleModel> {

		private final CellStyleModel cellStyleModel = new CellStyleModel();

		private Builder() {

		}

		@Override
		public CellStyleModel build() {

			return cellStyleModel.copy();
		}

		@Override
		public void clear() {

			cellStyleModel.reSet();
		}

		/**
		 * 横配置を設定する。
		 */
		public Builder alignHorizontal(AlignHType alignHorizontal) {

			cellStyleModel.setAlignHorizontal(alignHorizontal);

			return this;
		}

		/**
		 * 縦配置を設定する。
		 */
		public Builder alignVertical(AlignVType alignVertical) {

			cellStyleModel.setAlignVertical(alignVertical);

			return this;
		}

		/**
		 * 前背景色の填充方式を設定する。
		 */
		public Builder fillPatternType(FillPatternType fillPatternType) {

			cellStyleModel.setFillPatternType(fillPatternType);

			return this;
		}

		/**
		 * 前背景色を設定する。
		 */
		public Builder foregroundColor(Color foregroundColor) {

			cellStyleModel.setForegroundColor(foregroundColor);

			return this;
		}

		/**
		 * 上罫線の色を設定する。
		 */
		public Builder topBorderColor(Color topBorderColor) {

			cellStyleModel.setTopBorderColor(topBorderColor);

			return this;
		}

		/**
		 * 横の中間罫線の色を設定する。
		 */
		public Builder hMidBorderColor(Color hMidBorderColor) {

			cellStyleModel.setHMidBorderColor(hMidBorderColor);

			return this;
		}

		/**
		 * 下罫線の色を設定する。
		 */
		public Builder bottomBorderColor(Color bottomBorderColor) {

			cellStyleModel.setBottomBorderColor(bottomBorderColor);

			return this;
		}

		/**
		 * 左罫線の色を設定する。
		 */
		public Builder leftBorderColor(Color leftBorderColor) {

			cellStyleModel.setLeftBorderColor(leftBorderColor);

			return this;
		}

		/**
		 * 縦の中間罫線の色を設定する。
		 */
		public Builder vMidBorderColor(Color vMidBorderColor) {

			cellStyleModel.setVMidBorderColor(vMidBorderColor);

			return this;
		}

		/**
		 * 右罫線の色を設定する。
		 */
		public Builder rightBorderColor(Color rightBorderColor) {

			cellStyleModel.setRightBorderColor(rightBorderColor);

			return this;
		}

		/**
		 * 上罫線を設定する。
		 */
		public Builder topBorderType(BorderType topBorderType) {

			cellStyleModel.setTopBorderType(topBorderType);

			return this;
		}

		/**
		 * 横中間罫線を設定する。
		 */
		public Builder hMidBorderType(BorderType hMidBorderType) {

			cellStyleModel.setHMidBorderType(hMidBorderType);

			return this;
		}

		/**
		 * 下罫線を設定する。
		 */
		public Builder bottomBorderType(BorderType bottomBorderType) {

			cellStyleModel.setBottomBorderType(bottomBorderType);

			return this;
		}

		/**
		 * 左罫線を設定する。
		 */
		public Builder leftBorderType(BorderType leftBorderType) {

			cellStyleModel.setLeftBorderType(leftBorderType);

			return this;
		}

		/**
		 * 縦の中間罫線を設定する。
		 */
		public Builder vMidBorderType(BorderType vMidBorderType) {

			cellStyleModel.setVMidBorderType(vMidBorderType);

			return this;
		}

		/**
		 * 右罫線を設定する。
		 */
		public Builder rightBorderType(BorderType rightBorderType) {

			cellStyleModel.setRightBorderType(rightBorderType);

			return this;
		}

		/**
		 * 改行可否フラグを設定する。
		 */
		public Builder wrapText(boolean isWrapText) {

			cellStyleModel.setWrapText(isWrapText);

			return this;
		}

		/**
		 * データ表示形式を設定する。
		 */
		public Builder dataFormat(String dataFormat) {

			cellStyleModel.setDataFormat(dataFormat);

			return this;
		}
	}
}
