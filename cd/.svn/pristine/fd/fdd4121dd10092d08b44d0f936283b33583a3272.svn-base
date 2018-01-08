/**
 * Title: FileReadWriterUtil.java
 * File Description: 
 * @copyright: 2017
 * @company: CORSWORK
 * @author: Administrator
 * @version: 1.0
 * @date: 2017年7月29日
 */
package com.wk.cd.module1.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import com.wk.Controller;
import com.wk.beans.Injector;
import com.wk.cd.module1.entity.DeleteFileEntity;
import com.wk.cd.common.cm.service.CommonService;
import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.CfgTool;
import com.wk.cd.enu.IMPL_TYPE;
import com.wk.cd.exc.ReadConfigFileException;
import com.wk.cd.remote.fp.bean.FTPBean;
import com.wk.cd.remote.fp.service.FTPRCallService;
import com.wk.cd.system.dt.info.DtSourceInfo;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 文件按行读取和写入的服务
 * @author zhangj
 */
public class CmdsExecuteUtil {
	private static final Log logger = LogFactory.getLog();

	private Injector inject = Controller.getInstance().getInjector();
	@Inject
	FTPRCallService ftpRSrv;
	@Inject
	CommonService comsrv;
	// 换行符
	public static final String LINE = "\n";

	public static final String TEMP_FILE = "execute/temp/";
	
	public static final String EXECUTE_STYLE = CfgTool.getProjectPropterty("cv.remote.execute_error_stream");
	// 缓存
	public static final Map<String, DeleteFileEntity> FILE_CACHE = new ConcurrentHashMap<String, DeleteFileEntity>();
	// 上传到远程机器脚本临时文件的名字
	private final String file_name;
	// 使用 错误流方式 错误日志的名字
	private final String error_log_name;

	private final DtSourceInfo ftp_souce_info;
	
	//阶段执行上传的临时文件的相对目录
	private final String remote_relative_dir;

	public CmdsExecuteUtil(IMPL_TYPE impl_type, DtSourceInfo source_info, String remote_relative_dir) {
		inject.inject(this);
		String[] name = generateTempFileName(impl_type);
		this.file_name = name[0];
		this.error_log_name = name[1];
		this.ftp_souce_info = source_info;
		this.remote_relative_dir = remote_relative_dir;
	}

	public String[] uploadFile(String[] cmds) {
		String[] remote = getRemoteFilePath(ftp_souce_info);
		String local_file = getLocalFilePath();
		String remote_file = remote[0];
		String remote_error = remote[1];
		logger.debug("remote_file:" + remote_file);
		logger.debug("local_file:" + local_file);
		writerToLocalFile(cmds);
		logger.debug(ftp_souce_info.getProtocol_type().getCname());
		FTPBean ftpBean = comsrv.getFTPBeanBySocName(ftp_souce_info, "no seq");
		ftpRSrv.uploadFile(ftpBean, remote_file, local_file);
		com.wk.util.FileUtil.deleteFile(local_file);
		FILE_CACHE.put(remote_file,
				new DeleteFileEntity(remote_file, ftp_souce_info, System.currentTimeMillis()));
		FILE_CACHE.put(remote_error,
				new DeleteFileEntity(remote_error, ftp_souce_info, System.currentTimeMillis()));
		return new String[] { remote_file, remote_error };
	}

	/**
	 * Description: 按行写入文件
	 * @param script_content
	 * @param local_file_path
	 */
	private void writerToLocalFile(String[] script_content) {
		String local_file_path = getLocalFilePath();
		File file = new File(local_file_path);
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(file));
			if(!"true".equalsIgnoreCase(EXECUTE_STYLE)){
				writer.write("set -e" + LINE);
			}
			for (String elem : script_content) {
				writer.write(elem + LINE);
			}
		} catch (IOException e) {
			logger.error(e.toString(), e);
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					logger.error(e.toString(), e);
				}
			}
		}
		logger.debug("writerToLocalFile file_path [{}] end", local_file_path);
	}

	/**
	 * Description: 获取脚本文件远程上传路径 注意这个路径是全路径
	 * @param file_name
	 * @param source_info
	 * @return
	 */
	private String[] getRemoteFilePath(DtSourceInfo source_info) {
		String root_path = source_info.getUser_root_path();

		Assert.assertNotEmpty(root_path, "用户根路径");
		String remote_file = "";
		String remote_error = "";
		String upload_dir = getUploadRelative();
		if (root_path.endsWith("/")) {
			remote_file = root_path + upload_dir + remote_relative_dir + file_name;
			remote_error = root_path + upload_dir + remote_relative_dir + error_log_name;
		} else {
			remote_file = root_path + "/" + upload_dir + remote_relative_dir + file_name;
			remote_error = root_path + "/" + upload_dir + remote_relative_dir + error_log_name;
		}
		return new String[] { remote_file, remote_error };
	}

	/**
	 * Description: 注意这个路径是全路径
	 * @param file_name
	 * @return
	 */
	private String getLocalFilePath() {
		String web_path = CfgTool.getWebRootPath();
		String dir = web_path + TEMP_FILE;
		File file = new File(dir);
		if (!file.exists()) {
			file.mkdirs();
		}
		return web_path + TEMP_FILE + file_name;
	}


	/**
	 * Description: 获取上传的临时目录 这个目录是本项目所有涉及到上传到远程机器的 在用户更路径下的一个相对路径
	 * @return
	 */
	private String getUploadRelative() {
		String upload_path = CfgTool.getProjectPropterty("remote.upload.path");
		if (Assert.isEmpty(upload_path)) {
			throw new ReadConfigFileException().addScene("FILE", "cms.properties")
					.addScene("CONFIG", "remote.upload.path");
		}
		if (!upload_path.endsWith("/")) {
			upload_path = upload_path + "/";
		}
		return upload_path;
	}

	/**
	 * Description: 生成脚本临时文件名 以及脚本执行错误信息临时存放文件名
	 * @param impl_type
	 * @return
	 */
	private String[] generateTempFileName(IMPL_TYPE impl_type) {
		String name = UUID.randomUUID().toString();
		String file_name = "";
		String error_name = "";
		if (impl_type == IMPL_TYPE.PYTHON2 || impl_type == IMPL_TYPE.PYTHON3) {
			file_name = name + ".py";
		} else {
			file_name = name + ".sh";
		}
		error_name = name + ".log";
		return new String[] { file_name, error_name };

	}

	public void deleteFile(String file_name) {
		try {
			if (!Assert.isEmpty(file_name)) {
				DeleteFileEntity file = FILE_CACHE.get(file_name);
				if (!Assert.isEmpty(file)) {
					FTPBean ftpBean = comsrv.getFTPBeanBySocName(file.getDtSourceInfo(), "no seq");
					ftpRSrv.deleteFile(ftpBean, file.getFile_name());
				}
			}

			for (Map.Entry<String, DeleteFileEntity> m : FILE_CACHE.entrySet()) {
				DeleteFileEntity file = m.getValue();
				if ((System.currentTimeMillis()
						- file.getUpload_start_time()) >= DeleteFileEntity.TIME_OUT) {
					FTPBean ftpBean = comsrv.getFTPBeanBySocName(file.getDtSourceInfo(), "no seq");
					ftpRSrv.deleteFile(ftpBean, file.getFile_name());
				}
				
			}
		} catch (Exception e) {
			logger.debug(e.getMessage());
		}
	

	}

}
