package com.rms.common.poi.excel.enums;

import org.apache.poi.ss.usermodel.Font;

import com.rms.base.exception.UnexpectedDataException;

/**
 * 
 * 
 * @author ri.meisei
 * @since 2013/12/24
 */
public enum UnderlineType {

	/** not underlined */
	NONE(Font.U_NONE),

	/** single (normal) underline */
	SINGLE(Font.U_SINGLE),

	/** double underlined */
	DOBULE(Font.U_DOUBLE),

	/** accounting style single underline */
	SINGLE_ACCOUNTING(Font.U_SINGLE_ACCOUNTING),

	/** accounting style double underline */
	DOUBLE_ACCOUNTING(Font.U_DOUBLE_ACCOUNTING);

	private byte type;

	private UnderlineType(byte type) {

		this.type = type;
	}

	/**
	 * @return type
	 */
	public byte getType() {

		return type;
	}

	public static UnderlineType valueOf(short type) {

		for (UnderlineType underlineType : UnderlineType.values()) {
			if (underlineType.getType() == type) {
				return underlineType;
			}
		}

		throw new UnexpectedDataException("type:" + type);
	}
}
