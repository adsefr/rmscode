package com.rms.base.gen.javaclass.generator;

import java.util.Map.Entry;

import com.rms.base.gen.javaclass.model.ParameterInfo;
import com.rms.base.gen.javaclass.model.ParameterModel;

/**
 *
 * @author ri.meisei
 * @since 2015/09/17
 */
public class ParameterGenerator extends BaseGenerator {

	private ParameterInfo parameterInfo;

	public ParameterGenerator() {

		super();
	}

	/**
	 * @param parameterInfo
	 */
	public ParameterGenerator(ParameterInfo parameterInfo) {

		super();

		this.parameterInfo = parameterInfo;
	}

	/**
	 * @param parameterInfo
	 *            セットする parameterInfo
	 */
	public void setParameterInfo(ParameterInfo parameterInfo) {

		this.parameterInfo = parameterInfo;
	}

	@Override
	public void generate() {

		clearBuffer();

		int count = parameterInfo.count();

		for (Entry<String, ParameterModel> entry : parameterInfo.getParameterModelCollection().entrySet()) {
			ParameterModel parameterModel = entry.getValue();
			append(parameterModel.getDataType().getName());
			append(" ");
			append(parameterModel.getParameterName());

			if (count > 1) {
				append(", ");
				count--;
			}
		}
	}

}
