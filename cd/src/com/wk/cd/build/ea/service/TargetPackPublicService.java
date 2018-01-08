/**
 * Title: TargetPackPublicService.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: xuph
 * @version: 1.0
 * @date: 2016年11月17日
 */
package com.wk.cd.build.ea.service;

import java.io.File;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jcraft.jsch.Logger;
import com.wk.cd.build.ea.bean.TarPacDownloadBean;
import com.wk.cd.build.ea.bean.TargetPackageBean;
import com.wk.cd.build.ea.dao.EnvTagStorageDaoService;
import com.wk.cd.build.ea.dao.EnvTaskDaoService;
import com.wk.cd.build.ea.dao.UuFilelistDaoService;
import com.wk.cd.build.ea.info.EnvTagStorageInfo;
import com.wk.cd.build.ea.info.UuFilelistInfo;
import com.wk.cd.build.en.dao.CeServerDsDaoService;
import com.wk.cd.build.en.service.ServerCommonService;
import com.wk.cd.build.exc.AddDirectoryIsIllegalityException;
import com.wk.cd.build.exc.EnvStorageTaskIsExistException;
import com.wk.cd.build.exc.ServerDsIsExistException;
import com.wk.cd.build.exc.TargetIsExistException;
import com.wk.cd.build.exc.TargetIsNotExistException;
import com.wk.cd.build.exc.TargetTaskTypeException;
import com.wk.cd.enu.STORAGE_STATUS;
import com.wk.cd.enu.TASK_TYPE;
import com.wk.cd.common.cm.service.CommonService;
import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.CfgTool;
import com.wk.cd.exc.ReadConfigFileException;
import com.wk.cd.remote.fp.bean.FTPBean;
import com.wk.cd.remote.fp.service.FTPRCallService;
import com.wk.cd.system.dt.dao.DtSourceDaoService;
import com.wk.cd.system.dt.info.DtSourceInfo;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 入库服务
 * @author xuph
 */
public class TargetPackPublicService {
	@Inject
	private EnvTagStorageDaoService envTagStorageDaoService;
	@Inject
	private TargetPackPublicService targetPackPublicService;
	@Inject
	private GenerateExcelListService generateExcelListSrv;
	@Inject
	private CeServerDsDaoService ceServerDsDaoService;
	@Inject
	private DtSourceDaoService dtSourceDaoService;
	@Inject
	private UuFilelistDaoService uuFilelistDaoService;
	@Inject
	private EnvTaskDaoService envTaskDaoService;
	@Inject
	private EnvTaskPublicService envTaskPublicSrv;
	@Inject
	private FTPRCallService ftpRSrv;
	@Inject
	private CommonService comsrv;
	@Inject
	private ServerCommonService serverComSrv;

	private static final Log log = LogFactory.getLog();
	/**
	 * 定义包含不合法字段的数组
	 */
	private static final String[] illegal_str = { "/", ">", "<", "|", "\"", "*", "\\", ":", "?", " " };
	/**
	 * 打包后缀
	 */
	private static final String[] suffix_str = { ".tar" };

	/**
	 * Description: 校检服务器数据源是否存在，不存在抛出异常
	 * @param server_name
	 * @param soc_name
	 */
	public void checkCeServerDsIsExist(String server_name, String soc_name) {
		if (ceServerDsDaoService.countServerDtsource(server_name, soc_name) <= 0) {
			throw new ServerDsIsExistException().addScene("SERVER", server_name).addScene("SOC", soc_name);
		}
	}

	/**
	 * Description: 校检环境下面是否存在发布、执行、集成
	 * @param env_name
	 */
	public void checkTargetStorage(String env_name) {
		if (envTaskDaoService.countTaskByEnvAndStatus(env_name, TASK_TYPE.BUILD) > 0) {
			throw new TargetTaskTypeException().addScene("ENV", env_name).addScene("TASK", TASK_TYPE.BUILD.getCname());
		}
		if (envTaskDaoService.countTaskByEnvAndStatus(env_name, TASK_TYPE.INTEGRATION) > 0) {
			throw new TargetTaskTypeException().addScene("ENV", env_name).addScene("TASK", TASK_TYPE.INTEGRATION.getCname());
		}
		if (envTaskDaoService.countTaskByEnvAndStatus(env_name, TASK_TYPE.PUBLISH) > 0) {
			throw new TargetTaskTypeException().addScene("ENV", env_name).addScene("TASK", TASK_TYPE.PUBLISH.getCname());
		}
		if (envTagStorageDaoService.countStorageByEnvAndStatus(env_name, STORAGE_STATUS.STORAGING) > 0) {
			throw new EnvStorageTaskIsExistException().addScene("ENV", env_name);
		}
	}

	/**
	 * Description: 校检入库信息是否存在，不存在抛出异常
	 * @param storage_id
	 */
	public void checkTargetIsExist(String storage_id) {
		if (envTagStorageDaoService.countByStorageId(storage_id) <= 0) {
			throw new TargetIsExistException().addScene("STORID", storage_id);
		}
	}

