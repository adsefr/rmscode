package com.rms.tool.db.rnai;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import com.rms.base.jdbc.JDBCConnection;
import com.rms.base.jdbc.JDBCSelector;
import com.rms.base.jdbc.JDBCUpdater;
import com.rms.base.jdbc.constant.HoldabilityType;
import com.rms.base.jdbc.model.DataBaseInfo;
import com.rms.common.jdbc.JDBCObject;
import com.rms.common.jdbc.JDBCQueryResult;
import com.rms.common.jdbc.model.DataBaseType;

/**
 *
 *
 * @author ri.meisei
 * @since 2014/03/03
 */
public class InsertKeys {

	public static void main(String[] args) throws SQLException {

		Set<String> keys = new HashSet<String>();

		DataBaseInfo dataBase = new DataBaseInfo();
		dataBase.setHost("localhost");
		dataBase.setPort(5432);
		dataBase.setDataBaseType(DataBaseType.POSTGRESQL);
		dataBase.setDataBaseName("rimeisei");
		dataBase.setUserId("postgres");
		dataBase.setPassword("admin");
		JDBCObject jdbcObject = JDBCObject.newInstance(dataBase);
		JDBCConnection jdbcConnection = jdbcObject.getJDBCConnection();
		jdbcConnection.connection();
		JDBCSelector userSelector = jdbcObject.createJDBCSelector(jdbcConnection);
		userSelector.setQueryTimeout(100000);
		String users = "SELECT  \"users\".\"read_user_id\", \"misc\".\"jglobalid\" "
				+ "FROM \"public\".\"rm_users\" users,\"public\".\"rm_cv_data_misc\" misc "
				+ "WHERE users.id=misc.user_id and length(misc.jglobalid)=18";
		userSelector.setSqlClause(users);
		JDBCQueryResult rsUsers = userSelector.executeQuery();

		while (rsUsers.hasNext()) {
			String read_user_id = rsUsers.getValue(1, String.class);
			String jglobalid = rsUsers.getValue(2, String.class);

			System.out.println(read_user_id + " " + jglobalid);
			if (jglobalid.length() == 18 && !keys.contains(jdbcConnection)) {
				String sqlUpdate = "INSERT INTO public.keys(jglobalid, type, fkey) VALUES(?,?,?)";
				System.out.println(sqlUpdate);
				JDBCUpdater jdbcUpdater = jdbcObject.createJDBCUpdater(jdbcConnection);
				jdbcUpdater.setSqlClause(sqlUpdate);
				jdbcUpdater.addParameter(new BigDecimal(jglobalid));
				jdbcUpdater.addParameter(101);
				jdbcUpdater.addParameter(read_user_id);
				try {
					jdbcUpdater.executeUpdate();
					jdbcConnection.commit();
				} catch (Exception e) {
					System.out.println(e.getMessage());
					jdbcConnection.rollback();
				}
				jdbcUpdater.close();
			}
		}

		jdbcObject.close();
	}
}
