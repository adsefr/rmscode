package com.rms.base.jdbc.model;

/**
 *
 * @author ri.meisei
 * @since 2015/10/30
 */
public class DataBaseMeta {

	private String catalogSeparator;

	private Integer databaseMajorVersion;

	private Integer databaseMinorVersion;

	private String databaseProductName;

	private String databaseProductVersion;

	private Integer driverMajorVersion;

	private Integer driverMinorVersion;

	private String driverName;

	private String driverVersion;

	private Integer jdbcMajorVersion;

	private Integer jdbcMinorVersion;

	private String url;

	private String searchStringEscape;

	public DataBaseMeta() {
	}

	/**
	 * @return catalogSeparator
	 */
	public String getCatalogSeparator() {

		return catalogSeparator;
	}

	/**
	 * @param catalogSeparator
	 *            セットする catalogSeparator
	 */
	public void setCatalogSeparator(String catalogSeparator) {

		this.catalogSeparator = catalogSeparator;
	}

	/**
	 * @return databaseMajorVersion
	 */
	public Integer getDatabaseMajorVersion() {

		return databaseMajorVersion;
	}

	/**
	 * @param databaseMajorVersion
	 *            セットする databaseMajorVersion
	 */
	public void setDatabaseMajorVersion(Integer databaseMajorVersion) {

		this.databaseMajorVersion = databaseMajorVersion;
	}

	/**
	 * @return databaseMinorVersion
	 */
	public Integer getDatabaseMinorVersion() {

		return databaseMinorVersion;
	}

	/**
	 * @param databaseMinorVersion
	 *            セットする databaseMinorVersion
	 */
	public void setDatabaseMinorVersion(Integer databaseMinorVersion) {

		this.databaseMinorVersion = databaseMinorVersion;
	}

	/**
	 * @return databaseProductName
	 */
	public String getDatabaseProductName() {

		return databaseProductName;
	}

	/**
	 * @param databaseProductName
	 *            セットする databaseProductName
	 */
	public void setDatabaseProductName(String databaseProductName) {

		this.databaseProductName = databaseProductName;
	}

	/**
	 * @return databaseProductVersion
	 */
	public String getDatabaseProductVersion() {

		return databaseProductVersion;
	}

	/**
	 * @param databaseProductVersion
	 *            セットする databaseProductVersion
	 */
	public void setDatabaseProductVersion(String databaseProductVersion) {

		this.databaseProductVersion = databaseProductVersion;
	}

	/**
	 * @return driverMajorVersion
	 */
	public Integer getDriverMajorVersion() {

		return driverMajorVersion;
	}

	/**
	 * @param driverMajorVersion
	 *            セットする driverMajorVersion
	 */
	public void setDriverMajorVersion(Integer driverMajorVersion) {

		this.driverMajorVersion = driverMajorVersion;
	}

	/**
	 * @return driverMinorVersion
	 */
	public Integer getDriverMinorVersion() {

		return driverMinorVersion;
	}

	/**
	 * @param driverMinorVersion
	 *            セットする driverMinorVersion
	 */
	public void setDriverMinorVersion(Integer driverMinorVersion) {

		this.driverMinorVersion = driverMinorVersion;
	}

	/**
	 * @return driverName
	 */
	public String getDriverName() {

		return driverName;
	}

	/**
	 * @param driverName
	 *            セットする driverName
	 */
	public void setDriverName(String driverName) {

		this.driverName = driverName;
	}

	/**
	 * @return driverVersion
	 */
	public String getDriverVersion() {

		return driverVersion;
	}

	/**
	 * @param driverVersion
	 *            セットする driverVersion
	 */
	public void setDriverVersion(String driverVersion) {

		this.driverVersion = driverVersion;
	}

	/**
	 * @return jdbcMajorVersion
	 */
	public Integer getJdbcMajorVersion() {

		return jdbcMajorVersion;
	}

	/**
	 * @param jdbcMajorVersion
	 *            セットする jdbcMajorVersion
	 */
	public void setJdbcMajorVersion(Integer jdbcMajorVersion) {

		this.jdbcMajorVersion = jdbcMajorVersion;
	}

	/**
	 * @return jdbcMinorVersion
	 */
	public Integer getJdbcMinorVersion() {

		return jdbcMinorVersion;
	}

	/**
	 * @param jdbcMinorVersion
	 *            セットする jdbcMinorVersion
	 */
	public void setJdbcMinorVersion(Integer jdbcMinorVersion) {

		this.jdbcMinorVersion = jdbcMinorVersion;
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
	 * @return searchStringEscape
	 */
	public String getSearchStringEscape() {

		return searchStringEscape;
	}

	/**
	 * @param searchStringEscape
	 *            セットする searchStringEscape
	 */
	public void setSearchStringEscape(String searchStringEscape) {

		this.searchStringEscape = searchStringEscape;
	}

}
