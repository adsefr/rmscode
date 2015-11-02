package com.rms.base.jdbc.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rms.base.validate.Assertion;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/02/12
 */
public class SchemaMeta {

	private CatalogMeta catalogMeta;

	private String catalogName;

	private String schemaName;

	private Map<String, TableMeta> tableMetaMap = new HashMap<>();

	public SchemaMeta() {
		super();
	}

	public SchemaMeta(String catalogName, String schemaName) {
		super();
		this.catalogName = catalogName;
		this.schemaName = schemaName;
	}

	public boolean contains(String tableName) {

		Assertion.assertNotBlank("tableName", tableName);

		return tableMetaMap.containsKey(tableName);
	}

	public void addTableMeta(TableMeta tableMeta) {

		Assertion.assertNotNull("tableMeta", tableMeta);

		tableMetaMap.put(tableMeta.getTableName(), tableMeta);
	}

	public TableMeta getTableMeta(String tableName) {

		Assertion.assertNotNull("tableName", tableName);

		return tableMetaMap.get(tableName);
	}

	public List<TableMeta> getTableMetas() {

		return new ArrayList<>(tableMetaMap.values());
	}

	/**
	 * @return catalogMeta
	 */
	public CatalogMeta getCatalogMeta() {

		return catalogMeta;
	}

	/**
	 * @param catalogMeta
	 *            セットする catalogMeta
	 */
	public void setCatalogMeta(CatalogMeta catalogMeta) {

		this.catalogMeta = catalogMeta;
	}

	/**
	 * @return catalogName
	 */
	public String getCatalogName() {

		return catalogName;
	}

	/**
	 * @param catalogName
	 *            セットする catalogName
	 */
	public void setCatalogName(String catalogName) {

		this.catalogName = catalogName;
	}

	/**
	 * @return schemaName
	 */
	public String getSchemaName() {

		return schemaName;
	}

	/**
	 * @param schemaName
	 *            セットする schemaName
	 */
	public void setSchemaName(String schemaName) {

		this.schemaName = schemaName;
	}

}
