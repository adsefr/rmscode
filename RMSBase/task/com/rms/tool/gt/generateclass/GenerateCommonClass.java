package com.rms.tool.gt.generateclass;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rms.base.constant.Encodes;
import com.rms.base.gen.javaclass.model.ClassInfo;
import com.rms.base.gen.javaclass.model.MethodModel;
import com.rms.base.gen.javaclass.model.ModifierType;
import com.rms.base.gen.javaclass.model.ParameterModel;
import com.rms.base.io.IOFactory;
import com.rms.base.poi.excel.model.CellModel;
import com.rms.base.poi.excel.object.ExcelOperator;
import com.rms.base.poi.excel.object.SheetOperator;

public class GenerateCommonClass {

	public static void main(String[] args) {

		GenerateCommonClass generateCommonClass = new GenerateCommonClass();
		String excelPath = "D:\\tmp\\WB_NX_RUI_C030_共通機能一覧.xlsx";
		try {
			List<RowInfo> rowInfos = generateCommonClass.loadExcel(excelPath);
			generateCommonClass.generate(rowInfos);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void generate(List<RowInfo> rowInfos) {

		Map<String, ClassInfo> classMap = new HashMap<String, ClassInfo>();
		for (RowInfo rowInfo : rowInfos) {
			ClassInfo classInfo = null;
			String packageName = rowInfo.getPackageName();
			String simpleClassName = rowInfo.getClassName();
			if (packageName == null || packageName.isEmpty() || packageName.equals("-")) {
				continue;
			}
			if (simpleClassName == null || simpleClassName.isEmpty()) {
				continue;
			}

			String className = packageName + "." + simpleClassName;
			if (classMap.containsKey(className)) {
				classInfo = classMap.get(className);
			} else {
				classInfo = new ClassInfo(packageName, simpleClassName);
				classMap.put(className, classInfo);
			}

			// classInfo.getImportInfo().addImportClass(java.util.List.class.getName(),
			// javax.accessibility.Accessible.class.getName(),
			// com.oracle.jrockit.jfr.ContentType.Address.getClass().getName(),
			// org.apache.lucene.LucenePackage.class.getName(),
			// jp.co.rnai.parse.xml.ParseXMLInfo.class.getName());
			// classInfo.getImportInfo().addImportClass(java.util.Map.class.getName(),
			// javax.accessibility.AccessibleAction.class.getName(),
			// com.oracle.jrockit.jfr.DynamicEventToken.class.getName(),
			// org.apache.lucene.analysis.Analyzer.class.getName(),
			// jp.co.rnai.parse.xml.dtd.ParseDTDFile.class.getName());

			classInfo.getModifierInfo().add(ModifierType.PUBLIC);

			// FieldModel fieldModel = new FieldModel();
			// fieldModel.setComment("test field");
			// fieldModel.getModifierInfo().add(ModifierType.PUBLIC,
			// ModifierType.FINAL, ModifierType.STATIC);
			// fieldModel.setDataType(DataType.get("String"));
			// fieldModel.setFieldName("name");
			// fieldModel.setValue("\"abc\"");
			// classInfo.getFieldInfo().add(fieldModel);
			//
			// FieldModel fieldModel2 = new FieldModel();
			// fieldModel2.setComment("test field2");
			// fieldModel2.getModifierInfo().add(ModifierType.PUBLIC,
			// ModifierType.FINAL, ModifierType.STATIC);
			// fieldModel2.setDataType(DataType.get("Integer"));
			// fieldModel2.setFieldName("name2");
			// fieldModel2.setValue("1");
			// classInfo.getFieldInfo().add(fieldModel2);
			// FieldModel fieldModel3 = new FieldModel();
			// fieldModel3.setComment("test field3");
			// fieldModel3.getModifierInfo().add(ModifierType.PUBLIC,
			// ModifierType.FINAL, ModifierType.STATIC);
			// fieldModel3.setDataType(DataType.get("Object"));
			// fieldModel3.setFieldName("name3");
			// classInfo.getFieldInfo().add(fieldModel3);

			String methodName = rowInfo.getVbProcessName();
			if (methodName == null || methodName.isEmpty()) {
				continue;
			}

			MethodModel methodModel = new MethodModel();
			methodModel.getModifierInfo().add(ModifierType.PUBLIC);
			methodModel.getModifierInfo().add(ModifierType.STATIC);

			String returnType = rowInfo.getReturnType();
			switch (rowInfo.getReturnType()) {
				case "Boolean":
					returnType = "boolean";
					break;
				case "Byte":
					returnType = "byte";
					break;
				case "Short":
					returnType = "short";
					break;
				case "Integer":
					returnType = "int";
					break;
				case "Long":
					returnType = "long";
					break;
				case "Float":
					returnType = "float";
					break;
				case "Double":
					returnType = "double";
					break;
			}

			methodModel.setReturnType(DataTypes.getDataType(returnType));
			methodModel.setMethodName(rowInfo.getVbProcessName().substring(0, 1).toLowerCase() + rowInfo.getVbProcessName().substring(1));
			methodModel.setComment(rowInfo.getProcessDetail());
			String parameterList = rowInfo.getMethodParameterList();
			String[] parameters = parameterList.split(",");
			for (String parameter : parameters) {
				if (parameter == null || parameter.isEmpty()) {
					continue;
				}
				if (parameter.equals("NULL")) {
					parameter = "Object=param";
				}
				if (parameter.equals("ParamArray szMsgArgs()")) {
					parameter = "Object=szMsgArgs()";
				}
				String[] parameterInfo = parameter.split("=");
				switch (parameterInfo[1]) {
					case "Boolean":
						parameterInfo[1] = "boolean";
						break;
					case "Byte":
						parameterInfo[1] = "byte";
						break;
					case "Short":
						parameterInfo[1] = "short";
						break;
					case "Integer":
						parameterInfo[1] = "int";
						break;
					case "Long":
						parameterInfo[1] = "long";
						break;
					case "Float":
						parameterInfo[1] = "float";
						break;
					case "Double":
						parameterInfo[1] = "double";
						break;
				}
				if (parameterInfo[0].endsWith("()")) {
					parameterInfo[0] = parameterInfo[0].replace("()", "");
					parameterInfo[1] = parameterInfo[1] + "[]";
				}
				DataTypes dataType = DataTypes.getDataType(parameterInfo[1]);

				ParameterModel parameterModel = new ParameterModel();
				parameterModel.setParameterName(parameterInfo[0]);
				parameterModel.setDataType(dataType);
				methodModel.getParameterInfo().add(parameterModel);
			}

			classInfo.getMethodInfo().add(methodModel);
		}

		for (ClassInfo classInfo : classMap.values()) {
			printClass(classInfo);
		}
	}

	private void printClass(ClassInfo classInfo) {

		String path = "D:\\tmp\\" + classInfo.getSimpleClassName() + ".java";
		try (BufferedWriter bufferedWriter = IOFactory.newBufferedWriter(new File(path), Encodes.CHARSET_UTF8)) {
			bufferedWriter.write(classInfo.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private List<RowInfo> loadExcel(String excelPath) throws IOException {

		ExcelOperator excelOperator = ExcelOperatorFactory.load(excelPath);
		String sheetName = "処理一覧";
		boolean hasSheet = excelOperator.setActiveSheet(sheetName);

		if (!hasSheet) {
			throw new RuntimeException("the sheet is not found. sheetName:" + sheetName);
		}

		SheetOperator sheetOperator = excelOperator.getSheetOperator();

		int startRow = 3;
		int endRow = excelOperator.getLastRowIndex();

		List<RowInfo> rowInfos = new ArrayList<RowInfo>();

		for (int rowIndex = startRow; rowIndex < endRow; rowIndex++) {
			sheetOperator.setCurrRowIndex(rowIndex);

			CellModel seqNo = excelOperator.getCellValue(rowIndex, 2);
			if (seqNo == null) {
				break;
			}
			if (seqNo != null && String.valueOf(seqNo.getValue()).equals("追加")) {
				continue;
			}

			CellModel javaProcessName = excelOperator.getCellValue(rowIndex, 3);

			CellModel vbProcessName = excelOperator.getCellValue(rowIndex, 5);

			CellModel logicName = excelOperator.getCellValue(rowIndex, 7);

			CellModel packageName = excelOperator.getCellValue(rowIndex, 8);

			CellModel className = excelOperator.getCellValue(rowIndex, 9);

			CellModel methodName = excelOperator.getCellValue(rowIndex, 3);

			CellModel returnType = excelOperator.getCellValue(rowIndex, 63);

			CellModel parameterCount = excelOperator.getCellValue(rowIndex, 64);

			CellModel methodParameterList = excelOperator.getCellValue(rowIndex, 65);

			CellModel processDetail = excelOperator.getCellValue(rowIndex, 13);

			CellModel targetType = excelOperator.getCellValue(rowIndex, 17);
			if (!targetType.getValue().equals("Java化対象")) {
				continue;
			}
			CellModel targetTypeOld = excelOperator.getCellValue(rowIndex, 19);

			CellModel userId = excelOperator.getCellValue(rowIndex, 39);

			CellModel filePath = excelOperator.getCellValue(rowIndex, 38);

			RowInfo rowInfo = new RowInfo();
			rowInfo.setSeqNo(String.valueOf(seqNo.getValue()));
			rowInfo.setJavaProcessName(javaProcessName.getValue());
			rowInfo.setVbProcessName(vbProcessName.getValue());
			rowInfo.setLogicName(logicName.getValue());
			rowInfo.setPackageName(packageName.getValue());
			rowInfo.setClassName(className.getValue());
			rowInfo.setMethodName(methodName.getValue());
			rowInfo.setProcessDetail(processDetail.getValue());
			rowInfo.setTargetType(targetType.getValue());
			rowInfo.setTargetTypeOld(targetTypeOld.getValue());
			if (userId != null) {
				rowInfo.setUserId(userId.getValue());
			}
			if (filePath != null) {
				rowInfo.setFilePath(filePath.getValue());
			}
			if (returnType != null) {
				rowInfo.setReturnType(returnType.getValue());
			}
			if (parameterCount != null) {
				rowInfo.setParameterCount(String.valueOf(parameterCount.getValue()));
			}
			if (methodParameterList != null) {
				rowInfo.setMethodParameterList(methodParameterList.getValue());
			}

			rowInfos.add(rowInfo);
		}

		return rowInfos;
	}
}
