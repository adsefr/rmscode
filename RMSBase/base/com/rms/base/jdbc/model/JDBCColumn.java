package com.rms.base.jdbc.model;

import java.math.BigDecimal;
import java.util.Calendar;

import com.rms.base.jdbc.JDBCValue;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/01/30
 */
public interface JDBCColumn {

	public Integer getColumnNumber();

	public String getColumnName();

	public boolean isNull();

	public JDBCValue getJDBCValue();

	public <T> T getRawValue();

	public Object getObjectValue();

	public String getStringValue();

	public BigDecimal getNumberValue();

	public Calendar getCalendarValue();

}
