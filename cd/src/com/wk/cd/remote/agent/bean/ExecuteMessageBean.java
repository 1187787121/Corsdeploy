/**
 * Title: ExecuteMessageBean.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: "Zhangj"
 * @version: 1.0
 * @date: 2016年8月15日
 */
package com.wk.cd.remote.agent.bean;

import java.util.List;

import com.wk.cd.enu.SQL_STATE;
import com.wk.cd.enu.SQL_TYPE;
import com.wk.cd.enu.YN_FLAG;
import com.wk.cd.remote.jc.bean.JDBCResultBean;
import com.wk.sdo.ServiceData;

/**
 * Class Description:
 * @author "Zhangj"
 */
public class ExecuteMessageBean {
	
	/**
	 *SQL流水号
	 */
	private String sql_work_seq;

	public static final String SQL_WORK_SEQCN = "SQL流水号";
	
	/**
	 * 成功标志
	 */
	private boolean success_flag;
	
	public static final String SUCCESS_FLAGCN = "成功标志";
	
	/**
	 * 错误信息
	 */
	private String error_message;
	
	public static final String ERROR_MESSAGECN = "错误信息";
	
	/**
	 * SQL类别
	 */
	private SQL_TYPE sql_type ;
	
	public static final String SQL_TYPECN = "SQL类别";
	
	/**
	 * SQL状态
	 */
	private SQL_STATE sql_state;
	
	public static final String SQL_STATECN = "SQL状态";
	
	/**
	 * 执行耗时
	 */
	private int act_exec_time;
	
	public static final String ACT_EXEC_TIMECN = "执行耗时";
	
	/**
	 * 影响条数
	 */
	private Integer act_bk_num;

	public static final String ACT_BK_NUM = "影响条数";
	
	/**
	 * 只读标志
	 */
	private boolean read_only;
	
	public static final String READ_ONLYCN= "只读标志";
	
	/**
	 * 能否新增标志
	 */
	private boolean can_insert;
	
	public static final String CAN_INSERTCN = "能否新增标志";
	
	/**
	 * 查询表表头
	 */
	private List<SelectTitleHeadBean> theads;
	
	public static final String THEADSCN = "查询表表头";
	
	/**
	 * select查询结果集
	 */
	private List<ServiceData> tbodys;
	
	public static final String TBODYSCN = "select查询结果集";
	
	/**
	 * modify执行结果
	 */
	private List<JDBCResultBean> modify_list;
	
	public static final String MODIFY_LISTCN = "执行结果";
	
	/**
	 *系统中文名
	 */
	private String sys_cn_name;

	public static final String SYS_CN_NAMECN = "系统中文名";
	
	/**
	 *SQL语句
	 */
	private String sql_text;

	public static final String SQL_TEXTCN = "SQL语句";
	
	/**
	 * 权限标志
	 */
	private YN_FLAG priv_yn_flag;
	
	public static final String PRIV_YN_FLAGCN = "权限标志";
	
	/**
	 * 行数
	 */
	private int count;
	
	public static final String COUNTCN = "行数";
	
	private int total_page;
	
	public static final String TOTAL_PAGECN = "总页数";
	
	/**
	 * @return Sql流水号
	 */
	public String getSql_work_seq() {
		return this.sql_work_seq;
	}

	/**
	 * @param 设置sql流水号
	 */
	public void setSql_work_seq(String sql_work_seq) {
		this.sql_work_seq = sql_work_seq;
	}

	/**
	 * @return theads 查询表表头
	 */
	public List<SelectTitleHeadBean> getTheads() {
		return this.theads;
	}

	/*
	 * @param theads 查询表表头
	 */
	public void setTheads(List<SelectTitleHeadBean> theads) {
		this.theads = theads;
	}

	/**
	 * @return tbodys select查询结果集
	 */
	public List<ServiceData> getTbodys() {
		return this.tbodys;
	}

	/** 
	 * @param tbodys select查询结果集
	 */
	public void setTbodys(List<ServiceData> tbodys) {
		this.tbodys = tbodys;
	}

	/**
	 * @return success_flag 成功标志
	 */
	public boolean isSuccess_flag() {
		return this.success_flag;
	}

	/**
	 * @param success_flag 成功标志
	 */
	public void setSuccess_flag(boolean success_flag) {
		this.success_flag = success_flag;
	}

