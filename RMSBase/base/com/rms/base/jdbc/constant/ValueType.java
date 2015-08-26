package com.rms.base.jdbc.constant;

import java.sql.Types;

import com.rms.base.exception.UnexpectedTypeException;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/02/26
 */
public enum ValueType {
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

	// ARRAY(Types.ARRAY), //
	// JAVA_OBJECT(Types.JAVA_OBJECT), //
	// DATALINK(Types.DATALINK), //
	// DISTINCT(Types.DISTINCT), //
	// OTHER(Types.OTHER), //
	// REAL(Types.REAL), //
	// REF(Types.REF), //
	// ROWID(Types.ROWID), //
	// STRUCT(Types.STRUCT), //
	// NCLOB(Types.NCLOB), //
	// SQLXML(Types.SQLXML), //
	;

	private int type;

	private ValueType(int type) {

		this.type = type;
	}

	/**
	 * @return type
	 */
	public int getType() {

		return type;
	}

	public static ValueType valueOf(int type) {

		for (ValueType valueType : ValueType.values()) {
			if (valueType.getType() == type) {
				return valueType;
			}
		}

		throw new UnexpectedTypeException("type:" + type);
	}
}
