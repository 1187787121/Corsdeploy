/**
 * Title: ModuleBasicInfo.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: zhangj
 * @version: 1.0
 * @date: 2016年12月26日
 */
package com.wk.cd.module1.info;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.module1.enu.COMPONENT_PURPOSE;
import com.wk.cd.module1.enu.COMPONENT_SOURCE;
import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.IMPL_TYPE;
import com.wk.cd.enu.MODULE_TYPE;
import com.wk.cd.module1.info.ModuleBasicInfo;
import com.wk.cd.module1.info.ParamInfo;

/**
 * Class Description:
 * @author zhangj
 */
public class ModuleBasicInfo {

	protected String id;

	protected String cn_name;

	protected String alias_name;

	protected String bk_desc;

	protected MODULE_TYPE type;

	protected ParamInfo[] params;

	protected String[] cmds;

	protected String cmd_param;

	protected IMPL_TYPE impl_type;

	protected String script_file_path;

	protected COMPONENT_SOURCE script_source;

	protected COMPONENT_PURPOSE module_purpose;

	protected int[] module_purposes;

	protected List<Script> script_list = new ArrayList<Script>();

	private final List<ParamInfo> ref_params = new ArrayList<ParamInfo>();

	protected final List<ModuleBasicInfo> modules = new ArrayList<ModuleBasicInfo>();

	public static final String MODULE_TYPECN = "组件类型";

	public static ModuleBasicInfo copy(ModuleBasicInfo info) {
		if (info == null) {
			return null;
		}
		ModuleBasicInfo bean = new ModuleBasicInfo();
		bean.setId(info.getId());
		bean.setCn_name(info.getCn_name());
		bean.setAlias_name(info.getAlias_name());
		bean.setBk_desc(info.getBk_desc());
		bean.setType(info.getType());
		bean.setParams(info.getParams());
		bean.setCmds(info.getCmds());
		bean.setImpl_type(info.getImpl_type());
		bean.setRef_params(info.getRef_params());
		bean.setModules(info.getModules());
		return bean;
	}

	public static List<ModuleBasicInfo> copy(List<ModuleBasicInfo> infos) {
		if (infos == null) {
			return null;
		}
		List<ModuleBasicInfo> list = new ArrayList<ModuleBasicInfo>();
		if (Assert.isEmpty(infos)) {
			return list;
		}
		for (ModuleBasicInfo info : infos) {
			list.add(copy(info));
		}
		return list;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? id : new String(id);
	}

	public String getCn_name() {
		return cn_name;
	}

	public void setCn_name(String cn_name) {
		this.cn_name = cn_name == null ? cn_name : new String(cn_name);
	}

	public String getBk_desc() {
		return bk_desc;
	}

	public void setBk_desc(String bk_desc) {
		this.bk_desc = bk_desc == null ? bk_desc : new String(bk_desc);
	}

	public MODULE_TYPE getType() {
		return type;
	}

	public void setType(MODULE_TYPE type) {
		this.type = type;
	}

	public ParamInfo[] getParams() {
		return params;
	}

	public void setParams(ParamInfo[] params) {
		this.params = ParamInfo.copy(params);
	}

	public String getAlias_name() {
		return alias_name;
	}

	public void setAlias_name(String alias_name) {
		this.alias_name = alias_name == null ? alias_name : new String(alias_name);
	}

	public String[] getCmds() {
		return cmds;
	}

	public void setCmds(String[] cmds) {
		if (cmds == null) {
			this.cmds = null;
			return;
		}
		String[] array = new String[cmds.length];
		if (Assert.isEmpty(cmds)) {
			this.cmds = array;
			return;
		}
		for (int i = 0; i < array.length; i++) {
			array[i] = new String(cmds[i]);
		}
		this.cmds = array;
	}

	public IMPL_TYPE getImpl_type() {
		return impl_type;
	}

	public void setImpl_type(IMPL_TYPE impl_type) {
		this.impl_type = impl_type;

	}
	
	/**
	 * @return script_list
	 */
	public List<Script> getScript_list() {
		return script_list;
	}

	/**
	 * @param script_list
	 */
	public void setScript_list(List<Script> script_list) {
		this.script_list.clear();
		this.addScript_list(script_list);
	}
	
	/**
	 * @param script_list
	 */
	public void addScript_list(List<Script> script_list) {
		this.script_list.addAll(script_list);
	}
	
	/**
	 * @param script_list
	 */
	public void addScript_list(Script script) {
		this.script_list.add(script);
	}

	public List<ParamInfo> getRef_params() {
		return ref_params;
	}

	public void addAllRef_params(List<ParamInfo> ref_params) {
		if (!Assert.isEmpty(ref_params)) {
			this.ref_params.addAll(ParamInfo.copy(ref_params));
		}
	}

	public void addRef_params(ParamInfo info) {
		this.ref_params.add(ParamInfo.copy(info));
	}

	public void setRef_params(List<ParamInfo> ref_params) {
		this.ref_params.clear();
		this.addAllRef_params(ref_params);
	}

	public List<ModuleBasicInfo> getModules() {
		return this.modules;
	}

	public void addModules(List<ModuleBasicInfo> modules) {
		this.modules.addAll(ModuleBasicInfo.copy(modules));
	}

	public void setModules(List<ModuleBasicInfo> modules) {
		this.modules.clear();
		this.addModules(modules);
	}

	/**
	 * @return script_source
	 */
	public COMPONENT_SOURCE getScript_source() {
		return script_source;
	}

	/**
	 * @param script_source
	 */
	public void setScript_source(COMPONENT_SOURCE script_source) {
		this.script_source = script_source;
	}

	/**
	 * @return module_purpose
	 */
	public COMPONENT_PURPOSE getModule_purpose() {
		return module_purpose;
	}

	/**
	 * @param module_purpose
	 */
	public void setModule_purpose(COMPONENT_PURPOSE module_purpose) {
		this.module_purpose = module_purpose;
	}

	/**
	 * @return module_purposes
	 */
	public int[] getModule_purposes() {
		return module_purposes;
	}

	/**
	 * @param module_purposes
	 */
	public void setModule_purposes(int[] module_purposes) {
		this.module_purposes = module_purposes;
	}

	/**
	 * @return cmd_param
	 */
	public String getCmd_param() {
		return cmd_param;
	}

	/**
	 * @param cmd_param
	 */
	public void setCmd_param(String cmd_param) {
		this.cmd_param = cmd_param;
	}

	/**
	 * @return script_file_path
	 */
	public String getScript_file_path() {
		return script_file_path;
	}

	/**
	 * @param script_file_path
	 */
	public void setScript_file_path(String script_file_path) {
		this.script_file_path = script_file_path;
	}

}
