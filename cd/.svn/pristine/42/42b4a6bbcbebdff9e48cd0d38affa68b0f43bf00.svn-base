package com.wk.cd.module1.info;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.wk.cd.module1.Env;
import com.wk.cd.module1.info.ModuleInfo;
import com.wk.cd.module1.info.ParamInfo;
import com.wk.cd.module1.info.TemplateInfo;

/**
 * Class Description: ÊµÀý
 * @author "Zhangj"
 */
public class InstanceInfo {
 
	protected final String instanceId;

	protected final TemplateInfo template;

	protected final Env env;
	
	private ParamInfo[] params;

	protected final List<ModuleInfo> modules = new ArrayList<ModuleInfo>();

	public InstanceInfo(String instanceId, TemplateInfo template, Env env) {
		this.instanceId = instanceId;
		this.env = env;
		this.template = template;
	}

	public String getInstanceId() {
		return instanceId;
	}

	public Env getEnv() {
		return env;
	}


	public int getModuleCount() {
		return modules.size();
	}

	public void addModuleInfo(ModuleInfo info) {
		modules.add(info);
	}

	public void addModuleInfos(List<ModuleInfo> infos){
		modules.addAll(infos);
	}

	public Iterator<ModuleInfo> moduleIterator() {
		return modules.iterator();
	}

	public List<ModuleInfo> getModuleInfos() {
		return modules;
	}

	public ParamInfo[] getParams() {
		return params;
	}

	public void setParams(ParamInfo[] params) {
		this.params = params;
	}

	public TemplateInfo getTemplate() {
		return template;
	}

	public List<ModuleInfo> getModules() {
		return modules;
	}

}
 
