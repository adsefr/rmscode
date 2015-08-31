package com.rms.base.jdbc.implments;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rms.base.jdbc.JDBCValue;
import com.rms.base.jdbc.model.JDBCColumn;
import com.rms.base.jdbc.model.JDBCRow;
import com.rms.base.validate.Assertion;
import com.rms.common.jdbc.JDBCQueryResultMetaData;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/02/12
 */
public class DefaultJDBCRow implements JDBCRow {

	private JDBCQueryResultMetaData jdbcQueryResultMetaData;

	private Map<Integer, String> columnOrderMap = new HashMap<>();

	private List<JDBCColumn> columnList = new ArrayList<>();

	private List<JDBCValue> jdbcValuesCollection = new ArrayList<JDBCValue>(Arrays.asList(JDBCFactory.newJDBCValue()));

	private Map<String, JDBCValue> jdbcValuesMap = new HashMap<>();

	private Map<String, JDBCColumn> columnMap = new HashMap<>();

	public DefaultJDBCRow() {

	}

	@Override
	public int getColumnCount() {

		return columnList.size();
	}

	@Override
	public final boolean exist(int columnNumber) {

		return columnNumber >= 1 && columnNumber <= columnList.size();
	}

	@Override
	public final boolean exist(String columnName) {

		Assertion.assertNotBlank("columnName", columnName);

		return columnMap.containsKey(columnName);
	}

	@Override
	public void addValue(int columnNumber, JDBCValue jdbcValue) {

		jdbcValuesCollection.add(columnNumber, jdbcValue);
	}

	@Override
	public void addValue(String columnName, JDBCValue jdbcValue) {

		int columnNumber = jdbcQueryResultMetaData.getColumnNumber(columnName);

		jdbcValuesCollection.add(columnNumber, jdbcValue);
	}

	@Override
	public JDBCValue getValue(int columnNumber) {

		return jdbcValuesCollection.get(columnNumber);

	}

	@Override
	public JDBCValue getValue(String columnName) {

		return jdbcValuesMap.get(columnName);
	}
}
