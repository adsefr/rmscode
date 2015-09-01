package com.rms.base.jdbc.implments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rms.base.jdbc.model.CatalogMeta;
import com.rms.base.jdbc.model.SchemaMeta;
import com.rms.base.validate.Assertion;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/02/12
 */
public class DefaultCatalogMeta implements CatalogMeta {

	private String catalogName;

	private Map<String, SchemaMeta> schemaMetaMap = new HashMap<>();

	public DefaultCatalogMeta() {

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getCatalogName() {

		return catalogName;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setCatalogName(String catalogName) {

		Assertion.assertNotNull("catalogName", catalogName);

		this.catalogName = catalogName;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean contains(String schemaName) {

		Assertion.assertNotNull("schemaName", schemaName);

		return schemaMetaMap.containsKey(schemaName.toUpperCase());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addSchemaMeta(SchemaMeta schemaMeta) {

		Assertion.assertNotNull("schemaMeta", schemaMeta);

		if (schemaMeta.getSchemaName() != null) {
			schemaMetaMap.put(schemaMeta.getSchemaName().toUpperCase(), schemaMeta);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SchemaMeta getSchemaMeta(String schemaName) {

		Assertion.assertNotNull("schemaName", schemaName);

		return schemaMetaMap.get(schemaName.toUpperCase());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<SchemaMeta> getSchemaMetas(List<String> schemaNames) {

		Assertion.assertNotNull("schemaNames", schemaNames);

		List<SchemaMeta> schemaMetas = new ArrayList<>();

		for (String schemaName : schemaNames) {
			if (schemaName == null) {
				schemaMetas.add(null);
			} else {
				schemaMetas.add(schemaMetaMap.get(schemaName.toUpperCase()));
			}
		}

		return schemaMetas;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<SchemaMeta> getSchemaMetas() {

		return new ArrayList<>(schemaMetaMap.values());
	}

}
