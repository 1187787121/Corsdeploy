package com.wk.cd.module1.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.net.ftp.FTPClient;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.wk.Controller;
import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.DESUtil;
import com.wk.cd.common.util.FileTool;
import com.wk.cd.common.util.ScriptUtil;
import com.wk.cd.common.util.StringUtil;
import com.wk.cd.enu.IMPL_TYPE;
import com.wk.cd.enu.PROTOCOL_TYPE;
import com.wk.cd.module1.exc.TpTemplateConnectException;
import com.wk.cd.module1.info.ModuleSourceInfo;
import com.wk.cd.module1.service.CmdsExecuteUtil;
import com.wk.cd.remote.agent.service.AgentRSession;
import com.wk.cd.remote.bean.RBean;
import com.wk.cd.remote.exc.ChangeDirNotExistException;
import com.wk.cd.remote.exc.FileNotExistException;
import com.wk.cd.remote.fp.bean.FTPBean;
import com.wk.cd.remote.fp.service.FTPRCallService;
import com.wk.cd.remote.fp.service.PLTFTP;
import com.wk.cd.remote.fp.service.SFTPJSCHRCallService;
import com.wk.cd.remote.sh.bean.ShExecRsBean;
import com.wk.cd.remote.sh.service.AbstractRCallService;
import com.wk.cd.remote.sh.service.JSchRCallService;
import com.wk.cd.remote.sh.service.RConnection;
import com.wk.cd.remote.sh.service.RSession;
import com.wk.cd.remote.sh.service.TelnetRCallService;
import com.wk.cd.system.dt.info.DtSourceInfo;
import com.wk.cd.system.dt.service.DtSocService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * 配置文件上传、校验、备份
 * Class Description: 
 * @author 12049
 */
public class ConfigSession extends ModuleSessionBase{

	private static final Log logger = LogFactory.getLog();
	
	@Inject
	protected FTPRCallService ftp_svc;
	
	@Inject
	protected SFTPJSCHRCallService sftp_svc;
	
	// 用于存储脚本中需要传递的上下文，如ftp的本地目录
	protected final Map<String, String> ctx = new HashMap<String, String>();
		
	protected IMPL_TYPE impl_type;
	
	protected PLTFTP ftp;
	protected FTPBean ftp_bean;
	protected Session sftp_sess;
	protected ChannelSftp sftp;
	protected AgentRSession agent_session;
	
	@Inject
	private JSchRCallService ssh_svc;

	@Inject
	private TelnetRCallService telnet_svc;
	
	@Inject
	private DtSocService dtSocService;
	
	protected static final int DEFAULT_TIMEOUT = 3600;
	private String remote_relative_dir;
	
	private RConnection conn;
	private RSession sess;
	
	public ConfigSession(int step_count, ModuleSourceInfo module_source_info, IMPL_TYPE impl_type, String remote_relative_dir) {
		super(step_count);
		this.module_source_info = module_source_info;
		this.impl_type = impl_type;
		this.remote_relative_dir = remote_relative_dir;
		Controller.getInstance().getInjector().inject(this);
	}

