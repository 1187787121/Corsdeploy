/**
 * Title: TestPageAllProjectAction.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: xuph
 * @version: 1.0
 * @date: 2016年11月1日
 */
package com.wk.cd.test.build.en.action;

import com.wk.Controller;
import com.wk.beans.Injector;
import com.wk.cd.build.en.action.PageAllProjectAction;
import com.wk.cd.build.en.bean.CeProjectBean;
import com.wk.cd.build.en.bean.PageAllProjectInputBean;
import com.wk.cd.build.en.bean.PageAllProjectOutputBean;
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
 * @author xuph
 */
public class TestPageAllProjectAction extends TestCase{
	private  DBSource db = DBSource.get();
	private  Session session;
	private Injector inject=Controller.getInstance().getInjector();
	private PageAllProjectAction sc = inject.getBean(PageAllProjectAction.class);
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
		PageAllProjectInputBean input = new PageAllProjectInputBean();
		PageAllProjectOutputBean output = new PageAllProjectOutputBean();
		commonInput(input);
		int limit_recd = input.getLimit_recd();
		int start_recd = input.getStart_recd();
		String sys_name = input.getSys_name();
		//input.setSys_name("test_tpl");
		input.setSys_name("");
		
		//input.setOrder_col_name("rel_env_num");
		input.setOrder_col_name("default");
		input.setOrder_type(ORDER_TYPE.ASC);
		input.setStart_recd(0);
		input.setLimit_recd(10);
		output= sc.run(input);
		System.out.println(output.getAll_recd());
		for (CeProjectBean list : output.getProj_list()) {
			System.out.println(list.toString());
			System.out.println("getCreate_bk_date: " + list.getCreate_bk_date());
		}
		
	}
	
	/**
	 * 公共赋值
	 * @param input
	 */
	private void commonInput(PageAllProjectInputBean input) {
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
		input.setPendwk_bk_expl("测试新增组件信息");
		input.setSubmit_type(SUBMIT_TYPE.APPLY);
		JaDateTime curdt = cmsvc.getCurrentDateTime();
		input.setDtbs_bk_date(JaDate.valueOf(curdt.dateValue()));
		input.setDtbs_bk_time(JaTime.valueOf(curdt.timeValue()));

	}
}
