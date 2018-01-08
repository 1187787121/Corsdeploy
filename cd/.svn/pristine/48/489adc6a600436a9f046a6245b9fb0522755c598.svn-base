package com.wk.cd.common.cm.service;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.SftpException;
import com.wk.cd.common.cm.dao.CmSeqDaoService;
import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.CfgTool;
import com.wk.cd.common.util.DESUtil;
import com.wk.cd.common.util.FileTool;
import com.wk.cd.enu.PROTOCOL_TYPE;
import com.wk.cd.exc.RemoteSocDirIsNotExistException;
import com.wk.cd.exc.RemoteSocTypeNotFtpOrSftpException;
import com.wk.cd.remote.exc.FileNotExistException;
import com.wk.cd.remote.fp.bean.FTPBean;
import com.wk.cd.remote.fp.service.FTPRCallService;
import com.wk.cd.remote.fp.service.SFTPJSCHRCallService;
import com.wk.cd.system.dt.info.DtSourceInfo;
import com.wk.cd.system.dt.service.DtCheckSocExistService;
import com.wk.cd.system.dt.service.DtSocService;
import com.wk.lang.BaseException;
import com.wk.lang.Final;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;
import com.wk.startup.License;
import com.wk.util.GBKProperties;
import com.wk.util.JaDateTime;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonService {

	@Inject
	private CmSeqDaoService cmDaos;

	@Final
	private long lstm = 0L;

	@Final
	private long rtm = 0L;
	private static final SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmSSss");

	private static final Log logger = LogFactory.getLog();

	@Inject
	private FTPRCallService ftpsrv;

	@Inject
	private SFTPJSCHRCallService sftpsrv;

	@Inject
	private DtCheckSocExistService dtCheckSocExistService;

	@Inject
	private DtSocService dtSocService;

	public JaDateTime getCurrentDateTime() {
		return this.cmDaos.getCurrentDateTime();
	}

	public String getDBCurTimestamp() {
		Date dt = new Date(getCurrentDateTime().longValue());
		String work_seq = fmt.format(dt);
		if (work_seq != null) {
			int s = work_seq.length();
			for (int i = s; i < 17; i++) {
				work_seq = work_seq + "0";
			}
		}
		return work_seq;
	}

	public synchronized long getCurTmByTime(long btm) {
		long itm = 0L;
		long tm = 0L;
		tm = System.currentTimeMillis();
		if (this.lstm == 0L) {
			this.lstm = tm;
		}
		itm = tm - this.lstm;
		this.rtm = (btm + itm);
		return this.rtm;
	}

	public com.wk.db.Session getSession() {
		return this.cmDaos.getSession();
	}

	public void checkRemoteFileExist(String soc_name, String work_seq, String rfile, boolean isFile) {
		logger.info("**************checkRemoteFileExist begin***************");
		Assert.assertNotEmpty(soc_name, "数据源名");

		Assert.assertNotEmpty(rfile, "远程文件全路径");
		FTPBean bean = getFTPBeanBySocName(soc_name, work_seq);
		String new_rfile = rfile;
		if ((rfile.endsWith("/")) && (!isFile) && (!"/".equals(rfile))) {
			new_rfile = rfile.substring(0, rfile.length() - 1);
		}

		if ((PROTOCOL_TYPE.PLT_FTP.equals(bean.getProtocol_type())) && (isFile))
			this.ftpsrv.checkRemoteFileExist(bean, new_rfile);
		else if ((PROTOCOL_TYPE.PLT_FTP.equals(bean.getProtocol_type())) && (!isFile)) {
			this.ftpsrv.checkRemoteDirExist(bean, new_rfile);
		} else if ((PROTOCOL_TYPE.SFTP.equals(bean.getProtocol_type())) && (isFile))
			checkRemoteFileExistBySFTP(bean, new_rfile, true);
		else if ((PROTOCOL_TYPE.SFTP.equals(bean.getProtocol_type())) && (!isFile))
			checkRemoteFileExistBySFTP(bean, new_rfile, false);
		else {
			throw new RemoteSocTypeNotFtpOrSftpException().addScene("SOC", bean.getSoc_name());
		}
		logger.info("***************checkRemoteFileExist end****************");
	}

	/**
	 * Description: 检查远程文件或目录是否存在(SFTP)
	 * 
	 * @param bean
	 * @param rfile
	 */
	public void checkRemoteFileExistBySFTP(FTPBean bean, String rfile, boolean isFile) {
		logger.info("***************checkRemoteFileExistBySFTP end****************");
		String rfpath, rfname;
		com.jcraft.jsch.Session s = null;
		ChannelSftp sftp = null;
		// 远程文件校验
		if (isFile) {
			rfpath = FileTool.getFilePath(rfile);
			rfname = FileTool.getFileName(rfile);
			try {
				s = sftpsrv.getSession(bean);
				logger.info("sftp connect ok");
				sftp = sftpsrv.getChannelSftp(bean.getFtp_encoding(), s);
				logger.info("sftp open channel ok");
				sftp.cd(rfpath);
				sftp.ls(rfname);
			} catch (SftpException e) {
				throw new FileNotExistException().addScene("FILE", "远程文件：" + rfname);
			} finally {
				closeChannel(sftp, s);
				logger.info("***************checkRemoteFileExistBySFTP end****************");
			}
			// 远程目录校验
		} else {
			try {
				rfpath = rfile;
				s = sftpsrv.getSession(bean);
				logger.info("sftp connect ok");
				sftp = sftpsrv.getChannelSftp("gbk", s);
				logger.info("sftp open channel ok");
				sftp.cd(rfpath);
			} catch (SftpException e) {
				throw new RemoteSocDirIsNotExistException().addScene("SOC", bean.getSoc_name()).addScene("DIR", rfile);
			} finally {
				closeChannel(sftp, s);
				logger.info("***************checkRemoteFileExistBySFTP end****************");
			}
		}
	}

	private void checkSocIsFtpOrSftp(String soc_name) {
		this.dtCheckSocExistService.checkSocExist(soc_name);
		DtSourceInfo dtSourceInfo = this.dtSocService.querySocDetailBySocName(soc_name);
		PROTOCOL_TYPE protocol_type = dtSourceInfo.getProtocol_type();
		if ((Assert.isEmpty(protocol_type)) || ((!protocol_type.equals(PROTOCOL_TYPE.PLT_FTP)) && (!protocol_type.equals(PROTOCOL_TYPE.SFTP))))
			throw new RemoteSocTypeNotFtpOrSftpException().addScene("SOC", soc_name);
	}

	/**
	 * Description: 根据数据源名获取ftp或sftp连接实例
	 * @param soc_name
	 * @return
	 */
	public FTPBean getFTPBeanBySocName(String soc_name, String work_seq) {
		FTPBean bean = new FTPBean();
		DtSourceInfo dtSourceInfo = dtSocService.getInfoByKey(soc_name);
		if(!soc_name.contains("agent")) {
			checkSocIsFtpOrSftp(dtSourceInfo);
			bean.setRemote_uname(dtSourceInfo.getRemote_uname());
			String passed_key = DESUtil.docryptAllowReverse(false, null,
					dtSourceInfo.getKey_remote_passwd());
			String remote_passwd = DESUtil.docryptAllowReverse(false, passed_key,
					dtSourceInfo.getRemote_passwd());
			bean.setRemote_passwd(remote_passwd.trim());
			bean.setSoc_ip(dtSourceInfo.getSoc_ip());
		}
		bean.setProtocol_type(dtSourceInfo.getProtocol_type());
		bean.setSoc_port(dtSourceInfo.getSoc_port());
		bean.setSoc_name(dtSourceInfo.getSoc_name());
		String encoding_type = dtSourceInfo.getEncoding_type();
		bean.setFtp_encoding(Assert.isEmpty(encoding_type) ? "GBK"
				: encoding_type);
		bean.setWork_seq(work_seq);
		return bean;
	}
	
	/**
	 * Description: 根据数据源名获取ftp或sftp连接实例
	 * @param soc_name
	 * @return
	 */
	public FTPBean getFTPBeanBySocName(DtSourceInfo dtSourceInfo, String work_seq) {
		FTPBean bean = new FTPBean();
		String soc_name = dtSourceInfo.getSoc_name();
		if(!soc_name.contains("agent")) {
			checkSocIsFtpOrSftp(dtSourceInfo);
			bean.setRemote_uname(dtSourceInfo.getRemote_uname());
			String passed_key = DESUtil.docryptAllowReverse(false, null,
					dtSourceInfo.getKey_remote_passwd());
			String remote_passwd = DESUtil.docryptAllowReverse(false, passed_key,
					dtSourceInfo.getRemote_passwd());
			bean.setRemote_passwd(remote_passwd.trim());
			bean.setSoc_ip(dtSourceInfo.getSoc_ip());
		}
		bean.setProtocol_type(dtSourceInfo.getProtocol_type());
		bean.setSoc_port(dtSourceInfo.getSoc_port());
		bean.setSoc_name(dtSourceInfo.getSoc_name());
		String encoding_type = dtSourceInfo.getEncoding_type();
		bean.setFtp_encoding(Assert.isEmpty(encoding_type) ? "GBK"
				: encoding_type);
		bean.setWork_seq(work_seq);
		return bean;
	}
	
	/**
	 * Description: 根据传入的数据源名 校验该数据源的连接方式是不是FTP或SFTP的 如果都不是则抛异常
	 * @param soc_name
	 */
	private void checkSocIsFtpOrSftp(DtSourceInfo dtSourceInfo) {
		PROTOCOL_TYPE protocol_type = dtSourceInfo.getProtocol_type();
		if (Assert.isEmpty(protocol_type)
				|| (!protocol_type.equals(PROTOCOL_TYPE.PLT_FTP) && !protocol_type
						.equals(PROTOCOL_TYPE.SFTP)&& !protocol_type
						.equals(PROTOCOL_TYPE.SSH))) {
			throw new RemoteSocTypeNotFtpOrSftpException().addScene("SOC",
					dtSourceInfo.getSoc_name());
		}
	}

	public synchronized boolean checkLicense(GBKProperties prop, String license_key) {
		Assert.assertNotEmpty(prop, "GBKProperties");
		Assert.assertNotEmpty(license_key, "license_key");

		GBKProperties sys_prop = CfgTool.getProperties("system.properties");
		String _license_key = sys_prop.getProperty("license.key");
		String _license_file = sys_prop.getProperty("license.file");
		boolean check_ok = false;
		File f = null;
		FileOutputStream fo = null;
		try {
			if (!Assert.isEmpty(_license_file)) {
				String tf = CfgTool.getProjectRootPath() + "/config/license.properties.tmp";
				f = new File(tf);
				fo = new FileOutputStream(f);
				prop.save(fo, "license check tmp file");
				check_ok = License.checkLicenseFile(f);
			} else if (!Assert.isEmpty(_license_key)) {
				check_ok = License.checkLicenseCode(license_key);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			if (fo != null) {
				try {
					fo.close();
				} catch (IOException e) {
					logger.warn(e.getMessage());
				}
			}
			if ((f != null) && (f.exists())) {
				f.delete();
			}
		}

		return check_ok;
	}

	private void closeChannel(ChannelSftp sftp, com.jcraft.jsch.Session s) {
		if (sftp != null) {
			sftp.disconnect();
		}
		if (s != null)
			s.disconnect();
	}
}