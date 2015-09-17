package com.rms.base.gen.javaclass.model;

import java.nio.file.Path;

import com.rms.base.validate.Assertion;

/**
 *
 * @author ri.meisei
 * @since 2015/09/11
 */
public class PackageInfo extends BaseInfo {

	private final AnnotationInfo annotationInfo = new AnnotationInfo();

	private final StringBuilder comment = new StringBuilder();

	private boolean generatePackageInfoClass = false;

	private String packageName = "";

	private Path destDirecotry;

	/**
	 *
	 * @param packageName
	 */
	public PackageInfo(String packageName) {

		Assertion.assertNotBlank("packageName", packageName);

		this.packageName = packageName;
	}

	/**
	 *
	 * @param packageName
	 * @param generatePackageInfoClass
	 */
	public PackageInfo(String packageName, boolean generatePackageInfoClass) {

		Assertion.assertNotBlank("packageName", packageName);

		this.packageName = packageName;
		this.generatePackageInfoClass = generatePackageInfoClass;
	}

	/**
	 * @return packageName
	 */
	public String getPackageName() {

		return packageName;
	}

	/**
	 * @param packageName
	 *            セットする packageName
	 */
	public void setPackageName(String packageName) {

		this.packageName = packageName;
	}

	/**
	 * @return comment
	 */
	public String getComment() {

		return comment.toString();
	}

	/**
	 * @return comment
	 */
	public StringBuilder appendComment(CharSequence charSequence) {

		return comment.append(charSequence);
	}

	/**
	 * @return annotationInfo
	 */
	public AnnotationInfo getAnnotationInfo() {

		return annotationInfo;
	}

	/**
	 * @return generatePackageInfoClass
	 */
	public boolean isGeneratePackageInfoClass() {

		return generatePackageInfoClass;
	}

	/**
	 * @param generatePackageInfoClass
	 *            セットする generatePackageInfoClass
	 */
	public void setGeneratePackageInfoClass(boolean generatePackageInfoClass) {

		this.generatePackageInfoClass = generatePackageInfoClass;
	}

	/**
	 * @return destDirecotry
	 */
	public Path getDestDirecotry() {

		return destDirecotry;
	}

	/**
	 * @param destDirecotry
	 *            セットする destDirecotry
	 */
	public void setDestDirecotry(Path destDirecotry) {

		this.destDirecotry = destDirecotry;
	}

}
