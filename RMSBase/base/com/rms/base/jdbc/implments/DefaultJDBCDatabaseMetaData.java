package com.rms.base.jdbc.implments;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/02/12
 */
public class DefaultJDBCDatabaseMetaData extends AbstractJDBCDatabaseMetaData {

	DefaultJDBCDatabaseMetaData(DatabaseMetaData databaseMetaData) throws SQLException {

		super(databaseMetaData);
	}
}
