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
import com.wk.cd.build.ea.action.ViewTaskStorageAction;
import com.wk.cd.build.ea.bean.TaskSrorageBean;
import com.wk.cd.build.ea.bean.ViewTaskStorageInputBean;
import com.wk.cd.build.ea.bean.ViewTaskStorageOutputBean;
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
public class TestViewTaskStorageAction extends TestCase{
	private  DBSource db = DBSource.get();
	private  Session session;
	private Injector inject=Controller.getInstance().getInjector();
	private ViewTaskStorageAction sc = inject.getBean(ViewTaskStorageAction.class);
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
	
	public void testQueryTaskStorage(){
		ViewTaskStorageInputBean input = new ViewTaskStorageInputBean();
		commonInput(input);
		input.setEnv_name("test");
		input.setNum(10);
		ViewTaskStorageOutputBean output = sc.queryTaskStorage(input);
		for (TaskSrorageBean bean : output.getTask_storage_list()) {
			System.out.println(bean.toString());
		}
	}
	
	public void testQueryTaskByProName(){
		ViewTaskStorageInputBean input = new ViewTaskStorageInputBean();
		commonInput(input);
		input.setProject_name("test001");
		input.setNum(10);
		ViewTaskStorageOutputBean output = sc.queryTaskByProName(input);
		for (TaskSrorageBean bean : output.getTask_storage_list()) {
			System.out.println(bean.getTask_id());
		}
	}
	
	/**
	 * 公共赋值
	 * @param input
	 */
	private void commonInput(ViewTaskStorageInputBean input) {
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
