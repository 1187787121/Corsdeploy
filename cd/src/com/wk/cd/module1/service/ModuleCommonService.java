/**
 * Title: ComponentPublicService.java
 * File Description:
 *
 * @copyright: 2016
 * @company: CORSWORK
 * @author: xuph
 * @version: 1.0
 * @date: 2016��10��18��
 */
package com.wk.cd.module1.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.IMPL_TYPE;
import com.wk.cd.enu.MODULE_TYPE;
import com.wk.cd.enu.PROTOCOL_TYPE;
import com.wk.cd.module1.bean.SourceListBean;
import com.wk.cd.module1.dao.MoComponentDaoService;
import com.wk.cd.module1.exc.ComponentNotExistException;
import com.wk.cd.module1.impl.DefaultEnv;
import com.wk.cd.module1.info.InstanceInfo;
import com.wk.cd.module1.info.Param;
import com.wk.cd.module1.info.ParamInfo;
import com.wk.cd.module1.xml.XmlPathUtil;
import com.wk.cd.system.dt.info.DtSourceInfo;
import com.wk.cd.system.us.service.UsGetUserInfoService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;
import com.wk.util.FileUtil;

/**
 * Class Description:
 * @author xuph
 */
public class ModuleCommonService {

	@Inject
	private UsGetUserInfoService usGetUserInfoService;
	@Inject
	private MoComponentDaoService moComponentDaoService;

	private static final Log logger = LogFactory.getLog();

	public void checkCompIdIsExist(String comp_id, MODULE_TYPE comp_type) {
		if (moComponentDaoService.countComponentById(comp_id) <= 0) {
			throw new ComponentNotExistException().addScene("COMPTYPE", comp_type.getCname()).addScene("COMPID", comp_id);
		}
	}

	//
	// /**
	// * Description: �ж�����������ID�Ƿ����,���ڷ���true
	// * @param comp_id
	// * @return
	// */
	// public boolean assertCompIdIsExist(String comp_id) {
	// if (Assert.isEmpty(comp_id)) {
	// return false;
	// }
	// return moModuleDaoService.countModuleById(comp_id) > 0;
	// }
	//
	// /**
	// * Description: У�������������Ƿ�����, �������׳��쳣
	// * @param comp_id
	// */
	// public void checkComponentNotQuote(String comp_id, MODULE_TYPE comp_type)
	// {
	// if (moduleQuoteDaoService.countModuleByQuoteId(comp_id) > 0) {
	// throw new ComponentIsQuotedException().addScene("COMPTYPE",
	// comp_type.getCname())
	// .addScene("COMPID", comp_id);
	// }
	//
	// }
	//
	// /**
	// * Description: У�������������Ƿ�����
	// * @param comp_id
	// */
	// public boolean checkCompBeQuote(String comp_id) {
	// if (moduleQuoteDaoService.countModuleByQuoteId(comp_id) > 0) {
	// return true;
	// } else {
	// return false;
	// }
	// }
	//
	// /**
	// * Description: У�������������Ƿ�����, �������׳��쳣
	// * @param comp_id
	// */
	// public void checkAlterComponentValid(String comp_id, MODULE_TYPE
	// comp_type) {
	// if (moduleQuoteDaoService.countModuleByQuoteId(comp_id) > 0) {
	// throw new ComponentAlterQuotedException().addScene("COMPTYPE",
	// comp_type.getCname())
	// .addScene("COMPID", comp_id);
	// }
	//
	// }

	/**
	 * Description: ����ʵ�� �����ô����ID�滻����ʵ����id����ִ�е�ʱ����
	 * @param id
	 * @param inst
	 * @return
	 */
	public InstanceInfo generateInst(String id, InstanceInfo inst) {
		logger.debug("����[{}]��ִ��ʵ���ļ�", id);
		InstanceInfo inst_new = new InstanceInfo(id, inst.getTemplate(), inst.getEnv());
		inst_new.setParams(inst.getParams());
		inst_new.addModuleInfos(inst.getModuleInfos());
		return inst_new;
	}

