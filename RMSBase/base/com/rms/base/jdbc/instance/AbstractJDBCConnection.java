package com.rms.base.jdbc.instance;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rms.base.exception.UnexpectedTypeException;
import com.rms.base.jdbc.JDBCConnection;
import com.rms.base.jdbc.JDBCUtil;
import com.rms.base.jdbc.constant.HoldabilityType;
import com.rms.base.jdbc.constant.ResultSetConcurrency;
import com.rms.base.jdbc.constant.ResultSetType;
import com.rms.base.jdbc.constant.TransactionType;
import com.rms.base.jdbc.model.ConnectionInfo;
import com.rms.base.jdbc.model.QueryParameter;
import com.rms.base.jdbc.model.UpdateParameter;
import com.rms.base.logging.Logger;
import com.rms.base.util.TextUtil;
import com.rms.base.validate.Assertion;
import com.rms.common.jdbc.JDBCDataBaseMetaData;
import com.rms.common.jdbc.JDBCQueryResult;
import com.rms.common.jdbc.JDBCSavePoint;
import com.rms.common.jdbc.JDBCUpdateResult;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/02/24
 */
public class AbstractJDBCConnection implements JDBCConnection {

	protected Logger logger = Logger.getLogger(AbstractJDBCConnection.class);

	private Map<JDBCSavePoint, Savepoint> jdbcSavePointMap = new HashMap<>();

	private Connection connection;

	/**
	 *
	 * @param connection
	 * @throws SQLException
	 */
	protected AbstractJDBCConnection() {

	}

	private Connection getConnection() throws SQLException {

		if (isClosed()) {
			throw new SQLException("the connection is closed!!!");
		}

		return connection;
	}

	private void setConnection(Connection connection) throws SQLException {

		if (!isClosed()) {
			JDBCUtil.close(connection);
		}

		this.connection = connection;
	}

	@Override
	public final void connection(ConnectionInfo connectionInfo) throws SQLException {

		Assertion.assertNotNull("connectionInfo", connectionInfo);

		try {
			Class.forName(connectionInfo.getDriver());
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		}

		String url = connectionInfo.getUrl();
		String userId = connectionInfo.getUserId();
		String password = connectionInfo.getPassword();

		Connection connection = null;

		if (TextUtil.isNotBlank(url)) {
			if (TextUtil.isBlank(userId) && TextUtil.isBlank(password)) {
				connection = DriverManager.getConnection(url);
			} else if (TextUtil.isNotBlank(userId) && TextUtil.isNotBlank(password)) {
				connection = DriverManager.getConnection(url, userId, password);
			}
		} else {
			String host = connectionInfo.getHost();
			int port = connectionInfo.getPort();
			String dataBaseName = connectionInfo.getDataBaseName();
			switch (connectionInfo.getDataBaseType()) {
			case ORACLE:
				url = "jdbc:oracle:thin:@" + host + ":" + port + ":" + dataBaseName;
				break;
			case MYSQL:
				url = "jdbc:mysql://" + host + ":" + port + ":" + dataBaseName;
				break;
			case POSTGRESQL:
				url = "jdbc:postgresql://" + host + ":" + port + "/" + dataBaseName;
				break;
			default:
				throw new UnexpectedTypeException("type:" + connectionInfo.getDataBaseType().getClass().getName());
			}
			connection = DriverManager.getConnection(url, userId, password);
		}

		connection.setReadOnly(connectionInfo.isReadOnly());
		connection.setAutoCommit(connectionInfo.isAutoCommit());

		if (connectionInfo.getTransactionType() != null) {
			connection.setTransactionIsolation(connectionInfo.getTransactionType().getType());
		}

		if (connectionInfo.getHoldabilityType() != null) {
			connection.setHoldability(connectionInfo.getHoldabilityType().getType());
		}

		setConnection(connection);
	}

	@Override
	public final JDBCDataBaseMetaData getJDBCDataBaseMetaData() throws SQLException {

		DatabaseMetaData databaseMetaData = getConnection().getMetaData();
		JDBCDataBaseMetaData jdbcDataBaseMetaData = new DefaultJDBCDatabaseMetaData(databaseMetaData);

		return jdbcDataBaseMetaData;
	}

