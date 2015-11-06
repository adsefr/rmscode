package com.rms.tool.future.excel.testdata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataInfo {

	private String name;

	private Map<String, List<String>> dataCollection = new HashMap<>();

	public DataInfo() {
	}

	/**
	 * @return name
	 */
	public String getName() {

		return name;
	}

	/**
	 * @param name
	 *            セットする name
	 */
	public void setName(String name) {

		this.name = name;
	}

	/**
	 * @return dataCollection
	 */
	public Map<String, List<String>> getDataCollection() {

		return dataCollection;
	}

	/**
	 * @param dataCollection
	 *            セットする dataCollection
	 */
	public void setDataCollection(Map<String, List<String>> dataCollection) {

		this.dataCollection = dataCollection;
	}
}
