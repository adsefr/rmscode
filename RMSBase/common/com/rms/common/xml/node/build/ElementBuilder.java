package com.rms.common.xml.node.build;

import java.util.List;

import com.rms.common.xml.node.AttributeNode;
import com.rms.common.xml.node.ElementNode;
import com.rms.common.xml.node.TextNode;

/**
 * 
 * 
 * @author ri.meisei
 * @since 2014/01/23
 */
public interface ElementBuilder extends NodeBuilder<ElementBuilder, ElementNode> {

	public ElementBuilder namespaceURI(String namespaceURI);

	public ElementBuilder prefix(String prefix);

	public ElementBuilder localPart(String localPart);

	public ElementBuilder qualifiedName(String qualifiedName);

	public ElementBuilder tagName(String tagName);

	public ElementBuilder textContent(String textContent);

	public ElementBuilder attributeNodes(List<AttributeNode> attributeNodes);

	public ElementBuilder attributeNodes(AttributeNode... attributeNodes);

	public ElementBuilder textNodes(List<TextNode> textNodes);

	public ElementBuilder textNodes(TextNode... textNode);

	public ElementBuilder elementNodes(List<ElementNode> elementNodes);

	public ElementBuilder elementNodes(ElementNode... elementNodes);
}
