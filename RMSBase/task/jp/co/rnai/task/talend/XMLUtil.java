package jp.co.rnai.task.talend;

import java.io.ByteArrayInputStream;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class XMLUtil {

	private final static Charset CHARSET_UTF8 = Charset.forName("UTF-8");

	private final static String USER_DIR_KEY = "user.dir";

	public final static int TYPE_COMMON = 0;

	public final static int TYPE_CAPCAS = 3;

	public final static int TYPE_LCAS = 7;

	public final static int TYPE_WILEY = 11;

	public final static int TYPE_TAYLORFRANCIS = 13;

	public static void validateDTD(String xmlFile) throws ParserConfigurationException, SAXException, IOException {

		validateDTD0(xmlFile, null);
	}

	public static void validateDTD(String xmlFile, String dtdPath) throws ParserConfigurationException, SAXException,
			IOException {

		String userDir = System.getProperty(USER_DIR_KEY);

		try {
			File dtdPathFile = new File(dtdPath);
			if (dtdPathFile.isDirectory()) {
				System.setProperty(USER_DIR_KEY, dtdPath);
				validateDTD0(xmlFile, null);
			} else {
				validateDTD0(xmlFile, dtdPath);
			}
		} finally {
			System.setProperty(USER_DIR_KEY, userDir);
		}
	}

	private static void validateDTD0(String xmlFile, final String dtdPath) throws ParserConfigurationException,
			SAXException, IOException {

		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		documentBuilderFactory.setValidating(true);
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		if (dtdPath != null && !dtdPath.isEmpty()) {
			documentBuilder.setEntityResolver(new EntityResolver() {

				public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {

					return new InputSource(new FileInputStream(dtdPath));
				}
			});
		}
		documentBuilder.setErrorHandler(new ErrorHandler() {

			public void warning(SAXParseException exception) throws SAXException {

			}

			public void fatalError(SAXParseException exception) throws SAXException {

				throw exception;
			}

			public void error(SAXParseException exception) throws SAXException {

				throw exception;
			}
		});

		documentBuilder.parse(new FileInputStream(xmlFile));

	}

	public static void validateSChema(String xmlFile, String... xsdFiles) throws SAXException, IOException {

		Source[] schemas = new Source[xsdFiles.length];
		for (int i = 0; i < schemas.length; i++) {
			schemas[i] = new StreamSource(xsdFiles[i]);
		}

		SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
		Schema schema = schemaFactory.newSchema(schemas);
		Validator validator = schema.newValidator();
		Source source = new StreamSource(xmlFile);
		validator.validate(source);
	}

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

	public static String trimTag(String xmlContent) throws XPathExpressionException, TransformerException,
			SAXException, IOException, ParserConfigurationException {

		return trimTag(xmlContent, TYPE_COMMON);
	}

	public static String trimTag(String xmlContent, int metaType) throws XPathExpressionException,
			TransformerException, SAXException, IOException, ParserConfigurationException {

		String xmlText = xmlContent;
		if (xmlContent == null || xmlContent.isEmpty()) {
			return xmlText;
		}

		String[] exceptNamespace = new String[] {};
		String[] repaceTags = new String[] { "inf", "sub" };
		String[] valueTags = new String[] { "sub", "sup" };

		switch (metaType) {
		case TYPE_CAPCAS:
			exceptNamespace = new String[] { "mml" };
			repaceTags = new String[] { "ce:sup", "sup", "ce:sub", "sub", "ce:inf", "sub" };
			xmlText = trimTagCommon(xmlContent, exceptNamespace, repaceTags, valueTags);
			break;
		case TYPE_COMMON:
		case TYPE_WILEY:
		case TYPE_TAYLORFRANCIS:
			xmlText = trimTagCommon(xmlContent, exceptNamespace, repaceTags, valueTags);
			break;
		default:
			break;
		}

		return xmlText;
	}

	private static String trimTagCommon(String xmlContent, String[] exceptNamespace, String[] repaceTags,
			String[] valueTags) throws ParserConfigurationException, TransformerException, SAXException, IOException,
			XPathExpressionException {

		String xmlText = xmlContent;

		xmlText = trimNamespace(xmlText, exceptNamespace);

		xmlText = replaceTags(xmlText, repaceTags);

		xmlText = getValueExceptTags(xmlText, valueTags);

		return xmlText;
	}

	private static String replaceTags(String xmlContent, String... tags) throws TransformerException, SAXException,
			IOException, ParserConfigurationException, XPathExpressionException {

		Document document = parse(xmlContent, CHARSET_UTF8);
		NodeList nodeList = getNodeList(document, ".");
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			node = replaceTagName(document, node, tags);
		}

		return write(document);
	}

	private static Node replaceTagName(Document document, Node node, String... tags) {

		NodeList nodeList = node.getChildNodes();

		for (int i = 0; i < nodeList.getLength(); i++) {
			Node subNode = nodeList.item(i);
			if (subNode.getNodeType() != Node.ELEMENT_NODE) {
				continue;
			}

			String nodeName = subNode.getNodeName();
			for (int j = 0; j < tags.length / 2; j++) {
				if (nodeName.equals(tags[j])) {
					Element element = document.createElement(tags[j + 1]);
					for (int k = 0; k < subNode.getChildNodes().getLength(); k++) {
						element.appendChild(subNode.getChildNodes().item(k));
					}

					node.replaceChild(element, subNode);
					subNode = element;
				}
			}

			replaceTagName(document, subNode, tags);
		}

		return node;
	}

	private static String getValueExceptTags(String xmlContent, String... tags) throws TransformerException,
			SAXException, IOException, ParserConfigurationException {

		Document document = parse(xmlContent, CHARSET_UTF8);
		Element rootElement = document.getDocumentElement();
		replace2TextExceptTags(document, rootElement, tags);

		String rootTag = "xml_root_tag";
		Document newDocument = generate();
		Element newRootElement = newDocument.createElement(rootTag);
		newDocument.appendChild(newRootElement);
		copyNode(newDocument, newRootElement, rootElement);

		String text = write(newDocument);
		int startIndex = text.indexOf(">", text.indexOf("<" + rootTag));
		int lastIndex = text.lastIndexOf("</" + rootTag + ">");

		String result = "";
		if (startIndex > 0 && lastIndex > 0) {
			result = text.substring(startIndex + 1, lastIndex);
		}

		return result;
	}

	private static Element copyNode(Document destDocument, Element dest, Element src) {

		NamedNodeMap namedNodeMap = src.getAttributes();
		for (int i = 0; i < namedNodeMap.getLength(); i++) {
			Attr attr = (Attr) namedNodeMap.item(i);
			dest.setAttribute(attr.getName(), attr.getValue());
		}
		NodeList childNodeList = src.getChildNodes();
		for (int i = 0; i < childNodeList.getLength(); i++) {
			Node child = childNodeList.item(i);
			if (child.getNodeType() == Node.TEXT_NODE) {
				Text text = destDocument.createTextNode(child.getTextContent());
				dest.appendChild(text);
			} else if (child.getNodeType() == Node.ELEMENT_NODE) {
				Element element = destDocument.createElement(((Element) child).getTagName());
				element = copyNode(destDocument, element, (Element) child);
				dest.appendChild(element);
			}
		}

		return dest;
	}

	private static String trimNamespace(String xmlContent, String... except) throws ParserConfigurationException,
			TransformerException, SAXException, IOException {

		Document srcDoc = parse(xmlContent, CHARSET_UTF8);
		Document document = generate();

		List<String> exceptNamespaces = new ArrayList<String>();
		if (except != null) {
			exceptNamespaces = Arrays.asList(except);
		}

		Element rootElement = (Element) copyNodeExceptNamespace(document, srcDoc.getDocumentElement(),
				new HashSet<String>(), exceptNamespaces);
		document.appendChild(rootElement);

		return write(document);
	}

	private static Node copyNodeExceptNamespace(Document document, Node srcNode, Set<String> namespace,
			List<String> exceptNamespaces) {

		if (srcNode.getNodeType() == Node.ELEMENT_NODE) {

			String nodeName = srcNode.getNodeName();
			nodeName = nodeName.substring(nodeName.indexOf(":") + 1);
			Element element = document.createElement(nodeName);

			for (int i = 0; i < srcNode.getAttributes().getLength(); i++) {
				Attr attr = (Attr) srcNode.getAttributes().item(i);
				String name = attr.getName();
				if (name.startsWith("xmlns:")) {
					String suffix = name.substring(6);
					if (!exceptNamespaces.contains(suffix)) {
						namespace.add(suffix);
					}
					continue;
				}
			}

			for (int i = 0; i < srcNode.getAttributes().getLength(); i++) {
				Attr attr = (Attr) srcNode.getAttributes().item(i);
				String name = attr.getName();
				if (name.startsWith("xmlns:")) {
					continue;
				}
				int semi = name.indexOf(":");
				if (semi > 0) {
					if (namespace.contains(name.substring(0, semi))) {
						name = name.substring(semi + 1);
					}
				}
				element.setAttribute(name, attr.getValue());
			}

			NodeList nodeList = srcNode.getChildNodes();
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node childNode = nodeList.item(i);
				if (childNode.getNodeType() == Node.TEXT_NODE) {
					element.appendChild(document.createTextNode(childNode.getTextContent()));
				} else if (childNode.getNodeType() == Node.ELEMENT_NODE) {
					Node node = copyNodeExceptNamespace(document, childNode, namespace, exceptNamespaces);
					element.appendChild(node);
				}
			}

			return element;
		}

		if (srcNode.getNodeType() == Node.TEXT_NODE) {
			Text text = document.createTextNode(srcNode.getTextContent());
			return text;
		}

		return null;
	}

	private static Node replace2TextExceptTags(Document document, Node node, String... tags) {

		NodeList nodeList = node.getChildNodes();

		for (int i = 0; i < nodeList.getLength(); i++) {
			Node subNode = nodeList.item(i);
			if (subNode.getNodeType() == Node.TEXT_NODE) {
				String text = ((Text) subNode).getTextContent();
				text = text.replaceAll("[\t\n]+", "").replaceAll(" +", " ");
				subNode.setTextContent(text);
				continue;
			}
			if (subNode.getNodeType() != Node.ELEMENT_NODE) {
				continue;
			}

			String nodeName = subNode.getNodeName();
			boolean excepted = false;
			for (String tagName : tags) {
				if (tagName.equals(nodeName)) {
					excepted = true;
					replace2TextExceptTags(document, subNode, tags);
					break;
				}
			}

			if (excepted) {
				continue;
			}

			subNode = replace2TextExceptTags(document, subNode, tags);
			NodeList childList = subNode.getChildNodes();
			List<Node> tempList = new ArrayList<Node>();
			for (int j = 0; j < childList.getLength(); j++) {
				tempList.add(childList.item(j));
			}
			for (Node child : tempList) {
				node.insertBefore(child, subNode);
			}
			node.removeChild(subNode);
		}

		return node;
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

		NodeList nodeList = getNodeList(xmlContent, expression);
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

		NodeList nodeList = getNodeList(xmlContent, expression);
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
			String seq = "00000000000000000000" + seqNo++;
			seq = seq.substring(seq.length() - 20);
			element.setAttribute(attributeName, attributeName + "_" + seq);
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

	private static Document generate() throws ParserConfigurationException {

		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		documentBuilderFactory.setNamespaceAware(false);
		documentBuilderFactory.setValidating(false);
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document document = documentBuilder.newDocument();

		return document;
	}

	private static NodeList getNodeList(Document document, String expression) throws XPathExpressionException {

		XPath xpath = XPathFactory.newInstance().newXPath();
		NodeList nodeList = (NodeList) xpath
				.evaluate(expression, document.getDocumentElement(), XPathConstants.NODESET);

		return nodeList;
	}

	private static NodeList getNodeList(String xmlContent, String expression) throws XPathExpressionException,
			SAXException, IOException, ParserConfigurationException {

		Document document = parse(xmlContent, CHARSET_UTF8);
		XPath xpath = XPathFactory.newInstance().newXPath();
		NodeList nodeList = (NodeList) xpath
				.evaluate(expression, document.getDocumentElement(), XPathConstants.NODESET);

		return nodeList;
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
