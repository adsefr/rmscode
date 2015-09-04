package com.rms.base.function;

@FunctionalInterface
public interface Matcher<T> {

	public boolean matches(T t);
}
