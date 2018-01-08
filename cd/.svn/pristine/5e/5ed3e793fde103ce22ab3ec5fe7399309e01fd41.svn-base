package com.wk.cd.test.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.interfaces.RSAPublicKey;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

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
import com.wk.lang.Final;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;
import com.wk.security.RSAUtil;
import com.wk.startup.License;
import com.wk.util.Base64;
import com.wk.util.GBKProperties;
import com.wk.util.JaDate;
import com.wk.util.JaDateTime;
import com.wk.util.StringUtil;

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

	public FTPBean getFTPBeanBySocName(String soc_name, String work_seq) {
		checkSocIsFtpOrSftp(soc_name);
		FTPBean bean = new FTPBean();
		DtSourceInfo dtSourceInfo = this.dtSocService.getInfoByKey(soc_name);
		PROTOCOL_TYPE type = dtSourceInfo.getProtocol_type();
		bean.setProtocol_type(type);
		bean.setRemote_uname(dtSourceInfo.getRemote_uname());
		String passed_key = DESUtil.docryptAllowReverse(false, null, dtSourceInfo.getKey_remote_passwd());
		String remote_passwd = DESUtil.docryptAllowReverse(false, passed_key, dtSourceInfo.getRemote_passwd());
		bean.setRemote_passwd(remote_passwd.trim());
		bean.setSoc_ip(dtSourceInfo.getSoc_ip());
		bean.setSoc_port(dtSourceInfo.getSoc_port());
		bean.setSoc_name(dtSourceInfo.getSoc_name());
		String encoding_type = dtSourceInfo.getEncoding_type();
		bean.setFtp_encoding(Assert.isEmpty(encoding_type) ? "GBK" : encoding_type);
		bean.setWork_seq(work_seq);
		return bean;
	}
	
	private static final RSAPublicKey public_key = (RSAPublicKey) RSAUtil.readKeyFromFile("/public.key");
	

	public synchronized boolean checkLicense(GBKProperties prop, String license_key) {
		Assert.assertNotEmpty(prop, "GBKProperties");
		Assert.assertNotEmpty(license_key, "license_key");

		GBKProperties sys_prop = CfgTool.getProperties("system.properties");
		String _license_key = sys_prop.getProperty("license.key");
		String _license_file = sys_prop.getProperty("license.file");
		System.out.println("key: "+_license_key +" file:"+_license_file);
		boolean check_ok = false;
		File f = null;
		FileOutputStream fo = null;
		try {
			if (!Assert.isEmpty(_license_file)) {
				String tf = CfgTool.getProjectRootPath() + "/config/license.properties.tmp";
				System.out.println(tf);
				f = new File(tf);
				fo = new FileOutputStream(f);
				prop.save(fo, "license check tmp file");
				
				GBKProperties props = loadProperties(f);

				String license_string = props.getString("license");
				System.out.println("license_string: "+license_string);
				byte[] license_bytes = new byte[license_string.length() / 2];
				System.out.println("uuuu: "+Arrays.toString(license_bytes));
				StringUtil.fromHex(license_string, license_bytes);
				System.out.println("public_key: "+public_key);
				byte[] bytes = RSAUtil.decrypt(public_key, license_bytes);
				byte[] enbytes = RSAUtil.encrypt(public_key, license_bytes);
				
				System.out.println(new String(enbytes));
				System.out.println(new String(bytes));
				String text = generateCodeText(props);
				System.out.println("text: "+text);
				//check_ok = License.checkLicenseFile(f);
				System.out.println(text.equals(new String(bytes)));
				check_ok = text.equals(new String(bytes));
			} else if (!Assert.isEmpty(_license_key)) {
				System.out.println("kkkkkkkkkkkkkkkkkkkkkkk");
				check_ok = License.checkLicenseCode(license_key);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		} finally {
			System.out.println("finall");
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
	
	
	
	
	
	
	static GBKProperties loadProperties(File customer_props_file) {
		if (customer_props_file == null) {
			throw new RuntimeException("CUSTOMER_PROPS_FILE_MUST_NOT_NULL");
		}
		if (!(customer_props_file.exists())) {
			throw new RuntimeException("CUSTOMER_PROPS_FILE_NOT_EXISTS: " + customer_props_file);
		}

		GBKProperties props = new GBKProperties();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(customer_props_file);
			props.load(fis);

			GBKProperties localGBKProperties1 = props;

			return localGBKProperties1;
		} catch (FileNotFoundException fnfe) {
		} catch (IOException ioe) {
		} finally {
			if (fis != null)
				try {
					fis.close();
				} catch (IOException ioe) {
				}
		}
		return props;
	}

	static String generateCodeText(GBKProperties props) {
		if (props == null) {
			throw new RuntimeException("CUSTOMER_PROPS_MUST_NOT_NULL");
		}

		String name = props.getString("name");
		String zh_name = props.getString("zh_name");

		String ip_string = props.getString("ip");
		String ip;
		if (StringUtil.isEmpty(ip_string))
			ip = "00000000";
		else {
			try {
				InetAddress ip_address = InetAddress.getByName(ip_string);
				ip = new String(StringUtil.toHex(ip_address.getAddress()));
			} catch (UnknownHostException uhe) {
				throw new RuntimeException("INVALID_IP_ADDRESS: " + ip_string, uhe);
			}

		}

		JaDate expire_end_date = JaDate.valueOf(props.getString("expire_end_date"));
		if (expire_end_date == null) {
			int expire_period = props.getInt("expire_period", -1);
			if (expire_period > 0) {
				expire_end_date = JaDate.today().addDay(expire_period);
			}
		}
		String expire_date_string = (expire_end_date != null) ? expire_end_date.toSimpleDateString() : "ULIMITED";

		return buildLicenseText(name.toUpperCase(), zh_name, ip, expire_date_string);
	}

	private static String buildLicenseText(String name, String zh_name, String ip, String expire_date_string) {
		StringBuffer sb = new StringBuffer();
		sb.append(name).append("-").append(Base64.encode(zh_name)).append("-").append(ip).append("-").append(expire_date_string).append("-");

		return sb.toString();
	}
	
	private void closeChannel(ChannelSftp sftp, com.jcraft.jsch.Session s) {
		if (sftp != null) {
			sftp.disconnect();
		}
		if (s != null)
			s.disconnect();
	}
}