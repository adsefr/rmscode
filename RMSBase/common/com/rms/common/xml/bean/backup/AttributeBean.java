package com.rms.common.xml.bean.backup;

import com.rms.common.xml.enums.NodeType;

/**
 * 
 * 
 * @author ri.meisei
 * @since 2013/12/18
 */
public class AttributeBean extends NodeBean {

	/**
	 * デフォルトコンストラクター
	 */
	public AttributeBean() {

		setNodeType(NodeType.ATTRIBUTE);
	}

	public String toXMLString() {

		StringBuilder builder = new StringBuilder();
		builder.append(getTagName()).append("=\"").append(getTextValue()).append("\"");
		return builder.toString();
	}
}
