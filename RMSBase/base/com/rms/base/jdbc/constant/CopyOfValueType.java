package com.rms.base.jdbc.constant;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;

import com.rms.base.exception.UnexpectedTypeException;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/02/26
 */
public enum CopyOfValueType {

	OBJECT(Object.class), //

	BOOLEAN(boolean.class, Boolean.class), //

	BYTE(byte.class, Byte.class), //

	SHORT(short.class, Short.class), //

	CHARACTER(char.class, Character.class), //

	INTEGER(int.class, Integer.class), //

	LONG(long.class, Long.class), //

	FLOAT(float.class, Float.class), //

	DOUBLE(double.class, Double.class), //

	BIGDECIMAL(BigDecimal.class), //

	STRING(String.class), //

	CALENDAR(Calendar.class), //

	TIMESTAMP(Timestamp.class), //

	;

	private Class<?>[] classTypes;

	/**
	 * @return classTypes
	 */
	private Class<?>[] getClassTypes() {

		return classTypes;
	}

	private CopyOfValueType(Class<?>... classTypes) {

		this.classTypes = classTypes;
	}

	public static CopyOfValueType valueOf(Class<?> clazz) {

		for (CopyOfValueType valueType : CopyOfValueType.values()) {
			for (Class<?> classType : valueType.getClassTypes()) {
				if (classType == clazz) {
					return valueType;
				}
			}
		}

		throw new UnexpectedTypeException("type:" + clazz.getName());
	}
}
