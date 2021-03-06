/**
 * Title: XmlService.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: zhangj
 * @version: 1.0
 * @date: 2016年11月14日
 */
package com.wk.cd.module1.xml;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.MODIFY_FLAG;
import com.wk.cd.enu.MODULE_TYPE;
import com.wk.cd.enu.YN_FLAG;
import com.wk.cd.module1.entity.Component;
import com.wk.cd.module1.entity.ComponentGroup;
import com.wk.cd.module1.entity.Param;
import com.wk.cd.module1.entity.PhaseParam;
import com.wk.cd.module1.info.GroupModuleInfo;
import com.wk.cd.module1.info.ModuleBasicInfo;
import com.wk.cd.module1.info.ParamInfo;
import com.wk.cd.module1.xml1.XmlReader;

/**
 * Class Description:
 * @author "Zhangj"
 */
public class XmlUtils {

	private static final Pattern PARAM_PATTERN = Pattern.compile("\\$\\{\\s*\\w+\\s*\\}");
//	private static final Pattern GROUP_PARAM_PATTERN = Pattern.compile("\\$\\{\\s*\\w+\\.\\w+\\s*\\}");

	/**
	 * Description: 根据ID和类型获取其下面的参数列表
	 * @param id
	 * @param comp_type
	 * @return
	 */
	public static List<ParamInfo> getParamById(String id, MODULE_TYPE comp_type) {
		List<ParamInfo> params= new ArrayList<ParamInfo>();
		if (MODULE_TYPE.COMPONENT.equals(comp_type)) {
			Component component = new Component(id);
			component = XmlReader.read(component);
			List<Param> param = component.getParam_list();
			if(!Assert.isEmpty(param)){
				for (Param pm : param) {
					ParamInfo info = new ParamInfo();
					info.setParam_name(pm.getParam_name());
					info.setParam_value(pm.getParam_value());
					info.setParam_cn_name(pm.getParam_bk_desc());
				    info.setGen_flag(YN_FLAG.YES);
				    params.add(info);
				}
			}
		} else if (MODULE_TYPE.GROUP.equals(comp_type)) {
			ComponentGroup group = new ComponentGroup(id);
			group = XmlReader.read(group);
			List<PhaseParam> param_group = group.getParam_list();
			if(!Assert.isEmpty(param_group)){
				for (PhaseParam pm_gp : param_group) {
					ParamInfo info = new ParamInfo();
					info.setParam_name(pm_gp.getParam_name());
					info.setParam_value(pm_gp.getParam_value());
					info.setParam_cn_name(pm_gp.getParam_bk_desc());
				    info.setGen_flag(YN_FLAG.YES);
				    params.add(info);
				}
			}
		}
		return params;
	}

	
//	/**
//	 * Description: 根据传入的组件、阶段、组件组列表合并里面参数
//	 * @param list
//	 * @param Params
//	 * @return
//	 */
//	public static List<ParamInfo> combineParams(List<ModuleBasicInfo> modules, ParamInfo[] oldParams) {
//		Map<String, ParamInfo> params = new HashMap<String, ParamInfo>();
//		List<ParamInfo> result_params = new ArrayList<ParamInfo>();
//		List<ParamInfo> all_params = new ArrayList<ParamInfo>();
//		for (ModuleBasicInfo module : modules) {
//			MODULE_TYPE type = module.getType();
//			if (MODULE_TYPE.COMPONENT.equals(type) && !Assert.isEmpty(module.getParams())) {
//				all_params.addAll(getEffectiveParamsFromModule(module));
//			} else if (MODULE_TYPE.GROUP.equals(type) && !Assert.isEmpty(module.getParams())) {
//				all_params.addAll(getEffectiveParamsFromGroup(module));
//			} else if (MODULE_TYPE.PHASE.equals(type)) {
//				all_params.addAll(getEffectiveParamsFromPhase(module));
//			}
//		}
//		all_params = mergerParams(all_params);
//		if (!Assert.isEmpty(all_params)) {
//			for (ParamInfo param : all_params) {
//				params.put(param.getParam_name(), param);
//			}
//		}
//		if (!Assert.isEmpty(oldParams)) {
//			for (ParamInfo param : oldParams) {
//				if (param.isHand_param() || params.containsKey(param.getParam_name())) {
//					params.put(param.getParam_name(), param);
//				}
//			}
//		}
//		if (!Assert.isEmpty(params)) {
//			for (ParamInfo param : params.values()) {
//				result_params.add(param);
//			}
//		}
//		return result_params;
//	}

//	/**
//	 * Description: 获取阶段中的有效参数
//	 * @param module
//	 * @return
//	 */
//	private static List<ParamInfo> getEffectiveParamsFromPhase(ModuleBasicInfo phase) {
//		List<ParamInfo> params = matchParams(phase.getCmds());
//		params = uniqueParams(params);
//		params = removePkgs(params);
//		return params;
//	}

