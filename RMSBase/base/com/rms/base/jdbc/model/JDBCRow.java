package com.rms.base.jdbc.model;

import com.rms.base.jdbc.JDBCValue;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/02/12
 */
public interface JDBCRow {

	public int getColumnCount();

	public int getRowNumber();

	public void setRowNumber(int rowNumber);

	public boolean exist(int columnNumber);

	public boolean exist(String columnName);

	public JDBCValue getJDBCValue(int columnNumber);

	public JDBCValue getJDBCValue(String columnName);

	public JDBCColumn getJDBCColumn(int columnNumber);

	public JDBCColumn getJDBCColumn(String columnName);

	public void addJDBCColumn(JDBCColumn jdbcColumn);
}
