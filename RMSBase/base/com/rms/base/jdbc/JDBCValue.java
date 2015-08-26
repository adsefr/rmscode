package com.rms.base.jdbc;

import java.math.BigDecimal;
import java.util.Calendar;

import com.rms.base.jdbc.constant.ValueType;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/02/26
 */
public interface JDBCValue {

	public void setValueType(int type);

	public void setValueType(ValueType valueType);

	public void setRawValue(Object rawValue);

	public ValueType getValueType();

	public boolean isNull();

	public Object rawValue();

	public String stringValue();

	public BigDecimal NumberValue();

	public Calendar dateValue();

	public boolean isType(Class<?> clazz);

	public <T> T getValue(Class<T> clazz);
}
