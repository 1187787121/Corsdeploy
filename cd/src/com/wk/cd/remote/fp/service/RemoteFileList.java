/**
 * Title: ShowDirectoryAction.java
 * File Description: 查询目录下配置文件即目录列表
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Zhangj
 * @version: 1.0
 * @date: 2016年4月1日
 */
package com.wk.cd.remote.fp.service;

import com.wk.cd.common.cm.service.CommonService;
import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.CfgTool;
import com.wk.cd.common.util.FileTool;
import com.wk.cd.common.util.SVNUtil;
import com.wk.cd.enu.IMPL_TYPE;
import com.wk.cd.enu.PROTOCOL_TYPE;
import com.wk.cd.exc.CorsManagerSystemErrorException;
import com.wk.cd.exc.ReadConfigFileException;
import com.wk.cd.module1.Result;
import com.wk.cd.module1.exc.SourceTypeNotSupportRemoteFileException;
import com.wk.cd.module1.impl.SVN;
import com.wk.cd.module1.info.ModuleSourceInfo;
import com.wk.cd.remote.agent.service.AgentRSession;
import com.wk.cd.remote.agent.util.AgentCommonTool;
import com.wk.cd.remote.fp.bean.FTPBean;
import com.wk.cd.remote.fp.bean.FileListBean;
import com.wk.cd.remote.sh.bean.ShExecRsBean;
import com.wk.cd.system.dt.info.DtSourceInfo;
import com.wk.cd.system.dt.service.DtSocService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

import org.apache.commons.lang.exception.ExceptionUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class Description: 查询目录下配置文件即目录列表
 * @author Zhangj
 */
public class RemoteFileList {
	@Inject
	DtSocService dtSocService;
	@Inject
	private SFTPJSCHRCallService sftp_srv;
	@Inject
	FTPRCallService ftprCallService;
	@Inject 
	AgentFTPRCallService agentFTPRCallService;
	@Inject
	CommonService commonService;
	private static final Log logger = LogFactory.getLog();

	/**
	 * Description: 获取目录下的文件和文件夹排序并返回
	 * @param directory
	 * @param soc_name
	 * @param work_seq
	 * @param ftp_encoding
	 * @param modify_check_flag 如果是true 则根据配置文件种校验是否能修改，如果为false 认为文件都不能修改
	 * @return
	 */
	public List<FileListBean> showRemoteFileList(String directory, String soc_name,
			String work_seq, boolean modify_check_flag,String soc_ip) {
		logger.debug("获取目录[{}]下文件结构", directory);
		List<FileListBean> filelistBean = new ArrayList<FileListBean>();
		DtSourceInfo soc_info = dtSocService.getInfoByKey(soc_name,soc_ip);
		PROTOCOL_TYPE protocol_type = soc_info.getProtocol_type();
		// 远端文件列表
		if (protocol_type == PROTOCOL_TYPE.PLT_FTP || protocol_type == PROTOCOL_TYPE.SFTP) {
			FTPBean bean = commonService.getFTPBeanBySocName(soc_name, work_seq);
			List<FileListBean> filelist = ftprCallService.lsRemotePath(bean, directory);
			filelistBean = mergeFile(filelist, modify_check_flag);
		} else if (protocol_type == PROTOCOL_TYPE.AGENT) {
			FTPBean bean = commonService.getFTPBeanBySocName(soc_name, work_seq, soc_ip);
			List<FileListBean> filelist = agentFTPRCallService.lsRemotePath(bean, directory);
			filelistBean = mergeFile(filelist, modify_check_flag);
		}else if(protocol_type == PROTOCOL_TYPE.SVN){
			//TODO 添加SVN的目录列表
			List<FileListBean> filelist = new ArrayList<FileListBean>();
			if (CfgTool.getProjectPropterty("cv.svn.use.svnkit.client").equalsIgnoreCase("true")) {
				filelist = SVNUtil.lsRemotePath(soc_info, directory);
			}else{
				DtSourceInfo local_soc = dtSocService.getInfoByKey("local");
				ModuleSourceInfo msi = new ModuleSourceInfo(local_soc, soc_info);
				String[] cmd = new String[]{"svn list svn://"+ soc_ip + directory};
				SVN svn = new SVN(msi, cmd);
				Result result = svn.run();
				if(!Assert.isEmpty(result) && !Assert.isEmpty(result.getMsg())){
					String msg = result.getMsg();
					String[] msgs = msg.split("\n");
					for(String str : msgs){
						FileListBean bean = new FileListBean();
						if(str.endsWith("/")){
							bean.setDir(true);
							bean.setFile(str.substring(0, str.length() - 1));
						}else{
							bean.setDir(false);
							bean.setFile(str);
							
						}
						
						filelist.add(bean);
					}
				}
			}
			filelistBean = mergeFile(filelist, modify_check_flag);
		}
		// else if (protocol_type.equals(PROTOCOL_TYPE.TELNET)
		// || protocol_type.equals(PROTOCOL_TYPE.SSH)) {
		// filelistBean = getRemoteFile(directory, soc_name, key,
		// protocol_type, work_seq,
		// Assert.isEmpty(soc_info.getEncoding_type()) ? "GBK"
		// : soc_info.getEncoding_type());
		// }
		else {
			throw new SourceTypeNotSupportRemoteFileException().addScene("SOC", soc_name)
					.addScene("PROTOCOL", protocol_type.getCname());
		}
		// 目标返回列表
		List<FileListBean> filelistBean_target = new ArrayList<FileListBean>();
		// 文件夹存储map
		Map<String, FileListBean> folder_map = new TreeMap<String, FileListBean>();
		// 文件存储列表
		Map<String, FileListBean> file_map = new TreeMap<String, FileListBean>();
		if(Assert.notEmpty(filelistBean)){
			for (FileListBean fileListBean : filelistBean) {
				String fileName = fileListBean.getFile();
				if (fileListBean.isDir()) {
					folder_map.put(fileName, fileListBean);
				} else {
					file_map.put(fileName, fileListBean);
				}
			}
			
			for (String folder_key : folder_map.keySet()) {
				filelistBean_target.add(folder_map.get(folder_key));
			}
			for (String file_key : file_map.keySet()) {
				filelistBean_target.add(file_map.get(file_key));
			}
		}
		return filelistBean_target;
	}

