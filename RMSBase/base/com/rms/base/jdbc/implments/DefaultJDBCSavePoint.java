package com.rms.base.jdbc.implments;

import com.rms.common.jdbc.JDBCSavePoint;

class DefaultJDBCSavePoint implements JDBCSavePoint {

	private int savepointId;

	private String savepointName;

	public DefaultJDBCSavePoint() {

	}

	/**
	 * @return savepointId
	 */
	@Override
	public int getSavepointId() {

		return savepointId;
	}

	/**
	 * @param savepointId
	 *            セットする savepointId
	 */
	@Override
	public void setSavepointId(int savepointId) {

		this.savepointId = savepointId;
	}

	/**
	 * @return savepointName
	 */
	@Override
	public String getSavepointName() {

		return savepointName;
	}

	/**
	 * @param savepointName
	 *            セットする savepointName
	 */
	@Override
	public void setSavepointName(String savepointName) {

		this.savepointName = savepointName;
	}

}
