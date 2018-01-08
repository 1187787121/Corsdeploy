/**
 * Title: WkDealStateInfo.java
 * File Description: 任务处理状态表
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-3-15
 */

package com.wk.cd.work.wk.info;

import java.io.Serializable;

import com.wk.util.*;
import com.wk.cd.enu.RCD_STATE;
import com.wk.cd.enu.WORKFLOW_STATE;
import com.wk.db.PrimaryKey;
import com.wk.db.Table;
/**
 * Class description:任务处理状态表
 * @author AutoGen
 */
@Table("WK_DEAL_STATE")
@PrimaryKey({"pend_work_seq"})
public class WkDealStateInfo implements Serializable  {
	private static final long serialVersionUID = 1L;

	/**
	 *表名称
	 */
	public static final String TABLECN = "任务处理状态表";

	/**
	 *待处理任务流水号
	 */
	private String pend_work_seq;

	public static final String PEND_WORK_SEQCN = "待处理任务流水号";

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
	 *待处理序号
	 */
	private int pend_deal_seq;

	public static final String PEND_DEAL_SEQCN = "待处理序号";

	/**
	 *待处理用户
	 */
	private String pend_user_id;

	public static final String PEND_USER_IDCN = "待处理用户";

	/**
	 *待处理用户中文名
	 */
	private String pend_user_cn_name;

	public static final String PEND_USER_CN_NAMECN = "待处理用户中文名";

	/**
	 *问题单编码
	 */
	private String pbl_code;

	public static final String PBL_CODECN = "问题单编码";

	/**
	 *代理用户
	 */
	private String proxy_user_id;

	public static final String PROXY_USER_IDCN = "代理用户";

	/**
	 *创建用户
	 */
	private String crt_user_id;

	public static final String CRT_USER_IDCN = "创建用户";

	/**
	 *用户中文名
	 */
	private String crt_user_cn_name;

	public static final String CRT_USER_CN_NAMECN = "用户中文名";

	/**
	 *创建部门
	 */
	private String crt_dept_id;

	public static final String CRT_DEPT_IDCN = "创建部门";

	/**
	 *部门中文名
	 */
	private String crt_dept_cn_name;

	public static final String CRT_DEPT_CN_NAMECN = "部门中文名";

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
	 *任务状态
	 */
	private WORKFLOW_STATE workflow_state;

	public static final String WORKFLOW_STATECN = "任务状态";

	/**
	 *备用
	 */
	private String backup_fld;

	public static final String BACKUP_FLDCN = "备用";

	/**
	 *记录状态
	 */
	private RCD_STATE rcd_state;

	public static final String RCD_STATECN = "记录状态";

	/**
	 *任务申请说明
	 */
	private String apply_bk_expl;

	public static final String APPLY_BK_EXPLCN = "任务申请说明";

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
	 *@return submit_work_seq 提交任务流水号
	 */
	public String getSubmit_work_seq() {
		return this.submit_work_seq;
	}

	/**
	 *@param submit_work_seq 提交任务流水号
	 */
	public void setSubmit_work_seq(String submit_work_seq) {
		this.submit_work_seq = submit_work_seq;
	}

	/**
	 *@return pend_work_code 待处理任务编码
	 */
	public String getPend_work_code() {
		return this.pend_work_code;
	}

	/**
	 *@param pend_work_code 待处理任务编码
	 */
	public void setPend_work_code(String pend_work_code) {
		this.pend_work_code = pend_work_code;
	}

	/**
	 *@return pend_srv_name 待处理服务名称
	 */
	public String getPend_srv_name() {
		return this.pend_srv_name;
	}

	/**
	 *@param pend_srv_name 待处理服务名称
	 */
	public void setPend_srv_name(String pend_srv_name) {
		this.pend_srv_name = pend_srv_name;
	}

	/**
	 *@return pend_rs_code 待处理资源编码
	 */
	public String getPend_rs_code() {
		return this.pend_rs_code;
	}

	/**
	 *@param pend_rs_code 待处理资源编码
	 */
	public void setPend_rs_code(String pend_rs_code) {
		this.pend_rs_code = pend_rs_code;
	}

	/**
	 *@return pend_ary_socname 待处理数据源数组
	 */
	public String getPend_ary_socname() {
		return this.pend_ary_socname;
	}

	/**
	 *@param pend_ary_socname 待处理数据源数组
	 */
	public void setPend_ary_socname(String pend_ary_socname) {
		this.pend_ary_socname = pend_ary_socname;
	}

	/**
	 *@return pendwk_bk_expl 待处理任务说明
	 */
	public String getPendwk_bk_expl() {
		return this.pendwk_bk_expl;
	}

	/**
	 *@param pendwk_bk_expl 待处理任务说明
	 */
	public void setPendwk_bk_expl(String pendwk_bk_expl) {
		this.pendwk_bk_expl = pendwk_bk_expl;
	}

