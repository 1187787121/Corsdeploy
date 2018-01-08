/**
 * Title: ModuleInstanceGenerator.java
 * File Description: 
 * @copyright: 2017
 * @company: CORSWORK
 * @author: zhangj
 * @version: 1.0
 * @date: 2017年2月10日
 */
package com.wk.cd.module1.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.wk.cd.module1.bean.StageSourceBean;
import com.wk.cd.module1.exc.ModuleParamValueIllegitimateException;
import com.wk.cd.module1.info.ModuleInfo;
import com.wk.cd.module1.info.ModuleSourceInfo;
import com.wk.cd.module1.info.ParamInfo;
import com.wk.cd.remote.agent.util.AgentCommonTool;
import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.PROTOCOL_TYPE;
import com.wk.cd.module1.service.ModuleCommonService;
import com.wk.cd.module1.service.UserfulParamTools;
import com.wk.cd.system.dt.info.DtSourceInfo;
import com.wk.cd.system.dt.service.DtSocService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description:
 * cl项目中实例的生成类，这里主要考虑每个阶段如果有配置文件参数（每个参数自带数据源），投产包参数（不同投产包带有不同参数） 里面所有的参数
 * 包括阶段处理过程中 阶段号都是从“1”开始
 * @author zhangj
 */
public class ModuleInstanceGenerator {

	private static final Log logger = LogFactory.getLog();

	@Inject
	DtSocService dtSocService;
	@Inject
	ModuleCommonService moduleCommonService;






	/**
	 * Description: 单个组件实例化，、
	 * 确保组件里面的参数 在params里面都能找到对应的替换参数，
	 * 根据数据源的数量 返回对应多个实例化之后的阶段
	 * @param mi
	 * @param params
	 * @param ssb
	 * @return
	 */
	public List<ModuleInfo> moduleToInstance(ModuleInfo mi ,List<ParamInfo> params,StageSourceBean[] ssb){
		String[] cmds = mi.getCmds();
		List<String> r_cmds = new ArrayList<String>();
		if(!Assert.isEmpty(cmds)){
			for(String cmd : cmds){
				r_cmds.addAll(replaceCmd(cmd, params));
			}
		}
		
		ModuleInfo r_mi = ModuleInfo.copy(mi);
		r_mi.setCmds(r_cmds.toArray(new String[r_cmds.size()]));
		return mergeModuleSource(r_mi,ssb);
	}
	private List<String> replaceCmd(String cmd, List<ParamInfo> current_params) {
		logger.debug("替换命令[{}]中的参数", cmd);
		List<String> result = new ArrayList<String>();
		List<String> param_in_cmd = getParamInCmd(cmd);
		logger.debug("当前命令中用到的参数名[{}]", param_in_cmd);
		// 如果命令中没有需要替换的参数 则直接将这个命令和超时时限返回
		if (Assert.isEmpty(param_in_cmd)) {
			result.add(cmd);
			return result;
		}
		// 获取命令中有用的参数 并将这些参数分类组合返回
		List<List<ParamInfo>> params_needs = UserfulParamTools.getUsefulParam(param_in_cmd, current_params, cmd);
		for (List<ParamInfo> list : params_needs) {
			logger.debug("@@@@@@@@@@@@@@@");
			for (ParamInfo pp : list) {
				logger.debug(pp.toString());
			}
		}
		// 替换命令中的参数
		List<String> result_cmd = replaceParamInCmd(cmd, params_needs);

		return result_cmd;
	}

	private List<ModuleInfo> mergeModuleSource(ModuleInfo module, StageSourceBean[] ssb) {
		List<ModuleInfo> list = new ArrayList<ModuleInfo>();
		for (StageSourceBean bean : ssb) {
			ModuleInfo mi = ModuleInfo.copy(module);
			
			String exe_soc_name = bean.getExe_soc_name();
			String ver_soc_name = bean.getVer_soc_name();

			DtSourceInfo dt_source_info = new DtSourceInfo(); 
//			if(DtSourceInfo.AGENT_SOC_NAME.equals(exe_soc_name)){
			if(exe_soc_name.contains("agent") || DtSourceInfo.AGENT_SOC_NAME.equals(exe_soc_name)){
				dt_source_info.setProtocol_type(PROTOCOL_TYPE.AGENT);
				dt_source_info.setSoc_ip(bean.getExe_ip());
				dt_source_info.setSoc_port(AgentCommonTool.getPort());
				dt_source_info.setSoc_name(DtSourceInfo.AGENT_SOC_NAME);
//				dt_source_info.setSoc_port(soc_port);
			}else{
				dt_source_info = dtSocService.getInfoByKey(exe_soc_name);
			}
			if (Assert.isEmpty(ver_soc_name)) {
				ModuleSourceInfo info = new ModuleSourceInfo(dt_source_info.getProtocol_type(), dt_source_info);
				mi.setSource_info(info);
			} else {
				DtSourceInfo ver_source_info = dtSocService.getInfoByKey(ver_soc_name);
				ModuleSourceInfo info = new ModuleSourceInfo(dt_source_info, ver_source_info);
				mi.setSource_info(info);
			}
			mi.setCn_name(mi.getCn_name() + "_" + exe_soc_name+"_"+bean.getExe_ip());
			list.add(mi);

		}
		return list;
	}

