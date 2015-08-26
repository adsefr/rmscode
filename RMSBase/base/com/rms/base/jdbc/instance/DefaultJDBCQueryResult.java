package com.rms.base.jdbc.instance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rms.base.jdbc.model.JDBCRow;
import com.rms.base.jdbc.model.QueryParameter;
import com.rms.base.validate.Assertion;

/**
 *
 * @author ri.meisei
 * @since 2014/02/24
 */
class DefaultJDBCQueryResult extends AbstractJDBCQueryResult {

	private final List<Map<String, Object>> queryResultDataCollection = new ArrayList<>();

	private Integer currentRowNumber = 0;

	DefaultJDBCQueryResult(ResultSet resultSet) throws SQLException {

		super(resultSet, null);
	}

	DefaultJDBCQueryResult(ResultSet resultSet, QueryParameter queryParameter) throws SQLException {

		super(resultSet, queryParameter);

		while (resultSet.next()) {
			Map<String, Object> queryResultValue = new HashMap<>();
			for (String columnName : queryResultColumnNameCollection) {
				queryResultValue.put(columnName, resultSet.getObject(columnName));
			}
			queryResultDataCollection.add(queryResultValue);
		}

		close();
	}

	@Override
	public final void beforeFirst() throws SQLException {

		currentRowNumber = 0;
	}

	@Override
	public final void absolute(int rowNumber) throws SQLException {

		if (rowNumber == 0) {
		}

		if (rowNumber > 0) {
		}

		if (rowNumber < 0) {
		}
	}

	@Override
	public final void afterLast() throws SQLException {

		currentRowNumber = resultCount + 1;
	}

	@Override
	public final boolean hasNext() throws SQLException {

		return (++currentRowNumber > queryResultDataCollection.size());
	}

	@Override
	public final JDBCRow getRow() throws SQLException {

		// TODO
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

	@SuppressWarnings("unchecked")
	@Override
	public final <T> T getValue(int columnNumber) throws SQLException {

		Assertion.assertNotNull("columnNumber", columnNumber);

		if (hasColumn(columnNumber)) {
			throw new SQLException("the columnNumber is not found!!![" + columnNumber + "]");
		}

		Object object = queryResultDataCollection.get(currentRowNumber).get(columnNumber);

		return (T) object;
	}

	@SuppressWarnings("unchecked")
	@Override
	public final <T> T getValue(String columnName) throws SQLException {

		Assertion.assertNotNull("columnName", columnName);

		if (hasColumn(columnName)) {
			throw new SQLException("the columnName is not found!!![" + columnName + "]");
		}

		Object object = queryResultDataCollection.get(currentRowNumber).get(columnName.toUpperCase());

		return (T) object;
	}
}
