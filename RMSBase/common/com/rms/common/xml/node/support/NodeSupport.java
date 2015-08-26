package com.rms.common.xml.node.support;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import com.rms.base.exception.UnsupportedTypeException;
import com.rms.base.validate.Assertion;
import com.rms.common.xml.node.Node;

/**
 * nodeを処理する補助クラス
 *
 * @author ri.meisei
 * @since 2014/06/06
 */
public final class NodeSupport {

	private final static NodeConvertor convertor = new NodeConvertorImpl();

	public static Node convert(org.w3c.dom.Node node) {

		Assertion.assertNotNull("node", node);

		Node converted = null;

		if (node.getClass().isInstance(Document.class)) {
			converted = convertor.convert(Document.class.cast(node));
		} else if (node.getClass().isInstance(DocumentType.class)) {
			converted = convertor.convert(DocumentType.class.cast(node));
		} else if (node.getClass().isInstance(Element.class)) {
			converted = convertor.convert(Element.class.cast(node));
		} else if (node.getClass().isInstance(Text.class)) {
			converted = convertor.convert(Text.class.cast(node));
		} else if (node.getClass().isInstance(Attr.class)) {
			converted = convertor.convert(Attr.class.cast(node));
		} else {
			throw new UnsupportedTypeException(node.getClass().getName());
		}

		return converted;
	}

	public static <T extends Node> T convert(org.w3c.dom.Node node, Class<T> clazz) {

		Assertion.assertNotNull("clazz", clazz);

		return clazz.cast(convert(node));
	}
}
