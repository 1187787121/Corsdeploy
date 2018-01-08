package com.wk.cd.remote.fp.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.exception.ExceptionUtils;

import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.CfgTool;
import com.wk.cd.common.util.FileTool;
import com.wk.cd.enu.IMPL_TYPE;
import com.wk.cd.exc.CorsManagerSystemErrorException;
import com.wk.cd.exc.ReadConfigFileException;
import com.wk.cd.remote.agent.service.AgentRSession;
import com.wk.cd.remote.agent.util.AgentCommonTool;
import com.wk.cd.remote.exc.FileNotExistException;
import com.wk.cd.remote.fp.bean.FTPBean;
import com.wk.cd.remote.fp.bean.FileListBean;
import com.wk.cd.remote.sh.bean.ShExecRsBean;
import com.wk.lang.AppException;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;
/**
 * Class Description: Agent FTPԶ�̵��÷�����
 * @author hulc (2017-12-29)
 */
public class AgentFTPRCallService implements FTPRCallInterface{

	private static final Log logger = LogFactory.getLog();
	
	/**
	 * FTP���ط���
	 * @author hulc (2017-12-29)
	 * @param bean 
	 * @param rfile Զ���ļ���ȫ·����/home/test/A.txt
	 * @param lfile �����ļ���ȫ·����/home/local/A.txt
	 */
	@Override
	public void downloadFile(FTPBean bean, String rfile, String lfile) {
		String rfpath, rfname;
		String lfpath,lfname;
		String nlfile = FileTool.filePathCvt(lfile);
		lfpath = FileTool.getFilePath(nlfile);
		lfname = FileTool.getFileName(nlfile);
		rfpath = FileTool.getFilePath(rfile);
		rfname = FileTool.getFileName(rfile);
		
		int port = bean.getSoc_port();
		if(port == 0){
			port = AgentCommonTool.getPort();
		}
		
		AgentRSession sess = new AgentRSession(bean.getSoc_ip(), port, IMPL_TYPE.FTP, 0, AgentRSession.SYNCHRO_TYPE, false, null);
		logger.info("remote file path=[{}] name=[{}]", rfpath, rfname);
		sess.sendCmd("cd " + rfpath);
		logger.info("local file path=[{}] name=[{}]", lfpath, lfname);
		sess.sendCmd("lcd " + lfpath);
		ShExecRsBean result = sess.sendCmd("get " + rfname);
		logger.info("download rs=[{}]", result);
		if(!result.getIs_succ()){
			throw new AppException(result.getErr_msg());
		}
	}

	@Override
	public void uploadFile(FTPBean bean, String rfile, String lfile) {
		String rfpath, rfname;
		String lfpath,lfname;
		String nlfile = FileTool.filePathCvt(lfile);
		lfpath = FileTool.getFilePath(nlfile);
		lfname = FileTool.getFileName(nlfile);
		rfpath = FileTool.getFilePath(rfile);
		rfname = FileTool.getFileName(rfile);
		
		int port = bean.getSoc_port();
		if(port == 0){
			port = AgentCommonTool.getPort();
		}
		
		AgentRSession sess = new AgentRSession(bean.getSoc_ip(), port, IMPL_TYPE.FTP, 0, AgentRSession.SYNCHRO_TYPE, false, null);
		logger.info("remote file path=[{}] name=[{}]", rfpath, rfname);
		System.out.println("remote file path=[{}] name=[{}]" + rfpath + rfname);
		sess.sendCmd("cd " + rfpath);
		logger.info("local file path=[{}] name=[{}]", lfpath, lfname);
		System.out.println("local file path=[{}] name=[{}]" + lfpath + lfname);
		sess.sendCmd("lcd " + lfpath);
		ShExecRsBean result = sess.sendCmd("put " + lfname);
		logger.info("upload rs=[{}]", result);
		if(!result.getIs_succ()){
			throw new AppException(result.getErr_msg());
		}
	}

	@Override
	public void downloadDir(FTPBean bean, String rdir, String ldir) {
		throw new AppException("Agent�ݲ�֧������Ŀ¼");
	}

