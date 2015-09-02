package com.rms.base.jdbc;

import java.math.BigDecimal;
import java.util.Calendar;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/02/26
 */
public interface JDBCValue {

	public boolean isNull();

	public Object rawValue();

	public String stringValue();

	public BigDecimal numberValue();

	public Calendar dateValue();

	public boolean isType(Class<?> clazz);

	public <T> T getValue();
}
