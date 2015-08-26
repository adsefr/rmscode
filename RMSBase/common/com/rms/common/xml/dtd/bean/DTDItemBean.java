package com.rms.common.xml.dtd.bean;

public class DTDItemBean {

	private String name;

	private CardinalType cardinalType;

	/**
	 * @return name
	 */
	public String getName() {

		return name;
	}

	/**
	 * @param name
	 *            セットする name
	 */
	public void setName(String name) {

		this.name = name;
	}

	/**
	 * @return cardinalType
	 */
	public CardinalType getCardinalType() {

		return cardinalType;
	}

	/**
	 * @param cardinalType
	 *            セットする cardinalType
	 */
	public void setCardinalType(CardinalType cardinalType) {

		this.cardinalType = cardinalType;
	}

}
