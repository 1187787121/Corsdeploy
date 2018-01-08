/**
 * Title: ServerCommonService.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: yangl
 * @version: 1.0
 * @date: 2016年11月1日
 */
package com.wk.cd.build.en.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import com.wk.cd.build.en.bean.DBBean;
import com.wk.cd.build.en.bean.ServerBean;
import com.wk.cd.build.en.dao.CeEnvironmentServerDaoService;
import com.wk.cd.build.en.dao.CeServerDaoService;
import com.wk.cd.build.en.dao.CeServerDsDaoService;
import com.wk.cd.build.en.info.CeServerDsInfo;
import com.wk.cd.build.en.info.CeServerInfo;
import com.wk.cd.build.exc.ServerAlreadyExistException;
import com.wk.cd.build.exc.ServerDbNotValidException;
import com.wk.cd.build.exc.ServerDsNotValidException;
import com.wk.cd.build.exc.ServerInUseException;
import com.wk.cd.build.exc.ServerNotExistException;
import com.wk.cd.build.exc.ServerSocTypeIsNotExistException;
import com.wk.cd.enu.MID_WARE;
import com.wk.cd.enu.SERVER_TYPE;
import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.PROTOCOL_TYPE;
import com.wk.cd.enu.IMPL_TYPE;
import com.wk.cd.system.dt.bean.ProtocolSocBean;
import com.wk.cd.system.dt.info.DtSourceInfo;
import com.wk.cd.system.dt.service.DtSocService;
import com.wk.db.EnumValue;
import com.wk.lang.Inject;
import com.wk.lang.SystemException;

/**
 * Class Description:
 * @author yangl
 */
public class ServerCommonService {

	@Inject
	private CeServerDaoService ceServerDaoService;
	@Inject
	private CeServerDsDaoService ceServerDsDaoService;
	@Inject
	private CeEnvironmentServerDaoService ceEnvironmentServerDaoService;
	@Inject
	private DtSocService dtSocService;

	/**
	 * Description: 检查服务器是否存在，存在抛出异常
	 * @param server_name
	 */
	public void checkServerIsNotExist(String server_name, String server_ip) {
		if (ceServerDaoService.getInfoByServerName(server_name) != null) {
			throw new ServerAlreadyExistException().addScene("SERVER", server_name);
		}
		if (ceServerDaoService.countServerByIP(server_ip) != 0) {
			throw new ServerAlreadyExistException().addScene("SERVER", server_ip);
		}
	}

	/**
	 * Description: 检查服务器是否存在，存在抛出异常
	 * @param server_name
	 */
	public void checkServerIpIsNotExist(String server_ip) {
		if (ceServerDaoService.countServerByIP(server_ip) != 0) {
			throw new ServerAlreadyExistException().addScene("SERVER", server_ip);
		}
	}

	/**
	 * Description: 检查服务器是否关联环境，关联则抛出异常
	 * @param server_name
	 */
	public void checkServerNotBeUsed(String server_name) {
		if (ceEnvironmentServerDaoService.countEnvNumByServerName(server_name) > 0) {
			throw new ServerInUseException().addScene("OPT", "删除");
		}
	}

	/**
	 * Description: 检查服务器是否存在，不存在抛出异常
	 * @param server_name
	 */
	public void checkServerIsExist(String server_name) {
		if (ceServerDaoService.getInfoByServerName(server_name) == null) {
			throw new ServerNotExistException().addScene("SERVER", server_name);
		}
	}

	public CeServerInfo getInfoByKey(String server_name) {
		return ceServerDaoService.getInfoByServerName(server_name);
	}

	/**
	 * Description: 根据字符串生成服务器类型列表
	 * @param server_type_string
	 * @return server_type_list
	 */
	public List<SERVER_TYPE> generateServerTypeList(String server_type_string) {
		List<SERVER_TYPE> server_type_list = new ArrayList<SERVER_TYPE>();
		if (!Assert.isEmpty(server_type_string)) {
			for (String type_num : server_type_string.split("\\|")) {
				int num = Integer.parseInt(type_num);
				server_type_list.add(EnumValue.valueOf(SERVER_TYPE.class, num));
			}
		}
		return server_type_list;
	}