	/**
	 * Description: 获取组件中的有效参数
	 * @param module
	 * @return
	 */
	private static List<ParamInfo> getEffectiveParamsFromModule(ModuleBasicInfo module) {
		List<ParamInfo> params = new ArrayList<ParamInfo>();
		if (Assert.isEmpty(module.getRef_params())) {
			params.addAll(Arrays.asList(module.getParams()));
		} else {
			List<String> ref_params = new ArrayList<String>();
			for (ParamInfo p : module.getRef_params()) {
				ref_params.add(p.getParam_name());
			}
			for (ParamInfo p : module.getParams()) {
				if (!ref_params.contains(p.getParam_name())) {
					params.add(p);
				}
			}
		}
		return params;
	}

//	/**
//	 * Description: 获取组件组中的有效参数
//	 * @param module
//	 * @return
//	 */
//	private static Collection<ParamInfo> getEffectiveParamsFromGroup(ModuleBasicInfo group) {
//		Map<String, ParamInfo> params = new HashMap<String, ParamInfo>();
//		List<ParamInfo> cmd_params = new ArrayList<ParamInfo>();
//		List<ParamInfo> module_params = new ArrayList<ParamInfo>();
//		ParamInfo[] group_params = group.getParams();
//		Set<String> param_name = getAllRefParams(group.getModules());
//		List<ModuleBasicInfo> modules = group.getModules();
//		for (ModuleBasicInfo module : modules) {
//			if (module.getType() == MODULE_TYPE.COMPONENT) {
//				module_params.addAll(getEffectiveParamsFromModule(module));
//			} else if (module.getType() == MODULE_TYPE.PHASE) {
//				cmd_params.addAll(getEffectiveParamsFromPhase(module));
//			}
//		}
//		if (!Assert.isEmpty(cmd_params)) {
//			for (ParamInfo cmd_param : cmd_params) {
//				params.put(cmd_param.getParam_name(), cmd_param);
//			}
//		}
//		if (!Assert.isEmpty(module_params)) {
//			for (ParamInfo module_param : module_params) {
//				params.put(module_param.getParam_name(), module_param);
//			}
//		}
//		if (!Assert.isEmpty(group_params)) {
//			for (ParamInfo group_param : group_params) {
//				if (param_name.contains(group_param.getParam_name())) {
//					params.put(group_param.getParam_name(), group_param);
//				}
//			}
//		}
//		return params.values();
//	}

	/**
	 * Description: 获取所有引用参数名
	 * @param modules
	 * @return
	 */
	private static Set<String> getAllRefParams(List<ModuleBasicInfo> modules) {
		Set<String> param_names = new HashSet<String>();
		for (ModuleBasicInfo module : modules) {
			if (module.getType() == MODULE_TYPE.COMPONENT) {
				if (!Assert.isEmpty(module.getRef_params())) {
					for (ParamInfo p : module.getRef_params()) {
						param_names.add(p.getRef());
					}
				}

			} else if (module.getType() == MODULE_TYPE.GROUP) {
				param_names.addAll(getAllRefParams(module.getModules()));
			}
		}
		return param_names;
	}

	/**
	 * Description: 把传入的所有参数去重，优先选择有中文名和描述的及默认值的
	 * @param params
	 * @return
	 */
	private static List<ParamInfo> mergerParams(List<ParamInfo> params) {
		List<ParamInfo> result_params = new ArrayList<ParamInfo>();
		Map<String, ParamInfo> map = new HashMap<String, ParamInfo>();
		if (!Assert.isEmpty(params)) {
			for (ParamInfo p : params) {
				if (p.getParam_group() == null) {
					p.setParam_group("");
				}
				String name = p.getParam_name();
				if (map.containsKey(name)) {
					map.put(name, mergerParam(map.get(name), p));
				} else {
					map.put(name, p);
				}
			}
		}
		for (ParamInfo p : map.values()) {
			result_params.add(p);
		}
		return result_params;
	}

	private static ParamInfo mergerParam(ParamInfo p, ParamInfo p1) {
		String cn_name = Assert.isEmpty(p.getParam_cn_name()) ? p1.getParam_cn_name() : p.getParam_cn_name();
		String bk_desc = Assert.isEmpty(p.getParam_bk_desc()) ? p1.getParam_bk_desc() : p.getParam_bk_desc();
		String value = Assert.isEmpty(p.getParam_value()) ? p1.getParam_value() : p.getParam_value();
		boolean hand = p.isHand_param() || p1.isHand_param();
		MODIFY_FLAG modify = (p.getParam_modify_flag() == p1.getParam_modify_flag()) ? p.getParam_modify_flag()
				: MODIFY_FLAG.NO;
		ParamInfo param = ParamInfo.copy(p);
		param.setParam_bk_desc(bk_desc);
		param.setParam_cn_name(cn_name);
		param.setParam_value(value);
		param.setHand_param(hand);
		param.setParam_modify_flag(modify);
		return param;

	}

	public static List<PhaseParam> matchParams(String[] cmds) {
		String cmd = Arrays.toString(cmds);
		return matchParams(cmd);
	}

