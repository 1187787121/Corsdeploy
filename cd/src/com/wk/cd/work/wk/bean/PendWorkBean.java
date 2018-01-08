/**
 * Title: PendWorkBean.java
 * File Description: 复核或授权的任务
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2016年3月8日
 */
package com.wk.cd.work.wk.bean;

import java.util.List;

import com.wk.cd.enu.WORKFLOW_STATE;
import com.wk.util.JaDate;
import com.wk.util.JaTime;

/**
 * Class Description: 复核或授权的任务
 * @author Xul
 */
public class PendWorkBean {
	
	/**
	 *待处理任务流水号
	 */
	private String pend_work_seq;

	public static final String PEND_WORK_SEQCN = "待处理任务流水号";
	
	/**
	 *待处理任务说明
	 */
	private String pendwk_bk_expl;

	public static final String PENDWK_BK_EXPLCN = "待处理任务说明";
	
	/**
	 *任务状态
	 */
	private WORKFLOW_STATE workflow_state;

	public static final String WORKFLOW_STATECN = "任务状态";
	
	/**
	 *创建用户
	 */
	private String crt_cn_name;

	public static final String CRT_CN_NAMECN = "创建用户";
	
	/**
	 *创建日期
	 */
	private JaDate crt_bk_date;

	public static final String CRT_BK_DATECN = "创建日期";

	/**
	 *创建时间
	 */
	private JaTime crt_bk_time;

	public static final String CRT_BK_TIMECN = "创建时间";
	
	/**
	 * 任务状态详细列表
	 */
	private List<WorkStateDetailBean> work_detail_list;
	
	public static final String WORK_DETAIL_LISTCN = "任务状态详细列表";

	/**
	 * @return pend_work_seq 待处理任务流水号
	 */
	public String getPend_work_seq() {
		return pend_work_seq;
	}

	/**
	 * @param pend_work_seq 待处理任务流水号
	 */
	public void setPend_work_seq(String pend_work_seq) {
		this.pend_work_seq = pend_work_seq;
	}

	/**
	 * @return pendwk_bk_expl 待处理任务说明
	 */
	public String getPendwk_bk_expl() {
		return pendwk_bk_expl;
	}

	/**
	 * @param pendwk_bk_expl 待处理任务说明
	 */
	public void setPendwk_bk_expl(String pendwk_bk_expl) {
		this.pendwk_bk_expl = pendwk_bk_expl;
	}

	/**
	 * @return workflow_state 任务状态
	 */
	public WORKFLOW_STATE getWorkflow_state() {
		return workflow_state;
	}

	/**
	 * @param workflow_state 任务状态
	 */
	public void setWorkflow_state(WORKFLOW_STATE workflow_state) {
		this.workflow_state = workflow_state;
	}

	/**
	 * @return crt_cn_name 创建用户
	 */
	public String getCrt_cn_name() {
		return crt_cn_name;
	}

	/**
	 * @param crt_cn_name 创建用户
	 */
	public void setCrt_cn_name(String crt_cn_name) {
		this.crt_cn_name = crt_cn_name;
	}

	/**
	 * @return crt_bk_date 创建日期
	 */
	public JaDate getCrt_bk_date() {
		return crt_bk_date;
	}

	/**
	 * @param crt_bk_date 创建日期
	 */
	public void setCrt_bk_date(JaDate crt_bk_date) {
		this.crt_bk_date = crt_bk_date;
	}

	/**
	 * @return crt_bk_time 创建时间
	 */
	public JaTime getCrt_bk_time() {
		return crt_bk_time;
	}

	/**
	 * @param crt_bk_time 创建时间
	 */
	public void setCrt_bk_time(JaTime crt_bk_time) {
		this.crt_bk_time = crt_bk_time;
	}

	/**
	 * @return work_detail_list 任务状态详细列表
	 */
	public List<WorkStateDetailBean> getWork_detail_list() {
		return work_detail_list;
	}

	/**
	 * @param work_detail_list 任务状态详细列表
	 */
	public void setWork_detail_list(List<WorkStateDetailBean> work_detail_list) {
		this.work_detail_list = work_detail_list;
	}
}
