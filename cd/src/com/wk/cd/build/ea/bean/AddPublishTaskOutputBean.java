/**
 * Title: AddPublishTaskOutputBean.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: zhangj
 * @version: 1.0
 * @date: 2016年11月19日
 */
package com.wk.cd.build.ea.bean;

import com.wk.cd.bean.ActionRootOutputBean;

/**
 * Class Description: 
 * @author "Zhangj"
 */
public class AddPublishTaskOutputBean extends ActionRootOutputBean{

	/** 
	 * @Fields serialVersionUID : -5497194235844405077L
	 */ 
	private static final long serialVersionUID = -5497194235844405077L;
	
	/**
	 * 任务编号
	 */
	private String work_id ;
	
	public static final String WORK_IDCN = "任务编号";
	/**
	 *下一步阶段号（从0开始）
	 */
	private int exe_bk_no;

	public static final String EXE_BK_NOCN = "下一步阶段号";

	/**
	 * @return work_id 任务编号
	 */
	public String getWork_id() {
		return this.work_id;
	}

	/**
	 * @param work_id 任务编号
	 */
	public void setWork_id(String work_id) {
		this.work_id = work_id;
	}

	/**
	 * @return exe_bk_no 下一步阶段号（从0开始）
	 */
	public int getExe_bk_no() {
		return this.exe_bk_no;
	}

	/**
	 * @param exe_bk_no 下一步阶段号（从0开始）
	 */
	public void setExe_bk_no(int exe_bk_no) {
		this.exe_bk_no = exe_bk_no;
	}
	
	

}
