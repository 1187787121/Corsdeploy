/**
 * Title: GroupInfo.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: zhangj
 * @version: 1.0
 * @date: 2016年12月26日
 */
package com.wk.cd.module1.info;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.wk.cd.module1.xml.XmlReader;
import com.wk.cd.enu.MODULE_TYPE;
import com.wk.cd.module1.info.GroupModuleInfo;
import com.wk.cd.module1.info.ModuleBasicInfo;
import com.wk.cd.module1.info.ModuleInfo;
import com.wk.cd.module1.info.ParamInfo;
import com.wk.cd.module1.info.TemplateInfo;

/**
 * Class Description: 
 * @author zhangj
 */
public class GroupModuleInfo extends ModuleBasicInfo{
	
	
	
	public static final String GROUP_IDCN = "组件组ID";
	public static final String GROUP_ALIAS_NAMECN = "组件组别名";
	public static final String GROUP_CN_NAMECN = "组件组中文名";
	public static final String GROUP_BK_DESCCN = "组件组描述";
	public static final String MODULESCN = "组件组描述";
	public static final String PARAMSCN = "组件组参数列表";
	
	public int getModuleCount() {
		return this.modules.size();
	}
	 
	public void addModule(ModuleBasicInfo module) {
		this.modules.add(module);
	}
	
	public void addModules(List<ModuleBasicInfo> modules){
		this.modules.addAll(modules);
	}

	public Iterator<ModuleBasicInfo> moduleIterator() {
		return this.modules.iterator();
	}

	public List<ModuleBasicInfo> getModules() {
		return this.modules;
	}
	
	public void setModules(List<ModuleBasicInfo> modules) {
		this.modules.clear();
		this.addModules(modules);
	}
	
	public void cleanModules(){
		this.modules.clear();
	}
	
	public static GroupModuleInfo copyByBasic(ModuleBasicInfo info){
		GroupModuleInfo group = new GroupModuleInfo ();
		group.setAlias_name(info.getAlias_name());
		group.setBk_desc(info.getBk_desc());
		group.setCn_name(info.getCn_name());
		group.setId(info.getId());
		group.setType(MODULE_TYPE.GROUP);
		group.setModules(info.getModules());
		return group;
	}
	
	public static TemplateInfo getTemplateForInstance(String id){
		GroupModuleInfo group = XmlReader.readerGroup(id);
		List<ModuleBasicInfo> list = group.getModules();
		TemplateInfo template = new TemplateInfo ();
		List<ParamInfo> params = new ArrayList<ParamInfo>();
		for(ModuleBasicInfo info : list){
			 template.addSub_moduleInfo(ModuleInfo.copyToChild(info));
			 params.addAll(info.getRef_params());
		}
		params.addAll(Arrays.asList(group.getParams()));
		params = ParamInfo.PhaseNoMinus(params);
		template.setAll_params(params.toArray(new ParamInfo[params.size()]));
		return template;
	}

	/**
	 * 构造函数
	 */
	public GroupModuleInfo() {
		super();
	}

}
