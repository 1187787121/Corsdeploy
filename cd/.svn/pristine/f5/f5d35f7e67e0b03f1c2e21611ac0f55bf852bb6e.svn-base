package com.wk.cd.remote.fp.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.commons.net.ftp.FTPFile;

import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.CMSGBKConvert;
import com.wk.cd.common.util.FileTool;
import com.wk.cd.common.util.StringUtil;
import com.wk.cd.enu.CVT_TYPE;
import com.wk.cd.exc.DataErrorException;
import com.wk.cd.exc.RemoteSocDirIsNotExistException;
import com.wk.cd.remote.exc.FileNotExistException;
import com.wk.cd.remote.fp.bean.FTPBean;
import com.wk.cd.remote.fp.bean.FileListBean;
import com.wk.cd.remote.fp.bean.MBean;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

public class FTPRCallService {
	private static final Vector<MBean> mon_bean = new Vector<MBean>();
	private static final int BEAN_MAX = 50;
	private static final String FE = "E";
	private static final Log logger = LogFactory.getLog();

	public void downloadFile(FTPBean bean, String rfile, String lfile) {
		Assert.assertNotEmpty(bean, "FTPBean");
		Assert.assertNotEmpty(bean.getProtocol_type(), "protocol_type");
		Assert.assertNotEmpty(rfile, "远程文件");
		Assert.assertNotEmpty(lfile, "本地文件");

		FTPRCallInterface svc = FTPRCallServiceFactory.getFtpService(bean
				.getProtocol_type());
		if (bean.getIs_monitor()) {
			Assert.assertNotEmpty(bean.getWork_seq(), "work_seq");
			MBean mb = new MBean();
			mb.setFile_name(rfile);
			addMonitorBean(mb);
		}
		if (bean.getCvt_type() == CVT_TYPE.PLTCVT) {
			String fe = getFEname(lfile);
			FileTool.mkdir(fe);
			svc.downloadFile(bean, rfile, fe);
			cvtFileE2G(bean, fe, lfile);
		} else {
			svc.downloadFile(bean, rfile, lfile);
		}
	}

	public void uploadFile(FTPBean bean, String rfile, String lfile) {
		Assert.assertNotEmpty(bean, "FTPBean");
		Assert.assertNotEmpty(bean.getProtocol_type(), "protocol_type");
		Assert.assertNotEmpty(rfile, "远程文件");
		Assert.assertNotEmpty(lfile, "本地文件");

		FTPRCallInterface svc = FTPRCallServiceFactory.getFtpService(bean
				.getProtocol_type());
		if (bean.getIs_monitor()) {
			Assert.assertNotEmpty(bean.getWork_seq(), "work_seq");
			MBean mb = new MBean();
			mb.setFile_name(lfile);
			addMonitorBean(mb);
		}
		if (bean.getCvt_type() == CVT_TYPE.PLTCVT) {
			String fe = getFEname(lfile);
			cvtFileG2E(bean, lfile, fe);
			svc.uploadFile(bean, rfile, fe);
		} else {
			svc.uploadFile(bean, rfile, lfile);
		}
	}

	public void downloadDir(FTPBean bean, String rdir, String ldir) {
		Assert.assertNotEmpty(bean, "FTPBean");
		Assert.assertNotEmpty(bean.getProtocol_type(), "protocol_type");
		Assert.assertNotEmpty(rdir, "远程目录");
		Assert.assertNotEmpty(ldir, "本地目录");
		checkDir(ldir);

		FTPRCallInterface svc = FTPRCallServiceFactory.getFtpService(bean
				.getProtocol_type());
		if (bean.getIs_monitor()) {
			Assert.assertNotEmpty(bean.getWork_seq(), "work_seq");
			MBean mb = new MBean();
			addMonitorBean(mb);
		}
		if (bean.getCvt_type() == CVT_TYPE.PLTCVT) {
			String ddir = getFEdir(ldir);
			FileTool.mkdir(ddir);
			svc.downloadDir(bean, rdir, ddir);
			cvtDirE2G(bean, ddir, ldir);
		} else {
			svc.downloadDir(bean, rdir, ldir);
		}
	}

