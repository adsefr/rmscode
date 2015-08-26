package jp.co.rnai.task.xml;

import java.io.ByteArrayInputStream;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.DatatypeConverter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

public class XMLOperator {

	private static final Charset CHARSET_UTF8 = Charset.forName("UTF-8");

	/**
	 *
	 * @param content
	 * @return
	 */
	public static String printHexBinary(String content) {

		return printHexBinary(content, CHARSET_UTF8);
	}

	/**
	 *
	 * @param content
	 * @param charset
	 * @return
	 */
	public static String printHexBinary(String content, Charset charset) {

		String converted = content;

		if (content != null && !content.isEmpty()) {
			converted = DatatypeConverter.printHexBinary(content.getBytes(charset));
		}

		return converted;
	}

	/**
	 *
	 * @param array
	 * @return
	 */
	public static String[] printHexBinary(String[] array) {

		return printHexBinary(array, CHARSET_UTF8);
	}

	/**
	 *
	 * @param array
	 * @param charset
	 * @return
	 */
	public static String[] printHexBinary(String[] array, Charset charset) {

		if (array == null || array.length == 0) {
			return array;
		}

		String[] convertedArray = new String[array.length];
		for (int i = 0; i < array.length; i++) {
			convertedArray[i] = printHexBinary(array[i], charset);
		}

		return convertedArray;
	}

	/**
	 *
	 * @param content
	 * @return
	 */
	public static String parseHexBinary(String content) {

		return parseHexBinary(content, CHARSET_UTF8);
	}

	/**
	 *
	 * @param content
	 * @param charset
	 * @return
	 */
	public static String parseHexBinary(String content, Charset charset) {

		String converted = content;

		if (content != null && !content.isEmpty()) {
			converted = new String(DatatypeConverter.parseHexBinary(content), charset);
		}

		return converted;
	}

	/**
	 *
	 * @param array
	 * @return
	 */
	public static String[] parseHexBinary(String[] array) {

		return parseHexBinary(array, CHARSET_UTF8);
	}

	/**
	 *
	 * @param array
	 * @param charset
	 * @return
	 */
	public static String[] parseHexBinary(String[] array, Charset charset) {

		if (array == null || array.length == 0) {
			return array;
		}

		String[] convertedArray = new String[array.length];
		for (int i = 0; i < array.length; i++) {
			convertedArray[i] = parseHexBinary(array[i], charset);
		}

		return convertedArray;
	}

	/**
	 *
	 * @param xmlContent
	 * @param expression
	 * @return
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws XPathExpressionException
	 */
	public static List<String> getValues(String xmlContent, String expression) throws SAXException, IOException,
			ParserConfigurationException, XPathExpressionException {

		return getValues(xmlContent, CHARSET_UTF8, expression);
	}

	/**
	 *
	 * @param xmlContent
	 * @param charset
	 * @param expression
	 * @return
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws XPathExpressionException
	 */
	public static List<String> getValues(String xmlContent, Charset charset, String expression) throws SAXException,
			IOException, ParserConfigurationException, XPathExpressionException {

		List<String> valueList = new ArrayList<String>();

		Document document = parse(xmlContent, charset);
		XPath xpath = XPathFactory.newInstance().newXPath();
		NodeList nodeList = (NodeList) xpath
				.evaluate(expression, document.getDocumentElement(), XPathConstants.NODESET);
		int length = nodeList.getLength();
		for (int seq = 0; seq < length; seq++) {
			Node node = nodeList.item(seq);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				NodeList childNodeList = node.getChildNodes();
				StringBuilder sBuilder = new StringBuilder();
				for (int i = 0; i < childNodeList.getLength(); i++) {
					Node childNode = childNodeList.item(i);
					if (childNode.getNodeType() == Node.TEXT_NODE) {
						sBuilder.append(((Text) childNode).getNodeValue());
					}
				}
				valueList.add(sBuilder.toString());

			} else if (node.getNodeType() == Node.ATTRIBUTE_NODE) {
				valueList.add(node.getNodeValue());
			}
		}

