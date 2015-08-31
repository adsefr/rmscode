package com.rms.base.jdbc.abstractclass;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.rms.base.jdbc.JDBCUtil;
import com.rms.base.jdbc.implments.JDBCFactory;
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

	protected final JDBCQueryResultMetaData jdbcQueryResultMetaData;

	protected final Integer resultCount;

	protected String errorCode;

	protected String errorMessage;

	protected AbstractJDBCQueryResult(ResultSet resultSet, QueryParameter queryParameter) throws SQLException {

		this.resultSet = resultSet;
		this.queryParameter = queryParameter;

		getResultSet();

		resultCount = resultSet.getFetchSize();

		ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

		jdbcQueryResultMetaData = JDBCFactory.newJDBCQueryResultMetaData(resultSetMetaData.getColumnCount());

		for (int columnNumber = 1; columnNumber < resultSetMetaData.getColumnCount(); columnNumber++) {
			QueryResultColumnMeta queryResultColumnMeta = JDBCFactory.newQueryResultColumnMeta();

			jdbcQueryResultMetaData.addColumnMeta(queryResultColumnMeta);
		}
	}

	protected final ResultSet getResultSet() throws SQLException {

		if (isClosed()) {
			throw new SQLException("the resultset has been closed!!!");
		}

		return resultSet;
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
	 * @return resultCount
	 */
	@Override
	public final Integer getResultCount() {

		return resultCount;
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

		return (columnNumber >= 1 && columnNumber <= resultCount);
	}

	@Override
	public final boolean hasColumn(String columnName) throws SQLException {

		Assertion.assertNotBlank("columnName", columnName);

		return jdbcQueryResultMetaData.hasColumn(columnName.toUpperCase());
	}

}
