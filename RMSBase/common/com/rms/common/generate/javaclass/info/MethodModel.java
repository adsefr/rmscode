package com.rms.common.generate.javaclass.info;

public class MethodModel {

	private boolean isConstructor = false;

	private final ModifierInfo modifierInfo = new ModifierInfo(TargetType.METHOD);

	private DataType returnType;

	private String methodName;

	private String comment;

	private final ParameterInfo parameterInfo = new ParameterInfo();

	private final ExceptionInfo exceptionInfo = new ExceptionInfo();

	private String methodBody;

	public MethodModel() {

	}

	public boolean isConstructor() {

		return isConstructor;
	}

	public void setConstructor(boolean isConstructor) {

		this.isConstructor = isConstructor;
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

	public ModifierInfo getModifierInfo() {

		return modifierInfo;
	}

	public ParameterInfo getParameterInfo() {

		return parameterInfo;
	}

	public ExceptionInfo getExceptionInfo() {

		return exceptionInfo;
	}

}
