package com.rms.base.jdbc.implments;

import java.sql.JDBCType;

import com.rms.base.jdbc.model.QueryResultColumnMeta;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/02/26
 */
public class DefaultQueryResultColumnMeta implements QueryResultColumnMeta {

	private Integer columnNumber;

	private String catalogName;

	private String schemaName;

	private String tableName;

	private String columnName;

	private String columnClassName;

	private JDBCType columnType;

	private String columnTypeName;

	private String columnLabel;

	private Integer columnDisplaySize;

	private Integer precision;

	private Integer scale;

	private boolean autoIncrement;

	private boolean caseSensitive;

	private boolean currency;

	private boolean definitelyWritable;

	private boolean readOnly;

	private boolean searchable;

	private boolean signed;

	private boolean writable;

	private Integer nullable;

	public DefaultQueryResultColumnMeta() {

	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append("columnNumber=").append(columnNumber).append(", ");
		sb.append("catalogName=").append(catalogName).append(", ");
		sb.append("schemaName=").append(schemaName).append(", ");
		sb.append("tableName=").append(tableName).append(", ");
		sb.append("columnName=").append(columnName).append(", ");
		sb.append("columnClassName=").append(columnClassName).append(", ");
		sb.append("columnType=").append(columnType).append(", ");
		sb.append("columnTypeName=").append(columnTypeName).append(", ");
		sb.append("columnLabel=").append(columnLabel).append(", ");
		sb.append("columnDisplaySize=").append(columnDisplaySize).append(", ");
		sb.append("precision=").append(precision).append(", ");
		sb.append("scale=").append(scale).append(", ");
		sb.append("autoIncrement=").append(autoIncrement).append(", ");
		sb.append("caseSensitive=").append(caseSensitive).append(", ");
		sb.append("currency=").append(currency).append(", ");
		sb.append("definitelyWritable=").append(definitelyWritable).append(", ");
		sb.append("readOnly=").append(readOnly).append(", ");
		sb.append("searchable=").append(searchable).append(", ");
		sb.append("signed=").append(signed).append(", ");
		sb.append("writable=").append(writable).append(", ");
		sb.append("nullable=").append(nullable);

		return sb.toString();
	}

	/**
	 * @return columnNumber
	 */
	@Override
	public Integer getColumnNumber() {

		return columnNumber;
	}

	/**
	 * @param columnNumber
	 *            セットする columnNumber
	 */
	@Override
	public void setColumnNumber(Integer columnNumber) {

		this.columnNumber = columnNumber;
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
	@Override
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
	@Override
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
	@Override
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
	@Override
	public void setColumnName(String columnName) {

		this.columnName = columnName;
	}

	/**
	 * @return columnClassName
	 */
	@Override
	public String getColumnClassName() {

		return columnClassName;
	}

	/**
	 * @param columnClassName
	 *            セットする columnClassName
	 */
	@Override
	public void setColumnClassName(String columnClassName) {

		this.columnClassName = columnClassName;
	}

	/**
	 * @return columnType
	 */
	@Override
	public JDBCType getColumnType() {

		return columnType;
	}

	/**
	 * @param columnType
	 *            セットする columnType
	 */
	@Override
	public void setColumnType(JDBCType columnType) {

		this.columnType = columnType;
	}

	/**
	 * @return columnTypeName
	 */
	@Override
	public String getColumnTypeName() {

		return columnTypeName;
	}

	/**
	 * @param columnTypeName
	 *            セットする columnTypeName
	 */
	@Override
	public void setColumnTypeName(String columnTypeName) {

		this.columnTypeName = columnTypeName;
	}

	/**
	 * @return columnLabel
	 */
	@Override
	public String getColumnLabel() {

		return columnLabel;
	}

	/**
	 * @param columnLabel
	 *            セットする columnLabel
	 */
	@Override
	public void setColumnLabel(String columnLabel) {

		this.columnLabel = columnLabel;
	}

	/**
	 * @return columnDisplaySize
	 */
	@Override
	public Integer getColumnDisplaySize() {

		return columnDisplaySize;
	}

	/**
	 * @param columnDisplaySize
	 *            セットする columnDisplaySize
	 */
	@Override
	public void setColumnDisplaySize(Integer columnDisplaySize) {

		this.columnDisplaySize = columnDisplaySize;
	}

	/**
	 * @return precision
	 */
	@Override
	public Integer getPrecision() {

		return precision;
	}

	/**
	 * @param precision
	 *            セットする precision
	 */
	@Override
	public void setPrecision(Integer precision) {

		this.precision = precision;
	}

	/**
	 * @return scale
	 */
	@Override
	public Integer getScale() {

		return scale;
	}

	/**
	 * @param scale
	 *            セットする scale
	 */
	@Override
	public void setScale(Integer scale) {

		this.scale = scale;
	}

	/**
	 * @return autoIncrement
	 */
	@Override
	public boolean isAutoIncrement() {

		return autoIncrement;
	}

	/**
	 * @param autoIncrement
	 *            セットする autoIncrement
	 */
	@Override
	public void setAutoIncrement(boolean autoIncrement) {

		this.autoIncrement = autoIncrement;
	}

	/**
	 * @return caseSensitive
	 */
	@Override
	public boolean isCaseSensitive() {

		return caseSensitive;
	}

	/**
	 * @param caseSensitive
	 *            セットする caseSensitive
	 */
	@Override
	public void setCaseSensitive(boolean caseSensitive) {

		this.caseSensitive = caseSensitive;
	}

	/**
	 * @return currency
	 */
	@Override
	public boolean isCurrency() {

		return currency;
	}

	/**
	 * @param currency
	 *            セットする currency
	 */
	@Override
	public void setCurrency(boolean currency) {

		this.currency = currency;
	}

	/**
	 * @return definitelyWritable
	 */
	@Override
	public boolean isDefinitelyWritable() {

		return definitelyWritable;
	}

	/**
	 * @param definitelyWritable
	 *            セットする definitelyWritable
	 */
	@Override
	public void setDefinitelyWritable(boolean definitelyWritable) {

		this.definitelyWritable = definitelyWritable;
	}

	/**
	 * @return readOnly
	 */
	@Override
	public boolean isReadOnly() {

		return readOnly;
	}

	/**
	 * @param readOnly
	 *            セットする readOnly
	 */
	@Override
	public void setReadOnly(boolean readOnly) {

		this.readOnly = readOnly;
	}

	/**
	 * @return searchable
	 */
	@Override
	public boolean isSearchable() {

		return searchable;
	}

	/**
	 * @param searchable
	 *            セットする searchable
	 */
	@Override
	public void setSearchable(boolean searchable) {

		this.searchable = searchable;
	}

	/**
	 * @return signed
	 */
	@Override
	public boolean isSigned() {

		return signed;
	}

	/**
	 * @param signed
	 *            セットする signed
	 */
	@Override
	public void setSigned(boolean signed) {

		this.signed = signed;
	}

	/**
	 * @return writable
	 */
	@Override
	public boolean isWritable() {

		return writable;
	}

	/**
	 * @param writable
	 *            セットする writable
	 */
	@Override
	public void setWritable(boolean writable) {

		this.writable = writable;
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
	@Override
	public void setNullable(Integer nullable) {

		this.nullable = nullable;
	}

}
