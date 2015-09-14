package com.rms.base.generation.classfile;

import com.rms.base.validate.Assertion;

public abstract class ClassFileGenerator extends BaseGenerator {

	protected PackageGenerator packageGenerator = new PackageGenerator();

	protected ImportGenerator importGenerator = new ImportGenerator();

	protected ClassGenerator classGenerator = new ClassGenerator();

	protected FieldGenerator fieldGenerator = new FieldGenerator();

	protected MethodGenerator methodGenerator = new MethodGenerator();

	protected ClassFileGenerator() {

	}

	/**
	 * @return packageGenerator
	 */
	public PackageGenerator getPackageGenerator() {

		return packageGenerator;
	}

	/**
	 * @param packageGenerator
	 *            セットする packageGenerator
	 */
	public void setPackageGenerator(PackageGenerator packageGenerator) {

		Assertion.assertNotNull("packageGenerator", packageGenerator);

		this.packageGenerator = packageGenerator;
	}

	/**
	 * @return importGenerator
	 */
	public ImportGenerator getImportGenerator() {

		return importGenerator;
	}

	/**
	 * @param importGenerator
	 *            セットする importGenerator
	 */
	public void setImportGenerator(ImportGenerator importGenerator) {

		Assertion.assertNotNull("importGenerator", importGenerator);

		this.importGenerator = importGenerator;
	}

	/**
	 * @return classGenerator
	 */
	public ClassGenerator getClassGenerator() {

		return classGenerator;
	}

	/**
	 * @param classGenerator
	 *            セットする classGenerator
	 */
	public void setClassGenerator(ClassGenerator classGenerator) {

		Assertion.assertNotNull("classGenerator", classGenerator);

		this.classGenerator = classGenerator;
	}

	/**
	 * @return fieldGenerator
	 */
	public FieldGenerator getFieldGenerator() {

		return fieldGenerator;
	}

	/**
	 * @param fieldGenerator
	 *            セットする fieldGenerator
	 */
	public void setFieldGenerator(FieldGenerator fieldGenerator) {

		Assertion.assertNotNull("fieldGenerator", fieldGenerator);

		this.fieldGenerator = fieldGenerator;
	}

	/**
	 * @return methodGenerator
	 */
	public MethodGenerator getMethodGenerator() {

		return methodGenerator;
	}

	/**
	 * @param methodGenerator
	 *            セットする methodGenerator
	 */
	public void setMethodGenerator(MethodGenerator methodGenerator) {

		Assertion.assertNotNull("methodGenerator", methodGenerator);

		this.methodGenerator = methodGenerator;
	}

	@Override
	protected void generate() {

		clearBuffered();

		packageGenerator.generate(buffered);
		importGenerator.generate(buffered);
		classGenerator.generate(buffered);
		classGenerator.generateClassBodyStart(buffered);
		fieldGenerator.generate(buffered);
		methodGenerator.generate(buffered);
		classGenerator.generateClassBodyEnd(buffered);
	}
}