	@Override
	public void uploadDir(FTPBean bean, String rdir, String ldir) {
		throw new AppException("Agent�ݲ�֧���ϴ�Ŀ¼");
	}

	/**
	 * 
	 * Description: 
	 * @param bean
	 * @param rdir(ȫ·��)
	 */
	@Override
	public void deleteFile(FTPBean bean, String rdir) {
		
		int port = bean.getSoc_port();
		if(port == 0){
			port = AgentCommonTool.getPort();
		}
		
		AgentRSession sess = new AgentRSession(bean.getSoc_ip(), port, IMPL_TYPE.SHELL, 1, AgentRSession.SYNCHRO_TYPE, true, "compTest/test/");
		String remote_env = AgentCommonTool.checkRemoteEnv(bean.getSoc_ip());
		String cmd = "";
		if(remote_env.equals("windows")){
			cmd = "del /f /q " + rdir;
		}else{
			cmd = "rm -rf " + rdir;
		}
		logger.info("delete file cmd=[{}]", cmd);
		ShExecRsBean result = sess.sendCmd(cmd);
		if(!result.getIs_succ()){
			throw new AppException(result.getErr_msg());
		}
	}

	/**
	 * ɾ��һ���ļ���
	 * Description: 
	 * @param bean
	 * @param rdir
	 */
	@Override
	public void deleteDir(FTPBean bean, String rdir) {
		
		int port = bean.getSoc_port();
		if(port == 0){
			port = AgentCommonTool.getPort();
		}
		
		AgentRSession sess = new AgentRSession(bean.getSoc_ip(), port, IMPL_TYPE.SHELL, 1, AgentRSession.SYNCHRO_TYPE, true, "compTest/test/");
		String remote_env = AgentCommonTool.checkRemoteEnv(bean.getSoc_ip());
		String cmd = "";
		if(remote_env.equals("windows")){
			cmd = "rd /s /q " + rdir;
		}else{
			cmd = "rm -rf " + rdir;
		}
		logger.info("delete dir cmd=[{}]", cmd);
		ShExecRsBean result = sess.sendCmd(cmd);
		if(!result.getIs_succ()){
			throw new AppException(result.getErr_msg());
		}
	}

	/**
	 * �г���ǮĿ¼�������ļ���Ŀ¼
	 * Description: 
	 * @param bean
	 * @param rpath
	 * @return
	 */
	@Override
	public List<FileListBean> lsRemotePath(FTPBean bean, String rpath) {
		List<FileListBean> filelist = new ArrayList<FileListBean>();
		int port = bean.getSoc_port();
		if(port == 0){
			port = AgentCommonTool.getPort();
		}
		if(rpath.equals("�ҵĵ���/")){
			//��ȡ�ҵĵ��ԣ��������Ŀ¼
			AgentRSession session = new AgentRSession(bean.getSoc_ip(), port, IMPL_TYPE.SHELL, 1,  AgentRSession.SYNCHRO_TYPE, true, "common/");
			ShExecRsBean shExecRsBean = session.sendCmd("wmic logicaldisk where DriveType='3' get DeviceID");
			String dest = "";
			if (shExecRsBean.getRs_msg() != null) {
				Pattern p = Pattern.compile("\t|\r|\n");
				Matcher m = p.matcher(shExecRsBean.getRs_msg());
				dest = m.replaceAll(";");
			}

			String[] array = dest.split(";;");
			if (!Assert.isEmpty(array) && array.length > 1) {
				for (int i = 1; i < array.length; i++) {
					boolean is_dir = true;
					String file_type = "Ŀ¼";
					FileListBean file_bean = new FileListBean(array[i].trim(), file_type, is_dir);
					file_bean.setEdit_flag(true);
					filelist.add(file_bean);
				}
			}
			
		}else if(rpath.startsWith("�ҵĵ���/")){
			//����ǰ�˻ᴫ��·���ǡ��ҵĵ���/D:/xxx��������������Ҫȥ�����ҵĵ���/��
			filelist = getRemoteFile(rpath.substring(rpath.indexOf("/") + 1), bean.getSoc_ip(), port, null, true);
		}else{
			filelist = getRemoteFile(rpath, bean.getSoc_ip(), port, null, false);
		}
		return filelist;
	}

