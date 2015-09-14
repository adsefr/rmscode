package com.rms.common.generation.classfile;

import java.util.List;

import com.rms.base.constant.Characters;
import com.rms.base.enumeration.DataType;
import com.rms.base.generation.classfile.ClassFileGenerator;
import com.rms.base.generation.classfile.ClassInfo;
import com.rms.base.generation.classfile.FieldInfo;
import com.rms.base.generation.classfile.MethodInfo;
import com.rms.base.generation.classfile.ModifierType;
import com.rms.base.generation.classfile.PackageInfo;
import com.rms.base.generation.model.FieldModel;
import com.rms.base.generation.model.MethodModel;
import com.rms.base.jdbc.model.ColumnMeta;
import com.rms.base.jdbc.model.TableMeta;
import com.rms.base.logging.Logger;
import com.rms.base.validate.Assertion;

/**
 *
 * @author ri.meisei
 * @since 2015/09/04
 */
public class BeanClassFileGenerator extends ClassFileGenerator {

	private final Logger logger = Logger.getLogger(BeanClassFileGenerator.class);

	private final static String DEFAULT_PACKAGE_NAME = "com.rms.gen.bean";

	public BeanClassFileGenerator() {

	}

	public void setTableMeta(TableMeta tableMeta) {

		Assertion.assertNotNull("tableMeta", tableMeta);
		PackageInfo packageInfo = new PackageInfo(DEFAULT_PACKAGE_NAME);
		ClassInfo classInfo = new ClassInfo(DEFAULT_PACKAGE_NAME, tableMeta.getTableName());
		classInfo.getModifierInfo().add(ModifierType.PUBLIC);
		FieldInfo fieldInfo = new FieldInfo();
		MethodInfo methodInfo = new MethodInfo();

		List<ColumnMeta> columnMetas = tableMeta.getColumnMetas();

		for (ColumnMeta columnMeta : columnMetas) {
			String columnName = columnMeta.getColumnName();
			FieldModel fieldModel = new FieldModel();
			fieldModel.getModifierInfo().add(ModifierType.PRIVATE);
			fieldModel.setDataType(DataType.getDataType(columnMeta.getJdbcType()));
			fieldModel.setFieldName(columnName);
			fieldModel.setValue(columnMeta.getColumnDefaultValue());
			fieldInfo.add(fieldModel);

			clearBuffered();
			String getMethod = buffered.append("get").append(columnName).toString();
			MethodModel getMethodModel = new MethodModel();
			getMethodModel.setMethodName(getMethod);
			getMethodModel.setReturnType(DataType.getDataType(columnMeta.getJdbcType()));
			clearBuffered();
			getMethodModel.setMethodBody(buffered.append("return this.").append(fieldModel.getFieldName()).append(Characters.SEMICOLON).toString());
			methodInfo.add(getMethodModel);

			clearBuffered();
			String setMethod = buffered.append("set").append(columnName).toString();
			MethodModel setMethodModel = new MethodModel();
			setMethodModel.setReturnType(DataType.VOID);
			setMethodModel.setMethodName(setMethod);
			clearBuffered();
			setMethodModel.setMethodBody(buffered.append("this.").append(fieldModel.getFieldName()).append(" = ").append(fieldModel.getFieldName()).append(Characters.SEMICOLON).toString());
			methodInfo.add(setMethodModel);
		}

		getPackageGenerator().setPackageInfo(packageInfo);
		getClassGenerator().setClassInfo(classInfo);
		getFieldGenerator().setFieldInfo(fieldInfo);
		getMethodGenerator().setMethodInfo(methodInfo);
	}

	@Override
	protected void generate() {

		super.generate();
		logger.debug(buffered.toString());
	}

	// @Override
	// public void generate() {
	//
	// PackageInfo packageInfo = new PackageInfo(DEFAULT_PACKAGE_NAME);
	//
	// ClassInfo classInfo = new ClassInfo(className);
	// classInfo.getModifierInfo().add(ModifierType.PUBLIC);
	//
	// for (Entry<String, String> fieldInfo : fieldInfos.entrySet()) {
	// String fieldName = fieldInfo.getKey();
	// String fieldType = fieldInfo.getValue();
	// DataType dataType = DataType.getDataType(fieldType);
	// FieldModel fieldModel = new FieldModel();
	// fieldModel.getModifierInfo().add(ModifierType.PRIVATE);
	// fieldModel.setDataType(dataType);
	// fieldModel.setFieldName(fieldName);
	//
	// MethodModel getMethodModel = new MethodModel();
	// getMethodModel.getModifierInfo().add(ModifierType.PUBLIC);
	// getMethodModel.setMethodName("get" + TextUtil.capital(fieldName));
	// getMethodModel.setReturnType(dataType);
	// getMethodModel.setMethodBody(" return this." + fieldName + ";");
	// MethodModel setMethodModel = new MethodModel();
	// setMethodModel.getModifierInfo().add(ModifierType.PUBLIC);
	// setMethodModel.setMethodName("set" + TextUtil.capital(fieldName));
	// setMethodModel.setMethodBody("this." + fieldName + " = " + fieldName +
	// ";");
	// ParameterModel parameterModel = new ParameterModel();
	// parameterModel.setDataType(dataType);
	// parameterModel.setParameterName(fieldName);
	// setMethodModel.getParameterInfo().add(parameterModel);
	// }
	//
	// logger.trace(classInfo.toString());
	// }
}
