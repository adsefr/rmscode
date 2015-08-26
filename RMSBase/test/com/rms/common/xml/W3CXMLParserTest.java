package com.rms.common.xml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.rms.common.io.IOFactory;
import com.rms.common.xml.eception.XMLParseException;
import com.rms.common.xml.node.DocumentNode;

public class W3CXMLParserTest {

	public static void main(String[] args) throws XMLParseException, FileNotFoundException {

		String file = "D:/work/test/main.xml";

		InputStream is = IOFactory.newFileInputStream(new File(file));

		DocumentNode documentNode = W3CXMLParser.newInstance().parseObject(is);

		System.out.println(documentNode);
	}
}
