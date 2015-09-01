package com.rms.common.jdbc;

import java.sql.SQLException;
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

	public String getDatabaseProductName() throws SQLException;

	public String getDatabaseProductVersion() throws SQLException;

	public int getDatabaseMajorVersion() throws SQLException;

	public int getDatabaseMinorVersion() throws SQLException;

	public String getDriverName() throws SQLException;

	public String getDriverVersion() throws SQLException;

	public int getDriverMajorVersion();

	public int getDriverMinorVersion();

	public int getJDBCMajorVersion() throws SQLException;

	public int getJDBCMinorVersion() throws SQLException;

	public String getURL() throws SQLException;

	public List<CatalogMeta> getCatalogMetas() throws SQLException;

	public CatalogMeta getCatalogMeta() throws SQLException;

	public CatalogMeta getCatalogMeta(String catalogName) throws SQLException;

	public List<SchemaMeta> getSchemaMetas() throws SQLException;

	public List<SchemaMeta> getSchemaMetas(String catalogName) throws SQLException;

	public SchemaMeta getSchemaMeta(String schemaName) throws SQLException;

	public SchemaMeta getSchemaMeta(String catalogName, String schemaName) throws SQLException;

	public List<TableMeta> getTableMetas(String schemaName) throws SQLException;

	public List<TableMeta> getTableMetas(String catalogName, String schemaName) throws SQLException;

	public TableMeta getTableMeta(String schemaName, String tableName) throws SQLException;

	public TableMeta getTableMeta(String catalogName, String schemaName, String tableName) throws SQLException;
}
