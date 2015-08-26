package com.rms.common.xml.node.support;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import com.rms.common.xml.node.AttributeNode;
import com.rms.common.xml.node.DocumentNode;
import com.rms.common.xml.node.DocumentTypeNode;
import com.rms.common.xml.node.ElementNode;
import com.rms.common.xml.node.TextNode;

/**
 *
 * @author ri.meisei
 * @since 2014/01/21
 */
interface NodeConvertor {

	public DocumentNode convert(Document document);

	public DocumentTypeNode convert(DocumentType documentType);

	public ElementNode convert(Element element);

	public AttributeNode convert(Attr attr);

	public TextNode convert(Text text);
}
