package com.rms.base.jdbc.implments;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.rms.base.jdbc.JDBCConnection;
import com.rms.base.jdbc.JDBCQueryResultMetaData;
import com.rms.base.jdbc.JDBCValue;
import com.rms.base.jdbc.model.CatalogMeta;
import com.rms.base.jdbc.model.ColumnMeta;
import com.rms.base.jdbc.model.JDBCColumn;
import com.rms.base.jdbc.model.JDBCRow;
import com.rms.base.jdbc.model.QueryParameter;
import com.rms.base.jdbc.model.QueryResultColumnMeta;
import com.rms.base.jdbc.model.SchemaMeta;
import com.rms.base.jdbc.model.TableMeta;
import com.rms.base.jdbc.model.UpdateParameter;
import com.rms.common.jdbc.JDBCDataBaseMetaData;
import com.rms.common.jdbc.JDBCQueryExecutor;
import com.rms.common.jdbc.JDBCUpdateExecutor;

public class JDBCFactory {

	public static JDBCConnection newJDBCConnection() {

		JDBCConnection jdbcConnection = new DefaultJDBCConnection();

		return jdbcConnection;
	}

	public static JDBCDataBaseMetaData newJDBCDataBaseMetaData(DatabaseMetaData databaseMetaData) throws SQLException {

		DefaultJDBCDatabaseMetaData defaultJDBCDatabaseMetaData = new DefaultJDBCDatabaseMetaData(databaseMetaData);

		return defaultJDBCDatabaseMetaData;
	}

	public static JDBCQueryResultMetaData newJDBCQueryResultMetaData(ResultSetMetaData resultSetMetaData) throws SQLException {

		DefaultJDBCQueryResultMetaData defaultJDBCQueryResultMetaData = new DefaultJDBCQueryResultMetaData(resultSetMetaData);

		return defaultJDBCQueryResultMetaData;
	}

	public static JDBCQueryExecutor newJDBCQueryExecutor(JDBCConnection jdbcConnection, QueryParameter queryParameter) {

		JDBCQueryExecutor jdbcQueryExecutor = AbstractJDBCQueryExecutor.newInstance(jdbcConnection, queryParameter);

		return jdbcQueryExecutor;
	}

	public static JDBCQueryExecutor newJDBCQueryExecutor(ResultSet resultSet) throws SQLException {

		JDBCQueryExecutor jdbcQueryExecutor = AbstractJDBCQueryExecutor.newInstance(resultSet);
		resultSet.getStatement().close();
		return jdbcQueryExecutor;
	}

	public static JDBCUpdateExecutor newJDBCUpdateExecutor(JDBCConnection jdbcConnection, UpdateParameter updateParameter) {

		JDBCUpdateExecutor jdbcUpdateExecutor = new DefaultJDBCUpdateExecutor(jdbcConnection, updateParameter);

		return jdbcUpdateExecutor;
	}

	public static JDBCRow newJDBCRow() {

		DefaultJDBCRow defaultJDBCRow = new DefaultJDBCRow();

		return defaultJDBCRow;
	}

	public static JDBCColumn newJDBCColumn(QueryResultColumnMeta QueryExecutorColumnMeta, Object rawValue) {

		DefaultJDBCColumn defaultJDBCColumn = new DefaultJDBCColumn(QueryExecutorColumnMeta, rawValue);

		return defaultJDBCColumn;
	}

	public static JDBCValue newJDBCValue(Object rawValue) {

		DefaultJDBCValue defaultJDBCValue = new DefaultJDBCValue(rawValue);

		return defaultJDBCValue;
	}

	public static CatalogMeta newCatalogMeta() {

		return new DefaultCatalogMeta();
	}

	public static SchemaMeta newSchemaMeta() {

		return new DefaultSchemaMeta();
	}

	public static TableMeta newTableMeta() {

		return new DefaultTableMeta();
	}

	public static ColumnMeta newColumnMeta() {

		return new DefaultColumnMeta();
	}

	public static QueryResultColumnMeta newQueryResultColumnMeta() {

		return new DefaultQueryResultColumnMeta();
	}
}
