package com.rms.gen.bean;

import java.math.BigDecimal;

public class tcampaigngoods {

	private BigDecimal camp_no;

	private BigDecimal goods_no;

	private String reg_dm;

	private String user_id;

	/**
	 * 
	 * @return
	 */
	public BigDecimal getcamp_no() {

		return this.camp_no;
	}

	/**
	 * 
	 * @param camp_no
	 * 
	 * @return
	 */
	public final void setcamp_no(BigDecimal camp_no) {

		this.camp_no = camp_no;
	}

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
	public String getreg_dm() {

		return this.reg_dm;
	}

	/**
	 * 
	 * @param reg_dm
	 * 
	 * @return
	 */
	public final void setreg_dm(String reg_dm) {

		this.reg_dm = reg_dm;
	}

	/**
	 * 
	 * @return
	 */
	public String getuser_id() {

		return this.user_id;
	}

	/**
	 * 
	 * @param user_id
	 * 
	 * @return
	 */
	public final void setuser_id(String user_id) {

		this.user_id = user_id;
	}

}
