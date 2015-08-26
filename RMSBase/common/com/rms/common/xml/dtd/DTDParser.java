package com.rms.common.xml.dtd;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import com.rms.base.util.TextUtil;
import com.rms.common.xml.dtd.bean.CardinalType;
import com.rms.common.xml.dtd.bean.DTDAttlistBean;
import com.rms.common.xml.dtd.bean.DTDAttributeBean;
import com.rms.common.xml.dtd.bean.DTDBean;
import com.rms.common.xml.dtd.bean.DTDCommentBean;
import com.rms.common.xml.dtd.bean.DTDElementBean;
import com.rms.common.xml.dtd.bean.DTDEmptyBean;
import com.rms.common.xml.dtd.bean.DTDEntityBean;
import com.rms.common.xml.dtd.bean.DTDItemBean;
import com.rms.common.xml.dtd.bean.DTDNotationBean;
import com.wutka.dtd.DTD;
import com.wutka.dtd.DTDAny;
import com.wutka.dtd.DTDAttlist;
import com.wutka.dtd.DTDAttribute;
import com.wutka.dtd.DTDComment;
import com.wutka.dtd.DTDContainer;
import com.wutka.dtd.DTDElement;
import com.wutka.dtd.DTDEmpty;
import com.wutka.dtd.DTDEntity;
import com.wutka.dtd.DTDEnumeration;
import com.wutka.dtd.DTDItem;
import com.wutka.dtd.DTDName;
import com.wutka.dtd.DTDNotation;
import com.wutka.dtd.DTDNotationList;
import com.wutka.dtd.DTDOutput;
import com.wutka.dtd.DTDPCData;
import com.wutka.dtd.DTDProcessingInstruction;

public class DTDParser {

	/**
	 * @throws IOException
	 */
	public DTDParser() throws IOException {

	}

	/**
	 *
	 * @param dtdFile
	 * @return
	 * @throws IOException
	 */
	public DTDBean parse(File dtdFile) throws IOException {

		com.wutka.dtd.DTDParser dtdParser = new com.wutka.dtd.DTDParser(dtdFile);
		DTD dtd = dtdParser.parse();
		Object[] items = dtd.getItems();

		DTDBean dtdBean = new DTDBean();
		dtdBean.setDtdFile(dtdFile);

		for (Object item : items) {
			if (item instanceof DTDElement) {
				DTDElementBean dtdElementBean = convertDTDElement((DTDElement) item);
				dtdBean.getDtdElementBeans().add(dtdElementBean);

			} else if (item instanceof DTDAttlist) {
				DTDAttlistBean dtdAttlistBean = convertDTDAttlist((DTDAttlist) item);
				dtdBean.getDtdAttlistBeans().add(dtdAttlistBean);

			} else if (item instanceof DTDEntity) {
				DTDEntityBean dtdEntityBean = convertDTDEntity((DTDEntity) item);
				dtdBean.getDtdEntityBeans().add(dtdEntityBean);

			} else if (item instanceof DTDEmpty) {
				DTDEmptyBean dtdEmptyBean = convertDTDEmpty((DTDEmpty) item);
				dtdBean.getDtdEmptyBeans().add(dtdEmptyBean);

			} else if (item instanceof DTDNotation) {
				DTDNotationBean dtdNotationBean = convertDTDNotation((DTDNotation) item);
				dtdBean.getDtdNotationBeans().add(dtdNotationBean);

			} else if (item instanceof DTDComment) {
				DTDCommentBean dtdCommentBean = convertDTDComment((DTDComment) item);
				dtdBean.getDtdCommentBeans().add(dtdCommentBean);

			} else if (item instanceof DTDProcessingInstruction) {
				// TODO
			} else {
				throw new RuntimeException(item.toString());
			}
		}

		return dtdBean;
	}

	/**
	 *
	 * @param dtdElement
	 * @return
	 */
	private DTDElementBean convertDTDElement(DTDElement dtdElement) {

		DTDElementBean dtdElementBean = new DTDElementBean();

		dtdElementBean.setName(dtdElement.getName());
		dtdElementBean.setCardinalType(CardinalType.get(dtdElement.getContent().getCardinal().type));
		DTDItem dtdItem = dtdElement.getContent();
		dtdElementBean.setContent(printDTDOutput(dtdItem));
		dtdElementBean.setDtdItemBeans(convertDTDItem(dtdItem));

		String content = dtdElementBean.getContent();// TODO

		int endIndex = content.lastIndexOf(")");
		if (endIndex > 0) {
			content = content.substring(1, endIndex);
			String[] arrayStrings = content.split(",");
			for (String str : arrayStrings) {
				if (str.contains("(")) {
					dtdElementBean.setMixedContent(true);
				}
			}
		}

		@SuppressWarnings("unchecked")
		Hashtable<String, DTDAttribute> hashtable = dtdElement.attributes;
		for (String key : hashtable.keySet()) {
			DTDAttribute dtdAttribute = hashtable.get(key);
			DTDAttributeBean dtdAttributeBean = convertDTDAttribute(dtdAttribute);
			dtdElementBean.getAttribueMap().put(key, dtdAttributeBean);
		}

		return dtdElementBean;
	}

