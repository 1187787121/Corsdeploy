/**
 * Title: AS400FTPRCallService.java
 * File Description: FTP远程调用服务接口
 * @copyright 2014 
 * @company CORSWORK
 * @author lixl
 * @version 1.0
 * @date 11/26/2014
 */

package com.wk.cd.remote.fp.service;
import java.io.File;
import java.io.IOException;
import java.util.List;

import com.ibm.as400.access.FTP;
import com.wk.cd.common.util.FileTool;
import com.wk.cd.enu.CVT_TYPE;
import com.wk.cd.exc.CorsManagerSystemErrorException;
import com.wk.cd.exc.DataErrorException;
import com.wk.cd.remote.exc.*;
import com.wk.cd.remote.fp.bean.FTPBean;
import com.wk.cd.remote.fp.bean.FileListBean;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description:FTP远程调用服务接口
 * @author lixl
 */
class AS400FTPRCallService  implements FTPRCallInterface {
	private static final Log logger = LogFactory.getLog();

	/**
	 * 下载服务
	 * @author lixl (2014-12-1)
	 * @param bean 
	 * @param rfile AS400文件名称(全路径) LIB/FILE.MEMB
	 * @param lfile 本地文件名称（全路径) /home/sample/A.fld
	 */
	public void downloadFile(FTPBean bean, String rfile, String lfile){
		String[] p = rfile.split("/");
		if(p.length!=2) {
			throw new DataErrorException().addScene("INPUT", rfile);
		}
		String libname = p[0];
		String fname = p[1];

		logger.info("download begin");
		FTP ftp = new FTP(bean.getSoc_ip(), bean.getRemote_uname(), 
						  bean.getRemote_passwd());
		File file = FileTool.getFile(FileTool.filePathCvt(lfile));
		FileTool.mkdir(file);
		logger.info("local file path=[{}] name=[{}]", file.getPath(), file.getName());
		try{
			checkLogin(ftp.connect(), bean.getSoc_ip(), bean.getRemote_uname());
			logger.info("login ok");
			ftp.cd(libname);
			logger.info("remote file path=[{}] name=[{}]", libname, fname);
			if(bean.getCvt_type() == CVT_TYPE.FTPCVT) {
				ftp.setDataTransferType(FTP.ASCII);
			}else{
				ftp.setDataTransferType(FTP.BINARY);
			}
			if(!ftp.get(fname, file)) {
				throw new FileTransferFailException().addScene("FILE", rfile);
			}
			logger.info("transfer ok");
		}catch (IOException e) {
			logger.error(e.toString(), e);
			throw new FileTransferFailException().addScene("FILE", rfile);
		}finally {
			disconnect(ftp);
			logger.info("download over");
		}
	}

	/**
	 * 上传文件服务
	 * @author lixl (2014-12-1)
	 * @param bean 
	 * @param rfile 
	 * @param lfile 
	 */
	public void uploadFile(FTPBean bean, String rfile, String lfile){
		String[] p = rfile.split("/");
		if(p.length!=2) {
			throw new DataErrorException().addScene("INPUT", rfile);
		}
		String libname = p[0];
		String fname = p[1];
		String nlfile = FileTool.filePathCvt(lfile);

		logger.info("upload begin");
		FTP ftp = new FTP(bean.getSoc_ip(), bean.getRemote_uname(), 
						  bean.getRemote_passwd());
		File file = FileTool.getFile(nlfile);
		FileTool.checkLocalFile(file);
		logger.info("local file path=[{}] name=[{}]", file.getPath(), file.getName());

		try{
			checkLogin(ftp.connect(), bean.getSoc_ip(), bean.getRemote_uname());
			logger.info("login ok");
			ftp.cd(libname);
			logger.info("remote file path=[{}] name=[{}]", libname, fname);

			if(bean.getCvt_type() == CVT_TYPE.FTPCVT) {
				ftp.setDataTransferType(FTP.ASCII);
			}else{
				ftp.setDataTransferType(FTP.BINARY);
			}
			if(!ftp.put(file, fname)) {
				throw new FileTransferFailException().addScene("FILE", nlfile);
			}
			logger.info("transfer ok");
		}catch (IOException e) {
			logger.error(e.toString(), e);
			throw new FileTransferFailException().addScene("FILE", nlfile);
		}finally {
			disconnect(ftp);
			logger.info("upload over");
		}
	}

	/**
	 * 下载库下所有文件服务
	 * @author lixl (2014-12-1)
	 * @param bean 
	 * @param rdir 
	 * @param ldir 
	 */
	public void downloadDir(FTPBean bean, String rdir, String ldir){
		logger.info("download dir begin");
		FTP ftp = new FTP(bean.getSoc_ip(), bean.getRemote_uname(), 
						  bean.getRemote_passwd());
		logger.info("local dir=[{}]", ldir);
		try{
			checkLogin(ftp.connect(), bean.getSoc_ip(), bean.getRemote_uname());
			logger.info("login ok");
			ftp.cd(rdir);
			logger.info("remote dir=[{}]", rdir);
			if(bean.getCvt_type() == CVT_TYPE.FTPCVT) {
				ftp.setDataTransferType(FTP.ASCII);
			}else{
				ftp.setDataTransferType(FTP.BINARY);
			}
			for(String fname : ftp.ls()) {
				logger.debug("download file name=[{}]", fname);
				File file = FileTool.getFile(ldir+FileTool.getFileName(fname));
				if(!ftp.get(fname, file)) {
					throw new FileTransferFailException().addScene("FILE", 
													   file.getAbsoluteFile());
				}
			}
			logger.info("transfer ok");
		}catch (IOException e) {
			logger.error(e.toString(), e);
			throw new FileTransferFailException().addScene("FILE", rdir);
		}finally {
			disconnect(ftp);
			logger.info("download dir over");
		}
	}

	public void uploadDir(FTPBean bean, String rdir, String ldir){
		throw new CorsManagerSystemErrorException("CMS_UNSUPPORT_INVOKE").addScene("E", "整目录上传暂不支持");
	}
	
	public void deleteFile(FTPBean bean, String rdir){
		throw new CorsManagerSystemErrorException("CMS_UNSUPPORT_INVOKE").addScene("E", "删除远程文件暂不支持");
	}
	
	public void deleteDir(FTPBean bean, String rdir){
		throw new CorsManagerSystemErrorException("CMS_UNSUPPORT_INVOKE").addScene("E", "删除远程目录暂不支持");
	}

	private void checkLogin(boolean islogin, String ip, String user){
		if(!islogin) {
			throw new FtpLoginFailException().addScene("IP", ip).addScene("USER",user); 
		}
	}

	private void disconnect(FTP ftp){
		try{
			ftp.disconnect();
		}catch(IOException e){
			logger.error(e.toString(), e);
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
		return null;
	}
}