	/**
	 * Description:���Ŀ¼�Ƿ����
	 */
	public void checkRemoteDirExist(FTPBean bean, String rfile){
		int port = bean.getSoc_port();
		if(port == 0){
			port = AgentCommonTool.getPort();
		}
		
		String remote_env = AgentCommonTool.checkRemoteEnv(bean.getSoc_ip());
		AgentRSession session = new AgentRSession(bean.getSoc_ip(), port, IMPL_TYPE.SHELL, 1, AgentRSession.SYNCHRO_TYPE, true, "common/");
		if(remote_env.equals("windows")){
			String[] cmds = new String[2];
			cmds[0] = "set path=" + rfile;
			cmds[1] = "if exist \"%path%\" (echo true ) else (echo false)";
			ShExecRsBean result = session.sendCmds(cmds);
			if (result.getRs_msg().trim().equals("false")) {
				throw new AppException("Զ��Ŀ¼:" + result + " ������");
			}
		}else{
			String[] cmds = new String[6];
			cmds[0] = "path=" + rfile;
			cmds[1] = "if [ ! -d \"$path\" ];then";
			cmds[2] = "echo false";
			cmds[3] = "else";
			cmds[4] = "echo true";
			cmds[5] = "fi";
			ShExecRsBean result = session.sendCmds(cmds);
			if (result.getRs_msg().trim().equals("false")) {
				throw new AppException("Զ��Ŀ¼:" + result + " ������");
			}
		}
	}
	
	/**
	 * ���Զ��Ŀ¼�Ƿ���ڣ��������򴴽�
	 * Description: 
	 * @param ip
	 * @param port
	 * @param path
	 */
	public static void checkAndCreateRemotePath(FTPBean bean, String rfile) {
		int port = bean.getSoc_port();
		if(port == 0){
			port = AgentCommonTool.getPort();
		}
		
		AgentRSession session = new AgentRSession(bean.getSoc_ip(), port, IMPL_TYPE.SHELL, 1, AgentRSession.SYNCHRO_TYPE, true, "common/");
		String remote_env = AgentCommonTool.checkRemoteEnv(bean.getSoc_ip());
		if(remote_env.equals("windows")){
			String[] cmds = new String[2];
			cmds[0] = "set path=" + rfile;
			cmds[1] = "if exist \"%path%\" (echo exist) else (md %path%)";
			ShExecRsBean result = session.sendCmds(cmds);
			if (!result.getIs_succ()) {
				throw new AppException(result.getErr_msg());
			}
		}else{
			String[] cmds = new String[4];
			cmds[0] = "path=" + rfile;
			cmds[1] = "if [ ! -d \"$path\" ];then";
			cmds[2] = "mkdir $path";
			cmds[3] = "fi";
			ShExecRsBean result = session.sendCmds(cmds);
			if (!result.getIs_succ()) {
				throw new AppException(result.getErr_msg());
			}
		}
	}
	
	/**
	 * Description:���Զ���ļ��Ƿ����
	 */
	public void checkRemoteFileExist(FTPBean bean, String rfile){
		int port = bean.getSoc_port();
		if(port == 0){
			port = AgentCommonTool.getPort();
		}
		
		String remote_env = AgentCommonTool.checkRemoteEnv(bean.getSoc_ip());
		AgentRSession session = new AgentRSession(bean.getSoc_ip(), port, IMPL_TYPE.SHELL, 1, AgentRSession.SYNCHRO_TYPE, true, "common/");
		if(remote_env.equals("windows")){
			String[] cmds = new String[2];
			cmds[0] = "set path=" + rfile;
			cmds[1] = "if exist \"%path%\" (echo true ) else (echo false)";
			ShExecRsBean result = session.sendCmds(cmds);
			if (result.getRs_msg().trim().equals("false")) {
				throw new FileNotExistException().addScene("FILE", "Զ���ļ���" + rfile);
			}
		}else{
			String[] cmds = new String[6];
			cmds[0] = "path=" + rfile;
			cmds[1] = "if [ ! -f \"$path\" ];then";
			cmds[2] = "echo false";
			cmds[3] = "else";
			cmds[4] = "echo true";
			cmds[5] = "fi";
			ShExecRsBean result = session.sendCmds(cmds);
			if (result.getRs_msg().trim().equals("false")) {
				throw new FileNotExistException().addScene("FILE", "Զ���ļ���" + rfile);
			}
		}
		
	}
	