	public void uploadDir(FTPBean bean, String rdir, String ldir) {
		Assert.assertNotEmpty(bean, "FTPBean");
		Assert.assertNotEmpty(bean.getProtocol_type(), "protocol_type");
		Assert.assertNotEmpty(rdir, "远程目录");
		Assert.assertNotEmpty(ldir, "本地目录");
		checkDir(ldir);

		FTPRCallInterface svc = FTPRCallServiceFactory.getFtpService(bean
				.getProtocol_type());
		if (bean.getIs_monitor()) {
			Assert.assertNotEmpty(bean.getWork_seq(), "work_seq");
			MBean mb = new MBean();
			addMonitorBean(mb);
		}
		if (bean.getCvt_type() == CVT_TYPE.PLTCVT) {
			String ddir = getFEdir(ldir);
			cvtDirG2E(bean, ldir, ddir);
			svc.uploadDir(bean, rdir, ddir);
		} else {
			svc.uploadDir(bean, rdir, ldir);
		}
	}

	public void deleteFile(FTPBean bean, String rdir) {
		Assert.assertNotEmpty(bean, "FTPBean");
		Assert.assertNotEmpty(bean.getProtocol_type(), "protocol_type");
		Assert.assertNotEmpty(rdir, "远程目录");

		FTPRCallInterface svc = FTPRCallServiceFactory.getFtpService(bean
				.getProtocol_type());
		if (bean.getIs_monitor()) {
			Assert.assertNotEmpty(bean.getWork_seq(), "work_seq");
			MBean mb = new MBean();
			addMonitorBean(mb);
		}
		svc.deleteFile(bean, rdir);
	}

	public void deleteDir(FTPBean bean, String rdir) {
		Assert.assertNotEmpty(bean, "FTPBean");
		Assert.assertNotEmpty(bean.getProtocol_type(), "protocol_type");
		Assert.assertNotEmpty(rdir, "远程目录");

		FTPRCallInterface svc = FTPRCallServiceFactory.getFtpService(bean
				.getProtocol_type());
		if (bean.getIs_monitor()) {
			Assert.assertNotEmpty(bean.getWork_seq(), "work_seq");
			MBean mb = new MBean();
			addMonitorBean(mb);
		}
		svc.deleteDir(bean, rdir);
	}

	public List<FileListBean> lsRemotePath(FTPBean bean, String rpath) {
		List<FileListBean> files = new ArrayList<FileListBean>();
		Assert.assertNotEmpty(bean, "FTPBean");
		Assert.assertNotEmpty(bean.getProtocol_type(), "protocol_type");
		Assert.assertNotEmpty(rpath, "远程目录");

		FTPRCallInterface svc = FTPRCallServiceFactory.getFtpService(bean
				.getProtocol_type());

		files = svc.lsRemotePath(bean, rpath);

		return files;
	}

	public PLTFTP getPFTPClient(FTPBean bean) {
		return new PLTFTP(bean.getSoc_ip(), bean.getSoc_port(),
				bean.getRemote_uname(), bean.getRemote_passwd(),
				bean.getFtp_encoding(), bean.getTimeout());
	}

	public void checkRemoteFileExist(FTPBean bean, String rfile) {
		PLTFTP ftp = getPFTPClient(bean);
		String rfpath = FileTool.getFilePath(rfile);
		String rfname = FileTool.getFileName(rfile);
		try {
			logger.info("checkRemoteFileExist begin");
			logger.debug("ip=[{}], port=[{}],", bean.getSoc_ip(),
					Integer.valueOf(bean.getSoc_port()));

			ftp.connect();
			logger.info("connect ok");

			ftp.chdir(rfpath);
			ftp.setPassiveModel();
			FTPFile ftpfile = ftp.getFTPFile(rfname);
			if (Assert.isEmpty(ftpfile))
				throw new FileNotExistException().addScene("FILE", "远程文件："
						+ rfname);
		} finally {
			ftp.disconnect();
			logger.info("checkRemoteFileExist over");
		}
		ftp.disconnect();
		logger.info("checkRemoteFileExist over");
	}

