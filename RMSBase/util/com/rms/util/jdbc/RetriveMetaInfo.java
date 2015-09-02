package com.rms.util.jdbc;

import com.rms.base.validate.Assertion;
import com.rms.common.jdbc.JDBCDataBaseMetaData;
import com.rms.common.jdbc.JDBCObject;

public class RetriveMetaInfo extends JDBCProcess {

	private JDBCObject jdbcObject;

	private boolean isNewConnection = false;

	private JDBCDataBaseMetaData jdbcDataBaseMetaData;

	public RetriveMetaInfo(JDBCObject jdbcObject) {

		super();

		Assertion.assertNotNull("jdbcObject", jdbcObject);

		this.jdbcObject = jdbcObject;
	}

	/**
	 * @return jdbcDataBaseMetaData
	 */
	public final JDBCDataBaseMetaData getJdbcDataBaseMetaData() {

		return jdbcDataBaseMetaData;
	}

	@Override
	public void beforeProcess() throws Exception {

		isNewConnection = this.jdbcObject.startTransaction();
	}

	@Override
	public void process() throws Exception {

		this.jdbcDataBaseMetaData = this.jdbcObject.getJDBCDataBaseMetaData();
	}

	@Override
	public void afterProcessSuccess() throws Exception {

		this.jdbcObject.endTransaction();

	}

	@Override
	public void afterProcessFailure() throws Exception {

		this.jdbcObject.rollback();
	}

	@Override
	public void destory() throws Exception {

		if (isNewConnection) {
			this.jdbcObject.close();
		}
	}
}
