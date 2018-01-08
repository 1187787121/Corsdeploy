/**
 * Title: FTPCallHelper.java
 * File Description: 
 * @copyright: 2017
 * @company: CORSWORK
 * @author: yangl
 * @version: 1.0
 * @date: 2017Äê4ÔÂ7ÈÕ
 */
package com.wk.cd.common.util;

import com.wk.Controller;
import com.wk.beans.Injector;
import com.wk.cd.common.cm.service.CommonService;
import com.wk.cd.remote.fp.bean.FTPBean;
import com.wk.cd.remote.fp.service.FTPRCallService;

/**
 * Class Description:
 * @author yangl
 */
public class FTPCallHelper {

	private static final Injector injector = Controller.getInstance().getInjector();

	private static CommonService commonService = injector.getBean(CommonService.class);


	public static void uploadDir(String ftp_soc,String rdir,String ldir,String work_seq){
		work_seq = Assert.isEmpty(work_seq) ? "no work seq" : work_seq;
		FTPBean bean = commonService.getFTPBeanBySocName(ftp_soc,work_seq);
		FTPRCallService sc = new FTPRCallService();
		sc.uploadDir(bean, rdir, ldir);
	}
	
	public static void uploadFile(String ftp_soc,String rfile,String lfile,String work_seq){
		work_seq = Assert.isEmpty(work_seq) ? "no work seq" : work_seq;
		FTPBean bean = commonService.getFTPBeanBySocName(ftp_soc,work_seq);
		FTPRCallService sc = new FTPRCallService();
		sc.uploadFile(bean, rfile, lfile);
	}
	
	public static void downFile(String ftp_soc,String rfile,String lfile,String work_seq){
		work_seq = Assert.isEmpty(work_seq) ? "no work seq" : work_seq;
		FTPBean bean = commonService.getFTPBeanBySocName(ftp_soc,work_seq);
		FTPRCallService sc = new FTPRCallService();
		sc.downloadFile(bean, rfile, lfile);
	}

	public static void main(String args[]) {
//		String rfiles = "/home/sample/nohup.out";
//		String lfiles="/Users/luomw/temp/nohup.out";
//		downloadFile("1011220yl_system", rfiles, lfiles);

        System.out.println(FileTool.getFileName("/home/sample/nohup.out"));


    }

}
