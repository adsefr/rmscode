package com.rms.base.jdbc.instance;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.rms.base.jdbc.JDBCUtil;
import com.rms.base.jdbc.model.JDBCRow;
import com.rms.base.jdbc.model.QueryParameter;
import com.rms.base.validate.Assertion;
import com.rms.common.jdbc.JDBCQueryResult;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/02/24
 */
class DefaultJDBCQueryResult implements JDBCQueryResult {

	private QueryParameter queryParameter;

	private Map<Integer, String> queryResultColumnNameMap = new TreeMap<>();

	private Integer columnCount = 0;

	private Map<Integer, Map<String, Object>> queryResultValuesMap = new HashMap<>();

	private Integer resultCount = 0;

	private Integer currentRowNumber = 0;

	private String errorCode;

	private String errorMessage;

	private DefaultJDBCQueryResult(ResultSet resultSet) throws SQLException {

		Assertion.assertNotNull("resultSet", resultSet);

		ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
		columnCount = resultSetMetaData.getColumnCount();
		for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
			queryResultColumnNameMap.put(columnIndex, resultSetMetaData.getColumnName(columnIndex).toUpperCase());
		}

		int currentRowNumber = 1;
		int maxColumnNumber = queryResultColumnNameMap.size();
		while (resultSet.next()) {
			Map<String, Object> queryResultValue = new HashMap<>();
			for (int columnNumber = 1; columnNumber <= maxColumnNumber; columnNumber++) {
				String columnName = queryResultColumnNameMap.get(columnNumber);
				queryResultValue.put(columnName, resultSet.getObject(columnName));
			}
			queryResultValuesMap.put(currentRowNumber++, queryResultValue);
		}
		resultCount = queryResultValuesMap.size();

		JDBCUtil.close(resultSet);
	}

	DefaultJDBCQueryResult(ResultSet resultSet, QueryParameter queryParameter) throws SQLException {

		this(resultSet);

		this.queryParameter = queryParameter;
	}

	@Override
	public QueryParameter getQueryParameter() {

		return queryParameter;
	}

	@Override
	public boolean hasNext() throws SQLException {

		return (++currentRowNumber <= queryResultValuesMap.size());
	}

	@Override
	public void beforeFirst() throws SQLException {

		currentRowNumber = 0;
	}

	@Override
	public void before(int rowNumber) throws SQLException {

		currentRowNumber -= rowNumber;

		if (currentRowNumber < 0) {
			currentRowNumber = 0;
		}
	}

	@Override
	public void after(int rowNumber) throws SQLException {

		currentRowNumber += rowNumber;

		if (currentRowNumber > resultCount) {
			currentRowNumber = resultCount;
		}
	}

	@Override
	public void afterLast() throws SQLException {

		currentRowNumber = resultCount + 1;
	}

	@Override
	public JDBCRow getRow() throws SQLException {

		// DefaultJDBCRow row = new DefaultJDBCRow();
		//
		// for (int columnNumber = 1; columnNumber <= columnCount;
		// columnNumber++) {
		// String columnName = resultSetMetaData.getColumnName(columnNumber);
		// Object rawValue = getValue(columnNumber);
		//
		// DefaultJDBCColumn column = new DefaultJDBCColumn();
		// column.setColumnName(columnName);
		// column.setRawValue(rawValue);
		//
		// JDBCValue jdbcValue = new DefaultJDBCValue();
		// jdbcValue.setRawValue(rawValue);
		// jdbcValue.setValueType(resultSetMetaData.getColumnType(columnNumber));
		// column.setJdbcValue(jdbcValue);
		//
		// row.addColumn(column);
		// }
		//
		// return row;
		return null;
	}

	@Override
	public boolean hasColumn(int columnIndex) throws SQLException {

		return columnIndex >= 1 && queryResultColumnNameMap.size() >= columnIndex;
	}

	@Override
	public boolean hasColumn(String columnName) throws SQLException {

		Assertion.assertNotBlank("columnName", columnName);

		return queryResultColumnNameMap.values().contains(columnName.toUpperCase());
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getValue(int columnIndex) throws SQLException {

		Assertion.assertNotNull("columnIndex", columnIndex);

		Object object = null;

		if (hasColumn(columnIndex)) {
			object = queryResultValuesMap.get(currentRowNumber).get(columnIndex);
		}

		return (T) object;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getValue(String columnName) throws SQLException {

		Assertion.assertNotNull("columnName", columnName);

		Object object = null;

		if (hasColumn(columnName)) {
			object = queryResultValuesMap.get(currentRowNumber).get(columnName.toUpperCase());
		}

		return (T) object;
	}

	/**
	 * @return errorCode
	 */
	@Override
	public String getErrorCode() {

		return errorCode;
	}

	/**
	 * @return errorMessage
	 */
	@Override
	public String getErrorMessage() {

		return errorMessage;
	}

	/**
	 * @return resultCount
	 */
	@Override
	public Integer getResultCount() {

		return resultCount;
	}
}
