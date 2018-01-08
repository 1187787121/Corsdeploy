/**
 * Title: ComponentPublicService.java
 * File Description:
 *
 * @copyright: 2016
 * @company: CORSWORK
 * @author: xuph
 * @version: 1.0
 * @date: 2016年10月18日
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
	// * Description: 判断组件或组件组ID是否存在,存在返回true
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
	// * Description: 校检组件或组件组是否被引用, 被引用抛出异常
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
	// * Description: 校检组件或组件组是否被引用
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
	// * Description: 校检组件或组件组是否被引用, 被引用抛出异常
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
	 * Description: 生成实例 并且用传入的ID替换生成实例的id便于执行的时候监控
	 * @param id
	 * @param inst
	 * @return
	 */
	public InstanceInfo generateInst(String id, InstanceInfo inst) {
		logger.debug("生成[{}]的执行实例文件", id);
		InstanceInfo inst_new = new InstanceInfo(id, inst.getTemplate(), inst.getEnv());
		inst_new.setParams(inst.getParams());
		inst_new.addModuleInfos(inst.getModuleInfos());
		return inst_new;
	}

	/**
	 * Description: 删除组件Xml文件
	 * @param comp_id
	 * @param comp_type
	 */
	public void deleteCompFile(String comp_id, MODULE_TYPE comp_type) {
		String file_path = XmlPathUtil.getPathByCompId(comp_id, comp_type);
		if (!Assert.isEmpty(file_path)) {
			File file = new File(file_path);
			if (file.exists()) {
				logger.info("删除" + comp_type.getCname() + "文件：[" + file_path + "]");
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
			logger.debug("当前处理的参数的名[{}],参数值[{}]", info.getParam_name(), info.getParam_value());
			Integer phase_no = info.getPhase_no();
			String value = info.getParam_value();
			// 如果是自定义参数，即带阶段号的参数，不放在参数表中，因为生成实例的实现是以参数名为map的key，加入进去会重复
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
			logger.debug("当前处理参数的值[{}]", Arrays.toString(values));
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
	 * Description: 获取命令列表中的所有参数
	 * @param cmds
	 * @return
	 */
	public List<ParamInfo> getParamInfoInCmd(String[] cmds) {
		List<String> param_list = new ArrayList<String>();
		String cmd = Arrays.toString(cmds);
		// 正则表达式 匹配“${ was.was }”这种类别的其中空格可有可无
		String match_group = "\\$\\{\\s*\\w+\\.\\w+\\s*\\}";
		param_list.addAll(matchParam(match_group, cmd));
		// 正则表达式 匹配“${ was }”这种类别的其中空格可有可无
		String match_single = "\\$\\{\\s*\\w+\\s*\\}";
		param_list.addAll(matchParam(match_single, cmd));
		return mergeParam(param_list);
	}

	/**
	 * Description: 查找命令中 匹配上表达是的参数
	 * @param match
	 * @param cmd
	 * @return
	 */
	private List<String> matchParam(String match, String cmd) {
		List<String> param_list = new ArrayList<String>();
		Pattern regex_group = Pattern.compile(match);
		Matcher m_group = regex_group.matcher(cmd);
		// 获取命令中匹配正则表达式的参数加入到列表中
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
	 * Description: 参数的字符串列表转换为参数实例，同时校验分组参数名普通参数名不能重复
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
	 * Description: 根据实现类型（IMPL_TYPE）筛选符合要求的数据源列表，
	 * 如果实现类型是shell的，就额外加上一个AGENT的数据源，这个数据源不存在于数据源表中。
	 * @param soc_list
	 * @param type
	 * @return
	 */
	public List<String> getSocNameListByType(List<DtSourceInfo> soc_list, IMPL_TYPE type) {
		List<String> list = new ArrayList<String>();
		if (!Assert.isEmpty(soc_list)) {
			for (DtSourceInfo info : soc_list) {
				// 先判断是否是agent
				PROTOCOL_TYPE pt = info.getProtocol_type();
				String name = info.getSoc_name();

				if (IMPL_TYPE.FTP.equals(type)) {
					if (PROTOCOL_TYPE.AGENT.equals(pt)) {
						// ip+agent拼的数据源
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
						// ip+agent拼的数据源
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
	 * Description: 根据实现类型（IMPL_TYPE）筛选符合要求的数据源列表，
	 * 如果实现类型是shell的，就额外加上一个AGENT的数据源，这个数据源不存在于数据源表中。
	 * @param soc_list
	 * @param type
	 * @return
	 */
	public List<SourceListBean> getSocBeanList(List<DtSourceInfo> soc_list, IMPL_TYPE type) {
		List<SourceListBean> list = new ArrayList<SourceListBean>();
		if (!Assert.isEmpty(soc_list)) {
			for (DtSourceInfo info : soc_list) {
				// 先判断是否是agent
				PROTOCOL_TYPE pt = info.getProtocol_type();
				String name = info.getSoc_name();

				SourceListBean soc_bean = new SourceListBean();
				soc_bean.setSoc_ip(info.getSoc_ip());
				soc_bean.setProtocol_type(pt);
				soc_bean.setSoc_name(name);
				if (IMPL_TYPE.FTP.equals(type)) {
					if (PROTOCOL_TYPE.AGENT.equals(pt)) {
						// ip+agent拼的数据源
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
						// ip+agent拼的数据源
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
	 * Description: 根据字符串生成int数组
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
	 * Description: 根据int数组生成字符串
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
	 * // 已部署组件 public List<CompDeployBean>
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
