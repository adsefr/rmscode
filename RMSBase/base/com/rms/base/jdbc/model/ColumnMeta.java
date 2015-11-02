package com.rms.base.jdbc.model;

import java.sql.JDBCType;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/02/26
 */
public class ColumnMeta {

	private TableMeta tableMeta;

	/** TABLE_CAT String => テーブルカタログ (null の可能性がある) */
	private String catalogName;

	/** TABLE_SCHEM String => テーブルスキーマ (null の可能性がある) */
	private String schemaName;

	/** TABLE_NAME String => テーブル名 */
	private String tableName;

	/** COLUMN_NAME String => 列名 */
	private String columnName;

	/** DATA_TYPE int => java.sql.Types からの SQL の型 */
	private JDBCType jdbcType;

	/** TYPE_NAME String => データソース依存の型名。UDT の場合、型名は完全指定 */
	private JDBCValue typeName;

	/** COLUMN_SIZE int => 列サイズ */
	private JDBCValue columnSize;

	/** BUFFER_LENGTH - 未使用。 */

	/**
	 * DECIMAL_DIGITS int => 小数点以下の桁数。DECIMAL_DIGITS が適用できないデータ型の場合は、Null が返される。
	 */
	private JDBCValue decimalDits;

	/** NUM_PREC_RADIX int => 基数 (通常は、10 または 2 のどちらか) */
	private JDBCValue numPrecRadix;

	/**
	 * NULLABLE int => NULL は許されるか。<br>
	 * columnNoNulls - NULL 値を許さない可能性がある<br>
	 * columnNullable - 必ず NULL 値を許す<br>
	 * columnNullableUnknown - NULL 値を許すかどうかは不明<br>
	 */
	private JDBCValue nullable;

	/** REMARKS String => 列を記述するコメント (null の可能性がある) */
	private JDBCValue remarks;

	/** COLUMN_DEF String => 列のデフォルト値。単一引用符で囲まれた値は、文字列として解釈されるべき (null の可能性がある) */
	private JDBCValue columnDefaultValue;

	/** SQL_DATA_TYPE int => 未使用 */

	/** SQL_DATETIME_SUB int => 未使用 */

	/** CHAR_OCTET_LENGTH int => char の型については列の最大バイト数 */
	private JDBCValue charOctetLength;

	/** ORDINAL_POSITION int => テーブル中の列のインデックス (1 から始まる) */
	private JDBCValue ordinalPosition;

	/**
	 * IS_NULLABLE String => 列で NULL 値を許可するかどうかの判断に ISO 規則が使用される。<br>
	 * YES --- 列が NULL を許可する場合<br>
	 * NO --- 列が NULL を許可しない場合<br>
	 * 空の文字列 --- 列が NULL 値を許可するかどうか不明である場合<br>
	 */
	private JDBCValue isNullable;

	/**
	 * SCOPE_CATALOG String => 参照属性のスコープであるテーブルのカタログ (DATA_TYPE が REF でない場合は null)
	 */
	private JDBCValue scopeCatalog;

	/**
	 * SCOPE_SCHEMA String => 参照属性のスコープであるテーブルのスキーマ (DATA_TYPE が REF でない場合は null)
	 */
	private JDBCValue scopeSchema;

	/** SCOPE_TABLE String => 参照属性のスコープであるテーブル名 (DATA_TYPE が REF でない場合は null) */
	private JDBCValue scopeTable;

	/**
	 * SOURCE_DATA_TYPE short => 個別の型またはユーザー生成 Ref 型のソースの型、java.sql.Types の SQL 型 (DATA_TYPE が DISTINCT またはユーザー生成 REF
	 * でない場合は null)
	 */
	private JDBCValue sourceDataType;

	/**
	 * IS_AUTOINCREMENT String => この列が自動インクリメントされるかどうかを示す<br>
	 * YES --- 列が自動インクリメントされる場合<br>
	 * NO --- 列が自動インクリメントされない場合<br>
	 * 空の文字列 --- 列が自動インクリメントされるかどうかが判断できない場合<br>
	 */
	private JDBCValue isAutoIncrement;

	/**
	 * IS_GENERATEDCOLUMN String => これが生成された列かどうかを示す<br>
	 * YES --- これが生成された列である場合<br>
	 * NO --- これが生成された列でない場合<br>
	 * 空の文字列 --- これが生成された列かどうかが判断できない場合<br>
	 */
	private JDBCValue isGeneratedColumn;

	private JDBCValue primaryKey;

	private JDBCValue keySequence;

	public ColumnMeta() {
		super();
	}

	public ColumnMeta(String catalogName, String schemaName, String tableName, String columnName) {
		super();
		this.catalogName = catalogName;
		this.schemaName = schemaName;
		this.tableName = tableName;
		this.columnName = columnName;
	}

	/**
	 * @return tableMeta
	 */
	public TableMeta getTableMeta() {

		return tableMeta;
	}

