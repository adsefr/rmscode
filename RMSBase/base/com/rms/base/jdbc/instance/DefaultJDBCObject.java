package com.rms.base.jdbc.instance;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import com.rms.base.jdbc.JDBCConnection;
import com.rms.base.jdbc.JDBCUtil;
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

	private JDBCConnection getJDBCConnection() throws SQLException {

		if (isClosed()) {
			throw new SQLException("the connection has been closed!!!");
		}

		return jdbcConnection;
	}

	private void setJdbcConnection(JDBCConnection jdbcConnection) {

		this.jdbcConnection = jdbcConnection;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setDataBaseInfo(DataBaseInfo dataBaseInfo) {

		Assertion.assertNotNull("dataBaseInfo", dataBaseInfo);

		this.dataBaseInfo = dataBaseInfo;
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

			JDBCConnection jdbcConnection = JDBCFactory.newJDBCConnection();

			jdbcConnection.connection(connectionInfo);

			setJdbcConnection(jdbcConnection);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void endTransaction() throws SQLException {

		getJDBCConnection().commit();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public JDBCDataBaseMetaData getJDBCDataBaseMetaData() throws SQLException {

		return getJDBCConnection().getJDBCDataBaseMetaData();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public JDBCQueryResult query(QueryParameter queryParameter) throws SQLException {

		Assertion.assertNotNull("queryParameter", queryParameter);

		JDBCQueryResult jdbcQueryResult = getJDBCConnection().queryStatement(queryParameter);

		jdbcQueryResultMap.put(queryParameter, jdbcQueryResult);

		return jdbcQueryResult;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public JDBCUpdateResult update(UpdateParameter updateParameter) throws SQLException {

		Assertion.assertNotNull("updateParameter", updateParameter);

		JDBCUpdateResult jdbcUpdateResult = getJDBCConnection().updateStatement(updateParameter);

		jdbcUpdateResultMap.put(updateParameter, jdbcUpdateResult);

		return jdbcUpdateResult;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public JDBCSavePoint savePoint() throws SQLException {

		return getJDBCConnection().savePoint();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void commit() throws SQLException {

		getJDBCConnection().commit();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void rollback() throws SQLException {

		getJDBCConnection().rollback();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void rollback(JDBCSavePoint jdbcSavePoint) throws SQLException {

		Assertion.assertNotNull("jdbcSavePoint", jdbcSavePoint);

		getJDBCConnection().rollback(jdbcSavePoint);
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
