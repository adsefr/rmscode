package com.rms.base.jdbc.implments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rms.base.jdbc.JDBCValue;
import com.rms.base.jdbc.model.JDBCColumn;
import com.rms.base.jdbc.model.JDBCRow;
import com.rms.base.util.ArrayUtil;
import com.rms.base.validate.Assertion;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/02/12
 */
public class DefaultJDBCRow implements JDBCRow {

	private Integer rowNumber;

	private List<JDBCColumn> jdbcColumnCollection = new ArrayList<>();

	private Map<Integer, JDBCColumn> jdbcColumnNumberMap = new HashMap<>();

	private Map<String, JDBCColumn> jdbcColumnNameMap = new HashMap<>();

	public DefaultJDBCRow() {

	}

	@Override
	public int getColumnCount() {

		return jdbcColumnCollection.size() - 1;
	}

	@Override
	public int getRowNumber() {

		return rowNumber;
	}

	@Override
	public void setRowNumber(int rowNumber) {

		this.rowNumber = rowNumber;
	}

	@Override
	public final boolean exist(int columnNumber) {

		return jdbcColumnNumberMap.containsKey(columnNumber);
	}

	@Override
	public final boolean exist(String columnName) {

		Assertion.assertNotBlank("columnName", columnName);

		return jdbcColumnNameMap.containsKey(columnName.toUpperCase());
	}

	@Override
	public JDBCValue getJDBCValue(int columnNumber) {

		if (exist(columnNumber)) {
			return getJDBCColumn(columnNumber).getJDBCValue();
		}

		return null;
	}

	@Override
	public JDBCValue getJDBCValue(String columnName) {

		Assertion.assertNotNull("columnName", columnName);

		if (exist(columnName)) {
			return jdbcColumnNameMap.get(columnName.toUpperCase()).getJDBCValue();
		}

		return null;
	}

	@Override
	public JDBCColumn getJDBCColumn(int columnNumber) {

		return jdbcColumnNumberMap.get(columnNumber);
	}

	@Override
	public JDBCColumn getJDBCColumn(String columnName) {

		Assertion.assertNotNull("columnName", columnName);

		return jdbcColumnNameMap.get(columnName.toUpperCase());
	}

	@Override
	public void addJDBCColumn(JDBCColumn jdbcColumn) {

		Assertion.assertNotNull("jdbcColumn", jdbcColumn);
		Assertion.assertNotNull("jdbcColumn.getColumnNumber()", jdbcColumn.getColumnNumber());
		Assertion.assertNotBlank("jdbcColumn.getColumnName()", jdbcColumn.getColumnName());

		ArrayUtil.add(jdbcColumnCollection, jdbcColumn.getColumnNumber(), jdbcColumn);
		jdbcColumnNumberMap.put(jdbcColumn.getColumnNumber(), jdbcColumn);
		jdbcColumnNameMap.put(jdbcColumn.getColumnName().toUpperCase(), jdbcColumn);
	}
}
