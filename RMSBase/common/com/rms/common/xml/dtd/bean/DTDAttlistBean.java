package com.rms.common.xml.dtd.bean;

import java.util.ArrayList;
import java.util.List;

public class DTDAttlistBean {

	private String name;

	private List<DTDAttributeBean> dtdAttributeBeanList = new ArrayList<DTDAttributeBean>();

	/**
	 * @return name
	 */
	public String getName() {

		return name;
	}

	/**
	 * @param name
	 *            セットする name
	 */
	public void setName(String name) {

		this.name = name;
	}

	/**
	 * @return dtdAttributeBeanList
	 */
	public List<DTDAttributeBean> getDtdAttributeBeanList() {

		return dtdAttributeBeanList;
	}

	/**
	 * @param dtdAttributeBeanList
	 *            セットする dtdAttributeBeanList
	 */
	public void setDtdAttributeBeanList(List<DTDAttributeBean> dtdAttributeBeanList) {

		this.dtdAttributeBeanList = dtdAttributeBeanList;
	}

}
