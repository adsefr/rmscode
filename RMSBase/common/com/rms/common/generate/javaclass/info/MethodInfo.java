package com.rms.common.generate.javaclass.info;

import java.util.LinkedHashMap;
import java.util.Map;

import com.rms.base.constant.Characters;
import com.rms.base.validate.Assertion;

/**
 *
 * @author ri.meisei
 * @since 2015/03/24
 */
public class MethodInfo {

	private final Map<String, MethodModel> methodModelCollection = new LinkedHashMap<String, MethodModel>();

	MethodInfo() {

	}

	public MethodModel add(MethodModel methodModel) {

		Assertion.assertNotNull("methodModel", methodModel);

		String methodName = methodModel.getMethodName();

		return methodModelCollection.put(methodName, methodModel);
	}

	@Override
	public String toString() {

		StringBuilder sBuilder = new StringBuilder();

		for (String methodName : methodModelCollection.keySet()) {
			MethodModel methodModel = methodModelCollection.get(methodName);

			boolean hasComment = methodModel.getComment() != null && !methodModel.getComment().isEmpty();
			boolean hasParameter = !methodModel.getParameterInfo().isEmpty();
			boolean hasReturn = (methodModel.getReturnType() != null);

			if (hasComment || hasParameter || hasReturn) {
				sBuilder.append("\t");
				sBuilder.append("/**");
				sBuilder.append(Characters.LINE_SEPARATOR_SYSTEM);

				if (hasComment) {
					sBuilder.append("\t");
					sBuilder.append(" * ");
					sBuilder.append(methodModel.getComment());
					sBuilder.append(Characters.LINE_SEPARATOR_SYSTEM);
				}

				if (hasParameter) {
					sBuilder.append("\t");
					sBuilder.append(" * ");
					sBuilder.append(Characters.LINE_SEPARATOR_SYSTEM);
					for (ParameterModel parameterModel : methodModel.getParameterInfo().getParameterModelCollection().values()) {
						sBuilder.append("\t");
						sBuilder.append(" * @param ");
						sBuilder.append(parameterModel.getParameterName());
						sBuilder.append(Characters.LINE_SEPARATOR_SYSTEM);
					}
				}
				sBuilder.append("\t");
				sBuilder.append(" * ");
				sBuilder.append(Characters.LINE_SEPARATOR_SYSTEM);
				sBuilder.append("\t");
				sBuilder.append(" * @return ");
				sBuilder.append(Characters.LINE_SEPARATOR_SYSTEM);
				sBuilder.append("\t");
				sBuilder.append(" */");
				sBuilder.append(Characters.LINE_SEPARATOR_SYSTEM);
			}

			sBuilder.append("\t");
			sBuilder.append(methodModel.getModifierInfo().toString());
			sBuilder.append(" ");
			sBuilder.append(methodModel.getReturnType().getTypeName());
			sBuilder.append(" ");

			sBuilder.append(methodName);
			sBuilder.append("(");
			sBuilder.append(methodModel.getParameterInfo().toString());
			sBuilder.append(")");
			sBuilder.append(" {");
			sBuilder.append(Characters.LINE_SEPARATOR_SYSTEM);
			sBuilder.append(Characters.LINE_SEPARATOR_SYSTEM);

			if (methodModel.getMethodBody() == null || methodModel.getMethodBody().isEmpty()) {
				sBuilder.append("\t");
				sBuilder.append("\t");
				sBuilder.append(methodModel.getReturnType().getReturnText());
				sBuilder.append(";");
				sBuilder.append(Characters.LINE_SEPARATOR_SYSTEM);
			} else {
				sBuilder.append(methodModel.getMethodBody());
			}

			sBuilder.append("\t");
			sBuilder.append("}");
			sBuilder.append(Characters.LINE_SEPARATOR_SYSTEM);
			sBuilder.append(Characters.LINE_SEPARATOR_SYSTEM);
		}

		return sBuilder.toString();

	}
}
