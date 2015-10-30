package com.rms.tool.future.db;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.rms.base.jdbc.model.DataBaseInfo;
import com.rms.base.jdbc.model.DataBaseType;
import com.rms.base.jdbc.model.TableMeta;
import com.rms.base.logging.Logger;
import com.rms.common.jdbc.JDBCDataBaseMetaData;
import com.rms.common.jdbc.JDBCObject;

public class PrintObject {

	private final static Logger logger = Logger.getLogger(PrintObject.class);

	private static DataBaseInfo dataBaseInfo;

	private JDBCObject jdbcObject;

	public PrintObject() {

	}

	@BeforeClass
	public static void beforeClass() {

		dataBaseInfo = new DataBaseInfo();
		dataBaseInfo.setDataBaseType(DataBaseType.ORACLE);
		dataBaseInfo.setHost("localhost");
		dataBaseInfo.setPort(1521);
		dataBaseInfo.setDriver("oracle.jdbc.driver.OracleDriver");
		dataBaseInfo.setDataBaseName("xe");
		dataBaseInfo.setUserId("dka_ap1");
		dataBaseInfo.setPassword("dka_ap1");
	}

	@AfterClass
	public static void afterClass() {

	}

	@Before
	public void before() throws SQLException {

		jdbcObject = JDBCObject.getInstance(dataBaseInfo);
		jdbcObject.startTransaction();
	}

	@After
	public void after() {

		jdbcObject.close();
	}

	@Test
	public void printObjectName() throws SQLException {

		JDBCDataBaseMetaData jdbcDataBaseMetaData = jdbcObject.getJDBCDataBaseMetaData();
		List<TableMeta> tableMetas = jdbcDataBaseMetaData.getTableMetas(null, "DKA_AP1");
		for (TableMeta tableMeta : tableMetas) {
			System.out.println(tableMeta.getTableName());
		}
	}
}
