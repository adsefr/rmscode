package com.rms.base.gen.javaclass.model;

import java.util.LinkedHashMap;
import java.util.Map;

import com.rms.base.validate.Assertion;

/**
 *
 * @author ri.meisei
 * @since 2015/09/04
 */
public class ParameterInfo {

	private final Map<String, ParameterModel> parameterModelCollection = new LinkedHashMap<String, ParameterModel>();

	public ParameterInfo() {

	}

	public ParameterModel add(ParameterModel parameterModel) {

		Assertion.assertNotNull("parameterModel", parameterModel);

		String parameterName = parameterModel.getParameterName();

		return parameterModelCollection.put(parameterName, parameterModel);
	}

	public Map<String, ParameterModel> getParameterModelCollection() {

		return parameterModelCollection;
	}

	public int count() {

		return parameterModelCollection.size();
	}

	public boolean isEmpty() {

		return parameterModelCollection.isEmpty();
	}
}
