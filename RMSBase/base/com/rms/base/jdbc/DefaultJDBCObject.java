package com.rms.base.jdbc;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import com.rms.base.jdbc.model.ConnectionInfo;
import com.rms.base.jdbc.model.DataBaseInfo;
import com.rms.base.jdbc.model.QueryParameter;
import com.rms.base.jdbc.model.UpdateParameter;
import com.rms.base.validate.Assertion;
import com.rms.common.jdbc.JDBCDataBaseMetaData;
import com.rms.common.jdbc.JDBCObject;
import com.rms.common.jdbc.JDBCQueryResult;
import com.rms.common.jdbc.JDBCSavePoint;
import com.rms.common.jdbc.JDBCUpdateResult;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/02/24
 */
class DefaultJDBCObject extends JDBCObject {

	private DataBaseInfo dataBaseInfo;

	private JDBCConnection jdbcConnection;

	private Map<QueryParameter, JDBCQueryResult> jdbcQueryResultMap = new LinkedHashMap<>();

	private Map<UpdateParameter, JDBCUpdateResult> jdbcUpdateResultMap = new LinkedHashMap<>();

	public DefaultJDBCObject(DataBaseInfo dataBaseInfo) {

		Assertion.assertNotNull("dataBaseInfo", dataBaseInfo);

		this.dataBaseInfo = dataBaseInfo;
	}

	private void checkConnection() throws SQLException {

		if (isClosed()) {
			throw new SQLException("the connection has been closed!!!");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void startTransaction() throws SQLException {

		if (isClosed()) {
			ConnectionInfo connectionInfo = new ConnectionInfo();
			connectionInfo.setDriver(dataBaseInfo.getDriver());
			connectionInfo.setUrl(dataBaseInfo.getUrl());
			connectionInfo.setHost(dataBaseInfo.getHost());
			connectionInfo.setUserId(dataBaseInfo.getUserId());
			connectionInfo.setPassword(dataBaseInfo.getPassword());
			connectionInfo.setPort(dataBaseInfo.getPort());
			connectionInfo.setDataBaseType(dataBaseInfo.getDataBaseType());
			connectionInfo.setDataBaseName(dataBaseInfo.getDataBaseName());
			connectionInfo.setAutoCommit(dataBaseInfo.isAutoCommit());
			connectionInfo.setReadOnly(dataBaseInfo.isReadOnly());
			connectionInfo.setTransactionType(dataBaseInfo.getTransactionType());
			connectionInfo.setHoldabilityType(dataBaseInfo.getHoldabilityType());

			jdbcConnection = JDBCUtil.getConnection(connectionInfo);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void endTransaction() throws SQLException {

		jdbcConnection.commit();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public JDBCDataBaseMetaData getJDBCDataBaseMetaData() throws SQLException {

		checkConnection();

		return JDBCUtil.getJDBCDataBaseMetaData(jdbcConnection);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public JDBCQueryResult query(QueryParameter queryParameter) throws SQLException {

		checkConnection();

		Assertion.assertNotNull("queryParameter", queryParameter);

		JDBCQueryResult jdbcQueryResult = JDBCUtil.queryStatement(jdbcConnection, queryParameter);

		jdbcQueryResultMap.put(queryParameter, jdbcQueryResult);

		return jdbcQueryResult;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public JDBCUpdateResult update(UpdateParameter updateParameter) throws SQLException {

		checkConnection();

		Assertion.assertNotNull("updateParameter", updateParameter);

		JDBCUpdateResult jdbcUpdateResult = JDBCUtil.updateStatement(jdbcConnection, updateParameter);

		jdbcUpdateResultMap.put(updateParameter, jdbcUpdateResult);

		return jdbcUpdateResult;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public JDBCSavePoint savePoint() throws SQLException {

		checkConnection();

		return jdbcConnection.savePoint();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void commit() throws SQLException {

		checkConnection();

		if (jdbcConnection != null) {
			jdbcConnection.commit();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void rollback() throws SQLException {

		checkConnection();

		jdbcConnection.rollback();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void rollback(JDBCSavePoint jdbcSavePoint) throws SQLException {

		checkConnection();

		jdbcConnection.rollback(jdbcSavePoint);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void close() throws SQLException {

		JDBCUtil.close(jdbcConnection);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isClosed() throws SQLException {

		return (jdbcConnection == null || jdbcConnection.isClosed());
	}
}
