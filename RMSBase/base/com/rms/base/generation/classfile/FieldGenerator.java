package com.rms.base.generation.classfile;

import com.rms.base.generation.model.FieldModel;
import com.rms.base.logging.Logger;

/**
 *
 * @author ri.meisei
 * @since 2015/09/04
 */
public class FieldGenerator extends BaseGenerator {

	private final Logger logger = Logger.getLogger(this.getClass());

	private FieldInfo fieldInfo;

	public FieldGenerator() {

	}

	/**
	 * @param fieldInfo
	 */
	public FieldGenerator(FieldInfo fieldInfo) {

		super();
		this.fieldInfo = fieldInfo;
	}

	/**
	 * @return fieldInfo
	 */
	public FieldInfo getFieldInfo() {

		return fieldInfo;
	}

	/**
	 * @param fieldInfo
	 *            セットする fieldInfo
	 */
	public void setFieldInfo(FieldInfo fieldInfo) {

		this.fieldInfo = fieldInfo;
	}

	@Override
	protected void generate() {

		for (FieldModel fieldModel : fieldInfo.getFieldModelCollection().values()) {
			buffered.append("\t");
			buffered.append("/**");
			buffered.append(lineSeparator);
			buffered.append("\t");
			buffered.append(" *");
			buffered.append(fieldModel.getComment());
			buffered.append(lineSeparator);
			buffered.append("\t");
			buffered.append(" */");
			buffered.append(lineSeparator);
			buffered.append("\t");
			buffered.append(fieldModel.getModifierInfo().toString());
			buffered.append(" ");
			buffered.append(fieldModel.getDataType().getStringType());
			buffered.append(" ");
			buffered.append(fieldModel.getFieldName());

			if (fieldModel.getValue() != null) {
				buffered.append(" = ");
				buffered.append(fieldModel.getValue().toString());
			}

			buffered.append(";");
			buffered.append(lineSeparator);
			buffered.append(lineSeparator);
		}

		logger.trace(buffered.toString());
	}
}
