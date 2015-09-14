package com.rms.base.generation.model;

import com.rms.base.enumeration.DataType;
import com.rms.base.generation.classfile.AnnotationInfo;
import com.rms.base.generation.classfile.ModifierInfo;

/**
 *
 * @author ri.meisei
 * @since 2015/09/04
 */
public class FieldModel {

	private String comment = "";

	private AnnotationInfo annotationInfo = new AnnotationInfo();

	private final ModifierInfo modifierInfo = new ModifierInfo(TargetType.FIELD);

	private DataType dataType;

	private String fieldName = "";

	private String value = "";

	public FieldModel() {

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
	 * @param annotationInfo
	 *            セットする annotationInfo
	 */
	public void setAnnotationInfo(AnnotationInfo annotationInfo) {

		this.annotationInfo = annotationInfo;
	}

	/**
	 * @return dataType
	 */
	public DataType getDataType() {

		return dataType;
	}

	/**
	 * @param dataType
	 *            セットする dataType
	 */
	public void setDataType(DataType dataType) {

		this.dataType = dataType;
	}

	/**
	 * @return fieldName
	 */
	public String getFieldName() {

		return fieldName;
	}

	/**
	 * @param fieldName
	 *            セットする fieldName
	 */
	public void setFieldName(String fieldName) {

		this.fieldName = fieldName;
	}

	/**
	 * @return value
	 */
	public String getValue() {

		return value;
	}

	/**
	 * @param value
	 *            セットする value
	 */
	public void setValue(String value) {

		this.value = value;
	}

	/**
	 * @return modifierInfo
	 */
	public ModifierInfo getModifierInfo() {

		return modifierInfo;
	}

}
