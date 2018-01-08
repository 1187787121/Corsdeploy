/**
 * Title: ExePublishTaskInputBean.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: zhangj
 * @version: 1.0
 * @date: 2016年11月22日
 */
package com.wk.cd.build.ea.bean;

import com.wk.cd.enu.EXE_METHOD;
import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description: 
 * @author Zhangj
 */
public class ExePublishTaskInputBean extends ActionRootInputBean{

	/** 
	 * @Fields serialVersionUID : 6808637948377571559L
	 */ 
	private static final long serialVersionUID = 6808637948377571559L;
	/**
	 *任务编号
	 */
	private String work_id;

	public static final String WORK_IDCN = "任务编号";

	/**
	 *执行编号 （从1开始）
	 */
	private int phase_id;

	public static final String PHASE_IDCN = "执行编号";
	
	/**
	 * 执行动作
	 */
	private EXE_METHOD exe_method;
	
	public static final String EXE_METHODCN = "执行动作";
	
	
	
	/**
	 * @return work_id 任务编号
	 */
	public String getWork_id() {
		return work_id;
	}

	/**
	 * @param work_id 任务编号
	 */
	public void setWork_id(String work_id) {
		this.work_id = work_id;
	}

	

	/**
	 * @return phase_id
	 */
	public int getPhase_id() {
		return phase_id;
	}

	/**
	 * @param phase_id
	 */
	public void setPhase_id(int phase_id) {
		this.phase_id = phase_id;
	}

	/**
	 * @return exe_method
	 */
	public EXE_METHOD getExe_method() {
		return exe_method;
	}

	/**
	 * @param exe_method
	 */
	public void setExe_method(EXE_METHOD exe_method) {
		this.exe_method = exe_method;
	}
	
	

	
}
