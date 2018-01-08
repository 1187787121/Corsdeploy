/**
 * Title: ViewConfigModAction.java
 * File Description: 修改配置文件相关服务
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2016年11月10日
 */
package com.wk.cd.build.ea.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.build.ea.bean.EnvModFileBean;
import com.wk.cd.build.ea.bean.ServerConfigBean;
import com.wk.cd.build.ea.bean.ViewConfigModInputBean;
import com.wk.cd.build.ea.bean.ViewConfigModOutputBean;
import com.wk.cd.build.ea.dao.EnvConfigfileModDaoService;
import com.wk.cd.build.ea.info.EnvConfigfileModInfo;
import com.wk.cd.build.en.dao.CeEnvironmentDaoService;
import com.wk.cd.build.en.dao.CeEnvironmentServerDaoService;
import com.wk.cd.build.en.dao.CeServerDaoService;
import com.wk.cd.build.en.dao.CeServerDsDaoService;
import com.wk.cd.build.en.info.CeServerInfo;
import com.wk.cd.build.en.service.EnvironmentPublicService;
import com.wk.cd.build.en.service.ServerCommonService;
import com.wk.cd.enu.CFG_TYPE;
import com.wk.cd.enu.FOPT_TYPE;
import com.wk.cd.common.cm.service.GenNoService;
import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.YN_FLAG;
import com.wk.cd.remote.fp.bean.FileListBean;
import com.wk.cd.remote.fp.service.RemoteFileList;
import com.wk.cd.service.IViewActionBasic;
import com.wk.cd.system.dt.info.DtSourceInfo;
import com.wk.cd.system.dt.service.DtCheckSocExistService;
import com.wk.cd.system.dt.service.DtSocService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;
import com.wk.util.JaDate;

/**
 * Class Description: 修改配置文件相关服务
 * @author Xul
 */
