package com.rms.base.jdbc.instance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rms.base.jdbc.model.ColumnMeta;
import com.rms.base.jdbc.model.TableMeta;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/02/26
 */
public class DefaultTableMeta implements TableMeta {

	/** TABLE_CAT String => テーブルカタログ (null の可能性がある) */
	private String catalogName;

	/** TABLE_SCHEM String => テーブルスキーマ (null の可能性がある) */
	private String schemaName;

	/** TABLE_NAME String => テーブル名 */
	private String tableName;

	/**
	 * TABLE_TYPE String => テーブルのタイプ。<BR>
	 * 典型的なタイプは、「TABLE」、「VIEW」、「SYSTEM TABLE」、「GLOBAL TEMPORARY」、「LOCAL
	 * TEMPORARY」、「ALIAS」、「SYNONYM」である
	 */
	private String tableType;

	/** REMARKS String => テーブルに関する説明 */
	private String remarks;

	/** TYPE_CAT String => タイプのカタログ (null の可能性がある) */
	private String tableTypeCatalog;

	/** TYPE_SCHEM String => タイプのスキーマ (null の可能性がある) */
	private String tableTypeSchema;

	/** TYPE_NAME String => タイプ名 (null の可能性がある) */
	private String typeName;

	/**
	 * SELF_REFERENCING_COL_NAME String => タイプ指定されたテーブルの指定された「識別子」列の名前 (null
	 * の可能性がある)
	 */
	private String selfReferencingColumnName;

	/**
	 * REF_GENERATION String => SELF_REFERENCING_COL_NAME
	 * の値の作成方法を指定する。値は、「SYSTEM」、「USER」、「DERIVED」(null の可能性がある)
	 */
	private String referenceGeneration;

	private String primaryKeyName;

	private List<ColumnMeta> columnMetas = new ArrayList<>();

	private Map<String, ColumnMeta> columnMetaMap = new HashMap<>();

	public DefaultTableMeta() {

	}

	/**
	 * @return catalogName
	 */
	@Override
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
	@Override
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

	/**
	 * @return tableName
	 */
	@Override
	public String getTableName() {

		return tableName;
	}

	/**
	 * @param tableName
	 *            セットする tableName
	 */
	public void setTableName(String tableName) {

		this.tableName = tableName;
	}

	/**
	 * @return tableType
	 */
	@Override
	public String getTableType() {

		return tableType;
	}

	/**
	 * @param tableType
	 *            セットする tableType
	 */
	public void setTableType(String tableType) {

		this.tableType = tableType;
	}

	/**
	 * @return remarks
	 */
	public String getRemarks() {

		return remarks;
	}

	/**
	 * @param remarks
	 *            セットする remarks
	 */
	public void setRemarks(String remarks) {

		this.remarks = remarks;
	}

	/**
	 * @return tableTypeCatalog
	 */
	public String getTableTypeCatalog() {

		return tableTypeCatalog;
	}

	/**
	 * @param tableTypeCatalog
	 *            セットする tableTypeCatalog
	 */
	public void setTableTypeCatalog(String tableTypeCatalog) {

		this.tableTypeCatalog = tableTypeCatalog;
	}

	/**
	 * @return tableTypeSchema
	 */
	public String getTableTypeSchema() {

		return tableTypeSchema;
	}

	/**
	 * @param tableTypeSchema
	 *            セットする tableTypeSchema
	 */
	public void setTableTypeSchema(String tableTypeSchema) {

		this.tableTypeSchema = tableTypeSchema;
	}

	/**
	 * @return typeName
	 */
	public String getTypeName() {

		return typeName;
	}

	/**
	 * @param typeName
	 *            セットする typeName
	 */
	public void setTypeName(String typeName) {

		this.typeName = typeName;
	}

	/**
	 * @return selfReferencingColumnName
	 */
	public String getSelfReferencingColumnName() {

		return selfReferencingColumnName;
	}

	/**
	 * @param selfReferencingColumnName
	 *            セットする selfReferencingColumnName
	 */
	public void setSelfReferencingColumnName(String selfReferencingColumnName) {

		this.selfReferencingColumnName = selfReferencingColumnName;
	}

	/**
	 * @return referenceGeneration
	 */
	public String getReferenceGeneration() {

		return referenceGeneration;
	}

	/**
	 * @param referenceGeneration
	 *            セットする referenceGeneration
	 */
	public void setReferenceGeneration(String referenceGeneration) {

		this.referenceGeneration = referenceGeneration;
	}

	@Override
	public boolean contains(String columnName) {

		return (columnMetaMap.get(columnName) != null);
	}

	@Override
	public ColumnMeta getColumnMeta(String columnName) {

		return columnMetaMap.get(columnName);
	}

	/**
	 * @return columnMetas
	 */
	@Override
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

	@Override
	public void addColumnMeta(ColumnMeta columnMeta) {

		columnMetas.add(columnMeta);
		columnMetaMap.put(columnMeta.getColumnName(), columnMeta);
	}

	/**
	 * @return primaryKeyName
	 */
	public String getPrimaryKeyName() {

		return primaryKeyName;
	}

	/**
	 * @param primaryKeyName
	 *            セットする primaryKeyName
	 */
	@Override
	public void setPrimaryKeyName(String primaryKeyName) {

		this.primaryKeyName = primaryKeyName;
	}
}
