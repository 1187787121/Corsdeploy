package com.wk.cd.module1.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wk.cd.module1.Env;
import com.wk.cd.module1.ParamUtil;
import com.wk.cd.module1.info.ModuleSourceInfo;
import com.wk.cd.module1.info.Param;
import com.wk.cd.module1.info.ParamInfo;
import com.wk.cd.common.util.Assert;
import com.wk.util.StringUtil;

/**
 * 运行环境实现
 */
public class DefaultEnv implements Env {
 
	private final Map<String, Param> params = new HashMap<String, Param>();

	private final List<ParamInfo> infos = new ArrayList<ParamInfo>();

	private final Map<Integer, List<ModuleSourceInfo>> stage_nodes = new HashMap<Integer, List<ModuleSourceInfo>>();

	private final Map<String, Map<String, Param>> group_params = new HashMap<String, Map<String, Param>>();

	@Override
	public Param getParam(String name) {
		return params.get(name);
	}

	@Override
	public void setParam (String name, Param param) {
		String group = ParamUtil.resolveGroup(name);
		if (!StringUtil.isEmpty(group)) {
			Map<String, Param> gps = group_params.get(group);
			if (gps == null) {
				gps = new HashMap<String, Param>();
				group_params.put(group, gps);
			}
			gps.put(name, param);
		}
		params.put(name, param);
	}

	@Override
	public void setParamInfos(ParamInfo[] infos) {
		this.infos.clear();
		if(!Assert.isEmpty(infos)){
			for (ParamInfo info : infos) {
				this.infos.add(info);
			}
		}
		
	}

	@Override
	public Param getParam(int stage, String name) {
		ParamInfo ref = getRefParamInfo(stage, name);
		if (ref == null || StringUtil.isEmpty(ref.getRef())) {
			return null;
		}
		String param_name = StringUtil.isEmpty(ref.getParam_group()) ? ref.getRef() : ref.getParam_group()+"."+ref.getRef();
		Param rp = params.get(param_name);
		if (rp == null) {
			return null;
		}
		Param p = new Param(ref);
		p.setListValue(rp.getListValue());
		p.setSingleValue(rp.getSingleValue());
		return p;
	}

	private ParamInfo getRefParamInfo(int stage, String name) {
		for (ParamInfo info: infos) {
			Integer i = info.getPhase_no();
			if (i != null && stage == i && info.getParam_name().equals(name)) {
				return info;
			}
		}
		return null;
	}

	public Map<String, Param> getParams() {
		return params;
	}

	public Map<String, Param> getGroupParams(String group) {
		if (StringUtil.isEmpty(group)) {
			return null;
		}
		return group_params.get(group);
	}

	@Override
	public void addStageNode(int stage, ModuleSourceInfo soc_info) {
		List<ModuleSourceInfo> node_sources = stage_nodes.get(stage);
		if (node_sources == null) {
			node_sources = new ArrayList<ModuleSourceInfo>();
			stage_nodes.put(stage, node_sources);
		}
		node_sources.add(soc_info);
	}

	@Override
	public List<ModuleSourceInfo> getStageNodes(int stage) {
		return stage_nodes.get(stage);
	}
}
 
