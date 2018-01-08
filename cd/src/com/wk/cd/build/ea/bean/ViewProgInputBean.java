/**
 * Title: ViewProgActionInputBean.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Administrator
 * @version: 1.0
 * @date: 2016年11月14日
 */
package com.wk.cd.build.ea.bean;

import com.wk.cd.enu.PROG_TYPE;
import com.wk.cd.bean.ActionRootInputBean;
import com.wk.cd.enu.TEMPLATE_TYPE;

/**
 * Class Description: 
 * @author Administrator
 */
public class ViewProgInputBean extends ActionRootInputBean{

	/** 
	 * @Fields serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = -9008613475095645019L;
	
	/**
	 * 方案编号
	 */
	private String prog_id;
	
	public static final String PROG_IDCN = "方案编号";
	
	/**
	 * 环境名称
	 */
	private String env_name;
	
	public static final String ENV_NAMECN = "环境名称";
	/**
	 * 步骤编号
	 */
	private int step_id;
	
	public static final String STEP_IDCN = "步骤编号";
	
	/**
	 * 模版名
	 */
	private String template_name;
	
	public static final String TEMPLATE_NAMECN = "模版名";
	
	/**
	 * 方案类型
	 */
	private PROG_TYPE prog_type;
	
	public static final String PROG_TYPECN = "方案类型";
	
	/**
	 * 模板类型
	 */
	private TEMPLATE_TYPE template_type;
	
	public static final String TEMPLATE_TYPECN = "模板类型";

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
	 * @return env_name 环境名称
	 */
	public String getEnv_name() {
		return env_name;
	}

	/**
	 * @param env_name 环境名称
	 */
	public void setEnv_name(String env_name) {
		this.env_name = env_name;
	}

	/**
	 * @return step_id 步骤编号
	 */
	public int getStep_id() {
		return step_id;
	}

	/**
	 * @param step_id 步骤编号
	 */
	public void setStep_id(int step_id) {
		this.step_id = step_id;
	}

	/**
	 * @return template_name 模版名
	 */
	public String getTemplate_name() {
		return template_name;
	}

	/**
	 * @param template_name 模版名
	 */
	public void setTemplate_name(String template_name) {
		this.template_name = template_name;
	}

	/**
	 * @return prog_type 方案类型
	 */
	public PROG_TYPE getProg_type() {
		return prog_type;
	}

	/**
	 * @param prog_type 方案类型
	 */
	public void setProg_type(PROG_TYPE prog_type) {
		this.prog_type = prog_type;
	}

	/**
	 * @return template_type
	 */
	public TEMPLATE_TYPE getTemplate_type() {
		return template_type;
	}

	/**
	 * @param template_type
	 */
	public void setTemplate_type(TEMPLATE_TYPE template_type) {
		this.template_type = template_type;
	}
	
}
