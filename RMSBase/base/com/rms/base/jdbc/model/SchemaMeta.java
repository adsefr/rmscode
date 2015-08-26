package com.rms.base.jdbc.model;

import java.util.List;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/02/12
 */
public interface SchemaMeta {

	public String getCatalogName();

	public String getSchemaName();

	public boolean contains(String tableName);

	public void addTableMeta(TableMeta tableMeta);

	public TableMeta getTableMeta(String tableName);

	public List<TableMeta> getTableMetas(List<String> tableName);

	public List<TableMeta> getTableMetas();
}
