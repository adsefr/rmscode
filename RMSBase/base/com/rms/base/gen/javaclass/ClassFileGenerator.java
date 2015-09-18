package com.rms.base.gen.javaclass;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.rms.base.constant.Encodes;
import com.rms.base.gen.javaclass.generator.BaseGenerator;
import com.rms.base.gen.javaclass.generator.ClassGenerator;
import com.rms.base.gen.javaclass.generator.FieldGenerator;
import com.rms.base.gen.javaclass.generator.ImportGenerator;
import com.rms.base.gen.javaclass.generator.MethodGenerator;
import com.rms.base.gen.javaclass.generator.PackageGenerator;
import com.rms.base.io.FileWriter;
import com.rms.base.io.IOFactory;
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
	public void generate() {

		clearBuffer();

		// packageGenerator.generatePackageInfoFile(null);

		packageGenerator.generate();
		packageGenerator.appendTo(this);

		importGenerator.generate();
		importGenerator.appendTo(this);

		classGenerator.setMethodGenerator(methodGenerator);
		classGenerator.setFieldGenerator(fieldGenerator);
		classGenerator.generate();
		classGenerator.appendTo(this);
	}

	public void write(Path outDirectory) throws IOException {

		Assertion.assertNotNull("outDirectory", outDirectory);

		Path outFile = Paths.get(outDirectory.toString(), classGenerator.getClassInfo().getSimpleClassName() + ".java");

		FileWriter fileWriter = IOFactory.newFileWriter(Encodes.CHARSET_UTF8, outFile.toFile());
		fileWriter.write(super.toString());
		fileWriter.close();
	}
}