	/**
	 * Description: 根据int数组生成字符串
	 * @param int_array
	 * @return int_string
	 */
	public String generateEnumValueString(int[] int_array) {
		StringBuilder sb = new StringBuilder("");
		if (int_array != null) {
			for (int num : int_array) {
				sb.append(num);
				sb.append("|");
			}
		}
		return sb.toString();
	}

	/**
	 * Description: 根据字符串生成数据库列表
	 * @param server_db_string
	 * @return server_db_list
	 */
	public List<DBBean> generateServerDBList(String server_db_string) {
		List<DBBean> server_db_list = new ArrayList<DBBean>();
		if (!Assert.isEmpty(server_db_string)) {
			for (String db_string : server_db_string.split("\\|")) {
				if (!Assert.isEmpty(db_string)) {
					String[] db_info = db_string.split("@");
					DBBean bean = new DBBean();
					bean.setServer_db(db_info[0]);
					bean.setDb_bbk_ver(db_info[1]);
					server_db_list.add(bean);
				}
			}
		}
		return server_db_list;
	}

	/**
	 * Description: 根据数据库列表列表生成存表字符串
	 * @param server_db_list
	 * @return 存表字符串
	 */
	public String generateServerDBString(DBBean[] server_db_list) {
		StringBuilder sb = new StringBuilder("");
		if (!Assert.isEmpty(server_db_list)) {
			for (DBBean db_bean : server_db_list) {
				if (!Assert.isEmpty(db_bean.getServer_db()) && !Assert.isEmpty(db_bean.getDb_bbk_ver())) {
					sb.append(db_bean.getServer_db());
					sb.append("@");
					sb.append(db_bean.getDb_bbk_ver());
					sb.append("|");
				} else {
					throw new ServerDbNotValidException();
				}
			}
		}
		return sb.toString();
	}

	/**
	 * Description: 根据字符串生成中间件列表
	 * @param mid_ware_string
	 * @return mid_ware_list
	 */
	public List<MID_WARE> generateMidWareList(String mid_ware_string) {
		List<MID_WARE> mid_ware_list = new ArrayList<MID_WARE>();
		if (!Assert.isEmpty(mid_ware_string)) {
			for (String type_num : mid_ware_string.split("\\|")) {
				int num = Integer.parseInt(type_num);
				mid_ware_list.add(EnumValue.valueOf(MID_WARE.class, num));
			}
		}
		return mid_ware_list;
	}

	/**
	 * Description: 根据字符串生成int数组
	 * @param type_string
	 * @return type_array
	 */
	public int[] generateEnumTypeArray(String type_string) {
		int[] type_array = null;
		if (!Assert.isEmpty(type_string)) {
			String[] type_nums = type_string.split("\\|");
			type_array = new int[type_nums.length];
			for (int i = 0; i < type_nums.length; i++) {
				type_array[i] = Integer.parseInt(type_nums[i]);
			}
		}
		return type_array;
	}

	/**
	 * Description: 根据服务器名获取数据源名列表
	 * @param server_name
	 * @return
	 */
	public String[] getSocNamesByServerName(String server_name) {
		List<String> soc_list = new ArrayList<String>();
		checkServerIsExist(server_name);
		List<CeServerDsInfo> server_ds_list = ceServerDsDaoService.querySourceByServer(server_name);
		if (!Assert.isEmpty(server_ds_list)) {
			for (CeServerDsInfo csds : server_ds_list) {
				String soc_name = csds.getSoc_name();
				soc_list.add(soc_name);
			}
		}
		if (!Assert.isEmpty(soc_list)) {
			return soc_list.toArray(new String[soc_list.size()]);
		} else {
			return null;
		}
	}

