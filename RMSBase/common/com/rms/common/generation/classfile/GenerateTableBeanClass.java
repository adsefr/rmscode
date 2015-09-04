package com.rms.common.generation.classfile;

import com.rms.base.enumeration.DataType;
import com.rms.base.function.filter.Filter;
import com.rms.base.generation.classfile.ClassInfo;
import com.rms.base.generation.classfile.GenerateClassFile;
import com.rms.base.generation.classfile.ModifierType;
import com.rms.base.generation.model.FieldModel;
import com.rms.base.generation.model.MethodModel;
import com.rms.base.jdbc.model.ColumnMeta;
import com.rms.base.jdbc.model.SchemaMeta;
import com.rms.base.jdbc.model.TableMeta;
import com.rms.base.logging.Logger;

/**
 *
 * @author ri.meisei
 * @since 2015/09/04
 */
public class GenerateTableBeanClass extends GenerateClassFile {

	private final Logger logger = Logger.getLogger(GenerateTableBeanClass.class);

	private String packageName = "com.rms.gen.bean.";

	private String prefixClassName = "";

	private String postfixClassName = "Bean";

	private SchemaMeta schemaMeta;

	private Filter<TableMeta> tableMetaFilter;

	private Filter<ColumnMeta> columnMetaFilter;

	/**
	 * @return schemaMeta
	 */
	public SchemaMeta getSchemaMeta() {

		return schemaMeta;
	}

	/**
	 * @param schemaMeta
	 *            セットする schemaMeta
	 */
	public void setSchemaMeta(SchemaMeta schemaMeta) {

		this.schemaMeta = schemaMeta;
	}

	/**
	 * @param tableMetaFilter
	 *            セットする tableMetaFilter
	 */
	public void setTableMetaFilter(Filter<TableMeta> tableMetaFilter) {

		this.tableMetaFilter = tableMetaFilter;
	}

	@Override
	public String generate() {

		for (TableMeta tableMeta : schemaMeta.getTableMetas()) {
			if (tableMetaFilter != null && !tableMetaFilter.accept(tableMeta)) {
				continue;
			}
			String className = packageName + tableMeta.getTableName();
			ClassInfo classInfo = new ClassInfo(className);
			classInfo.getModifierInfo().add(ModifierType.PUBLIC);

			for (ColumnMeta columnMeta : tableMeta.getColumnMetas()) {
				FieldModel fieldModel = new FieldModel();
				fieldModel.getModifierInfo().add(ModifierType.PRIVATE);
				fieldModel.setDataType(com.rms.base.generation.model.DataType.getDataType(DataType.javaType(columnMeta.getDataType())));
				fieldModel.setFieldName(columnMeta.getColumnName());
				classInfo.getFieldInfo().add(fieldModel);

				MethodModel getMethodModel = new MethodModel();
				getMethodModel.getModifierInfo().add(ModifierType.PUBLIC);
				getMethodModel.setMethodName("get" + columnMeta.getColumnName());
				getMethodModel.setReturnType(com.rms.base.generation.model.DataType.getDataType(DataType.javaType(columnMeta.getDataType())));
				MethodModel setMethodModel = new MethodModel();
				setMethodModel.getModifierInfo().add(ModifierType.PUBLIC);
				setMethodModel.setMethodName("set" + columnMeta.getColumnName());
				setMethodModel.setReturnType(com.rms.base.generation.model.DataType.getDataType(DataType.javaType(columnMeta.getDataType())));

				classInfo.getMethodInfo().add(getMethodModel);
				classInfo.getMethodInfo().add(setMethodModel);
			}
			logger.trace(classInfo.toString());
		}

		return "";
	}

	// private ClassGenerator getClassGenerator(ClassInfo classInfo) {
	//
	// return new ClassGenerator() {
	//
	// @Override
	// public String generate() {
	//
	// StringBuilder stringBuilder = new StringBuilder();
	// stringBuilder.append(annotation(classInfo));
	// stringBuilder.append(GenerateTableBeanClass.this.getLineSeparator());
	// stringBuilder.append(classModify(classInfo));
	// stringBuilder.append(" ");
	// stringBuilder.append(className(classInfo));
	// return stringBuilder.toString();
	// }
	//
	// @Override
	// public String className(ClassInfo classInfo) {
	//
	// return classInfo.getClassName();
	// }
	//
	// @Override
	// public String classModify(ClassInfo classInfo) {
	//
	// return classInfo.getModifierInfo().toString();
	// }
	//
	// @Override
	// public String annotation(ClassInfo classInfo) {
	//
	// return "";
	// }
	// };
	// }
	//
	// private ClassGenerator geClassGenerator(TableMeta tableMeta) {
	//
	// }
}
