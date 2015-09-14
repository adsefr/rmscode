package jp.co.c21.jdbc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import com.jcraft.jsch.JSchException;
import com.rms.base.jdbc.model.DataBaseInfo;
import com.rms.base.jdbc.model.DataBaseType;
import com.rms.base.logging.Logger;
import com.rms.common.jdbc.JDBCObject;
import com.rms.net.ssh.SSHSessionFatory;
import com.rms.net.ssh.model.SSHAccessInfo;
import com.rms.net.ssh.model.SSHSession;
import com.rms.util.jdbc.CopyData;

public class CopyDataTask {

	private final Logger logger = Logger.getLogger(CopyDataTask.class);

	private static SSHSession sshSession;

	private static DataBaseInfo srcDatabaseInfo;

	private static DataBaseInfo destDatabaseInfo;

	private JDBCObject srcJDBCObject;

	private JDBCObject destJDBCObject;

	private CopyData copyData;

	@BeforeClass
	public static void beforeClass() {

		SSHAccessInfo sshAccessInfo = new SSHAccessInfo();
		sshAccessInfo.setSshUserId("auvm");
		sshAccessInfo.setSshHost("52.68.37.123");
		sshAccessInfo.setSshPort(22);
		sshAccessInfo.setPrivateKey("D:/au-virtual-mall/document/trunk/11_インフラ関連/AWS関連/秘密鍵(検証環境)/auVM-dev-web_c21.ppk");
		sshAccessInfo.setPasswordPhrase(null);
		sshAccessInfo.setRemoteHost("auvmdb.cwagdt1wygdl.ap-northeast-1.rds.amazonaws.com");
		sshAccessInfo.setRemotePort(5432);
		sshAccessInfo.setForwardHost("localhost");
		sshAccessInfo.setForwardPort(15432);

		srcDatabaseInfo = new DataBaseInfo();
		srcDatabaseInfo.setDataBaseType(DataBaseType.POSTGRESQL);
		srcDatabaseInfo.setDriver("org.postgresql.Driver");
		srcDatabaseInfo.setHost(sshAccessInfo.getForwardHost());
		srcDatabaseInfo.setPort(sshAccessInfo.getForwardPort());
		srcDatabaseInfo.setDataBaseName("auvm2");
		srcDatabaseInfo.setUserId("auvmadmin2");
		srcDatabaseInfo.setPassword("2wsxCDE#2");
		srcDatabaseInfo.setReadOnly(true);

		destDatabaseInfo = new DataBaseInfo();
		destDatabaseInfo.setDataBaseType(DataBaseType.POSTGRESQL);
		destDatabaseInfo.setDriver("org.postgresql.Driver");
		destDatabaseInfo.setHost("localhost");
		destDatabaseInfo.setPort(5432);
		destDatabaseInfo.setDataBaseName("V420_DEV01");
		destDatabaseInfo.setUserId("V420_DEV01usr");
		destDatabaseInfo.setPassword("V420_DEV01pwd");

		try {
			sshSession = SSHSessionFatory.getSession(sshAccessInfo);
		} catch (JSchException e) {
			logger.error(e);
		}
	}

	@AfterClass
	public static void afterClass() {

		if (sshSession != null) {
			sshSession.disconnect();
		}
	}

	@Before
	public void before() throws SQLException {

		srcJDBCObject = JDBCObject.getInstance(srcDatabaseInfo);
		srcJDBCObject.startTransaction();
		destJDBCObject = JDBCObject.getInstance(destDatabaseInfo);
		destJDBCObject.startTransaction();

		copyData = new CopyData(srcJDBCObject, destJDBCObject);
	}

	@After
	public void after() {

		try {
			srcJDBCObject.close();
		} catch (SQLException e) {
			logger.error(e);
		}
		try {
			destJDBCObject.close();
		} catch (SQLException e) {
			logger.error(e);
		}
	}

	// @Test
	public void testCopy1() {

		try {
			copyData.copy("public", "public");
		} catch (Exception e) {
			logger.error(e);
		}
	}

	// @Test
	public void testCopy2() {

		List<String> tableNames = new ArrayList<>();
		tableNames.add("tshopdisp");
		tableNames.add("tshopdispdepl");
		tableNames.add("tshopdispgoods");
		tableNames.add("tshopdispgoodsdepl");

		try {
			copyData.copy("public", "public", tableNames);
		} catch (Exception e) {
			logger.error(e);
		}
	}
}
