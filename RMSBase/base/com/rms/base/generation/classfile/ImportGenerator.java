package com.rms.base.generation.classfile;

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

import com.rms.base.constant.Characters;
import com.rms.base.logging.Logger;
import com.rms.base.validate.Assertion;

/**
 *
 * @author ri.meisei
 * @since 2015/09/14
 */
public class ImportGenerator extends BaseGenerator {

	private final Logger logger = Logger.getLogger(ImportGenerator.class);

	private final Set<String> javaPackageCollection = new TreeSet<String>();

	private final Set<String> javaxPackageCollection = new TreeSet<String>();

	private final Set<String> orgPackageCollection = new TreeSet<String>();

	private final Set<String> comPackageCollection = new TreeSet<String>();

	private final Set<String> otherPackageCollection = new TreeSet<String>();

	private ImportInfo importInfo;

	/**
	 *
	 */
	public ImportGenerator() {

		super();
	}

	/**
	 * @param packageInfo
	 */
	public ImportGenerator(ImportInfo importInfo) {

		super();
		this.importInfo = importInfo;
	}

	/**
	 * @param importInfo
	 *            セットする importInfo
	 */
	public void setImportInfo(ImportInfo importInfo) {

		this.importInfo = importInfo;
	}

	public void addImportClass(Collection<String> classNameCollection) {

		Assertion.assertNotNull("classNameCollection", classNameCollection);

		classNameCollection.parallelStream().forEach(className -> {
			int index = className.indexOf(".");
			String prefix = "";
			if (index > 0) {
				prefix = className.substring(0, index);
			}
			switch (prefix) {
			case "java":
				javaPackageCollection.add(className);
				break;
			case "javax":
				javaxPackageCollection.add(className);
				break;
			case "org":
				orgPackageCollection.add(className);
				break;
			case "com":
				comPackageCollection.add(className);
				break;
			default:
				otherPackageCollection.add(className);
				break;
			}
		});
	}

	@Override
	protected void generate() {

		clearBuffered();

		if (importInfo == null) {
			return;
		}

		addImportClass(importInfo.getClassNameCollection());

		generateInternal(javaPackageCollection);
		generateInternal(javaxPackageCollection);
		generateInternal(orgPackageCollection);
		generateInternal(comPackageCollection);
		generateInternal(otherPackageCollection);

		logger.trace(lineSeparator + buffered.toString());
	}

	private void generateInternal(Set<String> classNameCollection) {

		Assertion.assertNotNull("packageCollection", classNameCollection);

		classNameCollection.stream().forEach(className -> {
			buffered.append(className).append(Characters.SEMICOLON).append(lineSeparator);
		});
	}
}
