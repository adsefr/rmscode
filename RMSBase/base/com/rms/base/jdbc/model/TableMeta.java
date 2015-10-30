package com.rms.base.jdbc.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rms.base.jdbc.JDBCValue;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/02/26
 */
public class TableMeta {

	private SchemaMeta schemaMeta;

	/** TABLE_CAT String => テーブルカタログ (null の可能性がある) */
	private JDBCValue catalogName;

	/** TABLE_SCHEM String => テーブルスキーマ (null の可能性がある) */
	private JDBCValue schemaName;

	/** TABLE_NAME String => テーブル名 */
	private JDBCValue tableName;

	/**
	 * TABLE_TYPE String => テーブルのタイプ。<BR>
	 * 典型的なタイプは、「TABLE」、「VIEW」、「SYSTEM TABLE」、「GLOBAL TEMPORARY」、「LOCAL TEMPORARY」、「ALIAS」、「SYNONYM」である
	 */
	private JDBCValue tableType;

	/** REMARKS String => テーブルに関する説明 */
	private JDBCValue remarks;

	/** TYPE_CAT String => タイプのカタログ (null の可能性がある) */
	private JDBCValue tableTypeCatalog;

	/** TYPE_SCHEM String => タイプのスキーマ (null の可能性がある) */
	private JDBCValue tableTypeSchema;

	/** TYPE_NAME String => タイプ名 (null の可能性がある) */
	private JDBCValue typeName;

	/**
	 * SELF_REFERENCING_COL_NAME String => タイプ指定されたテーブルの指定された「識別子」列の名前 (null の可能性がある)
	 */
	private JDBCValue selfReferencingColumnName;

	/**
	 * REF_GENERATION String => SELF_REFERENCING_COL_NAME の値の作成方法を指定する。値は、「SYSTEM」、「USER」、「DERIVED」(null の可能性がある)
	 */
	private JDBCValue referenceGeneration;

	private JDBCValue primaryKeyName;

	private List<ColumnMeta> columnMetas = new ArrayList<>();

	private Map<String, ColumnMeta> columnMetaMap = new HashMap<>();

	public TableMeta() {
	}

	@Override
	public String toString() {

		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("catalogName=").append(catalogName).append(", ");
		sBuilder.append("schemaName=").append(schemaName).append(", ");
		sBuilder.append("tableName=").append(tableName).append(", ");
		sBuilder.append("tableType=").append(tableType);

		return sBuilder.toString();
	}

	public ColumnMeta getColumnMeta(JDBCValue columnName) {

		if (columnName == null || columnName.isNull()) {
			return null;
		}

		return columnMetaMap.get(columnName.getStringValue());
	}

	/**
	 * @return schemaMeta
	 */
	public SchemaMeta getSchemaMeta() {

		return schemaMeta;
	}

	/**
	 * @param schemaMeta
	 *            セットする schemaMeta
	 */
	public void setSchemaMeta(SchemaMeta schemaMeta) {

		this.schemaMeta = schemaMeta;
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

	/**
	 * @return tableName
	 */
	public JDBCValue getTableName() {

		return tableName;
	}

	/**
	 * @param tableName
	 *            セットする tableName
	 */
	public void setTableName(JDBCValue tableName) {

		this.tableName = tableName;
	}

	/**
	 * @return tableType
	 */
	public JDBCValue getTableType() {

		return tableType;
	}

	/**
	 * @param tableType
	 *            セットする tableType
	 */
	public void setTableType(JDBCValue tableType) {

		this.tableType = tableType;
	}

	/**
	 * @return remarks
	 */
	public JDBCValue getRemarks() {

		return remarks;
	}

	/**
	 * @param remarks
	 *            セットする remarks
	 */
	public void setRemarks(JDBCValue remarks) {

		this.remarks = remarks;
	}

	/**
	 * @return tableTypeCatalog
	 */
	public JDBCValue getTableTypeCatalog() {

		return tableTypeCatalog;
	}

	/**
	 * @param tableTypeCatalog
	 *            セットする tableTypeCatalog
	 */
	public void setTableTypeCatalog(JDBCValue tableTypeCatalog) {

		this.tableTypeCatalog = tableTypeCatalog;
	}

	/**
	 * @return tableTypeSchema
	 */
	public JDBCValue getTableTypeSchema() {

		return tableTypeSchema;
	}

	/**
	 * @param tableTypeSchema
	 *            セットする tableTypeSchema
	 */
	public void setTableTypeSchema(JDBCValue tableTypeSchema) {

		this.tableTypeSchema = tableTypeSchema;
	}

	/**
	 * @return typeName
	 */
	public JDBCValue getTypeName() {

		return typeName;
	}

	/**
	 * @param typeName
	 *            セットする typeName
	 */
	public void setTypeName(JDBCValue typeName) {

		this.typeName = typeName;
	}

	/**
	 * @return selfReferencingColumnName
	 */
	public JDBCValue getSelfReferencingColumnName() {

		return selfReferencingColumnName;
	}

	/**
	 * @param selfReferencingColumnName
	 *            セットする selfReferencingColumnName
	 */
	public void setSelfReferencingColumnName(JDBCValue selfReferencingColumnName) {

		this.selfReferencingColumnName = selfReferencingColumnName;
	}

	/**
	 * @return referenceGeneration
	 */
	public JDBCValue getReferenceGeneration() {

		return referenceGeneration;
	}

	/**
	 * @param referenceGeneration
	 *            セットする referenceGeneration
	 */
	public void setReferenceGeneration(JDBCValue referenceGeneration) {

		this.referenceGeneration = referenceGeneration;
	}

	/**
	 * @return primaryKeyName
	 */
	public JDBCValue getPrimaryKeyName() {

		return primaryKeyName;
	}

	/**
	 * @param primaryKeyName
	 *            セットする primaryKeyName
	 */
	public void setPrimaryKeyName(JDBCValue primaryKeyName) {

		this.primaryKeyName = primaryKeyName;
	}

	/**
	 * @return columnMetas
	 */
	public List<ColumnMeta> getColumnMetas() {

		return columnMetas;
	}

	/**
	 * @param columnMetas
	 *            セットする columnMetas
	 */
	public void setColumnMetas(List<ColumnMeta> columnMetas) {

		this.columnMetas = columnMetas;
	}

	/**
	 * @return columnMetaMap
	 */
	public Map<String, ColumnMeta> getColumnMetaMap() {

		return columnMetaMap;
	}

	/**
	 * @param columnMetaMap
	 *            セットする columnMetaMap
	 */
	public void setColumnMetaMap(Map<String, ColumnMeta> columnMetaMap) {

		this.columnMetaMap = columnMetaMap;
	}

}
