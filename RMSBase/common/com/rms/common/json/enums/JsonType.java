package com.rms.common.json.enums;

/**
 * 
 * @author ri.meisei
 * @since 2014/01/16
 */
public enum JsonType {
	/**
	 * objects
	 */
	OBJECT(11),
	/**
	 * arrays
	 */
	ARRAY(12),

	/**
	 * strings
	 */
	STRING(21),

	/**
	 * numbers
	 */
	NUMBER(22),

	/**
	 * booleans
	 */
	BOOLEAN(23),

	/**
	 * null
	 */
	NULL(24);

	private int type;

	private JsonType(int type) {

		this.type = type;
	}

	public int getType() {

		return type;
	}
}
