package com.rms.common.xml.node;

import java.util.List;

/**
 * XMLの元素情報を保持するすインタフェース
 * 
 * @author ri.meisei
 * @since 2014/01/16
 */
public interface ElementNode extends Node {

	public String getNamespaceURI();

	public String getPrefix();

	public String getLocalPart();

	public String getQualifiedName();

	public String getTagName();

	public String getTextContent();

	public List<AttributeNode> getAttributeNodes();

	public List<Node> getChildNodes();

	public List<TextNode> getChildTextNodes();

	public List<ElementNode> getChildElementNodes();

}
