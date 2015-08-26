package com.rms.common.xml.node.build;

import com.rms.common.xml.enums.NodeType;
import com.rms.common.xml.node.DocumentTypeNode;
import com.rms.common.xml.node.ElementNode;

public class DocumentTypeBuilderImpl implements DocumentTypeBuilder {

	private DocumentTypeNodeImpl documentTypeNodeImpl = null;

	DocumentTypeBuilderImpl() {

		documentTypeNodeImpl = new DocumentTypeNodeImpl();
	}

	@Override
	public DocumentTypeBuilder nodeName(String nodeName) {

		documentTypeNodeImpl.setNodeName(nodeName);

		return this;
	}

	@Override
	public DocumentTypeBuilder level(int level) {

		documentTypeNodeImpl.setLevel(level);

		return this;
	}

	@Override
	public DocumentTypeBuilder rootElementNode(ElementNode rootElementNode) {

		documentTypeNodeImpl.setRootElementNode(rootElementNode);

		return this;
	}

	@Override
	public DocumentTypeBuilder parentElementNode(ElementNode parentElementNode) {

		documentTypeNodeImpl.setParentElementNode(parentElementNode);

		return this;
	}

	@Override
	public DocumentTypeBuilder occurrenceTime(int occurrenceTime) {

		documentTypeNodeImpl.setOccurrenceTime(occurrenceTime);

		return this;
	}

	@Override
	public DocumentTypeNode build() {

		return documentTypeNodeImpl;
	}

	@Override
	public DocumentTypeBuilder name(String name) {

		documentTypeNodeImpl.setName(name);

		return this;
	}

	@Override
	public DocumentTypeBuilder systemId(String systemId) {

		documentTypeNodeImpl.setSystemId(systemId);

		return this;
	}

	@Override
	public DocumentTypeBuilder publicId(String publicId) {

		documentTypeNodeImpl.setPublicId(publicId);

		return this;
	}

	@Override
	public DocumentTypeBuilder internalSubSet(String internalSubSet) {

		documentTypeNodeImpl.setInternalSubSet(internalSubSet);

		return this;
	}

	private class DocumentTypeNodeImpl extends NodeImpl implements DocumentTypeNode {

		private String name = null;

		private String systemId = null;

		private String publicId = null;

		private String internalSubSet = null;

		DocumentTypeNodeImpl() {

			setNodeType(NodeType.DOCMENT_TYPE);
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
		 * @return systemId
		 */
		public String getSystemId() {

			return systemId;
		}

		/**
		 * @param systemId
		 *            セットする systemId
		 */
		public void setSystemId(String systemId) {

			this.systemId = systemId;
		}

		/**
		 * @return publicId
		 */
		public String getPublicId() {

			return publicId;
		}

		/**
		 * @param publicId
		 *            セットする publicId
		 */
		public void setPublicId(String publicId) {

			this.publicId = publicId;
		}

		/**
		 * @return internalSubSet
		 */
		public String getInternalSubSet() {

			return internalSubSet;
		}

		/**
		 * @param internalSubSet
		 *            セットする internalSubSet
		 */
		public void setInternalSubSet(String internalSubSet) {

			this.internalSubSet = internalSubSet;
		}

	}

}
