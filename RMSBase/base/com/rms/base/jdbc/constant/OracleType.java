package com.rms.base.jdbc.constant;

import java.sql.JDBCType;

import com.rms.base.jdbc.exception.UnsupportedJDBCTypeException;
import com.rms.base.logging.Logger;

/**
 *
 * @author ri.meisei
 * @since 2015/11/02
 */
public enum OracleType {

	ARRAY(2003, java.sql.JDBCType.ARRAY), //
	// BFILE(-13,java.sql.JDBCType.#N/A),//
	BIGINT(-5, java.sql.JDBCType.BIGINT), //
	BINARY(-2, java.sql.JDBCType.BINARY), //
	BINARY_DOUBLE(101, java.sql.JDBCType.DECIMAL), // TODO
	BINARY_FLOAT(100, java.sql.JDBCType.DECIMAL), // TODO
	BIT(-7, java.sql.JDBCType.BIT), //
	BLOB(2004, java.sql.JDBCType.BLOB), //
	BOOLEAN(16, java.sql.JDBCType.BOOLEAN), //
	CHAR(1, java.sql.JDBCType.CHAR), //
	CLOB(2005, java.sql.JDBCType.CLOB), //
	// CURSOR(-10,java.sql.JDBCType.#N/A),//
	DATALINK(70, java.sql.JDBCType.DATALINK), //
	DATE(91, java.sql.JDBCType.DATE), //
	DECIMAL(3, java.sql.JDBCType.DECIMAL), //
	DOUBLE(8, java.sql.JDBCType.DOUBLE), //
	// FIXED_CHAR(999,java.sql.JDBCType.#N/A),//
	FLOAT(6, java.sql.JDBCType.FLOAT), //
	INTEGER(4, java.sql.JDBCType.INTEGER), //
	// INTERVALDS(-104,java.sql.JDBCType.#N/A),//
	// INTERVALYM(-103,java.sql.JDBCType.#N/A),//
	JAVA_OBJECT(2000, java.sql.JDBCType.JAVA_OBJECT), //
	// JAVA_STRUCT(2008,java.sql.JDBCType.#N/A),//
	LONGNVARCHAR(-16, java.sql.JDBCType.LONGNVARCHAR), //
	LONGVARBINARY(-4, java.sql.JDBCType.LONGVARBINARY), //
	LONGVARCHAR(-1, java.sql.JDBCType.LONGVARCHAR), //
	NCHAR(-15, java.sql.JDBCType.NCHAR), //
	NCLOB(2011, java.sql.JDBCType.NCLOB), //
	NULL(0, java.sql.JDBCType.NULL), //
	// NUMBER(2,java.sql.JDBCType.#N/A),//
	NUMERIC(2, java.sql.JDBCType.NUMERIC), //
	NVARCHAR(-9, java.sql.JDBCType.NVARCHAR), //
	// OPAQUE(2007,java.sql.JDBCType.#N/A),//
	OTHER(1111, java.sql.JDBCType.OTHER), //
	// PLSQL_INDEX_TABLE(-14,java.sql.JDBCType.#N/A),//
	// RAW(-2,java.sql.JDBCType.#N/A),//
	REAL(7, java.sql.JDBCType.REAL), //
	REF(2006, java.sql.JDBCType.REF), //
	ROWID(-8, java.sql.JDBCType.ROWID), //
	SMALLINT(5, java.sql.JDBCType.SMALLINT), //
	SQLXML(2009, java.sql.JDBCType.SQLXML), //
	STRUCT(2002, java.sql.JDBCType.STRUCT), //
	TIME(92, java.sql.JDBCType.TIME), //
	TIMESTAMP(93, java.sql.JDBCType.TIMESTAMP), //
	TIMESTAMPLTZ(-102, java.sql.JDBCType.TIMESTAMP_WITH_TIMEZONE), // TODO
	// TIMESTAMPNS(-100,java.sql.JDBCType.#N/A),//
	TIMESTAMPTZ(-101, java.sql.JDBCType.TIMESTAMP_WITH_TIMEZONE), //
	TINYINT(-6, java.sql.JDBCType.TINYINT), //
	VARBINARY(-3, java.sql.JDBCType.VARBINARY), //
	VARCHAR(12, java.sql.JDBCType.VARCHAR),//
	;

	private final static Logger LOGGER = Logger.getLogger(OracleType.class);

	private final int type;

	private final JDBCType jdbcType;

	private OracleType(int type, JDBCType jdbcType) {
		this.type = type;
		this.jdbcType = jdbcType;
	}

	public JDBCType getJDBCType() {

		return jdbcType;
	}

	public static JDBCType valueOf(int type) {

		for (OracleType oracleType : OracleType.class.getEnumConstants()) {
			if (type == oracleType.type)
				return oracleType.jdbcType;
		}

		String message = String.format("Type(%d) is not supported!!!", type);

		LOGGER.trace(message);

		throw new UnsupportedJDBCTypeException(message);
	}

}
