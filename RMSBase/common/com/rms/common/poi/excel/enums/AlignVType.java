package com.rms.common.poi.excel.enums;

import org.apache.poi.ss.usermodel.CellStyle;

import com.rms.base.exception.UnexpectedDataException;

/**
 * 
 * 
 * @author ri.meisei
 * @since 2013/12/24
 */
public enum AlignVType {

	/**
	 * top-aligned vertical alignment
	 */

	VERTICAL_TOP(CellStyle.VERTICAL_TOP),

	/**
	 * center-aligned vertical alignment
	 */

	VERTICAL_CENTER(CellStyle.VERTICAL_CENTER),

	/**
	 * bottom-aligned vertical alignment
	 */

	VERTICAL_BOTTOM(CellStyle.VERTICAL_BOTTOM),

	/**
	 * vertically justified vertical alignment
	 */

	VERTICAL_JUSTIFY(CellStyle.VERTICAL_JUSTIFY);

	private short type;

	private AlignVType(short type) {

		this.type = type;
	}

	/**
	 * @return type
	 */
	public short getType() {

		return type;
	}

	public static AlignVType valueOf(short type) {

		for (AlignVType alignVType : AlignVType.values()) {
			if (alignVType.getType() == type) {
				return alignVType;
			}
		}

		throw new UnexpectedDataException("type:" + type);
	}
}
