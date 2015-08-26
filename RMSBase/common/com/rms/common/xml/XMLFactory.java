package com.rms.common.xml;

import com.rms.common.xml.impl.W3CXMLGenerator;

/**
 * 
 * 
 * @author ri.meisei
 * @since 2014/01/24
 */
public abstract class XMLFactory {

	public static W3CXMLGenerator newW3CXMLGenerator() {

		return W3CXMLGenerator.newInstance();
	}
}