	/**
	 * Description: 协议类型为Agent的时候调用这个方法查看文件夹下目录结构
	 * @param directory
	 * @param soc_name
	 * @param key
	 * @return
	 */
	private List<FileListBean> getRemoteFile(String directory, DtSourceInfo info, String ftp_encoding, boolean isWindows) {
		List<String> file_string_list = new ArrayList<String>();

		file_string_list = getRemoteFileList(directory, info, isWindows);

		List<FileListBean> file_list = new ArrayList<FileListBean>();
		String file_type = "";
		boolean is_dir = false;
		for (String file_string : file_string_list) {
			String[] files = file_string.split(" ");
			String type_string = files[0];
			String file_name = files[files.length - 1];
			if (file_name.startsWith(".")) {
				continue;
			}
			if (type_string.startsWith("d")) {
				is_dir = true;
				file_type = "目录";
				FileListBean file_bean = new FileListBean(file_name, file_type, is_dir);
				file_bean.setEdit_flag(true);
				file_list.add(file_bean);
			} else if (type_string.startsWith("-")) {
				is_dir = false;
				file_type = FileTool.getFileType(file_name);
				String[] file_types = getSupportEditType();
				FileListBean bean = new FileListBean(file_name, file_type, is_dir);
				boolean edit_flag = judgeFileEdit(file_types, file_type);
				bean.setEdit_flag(edit_flag);
				file_list.add(bean);
			} else {
				continue;
			}

		}
		return file_list;

	}

	/**
	 * Description: 把读取到的目录结构字符串安行读取，并转为List<String>返回
	 * @param directory
	 * @param soc_name
	 * @param key
	 * @return
	 */
	private List<String> getRemoteFileList(String directory,DtSourceInfo info, boolean isWindows) {
		String file_string = getRemoteFileStringList(directory, info, isWindows);
		List<String> file_string_list = new ArrayList<String>();
		StringReader sr = new StringReader(file_string);
		BufferedReader br = new BufferedReader(sr);
		try {
			while (true) {
				String str;
				str = br.readLine();
				if (str != null) {
					file_string_list.add(str);
				} else {
					break;
				}
			}

		} catch (IOException e) {
			logger.debug(ExceptionUtils.getStackTrace(e));
		} finally {
			sr.close();
			try {
				br.close();
			} catch (IOException e) {
			}
		}

		return file_string_list;

	}

