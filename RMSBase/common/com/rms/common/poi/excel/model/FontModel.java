package com.rms.common.poi.excel.model;

import java.awt.Color;

import com.rms.common.poi.excel.enums.BoldType;
import com.rms.common.poi.excel.enums.UnderlineType;

/**
 * Cellの文字情報を表すクラス
 * 
 * @author ri.meisei
 * @since 2013/12/19
 */
public class FontModel implements Model {

	/** フォント名称 */
	private String fontName = null;

	/** 太字 */
	private BoldType boldType = BoldType.NORMAL;

	/** 斜体 */
	private boolean isItalic = false;

	/** 取り消し線 */
	private boolean isStrikeout = false;

	/** 下線 */
	private UnderlineType underlineType = UnderlineType.NONE;

	/** フォントサイズ */
	private short fontSize = 11;

	/** フォントの色 */
	private Color fontColor = Color.BLACK;

	public FontModel() {

		reSet();
	}

	@Override
	public void reSet() {

		/** フォント名称 */
		fontName = null;

		/** 太字 */
		boldType = BoldType.NORMAL;

		/** 斜体 */
		isItalic = false;

		/** 取り消し線 */
		isStrikeout = false;

		/** 下線 */
		underlineType = UnderlineType.NONE;

		/** フォントサイズ */
		fontSize = 11;

		/** フォントの色 */
		fontColor = Color.BLACK;
	}

	@Override
	public FontModel copy() {

		try {
			return (FontModel) super.clone();
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
		result = prime * result + ((boldType == null) ? 0 : boldType.hashCode());
		result = prime * result + ((fontColor == null) ? 0 : fontColor.hashCode());
		result = prime * result + ((fontName == null) ? 0 : fontName.hashCode());
		long temp;
		temp = Double.doubleToLongBits(fontSize);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (isItalic ? 1231 : 1237);
		result = prime * result + (isStrikeout ? 1231 : 1237);
		result = prime * result + ((underlineType == null) ? 0 : underlineType.hashCode());
		return result;
	}

	/*
	 * (非 Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FontModel other = (FontModel) obj;
		if (boldType != other.boldType)
			return false;
		if (fontColor == null) {
			if (other.fontColor != null)
				return false;
		} else if (!fontColor.equals(other.fontColor))
			return false;
		if (fontName == null) {
			if (other.fontName != null)
				return false;
		} else if (!fontName.equals(other.fontName))
			return false;
		if (Double.doubleToLongBits(fontSize) != Double.doubleToLongBits(other.fontSize))
			return false;
		if (isItalic != other.isItalic)
			return false;
		if (isStrikeout != other.isStrikeout)
			return false;
		if (underlineType != other.underlineType)
			return false;
		return true;
	}

	/**
	 * @return fontName
	 */
	public String getFontName() {

		return fontName;
	}

	/**
	 * @param fontName
	 *            セットする fontName
	 */
	public void setFontName(String fontName) {

		this.fontName = fontName;
	}

	/**
	 * @return boldType
	 */
	public BoldType getBoldType() {

		return boldType;
	}

	/**
	 * @param boldType
	 *            セットする boldType
	 */
	public void setBoldType(BoldType boldType) {

		this.boldType = boldType;
	}

	/**
	 * @return isItalic
	 */
	public boolean isItalic() {

		return isItalic;
	}

	/**
	 * @param isItalic
	 *            セットする isItalic
	 */
	public void setItalic(boolean isItalic) {

		this.isItalic = isItalic;
	}

	/**
	 * @return isStrikeout
	 */
	public boolean isStrikeout() {

		return isStrikeout;
	}

	/**
	 * @param isStrikeout
	 *            セットする isStrikeout
	 */
	public void setStrikeout(boolean isStrikeout) {

		this.isStrikeout = isStrikeout;
	}

	/**
	 * @return underlineType
	 */
	public UnderlineType getUnderlineType() {

		return underlineType;
	}

	/**
	 * @param underlineType
	 *            セットする underlineType
	 */
	public void setUnderlineType(UnderlineType underlineType) {

		this.underlineType = underlineType;
	}

	/**
	 * @return fontSize
	 */
	public final short getFontSize() {

		return fontSize;
	}

	/**
	 * @param fontSize
	 *            セットする fontSize
	 */
	public final void setFontSize(short fontSize) {

		this.fontSize = fontSize;
	}

	/**
	 * @return fontColor
	 */
	public Color getFontColor() {

		return fontColor;
	}

	/**
	 * @param fontColor
	 *            セットする fontColor
	 */
	public void setFontColor(Color fontColor) {

		this.fontColor = fontColor;
	}

	public static class Builder implements ModelBuilder<FontModel> {

		private final FontModel fontModel = new FontModel();

		private Builder() {

		}

		/**
		 * 
		 * @return
		 */
		public static Builder builder() {

			return new Builder();
		}

		@Override
		public FontModel build() {

			return fontModel.copy();
		}

		@Override
		public void clear() {

			fontModel.reSet();
		}

		/**
		 * フォント名を設定する。
		 */
		public Builder setFontName(String fontName) {

			fontModel.setFontName(fontName);

			return this;
		}

		/**
		 * 文字サイズを設定する。
		 */
		public Builder setFontSize(short fontSize) {

			fontModel.setFontSize(fontSize);

			return this;
		}

		/**
		 * 文字色を設定する。
		 */
		public Builder setFontColor(Color fontColor) {

			fontModel.setFontColor(fontColor);

			return this;

		}

		/**
		 * 太字を設定する。
		 */
		public Builder setBoldType(BoldType boldType) {

			fontModel.setBoldType(boldType);

			return this;
		}

		/**
		 * 斜体を設定する。
		 */
		public Builder setItalic(boolean isItalic) {

			fontModel.setItalic(isItalic);

			return this;
		}

		/**
		 * 取り消し線を設定する。
		 */
		public Builder setStrikeout(boolean isStrikeout) {

			fontModel.setStrikeout(isStrikeout);

			return this;
		}

		/**
		 * 下線を設定する。
		 */
		public Builder setUnderlineType(UnderlineType underlineType) {

			fontModel.setUnderlineType(underlineType);

			return this;
		}

	}
}
