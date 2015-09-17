package com.rms.base.datatype;

public abstract class JavaType {

	public abstract String name();

	public abstract Class<?> classType();

	public abstract Object defaultValue();

	public abstract String stringClassType();

	public abstract String stringDefaultValue();
}
