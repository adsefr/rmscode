package com.rms.tool.future.excel.testdata;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.rms.base.constant.Characters;

/**
 *
 * @author ri.meisei
 * @since 2015/11/06
 */
public class TestDataBean {

	private String testCaseName;

	private Map<String, List<ColumnInfo>> testDataCollection = new LinkedHashMap<>();

	public TestDataBean() {
	}

	/**
	 * @return testCaseName
	 */
	public String getTestCaseName() {

		return testCaseName;
	}

	/**
	 * @param testCaseName
	 *            セットする testCaseName
	 */
	public void setTestCaseName(String testCaseName) {

		this.testCaseName = testCaseName;
	}

	/**
	 * @return testDataCollection
	 */
	public Map<String, List<ColumnInfo>> getTestDataCollection() {

		return testDataCollection;
	}

	/**
	 * @param testDataCollection
	 *            セットする testDataCollection
	 */
	public void setTestDataCollection(Map<String, List<ColumnInfo>> testDataCollection) {

		this.testDataCollection = testDataCollection;
	}

	@Override
	public String toString() {

		StringBuilder builder = new StringBuilder();
		builder.append(testCaseName).append(Characters.LINE_SEPARATOR_SYSTEM);
		for (Map.Entry<String, List<ColumnInfo>> entry : testDataCollection.entrySet()) {
			builder.append("TABLE：").append(entry.getKey()).append(Characters.LINE_SEPARATOR_SYSTEM);
			builder.append("COLUMN_INFO：").append(entry.getValue()).append(Characters.LINE_SEPARATOR_SYSTEM);
		}

		return builder.toString();
	}
}
