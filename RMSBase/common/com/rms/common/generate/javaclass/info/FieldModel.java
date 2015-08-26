package com.rms.common.generate.javaclass.info;

/**
 *
 * @author ri.meisei
 * @since 2015/03/24
 */
public class FieldModel {

	private String comment;

	private final ModifierInfo modifierInfo = new ModifierInfo(TargetType.FIELD);

	private DataType dataType;

	private String fieldName;

	private String value;

	public FieldModel() {

	}

	public String getComment() {

		return comment;
	}

	public void setComment(String comment) {

		this.comment = comment;
	}

	public ModifierInfo getModifierInfo() {

		return modifierInfo;
	}

	public DataType getDataType() {

		return dataType;
	}

	public void setDataType(DataType dataType) {

		this.dataType = dataType;
	}

	public String getFieldName() {

		return fieldName;
	}

	public void setFieldName(String fieldName) {

		this.fieldName = fieldName;
	}

	public String getValue() {

		return value;
	}

	public void setValue(String value) {

		this.value = value;
	}

}
