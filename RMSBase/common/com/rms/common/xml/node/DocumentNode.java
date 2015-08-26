package com.rms.common.xml.node;

/**
 * 
 * 
 * @author ri.meisei
 * @since 2014/01/21
 */
public interface DocumentNode extends Node {

	public String getVersion();

	public String getEncoding();

	public boolean isStandalone();

	public DocumentTypeNode getDocumentTypeNode();
}
