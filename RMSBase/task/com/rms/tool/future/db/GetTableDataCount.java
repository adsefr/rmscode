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
import com.rms.base.jdbc.model.UpdateParameter;
import com.rms.base.logging.Logger;
import com.rms.common.jdbc.JDBCObject;
import com.rms.common.jdbc.JDBCQueryExecutor;
import com.rms.common.jdbc.JDBCUpdateExecutor;

public class GetTableDataCount {

	private final static Logger logger = Logger.getLogger(PrintObject.class);

	private static DataBaseInfo dataBaseInfo;

	private JDBCObject jdbcObject;

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
	public void printTableDataCount() throws SQLException {

		String[] tableNames1 = { "MS_INFOHENKANCMDBUNRUI", "MS_INFOHENKANCMD", "MS_INFOHENKANCOMMANDHKSU", "MS_CALENDAR", "MS_HANDYLOCKKAIJOSET", "KR_DATA_REFRESH_SERVICE",
				"KR_DATA_REFRESH_EXECUTION",
				"MS_REFRESH_DATA_GRP", "MS_REFRESH_DATA_GRP_IDX", "MS_REFRESH_DATA", "MS_BARCDHENSYU", "MS_BARCDHENSYUMST_SETYO", "MS_PARAMSETTANIGRP", "MS_PARAMSETTANINM",
				"MS_PARAMSETTANIGRP_DETAIL", "MS_RP_PARAM_KEY", "MS_RP_PARAM_NM", "MS_RP_PARAM_VAL", "MS_PARAMTEIGI", "MS_PARAMTEIGISETTANI", "MS_PARAMCHECKLOGICIDX", "MS_PROCESS", "MS_PROCESSBUNRUI",
				"MS_PROCESSSYUBE", "MS_PROCESSSYORIKBN", "MS_SYORITEIGINRYKFUNCTEIG", "MS_NRYKFUNCTIONBUNRUI", "MS_NRYKFUNCTIONBUNRUIIDX", "MS_NRYKFUNCTION", "MS_SYORITEIGIPRTFUNCTEIGI",
				"MS_PRTFUNCTIONBUNRUI", "MS_PRTFUNCTIONBUNRUIIDX", "MS_PRTFUNCTION", "MS_FORMKANRI", "MS_FILE_CATEGORY", "MS_HANYOPOPUPKR_WMS", "MS_HANYOKYOTSUBLBUNRUI", "MS_HANYOKYOTSUBLBUNRUIIDX",
				"MS_HANYOKYOTSUBL", "MS_HANYOKYOTSUBLPARAM", "MS_RP_SCREEN_ACTION", "MS_RP_SCREEN_TRN", "MS_RP_TRN", "MS_RP_TRN_BLGRP", "MS_RP_BLGRP", "MS_RP_BL", "MS_RP_BL_NM", "MS_RP_TRN_NM",
				"MS_SYORITEIGIBUNRUI", "MS_SYORITEIGIBUNRUIIDX", "MS_KYOTSUIMPORTSYORI", "MS_GAMEN", "MS_GAMENBUNRUIIDX", "MS_GAMENBUNRUI", "MS_GAMENNM", "MS_MENUKOSEI", "MS_MENUHJNM", "MS_OAC_GRP",
				"MS_OAC_GRP_FUNC_IDX" };

		String[] tableNames = { "MS_PARAMSETTANIGRP", "MS_PARAMSETTANINM", "MS_PARAMSETTANIGRP_DETAIL", "MS_RP_PARAM_KEY", "MS_RP_PARAM_NM", "MS_RP_PARAM_VAL", "MS_PARAMTEIGI", "MS_PARAMTEIGISETTANI",
				"MS_PARAMCHECKLOGICIDX", "MS_RP_SCREEN_ACTION", "MS_RP_SCREEN_TRN", "MS_RP_TRN", "MS_RP_TRN_BLGRP", "MS_RP_BLGRP", "MS_RP_BL", "MS_RP_BL_NM", "MS_RP_TRN_NM", "MS_SYORITEIGIBUNRUI",
				"MS_SYORITEIGIBUNRUIIDX", "MS_KYOTSUIMPORTSYORI", "KR_NINUSISOKOBETUCALENDAR", "MS_RB_JOB_DEF", "MS_RB_SCHEDULE_DEF", "MS_RP_JOB_TRAN", "MS_NINUSOKOBTUBARCDHYOJUN", "MS_BARCDRTNSET",
				"MS_PROCESSFLOW", "MS_PROCFLOWSYORIJYNSET", "MS_PROCFLOKETTEIKOMOKUSET", "MS_PROCFLOKETTEIJYOKEN", "MS_IOTEIGI", "MS_MULTILAYOUT", "MS_FILELAYOUT_KOMOKU", "KR_SAMPLEFILE",
				"MS_LAYOUTHENKANINFO", "MS_INFOHENKANSET", "MS_INFOHENKANSET_DETAIL", "MS_FLOOR", "MS_AREA", "MS_LOCATION", "MS_LOCATIONCDSET", "MS_LOCATIONCDSET_AREABETU", "MS_ARABTULCTZOKUSEI_SET",
				"MS_LOCATIONZOKUSEI_SET", "MS_LCTZOKUSEIBTUSEIGYOSET", "MS_SHINBTUKAKUNOLCT", "MS_KIGYO_WMS", "MS_NINUSI", "MS_BUMON", "MS_SOKONINUSI_IDX", "MS_SOKO", "MS_CONFIGURATIONMST_WMS",
				"MS_ZAIKOKANRIKOMOKUSET", "MS_PARAMKAKUCHOTANI", "MS_RP_PARAM_VAL_CSTM", "MS_DEFLCTKETTEIKMKSET", "MS_DEFLCTKETTEIJKEN", "MS_RENKEIFLDR", "MS_PRINTTRM", "KR_PRINTTRMSET",
				"MS_PRINTTRMJYOTAIKANRI", "MS_PRTMODULELELEASEKNR", "MS_PRINTER", "MS_PRINTERRTNSET", "MS_PRINTTOREI", "MS_DEFPRINTSET", "MS_USRPRINTSET", "MS_TRHKSKKNRKMK_SET", "MS_TORIHIKISAKI",
				"MS_SHOHINCATEGORY", "MS_SHOHINKANRIKOMOKU_SET", "MS_SHOHINLOTCATEGORY_SET", "MS_SHOHIN", "MS_SHOHINLOT", "MS_SOKOBETUSHOHINKANRISET", "MS_SAIBANSYUBE", "MS_SAIBAN",
				"MS_SAIBANBUNKATUKEYSETMST", "MS_SAIBANHENSYU", "KR_SAIBAN", "MS_NINUSISOKOSAIBANIDX", "MS_DENTANISET", "KR_OAC_FUNC", "MS_USR", "MS_USRROLEIDX", "MS_USRKENGENSETTANIIDX",
				"MS_KENGENSETTANI", "MS_KENGENSETTANI_DETAIL" };
		for (String tableName : tableNames) {
			String sqlClause = "SELECT COUNT(*) COUNT FROM " + tableName;
			QueryParameter queryParameter = new QueryParameter();
			queryParameter.setSqlClause(sqlClause);
			JDBCQueryExecutor jdbcQueryExecutor = jdbcObject.query(queryParameter);
			jdbcQueryExecutor.execute();
			if (jdbcQueryExecutor.hasNext()) {
				BigDecimal count = jdbcQueryExecutor.getJDBCValue(1).toDecimalVal();
				System.out.println(tableName + "-" + count.intValue());
			}

			jdbcQueryExecutor.close();
		}
	}

