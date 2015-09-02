package com.rms.base.jdbc.model;

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

	public <T> T getValue(int columnNumber);

	public <T> T getValue(String columnName);

	public JDBCColumn getColumn(int columnNumber);

	public JDBCColumn getColumn(String columnName);

	public void addColumn(JDBCColumn jdbcColumn);
}
