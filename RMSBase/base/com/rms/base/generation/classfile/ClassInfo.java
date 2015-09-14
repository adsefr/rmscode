package com.rms.base.generation.classfile;

import java.util.ArrayList;
import java.util.List;

import com.rms.base.constant.Characters;
import com.rms.base.generation.model.TargetType;
import com.rms.base.util.ArrayUtil;
import com.rms.base.util.TextUtil;
import com.rms.base.validate.Assertion;

/**
 *
 * @author ri.meisei
 * @since 2015/09/04
 */
public class ClassInfo {

	private final AnnotationInfo annotationInfo = new AnnotationInfo();

	private final ModifierInfo modifierInfo = new ModifierInfo(TargetType.CLASS);

	private final List<String> parentClasses = new ArrayList<>();

	private final List<String> parentInterfaces = new ArrayList<>();

	private final FieldInfo fieldInfo = new FieldInfo();

	private final MethodInfo methodInfo = new MethodInfo();

	private String packageName = "";

	private String className = "";

	private String simpleClassName = "";

	/**
	 *
	 * @param className
	 */
	public ClassInfo(String className) {

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
	public ClassInfo(String packageName, String simpleClassName) {

		Assertion.assertNotBlank("packageName", packageName);
		Assertion.assertNotBlank("simpleClassName", simpleClassName);

		this.packageName = getPackageName(packageName);
		this.simpleClassName = simpleClassName;
		this.className = this.packageName + "." + simpleClassName;
	}

	/**
	 * @return packageName
	 */
	public String getPackageName() {

		return packageName;
	}

	/**
	 * @return annotationInfo
	 */
	public AnnotationInfo getAnnotationInfo() {

		return annotationInfo;
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
	 * @return parentClasses
	 */
	public List<String> getParentClasses() {

		return parentClasses;
	}

	/**
	 * @return parentInterfaces
	 */
	public List<String> getParentInterfaces() {

		return parentInterfaces;
	}

	/**
	 *
	 * @return
	 */
	public FieldInfo getFieldInfo() {

		return fieldInfo;
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

		StringBuilder sBuilder = new StringBuilder();
		String packageName = getPackageName(className);
		if (packageName != null && !packageName.isEmpty()) {
			sBuilder.append("package ").append(packageName).append(";");
			sBuilder.append(Characters.LINE_SEPARATOR_SYSTEM);
			sBuilder.append(Characters.LINE_SEPARATOR_SYSTEM);
		}

		sBuilder.append(modifierInfo.toString());
		sBuilder.append(" class ");
		sBuilder.append(simpleClassName);

		if (!parentClasses.isEmpty()) {
			int length1 = sBuilder.lastIndexOf(Characters.LINE_SEPARATOR_SYSTEM);
			int length2 = sBuilder.length();
			sBuilder.append(Characters.LINE_SEPARATOR_SYSTEM);
			TextUtil.repeat(" ", Math.max(length2 - length1, 16) - 4);
			sBuilder.append(" extends ");
			sBuilder.append(ArrayUtil.join(parentClasses, ", "));
		}

		if (!parentInterfaces.isEmpty()) {
			int length1 = sBuilder.lastIndexOf(Characters.LINE_SEPARATOR_SYSTEM);
			int length2 = sBuilder.length();
			sBuilder.append(Characters.LINE_SEPARATOR_SYSTEM);
			TextUtil.repeat(" ", Math.max(length2 - length1, 16) - 4);
			sBuilder.append(" implements ");
			sBuilder.append(ArrayUtil.join(parentInterfaces, ", "));
		}

		sBuilder.append(" {");
		sBuilder.append(Characters.LINE_SEPARATOR_SYSTEM);
		sBuilder.append(Characters.LINE_SEPARATOR_SYSTEM);

		sBuilder.append(fieldInfo.toString());

		sBuilder.append(methodInfo.toString());

		sBuilder.append("}");
		sBuilder.append(Characters.LINE_SEPARATOR_SYSTEM);

		return sBuilder.toString();
	}

	private static String getPackageName(String className) {

		String packageName = "";

		int index = className.lastIndexOf(".");
		if (index > 0) {
			packageName = className.substring(0, index);
		}

		return packageName;
	}

	private static String getSimpleClassName(String className) {

		String simpleClassName = "";

		int index = className.lastIndexOf(".");
		if (index > 0) {
			simpleClassName = className.substring(index + 1);
		}

		return simpleClassName;
	}
}