public class ViewConfigModAction
		extends IViewActionBasic<ViewConfigModInputBean, ViewConfigModOutputBean> {

	@Inject
	private GenNoService genNoSrv;
	@Inject
	private EnvironmentPublicService envPubSrv;
	@Inject
	private DtCheckSocExistService dtCheckSocSrv;
	@Inject
	private EnvConfigfileModDaoService confModSrv;
	@Inject
	private CeEnvironmentDaoService ceEnvironmentDaoService;
	@Inject
	private CeEnvironmentServerDaoService envServerSrv;
	@Inject
	private CeServerDsDaoService serverDsSrv;
	@Inject
	private CeServerDaoService serverSrv;
	@Inject
	private ServerCommonService serverComSrv;
	@Inject
	private RemoteFileList remoteFileList;
	@Inject
	private DtSocService dtSocSrv;
	private static final Log logger = LogFactory.getLog();

	/**
	 * Description: 生成新批次号
	 * @param date
	 * @return
	 */
	public String getNewBatch(JaDate date) {
		logger.info("-----------getNewBatch begin----------");

		// 获取最新的批次号
		String batch_no = genNoSrv.getBatchNo(date);

		logger.info("-----------getNewBatch end----------");
		return batch_no;
	}

	/**
	 * Description: 查询环境下所有服务器中的配置数据源
	 * @param input
	 * @return
	 */
	public ViewConfigModOutputBean queryAllServer(ViewConfigModInputBean input) {
		logger.info("-----------queryAllConfigSoc begin----------");
		ViewConfigModOutputBean output = new ViewConfigModOutputBean();

		// 非空校验
		String env_name = input.getEnv_name();
		Assert.assertNotEmpty(env_name, ViewConfigModInputBean.ENV_NAMECN);

		// 合法性校验
		envPubSrv.checkEnvNameIsExist(env_name);

		List<ServerConfigBean> server_list = new ArrayList<ServerConfigBean>();
		// 根据环境名称获得关联信息
		List<String> env_server_list = envServerSrv.getDistinctServerNameByEnv(env_name);
		if (!Assert.isEmpty(env_server_list)) {
			for (String server_name : env_server_list) {
				ServerConfigBean bean = new ServerConfigBean();
				// 根据服务器名称查询用途为配置的数据源
				String soc_name = serverDsSrv.getFtpConfigSocByServerName(server_name);
				bean.setCe_server_name(server_name);
				// 根据服务器名称查询服务器地址
				CeServerInfo server_info = serverSrv.getInfoByServerName(server_name);
				bean.setServer_ip(Assert.isEmpty(server_info) ? null : server_info.getServer_ip());
				// 根据数据源名查询用户根路径
				if (!Assert.isEmpty(soc_name)) {
					DtSourceInfo dt_info = dtSocSrv.getInfoByKey(soc_name);
					bean.setUser_root_path(dt_info.getUser_root_path());
				}
				server_list.add(bean);
			}
		}

		output.setServer_list(server_list);
		logger.info("-----------queryAllConfigSoc end----------");
		return output;
	}
	
	/**
	 * Description: 查询环境下所有服务器中有tomcat的配置数据源
	 * @param input
	 * @return
	 */
	public ViewConfigModOutputBean queryTomcatServer(ViewConfigModInputBean input) {
		logger.info("-----------queryTomcatServer begin----------");
		ViewConfigModOutputBean output = new ViewConfigModOutputBean();

		// 非空校验
		String env_name = input.getEnv_name();
		Assert.assertNotEmpty(env_name, ViewConfigModInputBean.ENV_NAMECN);

		// 合法性校验
		envPubSrv.checkEnvNameIsExist(env_name);

		List<ServerConfigBean> server_list = new ArrayList<ServerConfigBean>();
		// 根据环境名称获得关联信息
		List<String> env_server_list = envServerSrv.getDistinctServerNameByEnv(env_name);
		if (!Assert.isEmpty(env_server_list)) {
			for (String server_name : env_server_list) {
				ServerConfigBean bean = new ServerConfigBean();
				// 根据服务器名称查询服务器地址
				CeServerInfo server_info = serverSrv.getInfoByServerName(server_name);
				if(!Assert.isEmpty(server_info)) {
					String mid_ware = server_info.getServer_mid_ware();
					if(!Assert.isEmpty(mid_ware)){
						String[] mid = mid_ware.split("|");
						//当中间件有tomcat时才输出ip地址
						for (String m : mid) {
							if(m.equals("2")) {
								bean.setServer_ip(server_info.getServer_ip());
								if(!Assert.isEmpty(server_info.getServer_name())) {
									bean.setCe_server_name(server_info.getServer_name());
									// 根据服务器名称查询用途为配置的数据源
									String soc_name = serverDsSrv.getFtpConfigSocByServerName(server_info.getServer_name());
									// 根据数据源名查询用户根路径
									if (!Assert.isEmpty(soc_name)) {
										DtSourceInfo dt_info = dtSocSrv.getInfoByKey(soc_name);
										bean.setUser_root_path(dt_info.getUser_root_path());
									}
								}
								server_list.add(bean);
							}
						}
					}
				}
			}
		}

		output.setServer_list(server_list);
		logger.info("-----------queryTomcatServer end----------");
		return output;
	}

	/**
	 * Description: 查看批次内修改、删除文件列表
	 * @param input
	 * @return
	 */
	public ViewConfigModOutputBean queryModList(ViewConfigModInputBean input) {
		logger.info("-----------queryModList begin----------");
		ViewConfigModOutputBean output = new ViewConfigModOutputBean();

		// 非空校验
		String batch_no = input.getBatch_no();
		String env_name = input.getEnv_name();
		String ce_server_name = input.getCe_server_name();
		Assert.assertNotEmpty(ce_server_name, ViewConfigModInputBean.CE_SERVER_NAMECN);
		Assert.assertNotEmpty(env_name, ViewConfigModInputBean.ENV_NAMECN);
		
		serverComSrv.checkServerIsExist(ce_server_name);
		
		// 根据批次号及操作类型查询删除信息列表
		List<String> full_path_list = new ArrayList<String>();
		List<String> path_list = new ArrayList<String>();
		List<EnvModFileBean> delete_list = new ArrayList<EnvModFileBean>();
		List<EnvConfigfileModInfo> delete_info_list = confModSrv.getInfoByBatchAndFopt(ce_server_name, batch_no,
				FOPT_TYPE.DELETE);
		for (EnvConfigfileModInfo info : delete_info_list) {
			EnvModFileBean bean = new EnvModFileBean();
			String file_bk_path = info.getFile_bk_path();
			String file_bk_fname = info.getFile_bk_fname();
			// 若目录不以"/"结尾则补全
			if (!file_bk_path.endsWith("/")) {
				file_bk_path = file_bk_path + "/";
			}
			bean.setDir_yn_flag(info.getDir_yn_flag());
			bean.setFull_path(file_bk_path + file_bk_fname);
			full_path_list.add(file_bk_path + file_bk_fname);
			// 若为目录删除，则添加
			if (YN_FLAG.YES.equals(info.getDir_yn_flag())) {
				path_list.add(file_bk_path);
			}
			delete_list.add(bean);
		}

		// 根据批次号及操作类型查询修改信息列表
		List<EnvModFileBean> modify_list = new ArrayList<EnvModFileBean>();
		List<EnvConfigfileModInfo> modify_info_list = confModSrv.getInfoByBatchAndFopt(ce_server_name, batch_no,
				FOPT_TYPE.MODIFY);
		List<String> modify_file_list = new ArrayList<String>();
		for (EnvConfigfileModInfo info : modify_info_list) {
			EnvModFileBean bean = new EnvModFileBean();
			String file_bk_path = info.getFile_bk_path();
			String file_bk_fname = info.getFile_bk_fname();
			bean.setDir_yn_flag(info.getDir_yn_flag());
			bean.setFull_path(file_bk_path + file_bk_fname);
			// 标志出已删除的文件
			if (full_path_list.contains(file_bk_path + file_bk_fname)) {
				bean.setDel_mod_flag(true);
				// 若修改文件所在目录已被删除，则加上标志
			} else if (path_list.contains(file_bk_path)) {
				bean.setDel_mod_flag(true);
			} else {
				bean.setDel_mod_flag(false);
			}
			modify_file_list.add(file_bk_path+file_bk_fname);
			modify_list.add(bean);
		}
		output.setModify_list(modify_list);
		output.setDelete_list(delete_list);
		logger.info("-----------queryModList end----------");
		return output;
	}

	/**
	 * Description: 查看文件列表
	 * @param input
	 * @return
	 */
	public ViewConfigModOutputBean showDirectory(ViewConfigModInputBean input) {
		logger.info("-----------showDirectory begin----------");
		ViewConfigModOutputBean output = new ViewConfigModOutputBean();

		// 非空校验
		String ce_server_name = input.getCe_server_name();
		String relative_path = input.getRelative_path();
		boolean mod_flag = input.isMod_flag();
		CFG_TYPE cfg_type = input.getCfg_type();
		Assert.assertNotEmpty(ce_server_name, ViewConfigModInputBean.CE_SERVER_NAMECN);
		// 合法性校验
		serverComSrv.checkServerIsExist(ce_server_name);
		String soc_name = serverComSrv.getFtpConfigSocByServerName(ce_server_name);
		if (Assert.isEmpty(relative_path)) {
			relative_path = dtSocSrv.getUserRootPath(soc_name);
			output.setUser_root_path(relative_path);
		}
		List<FileListBean> file_list_bean = remoteFileList.showRemoteFileList(relative_path, soc_name,
				input.getWork_seq(), mod_flag);
		if (!Assert.isEmpty(file_list_bean)&&CFG_TYPE.TOMCAT.equals(cfg_type)) {
			// 只可修改server.xml、web.xml文件
			for (FileListBean bean : file_list_bean) {
				if ("server.xml".equals(bean.getFile()) || "web.xml".equals(bean.getFile()) || "目录".equals(bean.getType())) {
					bean.setEdit_flag(true);
				} else {
					bean.setEdit_flag(false);
				}
			}
		}
		output.setFile_list_bean(file_list_bean);
		logger.info("-----------showDirectory end----------");
		return output;
	}

	/**
	 * Description: 查询配置数据源
	 * @param input
	 * @return
	 */
	public ViewConfigModOutputBean getServerConfigSocName(ViewConfigModInputBean input) {
		logger.info("-----------getServerConfigSocName begin----------");
		ViewConfigModOutputBean output = new ViewConfigModOutputBean();
		String ce_server_name = input.getCe_server_name();
		Assert.assertNotEmpty(ce_server_name, ViewConfigModInputBean.CE_SERVER_NAMECN);
		serverComSrv.checkServerIsExist(ce_server_name);
		String soc_name = serverComSrv.getFtpConfigSocByServerName(ce_server_name);
		output.setUser_root_path(dtSocSrv.getUserRootPath(soc_name));
		logger.info("-----------getServerConfigSocName end----------");
		return output;
	}
}
