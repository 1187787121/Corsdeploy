/**
 * Title: SavePublishProgDetailInputBean.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Administrator
 * @version: 1.0
 * @date: 2016年11月14日
 */
package com.wk.cd.build.ea.bean;

import java.util.List;

import com.wk.cd.build.ea.info.UuParamInfo;
import com.wk.cd.bean.ActionRootInputBean;
import com.wk.cd.module1.entity.Program;

/**
 * Class Description: 
 * @author Administrator
 */
public class AddPublishProgDetailInputBean extends ActionRootInputBean{

	/** 
	 * @Fields serialVersionUID : 6896460009959766066L
	 */ 
	private static final long serialVersionUID = 6896460009959766066L;
	
	
	/**
	 * 环境名称
	 */
	private String env_name;
	
	public static final String ENV_NAMECN = "环境名称";
	
	/**
	 * 发布方案信息
	 */
	private Program pub_program;

	public static final String PUB_PROGRAMCN = "发布方案信息";
	
	/**
	 * 回退方案信息
	 */
	private Program rol_program;
	
	public static final String ROL_PROGRAMCN = "回退方案信息";

	/**
	 * @return env_name
	 */
	public String getEnv_name() {
		return env_name;
	}

	/**
	 * @param env_name
	 */
	public void setEnv_name(String env_name) {
		this.env_name = env_name;
	}

	/**
	 * @return pub_program
	 */
	public Program getPub_program() {
		return pub_program;
	}

	/**
	 * @param pub_program
	 */
	public void setPub_program(Program pub_program) {
		this.pub_program = pub_program;
	}

	/**
	 * @return rol_program
	 */
	public Program getRol_program() {
		return rol_program;
	}

	/**
	 * @param rol_program
	 */
	public void setRol_program(Program rol_program) {
		this.rol_program = rol_program;
	}
}
