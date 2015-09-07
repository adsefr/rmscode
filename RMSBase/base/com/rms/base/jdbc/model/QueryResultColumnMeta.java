package com.rms.base.jdbc.model;

import java.sql.JDBCType;

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

	public JDBCType getColumnType();

	public void setColumnType(JDBCType columnType);

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

	public boolean isAutoIncrement();

	public void setAutoIncrement(boolean autoIncrement);

	public boolean isCaseSensitive();

	public void setCaseSensitive(boolean caseSensitive);

	public boolean isCurrency();

	public void setCurrency(boolean currency);

	public boolean isDefinitelyWritable();

	public void setDefinitelyWritable(boolean definitelyWritable);

	public boolean isReadOnly();

	public void setReadOnly(boolean readOnly);

	public boolean isSearchable();

	public void setSearchable(boolean searchable);

	public boolean isSigned();

	public void setSigned(boolean signed);

	public boolean isWritable();

	public void setWritable(boolean writable);

	public Integer getNullable();

	public void setNullable(Integer nullable);

}
