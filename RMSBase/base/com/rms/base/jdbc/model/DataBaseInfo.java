package com.rms.base.jdbc.model;

import java.util.HashSet;
import java.util.Set;

import com.rms.base.jdbc.constant.HoldabilityType;
import com.rms.base.jdbc.constant.TransactionType;

/**
 *
 * @author ri.meisei
 * @since 2014/01/30
 */
public class DataBaseInfo {

	private String driver;

	private String url;

	private String host;

	private String userId;

	private String password;

	private int port;

	private DataBaseType dataBaseType;

	private String dataBaseName;

	private Set<String> schemaCollection = new HashSet<>();

	private boolean autoCommit = false;

	private boolean readOnly = false;

	private TransactionType transactionType = TransactionType.TRANSACTION_READ_COMMITTED;

	private HoldabilityType holdabilityType = HoldabilityType.CLOSE_CURSORS_AT_COMMIT;

	public DataBaseInfo() {

	}

	/**
	 * @return driver
	 */
	public String getDriver() {

		return driver;
	}

	/**
	 * @param driver
	 *            セットする driver
	 */
	public void setDriver(String driver) {

		this.driver = driver;
	}

	/**
	 * @return url
	 */
	public String getUrl() {

		return url;
	}

	/**
	 * @param url
	 *            セットする url
	 */
	public void setUrl(String url) {

		this.url = url;
	}

	/**
	 * @return host
	 */
	public String getHost() {

		return host;
	}

	/**
	 * @param host
	 *            セットする host
	 */
	public void setHost(String host) {

		this.host = host;
	}

	/**
	 * @return userId
	 */
	public String getUserId() {

		return userId;
	}

	/**
	 * @param userId
	 *            セットする userId
	 */
	public void setUserId(String userId) {

		this.userId = userId;
	}

	/**
	 * @return password
	 */
	public String getPassword() {

		return password;
	}

	/**
	 * @param password
	 *            セットする password
	 */
	public void setPassword(String password) {

		this.password = password;
	}

	/**
	 * @return port
	 */
	public int getPort() {

		return port;
	}

	/**
	 * @param port
	 *            セットする port
	 */
	public void setPort(int port) {

		this.port = port;
	}

	/**
	 * @return dataBaseType
	 */
	public DataBaseType getDataBaseType() {

		return dataBaseType;
	}

	/**
	 * @param dataBaseType
	 *            セットする dataBaseType
	 */
	public void setDataBaseType(DataBaseType dataBaseType) {

		this.dataBaseType = dataBaseType;
	}

	/**
	 * @return dataBaseName
	 */
	public String getDataBaseName() {

		return dataBaseName;
	}

	/**
	 * @param dataBaseName
	 *            セットする dataBaseName
	 */
	public void setDataBaseName(String dataBaseName) {

		this.dataBaseName = dataBaseName;
	}

	/**
	 * @return schemaCollection
	 */
	public Set<String> getSchemaCollection() {

		return schemaCollection;
	}

	/**
	 * @param schemaCollection
	 *            セットする schemaCollection
	 */
	public void setSchemaCollection(Set<String> schemaCollection) {

		this.schemaCollection = schemaCollection;
	}

	/**
	 * @return autoCommit
	 */
	public boolean isAutoCommit() {

		return autoCommit;
	}

	/**
	 * @param autoCommit
	 *            セットする autoCommit
	 */
	public void setAutoCommit(boolean autoCommit) {

		this.autoCommit = autoCommit;
	}

	/**
	 * @return readOnly
	 */
	public boolean isReadOnly() {

		return readOnly;
	}

	/**
	 * @param readOnly
	 *            セットする readOnly
	 */
	public void setReadOnly(boolean readOnly) {

		this.readOnly = readOnly;
	}

	/**
	 * @return transactionType
	 */
	public TransactionType getTransactionType() {

		return transactionType;
	}

	/**
	 * @param transactionType
	 *            セットする transactionType
	 */
	public void setTransactionType(TransactionType transactionType) {

		this.transactionType = transactionType;
	}

	/**
	 * @return holdabilityType
	 */
	public HoldabilityType getHoldabilityType() {

		return holdabilityType;
	}

	/**
	 * @param holdabilityType
	 *            セットする holdabilityType
	 */
	public void setHoldabilityType(HoldabilityType holdabilityType) {

		this.holdabilityType = holdabilityType;
	}
}
