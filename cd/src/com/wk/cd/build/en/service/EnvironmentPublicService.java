/**
 * Title: CepublishService.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: xuph
 * @version: 1.0
 * @date: 2016年10月31日
 */
package com.wk.cd.build.en.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.wk.cd.build.en.bean.AddEnvironmentInputBean;
import com.wk.cd.build.en.bean.EnvInfoBean;
import com.wk.cd.build.en.bean.ServerBean;
import com.wk.cd.build.en.dao.CeEnvironmentDaoService;
import com.wk.cd.build.en.dao.CeEnvironmentServerDaoService;
import com.wk.cd.build.en.info.CeEnvironmentInfo;
import com.wk.cd.build.en.info.CeEnvironmentServerInfo;
import com.wk.cd.build.exc.AddEnvironmentIsExistException;
import com.wk.cd.build.exc.UpdateEnvironmentIsNotExistException;
import com.wk.cd.enu.DT_RANGE;
import com.wk.cd.enu.ELE_TYPE;
import com.wk.cd.enu.SERVER_TYPE;
import com.wk.cd.common.util.Assert;
import com.wk.db.EnumValue;
import com.wk.lang.Inject;
import com.wk.util.JaDate;
import com.wk.util.JaDateTime;
import com.wk.util.JaTime;

/**
 * Class Description: 公共服务
 * @author xuph
 */
public class EnvironmentPublicService {
	@Inject
	private CeEnvironmentDaoService ceEnvironmentDaoService;
	@Inject
	private CeEnvironmentServerDaoService ceEnvironmentServerDaoService;
	@Inject
	private ServerCommonService serverCommonService;

	/**
	 * Description: 校检环境名是否存在，存在就抛出异常
	 * @param env_name
	 */
	public void checkEnvNameNotExist(String env_name) {
		if (ceEnvironmentDaoService.countEnvNameNum(env_name) > 0) {
			throw new AddEnvironmentIsExistException().addScene("ENVIR", env_name);
		}

	}

	/**
	 * Description: 校检环境名是否存在，不存在就抛出异常
	 * @param env_name
	 */
	public void checkEnvNameIsExist(String env_name) {
		if (ceEnvironmentDaoService.countEnvNameNum(env_name) <= 0) {
			throw new UpdateEnvironmentIsNotExistException().addScene("ENVIR", env_name);
		}

	}

	/**
	 * Description: 获得构成要素字符串
	 * @param ele_types
	 * @return
	 */
	public String getEleTypeStr(String[] ele_types) {
		StringBuffer ele_type = new StringBuffer();
		for (String str : ele_types) {
			ele_type.append(str + "|");
		}
		return ele_type.toString();
	}

	/**
	 * Description:获得构成要素列表
	 * @param problem_type_string
	 * @return
	 */
	public List<ELE_TYPE> generateEleTypeList(String ele_type) {
		List<ELE_TYPE> ele_type_list = new ArrayList<ELE_TYPE>();
		if (!Assert.isEmpty(ele_type)) {
			for (String ele : ele_type.split("\\|")) {
				int num = Integer.parseInt(ele);
				ele_type_list.add(EnumValue.valueOf(ELE_TYPE.class, num));
			}
		}
		return ele_type_list;
	}

	/**
	 * Description: 获得构成要素数组
	 * @param ele_type
	 * @return
	 */
	public ELE_TYPE[] generateEleTypeArray(String ele_type) {
		List<ELE_TYPE> ele_type_list = generateEleTypeList(ele_type);
		return ele_type_list.toArray(new ELE_TYPE[ele_type_list.size()]);
	}

	/**
	 * Description: 获得系统环境中文名字
	 * @param env_name
	 * @return
	 */
	public String getEnvirCnName(String env_name) {
		String envir_cn_name = "";
		CeEnvironmentInfo envir = ceEnvironmentDaoService.getInfoByEnvName(env_name);
		if (!Assert.isEmpty(envir)) {
			envir_cn_name = envir.getEnv_cn_name();
		}
		return envir_cn_name;
	}

	/**
	 * Description: 根据环境名获取环境实例
	 * @param env_name
	 * @return
	 */
	public CeEnvironmentInfo getInfoByKey(String env_name) {
		checkEnvNameIsExist(env_name);
		return ceEnvironmentDaoService.getInfoByKey(env_name);
	}

	/**
	 * Description: 获得环境服务器列表
	 * @param server_list
	 * @param env_name
	 * @return
	 */
	public List<CeEnvironmentServerInfo> getCeEnvServerList(List<ServerBean> server_list, String env_name) {
		List<CeEnvironmentServerInfo> envir_servers = new ArrayList<CeEnvironmentServerInfo>();
		if (!Assert.isEmpty(server_list)) {
			for (ServerBean server : server_list) {
				CeEnvironmentServerInfo envir_server = new CeEnvironmentServerInfo();
				envir_server.setEnv_name(env_name);
				envir_server.setServer_name(server.getServer_name());
				envir_server.setServer_type(server.getServer_type());
				if (!envir_servers.contains(envir_server)) {
					// 校检服务器名是否存在
					serverCommonService.checkServerIsExist(envir_server.getServer_name());
					envir_servers.add(envir_server);
				}
			}
		}
		return envir_servers;
	}

