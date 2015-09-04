package com.rms.base.generation.classfile;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.rms.base.constant.Characters;
import com.rms.base.validate.Assertion;

/**
 *
 * @author ri.meisei
 * @since 2015/09/04
 */
public class ImportInfo {

	private final Map<String, Boolean> javaCollection = new TreeMap<String, Boolean>();

	private final Map<String, Boolean> javaxCollection = new TreeMap<String, Boolean>();

	private final Map<String, Boolean> orgCollection = new TreeMap<String, Boolean>();

	private final Map<String, Boolean> comCollection = new TreeMap<String, Boolean>();

	private final Map<String, Boolean> otherCollection = new TreeMap<String, Boolean>();

	ImportInfo() {

	}

	public void addImportClass(String... classNameArray) {

		Assertion.assertNotNull("classNameArray", classNameArray);
		for (String className : classNameArray) {
			addImportClass(className);
		}
	}

	public void addImportClass(List<String> classNameList) {

		Assertion.assertNotNull("classNameList", classNameList);
		for (String className : classNameList) {
			addImportClass(className);
		}
	}

	public void addImportClass(Class<?>... classArray) {

		Assertion.assertNotNull("classArray", classArray);
		for (Class<?> clazz : classArray) {
			if (clazz != null) {
				addImportClass(clazz.getName());
			}
		}
	}

	public void addImportClass(String className) {

		if (className == null || className.isEmpty()) {
			return;
		}

		int index = className.indexOf(".");
		String prefix = "";
		if (index > 0) {
			prefix = className.substring(0, index);
		}

		switch (prefix) {
		case "java":
			javaCollection.put(className, false);
			break;
		case "javax":
			javaxCollection.put(className, false);
			break;
		case "org":
			orgCollection.put(className, false);
			break;
		case "com":
			comCollection.put(className, false);
			break;
		default:
			otherCollection.put(className, false);
			break;
		}
	}

	boolean isEmpty() {

		return (javaCollection.isEmpty() && javaxCollection.isEmpty() && orgCollection.isEmpty() && comCollection.isEmpty() && otherCollection.isEmpty());
	}

	@Override
	public String toString() {

		StringBuilder sbBuilder = new StringBuilder();

		if (!javaCollection.isEmpty()) {
			sbBuilder.append(convertToString(javaCollection));
			sbBuilder.append(Characters.LINE_SEPARATOR_SYSTEM);
		}
		if (!javaxCollection.isEmpty()) {
			sbBuilder.append(convertToString(javaxCollection));
			sbBuilder.append(Characters.LINE_SEPARATOR_SYSTEM);
		}
		if (!orgCollection.isEmpty()) {
			sbBuilder.append(convertToString(orgCollection));
			sbBuilder.append(Characters.LINE_SEPARATOR_SYSTEM);
		}
		if (!comCollection.isEmpty()) {
			sbBuilder.append(convertToString(comCollection));
			sbBuilder.append(Characters.LINE_SEPARATOR_SYSTEM);
		}
		if (!otherCollection.isEmpty()) {
			sbBuilder.append(convertToString(otherCollection));
			sbBuilder.append(Characters.LINE_SEPARATOR_SYSTEM);
		}

		return sbBuilder.toString();
	}

	private String convertToString(Map<String, Boolean> collection) {

		StringBuilder sbBuilder = new StringBuilder();

		for (String className : collection.keySet()) {
			sbBuilder.append("import ");
			sbBuilder.append(className);
			sbBuilder.append(";");
			sbBuilder.append(Characters.LINE_SEPARATOR_SYSTEM);
		}

		return sbBuilder.toString();
	}
}
