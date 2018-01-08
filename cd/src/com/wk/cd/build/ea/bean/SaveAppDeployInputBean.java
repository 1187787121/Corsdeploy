/**
 * Title: SaveAppDeployInputBean.java
 * File Description: 保存应用部署信息输入接口
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2016年12月12日
 */
package com.wk.cd.build.ea.bean;

import java.util.List;

import com.wk.cd.build.ea.info.UuParamInfo;
import com.wk.cd.bean.ActionRootInputBean;
import com.wk.cd.module1.entity.PhaseParam;

/**
 * Class Description: 保存应用部署信息输入接口
 * @author Xul
 */
public class SaveAppDeployInputBean extends ActionRootInputBean{

	private static final long serialVersionUID = -3327711021437398391L;
	
	/**
	 * 任务编号
	 */
	private String work_id;
	
	public static final String WORK_IDCN = "任务编号";
	
	/**
	 * 模板名
	 */
	private String template_name;
	
	public static final String TEMPLATE_NAMECN = "模板名";
	
	/**
	 * 模板参数列表
	 */
	private List<PhaseParam> param_list;
	
	public static final String PARAM_LISTCN = "模板参数列表";

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
	 * @return template_name 模板名
	 */
	public String getTemplate_name() {
		return template_name;
	}

	/**
	 * @param template_name 模板名
	 */
	public void setTemplate_name(String template_name) {
		this.template_name = template_name;
	}

	/**
	 * @return param_list 模板参数列表
	 */
	public List<PhaseParam> getParam_list() {
		return param_list;
	}

	/**
	 * @param param_list 模板参数列表
	 */
	public void setParam_list(List<PhaseParam> param_list) {
		this.param_list = param_list;
	}
}
