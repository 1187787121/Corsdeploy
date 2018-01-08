/**
 * Title: UpdateInteProgStepInputBean.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: xuph
 * @version: 1.0
 * @date: 2016年11月11日
 */
package com.wk.cd.build.ea.bean;

import java.util.List;

import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description: 
 * @author xuph
 */
public class UpdateInteProgStepInputBean extends ActionRootInputBean{

	/** 
	 * @Fields serialVersionUID : 4591988772655287110L
	 */ 
	private static final long serialVersionUID = 4591988772655287110L;
	
	/**
	 *方案编号
	 */
	private String prog_id;

	public static final String PROG_IDCN = "方案编号";
	
	/**
	 * 集成方案步骤信息
	 */
	private List<InteStepBean> step_list;
	
	public static final String  STEP_LISTCN= "集成方案步骤信息";

	/**
	 * @return prog_id
	 */
	public String getProg_id() {
		return prog_id;
	}

	/**
	 * @param prog_id
	 */
	public void setProg_id(String prog_id) {
		this.prog_id = prog_id;
	}

	/**
	 * @return step_list
	 */
	public List<InteStepBean> getStep_list() {
		return step_list;
	}

	/**
	 * @param step_list
	 */
	public void setStep_list(List<InteStepBean> step_list) {
		this.step_list = step_list;
	}
}
