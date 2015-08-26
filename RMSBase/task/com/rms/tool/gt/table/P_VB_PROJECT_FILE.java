package com.rms.tool.gt.table;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class P_VB_PROJECT_FILE {

	private String USER_ID;

	private String PROJECT_FILE_PATH;

	private String PROJECT_FILE_NAME;

	private String SOURCE_FILE_PATH;

	private String SOURCE_FILE_NAME;

	private String MODULE_NAME;

	private BigDecimal PKG_BASE_FLG;

	private BigDecimal COMMON_FLG;

	private BigDecimal LINES;

	private BigDecimal ACTUAL_LINES;

	private BigDecimal USE_CLS;

	private String DESCRIPTION;

	private String CREATE_USER;

	private String UPDATE_USER;

	private Timestamp CREATE_DT;

	private Timestamp UPDATE_DT;

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

	public String getPROJECT_FILE_NAME() {

		return PROJECT_FILE_NAME;
	}

	public void setPROJECT_FILE_NAME(String pROJECT_FILE_NAME) {

		PROJECT_FILE_NAME = pROJECT_FILE_NAME;
	}

	public String getSOURCE_FILE_PATH() {

		return SOURCE_FILE_PATH;
	}

	public void setSOURCE_FILE_PATH(String sOURCE_FILE_PATH) {

		SOURCE_FILE_PATH = sOURCE_FILE_PATH;
	}

	public String getSOURCE_FILE_NAME() {

		return SOURCE_FILE_NAME;
	}

	public void setSOURCE_FILE_NAME(String sOURCE_FILE_NAME) {

		SOURCE_FILE_NAME = sOURCE_FILE_NAME;
	}

	public String getMODULE_NAME() {

		return MODULE_NAME;
	}

	public void setMODULE_NAME(String mODULE_NAME) {

		MODULE_NAME = mODULE_NAME;
	}

	public BigDecimal getPKG_BASE_FLG() {

		return PKG_BASE_FLG;
	}

	public void setPKG_BASE_FLG(BigDecimal pKG_BASE_FLG) {

		PKG_BASE_FLG = pKG_BASE_FLG;
	}

	public BigDecimal getCOMMON_FLG() {

		return COMMON_FLG;
	}

	public void setCOMMON_FLG(BigDecimal cOMMON_FLG) {

		COMMON_FLG = cOMMON_FLG;
	}

	public BigDecimal getLINES() {

		return LINES;
	}

	public void setLINES(BigDecimal lINES) {

		LINES = lINES;
	}

	public BigDecimal getACTUAL_LINES() {

		return ACTUAL_LINES;
	}

	public void setACTUAL_LINES(BigDecimal aCTUAL_LINES) {

		ACTUAL_LINES = aCTUAL_LINES;
	}

	public BigDecimal getUSE_CLS() {

		return USE_CLS;
	}

	public void setUSE_CLS(BigDecimal uSE_CLS) {

		USE_CLS = uSE_CLS;
	}

	public String getDESCRIPTION() {

		return DESCRIPTION;
	}

	public void setDESCRIPTION(String dESCRIPTION) {

		DESCRIPTION = dESCRIPTION;
	}

	public String getCREATE_USER() {

		return CREATE_USER;
	}

	public void setCREATE_USER(String cREATE_USER) {

		CREATE_USER = cREATE_USER;
	}

	public String getUPDATE_USER() {

		return UPDATE_USER;
	}

	public void setUPDATE_USER(String uPDATE_USER) {

		UPDATE_USER = uPDATE_USER;
	}

	public Timestamp getCREATE_DT() {

		return CREATE_DT;
	}

	public void setCREATE_DT(Timestamp cREATE_DT) {

		CREATE_DT = cREATE_DT;
	}

	public Timestamp getUPDATE_DT() {

		return UPDATE_DT;
	}

	public void setUPDATE_DT(Timestamp uPDATE_DT) {

		UPDATE_DT = uPDATE_DT;
	}
}
