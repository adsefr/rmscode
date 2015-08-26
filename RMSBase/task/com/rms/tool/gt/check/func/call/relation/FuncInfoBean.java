package com.rms.tool.gt.check.func.call.relation;

public class FuncInfoBean {

	private String USER_ID;

	private String PROJECT_FILE_PATH;

	private String SOURCE_FILE_PATH;

	private String FUNC_NAME;

	private String CALL_SOURCE_FILE_PATH;

	private String CALL_FUNC_NAME;

	public String getUSER_ID() {

		return USER_ID;
	}

	public void setUSER_ID(String uSER_ID) {

		USER_ID = uSER_ID;
	}

	public String getPROJECT_FILE_PATH() {

		return PROJECT_FILE_PATH;
	}

	public void setPROJECT_FILE_PATH(String pROJECT_FILE_PATH) {

		PROJECT_FILE_PATH = pROJECT_FILE_PATH;
	}

	public String getSOURCE_FILE_PATH() {

		return SOURCE_FILE_PATH;
	}

	public void setSOURCE_FILE_PATH(String sOURCE_FILE_PATH) {

		SOURCE_FILE_PATH = sOURCE_FILE_PATH;
	}

	public String getFUNC_NAME() {

		return FUNC_NAME;
	}

	public void setFUNC_NAME(String fUNC_NAME) {

		FUNC_NAME = fUNC_NAME;
	}

	public String getCALL_SOURCE_FILE_PATH() {

		return CALL_SOURCE_FILE_PATH;
	}

	public void setCALL_SOURCE_FILE_PATH(String cALL_SOURCE_FILE_PATH) {

		CALL_SOURCE_FILE_PATH = cALL_SOURCE_FILE_PATH;
	}

	public String getCALL_FUNC_NAME() {

		return CALL_FUNC_NAME;
	}

	public void setCALL_FUNC_NAME(String cALL_FUNC_NAME) {

		CALL_FUNC_NAME = cALL_FUNC_NAME;
	}

}