	/**
	 *@return pend_deal_seq 待处理序号
	 */
	public int getPend_deal_seq() {
		return this.pend_deal_seq;
	}

	/**
	 *@param pend_deal_seq 待处理序号
	 */
	public void setPend_deal_seq(int pend_deal_seq) {
		this.pend_deal_seq = pend_deal_seq;
	}

	/**
	 *@return pend_user_id 待处理用户
	 */
	public String getPend_user_id() {
		return this.pend_user_id;
	}

	/**
	 *@param pend_user_id 待处理用户
	 */
	public void setPend_user_id(String pend_user_id) {
		this.pend_user_id = pend_user_id;
	}

	/**
	 *@return pend_user_cn_name 待处理用户中文名
	 */
	public String getPend_user_cn_name() {
		return this.pend_user_cn_name;
	}

	/**
	 *@param pend_user_cn_name 待处理用户中文名
	 */
	public void setPend_user_cn_name(String pend_user_cn_name) {
		this.pend_user_cn_name = pend_user_cn_name;
	}

	/**
	 *@return pbl_code 问题单编码
	 */
	public String getPbl_code() {
		return this.pbl_code;
	}

	/**
	 *@param pbl_code 问题单编码
	 */
	public void setPbl_code(String pbl_code) {
		this.pbl_code = pbl_code;
	}

	/**
	 *@return proxy_user_id 代理用户
	 */
	public String getProxy_user_id() {
		return this.proxy_user_id;
	}

	/**
	 *@param proxy_user_id 代理用户
	 */
	public void setProxy_user_id(String proxy_user_id) {
		this.proxy_user_id = proxy_user_id;
	}

	/**
	 *@return crt_user_id 创建用户
	 */
	public String getCrt_user_id() {
		return this.crt_user_id;
	}

	/**
	 *@param crt_user_id 创建用户
	 */
	public void setCrt_user_id(String crt_user_id) {
		this.crt_user_id = crt_user_id;
	}

	/**
	 *@return crt_user_cn_name 用户中文名
	 */
	public String getCrt_user_cn_name() {
		return this.crt_user_cn_name;
	}

	/**
	 *@param crt_user_cn_name 用户中文名
	 */
	public void setCrt_user_cn_name(String crt_user_cn_name) {
		this.crt_user_cn_name = crt_user_cn_name;
	}

	/**
	 *@return crt_dept_id 创建部门
	 */
	public String getCrt_dept_id() {
		return this.crt_dept_id;
	}

	/**
	 *@param crt_dept_id 创建部门
	 */
	public void setCrt_dept_id(String crt_dept_id) {
		this.crt_dept_id = crt_dept_id;
	}

	/**
	 *@return crt_dept_cn_name 部门中文名
	 */
	public String getCrt_dept_cn_name() {
		return this.crt_dept_cn_name;
	}

	/**
	 *@param crt_dept_cn_name 部门中文名
	 */
	public void setCrt_dept_cn_name(String crt_dept_cn_name) {
		this.crt_dept_cn_name = crt_dept_cn_name;
	}

	/**
	 *@return crt_bk_date 创建日期
	 */
	public JaDate getCrt_bk_date() {
		return this.crt_bk_date;
	}

	/**
	 *@param crt_bk_date 创建日期
	 */
	public void setCrt_bk_date(JaDate crt_bk_date) {
		this.crt_bk_date = crt_bk_date;
	}

	/**
	 *@return crt_bk_time 创建时间
	 */
	public JaTime getCrt_bk_time() {
		return this.crt_bk_time;
	}

	/**
	 *@param crt_bk_time 创建时间
	 */
	public void setCrt_bk_time(JaTime crt_bk_time) {
		this.crt_bk_time = crt_bk_time;
	}

	/**
	 *@return workflow_state 任务状态
	 */
	public WORKFLOW_STATE getWorkflow_state() {
		return this.workflow_state;
	}

	/**
	 *@param workflow_state 任务状态
	 */
	public void setWorkflow_state(WORKFLOW_STATE workflow_state) {
		this.workflow_state = workflow_state;
	}

	/**
	 *@return backup_fld 备用
	 */
	public String getBackup_fld() {
		return this.backup_fld;
	}

	/**
	 *@param backup_fld 备用
	 */
	public void setBackup_fld(String backup_fld) {
		this.backup_fld = backup_fld;
	}

	/**
	 *@return rcd_state 记录状态
	 */
	public RCD_STATE getRcd_state() {
		return this.rcd_state;
	}

	/**
	 *@param rcd_state 记录状态
	 */
	public void setRcd_state(RCD_STATE rcd_state) {
		this.rcd_state = rcd_state;
	}

	/**
	 *@return apply_bk_expl 任务申请说明
	 */
	public String getApply_bk_expl() {
		return this.apply_bk_expl;
	}

	/**
	 *@param apply_bk_expl 任务申请说明
	 */
	public void setApply_bk_expl(String apply_bk_expl) {
		this.apply_bk_expl = apply_bk_expl;
	}

}
