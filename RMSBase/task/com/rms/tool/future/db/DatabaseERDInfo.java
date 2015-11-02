package com.rms.tool.future.db;

import java.io.BufferedWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;

import org.junit.Test;

import com.rms.base.constant.Encodes;
import com.rms.base.jdbc.model.DataBaseInfo;
import com.rms.base.jdbc.model.DataBaseType;
import com.rms.base.jdbc.model.QueryParameter;
import com.rms.base.logging.Logger;
import com.rms.base.util.TextUtil;
import com.rms.common.jdbc.JDBCObject;
import com.rms.common.jdbc.JDBCQueryExecutor;

/**
 *
 * @author ri-meisei
 *
 */
public class DatabaseERDInfo {

	private final static Logger logger = Logger.getLogger(DatabaseERDInfo.class);

	public final static String ERDINFO_FILEPATH = "C:/document/作業内容/【00000000_参考情報】/ERD_INFO.txt";

	public final static Charset ERDINFO_FILEPATH_CHARSET = Encodes.CHARSET_UTF8;

	public final static String SPLIT_CHAR = "`";

	private JDBCObject jdbcObject;

	private DataBaseInfo dataBaseInfo;

	private BufferedWriter bufferedWriter;

	@Test
	public void retrive() {

		try {

			bufferedWriter = Files.newBufferedWriter(Paths.get(ERDINFO_FILEPATH), ERDINFO_FILEPATH_CHARSET);

			dataBaseInfo = new DataBaseInfo();
			dataBaseInfo.setDataBaseType(DataBaseType.ORACLE);
			dataBaseInfo.setHost("10.3.169.152");
			dataBaseInfo.setPort(1521);
			dataBaseInfo.setDriver("oracle.jdbc.driver.OracleDriver");
			dataBaseInfo.setDataBaseName("DKA2");
			dataBaseInfo.setUserId("ERD_MAINTE_READ_ONLY");
			dataBaseInfo.setPassword("ERD_MAINTE_READ_ONLY");
			jdbcObject = JDBCObject.getInstance(dataBaseInfo);
			jdbcObject.startTransaction();

			retriveDatabaseERDInfo();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (jdbcObject != null) {
				jdbcObject.close();
			}
			try {
				if (bufferedWriter != null) {
					bufferedWriter.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void retriveDatabaseERDInfo() throws SQLException, IOException {

		QueryParameter queryTables = new QueryParameter();
		String sqlQueryTableInfo = "SELECT "
				+ "MS_DOMAIN.DOMAIN_NUMBER ,"
				+ " MS_DOMAIN.DOMAIN_NAME ,"
				+ " MS_TABLES.TABLE_ID ,"
				+ " MS_TABLES.TABLE_NAME_JP ,"
				+ " MS_TABLES.TABLE_NAME "
				+ "FROM MS_DOMAIN , MS_TABLES "
				+ "WHERE "
				+ "MS_DOMAIN.DOMAIN_NUMBER IN ('501', '502', '503', '505', '506') "
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
				+ " ORDER BY DOMAIN_NUMBER , TABLE_ID , COLUMN_ID ";
		QueryParameter queryTableColumns = new QueryParameter();
		queryTableColumns.setSqlClause(sqlQueryTableColumns);

		StringBuilder resultBuffered = new StringBuilder(1024);
		while (jdbcQueryTablenames.hasNext()) {
			resultBuffered.setLength(0);
			String domainNumber = jdbcQueryTablenames.getJDBCValue("DOMAIN_NUMBER").toStringVal();
			String domainName = jdbcQueryTablenames.getJDBCValue("DOMAIN_NAME").toStringVal();
			BigDecimal tableID = jdbcQueryTablenames.getJDBCValue("TABLE_ID").toDecimalVal();
			String tableName = jdbcQueryTablenames.getJDBCValue("TABLE_NAME").toStringVal();
			String tableNameJP = jdbcQueryTablenames.getJDBCValue("TABLE_NAME_JP").toStringVal();

			resultBuffered.append(replaceNull(domainNumber));
			resultBuffered.append(SPLIT_CHAR);
			resultBuffered.append(replaceNull(domainName));
			resultBuffered.append(SPLIT_CHAR);
			resultBuffered.append(replaceNull(tableID));
			resultBuffered.append(SPLIT_CHAR);
			resultBuffered.append(replaceNull(tableName));
			resultBuffered.append(SPLIT_CHAR);
			resultBuffered.append(replaceNull(tableNameJP));
			resultBuffered.append(SPLIT_CHAR);
			int tableInfoLength = resultBuffered.length();

			logger.debug("【DOMAIN_ID】" + TextUtil.rPad(domainNumber, 5) + "【DOMAIN_NAME】" + TextUtil.rPad(domainName, 15) + " 【TABLE_NAME】" + TextUtil.rPad(tableName, 50) + "【TABLE_NAME_JP】"
					+ TextUtil.rPad(tableNameJP, 50));

			queryTableColumns.getParameterList().clear();
			queryTableColumns.getParameterList().add(domainNumber);
			queryTableColumns.getParameterList().add(tableID);
			JDBCQueryExecutor jdbcQueryTableColumns = jdbcObject.query(queryTableColumns);
			jdbcQueryTableColumns.execute();
			while (jdbcQueryTableColumns.hasNext()) {
				resultBuffered.setLength(tableInfoLength);
				BigDecimal columnID = jdbcQueryTableColumns.getJDBCValue("COLUMN_ID").toDecimalVal();
				String columnName = jdbcQueryTableColumns.getJDBCValue("COLUMN_NAME").toStringVal();
				String columnNameJP = jdbcQueryTableColumns.getJDBCValue("COLUMN_NAME_JP").toStringVal();
				BigDecimal pkPosition = jdbcQueryTableColumns.getJDBCValue("PK_POSITION").toDecimalVal();

				resultBuffered.append(replaceNull(columnID));
				resultBuffered.append(SPLIT_CHAR);
				resultBuffered.append(replaceNull(columnName));
				resultBuffered.append(SPLIT_CHAR);
				resultBuffered.append(replaceNull(columnNameJP));
				resultBuffered.append(SPLIT_CHAR);
				resultBuffered.append(replaceNull(pkPosition));
				resultBuffered.append(SPLIT_CHAR);

				bufferedWriter.write(resultBuffered.toString());
				bufferedWriter.newLine();
			}
		}
	}

	private Object replaceNull(Object obj) {

		if (obj == null) {
			return "";
		}

		return obj;
	}
}
