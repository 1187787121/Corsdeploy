/**
 * Title: ExcuteStorage.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: xuph
 * @version: 1.0
 * @date: 2016年11月23日
 */
package com.wk.cd.build.ea.bean;

import com.wk.cd.enu.CMD_STATUS;

/**
 * Class Description: 入库执行信息
 * @author xuph
 */
public class ExcuteStorage {
	/**
	 * 总步骤
	 */
	private int total_step;

	public static final String TOTAL_STEPCN = "总步骤";
	/**
	 * 当前步骤
	 */
	private int current_step;
	
	public static final String CURRENT_STEPCN = "当前步骤";
	/**
	 * 步骤状态
	 */
	private CMD_STATUS cmd_state;
	
	public static final String CMD_STATECN = "步骤状态";

	/**
	 * @return total_step
	 */
	public int getTotal_step() {
		return total_step;
	}

	/**
	 * @param total_step
	 */
	public void setTotal_step(int total_step) {
		this.total_step = total_step;
	}

	/**
	 * @return current_step
	 */
	public int getCurrent_step() {
		return current_step;
	}

	/**
	 * @param current_step
	 */
	public void setCurrent_step(int current_step) {
		this.current_step = current_step;
	}

	/**
	 * @return cmd_state
	 */
	public CMD_STATUS getCmd_state() {
		return cmd_state;
	}

	/**
	 * @param cmd_state
	 */
	public void setCmd_state(CMD_STATUS cmd_state) {
		this.cmd_state = cmd_state;
	}

}
