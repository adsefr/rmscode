package com.rms.base.enumeration;

import java.sql.Types;

import com.rms.base.exception.UnexpectedTypeException;
import com.rms.base.validate.Assertion;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/02/26
 */
public enum DataType {
	BIT(Types.BIT), //
	TINYINT(Types.TINYINT), //
	SMALLINT(Types.SMALLINT), //
	BIGINT(Types.BIGINT), //
	INTEGER(Types.INTEGER), //
	FLOAT(Types.FLOAT), //
	DOUBLE(Types.DOUBLE), //
	DECIMAL(Types.DECIMAL), //
	NUMERIC(Types.NUMERIC), //

	CHAR(Types.CHAR), //
	VARCHAR(Types.VARCHAR), //
	LONGVARCHAR(Types.LONGVARCHAR), //
	NCHAR(Types.NCHAR), //
	NVARCHAR(Types.NVARCHAR), //
	LONGNVARCHAR(Types.LONGNVARCHAR), //

	BOOLEAN(Types.BOOLEAN), //

	BINARY(Types.BINARY), //
	VARBINARY(Types.VARBINARY), //
	LONGVARBINARY(Types.LONGVARBINARY), //
	BLOB(Types.BLOB), //
	CLOB(Types.CLOB), //

	TIME(Types.TIME), //
	DATE(Types.DATE), //
	TIMESTAMP(Types.TIMESTAMP), //

	NULL(Types.NULL), //

	JAVA_OBJECT(Types.JAVA_OBJECT), //
	REAL(Types.REAL), //
	// ARRAY(Types.ARRAY), //
	// DATALINK(Types.DATALINK), //
	// DISTINCT(Types.DISTINCT), //
	// OTHER(Types.OTHER), //
	// REF(Types.REF), //
	// ROWID(Types.ROWID), //
	// STRUCT(Types.STRUCT), //
	// NCLOB(Types.NCLOB), //
	// SQLXML(Types.SQLXML), //
	;

	private int type;

	private DataType(int type) {

		this.type = type;
	}

	/**
	 * @return type
	 */
	public int getType() {

		return type;
	}

	public static DataType valueOf(int type) {

		for (DataType valueType : DataType.values()) {
			if (valueType.getType() == type) {
				return valueType;
			}
		}

		throw new UnexpectedTypeException("type.getType():" + type);
	}

	public static String javaType(int dataType) {

		Assertion.assertNotNull("dataType", dataType);

		switch (dataType) {
		case Types.CHAR:
			return " String";
		case Types.VARCHAR:
			return " String";
		case Types.LONGVARCHAR:
			return " String";
		case Types.NUMERIC:
			return " java.math.BigDecimal";
		case Types.DECIMAL:
			return " java.math.BigDecimal";
		case Types.BIT:
			return " Boolean";
		case Types.TINYINT:
			return " Integer";
		case Types.SMALLINT:
			return " Integer";
		case Types.INTEGER:
			return " Integer";
		case Types.BIGINT:
			return " Long";
		case Types.REAL:
			return " Float";
		case Types.FLOAT:
			return " Double";
		case Types.DOUBLE:
			return " Double";
		case Types.BINARY:
			return " byte[]";
		case Types.VARBINARY:
			return " byte[]";
		case Types.LONGVARBINARY:
			return " byte[]";
		case Types.DATE:
			return " java.sql.Date";
		case Types.TIME:
			return " java.sql.Time";
		case Types.TIMESTAMP:
			return " java.sql.Timestamp";

		default:
			throw new UnexpectedTypeException(dataType + "");
		}

	}
}
