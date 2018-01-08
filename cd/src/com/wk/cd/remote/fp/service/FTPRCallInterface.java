package com.wk.cd.remote.fp.service;

import com.wk.cd.remote.fp.bean.FTPBean;
import com.wk.cd.remote.fp.bean.FileListBean;
import java.util.List;

abstract interface FTPRCallInterface {
	public abstract void downloadFile(FTPBean paramFTPBean,
			String paramString1, String paramString2);

	public abstract void uploadFile(FTPBean paramFTPBean, String paramString1,
			String paramString2);

	public abstract void downloadDir(FTPBean paramFTPBean, String paramString1,
			String paramString2);

	public abstract void uploadDir(FTPBean paramFTPBean, String paramString1,
			String paramString2);

	public abstract void deleteFile(FTPBean paramFTPBean, String paramString);

	public abstract void deleteDir(FTPBean paramFTPBean, String paramString);

	public abstract List<FileListBean> lsRemotePath(FTPBean paramFTPBean,
			String paramString);
}