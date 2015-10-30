package com.rms.base.jdbc;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.JDBCType;
import java.util.Calendar;

import com.rms.base.exception.UnexpectedTypeException;
import com.rms.base.validate.Assertion;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/02/26
 */
public class JDBCValue {

	private Object rawValue = null;

	private JDBCType jdbcType = null;

	public JDBCValue(Object rawValue) {

		this.rawValue = rawValue;
	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((rawValue == null) ? 0 : rawValue.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JDBCValue other = (JDBCValue) obj;
		if (rawValue == null) {
			if (other.rawValue != null)
				return false;
		} else if (!rawValue.equals(other.rawValue))
			return false;
		return true;
	}

	public Class<?> getType() {

		return null;
	}

	public boolean isType(Class<?> clazz) {

		Assertion.assertNotNull("clazz", clazz);

		return rawValue != null && clazz.isInstance(rawValue);
	}

	public boolean isNull() {

		return (rawValue == null);
	}

	public boolean isNotNull() {

		return (rawValue != null);
	}

	@SuppressWarnings("unchecked")
	public <T> T getValue() {

		return (T) rawValue;
	}

	public Object getObjectValue() {

		return rawValue;
	}

	public String getStringValue() {

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

	public BigDecimal getNumberValue() {

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
				return new BigDecimal(getStringValue());
			default:
				throw new UnexpectedTypeException(jdbcType.getClass().getName());
		}
	}

	public Calendar getCalendarValue() {

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

}
