package com.rms.common.xml.node.convert;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import com.rms.common.xml.node.AttributeNode;
import com.rms.common.xml.node.DocumentNode;
import com.rms.common.xml.node.DocumentTypeNode;
import com.rms.common.xml.node.ElementNode;
import com.rms.common.xml.node.TextNode;
import com.rms.common.xml.node.build.AttributeBuilder;
import com.rms.common.xml.node.build.DocumentBuilder;
import com.rms.common.xml.node.build.DocumentTypeBuilder;
import com.rms.common.xml.node.build.ElementBuilder;
import com.rms.common.xml.node.build.NodeBuilderFactory;
import com.rms.common.xml.node.build.TextBuilder;

/**
 * 
 * 
 * @author ri.meisei
 * @since 2014/01/21
 */
class NodeConvertorImpl extends NodeConvertor {

	NodeConvertorImpl() {

	}

	@Override
	public DocumentNode convert(Document document) {

		DocumentTypeNode documentTypeNode = convert(document.getDoctype());

		ElementNode rootElementNode = convert(document.getDocumentElement());

		DocumentBuilder documentBuilder = NodeBuilderFactory.newDocumentBuilder();
		DocumentNode documentNode = null;

		documentNode = documentBuilder// documentNode define

				.nodeName(document.getNodeName())// node name

				.version(document.getXmlEncoding())// Version

				.encoding(document.getXmlEncoding())// encoding

				.standalone(document.getXmlStandalone())// standalone

				.documentTypeNode(documentTypeNode)// DOCTYPE

				.rootElementNode(rootElementNode)// rootElementNode

				.build();// return result

		return documentNode;
	}

	@Override
	public DocumentTypeNode convert(DocumentType documentType) {

		DocumentTypeBuilder documentTypeBuilder = NodeBuilderFactory.newdoDocumentTypeBuilder();
		DocumentTypeNode documentTypeNode = null;

		documentTypeNode = documentTypeBuilder//

				.nodeName(documentType.getNodeName())//

				.name(documentType.getName())//

				.publicId(documentType.getPublicId())//

				.systemId(documentType.getSystemId())//

				.internalSubSet(documentType.getInternalSubset())//

				.build();

		return documentTypeNode;
	}

	@Override
	public ElementNode convert(Element element) {

		ElementBuilder elementBuilder = NodeBuilderFactory.newElementBuilder();

		elementBuilder//
				.nodeName(element.getNodeName())//
				.namespaceURI(element.getNamespaceURI())//
				.localPart(element.getLocalName())//
				.prefix(element.getPrefix())//
				.qualifiedName(null)// TODO
				.tagName(element.getTagName())//
				.textContent(element.getTextContent());//

		NamedNodeMap namedNodeMap = element.getAttributes();
		for (int index = 0; index < namedNodeMap.getLength(); index++) {
			Attr attr = (Attr) namedNodeMap.item(index);
			AttributeNode attributeNode = convert(attr);
			elementBuilder.attributeNodes(attributeNode);
		}

		NodeList nodeList = element.getChildNodes();
		for (int index = 0; index < nodeList.getLength(); index++) {
			org.w3c.dom.Node node = nodeList.item(index);

			if (org.w3c.dom.Node.TEXT_NODE == node.getNodeType()) {
				Text textNode = (Text) node;
				TextNode childText = convert(textNode);
				elementBuilder.textNodes(childText);

			} else if (org.w3c.dom.Node.ELEMENT_NODE == node.getNodeType()) {
				Element elementNode = (Element) node;
				ElementNode childElement = convert(elementNode);
				elementBuilder.elementNodes(childElement);
			} else {
				// TODO 未完成
			}
		}
		ElementNode elementNode = elementBuilder.build();

		return elementNode;
	}

	@Override
	public AttributeNode convert(Attr attr) {

		AttributeBuilder attributeBuilder = NodeBuilderFactory.newAttributeBuilder();
		AttributeNode attributeNode = null;

		attributeNode = attributeBuilder//

				.nodeName(attr.getNodeName())//

				.namespaceURI(attr.getNamespaceURI())//

				.prefix(attr.getPrefix())//

				.localPart(attr.getLocalName())//

				.qualifiedName(null)// TODO

				.name(attr.getName())//

				.value(attr.getValue())//

				.build();

		return attributeNode;
	}

	@Override
	public TextNode convert(Text text) {

		TextBuilder textBuilder = NodeBuilderFactory.newTextBuilder();
		TextNode textNode = null;

		textNode = textBuilder//
				.nodeName(text.getNodeName())//
				.content(text.getData())//
				.build();

		return textNode;
	}

}
