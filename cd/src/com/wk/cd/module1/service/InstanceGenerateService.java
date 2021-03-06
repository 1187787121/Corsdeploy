/**
 * Title: InstanceGenerateService.java
 * File Description: 
 * @copyright: 2017
 * @company: CORSWORK
 * @author: Administrator
 * @version: 1.0
 * @date: 2017年8月17日
 */
package com.wk.cd.module1.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.wk.cd.module1.bean.StageSourceBean;
import com.wk.cd.module1.entity.Instance;
import com.wk.cd.module1.entity.InstancePhase;
import com.wk.cd.module1.exc.ModuleParamValueIllegitimateException;
import com.wk.cd.module1.info.ModuleSourceInfo;
import com.wk.cd.module1.entity.Phase;
import com.wk.cd.module1.entity.PhaseParam;
import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.DESUtil;
import com.wk.cd.module1.service.ModuleCommonService;
import com.wk.cd.exc.CmdParamIsNotExistException;
//import com.wk.cd.remote.agent.util.AgentCommonTool;
import com.wk.cd.system.dt.info.DtSourceInfo;
import com.wk.cd.system.dt.service.DtSocService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description:
 * @author zhangj
 */
public class InstanceGenerateService {
	private static final Log logger = LogFactory.getLog();

	@Inject
	DtSocService dtSocService;
	@Inject
	ModuleCommonService moduleCommonService;

	/**
	 * Description:
	 * @param phase_list 需要生成的所有阶段列表 这个里面需要包含命令 需要执行的数据源
	 * @param params 阶段命令中需要替换的参数
	 * @param system_params 系统参数，用来替换所有参数中应用的参数 可以为null
	 * @param instance_id 需要生成实例的ID可以为null
	 * @param config_param 配置文件参数，可以为null
	 * @param node_names 需要生成的节点ip列表，如果存在则和阶段中的数据源交集生成，也可以为null
	 * @return
	 */
	public Instance phaseListGenerate(List<Phase> phase_list, List<PhaseParam> params, List<PhaseParam> system_params, String instance_id, List<String> node_names) {
		Assert.assertNotEmpty(phase_list, "阶段");
		if (Assert.isEmpty(instance_id)) {
			instance_id = getInstanceId();
		}
		Instance instance = new Instance(instance_id);
		List<PhaseParam> params_temp = PhaseParam.copy(params);

		logger.debug("phaseListGenerate begin[{}]", instance_id);
		int o_phase_id = 1;
		int phase_id = 1;
		for (Phase phase : phase_list) {
			List<InstancePhase> modules = phaseGenerate(phase, params, system_params, null, node_names).getPhase();
			if (!Assert.isEmpty(modules)) {
				for (InstancePhase p : modules) {
					p.setOriginal_phase_id(o_phase_id);
					p.setPhase_no(phase_id);
					instance.addPhase(p);
					phase_id++;
				}
			}
			o_phase_id++;
		}

		// 参数解密
		decryPhaseParam(params_temp);
		instance.setParam_list(params_temp);
		logger.debug("phaseListGenerate end[{}]", instance_id);
		return instance;
	}

