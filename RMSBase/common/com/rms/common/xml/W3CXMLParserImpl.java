package com.rms.common.xml;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.rms.common.xml.eception.XMLParseException;
import com.rms.common.xml.node.DocumentNode;
import com.rms.common.xml.node.convert.NodeConvertor;

/**
 * 
 * 
 * @author ri.meisei
 * @since 2014/01/21
 */
class W3CXMLParserImpl extends AbstractXMLParser implements W3CXMLParser {

	W3CXMLParserImpl() {

	}

	@Override
	public DocumentNode parse(String uri) throws XMLParseException {

		InputSource inputSource = new InputSource(uri);

		return parse(inputSource);
	}

	@Override
	public DocumentNode parse(InputStream is) throws XMLParseException {

		InputSource inputSource = new InputSource(is);

		return parse(inputSource);
	}

	public DocumentNode parse(InputSource inputSource) throws XMLParseException {

		Document document = null;

		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			documentBuilderFactory.setNamespaceAware(isNamespaceAware());
			documentBuilderFactory.setValidating(isValidating());

			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

			document = documentBuilder.parse(inputSource);
		} catch (ParserConfigurationException e) {
			throw new XMLParseException(e);
		} catch (SAXException e) {
			throw new XMLParseException(e);
		} catch (IOException e) {
			throw new XMLParseException(e);
		}

		DocumentNode documentNode = NodeConvertor.getInstance().convert(document);

		return documentNode;
	}
}
