package com.rms.base.jdbc;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.rms.base.jdbc.implments.JDBCFactory;
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

	protected ResultSet resultSet;

	protected final JDBCQueryResultMetaData jdbcQueryResultMetaData = JDBCFactory.newJDBCQueryResultMetaData();;

	protected String errorCode;

	protected String errorMessage;

	protected AbstractJDBCQueryResult(ResultSet resultSet, QueryParameter queryParameter) throws SQLException {

		this.resultSet = resultSet;
		this.queryParameter = queryParameter;

		getResultSet();

		ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

		int columnCount = resultSetMetaData.getColumnCount();

		for (int columnNumber = 1; columnNumber <= columnCount; columnNumber++) {
			QueryResultColumnMeta queryResultColumnMeta = JDBCFactory.newQueryResultColumnMeta();
			queryResultColumnMeta.setColumnNumber(columnNumber);
			queryResultColumnMeta.setCatalogName(resultSetMetaData.getCatalogName(columnNumber));
			queryResultColumnMeta.setSchemaName(resultSetMetaData.getSchemaName(columnNumber));
			queryResultColumnMeta.setTableName(resultSetMetaData.getTableName(columnNumber));
			queryResultColumnMeta.setColumnName(resultSetMetaData.getColumnName(columnNumber));
			queryResultColumnMeta.setColumnClassName(resultSetMetaData.getColumnClassName(columnNumber));
			queryResultColumnMeta.setColumnType(resultSetMetaData.getColumnType(columnNumber));
			queryResultColumnMeta.setColumnTypeName(resultSetMetaData.getColumnTypeName(columnNumber));
			queryResultColumnMeta.setColumnLabel(resultSetMetaData.getColumnLabel(columnNumber));
			queryResultColumnMeta.setColumnDisplaySize(resultSetMetaData.getColumnDisplaySize(columnNumber));
			queryResultColumnMeta.setPrecision(resultSetMetaData.getPrecision(columnNumber));
			queryResultColumnMeta.setScale(resultSetMetaData.getScale(columnNumber));

			jdbcQueryResultMetaData.addColumnMeta(queryResultColumnMeta);
		}
	}

	protected final ResultSet getResultSet() throws SQLException {

		if (isClosed()) {
			throw new SQLException("the resultset has been closed!!!");
		}

		return resultSet;
	}

	protected JDBCRow convertCurrentResultToJDBCRow() throws SQLException {

		ResultSet resultSet = getResultSet();

		JDBCRow jdbcRow = JDBCFactory.newJDBCRow();

		for (int columnNumber = 1; columnNumber <= jdbcQueryResultMetaData.getColumnCount(); columnNumber++) {
			Object rawValue = resultSet.getObject(columnNumber);
			JDBCValue jdbcValue = JDBCFactory.newJDBCValue();
			jdbcValue.setColumnNumber(columnNumber);
			jdbcValue.setRawValue(rawValue);
			jdbcRow.addValue(columnNumber, jdbcValue);
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
