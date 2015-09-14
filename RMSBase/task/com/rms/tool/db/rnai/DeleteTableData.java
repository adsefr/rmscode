package com.rms.tool.db.rnai;

import java.sql.SQLException;
import java.util.List;

import com.rms.base.jdbc.JDBCConnection;
import com.rms.base.jdbc.JDBCUpdater;
import com.rms.base.jdbc.model.DataBaseInfo;
import com.rms.base.jdbc.model.TableMeta;
import com.rms.common.jdbc.JDBCObject;
import com.rms.common.jdbc.model.DataBaseType;
import com.rms.common.jdbc.old.JDBCMetaData;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/02/26
 */
public class DeleteTableData {

	public static void main(String[] args) throws SQLException {

		DataBaseInfo dataBase = new DataBaseInfo();
		dataBase.setHost("localhost");
		dataBase.setPort(5432);
		dataBase.setDataBaseType(DataBaseType.POSTGRESQL);
		dataBase.setDataBaseName("Ishioka");
		dataBase.setUserId("postgres");
		dataBase.setPassword("admin");
		JDBCObject jdbcObject = JDBCObject.getInstance(dataBase);
		JDBCConnection jdbcConnection = jdbcObject.getJDBCConnection();
		jdbcConnection.connection();
		JDBCUpdater jdbcUpdater = jdbcObject.createJDBCUpdater(jdbcConnection);
		try {

			JDBCMetaData jdbcMetaData = jdbcConnection.getJDBCMetaData();
			List<TableMeta> tableMetas = jdbcMetaData.getTableMetas();
			for (TableMeta tableMeta : tableMetas) {
				if (tableMeta.getTableType().equals("TABLE")) {
					System.out.println(tableMeta.getTableType() + " " + tableMeta.getTableCatalog() + "."
							+ tableMeta.getTableSchema() + "." + tableMeta.getTableName());

					// String sql = "DELETE FROM \"" +
					// tableMeta.getTableSchema() + "\".\"" +
					// tableMeta.getTableName()
					// + "\"";
					String sql = "TRUNCATE TABLE \"" + tableMeta.getTableSchema() + "\".\"" + tableMeta.getTableName()
							+ "\" CASCADE";
					jdbcUpdater.setSqlClause(sql);
					jdbcUpdater.executeUpdate();
					jdbcConnection.commit();
				}
			}
		} finally {
			jdbcConnection.close();
		}
	}
}
