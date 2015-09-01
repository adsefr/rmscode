package com.rms.base.jdbc.model;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/02/26
 */
public interface QueryResultColumnMeta {

	public Integer getColumnNumber();

	public void setColumnNumber(Integer columnNumber);

	public String getCatalogName();

	public void setCatalogName(String catalogName);

	public String getSchemaName();

	public void setSchemaName(String schemaName);

	public String getTableName();

	public void setTableName(String tableName);

	public String getColumnName();

	public void setColumnName(String columnName);

	public String getColumnClassName();

	public void setColumnClassName(String columnClassName);

	public Integer getColumnType();

	public void setColumnType(Integer columnType);

	public String getColumnTypeName();

	public void setColumnTypeName(String columnTypeName);

	public String getColumnLabel();

	public void setColumnLabel(String columnLabel);

	public Integer getColumnDisplaySize();

	public void setColumnDisplaySize(Integer columnDisplaySize);

	public Integer getPrecision();

	public void setPrecision(Integer precision);

	public Integer getScale();

	public void setScale(Integer scale);

	// public boolean isAutoIncrement();
	//
	// public boolean isCaseSensitive();
	//
	// public boolean isCurrency();
	//
	// public boolean isDefinitelyWritable();
	//
	// public boolean isReadOnly();
	//
	// public boolean isSearchable();
	//
	// public boolean isSigned();
	//
	// public boolean isWritable();
	//
	// public Integer getNullable();

	// public void setAutoIncrement(boolean autoIncrement);
	//
	// public void setCaseSensitive(boolean caseSensitive);
	//
	// public void setCurrency(boolean currency);
	//
	// public void setDefinitelyWritable(boolean definitelyWritable);
	//
	// public void setNullable(Integer nullable);
	//
	// public void setReadOnly(boolean readOnly);
	//
	//
	// public void setSearchable(boolean searchable);
	//
	// public void setSigned(boolean signed);
	//
	// public void setWritable(boolean writable);
}
