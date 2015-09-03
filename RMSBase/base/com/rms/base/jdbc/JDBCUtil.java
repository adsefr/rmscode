package com.rms.base.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;
import java.util.UUID;

import com.rms.base.exception.UnexpectedDataException;
import com.rms.base.jdbc.model.QueryParameter;
import com.rms.base.jdbc.model.UpdateParameter;
import com.rms.base.logging.Logger;

public class JDBCUtil {

	private final static Logger logger = Logger.getLogger("com.rms.jdbc.resource");

	static String generateID() {

		return UUID.randomUUID().toString();
	}

	public static Object getData(ResultSet resultSet, String fieldName, int dataType) throws SQLException {

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

	public static Connection getConnection(String url, String userId, String password) throws SQLException {

		Connection connection = DriverManager.getConnection(url, userId, password);

		logger.trace("it is succeeded to establish a connection." + connection.toString());

		return connection;
	}

	public static PreparedStatement queryStatement(JDBCConnection jdbcConnection, QueryParameter queryParameter) throws SQLException {

		String sqlClause = queryParameter.getSqlClause();
		List<Object> parameterList = queryParameter.getParameterList();
		int resultSetType = queryParameter.getResultSetType().getType();
		int resultSetConcurrency = queryParameter.getResultSetConcurrency().getType();
		int holdabilityType = queryParameter.getHoldabilityType().getType();

		PreparedStatement preparedStatement = null;
		preparedStatement = jdbcConnection.getConnection().prepareStatement(sqlClause, resultSetType, resultSetConcurrency, holdabilityType);
		logger.trace("it is success to create statement." + preparedStatement.toString());

		if (parameterList != null) {
			for (int parameterIndex = 0; parameterIndex < parameterList.size(); parameterIndex++) {
				preparedStatement.setObject(parameterIndex + 1, parameterList.get(parameterIndex));
			}
		}

		return preparedStatement;
	}

	public static PreparedStatement updateStatement(JDBCConnection jdbcConnection, UpdateParameter updateParameter) throws SQLException {

		String sqlClause = updateParameter.getSqlClause();
		List<Object> parameterList = updateParameter.getParameterList();

		PreparedStatement preparedStatement = null;

		preparedStatement = jdbcConnection.getConnection().prepareStatement(sqlClause);
		logger.trace("it is success to create statement." + preparedStatement.toString());

		if (parameterList != null) {
			for (int parameterIndex = 0; parameterIndex < parameterList.size(); parameterIndex++) {
				preparedStatement.setObject(parameterIndex + 1, parameterList.get(parameterIndex));
			}
		}

		return preparedStatement;
	}

	public static void close(Connection connection) {

		try {
			if (connection != null && !connection.isClosed()) {
				connection.close();
				logger.trace("it is success to close connection." + connection.toString());
			}
		} catch (SQLException e) {
			logger.error(e);
		}
	}

	public static void close(Statement statement) {

		try {
			if (statement != null && !statement.isClosed()) {
				statement.close();
				logger.trace("it is success to close statement." + statement.toString());
			}
		} catch (SQLException e) {
			logger.error(e);
		}
	}

	public static void close(ResultSet resultSet) {

		try {
			if (resultSet != null && !resultSet.isClosed()) {
				resultSet.close();
				logger.trace("it is success to close resultSet." + resultSet.toString());
			}
		} catch (SQLException e) {
			logger.error(e);
		}
	}

	public static boolean isClosed(Connection connection) throws SQLException {

		return (connection == null || connection.isClosed());
	}

	public static boolean isClosed(Statement statement) throws SQLException {

		return (statement == null || statement.isClosed());
	}

	public static boolean isClosed(ResultSet resultSet) throws SQLException {

		return (resultSet == null || resultSet.isClosed());
	}
}
