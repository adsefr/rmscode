package com.rms.common.jdbc;

import java.util.List;

import com.rms.base.jdbc.JDBCValue;
import com.rms.base.jdbc.model.CatalogMeta;
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

	public DataBaseMeta getDataBaseMeta();

	public boolean hasCatalog(JDBCValue catalogName);

	public boolean hasSchema(JDBCValue schemaName);

	public boolean hasSchema(JDBCValue catalogName, JDBCValue schemaName);

	boolean hasTable(JDBCValue schemaName, JDBCValue tableName);

	boolean hasTable(JDBCValue catalogName, JDBCValue schemaName, JDBCValue tableName);

	public List<CatalogMeta> getCatalogMetas();

	public CatalogMeta getCurrentCatalogMeta();

	public CatalogMeta getCatalogMeta(JDBCValue catalogName);

	public List<SchemaMeta> getSchemaMetas();

	public List<SchemaMeta> getSchemaMetas(JDBCValue catalogName);

	public SchemaMeta getSchemaMeta(JDBCValue schemaName);

	public SchemaMeta getSchemaMeta(JDBCValue catalogName, JDBCValue schemaName);

	public List<TableMeta> getTableMetas(JDBCValue schemaName);

	public List<TableMeta> getTableMetas(JDBCValue catalogName, JDBCValue schemaName);

	public TableMeta getTableMeta(JDBCValue schemaName, JDBCValue tableName);

	public TableMeta getTableMeta(JDBCValue catalogName, JDBCValue schemaName, JDBCValue tableName);
}
