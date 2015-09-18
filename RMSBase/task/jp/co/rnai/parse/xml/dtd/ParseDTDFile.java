package jp.co.rnai.parse.xml.dtd;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rms.base.constant.Characters;
import com.rms.base.io.constant.FileConst.FileType;
import com.rms.base.io.util.FileHelper;
import com.rms.base.util.TextUtil;
import com.rms.common.poi.excel.object.ExcelOperator;
import com.rms.common.poi.excel.object.SheetOperator;
import com.rms.common.xml.dtd.DTDParser;
import com.rms.common.xml.dtd.bean.DTDAttlistBean;
import com.rms.common.xml.dtd.bean.DTDAttributeBean;
import com.rms.common.xml.dtd.bean.DTDBean;
import com.rms.common.xml.dtd.bean.DTDElementBean;
import com.rms.common.xml.dtd.bean.DTDEntityBean;
import com.rms.common.xml.dtd.bean.DTDInfo;
import com.rms.common.xml.dtd.bean.DTDItemBean;
import com.wutka.dtd.DTDParseException;

public class ParseDTDFile {

	private static Map<String, String[]> mapRootElement = new HashMap<String, String[]>() {

		/**
		 *
		 */
		private static final long serialVersionUID = 1L;

		{
			put("jstjstage.dtd", new String[] { "ArticleSet" });
			put("art501.dtd", new String[] { "article", "simple-article", "book-review", "exam" });
			put("art502.dtd", new String[] { "article", "simple-article", "book-review", "exam" });
			put("art510.dtd", new String[] { "article", "simple-article", "book-review", "exam" });
			put("art520.dtd", new String[] { "article", "simple-article", "book-review", "exam" });
			put("AIP.dtd", new String[] { "ArticleSet" });
			put("A++V2.4.dtd", new String[] { "Publisher" });
			put("ani512.dtd", new String[] { "bibdataset" });

		}
	};

