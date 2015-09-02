package com.rms.base.jdbc.implments;

import java.math.BigDecimal;
import java.util.Calendar;

import com.rms.base.jdbc.JDBCValue;
import com.rms.base.jdbc.model.JDBCColumn;
import com.rms.base.jdbc.model.QueryResultColumnMeta;

/**
 *
 * @author ri.meisei
 * @since 2014/01/30
 */
public class DefaultJDBCColumn implements JDBCColumn {

	private QueryResultColumnMeta queryResultColumnMeta;

	private JDBCValue jdbcValue;

	public DefaultJDBCColumn(QueryResultColumnMeta queryResultColumnMeta, Object rawValue) {

		this.queryResultColumnMeta = queryResultColumnMeta;
		this.jdbcValue = JDBCFactory.newJDBCValue(rawValue);
	}

	@Override
	public Integer getColumnNumber() {

		return queryResultColumnMeta.getColumnNumber();
	}

	/**
	 * @return columnName
	 */
	@Override
	public String getColumnName() {

		return queryResultColumnMeta.getColumnName();
	}

	@Override
	public boolean isNull() {

		return jdbcValue.isNull();
	}

	/**
	 * @return rawValue
	 */
	@Override
	public Object rawValue() {

		return jdbcValue.rawValue();
	}

	@Override
	public String stringValue() {

		return jdbcValue.stringValue();
	}

	@Override
	public BigDecimal numberValue() {

		return jdbcValue.numberValue();
	}

	@Override
	public Calendar dateValue() {

		return jdbcValue.dateValue();
	}

	@Override
	public <T> T getValue() {

		return jdbcValue.getValue();
	}
}