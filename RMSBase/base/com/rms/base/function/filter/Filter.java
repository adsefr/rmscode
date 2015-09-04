package com.rms.base.function.filter;

@FunctionalInterface
public interface Filter<T> {

	public boolean accept(T t);
}
