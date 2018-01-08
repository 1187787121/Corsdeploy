/**
 * Title: TestDeleteServerAction.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: chiss
 * @version: 1.0
 * @date: 2016��11��17��
 */
package com.wk.cd.test.build.en.action;

import com.wk.Controller;
import com.wk.beans.Injector;
import com.wk.cd.build.en.action.ViewProjectAction;
import com.wk.cd.build.en.bean.CeProjectBean;
import com.wk.cd.build.en.bean.EnvTaskBean;
import com.wk.cd.build.en.bean.ViewProjectInputBean;
import com.wk.cd.build.en.bean.ViewProjectOutputBean;
import com.wk.cd.enu.SUBMIT_TYPE;
import com.wk.db.DBSource;
import com.wk.db.Session;
import com.wk.test.TestCase;
import com.wk.util.JaDate;
import com.wk.util.JaTime;

/**
 * Class Description: 
 * @author chiss
 */
public class TestViewProjectAction
		extends TestCase {
	
	private  DBSource db = DBSource.get();
	private  Session session;
	private Injector inject=Controller.getInstance().getInjector();
	private ViewProjectAction sc = inject.getBean(ViewProjectAction.class);

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
	
	public void testQueryProjByEnv(){
		ViewProjectInputBean input = new ViewProjectInputBean();
		commonInput(input);
		input.setEnv_name("XulEnv");
		ViewProjectOutputBean output = sc.queryProjByEnv(input);
		for (CeProjectBean bean : output.getProj_list()) {
			System.out.println(bean.getProject_name());
		}
		
	}
	public void testQueryTaskByProject(){
		ViewProjectInputBean input = new ViewProjectInputBean();
		commonInput(input);
		input.setProject_name("200number");
		ViewProjectOutputBean output = sc.queryTaskByProject(input);
		for (EnvTaskBean bean : output.getTask_info_list()) {
			System.out.println(bean.toString());
		}
	}
	public void testGetSingleProject(){
		ViewProjectInputBean input = new ViewProjectInputBean();
		commonInput(input);
		input.setProject_name("wangjTest");
		ViewProjectOutputBean output = sc.getSingleProject(input);
		System.out.println(output.getProj_bean().getProject_name());
	}
	
	/**
	 * ������ֵ
	 * @param input
	 */
	private void commonInput(ViewProjectInputBean input) {
		// ������ֵ
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
		input.setPendwk_bk_expl("�������������Ϣ");
		input.setSubmit_type(SUBMIT_TYPE.APPLY);
		input.setDtbs_bk_date(JaDate.today());
		input.setDtbs_bk_time(JaTime.time());
	}
}
