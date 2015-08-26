package jp.co.c21.jdbc;

import java.util.ArrayList;
import java.util.List;

import com.rms.base.jdbc.model.DataBaseInfo;
import com.rms.base.jdbc.model.DataBaseType;
import com.rms.net.ssh.SSHSessionFatory;
import com.rms.net.ssh.model.SSHAccessInfo;
import com.rms.net.ssh.model.SSHSession;
import com.rms.tool.jdbc.CopyTables;

public class CopyTableTask {

	public static void main(String[] args) {

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

		DataBaseInfo srcDatabaseInfo = new DataBaseInfo();
		srcDatabaseInfo.setDataBaseType(DataBaseType.POSTGRESQL);
		srcDatabaseInfo.setDriver("org.postgresql.Driver");
		srcDatabaseInfo.setHost(sshAccessInfo.getForwardHost());
		srcDatabaseInfo.setPort(sshAccessInfo.getForwardPort());
		srcDatabaseInfo.setDataBaseName("auvm2");
		srcDatabaseInfo.setUserId("auvmadmin2");
		srcDatabaseInfo.setPassword("2wsxCDE#2");
		srcDatabaseInfo.setReadOnly(true);

		SSHAccessInfo sshAccessInfo2 = new SSHAccessInfo();
		sshAccessInfo2.setSshUserId("auvm");
		sshAccessInfo2.setSshHost("52.68.37.123");
		sshAccessInfo2.setSshPort(22);
		sshAccessInfo2.setPrivateKey("D:/au-virtual-mall/document/trunk/11_インフラ関連/AWS関連/秘密鍵(検証環境)/auVM-dev-web_c21.ppk");
		sshAccessInfo2.setPasswordPhrase(null);
		sshAccessInfo2.setRemoteHost("auvmdb.cwagdt1wygdl.ap-northeast-1.rds.amazonaws.com");
		sshAccessInfo2.setRemotePort(5432);
		sshAccessInfo2.setForwardHost("localhost");
		sshAccessInfo2.setForwardPort(15432);

		DataBaseInfo destDatabaseInfo = new DataBaseInfo();
		destDatabaseInfo.setDataBaseType(DataBaseType.POSTGRESQL);
		destDatabaseInfo.setDriver("org.postgresql.Driver");
		destDatabaseInfo.setHost("localhost");
		destDatabaseInfo.setPort(5432);
		destDatabaseInfo.setDataBaseName("V420_DEV01");
		destDatabaseInfo.setUserId("V420_DEV01usr");
		destDatabaseInfo.setPassword("V420_DEV01pwd");

		SSHSession sshSession = null;
		SSHSession sshSession2 = null;
		try {
			sshSession = SSHSessionFatory.getSession(sshAccessInfo);
			String srcSchemaName = "public";
			String destSchemaName = "public";
			List<String> srcTables = new ArrayList<>();
			List<String> destTables = new ArrayList<>();
			srcTables.add("tgoodsort");
			destTables.addAll(srcTables);

			CopyTables copyTables = new CopyTables(srcDatabaseInfo, destDatabaseInfo);
			copyTables.copy(srcSchemaName, srcTables, destSchemaName, destTables);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sshSession != null) {
				sshSession.disconnect();
			}
			if (sshSession2 != null) {
				sshSession2.disconnect();
			}
		}

	}
}
