package com.rms.common.xml;

import java.io.InputStream;

import com.rms.common.xml.eception.XMLParseException;
import com.rms.common.xml.node.DocumentNode;

/**
 * 
 * 
 * @author ri.meisei
 * @since 2014/01/21
 */
public interface XMLParser {

	void setNamespaceAware(boolean namespaceAware);

	void setValidating(boolean validating);

	public DocumentNode parse(String uri) throws XMLParseException;

	public DocumentNode parse(InputStream is) throws XMLParseException;
}
