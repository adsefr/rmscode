package com.rms.base.gen.javaclass.generator;

import com.rms.base.gen.javaclass.model.FieldInfo;
import com.rms.base.gen.javaclass.model.FieldModel;
import com.rms.base.logging.Logger;
import com.rms.base.util.TextUtil;

/**
 *
 * @author ri.meisei
 * @since 2015/09/04
 */
public class FieldGenerator extends BaseGenerator {

	private final Logger logger = Logger.getLogger(this.getClass());

	private FieldInfo fieldInfo;

	private AnnotationGnerator annotationGnerator = new AnnotationGnerator();

	private ModifierGenerator modifierGenerator = new ModifierGenerator();

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
	public void generate() {

		clearBuffer();

		int count = fieldInfo.count();

		for (FieldModel fieldModel : fieldInfo.getFieldModelCollection().values()) {
			if (fieldModel.hasComment()) {
				append("\t");
				append("/**");
				append(lineSeparator);
				append("\t");
				append(" *");
				append(fieldModel.getComment());
				append(lineSeparator);
				append("\t");
				append(" */");
				append(lineSeparator);
			}
			append("\t");

			annotationGnerator.setAnnotationInfo(fieldModel.getAnnotationInfo());
			annotationGnerator.generate();
			if (!annotationGnerator.isEmpty()) {
				annotationGnerator.appendTo(this);
				append(lineSeparator);
			}

			modifierGenerator.setModifierInfo(fieldModel.getModifierInfo());
			modifierGenerator.generate();
			if (!modifierGenerator.isEmpty()) {
				modifierGenerator.appendTo(this);
				append(" ");
			}

			append(fieldModel.getDataType().getName());
			append(" ");
			append(fieldModel.getFieldName());

			if (TextUtil.isNotBlank(fieldModel.getValue())) {
				append(" = ");
				append(fieldModel.getValue().toString());
			}

			append(";");

			if (count-- > 1) {
				append(lineSeparator);
				append(lineSeparator);
			}
		}

		logger.trace(toString());
	}
}