	/**
	 * Description:
	 * @param phase 必须的
	 * @param params 需要替换的参数 可以为空 但是如果phase命令中有参数 这里没找到 则报错
	 * @param system_params 可以为空
	 * @param instance_id 可以为空
	 * @param config_param 配置参数 可以为空
	 * @param node_names 节点列表 可以为空
	 * @return TODO：将配置文件阶段实例化提取出来
	 */
	public Instance phaseGenerate(Phase phase, List<PhaseParam> params, List<PhaseParam> system_params, String instance_id, List<String> node_names) {
		Assert.assertNotEmpty(phase, "阶段");
		if (Assert.isEmpty(instance_id)) {
			instance_id = getInstanceId();
		}
		Instance instance = new Instance(instance_id);
		// copy一份全局参数，以防在下面修改参数的时候会改掉params中的数据
		// 这里的参数是整个项目的参数，并不是某个阶段的参数，并没有经过筛选
		List<PhaseParam> params_temp = PhaseParam.copy(params);
		// 每个阶段中的命令，此时命令中应包含未替换的参数
		String[] cmds = phase.getScript().getCmds();
		// 每个阶段配置的数据源list
		List<StageSourceBean> source_list = phase.getSrv_soc();
		if (Assert.isEmpty(source_list)) {
			logger.debug("阶段中没有数据源，****不能生成阶段***");
			return instance;
		}
		// 这里之所以没有替换完成命令后再弄阶段信息 因为考虑到配置文件的阶段生成
		// 对于多个数据源需要重复生成多个该阶段
		for (StageSourceBean source : source_list) {
			String ip = source.getExe_ip();
			if (!Assert.isEmpty(node_names) && !node_names.contains(ip)) {
				logger.debug("IP[{}]不在节点列表[{}]中 不生成当前阶段", ip, node_names);
				continue;
			}
			// 生成阶段实例
			InstancePhase instance_phase = new InstancePhase();
			instance_phase.setImpl_type(phase.getImpl_type());
			instance_phase.setPhase_name(phase.getPhase_name());
			instance_phase.setInteractor_flag(phase.isInteractor_flag());
			instance_phase.setParallel_flag(phase.isParallel_flag());
			// project参数(最后将全局参数、配置文件参数和系统参数整合到一起)
			List<PhaseParam> proj_params = PhaseParam.copy(params);
			List<PhaseParam> sys_params = PhaseParam.copy(system_params);
			// 替换参数中所有的系统参数
			if (!Assert.isEmpty(proj_params)) {
				// 替换参数值里面的系统参数（将某些引用系统参数的值先替换，此处并没有替换命令中的参数）
				proj_params = replaceParamValue(proj_params, sys_params);
				if (!Assert.isEmpty(sys_params)) {
					proj_params.addAll(sys_params);
				}
				// 获取阶段中所有的引用参数并将该参数附上值
				List<PhaseParam> ref_params = PhaseParam.copy(phase.getRef_param_list());
				// 把阶段中所有的引用参数的值替换成当前需要的值
				proj_params = replaceRefParam(proj_params, ref_params);
			} else {
				if (!Assert.isEmpty(sys_params)) {
					proj_params.addAll(sys_params);
				}
			}
			// 获取命令 并替换参数
			String[] c_s = Arrays.copyOf(cmds, cmds.length);
			
			// 替换命令里面所有的参数
			List<String> cmds_replaced = replaceCmds(c_s, proj_params);
		
			logger.debug("####SOURCE####:[{}]",source.toString());
			ModuleSourceInfo module_source_info = getMouduleSouceInfo(source);
			instance_phase.addCmds(cmds_replaced.toArray(new String[cmds_replaced.size()]));
			instance_phase.setModule_source_info(module_source_info);
//			instance_phase.setPhase_name(ip + "_" + phase.getPhase_name());
			instance_phase.setPhase_name(phase.getPhase_name());
			instance.addPhase(instance_phase);
		}

		// 参数解密
		decryPhaseParam(params_temp);
		instance.setParam_list(params_temp);

		return instance;
	}

	/**
	 * Description: 生成实例的ID如果生成实例的时候没有传入ID，则后台自动生成ID
	 * @return
	 */
	private String getInstanceId() {
		String name = UUID.randomUUID().toString();
		name = name.replaceAll("-", "");
		return name;
	}

	/**
	 * Description: 多条命令的参数替换
	 * @param cmds
	 * @param params
	 * @return
	 */
	private List<String> replaceCmds(String[] cmds, List<PhaseParam> params) {
		List<String> new_cmds = new ArrayList<String>();
		if (!Assert.isEmpty(cmds)) {
			for (String cmd : cmds) {
				if (Assert.isEmpty(cmd)) {
					continue;
				}
				new_cmds.addAll(replaceCmd(cmd, params));
			}
		}

		return new_cmds;

	}

	/**
	 * Description: 替换单条命令 如果一个参数有多个值 则生成多条命令
	 * @param cmd
	 * @param params
	 * @return
	 */
	private List<String> replaceCmd(String cmd, List<PhaseParam> params) {
		logger.debug("替换命令[{}]中的参数", cmd);
		List<String> result = new ArrayList<String>();
		// 返回参数，比如参数为“/home/${A}/${B}”，就会返回List<String>{A, B}
		List<String> param_in_cmd = getParamInCmd(cmd);
		logger.debug("当前命令中用到的参数名[{}]", param_in_cmd);
		// 如果命令中没有需要替换的参数 则直接将这个命令和超时时限返回
		if (Assert.isEmpty(param_in_cmd)) {
			result.add(cmd);
			return result;
		}
		// 获取命令中有用的参数 并将这些参数分类组合返回
		if (Assert.isEmpty(params)) {
			throw new CmdParamIsNotExistException().addScene("PARAM", param_in_cmd.get(0));
		}
		List<List<PhaseParam>> params_needs = PhaseParamTools.getUsefulParam(param_in_cmd, params);

		// 替换命令中的参数
		List<String> result_cmd = replaceParamInCmd(cmd, params_needs);

		return result_cmd;
	}

