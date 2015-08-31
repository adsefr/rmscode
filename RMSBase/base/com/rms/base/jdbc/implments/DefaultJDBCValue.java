package com.rms.base.jdbc.implments;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Calendar;

import com.rms.base.exception.UnexpectedTypeException;
import com.rms.base.jdbc.JDBCValue;
import com.rms.base.jdbc.constant.ValueType;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/02/27
 */
class DefaultJDBCValue implements JDBCValue {

	private Object rawValue = null;

	private ValueType valueType = null;

	public DefaultJDBCValue() {

	}

	@Override
	public int getColumnNumber() {

		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

	@Override
	public int getRowNumber() {

		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

	@Override
	public int setColumnNumber(int columnNumber) {

		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

	@Override
	public int setRowNumber(int rowNumber) {

		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

	@Override
	public void setValueType(int type) {

		this.valueType = ValueType.valueOf(type);
	}

	@Override
	public void setValueType(ValueType valueType) {

		this.valueType = valueType;
	}

	@Override
	public void setRawValue(Object rawValue) {

		this.rawValue = rawValue;
	}

	@Override
	public ValueType getValueType() {

		return valueType;
	}

	@Override
	public boolean isNull() {

		return (rawValue == null);
	}

	@Override
	public Object rawValue() {

		return rawValue;
	}

	@Override
	public String stringValue() {

		if (isNull()) {
			return "";
		}

		switch (valueType) {
		case BIT:
		case BIGINT:
		case TINYINT:
		case SMALLINT:
		case INTEGER:
		case FLOAT:
		case DOUBLE:
		case NUMERIC:
		case DECIMAL:
			return String.valueOf(rawValue);
		case CHAR:
		case VARCHAR:
		case LONGVARCHAR:
		case NCHAR:
		case NVARCHAR:
		case LONGNVARCHAR:
			return String.valueOf(rawValue);

		case BOOLEAN:
			return String.valueOf(rawValue);

		case NULL:
			return "";

		case TIME:
		case DATE:
		case TIMESTAMP:
			return String.valueOf(rawValue);

		default:
			throw new UnexpectedTypeException(valueType.getClass().getName());
		}
	}

	@Override
	public BigDecimal NumberValue() {

		switch (valueType) {
		case BIT:
		case BIGINT:
		case TINYINT:
		case SMALLINT:
		case INTEGER:
		case FLOAT:
		case DOUBLE:
		case NUMERIC:
		case DECIMAL:
			return new BigDecimal(stringValue());
		default:
			throw new UnexpectedTypeException(valueType.getClass().getName());
		}
	}

	@Override
	public Calendar dateValue() {

		switch (valueType) {
		case TIME:
		case DATE:
		case TIMESTAMP:
			Date date = Date.class.cast(rawValue);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			return calendar;
		default:
			throw new UnexpectedTypeException(valueType.getClass().getName());
		}
	}

	@Override
	public boolean isType(Class<?> clazz) {

		return clazz != null && clazz.isInstance(rawValue);
	}

	@Override
	public <T> T getValue(Class<T> clazz) {

		return clazz.cast(rawValue);
	}

}
