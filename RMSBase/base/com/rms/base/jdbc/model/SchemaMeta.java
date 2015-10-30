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
public class SchemaMeta {

	private CatalogMeta catalogMeta;

	private JDBCValue catalogName;

	private JDBCValue schemaName;

	private List<TableMeta> tableMetas = new ArrayList<>();

	private Map<JDBCValue, TableMeta> tableMetaMap = new HashMap<>();

	public SchemaMeta() {

	}

	public void addTableMeta(TableMeta tableMeta) {

		tableMetas.add(tableMeta);
		tableMetaMap.put(tableMeta.getTableName(), tableMeta);
	}

	public boolean contains(JDBCValue tableName) {

		// for (TableMeta tableMeta : tableMetas) {
		// if (TextUtil.isEqualsIgnoreCase(tableMeta.getTableName(), tableName)) {
		// return true;
		// }
		// }

		return false;
	}

	public TableMeta getTableMeta(JDBCValue tableName) {

		Assertion.assertNotNull("tableName", tableName);

		return tableMetaMap.get(tableName);
	}

	public List<TableMeta> getTableMetas(List<String> tableNames) {

		Assertion.assertNotNull("tableNames", tableNames);

		List<TableMeta> tableMetas = new ArrayList<>();
		for (String tableName : tableNames) {
			tableMetas.add(tableMetaMap.get(tableName));
		}

		return tableMetas;
	}

	public List<TableMeta> getTableMetas() {

		return new ArrayList<>(tableMetas);
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

	/**
	 * @return schemaName
	 */
	public JDBCValue getSchemaName() {

		return schemaName;
	}

	/**
	 * @param schemaName
	 *            セットする schemaName
	 */
	public void setSchemaName(JDBCValue schemaName) {

		this.schemaName = schemaName;
	}

}
