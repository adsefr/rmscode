package com.rms.base.jdbc.implments;

import com.rms.base.jdbc.model.ColumnMeta;


/**
 *
 *
 * @author ri.meisei
 * @since 2014/02/26
 */
public class DefaultColumnMeta implements ColumnMeta {

	/** TABLE_CAT String => テーブルカタログ (null の可能性がある) */
	private String catalogName;

	/** TABLE_SCHEM String => テーブルスキーマ (null の可能性がある) */
	private String schemaName;

	/** TABLE_NAME String => テーブル名 */
	private String tableName;

	/** COLUMN_NAME String => 列名 */
	private String columnName;

	/** DATA_TYPE int => java.sql.Types からの SQL の型 */
	private Integer dataType;

	/** TYPE_NAME String => データソース依存の型名。UDT の場合、型名は完全指定 */
	private String typeName;

	/** COLUMN_SIZE int => 列サイズ */
	private Integer columnSize;

	/** BUFFER_LENGTH - 未使用。 */

	/**
	 * DECIMAL_DIGITS int => 小数点以下の桁数。DECIMAL_DIGITS が適用できないデータ型の場合は、Null が返される。
	 */
	private Integer decimalDits;

	/** NUM_PREC_RADIX int => 基数 (通常は、10 または 2 のどちらか) */
	private Integer numPrecRadix;

	/**
	 * NULLABLE int => NULL は許されるか。<br>
	 * columnNoNulls - NULL 値を許さない可能性がある<br>
	 * columnNullable - 必ず NULL 値を許す<br>
	 * columnNullableUnknown - NULL 値を許すかどうかは不明<br>
	 */
	private Integer nullable;

	/** REMARKS String => 列を記述するコメント (null の可能性がある) */
	private String remarks;

	/** COLUMN_DEF String => 列のデフォルト値。単一引用符で囲まれた値は、文字列として解釈されるべき (null の可能性がある) */
	private String columnDefaultValue;

	/** SQL_DATA_TYPE int => 未使用 */

	/** SQL_DATETIME_SUB int => 未使用 */

	/** CHAR_OCTET_LENGTH int => char の型については列の最大バイト数 */
	private String charOctetLength;

	/** ORDINAL_POSITION int => テーブル中の列のインデックス (1 から始まる) */
	private Integer ordinalPosition;

	/**
	 * IS_NULLABLE String => 列で NULL 値を許可するかどうかの判断に ISO 規則が使用される。<br>
	 * YES --- 列が NULL を許可する場合<br>
	 * NO --- 列が NULL を許可しない場合<br>
	 * 空の文字列 --- 列が NULL 値を許可するかどうか不明である場合<br>
	 */
	private String isNullable;

	/**
	 * SCOPE_CATALOG String => 参照属性のスコープであるテーブルのカタログ (DATA_TYPE が REF でない場合は
	 * null)
	 */
	private String scopeCatalog;

	/**
	 * SCOPE_SCHEMA String => 参照属性のスコープであるテーブルのスキーマ (DATA_TYPE が REF でない場合は
	 * null)
	 */
	private String scopeSchema;

	/** SCOPE_TABLE String => 参照属性のスコープであるテーブル名 (DATA_TYPE が REF でない場合は null) */
	private String scopeTable;

	/**
	 * SOURCE_DATA_TYPE short => 個別の型またはユーザー生成 Ref 型のソースの型、java.sql.Types の SQL
	 * 型 (DATA_TYPE が DISTINCT またはユーザー生成 REF でない場合は null)
	 */
	private Integer sourceDataType;

	/**
	 * IS_AUTOINCREMENT String => この列が自動インクリメントされるかどうかを示す<br>
	 * YES --- 列が自動インクリメントされる場合<br>
	 * NO --- 列が自動インクリメントされない場合<br>
	 * 空の文字列 --- 列が自動インクリメントされるかどうかが判断できない場合<br>
	 */
	private String isAutoIncrement;

	/**
	 * IS_GENERATEDCOLUMN String => これが生成された列かどうかを示す<br>
	 * YES --- これが生成された列である場合<br>
	 * NO --- これが生成された列でない場合<br>
	 * 空の文字列 --- これが生成された列かどうかが判断できない場合<br>
	 */
	private String isGeneratedColumn;

	private boolean primaryKey;

	private Integer keySequence;