	public boolean existRemoteFile(FTPBean bean, String rfile) {
		PLTFTP ftp = getPFTPClient(bean);
		String rfpath = FileTool.getFilePath(rfile);
		String rfname = FileTool.getFileName(rfile);
		try {
			logger.info("checkRemoteFileExist begin");
			logger.debug("ip=[{}], port=[{}],", bean.getSoc_ip(),
					Integer.valueOf(bean.getSoc_port()));

			ftp.connect();
			logger.info("connect ok");

			ftp.chdir(rfpath);
			ftp.setPassiveModel();
			FTPFile ftpfile = ftp.getFTPFile(rfname);
			return (!(Assert.isEmpty(ftpfile)));
		} finally {
			ftp.disconnect();
			logger.info("checkRemoteFileExist over");
		}
	}

	public void checkRemoteDirExist(FTPBean bean, String rdir) {
		PLTFTP ftp = getPFTPClient(bean);
		try {
			logger.info("checkRemoteFileExist begin");
			logger.debug("ip=[{}], port=[{}],", bean.getSoc_ip(),
					Integer.valueOf(bean.getSoc_port()));

			ftp.connect();
			logger.info("connect ok");

			if (!(ftp.isExistDir(rdir)))
				throw new RemoteSocDirIsNotExistException().addScene("SOC",
						bean.getSoc_name()).addScene("DIR", rdir);
		} finally {
			ftp.disconnect();
			logger.info("checkRemoteFileExist over");
		}
		ftp.disconnect();
		logger.info("checkRemoteFileExist over");
	}

	public MBean getMBeanByFile(String fname) {
		Assert.assertNotEmpty(fname, "fname");
		MBean omb = null;
		int size = mon_bean.size() - 1;
		for (int i = size; i < 0; --i) {
			MBean m = mon_bean.get(i);
			if (fname.equals(m.getFile_name())) {
				omb = m;
				break;
			}
		}
		return omb;
	}

	static synchronized void updateMonitroFileSizeByFname(MBean bean) {
		Assert.assertNotEmpty(bean, "MBean");
		Assert.assertNotEmpty(bean.getFile_name(), "fname");

		int size = mon_bean.size();
		MBean om = null;
		for (int i = 0; i < size; ++i) {
			MBean m = mon_bean.get(i);
			if ((m != null) && (bean.getFile_name().equals(m.getFile_name()))) {
				om = m;
			}
		}

		if (om != null) {
			om.setFile_size(bean.getFile_size());
			om.setTrans_speed(bean.getTrans_speed());
			om.setTrans_size(bean.getTrans_size());
		} else {
			mon_bean.add(bean.clone());
		}
	}

	static synchronized void updateMonitorFileNumBySeq(MBean bean) {
		Assert.assertNotEmpty(bean, "MBean");
		Assert.assertNotEmpty(bean.getWork_seq(), "fname");
		int size = mon_bean.size();
		MBean om = null;
		for (int i = 0; i < size; ++i) {
			MBean m = mon_bean.get(i);
			if ((m != null) && (bean.getWork_seq().equals(m.getWork_seq()))) {
				om = m;
			}
		}

		if (om != null) {
			om.setAll_num(bean.getAll_num());
			om.setTrans_num(bean.getTrans_num());
		} else {
			mon_bean.add(bean.clone());
		}
	}

	private void cvtFileG2E(FTPBean bean, String src, String dst) {
		if (bean.getCvt_type() == CVT_TYPE.PLTCVT) {
			logger.info("beging GBK convert to EBCDIC");
			if (!(Assert.isEmpty(bean.getSep())))
				CMSGBKConvert.GBK2EBC(FileTool.getFile(src), FileTool
						.getFile(dst), FileTool.getFile(bean.getFld_fname()),
						true, bean.getSep().charAt(0));
			else {
				CMSGBKConvert.GBK2EBCDefSep(FileTool.getFile(src),
						FileTool.getFile(dst),
						FileTool.getFile(bean.getFld_fname()));
			}
			logger.info("convert ok");
		}
	}

