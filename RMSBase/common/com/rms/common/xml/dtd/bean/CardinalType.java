package com.rms.common.xml.dtd.bean;

public enum CardinalType {

	ONE(0, "1"), OPTIONAL(1, "?"), ZEROMANY(2, "*"), ONEMANY(3, "+");

	private int type;

	private String value;

	private CardinalType(int type, String value) {

		this.type = type;
		this.value = value;
	}

	/**
	 * @return type
	 */
	public int getType() {

		return type;
	}

	/**
	 * @return value
	 */
	public String getValue() {

		return value;
	}

	/**
	 *
	 */
	@Override
	public String toString() {

		return getValue();
	}

	/**
	 * 
	 * @param type
	 * @return
	 */
	public static CardinalType get(int type) {

		if (type == 0) {
			return ONE;
		}
		if (type == 1) {
			return OPTIONAL;
		}
		if (type == 2) {
			return ZEROMANY;
		}
		if (type == 3) {
			return ONEMANY;
		}

		return null;
	}
}
