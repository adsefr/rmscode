package com.rms.common.xml.node.build;

/**
 * 
 * 
 * @author ri.meisei
 * @since 2014/01/23
 */
public final class NodeBuilderFactory {

	public static DocumentBuilder newDocumentBuilder() {

		return new DocumentBuilderImpl();
	}

	public static DocumentTypeBuilder newdoDocumentTypeBuilder() {

		return new DocumentTypeBuilderImpl();
	}

	public static ElementBuilder newElementBuilder() {

		return new ElementBuilderImpl();
	}

	public static AttributeBuilder newAttributeBuilder() {

		return new AttributeBuilderImpl();
	}

	public static TextBuilder newTextBuilder() {

		return new TextBuilderImpl();
	}
}
