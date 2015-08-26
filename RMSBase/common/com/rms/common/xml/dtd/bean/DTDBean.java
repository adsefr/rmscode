package com.rms.common.xml.dtd.bean;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DTDBean {

	private File dtdFile;

	private List<DTDElementBean> dtdElementBeans = new ArrayList<DTDElementBean>();

	private List<DTDAttlistBean> dtdAttlistBeans = new ArrayList<DTDAttlistBean>();

	private List<DTDEntityBean> dtdEntityBeans = new ArrayList<DTDEntityBean>();

	private List<DTDEmptyBean> dtdEmptyBeans = new ArrayList<DTDEmptyBean>();

	private List<DTDNotationBean> dtdNotationBeans = new ArrayList<DTDNotationBean>();

	private List<DTDCommentBean> dtdCommentBeans = new ArrayList<DTDCommentBean>();

	/**
	 * @return dtdFile
	 */
	public File getDtdFile() {

		return dtdFile;
	}

	/**
	 * @param dtdFile
	 *            セットする dtdFile
	 */
	public void setDtdFile(File dtdFile) {

		this.dtdFile = dtdFile;
	}

	/**
	 * @return dtdElementBeans
	 */
	public List<DTDElementBean> getDtdElementBeans() {

		return dtdElementBeans;
	}

	/**
	 * @param dtdElementBeans
	 *            セットする dtdElementBeans
	 */
	public void setDtdElementBeans(List<DTDElementBean> dtdElementBeans) {

		this.dtdElementBeans = dtdElementBeans;
	}

	/**
	 * @return dtdAttlistBeans
	 */
	public List<DTDAttlistBean> getDtdAttlistBeans() {

		return dtdAttlistBeans;
	}

	/**
	 * @param dtdAttlistBeans
	 *            セットする dtdAttlistBeans
	 */
	public void setDtdAttlistBeans(List<DTDAttlistBean> dtdAttlistBeans) {

		this.dtdAttlistBeans = dtdAttlistBeans;
	}

	/**
	 * @return dtdEntityBeans
	 */
	public List<DTDEntityBean> getDtdEntityBeans() {

		return dtdEntityBeans;
	}

	/**
	 * @param dtdEntityBeans
	 *            セットする dtdEntityBeans
	 */
	public void setDtdEntityBeans(List<DTDEntityBean> dtdEntityBeans) {

		this.dtdEntityBeans = dtdEntityBeans;
	}

	/**
	 * @return dtdEmptyBeans
	 */
	public List<DTDEmptyBean> getDtdEmptyBeans() {

		return dtdEmptyBeans;
	}

	/**
	 * @param dtdEmptyBeans
	 *            セットする dtdEmptyBeans
	 */
	public void setDtdEmptyBeans(List<DTDEmptyBean> dtdEmptyBeans) {

		this.dtdEmptyBeans = dtdEmptyBeans;
	}

	/**
	 * @return dtdNotationBeans
	 */
	public List<DTDNotationBean> getDtdNotationBeans() {

		return dtdNotationBeans;
	}

	/**
	 * @param dtdNotationBeans
	 *            セットする dtdNotationBeans
	 */
	public void setDtdNotationBeans(List<DTDNotationBean> dtdNotationBeans) {

		this.dtdNotationBeans = dtdNotationBeans;
	}

	/**
	 * @return dtdCommentBeans
	 */
	public List<DTDCommentBean> getDtdCommentBeans() {

		return dtdCommentBeans;
	}

	/**
	 * @param dtdCommentBeans
	 *            セットする dtdCommentBeans
	 */
	public void setDtdCommentBeans(List<DTDCommentBean> dtdCommentBeans) {

		this.dtdCommentBeans = dtdCommentBeans;
	}

}
