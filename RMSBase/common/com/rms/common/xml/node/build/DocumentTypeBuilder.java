package com.rms.common.xml.node.build;

import com.rms.common.xml.node.DocumentTypeNode;

public interface DocumentTypeBuilder extends NodeBuilder<DocumentTypeBuilder, DocumentTypeNode> {

	public DocumentTypeBuilder name(String name);

	public DocumentTypeBuilder systemId(String systemId);

	public DocumentTypeBuilder publicId(String publicId);

	public DocumentTypeBuilder internalSubSet(String internalSubSet);

}