	/**
	 * Description: 获取命令中参数列表
	 * @param cmd
	 * @return
	 */
	private List<String> getParamInCmd(String cmd) {
		List<String> param_list = new ArrayList<String>();
		// 正则表达式 匹配“${ was.was }”这种类别的其中空格可有可无
		String match_group = "\\$\\{\\s*\\w+\\.\\w+\\s*\\}";
		Pattern regex_group = Pattern.compile(match_group);
		Matcher m_group = regex_group.matcher(cmd);
		// 获取命令中匹配正则表达式的参数加入到列表中
		while (true) {
			if (!m_group.find()) {
				break;
			}
			String param_group = m_group.group().substring(2, m_group.group().length() - 1).trim();
			if (!param_list.contains(param_group)) {
				param_list.add(param_group);
			}

		}
		// 正则表达式 匹配“${ was }”这种类别的其中空格可有可无
		String match_single = "\\$\\{\\s*\\w+\\s*\\}";
		Pattern regex_single = Pattern.compile(match_single);
		Matcher m_single = regex_single.matcher(cmd);
		// 获取命令中匹配正则表达式的参数加入到列表中
		while (true) {
			if (!m_single.find()) {
				break;
			}
			String param_single = m_single.group().substring(2, m_single.group().length() - 1).trim();
			if (!param_list.contains(param_single)) {
				param_list.add(param_single);
			}

		}
		return param_list;
	}

	/**
	 * Description: 替换命令中的参数
	 * @param cmd
	 * @param params
	 * @return
	 */
	private List<String> replaceParamInCmd(String cmd, List<List<ParamInfo>> params) {
		List<String> result_cmd = new ArrayList<String>();
		if (Assert.isEmpty(params)) {
			result_cmd.add(cmd);
			return result_cmd;
		}
		for (List<ParamInfo> list : params) {
			String merged_cmd = replaceCmd1(list, cmd);
			result_cmd.add(merged_cmd);
		}

		return result_cmd;

	}

	/**
	 * Description: 替换具体命令
	 * @param list
	 * @param cmd
	 * @return
	 */
	private String replaceCmd1(List<ParamInfo> list, String cmd) {

		String cmd_temp = cmd;
		for (ParamInfo info : list) {
			String group_name = info.getParam_group();
			String match = "";
			if (Assert.isEmpty(group_name)) {
				match = "\\$\\{\\s*" + info.getParam_name() + "\\s*\\}";
			} else {
				match = "\\$\\{\\s*" + group_name + "." + info.getParam_name() + "\\s*\\}";
			}
			Pattern var_regex = Pattern.compile(match);
			Matcher m = var_regex.matcher(cmd_temp);
			if (m.find()) {
				try {
					cmd_temp = m.replaceAll(info.getParam_value());
				} catch (Exception e) {
					throw new ModuleParamValueIllegitimateException().addScene("VALUE", info.getParam_value());
				}
			}
		}
		return cmd_temp;
	}

	/**
	 * Description: 把所有的项目参数存放到map集合中便于根据参数的key值获取参数值，其中不包含各阶段自定义的参数
	 * @param project_params
	 * @return
	 */
	private Map<String, ParamInfo> mergeParam(List<ParamInfo> project_params) {
		Map<String, ParamInfo> map = new HashMap<String, ParamInfo>();
		if (!Assert.isEmpty(project_params)) {
			for (ParamInfo info : project_params) {
				if (!Assert.isEmpty(info.getRef())) {
					continue;
				}
				map.put(info.getParam_name(), info);
			}
		}
		return map;

	}







	public List<ParamInfo> getConfigPhaseParam(int index, List<ParamInfo> project_params, ParamInfo[] all_params,
			List<ParamInfo> config_params, List<String> config_ip) {
		List<ParamInfo> list = new ArrayList<ParamInfo>();
		for (ParamInfo info : config_params) {
			String ip = info.getNode();
			logger.debug("current config_param ip[{}]", ip);
			if (config_ip.contains(ip)) {
				list.add(info);
			}
		}

		List<ParamInfo> c_list = new ArrayList<ParamInfo>();
		if (!Assert.isEmpty(all_params)) {
			for (ParamInfo info : all_params) {
				if (info.getPhase_no() == null) {
					continue;
				} else if (info.getPhase_no() + 1 == index) {
					c_list.add(info);
				}

			}
		}
		if (!Assert.isEmpty(c_list)) {// 如果有重定义的参数 则替换现有项目参数中的参数值
			List<ParamInfo> all_param = new ArrayList<ParamInfo>();
			all_param.addAll(project_params);
			Map<String, ParamInfo> map = mergeParam(all_param);
			for (ParamInfo param : c_list) {
				ParamInfo pi = ParamInfo.copy(param);
				String ref = param.getRef();
				if (!Assert.isEmpty(ref) && map.containsKey(ref)) {
					pi.setParam_value(map.get(ref).getParam_value());
					list.add(pi);
					map.remove(ref);
				}
			}
			for (Map.Entry<String, ParamInfo> m : map.entrySet()) {
				list.add(m.getValue());
			}

		} else {
			list.addAll(project_params);
		}
		return list;
	}

}
