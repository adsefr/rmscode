package com.rms.base.datatype.enumeration;

import java.math.BigDecimal;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.JDBCType;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.RowId;
import java.sql.SQLXML;
import java.sql.Struct;
import java.sql.Time;
import java.sql.Timestamp;

import com.rms.base.exception.UnexpectedTypeException;
import com.rms.base.validate.Assertion;

/**
 *
 * @author ri.meisei
 * @since 2015/09/07
 */
public enum DataType {

	/**
	 *
	 * @param name
	 * @param clazz
	 * @param defaultValue
	 * @param stringDefaultValue
	 */

	VOID("void", Void.class),

	BYTE("Byte", Byte.class), //
	BYTE_PRIMITIVE("byte", byte.class), //
	BYTE_ARRAY("byte[]", byte[].class), //

	SHORT("Short", Short.class), //
	SHORT_PRIMITIVE("short", short.class), //
	SHORT_ARRAY("short[]", short[].class), //

	CHAR("Character", Character.class), //
	CHAR_PRIMITIVE("char", char.class), //
	CHARACTER_ARRAY("char[]", char[].class), //

	INTEGER("Integer", Integer.class), //
	INT_PRIMITIVE("int", int.class), //
	INTEGER_ARRAY("int[]", int[].class), //

	LONG("Long", Long.class), //
	LONG_PRIMITIVE("long", long.class), //
	LONG_ARRAY("long[]", long[].class), //

	FLOAT("Float", Float.class), //
	FLOAT_PRIMITIVE("float", float.class), //
	FLOAT_ARRAY("float[]", float[].class), //

	DOUBLE("Double", Double.class), //
	DOUBLE_PRIMITIVE("double", double.class), //
	DOUBLE_ARRAY("double[]", double[].class), //

	BOOLEAN("Boolean", Boolean.class), //
	BOOLEAN_PRIMITIVE("boolean", boolean.class), //
	BOOLEAN_ARRAY("boolean[]", boolean[].class), //

	STRING("String", String.class), //
	STRING_ARRAY("String[]", String[].class), //

	BIGDECIMAL("BigDecimal", BigDecimal.class), //
	BIGDECIMAL_ARRAY("BigDecimal[]", BigDecimal[].class), //

	TIME("Time", Time.class), //
	DATE("Date", Date.class), //
	TIMESTAMP("Timestamp", Timestamp.class), //

	BLOB("Blob", Blob.class), //
	CLOB("Clob", Clob.class), //
	NCLOB("NClob", NClob.class), //
	ARRAY("Array", Array.class), //
	STRUCT("Struct", Struct.class), //
	REF("Ref", Ref.class), //
	SQLXML("SQLXML", SQLXML.class), //
	ROWID("RowId", RowId.class), //
	;

	private String name;

	private Class<?> classType;

	/**
	 *
	 * @param name
	 * @param classType
	 * @param defaultValue
	 */
	private DataType(String name, Class<?> classType) {

		this.name = name;
		this.classType = classType;
	}

	public String getName() {

		return name;
	}

	public Class<?> getClassType() {

		return classType;
	}

	public static DataType getDataType(JDBCType jdbcType) {

		Assertion.assertNotNull("jdbcType", jdbcType);

		switch (jdbcType) {
		case TINYINT:
			// TINYINT(Types.TINYINT),
			return BYTE_PRIMITIVE;
		case SMALLINT:
			// SMALLINT(Types.SMALLINT),
			return SHORT_PRIMITIVE;
		case INTEGER:
			// INTEGER(Types.INTEGER),
			return DataType.INT_PRIMITIVE;
		case BIGINT:
			// BIGINT(Types.BIGINT),
			return LONG_PRIMITIVE;
		case REAL:
			// REAL(Types.REAL),
			return FLOAT_PRIMITIVE;
		case FLOAT:
			// FLOAT(Types.FLOAT),
			return DOUBLE_PRIMITIVE;
		case DOUBLE:
			// DOUBLE(Types.DOUBLE),
			return DOUBLE_PRIMITIVE;
		case NUMERIC:
			// NUMERIC(Types.NUMERIC),
			return BIGDECIMAL;
		case DECIMAL:
			// DECIMAL(Types.DECIMAL),
			return BIGDECIMAL;

		case BIT:
			// BIT(Types.BIT),
			return BOOLEAN_PRIMITIVE;
		case BOOLEAN:
			// BOOLEAN(Types.BOOLEAN),
			return BOOLEAN_PRIMITIVE;

		case BINARY:
			// BINARY(Types.BINARY),
			return BYTE_ARRAY;
		case VARBINARY:
			// VARBINARY(Types.VARBINARY),
			return BYTE_ARRAY;
		case LONGVARBINARY:
			// LONGVARBINARY(Types.LONGVARBINARY),
			return BYTE_ARRAY;

		case CHAR:
			// CHAR(Types.CHAR),
			return STRING;
		case VARCHAR:
			// VARCHAR(Types.VARCHAR),
			return STRING;
		case LONGVARCHAR:
			// LONGVARCHAR(Types.LONGVARCHAR),
			return STRING;
		case NCHAR:
			// NCHAR(Types.NCHAR),
			return STRING;
		case NVARCHAR:
			// NVARCHAR(Types.NVARCHAR),
			return STRING;
		case LONGNVARCHAR:
			// LONGNVARCHAR(Types.LONGNVARCHAR),
			return STRING;

		case TIME:
			// TIME(Types.TIME),
			return TIME;
		case DATE:
			// DATE(Types.DATE),
			return DATE;
		case TIMESTAMP:
			// TIMESTAMP(Types.TIMESTAMP),
			return TIMESTAMP;

		case BLOB:
			// BLOB(Types.BLOB),
			return BLOB;
		case CLOB:
			// CLOB(Types.CLOB),
			return CLOB;
		case NCLOB:
			// NCLOB(Types.NCLOB),
			return NCLOB;

		case ARRAY:
			// ARRAY(Types.ARRAY),
			return ARRAY;
		case STRUCT:
			// STRUCT(Types.STRUCT),
			return STRUCT;
		case REF:
			// REF(Types.REF),
			return REF;
		case ROWID:
			// ROWID(Types.ROWID),
			return ROWID;
		case SQLXML:
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
			throw new UnexpectedTypeException(jdbcType.getName());
		}
	}

}
