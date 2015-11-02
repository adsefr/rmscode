package com.rms.base.jdbc.implments;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.rms.base.jdbc.JDBCConnection;
import com.rms.base.jdbc.model.JDBCRow;
import com.rms.base.jdbc.model.JDBCValue;
import com.rms.base.jdbc.model.QueryParameter;
import com.rms.base.logging.Logger;
import com.rms.base.validate.Assertion;

/**
 *
 * @author ri.meisei
 * @since 2014/02/24
 */
public class DefaultJDBCQueryExecutor extends AbstractJDBCQueryExecutor {

	private final static Logger LOGGER = Logger.getLogger(DefaultJDBCQueryExecutor.class);

	private Integer currentRowNumber = 0;

	public DefaultJDBCQueryExecutor(JDBCConnection jdbcConnection, QueryParameter queryParameter) {

		super(jdbcConnection, queryParameter);
	}

	public DefaultJDBCQueryExecutor(ResultSet resultSet) throws SQLException {

		super(resultSet);
	}

	@Override
	public final void beforeFirst() throws SQLException {

		currentRowNumber = 0;
	}

	@Override
	public final void absolute(int rowNumber) throws SQLException {

		int _rowNumber = Math.abs(rowNumber);
		int mod = _rowNumber % jdbcRowCollection.size();

		if (rowNumber > 0) {
			currentRowNumber = mod;
		} else if (rowNumber < 0) {
			currentRowNumber = jdbcRowCollection.size() - mod;
		} else {
			currentRowNumber = 0;
		}
	}

	@Override
	public final void afterLast() throws SQLException {

		currentRowNumber = jdbcRowCollection.size() + 1;
	}

	@Override
	public final boolean hasNext() throws SQLException {

		return (++currentRowNumber < jdbcRowCollection.size());
	}

	@Override
	public final JDBCRow getRow() throws SQLException {

		return jdbcRowCollection.get(currentRowNumber);
	}

	@SuppressWarnings("unchecked")
	@Override
	public final <T> T getValue(int columnNumber) throws SQLException {

		Assertion.assertNotNull("columnNumber", columnNumber);

		if (!hasColumn(columnNumber)) {
			throw new SQLException("the columnNumber[" + columnNumber + "] is not found!!!");
		}

		Object object = jdbcRowCollection.get(currentRowNumber).getValue(columnNumber);

		return (T) object;
	}

	@SuppressWarnings("unchecked")
	@Override
	public final <T> T getValue(String columnName) throws SQLException {

		Assertion.assertNotNull("columnName", columnName);

		if (!hasColumn(columnName)) {
			throw new SQLException("the columnName[" + columnName + "] is not found!!!");
		}

		JDBCRow jdbcRow = jdbcRowCollection.get(currentRowNumber);

		Object object = jdbcRow.getValue(columnName);

		return (T) object;
	}

	@Override
	public final JDBCValue getJDBCValue(int columnNumber) throws SQLException {

		Assertion.assertNotNull("columnNumber", columnNumber);

		if (!hasColumn(columnNumber)) {
			// throw new SQLException("the columnNumber[" + columnNumber + "] is not found!!!");
			logger.trace("the columnNumber[" + columnNumber + "] is not found!!!");
			return JDBCValue.newJDBCValue(null);
		}

		JDBCValue jdbcValue = jdbcRowCollection.get(currentRowNumber).getJDBCValue(columnNumber);

		return jdbcValue;
	}

	@Override
	public final JDBCValue getJDBCValue(String columnName) throws SQLException {

		Assertion.assertNotNull("columnName", columnName);

		if (!hasColumn(columnName)) {
			// throw new SQLException("the columnName[" + columnName + "] is not found!!!");
			logger.trace("the columnName[" + columnName + "] is not found!!!");
			return JDBCValue.newJDBCValue(null);
		}

		JDBCRow jdbcRow = jdbcRowCollection.get(currentRowNumber);

		JDBCValue jdbcValue = jdbcRow.getJDBCValue(columnName);

		return jdbcValue;
	}
}
