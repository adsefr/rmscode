package com.rms.tool.jdbc;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.rms.base.jdbc.model.DataBaseInfo;
import com.rms.base.jdbc.model.SchemaMeta;
import com.rms.base.jdbc.model.TableMeta;
import com.rms.base.validate.Assertion;
import com.rms.common.jdbc.JDBCDataBaseMetaData;
import com.rms.common.jdbc.JDBCObject;

public class JDBCOperator {

	public static List<String> getSchemas(DataBaseInfo dataBaseInfo) {

		Assertion.assertNotNull("dataBaseInfo", dataBaseInfo);

		return null;
	}

	public static Map<String, List<String>> getAllTableNameList(DataBaseInfo dataBaseInfo) throws SQLException {

		Assertion.assertNotNull("dataBaseInfo", dataBaseInfo);

		JDBCObject jdbcObject = JDBCObject.getInstance(dataBaseInfo);
		jdbcObject.startTransaction();
		JDBCDataBaseMetaData jdbcDataBaseMetaData = jdbcObject.getJDBCDataBaseMetaData();
		List<SchemaMeta> schemaList = jdbcDataBaseMetaData.getCurrentSchemaMetas();
		for (SchemaMeta schemaMeta : schemaList) {
			for (TableMeta tableMeta : schemaMeta.getTableMetas()) {
				System.out.println(schemaMeta.getSchemaName() + "." + tableMeta.getTableName());
			}
		}
		return null;
	}

}
