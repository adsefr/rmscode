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
public class CatalogMeta {

	private String catalogName;

	private Map<String, SchemaMeta> schemaMetaMap = new HashMap<>();

	public CatalogMeta() {
		super();
	}

	public CatalogMeta(String catalogName) {
		super();
		this.catalogName = catalogName;
	}

	public boolean contains(String schemaName) {

		Assertion.assertNotBlank("schemaName", schemaName);

		return schemaMetaMap.containsKey(schemaName);
	}

	public void addSchemaMeta(SchemaMeta schemaMeta) {

		Assertion.assertNotNull("schemaMeta", schemaMeta);

		schemaMetaMap.put(schemaMeta.getSchemaName(), schemaMeta);
	}

	public SchemaMeta getSchemaMeta(String schemaName) {

		Assertion.assertNotBlank("schemaName", schemaName);

		return schemaMetaMap.get(schemaName);
	}

	public List<SchemaMeta> getSchemaMetas() {

		return new ArrayList<>(schemaMetaMap.values());
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

}
