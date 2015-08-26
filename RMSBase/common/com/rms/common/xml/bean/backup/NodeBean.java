package com.rms.common.xml.bean.backup;

import com.rms.common.xml.enums.NodeType;

/**
 * 
 * 
 * @author ri.meisei
 * @since 2013/12/18
 */
public class NodeBean {

	/** NamespaceのPrefix */
	private String namespacePrefix;

	/** NamespaceのURI */
	private String namespaceURI;

	/** NodeのXPATH */
	private String xpath;

	/** Nodeの階層 */
	private int level;

	/** Nodeの名称 */
	private String name;

	/** NodeのTag名称(namespacePrefix+name) */
	private String tagName;

	/** Nodeのテキスト値 */
	private String textValue;

	/** NodeのXML値 */
	private String xmlValue;

	/** Nodeの出現回数 */
	private int times;

	/** 解析の順番 */
	private int seqNo;

	private NodeType nodeType;

	/**
	 * デフォールトコンストラクター
	 */
	public NodeBean() {

	}

	/**
	 * @return namespacePrefix
	 */
	public String getNamespacePrefix() {

		return namespacePrefix;
	}

	/**
	 * @param namespacePrefix
	 *            セットする namespacePrefix
	 */
	public void setNamespacePrefix(String namespacePrefix) {

		this.namespacePrefix = namespacePrefix;
	}

	/**
	 * @return namespaceURI
	 */
	public String getNamespaceURI() {

		return namespaceURI;
	}

	/**
	 * @param namespaceURI
	 *            セットする namespaceURI
	 */
	public void setNamespaceURI(String namespaceURI) {

		this.namespaceURI = namespaceURI;
	}

	/**
	 * @return xpath
	 */
	public String getXpath() {

		return xpath;
	}

	/**
	 * @param xpath
	 *            セットする xpath
	 */
	public void setXpath(String xpath) {

		this.xpath = xpath;
	}

	/**
	 * @return level
	 */
	public int getLevel() {

		return level;
	}

	/**
	 * @param level
	 *            セットする level
	 */
	public void setLevel(int level) {

		this.level = level;
	}

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
	 * @return tagName
	 */
	public String getTagName() {

		return tagName;
	}

	/**
	 * @param tagName
	 *            セットする tagName
	 */
	public void setTagName(String tagName) {

		this.tagName = tagName;
	}

	/**
	 * @return textValue
	 */
	public String getTextValue() {

		return textValue;
	}

	/**
	 * @param textValue
	 *            セットする textValue
	 */
	public void setTextValue(String textValue) {

		this.textValue = textValue;
	}

	/**
	 * @return xmlValue
	 */
	public String getXmlValue() {

		return xmlValue;
	}

	/**
	 * @param xmlValue
	 *            セットする xmlValue
	 */
	public void setXmlValue(String xmlValue) {

		this.xmlValue = xmlValue;
	}

	/**
	 * @return times
	 */
	public int getTimes() {

		return times;
	}

	/**
	 * @param times
	 *            セットする times
	 */
	public void setTimes(int times) {

		this.times = times;
	}

	/**
	 * @return seqNo
	 */
	public int getSeqNo() {

		return seqNo;
	}

	/**
	 * @param seqNo
	 *            セットする seqNo
	 */
	public void setSeqNo(int seqNo) {

		this.seqNo = seqNo;
	}

	/**
	 * @return nodeType
	 */
	public NodeType getNodeType() {

		return nodeType;
	}

	/**
	 * @param nodeType
	 *            セットする nodeType
	 */
	public void setNodeType(NodeType nodeType) {

		this.nodeType = nodeType;
	}

}
