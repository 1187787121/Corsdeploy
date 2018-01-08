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

import com.wk.Controller;
import com.wk.beans.Injector;
import com.wk.cd.build.ea.action.AddEnvProgBasicAction;
import com.wk.cd.build.ea.bean.AddEnvProgBasicInputBean;
import com.wk.cd.build.ea.bean.AddEnvProgBasicOutputBean;
import com.wk.cd.enu.PROG_TYPE;
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
public class TestAddEnvProgBasicAction extends TestCase{
	private  DBSource db = DBSource.get();
	private  Session session;
	private Injector inject=Controller.getInstance().getInjector();
	private AddEnvProgBasicAction sc = inject.getBean(AddEnvProgBasicAction.class);
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
		session.commit();
	}

	@Override
	protected void tearDownOnce()
		throws Exception {
//		session.commit();
	}
	
	public void testAddEnvProgBasicAction(){
		AddEnvProgBasicInputBean input = new AddEnvProgBasicInputBean();
		commonInput(input);
		input.setProg_type(PROG_TYPE.PUBLISH);
		input.setProg_name("方案1");
		input.setEnv_name("环境名一");
		input.setPub_template_name("pub");
		input.setRol_template_name("rol");
		AddEnvProgBasicOutputBean o = sc.run(input);
		System.out.println(o.getProg_id());
		 
	}
	
	/**
	 * 公共赋值
	 * @param input
	 */
	private void commonInput(AddEnvProgBasicInputBean input) {
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
