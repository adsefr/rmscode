package com.rms.common.xml;

import java.io.File;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentType;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.rms.base.util.TextUtil;
import com.rms.common.xml.bean.backup.AttributeBean;
import com.rms.common.xml.bean.backup.DocumentBean;
import com.rms.common.xml.bean.backup.ElementBean;

/**
 * dom4jライブラリを使い、xmlファイルを解析する共通クラス
 * 
 * @author ri.meisei
 */
public class DOM4JXMLParser {

	/**
	 * デフォルトコンストラクター
	 */
	public DOM4JXMLParser() {

	}

	/**
	 * xmlファイルを解析するメソッド
	 * 
	 * @param targetFileName
	 * @return
	 * @throws DocumentException
	 */
	public DocumentBean parse(String targetFileName) throws DocumentException {

		return parse(new File(targetFileName), "UTF-8");
	}

	/**
	 * xmlファイルを解析するメソッド
	 * 
	 * @param targetFile
	 * @param encoding
	 * @return
	 * @throws DocumentException
	 */
	public DocumentBean parse(File targetFile, String encoding) throws DocumentException {

		SAXReader saxReader = new SAXReader();
		saxReader.setValidation(false);
		saxReader.setEncoding(encoding);

		Document document = saxReader.read(targetFile);
		Element rootElement = document.getRootElement();
		DocumentType documentType = document.getDocType();

		DocumentBean documentBean = new DocumentBean();
		if (documentType != null) {
			documentBean.setPublicID(documentType.getPublicID());
			documentBean.setSystemID(documentType.getSystemID());
		}

		documentBean.setDocumentFilePath(targetFile.getAbsolutePath());
		documentBean.setXMLEncoding(document.getXMLEncoding());
		ElementBean rootElementBean = convertElement(rootElement, 1);
		documentBean.setRootElementBean(rootElementBean);

		return documentBean;
	}

	/**
	 * 
	 * @param element
	 * @param level
	 * @return
	 */
	private ElementBean convertElement(Element element, int level) {

		ElementBean currElementBean = new ElementBean();
		String namespacePrefix = element.getNamespacePrefix();
		String elementName = element.getName();
		currElementBean.setNamespacePrefix(namespacePrefix);
		currElementBean.setNamespaceURI(element.getNamespaceURI());
		currElementBean.setXpath(element.getPath());
		currElementBean.setName(elementName);
		if (TextUtil.isBlank(namespacePrefix)) {
			currElementBean.setTagName(elementName);
		} else {
			currElementBean.setTagName(namespacePrefix + ":" + elementName);
		}
		currElementBean.setTextValue(element.getText());
		currElementBean.setXmlValue(element.asXML());
		currElementBean.setLevel(level);

		for (Attribute attribute : element.attributes()) {
			String prefix = attribute.getNamespacePrefix();
			String attrName = attribute.getName();

			AttributeBean attributeBean = new AttributeBean();
			attributeBean.setNamespacePrefix(prefix);
			attributeBean.setNamespaceURI(attribute.getNamespaceURI());
			attributeBean.setXpath(attribute.getPath());
			attributeBean.setName(attrName);
			if (TextUtil.isBlank(prefix)) {
				attributeBean.setTagName(attrName);
			} else {
				attributeBean.setTagName(prefix + ":" + attrName);
			}
			attributeBean.setTextValue(attribute.getValue());
			attributeBean.setXmlValue(attribute.asXML());
			attributeBean.setLevel(level);
			currElementBean.getAttributes().add(attributeBean);
		}

		List<Element> elements = element.elements();
		for (Element nextElement : elements) {
			ElementBean child = convertElement(nextElement, level + 1);
			child.setParent(currElementBean);
			currElementBean.getChildrens().add(child);
		}

		return currElementBean;
	}

}
