/**
 * Title: WorkStateDetailBean.java
 * File Description: 任务状态详细
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2016年3月17日
 */
package com.wk.cd.work.wk.bean;

import com.wk.cd.enu.PEND_TYPE;
import com.wk.util.JaDate;
import com.wk.util.JaTime;

/**
 * Class Description: 任务状态详细
 * @author Xul
 */
public class WorkStateDetailBean {
	
	/**
	 *处理方式
	 */
	private PEND_TYPE pend_type;

	public static final String PEND_TYPECN = "处理方式";
	
	/**
	 *任务执行状态
	 */
	private int work_state;

	public static final String WORK_STATECN = "任务执行状态";
	
	/**
	 *处理人员
	 */
	private String deal_user_cn_name;

	public static final String DEAL_USER_CN_NAMECN = "处理人员";
	
	/**
	 *处理日期
	 */
	private JaDate deal_bk_date;

	public static final String DEAL_BK_DATECN = "处理日期";

	/**
	 *处理时间
	 */
	private JaTime deal_bk_time;

	public static final String DEAL_BK_TIMECN = "处理时间";
	
	/**
	 *处理说明
	 */
	private String deal_bk_desc;

	public static final String DEAL_BK_DESCCN = "处理说明";

	/**
	 * @return pend_type 处理方式
	 */
	public PEND_TYPE getPend_type() {
		return pend_type;
	}

	/**
	 * @param pend_type 处理方式
	 */
	public void setPend_type(PEND_TYPE pend_type) {
		this.pend_type = pend_type;
	}


	/**
	 * @return work_state 任务执行状态
	 */
	public int getWork_state() {
		return work_state;
	}

	/**
	 * @param work_state 任务执行状态
	 */
	public void setWork_state(int work_state) {
		this.work_state = work_state;
	}

	/**
	 * @return deal_user_cn_name 处理人员
	 */
	public String getDeal_user_cn_name() {
		return deal_user_cn_name;
	}

	/**
	 * @param deal_user_cn_name 处理人员
	 */
	public void setDeal_user_cn_name(String deal_user_cn_name) {
		this.deal_user_cn_name = deal_user_cn_name;
	}

	/**
	 *@return deal_bk_date 处理日期
	 */
	public JaDate getDeal_bk_date() {
		return this.deal_bk_date;
	}

	/**
	 *@param deal_bk_date 处理日期
	 */
	public void setDeal_bk_date(JaDate deal_bk_date) {
		this.deal_bk_date = deal_bk_date;
	}

	/**
	 *@return deal_bk_time 处理时间
	 */
	public JaTime getDeal_bk_time() {
		return this.deal_bk_time;
	}

	/**
	 *@param deal_bk_time 处理时间
	 */
	public void setDeal_bk_time(JaTime deal_bk_time) {
		this.deal_bk_time = deal_bk_time;
	}

	/**
	 * @return deal_bk_desc 处理说明
	 */
	public String getDeal_bk_desc() {
		return deal_bk_desc;
	}

	/**
	 * @param deal_bk_desc 处理说明
	 */
	public void setDeal_bk_desc(String deal_bk_desc) {
		this.deal_bk_desc = deal_bk_desc;
	}
	
}
