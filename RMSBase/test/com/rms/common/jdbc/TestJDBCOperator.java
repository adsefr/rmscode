package com.rms.common.jdbc;

import java.sql.SQLException;

import com.rms.base.jdbc.model.DataBaseInfo;
import com.rms.common.jdbc.model.DataBaseType;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/02/12
 */
public class TestJDBCOperator {

	public static void main(String[] args) throws SQLException {

		DataBaseInfo dataBase = new DataBaseInfo();
		dataBase.setHost("localhost");
		dataBase.setPort(5432);
		dataBase.setDataBaseName("Ishioka");
		dataBase.setUserId("Ishioka");
		dataBase.setPassword("admin");
		dataBase.setDataBaseType(DataBaseType.POSTGRESQL);

		JDBCOperator jdbcOperator = JDBC.getOperator(dataBase);
//		List<TableMeta> tableMetas = jdbcOperator.getMetaDataOperator().getTableMetas();
//		for (TableMeta tableMeta : tableMetas) {
//			Table table = jdbcOperator.select(tableMeta);
//			List<Row> rowlist = table.getRowList();
//			System.out.println(rowlist.size());
//		}

		String sql ="SELECT 管理番号, 同義語ＩＤ, レコード種別, 第１主題カテゴリー, 第２主題カテゴリー, 第３主題カテゴリー, 第４主題カテゴリー, 第５主題カテゴリー, 第６主題カテゴリー, 関係子, 対応ディスクリプタ／日化辞ＩＤ, スコープ, シソーラス語番号, ＭｅＳＨＵＩ, データ作成日, データ更新日, 登録状態, 削除フラグ  FROM yougo.同義語情報";

		jdbcOperator.select(sql,null);
	}
}