	@Override
	public final JDBCQueryResult queryStatement(QueryParameter queryParameter) throws SQLException {

		String sqlClause = queryParameter.getSqlClause();
		List<Object> parameterList = queryParameter.getParameterList();
		ResultSetType resultSetType = queryParameter.getResultSetType();
		ResultSetConcurrency resultSetConcurrency = queryParameter.getResultSetConcurrency();
		HoldabilityType holdabilityType = queryParameter.getHoldabilityType();
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = getConnection().prepareStatement(sqlClause, resultSetType.getType(), resultSetConcurrency.getType(), holdabilityType.getType());

			if (parameterList != null) {
				for (int parameterIndex = 0; parameterIndex < parameterList.size(); parameterIndex++) {
					preparedStatement.setObject(parameterIndex + 1, parameterList.get(parameterIndex));
				}
			}

			ResultSet resultSet = preparedStatement.executeQuery();

			JDBCQueryResult jdbcQueryResult = JDBCFactory.newJDBCQueryResult(resultSet, queryParameter);

			return jdbcQueryResult;
		} finally {
			JDBCUtil.close(preparedStatement);
		}
	}

	@Override
	public final JDBCUpdateResult updateStatement(UpdateParameter updateParameter) throws SQLException {

		String sqlClause = updateParameter.getSqlClause();
		List<Object> parameterList = updateParameter.getParameterList();
		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = getConnection().prepareStatement(sqlClause);

			if (parameterList != null) {
				for (int parameterIndex = 0; parameterIndex < parameterList.size(); parameterIndex++) {
					preparedStatement.setObject(parameterIndex + 1, parameterList.get(parameterIndex));
				}
			}

			preparedStatement.executeUpdate();

			JDBCUpdateResult jdbcUpdateResult = JDBCFactory.newJDBCUpdateResult();

			jdbcUpdateResult.setUpdateCount(preparedStatement.getUpdateCount());

			return jdbcUpdateResult;

		} finally {
			JDBCUtil.close(preparedStatement);
		}

	}

	@Override
	public void setAutoCommit(boolean autoCommit) throws SQLException {

		getConnection().setAutoCommit(autoCommit);
	}

	@Override
	public boolean isAutoCommit() throws SQLException {

		return getConnection().getAutoCommit();
	}

	@Override
	public void setReadOnly(boolean readOnly) throws SQLException {

		getConnection().setReadOnly(readOnly);
	}

	@Override
	public boolean isReadOnly() throws SQLException {

		return getConnection().isReadOnly();
	}

	@Override
	public void setTransactionType(TransactionType transactionType) throws SQLException {

		getConnection().setTransactionIsolation(transactionType.getType());
	}

	@Override
	public TransactionType getTransactionType() throws SQLException {

		return TransactionType.valueOf(getConnection().getTransactionIsolation());
	}

	@Override
	public void setHoldabilityType(HoldabilityType holdabilityType) throws SQLException {

		getConnection().setHoldability(holdabilityType.getType());
	}

	@Override
	public HoldabilityType getHoldabilityType() throws SQLException {

		return HoldabilityType.valueOf(getConnection().getTransactionIsolation());
	}

	@Override
	public final JDBCSavePoint savePoint() throws SQLException {

		Savepoint savepoint = getConnection().setSavepoint();

		JDBCSavePoint jdbcSavePoint = JDBCFactory.newJDBCSavePoint();
		jdbcSavePoint.setSavepointId(savepoint.getSavepointId());
		jdbcSavePoint.setSavepointName(savepoint.getSavepointName());

		jdbcSavePointMap.put(jdbcSavePoint, savepoint);

		return jdbcSavePoint;
	}

	@Override
	public final JDBCSavePoint savePoint(String savePointName) throws SQLException {

		Savepoint savepoint = getConnection().setSavepoint(savePointName);

		JDBCSavePoint jdbcSavePoint = JDBCFactory.newJDBCSavePoint();
		jdbcSavePoint.setSavepointId(savepoint.getSavepointId());
		jdbcSavePoint.setSavepointName(savepoint.getSavepointName());

		return jdbcSavePoint;
	}

	@Override
	public final void commit() throws SQLException {

		getConnection().commit();
	}

	@Override
	public final void rollback() throws SQLException {

		getConnection().rollback();
	}

	@Override
	public final void rollback(JDBCSavePoint jdbcSavePoint) throws SQLException {

		Savepoint savepoint = jdbcSavePointMap.get(jdbcSavePoint);

		getConnection().rollback(savepoint);
	}

	@Override
	public final void close() throws SQLException {

		JDBCUtil.close(getConnection());
	}

	@Override
	public final boolean isClosed() throws SQLException {

		return connection == null || connection.isClosed();
	}
}