	@Override
	protected void implConnect() {
		DtSourceInfo dt_info = module_source_info.getDt_source_info();
		Assert.assertNotEmpty(dt_info, ModuleSourceInfo.DT_SOURCE_INFOCN);
		
		PROTOCOL_TYPE protocol_type = dt_info.getProtocol_type();
		/**
		 * FTP实现类型,上传配置文件包
		 */
		if(impl_type == IMPL_TYPE.FTP){
			try {
				FTPBean bean = null;
				if (PROTOCOL_TYPE.AGENT != protocol_type) {
					bean = getFTPBeanFromSoc(dt_info);
					this.ftp_bean = bean;
				}

				if (PROTOCOL_TYPE.PLT_FTP == protocol_type) {
					ftp = ftp_svc.getPFTPClient(bean);
					if (!ftp.connect()) {
						throw new TpTemplateConnectException().addScene("SOURCE", dt_info.getSoc_name());
					}
					logger.info("{}'s session {} connected", dt_info.getSoc_name(), ftp);
				} else if (PROTOCOL_TYPE.SFTP == protocol_type) {
					sftp_sess = sftp_svc.getSession(bean);
					sftp = sftp_svc.getChannelSftp(bean.getFtp_encoding(), sftp_sess);
					logger.info("{}'s session {} connected", dt_info.getSoc_name(), sftp);
				} else if (PROTOCOL_TYPE.AGENT == protocol_type) {
					agent_session = new AgentRSession(dt_info.getSoc_ip(), dt_info.getSoc_port(), IMPL_TYPE.FTP,
							step_count, AgentRSession.SYNCHRO_TYPE, false, null);
				} else {
					throw new RuntimeException(protocol_type + " not ftp source");
				}
			} catch (RuntimeException t) {
				logger.error("连接数据源[{}]异常", dt_info.getSoc_name(), t);
				throw t;
			}
		}else if(impl_type == IMPL_TYPE.SHELL){
			//Shell实现类型，实现配置文件的上传和备份
			try {
				if (protocol_type == PROTOCOL_TYPE.SSH) {
					RBean bean = getRBeanFromSoc(dt_info);
					conn = ssh_svc.getConnection(bean);
					sess = ssh_svc.openSession(conn);
				} else if (protocol_type == PROTOCOL_TYPE.TELNET) {
					RBean bean = getRBeanFromSoc(dt_info);
					conn = telnet_svc.getConnection(bean);
					sess = telnet_svc.openSession(conn);
				} else if (protocol_type == PROTOCOL_TYPE.AGENT) {
					sess = new AgentRSession(dt_info.getSoc_ip(), dt_info.getSoc_port(),
							IMPL_TYPE.SHELL, step_count, AgentRSession.SYNCHRO_TYPE, true, remote_relative_dir);
				} else {
					throw new RuntimeException(protocol_type + " not supported");
				}
				String vars = dt_info.getEnvironment_variables();
				
				if (!com.wk.util.StringUtil.isEmpty(vars)) {
					executeEnvironmentVariables(sess, dt_info, dt_info.getProtocol_type());
				}
				logger.info("{}'s session {} connected", dt_info.getSoc_name(), sess);
			} catch (RuntimeException t) {
				t.printStackTrace();
				logger.error("连接数据源[{}]异常", dt_info.getSoc_name(), t);
				throw t;
			}
		}
	}

	@Override
	protected void implDisconnect() {
		//关闭FTP的相关连接
		if (ftp != null) {
			ftp.disconnect();
		} else if (sftp != null) {
			sftp_svc.closeChannel(sftp, sftp_sess);
		} else {
			if (agent_session != null) {
				agent_session.disconnect();
			}

		}
		
		//关闭Shell的相关连接
		if (sess != null) {
			sess.disconnect();
		}
		if (conn != null) {
			conn.disconnect();
		}
	}
	
	public ShExecRsBean sendCmd(String cmd) {
		ShExecRsBean result = new ShExecRsBean();
		try {
			// String msg = ftp != null ? callFtpCmd(cmd) : callSFtpCmd(cmd);
			String msg = "";
			if(impl_type == IMPL_TYPE.FTP){
				if (ftp != null) {
					msg = callFtpCmd(cmd);
				} else if (sftp != null) {
					msg = callSFtpCmd(cmd);
				} else {
					logger.debug("ftp agnet send cmd [{}]", cmd);
					ShExecRsBean rs_bean = agent_session.sendCmd(cmd);
					msg = rs_bean.getRs_msg();
				}
			}
			
			result.setIs_succ(true);
			result.setRs_msg(msg);
		} catch (Exception e) {
			logger.error("call ftp error", e);
			result.setIs_succ(false);
			result.setErr_msg(e.getMessage());
		}
		return result;
	}
	
