package com.rms.base.poi.excel.enums;

import org.apache.poi.ss.usermodel.Font;

import com.rms.base.exception.UnexpectedDataException;

/**
 * 
 * 
 * @author ri.meisei
 * @since 2013/12/24
 */
public enum BoldType {
	/**
	 * デフォルト
	 */
	NORMAL(Font.BOLDWEIGHT_NORMAL),
	/**
	 * 太字
	 */
	BOLD(Font.BOLDWEIGHT_BOLD),;

	private short type;

	private BoldType(short type) {

		this.type = type;
	}

	/**
	 * @return type
	 */
	public short getType() {

		return type;
	}

	public static BoldType valueOf(short type) {

		for (BoldType boldType : BoldType.values()) {
			if (boldType.getType() == type) {
				return boldType;
			}
		}

		throw new UnexpectedDataException("type:" + type);
	}
}
