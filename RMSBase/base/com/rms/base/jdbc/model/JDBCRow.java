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

	public boolean exist(int columnNumber);

	public boolean exist(String columnName);

	public void addValue(int columnNumber, JDBCValue jdbcValue);

	public void addValue(String columnName, JDBCValue jdbcValue);

	public JDBCValue getValue(int columnNumber);

	public JDBCValue getValue(String columnName);

}
