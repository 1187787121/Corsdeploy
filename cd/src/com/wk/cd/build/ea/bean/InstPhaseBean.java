/**
 * Title: InstPhaseBean.java
 * File Description: ʵ���׶νӿ�
 * @copyright: 2017
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2017��2��10��
 */
package com.wk.cd.build.ea.bean;

import com.wk.cd.enu.EXE_RESULT;
import com.wk.cd.enu.EXE_STATUS;

/**
 * Class Description: ʵ���׶νӿ�
 * @author Xul
 */
public class InstPhaseBean {
	
	/**
	 * ִ��״̬
	 */
	private EXE_STATUS exe_status;

	public static final String EXE_STATUSCN = "ִ��״̬";
	
	/**
	 * ִ�н��
	 */
	private EXE_RESULT task_exe_result;

	public static final String TASK_EXE_RESULTCN = "ִ�н��";
	
	/**
	 * @return exe_status ִ��״̬
	 */
	public EXE_STATUS getExe_status() {
		return exe_status;
	}

	/**
	 * @param exe_status ִ��״̬
	 */
	public void setExe_status(EXE_STATUS exe_status) {
		this.exe_status = exe_status;
	}

	/**
	 * @return task_exe_result ִ�н��
	 */
	public EXE_RESULT getTask_exe_result() {
		return task_exe_result;
	}

	/**
	 * @param task_exe_result ִ�н��
	 */
	public void setTask_exe_result(EXE_RESULT task_exe_result) {
		this.task_exe_result = task_exe_result;
	}
}
