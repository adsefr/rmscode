package com.rms.common.xml.dtd.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DTDElementBean {

	private String name;

	private String content;

	private boolean isMixedContent;

	private CardinalType cardinalType;

	private List<DTDItemBean> dtdItemBeans;

	private Map<String, DTDAttributeBean> attribueMap = new HashMap<String, DTDAttributeBean>();

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
	 * @return content
	 */
	public String getContent() {

		return content;
	}

	/**
	 * @param content
	 *            セットする content
	 */
	public void setContent(String content) {

		this.content = content;
	}

	/**
	 * @return isMixedContent
	 */
	public boolean isMixedContent() {

		return isMixedContent;
	}

	/**
	 * @param isMixedContent
	 *            セットする isMixedContent
	 */
	public void setMixedContent(boolean isMixedContent) {

		this.isMixedContent = isMixedContent;
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

	/**
	 * @return dtdItemBeans
	 */
	public List<DTDItemBean> getDtdItemBeans() {

		return dtdItemBeans;
	}

	/**
	 * @param dtdItemBeans
	 *            セットする dtdItemBeans
	 */
	public void setDtdItemBeans(List<DTDItemBean> dtdItemBeans) {

		this.dtdItemBeans = dtdItemBeans;
	}

	/**
	 * @return attribueMap
	 */
	public Map<String, DTDAttributeBean> getAttribueMap() {

		return attribueMap;
	}

	/**
	 * @param attribueMap
	 *            セットする attribueMap
	 */
	public void setAttribueMap(Map<String, DTDAttributeBean> attribueMap) {

		this.attribueMap = attribueMap;
	}

}
