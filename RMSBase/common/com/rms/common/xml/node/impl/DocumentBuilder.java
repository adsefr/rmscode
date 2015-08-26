package com.rms.common.xml.node.impl;

import com.rms.common.xml.node.DocumentNode;
import com.rms.common.xml.node.DocumentTypeNode;

/**
 * 
 * 
 * @author ri.meisei
 * @since 2014/01/23
 */
public interface DocumentBuilder extends NodeBuilder<DocumentBuilder, DocumentNode> {

	public DocumentBuilder version(String version);

	public DocumentBuilder encoding(String encoding);

	public DocumentBuilder standalone(boolean standalone);

	public DocumentBuilder documentTypeNode(DocumentTypeNode documentTypeNode);

}
