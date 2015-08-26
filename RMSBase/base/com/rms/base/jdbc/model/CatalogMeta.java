package com.rms.base.jdbc.model;

import java.util.List;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/02/12
 */
public interface CatalogMeta {

	public String getCatalogName();

	public boolean contains(String schemaName);

	public void addSchemaMeta(SchemaMeta schemaMeta);

	public SchemaMeta getSchemaMeta(String schemaName);

	public List<SchemaMeta> getSchemaMetas(List<String> schemaNames);

	public List<SchemaMeta> getSchemaMetas();

}
