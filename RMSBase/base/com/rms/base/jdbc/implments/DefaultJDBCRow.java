package com.rms.base.jdbc.implments;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.rms.base.jdbc.JDBCValue;
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

	private List<JDBCValue> jdbcValuesCollection = new ArrayList<JDBCValue>(Arrays.asList(JDBCFactory.newJDBCValue()));

	public DefaultJDBCRow() {

	}

	@Override
	public int getColumnCount() {

		return jdbcValuesCollection.size();
	}

	@Override
	public final boolean exist(int columnNumber) {

		return jdbcQueryResultMetaData.hasColumn(columnNumber);
	}

	@Override
	public final boolean exist(String columnName) {

		Assertion.assertNotBlank("columnName", columnName);

		return jdbcQueryResultMetaData.hasColumn(columnName);
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

		return getValue(jdbcQueryResultMetaData.getColumnNumber(columnName));
	}
}