	/**
	 * Description: 校检入库信息是否存在，存在抛出异常
	 * @param storage_id
	 */
	public void checkTargetIsNotExist(String storage_id) {
		if (envTagStorageDaoService.countByStorageId(storage_id) > 0) {
			throw new TargetIsNotExistException().addScene("STORID", storage_id);
		}
	}

	/**
	 * Description: 对纯目录格式的校检
	 * @param dir_pure_path 纯目录字段style：/XXXX/XXXX/
	 */
	public void checkPureDirectory(String dir_pure_path) {
		// 传入字段为空
		Assert.assertNotEmpty(dir_pure_path, "传入的目录");
		String dir_execption = dir_pure_path;
		// 统一目录格式/XXXX/XXXX/
		dir_pure_path = mergeDir(dir_pure_path);
		if (!dir_pure_path.equals("/")) {
			String pure_path = dir_pure_path.substring(1);
			if (pure_path.charAt(0) == '/') {
				throw new AddDirectoryIsIllegalityException().addScene("DIR", dir_execption);
			}
			// 以“/”分割字段
			String[] str = pure_path.split("/");
			for (int i = 0; i < str.length; i++) {
				if (str[i].equals("") || str[i].length() == 0) {
					throw new AddDirectoryIsIllegalityException().addScene("DIR", dir_execption);
				}
				if (str[i].startsWith(".")) {
					throw new AddDirectoryIsIllegalityException().addScene("DIR", dir_execption);
				}
				for (int j = 0; j < illegal_str.length; j++) {
					if (str[i].contains(illegal_str[j])) {
						throw new AddDirectoryIsIllegalityException().addScene("DIR", dir_execption);
					}
				}
			}
		}
	}

	/**
	 * Description: 将目录格式进行前后都加斜杠的统一
	 * @param dir
	 * @return
	 */
	public String mergeDir(String dir) {
		if (!Assert.isEmpty(dir)) {
			if ('/' != (dir.charAt(0))) {
				dir = "/" + dir;
			}
			if ('/' != dir.charAt(dir.length() - 1)) {
				dir = dir + "/";
			}
		}
		return dir;
	}

	/**
	 * Description: 校检远程目录是否存在
	 * @param soc_name
	 * @param remote_dir
	 * @param work_seq
	 */
	public void checkRemoteDirExist(String soc_name, String remote_dir, String work_seq) {
		comsrv.checkRemoteFileExist(soc_name, work_seq, remote_dir, false);
	}

	/**
	 * Description: 获得入库Excel清单临时目录
	 * @return
	 */
	public String getStorageExcelTempDir() {
		String file_path = "";
		String web_root_dir = CfgTool.getProjectPropterty("web.root.path");
		if (Assert.isEmpty(web_root_dir)) {
			throw new ReadConfigFileException().addScene("FILE", "cms.properties").addScene("CONFIG", "web.root.path");
		}
		if (web_root_dir.endsWith("/")) {
			file_path = web_root_dir + "temp/";
		} else {
			file_path = web_root_dir + "/temp/";
		}
		File file = new File(file_path);
		if (!file.exists()) {
			file.mkdirs();
		}
		return file_path;
	}

	/**
	 * Description:入库日志目录
	 * @return
	 */
	public String getStorageDirectory() {
		String file_path = "";
		String web_root_dir = CfgTool.getProjectPropterty("web.root.path");
		if (Assert.isEmpty(web_root_dir)) {
			throw new ReadConfigFileException().addScene("FILE", "cms.properties").addScene("CONFIG", "web.root.path");
		}
		if (web_root_dir.endsWith("/")) {
			file_path = web_root_dir + "storage/";
		} else {
			file_path = web_root_dir + "/storage/";
		}
		File file = new File(file_path);
		if (!file.exists()) {
			file.mkdirs();
		}
		return file_path;
	}

	/**
	 * Description:入库TAR包本地目录
	 * @return
	 */
	public String getStorageTarPath(String storage_id) {
		String file_path = "";
		String web_root_dir = CfgTool.getProjectPropterty("web.root.path");
		if (Assert.isEmpty(web_root_dir)) {
			throw new ReadConfigFileException().addScene("FILE", "cms.properties").addScene("CONFIG", "web.root.path");
		}
		if (web_root_dir.endsWith("/")) {
			file_path = web_root_dir + "storage/" + storage_id;
		} else {
			file_path = web_root_dir + "/storage/" + storage_id;
		}
		File file = new File(file_path);
		if (!file.exists()) {
			file.mkdirs();
		}
		return file_path;
	}

