package com.rms.net.ssh.model;

import com.jcraft.jsch.Session;

public class SSHSession {

	private Session session;

	public SSHSession(Session session) {

		this.session = session;
	}

	public void disconnect() {

		session.disconnect();
	}
}