	/**
	 * @param tableMeta
	 *            セットする tableMeta
	 */
	public void setTableMeta(TableMeta tableMeta) {

		this.tableMeta = tableMeta;
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

	/**
	 * @return tableName
	 */
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
	 * @return columnName
	 */
	public String getColumnName() {

		return columnName;
	}

	/**
	 * @param columnName
	 *            セットする columnName
	 */
	public void setColumnName(String columnName) {

		this.columnName = columnName;
	}

	/**
	 * @return jdbcType
	 */
	public JDBCType getJdbcType() {

		return jdbcType;
	}

	/**
	 * @param jdbcType
	 *            セットする jdbcType
	 */
	public void setJdbcType(JDBCType jdbcType) {

		this.jdbcType = jdbcType;
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
	 * @return columnSize
	 */
	public JDBCValue getColumnSize() {

		return columnSize;
	}

	/**
	 * @param columnSize
	 *            セットする columnSize
	 */
	public void setColumnSize(JDBCValue columnSize) {

		this.columnSize = columnSize;
	}

	/**
	 * @return decimalDits
	 */
	public JDBCValue getDecimalDits() {

		return decimalDits;
	}

	/**
	 * @param decimalDits
	 *            セットする decimalDits
	 */
	public void setDecimalDits(JDBCValue decimalDits) {

		this.decimalDits = decimalDits;
	}

	/**
	 * @return numPrecRadix
	 */
	public JDBCValue getNumPrecRadix() {

		return numPrecRadix;
	}

	/**
	 * @param numPrecRadix
	 *            セットする numPrecRadix
	 */
	public void setNumPrecRadix(JDBCValue numPrecRadix) {

		this.numPrecRadix = numPrecRadix;
	}

	/**
	 * @return nullable
	 */
	public JDBCValue getNullable() {

		return nullable;
	}

	/**
	 * @param nullable
	 *            セットする nullable
	 */
	public void setNullable(JDBCValue nullable) {

		this.nullable = nullable;
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
	 * @return columnDefaultValue
	 */
	public JDBCValue getColumnDefaultValue() {

		return columnDefaultValue;
	}

	/**
	 * @param columnDefaultValue
	 *            セットする columnDefaultValue
	 */
	public void setColumnDefaultValue(JDBCValue columnDefaultValue) {

		this.columnDefaultValue = columnDefaultValue;
	}

	/**
	 * @return charOctetLength
	 */
	public JDBCValue getCharOctetLength() {

		return charOctetLength;
	}

	/**
	 * @param charOctetLength
	 *            セットする charOctetLength
	 */
	public void setCharOctetLength(JDBCValue charOctetLength) {

		this.charOctetLength = charOctetLength;
	}

	/**
	 * @return ordinalPosition
	 */
	public JDBCValue getOrdinalPosition() {

		return ordinalPosition;
	}

	/**
	 * @param ordinalPosition
	 *            セットする ordinalPosition
	 */
	public void setOrdinalPosition(JDBCValue ordinalPosition) {

		this.ordinalPosition = ordinalPosition;
	}

	/**
	 * @return isNullable
	 */
	public JDBCValue getIsNullable() {

		return isNullable;
	}

	/**
	 * @param isNullable
	 *            セットする isNullable
	 */
	public void setIsNullable(JDBCValue isNullable) {

		this.isNullable = isNullable;
	}

	/**
	 * @return scopeCatalog
	 */
	public JDBCValue getScopeCatalog() {

		return scopeCatalog;
	}

	/**
	 * @param scopeCatalog
	 *            セットする scopeCatalog
	 */
	public void setScopeCatalog(JDBCValue scopeCatalog) {

		this.scopeCatalog = scopeCatalog;
	}

	/**
	 * @return scopeSchema
	 */
	public JDBCValue getScopeSchema() {

		return scopeSchema;
	}

	/**
	 * @param scopeSchema
	 *            セットする scopeSchema
	 */
	public void setScopeSchema(JDBCValue scopeSchema) {

		this.scopeSchema = scopeSchema;
	}

	/**
	 * @return scopeTable
	 */
	public JDBCValue getScopeTable() {

		return scopeTable;
	}

	/**
	 * @param scopeTable
	 *            セットする scopeTable
	 */
	public void setScopeTable(JDBCValue scopeTable) {

		this.scopeTable = scopeTable;
	}

	/**
	 * @return sourceDataType
	 */
	public JDBCValue getSourceDataType() {

		return sourceDataType;
	}

	/**
	 * @param sourceDataType
	 *            セットする sourceDataType
	 */
	public void setSourceDataType(JDBCValue sourceDataType) {

		this.sourceDataType = sourceDataType;
	}

	/**
	 * @return isAutoIncrement
	 */
	public JDBCValue getIsAutoIncrement() {

		return isAutoIncrement;
	}

	/**
	 * @param isAutoIncrement
	 *            セットする isAutoIncrement
	 */
	public void setIsAutoIncrement(JDBCValue isAutoIncrement) {

		this.isAutoIncrement = isAutoIncrement;
	}

	/**
	 * @return isGeneratedColumn
	 */
	public JDBCValue getIsGeneratedColumn() {

		return isGeneratedColumn;
	}

	/**
	 * @param isGeneratedColumn
	 *            セットする isGeneratedColumn
	 */
	public void setIsGeneratedColumn(JDBCValue isGeneratedColumn) {

		this.isGeneratedColumn = isGeneratedColumn;
	}

	/**
	 * @return primaryKey
	 */
	public JDBCValue getPrimaryKey() {

		return primaryKey;
	}

	/**
	 * @param primaryKey
	 *            セットする primaryKey
	 */
	public void setPrimaryKey(JDBCValue primaryKey) {

		this.primaryKey = primaryKey;
	}

	/**
	 * @return keySequence
	 */
	public JDBCValue getKeySequence() {

		return keySequence;
	}

	/**
	 * @param keySequence
	 *            セットする keySequence
	 */
	public void setKeySequence(JDBCValue keySequence) {

		this.keySequence = keySequence;
	}

}
