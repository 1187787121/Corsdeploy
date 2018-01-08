/**
 * Title: WkDealDetailBean.java
 * File Description: 
 * @copyright: 2015
 * @company: CORSWORK
 * @author: tlw
 * @version: 1.0
 * @date: 2015-2-10
 */
package com.wk.cd.work.wk.bean;

import com.wk.cd.enu.DEAL_RES;
import com.wk.cd.enu.DEAL_TYPE;
import com.wk.util.JaDate;
import com.wk.util.JaTime;

/**
 * Class Description:
 * @author tlw
 */
public class WkDealDetailBean {
	/**
	 *待处理任务流水号
	 */
	private String pend_work_seq;

	public static final String PEND_WORK_SEQCN = "待处理任务流水号";

	/**
	 *处理序号
	 */
	private int deal_seq;

	public static final String DEAL_SEQCN = "处理序号";

	/**
	 *处理方式
	 */
	private DEAL_TYPE deal_type;

	public static final String DEAL_TYPECN = "处理方式";

	/**
	 *处理结果
	 */
	private DEAL_RES deal_res;

	public static final String DEAL_RESCN = "处理结果";

	/**
	 *处理人员
	 */
	private String deal_user_id;

	public static final String DEAL_USER_IDCN = "处理人员";

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
	 * 处理人员姓名
	 */
	private String user_cn_name;

	public static final String USER_CN_NAMECN = "处理人员姓名";

	/**
	 *@return pend_work_seq 待处理任务流水号
	 */
	public String getPend_work_seq() {
		return this.pend_work_seq;
	}

	/**
	 *@param pend_work_seq 待处理任务流水号
	 */
	public void setPend_work_seq(String pend_work_seq) {
		this.pend_work_seq = pend_work_seq;
	}

	/**
	 *@return deal_seq 处理序号
	 */
	public int getDeal_seq() {
		return this.deal_seq;
	}

	/**
	 *@param deal_seq 处理序号
	 */
	public void setDeal_seq(int deal_seq) {
		this.deal_seq = deal_seq;
	}

	/**
	 *@return deal_type 处理方式
	 */
	public DEAL_TYPE getDeal_type() {
		return this.deal_type;
	}

	/**
	 *@param deal_type 处理方式
	 */
	public void setDeal_type(DEAL_TYPE deal_type) {
		this.deal_type = deal_type;
	}

	/**
	 *@return deal_res 处理结果
	 */
	public DEAL_RES getDeal_res() {
		return this.deal_res;
	}

	/**
	 *@param deal_res 处理结果
	 */
	public void setDeal_res(DEAL_RES deal_res) {
		this.deal_res = deal_res;
	}

	/**
	 *@return deal_user_id 处理人员
	 */
	public String getDeal_user_id() {
		return this.deal_user_id;
	}

	/**
	 *@param deal_user_id 处理人员
	 */
	public void setDeal_user_id(String deal_user_id) {
		this.deal_user_id = deal_user_id;
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
	 *@return deal_bk_desc 处理说明
	 */
	public String getDeal_bk_desc() {
		return this.deal_bk_desc;
	}

	/**
	 *@param deal_bk_desc 处理说明
	 */
	public void setDeal_bk_desc(String deal_bk_desc) {
		this.deal_bk_desc = deal_bk_desc;
	}

	/**
	 * @return user_cn_name 处理人员姓名
	 */
	public String getUser_cn_name() {
		return this.user_cn_name;
	}

	/**
	 * @param user_cn_name 处理人员姓名
	 */
	public void setUser_cn_name(String user_cn_name) {
		this.user_cn_name = user_cn_name;
	}

}
