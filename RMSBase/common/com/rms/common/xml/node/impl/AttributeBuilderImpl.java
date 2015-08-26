package com.rms.common.xml.node.impl;

import com.rms.common.xml.enums.NodeType;
import com.rms.common.xml.node.AttributeNode;
import com.rms.common.xml.node.ElementNode;

/**
 * 
 * 
 * @author ri.meisei
 * @since 2014/01/23
 */
class AttributeBuilderImpl implements AttributeBuilder {

	private AttributeNodeImpl attributeNodeImpl = null;

	AttributeBuilderImpl() {

		attributeNodeImpl = new AttributeNodeImpl();
	}

	@Override
	public AttributeBuilder nodeName(String nodeName) {

		attributeNodeImpl.setNodeName(nodeName);

		return this;
	}

	@Override
	public AttributeBuilder level(int level) {

		attributeNodeImpl.setLevel(level);

		return this;
	}

	@Override
	public AttributeBuilder rootElementNode(ElementNode elementNode) {

		attributeNodeImpl.setRootElementNode(elementNode);

		return this;
	}

	@Override
	public AttributeBuilder parentElementNode(ElementNode elementNode) {

		attributeNodeImpl.setParentElementNode(elementNode);

		return this;
	}

	@Override
	public AttributeBuilder occurrenceTime(int occurrenceTime) {

		attributeNodeImpl.setOccurrenceTime(occurrenceTime);

		return this;
	}

	@Override
	public AttributeNode build() {

		return attributeNodeImpl;
	}

	@Override
	public AttributeBuilder namespaceURI(String namespaceURI) {

		attributeNodeImpl.setNamespaceURI(namespaceURI);

		return this;
	}

	@Override
	public AttributeBuilder prefix(String prefix) {

		attributeNodeImpl.setPrefix(prefix);
		return this;
	}

	@Override
	public AttributeBuilder localPart(String localPart) {

		attributeNodeImpl.setLocalPart(localPart);
		return this;
	}

	@Override
	public AttributeBuilder qualifiedName(String qualifiedName) {

		attributeNodeImpl.setQualifiedName(qualifiedName);
		return this;
	}

	@Override
	public AttributeBuilder name(String name) {

		attributeNodeImpl.setName(name);
		return this;
	}

	@Override
	public AttributeBuilder value(String value) {

		attributeNodeImpl.setValue(value);
		return this;
	}

	private class AttributeNodeImpl extends NodeImpl implements AttributeNode {

		private String namespaceURI = null;

		private String prefix = null;

		private String localPart = null;

		private String qualifiedName = null;

		private String name = null;

		private String value = null;

		public AttributeNodeImpl() {

			setNodeType(NodeType.ATTRIBUTE);
		}

		/**
		 * @return namespaceURI
		 */
		public String getNamespaceURI() {

			return namespaceURI;
		}

		/**
		 * @return prefix
		 */
		public String getPrefix() {

			return prefix;
		}

		/**
		 * @return localPart
		 */
		public String getLocalPart() {

			return localPart;
		}

		/**
		 * @return qualifiedName
		 */
		public String getQualifiedName() {

			return qualifiedName;
		}

		/**
		 * @return name
		 */
		public String getName() {

			return name;
		}

		/**
		 * @return value
		 */
		public String getValue() {

			return value;
		}

		/**
		 * @param namespaceURI
		 *            セットする namespaceURI
		 */
		void setNamespaceURI(String namespaceURI) {

			this.namespaceURI = namespaceURI;
		}

		/**
		 * @param prefix
		 *            セットする prefix
		 */
		void setPrefix(String prefix) {

			this.prefix = prefix;
		}

		/**
		 * @param localPart
		 *            セットする localPart
		 */
		void setLocalPart(String localPart) {

			this.localPart = localPart;
		}

		/**
		 * @param qualifiedName
		 *            セットする qualifiedName
		 */
		void setQualifiedName(String qualifiedName) {

			this.qualifiedName = qualifiedName;
		}

		/**
		 * @param name
		 *            セットする name
		 */
		void setName(String name) {

			this.name = name;
		}

		/**
		 * @param value
		 *            セットする value
		 */
		void setValue(String value) {

			this.value = value;
		}

	}

}
