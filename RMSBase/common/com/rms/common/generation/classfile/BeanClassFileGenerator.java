package com.rms.common.generation.classfile;

import java.util.List;

import com.rms.base.constant.Characters;
import com.rms.base.datatype.enumeration.DataType;
import com.rms.base.gen.javaclass.ClassFileGenerator;
import com.rms.base.gen.javaclass.enumeration.ClassModifier;
import com.rms.base.gen.javaclass.enumeration.FieldModifier;
import com.rms.base.gen.javaclass.enumeration.MethodModifier;
import com.rms.base.gen.javaclass.model.ClassInfo;
import com.rms.base.gen.javaclass.model.FieldInfo;
import com.rms.base.gen.javaclass.model.FieldModel;
import com.rms.base.gen.javaclass.model.MethodInfo;
import com.rms.base.gen.javaclass.model.MethodModel;
import com.rms.base.gen.javaclass.model.PackageInfo;
import com.rms.base.gen.javaclass.model.ParameterModel;
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
		ClassInfo classInfo = new ClassInfo(DEFAULT_PACKAGE_NAME, tableMeta.getTableName().getStringValue());
		classInfo.getModifierInfo().add(ClassModifier.PUBLIC);
		FieldInfo fieldInfo = new FieldInfo();
		MethodInfo methodInfo = new MethodInfo();

		List<ColumnMeta> columnMetas = tableMeta.getColumnMetas();

		for (ColumnMeta columnMeta : columnMetas) {
			String columnName = columnMeta.getColumnName().getStringValue();
			FieldModel fieldModel = new FieldModel();
			fieldModel.getModifierInfo().add(FieldModifier.PRIVATE);
			fieldModel.setDataType(DataType.getDataType(columnMeta.getJdbcType()));
			fieldModel.setFieldName(columnName);
			fieldInfo.add(fieldModel);

			clearBuffer();
			append("get");
			append(columnName);
			String getMethod = toString();

			MethodModel getMethodModel = new MethodModel();
			getMethodModel.getModifierInfo().add(MethodModifier.PUBLIC);
			getMethodModel.setMethodName(getMethod);
			getMethodModel.setReturnType(DataType.getDataType(columnMeta.getJdbcType()));
			clearBuffer();
			append("return this.");
			append(fieldModel.getFieldName());
			append(Characters.SEMICOLON);
			getMethodModel.setMethodBody(toString());
			methodInfo.add(getMethodModel);

			clearBuffer();
			append("set");
			append(columnName);
			String setMethod = toString();
			MethodModel setMethodModel = new MethodModel();
			setMethodModel.getModifierInfo().add(MethodModifier.PUBLIC, MethodModifier.FINAL);
			setMethodModel.setReturnType(DataType.VOID);
			setMethodModel.setMethodName(setMethod);
			ParameterModel parameterModel = new ParameterModel();
			parameterModel.setDataType(fieldModel.getDataType());
			parameterModel.setParameterName(columnName);
			setMethodModel.getParameterInfo().add(parameterModel);
			clearBuffer();
			append("this.");
			append(fieldModel.getFieldName());
			append(" = ");
			append(fieldModel.getFieldName());
			append(Characters.SEMICOLON);
			setMethodModel.setMethodBody(toString());
			methodInfo.add(setMethodModel);
		}

		getPackageGenerator().setPackageInfo(packageInfo);
		getClassGenerator().setClassInfo(classInfo);
		getFieldGenerator().setFieldInfo(fieldInfo);
		getMethodGenerator().setMethodInfo(methodInfo);
	}
}