	public List<String> getSocNameByServiceNameAndType(String server_name, IMPL_TYPE impl_type) {
		List<String> soc_name_list = ceServerDsDaoService.querySocNameByServer(server_name);
		List<String> list = new ArrayList<String>();
		for (String name : soc_name_list) {
			PROTOCOL_TYPE pt = dtSocService.getInfoByKey(name).getProtocol_type();
			if (IMPL_TYPE.FTP.equals(impl_type)) {
				if (PROTOCOL_TYPE.PLT_FTP.equals(pt) || PROTOCOL_TYPE.SFTP.equals(pt)) {
					list.add(name);
				}
			} else if (IMPL_TYPE.SVN.equals(impl_type)) {
				if (PROTOCOL_TYPE.SVN.equals(pt)) {
					list.add(name);
				}
			} else if (IMPL_TYPE.SHELL.equals(impl_type)) {
				if (PROTOCOL_TYPE.SSH.equals(pt) || PROTOCOL_TYPE.TELNET.equals(pt)) {
					list.add(name);
				}
			} else if (IMPL_TYPE.WAS.equals(impl_type)) {
				if (PROTOCOL_TYPE.WAS.equals(pt)) {
					list.add(name);
				}
			} else if (IMPL_TYPE.WEBLOGIC.equals(impl_type)){
				if (PROTOCOL_TYPE.WEBLOGIC.equals(pt)) {
					list.add(name);
				}
			}
		}
		return list;
	}

	public IMPL_TYPE getImplTypeByProtocol(PROTOCOL_TYPE pt) {
		if (PROTOCOL_TYPE.PLT_FTP.equals(pt) || PROTOCOL_TYPE.SFTP.equals(pt)) {
			return IMPL_TYPE.FTP;
		} else if (PROTOCOL_TYPE.SVN.equals(pt)) {
			return IMPL_TYPE.SVN;
		} else if (PROTOCOL_TYPE.SSH.equals(pt) || PROTOCOL_TYPE.TELNET.equals(pt)) {
			return IMPL_TYPE.SHELL;
		} else if (PROTOCOL_TYPE.WAS.equals(pt)) {
			return IMPL_TYPE.WAS;
		} else {
			throw new SystemException("协议类型不支持");
		}
	}

	/**
	 * Description: 生成服务器数据源关联信息列表
	 * @param server_name
	 * @param soc_list
	 * @return 服务器数据源关联信息列表
	 */
	public List<CeServerDsInfo> generateCeServerDsInfoList(String server_name, List<String> soc_name_list,
			String ftp_config_soc, String shell_config_soc) {
		// 生成服务器数据源关联表Info
		List<CeServerDsInfo> serverds_info_list = new ArrayList<CeServerDsInfo>();
		// 配置了SHELL数据源必须要有FTP数据源
		if (Assert.isEmpty(ftp_config_soc) && !(Assert.isEmpty(shell_config_soc))) {
			throw new ServerDsNotValidException();
		}
		if (!Assert.isEmpty(soc_name_list)) {
			for (String soc_name : soc_name_list) {
				PROTOCOL_TYPE type = dtSocService.getProtocolTypeByName(soc_name);
				CeServerDsInfo serverds_info = new CeServerDsInfo();
				serverds_info.setServer_name(server_name);
				serverds_info.setSoc_name(soc_name);
				// 1|一般用途、1|2配置用途
				serverds_info.setApply_type("1|");
				if ((soc_name.equals(ftp_config_soc) && (type == PROTOCOL_TYPE.PLT_FTP || type == PROTOCOL_TYPE.SFTP))
						|| (soc_name.equals(shell_config_soc) && (type == PROTOCOL_TYPE.SSH || type == PROTOCOL_TYPE.TELNET))) {
					serverds_info.setApply_type("1|2|");
				} else if (soc_name.equals(ftp_config_soc) || soc_name.equals(shell_config_soc)) {
					throw new ServerDsNotValidException();
				}
				serverds_info_list.add(serverds_info);
			}
		}
		return serverds_info_list;
	}

	/**
	 * Description: 根据服务器名称获取FTP配置数据源
	 * @param server_name
	 * @return
	 */
	public String getFtpConfigSocByServerName(String server_name) {
		String soc_name = ceServerDsDaoService.getFtpConfigSocByServerName(server_name.trim());
		if (Assert.isEmpty(soc_name)) {
			throw new ServerSocTypeIsNotExistException().addScene("SERVER", server_name.trim()).addScene("TYPE", "FTP");
		}
		return soc_name;
	}

	/**
	 * Description: 根据服务器名称获取SHELL配置数据源
	 * @param server_name
	 * @return
	 */
	public String getShellConfigSocByServerName(String server_name) {
		String soc_name = ceServerDsDaoService.getShellConfigSocByServerName(server_name.trim());
		if (Assert.isEmpty(soc_name)) {
			throw new ServerSocTypeIsNotExistException().addScene("SERVER", server_name.trim()).addScene("TYPE",
					"SHELL");
		}
		return soc_name;
	}

