package com.rms.base.generation.classfile;

public abstract class GenerateClassFile {

	private String lineSeparator = System.getProperty("line.separator");

	private PackageGenerator packageGenerator;

	private ImportGenerator importGenerator;

	private ClassGenerator classGenerator;

	private FieldGenerator fieldGenerator;

	private MethodGenerator methodGenerator;

	protected GenerateClassFile() {

	}

	/**
	 * @return lineSeparator
	 */
	public String getLineSeparator() {

		return lineSeparator;
	}

	/**
	 * @param lineSeparator
	 *            セットする lineSeparator
	 */
	public void setLineSeparator(String lineSeparator) {

		this.lineSeparator = lineSeparator;
	}

	/**
	 * @param packageGenerator
	 *            セットする packageGenerator
	 */
	public void setPackageGenerator(PackageGenerator packageGenerator) {

		this.packageGenerator = packageGenerator;
	}

	/**
	 * @param importGenerator
	 *            セットする importGenerator
	 */
	public void setImportGenerator(ImportGenerator importGenerator) {

		this.importGenerator = importGenerator;
	}

	/**
	 * @param classGenerator
	 *            セットする classGenerator
	 */
	public void setClassGenerator(ClassGenerator classGenerator) {

		this.classGenerator = classGenerator;
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

	public String generate() {

		StringBuilder stringBuilder = new StringBuilder();
		if (packageGenerator != null) {
			stringBuilder.append(packageGenerator.generate());
			stringBuilder.append(lineSeparator);
		}
		if (packageGenerator != null) {
			stringBuilder.append(importGenerator.generate());
			stringBuilder.append(lineSeparator);
		}
		if (packageGenerator != null) {
			stringBuilder.append(classGenerator.generate());
			stringBuilder.append(lineSeparator);
		}
		if (packageGenerator != null) {
			stringBuilder.append(fieldGenerator.generate());
			stringBuilder.append(lineSeparator);
		}
		if (packageGenerator != null) {
			stringBuilder.append(methodGenerator.generate());
			stringBuilder.append(lineSeparator);
		}

		return stringBuilder.toString();
	}
}