	public static List<PhaseParam> matchParams(String cmd) {
		List<PhaseParam> param_list = new ArrayList<PhaseParam>();
		Matcher param_matcher = PARAM_PATTERN.matcher(cmd);
		while (param_matcher.find()) {
			String param_str = param_matcher.group();
			String param_name = param_str.substring(2, param_str.length() - 1).trim();
			PhaseParam param_info = new PhaseParam();
			param_info.setParam_name(param_name);
			param_list.add(param_info);
		}
//		Matcher group_param_matcher = GROUP_PARAM_PATTERN.matcher(cmd);
//		while (group_param_matcher.find()) {
//			String param_str = group_param_matcher.group();
//			param_str = param_str.substring(2, param_str.length() - 1).trim();
//			String[] param = param_str.split("\\.");
//			ParamInfo param_info = new ParamInfo();
//			param_info.setParam_group(param[0]);
//			param_info.setParam_name(param[1]);
//			param_list.add(param_info);
//		}
		return param_list;
	}

	//根据脚本生成param
	public static List<Param> matchParams1(String[] cmds) {
		String cmd = Arrays.toString(cmds);
		return matchParams1(cmd);
	}

	public static List<Param> matchParams1(String cmd) {
		List<Param> param_list = new ArrayList<Param>();
		Matcher param_matcher = PARAM_PATTERN.matcher(cmd);
		while (param_matcher.find()) {
			String param_str = param_matcher.group();
			String param_name = param_str.substring(2, param_str.length() - 1).trim();
			Param param_info = new Param();
			param_info.setParam_name(param_name);
			param_list.add(param_info);
		}
		return param_list;
	}
	
	public static List<ParamInfo> uniqueParams(List<ParamInfo> params) {
		Set<String> set = new HashSet<String>();
		List<ParamInfo> result = new ArrayList<ParamInfo>();
		if (!Assert.isEmpty(params)) {
			for (ParamInfo p : params) {
				if (set.add(p.getParam_name())) {
					result.add(p);
				}
			}
		}
		return result;
	}
	
	private static List<ParamInfo> removePkgs(List<ParamInfo> params) {
		List<ParamInfo> result = new ArrayList<ParamInfo>();
		if (!Assert.isEmpty(params)) {
			for (ParamInfo p : params) {
				if (!"pkg".equalsIgnoreCase(p.getParam_group())) {
					result.add(p);
				}
			}
		}
		return result;
	}

	public static List<ParamInfo> removeParams(List<ParamInfo> original_params, List<ParamInfo> removed_params) {
		List<String> removed_param_names = new ArrayList<String>();
		List<ParamInfo> result = new ArrayList<ParamInfo>();
		if (!Assert.isEmpty(removed_params)) {
			for (ParamInfo paramInfo : removed_params) {
				removed_param_names.add(paramInfo.getParam_name());
			}
		}
		if (!Assert.isEmpty(original_params)) {
			for (ParamInfo paramInfo : original_params) {
				if (!removed_param_names.contains(paramInfo.getParam_name())) {
					result.add(paramInfo);
				}
			}
		}
		return result;
	}

	public static String[] replaceRefParams(String[] cmds, List<ParamInfo> ref_params) {
		if (Assert.isEmpty(ref_params)) {
			return cmds;
		}
		String[] new_cmds = Arrays.copyOf(cmds,cmds.length);
		for (ParamInfo ref_param : ref_params) {
			String param_name = ref_param.getParam_name();
			String ref = ref_param.getRef();
			Pattern param_pattern = Pattern.compile("\\$\\{\\s*" + param_name + "\\s*\\}");
			Pattern group_param_pattern = Pattern.compile("\\$\\{\\s*\\w+\\." + param_name + "\\s*\\}");
			for (int i = 0; i < new_cmds.length; i++) {
				String cmd = new_cmds[i];
				Matcher group_param_matcher = group_param_pattern.matcher(cmd);
				if (group_param_matcher.find()) {
					String param_str = group_param_matcher.group();
					param_str = param_str.substring(2, param_str.length() - 1).trim();
					String[] param = param_str.split("\\.");
					String group_name = param[0];
					cmd = group_param_matcher.replaceAll("\\${"+group_name+"."+ref+"}");
				} else {
					cmd = param_pattern.matcher(cmd).replaceAll("\\${"+ref+"}");
				}
				new_cmds[i] = cmd;
			}
		}
		return new_cmds;
	}

	public static Map<Integer, List<ParamInfo>> getRefParamsMap(List<ParamInfo> params) {
		Map<Integer, List<ParamInfo>> map = new HashMap<Integer, List<ParamInfo>>();
		if (!Assert.isEmpty(params)) {
			for (ParamInfo param : params) {
				Integer phase_no = param.getPhase_no();
				if (map.containsKey(phase_no)) {
					map.get(phase_no).add(param);
				} else {
					List<ParamInfo> param_list = new ArrayList<ParamInfo>();
					param_list.add(param);
					map.put(phase_no, param_list);
				}
			}
		}
		return map;
	}

}