	/**
	 * Description:执行ls -al 来获取文件夹下信息并返回执行结果
	 * @param directory 需要查看的文件夹
	 * @param soc_name 数据源名
	 * @param key 有系统名、项目名、数据源名合并而成
	 * @return
	 */
	private String getRemoteFileStringList(String directory,DtSourceInfo info, boolean isWindows) {
			AgentRSession session = new AgentRSession(info.getSoc_ip(), info.getSoc_port(), IMPL_TYPE.SHELL, 2, AgentRSession.SYNCHRO_TYPE, true, "common/");
			
			String cmd_cd = "";
			if(isWindows){
				cmd_cd = "cd /d " + directory;
			}else{
				cmd_cd = "cd " + directory;
			}
			
			cmd_cd = getISOFname(cmd_cd, "GBK");
			String cmd = "ls -al";
			logger.debug("发送命令[{}]来获取文件夹目录结构", "ls -al");
			// helper.callShellCmd(cmd_cd);
			session.sendCmd(cmd_cd);
			String str = session.sendCmd(cmd).getRs_msg();
			str = getUtf8(str);
			return str;
	}

	/**
	 * Description:
	 * @param file_list
	 * @param modify_check_flag 如果是true 则根据配置文件种校验是否能修改，如果为false 认为文件都不能修改
	 * @return
	 */
	private List<FileListBean> mergeFile(List<FileListBean> file_list, boolean modify_check_flag) {
		List<FileListBean> files = new ArrayList<FileListBean>();
		if(Assert.notEmpty(file_list)){
			for (FileListBean bean : file_list) {
				String file_name = bean.getFile();
				if (bean.isDir()) {
					FileListBean file_bean = new FileListBean(file_name, "目录", true);
					file_bean.setEdit_flag(true);
					files.add(file_bean);
				} else if (modify_check_flag) {
					String file_type = FileTool.getFileType(file_name);
					String[] file_types = getSupportEditType();
					FileListBean file_bean = new FileListBean(file_name, file_type, false);
					boolean edit_flag = judgeFileEdit(file_types, file_type);
					file_bean.setEdit_flag(edit_flag);
					files.add(file_bean);
				} else {
					String file_type = FileTool.getFileType(file_name);
					FileListBean file_bean = new FileListBean(file_name, file_type, false);
					file_bean.setEdit_flag(false);
					files.add(file_bean);
				}
			}
		}
		return files;
	}

	/**
	 * Description: 判断文件是否支持编辑
	 * @param file_types
	 * @param file_type
	 * @return
	 */
	private boolean judgeFileEdit(String[] file_types, String file_type) {
		boolean edit_flag = false;
		for (String str1 : file_types) {
			if (str1.equalsIgnoreCase(file_type)) {
				edit_flag = true;

			}
		}
		return edit_flag;

	}

	/**
	 * Description: 获取支持编辑的文件类型 数组
	 * @return
	 */
	private String[] getSupportEditType() {
		String edit_file_type = CfgTool.getProjectPropterty("cv.edit.file");
		if (Assert.isEmpty(edit_file_type)) {
			throw new ReadConfigFileException().addScene("FILE", "cms.properties")
					.addScene("CONFIG", "cv.edit.file");
		}

		String[] file_types = edit_file_type.split(",");
		return file_types;
	}

	/**
	 * Description: 用于字符编码的转换
	 * @param rfname_c
	 * @param ftp_encoding
	 * @return
	 */
	private String getISOFname(String rfname_c, String ftp_encoding) {

		try {

			return new String(rfname_c.getBytes(), ftp_encoding);

		} catch (UnsupportedEncodingException e) {
			logger.error(e.toString(), e);
			throw new CorsManagerSystemErrorException("CMS_GETISOFANEM_ERROR").addScene("E",
					e.toString());
		}
	}

	private String getUtf8(String rfname_c) {
		try {
			return new String(rfname_c.getBytes(), "UTF-8");

		} catch (UnsupportedEncodingException e) {
			logger.error(e.toString(), e);
			throw new CorsManagerSystemErrorException("CMS_GETISOFANEM_ERROR").addScene("E",
					e.toString());
		}
	}

}
