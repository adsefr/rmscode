package com.rms.base.enumeration;

import java.math.BigDecimal;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.JDBCType;
import java.sql.Ref;
import java.sql.Struct;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Types;

import com.rms.base.exception.UnexpectedTypeException;
import com.rms.base.validate.Assertion;

/**
 *
 * @author ri.meisei
 * @since 2015/09/07
 */
public enum DataType {

	VOID(Void.class),

	STRING(String.class), //
	STRING_ARRAY(String[].class), //

	BYTE(Byte.class, byte.class), //
	BYTE_ARRAY(Byte[].class, byte[].class), //

	SHORT(Short.class, short.class), //
	SHORT_ARRAY(Short.class, short.class), //

	CHARACTER(Character.class, char.class), //
	CHARACTER_ARRAY(Character.class, char.class), //

	INTEGER(Integer.class, int.class), //
	INTEGER_ARRAY(Integer[].class, int[].class), //

	LONG(Long[].class, long[].class), //
	LONG_ARRAY(Long.class, long.class), //

	FLOAT(Float.class, float.class), //
	FLOAT_ARRAY(Float[].class, float[].class), //

	DOUBLE(Double.class, double.class), //
	DOUBLE_ARRAY(Double[].class, double[].class), //

	BIGDECIMAL(BigDecimal.class), //
	BIGDECIMAL_ARRAY(BigDecimal[].class), //

	BOOLEAN(Boolean.class, boolean.class), //
	BOOLEAN_ARRAY(Boolean[].class, boolean[].class), //

	TIME(Time.class), //
	DATE(Date.class), //
	TIMESTAMP(Timestamp.class), //

	BLOB(Blob.class), //
	CLOB(Clob.class), //
	NCLOB(java.sql.NClob.class), //
	ARRAY(Array.class), //
	STRUCT(Struct.class), //
	REF(Ref.class), //
	SQLXML(java.sql.SQLXML.class), //
	ROWID(java.sql.RowId.class), //
	;

	private Class<?>[] clazz;

	private DataType(Class<?>... clazz) {

		this.clazz = clazz;
	}

	//
	public static DataType getDataType(JDBCType jdbcType) {

		Assertion.assertNotNull("jdbcType", jdbcType);

		switch (jdbcType.getVendorTypeNumber()) {
		case Types.CHAR:
			// CHAR(Types.CHAR),
			return STRING;
		case Types.VARCHAR:
			// VARCHAR(Types.VARCHAR),
			return STRING;
		case Types.LONGVARCHAR:
			// LONGVARCHAR(Types.LONGVARCHAR),
			return STRING;
		case Types.NCHAR:
			// NCHAR(Types.NCHAR),
			return STRING;
		case Types.NVARCHAR:
			// NVARCHAR(Types.NVARCHAR),
			return STRING;
		case Types.LONGNVARCHAR:
			// LONGNVARCHAR(Types.LONGNVARCHAR),
			return STRING;

		case Types.TINYINT:
			// TINYINT(Types.TINYINT),
			return BYTE;
		case Types.SMALLINT:
			// SMALLINT(Types.SMALLINT),
			return SHORT;
		case Types.INTEGER:
			// INTEGER(Types.INTEGER),
			return DataType.INTEGER;
		case Types.BIGINT:
			// BIGINT(Types.BIGINT),
			return LONG;
		case Types.REAL:
			// REAL(Types.REAL),
			return FLOAT;
		case Types.FLOAT:
			// FLOAT(Types.FLOAT),
			return DOUBLE;
		case Types.DOUBLE:
			// DOUBLE(Types.DOUBLE),
			return DOUBLE;
		case Types.NUMERIC:
			// NUMERIC(Types.NUMERIC),
			return BIGDECIMAL;
		case Types.DECIMAL:
			// DECIMAL(Types.DECIMAL),
			return BIGDECIMAL;

		case Types.BIT:
			// BIT(Types.BIT),
			return BOOLEAN;
		case Types.BOOLEAN:
			// BOOLEAN(Types.BOOLEAN),
			return BOOLEAN;

		case Types.BINARY:
			// BINARY(Types.BINARY),
			return BYTE_ARRAY;
		case Types.VARBINARY:
			// VARBINARY(Types.VARBINARY),
			return BYTE_ARRAY;
		case Types.LONGVARBINARY:
			// LONGVARBINARY(Types.LONGVARBINARY),
			return BYTE_ARRAY;

		case Types.TIME:
			// TIME(Types.TIME),
			return TIME;
		case Types.DATE:
			// DATE(Types.DATE),
			return DATE;
		case Types.TIMESTAMP:
			// TIMESTAMP(Types.TIMESTAMP),
			return TIMESTAMP;

		case Types.BLOB:
			// BLOB(Types.BLOB),
			return BLOB;
		case Types.CLOB:
			// CLOB(Types.CLOB),
			return CLOB;
		case Types.NCLOB:
			// NCLOB(Types.NCLOB),
			return NCLOB;

		case Types.ARRAY:
			// ARRAY(Types.ARRAY),
			return ARRAY;
		case Types.STRUCT:
			// STRUCT(Types.STRUCT),
			return STRUCT;
		case Types.REF:
			// REF(Types.REF),
			return REF;
		case Types.ROWID:
			// ROWID(Types.ROWID),
			return ROWID;
		case Types.SQLXML:
			// SQLXML(Types.SQLXML),
			return SQLXML;
		default:
			// DATALINK(Types.DATALINK),
			// NULL(Types.NULL),
			// OTHER(Types.OTHER),
			// JAVA_OBJECT(Types.JAVA_OBJECT),
			// DISTINCT(Types.DISTINCT),
			// REF_CURSOR(Types.REF_CURSOR),
			// TIME_WITH_TIMEZONE(Types.TIME_WITH_TIMEZONE),
			// TIMESTAMP_WITH_TIMEZONE(Types.TIMESTAMP_WITH_TIMEZONE);
			throw new UnexpectedTypeException(jdbcType.getClass().getName());
		}
	}

	public String getStringType() {

		switch (this) {
		case VOID:
			return "void";
		case STRING:
			return clazz[0].getName();
		case BOOLEAN:
			return clazz[0].getName();
		case BYTE:
			return clazz[0].getName();
		case SHORT://
			return clazz[0].getName();
		case CHARACTER:
			return clazz[0].getName();
		case INTEGER:
			return clazz[0].getName();
		case LONG:
			return clazz[0].getName();
		case FLOAT:
			return clazz[0].getName();
		case DOUBLE:
			return clazz[0].getName();
		case BIGDECIMAL:
			return clazz[0].getName();

		case BYTE_ARRAY:
			return Byte.class.getName() + "[]";

		case TIME:
			return clazz[0].getName();
		case DATE:
			return clazz[0].getName();
		case TIMESTAMP:
			return clazz[0].getName();

		case BLOB:
			return clazz[0].getName();
		case CLOB:
			return clazz[0].getName();
		case NCLOB:
			return clazz[0].getName();
		case ARRAY:
			return clazz[0].getName();
		case STRUCT:
			return clazz[0].getName();
		case REF:
			return clazz[0].getName();
		case SQLXML:
			return clazz[0].getName();
		case ROWID:
			return clazz[0].getName();
		default:
			throw new UnexpectedTypeException(this.getClass().getName());
		}
	}
}
