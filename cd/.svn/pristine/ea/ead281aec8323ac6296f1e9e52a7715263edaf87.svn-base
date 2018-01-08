package com.wk.cd.remote.agent.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.DESUtil;
import com.wk.cd.common.util.FileTool;
import com.wk.cd.common.util.StringUtil;
import com.wk.cd.remote.agent.bean.ShellBean;
import com.wk.cd.remote.agent.util.AgentHelperUtil;
import com.wk.cd.remote.exc.FileNotExistException;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;
import com.wk.util.Base64;

/**
 * Agent FTP任务 
 * 
 * @author 12049
 */
public class FtpAgent {

	private static final Log logger = LogFactory.getLog();

	private static final long OFFSIZE = 500 * 1024;
	
	public ShellBean execFTPCmd(ShellBean isbean, ShellBean osbean) {
		
		return ftpTask(isbean, osbean);
	}

	/**
	 * Description: ftp的执行操作在此方法中 上传与下载
	 * 
	 * @param isbean
	 * @param osbean
	 * @param envDirMap
	 * @return
	 */
	private ShellBean ftpTask(ShellBean isbean, ShellBean osbean) {
		/**
		 * shell命令,id,终端路径,文件名,本地路径
		 */
		String cmd_str = DESUtil.decrypt(isbean.getShell());
		String id = isbean.getId();
		String remote_file_name = isbean.getRemote_file_name();
		String local_dir = isbean.getLocal_dir();
		String local_file_name = isbean.getLocal_file_name();
		
		if (cmd_str.startsWith("put ")) {
			try {
				osbean = uploadFile(isbean, osbean);
			} catch (IOException e) {
				osbean.setResult(e.getMessage());
				logger.error("agent upload file error", e);
			}
		} else if (cmd_str.startsWith("get ")) {
			try {
				osbean = downloadFile(isbean, osbean);
			} catch (IOException e) {
				osbean.setResult(e.getMessage());
				logger.error("agent download file error", e);
			}
		} else if (cmd_str.startsWith("get_file_list ")) {
			osbean = getDirectoryFileList(isbean, osbean);
		}
		osbean.setId(id);
		osbean.setShell(cmd_str);
		osbean.setRs_flag("ok");
		osbean.setExitStatus(0);
		osbean = AgentHelperUtil.getMD5Code(osbean); // 进行摘要验证
		osbean.setShell(DESUtil.encrypt(cmd_str));
		osbean.setRemote_file_name(remote_file_name);
		osbean.setLocal_dir(local_dir);
		osbean.setLocal_file_name(local_file_name);

		return osbean;
	}

	/**
	 * Description: ftp 文件上传
	 * 
	 * @param isbean
	 * @param osbean
	 * @param envDirMap
	 * @return ShellBean
	 * @throws IOException
	 */
	private ShellBean uploadFile(ShellBean isbean, ShellBean osbean)
			throws IOException {
		/**
		 * 文件内容,终端路径,文件名,当前序号
		 */
		String remote_dir = dealDestRootPath(isbean.getRemote_dir());
		String file_name = isbean.getRemote_file_name();
		int cur_num = isbean.getCur_num();
		String id = isbean.getId();
		logger.debug("agent[{}] begin upload file[{}]", id, file_name);
		if(isbean.getTotal_num() == 0 && isbean.getSlice_size() == 0){      //空文件
			byte[] content_bytes = new byte[0];
			FileTool.writeFileFromBytes(content_bytes, OFFSIZE * (cur_num - 1), remote_dir, file_name);
		}else{
			byte[] content_bytes = Base64.decode(isbean.getFile_content());
			FileTool.writeFileFromBytes(content_bytes, OFFSIZE * (cur_num - 1),
					remote_dir, file_name);
		}
		logger.debug("agent[{}] end upload file[{}]", id, file_name);
		osbean.setRemote_dir(remote_dir);
		return osbean;
	}

