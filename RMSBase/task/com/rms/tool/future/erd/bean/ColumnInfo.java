package com.rms.tool.future.erd.bean;

import java.math.BigDecimal;

public class ColumnInfo implements Comparable<ColumnInfo> {
	private BigDecimal columnId;
	private String columnName;
	private String columnNameJP;
	private String dbColumnNameJP;
	private String dbColumnNameJPConverted;
	private String fileColumnNameJP;
	private String fileColumnNameJPConverted;
	private String columnDefinedScope;
	private BigDecimal primaryKeyPosition;
	private String primaryKeyDefinedScope;
	private String visioFileName;
	private String visioPageName;
	private boolean splited;
	private boolean processed;

	public ColumnInfo() {
	}

	@Override
	public int compareTo(ColumnInfo o) {
		int result = 0;

		if (columnId == null && o.getColumnId() != null) {
			return 1;
		}
		if (columnId != null && o.getColumnId() == null) {
			return -1;
		}

		if (columnId != null && o.getColumnId() != null) {
			result = columnId.compareTo(o.getColumnId());
			if (result != 0) {
				return result;
			}
		}

		if (columnName == null && o.getColumnName() != null) {
			return 1;
		}
		if (columnName != null && o.getColumnName() == null) {
			return -1;
		}
		if (columnName != null && o.getColumnName() != null) {
			result = columnName.compareTo(o.getColumnName());
			if (result != 0) {
				return result;
			}
		}

		if (columnNameJP == null && o.getColumnNameJP() != null) {
			return 1;
		}
		if (columnNameJP != null && o.getColumnNameJP() == null) {
			return -1;
		}
		if (columnNameJP != null && o.getColumnNameJP() != null) {
			result = columnNameJP.compareTo(o.getColumnNameJP());
			if (result != 0) {
				return result;
			}
		}

		return result;
	}

	public BigDecimal getColumnId() {
		return columnId;
	}

	public void setColumnId(BigDecimal columnId) {
		this.columnId = columnId;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getColumnNameJP() {
		return columnNameJP;
	}

	public void setColumnNameJP(String columnNameJP) {
		this.columnNameJP = columnNameJP;
	}

	public String getDbColumnNameJP() {
		return dbColumnNameJP;
	}

	public void setDbColumnNameJP(String dbColumnNameJP) {
		this.dbColumnNameJP = dbColumnNameJP;
	}

	public String getDbColumnNameJPConverted() {
		return dbColumnNameJPConverted;
	}

	public void setDbColumnNameJPConverted(String dbColumnNameJPConverted) {
		this.dbColumnNameJPConverted = dbColumnNameJPConverted;
	}

	public String getFileColumnNameJP() {
		return fileColumnNameJP;
	}

	public void setFileColumnNameJP(String fileColumnNameJP) {
		this.fileColumnNameJP = fileColumnNameJP;
	}

	public String getFileColumnNameJPConverted() {
		return fileColumnNameJPConverted;
	}

	public void setFileColumnNameJPConverted(String fileColumnNameJPConverted) {
		this.fileColumnNameJPConverted = fileColumnNameJPConverted;
	}

	public String getColumnDefinedScope() {
		return columnDefinedScope;
	}

	public void setColumnDefinedScope(String columnDefinedScope) {
		this.columnDefinedScope = columnDefinedScope;
	}

	public BigDecimal getPrimaryKeyPosition() {
		return primaryKeyPosition;
	}

	public void setPrimaryKeyPosition(BigDecimal primaryKeyPosition) {
		this.primaryKeyPosition = primaryKeyPosition;
	}

	public String getPrimaryKeyDefinedScope() {
		return primaryKeyDefinedScope;
	}

	public void setPrimaryKeyDefinedScope(String primaryKeyDefinedScope) {
		this.primaryKeyDefinedScope = primaryKeyDefinedScope;
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

	public boolean isSplited() {
		return splited;
	}

	public void setSplited(boolean splited) {
		this.splited = splited;
	}

}
