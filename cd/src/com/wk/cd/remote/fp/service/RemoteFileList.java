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


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;




import com.wk.cd.common.cm.service.CommonService;
import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.CfgTool;
import com.wk.cd.enu.PROTOCOL_TYPE;
import com.wk.cd.exc.ReadConfigFileException;
import com.wk.cd.exc.SourceTypeNotSupportRemoteFileException;
import com.wk.cd.remote.fp.bean.FTPBean;
import com.wk.cd.remote.fp.bean.FileListBean;
import com.wk.cd.system.dt.info.DtSourceInfo;
import com.wk.cd.system.dt.service.DtSocService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

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
	CommonService commonService;
	private static final Log logger = LogFactory.getLog();

	/**
	 * Description: 重载方法字符编码默认为GBK
	 * @param directory
	 * @param soc_name
	 * @param work_seq
	 * @param modify_check_flag 如果是true 则根据配置文件种校验是否能修改，如果为false 认为文件都不能修改
	 * @return
	 */
	public List<FileListBean> showRemoteFileList(String business_sys_name,
			String project_name, String directory, String soc_name,
			String work_seq,boolean modify_check_flag) {
		String key = business_sys_name + "_" + project_name + "_" + soc_name;
		return sortRemoteFileList(key, directory, soc_name, work_seq,modify_check_flag);
	}
	
	public List<FileListBean> showRemoteFileList( String directory, String soc_name,
			String work_seq,boolean modify_check_flag) {
		String key = soc_name;
		return sortRemoteFileList(key, directory, soc_name, work_seq,modify_check_flag);
	}

	/**
	 * Description: 对获取的文件和文件夹进行排序
	 * @param directory
	 * @param soc_name
	 * @param work_seq
	 * @param ftp_encoding
	 * @param modify_check_flag 如果是true 则根据配置文件种校验是否能修改，如果为false 认为文件都不能修改
	 * @return
	 */
	public List<FileListBean> sortRemoteFileList(String key, String directory,
			String soc_name, String work_seq,boolean modify_check_flag) {
		logger.debug("通过[{}]获取目录[{}]下文件结构", key, directory);
		List<FileListBean> filelistBean = new ArrayList<FileListBean>();
		DtSourceInfo soc_info = dtSocService.getInfoByKey(soc_name);
		PROTOCOL_TYPE protocol_type = soc_info.getProtocol_type();
		// 远端文件列表
		if (protocol_type.equals(PROTOCOL_TYPE.PLT_FTP)
				|| protocol_type.equals(PROTOCOL_TYPE.SFTP)) {
			FTPBean bean = commonService
					.getFTPBeanBySocName(soc_name, work_seq);
			List<FileListBean> filelist = ftprCallService.lsRemotePath(bean, directory);
			filelistBean = mergeFile(filelist,modify_check_flag);
		} 
//		else if (protocol_type.equals(PROTOCOL_TYPE.TELNET)
//				|| protocol_type.equals(PROTOCOL_TYPE.SSH)) {
//			filelistBean = getRemoteFile(directory, soc_name, key,
//					protocol_type, work_seq,
//					Assert.isEmpty(soc_info.getEncoding_type()) ? "GBK"
//							: soc_info.getEncoding_type());
//		} 
		else {
			throw new SourceTypeNotSupportRemoteFileException().addScene("SOC",
					soc_name).addScene("PROTOCOL", protocol_type.getCname());
		}
		// 目标返回列表
		List<FileListBean> filelistBean_target = new ArrayList<FileListBean>();
		// 文件夹存储map
		Map<String, FileListBean> folder_map = new TreeMap<String, FileListBean>();
		// 文件存储列表
		Map<String, FileListBean> file_map = new TreeMap<String, FileListBean>();
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
		return filelistBean_target;
	}

	/**
	 * Description: 协议类型为ssh和telnet的时候调用这个方法查看文件夹下目录结构
	 * @param directory
	 * @param soc_name
	 * @param key
	 * @return
	 */
//	private List<FileListBean> getRemoteFile(String directory, String soc_name,
//			String key, PROTOCOL_TYPE protocol_type, String work_seq,
//			String ftp_encoding) {
//		List<String> file_string_list = new ArrayList<String>();
//
//		file_string_list = getRemoteFileList(directory, soc_name, key);
//
//		List<FileListBean> file_list = new ArrayList<FileListBean>();
//		String file_type = "";
//		boolean is_dir = false;
//		for (String file_string : file_string_list) {
//			String[] files = file_string.split(" ");
//			String type_string = files[0];
//			String file_name = files[files.length - 1];
//			if (file_name.startsWith(".")) {
//				continue;
//			}
//			if (type_string.startsWith("d")) {
//				is_dir = true;
//				file_type = "目录";
//				FileListBean file_bean = new FileListBean(file_name, file_type,
//						is_dir);
//				file_bean.setEdit_flag(true);
//				file_list.add(file_bean);
//			} else if (type_string.startsWith("-")) {
//				is_dir = false;
//				file_type = judgeFileType(file_name);
//				String[] file_types = getSupportEditType();
//				FileListBean bean = new FileListBean(file_name, file_type,
//						is_dir);
//				boolean edit_flag = judgeFileEdit(file_types, file_type);
//				bean.setEdit_flag(edit_flag);
//				file_list.add(bean);
//			} else {
//				continue;
//			}
//
//		}
//		return file_list;
//
//	}

	/**
	 * Description: 把读取到的目录结构字符串安行读取，并转为List<String>返回
	 * @param directory
	 * @param soc_name
	 * @param key
	 * @return
	 */
