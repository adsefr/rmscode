package com.rms.base.generation.classfile;

import java.util.Map.Entry;

import com.rms.base.enumeration.DataType;
import com.rms.base.generation.model.MethodModel;
import com.rms.base.generation.model.ParameterModel;
import com.rms.base.logging.Logger;
import com.rms.base.util.TextUtil;

/**
 *
 * @author ri.meisei
 * @since 2015/09/04
 */
public class MethodGenerator extends BaseGenerator {

	private final Logger logger = Logger.getLogger(this.getClass());

	private MethodInfo methodInfo;

	public MethodGenerator() {

	}

	/**
	 * @param methodInfo
	 */
	public MethodGenerator(MethodInfo methodInfo) {

		super();
		this.methodInfo = methodInfo;
	}

	/**
	 * @return methodInfo
	 */
	public MethodInfo getMethodInfo() {

		return methodInfo;
	}

	/**
	 * @param methodInfo
	 *            セットする methodInfo
	 */
	public void setMethodInfo(MethodInfo methodInfo) {

		this.methodInfo = methodInfo;
	}

	@Override
	protected void generate() {

		clearBuffered();

		for (Entry<String, MethodModel> entry : methodInfo.getMethodModelCollection().entrySet()) {
			String methodName = entry.getKey();
			MethodModel methodModel = entry.getValue();

			boolean hasComment = TextUtil.isNotBlank(methodModel.getComment());
			boolean hasParameter = !methodModel.getParameterInfo().isEmpty();
			boolean hasReturn = (methodModel.getReturnType() != null && methodModel.getReturnType() != DataType.VOID);

			if (hasComment || hasParameter || hasReturn) {
				buffered.append("\t");
				buffered.append("/**");
				buffered.append(lineSeparator);

				if (hasComment) {
					buffered.append("\t");
					buffered.append(" * ");
					buffered.append(methodModel.getComment());
					buffered.append(lineSeparator);
				}

				if (hasParameter) {
					buffered.append("\t");
					buffered.append(" * ");
					buffered.append(lineSeparator);
					for (ParameterModel parameterModel : methodModel.getParameterInfo().getParameterModelCollection().values()) {
						buffered.append("\t");
						buffered.append(" * @param ");
						buffered.append(parameterModel.getParameterName());
						buffered.append(lineSeparator);
					}
				}
				buffered.append("\t");
				buffered.append(" * ");
				buffered.append(lineSeparator);
				buffered.append("\t");
				buffered.append(" * @return ");
				buffered.append(lineSeparator);
				buffered.append("\t");
				buffered.append(" */");
				buffered.append(lineSeparator);
			}

			buffered.append("\t");
			buffered.append(methodModel.getModifierInfo().toString());
			buffered.append(" ");
			buffered.append(methodModel.getReturnType().getStringType());
			buffered.append(" ");

			buffered.append(methodName);
			buffered.append("(");
			buffered.append(methodModel.getParameterInfo().toString());
			buffered.append(")");
			buffered.append(" {");
			buffered.append(lineSeparator);
			buffered.append(lineSeparator);

			if (methodModel.getMethodBody() == null || methodModel.getMethodBody().isEmpty()) {
				buffered.append("\t");
				buffered.append("\t");
				buffered.append(methodModel.getReturnType().getStringType());
				buffered.append(";");
				buffered.append(lineSeparator);
			} else {
				buffered.append("\t\t" + methodModel.getMethodBody().replaceAll(lineSeparator, lineSeparator + "\t\t"));
			}

			buffered.append(lineSeparator);
			buffered.append("\t");
			buffered.append("}");
			buffered.append(lineSeparator);
			buffered.append(lineSeparator);
		}

		logger.trace(buffered.toString());
	}

}