	/**
	 * Description: ɾ�����Xml�ļ�
	 * @param comp_id
	 * @param comp_type
	 */
	public void deleteCompFile(String comp_id, MODULE_TYPE comp_type) {
		String file_path = XmlPathUtil.getPathByCompId(comp_id, comp_type);
		if (!Assert.isEmpty(file_path)) {
			File file = new File(file_path);
			if (file.exists()) {
				logger.info("ɾ��" + comp_type.getCname() + "�ļ���[" + file_path + "]");
				FileUtil.deleteFile(file);
			}
		}
	}

	public DefaultEnv getEnvByParam(ParamInfo[] param_infos) {
		DefaultEnv env = new DefaultEnv();
		if (Assert.isEmpty(param_infos)) {
			return env;
		}
		for (ParamInfo info : param_infos) {
			logger.debug("��ǰ����Ĳ�������[{}],����ֵ[{}]", info.getParam_name(), info.getParam_value());
			Integer phase_no = info.getPhase_no();
			String value = info.getParam_value();
			// ������Զ�������������׶κŵĲ����������ڲ������У���Ϊ����ʵ����ʵ�����Բ�����Ϊmap��key�������ȥ���ظ�
			if ((phase_no != null && phase_no > 0) || Assert.isEmpty(value)) {
				continue;
			}
			String[] values = null;
			if (value.contains("-,-")) {
				values = value.split("-,-");
			} else {
				values = new String[1];
				values[0] = value;
			}
			logger.debug("��ǰ���������ֵ[{}]", Arrays.toString(values));
			Param sub_p = new Param(info, values);
			if (Assert.isEmpty(info.getParam_group())) {
				env.setParam(info.getParam_name(), sub_p);
			} else {
				env.setParam(info.getParam_group() + "." + info.getParam_name(), sub_p);
			}
		}
		return env;

	}

	/**
	 * Description: ��ȡ�����б��е����в���
	 * @param cmds
	 * @return
	 */
	public List<ParamInfo> getParamInfoInCmd(String[] cmds) {
		List<String> param_list = new ArrayList<String>();
		String cmd = Arrays.toString(cmds);
		// ������ʽ ƥ�䡰${ was.was }�������������пո���п���
		String match_group = "\\$\\{\\s*\\w+\\.\\w+\\s*\\}";
		param_list.addAll(matchParam(match_group, cmd));
		// ������ʽ ƥ�䡰${ was }�������������пո���п���
		String match_single = "\\$\\{\\s*\\w+\\s*\\}";
		param_list.addAll(matchParam(match_single, cmd));
		return mergeParam(param_list);
	}

	/**
	 * Description: ���������� ƥ���ϱ���ǵĲ���
	 * @param match
	 * @param cmd
	 * @return
	 */
	private List<String> matchParam(String match, String cmd) {
		List<String> param_list = new ArrayList<String>();
		Pattern regex_group = Pattern.compile(match);
		Matcher m_group = regex_group.matcher(cmd);
		// ��ȡ������ƥ��������ʽ�Ĳ������뵽�б���
		while (true) {
			if (!m_group.find()) {
				break;
			}
			String param = m_group.group().substring(2, m_group.group().length() - 1);
			if (!param_list.contains(param.trim())) {
				param_list.add(param.trim());
			}
		}
		return param_list;
	}

	/**
	 * Description: �������ַ����б�ת��Ϊ����ʵ����ͬʱУ������������ͨ�����������ظ�
	 * @param list
	 * @return
	 */
	private List<ParamInfo> mergeParam(List<String> list) {
		List<ParamInfo> params = new ArrayList<ParamInfo>();
		if (!Assert.isEmpty(list)) {
			for (String param : list) {
				if (param.contains(".")) {
					ParamInfo info = new ParamInfo();
					String[] strs = param.split("\\.");
					info.setParam_name(strs[1]);
					info.setParam_group(strs[0]);
					params.add(info);
				} else {
					ParamInfo info = new ParamInfo();
					info.setParam_name(param);
					params.add(info);
				}
			}
		}
		return params;
	}

