package com.rms.tool.future.db;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.rms.base.constant.Characters;
import com.rms.base.constant.Encodes;
import com.rms.base.jdbc.model.DataBaseInfo;
import com.rms.base.jdbc.model.DataBaseType;
import com.rms.base.logging.Logger;
import com.rms.common.jdbc.JDBCDataBaseMetaData;
import com.rms.common.jdbc.JDBCObject;

public class PrintObject {

	private final static Logger LOGGER = Logger.getLogger(PrintObject.class);

	private final static String OUTPUT_FILEPATH = "C:/document/TEMP/TABLE_DESC_INFO.txt";

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
		dataBaseInfo.setDriver("oracle.jdbc.OracleDriver");
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
	public void printObjectName() throws SQLException, IOException {

		BufferedWriter writer = Files.newBufferedWriter(Paths.get(OUTPUT_FILEPATH), Encodes.CHARSET_UTF8);
		StringBuilder buffered = new StringBuilder();
		JDBCDataBaseMetaData jdbcDataBaseMetaData = jdbcObject.getJDBCDataBaseMetaData();
		jdbcDataBaseMetaData.getSchemaMetas(null).parallelStream().forEach((schemaMeta) -> {
			schemaMeta.getTableMetas().stream().forEach(tableMeta -> {
				LOGGER.debug(tableMeta.getCatalogName() + " " + tableMeta.getSchemaName() + " " + tableMeta.getTableName());
				tableMeta.getColumnMetas().stream().forEach(columnMeta -> {
					buffered.setLength(0);
					buffered.append(columnMeta.getCatalogName());
					buffered.append(Characters.TAB);
					buffered.append(columnMeta.getSchemaName());
					buffered.append(Characters.TAB);
					buffered.append(columnMeta.getTableName());
					buffered.append(Characters.TAB);
					buffered.append(columnMeta.getColumnName());
					buffered.append(Characters.TAB);
					buffered.append(columnMeta.getTypeName());
					buffered.append(Characters.TAB);
					buffered.append(columnMeta.getColumnSize());
					buffered.append(Characters.TAB);
					buffered.append(columnMeta.getPrimaryKey());
					buffered.append(Characters.TAB);
					buffered.append(columnMeta.getKeySequence());
					buffered.append(Characters.TAB);
					buffered.append(columnMeta.getColumnDefaultValue());
					buffered.append(Characters.TAB);
					buffered.append(columnMeta.getIsNullable());
					try {
						writer.write(buffered.toString().replaceAll("\r\n", "").replaceAll("\r", "").replaceAll("\n", ""));
						writer.newLine();
						buffered.append(Characters.LINE_SEPARATOR_SYSTEM);
						buffered.setLength(0);
					} catch (Exception e) {
						e.printStackTrace();
					}
				});
			});
		});

		writer.flush();
		writer.close();
	}
}
