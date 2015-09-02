package com.rms.base.jdbc.model;

import java.math.BigDecimal;
import java.util.Calendar;

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

	public <T> T getValue();

	public Object rawValue();

	public String stringValue();

	public BigDecimal numberValue();

	public Calendar dateValue();
}