	public static void main(String[] args) {

		String dirPath = "C:/workspace/作業内容/DTD/";
		// String regex = ".+\\.(dtd|DTD)(\\.(html|HTML))?";
		String regex = ".+\\.(dtd|DTD)";
		String outDirPath = "C:/workspace/temp/dtd/";
		FileHelper.delete(outDirPath, false);
		try {
			new ParseDTDFile().parse(dirPath, regex, outDirPath);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	private void parse(String path, String regex, String outDir) throws IOException {

		List<File> dtdFileList = FileHelper.getFileList(path, regex, FileType.FILE, true);
		DTDParser dtdParser = new DTDParser();

		for (File dtdFile : dtdFileList) {
			System.out.println(dtdFile.getAbsolutePath());
			try {
				DTDBean dtdBean = dtdParser.parse(dtdFile);
				String filePath = outDir + dtdFile.getName() + ".xlsx";
				ExcelOperator operator = ExcelOperatorFactory.createExcelOperator(new File(filePath));
				writeToPOIOperator(operator, dtdBean);
				operator.write();
			} catch (DTDParseException e) {
				System.out.println(e.getMessage() + " " + dtdFile.getName());
			}
		}
	}

	private void writeToPOIOperator(ExcelOperator operator, DTDBean dtdBean) {

		List<String> rowDataList = new ArrayList<String>();
		operator.createSheet("ELEMENT");
		SheetOperator sheetOperator = operator.getSheetOperator();
		sheetOperator.setCellValues(operator.getFirstRowNo(), getElementHeadInfo());
		for (DTDElementBean dtdElementBean : dtdBean.getDtdElementBeans()) {
			rowDataList.add(dtdElementBean.getName());
			rowDataList.add(dtdElementBean.getContent());

			for (String key : dtdElementBean.getAttribueMap().keySet()) {
				DTDAttributeBean dtdAttributeBean = dtdElementBean.getAttribueMap().get(key);
				rowDataList.add(dtdAttributeBean.getName());
				rowDataList.add(dtdAttributeBean.getType());
				rowDataList.add(dtdAttributeBean.getDeclaration());
				rowDataList.add(dtdAttributeBean.getDefaultValue());
			}

			operator.setCellValues(operator.getLastRowNo() + 1, rowDataList);
			rowDataList.clear();
		}

		;
		operator.setCurrentSheet("ATTLIST");
		operator.setAutoFilter(1, 1048576, 1, getAttlistHeadInfo().size());
		operator.setCellValues(operator.getFirstRowNo(), getAttlistHeadInfo());
		for (DTDAttlistBean dtdAttlistBean : dtdBean.getDtdAttlistBeans()) {
			rowDataList.add(dtdAttlistBean.getName());

			for (DTDAttributeBean dtdAttributeBean : dtdAttlistBean.getDtdAttributeBeanList()) {
				rowDataList.add(dtdAttributeBean.getName());
				rowDataList.add(dtdAttributeBean.getType());
				rowDataList.add(dtdAttributeBean.getDeclaration() + Characters.SPACE_HALF
						+ dtdAttributeBean.getDefaultValue());
			}

			operator.setCellValues(operator.getLastRowNo() + 1, rowDataList);
			rowDataList.clear();
		}

		operator.setCurrentSheet("ENTITY");
		operator.setAutoFilter(1, 1048576, 1, getEntityHeadInfo().size());
		operator.setCellValues(operator.getFirstRowNo(), getEntityHeadInfo());
		for (DTDEntityBean dtdEntityBean : dtdBean.getDtdEntityBeans()) {
			rowDataList.add(dtdEntityBean.getName());
			rowDataList.add(dtdEntityBean.getValue());

			operator.setCellValues(operator.getLastRowNo() + 1, rowDataList);
			rowDataList.clear();
		}

		String[] rootNames = mapRootElement.get(dtdBean.getDtdFile().getName());
		if (rootNames != null) {
			for (String rootName : rootNames) {
				for (DTDElementBean dtdElementBean : dtdBean.getDtdElementBeans()) {
					if (TextUtil.isEquals(rootName, dtdElementBean.getName())) {
						operator.setCurrentSheet("TREE_" + rootName);
						operator.setAutoFilter(1, 1048576, 1, 50);
						writeXMLTree(operator, dtdBean, dtdElementBean, operator.getFirstRowNo() + 1, 4, 1);
					}
				}
			}
		}
	}

	private Integer writeXMLTree(POIOperator operator, DTDBean dtdBean, DTDElementBean dtdElementBean, Integer rowNo,
			int columnNo, int level) {

		operator.setCellValue(rowNo, 2, dtdElementBean.getName());
		operator.setCellValue(rowNo, 3, dtdElementBean.getCardinalType());
		operator.setCellValue(rowNo, columnNo, dtdElementBean.getName());
		operator.setCellValue(rowNo, columnNo + 1, dtdElementBean.getContent());// TODO
		operator.setCellValue(rowNo++, columnNo + 2, dtdElementBean.getCardinalType());// TODO
		if (dtdElementBean.isMixedContent()) {
			operator.setCellValue(rowNo - 1, 0, level);
			operator.setCellValue(rowNo - 1, 1, "要確認（Parent）");
		}
		List<DTDItemBean> dtdItemBeans = dtdElementBean.getDtdItemBeans();
		for (DTDItemBean dtdItemBean : dtdItemBeans) {
			if (isANY(dtdItemBean) || isEmpty(dtdItemBean) || isPCDATA(dtdItemBean)) {
				return rowNo;
			}
		}

		for (DTDItemBean dtdItemBean : dtdItemBeans) {
			if (isANY(dtdItemBean) || isEmpty(dtdItemBean) || isPCDATA(dtdItemBean)) {
				continue;
			}

			DTDElementBean nextLevel = getDTDElementBean(dtdBean, dtdElementBean, dtdItemBean);
			if (nextLevel != null && !TextUtil.isEquals(dtdElementBean.getName(), nextLevel.getName())) {
				if (dtdElementBean.isMixedContent()) {
					operator.setCellValue(rowNo, 0, level);
					operator.setCellValue(rowNo, 1, "要確認（Child）");
				}
				rowNo = writeXMLTree(operator, dtdBean, nextLevel, rowNo, columnNo + 1, level + 1);
			}
		}

		return rowNo;
	}

	private boolean isANY(DTDItemBean dtdItemBean) {

		return TextUtil.isEquals(dtdItemBean.getName(), "#ANY");
	}

	private boolean isEmpty(DTDItemBean dtdItemBean) {

		return TextUtil.isEquals(dtdItemBean.getName(), "#EMPTY");
	}

	private boolean isPCDATA(DTDItemBean dtdItemBean) {

		return TextUtil.isEquals(dtdItemBean.getName(), "#PCDATA");
	}

	private DTDElementBean getDTDElementBean(DTDBean dtdBean, DTDElementBean parentElementBean, DTDItemBean dtdItemBean) {

		for (DTDElementBean dtdElementBean : dtdBean.getDtdElementBeans()) {
			if (dtdElementBean.getName().equals(dtdItemBean.getName())) {
				for (DTDItemBean subDTDItemBean : dtdElementBean.getDtdItemBeans()) {
					if (subDTDItemBean.getName().equals(parentElementBean.getName())) {
						return null;
					}
				}

				dtdElementBean.setCardinalType(dtdItemBean.getCardinalType());

				return dtdElementBean;
			}
		}

		return null;
	}

	private List<String> getElementHeadInfo() {

		List<String> headInfoList = new ArrayList<String>();
		headInfoList.add(DTDInfo.DTD_ELEMENT_NAME);
		headInfoList.add(DTDInfo.DTD_ELEMENT_CONTENT);
		headInfoList.add(DTDInfo.DTD_ELEMENT_CONTENT);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_01);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_01);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_02);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_02);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_03);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_03);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_04);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_05);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_05);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_05);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_06);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_06);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_07);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_07);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_08);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_08);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_09);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_09);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_10);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_10);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_11);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_11);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_12);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_12);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_13);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_13);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_14);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_14);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_15);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_15);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_16);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_16);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_17);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_17);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_18);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_18);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_19);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_19);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_20);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_20);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_21);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_21);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_22);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_22);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_23);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_23);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_24);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_24);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_25);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_25);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_26);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_26);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_27);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_27);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_28);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_28);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_29);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_29);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_30);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_30);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_31);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_31);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_32);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_32);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_33);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_33);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_34);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_34);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_35);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_35);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_36);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_36);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_37);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_37);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_38);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_38);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_39);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_39);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_40);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_40);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_41);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_41);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_42);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_42);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_43);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_43);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_44);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_44);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_45);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_45);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_46);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_46);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_47);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_47);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_48);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_48);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_49);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_49);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_50);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_50);

		return headInfoList;
	}

	private static List<String> getAttlistHeadInfo() {

		List<String> headInfoList = new ArrayList<String>();
		headInfoList.add(DTDInfo.DTD_ATTLIST_ELEMENT_NAME);

		headInfoList.add(DTDInfo.DTD_ATTR_NAME_01);
		headInfoList.add(DTDInfo.DTD_ATTLIST_ATTRIBUTE_TYPE);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_01);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_02);
		headInfoList.add(DTDInfo.DTD_ATTLIST_ATTRIBUTE_TYPE);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_02);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_03);
		headInfoList.add(DTDInfo.DTD_ATTLIST_ATTRIBUTE_TYPE);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_03);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_04);
		headInfoList.add(DTDInfo.DTD_ATTLIST_ATTRIBUTE_TYPE);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_04);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_05);
		headInfoList.add(DTDInfo.DTD_ATTLIST_ATTRIBUTE_TYPE);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_05);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_06);
		headInfoList.add(DTDInfo.DTD_ATTLIST_ATTRIBUTE_TYPE);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_06);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_07);
		headInfoList.add(DTDInfo.DTD_ATTLIST_ATTRIBUTE_TYPE);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_07);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_08);
		headInfoList.add(DTDInfo.DTD_ATTLIST_ATTRIBUTE_TYPE);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_08);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_09);
		headInfoList.add(DTDInfo.DTD_ATTLIST_ATTRIBUTE_TYPE);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_09);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_10);
		headInfoList.add(DTDInfo.DTD_ATTLIST_ATTRIBUTE_TYPE);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_10);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_11);
		headInfoList.add(DTDInfo.DTD_ATTLIST_ATTRIBUTE_TYPE);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_11);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_12);
		headInfoList.add(DTDInfo.DTD_ATTLIST_ATTRIBUTE_TYPE);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_12);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_13);
		headInfoList.add(DTDInfo.DTD_ATTLIST_ATTRIBUTE_TYPE);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_13);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_14);
		headInfoList.add(DTDInfo.DTD_ATTLIST_ATTRIBUTE_TYPE);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_14);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_15);
		headInfoList.add(DTDInfo.DTD_ATTLIST_ATTRIBUTE_TYPE);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_15);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_16);
		headInfoList.add(DTDInfo.DTD_ATTLIST_ATTRIBUTE_TYPE);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_16);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_17);
		headInfoList.add(DTDInfo.DTD_ATTLIST_ATTRIBUTE_TYPE);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_17);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_18);
		headInfoList.add(DTDInfo.DTD_ATTLIST_ATTRIBUTE_TYPE);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_18);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_19);
		headInfoList.add(DTDInfo.DTD_ATTLIST_ATTRIBUTE_TYPE);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_19);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_20);
		headInfoList.add(DTDInfo.DTD_ATTLIST_ATTRIBUTE_TYPE);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_20);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_21);
		headInfoList.add(DTDInfo.DTD_ATTLIST_ATTRIBUTE_TYPE);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_21);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_22);
		headInfoList.add(DTDInfo.DTD_ATTLIST_ATTRIBUTE_TYPE);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_22);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_23);
		headInfoList.add(DTDInfo.DTD_ATTLIST_ATTRIBUTE_TYPE);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_23);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_24);
		headInfoList.add(DTDInfo.DTD_ATTLIST_ATTRIBUTE_TYPE);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_24);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_25);
		headInfoList.add(DTDInfo.DTD_ATTLIST_ATTRIBUTE_TYPE);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_25);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_26);
		headInfoList.add(DTDInfo.DTD_ATTLIST_ATTRIBUTE_TYPE);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_26);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_27);
		headInfoList.add(DTDInfo.DTD_ATTLIST_ATTRIBUTE_TYPE);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_27);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_28);
		headInfoList.add(DTDInfo.DTD_ATTLIST_ATTRIBUTE_TYPE);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_28);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_29);
		headInfoList.add(DTDInfo.DTD_ATTLIST_ATTRIBUTE_TYPE);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_29);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_30);
		headInfoList.add(DTDInfo.DTD_ATTLIST_ATTRIBUTE_TYPE);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_30);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_31);
		headInfoList.add(DTDInfo.DTD_ATTLIST_ATTRIBUTE_TYPE);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_31);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_32);
		headInfoList.add(DTDInfo.DTD_ATTLIST_ATTRIBUTE_TYPE);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_32);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_33);
		headInfoList.add(DTDInfo.DTD_ATTLIST_ATTRIBUTE_TYPE);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_33);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_34);
		headInfoList.add(DTDInfo.DTD_ATTLIST_ATTRIBUTE_TYPE);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_34);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_35);
		headInfoList.add(DTDInfo.DTD_ATTLIST_ATTRIBUTE_TYPE);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_35);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_36);
		headInfoList.add(DTDInfo.DTD_ATTLIST_ATTRIBUTE_TYPE);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_36);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_37);
		headInfoList.add(DTDInfo.DTD_ATTLIST_ATTRIBUTE_TYPE);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_37);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_38);
		headInfoList.add(DTDInfo.DTD_ATTLIST_ATTRIBUTE_TYPE);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_38);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_39);
		headInfoList.add(DTDInfo.DTD_ATTLIST_ATTRIBUTE_TYPE);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_39);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_40);
		headInfoList.add(DTDInfo.DTD_ATTLIST_ATTRIBUTE_TYPE);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_40);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_41);
		headInfoList.add(DTDInfo.DTD_ATTLIST_ATTRIBUTE_TYPE);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_41);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_42);
		headInfoList.add(DTDInfo.DTD_ATTLIST_ATTRIBUTE_TYPE);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_42);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_43);
		headInfoList.add(DTDInfo.DTD_ATTLIST_ATTRIBUTE_TYPE);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_43);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_44);
		headInfoList.add(DTDInfo.DTD_ATTLIST_ATTRIBUTE_TYPE);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_44);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_45);
		headInfoList.add(DTDInfo.DTD_ATTLIST_ATTRIBUTE_TYPE);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_45);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_46);
		headInfoList.add(DTDInfo.DTD_ATTLIST_ATTRIBUTE_TYPE);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_46);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_47);
		headInfoList.add(DTDInfo.DTD_ATTLIST_ATTRIBUTE_TYPE);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_47);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_48);
		headInfoList.add(DTDInfo.DTD_ATTLIST_ATTRIBUTE_TYPE);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_48);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_49);
		headInfoList.add(DTDInfo.DTD_ATTLIST_ATTRIBUTE_TYPE);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_49);
		headInfoList.add(DTDInfo.DTD_ATTR_NAME_50);
		headInfoList.add(DTDInfo.DTD_ATTLIST_ATTRIBUTE_TYPE);
		headInfoList.add(DTDInfo.DTD_ATTR_VALUE_50);

		return headInfoList;
	}

	private List<String> getEntityHeadInfo() {

		List<String> headInfoList = new ArrayList<String>();
		headInfoList.add(DTDInfo.DTD_ENTITY_NAME);
		headInfoList.add(DTDInfo.DTD_ENTITY_VALUE);

		return headInfoList;
	}

}
