package com.rms.base.gen.javaclass.model;

import java.util.LinkedHashMap;
import java.util.Map;

import com.rms.base.validate.Assertion;

/**
 *
 * @author ri.meisei
 * @since 2015/09/04
 */
public class FieldInfo {

	private final Map<String, FieldModel> fieldModelCollection = new LinkedHashMap<String, FieldModel>();

	public FieldInfo() {

	}

	/**
	 * @return fieldModelCollection
	 */
	public Map<String, FieldModel> getFieldModelCollection() {

		return fieldModelCollection;
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

	public int count() {

		return fieldModelCollection.size();
	}

	boolean isEmpty() {

		return fieldModelCollection.isEmpty();
	}
}