	public DefaultColumnMeta() {

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
	 * @return columnName
	 */
	@Override
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
	 * @return dataType
	 */
	@Override
	public Integer getDataType() {

		return dataType;
	}

	/**
	 * @param dataType
	 *            セットする dataType
	 */
	public void setDataType(Integer dataType) {

		this.dataType = dataType;
	}

	/**
	 * @return typeName
	 */
	@Override
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
	 * @return columnSize
	 */
	@Override
	public Integer getColumnSize() {

		return columnSize;
	}

	/**
	 * @param columnSize
	 *            セットする columnSize
	 */
	public void setColumnSize(Integer columnSize) {

		this.columnSize = columnSize;
	}

	/**
	 * @return decimalDits
	 */
	@Override
	public Integer getDecimalDits() {

		return decimalDits;
	}

	/**
	 * @param decimalDits
	 *            セットする decimalDits
	 */
	public void setDecimalDits(Integer decimalDits) {

		this.decimalDits = decimalDits;
	}

	/**
	 * @return numPrecRadix
	 */
	@Override
	public Integer getNumPrecRadix() {

		return numPrecRadix;
	}

	/**
	 * @param numPrecRadix
	 *            セットする numPrecRadix
	 */
	public void setNumPrecRadix(Integer numPrecRadix) {

		this.numPrecRadix = numPrecRadix;
	}

	/**
	 * @return nullable
	 */
	@Override
	public Integer getNullable() {

		return nullable;
	}

	/**
	 * @param nullable
	 *            セットする nullable
	 */
	public void setNullable(Integer nullable) {

		this.nullable = nullable;
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
	 * @return columnDefaultValue
	 */
	@Override
	public String getColumnDefaultValue() {

		return columnDefaultValue;
	}

	/**
	 * @param columnDefaultValue
	 *            セットする columnDefaultValue
	 */
	public void setColumnDefaultValue(String columnDefaultValue) {

		this.columnDefaultValue = columnDefaultValue;
	}

	/**
	 * @return charOctetLength
	 */
	public String getCharOctetLength() {

		return charOctetLength;
	}

	/**
	 * @param charOctetLength
	 *            セットする charOctetLength
	 */
	public void setCharOctetLength(String charOctetLength) {

		this.charOctetLength = charOctetLength;
	}

	/**
	 * @return ordinalPosition
	 */
	public Integer getOrdinalPosition() {

		return ordinalPosition;
	}

	/**
	 * @param ordinalPosition
	 *            セットする ordinalPosition
	 */
	public void setOrdinalPosition(Integer ordinalPosition) {

		this.ordinalPosition = ordinalPosition;
	}

	/**
	 * @return isNullable
	 */
	public String getIsNullable() {

		return isNullable;
	}

	/**
	 * @param isNullable
	 *            セットする isNullable
	 */
	public void setIsNullable(String isNullable) {

		this.isNullable = isNullable;
	}

	/**
	 * @return scopeCatalog
	 */
	public String getScopeCatalog() {

		return scopeCatalog;
	}

	/**
	 * @param scopeCatalog
	 *            セットする scopeCatalog
	 */
	public void setScopeCatalog(String scopeCatalog) {

		this.scopeCatalog = scopeCatalog;
	}

	/**
	 * @return scopeSchema
	 */
	public String getScopeSchema() {

		return scopeSchema;
	}

	/**
	 * @param scopeSchema
	 *            セットする scopeSchema
	 */
	public void setScopeSchema(String scopeSchema) {

		this.scopeSchema = scopeSchema;
	}

	/**
	 * @return scopeTable
	 */
	public String getScopeTable() {

		return scopeTable;
	}

	/**
	 * @param scopeTable
	 *            セットする scopeTable
	 */
	public void setScopeTable(String scopeTable) {

		this.scopeTable = scopeTable;
	}

	/**
	 * @return sourceDataType
	 */
	public Integer getSourceDataType() {

		return sourceDataType;
	}

	/**
	 * @param sourceDataType
	 *            セットする sourceDataType
	 */
	public void setSourceDataType(Integer sourceDataType) {

		this.sourceDataType = sourceDataType;
	}

	/**
	 * @return isAutoIncrement
	 */
	public String getIsAutoIncrement() {

		return isAutoIncrement;
	}

	/**
	 * @param isAutoIncrement
	 *            セットする isAutoIncrement
	 */
	public void setIsAutoIncrement(String isAutoIncrement) {

		this.isAutoIncrement = isAutoIncrement;
	}

	/**
	 * @return isGeneratedColumn
	 */
	public String getIsGeneratedColumn() {

		return isGeneratedColumn;
	}

	/**
	 * @param isGeneratedColumn
	 *            セットする isGeneratedColumn
	 */
	public void setIsGeneratedColumn(String isGeneratedColumn) {

		this.isGeneratedColumn = isGeneratedColumn;
	}

	/**
	 * @return primaryKey
	 */
	@Override
	public boolean isPrimaryKey() {

		return primaryKey;
	}

	/**
	 * @param primaryKey
	 *            セットする primaryKey
	 */
	public void setPrimaryKey(boolean primaryKey) {

		this.primaryKey = primaryKey;
	}

	/**
	 * @return keySequence
	 */
	@Override
	public Integer getKeySequence() {

		return keySequence;
	}

	/**
	 * @param keySequence
	 *            セットする keySequence
	 */
	public void setKeySequence(Integer keySequence) {

		this.keySequence = keySequence;
	}

}
