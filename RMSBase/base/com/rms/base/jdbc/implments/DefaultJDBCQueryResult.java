package com.rms.base.jdbc.implments;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.rms.base.jdbc.AbstractJDBCQueryResult;
import com.rms.base.jdbc.model.JDBCRow;
import com.rms.base.jdbc.model.QueryParameter;
import com.rms.base.validate.Assertion;

/**
 *
 * @author ri.meisei
 * @since 2014/02/24
 */
class DefaultJDBCQueryResult extends AbstractJDBCQueryResult {

	private final List<JDBCRow> queryResultRows = new ArrayList<>();

	private Integer currentRowNumber = 0;

	DefaultJDBCQueryResult(ResultSet resultSet) throws SQLException {

		super(resultSet, null);

	}

	DefaultJDBCQueryResult(ResultSet resultSet, QueryParameter queryParameter) throws SQLException {

		super(resultSet, queryParameter);

		while (resultSet.next()) {
			JDBCRow jdbcRow = convertCurrentResultToJDBCRow();

			queryResultRows.add(jdbcRow);
		}

		queryResultRows.add(0, JDBCFactory.newJDBCRow());
	}

	@Override
	public final void beforeFirst() throws SQLException {

		currentRowNumber = 0;
	}

	@Override
	public final void absolute(int rowNumber) throws SQLException {

		int _rowNumber = Math.abs(rowNumber);
		int mod = _rowNumber % queryResultRows.size();

		if (rowNumber > 0) {
			currentRowNumber = mod;
		} else if (rowNumber < 0) {
			currentRowNumber = queryResultRows.size() - mod;
		} else {
			currentRowNumber = 0;
		}
	}

	@Override
	public final void afterLast() throws SQLException {

		currentRowNumber = queryResultRows.size() + 1;
	}

	@Override
	public final boolean hasNext() throws SQLException {

		return (++currentRowNumber >= queryResultRows.size());
	}

	@Override
	public final JDBCRow getRow() throws SQLException {

		return queryResultRows.get(currentRowNumber);
	}

	@SuppressWarnings("unchecked")
	@Override
	public final <T> T getValue(int columnNumber) throws SQLException {

		Assertion.assertNotNull("columnNumber", columnNumber);

		if (!hasColumn(columnNumber)) {
			throw new SQLException("the columnNumber[" + columnNumber + "] is not found!!!");
		}

		Object object = queryResultRows.get(currentRowNumber).getValue(columnNumber);

		return (T) object;
	}

	@SuppressWarnings("unchecked")
	@Override
	public final <T> T getValue(String columnName) throws SQLException {

		Assertion.assertNotNull("columnName", columnName);

		if (!hasColumn(columnName)) {
			throw new SQLException("the columnName[" + columnName + "] is not found!!!");
		}

		Object object = queryResultRows.get(currentRowNumber).getValue(columnName);

		return (T) object;
	}
}
