package com.rms.base.jdbc.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rms.base.jdbc.JDBCValue;
import com.rms.base.validate.Assertion;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/02/12
 */
public class CatalogMeta {

	private JDBCValue catalogName;

	private Map<String, SchemaMeta> schemaMetaMap = new HashMap<>();

	public CatalogMeta() {

	}

	private boolean hasSchema(String schemaName) {

		return schemaMetaMap.containsKey(schemaName.toUpperCase());
	}

	private SchemaMeta _getSchemaMeta(String schemaName) {

		return schemaMetaMap.get(schemaName.toUpperCase());
	}

	private void _addSchemaMeta(SchemaMeta schemaMeta) {

		// schemaMetaMap.put(schemaMeta.getSchemaName(), schemaMeta);
	}

	/**
	 * @return catalogName
	 */
	public JDBCValue getCatalogName() {

		return catalogName;
	}

	/**
	 * @param catalogName
	 *            セットする catalogName
	 */
	public void setCatalogName(JDBCValue catalogName) {

		this.catalogName = catalogName;
	}

	public boolean contains(String schemaName) {

		Assertion.assertNotNull("schemaName", schemaName);

		return hasSchema(schemaName);
	}

	public void addSchemaMeta(SchemaMeta schemaMeta) {

		Assertion.assertNotNull("schemaMeta", schemaMeta);

		if (schemaMeta.getSchemaName() != null) {
			_addSchemaMeta(schemaMeta);
		}
	}

	public SchemaMeta getSchemaMeta(String schemaName) {

		Assertion.assertNotNull("schemaName", schemaName);

		return _getSchemaMeta(schemaName);
	}

	public List<SchemaMeta> getSchemaMetas() {

		return new ArrayList<>(schemaMetaMap.values());
	}

}
