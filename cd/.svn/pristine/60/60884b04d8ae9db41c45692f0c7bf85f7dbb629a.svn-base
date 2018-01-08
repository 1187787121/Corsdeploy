/**
 * Title: AddBuildExeScriptInputBean.java
 * File Description: 添加配置参数输入接口
 * @copyright: 2016
 * @company: CORSWORK
 * @author: xuph
 * @version: 1.0
 * @date: 2016年12月9日
 */
package com.wk.cd.build.ea.bean;

import java.util.List;

import com.wk.cd.build.ea.info.UuParamInfo;
import com.wk.cd.build.ea.info.UuSocInfo;
import com.wk.cd.enu.SCRIPT_METHOD;
import com.wk.cd.enu.SCRIPT_TYPE;
import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description: 添加配置参数输入接口
 * @author xuph
 */
public class AddBuildExeScriptInputBean extends ActionRootInputBean{

	/** 
	 * @Fields serialVersionUID : 7844246912851643275L
	 */ 
	private static final long serialVersionUID = 7844246912851643275L;
	
	/**
	 * 任务编号
	 */
	private String work_id;
	
	public static final String WORK_IDCN = "任务编号";
	/**
	 *组件ID
	 */
	private String id;

	public static final String IDCN = "组件ID";

	/**
	 *组件中文名
	 */
	private String cn_name;

	public static final String CN_NAMECN = "组件中文名";
	
	/**
	 *脚本类型
	 */
	private SCRIPT_TYPE script_type;

	public static final String SCRIPT_TYPECN = "脚本类型";
	
	/**
	 *脚本方式
	 */
	private SCRIPT_METHOD script_method;

	public static final String SCRIPT_METHODCN = "脚本方式";
	
	/**
	 * 服务数据源列表
	 */
	private List<UuSocInfo>soc_list;
	
	public static final String SOC_LISTCN = "服务数据源列表";
	 
	/**
	 *脚本命令
	 */
	private String []script_text;

	public static final String SCRIPT_TEXTCN = "脚本命令";
	
	/**
	 * 参数信息
	 */
	List<UuParamInfo> param_list;
	
	public static final String PARAM_LISTCN = "参数信息";
	
	
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
	 *@return script_type 脚本类型
	 */
	public SCRIPT_TYPE getScript_type() {
		return this.script_type;
	}

	/**
	 *@param script_type 脚本类型
	 */
	public void setScript_type(SCRIPT_TYPE script_type) {
		this.script_type = script_type;
	}
	
	/**
	 *@return script_method 脚本方式
	 */
	public SCRIPT_METHOD getScript_method() {
		return this.script_method;
	}

	/**
	 *@param script_method 脚本方式
	 */
	public void setScript_method(SCRIPT_METHOD script_method) {
		this.script_method = script_method;
	}

	/**
	 *@return comp_id 组件ID
	 */
	public String getId() {
		return this.id;
	}

	/**
	 *@param comp_id 组件ID
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 *@return comp_cn_name 组件中文名
	 */
	public String getCn_name() {
		return this.cn_name;
	}

	/**
	 *@param comp_cn_name 组件中文名
	 */
	public void setCn_name(String cn_name) {
		this.cn_name = cn_name;
	}
	
	/**
	 *@return script_text 脚本命令
	 */
	public String[] getScript_text() {
		return this.script_text;
	}

	/**
	 *@param script_text 脚本命令
	 */
	public void setScript_text(String[] script_text) {
		this.script_text = script_text;
	}

	
	/**
	 * @return soc_list 服务数据源列表
	 */
	public List<UuSocInfo> getSoc_list() {
		return this.soc_list;
	}

	/**
	 * @param soc_list 服务数据源列表
	 */
	public void setSoc_list(List<UuSocInfo> soc_list) {
		this.soc_list = soc_list;
	}

	/**
	 * @return param_list 参数信息
	 */
	public List<UuParamInfo> getParam_list() {
		return param_list;
	}

	/**
	 * @param param_list 参数信息
	 */
	public void setParam_list(List<UuParamInfo> param_list) {
		this.param_list = param_list;
	}

}
