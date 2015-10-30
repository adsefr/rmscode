package com.rms.tool.future.erd.bean;

public class FileERDInfo {

	private String fileName;

	private String pageId;

	private String pageName;

	private String width;

	private String height;

	private String positionX;

	private String positionY;

	private String tableName;

	private String primaryKey;

	private String items;

	private boolean processed;

	public FileERDInfo() {
	}

	public String getFileName() {

		return fileName;
	}

	public void setFileName(String fileName) {

		this.fileName = fileName;
	}

	public String getPageId() {

		return pageId;
	}

	public void setPageId(String pageId) {

		this.pageId = pageId;
	}

	public String getPageName() {

		return pageName;
	}

	public void setPageName(String pageName) {

		this.pageName = pageName;
	}

	public String getWidth() {

		return width;
	}

	public void setWidth(String width) {

		this.width = width;
	}

	public String getHeight() {

		return height;
	}

	public void setHeight(String height) {

		this.height = height;
	}

	public String getPositionX() {

		return positionX;
	}

	public void setPositionX(String positionX) {

		this.positionX = positionX;
	}

	public String getPositionY() {

		return positionY;
	}

	public void setPositionY(String positionY) {

		this.positionY = positionY;
	}

	public String getTableName() {

		return tableName;
	}

	public void setTableName(String tableName) {

		this.tableName = tableName;
	}

	public String getPrimaryKey() {

		return primaryKey;
	}

	public void setPrimaryKey(String primaryKey) {

		this.primaryKey = primaryKey;
	}

	public String getItems() {

		return items;
	}

	public void setItems(String items) {

		this.items = items;
	}

	public boolean isProcessed() {

		return processed;
	}

	public void setProcessed(boolean processed) {

		this.processed = processed;
	}

}
