package com.rms.common.xml.impl;

import java.io.CharArrayReader;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentType;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.rms.common.io.file.FileHelper;
import com.rms.common.xml.bean.backup.DocumentInfo;
import com.rms.common.xml.bean.backup.ElementInfo;

/**
 * dom4jライブラリを使い、xmlファイルを解析する共通クラス
 * 
 * @author ri.meisei
 */
@Deprecated
public class Dom4jXMLParser2 {

	/**
	 * デフォルトコンストラクター
	 */
	public Dom4jXMLParser2() {

	}

	/**
	 * * xmlファイルを解析するメソッド
	 * 
	 * @param targetFileName
	 * @param encoding
	 * @return
	 * @throws DocumentException
	 * @throws IOException
	 */
	public DocumentInfo parse(String targetFileName, String encoding) throws DocumentException, IOException {

		DocumentInfo documentInfo = parse(new File(targetFileName), encoding);
		return documentInfo;
	}

	/**
	 * xmlファイルを解析するメソッド
	 * 
	 * @param targetFile
	 * @param encoding
	 * @return
	 * @throws DocumentException
	 * @throws IOException
	 */
	public DocumentInfo parse(File targetFile, String encoding) throws DocumentException, IOException {

		char[] charArray = FileHelper.readAllChars(targetFile, encoding);
		Reader reader = new CharArrayReader(charArray);

		SAXReader saxReader = new SAXReader();
		saxReader.setValidation(false);
		saxReader.setEncoding(encoding);
		Document document = saxReader.read(targetFile);
		Element rootElement = document.getRootElement();
		DocumentInfo documentInfo = new DocumentInfo();
		documentInfo.setDocmentFilePath(targetFile.getAbsolutePath());
		documentInfo.setDocmentFileEncoding(document.getXMLEncoding());
		DocumentType docType = document.getDocType();
		if (docType != null) {
			documentInfo.setSystemID(docType.getSystemID());
			documentInfo.setPublicID(docType.getPublicID());
		} else {
			String schemaLocation = rootElement.attributeValue("noNamespaceSchemaLocation");
			documentInfo.setSchemaLocation(schemaLocation);
		}

		documentInfo.setElementInfoList(getElementInfoList(rootElement, 1));
		Map<String, Integer> elementsTimes = new TreeMap<String, Integer>();

		for (ElementInfo elementInfo : documentInfo.getElementInfoList()) {

			if (elementsTimes.containsKey(elementInfo.getName())) {
				elementsTimes.put(elementInfo.getName(), elementsTimes.get(elementInfo.getName()) + 1);
			} else {
				elementsTimes.put(elementInfo.getName(), 1);
			}

		}

		for (ElementInfo elementInfo : documentInfo.getElementInfoList()) {
			elementInfo.setTimes(elementsTimes.get(elementInfo.getName()));
		}
		return documentInfo;
	}

	/**
	 * elementの情報を取得する
	 * 
	 * @param element
	 *            nodeオブジェクト
	 * @return
	 */
	private List<ElementInfo> getElementInfoList(Element element, int level) {

		List<ElementInfo> elementList = new ArrayList<ElementInfo>();
		ElementInfo elementInfo = new ElementInfo();
		elementInfo.setName(element.getName());
		elementInfo.setLevel(level);
		elementInfo.setPath(element.getPath());

		elementInfo.setTextValue(element.getText());

		List<Attribute> attributes = element.attributes();
		Map<String, String> attribueMap = new HashMap<String, String>();
		for (Attribute attribute : attributes) {
			String name = attribute.getName();
			String value = attribute.getValue();
			attribueMap.put(name, value);
		}
		elementInfo.setAttribueMap(attribueMap);

		elementList.add(elementInfo);

		List<Element> elements = element.elements();
		for (Element nextElement : elements) {
			elementList.addAll(getElementInfoList(nextElement, level + 1));
		}

		return elementList;
	}
}
