package com.rms.base.generation.model;

import com.rms.base.enumeration.DataType;

/**
 *
 * @author ri.meisei
 * @since 2015/09/04
 */
public class ParameterModel {

	private String parameterName = "";

	private DataType dataType;

	public ParameterModel() {

	}

	public String getParameterName() {

		return parameterName;
	}

	public void setParameterName(String parameterName) {

		this.parameterName = parameterName;
	}

	public DataType getDataType() {

		return dataType;
	}

	public void setDataType(DataType dataType) {

		this.dataType = dataType;
	}

}
