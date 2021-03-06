/**
 * Title: PLTFTPRCallService.java
 * File Description: FTP远程调用服务接口
 * @copyright 2014 
 * @company CORSWORK
 * @author lixl
 * @version 1.0
 * @date 11/26/2014
 */

package com.wk.cd.remote.fp.service;

import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.FileTool;
import com.wk.cd.common.util.StringUtil;
import com.wk.cd.exc.CorsManagerSystemErrorException;
import com.wk.cd.remote.exc.*;
import com.wk.cd.remote.fp.bean.FTPBean;
import com.wk.cd.remote.fp.bean.FileListBean;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class Description:FTP远程调用服务接口
 *       适用apache-ftp实现FTP功能，此功能仅限于开放平台之间做文件传输
 *       目前暂时不支持GBK/UTF8等转码
 * @author lixl
 */
class PLTFTPRCallService  implements FTPRCallInterface {
	private static final int BUFFER_SIZE = 1024 * 10;
	private static final Log logger = LogFactory.getLog();

	/**
	 * FTP下载服务
	 * @author lixl (2014-11-30)
	 * @param bean 
	 * @param rfile 远程文件（全路径）/home/test/A.txt
	 * @param lfile 本地文件（全路径）/home/local/A.txt
	 */
	public void downloadFile(FTPBean bean, String rfile, String lfile){
		String rfpath, rfname;
		long rfsize;
		FileOutputStream fout = null;
		InputStream fin = null;

		String nlfile = FileTool.filePathCvt(lfile);
		rfpath = FileTool.getFilePath(rfile);
		rfname = FileTool.getFileName(rfile);

		PLTFTP ftp = new PLTFTP(bean.getSoc_ip(),bean.getSoc_port(), 
							bean.getRemote_uname(), bean.getRemote_passwd(),
								bean.getFtp_encoding(), bean.getTimeout());
		try{
			logger.info("download begin");
			//本地处理
			File file = FileTool.getFile(nlfile);
			FileTool.mkdir(file);
			logger.info("local file path=[{}]", file.getPath());
			fout = getFileOutStream(file);
			FileTool.checkLocalFile(file);

			logger.debug("ip=[{}], port=[{}]", bean.getSoc_ip(), bean.getSoc_port());
			//FTP开始处理
			checkLogin(ftp.connect(), bean.getSoc_ip(), 
							bean.getSoc_port(), bean.getRemote_uname());
			logger.info("connect ok");

			checkchdir(ftp.chdir(rfpath), rfpath);
			ftp.pwd();
			logger.info("cd remote path=[{}]", 
						StringUtil.ustr2wstr(ftp.getReplyString())); 
			ftp.setPassiveModel();
			FTPFile ftpfile = ftp.getFTPFile(rfname);
			checkRemoteFile(ftpfile, rfname);
			rfsize = ftpfile.getSize();
			logger.info("remote file name=[{}] size=[{}]", ftpfile.getName(), rfsize); 
			fin = ftp.getFTPFileInputStream(rfname);
			logger.info("file input stream code=[{}]", 
						StringUtil.ustr2wstr(ftp.getReplyString())); 

			//开始传输
			logger.info("begin get");
			if(bean.getIs_monitor()) {
			    transferFile(fin, fout, bean.getWork_seq(), rfile, rfsize);
			}
			checkTransferOK(ftp,rfile);
			logger.info("transfer ok");

		}finally{
			ftp.disconnect();
			logger.info("download over");
		}
	}

	public void uploadFile(FTPBean bean, String rfile, String lfile){
		String rfpath, rfname;
		long lfsize;
		OutputStream fout = null;
		FileInputStream fin = null;

		String nlfile = FileTool.filePathCvt(lfile);
		rfpath = FileTool.getFilePath(rfile);
		rfname = FileTool.getFileName(rfile);
		PLTFTP ftp = new PLTFTP(bean.getSoc_ip(),bean.getSoc_port(), 
							bean.getRemote_uname(), bean.getRemote_passwd(),
								bean.getFtp_encoding(),bean.getTimeout());
		try{
			logger.info("upload begin");
			//本地文件处理
			File file = FileTool.getFile(nlfile);
			lfsize = file.length();
			logger.info("local file=[{}] size=[{}]", file.getPath(), lfsize);
            logger.info("remote file=[{}]", rfile);
			fin = getFileInputStream(file);
			checkLocalFile(file);

			logger.debug("ip=[{}], port=[{}]", bean.getSoc_ip(), bean.getSoc_port());
			//FTP开始处理
			checkLogin(ftp.connect(), bean.getSoc_ip(), 
							bean.getSoc_port(), bean.getRemote_uname());
			logger.info("connect ok");
            logger.info("mkdir begin");
            ftp.mkdir(rfpath);
            logger.info("mkdir ok");
			checkchdir(ftp.isExistDir(rfpath), rfpath);
			ftp.pwd();
			logger.info("cd remote file path=[{}]", 
						StringUtil.ustr2wstr(ftp.getReplyString())); 
			ftp.setPassiveModel();
			fout = ftp.getFTPFileOutputStream(rfile);
			checkRemoteOutStream(fout, rfname);
			logger.info("file output stream code=[{}]", 
						StringUtil.ustr2wstr(ftp.getReplyString())); 

			//开始传输
			logger.info("begin put");
			transferFile(fin, fout, bean.getWork_seq(), lfile, lfsize);
			checkTransferOK(ftp,lfile);
			logger.info("transfer ok");

		}finally{
			ftp.disconnect();
			logger.info("upload over");
		}
	}

