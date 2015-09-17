package com.rms.base.gen.javaclass.generator;

import java.util.Map.Entry;

import com.rms.base.datatype.enumeration.DataType;
import com.rms.base.gen.javaclass.model.MethodInfo;
import com.rms.base.gen.javaclass.model.MethodModel;
import com.rms.base.gen.javaclass.model.ParameterModel;
import com.rms.base.logging.Logger;
import com.rms.base.util.TextUtil;

/**
 *
 * @author ri.meisei
 * @since 2015/09/04
 */
public class MethodGenerator extends BaseGenerator {

	private final Logger logger = Logger.getLogger(this.getClass());

	private AnnotationGnerator annotationGnerator = new AnnotationGnerator();

	private ModifierGenerator modifierGenerator = new ModifierGenerator();

	private ParameterGenerator parameterGenerator = new ParameterGenerator();

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
	public void generate() {

		clearBuffer();

		int count = methodInfo.count();

		for (Entry<String, MethodModel> entry : methodInfo.getMethodModelCollection().entrySet()) {
			String methodName = entry.getKey();
			MethodModel methodModel = entry.getValue();

			boolean hasComment = TextUtil.isNotBlank(methodModel.getComment());
			boolean hasParameter = !methodModel.getParameterInfo().isEmpty();
			boolean hasReturn = (methodModel.getReturnType() != null && methodModel.getReturnType() != DataType.VOID);

			if (hasComment || hasParameter || hasReturn) {
				append("\t");
				append("/**");
				append(lineSeparator);

				if (hasComment) {
					append("\t");
					append(" * ");
					append(methodModel.getComment());
					append(lineSeparator);
				}

				if (hasParameter) {
					append("\t");
					append(" * ");
					append(lineSeparator);
					for (ParameterModel parameterModel : methodModel.getParameterInfo().getParameterModelCollection().values()) {
						append("\t");
						append(" * @param ");
						append(parameterModel.getParameterName());
						append(lineSeparator);
					}
				}
				append("\t");
				append(" * ");
				append(lineSeparator);
				append("\t");
				append(" * @return ");
				append(lineSeparator);
				append("\t");
				append(" */");
				append(lineSeparator);
			}

			annotationGnerator.setAnnotationInfo(methodModel.getAnnotationInfo());
			annotationGnerator.generate();
			if (!annotationGnerator.isEmpty()) {
				append("\t");
				annotationGnerator.appendTo(this);
			}

			append("\t");
			modifierGenerator.setModifierInfo(methodModel.getModifierInfo());
			modifierGenerator.generate();
			if (!modifierGenerator.isEmpty()) {
				modifierGenerator.appendTo(this);
				append(" ");
			}
			append(methodModel.getReturnType().getName());
			append(" ");

			append(methodName);
			append("(");

			parameterGenerator.setParameterInfo(methodModel.getParameterInfo());
			parameterGenerator.generate();
			if (!parameterGenerator.isEmpty()) {
				parameterGenerator.appendTo(this);
			}
			append(")");
			append(" {");
			append(lineSeparator);
			append(lineSeparator);

			if (methodModel.getMethodBody() == null || methodModel.getMethodBody().isEmpty()) {
				append("\t");
				append("\t");
				append(methodModel.getReturnType().getName());
				append(";");
				append(lineSeparator);
			} else {
				append("\t\t" + methodModel.getMethodBody().replaceAll(lineSeparator, lineSeparator + "\t\t"));
			}

			append(lineSeparator);
			append("\t");
			append("}");

			if (count-- > 1) {
				append(lineSeparator);
				append(lineSeparator);
			}
		}

		logger.trace(toString());
	}

}
