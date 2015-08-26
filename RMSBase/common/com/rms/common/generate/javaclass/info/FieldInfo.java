package com.rms.common.generate.javaclass.info;

import java.util.LinkedHashMap;
import java.util.Map;

import com.rms.base.constant.Characters;
import com.rms.base.validate.Assertion;

/**
 *
 * @author ri.meisei
 * @since 2015/03/20
 */
public class FieldInfo {

	private final Map<String, FieldModel> fieldModelCollection = new LinkedHashMap<String, FieldModel>();

	FieldInfo() {

	}

	/**
	 *
	 * @param fieldModel
	 */
	public FieldModel add(FieldModel fieldModel) {

		Assertion.assertNotNull("fieldModel", fieldModel);

		String fieldName = fieldModel.getFieldName();

		return fieldModelCollection.put(fieldName, fieldModel);
	}

	/**
	 *
	 * @param fieldName
	 * @return
	 */
	public FieldModel get(String fieldName) {

		return fieldModelCollection.get(fieldName);
	}

	/**
	 *
	 * @param fieldModel
	 * @return
	 */
	public FieldModel remove(FieldModel fieldModel) {

		Assertion.assertNotNull("fieldModel", fieldModel);

		String fieldName = fieldModel.getFieldName();

		return remove(fieldName);
	}

	/**
	 *
	 * @param fieldName
	 * @return
	 */
	public FieldModel remove(String fieldName) {

		return fieldModelCollection.remove(fieldName);
	}

	public void clear() {

		fieldModelCollection.clear();
	}

	boolean isEmpty() {

		return fieldModelCollection.isEmpty();
	}

	@Override
	public String toString() {

		StringBuilder sBuilder = new StringBuilder();

		for (FieldModel fieldModel : fieldModelCollection.values()) {
			sBuilder.append("\t");
			sBuilder.append("/**");
			sBuilder.append(Characters.LINE_SEPARATOR_SYSTEM);
			sBuilder.append("\t");
			sBuilder.append(" *");
			sBuilder.append(fieldModel.getComment());
			sBuilder.append(Characters.LINE_SEPARATOR_SYSTEM);
			sBuilder.append("\t");
			sBuilder.append(" */");
			sBuilder.append(Characters.LINE_SEPARATOR_SYSTEM);
			sBuilder.append("\t");
			sBuilder.append(fieldModel.getModifierInfo().toString());
			sBuilder.append(" ");
			sBuilder.append(fieldModel.getDataType().getTypeName());
			sBuilder.append(" ");
			sBuilder.append(fieldModel.getFieldName());

			if (fieldModel.getValue() != null) {
				sBuilder.append(" = ");
				sBuilder.append(fieldModel.getValue().toString());
			}

			sBuilder.append(";");
			sBuilder.append(Characters.LINE_SEPARATOR_SYSTEM);
			sBuilder.append(Characters.LINE_SEPARATOR_SYSTEM);
		}

		return sBuilder.toString();
	}
}
