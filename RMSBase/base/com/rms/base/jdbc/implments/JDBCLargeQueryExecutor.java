package com.rms.base.jdbc.implments;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.rms.base.jdbc.JDBCConnection;
import com.rms.base.jdbc.JDBCValue;
import com.rms.base.jdbc.model.JDBCRow;
import com.rms.base.jdbc.model.QueryParameter;
import com.rms.base.validate.Assertion;

/**
 *
 * @author ri.meisei
 * @since 2014/02/24
 */
public class JDBCLargeQueryExecutor extends AbstractJDBCQueryExecutor {

	public JDBCLargeQueryExecutor(JDBCConnection jdbcConnection, QueryParameter queryParameter) {

		super(jdbcConnection, queryParameter);
	}

	public JDBCLargeQueryExecutor(ResultSet resultSet) throws SQLException {

		super(resultSet);
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

		JDBCRow jdbcRow = super.convertCurrentRowToJDBCRow();

		return jdbcRow;
	}

	@Override
	public final JDBCValue getJDBCValue(int columnNumber) throws SQLException {

		Assertion.assertNotNull("columnNumber", columnNumber);

		if (!hasColumn(columnNumber)) {
			throw new SQLException("the columnNumber[" + columnNumber + "] is not found!!!");
		}

		Object object = getResultSet().getObject(columnNumber);

		JDBCValue jdbcValue = new JDBCValue(object);

		return jdbcValue;
	}

	@Override
	public final JDBCValue getJDBCValue(String columnName) throws SQLException {

		Assertion.assertNotNull("columnName", columnName);

		if (!hasColumn(columnName)) {
			throw new SQLException("the columnName[" + columnName + "] is not found!!!");
		}

		Object object = getResultSet().getObject(columnName);

		JDBCValue jdbcValue = new JDBCValue(object);

		return jdbcValue;
	}
}
