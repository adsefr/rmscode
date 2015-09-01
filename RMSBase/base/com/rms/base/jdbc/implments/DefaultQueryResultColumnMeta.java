package com.rms.base.jdbc.implments;

import com.rms.base.jdbc.model.QueryResultColumnMeta;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/02/26
 */
public class DefaultQueryResultColumnMeta implements QueryResultColumnMeta {

	private Integer columnNumber;

	private String schemaName;

	private String catalogName;

	private String tableName;

	private String columnName;

	private String columnClassName;

	private Integer columnType;

	private String columnTypeName;

	private String columnLabel;

	private Integer columnDisplaySize;

	private Integer precision;

	private Integer scale;

	public DefaultQueryResultColumnMeta() {

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
	public Integer getColumnType() {

		return columnType;
	}

	/**
	 * @param columnType
	 *            セットする columnType
	 */
	@Override
	public void setColumnType(Integer columnType) {

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

	// private boolean readOnly;
	//
	// private boolean writable;
	//
	// private boolean definitelyWritable;
	//
	// private int columnCount;
	//
	// private boolean autoIncrement;
	//
	// private boolean caseSensitive;
	//
	// private boolean searchable;
	//
	// private boolean currency;
	//
	// private int nullable;
	//
	// private boolean signed;

}
