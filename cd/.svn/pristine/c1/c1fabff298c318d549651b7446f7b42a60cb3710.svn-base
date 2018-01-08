/**
 * Title: TestViewConfigModAction.java
 * File Description: 测试修改配置文件相关服务
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2016年11月12日
 */
package com.wk.cd.test.build.ea.action;

import com.wk.Controller;
import com.wk.beans.Injector;
import com.wk.cd.build.ea.action.ViewConfigModAction;
import com.wk.cd.build.ea.bean.EnvModFileBean;
import com.wk.cd.build.ea.bean.ServerConfigBean;
import com.wk.cd.build.ea.bean.ViewConfigModInputBean;
import com.wk.cd.build.ea.bean.ViewConfigModOutputBean;
import com.wk.cd.enu.CFG_TYPE;
import com.wk.cd.remote.fp.bean.FileListBean;
import com.wk.db.DBSource;
import com.wk.db.Session;
import com.wk.test.TestCase;
import com.wk.util.JaDate;

/**
 * Class Description: 测试修改配置文件相关服务
 * @author Xul
 */
public class TestViewConfigModAction extends TestCase{
	
	private  DBSource db = DBSource.get();
	private  Session session;
	private Injector inject=Controller.getInstance().getInjector();
	private ViewConfigModAction sc = inject.getBean(ViewConfigModAction.class);
	
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
	
	//测试生成新批次号
	public void testGetNewBatch(){
		ViewConfigModInputBean input = new ViewConfigModInputBean();
		input.setDtbs_bk_date(JaDate.today());
//		ViewConfigModOutputBean output = sc.getNewBatch(input);
//		System.out.println(output.getBatch_no());
	}
	
//	//测试查询环境下所有服务器中的配置数据源
//	public void testQueryAllConfigSoc(){
//		ViewConfigModInputBean input = new ViewConfigModInputBean();
//		input.setEnv_name("XulEnv");
//		ViewConfigModOutputBean output = sc.queryAllConfigSoc(input);
//		for(ServerConfigBean bean : output.getServer_config_list()){
//			System.out.println(bean.getServer_ip());
//			System.out.println(bean.getSoc_name());
//			System.out.println(bean.getUser_root_path());
//		}
//	}
	
	//测试查看批次内修改、删除文件列表
	public void testQueryModList(){
		ViewConfigModInputBean input = new ViewConfigModInputBean();
		input.setServer_ip("10.1.1.220");
		input.setBatch_no("201612010005");
		input.setCe_server_name("testProg");
		ViewConfigModOutputBean output = sc.queryModList(input);
		System.out.println("修改文件列表");
		for(EnvModFileBean bean : output.getModify_list()){
			System.out.println(bean.getFull_path());
			System.out.println(bean.getDir_yn_flag());
			System.out.println(bean.isDel_mod_flag());
		}
		System.out.println("删除文件列表");
		for(EnvModFileBean bean : output.getDelete_list()){
			System.out.println(bean.getFull_path());
			System.out.println(bean.getDir_yn_flag());
			System.out.println(bean.isDel_mod_flag());
		}
	}
	
	//测试查看文件列表
	public void testShowDirectory(){
		ViewConfigModInputBean input = new ViewConfigModInputBean();
		input.setWork_seq("201612010001");
		input.setCe_server_name("testProg");
		input.setCfg_type(CFG_TYPE.TOMCAT);
		input.setMod_flag(true);
		input.setRelative_path("/home/front/");
		ViewConfigModOutputBean output = sc.showDirectory(input);
		for(FileListBean bean : output.getFile_list_bean()){
			System.out.println(bean.getFile());
			System.out.println(bean.getType());
			System.out.println(bean.isEdit_flag());
			System.out.println(bean.isDir());
		}
	}
	
	public void testqueryTomcatServer(){
		ViewConfigModInputBean input = new ViewConfigModInputBean();
		input.setEnv_name("XulEnv");
		ViewConfigModOutputBean output = sc.queryTomcatServer(input);
		for(ServerConfigBean bean : output.getServer_list()){
			System.out.println(bean.getServer_ip());
			System.out.println(bean.getUser_root_path());
		}
	}
}
