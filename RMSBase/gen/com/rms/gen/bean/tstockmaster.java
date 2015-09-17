package com.rms.gen.bean;

import java.math.BigDecimal;

public class tstockmaster {

	private BigDecimal goods_no;

	private BigDecimal stock_cnt;

	private String sale_kbn;

	private BigDecimal sellout_cnt;

	/**
	 * 
	 * @return
	 */
	public BigDecimal getgoods_no() {

		return this.goods_no;
	}

	/**
	 * 
	 * @param goods_no
	 * 
	 * @return
	 */
	public final void setgoods_no(BigDecimal goods_no) {

		this.goods_no = goods_no;
	}

	/**
	 * 
	 * @return
	 */
	public BigDecimal getstock_cnt() {

		return this.stock_cnt;
	}

	/**
	 * 
	 * @param stock_cnt
	 * 
	 * @return
	 */
	public final void setstock_cnt(BigDecimal stock_cnt) {

		this.stock_cnt = stock_cnt;
	}

	/**
	 * 
	 * @return
	 */
	public String getsale_kbn() {

		return this.sale_kbn;
	}

	/**
	 * 
	 * @param sale_kbn
	 * 
	 * @return
	 */
	public final void setsale_kbn(String sale_kbn) {

		this.sale_kbn = sale_kbn;
	}

	/**
	 * 
	 * @return
	 */
	public BigDecimal getsellout_cnt() {

		return this.sellout_cnt;
	}

	/**
	 * 
	 * @param sellout_cnt
	 * 
	 * @return
	 */
	public final void setsellout_cnt(BigDecimal sellout_cnt) {

		this.sellout_cnt = sellout_cnt;
	}

}
