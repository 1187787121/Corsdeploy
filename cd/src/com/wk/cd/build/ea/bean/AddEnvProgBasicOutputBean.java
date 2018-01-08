/**
 * Title: AddEnvProgBasicOutputBean.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Administrator
 * @version: 1.0
 * @date: 2016年11月10日
 */
package com.wk.cd.build.ea.bean;

import com.wk.cd.bean.ActionRootOutputBean;

/**
 * Class Description: 
 * @author Administrator
 */
public class AddEnvProgBasicOutputBean extends ActionRootOutputBean{

	/** 
	 * @Fields serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = -9071144506103644715L;
	
	/**
	 * 方案编号
	 */
	private String prog_id;
	
	public static final String PROG_IDCN = "方案编号";
	
	/**
	 * 编译路径
	 */
	private String complie_bk_path;

	public static final String COMPLIE_BK_PATHCN = "编译路径";
	
	/**
	 * 打包根目录
	 */
	private String target_root_path;
	
	public static final String TARGET_ROOT_PATHCN = "打包根目录";
	
	/**
	 * 下一步骤编号
	 */
	private int next_step_id;
	
	public static final String NEXT_STEP_IDCN = "下一步骤编号";

	/**
	 * @return prog_id 方案编号
	 */
	public String getProg_id() {
		return prog_id;
	}

	/**
	 * @param prog_id 方案编号
	 */
	public void setProg_id(String prog_id) {
		this.prog_id = prog_id;
	}
	
	/**
	 * @return complie_bk_path 编译路径
	 */
	public String getComplie_bk_path() {
		return complie_bk_path;
	}

	/**
	 * @param complie_bk_path 编译路径
	 */
	public void setComplie_bk_path(String complie_bk_path) {
		this.complie_bk_path = complie_bk_path;
	}

	/**
	 * @return target_root_path 打包根目录
	 */
	public String getTarget_root_path() {
		return target_root_path;
	}

	/**
	 * @param target_root_path 打包根目录
	 */
	public void setTarget_root_path(String target_root_path) {
		this.target_root_path = target_root_path;
	}

	/**
	 * @return next_step_id 下一步骤编号
	 */
	public int getNext_step_id() {
		return next_step_id;
	}

	/**
	 * @param next_step_id 下一步骤编号
	 */
	public void setNext_step_id(int next_step_id) {
		this.next_step_id = next_step_id;
	}
	
}
