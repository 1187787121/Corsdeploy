/**
 * Title: InstanceGenerateService.java
 * File Description: 
 * @copyright: 2017
 * @company: CORSWORK
 * @author: Administrator
 * @version: 1.0
 * @date: 2017��8��17��
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
	 * @param phase_list ��Ҫ���ɵ����н׶��б� ���������Ҫ�������� ��Ҫִ�е�����Դ
	 * @param params �׶���������Ҫ�滻�Ĳ���
	 * @param system_params ϵͳ�����������滻���в�����Ӧ�õĲ��� ����Ϊnull
	 * @param instance_id ��Ҫ����ʵ����ID����Ϊnull
	 * @param config_param �����ļ�����������Ϊnull
	 * @param node_names ��Ҫ���ɵĽڵ�ip�б������������ͽ׶��е�����Դ�������ɣ�Ҳ����Ϊnull
	 * @return
	 */
	public Instance phaseListGenerate(List<Phase> phase_list, List<PhaseParam> params, List<PhaseParam> system_params, String instance_id, List<String> node_names) {
		Assert.assertNotEmpty(phase_list, "�׶�");
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

		// ��������
		decryPhaseParam(params_temp);
		instance.setParam_list(params_temp);
		logger.debug("phaseListGenerate end[{}]", instance_id);
		return instance;
	}

	/**
	 * Description:
	 * @param phase �����
	 * @param params ��Ҫ�滻�Ĳ��� ����Ϊ�� �������phase�������в��� ����û�ҵ� �򱨴�
	 * @param system_params ����Ϊ��
	 * @param instance_id ����Ϊ��
	 * @param config_param ���ò��� ����Ϊ��
	 * @param node_names �ڵ��б� ����Ϊ��
	 * @return TODO���������ļ��׶�ʵ������ȡ����
	 */
	public Instance phaseGenerate(Phase phase, List<PhaseParam> params, List<PhaseParam> system_params, String instance_id, List<String> node_names) {
		Assert.assertNotEmpty(phase, "�׶�");
		if (Assert.isEmpty(instance_id)) {
			instance_id = getInstanceId();
		}
		Instance instance = new Instance(instance_id);
		// copyһ��ȫ�ֲ������Է��������޸Ĳ�����ʱ���ĵ�params�е�����
		// ����Ĳ�����������Ŀ�Ĳ�����������ĳ���׶εĲ�������û�о���ɸѡ
		List<PhaseParam> params_temp = PhaseParam.copy(params);
		// ÿ���׶��е������ʱ������Ӧ����δ�滻�Ĳ���
		String[] cmds = phase.getScript().getCmds();
		// ÿ���׶����õ�����Դlist
		List<StageSourceBean> source_list = phase.getSrv_soc();
		if (Assert.isEmpty(source_list)) {
			logger.debug("�׶���û������Դ��****�������ɽ׶�***");
			return instance;
		}
		// ����֮����û���滻����������Ū�׶���Ϣ ��Ϊ���ǵ������ļ��Ľ׶�����
		// ���ڶ������Դ��Ҫ�ظ����ɶ���ý׶�
		for (StageSourceBean source : source_list) {
			String ip = source.getExe_ip();
			if (!Assert.isEmpty(node_names) && !node_names.contains(ip)) {
				logger.debug("IP[{}]���ڽڵ��б�[{}]�� �����ɵ�ǰ�׶�", ip, node_names);
				continue;
			}
			// ���ɽ׶�ʵ��
			InstancePhase instance_phase = new InstancePhase();
			instance_phase.setImpl_type(phase.getImpl_type());
			instance_phase.setPhase_name(phase.getPhase_name());
			instance_phase.setInteractor_flag(phase.isInteractor_flag());
			instance_phase.setParallel_flag(phase.isParallel_flag());
			// project����(���ȫ�ֲ����������ļ�������ϵͳ�������ϵ�һ��)
			List<PhaseParam> proj_params = PhaseParam.copy(params);
			List<PhaseParam> sys_params = PhaseParam.copy(system_params);
			// �滻���������е�ϵͳ����
			if (!Assert.isEmpty(proj_params)) {
				// �滻����ֵ�����ϵͳ��������ĳЩ����ϵͳ������ֵ���滻���˴���û���滻�����еĲ�����
				proj_params = replaceParamValue(proj_params, sys_params);
				if (!Assert.isEmpty(sys_params)) {
					proj_params.addAll(sys_params);
				}
				// ��ȡ�׶������е����ò��������ò�������ֵ
				List<PhaseParam> ref_params = PhaseParam.copy(phase.getRef_param_list());
				// �ѽ׶������е����ò�����ֵ�滻�ɵ�ǰ��Ҫ��ֵ
				proj_params = replaceRefParam(proj_params, ref_params);
			} else {
				if (!Assert.isEmpty(sys_params)) {
					proj_params.addAll(sys_params);
				}
			}
			// ��ȡ���� ���滻����
			String[] c_s = Arrays.copyOf(cmds, cmds.length);
			
			// �滻�����������еĲ���
			List<String> cmds_replaced = replaceCmds(c_s, proj_params);
			
			ModuleSourceInfo module_source_info = getMouduleSouceInfo(source);
			instance_phase.addCmds(cmds_replaced.toArray(new String[cmds_replaced.size()]));
			instance_phase.setModule_source_info(module_source_info);
//			instance_phase.setPhase_name(ip + "_" + phase.getPhase_name());
			instance_phase.setPhase_name(phase.getPhase_name());
			instance.addPhase(instance_phase);
		}

		// ��������
		decryPhaseParam(params_temp);
		instance.setParam_list(params_temp);

		return instance;
	}

	/**
	 * Description: ����ʵ����ID�������ʵ����ʱ��û�д���ID�����̨�Զ�����ID
	 * @return
	 */
	private String getInstanceId() {
		String name = UUID.randomUUID().toString();
		name = name.replaceAll("-", "");
		return name;
	}

	/**
	 * Description: ��������Ĳ����滻
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
	 * Description: �滻�������� ���һ�������ж��ֵ �����ɶ�������
	 * @param cmd
	 * @param params
	 * @return
	 */
	private List<String> replaceCmd(String cmd, List<PhaseParam> params) {
		logger.debug("�滻����[{}]�еĲ���", cmd);
		List<String> result = new ArrayList<String>();
		// ���ز������������Ϊ��/home/${A}/${B}�����ͻ᷵��List<String>{A, B}
		List<String> param_in_cmd = getParamInCmd(cmd);
		logger.debug("��ǰ�������õ��Ĳ�����[{}]", param_in_cmd);
		// ���������û����Ҫ�滻�Ĳ��� ��ֱ�ӽ��������ͳ�ʱʱ�޷���
		if (Assert.isEmpty(param_in_cmd)) {
			result.add(cmd);
			return result;
		}
		// ��ȡ���������õĲ��� ������Щ����������Ϸ���
		if (Assert.isEmpty(params)) {
			throw new CmdParamIsNotExistException().addScene("PARAM", param_in_cmd.get(0));
		}
		List<List<PhaseParam>> params_needs = PhaseParamTools.getUsefulParam(param_in_cmd, params);

		// �滻�����еĲ���
		List<String> result_cmd = replaceParamInCmd(cmd, params_needs);

		return result_cmd;
	}

	/**
	 * һ�������п����ж������ Description: ��ȡ�����в����б�
	 * @param cmd
	 * @return
	 */
	private List<String> getParamInCmd(String cmd) {

		List<String> param_list = new ArrayList<String>();
		if (Assert.isEmpty(cmd)) {
			return param_list;
		}
		// �������ʽ ƥ�䡰${ was }�������������пո���п���
		String match_single = "\\$\\{\\s*\\w+\\s*\\}";
		Pattern regex_single = Pattern.compile(match_single);
		Matcher m_single = regex_single.matcher(cmd);
		// ��ȡ������ƥ���������ʽ�Ĳ������뵽�б���
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
	 * Description: �滻�����еĲ���
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
	 * Description: �滻��������
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
	 * Description: �滻�������õ���ϵͳ������������ֵ�����õ���ϵͳ���� �滻Ϊϵͳ������ֵ
	 * ���ﶼ�����ò�����params�����е�ֵ����system_param�����еļ�
	 * @param params
	 * @param system_param ��Ҫ�滻��ϵͳ���������û��ϵͳ��������null
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
	 * Description: �ѽ׶������е����ò�����ֵ�滻�ɵ�ǰ��Ҫ��ֵ
	 * @param params ����ȫ�ֲ�����ϵͳ������param_name+param_value
	 * @param ref_params ���е����ò���
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
					throw new RuntimeException(param_name + "δ�ҵ����õ�ֵ");
				}
				//�����ò�����param_name��ȫ�ֲ�����ʱ��ֱ���滻������param_value��һ��Ӧ�ò�����ref����ȫ�ֲ����еģ�
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
	 * Description: ��ȡModuleSourceInfo
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
		/*
		 * // if(DtSourceInfo.AGENT_SOC_NAME.equals(exe_soc_name)){
		 * if(exe_soc_name.contains("agent") ||
		 * DtSourceInfo.AGENT_SOC_NAME.equals(exe_soc_name)){
		 * dt_source_info.setProtocol_type(PROTOCOL_TYPE.AGENT);
		 * dt_source_info.setSoc_ip(bean.getExe_ip());
		 * dt_source_info.setSoc_port(AgentCommonTool.getPort());
		 * dt_source_info.setSoc_name(DtSourceInfo.AGENT_SOC_NAME); //
		 * dt_source_info.setSoc_port(soc_port); }else{ dt_source_info =
		 * dtSocService.getInfoByKey(exe_soc_name); }
		 */
		dt_source_info = dtSocService.getInfoByKey(exe_soc_name);
		if (Assert.isEmpty(ver_soc_name)) {
			ModuleSourceInfo info = new ModuleSourceInfo(dt_source_info.getProtocol_type(), dt_source_info);
			info.setExe_server_name(bean.getExe_server_name());
			info.setExe_soc_name(exe_soc_name);
			return info;
		} else {
			DtSourceInfo ver_source_info = dtSocService.getInfoByKey(ver_soc_name);
			ModuleSourceInfo info = new ModuleSourceInfo(dt_source_info, ver_source_info);
			info.setExe_server_name(bean.getExe_server_name());
			info.setVer_server_name(bean.getVer_server_name());
			info.setExe_soc_name(exe_soc_name);
			info.setVer_soc_name(ver_soc_name);
			// System.out.println(info.getData().toString());
			return info;
		}
	}

	/**
	 * Description: ����������������ļ��Ĳ��� ��������Ϊ ����׶ξ��������ļ��Ľ׶�
	 * @param cmds1
	 * @return
	 */
	private boolean checkConfigCmd(String[] cmds1) {
		String[] cmds = Arrays.copyOf(cmds1, cmds1.length);
		for (String cmd : cmds) {
			List<String> list = getParamInCmd(cmd);
			if (list.contains(PhaseParam.CONFIG_LOCAL) || list.contains(PhaseParam.CONFIG_REMOTE)) {
				logger.debug("��ǰ�׶��������ļ��׶�");
				return true;
			}
		}
		return false;

	}

	public void decryPhaseParam(List<PhaseParam> params_temp) {
		// ��дxml��ʱ�򣬻���PARAM������жԲ������м��ܣ��˴���Ҫ�Ƚ���
		// ������������
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