	/**
	 * 一条命令中可能有多个参数 Description: 获取命令中参数列表
	 * @param cmd
	 * @return
	 */
	private List<String> getParamInCmd(String cmd) {

		List<String> param_list = new ArrayList<String>();
		if (Assert.isEmpty(cmd)) {
			return param_list;
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
	private List<String> replaceParamInCmd(String cmd, List<List<PhaseParam>> params) {
		List<String> result_cmd = new ArrayList<String>();
		if (Assert.isEmpty(params)) {
			result_cmd.add(cmd);
			return result_cmd;
		}
		for (List<PhaseParam> list : params) {
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
	private String replaceCmd1(List<PhaseParam> list, String cmd) {
		String cmd_temp = cmd;
		for (PhaseParam info : list) {
			String match = "";
			match = "\\$\\{\\s*" + info.getParam_name() + "\\s*\\}";
			Pattern var_regex = Pattern.compile(match);
			Matcher m = var_regex.matcher(cmd_temp);
			if (m.find()) {
				try {
					String value = info.getParam_value();
					Assert.assertNotEmpty(value, info.getParam_name());
					value = value.replace("\\", "\\\\");
					value = value.replace("$", "\\$");
					value = value.trim();
					cmd_temp = m.replaceAll(value);

				} catch (Exception e) {
					throw new ModuleParamValueIllegitimateException().addScene("VALUE", info.getParam_value());
				}
			}
		}
		return cmd_temp;
	}

	/**
	 * Description: 替换参数中用到的系统参数，将参数值中引用到的系统参数 替换为系统参数的值
	 * 这里都是引用参数，params参数中的值就是system_param参数中的键
	 * @param params
	 * @param system_param 需要替换的系统参数，如果没有系统参数则传入null
	 * @return
	 */
	private List<PhaseParam> replaceParamValue(List<PhaseParam> params, List<PhaseParam> system_param) {
		logger.debug("replaceParamValue begine");
		if (Assert.isEmpty(params) || Assert.isEmpty(system_param)) {
			logger.debug("no param");
			return params;
		}
		List<PhaseParam> list = new ArrayList<PhaseParam>();
		for (PhaseParam info : params) {
			String value = info.getParam_value();
			PhaseParam pi = PhaseParam.copy(info);
			List<String> values = replaceCmd(value, system_param);
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < values.size(); i++) {
				String str = values.get(i);
				if (i == values.size() - 1) {
					sb.append(str);
				} else {
					sb.append(str + ";");
				}
			}
			pi.setParam_value(sb.toString());
			list.add(pi);
		}
		logger.debug("replaceParamValue end");
		return list;
	}

	/**
	 * Description: 把阶段中所有的引用参数的值替换成当前需要的值
	 * @param params 所有全局参数和系统参数的param_name+param_value
	 * @param ref_params 所有的引用参数
	 * @return
	 */
	private List<PhaseParam> replaceRefParam(List<PhaseParam> params, List<PhaseParam> ref_params) {
		List<PhaseParam> new_params = PhaseParam.copy(params);
		if (!Assert.isEmpty(ref_params)) {
			Map<String, PhaseParam> maps = mergeByParamName(params);
			for (PhaseParam info : ref_params) {
				PhaseParam pi = PhaseParam.copy(info);
				String param_name = info.getRef();
				PhaseParam param = maps.get(param_name);
				if (Assert.isEmpty(param)) {
					throw new RuntimeException(param_name + "未找到引用的值");
				}
				//当引用参数的param_name在全局参数中时，直接替换参数的param_value（一般应用参数的ref是在全局参数中的）
				if(!maps.containsKey(info.getParam_name())) {
					pi.setParam_value(param.getParam_value());
					new_params.add(pi);
				} else {
					if(!Assert.isEmpty(new_params)) {
						for (PhaseParam phaseParam : new_params) {
							if(phaseParam.getParam_name().equals(info.getParam_name())) {
								phaseParam.setParam_value(param.getParam_value());
							}
						}
					}
				}
			}
		}
		return new_params;

	}

	private Map<String, PhaseParam> mergeByParamName(List<PhaseParam> params) {
		List<PhaseParam> new_params = PhaseParam.copy(params);
		Map<String, PhaseParam> maps = new HashMap<String, PhaseParam>();
		if (!Assert.isEmpty(new_params)) {
			for (PhaseParam info : new_params) {
				maps.put(info.getParam_name(), info);
			}
		}
		return maps;

	}

	/**
	 * Description: 获取ModuleSourceInfo
	 * @param bean
	 * @return
	 */
	private ModuleSourceInfo getMouduleSouceInfo(StageSourceBean bean) {
		if (Assert.isEmpty(bean.getExe_soc_name())) {
			return null;
		}
		String exe_soc_name = bean.getExe_soc_name();
		String ver_soc_name = bean.getVer_soc_name();

		DtSourceInfo dt_source_info = new DtSourceInfo();
	
		/*if(DtSourceInfo.AGENT_SOC_NAME.equals(exe_soc_name)){
		  if(exe_soc_name.contains("agent") ||
		  DtSourceInfo.AGENT_SOC_NAME.equals(exe_soc_name)){
		  dt_source_info.setProtocol_type(PROTOCOL_TYPE.AGENT);
		  dt_source_info.setSoc_ip(bean.getExe_ip());
		  dt_source_info.setSoc_port(AgentCommonTool.getPort());
		  dt_source_info.setSoc_name(DtSourceInfo.AGENT_SOC_NAME); //
		  dt_source_info.setSoc_port(soc_port); }else{ dt_source_info =
		  dtSocService.getInfoByKey(exe_soc_name); 
		}*/
		 
		logger.debug("#######EXE_SOC_NAME:[{}],EXE_IP:[{}]",exe_soc_name,bean.getExe_ip());
		dt_source_info = dtSocService.getInfoByKey(exe_soc_name,bean.getExe_ip());
		if (Assert.isEmpty(ver_soc_name)) {
			ModuleSourceInfo info = new ModuleSourceInfo(dt_source_info.getProtocol_type(), dt_source_info);
			info.setExe_server_name(bean.getExe_server_name());
			info.setExe_soc_name(exe_soc_name);
			return info;
		} else {
			DtSourceInfo ver_source_info = dtSocService.getInfoByKey(ver_soc_name,bean.getVer_ip());
			ModuleSourceInfo info = new ModuleSourceInfo(dt_source_info, ver_source_info);
			info.setExe_server_name(bean.getExe_server_name());
			info.setVer_server_name(bean.getVer_server_name());
			info.setExe_soc_name(exe_soc_name);
			info.setVer_soc_name(ver_soc_name);
			return info;
		}
	}

	/**
	 * Description: 如果命令中有配置文件的参数 则这里认为 这个阶段就是配置文件的阶段
	 * @param cmds1
	 * @return
	 */
	private boolean checkConfigCmd(String[] cmds1) {
		String[] cmds = Arrays.copyOf(cmds1, cmds1.length);
		for (String cmd : cmds) {
			List<String> list = getParamInCmd(cmd);
			if (list.contains(PhaseParam.CONFIG_LOCAL) || list.contains(PhaseParam.CONFIG_REMOTE)) {
				logger.debug("当前阶段是配置文件阶段");
				return true;
			}
		}
		return false;

	}

	public void decryPhaseParam(List<PhaseParam> params_temp) {
		// 在写xml的时候，会在PARAM这个类中对参数进行加密，此处需要先解密
		// 多个参数的情况
		if (!Assert.isEmpty(params_temp)) {
			for (PhaseParam info : params_temp) {
				if (info.getSensitive_flag()) {
					StringBuffer sb = new StringBuffer();
					if (!Assert.isEmpty(info.getParam_value())) {
						String[] param_array = info.getParam_value().trim().split(PhaseParam.PARAM_SLIP);
						for (int i = 0; i < param_array.length; i++) {
							if (i == param_array.length - 1) {
								sb.append(DESUtil.decrypt(param_array[i]));
							} else {
								sb.append(DESUtil.decrypt(param_array[i])).append(PhaseParam.PARAM_SLIP);
							}
						}
					}
					logger.info("Instance Generate sb = {}", sb.toString());
					info.setParam_value(sb.toString());
				}
			}
		}
	}

}
