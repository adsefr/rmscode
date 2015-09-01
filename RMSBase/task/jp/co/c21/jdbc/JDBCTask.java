package jp.co.c21.jdbc;

import com.rms.base.jdbc.model.DataBaseInfo;
import com.rms.base.jdbc.model.DataBaseType;
import com.rms.net.ssh.SSHSessionFatory;
import com.rms.net.ssh.model.SSHAccessInfo;
import com.rms.net.ssh.model.SSHSession;
import com.rms.tool.jdbc.JDBCOperator;

public class JDBCTask {

	public static void main(String[] args) {

		printSchemaNames();

	}

	public static void printSchemaNames() {

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

		SSHSession sshSession = null;
		try {
			sshSession = SSHSessionFatory.getSession(sshAccessInfo);

			DataBaseInfo dataBaseInfo = new DataBaseInfo();
			dataBaseInfo.setDataBaseType(DataBaseType.POSTGRESQL);
			dataBaseInfo.setDriver("org.postgresql.Driver");
			dataBaseInfo.setHost(sshAccessInfo.getForwardHost());
			dataBaseInfo.setPort(sshAccessInfo.getForwardPort());
			dataBaseInfo.setDataBaseName("auvm2");
			dataBaseInfo.setUserId("auvmadmin2");
			dataBaseInfo.setPassword("2wsxCDE#2");
			JDBCOperator.getAllTableNameList(dataBaseInfo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sshSession != null) {
				sshSession.disconnect();
			}
		}
	}
}
