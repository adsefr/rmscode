package com.rms.common.jdbc;

import java.util.List;

import com.rms.base.jdbc.model.CatalogMeta;
import com.rms.base.jdbc.model.SchemaMeta;
import com.rms.base.jdbc.model.TableMeta;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/02/24
 */
public interface JDBCDataBaseMetaData {

	public String getDatabaseProductName();

	public String getDatabaseProductVersion();

	public int getDatabaseMajorVersion();

	public int getDatabaseMinorVersion();

	public String getDriverName();

	public String getDriverVersion();

	public int getDriverMajorVersion();

	public int getDriverMinorVersion();

	public int getJDBCMajorVersion();

	public int getJDBCMinorVersion();

	public String getURL();

	public boolean hasCatalog(String catalogName);

	public boolean hasSchema(String schemaName);

	public boolean hasSchema(String catalogName, String schemaName);

	boolean hasTable(String schemaName, String tableName);

	boolean hasTable(String catalogName, String schemaName, String tableName);

	public List<CatalogMeta> getCatalogMetas();

	public CatalogMeta getCatalogMeta();

	public CatalogMeta getCatalogMeta(String catalogName);

	public List<SchemaMeta> getSchemaMetas();

	public List<SchemaMeta> getSchemaMetas(String catalogName);

	public SchemaMeta getSchemaMeta(String schemaName);

	public SchemaMeta getSchemaMeta(String catalogName, String schemaName);

	public List<TableMeta> getTableMetas(String schemaName);

	public List<TableMeta> getTableMetas(String catalogName, String schemaName);

	public TableMeta getTableMeta(String schemaName, String tableName);

	public TableMeta getTableMeta(String catalogName, String schemaName, String tableName);
}
