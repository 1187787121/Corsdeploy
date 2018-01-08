/**
 * Title: MonitorCompTestOutputBean.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: zhangj
 * @version: 1.0
 * @date: 2016年10月27日
 */
package com.wk.cd.module1.bean;

import com.wk.cd.bean.ActionRootOutputBean;
import com.wk.cd.enu.CMD_STATUS;

/**
 * Class Description: 
 * @author "Zhangj"
 */
public class ViewMonitorModuleOutputBean extends ActionRootOutputBean{

	/** 
	 * @Fields serialVersionUID : -5619705295282253636L
	 */ 
	private static final long serialVersionUID = -5619705295282253636L;
	
	/**
	 * 总步骤数
	 */
	private int total_step;
	
	public static final String TOTAL_STEPCN = "总步骤数";
	
	/**
	 * 总阶段数
	 */
	private int total_phase;
	
	public static final String TOTOAL_PHASECN = "总阶段数";
	
	/**
	 * 当前步骤号
	 */
	private int current_step;
	
	public static final String CURRENT_STEPCN = "当前步骤号";
	
	/**
	 * 当前阶段号
	 */
	private int current_phase;
	
	public static final String CURRENT_PHASECN= "当前阶段号";
	
	/**
	 * 当前执行命令
	 */
	private String current_cmd;
	
	public static final String CURRENT_CMDCN = "当前执行命令";
	
	/**
	 * 当前命令状态
	 */
	private CMD_STATUS current_cmd_status;
	
	public static final String CURRENT_CMD_STATUSCN = "当前命令状态";

	/**
	 * @return total_step 总步骤数
	 */
	public int getTotal_step() {
		return this.total_step;
	}

	/**
	 * @param total_step 总步骤数
	 */
	public void setTotal_step(int total_step) {
		this.total_step = total_step;
	}

	/**
	 * @return total_phase 总阶段数
	 */
	public int getTotal_phase() {
		return this.total_phase;
	}

	/**
	 * @param total_phase 总阶段数
	 */
	public void setTotal_phase(int total_phase) {
		this.total_phase = total_phase;
	}

	/**
	 * @return current_step 当前步骤号
	 */
	public int getCurrent_step() {
		return this.current_step;
	}

	/**
	 * @param current_step 当前步骤号
	 */
	public void setCurrent_step(int current_step) {
		this.current_step = current_step;
	}

	/** 
	 * @return current_phase 当前阶段号
	 */
	public int getCurrent_phase() {
		return this.current_phase;
	}

	/**
	 * @param current_phase 当前阶段号
	 */
	public void setCurrent_phase(int current_phase) {
		this.current_phase = current_phase;
	}

	/** 
	 * @return current_cmd 当前执行命令
	 */
	public String getCurrent_cmd() {
		return this.current_cmd;
	}

	/**
	 * @param current_cmd 当前执行命令
	 */
	public void setCurrent_cmd(String current_cmd) {
		this.current_cmd = current_cmd;
	}


	/** 
	 * @return current_cmd_status 当前命令状态
	 */
	public CMD_STATUS getCurrent_cmd_status() {
		return this.current_cmd_status;
	}
	/**
	 * @param cmd_status 当前命令状态
	 */
	public void setCurrent_cmd_status(CMD_STATUS current_cmd_status) {
		this.current_cmd_status = current_cmd_status;
	}
	

	
	
}