	/**
	 * Description: 获得入库文件信息
	 * @param file_name
	 * @param storage_id
	 * @return
	 */
	public TarPacDownloadBean getRemoteStorFile(String file_name, String storage_id) {
		EnvTagStorageInfo tagst = envTagStorageDaoService.getInfoByKey2(storage_id);
		TarPacDownloadBean pack_file = new TarPacDownloadBean();
		// 文件清单UUID
		String uu_id = tagst.getStorage_list_uuid();
		String inte_ver_num = tagst.getInte_ver_num();
		// 获得文件清单列表
		List<UuFilelistInfo> file_list = uuFilelistDaoService.getInfoByFileUuId(uu_id);
		if (!Assert.isEmpty(file_list)) {
			UuFilelistInfo file_bean = file_list.get(0);
			String ce_server_name = file_bean.getServer_name();
			if (!Assert.isEmpty(ce_server_name)) {
				// 得到配置数据源
				serverComSrv.checkServerIsExist(ce_server_name);
				String soc_name = serverComSrv.getFtpConfigSocByServerName(ce_server_name);
				DtSourceInfo soc_info = new DtSourceInfo();
				soc_info.setSoc_name(soc_name);
				DtSourceInfo dt_soc = dtSourceDaoService.getInfoByKey(soc_info);
				// 环境名
				String env_name = tagst.getEnv_name();
				String local_full_path = getStorageTarPath(storage_id);
				for (UuFilelistInfo flist : file_list) {
					String pack_name = flist.getPackage_name();
					String relative_path = flist.getTarget_relative_path();
					String local_path = mergeDir(local_full_path) + file_name;
					if (file_name.equals(pack_name)) {
						String romote_path = null;
						if (Assert.isEmpty(relative_path)) {
							romote_path = mergeDir(dt_soc.getUser_root_path()) + env_name + "/" + storage_id + "/" + file_name;
							if (!Assert.isEmpty(inte_ver_num)) {
								romote_path = mergeDir(dt_soc.getUser_root_path()) + env_name + "/" + storage_id + "/" + inte_ver_num + "/" + file_name;
							}
						} else {
							romote_path = mergeDir(dt_soc.getUser_root_path()) + env_name + "/" + storage_id + mergeDir(relative_path) + file_name;
							if (!Assert.isEmpty(inte_ver_num)) {
								romote_path = mergeDir(dt_soc.getUser_root_path()) + env_name + "/" + storage_id + "/" + inte_ver_num + mergeDir(relative_path) + file_name;
							}
						}
						pack_file.setLocal_full_path(local_path);
						pack_file.setRomote_full_path(romote_path);
						pack_file.setSoc_name(soc_name);
						break;
					}
				}
			}
		}
		return pack_file;
	}

	/**
	 * Description: 格式目录(减/)
	 * @param file_path
	 * @return
	 */
	public String formatDirD(String file_path) {
		if (!Assert.isEmpty(file_path)) {
			if ('/' == (file_path.charAt(0))) {
				file_path = file_path.substring(1);
			}
			if ('/' == file_path.charAt(file_path.length() - 1)) {
				file_path = file_path.substring(0, file_path.length() - 1);
			}
		}
		return file_path;
	}

	/**
	 * Description: 判断字符串是否包含中文
	 */
	public boolean isContainChinese(String str) {
		if (!Assert.isEmpty(str)) {
			Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
			Matcher m = p.matcher(str);
			if (m.find()) {
				return true;
			}
			return false;
		} else {
			return false;
		}
	}

	/**
	 * Description: 判断字符是否包含后缀
	 * @param str
	 * @return
	 */
	public boolean isContainSuffix(String str) {
		boolean mark = true;
		if (!str.contains(".")) {
			mark = true;
		} else {
			String suffix = str.substring(str.lastIndexOf("."));
			for (int i = 0; i < suffix_str.length; i++) {
				if (suffix.equals(suffix_str[i])) {
					mark = false;
				}
			}
		}
		return mark;
	}

	/**
	 * Description: 生成入库清单并上传
	 * @param tar_package_list
	 * @param env_name
	 * @param storage_id
	 */
	public void uploadStorExcel(List<TargetPackageBean> tar_package_list, String env_name, String storage_id, String ce_server_name, String version_id) {
		log.debug("#######生成入库清单并上传开始@@@@########");
		String tar_file_path = targetPackPublicService.getStorageDirectory();
		String tar_file_name = envTaskPublicSrv.generateListName(version_id, storage_id);

		TarPacDownloadBean bean = new TarPacDownloadBean();
		// 得到配置数据源
		serverComSrv.checkServerIsExist(ce_server_name);
		String soc_name = serverComSrv.getFtpConfigSocByServerName(ce_server_name);
		DtSourceInfo soc_info = new DtSourceInfo();
		soc_info.setSoc_name(soc_name);
		DtSourceInfo dt_soc = dtSourceDaoService.getInfoByKey(soc_info);
		bean.setSoc_name(dt_soc.getSoc_name());
		bean.setLocal_full_path(tar_file_path + tar_file_name);
		bean.setRomote_full_path(mergeDir(dt_soc.getUser_root_path()) + tar_file_name);
		// 生成文件清单EXCEL
		generateExcelListSrv.generateStorgExcel(tar_package_list, tar_file_path + tar_file_name,mergeDir(dt_soc.getUser_root_path()));
		FTPBean ftpBean = comsrv.getFTPBeanBySocName(bean.getSoc_name(), storage_id);
		ftpRSrv.uploadFile(ftpBean, bean.getRomote_full_path(), bean.getLocal_full_path());
		log.debug("#######生成入库清单并上传结束@@@@########");
	}

}