	/**
	 *
	 * @param dtdAttlist
	 * @return
	 */
	private static DTDAttlistBean convertDTDAttlist(DTDAttlist dtdAttlist) {

		DTDAttlistBean dtdAttlistBean = new DTDAttlistBean();

		dtdAttlistBean.setName(dtdAttlist.getName());

		for (DTDAttribute dtdAttribute : dtdAttlist.getAttribute()) {
			DTDAttributeBean dtdAttributeBean = convertDTDAttribute(dtdAttribute);

			dtdAttlistBean.getDtdAttributeBeanList().add(dtdAttributeBean);
		}

		return dtdAttlistBean;
	}

	/**
	 *
	 * @param dtdAttribute
	 * @return
	 */
	private static DTDAttributeBean convertDTDAttribute(DTDAttribute dtdAttribute) {

		DTDAttributeBean dtdAttributeBean = new DTDAttributeBean();

		dtdAttributeBean.setName(dtdAttribute.getName());

		Object typeObj = dtdAttribute.getType();
		String typeVal = "";

		if (dtdAttribute.getType() instanceof String) {
			typeVal = String.valueOf(typeObj);

		} else if (typeObj instanceof DTDEnumeration) {

			typeVal = printDTDOutput((DTDEnumeration) typeObj);

		} else if (typeObj instanceof DTDNotationList) {

			typeVal = printDTDOutput((DTDNotationList) typeObj);
		}
		dtdAttributeBean.setType(typeVal);
		if (dtdAttribute.getDecl() != null) {
			dtdAttributeBean.setDeclaration(printDTDOutput(dtdAttribute.getDecl()));
		}
		dtdAttributeBean.setDefaultValue(TextUtil.repaceNull(dtdAttribute.getDefaultValue()));

		return dtdAttributeBean;
	}

	/**
	 *
	 * @param dtdEntity
	 * @return
	 */
	private static DTDEntityBean convertDTDEntity(DTDEntity dtdEntity) {

		DTDEntityBean dtdEntityBean = new DTDEntityBean();

		dtdEntityBean.setName(dtdEntity.getName());
		dtdEntityBean.setValue(dtdEntity.getValue());
		dtdEntityBean.setNdata(dtdEntity.getNdata());

		return dtdEntityBean;
	}

	/**
	 *
	 * @param dtdEmpty
	 * @return
	 */
	private static DTDEmptyBean convertDTDEmpty(DTDEmpty dtdEmpty) {

		DTDEmptyBean dtdEmptyBean = new DTDEmptyBean();
		dtdEmptyBean.setType(dtdEmpty.getCardinal().type);
		dtdEmptyBean.setName(dtdEmpty.getCardinal().name);

		return dtdEmptyBean;
	}

	/**
	 *
	 * @param dtdNotation
	 * @return
	 */
	private static DTDNotationBean convertDTDNotation(DTDNotation dtdNotation) {

		DTDNotationBean dtdNotationBean = new DTDNotationBean();
		dtdNotationBean.setName(dtdNotation.getName());
		dtdNotationBean.setSystem(dtdNotation.getExternalID().getSystem());

		return dtdNotationBean;
	}

	/**
	 *
	 * @param dtdComment
	 * @return
	 */
	private static DTDCommentBean convertDTDComment(DTDComment dtdComment) {

		DTDCommentBean dtdCommentBean = new DTDCommentBean();
		dtdCommentBean.setText(dtdComment.getText());

		return dtdCommentBean;
	}

	/**
	 *
	 * @param dtdItem
	 * @return
	 */
	private static String printDTDOutput(DTDOutput dtdOutput) {

		StringWriter writer = new StringWriter();
		PrintWriter printWriter = new PrintWriter(writer);
		try {
			dtdOutput.write(printWriter);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return writer.toString();
	}

	private List<DTDItemBean> convertDTDItem(DTDItem dtdItem) {

		List<DTDItemBean> dtdItemBeans = new ArrayList<DTDItemBean>();

		int type = dtdItem.getCardinal().type;
		CardinalType cardinalType = CardinalType.get(type);

		if (dtdItem instanceof DTDAny) {
			DTDItemBean dtdItemBean = new DTDItemBean();
			dtdItemBean.setName("ANY");
			dtdItemBean.setCardinalType(cardinalType);
			dtdItemBeans.add(dtdItemBean);
		}

		if (dtdItem instanceof DTDContainer) {
			DTDItem[] dtdItems = ((DTDContainer) dtdItem).getItems();
			for (DTDItem item : dtdItems) {
				dtdItemBeans.addAll(convertDTDItem(item));
			}
		}

		if (dtdItem instanceof DTDEmpty) {
			DTDItemBean dtdItemBean = new DTDItemBean();
			dtdItemBean.setName("EMPTY");
			dtdItemBean.setCardinalType(cardinalType);
			dtdItemBeans.add(dtdItemBean);
		}

		if (dtdItem instanceof DTDName) {
			DTDItemBean dtdItemBean = new DTDItemBean();
			dtdItemBean.setName(((DTDName) dtdItem).getValue());
			dtdItemBean.setCardinalType(cardinalType);
			dtdItemBeans.add(dtdItemBean);
		}

		if (dtdItem instanceof DTDPCData) {
			DTDItemBean dtdItemBean = new DTDItemBean();
			dtdItemBean.setName("#PCDATA");
			dtdItemBean.setCardinalType(cardinalType);
			dtdItemBeans.add(dtdItemBean);
		}

		return dtdItemBeans;
	}
}
