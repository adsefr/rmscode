package com.rms.base.jdbc.instance;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.rms.base.jdbc.model.JDBCRow;
import com.rms.base.jdbc.model.QueryParameter;
import com.rms.base.validate.Assertion;

/**
 *
 * @author ri.meisei
 * @since 2014/02/24
 */
class JDBCQueryLargeDataResult extends AbstractJDBCQueryResult {

	JDBCQueryLargeDataResult(ResultSet resultSet, QueryParameter queryParameter) throws SQLException {

		super(resultSet, queryParameter);
	}

	@Override
	public final boolean hasNext() throws SQLException {

		return getResultSet().next();
	}

	@Override
	public final void beforeFirst() throws SQLException {

		getResultSet().beforeFirst();
	}

	@Override
	public final void absolute(int rowNumber) throws SQLException {

		getResultSet().absolute(rowNumber);
	}

	@Override
	public final void afterLast() throws SQLException {

		getResultSet().afterLast();
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

		Object object = getResultSet().getObject(columnNumber);

		return (T) object;
	}

	@SuppressWarnings("unchecked")
	@Override
	public final <T> T getValue(String columnName) throws SQLException {

		Assertion.assertNotNull("columnName", columnName);

		if (hasColumn(columnName)) {
			throw new SQLException("the columnName is not found!!![" + columnName + "]");
		}

		Object object = getResultSet().getObject(columnName);

		return (T) object;
	}

}
