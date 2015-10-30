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
		this.jdbcValue = new JDBCValue(rawValue);
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

		return jdbcValue == null || jdbcValue.isNull();
	}

	@Override
	public JDBCValue getJDBCValue() {

		return jdbcValue;
	}

	@Override
	public <T> T getRawValue() {

		return jdbcValue.getValue();
	}

	/**
	 * @return rawValue
	 */
	@Override
	public Object getObjectValue() {

		return jdbcValue.getObjectValue();
	}

	@Override
	public String getStringValue() {

		return jdbcValue.getStringValue();
	}

	@Override
	public BigDecimal getNumberValue() {

		return jdbcValue.getNumberValue();
	}

	@Override
	public Calendar getCalendarValue() {

		return jdbcValue.getCalendarValue();
	}

}
