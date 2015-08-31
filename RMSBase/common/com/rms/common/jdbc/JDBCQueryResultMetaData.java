package com.rms.common.jdbc;

import com.rms.base.jdbc.model.QueryResultColumnMeta;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/02/24
 */
public interface JDBCQueryResultMetaData {

	public int getColumnCount();

	public boolean hasColumn(int columnNumber);

	public boolean hasColumn(String columnName);

	public QueryResultColumnMeta getColumn(int columnNumber);

	public QueryResultColumnMeta getColumn(String columnName);

	public void addColumnMeta(QueryResultColumnMeta queryResultColumnMeta);

	// public String getCatalogName(int column);
	//
	// public String getColumnClassName(int column);
	//
	// public int getColumnCount();
	//
	// public int getColumnNumber(String columnName);
	//
	// public int getColumnDisplaySize(int column);
	//
	// public String getColumnLabel(int column);
	//
	// public String getColumnName(int column);
	//
	// public int getColumnType(int column);
	//
	// public String getColumnTypeName(int column);
	//
	// public int getPrecision(int column);
	//
	// public int getScale(int column);
	//
	// public String getSchemaName(int column);
	//
	// public String getTableName(int column);
	//
	// public boolean isAutoIncrement(int column);
	//
	// public boolean isCaseSensitive(int column);
	//
	// public boolean isCurrency(int column);
	//
	// public boolean isDefinitelyWritable(int column);
	//
	// public int isNullable(int column);
	//
	// public boolean isReadOnly(int column);
	//
	// public boolean isSearchable(int column);
	//
	// public boolean isSigned(int column);
	//
	// public boolean isWritable(int column);
}
