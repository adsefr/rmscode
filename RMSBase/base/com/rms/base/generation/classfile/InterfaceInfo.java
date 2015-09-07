package com.rms.base.generation.classfile;

import java.util.ArrayList;
import java.util.List;

import com.rms.base.constant.Characters;
import com.rms.base.generation.model.TargetType;
import com.rms.base.validate.Assertion;

/**
 *
 * @author ri.meisei
 * @since 2015/09/04
 */
public class InterfaceInfo {

	private final String packageName;

	private final ImportInfo importInfo = new ImportInfo();

	private final AnnotationInfo annotationInfo = new AnnotationInfo();

	private final ModifierInfo modifierInfo = new ModifierInfo(TargetType.CLASS);

	private final String simpleClassName;

	private final String className;

	private final List<String> parentInterface = new ArrayList<>();

	private final MethodInfo methodInfo = new MethodInfo();

	/**
	 *
	 * @param className
	 */
	public InterfaceInfo(String className) {

		Assertion.assertNotBlank("className", className);

		this.packageName = getPackageName(className);
		this.simpleClassName = getSimpleClassName(className);
		this.className = className;
	}

	/**
	 *
	 * @param packageName
	 * @param simpleClassName
	 */
	public InterfaceInfo(String packageName, String simpleClassName) {

		Assertion.assertNotBlank("packageName", packageName);
		Assertion.assertNotBlank("simpleClassName", simpleClassName);

		this.packageName = packageName;
		this.simpleClassName = simpleClassName;
		this.className = packageName + "." + simpleClassName;
	}

	/**
	 *
	 * @return
	 */
	public String getPackageName() {

		return packageName;
	}

	/**
	 *
	 * @return
	 */
	public ImportInfo getImportInfo() {

		return importInfo;
	}

	/**
	 *
	 * @return
	 */
	public ModifierInfo getModifierInfo() {

		return modifierInfo;
	}

	/**
	 *
	 * @return
	 */
	public String getClassName() {

		return className;
	}

	/**
	 *
	 * @return
	 */
	public String getSimpleClassName() {

		return simpleClassName;
	}

	/**
	 *
	 * @return
	 */
	public MethodInfo getMethodInfo() {

		return methodInfo;
	}

	@Override
	public String toString() {

		StringBuilder sbBuilder = new StringBuilder();
		String packageName = getPackageName(className);
		if (packageName != null && !packageName.isEmpty()) {
			sbBuilder.append("package ").append(packageName).append(";");
			sbBuilder.append(Characters.LINE_SEPARATOR_SYSTEM);
			sbBuilder.append(Characters.LINE_SEPARATOR_SYSTEM);
		}

		sbBuilder.append(importInfo.toString());

		sbBuilder.append(modifierInfo.toString());
		sbBuilder.append(" class ");
		sbBuilder.append(simpleClassName);
		sbBuilder.append(" {");
		sbBuilder.append(Characters.LINE_SEPARATOR_SYSTEM);
		sbBuilder.append(Characters.LINE_SEPARATOR_SYSTEM);

		sbBuilder.append(methodInfo.toString());

		sbBuilder.append("}");
		sbBuilder.append(Characters.LINE_SEPARATOR_SYSTEM);

		return sbBuilder.toString();
	}

	private String getPackageName(String className) {

		String packageName = "";

		int index = className.lastIndexOf(".");
		if (index > 0) {
			packageName = className.substring(0, index);
		}

		return packageName;
	}

	private String getSimpleClassName(String className) {

		String simpleClassName = "";

		int index = className.lastIndexOf(".");
		if (index > 0) {
			simpleClassName = className.substring(index + 1);
		}

		return simpleClassName;
	}
}
