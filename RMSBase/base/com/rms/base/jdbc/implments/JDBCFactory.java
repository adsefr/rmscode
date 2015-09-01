package com.rms.base.jdbc.implments;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.rms.base.jdbc.JDBCConnection;
import com.rms.base.jdbc.JDBCValue;
import com.rms.base.jdbc.model.CatalogMeta;
import com.rms.base.jdbc.model.ColumnMeta;
import com.rms.base.jdbc.model.JDBCRow;
import com.rms.base.jdbc.model.QueryParameter;
import com.rms.base.jdbc.model.QueryResultColumnMeta;
import com.rms.base.jdbc.model.SchemaMeta;
import com.rms.base.jdbc.model.TableMeta;
import com.rms.common.jdbc.JDBCDataBaseMetaData;
import com.rms.common.jdbc.JDBCQueryResult;
import com.rms.common.jdbc.JDBCQueryResultMetaData;
import com.rms.common.jdbc.JDBCSavePoint;
import com.rms.common.jdbc.JDBCUpdateResult;

public class JDBCFactory {

	public static JDBCConnection newJDBCConnection() {

		JDBCConnection jdbcConnection = new DefaultJDBCConnection();

		return jdbcConnection;
	}

	public static JDBCDataBaseMetaData newJDBCDataBaseMetaData(DatabaseMetaData databaseMetaData) throws SQLException {

		DefaultJDBCDatabaseMetaData defaultJDBCDatabaseMetaData = new DefaultJDBCDatabaseMetaData(databaseMetaData);

		return defaultJDBCDatabaseMetaData;
	}

	public static JDBCQueryResultMetaData newJDBCQueryResultMetaData() {

		DefaultJDBCQueryResultMetaData defaultJDBCQueryResultMetaData = new DefaultJDBCQueryResultMetaData();

		return defaultJDBCQueryResultMetaData;
	}

	public static JDBCQueryResult newJDBCQueryResult(ResultSet resultSet) throws SQLException {

		JDBCQueryResult jdbcQueryResult = new DefaultJDBCQueryResult(resultSet, null);

		return jdbcQueryResult;
	}

	public static JDBCQueryResult newJDBCQueryResult(ResultSet resultSet, QueryParameter queryParameter) throws SQLException {

		JDBCQueryResult jdbcQueryResult = new DefaultJDBCQueryResult(resultSet, queryParameter);

		return jdbcQueryResult;
	}

	public static JDBCUpdateResult newJDBCUpdateResult() {

		JDBCUpdateResult jdbcUpdateResult = new DefaultJDBCUpdateResult();

		return jdbcUpdateResult;
	}

	public static JDBCRow newJDBCRow() {

		DefaultJDBCRow defaultJDBCRow = new DefaultJDBCRow();

		return defaultJDBCRow;
	}

	public static JDBCValue newJDBCValue() {

		DefaultJDBCValue defaultJDBCValue = new DefaultJDBCValue();

		return defaultJDBCValue;
	}

	public static JDBCSavePoint newJDBCSavePoint() {

		JDBCSavePoint jdbcSavePoint = new DefaultJDBCSavePoint();

		return jdbcSavePoint;
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
