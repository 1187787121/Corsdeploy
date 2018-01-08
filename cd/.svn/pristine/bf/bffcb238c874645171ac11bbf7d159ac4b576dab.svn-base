/**
 * Title: TestAddEnvProgBasicAction.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: chiss
 * @version: 1.0
 * @date: 2016年11月10日
 */
package com.wk.cd.test.build.ea.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.Controller;
import com.wk.beans.Injector;
import com.wk.cd.build.ea.action.TargetStorageAction;
import com.wk.cd.build.ea.bean.TargetPackageBean;
import com.wk.cd.build.ea.bean.TargetStorageInputBean;
import com.wk.cd.build.ea.info.UuFilelistInfo;
import com.wk.cd.common.cm.service.CommonService;
import com.wk.cd.enu.SUBMIT_TYPE;
import com.wk.db.DBSource;
import com.wk.db.Session;
import com.wk.test.TestCase;
import com.wk.util.JaDate;
import com.wk.util.JaDateTime;
import com.wk.util.JaTime;

/**
 * Class Description: 
 * @author chiss
 */
public class TestTargetStorageAction extends TestCase{
	private  DBSource db = DBSource.get();
	private  Session session;
	private Injector inject=Controller.getInstance().getInjector();
	private TargetStorageAction sc = inject.getBean(TargetStorageAction.class);
	private CommonService cmsvc = inject.getBean(CommonService.class);
	
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
	
	public void testTargetStorageAction(){
		TargetStorageInputBean input = new TargetStorageInputBean();
		commonInput(input);
		input.setEnv_name("test_publish");
		input.setInte_ver_num("dddkk");
		input.setStorage_bk_desc("xuph测试描述");
		input.setCe_server_name("testProg");
		input.setSoc_name("220ssh");
		input.setTag_server_name("svn");
		input.setTag_soc_name("240svn");
		input.setTag_bk_dir("/CorsManager/Train/storage");
		List<TargetPackageBean> tar_package_list = new ArrayList<TargetPackageBean>();
		
		List<UuFilelistInfo> file_list = new ArrayList<UuFilelistInfo>();
		UuFilelistInfo filis = new UuFilelistInfo();
		filis.setFile_name("bak.sh");
		filis.setFile_path("/home/front/xpp/");
		filis.setFile_type("sh");
		UuFilelistInfo filss = new UuFilelistInfo();
		filss.setFile_name("gbk.txt");
		filss.setFile_path("/home/front/xpp/");
		filss.setFile_type("txt");
		
		List<UuFilelistInfo> file_lists = new ArrayList<UuFilelistInfo>();
		UuFilelistInfo filisd = new UuFilelistInfo();
		filisd.setFile_name("gbk.txt");
		filisd.setFile_path("/home/front/xpp/");
		filisd.setFile_type("sh");
		UuFilelistInfo filssdd = new UuFilelistInfo();
		filssdd.setFile_name("app.xlsx");
		filssdd.setFile_path("/home/front/");
		filssdd.setFile_type("xlsx");
		
		file_list.add(filis);
		file_list.add(filss);
		
		file_lists.add(filisd);
		file_lists.add(filssdd);
		TargetPackageBean bean = new TargetPackageBean();
		bean.setPackage_name("krishna.tar");
		bean.setFile_list(file_list);
		bean.setStorage_bk_path("/home/front/xpp/");
		bean.setTarget_relative_path("/dddkk/sss");
		
		TargetPackageBean bean2 = new TargetPackageBean();
		bean2.setStorage_bk_path("/home/front/");
		bean2.setTarget_relative_path("");
		bean2.setPackage_name("jkisme.tar");
		bean2.setFile_list(file_lists);
		
		tar_package_list.add(bean);
		tar_package_list.add(bean2);
		input.setTar_package_list(tar_package_list);
		sc.run(input);
	}
	
	/**
	 * 公共赋值
	 * @param input
	 */
	private void commonInput(TargetStorageInputBean input) {
		// 公共赋值
		input.setOrg_channel_code("01");
		input.setOrg_work_code("");
		input.setOrg_srv_name("en_AddEnvironmentAction");
		input.setOrg_rs_code("00");
		input.setOrg_user_id("admin");
		input.setRemote_ip("10.1.1.220");
		input.setServer_port(22);
		input.setOrg_term_no("10.1.1.195");
		input.setOrg_dept_id("110001");
		input.setPbl_code("00001");
		input.setPend_srv_name("add_work_action");
		input.setPend_rs_code("html");
		input.setPendwk_bk_expl("测试新增环境关联表信息");
		input.setSubmit_type(SUBMIT_TYPE.APPLY);
		JaDateTime curdt = cmsvc.getCurrentDateTime();
		input.setDtbs_bk_date(JaDate.valueOf(curdt.dateValue()));
		input.setDtbs_bk_time(JaTime.valueOf(curdt.timeValue()));

	}
}