	/**
	 * Description: 获得服务器列表ForAPP
	 * @param server_list
	 * @return
	 */
	public List<ServerBean> getSerListForApp(List<CeServerInfo> server_list) {
		List<ServerBean> server_lists = new ArrayList<ServerBean>();
		// 获取服务器信息
		if(!Assert.isEmpty(server_list)){
			for (CeServerInfo server_info : server_list) {
				ServerBean bean = new ServerBean();
				String server_name = server_info.getServer_name();
				// 获取数据源关联信息列表
				List<CeServerDsInfo> serverds_info_list = ceServerDsDaoService.querySourceByServer(server_name);
				String ftp_config_soc = null;
				String shell_config_soc = null;
				// 生成协议与数据源Bean的对应关系MAP
				HashMap<PROTOCOL_TYPE, List<String>> protocol_soc_map = new HashMap<PROTOCOL_TYPE, List<String>>();
				if (!Assert.isEmpty(serverds_info_list)) {
					for (CeServerDsInfo serverds_info : serverds_info_list) {
						String soc_name = serverds_info.getSoc_name();
						// 若数据源存在，则获取数据源信息
						DtSourceInfo soc_info = dtSocService.getInfoByKey(soc_name);
						PROTOCOL_TYPE type = soc_info.getProtocol_type();
						if (serverds_info.getApply_type().equals("1|2|")) {
							if (type == PROTOCOL_TYPE.PLT_FTP || type == PROTOCOL_TYPE.SFTP) {
								ftp_config_soc = soc_name;
							} else if (type == PROTOCOL_TYPE.SSH || type == PROTOCOL_TYPE.TELNET) {
								shell_config_soc = soc_name;
							}
						}
						if (protocol_soc_map.containsKey(type)) {
							protocol_soc_map.get(type).add(soc_name);
						} else {
							List<String> list = new ArrayList<String>();
							list.add(soc_name);
							protocol_soc_map.put(type, list);
						}
					}
				}
				bean.setFtp_config_soc(ftp_config_soc);
				bean.setShell_config_soc(shell_config_soc);
				// 根据协议与数据源Bean的对应关系MAP生成协议数据源列表
				List<ProtocolSocBean> protocol_soc_list = new ArrayList<ProtocolSocBean>();
				if (!Assert.isEmpty(protocol_soc_map)) {
					for (PROTOCOL_TYPE protocol_type : protocol_soc_map.keySet()) {
						ProtocolSocBean protocol_soc_bean = new ProtocolSocBean();
						protocol_soc_bean.setProtocol_type(protocol_type);
						protocol_soc_bean.setSoc_name_list(protocol_soc_map.get(protocol_type));
						protocol_soc_list.add(protocol_soc_bean);
					}
				}
				// 获取数据库列表
				List<DBBean> server_db_list = generateServerDBList(server_info.getServer_db());
				// 设置出输出参数
				bean.setServer_name(server_name);
				bean.setServer_cn_name(server_info.getServer_cn_name());
				bean.setServer_bk_desc(server_info.getServer_desc());
				bean.setServer_ip(server_info.getServer_ip());
				bean.setServer_os(server_info.getServer_os());
				bean.setOs_sbk_ver(server_info.getOs_sbk_ver());
				bean.setServer_db_list(server_db_list.toArray(new DBBean[server_db_list.size()]));
				bean.setMid_ware_list(generateEnumTypeArray(server_info.getServer_mid_ware()));
				bean.setProtocol_soc_list(protocol_soc_list);
				server_lists.add(bean);
			}
		}
		sortServerWithServerIp(server_lists);
		return server_lists;
	}

	/**
	 * Description: sort
	 * @param server_list
	 */
	private void sortServerWithServerIp(List<ServerBean> server_list) {
		Collections.sort(server_list, new Comparator<ServerBean>() {
			@Override
			public int compare(ServerBean bean1, ServerBean bean2) {
				return (bean1.getServer_ip()).compareTo(bean2.getServer_ip());
			}
		});
	}
	
}
