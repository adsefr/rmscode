package com.rms.base.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rms.base.validate.Assertion;

/**
 *
 * @author ri.meisei
 * @since 2015/08/18
 */
public class Reflection {

	/**
	 *
	 * @param className
	 * @return
	 * @throws ReflectiveOperationException
	 */
	public static Class<?> forName(String className) throws ReflectiveOperationException {

		Assertion.assertNotNull("className", className);

		return Class.forName(className);
	}

	/**
	 *
	 * @param className
	 * @return
	 * @throws ReflectiveOperationException
	 */
	@SuppressWarnings("unchecked")
	public static <T> T newInstance(String className) throws ReflectiveOperationException {

		Assertion.assertNotNull("className", className);

		Constructor<?> constructor = forName(className).getDeclaredConstructor();

		constructor.setAccessible(true);

		return (T) constructor.newInstance();
	}

	/**
	 *
	 * @param clazz
	 * @return
	 * @throws ReflectiveOperationException
	 */
	@SuppressWarnings("unchecked")
	public static <T> T newInstance(Class<?> clazz) throws ReflectiveOperationException {

		Assertion.assertNotNull("clazz", clazz);

		Constructor<?> constructor = clazz.getDeclaredConstructor();

		constructor.setAccessible(true);

		return (T) constructor.newInstance();
	}

	/**
	 *
	 * @param className
	 * @param parameterTypes
	 * @param parameters
	 * @return
	 * @throws ReflectiveOperationException
	 */
	@SuppressWarnings("unchecked")
	public static <T> T newInstance(Class<?> clazz, Class<?>[] parameterTypes, Object[] parameters) throws ReflectiveOperationException {

		Assertion.assertNotNull("clazz", clazz);

		Constructor<?> constructor = clazz.getDeclaredConstructor(parameterTypes);
		constructor.setAccessible(true);

		return (T) constructor.newInstance(parameters);
	}

	/**
	 *
	 * @param className
	 * @param parameterTypes
	 * @param parameters
	 * @return
	 * @throws ReflectiveOperationException
	 */
	@SuppressWarnings("unchecked")
	public static <T> T newInstance(String className, Class<?>[] parameterTypes, Object[] parameters) throws ReflectiveOperationException {

		Assertion.assertNotNull("className", className);

		Constructor<?> constructor = forName(className).getDeclaredConstructor(parameterTypes);
		constructor.setAccessible(true);

		return (T) constructor.newInstance(parameters);
	}

	/**
	 *
	 * @param clazz
	 * @param methodName
	 * @param parameterTypes
	 * @return
	 * @throws ReflectiveOperationException
	 */
	public static Method getMethod(Class<?> clazz, String methodName, Class<?>... parameterTypes) throws ReflectiveOperationException {

		Assertion.assertNotNull("clazz", clazz);
		Assertion.assertNotNull("methodName", methodName);

		Method method = clazz.getDeclaredMethod(methodName, parameterTypes);

		return method;
	}

	/**
	 *
	 * @param className
	 * @param parameterTypes
	 * @param params
	 * @return
	 * @throws ReflectiveOperationException
	 */
	public static Method getMethod(String className, String methodName, Class<?>... parameterTypes) throws ReflectiveOperationException {

		Assertion.assertNotNull("className", className);
		Assertion.assertNotNull("methodName", methodName);

		Class<?> clazz = forName(className);

		Method method = clazz.getDeclaredMethod(methodName, parameterTypes);

		return method;
	}

	/**
	 *
	 * @param clazz
	 * @return
	 */
	public static List<Method> getAllMethods(Class<?> clazz) {

		Assertion.assertNotNull("clazz", clazz);

		Method[] methods = clazz.getDeclaredMethods();

		return Arrays.asList(methods);
	}

	/**
	 *
	 * @param className
	 * @return
	 * @throws ReflectiveOperationException
	 */
	public static List<Method> getAllMethods(String className) throws ReflectiveOperationException {

		Assertion.assertNotNull("className", className);

		Class<?> clazz = forName(className);

		Method[] methods = clazz.getDeclaredMethods();

		return Arrays.asList(methods);
	}

	/**
	 *
	 * @param clazz
	 * @param fieldName
	 * @return
	 * @throws ReflectiveOperationException
	 */
	public static Field getField(Class<?> clazz, String fieldName) throws ReflectiveOperationException {

		Assertion.assertNotNull("clazz", clazz);
		Assertion.assertNotNull("fieldName", fieldName);

		Field field = clazz.getDeclaredField(fieldName);

		return field;
	}

	/**
	 *
	 * @param className
	 * @param fieldName
	 * @return
	 * @throws ReflectiveOperationException
	 */
	public static Field getField(String className, String fieldName) throws ReflectiveOperationException {

		Assertion.assertNotNull("className", className);
		Assertion.assertNotNull("fieldName", fieldName);

		Class<?> clazz = forName(className);

		Field field = clazz.getDeclaredField(fieldName);

		return field;
	}

	/**
	 *
	 * @param clazz
	 * @return
	 */
	public static List<Field> getFields(Class<?> clazz) {

		Assertion.assertNotNull("clazz", clazz);

		Field[] fields = clazz.getDeclaredFields();

		return Arrays.asList(fields);
	}

	/**
	 *
	 * @param className
	 * @return
	 * @throws ReflectiveOperationException
	 */
	public static List<Field> getFields(String className) throws ReflectiveOperationException {

		Assertion.assertNotNull("className", className);

		Class<?> clazz = forName(className);

		Field[] fields = clazz.getDeclaredFields();

		return Arrays.asList(fields);
	}

	/**
	 *
	 * @param object
	 * @param fieldName
	 * @return
	 * @throws ReflectiveOperationException
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getValue(Object object, String fieldName) throws ReflectiveOperationException {

		Assertion.assertNotNull("object", object);
		Assertion.assertNotNull("fieldName", fieldName);

		Field field = getField(object.getClass(), fieldName);

		field.setAccessible(true);

		return (T) field.get(object);
	}

	/**
	 *
	 * @param object
	 * @return
	 * @throws ReflectiveOperationException
	 */
	public static Map<String, Object> getValues(Object object) throws ReflectiveOperationException {

		Assertion.assertNotNull("object", object);

		Field[] fields = object.getClass().getDeclaredFields();

		Map<String, Object> objects = new HashMap<>();

		for (Field field : fields) {
			objects.put(field.getName(), getValue(object, field.getName()));
		}

		return objects;
	}

	/**
	 *
	 * @param object
	 * @param methodName
	 * @param parameters
	 * @return
	 * @throws ReflectiveOperationException
	 */
	@SuppressWarnings("unchecked")
	public static <T> T invoke(Object object, String methodName, Class<?>[] parameterTypes, Object... parameters) throws ReflectiveOperationException {

		Assertion.assertNotNull("object", object);
		Assertion.assertNotNull("methodName", methodName);

		Method method = getMethod(object.getClass(), methodName, parameterTypes);

		method.setAccessible(true);

		Object result = method.invoke(object, parameters);

		return (T) result;
	}
}
