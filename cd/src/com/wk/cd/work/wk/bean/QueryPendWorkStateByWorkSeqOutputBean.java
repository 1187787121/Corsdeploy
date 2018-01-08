/**
 * Title: QueryPendWorkDetailByWorkSeqOutputBean.java
 * File Description:根据流水号查询任务状态接口 
 * @copyright: 2014
 * @company: CORSWORK
 * @author: tlw
 * @version: 1.0
 * @date: 2014-12-4
 */
package com.wk.cd.work.wk.bean;

import com.wk.cd.bean.ActionRootOutputBean;
import com.wk.cd.enu.WORKFLOW_STATE;
import com.wk.util.JaDate;
import com.wk.util.JaTime;

/**
 * Class Description:根据流水号查询任务状态接口
 * @author tlw
 */
public class QueryPendWorkStateByWorkSeqOutputBean
		extends ActionRootOutputBean {
	private static final long serialVersionUID = -4838371200700467049L;

	/**
	 *待处理任务流水号
	 */
	private String pend_wk_seq;

	public static final String PEND_WK_SEQ = "待处理任务流水号";

	/**
	 *提交任务流水号
	 */
	private String submit_work_seq;

	public static final String SUBMIT_WORK_SEQCN = "提交任务流水号";

	/**
	 *待处理任务编码
	 */
	private String pend_work_code;

	public static final String PEND_WORK_CODECN = "待处理任务编码";

	/**
	 *待处理服务名称
	 */
	private String pend_srv_name;

	public static final String PEND_SRV_NAMECN = "待处理服务名称";

	/**
	 *待处理资源编码
	 */
	private String pend_rs_code;

	public static final String PEND_RS_CODECN = "待处理资源编码";

	/**
	 *待处理数据源数组
	 */
	private String pend_ary_socname;

	public static final String PEND_ARY_SOCNAMECN = "待处理数据源数组";

	/**
	 *待处理任务说明
	 */
	private String pendwk_bk_expl;

	public static final String PENDWK_BK_EXPLCN = "待处理任务说明";
	
	/**
	 * 待处理序号
	 */
	private int pend_deal_seq;
	
	public static final String PEND_DEAL_SEQCN = "待处理序号";
	
	/**
	 * 待处理部门角色编码
	 */
	private String pend_dprl_code;
	
	public static final String PEND_DPRL_CODECN = "待处理部门角色编码";
	/**
	 *问题单编码
	 */
	private String pbl_code;

	public static final String PBL_CODECN = "问题单编码";

	/**
	 *创建用户
	 */
	private String crt_user_id;

	public static final String CRT_USER_IDCN = "创建用户";

	/**
	 *创建部门
	 */
	private String crt_dept_id;

	public static final String CRT_DEPT_IDCNCN = "创建部门";

	/**
	 *创建日期
	 */
	private JaDate crt_bk_date;

	public static final String CRT_BK_DATECNCN = "创建日期";

	/**
	 *创建时间
	 */
	private JaTime crt_bk_time;

	public static final String CRT_BK_TIMECNCN = "创建时间";

	/**
	 *任务状态
	 */
	private WORKFLOW_STATE workflow_state;

	public static final String WORKFLOW_STATECNCN = "任务状态";

	/**
	 * @return pend_work_seq 待处理任务流水号
	 */
	public String getPend_work_seq() {
		return pend_wk_seq;
	}

	/**
	 * @param pend_work_seq 待处理任务流水号
	 */
	public void setPend_work_seq(String pend_work_seq) {
		this.pend_wk_seq = pend_work_seq;
	}

	
	/**
	 * @return pend_deal_seq 待处理序号
	 */
	public int getPend_deal_seq() {
		return pend_deal_seq;
	}

	/**
	 * @param pend_deal_seq 待处理序号
	 */
	public void setPend_deal_seq(int pend_deal_seq) {
		this.pend_deal_seq = pend_deal_seq;
	}

	/**
	 * @return pend_dprl_code 待处理部门角色编码
	 */
	public String getPend_dprl_code() {
		return pend_dprl_code;
	}

	/**
	 * @param pend_dprl_code 待处理部门角色编码
	 */
	public void setPend_dprl_code(String pend_dprl_code) {
		this.pend_dprl_code = pend_dprl_code;
	}

	/**
	 * @return submit_work_seq 提交任务流水号
	 */
	public String getSubmit_work_seq() {
		return submit_work_seq;
	}

	/**
	 * @param submit_work_seq 提交任务流水号
	 */
	public void setSubmit_work_seq(String submit_work_seq) {
		this.submit_work_seq = submit_work_seq;
	}

	/**
	 * @return pend_work_code 待处理任务编码
	 */
	public String getPend_work_code() {
		return pend_work_code;
	}

	/**
	 * @param pend_work_code 待处理任务编码
	 */
	public void setPend_work_code(String pend_work_code) {
		this.pend_work_code = pend_work_code;
	}

	/**
	 * @return pend_srv_name 待处理服务名称
	 */
	public String getPend_srv_name() {
		return pend_srv_name;
	}

	/**
	 * @param pend_srv_name 待处理服务名称
	 */
	public void setPend_srv_name(String pend_srv_name) {
		this.pend_srv_name = pend_srv_name;
	}

	/**
	 * @return pend_rs_code 待处理资源编码
	 */
	public String getPend_rs_code() {
		return pend_rs_code;
	}

	/**
	 * @param pend_rs_code 待处理资源编码
	 */
	public void setPend_rs_code(String pend_rs_code) {
		this.pend_rs_code = pend_rs_code;
	}

	/**
	 * @return pend_ary_socname 待处理数据源数组
	 */
	public String getPend_ary_socname() {
		return pend_ary_socname;
	}

	/**
	 * @param pend_ary_socname 待处理数据源数组
	 */
	public void setPend_ary_socname(String pend_ary_socname) {
		this.pend_ary_socname = pend_ary_socname;
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
	 * @return pbl_code 问题单编码
	 */
	public String getPbl_code() {
		return pbl_code;
	}

	/**
	 * @param pbl_code 问题单编码
	 */
	public void setPbl_code(String pbl_code) {
		this.pbl_code = pbl_code;
	}

	/**
	 * @return crt_user_id 创建用户
	 */
	public String getCrt_user_id() {
		return crt_user_id;
	}

	/**
	 * @param crt_user_id 创建用户
	 */
	public void setCrt_user_id(String crt_user_id) {
		this.crt_user_id = crt_user_id;
	}

	/**
	 * @return crt_dept_id 创建部门
	 */
	public String getCrt_dept_id() {
		return crt_dept_id;
	}

	/**
	 * @param crt_dept_id 创建部门
	 */
	public void setCrt_dept_id(String crt_dept_id) {
		this.crt_dept_id = crt_dept_id;
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

}
