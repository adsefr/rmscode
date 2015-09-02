package com.rms.base.jdbc.model;

import java.util.List;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/02/26
 */
public interface TableMeta {

	public String getCatalogName();

	public String getSchemaName();

	public String getTableName();

	public String getTableType();

	public boolean contains(String columnName);

	public ColumnMeta getColumnMeta(String columnName);

	public List<ColumnMeta> getColumnMetas();

	public void addColumnMeta(ColumnMeta columnMeta);

	public void setPrimaryKeyName(String primaryKeyName);
}
