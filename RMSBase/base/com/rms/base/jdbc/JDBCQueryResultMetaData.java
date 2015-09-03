package com.rms.base.jdbc;

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

	public Integer getColumnNumber(String columnName);

	public String getColumnName(int columnNumber);

	public QueryResultColumnMeta getColumnMeta(int columnNumber);

	public QueryResultColumnMeta getColumnMeta(String columnName);
}
