package com.rms.base.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.JDBCType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;
import java.util.UUID;

import com.rms.base.exception.UnexpectedDataException;
import com.rms.base.exception.UnexpectedTypeException;
import com.rms.base.jdbc.model.QueryParameter;
import com.rms.base.jdbc.model.UpdateParameter;
import com.rms.base.logging.Logger;
import com.rms.base.validate.Assertion;

public class JDBCUtil {

	private final static Logger logger = Logger.getLogger("com.rms.jdbc.resource");

	static String generateID() {

		return UUID.randomUUID().toString();
	}

	public static boolean isNull(JDBCValue jdbcValue) {

		return jdbcValue == null || jdbcValue.isNull();
	}

	public static boolean isNotNull(JDBCValue jdbcValue) {

		return jdbcValue != null && jdbcValue.isNotNull();
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

	public static Object getValue(ResultSet resultSet, int columnNumber, JDBCType jdbcType) throws SQLException {

		Assertion.assertNotNull("jdbcType", jdbcType);

		switch (jdbcType) {
			case CHAR:
				// CHAR(Types.CHAR),
				return resultSet.getString(columnNumber);
			case VARCHAR:
				// VARCHAR(Types.VARCHAR),
				return resultSet.getString(columnNumber);
			case LONGVARCHAR:
				// LONGVARCHAR(Types.LONGVARCHAR),
				return resultSet.getAsciiStream(columnNumber);
			case NCHAR:
				// NCHAR(Types.NCHAR),
				return resultSet.getString(columnNumber);
			case NVARCHAR:
				// NVARCHAR(Types.NVARCHAR),
				return resultSet.getString(columnNumber);
			case LONGNVARCHAR:
				// LONGNVARCHAR(Types.LONGNVARCHAR),
				return resultSet.getCharacterStream(columnNumber);

			case TINYINT:
				// TINYINT(Types.TINYINT),
				return resultSet.getByte(columnNumber);
			case SMALLINT:
				// SMALLINT(Types.SMALLINT),
				return resultSet.getShort(columnNumber);
			case INTEGER:
				// INTEGER(Types.INTEGER),
				return resultSet.getInt(columnNumber);
			case BIGINT:
				// BIGINT(Types.BIGINT),
				return resultSet.getLong(columnNumber);
			case REAL:
				// REAL(Types.REAL),
				return resultSet.getFloat(columnNumber);
			case FLOAT:
				// FLOAT(Types.FLOAT),
				return resultSet.getDouble(columnNumber);
			case DOUBLE:
				// DOUBLE(Types.DOUBLE),
				return resultSet.getDouble(columnNumber);
			case NUMERIC:
				// NUMERIC(Types.NUMERIC),
				return resultSet.getBigDecimal(columnNumber);
			case DECIMAL:
				// DECIMAL(Types.DECIMAL),
				return resultSet.getBigDecimal(columnNumber);

			case BIT:
				// BIT(Types.BIT),
				return resultSet.getBigDecimal(columnNumber);
			case BOOLEAN:
				// BOOLEAN(Types.BOOLEAN),
				return resultSet.getBoolean(columnNumber);

			case BINARY:
				// BINARY(Types.BINARY),
				return resultSet.getBytes(columnNumber);
			case VARBINARY:
				// VARBINARY(Types.VARBINARY),
				return resultSet.getBytes(columnNumber);
			case LONGVARBINARY:
				// LONGVARBINARY(Types.LONGVARBINARY),
				return resultSet.getBinaryStream(columnNumber);

			case TIME:
				// TIME(Types.TIME),
				return resultSet.getTime(columnNumber);

			case DATE:
				// DATE(Types.DATE),
				return resultSet.getDate(columnNumber);

			case TIMESTAMP:
				// TIMESTAMP(Types.TIMESTAMP),
				return resultSet.getTimestamp(columnNumber);

			case BLOB:
				// BLOB(Types.BLOB),
				return resultSet.getBlob(columnNumber);

			case CLOB:
				// CLOB(Types.CLOB),
				return resultSet.getClob(columnNumber);

			case NCLOB:
				// NCLOB(Types.NCLOB),
				return resultSet.getNClob(columnNumber);

			case ARRAY:
				// ARRAY(Types.ARRAY),
				return resultSet.getArray(columnNumber);
			case REF:
				// REF(Types.REF),
				return resultSet.getRef(columnNumber);

			case ROWID:
				// ROWID(Types.ROWID),
				return resultSet.getRowId(columnNumber);

			case SQLXML:
				// SQLXML(Types.SQLXML),
				return resultSet.getSQLXML(columnNumber);

			case STRUCT:
			case DATALINK:
			case NULL:
			case OTHER:
			case JAVA_OBJECT:
			case DISTINCT:
			case REF_CURSOR:
			case TIME_WITH_TIMEZONE:
			case TIMESTAMP_WITH_TIMEZONE:
				return resultSet.getObject(columnNumber);

			default:
				throw new UnexpectedTypeException(jdbcType.getClass().getName());
		}
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