	// @Test
	public void deleteTableDataCount() throws SQLException {

		String[] tableNames = { "MS_PARAMSETTANIGRP", "MS_PARAMSETTANINM", "MS_PARAMSETTANIGRP_DETAIL", "MS_RP_PARAM_KEY", "MS_RP_PARAM_NM", "MS_RP_PARAM_VAL", "MS_PARAMTEIGI", "MS_PARAMTEIGISETTANI",
				"MS_PARAMCHECKLOGICIDX", "MS_RP_SCREEN_ACTION", "MS_RP_SCREEN_TRN", "MS_RP_TRN", "MS_RP_TRN_BLGRP", "MS_RP_BLGRP", "MS_RP_BL", "MS_RP_BL_NM", "MS_RP_TRN_NM", "MS_SYORITEIGIBUNRUI",
				"MS_SYORITEIGIBUNRUIIDX", "MS_KYOTSUIMPORTSYORI", "KR_NINUSISOKOBETUCALENDAR", "MS_RB_JOB_DEF", "MS_RB_SCHEDULE_DEF", "MS_RP_JOB_TRAN", "MS_NINUSOKOBTUBARCDHYOJUN", "MS_BARCDRTNSET",
				"MS_PROCESSFLOW", "MS_PROCFLOWSYORIJYNSET", "MS_PROCFLOKETTEIKOMOKUSET", "MS_PROCFLOKETTEIJYOKEN", "MS_IOTEIGI", "MS_MULTILAYOUT", "MS_FILELAYOUT_KOMOKU", "KR_SAMPLEFILE",
				"MS_LAYOUTHENKANINFO", "MS_INFOHENKANSET", "MS_INFOHENKANSET_DETAIL", "MS_FLOOR", "MS_AREA", "MS_LOCATION", "MS_LOCATIONCDSET", "MS_LOCATIONCDSET_AREABETU", "MS_ARABTULCTZOKUSEI_SET",
				"MS_LOCATIONZOKUSEI_SET", "MS_LCTZOKUSEIBTUSEIGYOSET", "MS_SHINBTUKAKUNOLCT", "MS_KIGYO_WMS", "MS_NINUSI", "MS_BUMON", "MS_SOKONINUSI_IDX", "MS_SOKO", "MS_CONFIGURATIONMST_WMS",
				"MS_ZAIKOKANRIKOMOKUSET", "MS_PARAMKAKUCHOTANI", "MS_RP_PARAM_VAL_CSTM", "MS_DEFLCTKETTEIKMKSET", "MS_DEFLCTKETTEIJKEN", "MS_RENKEIFLDR", "MS_PRINTTRM", "KR_PRINTTRMSET",
				"MS_PRINTTRMJYOTAIKANRI", "MS_PRTMODULELELEASEKNR", "MS_PRINTER", "MS_PRINTERRTNSET", "MS_PRINTTOREI", "MS_DEFPRINTSET", "MS_USRPRINTSET", "MS_TRHKSKKNRKMK_SET", "MS_TORIHIKISAKI",
				"MS_SHOHINCATEGORY", "MS_SHOHINKANRIKOMOKU_SET", "MS_SHOHINLOTCATEGORY_SET", "MS_SHOHIN", "MS_SHOHINLOT", "MS_SOKOBETUSHOHINKANRISET", "MS_SAIBANSYUBE", "MS_SAIBAN",
				"MS_SAIBANBUNKATUKEYSETMST", "MS_SAIBANHENSYU", "KR_SAIBAN", "MS_NINUSISOKOSAIBANIDX", "MS_DENTANISET", "KR_OAC_FUNC", "MS_USR", "MS_USRROLEIDX", "MS_USRKENGENSETTANIIDX",
				"MS_KENGENSETTANI", "MS_KENGENSETTANI_DETAIL" };

		for (String tableName : tableNames) {
			String sqlClause = "DELETE FROM " + tableName;
			UpdateParameter updateParameter = new UpdateParameter();
			updateParameter.setSqlClause(sqlClause);
			JDBCUpdateExecutor jdbcUpdateExecutor = jdbcObject.update(updateParameter);
			jdbcUpdateExecutor.execute();
			jdbcUpdateExecutor.close();
		}
	}

}
