package com.rms.common.xml.node.impl;

import com.rms.common.xml.enums.NodeType;
import com.rms.common.xml.node.ElementNode;
import com.rms.common.xml.node.Node;

/**
 * 
 * 
 * @author ri.meisei
 * @since 2014/01/21
 */
public abstract class NodeImpl implements Node {

	private String nodeName = null;

	private NodeType nodeType = null;

	private int level = 0;

	private ElementNode rootElementNode = null;

	private ElementNode parentElementNode = null;

	private int occurrenceTime = 0;

	protected NodeImpl() {

	}

	/**
	 * @return nodeName
	 */
	public String getNodeName() {

		return nodeName;
	}

	/**
	 * @return nodeType
	 */
	public NodeType getNodeType() {

		return nodeType;
	}

	/**
	 * @return level
	 */
	public int getLevel() {

		return level;
	}

	/**
	 * @return rootElementNode
	 */
	public ElementNode getRootElementNode() {

		return rootElementNode;
	}

	/**
	 * @return parentElementNode
	 */
	public ElementNode getParentElementNode() {

		return parentElementNode;
	}

	/**
	 * @return occurrenceTime
	 */
	public int getOccurrenceTime() {

		return occurrenceTime;
	}

	/**
	 * @param nodeName
	 *            セットする nodeName
	 */
	void setNodeName(String nodeName) {

		this.nodeName = nodeName;
	}

	/**
	 * @param nodeType
	 *            セットする nodeType
	 */
	void setNodeType(NodeType nodeType) {

		this.nodeType = nodeType;
	}

	/**
	 * @param level
	 *            セットする level
	 */
	void setLevel(int level) {

		this.level = level;
	}

	/**
	 * @param rootElementNode
	 *            セットする rootElementNode
	 */
	void setRootElementNode(ElementNode rootElementNode) {

		this.rootElementNode = rootElementNode;
	}

	/**
	 * @param parentElementNode
	 *            セットする parentElementNode
	 */
	void setParentElementNode(ElementNode parentElementNode) {

		this.parentElementNode = parentElementNode;
	}

	/**
	 * @param occurrenceTime
	 *            セットする occurrenceTime
	 */
	void setOccurrenceTime(int occurrenceTime) {

		this.occurrenceTime = occurrenceTime;
	}

}
