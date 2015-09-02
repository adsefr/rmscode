package com.rms.util.jdbc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.rms.base.jdbc.model.ColumnMeta;
import com.rms.base.jdbc.model.QueryParameter;
import com.rms.base.jdbc.model.SchemaMeta;
import com.rms.base.jdbc.model.TableMeta;
import com.rms.base.jdbc.model.UpdateParameter;
import com.rms.base.logging.Logger;
import com.rms.base.util.ArrayUtil;
import com.rms.base.validate.Assertion;
import com.rms.common.jdbc.JDBCObject;
import com.rms.common.jdbc.JDBCQueryResult;

public class CopyData extends JDBCProcess {

	private final static Logger logger = Logger.getLogger(CopyData.class);

	private JDBCObject srcJDBCObject;

	private JDBCObject destJDBCObject;

	SchemaMeta srcSchemaMeta;

	SchemaMeta destSchemaMeta;

	private List<TableMeta> targetTableMetas = new ArrayList<>();

	private boolean srcNewConnection = false;

	private boolean destNewConnection = false;

	public CopyData(JDBCObject srcJDBCObject, JDBCObject destJDBCObject) {

		Assertion.assertNotNull("srcJDBCObject", srcJDBCObject);
		Assertion.assertNotNull("destJDBCObject", destJDBCObject);

		this.srcJDBCObject = srcJDBCObject;
		this.destJDBCObject = destJDBCObject;
	}

	@Override
	public void beforeProcess() throws Exception {

		srcNewConnection = srcJDBCObject.startTransaction();
		destNewConnection = destJDBCObject.startTransaction();
	}

	@Override
	public void process() throws Exception {

		for (TableMeta destTableMeta : targetTableMetas) {
			String tableName = destTableMeta.getTableName();
			TableMeta srcTableMeta = srcSchemaMeta.getTableMeta(tableName);

			List<String> columnNames = new ArrayList<>();

			for (ColumnMeta columnMeta : destTableMeta.getColumnMetas()) {
				String columnName = columnMeta.getColumnName();

				if (!srcTableMeta.contains(columnName)) {
					logger.warn("the column[" + columnName + "] which doesn't exist in srcSchema hasn't been set!!!");
					continue;
				}

				columnNames.add(columnName);
			}

			String fields = ArrayUtil.join(columnNames, ", ");
			StringBuilder sqlClause = new StringBuilder();
			sqlClause.setLength(0);
			sqlClause.append("SELECT ").append(fields).append(" FROM ").append(tableName);
			logger.debug("query sql:" + sqlClause.toString());

			QueryParameter queryParameter = new QueryParameter();
			queryParameter.setSqlClause(sqlClause.toString());
			JDBCQueryResult jdbcQueryResult = srcJDBCObject.query(queryParameter);

			List<String> valuesList = new ArrayList<>(columnNames);
			Collections.fill(valuesList, "?");
			String values = ArrayUtil.join(valuesList, ", ");
			sqlClause.setLength(0);
			sqlClause.append("INSERT INTO ").append(tableName).append(" (").append(fields).append(") VALUES (").append(values).append(")");
			logger.debug("insert sql:" + sqlClause.toString());

			UpdateParameter updateParameter = new UpdateParameter();
			updateParameter.setSqlClause(sqlClause.toString());

			while (jdbcQueryResult.hasNext()) {
				List<Object> parameterList = jdbcQueryResult.getValues();
				updateParameter.setParameterList(parameterList);
				destJDBCObject.update(updateParameter);
			}
			destJDBCObject.commit();
		}
	}

	@Override
	public void afterProcessSuccess() throws Exception {

		destJDBCObject.commit();
		destJDBCObject.endTransaction();
	}

	@Override
	public void afterProcessFailure() throws Exception {

		destJDBCObject.rollback();
	}

	@Override
	public void destory() throws Exception {

		try {
			if (srcNewConnection && srcJDBCObject != null && !srcJDBCObject.isClosed()) {
				srcJDBCObject.close();
			}
		} catch (SQLException e) {
			logger.debug(e);
		}
		try {
			if (destNewConnection && destJDBCObject != null && !destJDBCObject.isClosed()) {
				destJDBCObject.close();
			}
		} catch (SQLException e) {
			logger.debug(e);
		}
	}

	private void copy(SchemaMeta srcSchemaMeta, SchemaMeta destSchemaMeta, List<TableMeta> tableMetaList) throws Exception {

		for (TableMeta tableMeta : tableMetaList) {
			String tableName = tableMeta.getTableName();

			if (!srcSchemaMeta.contains(tableName)) {
				logger.warn("the table[" + tableName + "] doesn't exist in srcSchema!!!");
				continue;
			}
			if (!destSchemaMeta.contains(tableName)) {
				logger.warn("the table[" + tableName + "] doesn't exist in destSchema!!!");
				continue;
			}

			if (!srcSchemaMeta.getTableMeta(tableName).getTableType().equals("TABLE")) {
				logger.warn("the table[" + tableName + "] is not table type in srcSchema!!!");
				continue;
			}

			if (!destSchemaMeta.getTableMeta(tableName).getTableType().equals("TABLE")) {
				logger.warn("the table[" + tableName + "] is not table type in destSchema!!!");
				continue;
			}

			targetTableMetas.add(destSchemaMeta.getTableMeta(tableName));
		}

		execute();
	}

	public void copy(String srcSchemaName, String destSchemaName) throws Exception {

		Assertion.assertNotBlank("srcSchemaName", srcSchemaName);
		Assertion.assertNotBlank("destSchemaName", destSchemaName);

		srcSchemaMeta = srcJDBCObject.getJDBCDataBaseMetaData().getSchemaMeta(srcSchemaName);
		destSchemaMeta = destJDBCObject.getJDBCDataBaseMetaData().getSchemaMeta(destSchemaName);
		copy(srcSchemaMeta, destSchemaMeta, destSchemaMeta.getTableMetas());
	}

	public void copy(String srcSchemaName, String destSchemaName, List<String> tablesNameList) throws Exception {

		Assertion.assertNotBlank("srcSchemaName", srcSchemaName);
		Assertion.assertNotBlank("destSchemaName", destSchemaName);
		Assertion.assertNotNull("tablesNameList", tablesNameList);

		srcSchemaMeta = srcJDBCObject.getJDBCDataBaseMetaData().getSchemaMeta(srcSchemaName);
		destSchemaMeta = destJDBCObject.getJDBCDataBaseMetaData().getSchemaMeta(destSchemaName);
		List<TableMeta> tableMetaList = tablesNameList.stream().map(e -> destSchemaMeta.getTableMeta(e)).collect(Collectors.toList());
		copy(srcSchemaMeta, destSchemaMeta, tableMetaList);
	}
}
