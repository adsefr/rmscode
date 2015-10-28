package com.rms.tool.future.erd.bean;

import java.math.BigDecimal;
import java.util.Set;
import java.util.TreeSet;

public class TableInfo implements Comparable<TableInfo> {

	private BigDecimal domainNumber;
	private String domainName;
	private BigDecimal tableId;
	private String tableName;
	private String tableNameJP;
	private String dbTableNameJP;
	private String dbTableNameJPConverted;
	private String fileTableNameJP;
	private String fileTableNameJPConverted;
	private String definedScope;
	private boolean definedDuplicated;
	private boolean processed;
	private String visioFileName;
	private String visioPageName;

	private Set<ColumnInfo> columnInfos = new TreeSet<>();

	public TableInfo() {
	}

	@Override
	public int compareTo(TableInfo o) {
		int result = 0;

		if (domainNumber == null && o.getDomainNumber() != null) {
			return 1;
		}
		if (domainNumber != null && o.getDomainNumber() == null) {
			return -1;
		}
		if (domainNumber != null && o.getDomainNumber() != null) {
			result = domainNumber.compareTo(o.getDomainNumber());
			if (result != 0) {
				return result;
			}
		}

		if (domainName == null && o.getDomainName() != null) {
			return 1;
		}
		if (domainName != null && o.getDomainName() == null) {
			return -1;
		}
		if (domainName != null && o.getDomainName() != null) {
			result = domainName.compareTo(o.getDomainName());
			if (result != 0) {
				return result;
			}
		}

		if (tableId == null && o.getTableId() != null) {
			return 1;
		}
		if (tableId != null && o.getTableId() == null) {
			return -1;
		}

		if (tableId != null && o.getTableId() != null) {
			result = tableId.compareTo(o.getTableId());
			if (result != 0) {
				return result;
			}
		}

		if (tableNameJP == null && o.getTableNameJP() != null) {
			return 1;
		}
		if (tableNameJP != null && o.getTableNameJP() == null) {
			return -1;
		}
		if (tableNameJP != null && o.getTableNameJP() != null) {
			result = tableNameJP.compareTo(o.getTableNameJP());
			if (result != 0) {
				return result;
			}
		}

		return result;
	}

	public BigDecimal getDomainNumber() {
		return domainNumber;
	}

	public void setDomainNumber(BigDecimal domainNumber) {
		this.domainNumber = domainNumber;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public BigDecimal getTableId() {
		return tableId;
	}

	public void setTableId(BigDecimal tableId) {
		this.tableId = tableId;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTableNameJP() {
		return tableNameJP;
	}

	public void setTableNameJP(String tableNameJP) {
		this.tableNameJP = tableNameJP;
	}

	public String getDbTableNameJP() {
		return dbTableNameJP;
	}

	public void setDbTableNameJP(String dbTableNameJP) {
		this.dbTableNameJP = dbTableNameJP;
	}

	public String getDbTableNameJPConverted() {
		return dbTableNameJPConverted;
	}

	public void setDbTableNameJPConverted(String dbTableNameJPConverted) {
		this.dbTableNameJPConverted = dbTableNameJPConverted;
	}

	public String getFileTableNameJP() {
		return fileTableNameJP;
	}

	public void setFileTableNameJP(String fileTableNameJP) {
		this.fileTableNameJP = fileTableNameJP;
	}

	public String getFileTableNameJPConverted() {
		return fileTableNameJPConverted;
	}

	public void setFileTableNameJPConverted(String fileTableNameJPConverted) {
		this.fileTableNameJPConverted = fileTableNameJPConverted;
	}

	public String getDefinedScope() {
		return definedScope;
	}

	public void setDefinedScope(String definedScope) {
		this.definedScope = definedScope;
	}

	public boolean isDefinedDuplicated() {
		return definedDuplicated;
	}

	public void setDefinedDuplicated(boolean definedDuplicated) {
		this.definedDuplicated = definedDuplicated;
	}

	public boolean isProcessed() {
		return processed;
	}

	public void setProcessed(boolean processed) {
		this.processed = processed;
	}

	public String getVisioFileName() {
		return visioFileName;
	}

	public void setVisioFileName(String visioFileName) {
		this.visioFileName = visioFileName;
	}

	public String getVisioPageName() {
		return visioPageName;
	}

	public void setVisioPageName(String visioPageName) {
		this.visioPageName = visioPageName;
	}

	public Set<ColumnInfo> getColumnInfos() {
		return columnInfos;
	}

	public void setColumnInfos(Set<ColumnInfo> columnInfos) {
		this.columnInfos = columnInfos;
	}

}
