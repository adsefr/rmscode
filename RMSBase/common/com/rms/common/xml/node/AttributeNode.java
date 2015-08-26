package com.rms.common.xml.node;

/**
 * 
 * @author ri.meisei
 * @since 2014/01/21
 */
public interface AttributeNode extends Node {

	public String getNamespaceURI();

	public String getPrefix();

	public String getLocalPart();

	public String getQualifiedName();

	public String getName();

	public String getValue();

}
