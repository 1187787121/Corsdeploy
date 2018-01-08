/**
 * Title: TestPageInteTaskAction.java
 * File Description: 测试分页查询集成任务列表
 * @copyright: 2016
 * @company: CORSWORK
 * @author: chiss
 * @version: 1.0
 * @date: 2016年11月19日
 */
package com.wk.cd.test.build.ea.action;

import com.wk.Controller;
import com.wk.beans.Injector;
import com.wk.cd.build.ea.action.PageStorageTaskAction;
import com.wk.cd.build.ea.bean.EnvTagStorageBean;
import com.wk.cd.build.ea.bean.PageStorageTaskInputBean;
import com.wk.cd.build.ea.bean.PageStorageTaskOutputBean;
import com.wk.cd.common.cm.service.CommonService;
import com.wk.cd.enu.SUBMIT_TYPE;
import com.wk.db.DBSource;
import com.wk.db.Session;
import com.wk.test.TestCase;
import com.wk.util.JaDate;
import com.wk.util.JaDateTime;
import com.wk.util.JaTime;

/**
 * Class Description: 测试分页查询集成任务列表
 * @author chiss
 */
public class TestPageStorageTaskAction extends TestCase{
	private DBSource db = DBSource.get();
	private Session session;
	private Injector inject=Controller.getInstance().getInjector();
	private PageStorageTaskAction sc = inject.getBean(PageStorageTaskAction.class);
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
	
	public void testPageStorageTask(){
		PageStorageTaskInputBean input = new PageStorageTaskInputBean();
		commonInput(input);
		input.setStart_recd(0);
		input.setLimit_recd(10);
		input.setEnv_name("XulEnv");
		PageStorageTaskOutputBean output = sc.run(input);
		System.out.println(output.getAll_recd());
		for(EnvTagStorageBean bean : output.getStor_list()){
			System.out.println(bean.getStorage_id());
			System.out.println(bean.getStart_bk_tm());
			System.out.println(bean.getEnd_bk_tm());
			System.out.println(bean.getStorage_status());
			System.out.println(bean.getStorage_result());
			System.out.println(bean.getPackage_list()[0]);
			System.out.println("--------------------------------");
			System.out.println(bean.getLog_name());
			System.out.println(bean.getTar_ver_num());
			System.out.println(bean.getStorage_list_uuid());
		}
		
	}
	
	/**
	 * 公共赋值 
	 * @param input
	 */
	private void commonInput(PageStorageTaskInputBean input) {
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
