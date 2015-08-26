package com.rms.net.ssh;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.rms.base.validate.Assertion;
import com.rms.net.ssh.model.SSHAccessInfo;
import com.rms.net.ssh.model.SSHSession;

public class SSHSessionFatory {

	public static SSHSession getSession(SSHAccessInfo sshAccessInfo) throws JSchException {

		Assertion.assertNotNull("sshAccessInfo", sshAccessInfo);

		JSch jsch = new JSch();
		Session session = null;
		String privateKey = sshAccessInfo.getPrivateKey();
		String passwordPhrase = sshAccessInfo.getPasswordPhrase();
		if (privateKey != null && !"".equals(privateKey)) {
			if (passwordPhrase != null && "".equals(passwordPhrase)) {
				jsch.addIdentity(privateKey, passwordPhrase);
			} else {
				jsch.addIdentity(privateKey);
			}
		}

		String sshUserId = sshAccessInfo.getSshUserId();
		String sshHost = sshAccessInfo.getSshHost();
		Integer sshPort = sshAccessInfo.getSshPort();
		session = jsch.getSession(sshUserId, sshHost, sshPort);
		session.setConfig("StrictHostKeyChecking", "no");
		session.connect();
		String remoteHost = sshAccessInfo.getRemoteHost();
		Integer remotePort = sshAccessInfo.getRemotePort();
		String forwardHost = sshAccessInfo.getForwardHost();
		Integer forwardPort = sshAccessInfo.getForwardPort();
		if (forwardHost == null || forwardHost.equalsIgnoreCase("localhost") || forwardHost.equals("127.0.0.1")) {
			session.setPortForwardingL(forwardPort, remoteHost, remotePort);
		} else {
			session.setPortForwardingR(forwardHost, forwardPort, remoteHost, remotePort);
		}

		return new SSHSession(session);
	}
}
