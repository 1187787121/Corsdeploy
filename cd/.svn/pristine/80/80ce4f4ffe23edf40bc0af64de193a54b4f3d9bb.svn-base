/**
 * Title: TestPublishEnvProgAction.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: chiss
 * @version: 1.0
 * @date: 2016年11月11日
 */
package com.wk.cd.test.build.ea.action;

import com.wk.Controller;
import com.wk.beans.Injector;
import com.wk.cd.build.ea.action.QueryEnvProgAction;
import com.wk.cd.build.ea.bean.QueryEnvProgInputBean;
import com.wk.cd.build.ea.bean.QueryEnvProgOutputBean;
import com.wk.cd.build.ea.info.PgProgramInfo;
import com.wk.cd.common.cm.service.CommonService;
import com.wk.cd.enu.ORDER_TYPE;
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
public class TestQueryEnvProgAction extends TestCase{
	private  DBSource db = DBSource.get();
	private  Session session;
	private Injector inject=Controller.getInstance().getInjector();
	private QueryEnvProgAction sc = inject.getBean(QueryEnvProgAction.class);
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
	
	public void testQueryEnvProgAction(){
		QueryEnvProgInputBean input = new QueryEnvProgInputBean();
		commonInput(input);
		input.setEnv_name("test1");
		input.setOrder_col_name("default");
		input.setOrder_type(ORDER_TYPE.DESC);
		QueryEnvProgOutputBean output = sc.run(input);
		for (PgProgramInfo info : output.getProgram_list()) {
			System.out.println(info.toString());
		}
	}
	
	/**
	 * 公共赋值
	 * @param input
	 */
	private void commonInput(QueryEnvProgInputBean input) {
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
		input.setPendwk_bk_expl("测试新增环境关联表信息");
		input.setSubmit_type(SUBMIT_TYPE.APPLY);
		JaDateTime curdt = cmsvc.getCurrentDateTime();
		input.setDtbs_bk_date(JaDate.valueOf(curdt.dateValue()));
		input.setDtbs_bk_time(JaTime.valueOf(curdt.timeValue()));

	}
}
