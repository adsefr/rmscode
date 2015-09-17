package com.rms.base.gen.javaclass.model;

import com.rms.base.datatype.enumeration.DataType;
import com.rms.base.gen.javaclass.enumeration.FieldModifier;
import com.rms.base.util.TextUtil;

/**
 *
 * @author ri.meisei
 * @since 2015/09/04
 */
public class FieldModel {

	private String comment = "";

	private AnnotationInfo annotationInfo = new AnnotationInfo();

	private final ModifierInfo<FieldModifier> modifierInfo = new ModifierInfo<>();

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

	public boolean hasComment() {

		return TextUtil.isNotBlank(comment);
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
	public ModifierInfo<FieldModifier> getModifierInfo() {

		return modifierInfo;
	}

}
