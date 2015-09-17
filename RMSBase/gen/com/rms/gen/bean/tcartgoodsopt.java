package com.rms.gen.bean;

import java.math.BigDecimal;

public class tcartgoodsopt {

	private String cart_no;

	private BigDecimal cart_seq;

	private BigDecimal opt_itm_cd;

	private BigDecimal opt_seq;

	private String opt_itm_nm;

	private String opt_itm_val;

	/**
	 * 
	 * @return
	 */
	public String getcart_no() {

		return this.cart_no;
	}

	/**
	 * 
	 * @param cart_no
	 * 
	 * @return
	 */
	public final void setcart_no(String cart_no) {

		this.cart_no = cart_no;
	}

	/**
	 * 
	 * @return
	 */
	public BigDecimal getcart_seq() {

		return this.cart_seq;
	}

	/**
	 * 
	 * @param cart_seq
	 * 
	 * @return
	 */
	public final void setcart_seq(BigDecimal cart_seq) {

		this.cart_seq = cart_seq;
	}

	/**
	 * 
	 * @return
	 */
	public BigDecimal getopt_itm_cd() {

		return this.opt_itm_cd;
	}

	/**
	 * 
	 * @param opt_itm_cd
	 * 
	 * @return
	 */
	public final void setopt_itm_cd(BigDecimal opt_itm_cd) {

		this.opt_itm_cd = opt_itm_cd;
	}

	/**
	 * 
	 * @return
	 */
	public BigDecimal getopt_seq() {

		return this.opt_seq;
	}

	/**
	 * 
	 * @param opt_seq
	 * 
	 * @return
	 */
	public final void setopt_seq(BigDecimal opt_seq) {

		this.opt_seq = opt_seq;
	}

	/**
	 * 
	 * @return
	 */
	public String getopt_itm_nm() {

		return this.opt_itm_nm;
	}

	/**
	 * 
	 * @param opt_itm_nm
	 * 
	 * @return
	 */
	public final void setopt_itm_nm(String opt_itm_nm) {

		this.opt_itm_nm = opt_itm_nm;
	}

	/**
	 * 
	 * @return
	 */
	public String getopt_itm_val() {

		return this.opt_itm_val;
	}

	/**
	 * 
	 * @param opt_itm_val
	 * 
	 * @return
	 */
	public final void setopt_itm_val(String opt_itm_val) {

		this.opt_itm_val = opt_itm_val;
	}

}
