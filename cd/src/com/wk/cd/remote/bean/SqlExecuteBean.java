package com.wk.cd.remote.bean;

import com.wk.cd.enu.SQL_STATE;

public class SqlExecuteBean {
	
	private int task_no;
	
	private int sql_seq;
	
	private String soc_name;
	
	private String sql_text;
	
	private String error_msg;
	
	private SQL_STATE sql_state;
	
	private long used_time;

	private boolean success_flag;
	
	 
	/**
	 * @return task_no
	 */
	public int getTask_no() {
		return task_no;
	}

	/**
	 * @param task_no
	 */
	public void setTask_no(int task_no) {
		this.task_no = task_no;
	}

	/**
	 * @return sql_seq
	 */
	public int getSql_seq() {
		return sql_seq;
	}

	/**
	 * @param sql_seq
	 */
	public void setSql_seq(int sql_seq) {
		this.sql_seq = sql_seq;
	}

	/**
	 * @return soc_name
	 */
	public String getSoc_name() {
		return soc_name;
	}

	/**
	 * @param soc_name
	 */
	public void setSoc_name(String soc_name) {
		this.soc_name = soc_name;
	}

	/**
	 * @return sql_text
	 */
	public String getSql_text() {
		return sql_text;
	}

	/**
	 * @param sql_text
	 */
	public void setSql_text(String sql_text) {
		this.sql_text = sql_text;
	}

	/**
	 * @return error_msg
	 */
	public String getError_msg() {
		return error_msg;
	}

	/**
	 * @param error_msg
	 */
	public void setError_msg(String error_msg) {
		this.error_msg = error_msg;
	}

	/**
	 * @return sql_state
	 */
	public SQL_STATE getSql_state() {
		return sql_state;
	}

	/**
	 * @param sql_state
	 */
	public void setSql_state(SQL_STATE sql_state) {
		this.sql_state = sql_state;
	}

	/**
	 * @return used_time
	 */
	public long getUsed_time() {
		return used_time;
	}

	/**
	 * @param used_time
	 */
	public void setUsed_time(long used_time) {
		this.used_time = used_time;
	}

	/**
	 * @return success_flag
	 */
	public boolean isSuccess_flag() {
		return success_flag;
	}

	/**
	 * @param success_flag
	 */
	public void setSuccess_flag(boolean success_flag) {
		this.success_flag = success_flag;
	}

	/** 
	 * Description: 
	 * @return 
	 */
	@Override
	public String toString() {
		return "SqlExecuteBean [task_no=" + task_no + ", sql_seq=" + sql_seq + ", soc_name=" + soc_name + ", sql_text="
				+ sql_text + ", error_msg=" + error_msg + ", sql_state=" + sql_state + ", used_time=" + used_time
				+ ", success_flag=" + success_flag + "]";
	}

}