//	private List<String> getRemoteFileList(String directory, String soc_name,
//			String key) {
//		String file_string = getRemoteFileStringList(directory, soc_name, key);
//		List<String> file_string_list = new ArrayList<String>();
//		StringReader sr = new StringReader(file_string);
//		BufferedReader br = new BufferedReader(sr);
//		try {
//			while (true) {
//				String str;
//				str = br.readLine();
//				if (str != null) {
//					file_string_list.add(str);
//				} else {
//					break;
//				}
//			}
//
//		} catch (IOException e) {
//			logger.debug(ExceptionUtils.getStackTrace(e));
//		} finally {
//			sr.close();
//			try {
//				br.close();
//			} catch (IOException e) {
//			}
//		}
//
//		return file_string_list;
//
//	}

	/**
	 * Description:执行ls -al 来获取文件夹下信息并返回执行结果
	 * @param directory 需要查看的文件夹
	 * @param soc_name 数据源名
	 * @param key 有系统名、项目名、数据源名合并而成
	 * @return
	 */
//	private String getRemoteFileStringList(String directory, String soc_name,
//			String key) {
//		try {
//			ScriptCallHelper helper = HelperCreate.getHelper(key, soc_name);
//			String cmd_cd = "cd " + directory;
//			cmd_cd = getISOFname(cmd_cd, "GBK");
//			String cmd = cmd_cd + ";ls -al";
//			logger.debug("发送命令[{}]来获取文件夹目录结构", "ls -al");
//			// helper.callShellCmd(cmd_cd);
//			String str = helper.callShellCmd(cmd);
//			str = getUtf8(str);
//			return str;
//		} catch (IOException e) {
//			logger.debug(ExceptionUtils.getStackTrace(e));
//		}
//		return null;
//
//	}
	
	/**
	 * 
	 * Description: 
	 * @param file_list
	 * @param modify_check_flag 如果是true 则根据配置文件种校验是否能修改，如果为false 认为文件都不能修改
	 * @return
	 */
	private List<FileListBean> mergeFile(List<FileListBean> file_list,boolean modify_check_flag){
		List<FileListBean> files = new ArrayList<FileListBean>();
		for(FileListBean bean : file_list){
			String file_name = bean.getFile();
			if (bean.isDir()) {
				FileListBean file_bean = new FileListBean(file_name, "目录",
						true);
				file_bean.setEdit_flag(true);
				files.add(file_bean);
			} else if(modify_check_flag) {
				String file_type = judgeFileType(file_name);
				String[] file_types = getSupportEditType();
				FileListBean file_bean = new FileListBean(file_name, file_type,
						false);
				boolean edit_flag = judgeFileEdit(file_types, file_type);
				file_bean.setEdit_flag(edit_flag);
				files.add(file_bean);
			}else{
				String file_type = judgeFileType(file_name);
				FileListBean file_bean = new FileListBean(file_name, file_type,
						false);
				file_bean.setEdit_flag(false);
				files.add(file_bean);
			} 
		}
		return files;
	}

	/**
	 * Description: 根据文件名判断他的文件类型
	 * @param file_name
	 * @return
	 */
	private String judgeFileType(String file_name) {
		String type = "";
		if (file_name.contains(".")) {
			if (file_name.indexOf(".") != file_name.lastIndexOf(".")) {
				String[] s = file_name.split("\\.");
				if (s.length > 2) {
					type = s[s.length - 2];
				}
				if (!type.equalsIgnoreCase("properties")) {
					type = s[s.length - 1];
				}
			} else {
				type = file_name.substring(file_name.lastIndexOf('.') + 1,
						file_name.length());
			}
		}
		return type;
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
			throw new ReadConfigFileException().addScene("FILE",
					"cms.properties").addScene("CONFIG", "cv.edit.file");
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
//	private String getISOFname(String rfname_c, String ftp_encoding) {
//
//		try {
//
//			return new String(rfname_c.getBytes(), ftp_encoding);
//
//		} catch (UnsupportedEncodingException e) {
//			logger.error(e.toString(), e);
//			throw new CorsManagerSystemErrorException("CMS_GETISOFANEM_ERROR")
//					.addScene("E", e.toString());
//		}
//	}
//
//	private String getUtf8(String rfname_c) {
//		try {
//			return new String(rfname_c.getBytes(), "UTF-8");
//
//		} catch (UnsupportedEncodingException e) {
//			logger.error(e.toString(), e);
//			throw new CorsManagerSystemErrorException("CMS_GETISOFANEM_ERROR")
//					.addScene("E", e.toString());
//		}
//	}

}
