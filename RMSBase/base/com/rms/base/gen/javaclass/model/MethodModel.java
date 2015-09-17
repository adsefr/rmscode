package com.rms.base.gen.javaclass.model;

import com.rms.base.datatype.enumeration.DataType;
import com.rms.base.gen.javaclass.enumeration.MethodModifier;

/**
 *
 * @author ri.meisei
 * @since 2015/09/04
 */
public class MethodModel {

	private boolean isConstructor = false;

	private final AnnotationInfo annotationInfo = new AnnotationInfo();

	private final ModifierInfo<MethodModifier> modifierInfo = new ModifierInfo<>();

	private DataType returnType = DataType.VOID;

	private String methodName = "";

	private String comment = "";

	private final ParameterInfo parameterInfo = new ParameterInfo();

	private String methodBody = "";

	public MethodModel() {

	}

	public boolean isConstructor() {

		return isConstructor;
	}

	public void setConstructor(boolean isConstructor) {

		this.isConstructor = isConstructor;
	}

	/**
	 * @return annotationInfo
	 */
	public AnnotationInfo getAnnotationInfo() {

		return annotationInfo;
	}

	public DataType getReturnType() {

		return returnType;
	}

	public void setReturnType(DataType returnType) {

		this.returnType = returnType;
	}

	public String getMethodName() {

		return methodName;
	}

	public void setMethodName(String methodName) {

		this.methodName = methodName;
	}

	public String getComment() {

		return comment;
	}

	public void setComment(String comment) {

		this.comment = comment;
	}

	public String getMethodBody() {

		return methodBody;
	}

	public void setMethodBody(String methodBody) {

		this.methodBody = methodBody;
	}

	public ModifierInfo<MethodModifier> getModifierInfo() {

		return modifierInfo;
	}

	public ParameterInfo getParameterInfo() {

		return parameterInfo;
	}

}