	public void downloadDir(FTPBean bean, String rdir, String ldir){
		FileOutputStream fout = null;
		InputStream fin = null;

		PLTFTP ftp = new PLTFTP(bean.getSoc_ip(),bean.getSoc_port(), 
							bean.getRemote_uname(), bean.getRemote_passwd(),
								bean.getFtp_encoding(),
								bean.getTimeout());
		try{
			logger.info("download dir begin");
			logger.debug("ip=[{}], port=[{}]", bean.getSoc_ip(), bean.getSoc_port());
			//FTP开始处理
			checkLogin(ftp.connect(), bean.getSoc_ip(), 
							bean.getSoc_port(), bean.getRemote_uname());
			logger.info("connect ok");
			checkchdir(ftp.chdir(rdir), rdir);
			ftp.pwd();
			logger.info("cd remote file path=[{}]", 
						StringUtil.ustr2wstr(ftp.getReplyString())); 
			ftp.setPassiveModel();

			FTPFile[] flist = ftp.listFiles();
			List<String> fnlist = new ArrayList<String>();
			for(FTPFile f : flist) {
				fnlist.add(f.getName());
			}
			DirTransferMonitor dm = new DirTransferMonitor(bean.getWork_seq(), flist.length, fnlist);
			dm.init();
			for(FTPFile ftpfile : flist) {
				logger.debug("download remote file=[{}]", ftpfile.getName());
				String nlfile = ldir + FileTool.getFileName(ftpfile.getName());

				//本地处理
				File file = FileTool.getFile(nlfile);
				FileTool.mkdir(file);
				logger.debug("local file=[{}]", file.getPath());
				fout = getFileOutStream(file);
				FileTool.checkLocalFile(file);
				long rfsize = ftpfile.getSize();
				logger.debug("remote file name=[{}] size=[{}]", ftpfile.getName(), rfsize);
				fin = ftp.getFTPFileInputStream(ftpfile.getName());
				logger.info("file input stream code=[{}]", 
							StringUtil.ustr2wstr(ftp.getReplyString())); 
				//开始传输
				logger.debug("begin one get");
				if(bean.getIs_monitor()) {
				    transferFile(fin, fout, bean.getWork_seq(), ftpfile.getName(), rfsize);
				}
				checkTransferOK(ftp,ftpfile.getName());
				logger.debug("one file transered ok");
				dm.count(ftpfile.getName());
			}
			dm.end();
			logger.info("transfer ok");

		}finally{
			ftp.disconnect();
			logger.info("download dir over");
		}
	}

	public void uploadDir(FTPBean bean, String rdir, String ldir){
		throw new CorsManagerSystemErrorException("CMS_UNSUPPORT_INVOKE")
			.addScene("E", "整目录上传暂不支持");
	}
	
	public void deleteFile(FTPBean bean, String rdir){
		PLTFTP ftp = new PLTFTP(bean.getSoc_ip(),bean.getSoc_port(), 
				bean.getRemote_uname(), bean.getRemote_passwd(),
					bean.getFtp_encoding(),
					bean.getTimeout());
		try{
			logger.info("delete file begin");
			logger.debug("ip=[{}], port=[{}]", bean.getSoc_ip(), bean.getSoc_port());
			//FTP开始处理
			checkLogin(ftp.connect(), bean.getSoc_ip(), 
							bean.getSoc_port(), bean.getRemote_uname());
			logger.info("connect ok");
			ftp.setPassiveModel();
			ftp.deleteFile(rdir);
			logger.info("delete ok");
		}finally{
			ftp.disconnect();
			logger.info("delete file over");
		}
	}
	
	public void deleteDir(FTPBean bean, String rdir){
		PLTFTP ftp = new PLTFTP(bean.getSoc_ip(),bean.getSoc_port(), 
				bean.getRemote_uname(), bean.getRemote_passwd(),
					bean.getFtp_encoding(),
					bean.getTimeout());
		try{
			logger.info("delete file begin");
			logger.debug("ip=[{}], port=[{}]", bean.getSoc_ip(), bean.getSoc_port());
			//FTP开始处理
			checkLogin(ftp.connect(), bean.getSoc_ip(), 
							bean.getSoc_port(), bean.getRemote_uname());
			logger.info("connect ok");
			ftp.setPassiveModel();
			ftp.deleteDir(rdir);
			logger.info("delete ok");
		}finally{
			ftp.disconnect();
			logger.info("delete file over");
		}
	}


