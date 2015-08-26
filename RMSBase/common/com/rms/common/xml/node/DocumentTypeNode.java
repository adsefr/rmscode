package com.rms.common.xml.node;

/**
 * 
 * 
 * @author ri.meisei
 * @since 2014/01/21
 */
public interface DocumentTypeNode extends Node {

	public String getName();

	public String getSystemId();

	public String getPublicId();

	public String getInternalSubSet();
}
