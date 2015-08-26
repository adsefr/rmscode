package com.rms.tool.jdbc;

import java.sql.SQLException;
import java.util.List;

import com.rms.base.jdbc.model.ColumnMeta;
import com.rms.base.jdbc.model.DataBaseInfo;
import com.rms.base.jdbc.model.QueryParameter;
import com.rms.base.jdbc.model.SchemaMeta;
import com.rms.base.jdbc.model.TableMeta;
import com.rms.base.jdbc.model.UpdateParameter;
import com.rms.common.jdbc.JDBCDataBaseMetaData;
import com.rms.common.jdbc.JDBCObject;
import com.rms.common.jdbc.JDBCQueryResult;

public class CopyTables {

	private JDBCObject srcJDBCObject;

	private JDBCObject destJDBCObject;

	private JDBCDataBaseMetaData srcJDBCDataBaseMetaData;

	private JDBCDataBaseMetaData destJDBCDataBaseMetaData;

	public CopyTables(DataBaseInfo srcDatabaseInfo, DataBaseInfo destDatabaseInfo) {

		srcJDBCObject = JDBCObject.newInstance(srcDatabaseInfo);
		destJDBCObject = JDBCObject.newInstance(destDatabaseInfo);
	}

	private void close() {

		if (srcJDBCObject != null) {
			try {
				srcJDBCObject.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (destJDBCObject != null) {
			try {
				destJDBCObject.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private void beforeCopy() throws SQLException {

		srcJDBCObject.startTransaction();
		destJDBCObject.startTransaction();
		srcJDBCDataBaseMetaData = srcJDBCObject.getJDBCDataBaseMetaData();
		destJDBCDataBaseMetaData = destJDBCObject.getJDBCDataBaseMetaData();
	}

	private void afterCopy() {

		close();
	}

	public void copy(String srcSchemaName, String destSchemaName) {

		try {
			beforeCopy();
			SchemaMeta srcSchemaMeta = srcJDBCDataBaseMetaData.getCatalogMeta().getSchemaMeta(srcSchemaName);
			SchemaMeta destSchemaMeta = destJDBCDataBaseMetaData.getCatalogMeta().getSchemaMeta(destSchemaName);
			List<TableMeta> srcTableMetas = srcSchemaMeta.getTableMetas();
			List<TableMeta> destTableMetas = destSchemaMeta.getTableMetas();
			copy(srcSchemaMeta, srcTableMetas, destSchemaMeta, destTableMetas);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void copy(SchemaMeta srcSchemaMeta, SchemaMeta destSchemaMeta) {

		try {
			beforeCopy();
			List<TableMeta> srcTableMetas = srcSchemaMeta.getTableMetas();
			List<TableMeta> destTableMetas = destSchemaMeta.getTableMetas();
			copy(srcSchemaMeta, srcTableMetas, destSchemaMeta, destTableMetas);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void copy(String srcSchemaName, List<String> srcTables, String destSchemaName, List<String> destTables) throws SQLException {

		try {
			beforeCopy();
			SchemaMeta srcSchemaMeta = srcJDBCDataBaseMetaData.getCatalogMeta().getSchemaMeta(srcSchemaName);
			SchemaMeta destSchemaMeta = destJDBCDataBaseMetaData.getCatalogMeta().getSchemaMeta(destSchemaName);
			List<TableMeta> srcTableMetas = srcSchemaMeta.getTableMetas(srcTables);
			List<TableMeta> destTableMetas = destSchemaMeta.getTableMetas(destTables);
			copy(srcSchemaMeta, srcTableMetas, destSchemaMeta, destTableMetas);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void copy(SchemaMeta srcSchemaMeta, List<TableMeta> srcTableMetas, SchemaMeta destSchemaMeta, List<TableMeta> destTableMetas) throws SQLException {

		try {
			for (TableMeta tableMeta : destTableMetas) {
				if (!srcSchemaMeta.contains(tableMeta.getTableName())) {
					System.out.println("the table " + tableMeta.getTableName() + " not existes in src." + srcSchemaMeta.getCatalogName() + "." + srcSchemaMeta.getSchemaName());
				}
			}

			for (TableMeta tableMeta : srcTableMetas) {
				if (!destSchemaMeta.contains(tableMeta.getTableName())) {
					System.out.println("the table " + tableMeta.getTableName() + " not existes in dest." + destSchemaMeta.getCatalogName() + "." + destSchemaMeta.getSchemaName());
				} else {
					copy(tableMeta, destSchemaMeta.getTableMeta(tableMeta.getTableName()));
				}
			}
			destJDBCObject.commit();
		} catch (SQLException e) {
			srcJDBCObject.rollback();
			destJDBCObject.rollback();
		} finally {
			afterCopy();
		}
	}

	private void copy(TableMeta srcTableMeta, TableMeta destTableMeta) {

		if (!srcTableMeta.getTableType().equals("TABLE")) {
			return;
		}
		List<ColumnMeta> destColumnMetas = destTableMeta.getColumnMetas();
		boolean isReturn = false;
		for (ColumnMeta columnMeta : destColumnMetas) {
			if (!srcTableMeta.contains(columnMeta.getColumnName())) {
				System.out.println("the column " + columnMeta.getColumnName() + " not existes in src." + srcTableMeta.getTableName());// TODO
				isReturn = true;
			}
		}

		if (isReturn) {
			return;
		}

		StringBuilder sqlClause = new StringBuilder();
		sqlClause.setLength(0);
		sqlClause.append("SELECT ");
		for (ColumnMeta columnMeta : destColumnMetas) {
			sqlClause.append(columnMeta.getColumnName()).append(", ");
		}
		sqlClause.delete(sqlClause.length() - 2, sqlClause.length());
		sqlClause.append(" FROM ").append(srcTableMeta.getTableName());
		System.out.println(sqlClause.toString());
		JDBCQueryResult jdbcResultSet = null;
		try {
			QueryParameter queryParameter = new QueryParameter();
			queryParameter.setSqlClause(sqlClause.toString());
			jdbcResultSet = srcJDBCObject.query(queryParameter);
			UpdateParameter updateParameter = new UpdateParameter();
			while (jdbcResultSet.hasNext()) {
				sqlClause.setLength(0);
				sqlClause.append("INSERT INTO ").append(destTableMeta.getTableName());
				sqlClause.append("(");
				for (ColumnMeta columnMeta : destColumnMetas) {
					sqlClause.append(columnMeta.getColumnName()).append(", ");
				}
				sqlClause.delete(sqlClause.length() - 2, sqlClause.length());
				sqlClause.append(")");
				sqlClause.append("VALUES(");
				for (ColumnMeta columnMeta : destColumnMetas) {
					sqlClause.append("'").append(jdbcResultSet.getValue(columnMeta.getColumnName())).append("'").append(", ");
				}
				sqlClause.delete(sqlClause.length() - 2, sqlClause.length());
				sqlClause.append(")");

				System.out.println(sqlClause.toString());
				updateParameter.setSqlClause(sqlClause.toString());
				destJDBCObject.update(updateParameter);
			}
			try {
				jdbcResultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	}
}
