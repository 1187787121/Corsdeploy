/**
 * Title: ViewCompOutputBean.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: xuph
 * @version: 1.0
 * @date: 2016年10月19日
 */
package com.wk.cd.module1.bean;

import java.util.List;

import com.wk.cd.bean.ActionRootOutputBean;
import com.wk.cd.enu.PUBLISH_STATE;
import com.wk.cd.module1.entity.Template;
import com.wk.cd.module1.info.GroupModuleInfo;
import com.wk.cd.module1.info.ModuleInfo;
import com.wk.cd.module1.info.ParamInfo;
import com.wk.cd.module1.info.TemplateInfo;
/**
 * Class Description:
 * @author xuph
 */
public class ViewModuleOutputBean extends ActionRootOutputBean {

	/**
	 * @Fields serialVersionUID : 5691621228691020354L
	 */
	private static final long serialVersionUID = 5691621228691020354L;

	/**
	 * 组件信息
	 */
	private ModuleInfo module;

	public static final String MODULECN = "组件信息";
	
	/**
	 * 脚本路径 
	 */
	private String script_path;
	
	public static final String SCRIPT_PATHCN = "脚本路径";
	
	/**
	 * 组件组信息
	 */
	private GroupModuleInfo module_group;

	public static final String GROUPCN = "组件组信息";
	
	/**
	 * 流程模板信息
	 */
	private Template template;

	public static final String TEMPLATEINFOCN = "流程模板信息";
	
	/**
	 * 文件路径
	 */
	private String file_path;

	public static final String FILE_PATH = "文件路径";

	/**
	 * 发布状态
	 */
	private PUBLISH_STATE publish_state;
	
	public static final String PUBLISH_STATECN = "发布状态";
	
	/**
	 * 组件信息列表
	 */
	private List<ModuleInfo> modules;

	public static final String MODULESCN = "组件信息列表";
	
	/**
	 * 组件组信息列表
	 */
	private List<GroupModuleInfo> groups;

	public static final String GROUPSCN = "组件组信息列表";
	
	/**
	 * 参数列表
	 */
	private List<ParamInfo> params;

	public static final String PARAMSCN = "参数列表";
	
	/**
	 * 测试投产包临时上传目录
	 */
	private String test_package_dir;
	
	public static final String TEST_PACKAGE_DIRCN="测试投产包临时上传目录";
	
	/**
	 * 引用标识
	 */
	private boolean ref_flag;
	
	public static final String REF_FLAGCN = " 引用标识";
	
	/**
	 * 数据源列表
	 */
	private String[] soc_list;
	
	public static final String SOC_LISTCN = " 数据源列表";
	
	/**
	 * 引用列表
	 */
	private List<ReferenceBean> ref_list;
	
	public static final String REF_LISTCN = "引用列表";
	
	
	/**
	 * 组件列表
	 */
	private List<ModuleBeanForList> module_list;
	
	public static final String MODULE_LISTCN = "组件列表";
	
//	/**
//	 * 组件用途
//	 */
//	private MODULE_PURPOSE module_purpose;
//	
//	public static final String MODULE_PURPOSE = "组件用途";
	
	/**
	 * 数据源列表
	 */
	private List<SourceListBean> source_list;
	
	public static final String SOURCE_LISTCN = "数据源列表";
	
	/**
	 * @return file_path 组件文件路径
	 */
	public String getFile_path() {
		return this.file_path;
	}

	/**
	 * @param file_path 组件文件路径
	 */
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}

	/**
	 * @return publish_state 发布状态
	 */
	public PUBLISH_STATE getPublish_state() {
		return this.publish_state;
	}

	/**
	 * @param publish_state 发布状态
	 */
	public void setPublish_state(PUBLISH_STATE publish_state) {
		this.publish_state = publish_state;
	}

	/** 
	 * @return test_package_dir 测试投产包临时上传目录
	 */
	public String getTest_package_dir() {
		return this.test_package_dir;
	}

	/**
	 * @param test_package_dir 测试投产包临时上传目录
	 */
	public void setTest_package_dir(String test_package_dir) {
		this.test_package_dir = test_package_dir;
	}

	/** 
	 * @return soc_list 数据源列表
	 */
	public String[] getSoc_list() {
		return this.soc_list;
	}

	/**
	 * @param soc_list 数据源列表
	 */
	public void setSoc_list(String[] soc_list) {
		this.soc_list = soc_list;
	}

	/**
	 * @return ref_flag 是否被引用标识（被引用为true）
	 */
	public boolean isRef_flag() {
		return ref_flag;
	}

	/**
	 * @param ref_flag 是否被引用标识（被引用为true）
	 */
	public void setRef_flag(boolean ref_flag) {
		this.ref_flag = ref_flag;
	}

	/**
	 * @return ref_list 引用列表
	 */
	public List<ReferenceBean> getRef_list() {
		return ref_list;
	}

	/**
	 * @param ref_list 引用列表
	 */
	public void setRef_list(List<ReferenceBean> ref_list) {
		this.ref_list = ref_list;
	}

	/**
	 * @return module
	 */
	public ModuleInfo getModule() {
		return module;
	}

	/**
	 * @param module
	 */
	public void setModule(ModuleInfo module) {
		this.module = module;
	}

	/**
	 * @return module_group
	 */
	public GroupModuleInfo getModule_group() {
		return module_group;
	}

	/**
	 * @param module_group
	 */
	public void setModule_group(GroupModuleInfo module_group) {
		this.module_group = module_group;
	}

	/**
	 * @return template
	 */
	public Template getTemplate() {
		return template;
	}

	/**
	 * @param template
	 */
	public void setTemplate(Template template) {
		this.template = template;
	}

	/**
	 * @return modules
	 */
	public List<ModuleInfo> getModules() {
		return modules;
	}

	/**
	 * @param modules
	 */
	public void setModules(List<ModuleInfo> modules) {
		this.modules = modules;
	}

	/**
	 * @return groups
	 */
	public List<GroupModuleInfo> getGroups() {
		return groups;
	}

	/**
	 * @param groups
	 */
	public void setGroups(List<GroupModuleInfo> groups) {
		this.groups = groups;
	}

	/**
	 * @return params
	 */
	public List<ParamInfo> getParams() {
		return params;
	}

	/**
	 * @param params
	 */
	public void setParams(List<ParamInfo> params) {
		this.params = params;
	}

	/**
	 * @return module_list
	 */
	public List<ModuleBeanForList> getModule_list() {
		return module_list;
	}

	/**
	 * @param module_list
	 */
	public void setModule_list(List<ModuleBeanForList> module_list) {
		this.module_list = module_list;
	}

	/**
	 * @return script_path
	 */
	public String getScript_path() {
		return script_path;
	}

	/**
	 * @param script_path
	 */
	public void setScript_path(String script_path) {
		this.script_path = script_path;
	}

	/**
	 * @return source_list
	 */
	public List<SourceListBean> getSource_list() {
		return source_list;
	}

	/**
	 * @param source_list
	 */
	public void setSource_list(List<SourceListBean> source_list) {
		this.source_list = source_list;
	}

//	public MODULE_PURPOSE getModule_purpose() {
//		return module_purpose;
//	}
//
//	public void setModule_purpose(MODULE_PURPOSE module_purpose) {
//		this.module_purpose = module_purpose;
//	}

	
}
