package com.rms.common.xml.bean.backup;

import java.util.ArrayList;
import java.util.List;

import com.rms.base.constant.Characters;
import com.rms.base.util.TextUtil;
import com.rms.common.xml.enums.NodeType;

/**
 * 
 * @author ri.meisei
 * @since 2013/12/18
 */
public class ElementBean extends NodeBean {

	private ElementBean parent;

	private List<ElementBean> childrens = new ArrayList<ElementBean>();

	private List<AttributeBean> attributes = new ArrayList<AttributeBean>();

	/**
	 * デフォルトコンストラクター
	 */
	public ElementBean() {

		setNodeType(NodeType.ELEMENT);
	}

	/**
	 * @return parent
	 */
	public ElementBean getParent() {

		return parent;
	}

	/**
	 * @param parent
	 *            セットする parent
	 */
	public void setParent(ElementBean parent) {

		this.parent = parent;
	}

	/**
	 * @return childrens
	 */
	public List<ElementBean> getChildrens() {

		return childrens;
	}

	/**
	 * @param childrens
	 *            セットする childrens
	 */
	public void setChildrens(List<ElementBean> childrens) {

		this.childrens = childrens;
	}

	/**
	 * @return attributes
	 */
	public List<AttributeBean> getAttributes() {

		return attributes;
	}

	/**
	 * @param attributes
	 *            セットする attributes
	 */
	public void setAttributes(List<AttributeBean> attributes) {

		this.attributes = attributes;
	}

	public String toXMLString() {

		StringBuilder builder = new StringBuilder();

		builder.append(printStartTag());
		builder.append(printTagBody());
		builder.append(printEndTag());

		return builder.toString();
	}

	private String printStartTag() {

		StringBuilder builder = new StringBuilder();

		builder.append(TextUtil.lPad("", getLevel() - 1));

		builder.append("<").append(getTagName());

		if (!attributes.isEmpty()) {
			builder.append(" ");
			for (AttributeBean attributeBean : attributes) {
				builder.append(attributeBean.toXMLString()).append(" ");
			}
			builder.deleteCharAt(builder.length() - 1);
		}

		if (childrens.isEmpty() && TextUtil.isBlank(TextUtil.trim(getTextValue()))) {
			builder.append(" ").append("/>");
		} else {
			builder.append(">");
		}

		return builder.toString();
	}

	private String printTagBody() {

		StringBuilder builder = new StringBuilder();

		builder.append(TextUtil.repaceNull(TextUtil.trim(getTextValue())));
		if (!childrens.isEmpty()) {
			builder.append(Characters.LINE_SEPARATOR_SYSTEM);
			for (ElementBean elementBean : childrens) {
				builder.append(elementBean.toXMLString());
			}
		}

		return builder.toString();
	}

	private String printEndTag() {

		StringBuilder builder = new StringBuilder();

		if (!(childrens.isEmpty() && TextUtil.isBlank(TextUtil.trim(getTextValue())))) {
			builder.append("</").append(getTagName()).append(">");
		}
		builder.append(Characters.LINE_SEPARATOR_SYSTEM);
		return builder.toString();
	}
}
