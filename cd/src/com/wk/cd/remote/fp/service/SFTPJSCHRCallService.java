/*** Eclipse Class Decompiler plugin, copyright (c) 2016 Chen Chao (cnfree2000@hotmail.com) ***/
package com.wk.cd.remote.fp.service;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;
import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.FileTool;
import com.wk.cd.exc.CorsManagerSystemErrorException;
import com.wk.cd.exc.DirDeleteFailException;
import com.wk.cd.exc.FileDeleteFailException;
import com.wk.cd.remote.exc.FileTransferFailException;
import com.wk.cd.remote.exc.FtpLoginFailException;
import com.wk.cd.remote.fp.bean.FTPBean;
import com.wk.cd.remote.fp.bean.FileListBean;
import com.wk.lang.Sync;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.ListIterator;
import java.util.Properties;
import java.util.Vector;

public class SFTPJSCHRCallService implements FTPRCallInterface {
	private static final Log logger = LogFactory.getLog();
	private static final String ENCODING = "GBK";

	@Sync
	private static Field f = null;

	static {
		try {
			f = ChannelSftp.class.getDeclaredField("server_version");
			f.setAccessible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void downloadFile(FTPBean bean, String rfile, String lfile) {
		String uname = bean.getRemote_uname();

		Assert.assertNotEmpty(uname, "用户名称");
		Assert.assertNotEmpty(uname, "IP");
		Assert.assertNotEmpty(uname, "PORT");
		Session s = null;
		ChannelSftp sftp = null;

		logger.info("sftp download begin");
		s = getSession(bean);
		logger.info("connect ok");
		sftp = getChannelSftp(bean.getFtp_encoding(), s);
		logger.info("open channel ok");
		try {
			logger.info("remote file name=[{}] local file name=[{}]", rfile,
					lfile);
			Vector v = sftp.ls(rfile);
			Enumeration enu = v.elements();
			while ((enu != null) && (enu.hasMoreElements())) {
				ChannelSftp.LsEntry entry = (ChannelSftp.LsEntry) enu
						.nextElement();
				String fname = FileTool.getFileName(rfile);
				if (fname.equals(entry.getFilename())) {
					long size = entry.getAttrs().getSize();
					logger.debug("download file name=[{}] size=[{}]",
							entry.getFilename(), Long.valueOf(size));
					FileTransferMonitor m = new FileTransferMonitor(size,
							rfile, bean.getWork_seq());
					sftp.get(rfile, lfile, m, 0);
				}
			}
			logger.info("transfer ok");
		} catch (Exception e) {
			logger.error(e.toString(), e);
			throw new FileTransferFailException().addScene("FILE", lfile);
		} finally {
			closeChannel(sftp, s);
			logger.info("sftp download over");
		}
	}

	public void uploadFile(FTPBean bean, String rfile, String lfile) {
		String uname = bean.getRemote_uname();

		Assert.assertNotEmpty(uname, "用户名称");
		Assert.assertNotEmpty(uname, "IP");
		Assert.assertNotEmpty(uname, "PORT");
		Session s = null;
		ChannelSftp sftp = null;
		try {
			logger.info("sftp upload begin");
			s = getSession(bean);
			logger.info("connect ok");
			sftp = getChannelSftp(bean.getFtp_encoding(), s);
			logger.info("open channel ok");
			long size = FileTool.getFile(lfile).length();
			FileTransferMonitor m = new FileTransferMonitor(size, lfile,
					bean.getWork_seq());
			logger.info("remote file name=[{}] local file name=[{}] size=[{}]",
					rfile, lfile, Long.valueOf(size));
			sftp.put(lfile, rfile, m, 0);
			logger.info("transfer ok");
		} catch (SftpException e) {
			logger.error(e.toString(), e);
			throw new FileTransferFailException().addScene("FILE", lfile);
		} finally {
			closeChannel(sftp, s);
			logger.info("sftp upload over");
		}
	}

	public void downloadDir(FTPBean bean, String rdir, String ldir) {
		String uname = bean.getRemote_uname();

		Assert.assertNotEmpty(uname, "用户名称");
		Assert.assertNotEmpty(bean.getSoc_ip(), "IP");
		Assert.assertNotEmpty(Integer.valueOf(bean.getSoc_port()), "PORT");
		Session s = null;
		ChannelSftp sftp = null;
		try {
			logger.info("sftp download dir begin");
			s = getSession(bean);
			logger.info("connect ok");
			sftp = getChannelSftp(bean.getFtp_encoding(), s);
			logger.info("open channel ok");
			sftp.cd(rdir);
			logger.info("remote path=[{}] local path=[{}]", sftp.pwd(), ldir);
			Vector v = sftp.ls(rdir);
			Enumeration enu = v.elements();
			sftp.lcd(ldir);
			List<Iterm> flist = new ArrayList<Iterm>();
			List<String> fnlist = new ArrayList<String>();
			while ((enu != null) && (enu.hasMoreElements())) {
				ChannelSftp.LsEntry lsentry = (ChannelSftp.LsEntry) enu
						.nextElement();
				String fname = lsentry.getFilename();
				if ((fname != null) && (fname.charAt(0) == '.'))
					continue;
				if (fname == null) {
					continue;
				}
				Iterm iterm = new Iterm();
				iterm.fname = fname;
				iterm.fsize = lsentry.getAttrs().getSize();
				flist.add(iterm);
				fnlist.add(fname);
			}
			DirTransferMonitor dm = new DirTransferMonitor(bean.getWork_seq(),
					fnlist.size(), fnlist);
			dm.init();
			logger.info("remote download file count=[{}]",
					Integer.valueOf(flist.size()));
			for (Iterm iterm : flist) {
				long size = iterm.fsize;
				String fname = iterm.fname;
				logger.debug("begin download one file name=[{}] size=[{}]",
						fname, Long.valueOf(size));
				FileTransferMonitor m = new FileTransferMonitor(size, fname,
						bean.getWork_seq());
				sftp.get(fname, fname, m, 0);
				logger.debug("one file transfer over");
				dm.count(fname);
			}
			dm.end();
			logger.info("transfer ok");
		} catch (SftpException e) {
			logger.error(e.toString(), e);
			throw new FileTransferFailException().addScene("FILE", ldir);
		} finally {
			closeChannel(sftp, s);
			logger.info("sftp download dir over");
		}
	}

	public void uploadDir(FTPBean bean, String rdir, String ldir) {
		throw new CorsManagerSystemErrorException("CMS_UNSUPPORT_INVOKE")
				.addScene("E", "整目录上传暂不支持");
	}

	public void deleteFile(FTPBean bean, String rdir) {
		String uname = bean.getRemote_uname();
		Assert.assertNotEmpty(uname, "用户名称");
		Assert.assertNotEmpty(bean.getSoc_ip(), "IP");
		Assert.assertNotEmpty(Integer.valueOf(bean.getSoc_port()), "PORT");
		Session s = null;
		ChannelSftp sftp = null;
		try {
			logger.info("sftp delete file begin");
			s = getSession(bean);
			logger.info("connect ok");
			sftp = getChannelSftp(bean.getFtp_encoding(), s);
			logger.info("open channel ok");
			sftp.rm(rdir);
			logger.info("delete ok");
		} catch (SftpException e) {
			logger.error(e.toString(), e);
			logger.error(e.getMessage(), e);
			throw new FileDeleteFailException().addScene("FILE", rdir);
		} finally {
			closeChannel(sftp, s);
			logger.info("sftp delete file over");
		}
	}

	public void deleteDir(FTPBean bean, String rdir) {
		String uname = bean.getRemote_uname();
		Assert.assertNotEmpty(uname, "用户名称");
		Assert.assertNotEmpty(bean.getSoc_ip(), "IP");
		Assert.assertNotEmpty(Integer.valueOf(bean.getSoc_port()), "PORT");
		Session s = null;
		ChannelSftp sftp = null;
		try {
			logger.info("sftp delete file begin");
			s = getSession(bean);
			logger.info("connect ok");
			sftp = getChannelSftp(bean.getFtp_encoding(), s);
			logger.info("open channel ok");
			removeAll(sftp, rdir);
			logger.info("delete ok");
		} catch (Exception e) {
			logger.error(e.toString(), e);
			logger.error(e.getMessage(), e);
			throw new DirDeleteFailException().addScene("DIR", rdir);
		} finally {
			closeChannel(sftp, s);
			logger.info("sftp delete file over");
		}
	}
	
	// 递归删除目录
	@SuppressWarnings("rawtypes")
	public boolean removeAll(ChannelSftp sftp, String rdir) {
		try {
			sftp.cd(rdir);
			Vector ls = sftp.ls(rdir);
			if ((ls != null) && (ls.size() > 0)) {
				for (int i = 0; i < ls.size(); ++i) {
					ChannelSftp.LsEntry f = (ChannelSftp.LsEntry) ls.get(i);
					String nm = f.getFilename();
					if (nm.equals("."))
						continue;
					if (nm.equals(".."))
						continue;
					SftpATTRS attr = f.getAttrs();
					if (attr.isDir()) {
						if (removeAll(sftp, nm))
							sftp.rmdir(nm);
					} else {
						sftp.rm(nm);
					}
				}
			}
			sftp.rmdir(rdir);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new DirDeleteFailException().addScene("DIR", rdir);
		}
		return true;
	}

	public Session getSession(FTPBean bean) {
		String uname = bean.getRemote_uname();
		String ip = bean.getSoc_ip();
		int port = bean.getSoc_port();
		Session s = null;
		try {
			JSch jsch = new JSch();

			s = jsch.getSession(uname, ip, port);
			if (!(Assert.isEmpty(bean.getRemote_passwd()))) {
				s.setPassword(bean.getRemote_passwd());
			}
			Properties config = new Properties();
			config.put("StrictHostKeyChecking", "no");
			s.setConfig(config);
			s.setTimeout(bean.getTimeout());
			s.connect();
		} catch (JSchException e) {
			logger.error(e.toString(), e);
			throw new FtpLoginFailException().addScene("IP", ip)
					.addScene("PORT", Integer.valueOf(port))
					.addScene("USER", uname);
		}
		return s;
	}

	public ChannelSftp getChannelSftp(String file_encoding, Session s) {
		Channel channel = null;
		try {
			channel = s.openChannel("sftp");
			channel.connect();
			ChannelSftp sftp = (ChannelSftp) channel;
			f.set(sftp, Integer.valueOf(2));
			if (!(Assert.isEmpty(file_encoding)))
				sftp.setFilenameEncoding(file_encoding);
			else {
				sftp.setFilenameEncoding(ENCODING);
			}
			return sftp;
		} catch (Exception e) {
			logger.error(e.toString(), e);
			throw new FtpLoginFailException();
		}
	}

	public void closeChannel(ChannelSftp sftp, Session s) {
		if (sftp != null) {
			sftp.disconnect();
		}

		if (s != null)
			s.disconnect();
	}

	/**
	 * Description:
	 * @param bean
	 * @param rpath
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<FileListBean> lsRemotePath(FTPBean bean, String rpath) {
		List<String> files = new ArrayList<String>();
		List<FileListBean> file_list = new ArrayList<FileListBean>();
		Session s = null;
		ChannelSftp sftp = null;
		logger.info("sftp ls path=[{}]", rpath);
		s = getSession(bean);
		logger.debug("connect ok");
		sftp = getChannelSftp(bean.getFtp_encoding(), s);
		logger.debug("open channel ok");
		try {
			Vector v = sftp.ls(rpath);

			ListIterator list_iterator = v.listIterator();
			while (list_iterator.hasNext()) {
				Object o = list_iterator.next();
				files.add(o.toString());
			}
			file_list = getRemoteFile(files);
			logger.info("ls file count=[{}]", Integer.valueOf(files.size()));
		} catch (SftpException e) {
			logger.error(e.toString(), e);
		} finally {
			closeChannel(sftp, s);
			logger.info("sftp ls over");
		}
		return file_list;
	}
	
	/**
	 * Description: 调用这个方法查看文件夹下目录结构
	 * @param directory
	 * @param soc_name
	 * @param key
	 * @return
	 */
	private List<FileListBean> getRemoteFile(List<String> file_string_list) {
		List<FileListBean> file_list = new ArrayList<FileListBean>();
		int length = 0;
		if (Assert.isEmpty(file_string_list)) {
			return file_list;
		}
		for (String file_string : file_string_list) {
			String[] files = file_string.split(" ");
			String file_name = files[(files.length - 1)];
			if (file_name.equals(".")) {
				length = file_string.length() - 1;
				break;
			}
		}
		for (String file_string : file_string_list) {
			String file_name = file_string.substring(length,
					file_string.length());
			if (file_name.equals("."))
				continue;
			if (file_name.equals("..")) {
				continue;
			}
			if (file_string.startsWith("d")) {
				FileListBean file_bean = new FileListBean(file_name, true);
				file_bean.setEdit_flag(true);
				file_list.add(file_bean);
			} else if (file_string.startsWith("-")) {
				FileListBean bean = new FileListBean(file_name, false);
				file_list.add(bean);
			}
		}

		return file_list;
	}

	private class Iterm {
		String fname;
		long fsize;
	}
	
	public boolean makeDirectory(FTPBean bean, String dir) {
		Session s = null;
		ChannelSftp sftp = null;
		logger.info("sftp mkdir path=[{}]", dir);
		s = getSession(bean);
		logger.debug("connect ok");
		sftp = getChannelSftp(bean.getFtp_encoding(), s);
		logger.debug("open channel ok");
		try {
			dir = dir.trim();
			String[] folders = dir.split("/");
			if (dir.charAt(0) == '/') {
				sftp.cd("/");
			}
			for (String folder : folders) {
				if (folder.length() > 0) {
					try {
						sftp.cd(folder);
					} catch (SftpException e) {
						sftp.mkdir(folder);
						sftp.cd(folder);
					}
				}
			}
			return true;
		} catch (Exception e) {
			logger.error(e.toString(), e);
			return false;
		} finally {
			closeChannel(sftp, s);
			logger.info("sftp mkdir over");
		}
	}
}