package com.rms.base.generation.classfile;

import java.util.LinkedHashMap;
import java.util.Map;

import com.rms.base.generation.model.ParameterModel;
import com.rms.base.validate.Assertion;

/**
 *
 * @author ri.meisei
 * @since 2015/09/04
 */
public class ParameterInfo {

	private final Map<String, ParameterModel> ParameterModelCollection = new LinkedHashMap<String, ParameterModel>();

	public ParameterInfo() {

	}

	public ParameterModel add(ParameterModel parameterModel) {

		Assertion.assertNotNull("parameterModel", parameterModel);

		String parameterName = parameterModel.getParameterName();

		return ParameterModelCollection.put(parameterName, parameterModel);
	}

	Map<String, ParameterModel> getParameterModelCollection() {

		return ParameterModelCollection;
	}

	public boolean isEmpty() {

		return ParameterModelCollection.isEmpty();
	}

	@Override
	public String toString() {

		StringBuilder sbBuilder = new StringBuilder();
		for (String parameterName : ParameterModelCollection.keySet()) {
			ParameterModel parameterModel = ParameterModelCollection.get(parameterName);

			sbBuilder.append(parameterModel.getDataType().getStringType());
			sbBuilder.append(" ");
			sbBuilder.append(parameterName);
			sbBuilder.append(", ");
		}

		if (!ParameterModelCollection.isEmpty()) {
			sbBuilder.delete(sbBuilder.length() - 2, sbBuilder.length());
		}

		return sbBuilder.toString();
	}
}