		return valueList;
	}

	/**
	 *
	 * @param xmlContent
	 * @param expression
	 * @return
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws XPathExpressionException
	 */
	public static List<String> getHexBinaryValues(String xmlContent, String expression) throws SAXException,
			IOException, ParserConfigurationException, XPathExpressionException {

		return getHexBinaryValues(xmlContent, CHARSET_UTF8, expression);
	}

	/**
	 *
	 * @param xmlContent
	 * @param charset
	 * @param expression
	 * @return
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws XPathExpressionException
	 */
	public static List<String> getHexBinaryValues(String xmlContent, Charset charset, String expression)
			throws SAXException, IOException, ParserConfigurationException, XPathExpressionException {

		List<String> valueList = new ArrayList<String>();

		Document document = parse(xmlContent, charset);
		XPath xpath = XPathFactory.newInstance().newXPath();
		NodeList nodeList = (NodeList) xpath
				.evaluate(expression, document.getDocumentElement(), XPathConstants.NODESET);
		int length = nodeList.getLength();
		for (int seq = 0; seq < length; seq++) {
			Node node = nodeList.item(seq);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				NodeList childNodeList = node.getChildNodes();
				StringBuilder sBuilder = new StringBuilder();
				for (int i = 0; i < childNodeList.getLength(); i++) {
					Node childNode = childNodeList.item(i);
					if (childNode.getNodeType() == Node.TEXT_NODE) {
						sBuilder.append(((Text) childNode).getNodeValue());
					}
				}
				valueList.add(printHexBinary(sBuilder.toString(), charset));

			} else if (node.getNodeType() == Node.ATTRIBUTE_NODE) {
				valueList.add(node.getNodeValue());
			}
		}

		return valueList;
	}

	/**
	 *
	 * @param xmlContent
	 * @param attributeName
	 * @param matches
	 * @return
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws TransformerException
	 */
	public static String addAttribute(String xmlContent, String attributeName, String... matches) throws SAXException,
			IOException, ParserConfigurationException, TransformerException {

		return addAttribute(xmlContent, CHARSET_UTF8, attributeName, matches);
	}

	/**
	 *
	 * @param xmlContent
	 * @param charset
	 * @param attributeName
	 * @param matches
	 * @return
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws TransformerException
	 */
	public static String addAttribute(String xmlContent, Charset charset, String attributeName, String... matches)
			throws SAXException, IOException, ParserConfigurationException, TransformerException {

		Document document = parse(xmlContent, charset);
		Element element = document.getDocumentElement();
		addAttributeLoopElement(element, attributeName, 1, matches);

		return write(document);
	}

	/**
	 *
	 * @param xmlContent
	 * @param attributeName
	 * @param matches
	 * @return
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws TransformerException
	 */
	public static String removeAttribute(String xmlContent, String attributeName, String... matches)
			throws SAXException, IOException, ParserConfigurationException, TransformerException {

		return removeAttribute(xmlContent, CHARSET_UTF8, attributeName, matches);
	}

	/**
	 *
	 * @param xmlContent
	 * @param charset
	 * @param attributeName
	 * @param matches
	 * @return
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws TransformerException
	 */
	public static String removeAttribute(String xmlContent, Charset charset, String attributeName, String... matches)
			throws SAXException, IOException, ParserConfigurationException, TransformerException {

		Document document = parse(xmlContent, charset);
		Element element = document.getDocumentElement();
		removeAttributeLoopElement(element, attributeName, matches);

		return write(document);
	}

	/**
	 *
	 * @param element
	 * @param attributeName
	 * @param sequence
	 * @param matches
	 * @return
	 */
	private static int addAttributeLoopElement(Element element, String attributeName, int sequence, String... matches) {

		int seqNo = sequence;
		if (matches(element.getTagName(), matches)) {
			element.setAttribute(attributeName, attributeName + "_" + seqNo++);
		}
		NodeList nodeList = element.getChildNodes();
		int length = nodeList.getLength();
		for (int seq = 0; seq < length; seq++) {
			Node node = nodeList.item(seq);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				seqNo = addAttributeLoopElement((Element) node, attributeName, seqNo, matches);
			}
		}

		return seqNo;
	}

	/**
	 *
	 * @param element
	 * @param attributeName
	 * @param matches
	 */
	private static void removeAttributeLoopElement(Element element, String attributeName, String... matches) {

		if (element.hasAttribute(attributeName)) {
			if (matches(element.getTagName(), matches)) {
				element.removeAttribute(attributeName);
			}
		}

		NodeList nodeList = element.getChildNodes();
		int length = nodeList.getLength();
		for (int seq = 0; seq < length; seq++) {
			Node node = nodeList.item(seq);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				if (element.hasAttribute(attributeName)) {
					if (matches(element.getTagName(), matches)) {
						removeAttributeLoopElement((Element) node, attributeName, matches);
					}
				}
			}
		}
	}

	/**
	 *
	 * @param xmlContent
	 * @param charset
	 * @return
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
	private static Document parse(String xmlContent, Charset charset) throws SAXException, IOException,
			ParserConfigurationException {

		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		documentBuilderFactory.setNamespaceAware(false);
		documentBuilderFactory.setValidating(false);
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document document = documentBuilder.parse(new ByteArrayInputStream(xmlContent.getBytes(charset)));

		return document;
	}

	/**
	 *
	 * @param document
	 * @return
	 * @throws TransformerException
	 * @throws UnsupportedEncodingException
	 */
	private static String write(Document document) throws TransformerException, UnsupportedEncodingException {

		TransformerFactory factory = TransformerFactory.newInstance();
		Transformer transformer = factory.newTransformer();
		Source source = new DOMSource(document);
		CharArrayWriter os = new CharArrayWriter();
		Result result = new StreamResult(os);
		transformer.transform(source, result);

		return os.toString();
	}

	/**
	 *
	 * @param tagName
	 * @param matches
	 * @return
	 */
	private static boolean matches(String tagName, String... matches) {

		if (matches == null || matches.length == 0) {
			return true;
		}

		for (String matche : matches) {
			if (tagName.matches(matche)) {
				return true;
			}
		}

		return false;
	}
}
