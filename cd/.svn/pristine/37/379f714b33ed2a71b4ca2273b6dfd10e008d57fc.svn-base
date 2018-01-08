/**
 * Title: TestDeleteServerAction.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: chiss
 * @version: 1.0
 * @date: 2016年11月17日
 */
package com.wk.cd.test.module1;

import com.wk.Controller;
import com.wk.beans.Injector;
import com.wk.cd.build.en.action.ViewProjectAction;
import com.wk.cd.build.en.bean.CeProjectBean;
import com.wk.cd.build.en.bean.EnvTaskBean;
import com.wk.cd.build.en.bean.ViewProjectInputBean;
import com.wk.cd.build.en.bean.ViewProjectOutputBean;
import com.wk.cd.enu.SUBMIT_TYPE;
import com.wk.cd.module1.action.ViewModuleAction;
import com.wk.cd.module1.bean.ViewModuleInputBean;
import com.wk.cd.module1.bean.ViewModuleOutputBean;
import com.wk.cd.module1.action.ViewComponentAction;
import com.wk.cd.module1.bean.ViewComponentInputBean;
import com.wk.cd.module1.bean.ViewComponentOutputBean;
import com.wk.cd.module1.enu.MODULE_TYPE;
import com.wk.db.DBSource;
import com.wk.db.Session;
import com.wk.test.TestCase;
import com.wk.util.JaDate;
import com.wk.util.JaTime;

/**
 * Class Description: 
 * @author chiss
 */
public class TestViewComponentAction
		extends TestCase {
	
	private  DBSource db = DBSource.get();
	private  Session session;
	private Injector inject=Controller.getInstance().getInjector();
	private ViewComponentAction sc = inject.getBean(ViewComponentAction.class);

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
	
	public void queryPublishedCompByType(){
		ViewComponentInputBean input = new ViewComponentInputBean();
		commonInput(input);
		input.setComponent_cn_name("");
		input.setType(MODULE_TYPE.GROUP);
		ViewComponentOutputBean output = sc.queryPublishedCompByType(input);
		System.out.println(output.getComp_list().size());
	}
	
	public void testQueryProjByEnv(){
		ViewComponentInputBean input = new ViewComponentInputBean();
		commonInput(input);
		input.setId("COMP201711230083");
		ViewComponentOutputBean output = sc.getComponentDetail(input);
		System.out.println(output.getComponent().getId());
		System.out.println(output.getComponent().getCn_name());
	}
	
	public void getGroupDetail(){
		ViewComponentInputBean input = new ViewComponentInputBean();
		commonInput(input);
		input.setId("COMP201711240095");
		ViewComponentOutputBean output = sc.getGroupDetail(input);
		System.out.println(output.getFile_path());
		System.out.println(output.getPublish_state());
		System.out.println(output.getGroup().toString());
	}
	
	
//	public void queryPublishedCompByType(){
//		ViewComponentInputBean input = new ViewComponentInputBean();
//		commonInput(input);
//		input.setId("COMP201711240095");
//		ViewComponentOutputBean output = sc.queryPublishedCompByType(input);
//		System.out.println(output.getFile_path());
//		System.out.println(output.getPublish_state());
//		System.out.println(output.getGroup().toString());
//	}
	/**
	 * 公共赋值
	 * @param input
	 */
	private void commonInput(ViewComponentInputBean input) {
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
