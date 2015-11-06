package com.rms.base.poi.excel.enums;

import org.apache.poi.ss.usermodel.CellStyle;

import com.rms.base.exception.UnexpectedDataException;

/**
 * 
 * 
 * @author ri.meisei
 * @since 2013/12/24
 */
public enum BorderStyleType {

	/**
	 * No border
	 */
	BORDER_NONE(CellStyle.BORDER_NONE),

	/**
	 * Thin border
	 */
	BORDER_THIN(CellStyle.BORDER_THIN),

	/**
	 * Medium border
	 */
	BORDER_MEDIUM(CellStyle.BORDER_MEDIUM),

	/**
	 * dash border
	 */
	BORDER_DASHED(CellStyle.BORDER_DASHED),

	/**
	 * dot border
	 */
	BORDER_HAIR(CellStyle.BORDER_HAIR),

	/**
	 * Thick border
	 */
	BORDER_THICK(CellStyle.BORDER_THICK),

	/**
	 * double-line border
	 */
	BORDER_DOUBLE(CellStyle.BORDER_DOUBLE),

	/**
	 * hair-line border
	 */
	BORDER_DOTTED(CellStyle.BORDER_DOTTED),

	/**
	 * Medium dashed border
	 */
	BORDER_MEDIUM_DASHED(CellStyle.BORDER_MEDIUM_DASHED),

	/**
	 * dash-dot border
	 */
	BORDER_DASH_DOT(CellStyle.BORDER_DASH_DOT),

	/**
	 * medium dash-dot border
	 */
	BORDER_MEDIUM_DASH_DOT(CellStyle.BORDER_MEDIUM_DASH_DOT),

	/**
	 * dash-dot-dot border
	 */
	BORDER_DASH_DOT_DOT(CellStyle.BORDER_DASH_DOT_DOT),

	/**
	 * medium dash-dot-dot border
	 */
	BORDER_MEDIUM_DASH_DOT_DOT(CellStyle.BORDER_MEDIUM_DASH_DOT_DOT),

	/**
	 * slanted dash-dot border
	 */
	BORDER_SLANTED_DASH_DOT(CellStyle.BORDER_SLANTED_DASH_DOT);

	private short type;

	private BorderStyleType(short type) {

		this.type = type;
	}

	/**
	 * @return type
	 */
	public short getType() {

		return type;
	}

	public static BorderStyleType valueOf(short type) {

		for (BorderStyleType borderStyleType : BorderStyleType.values()) {
			if (borderStyleType.getType() == type) {
				return borderStyleType;
			}
		}

		throw new UnexpectedDataException("type:" + type);
	}
}
