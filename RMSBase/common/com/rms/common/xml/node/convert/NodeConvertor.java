package com.rms.common.xml.node.convert;

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
 * 
 * @author ri.meisei
 * @since 2014/01/21
 */
public abstract class NodeConvertor {

	public static NodeConvertor getInstance() {

		return new NodeConvertorImpl();
	}

	public abstract DocumentNode convert(Document document);

	public abstract DocumentTypeNode convert(DocumentType documentType);

	public abstract ElementNode convert(Element element);

	public abstract AttributeNode convert(Attr attr);

	public abstract TextNode convert(Text text);

}
