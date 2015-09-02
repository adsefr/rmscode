package com.rms.base.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.rms.base.jdbc.implments.JDBCFactory;
import com.rms.base.jdbc.model.JDBCColumn;
import com.rms.base.jdbc.model.JDBCRow;
import com.rms.base.jdbc.model.QueryParameter;
import com.rms.base.jdbc.model.QueryResultColumnMeta;
import com.rms.base.validate.Assertion;
import com.rms.common.jdbc.JDBCQueryResult;
import com.rms.common.jdbc.JDBCQueryResultMetaData;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/02/24
 */
public abstract class AbstractJDBCQueryResult implements JDBCQueryResult {

	protected QueryParameter queryParameter;

	private ResultSet resultSet;

	protected final JDBCQueryResultMetaData jdbcQueryResultMetaData;

	protected String errorCode;

	protected String errorMessage;

	protected AbstractJDBCQueryResult(ResultSet resultSet, QueryParameter queryParameter) throws SQLException {

		this.resultSet = resultSet;
		this.queryParameter = queryParameter;

		jdbcQueryResultMetaData = JDBCFactory.newJDBCQueryResultMetaData(getResultSet().getMetaData());
	}

	protected final ResultSet getResultSet() throws SQLException {

		if (isClosed()) {
			throw new SQLException("the resultset has been closed!!!");
		}

		return resultSet;
	}

	protected JDBCRow convertToJDBCRow(ResultSet resultSet) throws SQLException {

		int columnCount = jdbcQueryResultMetaData.getColumnCount();

		JDBCRow jdbcRow = JDBCFactory.newJDBCRow();
		jdbcRow.setRowNumber(resultSet.getRow());

		for (int columnNumber = 1; columnNumber <= columnCount; columnNumber++) {
			QueryResultColumnMeta queryResultColumnMeta = jdbcQueryResultMetaData.getColumnMeta(columnNumber);
			Object rawValue = resultSet.getObject(columnNumber);
			JDBCColumn jdbcColumn = JDBCFactory.newJDBCColumn(queryResultColumnMeta, rawValue);
			jdbcRow.addColumn(jdbcColumn);
		}

		return jdbcRow;
	}

	@Override
	public final void close() throws SQLException {

		JDBCUtil.close(resultSet);

		resultSet = null;
	}

	@Override
	public final boolean isClosed() throws SQLException {

		return resultSet == null || resultSet.isClosed();
	}

	@Override
	public final QueryParameter getQueryParameter() {

		return queryParameter;
	}

	/**
	 * @return errorCode
	 */
	@Override
	public final String getResultCode() {

		return errorCode;
	}

	/**
	 * @return errorMessage
	 */
	@Override
	public final String getErrorMessage() {

		return errorMessage;
	}

	@Override
	public boolean hasColumn(int columnNumber) throws SQLException {

		return (columnNumber >= 1 && columnNumber <= jdbcQueryResultMetaData.getColumnCount());
	}

	@Override
	public final boolean hasColumn(String columnName) throws SQLException {

		Assertion.assertNotBlank("columnName", columnName);

		return jdbcQueryResultMetaData.hasColumn(columnName);
	}

}
