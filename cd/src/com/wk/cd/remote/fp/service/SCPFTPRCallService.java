/**
 * Title: SCPFTPRCallService.java
 * File Description: FTPԶ�̵��÷���ӿ�
 * @copyright 2014 
 * @company CORSWORK
 * @author lixl
 * @version 1.0
 * @date 11/26/2014
 */

package com.wk.cd.remote.fp.service;

import java.util.List;

import com.wk.cd.common.util.*;
import com.wk.cd.enu.CVT_TYPE;
import com.wk.cd.exc.*;
import com.wk.cd.remote.fp.bean.FTPBean;
import com.wk.cd.remote.fp.bean.FileListBean;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description:FTPԶ�̵��÷���ӿ�
 * 
 * @author lixl
 */
class SCPFTPRCallService implements FTPRCallInterface {
	private static final Log logger = LogFactory.getLog();

	/**
	 * ͨ����ҽű������ļ�
	 * 
	 * @author lixl (2014-12-4)
	 * @param bean
	 * @param rfile
	 * @param lfile
	 */
	public void downloadFile(FTPBean bean, String rfile, String lfile) {
		String[] cmd;
		String outbr = new String();

		logger.info("download begin!");
		cmd = getCmdArgs(bean, rfile, lfile, "D");
		cmd[0] = bean.getFtp_local_script();
		logger.info("download script =[{}]", StringUtil.ary2str(cmd, " "));
		outbr = ScriptUtil.execScript(cmd, false);
		logger.info("download script exectue ok! message={\n{}}", outbr);
		// ִ��ת��ű�
		if (bean.getCvt_type() == CVT_TYPE.FTPCVT) {
			cmd[0] = bean.getCvt_local_script();
			logger.info("convert script=[{}]", StringUtil.ary2str(cmd, " "));
			outbr = "";
			outbr = ScriptUtil.execScript(cmd, false);
			logger.info("convert script exectue ok! message={\n{}}", outbr);
		}
		logger.info("transfer ok");
	}

	/**
	 * ͨ����ҽű��ϴ��ļ�
	 * 
	 * @author lixl (2014-12-4)
	 * @param bean
	 * @param rfile
	 * @param lfile
	 */
	public void uploadFile(FTPBean bean, String rfile, String lfile) {
		String[] cmd;
		String outbr;

		logger.info("upload begin");
		// ִ��ת��ű�
		if (bean.getCvt_type() == CVT_TYPE.FTPCVT) {
			cmd = getCmdArgs(bean, rfile, lfile, "U");
			cmd[0] = bean.getCvt_local_script();
			logger.info("convert script =[{}]", StringUtil.ary2str(cmd, " "));
			outbr = ScriptUtil.execScript(cmd, false);
			logger.info("convert script execute ok! message={\n{}}", outbr);
		}

		cmd = getCmdArgs(bean, rfile, lfile, "U");
		cmd[0] = bean.getFtp_local_script();
		logger.info("upload script =[{}]", StringUtil.ary2str(cmd, " "));
		outbr = ScriptUtil.execScript(cmd, false);
		logger.info("upload script execute ok! message={\n{}}", outbr);
		logger.info("transfer ok");
	}

	public void downloadDir(FTPBean bean, String rdir, String ldir) {
		throw new CorsManagerSystemErrorException("CMS_UNSUPPORT_INVOKE")
				.addScene("E", "��Ŀ¼�����ݲ�֧��");
	}

	public void uploadDir(FTPBean bean, String rdir, String ldir) {
		throw new CorsManagerSystemErrorException("CMS_UNSUPPORT_INVOKE")
				.addScene("E", "��Ŀ¼�ϴ��ݲ�֧��");
	}
	
	public void deleteFile(FTPBean bean, String rdir){
		throw new CorsManagerSystemErrorException("CMS_UNSUPPORT_INVOKE")
				.addScene("E", "ɾ��Զ���ļ��ݲ�֧��");
	}
	
	public void deleteDir(FTPBean bean, String rdir){
		throw new CorsManagerSystemErrorException("CMS_UNSUPPORT_INVOKE")
				.addScene("E", "ɾ��Զ��Ŀ¼�ݲ�֧��");
	}

	private String[] getCmdArgs(FTPBean bean, String rfile, String lfile,
			String flag) {
		String[] cmd = { "", "", "", "", "", "", "", "" };
		cmd[1] = flag;
		cmd[2] = rfile;
		cmd[3] = lfile;
		if (!Assert.isEmpty(bean.getSoc_ip())) {
			cmd[4] = bean.getSoc_ip();
		}
		if (!Assert.isEmpty(bean.getSoc_port())) {
			cmd[5] = String.valueOf(bean.getSoc_port());
		}
		if (!Assert.isEmpty(bean.getRemote_uname())) {
			cmd[6] = bean.getRemote_uname();
		}
		if (!Assert.isEmpty(bean.getRemote_passwd())) {
			cmd[7] = bean.getRemote_passwd();
		}
		return cmd;
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
