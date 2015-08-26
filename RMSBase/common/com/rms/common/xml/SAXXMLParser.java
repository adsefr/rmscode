package com.rms.common.xml;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;
import org.xml.sax.ext.DefaultHandler2;
import org.xml.sax.helpers.DefaultHandler;

import com.rms.base.constant.Characters;

public class SAXXMLParser {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {

		File file = null;
		file = new File("D:/work/test/web.xml");
		file = new File("D:/work/test/main.xml");
		SAXParserFactory factory = SAXParserFactory.newInstance();
		factory.setNamespaceAware(true);
		factory.setValidating(true);
		factory.setXIncludeAware(true);
		SAXParser saxParser = factory.newSAXParser();
		XMLReader xmlReader = saxParser.getXMLReader();
		DefaultHandler handler = new Handler();
		xmlReader.setProperty("http://xml.org/sax/properties/lexical-handler", handler);
		saxParser.parse(file, handler);
	}
}

class Handler extends DefaultHandler2 {

	private StringBuilder builder = new StringBuilder();

	private void append(String text) {

		builder.append(text).append(Characters.LINE_SEPARATOR_SYSTEM);
	}

	private void printMethod() {

		System.out.println(builder.toString());
		builder.setLength(0);
		System.out.println("\n======================================");

		String method = Thread.currentThread().getStackTrace()[2].getMethodName();
		System.out.println(method);
	}

	@Override
	public void warning(SAXParseException exception) throws SAXException {

		printMethod();
	}

	@Override
	public void error(SAXParseException exception) throws SAXException {

		printMethod();
		exception.printStackTrace();
	}

	@Override
	public void fatalError(SAXParseException exception) throws SAXException {

		printMethod();

	}

	@Override
	public void setDocumentLocator(Locator locator) {

		printMethod();

		append(locator.getClass().getName());

		append("locator.getPublicId():" + locator.getPublicId());
		append("locator.getSystemId():" + locator.getSystemId());
		append("locator.getLineNumber():" + locator.getLineNumber());
		append("locator.getColumnNumber():" + locator.getColumnNumber());
	}

	@Override
	public void startDocument() throws SAXException {

		printMethod();

	}

	@Override
	public void endDocument() throws SAXException {

		printMethod();

	}

	@Override
	public void startPrefixMapping(String prefix, String uri) throws SAXException {

		printMethod();

	}

	@Override
	public void endPrefixMapping(String prefix) throws SAXException {

		printMethod();
		append("prefix:" + prefix);
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {

		printMethod();
		append("-------------------------");
		append("uri:" + uri.toString());
		append("localName:" + localName);
		append("qName:" + qName);

		for (int i = 0; i < atts.getLength(); i++) {
			append("getLocalName:" + atts.getLocalName(i));
			append("getValue:" + atts.getValue(i));
			append("getQName:" + atts.getQName(i));
			append("getType:" + atts.getType(i));
			append("getURI:" + atts.getURI(i));
		}
		append("-------------------------");
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {

		printMethod();

	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {

		printMethod();

		// append("ch:"+new String(ch));
		append("start:" + start + " length:" + length + " content:"
				+ new String(Arrays.copyOfRange(ch, start, start + length)));
	}

	@Override
	public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {

		printMethod();

	}

	@Override
	public void processingInstruction(String target, String data) throws SAXException {

		printMethod();

	}

	@Override
	public void skippedEntity(String name) throws SAXException {

		printMethod();

	}

	@Override
	public void notationDecl(String name, String publicId, String systemId) throws SAXException {

		printMethod();
		append(name + " " + publicId + " " + systemId);

	}

	@Override
	public void unparsedEntityDecl(String name, String publicId, String systemId, String notationName)
			throws SAXException {

		printMethod();
		append(name + " " + publicId + " " + systemId + notationName);

	}

	@Override
	public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {

		printMethod();

		append("publicId:" + publicId);
		append("systemId:" + systemId);

		return null;
	}

	@Override
	public InputSource getExternalSubset(String name, String baseURI) throws SAXException, IOException {

		printMethod();
		return null;
	}

	@Override
	public InputSource resolveEntity(String name, String publicId, String baseURI, String systemId)
			throws SAXException, IOException {

		printMethod();
		return null;
	}

	@Override
	public void elementDecl(String name, String model) throws SAXException {

		printMethod();
		append(name + " " + model);

	}

	@Override
	public void attributeDecl(String eName, String aName, String type, String mode, String value) throws SAXException {

		printMethod();
	}

	@Override
	public void internalEntityDecl(String name, String value) throws SAXException {

		printMethod();

	}

	@Override
	public void externalEntityDecl(String name, String publicId, String systemId) throws SAXException {

		printMethod();

	}

	@Override
	public void startDTD(String name, String publicId, String systemId) throws SAXException {

		printMethod();
		append("name:" + name);
	}

	@Override
	public void endDTD() throws SAXException {

		printMethod();
	}

	@Override
	public void startEntity(String name) throws SAXException {

		printMethod();

		append("name:" + name);
	}

	@Override
	public void endEntity(String name) throws SAXException {

		printMethod();
		append("name:" + name);
	}

	@Override
	public void startCDATA() throws SAXException {

		printMethod();

	}

	@Override
	public void endCDATA() throws SAXException {

		printMethod();

	}

	@Override
	public void comment(char[] ch, int start, int length) throws SAXException {

		printMethod();

	}
}
