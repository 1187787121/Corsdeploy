/**
 * Title: TestViewProgAction.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: chiss
 * @version: 1.0
 * @date: 2016年11月15日
 */
package com.wk.cd.test.build.ea.action;

import com.wk.Controller;
import com.wk.beans.Injector;
import com.wk.cd.build.ea.action.ViewStorageAction;
import com.wk.cd.build.ea.bean.DownFileBean;
import com.wk.cd.build.ea.bean.TargetPackageBean;
import com.wk.cd.build.ea.bean.ViewStorageInputBean;
import com.wk.cd.build.ea.bean.ViewStorageOutputBean;
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
public class TestViewStorageAction extends TestCase{
	private  DBSource db = DBSource.get();
	private  Session session;
	private Injector inject=Controller.getInstance().getInjector();
	private ViewStorageAction sc = inject.getBean(ViewStorageAction.class);
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
	
	public void testQueryStorageDetail(){
		ViewStorageInputBean input = new ViewStorageInputBean();
		commonInput(input);
		input.setStorage_id("ST201701120001");
		ViewStorageOutputBean output  = sc.queryStorageDetail(input);
		System.out.println(output.getEnv_name());
		System.out.println(output.getStorage_bk_desc());
		System.out.println(output.getProject_name());
		System.out.println(output.getCe_server_name());
		System.out.println(output.getSoc_name());
		System.out.println(output.getTag_soc_name());
		System.out.println(output.getTag_server_name());
		System.out.println(output.getTag_bk_dir());
//		output.getTar_package_list();
		for (TargetPackageBean bean : output.getTar_package_list()) {
			System.out.println(bean.getPackage_name());
			System.out.println(bean.getFile_list().toString());
		}
		System.out.println(output.getStorage_status());
		System.out.println(output.getStorage_result());
		System.out.println(output.getStart_bk_tm());
		System.out.println(output.getEnd_bk_tm());
	}
	
	public void testQueryDownFileList(){
		ViewStorageInputBean input = new ViewStorageInputBean();
		commonInput(input);
		input.setStorage_id("ST201701120001");
		ViewStorageOutputBean output  = sc.queryDownFileList(input);
		for (DownFileBean bean : output.getDown_file_list()) {
			System.out.println(bean.getFile_name());
		}
	}
	
	/**
	 * 公共赋值
	 * @param input
	 */
	private void commonInput(ViewStorageInputBean input) {
		// 公共赋值
		input.setOrg_channel_code("01");
		input.setOrg_work_code("");
		input.setOrg_srv_name("en_AddEnvironmentAction");
		input.setOrg_rs_code("00");
		input.setOrg_user_id("admin");
		input.setRemote_ip("10.1.1.220");
		input.setServer_port(21);
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