	public ShExecRsBean sendCmds(String[] cmds, String remote_relative_dir) {
		DtSourceInfo dt_info = module_source_info.getDt_source_info();
		PROTOCOL_TYPE protocol_type = dt_info.getProtocol_type();
		if (protocol_type == PROTOCOL_TYPE.AGENT) {
			if (sess instanceof AgentRSession) {
				AgentRSession a = (AgentRSession) sess;
				return a.sendCmds(cmds);
			}
		} else {
			DtSourceInfo ftp_info = dtSocService.changeDtSource(dt_info);
			CmdsExecuteUtil execute = new CmdsExecuteUtil(IMPL_TYPE.SHELL, ftp_info, remote_relative_dir);
			String[] remote_file = execute.uploadFile(cmds);
			StringBuilder sb = new StringBuilder();
			sb.append("chmod 777 ").append(remote_file[0]).append(';');
			sb.append("cd ").append(dt_info.getUser_root_path()).append(';');
			if("true".equalsIgnoreCase(CmdsExecuteUtil.EXECUTE_STYLE)){
				sb.append(remote_file[0]).append(" 2>>").append(remote_file[1]).append(';');
			}else{
				sb.append(remote_file[0]).append(';');
			}
			logger.info("before cmd ts_soc_name = {}", dt_info.getSoc_name());
//			sess.sendCmd("chmod 777 " + remote_file[0]);
//			sess.sendCmd("cd "+dt_info.getUser_root_path());
//			String cmd = remote_file[0] + " 2>>" + remote_file[1];
			ShExecRsBean result = sess.sendCmd(sb.toString());
			logger.debug("send cmd is success[{}]",result.getIs_succ());
			logger.debug("send cmd msg [{}]",result.getRs_msg());
			logger.debug("send cmd error_msg [{}]",result.getErr_msg());
			String msg = result.getRs_msg();
			if(!Assert.isEmpty(msg) && msg.endsWith(AbstractRCallService.CLOSE_MSG)){
				execute.deleteFile(remote_file[0]);
				execute.deleteFile(remote_file[1]);
				return result;
			}
			if("true".equalsIgnoreCase(CmdsExecuteUtil.EXECUTE_STYLE)){
				ShExecRsBean error_result = sess.sendCmd("cat "+remote_file[1]);
				String error_msg = error_result.getRs_msg();
				if(!Assert.isEmpty(error_msg)){
					logger.debug("error msg[{}]",error_msg);
					result.setErr_msg(result.getErr_msg()+error_msg);
					result.setIs_succ(false);
				}
				execute.deleteFile(remote_file[1]);
			}
			
			execute.deleteFile(remote_file[0]);
			
			return result;
		}
		return null;
	}
	
	/**
	 * 将DtSource转化为需要的FTPBean
	 * Description: 
	 * @param dt_info
	 * @return
	 */
	private FTPBean getFTPBeanFromSoc(DtSourceInfo dt_info) {
		PROTOCOL_TYPE pro_type = dt_info.getProtocol_type();
		if (pro_type == PROTOCOL_TYPE.PLT_FTP || pro_type == PROTOCOL_TYPE.SFTP) {
			FTPBean bean = new FTPBean();
			bean.setProtocol_type(PROTOCOL_TYPE.PLT_FTP);
			bean.setRemote_uname(dt_info.getRemote_uname());
			String passed_key = DESUtil.docryptAllowReverse(false, null, dt_info.getKey_remote_passwd().trim());
			String remote_passwd = DESUtil.docryptAllowReverse(false, passed_key, dt_info.getRemote_passwd());
			logger.debug("user=[{}]", dt_info.getRemote_uname());
			bean.setRemote_passwd(remote_passwd.trim());
			bean.setSoc_ip(dt_info.getSoc_ip());
			bean.setSoc_port(dt_info.getSoc_port());
			bean.setSoc_name(dt_info.getSoc_name());
			bean.setIs_monitor(false);
			String encoding_type = dt_info.getEncoding_type();
			bean.setFtp_encoding(Assert.isEmpty(encoding_type) ? "GBK" : encoding_type);
			bean.setIs_monitor(false);
			return bean;
		}
		throw new RuntimeException(pro_type + " not FTP protocol");
	}
	
	/**
	 * 将DtSource转换成Shell所需的bean
	 * Description: 
	 * @param dt_info
	 * @return
	 */
	private RBean getRBeanFromSoc(DtSourceInfo dt_info) {
		RBean bean = new RBean();
		bean.setProtocol_type(dt_info.getProtocol_type());
		bean.setRemote_uname(dt_info.getRemote_uname().trim());
		String passed_key = DESUtil.docryptAllowReverse(false, null,
				dt_info.getKey_remote_passwd().trim());
		String remote_passwd = DESUtil.docryptAllowReverse(false, passed_key,
				dt_info.getRemote_passwd());
		bean.setRemote_passwd(remote_passwd.trim());
		bean.setSoc_ip(dt_info.getSoc_ip().trim());
		bean.setSoc_port(dt_info.getSoc_port());
		bean.setSoc_name(dt_info.getSoc_name().trim());
		int timeout = (int) dt_info.getBk_timeout();
		if (timeout != 0) {
			bean.setTimeout(timeout);
		} else {
			bean.setTimeout(DEFAULT_TIMEOUT);
		}
		return bean;
	}
	
