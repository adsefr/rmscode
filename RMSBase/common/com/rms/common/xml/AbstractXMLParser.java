package com.rms.common.xml;

/**
 * 
 * @author ri.meisei
 * @since 2014/01/22
 */
abstract class AbstractXMLParser implements XMLParser {

	private boolean namespaceAware;

	private boolean validating;

	protected AbstractXMLParser() {

	}

	/**
	 * @param namespaceAware
	 *            セットする namespaceAware
	 */
	public void setNamespaceAware(boolean namespaceAware) {

		this.namespaceAware = namespaceAware;
	}

	/**
	 * @param validating
	 *            セットする validating
	 */
	public void setValidating(boolean validating) {

		this.validating = validating;
	}

	/**
	 * @return namespaceAware
	 */
	protected boolean isNamespaceAware() {

		return namespaceAware;
	}

	/**
	 * @return validating
	 */
	protected boolean isValidating() {

		return validating;
	}

}
