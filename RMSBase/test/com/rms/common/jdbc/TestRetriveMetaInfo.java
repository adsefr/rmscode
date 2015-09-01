package com.rms.common.jdbc;

import java.sql.SQLException;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.rms.base.jdbc.model.DataBaseInfo;
import com.rms.base.jdbc.model.DataBaseType;
import com.rms.base.logging.Logger;
import com.rms.tool.jdbc.RetriveMetaInfo;

public class TestRetriveMetaInfo {

	private final static Logger logger = Logger.getLogger(TestRetriveMetaInfo.class);

	private static RetriveMetaInfo retriveMetaInfo;

	@BeforeClass
	public static void beforeClass() throws SQLException {

		try {
			DataBaseInfo dataBaseInfo = new DataBaseInfo();
			dataBaseInfo.setDataBaseType(DataBaseType.POSTGRESQL);
			dataBaseInfo.setDriver("org.postgresql.Driver");
			dataBaseInfo.setHost("localhost");
			dataBaseInfo.setPort(5432);
			dataBaseInfo.setDataBaseName("V420_DEV01");
			dataBaseInfo.setUserId("V420_DEV01usr");
			dataBaseInfo.setPassword("V420_DEV01pwd");

			retriveMetaInfo = new RetriveMetaInfo(dataBaseInfo);
			retriveMetaInfo.initialize();
			retriveMetaInfo.execute();
		} catch (SQLException e) {
			logger.error(e);
			throw e;
		}
	}

	@AfterClass
	public static void afterClass() throws SQLException {

		retriveMetaInfo.destory();
	}

	@SuppressWarnings("static-method")
	@Test
	public void getTableNameList() {

		try {
			List<String> tableNames = retriveMetaInfo.getTableNameList("public");
			tableNames.forEach(element -> logger.info(element));
		} catch (SQLException e) {
			logger.error(e);
		}

	}
}
