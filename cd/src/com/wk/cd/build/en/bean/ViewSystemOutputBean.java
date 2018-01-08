/**
 * Title: ViewCeOutput.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: chiss
 * @version: 1.0
 * @date: 2016年10月31日
 */
package com.wk.cd.build.en.bean;

import java.util.List;

import com.wk.cd.build.en.info.CeServerInfo;
import com.wk.cd.build.en.info.CeSystemInfo;
import com.wk.cd.enu.SERVER_TYPE;
import com.wk.cd.enu.SYS_TYPE;
import com.wk.cd.bean.ActionRootOutputBean;

/**
 * Class Description:查看应用系统输出接口
 * @author chiss
 */
public class ViewSystemOutputBean extends ActionRootOutputBean {

	/** 
	 * @Fields serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = -3224315437108364211L;
	
	/**
	 * 模板名称
	 */
	private String[] template_name;
	
	public static final String TEMPLATE_NAMECN = "模板名称";
	
	/**
	 * 应用系统模板列表
	 */
	private List<SystemTemplateBean> sys_bean;
	
	public static final String SYS_BEANCN = "应用系统模板列表";
	
	/**
	 *应用系统名称
	 */
	private String sys_name;

	public static final String SYS_NAMECN = "应用系统名称";

	/**
	 *应用系统简称
	 */
	private String sys_cn_name;

	public static final String SYS_CN_NAMECN = "应用系统简称";

	/**
	 *应用系统类型
	 */
	private SYS_TYPE sys_type;

	public static final String SYS_TYPECN = "应用系统类型";

	/**
	 *应用系统描述
	 */
	private String sys_bk_desc;

	public static final String SYS_BK_DESCCN = "应用系统描述";
	
	/**
	 * 应用系统模板表信息
	 */
	private List<SystemTemplateBean> systemp_list;
	
	public static final String SYS_TEM_BEANCN = "应用系统模板表信息";
	
	
	/**
	 * 服务器类型
	 */
	private SERVER_TYPE server_type;
	
	public static final String SERVER_TYPECN = "服务器类型";
	
	/**
	 * 系统环境关联
	 */
	private List<SystemEnvirBean> envir_list;
	
	public static final String ENVIR_LISTCN = "系统环境关联";
	
	/**
	 * 系统名称信息
	 */
	private List<CeSystemInfo> sys_list;
	
	public static final String SYS_LISTCN = "系统名称信息";
	
	/**
	 * 应用系统列表
	 */
	private List<PageSystemListBean> page_sys_List;
	
	public static final String PAGE_SYS_BEANCN = "应用系统列表";
	
	/**
	 * 系统环境及任务明细列表
	 */
	private List<SysEnvAndTaskBean> sys_detail_list;
	
	public static final String SYS_DETAIL_LISTCN = "系统环境及任务明细列表";
	
	/**
	 * 应用系统文件名列表
	 */
	private String[] file_list;
	
	public static final String FILE_LISTCN = "应用系统文件名列表";
	
	/**
	 * 环境列表
	 */
	private List<EnvInfoBean> env_list;
	
	public static final String ENV_LISTCN = "环境列表";
	
	/**
	 * 服务器列表
	 */
	private List<CeServerInfo> server_list;
	
	public static final String SERVER_LISTCN = "服务器列表";
	
	/**
	 * @return template_name 模板名称
	 */
	public String[] getTemplate_name() {
		return template_name;
	}

	/**
	 * @param template_name 模板名称
	 */
	public void setTemplate_name(String[] template_name) {
		this.template_name = template_name;
	}
	
	/**
	 * @return sys_bean 应用系统模板列表
	 */
	public List<SystemTemplateBean> getSys_bean() {
		return sys_bean;
	}

	/**
	 * @param sys_bean 应用系统模板列表
	 */
	public void setSys_bean(List<SystemTemplateBean> sys_bean) {
		this.sys_bean = sys_bean;
	}
	
	
	/**
	 * @return sys_name 应用系统名称
	 */
	public String getSys_name() {
		return sys_name;
	}