	private void checkLocalFile(File file){
		if(Assert.isEmpty(file) || !file.exists()) {
			throw new FileNotExistException().addScene("FILE", 
											   "本地文件："+file.getName());
		}
	}

	private void checkLogin(boolean islogin, String ip, int port, String user){
		if(!islogin) {
			throw new FtpLoginFailException().addScene("IP", ip)
						.addScene("PORT", port).addScene("USER",user); 
		}
	}

	private void checkchdir(boolean r, String dir){
		if(!r) {
			throw new ChangeDirNotExistException().addScene("DIR", dir);
		}
	}

	private void checkRemoteFile(FTPFile f, String fname){
		if(Assert.isEmpty(f)) {
			throw new FileNotExistException().addScene("FILE", "远程文件："+fname);
		}
	}

	private void checkRemoteOutStream(OutputStream o, String fname){
		if(Assert.isEmpty(o)) {
			throw new GetRemoteFileStreamFailException().addScene("FILE", fname);
		}
	}


	private void checkTransferOK(PLTFTP ftp, String rfname){
		if(!isTransferOver(ftp)) {
			throw new FileTransferFailException().addScene("FILE", rfname);
		}
	}
	private boolean isTransferOver(PLTFTP ftp){
		return (ftp.isComplete());
	}

	private void close(OutputStream out){
		try{
			if(out != null) {
				out.close();
			}
		}catch(IOException e){
			logger.error(e.toString(), e);
		}
   }

	private void close(InputStream in){
		try{
			if(in != null) {
				in.close();
			}
		}catch(IOException e){
			logger.error(e.toString(), e);
		}
   }

	private void transferFile(InputStream in, OutputStream out, String work_seq, String file, long size){
		int s = (int)((size > BUFFER_SIZE) ? BUFFER_SIZE : size);
		byte[] b = new byte[s];
		try{
			FileTransferMonitor m = new FileTransferMonitor(size, file, work_seq);
			for(int rl=in.read(b); rl>0; rl=in.read(b)) {
				out.write(b, 0, rl);
				m.count(rl);
			}
			m.end();
		}catch(IOException e){
			logger.error(e.toString(), e);
			throw new FileTransferFailException().addScene("FILE", file);
		}finally{
			close(in);
			close(out);
		}
	}

   private FileOutputStream getFileOutStream(File file){
		try{
			return new FileOutputStream(file, false);
		}catch(FileNotFoundException e){
			logger.error(e.toString(), e);
			throw new CorsManagerSystemErrorException("CMS_GET_OUTSTREAM_ERROR").
				addScene("E", e.toString());
		}
	}

	private FileInputStream getFileInputStream(File file){
		try{
			return new FileInputStream(file);
		}catch(FileNotFoundException e){
			logger.error(e.toString(), e);
			throw new CorsManagerSystemErrorException("CMS_GET_INPUTSTREAM_ERROR").
				addScene("E", e.toString());
		}
	}

	/** 
	 * Description: 
	 * @param bean
	 * @param rpath
	 * @return 
	 */
	@Override
	public List<FileListBean> lsRemotePath(FTPBean bean, String rpath) {
		// TODO Auto-generated method stub
		List<FileListBean> lst = new ArrayList<FileListBean>();
		PLTFTP plt_ftp =  new PLTFTP(bean.getSoc_ip(), bean.getSoc_port(),
				bean.getRemote_uname(), bean.getRemote_passwd(),
				bean.getFtp_encoding(), bean.getTimeout());
		FTPClient fc = plt_ftp.getFTPClient();

		plt_ftp.connect();
		try {
			String _isopath = plt_ftp.getISOFname(rpath);
			logger.info("ftp ls path = [{}]", rpath);
			plt_ftp.chdir(_isopath);
			plt_ftp.pwd();
			plt_ftp.setPassiveModel();
			logger.info("cd remote path=[{}]",
					StringUtil.ustr2wstr(plt_ftp.getReplyString()));
			FTPFile[] dir = plt_ftp.listFiles();
			logger.info("ls file count=[{}]", dir.length);
			lst = ftpRemoteFileList(dir);

		} finally {
			plt_ftp.disconnect();
		}
		return lst;
	}
	
	/**
	 * Description: 返回的实例列表中 只写明了文件是否是目录 和文件名，其他信息都没有写
	 * @param fs
	 * @return
	 */
	private List<FileListBean> ftpRemoteFileList(FTPFile[] fs) {
	
		List<FileListBean> file_list_bean = new ArrayList<FileListBean>();
		if (!Assert.isEmpty(fs)) {
			for (FTPFile file : fs) {
				String file_name = file.getName();
				int type = file.getType();
				if (type == 1) {
					FileListBean file_bean = new FileListBean(file_name,
							 true);
					file_list_bean.add(file_bean);
				} else {
					FileListBean bean = new FileListBean(file_name, 
							false);
					file_list_bean.add(bean);

				}
			}
		}
		return file_list_bean;
	}
}