	/**
	 * Description: ����ʵ�����ͣ�IMPL_TYPE��ɸѡ����Ҫ�������Դ�б�
	 * ���ʵ��������shell�ģ��Ͷ������һ��AGENT������Դ���������Դ������������Դ���С�
	 * @param soc_list
	 * @param type
	 * @return
	 */
	public List<String> getSocNameListByType(List<DtSourceInfo> soc_list, IMPL_TYPE type) {
		List<String> list = new ArrayList<String>();
		if (!Assert.isEmpty(soc_list)) {
			for (DtSourceInfo info : soc_list) {
				// ���ж��Ƿ���agent
				PROTOCOL_TYPE pt = info.getProtocol_type();
				String name = info.getSoc_name();

				if (IMPL_TYPE.FTP.equals(type)) {
					if (PROTOCOL_TYPE.AGENT.equals(pt)) {
						// ip+agentƴ������Դ
						String soc_name = info.getSoc_ip().substring(info.getSoc_ip().lastIndexOf(".") + 1) + "agent";
						list.add(soc_name);
						continue;
					}
					if (PROTOCOL_TYPE.PLT_FTP.equals(pt) || PROTOCOL_TYPE.SFTP.equals(pt)) {
						list.add(name);
					}
				} else if (IMPL_TYPE.SVN.equals(type)) {
					if (PROTOCOL_TYPE.SVN.equals(pt)) {
						list.add(name);
					}
				} else if (IMPL_TYPE.SHELL.equals(type)) {
					if (PROTOCOL_TYPE.AGENT.equals(pt)) {
						// ip+agentƴ������Դ
						String soc_name = info.getSoc_ip().substring(info.getSoc_ip().lastIndexOf(".") + 1) + "agent";
						list.add(soc_name);
						continue;
					}
					if (PROTOCOL_TYPE.SSH.equals(pt) || PROTOCOL_TYPE.TELNET.equals(pt) || PROTOCOL_TYPE.AGENT.equals(pt)) {
						list.add(name);
					}
				} else if (IMPL_TYPE.WAS.equals(type)) {
					if (PROTOCOL_TYPE.WAS.equals(pt)) {
						list.add(name);
					}
				} else if (IMPL_TYPE.WEBLOGIC.equals(type)) {
					if (PROTOCOL_TYPE.WEBLOGIC.equals(pt)) {
						list.add(name);
					}
				} else if (IMPL_TYPE.SQL.equals(type)) {
					if (PROTOCOL_TYPE.JDBC.equals(pt)) {
						list.add(name);
					}
				}
			}
		}
		return list;
	}

	/**
	 * Description: ����ʵ�����ͣ�IMPL_TYPE��ɸѡ����Ҫ�������Դ�б�
	 * ���ʵ��������shell�ģ��Ͷ������һ��AGENT������Դ���������Դ������������Դ���С�
	 * @param soc_list
	 * @param type
	 * @return
	 */
	public List<SourceListBean> getSocBeanList(List<DtSourceInfo> soc_list, IMPL_TYPE type) {
		List<SourceListBean> list = new ArrayList<SourceListBean>();
		if (!Assert.isEmpty(soc_list)) {
			for (DtSourceInfo info : soc_list) {
				// ���ж��Ƿ���agent
				PROTOCOL_TYPE pt = info.getProtocol_type();
				String name = info.getSoc_name();

				SourceListBean soc_bean = new SourceListBean();
				soc_bean.setSoc_ip(info.getSoc_ip());
				soc_bean.setProtocol_type(pt);
				soc_bean.setSoc_name(name);
				if (IMPL_TYPE.FTP.equals(type)) {
					if (PROTOCOL_TYPE.AGENT.equals(pt)) {
						// ip+agentƴ������Դ
						String soc_name = info.getSoc_ip().substring(info.getSoc_ip().lastIndexOf(".") + 1) + "agent";
						soc_bean.setSoc_name(soc_name);
						list.add(soc_bean);
						continue;
					}
					if (PROTOCOL_TYPE.PLT_FTP.equals(pt) || PROTOCOL_TYPE.SFTP.equals(pt)) {
						list.add(soc_bean);
					}
				} else if (IMPL_TYPE.SVN.equals(type)) {
					if (PROTOCOL_TYPE.SVN.equals(pt)) {
						list.add(soc_bean);
					}
				} else if (IMPL_TYPE.SHELL.equals(type)) {
					if (PROTOCOL_TYPE.AGENT.equals(pt)) {
						// ip+agentƴ������Դ
						String soc_name = info.getSoc_ip().substring(info.getSoc_ip().lastIndexOf(".") + 1) + "agent";
						soc_bean.setSoc_name(soc_name);
						list.add(soc_bean);
						continue;
					}
					if (PROTOCOL_TYPE.SSH.equals(pt) || PROTOCOL_TYPE.TELNET.equals(pt) || PROTOCOL_TYPE.AGENT.equals(pt)) {
						list.add(soc_bean);
					}
				} else if (IMPL_TYPE.WAS.equals(type)) {
					if (PROTOCOL_TYPE.WAS.equals(pt)) {
						list.add(soc_bean);
					}
				} else if (IMPL_TYPE.WEBLOGIC.equals(type)) {
					if (PROTOCOL_TYPE.WEBLOGIC.equals(pt)) {
						list.add(soc_bean);
					}
				} else if (IMPL_TYPE.SQL.equals(type)) {
					if (PROTOCOL_TYPE.JDBC.equals(pt)) {
						list.add(soc_bean);
					}
				}
			}
		}
		return list;
	}

