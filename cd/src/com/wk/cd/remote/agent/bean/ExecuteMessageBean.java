/**
 * Title: ExecuteMessageBean.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: "Zhangj"
 * @version: 1.0
 * @date: 2016��8��15��
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
	 *SQL��ˮ��
	 */
	private String sql_work_seq;

	public static final String SQL_WORK_SEQCN = "SQL��ˮ��";
	
	/**
	 * �ɹ���־
	 */
	private boolean success_flag;
	
	public static final String SUCCESS_FLAGCN = "�ɹ���־";
	
	/**
	 * ������Ϣ
	 */
	private String error_message;
	
	public static final String ERROR_MESSAGECN = "������Ϣ";
	
	/**
	 * SQL���
	 */
	private SQL_TYPE sql_type ;
	
	public static final String SQL_TYPECN = "SQL���";
	
	/**
	 * SQL״̬
	 */
	private SQL_STATE sql_state;
	
	public static final String SQL_STATECN = "SQL״̬";
	
	/**
	 * ִ�к�ʱ
	 */
	private int act_exec_time;
	
	public static final String ACT_EXEC_TIMECN = "ִ�к�ʱ";
	
	/**
	 * Ӱ������
	 */
	private Integer act_bk_num;

	public static final String ACT_BK_NUM = "Ӱ������";
	
	/**
	 * ֻ����־
	 */
	private boolean read_only;
	
	public static final String READ_ONLYCN= "ֻ����־";
	
	/**
	 * �ܷ�������־
	 */
	private boolean can_insert;
	
	public static final String CAN_INSERTCN = "�ܷ�������־";
	
	/**
	 * ��ѯ����ͷ
	 */
	private List<SelectTitleHeadBean> theads;
	
	public static final String THEADSCN = "��ѯ����ͷ";
	
	/**
	 * select��ѯ�����
	 */
	private List<ServiceData> tbodys;
	
	public static final String TBODYSCN = "select��ѯ�����";
	
	/**
	 * modifyִ�н��
	 */
	private List<JDBCResultBean> modify_list;
	
	public static final String MODIFY_LISTCN = "ִ�н��";
	
	/**
	 *ϵͳ������
	 */
	private String sys_cn_name;

	public static final String SYS_CN_NAMECN = "ϵͳ������";
	
	/**
	 *SQL���
	 */
	private String sql_text;

	public static final String SQL_TEXTCN = "SQL���";
	
	/**
	 * Ȩ�ޱ�־
	 */
	private YN_FLAG priv_yn_flag;
	
	public static final String PRIV_YN_FLAGCN = "Ȩ�ޱ�־";
	
	/**
	 * ����
	 */
	private int count;
	
	public static final String COUNTCN = "����";
	
	private int total_page;
	
	public static final String TOTAL_PAGECN = "��ҳ��";
	
	/**
	 * @return Sql��ˮ��
	 */
	public String getSql_work_seq() {
		return this.sql_work_seq;
	}

	/**
	 * @param ����sql��ˮ��
	 */
	public void setSql_work_seq(String sql_work_seq) {
		this.sql_work_seq = sql_work_seq;
	}

	/**
	 * @return theads ��ѯ����ͷ
	 */
	public List<SelectTitleHeadBean> getTheads() {
		return this.theads;
	}

	/*
	 * @param theads ��ѯ����ͷ
	 */
	public void setTheads(List<SelectTitleHeadBean> theads) {
		this.theads = theads;
	}

	/**
	 * @return tbodys select��ѯ�����
	 */
	public List<ServiceData> getTbodys() {
		return this.tbodys;
	}

	/** 
	 * @param tbodys select��ѯ�����
	 */
	public void setTbodys(List<ServiceData> tbodys) {
		this.tbodys = tbodys;
	}

	/**
	 * @return success_flag �ɹ���־
	 */
	public boolean isSuccess_flag() {
		return this.success_flag;
	}

	/**
	 * @param success_flag �ɹ���־
	 */
	public void setSuccess_flag(boolean success_flag) {
		this.success_flag = success_flag;
	}

	/**
	 * @return error_message ������Ϣ
	 */
	public String getError_message() {
		return this.error_message;
	}

	/**
	 * @param error_message ������Ϣ
	 */
	public void setError_message(String error_message) {
		this.error_message = error_message;
	}

	/**
	 * @return sql_type SQL���
	 */
	public SQL_TYPE getSql_type() {
		return this.sql_type;
	}

	/**
	 * @param sql_type SQL���
	 */
	public void setSql_type(SQL_TYPE sql_type) {
		this.sql_type = sql_type;
	}
	
	/** 
	 * @return sql_state SQL״̬
	 */
	public SQL_STATE getSql_state() {
		return this.sql_state;
	}

	/**
	 * @param sql_state SQL״̬
	 */
	public void setSql_state(SQL_STATE sql_state) {
		this.sql_state = sql_state;
	}
	/**
	 * @return effect_line Ӱ������
	 */
	public Integer getAct_bk_num() {
		return this.act_bk_num;
	}

	/** 
	 * @param effect_line Ӱ������
	 */
	public void setAct_bk_num(Integer act_bk_num) {
		this.act_bk_num = act_bk_num;
	}
	/**
	 * @return read_only ֻ����־
	 */
	public boolean isRead_only() {
		return read_only;
	}

	/**
	 * @param read_only  ֻ����־
	 */
	public void setRead_only(boolean read_only) {
		this.read_only = read_only;
	}

	/**
	 * @return can_insert �ܷ�������־
	 */
	public boolean isCan_insert() {
		return can_insert;
	}

	/**
	 * @param can_insert �ܷ�������־
	 */
	public void setCan_insert(boolean can_insert) {
		this.can_insert = can_insert;
	}

	/**
	 * @return time_used ִ�к�ʱ
	 */
	public int getAct_exec_time() {
		return this.act_exec_time;
	}
	/**
	 * 
	 * @param time_used ִ�к�ʱ ��λ����
	 */
	public void setAct_exec_time(int act_exec_time) {
		this.act_exec_time = act_exec_time;
	}

	/**
	 * @return modify_list modifyִ�н��
	 */
	public List<JDBCResultBean> getModify_list() {
		return this.modify_list;
	}
	/**
	 * @param modify_list modifyִ�н��
	 */
	public void setModify_list(List<JDBCResultBean> modify_list) {
		this.modify_list = modify_list;
	}

	/**
	 * @return sys_cn_name ϵͳ������
	 */
	public String getSys_cn_name() {
		return sys_cn_name;
	}

	/**
	 * @param sys_cn_name ϵͳ������
	 */
	public void setSys_cn_name(String sys_cn_name) {
		this.sys_cn_name = sys_cn_name;
	}

	/**
	 * @return sql_text SQL���
	 */
	public String getSql_text() {
		return sql_text;
	}

	/**
	 * @param sql_text SQL���
	 */
	public void setSql_text(String sql_text) {
		this.sql_text = sql_text;
	}

	/**
	 * @return priv_yn_flag Ȩ�ޱ�־
	 */
	public YN_FLAG getPriv_yn_flag() {
		return priv_yn_flag;
	}

	/**
	 * @param priv_yn_flag Ȩ�ޱ�־
	 */
	public void setPriv_yn_flag(YN_FLAG priv_yn_flag) {
		this.priv_yn_flag = priv_yn_flag;
	}

	/**
	 * @return count ����
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param count ����
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