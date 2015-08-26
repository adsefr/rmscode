package com.rms.common.xml.bean.backup;

import java.util.ArrayList;
import java.util.List;

public class DocumentInfo {

	private static final int DEFALUT_ELEMENT_SIZE = 50;

	/** DOCTYPEのschemaLocation */
	private String schemaLocation;

	/** DOCTYPEのSYSTEMID */
	private String systemID;

	/** DOCTYPEのPUBLICID */
	private String publicID;

	/** documentのファイルパス */
	private String docmentFilePath;

	/** documentのENCODING */
	private String docmentFileEncoding;

	/** documentのelementの情報 */
	private List<ElementInfo> elementInfoList = new ArrayList<ElementInfo>();

	/**
	 * デフォルトコンストラクター
	 */
	public DocumentInfo() {

		elementInfoList = new ArrayList<ElementInfo>();
		while (elementInfoList.size() < DEFALUT_ELEMENT_SIZE) {
			elementInfoList.add(new ElementInfo());
		}
	}

	/**
	 * 内部持ち情報をクリアする
	 */
	public void clear() {

		systemID = null;
		publicID = null;
		docmentFilePath = null;
		docmentFileEncoding = null;

		for (ElementInfo elementInfo : elementInfoList) {
			elementInfo.clear();
		}

		elementInfoList.clear();
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
	 * @return docmentFilePath
	 */
	public String getDocmentFilePath() {

		return docmentFilePath;
	}

	/**
	 * @param docmentFilePath
	 *            セットする docmentFilePath
	 */
	public void setDocmentFilePath(String docmentFilePath) {

		this.docmentFilePath = docmentFilePath;
	}

	/**
	 * @return docmentFileEncoding
	 */
	public String getDocmentFileEncoding() {

		return docmentFileEncoding;
	}

	/**
	 * @param docmentFileEncoding
	 *            セットする docmentFileEncoding
	 */
	public void setDocmentFileEncoding(String docmentFileEncoding) {

		this.docmentFileEncoding = docmentFileEncoding;
	}

	/**
	 * @return elementInfoList
	 */
	public List<ElementInfo> getElementInfoList() {

		return elementInfoList;
	}

	/**
	 * @param elementInfoList
	 *            セットする elementInfoList
	 */
	public void setElementInfoList(List<ElementInfo> elementInfoList) {

		this.elementInfoList = elementInfoList;
	}

}
