package com.rms.net.ssh.model;

/**
 *
 * @author ri.meisei
 * @since 2015/08/19
 */
public class SSHAccessInfo {

	private String sshUserId;

	private String sshPassword;

	private String sshHost;

	private Integer sshPort;

	private String privateKey;

	private String passwordPhrase;

	private String remoteHost;

	private Integer remotePort;

	private String forwardHost;

	private Integer forwardPort;

	public SSHAccessInfo() {

	}

	/**
	 * @return sshUserId
	 */
	public String getSshUserId() {

		return sshUserId;
	}

	/**
	 * @param sshUserId
	 *            セットする sshUserId
	 */
	public void setSshUserId(String sshUserId) {

		this.sshUserId = sshUserId;
	}

	/**
	 * @return sshPassword
	 */
	public String getSshPassword() {

		return sshPassword;
	}

	/**
	 * @param sshPassword
	 *            セットする sshPassword
	 */
	public void setSshPassword(String sshPassword) {

		this.sshPassword = sshPassword;
	}

	/**
	 * @return sshHost
	 */
	public String getSshHost() {

		return sshHost;
	}

	/**
	 * @param sshHost
	 *            セットする sshHost
	 */
	public void setSshHost(String sshHost) {

		this.sshHost = sshHost;
	}

	/**
	 * @return sshPort
	 */
	public Integer getSshPort() {

		return sshPort;
	}

	/**
	 * @param sshPort
	 *            セットする sshPort
	 */
	public void setSshPort(Integer sshPort) {

		this.sshPort = sshPort;
	}

	/**
	 * @return privateKey
	 */
	public String getPrivateKey() {

		return privateKey;
	}

	/**
	 * @param privateKey
	 *            セットする privateKey
	 */
	public void setPrivateKey(String privateKey) {

		this.privateKey = privateKey;
	}

	/**
	 * @return passwordPhrase
	 */
	public String getPasswordPhrase() {

		return passwordPhrase;
	}

	/**
	 * @param passwordPhrase
	 *            セットする passwordPhrase
	 */
	public void setPasswordPhrase(String passwordPhrase) {

		this.passwordPhrase = passwordPhrase;
	}

	/**
	 * @return remoteHost
	 */
	public String getRemoteHost() {

		return remoteHost;
	}

	/**
	 * @param remoteHost
	 *            セットする remoteHost
	 */
	public void setRemoteHost(String remoteHost) {

		this.remoteHost = remoteHost;
	}

	/**
	 * @return remotePort
	 */
	public Integer getRemotePort() {

		return remotePort;
	}

	/**
	 * @param remotePort
	 *            セットする remotePort
	 */
	public void setRemotePort(Integer remotePort) {

		this.remotePort = remotePort;
	}

	/**
	 * @return forwardHost
	 */
	public String getForwardHost() {

		return forwardHost;
	}

	/**
	 * @param forwardHost
	 *            セットする forwardHost
	 */
	public void setForwardHost(String forwardHost) {

		this.forwardHost = forwardHost;
	}

	/**
	 * @return forwardPort
	 */
	public Integer getForwardPort() {

		return forwardPort;
	}

	/**
	 * @param forwardPort
	 *            セットする forwardPort
	 */
	public void setForwardPort(Integer forwardPort) {

		this.forwardPort = forwardPort;
	}

}
