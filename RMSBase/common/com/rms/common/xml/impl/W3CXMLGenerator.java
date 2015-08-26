package com.rms.common.xml.impl;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import com.rms.base.exception.InitializationException;
import com.rms.base.exception.UnexpectedDataException;
import com.rms.base.validate.Assertion;
import com.rms.common.xml.XMLGenerator;
import com.rms.common.xml.eception.XMLParseException;
import com.rms.common.xml.enums.NodeType;
import com.rms.common.xml.node.AttributeNode;
import com.rms.common.xml.node.DocumentNode;
import com.rms.common.xml.node.DocumentTypeNode;
import com.rms.common.xml.node.ElementNode;
import com.rms.common.xml.node.Node;
import com.rms.common.xml.node.TextNode;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/01/21
 */
public class W3CXMLGenerator implements XMLGenerator {

	private DocumentBuilderFactory factory = null;

	private DocumentBuilder builder = null;

	private Document document = null;

	private Map<String, String> outputKeys = new HashMap<String, String>();

	private W3CXMLGenerator() {

		try {
			factory = DocumentBuilderFactory.newInstance();
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			throw new InitializationException(e);
		}
	}

	public static W3CXMLGenerator newInstance() {

		return new W3CXMLGenerator();
	}

	@Override
	public void generate(DocumentNode documentNode) throws XMLParseException {

		Assertion.assertNotNull("documentNode", documentNode);

		document = builder.newDocument();

		ElementNode rootElementNode = documentNode.getRootElementNode();
		Element documentElement = generate(rootElementNode);

		document.appendChild(documentElement);

		setOutputKeys(documentNode);
	}

	private void setOutputKeys(DocumentNode documentNode) {

		outputKeys.put(OutputKeys.INDENT, "yes");
		outputKeys.put(OutputKeys.VERSION, documentNode.getVersion());
		outputKeys.put(OutputKeys.ENCODING, documentNode.getEncoding());
		outputKeys.put(OutputKeys.STANDALONE, String.valueOf(documentNode.isStandalone()));

		DocumentTypeNode documentTypeNode = documentNode.getDocumentTypeNode();
		if (documentTypeNode != null) {
			outputKeys.put(OutputKeys.DOCTYPE_PUBLIC, documentTypeNode.getPublicId());
			outputKeys.put(OutputKeys.DOCTYPE_SYSTEM, documentTypeNode.getSystemId());
			outputKeys.put(OutputKeys.METHOD, "xml");
		}
	}

	private Element generate(ElementNode elementNode) throws XMLParseException {

		Element element = document.createElement(elementNode.getTagName());

		for (AttributeNode attributeNode : elementNode.getAttributeNodes()) {
			Attr attribute = generate(attributeNode);
			element.setAttributeNode(attribute);
		}

		List<Node> nodes = elementNode.getChildNodes();
		for (Node node : nodes) {
			if (NodeType.ELEMENT == node.getNodeType()) {
				element.appendChild(generate((ElementNode) node));

			} else if (NodeType.TEXT == node.getNodeType()) {
				element.appendChild(generate((TextNode) node));

			} else {
				throw new UnexpectedDataException("");
			}
		}

		return element;
	}

	private Attr generate(AttributeNode attributeNode) {

		Attr attr = document.createAttribute(attributeNode.getName());
		attr.setValue(attributeNode.getValue());

		return attr;
	}

	private Text generate(TextNode textNode) {

		Text text = document.createTextNode(textNode.getContent());

		return text;
	}

	@Override
	public void write(File outputFile) throws IOException {

		Assertion.assertNotNull("outputFile", outputFile);

		Source source = new DOMSource(document);

		Result result = new StreamResult(outputFile);

		XMLTransformer.transform(source, result, outputKeys);
	}

	@Override
	public void write(OutputStream outputStream) throws IOException {

		Assertion.assertNotNull("outputStream", outputStream);

		Source source = new DOMSource(document);

		Result result = new StreamResult(outputStream);

		XMLTransformer.transform(source, result, outputKeys);

	}

}
