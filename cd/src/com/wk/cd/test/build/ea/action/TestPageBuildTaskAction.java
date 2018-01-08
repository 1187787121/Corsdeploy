/**
 * Title: TestPageBuildTaskAction.java
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
import com.wk.cd.build.ea.action.PageBuildTaskAction;
import com.wk.cd.build.ea.bean.PageBuildTaskInputBean;
import com.wk.cd.build.ea.bean.PageBuildTaskOutputBean;
import com.wk.cd.build.ea.info.EnvTaskInfo;
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
public class TestPageBuildTaskAction extends TestCase{
	private DBSource db = DBSource.get();
	private Session session;
	private Injector inject=Controller.getInstance().getInjector();
	private PageBuildTaskAction sc = inject.getBean(PageBuildTaskAction.class);
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
	
	//正常
	public void test1(){
		PageBuildTaskInputBean input = new PageBuildTaskInputBean();
		commonInput(input);
		input.setStart_recd(0);
		input.setLimit_recd(1);
		input.setEnv_name("XulEnv");
		PageBuildTaskOutputBean output = sc.run(input);
		for(EnvTaskInfo bean : output.getBuild_task_list()){
			System.out.println(bean.getWork_id());
			System.out.println(bean.getTask_status());
			System.out.println("getCreate_bk_date: " + bean.getCrt_bk_date());
		}
	}
	
	//环境不存在
	public void test2(){
		PageBuildTaskInputBean input = new PageBuildTaskInputBean();
		commonInput(input);
		input.setStart_recd(0);
		input.setLimit_recd(1);
		input.setEnv_name("Xul_En");
		PageBuildTaskOutputBean output = sc.run(input);
		for(EnvTaskInfo bean : output.getBuild_task_list()){
			System.out.println(bean.getWork_id());
			System.out.println(bean.getTask_status());
			System.out.println("getCreate_bk_date: " + bean.getCrt_bk_date());
		}
	}
	
	/**
	 * 公共赋值 
	 * @param input
	 */
	private void commonInput(PageBuildTaskInputBean input) {
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
		input.setPendwk_bk_expl("删除配置文件服务");
		input.setSubmit_type(SUBMIT_TYPE.APPLY);
		JaDateTime curdt = cmsvc.getCurrentDateTime();
		input.setDtbs_bk_date(JaDate.valueOf(curdt.dateValue()));
		input.setDtbs_bk_time(JaTime.valueOf(curdt.timeValue()));
	}
}
