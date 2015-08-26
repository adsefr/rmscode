package com.rms.common.generate.javaclass.info;

import java.util.LinkedHashMap;
import java.util.Map;

import com.rms.base.validate.Assertion;

public class ParameterInfo {

	private final Map<String, ParameterModel> ParameterModelCollection = new LinkedHashMap<String, ParameterModel>();

	ParameterInfo() {

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

			sbBuilder.append(parameterModel.getDataType().getTypeName());
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
