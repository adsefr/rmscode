package com.rms.base.generation.classfile;

import com.rms.base.constant.Characters;

/**
 *
 * @author ri.meisei
 * @since 2015/09/14
 */
public abstract class BaseInfo {

	protected String lineSeparator = Characters.LINE_SEPARATOR_SYSTEM;

	public BaseInfo() {

	}

	/**
	 * @return lineSeparator
	 */
	public String getLineSeparator() {

		return lineSeparator;
	}

	/**
	 * @param lineSeparator
	 *            セットする lineSeparator
	 */
	public void setLineSeparator(String lineSeparator) {

		this.lineSeparator = lineSeparator;
	}

}