	/**
	 * Description: 执行FTP上传或下载命令
	 * @param cmd_text
	 * @return
	 * @throws IOException
	 */
	private String callFtpCmd(String cmd_text)
		throws IOException {
		String[] cmd_args = cmd_text.split(" ");
		String cmd = cmd_args[0];

		FTPClient client = ftp.getFTPClient();
		// ftp.isExistDir(dir)
		boolean flag = false;
		// 在一个会话中记录变量
		File local_dir = new File(getCtxValue("#local_dir", "."));
		if (cmd.equalsIgnoreCase("cd")) {
			// ftp
			boolean chang_flag = client.changeWorkingDirectory(cmd_args[1]);
			if (!chang_flag) {
				throw new RuntimeException("远程目录【" + cmd_args[1] + "】未找到");
			}
			ctx.put("#remote_dir", cmd_args[1]);
		} else if (cmd.equalsIgnoreCase("lcd")) {
			local_dir = new File(cmd_args[1]);
			if (!local_dir.exists()) {
				throw new FileNotExistException().addScene("FILE", local_dir);
			}
			ctx.put("#local_dir", local_dir.getAbsolutePath());
		} else if (cmd.equalsIgnoreCase("get")) {
			String rfile = cmd_args[1];
			// 获取当前工作目录
			String curdir = client.printWorkingDirectory();
			logger.info("current remote path=[{}]", curdir);
			// 切换远程目录
			String rfpath = FileTool.getFilePath(rfile);
			logger.info("change remote path to [{}]", rfpath);
			checkchdir(client.changeWorkingDirectory(rfpath), rfpath);
			// 获取远程文件名
			String file_name = FileTool.getFileName(rfile);
			File local_file = new File(local_dir, file_name);
			logger.info("retrieve to local [{}]", local_file);
			OutputStream os = new FileOutputStream(local_file);
			try {
				flag = client.retrieveFile(file_name, os);
			} finally {
				os.close();
			}
			if (!flag) {
				logger.debug("获取文件[{}]失败", file_name);
				local_file.delete();
			}
			// 切换回原来的远程目录
			logger.info("change remote path to [{}]", curdir);
			checkchdir(client.changeWorkingDirectory(curdir), curdir);
		} else if (cmd.equalsIgnoreCase("put")) {
			final String local_path = cmd_args[1].trim();
			// 如果只有一个本地路径则远程路径为本地路径的文件名
			final String remote_path = cmd_args.length > 2 ? cmd_args[2].trim() : FileTool.getFileName(local_path);

			// 获取当前本地文件全路径
			final String local_full_path = getFullPath(local_dir.getAbsolutePath(), local_path);
			// 获取当前远程工作目录
			final String remote_dir = client.printWorkingDirectory();
			logger.info("current remote path=[{}]", remote_dir);

			// 获取当前远程文件全路径
			final String remote_full_path = getFullPath(remote_dir, remote_path);

			logger.info("upload from local [{}], local_dir [{}], to remote [{}], remote_dir [{}]", local_full_path,
					local_dir, remote_full_path, remote_dir);
			checkLocalFileExist(local_full_path);
			ftp_svc.uploadFile(ftp_bean, remote_full_path, local_full_path);
		} else if (cmd.equalsIgnoreCase("bin") || cmd.equalsIgnoreCase("bi")) {
//			client.setFileTransferMode(org.apache.commons.net.ftp.FTP.BINARY_FILE_TYPE);
			client.setFileType(org.apache.commons.net.ftp.FTP.BINARY_FILE_TYPE);
		} else if (cmd.startsWith("!")) {
			cmd = cmd_text.substring(1);
			return ScriptUtil.execScript(new String[] { cmd }, true);
		} else if (cmd.equalsIgnoreCase("mkdir")) {// 创建远程目录
			boolean dir_flag = ftp.mkdir(cmd_args[1]);
			if (!dir_flag) {
				throw new RuntimeException("创建远程目录【" + cmd_args[1] + "】失败");
			}
		} else {
			throw new UnsupportedOperationException("[" + cmd + "]命令不支持");
		}

		return client.getReplyString();
	}

