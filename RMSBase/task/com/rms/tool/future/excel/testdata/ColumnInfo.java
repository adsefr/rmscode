package com.rms.tool.future.excel.testdata;

import java.util.ArrayList;
import java.util.List;

public class ColumnInfo {

	private String name;

	private List<String> values = new ArrayList<>();

	public ColumnInfo() {
	}

	@Override
	public String toString() {

		return "【name】" + name + "【VALUE】" + values;
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
	 * @return values
	 */
	public List<String> getValues() {

		return values;
	}

	/**
	 * @param values
	 *            セットする values
	 */
	public void setValues(List<String> values) {

		this.values = values;
	}

}
