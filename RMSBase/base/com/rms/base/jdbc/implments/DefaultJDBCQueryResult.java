package com.rms.base.jdbc.implments;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.rms.base.jdbc.AbstractJDBCQueryResult;
import com.rms.base.jdbc.model.JDBCRow;
import com.rms.base.jdbc.model.QueryParameter;
import com.rms.base.util.ArrayUtil;
import com.rms.base.validate.Assertion;

/**
 *
 * @author ri.meisei
 * @since 2014/02/24
 */
class DefaultJDBCQueryResult extends AbstractJDBCQueryResult {

	private final List<JDBCRow> queryResultJDBCRowCollection = new ArrayList<>();

	private Integer currentRowNumber = 0;

	DefaultJDBCQueryResult(ResultSet resultSet) throws SQLException {

		this(resultSet, null);
	}

	DefaultJDBCQueryResult(ResultSet resultSet, QueryParameter queryParameter) throws SQLException {

		super(resultSet, queryParameter);

		while (getResultSet().next()) {
			JDBCRow jdbcRow = convertToJDBCRow(getResultSet());

			ArrayUtil.add(queryResultJDBCRowCollection, jdbcRow.getRowNumber(), jdbcRow);
		}

		beforeFirst();

		close();
	}

	@Override
	public final void beforeFirst() throws SQLException {

		currentRowNumber = 0;
	}

	@Override
	public final void absolute(int rowNumber) throws SQLException {

		int _rowNumber = Math.abs(rowNumber);
		int mod = _rowNumber % queryResultJDBCRowCollection.size();

		if (rowNumber > 0) {
			currentRowNumber = mod;
		} else if (rowNumber < 0) {
			currentRowNumber = queryResultJDBCRowCollection.size() - mod;
		} else {
			currentRowNumber = 0;
		}
	}

	@Override
	public final void afterLast() throws SQLException {

		currentRowNumber = queryResultJDBCRowCollection.size() + 1;
	}

	@Override
	public final boolean hasNext() throws SQLException {

		return (++currentRowNumber < queryResultJDBCRowCollection.size());
	}

	@Override
	public final JDBCRow getRow() throws SQLException {

		return queryResultJDBCRowCollection.get(currentRowNumber);
	}

	@SuppressWarnings("unchecked")
	@Override
	public final <T> T getValue(int columnNumber) throws SQLException {

		Assertion.assertNotNull("columnNumber", columnNumber);

		if (!hasColumn(columnNumber)) {
			throw new SQLException("the columnNumber[" + columnNumber + "] is not found!!!");
		}

		Object object = queryResultJDBCRowCollection.get(currentRowNumber).getValue(columnNumber);

		return (T) object;
	}

	@SuppressWarnings("unchecked")
	@Override
	public final <T> T getValue(String columnName) throws SQLException {

		Assertion.assertNotNull("columnName", columnName);

		if (!hasColumn(columnName)) {
			throw new SQLException("the columnName[" + columnName + "] is not found!!!");
		}

		JDBCRow jdbcRow = queryResultJDBCRowCollection.get(currentRowNumber);

		Object object = jdbcRow.getValue(columnName);

		return (T) object;
	}

	@Override
	public final List<Object> getValues() throws SQLException {

		JDBCRow jdbcRow = getRow();

		List<Object> values = new ArrayList<>();
		for (int columnNumber = 1; columnNumber <= jdbcRow.getColumnCount(); columnNumber++) {
			values.add(jdbcRow.getValue(columnNumber));
		}

		return values;

	}
}
