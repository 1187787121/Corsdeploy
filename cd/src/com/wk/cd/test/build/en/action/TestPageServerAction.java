/**
 * Title: TestPageServerAction.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: yangl
 * @version: 1.0
 * @date: 2016年11月1日
 */
package com.wk.cd.test.build.en.action;

import com.wk.Controller;
import com.wk.beans.Injector;
import com.wk.cd.build.en.action.PageServerAction;
import com.wk.cd.build.en.bean.PageServerBean;
import com.wk.cd.build.en.bean.PageServerInputBean;
import com.wk.cd.build.en.bean.PageServerOutputBean;
import com.wk.cd.enu.ORDER_TYPE;
import com.wk.cd.enu.SUBMIT_TYPE;
import com.wk.db.DBSource;
import com.wk.db.Session;
import com.wk.test.TestCase;
import com.wk.util.JaDate;
import com.wk.util.JaTime;

/**
 * Class Description: 
 * @author yangl
 */
public class TestPageServerAction extends TestCase{
	private  DBSource db = DBSource.get();
	private  Session session;
	private Injector inject=Controller.getInstance().getInjector();
	private PageServerAction sc = inject.getBean(PageServerAction.class);
	
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
	
	public void test(){
		PageServerInputBean input = new PageServerInputBean();
		PageServerOutputBean output = new PageServerOutputBean();
		commonInput(input);
		//input.setServer_ip("10.1.1.220");
		//input.setServer_ip("100");
		input.setServer_ip("28");
		input.setOrder_col_name("create_bk_date");
		input.setOrder_type(ORDER_TYPE.ASC);
		input.setStart_recd(0);
		input.setLimit_recd(10);
		output= sc.run(input);
		for (PageServerBean bean : output.getServer_list()) {
			System.out.println(bean.getServer_name());
			System.out.println(bean.getServer_ip());
			System.out.println("getCreate_bk_date: " + bean.getCreate_bk_date());
		}
		System.out.println(output.getAll_recd());
	}
	
	/**
	 * 公共赋值
	 * @param input
	 */
	private void commonInput(PageServerInputBean input) {
		// 公共赋值
		input.setOrg_channel_code("01");
		input.setOrg_work_code("");
		input.setOrg_srv_name("en_AddServerAction");
		input.setOrg_rs_code("00");
		input.setOrg_user_id("admin");
		input.setOrg_term_no("10.1.1.195");
		input.setOrg_dept_id("110001");
		input.setPbl_code("00001");
		input.setPend_srv_name("add_work_action");
		input.setPend_rs_code("html");
		input.setPendwk_bk_expl("测试新增组件信息");
		input.setSubmit_type(SUBMIT_TYPE.APPLY);
		input.setDtbs_bk_date(JaDate.today());
		input.setDtbs_bk_time(JaTime.time());
	}
}
