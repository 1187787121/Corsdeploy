/**
 * Title: LgLogMfInfo.java
 * File Description: 任务日志流水表
 * @copyright: 2015
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2015-4-28
 */

package com.wk.cd.system.lg.info;

import java.io.Serializable;

import com.wk.util.*;
import com.wk.cd.enu.YN_FLAG;
import com.wk.db.PrimaryKey;
import com.wk.db.Table;
/**
 * Class description:任务日志流水表
 * @author AutoGen
 */
@Table("LG_LOG_MF")
@PrimaryKey({"work_seq"})
public class LgLogMfInfo implements Serializable{
	private static final long serialVersionUID = 1L;

	/**
	 *表名称
	 */
	public static final String TABLECN = "任务日志流水表";

	/**
	 *任务流水号
	 */
	private String work_seq;

	public static final String WORK_SEQCN = "任务流水号";

	/**
	 *发起渠道
	 */
	private String org_channel_code;

	public static final String ORG_CHANNEL_CODECN = "发起渠道";

	/**
	 *发起终端
	 */
	private String org_term_no;

	public static final String ORG_TERM_NOCN = "发起终端";

	/**
	 *发起任务编码
	 */
	private String org_work_code;

	public static final String ORG_WORK_CODECN = "发起任务编码";

	/**
	 *发起服务名
	 */
	private String org_srv_name;

	public static final String ORG_SRV_NAMECN = "发起服务名";

	/**
	 *发起服务描述
	 */
	private String org_srv_bk_desc;

	public static final String ORG_SRV_BK_DESCCN = "发起服务描述";

	/**
	 *发起资源编码
	 */
	private String org_rs_code;

	public static final String ORG_RS_CODECN = "发起资源编码";

	/**
	 *发起数据源数组
	 */
	private String org_ary_socname;

	public static final String ORG_ARY_SOCNAMECN = "发起数据源数组";

	/**
	 *待处理流水号
	 */
	private String pend_work_seq;

	public static final String PEND_WORK_SEQCN = "待处理流水号";

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
	 *问题单编码
	 */
	private String pbl_code;

	public static final String PBL_CODECN = "问题单编码";

	/**
	 *成功标准
	 */
	private YN_FLAG sr_yn_flag;

	public static final String SR_YN_FLAGCN = "成功标准";

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
	 *日志内容
	 */
	private String log_txt;

	public static final String LOG_TXTCN = "日志内容";

	/**
	 *日志级别
	 */
	private int log_level;

	public static final String LOG_LEVELCN = "日志级别";

	/**
	 *备用
	 */
	private String backup_fld;

	public static final String BACKUP_FLDCN = "备用";

	/**
	 *@return work_seq 任务流水号
	 */
	public String getWork_seq() {
		return this.work_seq;
	}

	/**
	 *@param work_seq 任务流水号
	 */
	public void setWork_seq(String work_seq) {
		this.work_seq = work_seq;
	}

	/**
	 *@return org_channel_code 发起渠道
	 */
	public String getOrg_channel_code() {
		return this.org_channel_code;
	}

	/**
	 *@param org_channel_code 发起渠道
	 */
	public void setOrg_channel_code(String org_channel_code) {
		this.org_channel_code = org_channel_code;
	}

	/**
	 *@return org_term_no 发起终端
	 */
	public String getOrg_term_no() {
		return this.org_term_no;
	}

	/**
	 *@param org_term_no 发起终端
	 */
	public void setOrg_term_no(String org_term_no) {
		this.org_term_no = org_term_no;
	}

	/**
	 *@return org_work_code 发起任务编码
	 */
	public String getOrg_work_code() {
		return this.org_work_code;
	}

	/**
	 *@param org_work_code 发起任务编码
	 */
	public void setOrg_work_code(String org_work_code) {
		this.org_work_code = org_work_code;
	}

	/**
	 *@return org_srv_name 发起服务名
	 */
	public String getOrg_srv_name() {
		return this.org_srv_name;
	}

	/**
	 *@param org_srv_name 发起服务名
	 */
	public void setOrg_srv_name(String org_srv_name) {
		this.org_srv_name = org_srv_name;
	}

	/**
	 *@return org_srv_bk_desc 发起服务描述
	 */
	public String getOrg_srv_bk_desc() {
		return this.org_srv_bk_desc;
	}

	/**
	 *@param org_srv_bk_desc 发起服务描述
	 */
	public void setOrg_srv_bk_desc(String org_srv_bk_desc) {
		this.org_srv_bk_desc = org_srv_bk_desc;
	}

	/**
	 *@return org_rs_code 发起资源编码
	 */
	public String getOrg_rs_code() {
		return this.org_rs_code;
	}

	/**
	 *@param org_rs_code 发起资源编码
	 */
	public void setOrg_rs_code(String org_rs_code) {
		this.org_rs_code = org_rs_code;
	}

	/**
	 *@return org_ary_socname 发起数据源数组
	 */
	public String getOrg_ary_socname() {
		return this.org_ary_socname;
	}

	/**
	 *@param org_ary_socname 发起数据源数组
	 */
	public void setOrg_ary_socname(String org_ary_socname) {
		this.org_ary_socname = org_ary_socname;
	}

	/**
	 *@return pend_work_seq 待处理流水号
	 */
	public String getPend_work_seq() {
		return this.pend_work_seq;
	}

	/**
	 *@param pend_work_seq 待处理流水号
	 */
	public void setPend_work_seq(String pend_work_seq) {
		this.pend_work_seq = pend_work_seq;
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
	 *@return sr_yn_flag 成功标准
	 */
	public YN_FLAG getSr_yn_flag() {
		return this.sr_yn_flag;
	}

	/**
	 *@param sr_yn_flag 成功标准
	 */
	public void setSr_yn_flag(YN_FLAG sr_yn_flag) {
		this.sr_yn_flag = sr_yn_flag;
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
	 *@return log_txt 日志内容
	 */
	public String getLog_txt() {
		return this.log_txt;
	}

	/**
	 *@param log_txt 日志内容
	 */
	public void setLog_txt(String log_txt) {
		this.log_txt = log_txt;
	}

	/**
	 *@return log_level 日志级别
	 */
	public int getLog_level() {
		return this.log_level;
	}

	/**
	 *@param log_level 日志级别
	 */
	public void setLog_level(int log_level) {
		this.log_level = log_level;
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

}
