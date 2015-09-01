package com.rms.tool.jdbc;

import java.sql.SQLException;

import com.rms.base.jdbc.model.DataBaseInfo;
import com.rms.base.logging.Logger;
import com.rms.common.jdbc.JDBCObject;

/**
 *
 * @author ri.meisei
 * @since 2015/09/01
 */
public abstract class BaseJDBCTool {

	Logger logger = Logger.getLogger(BaseJDBCTool.class);

	protected JDBCObject jdbcObject;

	protected BaseJDBCTool(DataBaseInfo dataBaseInfo) {

		this.jdbcObject = JDBCObject.getInstance(dataBaseInfo);

	}

	public void initialize() throws SQLException {

		jdbcObject.startTransaction();
	}

	public abstract void execute() throws SQLException;

	public void destory() throws SQLException {

		try {
			jdbcObject.endTransaction();

		} catch (Exception e) {
			logger.error(e);
		} finally {
			jdbcObject.close();
		}

	}
}
