package com.rms.base.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Types;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import com.rms.base.exception.UnexpectedDataException;
import com.rms.base.exception.UnexpectedTypeException;
import com.rms.base.jdbc.constant.HoldabilityType;
import com.rms.base.jdbc.constant.ResultSetConcurrency;
import com.rms.base.jdbc.constant.ResultSetType;
import com.rms.base.jdbc.model.CatalogMeta;
import com.rms.base.jdbc.model.ColumnMeta;
import com.rms.base.jdbc.model.ConnectionInfo;
import com.rms.base.jdbc.model.DefaultCatalogMeta;
import com.rms.base.jdbc.model.DefaultColumnMeta;
import com.rms.base.jdbc.model.DefaultSchemaMeta;
import com.rms.base.jdbc.model.DefaultTableMeta;
import com.rms.base.jdbc.model.QueryParameter;
import com.rms.base.jdbc.model.SchemaMeta;
import com.rms.base.jdbc.model.TableMeta;
import com.rms.base.jdbc.model.UpdateParameter;
import com.rms.base.util.TextUtil;
import com.rms.base.validate.Assertion;
import com.rms.common.jdbc.JDBCCloseable;
import com.rms.common.jdbc.JDBCDataBaseMetaData;
import com.rms.common.jdbc.JDBCQueryResult;
import com.rms.common.jdbc.JDBCSavePoint;
import com.rms.common.jdbc.JDBCUpdateResult;

class JDBCUtil {

	static String generateID() {

		return UUID.randomUUID().toString();
	}

	static DefaultJDBCConnection getConnection(ConnectionInfo connectionInfo) throws SQLException {

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

		DefaultJDBCConnection defaultJDBCConnection = new DefaultJDBCConnection(connection);

		return defaultJDBCConnection;
	}

	// final static Connection getConnection(JDBCConnection jdbcConnection) {
	//
	// if (jdbcConnection instanceof DefaultJDBCConnection) {
	// DefaultJDBCConnection defaultJDBCConnection = (DefaultJDBCConnection)
	// jdbcConnection;
	// return defaultJDBCConnection.getConnection();
	// }
	//
	// return null;
	// }

	final static JDBCDataBaseMetaData getJDBCDataBaseMetaData(JDBCConnection jdbcConnection) throws SQLException {

		JDBCDataBaseMetaData jdbcDataBaseMetaData = null;

		if (jdbcConnection instanceof DefaultJDBCConnection) {
			DefaultJDBCConnection defaultJDBCConnection = (DefaultJDBCConnection) jdbcConnection;
			jdbcDataBaseMetaData = defaultJDBCConnection.getJDBCDataBaseMetaData();
		}

		return jdbcDataBaseMetaData;
	}

	final static JDBCQueryResult queryStatement(JDBCConnection jdbcConnection, QueryParameter queryParameter) throws SQLException {

		Connection connection = ((DefaultJDBCConnection) jdbcConnection).getConnection();

		PreparedStatement preparedStatement = connection.prepareStatement(queryParameter.getSqlClause());
		String sqlClause = queryParameter.getSqlClause();
		List<Object> parameterList = queryParameter.getParameterList();
		ResultSetType resultSetType = queryParameter.getResultSetType();
		ResultSetConcurrency resultSetConcurrency = queryParameter.getResultSetConcurrency();
		HoldabilityType holdabilityType = queryParameter.getHoldabilityType();
		preparedStatement = connection.prepareStatement(sqlClause, resultSetType.getType(), resultSetConcurrency.getType(), holdabilityType.getType());

		if (parameterList != null) {
			for (int parameterIndex = 0; parameterIndex < parameterList.size(); parameterIndex++) {
				preparedStatement.setObject(parameterIndex + 1, parameterList.get(parameterIndex));
			}
		}

		ResultSet resultSet = preparedStatement.executeQuery();

		JDBCQueryResult jdbcQueryResult = new DefaultJDBCQueryResult(resultSet, queryParameter);

		JDBCUtil.close(preparedStatement);

		return jdbcQueryResult;
	}

	final static JDBCUpdateResult updateStatement(JDBCConnection jdbcConnection, UpdateParameter updateParameter) throws SQLException {

		Connection connection = ((DefaultJDBCConnection) jdbcConnection).getConnection();

		String sqlClause = updateParameter.getSqlClause();
		List<Object> parameterList = updateParameter.getParameterList();
		PreparedStatement preparedStatement = connection.prepareStatement(sqlClause);

		if (parameterList != null) {
			for (int parameterIndex = 0; parameterIndex < parameterList.size(); parameterIndex++) {
				preparedStatement.setObject(parameterIndex + 1, parameterList.get(parameterIndex));
			}
		}

		preparedStatement.executeUpdate();

		DefaultJDBCUpdateResult jdbcUpdateResult = new DefaultJDBCUpdateResult();

		jdbcUpdateResult.setUpdateCount(preparedStatement.getUpdateCount());

		JDBCUtil.close(preparedStatement);

		return jdbcUpdateResult;
	}

	static Object getData(ResultSet resultSet, String fieldName, int dataType) throws SQLException {

		switch (dataType) {
		case Types.CHAR:
		case Types.VARCHAR:
			return resultSet.getString(fieldName);
		case Types.SMALLINT:
		case Types.BIGINT:
		case Types.INTEGER:
			return resultSet.getInt(fieldName);
		case Types.TIMESTAMP:
			return resultSet.getTimestamp(fieldName);
		default:
			throw new UnexpectedDataException("type:" + dataType);
		}
	}

	static void close(AutoCloseable autoCloseable) {

		try {
			if (autoCloseable != null) {
				autoCloseable.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void close(Collection<? extends JDBCCloseable> jdbcCloseableCollection) {

		for (Iterator<? extends JDBCCloseable> iterator = jdbcCloseableCollection.iterator(); iterator.hasNext();) {
			JDBCCloseable jdbcCloseable = iterator.next();
			close(jdbcCloseable);
		}
	}

	static JDBCQueryResult getJDBCQueryResult(ResultSet resultSet) throws SQLException {

		JDBCQueryResult jdbcQueryResult = new DefaultJDBCQueryResult(resultSet, null);

		return jdbcQueryResult;
	}

	static JDBCSavePoint getJDBCSavePoint(Savepoint savepoint) {

		JDBCSavePoint jdbcSavePoint = new DefaultJDBCSavePoint(savepoint);

		return jdbcSavePoint;
	}

	static CatalogMeta getCatalogMeta() {

		return new DefaultCatalogMeta();

	}

	static SchemaMeta getSchemaMeta() {

		return new DefaultSchemaMeta();

	}

	static TableMeta getTableMeta() {

		return new DefaultTableMeta();

	}

	static ColumnMeta getColumnMeta() {

		return new DefaultColumnMeta();

	}

}
