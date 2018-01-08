/**
 * Title: TestEnvirAction.java
 * File Description: 
 * @copyright: 2017
 * @company: CORSWORK
 * @author: wangj
 * @version: 1.0
 * @date: 2017年1月6日
 */
package com.wk.cd.test.build.en.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.Controller;
import com.wk.beans.Injector;
import com.wk.cd.build.en.action.ViewEnvirAction;
import com.wk.cd.build.en.bean.ViewEnvirInputBean;
import com.wk.cd.build.en.bean.ViewEnvirOutputBean;
import com.wk.cd.enu.SUBMIT_TYPE;
import com.wk.db.DBSource;
import com.wk.db.Session;
import com.wk.test.TestCase;
import com.wk.util.JaDate;
import com.wk.util.JaTime;

/**
 * Class Description: 
 * @author wangj
 */
public class TestViewEnvirAction extends TestCase{
	private  DBSource db = DBSource.get();
	private  Session session;
	private Injector inject=Controller.getInstance().getInjector();
	private ViewEnvirAction sc = inject.getBean(ViewEnvirAction.class);

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
	
	public void TestGetEnvironmentMsg(){
		ViewEnvirInputBean input = new ViewEnvirInputBean();
		commonInput(input);
		input.setEnv_name("test");
		ViewEnvirOutputBean output = sc.getEnvironmentMsg(input);
		System.out.println(output.getAf_flag());
		System.out.println(output.getDt_range());
		System.out.println(output.getEnv_cn_name());
		System.out.println(output.getEnv_type());
		System.out.println(output.getEnv_bk_desc());
		System.out.println(output.getEnv_name());
		System.out.println(output.getSys_name());
	}
	
	public void testQueryEnvInfoList(){
//		ViewEnvirInputBean input = new ViewEnvirInputBean();
//		List<String> env_list = new ArrayList<String>();
//		env_list.add("XulEnv");
//		input.setEnv_list(env_list);
//		ViewEnvirOutputBean output = sc.queryEnvInfoList(input);
//		System.out.println(output.getEnv_list().get(0).getEnv_name());
//		System.out.println(output.getEnv_list().get(0).getEnv_cn_name());
//		System.out.println(output.getEnv_list().get(0).getFollow_enable());
//		System.out.println(output.getEnv_list().get(0).getModify_date_time());
//		System.out.println(output.getEnv_list().get(0).getEnv_cn_name());
	}
	
	/**
	 * 公共赋值
	 * @param input
	 */
	private void commonInput(ViewEnvirInputBean input) {
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
