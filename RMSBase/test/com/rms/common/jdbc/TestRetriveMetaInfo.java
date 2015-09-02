package com.rms.common.jdbc;

import java.sql.SQLException;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.rms.base.jdbc.model.DataBaseInfo;
import com.rms.base.jdbc.model.DataBaseType;
import com.rms.base.jdbc.model.TableMeta;
import com.rms.base.logging.Logger;
import com.rms.util.jdbc.RetriveMetaInfo;

public class TestRetriveMetaInfo {

	private final static Logger logger = Logger.getLogger(TestRetriveMetaInfo.class);

	private static RetriveMetaInfo retriveMetaInfo;

	@BeforeClass
	public static void beforeClass() {

		JDBCObject jdbcObject = null;
		try {
			DataBaseInfo dataBaseInfo = new DataBaseInfo();
			dataBaseInfo.setDataBaseType(DataBaseType.POSTGRESQL);
			dataBaseInfo.setDriver("org.postgresql.Driver");
			dataBaseInfo.setHost("localhost");
			dataBaseInfo.setPort(5432);
			dataBaseInfo.setDataBaseName("V420_DEV01");
			dataBaseInfo.setUserId("V420_DEV01usr");
			dataBaseInfo.setPassword("V420_DEV01pwd");
			jdbcObject = JDBCObject.getInstance(dataBaseInfo);
			jdbcObject.startTransaction();
			retriveMetaInfo = new RetriveMetaInfo(jdbcObject);
			retriveMetaInfo.execute();
		} catch (Exception e) {
			logger.error(e);
		} finally {
			try {
				if (jdbcObject != null && !jdbcObject.isClosed()) {
					jdbcObject.close();
				}
			} catch (SQLException e) {
				logger.error(e);
			}
		}
	}

	@SuppressWarnings("static-method")
	@Test
	public void getTableNameList() {

		JDBCDataBaseMetaData jdbcDataBaseMetaData = retriveMetaInfo.getJdbcDataBaseMetaData();
		List<TableMeta> tableMetas = jdbcDataBaseMetaData.getTableMetas("public");
		tableMetas.forEach(element -> logger.info(element.toString()));

	}
}
