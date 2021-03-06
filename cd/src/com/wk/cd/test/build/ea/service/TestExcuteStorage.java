/**
 * Title: TestExcuteStorage.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: xuph
 * @version: 1.0
 * @date: 2016��11��18��
 */
package com.wk.cd.test.build.ea.service;

import java.util.ArrayList;
import java.util.List;

import com.wk.Controller;
import com.wk.beans.Injector;
import com.wk.cd.build.ea.bean.TargetPackageBean;
import com.wk.cd.build.ea.info.UuFilelistInfo;
import com.wk.cd.build.ea.info.UuSocInfo;
import com.wk.cd.build.ea.service.ExcuteStoragePubService;
import com.wk.db.DBSource;
import com.wk.db.Session;
import com.wk.test.TestCase;

/**
 * Class Description: 
 * @author xuph
 */
public class TestExcuteStorage extends TestCase{
	private  DBSource db = DBSource.get();
	private  Session session;
	private Injector inject=Controller.getInstance().getInjector();
	private ExcuteStoragePubService sc = inject.getBean(ExcuteStoragePubService.class);
	@Override
	protected void setUp()
		throws Exception {
		if (session == null) {
			session = db.openSession();
			session.beginTransaction();
		}
	}

	@Override
	protected void setUpOnce()
		throws Exception {
		session = db.openSession();
		session.beginTransaction();
	}

	@Override
	protected void tearDown()
		throws Exception {
		session.commitAndResume();
	}

	@Override
	protected void tearDownOnce()
		throws Exception {
		session.commit();
	}
	
	public void testExcuteStorage(){
		UuSocInfo exesocInfo = new UuSocInfo();
		exesocInfo.setExe_soc_name("220ssh");
		UuSocInfo versionInfo = new UuSocInfo(); 
		versionInfo.setCode_bk_dir("/CorsManager/Train/storage");
		versionInfo.setVer_soc_name("240svn");
		List<TargetPackageBean> tar_list = new ArrayList<TargetPackageBean>();
		TargetPackageBean tar1 = new TargetPackageBean();
		tar1.setPackage_name("packcs1");
		List<UuFilelistInfo>file_list = new ArrayList<UuFilelistInfo>();
		UuFilelistInfo fils = new UuFilelistInfo();
		fils.setFile_name("a.txt");
		fils.setFile_path("/home/front/filedir/");
		UuFilelistInfo filss = new UuFilelistInfo();
		filss.setFile_name("b.txt");
		filss.setFile_path("/home/front/filedir/");
		file_list.add(filss);
		file_list.add(fils);
		tar1.setFile_list(file_list);
		
		TargetPackageBean tar2 = new TargetPackageBean();
		tar2.setPackage_name("packcs2");
		List<UuFilelistInfo>file_list1 = new ArrayList<UuFilelistInfo>();
		UuFilelistInfo filssd = new UuFilelistInfo();
		filssd.setFile_name("a.txt");
		filssd.setFile_path("/home/front/filedir/");
		UuFilelistInfo filsss = new UuFilelistInfo();
		filsss.setFile_name("b.txt");
		filsss.setFile_path("/home/front/filedir/");
		file_list1.add(filssd);
		file_list1.add(filsss);
		tar2.setFile_list(file_list1);
		
		String storage_id  = "ST201701040001";
		String env_name = "tst_prog";
		
		tar_list.add(tar1);
		tar_list.add(tar2);
	   // sc.getExcuteStorageId(tar_list, exesocInfo, versionInfo, storage_id, env_name);
//	    sc.getStorageIdBk(tar_list, exesocInfo, versionInfo, storage_id, env_name);
	}
	
  /*   public void testSvnUploadFile(){
		UuExsocInfo exesocInfo = new UuExsocInfo();
		exesocInfo.setSoc_name("220ssh");
		UuVersocInfo versionInfo = new UuVersocInfo(); 
		versionInfo.setCode_soc_name("228ftp");
		versionInfo.setCode_bk_dir("/svntest/");
		List<String> tarname_list = new ArrayList<String>();
		tarname_list.add("packname.tar");
		sc.svnUploadFile(tarname_list,exesocInfo, versionInfo);
	}*/
}
