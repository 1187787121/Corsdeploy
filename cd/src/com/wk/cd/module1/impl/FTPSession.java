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
import com.wk.cd.module1.exc.TpTemplateConnectException;
import com.wk.cd.module1.info.ModuleSourceInfo;
import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.DESUtil;
import com.wk.cd.common.util.FileTool;
import com.wk.cd.common.util.ScriptUtil;
import com.wk.cd.enu.IMPL_TYPE;
import com.wk.cd.enu.PROTOCOL_TYPE;
import com.wk.cd.module1.impl.ModuleSessionBase;
import com.wk.cd.remote.agent.service.AgentRSession;
import com.wk.cd.remote.exc.ChangeDirNotExistException;
import com.wk.cd.remote.exc.FileNotExistException;
import com.wk.cd.remote.fp.bean.FTPBean;
import com.wk.cd.remote.fp.service.FTPRCallService;
import com.wk.cd.remote.fp.service.PLTFTP;
import com.wk.cd.remote.fp.service.SFTPJSCHRCallService;
import com.wk.cd.remote.sh.bean.ShExecRsBean;
import com.wk.cd.system.dt.info.DtSourceInfo;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * FTP Module��ʹ�õĻỰ���� Created by ��־�� on 2016/11/18.
 */
public class FTPSession
		extends ModuleSessionBase {

	private static final Log logger = LogFactory.getLog();

	@Inject
	protected FTPRCallService ftp_svc;

	@Inject
	protected SFTPJSCHRCallService sftp_svc;

	// ���ڴ洢�ű�����Ҫ���ݵ������ģ���ftp�ı���Ŀ¼
	protected final Map<String, String> ctx = new HashMap<String, String>();

	protected PLTFTP ftp;
	protected FTPBean ftp_bean;
	protected Session sftp_sess;
	protected ChannelSftp sftp;
	protected AgentRSession agent_session;

	public FTPSession(ModuleSourceInfo module_source_info, int step_count) {
		super(step_count);
		this.module_source_info = module_source_info;
		Controller.getInstance().getInjector().inject(this);
	}

	@Override
	protected void implConnect() {
		DtSourceInfo dt_info = module_source_info.getDt_source_info();
		Assert.assertNotEmpty(dt_info, ModuleSourceInfo.DT_SOURCE_INFOCN);
		try {
			PROTOCOL_TYPE protocol_type = dt_info.getProtocol_type();
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
			logger.error("��������Դ[{}]�쳣", dt_info.getSoc_name(), t);
			throw t;
		}
	}

	@Override
	protected void implDisconnect() {
		if (ftp != null) {
			ftp.disconnect();
		} else if (sftp != null) {
			sftp_svc.closeChannel(sftp, sftp_sess);
		} else {
			if (agent_session != null) {
				agent_session.disconnect();
			}

		}
	}

	public ShExecRsBean sendCmd(String cmd) {
		ShExecRsBean result = new ShExecRsBean();
		try {
			// String msg = ftp != null ? callFtpCmd(cmd) : callSFtpCmd(cmd);
			String msg = "";
			if (ftp != null) {
				msg = callFtpCmd(cmd);
			} else if (sftp != null) {
				msg = callSFtpCmd(cmd);
			} else {
				logger.debug("ftp agnet send cmd [{}]", cmd);
				ShExecRsBean rs_bean = agent_session.sendCmd(cmd);
				msg = rs_bean.getRs_msg();
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

	private FTPBean getFTPBeanFromSoc(DtSourceInfo dt_info) {
		PROTOCOL_TYPE pro_type = dt_info.getProtocol_type();
		String source_name = dt_info.getSoc_name();
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
	 * Description: ִ��FTP�ϴ�����������
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
		// ��һ���Ự�м�¼����
		File local_dir = new File(getCtxValue("#local_dir", "."));
		if (cmd.equalsIgnoreCase("cd")) {
			// ftp.
			boolean chang_flag = client.changeWorkingDirectory(cmd_args[1]);
			if (!chang_flag) {
				throw new RuntimeException("Զ��Ŀ¼��" + cmd_args[1] + "��δ�ҵ�");
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
			// ��ȡ��ǰ����Ŀ¼
			String curdir = client.printWorkingDirectory();
			logger.info("current remote path=[{}]", curdir);
			// �л�Զ��Ŀ¼
			String rfpath = FileTool.getFilePath(rfile);
			logger.info("change remote path to [{}]", rfpath);
			checkchdir(client.changeWorkingDirectory(rfpath), rfpath);
			// ��ȡԶ���ļ���
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
				logger.debug("��ȡ�ļ�[{}]ʧ��", file_name);
				local_file.delete();
			}
			// �л���ԭ����Զ��Ŀ¼
			logger.info("change remote path to [{}]", curdir);
			checkchdir(client.changeWorkingDirectory(curdir), curdir);
		} else if (cmd.equalsIgnoreCase("put")) {
			final String local_path = cmd_args[1].trim();
			// ���ֻ��һ������·����Զ��·��Ϊ����·�����ļ���
			final String remote_path = cmd_args.length > 2 ? cmd_args[2].trim() : FileTool.getFileName(local_path);

			// ��ȡ��ǰ�����ļ�ȫ·��
			final String local_full_path = getFullPath(local_dir.getAbsolutePath(), local_path);
			// ��ȡ��ǰԶ�̹���Ŀ¼
			final String remote_dir = client.printWorkingDirectory();
			logger.info("current remote path=[{}]", remote_dir);

			// ��ȡ��ǰԶ���ļ�ȫ·��
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
		} else if (cmd.equalsIgnoreCase("mkdir")) {// ����Զ��Ŀ¼
			boolean dir_flag = ftp.mkdir(cmd_args[1]);
			if (!dir_flag) {
				throw new RuntimeException("����Զ��Ŀ¼��" + cmd_args[1] + "��ʧ��");
			}
		} else {
			throw new UnsupportedOperationException("[" + cmd + "]���֧��");
		}

		return client.getReplyString();
	}

	/**
	 * Description: ִ��FTP�ϴ�����������
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
		// ��һ���Ự�м�¼����
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
			// ��ȡ��ǰ����Ŀ¼
			String curdir = sftp.pwd();
			logger.info("current remote path=[{}]", curdir);
			// �л�Զ��Ŀ¼
			String rfpath = FileTool.getFilePath(rfile);
			logger.info("change remote path to [{}]", rfpath);
			sftp.cd(rfpath);
			// ��ȡԶ���ļ���
			String file_name = FileTool.getFileName(rfile);
			File local_file = new File(local_dir, file_name);
			logger.info("retrieve to local [{}]", local_file);
			OutputStream os = new FileOutputStream(local_file);
			try {
				sftp.get(file_name, os);
			} catch (Exception e) {
				logger.debug("��ȡ�ļ�[{}]ʧ��", file_name);
				local_file.delete();
			} finally {
				os.close();
			}
		} else if (cmd.equalsIgnoreCase("put")) {
			final String local_path = cmd_args[1].trim();
			// ���ֻ��һ������·����Զ��·��Ϊ����·�����ļ���
			final String remote_path = cmd_args.length > 2 ? cmd_args[2].trim() : FileTool.getFileName(local_path);

			// ��ȡ��ǰ�����ļ�ȫ·��
			final String local_full_path = getFullPath(local_dir.getAbsolutePath(), local_path);
			// ��ȡ��ǰԶ�̹���Ŀ¼
			final String remote_dir = getCtxValue("#remote_dir", sftp.pwd());
			logger.info("current remote path=[{}]", remote_dir);

			// ��ȡ��ǰԶ���ļ�ȫ·��
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
		} else if (cmd.equalsIgnoreCase("mkdir")) {// ����Զ��Ŀ¼
			boolean dir_flag = sftp_svc.makeDirectory(ftp_bean, cmd_args[1]);
			if (!dir_flag) {
				throw new RuntimeException("����Զ��Ŀ¼��" + cmd_args[1] + "��ʧ��");
			}
		} else {
			throw new UnsupportedOperationException("[" + cmd + "]���֧��");
		}

		return "";
	}

	private static String getFullPath(String dir, String file_path) {
		if (file_path.startsWith("/")) { // ����·��
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
			throw new RuntimeException("�����ļ�[" + file_name + "]������");
		}
	}
}