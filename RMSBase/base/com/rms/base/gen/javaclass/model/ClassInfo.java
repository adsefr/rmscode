package com.rms.base.gen.javaclass.model;

import java.util.ArrayList;
import java.util.List;

import com.rms.base.gen.javaclass.enumeration.ClassModifier;
import com.rms.base.validate.Assertion;

/**
 *
 * @author ri.meisei
 * @since 2015/09/04
 */
public class ClassInfo {

	private final String packageName;

	private final ImportInfo importInfo = new ImportInfo();

	private final AnnotationInfo annotationInfo = new AnnotationInfo();

	private final ModifierInfo<ClassModifier> modifierInfo = new ModifierInfo<>();

	private final List<String> parentClasses = new ArrayList<>();

	private final List<String> parentInterfaces = new ArrayList<>();

	private final FieldInfo fieldInfo = new FieldInfo();

	private final MethodInfo methodInfo = new MethodInfo();

	private final String className;

	private final String simpleClassName;

	private String comment = "";

	/**
	 *
	 * @param className
	 */
	public ClassInfo(String className) {

		Assertion.assertNotBlank("className", className);

		String packageName = "";
		String simpleClassName = "";

		int index = className.lastIndexOf(".");
		if (index > 0) {
			packageName = className.substring(0, index);
		}

		simpleClassName = className.substring(index + 1);

		this.className = className;
		this.packageName = packageName;
		this.simpleClassName = simpleClassName;
	}

	/**
	 *
	 * @param packageName
	 * @param simpleClassName
	 */
	public ClassInfo(String packageName, String simpleClassName) {

		Assertion.assertNotBlank("packageName", packageName);
		Assertion.assertNotBlank("simpleClassName", simpleClassName);

		this.packageName = packageName;
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
	 * @return importInfo
	 */
	public ImportInfo getImportInfo() {

		return importInfo;
	}

	/**
	 * @return comment
	 */
	public String getComment() {

		return comment;
	}

	/**
	 * @param comment
	 *            セットする comment
	 */
	public void setComment(String comment) {

		this.comment = comment;
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
	public ModifierInfo<ClassModifier> getModifierInfo() {

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
	 * @return fieldInfo
	 */
	public FieldInfo getFieldInfo() {

		return fieldInfo;
	}

	/**
	 * @return methodInfo
	 */
	public MethodInfo getMethodInfo() {

		return methodInfo;
	}
}
