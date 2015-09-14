package com.rms.base.generation.classfile;

import java.util.List;

import com.rms.base.constant.Characters;
import com.rms.base.logging.Logger;
import com.rms.base.util.ArrayUtil;
import com.rms.base.util.TextUtil;

/**
 *
 * @author ri.meisei
 * @since 2015/09/04
 */
public class ClassGenerator extends BaseGenerator {

	private final Logger logger = Logger.getLogger(this.getClass());

	private ClassInfo classInfo;

	public ClassGenerator() {

	}

	/**
	 * @param classInfo
	 */
	public ClassGenerator(ClassInfo classInfo) {

		super();
		this.classInfo = classInfo;
	}

	/**
	 * @param classInfo
	 *            セットする classInfo
	 */
	public void setClassInfo(ClassInfo classInfo) {

		this.classInfo = classInfo;
	}

	// {ClassModifier} class Identifier [TypeParameters] [Superclass]
	// [Superinterfaces] ClassBody

	@Override
	protected void generate() {

		clearBuffered();

		generatePackage(classInfo);

		generateAnnotation(classInfo);

		generateClassDeclarations(classInfo);
	}

	public void addClassContent(CharSequence content) {

		buffered.append(content);
		buffered.append(lineSeparator);
		buffered.append(lineSeparator);
	}

	private void generatelineSeparator(int count) {

		if (count == 1) {
			buffered.append(lineSeparator);
		} else if (count > 1) {
			buffered.append(TextUtil.repeat(lineSeparator, count));
		}
	}

	private void generateSpace(int count) {

		if (count == 1) {
			buffered.append(Characters.SPACE_HALF);
		} else if (count > 1) {
			buffered.append(TextUtil.repeat(Characters.SPACE_HALF, count));
		}
	}

	private void generatePackage(ClassInfo classInfo) {

		String packageName = classInfo.getPackageName();
		if (packageName != null && !packageName.isEmpty()) {
			buffered.append("package ").append(packageName).append(";");
			generatelineSeparator(2);
		}
	}

	private void generateAnnotation(ClassInfo classInfo) {

		AnnotationInfo annotationInfo = classInfo.getAnnotationInfo();
		buffered.append(annotationInfo.toString());
	}

	private void generateClassDeclarations(ClassInfo classInfo) {

		long beforeLength = 0;
		long afterLength = 0;

		beforeLength = buffered.length();
		ModifierInfo modifierInfo = classInfo.getModifierInfo();
		buffered.append(modifierInfo.toString());
		afterLength = buffered.length();
		if ((beforeLength != afterLength)) {
			generateSpace(1);
		}
		buffered.append("class");
		generateSpace(1);
		buffered.append(classInfo.getSimpleClassName());

		List<String> parentClasses = classInfo.getParentClasses();
		if (!parentClasses.isEmpty()) {
			buffered.append("extends ").append(ArrayUtil.join(parentClasses, ", "));
		}

		List<String> parentInterfaces = classInfo.getParentInterfaces();
		if (!parentInterfaces.isEmpty()) {
			buffered.append("implements ").append(ArrayUtil.join(parentInterfaces, ", "));
		}
	}

	public void generateClassBodyStart(StringBuilder buffered) {

		buffered.append(" {");
		buffered.append(lineSeparator);
		buffered.append(lineSeparator);
	}

	public void generateClassBodyEnd(StringBuilder buffered) {

		buffered.append("}");
		buffered.append(lineSeparator);
	}
}