	/**
	 * Description: 校检数据范围是否应该为空
	 * @param dt_range
	 * @param ele_types
	 */
	public void checkDtRangeIsNotEmpty(DT_RANGE dt_range, String[] ele_types) {
		for (String ele : ele_types) {
			int num = Integer.parseInt(ele);
			ELE_TYPE dt_type = EnumValue.valueOf(ELE_TYPE.class, num);
			if (dt_type == ELE_TYPE.DATA) {
				Assert.assertNotEmpty(dt_range == null ? null : dt_range, AddEnvironmentInputBean.DT_RANGECN);
				Assert.assertNotEmpty(dt_range.getValue() == 0 ? null : dt_range.getValue(),
						AddEnvironmentInputBean.DT_RANGECN);
			}
		}
	}

	/**
	 * Description: 根据环境名称获得关联服务器名(去重)
	 * @param env_name
	 * @return
	 */
	public List<String> getDistinctServerNameByEnv(String env_name) {
		checkEnvNameIsExist(env_name);
		return ceEnvironmentServerDaoService.getDistinctServerNameByEnv(env_name);
	}

	/**
	 * Description: 获得环境列表
	 * @return
	 */
	public List<EnvInfoBean> getEnvBeanListForApp(List<CeEnvironmentInfo> env_list) {
		List<EnvInfoBean> env_lists = new ArrayList<EnvInfoBean>();
		if (!Assert.isEmpty(env_list)) {
			for (CeEnvironmentInfo envs : env_list) {
				EnvInfoBean envbean = new EnvInfoBean();
				// 构成要素
				List<ELE_TYPE> ele_type = new ArrayList<ELE_TYPE>();
				ele_type = generateEleTypeList(envs.getEle_type());
				// 环境信息封装
				envbean.setEnv_name(envs.getEnv_name());
				envbean.setEnv_cn_name(envs.getEnv_cn_name());
				envbean.setEnv_bk_desc(envs.getEnv_bk_desc());
				envbean.setEnv_type(envs.getEnv_type());
				envbean.setEle_type(ele_type.toArray(new ELE_TYPE[ele_type.size()]));
				envbean.setDt_range(envs.getDt_range());
				envbean.setModify_user_id(envs.getModify_user_id());
				envbean.setCreate_user_id(envs.getCreate_user_id());
				envbean.setSys_name(envs.getSys_name());
				// 环境关联服务器信息
				List<CeEnvironmentServerInfo> envir_servers = ceEnvironmentServerDaoService.queryInfoByEnvName(envs
						.getEnv_name());
				if (!Assert.isEmpty(envir_servers)) {
					List<String> ver_servers = new ArrayList<String>();
					List<String> sys_servers = new ArrayList<String>();
					List<String> db_servers = new ArrayList<String>();
					for (CeEnvironmentServerInfo server : envir_servers) {
						SERVER_TYPE type = server.getServer_type();
						String name = server.getServer_name();
						if (type == SERVER_TYPE.APPLY) {
							sys_servers.add(name);
						}
						if (type == SERVER_TYPE.DATABASE) {
							db_servers.add(name);
						}
						if (type == SERVER_TYPE.VERSION) {
							ver_servers.add(name);
						} else {
							continue;
						}
					}
					envbean.setDb_server_list(db_servers);
					envbean.setVer_server_list(ver_servers);
					envbean.setSys_server_list(sys_servers);
				}
				JaDate create_date = envs.getCreate_bk_date();
				JaTime crate_time = envs.getCreate_bk_time();
				JaDate modify_date = envs.getModify_bk_date();
				JaTime modify_time = envs.getModify_bk_time();
				if (!Assert.isEmpty(create_date) && !Assert.isEmpty(crate_time)) {
					envbean.setCreate_date_time(JaDateTime.valueOf(create_date.toString() + " " + crate_time.toString()));
				}
				if (!Assert.isEmpty(modify_date) && !Assert.isEmpty(modify_time)) {
					envbean.setModify_date_time(JaDateTime.valueOf(modify_date.toString() + " "
							+ modify_time.toString()));
				}
				env_lists.add(envbean);
			}
		}
		sortEnvWithEnvCnName(env_lists);
		return env_lists;
	}
	
	/**
	 * Description:根据系统中文名排序
	 */
	private void sortEnvWithEnvCnName(List<EnvInfoBean> env_list){
		Collections.sort(env_list, new Comparator<EnvInfoBean>() {
			@Override
			public int compare(EnvInfoBean bean1, EnvInfoBean bean2) {
				return (bean1.getEnv_cn_name()).compareTo(bean2.getEnv_cn_name());
			}
		});
	}
}
