package com.rms.common.xml.dtd.bean;

public class DTDAttributeBean {

	private String name;

	private String type;

	private String declaration;

	private String defaultValue;

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
	 * @return type
	 */
	public String getType() {

		return type;
	}

	/**
	 * @param type
	 *            セットする type
	 */
	public void setType(String type) {

		this.type = type;
	}

	/**
	 * @return declaration
	 */
	public String getDeclaration() {

		return declaration;
	}

	/**
	 * @param declaration
	 *            セットする declaration
	 */
	public void setDeclaration(String declaration) {

		this.declaration = declaration;
	}

	/**
	 * @return defaultValue
	 */
	public String getDefaultValue() {

		return defaultValue;
	}

	/**
	 * @param defaultValue
	 *            セットする defaultValue
	 */
	public void setDefaultValue(String defaultValue) {

		this.defaultValue = defaultValue;
	}

}
