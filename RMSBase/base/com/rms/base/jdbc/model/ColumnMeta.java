package com.rms.base.jdbc.model;

import java.sql.JDBCType;

import com.rms.base.jdbc.JDBCValue;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/02/26
 */
public class ColumnMeta {

	private TableMeta tableMeta;

	/** TABLE_CAT String => テーブルカタログ (null の可能性がある) */
	private JDBCValue catalogName;

	/** TABLE_SCHEM String => テーブルスキーマ (null の可能性がある) */
	private JDBCValue schemaName;

	/** TABLE_NAME String => テーブル名 */
	private JDBCValue tableName;

	/** COLUMN_NAME String => 列名 */
	private JDBCValue columnName;

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

	public JDBCValue getCatalogName() {

		return catalogName;
	}

	public void setCatalogName(JDBCValue catalogName) {

		this.catalogName = catalogName;
	}

	public JDBCValue getSchemaName() {

		return schemaName;
	}

	public void setSchemaName(JDBCValue schemaName) {

		this.schemaName = schemaName;
	}

	public JDBCValue getTableName() {

		return tableName;
	}

	public void setTableName(JDBCValue tableName) {

		this.tableName = tableName;
	}

	public JDBCValue getColumnName() {

		return columnName;
	}

	public void setColumnName(JDBCValue columnName) {

		this.columnName = columnName;
	}

	public JDBCType getJdbcType() {

		return jdbcType;
	}

	public void setJdbcType(JDBCType jdbcType) {

		this.jdbcType = jdbcType;
	}

	public JDBCValue getTypeName() {

		return typeName;
	}

	public void setTypeName(JDBCValue typeName) {

		this.typeName = typeName;
	}

	public JDBCValue getColumnSize() {

		return columnSize;
	}

	public void setColumnSize(JDBCValue columnSize) {

		this.columnSize = columnSize;
	}

	public JDBCValue getDecimalDits() {

		return decimalDits;
	}

	public void setDecimalDits(JDBCValue decimalDits) {

		this.decimalDits = decimalDits;
	}

	public JDBCValue getNumPrecRadix() {

		return numPrecRadix;
	}

	public void setNumPrecRadix(JDBCValue numPrecRadix) {

		this.numPrecRadix = numPrecRadix;
	}

	public JDBCValue getNullable() {

		return nullable;
	}

	public void setNullable(JDBCValue nullable) {

		this.nullable = nullable;
	}

	public JDBCValue getRemarks() {

		return remarks;
	}

	public void setRemarks(JDBCValue remarks) {

		this.remarks = remarks;
	}

	public JDBCValue getColumnDefaultValue() {

		return columnDefaultValue;
	}

	public void setColumnDefaultValue(JDBCValue columnDefaultValue) {

		this.columnDefaultValue = columnDefaultValue;
	}

	public JDBCValue getCharOctetLength() {

		return charOctetLength;
	}

	public void setCharOctetLength(JDBCValue charOctetLength) {

		this.charOctetLength = charOctetLength;
	}

	public JDBCValue getOrdinalPosition() {

		return ordinalPosition;
	}

	public void setOrdinalPosition(JDBCValue ordinalPosition) {

		this.ordinalPosition = ordinalPosition;
	}

	public JDBCValue getIsNullable() {

		return isNullable;
	}

	public void setIsNullable(JDBCValue isNullable) {

		this.isNullable = isNullable;
	}

	public JDBCValue getScopeCatalog() {

		return scopeCatalog;
	}

	public void setScopeCatalog(JDBCValue scopeCatalog) {

		this.scopeCatalog = scopeCatalog;
	}

	public JDBCValue getScopeSchema() {

		return scopeSchema;
	}

	public void setScopeSchema(JDBCValue scopeSchema) {

		this.scopeSchema = scopeSchema;
	}

	public JDBCValue getScopeTable() {

		return scopeTable;
	}

	public void setScopeTable(JDBCValue scopeTable) {

		this.scopeTable = scopeTable;
	}

	public JDBCValue getSourceDataType() {

		return sourceDataType;
	}

	public void setSourceDataType(JDBCValue sourceDataType) {

		this.sourceDataType = sourceDataType;
	}

	public JDBCValue getIsAutoIncrement() {

		return isAutoIncrement;
	}

	public void setIsAutoIncrement(JDBCValue isAutoIncrement) {

		this.isAutoIncrement = isAutoIncrement;
	}

	public JDBCValue getIsGeneratedColumn() {

		return isGeneratedColumn;
	}

	public void setIsGeneratedColumn(JDBCValue isGeneratedColumn) {

		this.isGeneratedColumn = isGeneratedColumn;
	}

	public JDBCValue getPrimaryKey() {

		return primaryKey;
	}

	public void setPrimaryKey(JDBCValue primaryKey) {

		this.primaryKey = primaryKey;
	}

	public JDBCValue getKeySequence() {

		return keySequence;
	}

	public void setKeySequence(JDBCValue keySequence) {

		this.keySequence = keySequence;
	}

}
