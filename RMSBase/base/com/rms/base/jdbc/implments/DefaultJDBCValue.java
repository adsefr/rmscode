package com.rms.base.jdbc.implments;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.JDBCType;
import java.util.Calendar;

import com.rms.base.exception.UnexpectedTypeException;
import com.rms.base.jdbc.JDBCValue;
import com.rms.base.validate.Assertion;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/02/27
 */
class DefaultJDBCValue implements JDBCValue {

	private Object rawValue = null;

	private JDBCType jdbcType = null;

	public DefaultJDBCValue(Object rawValue) {

		this.rawValue = rawValue;
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

		switch (jdbcType) {
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
			throw new UnexpectedTypeException(jdbcType.getClass().getName());
		}
	}

	@Override
	public BigDecimal numberValue() {

		switch (jdbcType) {
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
			throw new UnexpectedTypeException(jdbcType.getClass().getName());
		}
	}

	@Override
	public Calendar dateValue() {

		switch (jdbcType) {
		case TIME:
		case DATE:
		case TIMESTAMP:
			Date date = Date.class.cast(rawValue);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			return calendar;
		default:
			throw new UnexpectedTypeException(jdbcType.getClass().getName());
		}
	}

	@Override
	public boolean isType(Class<?> clazz) {

		Assertion.assertNotNull("clazz", clazz);

		return rawValue != null && clazz.isInstance(rawValue);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getValue() {

		return (T) rawValue;
	}
}
