package com.rms.base.gen.javaclass.generator;

import java.util.List;

import com.rms.base.gen.javaclass.enumeration.ClassModifier;
import com.rms.base.gen.javaclass.model.AnnotationInfo;
import com.rms.base.gen.javaclass.model.ClassInfo;
import com.rms.base.gen.javaclass.model.ModifierInfo;
import com.rms.base.logging.Logger;
import com.rms.base.util.ArrayUtil;

/**
 *
 * @author ri.meisei
 * @since 2015/09/04
 */
public class ClassGenerator extends BaseGenerator {

	private final Logger logger = Logger.getLogger(this.getClass());

	private AnnotationGnerator annotationGnerator = new AnnotationGnerator();

	private ModifierGenerator modifierGenerator = new ModifierGenerator();

	private FieldGenerator fieldGenerator = new FieldGenerator();

	private MethodGenerator methodGenerator = new MethodGenerator();

	private ClassInfo classInfo;

	public ClassGenerator() {

		super();
	}

	/**
	 * @param classInfo
	 */
	public ClassGenerator(ClassInfo classInfo) {

		super();

		this.classInfo = classInfo;
	}

	/**
	 * @return classInfo
	 */
	public ClassInfo getClassInfo() {

		return classInfo;
	}

	/**
	 * @param classInfo
	 *            セットする classInfo
	 */
	public void setClassInfo(ClassInfo classInfo) {

		this.classInfo = classInfo;
	}

	/**
	 * @param modifierGenerator
	 *            セットする modifierGenerator
	 */
	public void setModifierGenerator(ModifierGenerator modifierGenerator) {

		this.modifierGenerator = modifierGenerator;
	}

	/**
	 * @param fieldGenerator
	 *            セットする fieldGenerator
	 */
	public void setFieldGenerator(FieldGenerator fieldGenerator) {

		this.fieldGenerator = fieldGenerator;
	}

	/**
	 * @param methodGenerator
	 *            セットする methodGenerator
	 */
	public void setMethodGenerator(MethodGenerator methodGenerator) {

		this.methodGenerator = methodGenerator;
	}

	@Override
	public void generate() {

		clearBuffer();

		AnnotationInfo annotationInfo = classInfo.getAnnotationInfo();
		annotationGnerator.setAnnotationInfo(annotationInfo);
		annotationGnerator.generate();
		if (!annotationGnerator.isEmpty()) {
			annotationGnerator.appendTo(this);
			append(lineSeparator);
		}

		ModifierInfo<ClassModifier> modifierInfo = classInfo.getModifierInfo();
		modifierGenerator.setModifierInfo(modifierInfo);
		modifierGenerator.generate();
		if (!modifierGenerator.isEmpty()) {
			modifierGenerator.appendTo(this);
			append(" ");
		}

		append("class ");
		append(classInfo.getSimpleClassName());

		List<String> parentClasses = classInfo.getParentClasses();
		if (!parentClasses.isEmpty()) {
			append("extends ").append(ArrayUtil.join(parentClasses, ", "));
		}

		List<String> parentInterfaces = classInfo.getParentInterfaces();
		if (!parentInterfaces.isEmpty()) {
			append("implements ").append(ArrayUtil.join(parentInterfaces, ", "));
		}

		append(" ");
		append("{");
		append(lineSeparator);
		append(lineSeparator);

		fieldGenerator.generate();
		if (!fieldGenerator.isEmpty()) {
			fieldGenerator.appendTo(this);
			append(lineSeparator);
			append(lineSeparator);
		}

		methodGenerator.generate();
		if (!methodGenerator.isEmpty()) {
			methodGenerator.appendTo(this);
			append(lineSeparator);
			append(lineSeparator);
		}

		append("}");
		append(lineSeparator);

		logger.trace(toString());
	}
}
