package com.rms.base.jdbc.model;

import com.rms.base.jdbc.JDBCValue;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/01/30
 */
public class DefaultJDBCColumn implements JDBCColumn {

	private DefaultColumnMeta columnMeta;

	private String columnName;

	private Object rawValue;

	private JDBCValue jdbcValue;

	public DefaultJDBCColumn() {

	}

	/**
	 * @return columnMeta
	 */
	public DefaultColumnMeta getColumnMeta() {

		return columnMeta;
	}

	/**
	 * @param columnMeta
	 *            セットする columnMeta
	 */
	public void setColumnMeta(DefaultColumnMeta columnMeta) {

		this.columnMeta = columnMeta;
	}

	/**
	 * @return columnName
	 */
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
	 * @return rawValue
	 */
	public Object getRawValue() {

		return rawValue;
	}

	/**
	 * @param rawValue
	 *            セットする rawValue
	 */
	public void setRawValue(Object rawValue) {

		this.rawValue = rawValue;
	}

	/**
	 * @return jdbcValue
	 */
	public JDBCValue getJdbcValue() {

		return jdbcValue;
	}

	/**
	 * @param jdbcValue
	 *            セットする jdbcValue
	 */
	public void setJdbcValue(JDBCValue jdbcValue) {

		this.jdbcValue = jdbcValue;
	}

}
