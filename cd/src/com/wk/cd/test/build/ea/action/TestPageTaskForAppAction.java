/**
 * Title: TestPageTaskForAppAction.java
 * File Description: 
 * @copyright: 2017
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2017年2月28日
 */
package com.wk.cd.test.build.ea.action;

import com.wk.Controller;
import com.wk.beans.Injector;
import com.wk.cd.build.ea.action.PageTaskForAppAction;
import com.wk.cd.build.ea.bean.EnvTaskAppBean;
import com.wk.cd.build.ea.bean.PageTaskForAppInputBean;
import com.wk.cd.build.ea.bean.PageTaskForAppOutputBean;
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
 * @author Xul
 */
public class TestPageTaskForAppAction extends TestCase{
	
	private  DBSource db = DBSource.get();
	private  Session session;
	private Injector inject=Controller.getInstance().getInjector();
	private PageTaskForAppAction sc = inject.getBean(PageTaskForAppAction.class);
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
	
	public void testpageAllProject(){
		PageTaskForAppInputBean input = new PageTaskForAppInputBean();
		input.setEnv_name("XulEnv");
		input.setStart_recd(0);
		input.setLimit_recd(5);
		commonInput(input);
		PageTaskForAppOutputBean output = sc.run(input);
		for (EnvTaskAppBean bean : output.getEnv_task_list()) {
			System.out.println(bean.getEnv_name());
			System.out.println(bean.getProgram_name());
			System.out.println(bean.getProg_id());
			System.out.println(bean.getCreateDateTime());
			System.out.println(bean.getProject_short_name());
		}
		System.out.println(output.getAll_recd());
		
	}
	
	/**
	 * 公共赋值
	 * @param input
	 */
	private void commonInput(PageTaskForAppInputBean input) {
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
