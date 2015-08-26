package com.rms.common.xml.bean.backup;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * 
 * @author ri.meisei
 * @since 2013/12/18
 */
public class StatictsInfo {

	/** Seq-Xpath */
	private Map<Integer, String> sequenceMap = new TreeMap<Integer, String>();

	/** XPath-TagName */
	private Map<String, String> staticsTagNames = new TreeMap<String, String>();

	/** Xpath-times */
	private Map<String, Integer> statictsTagTimes = new HashMap<String, Integer>();

	/** Xpath-attributes */
	private Map<String, Set<String>> staticsTagAttributes = new HashMap<String, Set<String>>();

	public StatictsInfo() {

	}

	public StatictsInfo staticts(ElementBean elementBean) {

		List<ElementBean> stack = new LinkedList<ElementBean>();
		stack.add(elementBean);

		int seq = 1;
		while (!stack.isEmpty()) {
			ElementBean currBean = stack.remove(0);
			Collections.reverse(currBean.getChildrens());
			for (int i = 0; i < currBean.getChildrens().size(); i++) {
				stack.add(0, currBean.getChildrens().get(i));
			}
			Collections.reverse(currBean.getChildrens());

			String xpath = currBean.getXpath();
			String tagName = currBean.getTagName();
			Integer times = statictsTagTimes.get(xpath);
			if (times == null) {
				times = new Integer(0);
			}
			Set<String> attributeNames = staticsTagAttributes.get(xpath);
			if (attributeNames == null) {
				attributeNames = new LinkedHashSet<String>();
				staticsTagAttributes.put(xpath, attributeNames);
			}

			if (!getSequenceMap().values().contains(xpath)) {
				getSequenceMap().put(seq++, xpath);
				getStaticsTagNames().put(xpath, tagName);
			}
			statictsTagTimes.put(xpath, times + 1);

			for (AttributeBean attributeBean : currBean.getAttributes()) {
				attributeNames.add(attributeBean.getTagName());
			}
		}

		return this;
	}

	/**
	 * @return sequenceMap
	 */
	public synchronized Map<Integer, String> getSequenceMap() {

		return sequenceMap;
	}

	/**
	 * @param sequenceMap
	 *            セットする sequenceMap
	 */
	public synchronized void setSequenceMap(Map<Integer, String> sequenceMap) {

		this.sequenceMap = sequenceMap;
	}

	/**
	 * @return staticsTagNames
	 */
	public synchronized Map<String, String> getStaticsTagNames() {

		return staticsTagNames;
	}

	/**
	 * @param staticsTagNames
	 *            セットする staticsTagNames
	 */
	public synchronized void setStaticsTagNames(Map<String, String> staticsTagNames) {

		this.staticsTagNames = staticsTagNames;
	}

	/**
	 * @return statictsTagTimes
	 */
	public synchronized Map<String, Integer> getStatictsTagTimes() {

		return statictsTagTimes;
	}

	/**
	 * @param statictsTagTimes
	 *            セットする statictsTagTimes
	 */
	public synchronized void setStatictsTagTimes(Map<String, Integer> statictsTagTimes) {

		this.statictsTagTimes = statictsTagTimes;
	}

	/**
	 * @return staticsTagAttributes
	 */
	public synchronized Map<String, Set<String>> getStaticsTagAttributes() {

		return staticsTagAttributes;
	}

	/**
	 * @param staticsTagAttributes
	 *            セットする staticsTagAttributes
	 */
	public synchronized void setStaticsTagAttributes(Map<String, Set<String>> staticsTagAttributes) {

		this.staticsTagAttributes = staticsTagAttributes;
	}

}