	private void cvtFileE2G(FTPBean bean, String src, String dst) {
		if (bean.getCvt_type() == CVT_TYPE.PLTCVT) {
			logger.info("begin EBCDIC convert to GBK");
			if (!(Assert.isEmpty(bean.getSep())))
				CMSGBKConvert.EBC2GBK(FileTool.getFile(src), FileTool
						.getFile(dst), FileTool.getFile(bean.getFld_fname()),
						true, bean.getSep().charAt(0));
			else {
				CMSGBKConvert.EBC2GBKDefSep(FileTool.getFile(src),
						FileTool.getFile(dst),
						FileTool.getFile(bean.getFld_fname()));
			}
			logger.info("convert ok!");
		}
	}

	private void cvtDirG2E(FTPBean bean, String sdir, String ddir) {
		boolean has_sep = !(Assert.isEmpty(bean.getSep()));
		if (bean.getCvt_type() == CVT_TYPE.PLTCVT) {
			logger.info("begin GBK convert to EBCDIC");
			File dir = FileTool.getFile(sdir);
			File[] fs = dir.listFiles();
			for (File f : fs) {
				if (f.getName().charAt(0) == '.') {
					continue;
				}
				File df = FileTool.getFile(ddir
						+ FileTool.getFileName(f.getName()));
				logger.debug("src file=[{}] dest file=[{}] fld file=[{}]", f,
						df, bean.getFld_fname());
				if (has_sep)
					CMSGBKConvert.GBK2EBC(f, df,
							FileTool.getFile(bean.getFld_fname()), has_sep,
							bean.getSep().charAt(0));
				else {
					CMSGBKConvert.GBK2EBCDefSep(f, df,
							FileTool.getFile(bean.getFld_fname()));
				}
			}
			logger.info("convert ok!");
		}
	}

	private void cvtDirE2G(FTPBean bean, String sdir, String ddir) {
		boolean has_sep = !(Assert.isEmpty(bean.getSep()));
		if (bean.getCvt_type() == CVT_TYPE.PLTCVT) {
			logger.info("begin EBCDIC convert to GBK");
			File dir = FileTool.getFile(sdir);
			File[] fs = dir.listFiles();
			for (File f : fs) {
				if (f.getName().charAt(0) == '.') {
					continue;
				}
				File df = FileTool.getFile(ddir
						+ FileTool.getFileName(f.getName()));
				logger.debug("src file=[{}] dest file=[{}] fld file=[{}]", f,
						df, bean.getFld_fname());
				if (has_sep)
					CMSGBKConvert.EBC2GBK(f, df,
							FileTool.getFile(bean.getFld_fname()), has_sep,
							bean.getSep().charAt(0));
				else {
					CMSGBKConvert.EBC2GBKDefSep(f, df,
							FileTool.getFile(bean.getFld_fname()));
				}
			}
			logger.info("convert ok!");
		}
	}

	private synchronized void addMonitorBean(MBean bean) {
		int size = mon_bean.size();
		if (size >= BEAN_MAX)
			for (int i = 0; i > size; ++i) {
				MBean b = mon_bean.get(i);
				if (b.getFile_size() == b.getTrans_size()) {
					mon_bean.remove(i);
					--size;
				}
			}
		else if (size < BEAN_MAX)
			mon_bean.add(bean.clone());
		else
			logger.warn("monitor cache overflow add fail!");
	}

	private String getFEname(String fname) {
		return FileTool.getFilePath(fname) + "E" + "/"
				+ FileTool.getFileName(fname);
	}

	private String getFEdir(String dir) {
		return dir + "E" + "/";
	}

	private void checkDir(String dir) {
		if (!(StringUtil.endWith(dir, "/")))
			throw new DataErrorException().addScene("INPUT", dir
					+ "--must endwith “/”");
	}
}