	/**
	 * Description: 执行FTP上传或下载命令
	 * @param cmd_text
	 * @return
	 * @throws IOException
	 * @throws SftpException
	 */
	private String callSFtpCmd(String cmd_text)
		throws IOException, SftpException {
		String[] cmd_args = cmd_text.split(" ");
		String cmd = cmd_args[0];
		logger.info("call sftp cmd =[{}]", cmd_text);
		// 在一个会话中记录变量
		File local_dir = new File(getCtxValue("#local_dir", "."));
		if (cmd.equalsIgnoreCase("cd")) {
			sftp.cd(cmd_args[1]);
			ctx.put("#remote_dir", cmd_args[1]);
		} else if (cmd.equalsIgnoreCase("lcd")) {
			local_dir = new File(cmd_args[1]);
			if (!local_dir.exists()) {
				throw new FileNotExistException().addScene("FILE", local_dir);
			}
			ctx.put("#local_dir", local_dir.getAbsolutePath());
		} else if (cmd.equalsIgnoreCase("get")) {
			String rfile = cmd_args[1];
			// 获取当前工作目录
			String curdir = sftp.pwd();
			logger.info("current remote path=[{}]", curdir);
			// 切换远程目录
			String rfpath = FileTool.getFilePath(rfile);
			logger.info("change remote path to [{}]", rfpath);
			sftp.cd(rfpath);
			// 获取远程文件名
			String file_name = FileTool.getFileName(rfile);
			File local_file = new File(local_dir, file_name);
			logger.info("retrieve to local [{}]", local_file);
			OutputStream os = new FileOutputStream(local_file);
			try {
				sftp.get(file_name, os);
			} catch (Exception e) {
				logger.debug("获取文件[{}]失败", file_name);
				local_file.delete();
			} finally {
				os.close();
			}
		} else if (cmd.equalsIgnoreCase("put")) {
			final String local_path = cmd_args[1].trim();
			// 如果只有一个本地路径则远程路径为本地路径的文件名
			final String remote_path = cmd_args.length > 2 ? cmd_args[2].trim() : FileTool.getFileName(local_path);

			// 获取当前本地文件全路径
			final String local_full_path = getFullPath(local_dir.getAbsolutePath(), local_path);
			// 获取当前远程工作目录
			final String remote_dir = getCtxValue("#remote_dir", sftp.pwd());
			logger.info("current remote path=[{}]", remote_dir);

			// 获取当前远程文件全路径
			final String remote_full_path = getFullPath(remote_dir, remote_path);

			logger.info("upload from local [{}], local_dir [{}], to remote [{}], remote_dir [{}]", local_full_path,
					local_dir, remote_full_path, remote_dir);
			checkLocalFileExist(local_full_path);
			sftp_svc.uploadFile(ftp_bean, remote_full_path, local_full_path);
		} else if (cmd.equalsIgnoreCase("bin") || cmd.equalsIgnoreCase("bi")) {
			// client.setFileTransferMode(FTP.BINARY_FILE_TYPE);
		} else if (cmd.startsWith("!")) {
			cmd = cmd_text.substring(1);
			return ScriptUtil.execScript(new String[] { cmd }, true);
		} else if (cmd.equalsIgnoreCase("mkdir")) {// 创建远程目录
			boolean dir_flag = sftp_svc.makeDirectory(ftp_bean, cmd_args[1]);
			if (!dir_flag) {
				throw new RuntimeException("创建远程目录【" + cmd_args[1] + "】失败");
			}
		} else {
			throw new UnsupportedOperationException("[" + cmd + "]命令不支持");
		}

		return "";
	}
	
	private static String getFullPath(String dir, String file_path) {
		if (file_path.startsWith("/")) { // 绝对路径
			return file_path;
		}

		if (dir.charAt(dir.length() - 1) != '/') {
			dir += '/';
		}
		return dir + file_path;
	}
	
	private void checkchdir(boolean r, String dir) {
		if (!r) {
			throw new ChangeDirNotExistException().addScene("DIR", dir);
		}
	}

	private String getCtxValue(String name, String default_value) {
		String val = ctx.get(name);
		if (val == null) {
			ctx.put(name, default_value);
			val = default_value;
		}
		return val;
	}

	private void checkLocalFileExist(String file_name) {
		File file = new File(file_name);
		if (!file.exists()) {
			throw new RuntimeException("本地文件[" + file_name + "]不存在");
		}
	}
	
	/**
	 * Description: 执行环境参数
	 * @param sess
	 * @param dt
	 * @param pro
	 * @throws IOException
	 */
	private void executeEnvironmentVariables(RSession sess, DtSourceInfo dt, PROTOCOL_TYPE pro) {
		if (pro.equals(PROTOCOL_TYPE.SSH) || pro.equals(PROTOCOL_TYPE.TELNET)) {
			logger.debug("开始执行环境参数");
			String vars = dt.getEnvironment_variables();
			if (!Assert.isEmpty(vars)) {
				String[] var_list = varsSplit(vars);
				for (String var : var_list) {
					logger.debug("当前执行的环境参数[{}]", var);
					sess.sendCmd(var);
				}
			}
		}
	}
	
	/**
	 * Description: 把传入的参数 按照“；”分隔 再按行读取 返回list
	 * @param vars
	 * @return
	 * @throws IOException
	 */
	private String[] varsSplit(String vars) {
		String vars1 = StringUtil.rpEnter(vars).trim();
		String[] var_array = vars1.split(";");
		return var_array;
	}
}