	/**
	 * @return error_message 错误信息
	 */
	public String getError_message() {
		return this.error_message;
	}

	/**
	 * @param error_message 错误信息
	 */
	public void setError_message(String error_message) {
		this.error_message = error_message;
	}

	/**
	 * @return sql_type SQL类别
	 */
	public SQL_TYPE getSql_type() {
		return this.sql_type;
	}

	/**
	 * @param sql_type SQL类别
	 */
	public void setSql_type(SQL_TYPE sql_type) {
		this.sql_type = sql_type;
	}
	
	/** 
	 * @return sql_state SQL状态
	 */
	public SQL_STATE getSql_state() {
		return this.sql_state;
	}

	/**
	 * @param sql_state SQL状态
	 */
	public void setSql_state(SQL_STATE sql_state) {
		this.sql_state = sql_state;
	}
	/**
	 * @return effect_line 影响条数
	 */
	public Integer getAct_bk_num() {
		return this.act_bk_num;
	}

	/** 
	 * @param effect_line 影响条数
	 */
	public void setAct_bk_num(Integer act_bk_num) {
		this.act_bk_num = act_bk_num;
	}
	/**
	 * @return read_only 只读标志
	 */
	public boolean isRead_only() {
		return read_only;
	}

	/**
	 * @param read_only  只读标志
	 */
	public void setRead_only(boolean read_only) {
		this.read_only = read_only;
	}

	/**
	 * @return can_insert 能否新增标志
	 */
	public boolean isCan_insert() {
		return can_insert;
	}

	/**
	 * @param can_insert 能否新增标志
	 */
	public void setCan_insert(boolean can_insert) {
		this.can_insert = can_insert;
	}

	/**
	 * @return time_used 执行耗时
	 */
	public int getAct_exec_time() {
		return this.act_exec_time;
	}
	/**
	 * 
	 * @param time_used 执行耗时 单位毫秒
	 */
	public void setAct_exec_time(int act_exec_time) {
		this.act_exec_time = act_exec_time;
	}

	/**
	 * @return modify_list modify执行结果
	 */
	public List<JDBCResultBean> getModify_list() {
		return this.modify_list;
	}
	/**
	 * @param modify_list modify执行结果
	 */
	public void setModify_list(List<JDBCResultBean> modify_list) {
		this.modify_list = modify_list;
	}

	/**
	 * @return sys_cn_name 系统中文名
	 */
	public String getSys_cn_name() {
		return sys_cn_name;
	}

	/**
	 * @param sys_cn_name 系统中文名
	 */
	public void setSys_cn_name(String sys_cn_name) {
		this.sys_cn_name = sys_cn_name;
	}

	/**
	 * @return sql_text SQL语句
	 */
	public String getSql_text() {
		return sql_text;
	}

	/**
	 * @param sql_text SQL语句
	 */
	public void setSql_text(String sql_text) {
		this.sql_text = sql_text;
	}

	/**
	 * @return priv_yn_flag 权限标志
	 */
	public YN_FLAG getPriv_yn_flag() {
		return priv_yn_flag;
	}

	/**
	 * @param priv_yn_flag 权限标志
	 */
	public void setPriv_yn_flag(YN_FLAG priv_yn_flag) {
		this.priv_yn_flag = priv_yn_flag;
	}

	/**
	 * @return count 行数
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param count 行数
	 */
	public void setCount(int count) {
		this.count = count;
	}
	
	

	/**
	 * @return total_page
	 */
	public int getTotal_page() {
		return total_page;
	}

	/**
	 * @param total_page
	 */
	public void setTotal_page(int total_page) {
		this.total_page = total_page;
	}

	/** 
	 * Description: 
	 * @return 
	 */
	@Override
	public String toString() {
		return "ExecuteMessageBean [sql_work_seq=" + sql_work_seq + ", success_flag=" + success_flag + ", error_message=" + error_message + ", sql_type=" + sql_type
				+ ", sql_state=" + sql_state + ", act_exec_time=" + act_exec_time + ", act_bk_num=" + act_bk_num + ", read_only=" + read_only + ", can_insert=" + can_insert
				+ ", theads=" + theads + ", tbodys=" + tbodys + ", modify_list=" + modify_list + ", sys_cn_name=" + sys_cn_name + ", sql_text=" + sql_text + ", priv_yn_flag="
				+ priv_yn_flag + ", count=" + count + "]";
	}
}
