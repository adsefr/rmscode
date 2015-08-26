package com.rms.common.reflect;

import java.lang.reflect.Constructor;

/**
 *
 * @author ri.meisei
 * @since 2015/08/18
 */
public class ReflectUtil {

	public static Class<?> forName(String className) throws ReflectiveOperationException {

		return Class.forName(className);
	}

	@SuppressWarnings("unchecked")
	public static <T> T newInstance(String className, Class<?>[] parameterTypes, Object[] params) throws ReflectiveOperationException {

		Constructor<?> constructor = forName(className).getDeclaredConstructor(parameterTypes);
		constructor.setAccessible(true);
		return (T) constructor.newInstance(params);
	}
}