	/**
	 * Description:�ж�Զ���ļ��Ƿ����
	 */
	public boolean existRemoteFile(FTPBean bean, String rfile){
		int port = bean.getSoc_port();
		if(port == 0){
			port = AgentCommonTool.getPort();
		}
		
		String remote_env = AgentCommonTool.checkRemoteEnv(bean.getSoc_ip());
		AgentRSession session = new AgentRSession(bean.getSoc_ip(), port, IMPL_TYPE.SHELL, 1, AgentRSession.SYNCHRO_TYPE, true, "common/");
		if(remote_env.equals("windows")){
			String[] cmds = new String[2];
			cmds[0] = "set path=" + rfile;
			cmds[1] = "if exist \"%path%\" (echo true ) else (echo false)";
			ShExecRsBean result = session.sendCmds(cmds);
			if (result.getRs_msg().trim().equals("false")) {
				return false;
			}
		}else{
			String[] cmds = new String[6];
			cmds[0] = "path=" + rfile;
			cmds[1] = "if [ ! -f \"$path\" ];then";
			cmds[2] = "echo false";
			cmds[3] = "else";
			cmds[4] = "echo true";
			cmds[5] = "fi";
			ShExecRsBean result = session.sendCmds(cmds);
			if (result.getRs_msg().trim().equals("false")) {
				return false;
			}
		}
		return true;
	}
	/**
	 * Description: Э������ΪAgent��ʱ�������������鿴�ļ�����Ŀ¼�ṹ
	 * @param directory
	 * @param soc_name
	 * @param key
	 * @return
	 */
	private List<FileListBean> getRemoteFile(String directory, String ip, int port, String ftp_encoding, boolean isWindows) {
		List<String> file_string_list = new ArrayList<String>();

		file_string_list = getRemoteFileList(directory, ip, port, isWindows);

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
				file_type = "Ŀ¼";
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
	 * Description: �Ѷ�ȡ����Ŀ¼�ṹ�ַ������ж�ȡ����תΪList<String>����
	 * @param directory
	 * @param soc_name
	 * @param key
	 * @return
	 */
	private List<String> getRemoteFileList(String directory, String ip, int port, boolean isWindows) {
		String file_string = getRemoteFileStringList(directory, ip, port, isWindows);
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
	 * Description:ִ��ls -al ����ȡ�ļ�������Ϣ������ִ�н��
	 * @param directory ��Ҫ�鿴���ļ���
	 * @param soc_name ����Դ��
	 * @param key ��ϵͳ������Ŀ��������Դ���ϲ�����
	 * @return
	 */
	private String getRemoteFileStringList(String directory,String ip, int port, boolean isWindows) {
			AgentRSession session = new AgentRSession(ip, port, IMPL_TYPE.SHELL, 2, AgentRSession.SYNCHRO_TYPE, true, "common/");
			
			String cmd_cd = "";
			if(isWindows){
				cmd_cd = "cd /d " + directory;
			}else{
				cmd_cd = "cd " + directory;
			}
			
			cmd_cd = getISOFname(cmd_cd, "GBK");
			String cmd = "ls -al";
			logger.debug("��������[{}]����ȡ�ļ���Ŀ¼�ṹ", "ls -al");
			// helper.callShellCmd(cmd_cd);
			session.sendCmd(cmd_cd);
			String str = session.sendCmd(cmd).getRs_msg();
			str = getUtf8(str);
			return str;
	}
	
	/**
	 * Description: �ж��ļ��Ƿ�֧�ֱ༭
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
	 * Description: ��ȡ֧�ֱ༭���ļ����� ����
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
	 * Description: �����ַ������ת��
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
