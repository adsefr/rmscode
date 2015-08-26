package com.rms.common.xml.node.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.rms.common.xml.enums.NodeType;
import com.rms.common.xml.node.AttributeNode;
import com.rms.common.xml.node.ElementNode;
import com.rms.common.xml.node.Node;
import com.rms.common.xml.node.TextNode;

/**
 * 
 * 
 * @author ri.meisei
 * @since 2014/01/23
 */
class ElementBuilderImpl implements ElementBuilder {

	private ElementNodeImpl elementNodeImpl = null;

	ElementBuilderImpl() {

		elementNodeImpl = new ElementNodeImpl();
	}

	@Override
	public ElementBuilder nodeName(String nodeName) {

		elementNodeImpl.setNodeName(nodeName);

		return this;
	}

	@Override
	public ElementBuilder level(int level) {

		elementNodeImpl.setLevel(level);

		return this;

	}

	@Override
	public ElementBuilder rootElementNode(ElementNode rootElementNode) {

		elementNodeImpl.setRootElementNode(rootElementNode);

		return this;

	}

	@Override
	public ElementBuilder parentElementNode(ElementNode parentElementNode) {

		elementNodeImpl.setParentElementNode(parentElementNode);

		return this;

	}

	@Override
	public ElementBuilder occurrenceTime(int occurrenceTime) {

		elementNodeImpl.setOccurrenceTime(occurrenceTime);

		return this;

	}

	@Override
	public ElementNode build() {

		return elementNodeImpl;

	}

	@Override
	public ElementBuilder namespaceURI(String namespaceURI) {

		return this;

	}

	@Override
	public ElementBuilder prefix(String prefix) {

		return null;
	}

	@Override
	public ElementBuilder localPart(String localPart) {

		elementNodeImpl.setLocalPart(localPart);

		return this;

	}

	@Override
	public ElementBuilder qualifiedName(String qualifiedName) {

		elementNodeImpl.setQualifiedName(qualifiedName);

		return this;

	}

	@Override
	public ElementBuilder tagName(String tagName) {

		elementNodeImpl.setTagName(tagName);

		return this;

	}

	@Override
	public ElementBuilder textContent(String textContent) {

		elementNodeImpl.setTextContent(textContent);

		return this;

	}

	@Override
	public ElementBuilder attributeNodes(List<AttributeNode> attributeNodes) {

		elementNodeImpl.getAttributeNodes().addAll(attributeNodes);

		return this;

	}

	@Override
	public ElementBuilder attributeNodes(AttributeNode... attributeNodes) {

		List<AttributeNode> attributeNodes2 = Arrays.asList(attributeNodes);
		elementNodeImpl.getAttributeNodes().addAll(attributeNodes2);

		return this;
	}

	@Override
	public ElementBuilder textNodes(List<TextNode> textNodes) {

		elementNodeImpl.getChildNodes().addAll(textNodes);
		elementNodeImpl.getChildTextNodes().addAll(textNodes);

		return this;
	}

	@Override
	public ElementBuilder textNodes(TextNode... textNodes) {

		List<TextNode> textNodes2 = Arrays.asList(textNodes);
		elementNodeImpl.getChildNodes().addAll(textNodes2);
		elementNodeImpl.getChildTextNodes().addAll(textNodes2);

		return this;
	}

	@Override
	public ElementBuilder elementNodes(List<ElementNode> elementNodes) {

		elementNodeImpl.getChildNodes().addAll(elementNodes);
		elementNodeImpl.getChildElementNodes().addAll(elementNodes);

		return this;
	}

	@Override
	public ElementBuilder elementNodes(ElementNode... elementNode) {

		List<ElementNode> elementNodes = Arrays.asList(elementNode);
		elementNodeImpl.getChildNodes().addAll(elementNodes);
		elementNodeImpl.getChildElementNodes().addAll(elementNodes);

		return this;
	}

	class ElementNodeImpl extends NodeImpl implements ElementNode {

		private String namespaceURI = null;

		private String localPart = null;

		private String prefix = null;

		private String qualifiedName = null;

		private String tagName = null;

		private String textContent = null;

		private List<AttributeNode> attributeNodes = new ArrayList<AttributeNode>();

		private List<Node> childNodes = new ArrayList<Node>();

		private List<TextNode> childTextNodes = new ArrayList<TextNode>();

		private List<ElementNode> childElementNodes = new ArrayList<ElementNode>();

		public ElementNodeImpl() {

			setNodeType(NodeType.ELEMENT);
		}

		/**
		 * @return namespaceURI
		 */
		public String getNamespaceURI() {

			return namespaceURI;
		}

		/**
		 * @return localPart
		 */
		public String getLocalPart() {

			return localPart;
		}

		/**
		 * @return prefix
		 */
		public String getPrefix() {

			return prefix;
		}

		/**
		 * @return qualifiedName
		 */
		public String getQualifiedName() {

			return qualifiedName;
		}

		/**
		 * @return tagName
		 */
		public String getTagName() {

			return tagName;
		}

		/**
		 * @return textContent
		 */
		public String getTextContent() {

			return textContent;
		}

		/**
		 * @return attributeNodes
		 */
		public List<AttributeNode> getAttributeNodes() {

			return attributeNodes;
		}

		/**
		 * @return childNodes
		 */
		public List<Node> getChildNodes() {

			return childNodes;
		}

		/**
		 * @return childTextNodes
		 */
		public List<TextNode> getChildTextNodes() {

			return childTextNodes;
		}

		/**
		 * @return childElementNodes
		 */
		public List<ElementNode> getChildElementNodes() {

			return childElementNodes;
		}

		/**
		 * @param namespaceURI
		 *            セットする namespaceURI
		 */
		void setNamespaceURI(String namespaceURI) {

			this.namespaceURI = namespaceURI;
		}

		/**
		 * @param localPart
		 *            セットする localPart
		 */
		void setLocalPart(String localPart) {

			this.localPart = localPart;
		}

		/**
		 * @param prefix
		 *            セットする prefix
		 */
		void setPrefix(String prefix) {

			this.prefix = prefix;
		}

		/**
		 * @param qualifiedName
		 *            セットする qualifiedName
		 */
		void setQualifiedName(String qualifiedName) {

			this.qualifiedName = qualifiedName;
		}

		/**
		 * @param tagName
		 *            セットする tagName
		 */
		void setTagName(String tagName) {

			this.tagName = tagName;
		}

		/**
		 * @param textContent
		 *            セットする textContent
		 */
		void setTextContent(String textContent) {

			this.textContent = textContent;
		}

		/**
		 * @param attributeNodes
		 *            セットする attributeNodes
		 */
		void setAttributeNodes(List<AttributeNode> attributeNodes) {

			this.attributeNodes = attributeNodes;
		}

		/**
		 * @param childNodes
		 *            セットする childNodes
		 */
		void setChildNodes(List<Node> childNodes) {

			this.childNodes = childNodes;
		}

		/**
		 * @param childTextNodes
		 *            セットする childTextNodes
		 */
		void setChildTextNodes(List<TextNode> childTextNodes) {

			this.childTextNodes = childTextNodes;
		}

		/**
		 * @param childElementNodes
		 *            セットする childElementNodes
		 */
		void setChildElementNodes(List<ElementNode> childElementNodes) {

			this.childElementNodes = childElementNodes;
		}

	}
}
