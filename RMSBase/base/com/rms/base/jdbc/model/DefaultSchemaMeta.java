package com.rms.base.jdbc.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rms.base.util.TextUtil;
import com.rms.base.validate.Assertion;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/02/12
 */
public class DefaultSchemaMeta implements SchemaMeta {

	private String catalogName;

	private String schemaName;

	private List<TableMeta> tableMetas = new ArrayList<>();

	private Map<String, TableMeta> tableMetaMap = new HashMap<>();

	public DefaultSchemaMeta() {

	}

	public void setCatalogName(String catalogName) {

		this.catalogName = catalogName;
	}

	public void setSchemaName(String schemaName) {

		this.schemaName = schemaName;
	}

	@Override
	public void addTableMeta(TableMeta tableMeta) {

		tableMetas.add(tableMeta);
		tableMetaMap.put(tableMeta.getTableName(), tableMeta);
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
	public String getSchemaName() {

		return schemaName;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean contains(String tableName) {

		for (TableMeta tableMeta : tableMetas) {
			if (TextUtil.isEqualsIgnoreCase(tableMeta.getTableName(), tableName)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TableMeta getTableMeta(String tableName) {

		Assertion.assertNotNull("tableName", tableName);

		return tableMetaMap.get(tableName);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<TableMeta> getTableMetas(List<String> tableNames) {

		Assertion.assertNotNull("tableNames", tableNames);

		List<TableMeta> tableMetas = new ArrayList<>();
		for (String tableName : tableNames) {
			tableMetas.add(tableMetaMap.get(tableName));
		}

		return tableMetas;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<TableMeta> getTableMetas() {

		return new ArrayList<>(tableMetas);
	}

}
