package com.rms.base.jdbc.instance;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.rms.base.jdbc.JDBCConnection;
import com.rms.base.jdbc.model.CatalogMeta;
import com.rms.base.jdbc.model.ColumnMeta;
import com.rms.base.jdbc.model.QueryParameter;
import com.rms.base.jdbc.model.SchemaMeta;
import com.rms.base.jdbc.model.TableMeta;
import com.rms.common.jdbc.JDBCQueryResult;
import com.rms.common.jdbc.JDBCSavePoint;

public class JDBCFactory {

	public static JDBCConnection newJDBCConnection() {

		JDBCConnection jdbcConnection = new DefaultJDBCConnection();

		return jdbcConnection;
	}

	public static JDBCQueryResult newJDBCQueryResult(ResultSet resultSet) throws SQLException {

		JDBCQueryResult jdbcQueryResult = new DefaultJDBCQueryResult(resultSet, null);

		return jdbcQueryResult;
	}

	public static JDBCQueryResult newJDBCQueryResult(ResultSet resultSet, QueryParameter queryParameter) throws SQLException {

		JDBCQueryResult jdbcQueryResult = new DefaultJDBCQueryResult(resultSet, queryParameter);

		return jdbcQueryResult;
	}

	static JDBCSavePoint newJDBCSavePoint() {

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
}
