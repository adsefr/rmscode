package com.rms.base.generation.classfile;

import java.util.LinkedHashMap;
import java.util.Map;

import com.rms.base.generation.model.MethodModel;
import com.rms.base.validate.Assertion;

/**
 *
 * @author ri.meisei
 * @since 2015/09/04
 */
public class MethodInfo {

	private final Map<String, MethodModel> methodModelCollection = new LinkedHashMap<String, MethodModel>();

	public MethodInfo() {

	}

	public MethodModel add(MethodModel methodModel) {

		Assertion.assertNotNull("methodModel", methodModel);

		String methodName = methodModel.getMethodName();

		return methodModelCollection.put(methodName, methodModel);
	}

	/**
	 * @return methodModelCollection
	 */
	public Map<String, MethodModel> getMethodModelCollection() {

		return methodModelCollection;
	}

	public boolean isEmpty() {

		return methodModelCollection.isEmpty();
	}
}
