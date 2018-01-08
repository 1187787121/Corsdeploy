/**
 * Title: TestAddBuildTaskAction.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: wangj
 * @version: 1.0
 * @date: 2016年12月10日
 */
package com.wk.cd.test.build.ea.action;

import com.wk.Controller;
import com.wk.beans.Injector;
import com.wk.cd.build.ea.action.EditBuildTaskAction;
import com.wk.cd.build.ea.bean.EditBuildTaskInputBean;
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
 * @author wangj
 */
public class TestEditBuildTaskAction extends TestCase{
	
	private DBSource db = DBSource.get();
	private Session session;
	private Injector inject=Controller.getInstance().getInjector();
	private EditBuildTaskAction sc = inject.getBean(EditBuildTaskAction.class);
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
//		session.commit();
		session.rollback();
	}
	
	//正常
	public void test1(){
		EditBuildTaskInputBean input = new EditBuildTaskInputBean();
		commonInput(input);
		input.setEnv_name("Xul_Env");
		input.setTask_bk_desc("测试执行111");
		input.setProject_name("project");
		sc.run(input);
	}
	
	//环境不存在
	public void test2(){
		EditBuildTaskInputBean input = new EditBuildTaskInputBean();
		commonInput(input);
		input.setEnv_name("En");
		input.setTask_bk_desc("测试执行111");
		input.setProject_name("project");
		sc.run(input);
	}
	
	//项目不存在
	public void test3(){
		EditBuildTaskInputBean input = new EditBuildTaskInputBean();
		commonInput(input);
		input.setEnv_name("Xul_Env");
		input.setTask_bk_desc("测试执行111");
		input.setProject_name("project11111");
		sc.run(input);
	}
	
	//修改
	public void test4(){
		EditBuildTaskInputBean input = new EditBuildTaskInputBean();
		commonInput(input);
		input.setWork_id("TK201612130001");
		input.setTask_bk_desc("测试执行113");
		input.setProject_name("project");
		sc.run(input);
	}
	
	/**
	 * 公共赋值 
	 * @param input
	 */
	private void commonInput(EditBuildTaskInputBean input) {
		// 公共赋值
		input.setOrg_channel_code("01");
		input.setOrg_work_code("");
		input.setOrg_srv_name("en_AddEnvironmentAction");
		input.setOrg_rs_code("00");
		input.setOrg_user_id("admin");
		input.setOrg_term_no("10.1.1.195");
		input.setOrg_dept_id("110001");
		input.setPbl_code("00001");
		input.setPend_srv_name("add_work_action");
		input.setPend_rs_code("html");
		input.setPendwk_bk_expl("保存集成任务");
		input.setSubmit_type(SUBMIT_TYPE.APPLY);
		JaDateTime curdt = cmsvc.getCurrentDateTime();
		input.setDtbs_bk_date(JaDate.valueOf(curdt.dateValue()));
		input.setDtbs_bk_time(JaTime.valueOf(curdt.timeValue()));

	}
}
