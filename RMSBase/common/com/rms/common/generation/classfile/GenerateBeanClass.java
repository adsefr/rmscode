package com.rms.common.generation.classfile;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.rms.base.generation.classfile.ClassInfo;
import com.rms.base.generation.classfile.GenerateClassFile;
import com.rms.base.generation.classfile.ModifierType;
import com.rms.base.generation.model.DataType;
import com.rms.base.generation.model.FieldModel;
import com.rms.base.generation.model.MethodModel;
import com.rms.base.generation.model.ParameterModel;
import com.rms.base.logging.Logger;
import com.rms.base.util.TextUtil;
import com.rms.base.validate.Assertion;

/**
 *
 * @author ri.meisei
 * @since 2015/09/04
 */
public class GenerateBeanClass extends GenerateClassFile {

	private final Logger logger = Logger.getLogger(GenerateBeanClass.class);

	protected ClassInfo classInfo;

	protected final Map<String, String> fieldInfos = new LinkedHashMap<>();

	public GenerateBeanClass() {

	}

	public void setClassName(String className) {

		Assertion.assertNotBlank("className", className);

		this.classInfo = new ClassInfo(className);
	}

	public void setClassName(String packageName, String simpleClassName) {

		Assertion.assertNotBlank("packageName", packageName);
		Assertion.assertNotBlank("simpleClassName", simpleClassName);

		this.classInfo = new ClassInfo(packageName, simpleClassName);
	}

	public void addField(String fieldName, String fieldType) {

		Assertion.assertNotBlank("fieldName", fieldName);
		Assertion.assertNotBlank("fieldType", fieldType);

		fieldInfos.put(fieldName, fieldType);
	}

	public void reset() {

		classInfo = null;
		fieldInfos.clear();
	}

	@Override
	public String generate() {

		classInfo.getModifierInfo().add(ModifierType.PUBLIC);

		for (Entry<String, String> fieldInfo : fieldInfos.entrySet()) {
			String fieldName = fieldInfo.getKey();
			String fieldType = fieldInfo.getValue();
			DataType dataType = DataType.getDataType(fieldType);
			FieldModel fieldModel = new FieldModel();
			fieldModel.getModifierInfo().add(ModifierType.PRIVATE);
			fieldModel.setDataType(dataType);
			fieldModel.setFieldName(fieldName);

			MethodModel getMethodModel = new MethodModel();
			getMethodModel.getModifierInfo().add(ModifierType.PUBLIC);
			getMethodModel.setMethodName("get" + TextUtil.capital(fieldName));
			getMethodModel.setReturnType(dataType);
			getMethodModel.setMethodBody(" return this." + fieldName + ";");
			MethodModel setMethodModel = new MethodModel();
			setMethodModel.getModifierInfo().add(ModifierType.PUBLIC);
			setMethodModel.setMethodName("set" + TextUtil.capital(fieldName));
			setMethodModel.setMethodBody("this." + fieldName + " = " + fieldName + ";");
			ParameterModel parameterModel = new ParameterModel();
			parameterModel.setDataType(dataType);
			parameterModel.setParameterName(fieldName);
			setMethodModel.getParameterInfo().add(parameterModel);

			classInfo.getFieldInfo().add(fieldModel);
			classInfo.getMethodInfo().add(getMethodModel);
			classInfo.getMethodInfo().add(setMethodModel);
		}

		logger.trace(classInfo.toString());

		return classInfo.toString();
	}
}