	/**
	 * 获取某个目录下所有文件路径 Description:
	 * 
	 * @return
	 */
	private ShellBean getDirectoryFileList(ShellBean isbean, ShellBean osbean) {
		logger.debug("agent[{}] begin getDirectoryFileList", isbean.getId());
		String remote_dir = isbean.getRemote_dir().endsWith("/") ? isbean
				.getRemote_dir() : isbean.getRemote_dir() + "/";
		File file = new File(remote_dir);
		List<String> list = new ArrayList<String>();
		if (file.isDirectory()) {
			String[] filelist = file.list();
			for (int i = 0; i < filelist.length; i++) {
				checkPath(remote_dir, filelist[i], list);
			}

		}

		osbean.setFile_list(list);
		logger.debug("agent[{}] end getDirectoryFileList", isbean.getId());
		return osbean;
	}

	public void checkPath(String remote_dir, String file_name, List<String> list) {
		logger.debug("file_name[{}]", file_name);
		File file = new File(remote_dir + file_name);
		if (file.isDirectory()) {
			String[] filelist = file.list();
			for (int i = 0; i < filelist.length; i++) {
				checkPath(remote_dir, file_name + "/" + filelist[i], list);
			}
		} else {
			list.add(file_name);
		}
	}

	/**
	 * Description: ftp 文件下载
	 * 
	 * @param isbean
	 * @param osbean
	 * @param envDirMap
	 * @return ShellBean
	 * @throws IOException
	 */
	private ShellBean downloadFile(ShellBean isbean, ShellBean osbean)
			throws IOException {
		/**
		 * 终端路径,文件名
		 */
		String remote_dir = dealDestRootPath(isbean.getRemote_dir());
		String file_name = isbean.getRemote_file_name();
		String id = isbean.getId();
		int cur_num = isbean.getCur_num();
		int total_num = 0;
		long slice_size = 0;
		logger.debug("agent[{}] begin download file[{}]", id, file_name);
		/**
		 * ftp下载文件
		 */
		String path = remote_dir + file_name;
		File file = new File(path);
		if (!file.exists()) {
			throw new FileNotExistException().addScene("FILE", file);
		}

		long file_total_size = file.length();
		int exe_num = (int) (file_total_size / OFFSIZE); // 执行次数
		long remain_pac_size = file_total_size % OFFSIZE; // 剩余分包大小
		byte[] file_contents = null; // 文件内容byte数组

		if (remain_pac_size > 0) {
			total_num = exe_num + 1;
			if (cur_num < total_num) {
				slice_size = OFFSIZE;
			} else {
				slice_size = remain_pac_size;
			}
		} else if (remain_pac_size == 0) {
			total_num = exe_num;
			slice_size = exe_num == 0 ? 0 : OFFSIZE;
		}

		file_contents = FileTool.getFileToByte(file, OFFSIZE * (cur_num - 1),
				(int) slice_size);
		osbean.setSlice_size(slice_size); // 分包大小
		osbean.setFile_total_size(file_total_size);
		osbean.setTotal_num(total_num);
		if(total_num == 0 && slice_size == 0){
			osbean.setFile_content("");
		}else{
			osbean.setFile_content(new String(Base64.encode(file_contents)));
		}
		osbean.setCur_num(cur_num);
		osbean.setRemote_dir(remote_dir);
		logger.debug("agent[{}] end download file[{}]", id, file_name);
		return osbean;
	}

	/**
	 * 处理“./”开头的路径,将其转换成主目录路径
	 * Description: 
	 * @return
	 */
	private String dealDestRootPath(String path){
		if(path.startsWith("./")){
			Process proc = null;
			try {
				String[] cmds = {"sh", "-c", "cd;pwd"};
				proc = Runtime.getRuntime().exec(cmds);
				String rs = StringUtil.stream2str(proc.getInputStream());
				if(!Assert.isEmpty(rs)){
					return path.replace("./", rs.trim().endsWith("/") ? rs.trim() : rs.trim() + "/");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				if(!Assert.isEmpty(proc)){
					proc.destroy();
				}
			}
		}
		return path;
	}
}
