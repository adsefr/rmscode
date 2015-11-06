package com.rms.common.jdbc;

import java.util.List;

import com.rms.base.jdbc.model.CatalogMeta;
import com.rms.base.jdbc.model.ColumnMeta;
import com.rms.base.jdbc.model.DataBaseMeta;
import com.rms.base.jdbc.model.SchemaMeta;
import com.rms.base.jdbc.model.TableMeta;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/02/24
 */
public interface JDBCDataBaseMetaData {

	/**
	 *
	 * @return
	 */
	public DataBaseMeta getDataBaseMeta();

	/**
	 *
	 * @return
	 */
	public List<CatalogMeta> getCatalogMetas();

	/**
	 *
	 * @param catalogName
	 * @return
	 */
	public boolean hasCatalogMeta(String catalogName);

	/**
	 *
	 * @param catalogName
	 * @return
	 */
	public CatalogMeta getCatalogMeta(String catalogName);

	/**
	 *
	 * @return
	 */
	public List<SchemaMeta> getSchemaMetas();

	/**
	 *
	 * @param catalogName
	 * @return
	 */
	public List<SchemaMeta> getSchemaMetas(String catalogName);

	/**
	 *
	 * @param catalogName
	 * @param schemaName
	 * @return
	 */
	public boolean hasSchemaMeta(String catalogName, String schemaName);

	/**
	 *
	 * @param catalogName
	 * @return
	 */
	public SchemaMeta getSchemaMeta(String catalogName, String schemaName);

	/**
	 *
	 * @param catalogName
	 * @param schemaName
	 * @return
	 */
	public List<TableMeta> getTableMetas(String catalogName, String schemaName);

	/**
	 *
	 * @param catalogName
	 * @param schemaName
	 * @param tableName
	 * @return
	 */
	public boolean hasTableMeta(String catalogName, String schemaName, String tableName);

	/**
	 *
	 * @param catalogName
	 * @param schemaName
	 * @param tableName
	 * @return
	 */
	public TableMeta getTableMeta(String catalogName, String schemaName, String tableName);

	/**
	 *
	 * @param catalogName
	 * @param schemaName
	 * @param tableName
	 * @return
	 */
	public List<ColumnMeta> getColumnMetas(String catalogName, String schemaName, String tableName);

	/**
	 *
	 * @param catalogName
	 * @param schemaName
	 * @param tableName
	 * @param columnName
	 * @return
	 */
	public boolean hasColumnMeta(String catalogName, String schemaName, String tableName, String columnName);

	/**
	 *
	 * @param catalogName
	 * @param schemaName
	 * @param tableName
	 * @param columnName
	 * @return
	 */
	public ColumnMeta getColumnMeta(String catalogName, String schemaName, String tableName, String columnName);
}
