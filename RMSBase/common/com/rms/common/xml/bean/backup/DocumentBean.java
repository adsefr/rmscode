package com.rms.common.xml.bean.backup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DocumentBean extends NodeBean {

	/** DOCTYPEのschemaLocation */
	private String schemaLocation;

	/** DOCTYPEのSYSTEMID */
	private String systemID;

	/** DOCTYPEのPUBLICID */
	private String publicID;

	/** documentのファイルパス */
	private String documentFilePath;

	/** XMLのENCODING */
	private String XMLEncoding;

	private ElementBean rootElementBean;

	private Map<String, List<ElementBean>> xpathMap = new HashMap<String, List<ElementBean>>();

	/**
	 * デフォルトコンストラクター
	 */
	public DocumentBean() {

	}

	/**
	 * @return schemaLocation
	 */
	public String getSchemaLocation() {

		return schemaLocation;
	}

	/**
	 * @param schemaLocation
	 *            セットする schemaLocation
	 */
	public void setSchemaLocation(String schemaLocation) {

		this.schemaLocation = schemaLocation;
	}

	/**
	 * @return systemID
	 */
	public String getSystemID() {

		return systemID;
	}

	/**
	 * @param systemID
	 *            セットする systemID
	 */
	public void setSystemID(String systemID) {

		this.systemID = systemID;
	}

	/**
	 * @return publicID
	 */
	public String getPublicID() {

		return publicID;
	}

	/**
	 * @param publicID
	 *            セットする publicID
	 */
	public void setPublicID(String publicID) {

		this.publicID = publicID;
	}

	/**
	 * @return documentFilePath
	 */
	public String getDocumentFilePath() {

		return documentFilePath;
	}

	/**
	 * @param documentFilePath
	 *            セットする documentFilePath
	 */
	public void setDocumentFilePath(String documentFilePath) {

		this.documentFilePath = documentFilePath;
	}

	/**
	 * @return xMLEncoding
	 */
	public String getXMLEncoding() {

		return XMLEncoding;
	}

	/**
	 * @param xMLEncoding
	 *            セットする xMLEncoding
	 */
	public void setXMLEncoding(String xMLEncoding) {

		XMLEncoding = xMLEncoding;
	}

	/**
	 * @return xpathMap
	 */
	public Map<String, List<ElementBean>> getXpathMap() {

		return xpathMap;
	}

	/**
	 * @param xpathMap
	 *            セットする xpathMap
	 */
	public void setXpathMap(Map<String, List<ElementBean>> xpathMap) {

		this.xpathMap = xpathMap;
	}

	/**
	 * @return rootElementBean
	 */
	public ElementBean getRootElementBean() {

		return rootElementBean;
	}

	/**
	 * @param rootElementBean
	 *            セットする rootElementBean
	 */
	public void setRootElementBean(ElementBean rootElementBean) {

		this.rootElementBean = rootElementBean;
	}

	public String toXMLString() {

		return rootElementBean.toXMLString();
	}
}
