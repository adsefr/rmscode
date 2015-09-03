package com.rms.common.jdbc;

import java.sql.SQLException;

import com.rms.base.jdbc.JDBCCloseable;
import com.rms.base.jdbc.model.DataBaseInfo;
import com.rms.base.jdbc.model.QueryParameter;
import com.rms.base.jdbc.model.UpdateParameter;
import com.rms.base.logging.Logger;
import com.rms.base.reflect.Reflection;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/02/24
 */
public abstract class JDBCObject implements JDBCCloseable {

	private final static Logger logger = Logger.getLogger(JDBCObject.class);

	private final static String INSTANCE_JDBCOBJECT_CLASS_NAME = "com.rms.base.jdbc.DefaultJDBCObject";

	public static JDBCObject getInstance(DataBaseInfo dataBaseInfo) {

		JDBCObject instance = null;
		try {
			Class<?>[] parameterTypes = new Class<?>[] { DataBaseInfo.class };
			Object[] params = new Object[] { dataBaseInfo };
			instance = Reflection.newInstance(INSTANCE_JDBCOBJECT_CLASS_NAME, parameterTypes, params);
		} catch (ReflectiveOperationException e) {
			logger.error(e);
		}

		return instance;
	}

	public abstract DataBaseInfo getDatabaseInfo() throws SQLException;

	public abstract boolean startTransaction() throws SQLException;

	public abstract void commitTransaction() throws SQLException;

	public abstract JDBCDataBaseMetaData getJDBCDataBaseMetaData() throws SQLException;

	public abstract JDBCQueryExecutor query(QueryParameter queryParameter) throws SQLException;

	public abstract JDBCUpdateExecutor update(UpdateParameter updateParameter) throws SQLException;

	public abstract void commit() throws SQLException;

	public abstract void rollback() throws SQLException;

}