	/**
	 * @param sys_name 应用系统名称
	 */
	public void setSys_name(String sys_name) {
		this.sys_name = sys_name;
	}

	/**
	 * @return sys_cn_name 应用系统简称
	 */
	public String getSys_cn_name() {
		return sys_cn_name;
	}

	/**
	 * @param sys_cn_name 应用系统简称
	 */
	public void setSys_cn_name(String sys_cn_name) {
		this.sys_cn_name = sys_cn_name;
	}

	/**
	 * @return sys_type 应用系统类型
	 */
	public SYS_TYPE getSys_type() {
		return sys_type;
	}

	/**
	 * @param sys_type 应用系统类型
	 */
	public void setSys_type(SYS_TYPE sys_type) {
		this.sys_type = sys_type;
	}

	/**
	 * @return sys_bk_desc 应用系统描述
	 */
	public String getSys_bk_desc() {
		return sys_bk_desc;
	}

	/**
	 * @param sys_bk_desc 应用系统描述
	 */
	public void setSys_bk_desc(String sys_bk_desc) {
		this.sys_bk_desc = sys_bk_desc;
	}
	/**
	 * @return systemp_list 应用系统模板表信息
	 */
	public List<SystemTemplateBean> getSystemp_list() {
		return systemp_list;
	}

	/**
	 * @param systemp_list 应用系统模板表信息
	 */
	public void setSystemp_list(List<SystemTemplateBean> systemp_list) {
		this.systemp_list = systemp_list;
	}

	/**
	 * @return server_type 服务器类型
	 */
	public SERVER_TYPE getServer_type() {
		return server_type;
	}

	/**
	 * @param server_type 服务器类型
	 */
	public void setServer_type(SERVER_TYPE server_type) {
		this.server_type = server_type;
	}

	/**
	 * @return envir_list 系统环境关联
	 */
	public List<SystemEnvirBean> getEnvir_list() {
		return this.envir_list;
	}

	/**
	 * @param envir_list 系统环境关联
	 */
	public void setEnvir_list(List<SystemEnvirBean> envir_list) {
		this.envir_list = envir_list;
	}

	/**
	 * @return sys_list
	 */
	public List<CeSystemInfo> getSys_list() {
		return this.sys_list;
	}

	/**
	 * @param sys_list
	 */
	public void setSys_list(List<CeSystemInfo> sys_list) {
		this.sys_list = sys_list;
	}
	/**
	 * @return page_sys_List 应用系统列表
	 */
	public List<PageSystemListBean> getPage_sys_List() {
		return page_sys_List;
	}

	/**
	 * @param page_sys_List 应用系统列表
	 */
	public void setPage_sys_List(List<PageSystemListBean> page_sys_List) {
		this.page_sys_List = page_sys_List;
	}
	
	/**
	 * @return sys_detail_list 系统环境及任务明细列表
	 */
	public List<SysEnvAndTaskBean> getSys_detail_list() {
		return sys_detail_list;
	}

	/**
	 * @param sys_detail_list 系统环境及任务明细列表
	 */
	public void setSys_detail_list(List<SysEnvAndTaskBean> sys_detail_list) {
		this.sys_detail_list = sys_detail_list;
	}

	/**
	 * @return file_list 应用系统文件名列表
	 */
	public String[] getFile_list() {
		return file_list;
	}

	/**
	 * @param file_list 应用系统文件名列表
	 */
	public void setFile_list(String[] file_list) {
		this.file_list = file_list;
	}

	/**
	 * @return env_list 环境列表
	 */
	public List<EnvInfoBean> getEnv_list() {
		return env_list;
	}

	/**
	 * @param env_list 环境列表
	 */
	public void setEnv_list(List<EnvInfoBean> env_list) {
		this.env_list = env_list;
	}

	/**
	 * @return server_list 服务器列表
	 */
	public List<CeServerInfo> getServer_list() {
		return server_list;
	}

	/**
	 * @param server_list 服务器列表
	 */
	public void setServer_list(List<CeServerInfo> server_list) {
		this.server_list = server_list;
	}
}