	/**
	 * Description: �����ַ�������int����
	 * @param int_string
	 * @return int_array
	 */
	public int[] generateIntArray(String int_string) {
		int[] int_array = null;
		if (!Assert.isEmpty(int_string)) {
			String[] str_array = int_string.split("\\|");
			int_array = new int[str_array.length];
			for (int i = 0; i < str_array.length; i++) {
				int_array[i] = Integer.parseInt(str_array[i]);
			}
		}
		return int_array;
	}

	/**
	 * Description: ����int���������ַ���
	 * @param int_array
	 * @return int_string
	 */
	public String generateIntString(int[] int_array) {
		StringBuilder sb = new StringBuilder("");
		if (int_array != null) {
			for (int num : int_array) {
				sb.append(num);
				sb.append("|");
			}
		}
		return sb.toString();
	}

	/*
	 * public List<CompDeployBean> classifyCompDeploy(List<MoModuleInfo>
	 * moModuleInfos, List<CoNodeModuleInfo> coNodeModuleInfos) {
	 * List<CompDeployBean> compDeployBeanList = new
	 * ArrayList<CompDeployBean>(); if (!Assert.isEmpty(moModuleInfos)) { for
	 * (MoModuleInfo moModuleInfo : moModuleInfos) { CompDeployBean
	 * compDeployBean = new CompDeployBean(); compDeployBean.setIs_deploy(0);
	 * compDeployBean.setModule_cn_name(moModuleInfo.getModule_cn_name());
	 * compDeployBean.setModule_id(moModuleInfo.getModule_id());
	 * compDeployBeanList.add(compDeployBean); } } if
	 * (!Assert.isEmpty(coNodeModuleInfos)) { for (CompDeployBean compDeployBean
	 * : compDeployBeanList) { for (CoNodeModuleInfo coNodeModuleInfo :
	 * coNodeModuleInfos) { if
	 * (compDeployBean.getModule_id().equals(coNodeModuleInfo
	 * .getComponent_id())) { compDeployBean.setIs_deploy(1); } } } } return
	 * compDeployBeanList; }
	 * 
	 * // �Ѳ������ public List<CompDeployBean>
	 * classifyCompDeploy(List<CoNodeModuleInfo> coNodeModuleInfos) {
	 * List<CompDeployBean> compDeployBeanList = new
	 * ArrayList<CompDeployBean>(); MoModuleInfo info = null; if
	 * (!Assert.isEmpty(coNodeModuleInfos)) { for (CoNodeModuleInfo
	 * coNodeModuleInfo : coNodeModuleInfos) { CompDeployBean compDeployBean =
	 * new CompDeployBean(); compDeployBean.setIs_deploy(1); info = new
	 * MoModuleInfo(); info.setModule_id(coNodeModuleInfo.getComponent_id());
	 * MoComponentInfo temp = moComponentDaoService
	 * .getInfoByKey(coNodeModuleInfo.getComponent_id());
	 * 
	 * compDeployBean.setModule_cn_name(temp.getComponent_cn_name());
	 * compDeployBean.setModule_id(coNodeModuleInfo.getComponent_id());
	 * compDeployBeanList.add(compDeployBean); } }
	 * 
	 * return compDeployBeanList; }
	 */

}
