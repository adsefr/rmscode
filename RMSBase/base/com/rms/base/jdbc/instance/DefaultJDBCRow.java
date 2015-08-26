package com.rms.base.jdbc.instance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rms.base.jdbc.JDBCValue;
import com.rms.base.jdbc.model.JDBCRow;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/02/12
 */
public class DefaultJDBCRow implements JDBCRow {

	private List<DefaultJDBCColumn> columnList = new ArrayList<DefaultJDBCColumn>();

	private Map<String, DefaultJDBCColumn> columnMap = new HashMap<String, DefaultJDBCColumn>();

	public DefaultJDBCRow() {

	}

	public boolean isExist(int columnNumber) {

		return columnNumber >= 1 && columnNumber <= columnList.size();
	}

	public boolean isExist(String columnName) {

		return columnName != null && columnMap.containsKey(columnName);
	}

	/**
	 *
	 * @param column
	 */
	public void addColumn(DefaultJDBCColumn column) {

		if (column != null) {
			columnList.add(column);
			if (column.getColumnName() != null) {
				columnMap.put(column.getColumnName(), column);
			}
		}
	}

	/**
	 *
	 * @param columnNumber
	 *            1,2,.....からの数字
	 * @return
	 */
	public Object getRawValue(int columnNumber) {

		Object value = null;

		if (isExist(columnNumber)) {
			value = columnList.get(columnNumber - 1).getRawValue();
		}

		return value;
	}

	/**
	 *
	 * @param columnName
	 * @return
	 */
	public Object getRawValue(String columnName) {

		Object value = null;

		if (isExist(columnName)) {
			value = columnMap.get(columnName).getRawValue();
		}

		return value;
	}

	public JDBCValue getJDBCValue(int columnNumber) {

		JDBCValue jdbcValue = null;

		if (isExist(columnNumber)) {
			jdbcValue = columnList.get(columnNumber - 1).getJdbcValue();
		}
		return jdbcValue;
	}

	public JDBCValue getJDBCValue(String columnName) {

		JDBCValue jdbcValue = null;

		if (isExist(columnName)) {
			jdbcValue = columnMap.get(columnName).getJdbcValue();
		}
		return jdbcValue;
	}

	/**
	 *
	 * @param columnNumber
	 * @param clazz
	 * @return
	 */
	public <T> T getValue(int columnNumber, Class<T> clazz) {

		T value = null;

		if (isExist(columnNumber) && clazz != null) {
			value = clazz.cast(getRawValue(columnNumber));
		}

		return value;
	}

	/**
	 *
	 * @param columnName
	 * @param clazz
	 * @return
	 */
	public <T> T getValue(String columnName, Class<T> clazz) {

		T value = null;

		if (isExist(columnName) && clazz != null) {
			value = clazz.cast(getRawValue(columnName));
		}

		return value;
	}

}
