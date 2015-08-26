package com.rms.common.poi.excel.enums;

import org.apache.poi.ss.usermodel.CellStyle;

import com.rms.base.exception.UnexpectedDataException;

/**
 * 
 * 
 * @author ri.meisei
 * @since 2013/12/24
 */
public enum AlignHType {

	/**
	 * general (normal) horizontal alignment
	 */

	HORIZONTAL_GENERAL(CellStyle.ALIGN_GENERAL),

	/**
	 * left-justified horizontal alignment
	 */

	HORIZONTAL_LEFT(CellStyle.ALIGN_LEFT),

	/**
	 * center horizontal alignment
	 */

	HORIZONTAL_CENTER(CellStyle.ALIGN_CENTER),

	/**
	 * right-justified horizontal alignment
	 */

	HORIZONTAL_RIGHT(CellStyle.ALIGN_RIGHT),

	/**
	 * fill? horizontal alignment
	 */

	HORIZONTAL_FILL(CellStyle.ALIGN_FILL),

	/**
	 * justified horizontal alignment
	 */

	HORIZONTAL_JUSTIFY(CellStyle.ALIGN_JUSTIFY),

	/**
	 * center-selection? horizontal alignment
	 */

	HORIZONTAL_CENTER_SELECTION(CellStyle.ALIGN_CENTER_SELECTION);

	private short type;

	private AlignHType(short type) {

		this.type = type;
	}

	/**
	 * @return type
	 */
	public short getType() {

		return type;
	}

	public static AlignHType valueOf(short type) {

		for (AlignHType alignHType : AlignHType.values()) {
			if (alignHType.getType() == type) {
				return alignHType;
			}
		}

		throw new UnexpectedDataException("type:" + type);
	}
}
