package com.rms.tool.future.db;

import java.math.BigDecimal;
import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.rms.base.jdbc.model.DataBaseInfo;
import com.rms.base.jdbc.model.DataBaseType;
import com.rms.base.jdbc.model.QueryParameter;
import com.rms.base.logging.Logger;
import com.rms.common.jdbc.JDBCObject;
import com.rms.common.jdbc.JDBCQueryExecutor;

public class CheckERDInfo {

	private final static Logger logger = Logger.getLogger(CheckERDInfo.class);

	private static DataBaseInfo dataBaseInfo;

	private JDBCObject jdbcObject;

	private static final String[] DOMAIN_NUMBERS = { "501", "502", "503", "504", "505", "506" };

	private static final String OUTPUTFILE = "C:/document/TEMP/TableInfos/TableInfos.tsv";

	public CheckERDInfo() {

	}

	@BeforeClass
	public static void beforeClass() {

		dataBaseInfo = new DataBaseInfo();
		dataBaseInfo.setDataBaseType(DataBaseType.ORACLE);
		dataBaseInfo.setHost("10.3.169.152");
		dataBaseInfo.setPort(1521);
		dataBaseInfo.setDriver("oracle.jdbc.driver.OracleDriver");
		dataBaseInfo.setDataBaseName("DKA2");
		dataBaseInfo.setUserId("ERD_MAINTE_READ_ONLY");
		dataBaseInfo.setPassword("ERD_MAINTE_READ_ONLY");
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
	public void printTableInfo() throws SQLException {
		QueryParameter queryTables = new QueryParameter();
		String sqlQueryTableInfo = "SELECT "
				+ "MS_DOMAIN.DOMAIN_NUMBER ,"
				+ " MS_DOMAIN.DOMAIN_NAME ,"
				+ " MS_TABLES.TABLE_ID ,"
				+ " MS_TABLES.TABLE_NAME_JP ,"
				+ " MS_TABLES.TABLE_NAME "
				+ "FROM MS_DOMAIN , MS_TABLES "
				+ "WHERE "
				+ "MS_DOMAIN.DOMAIN_NUMBER IN ('501', '502', '503', '504', '505', '506') "
				+ "AND MS_DOMAIN.DOMAIN_NUMBER = MS_TABLES.DOMAIN_NUMBER "
				+ "ORDER BY MS_DOMAIN.DOMAIN_NUMBER , MS_DOMAIN.DOMAIN_NAME";
		queryTables.setSqlClause(sqlQueryTableInfo);

		JDBCQueryExecutor jdbcQueryTablenames = jdbcObject.query(queryTables);
		jdbcQueryTablenames.execute();

		String sqlQueryTableColumns = "SELECT "
				+ "DOMAIN_NUMBER ,"
				+ " TABLE_ID , "
				+ " COLUMN_ID , "
				+ " COLUMN_NAME , "
				+ " COLUMN_NAME_JP ,"
				+ " PK_POSITION "
				+ " FROM "
				+ " MS_TAB_COLUMNS "
				+ " WHERE "
				+ " DOMAIN_NUMBER = ? "
				+ " AND TABLE_ID = ? "
				+ " AND PK_POSITION IS NOT NULL "
				+ " ORDER BY DOMAIN_NUMBER , TABLE_ID , COLUMN_ID,PK_POSITION ";
		QueryParameter queryTableColumns = new QueryParameter();
		queryTableColumns.setSqlClause(sqlQueryTableColumns);

		StringBuilder buffered = new StringBuilder();

		while (jdbcQueryTablenames.hasNext()) {
			String domain_number = jdbcQueryTablenames.getValue("DOMAIN_NUMBER");
			String domain_name = jdbcQueryTablenames.getValue("DOMAIN_NAME");
			BigDecimal table_id = jdbcQueryTablenames.getValue("TABLE_ID");
			String table_name = jdbcQueryTablenames.getValue("TABLE_NAME");

			buffered.setLength(0);
			buffered.append(domain_number).append("\t");
			buffered.append(domain_name).append("\t");
			buffered.append(table_id.intValue()).append("\t");
			buffered.append(table_name).append("\t");

			int length = buffered.length();

			queryTableColumns.getParameterList().clear();
			queryTableColumns.getParameterList().add(domain_number);
			queryTableColumns.getParameterList().add(table_id);

			JDBCQueryExecutor jdbcQueryTableColumns = jdbcObject.query(queryTableColumns);
			jdbcQueryTableColumns.execute();

			while (jdbcQueryTableColumns.hasNext()) {
				BigDecimal column_id = jdbcQueryTableColumns.getValue("COLUMN_ID");
				String column_name = jdbcQueryTableColumns.getValue("COLUMN_NAME");
				String column_name_jp = jdbcQueryTableColumns.getValue("COLUMN_NAME_JP");
				BigDecimal pk_position = jdbcQueryTableColumns.getValue("PK_POSITION");

				buffered.setLength(length);
				buffered.append(column_id.intValue()).append("\t");
				buffered.append(column_name).append("\t");
				buffered.append(column_name_jp).append("\t");
				buffered.append(pk_position.intValueExact()).append("\t");
			}

			logger.info(buffered.toString());

		}
	}
}
