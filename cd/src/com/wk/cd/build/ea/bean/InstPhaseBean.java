/**
 * Title: InstPhaseBean.java
 * File Description: 实例阶段接口
 * @copyright: 2017
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2017年2月10日
 */
package com.wk.cd.build.ea.bean;

import com.wk.cd.enu.EXE_RESULT;
import com.wk.cd.enu.EXE_STATUS;

/**
 * Class Description: 实例阶段接口
 * @author Xul
 */
public class InstPhaseBean {
	
	/**
	 * 执行状态
	 */
	private EXE_STATUS exe_status;

	public static final String EXE_STATUSCN = "执行状态";
	
	/**
	 * 执行结果
	 */
	private EXE_RESULT task_exe_result;

	public static final String TASK_EXE_RESULTCN = "执行结果";
	
	/**
	 * @return exe_status 执行状态
	 */
	public EXE_STATUS getExe_status() {
		return exe_status;
	}

	/**
	 * @param exe_status 执行状态
	 */
	public void setExe_status(EXE_STATUS exe_status) {
		this.exe_status = exe_status;
	}

	/**
	 * @return task_exe_result 执行结果
	 */
	public EXE_RESULT getTask_exe_result() {
		return task_exe_result;
	}

	/**
	 * @param task_exe_result 执行结果
	 */
	public void setTask_exe_result(EXE_RESULT task_exe_result) {
		this.task_exe_result = task_exe_result;
	}
}
