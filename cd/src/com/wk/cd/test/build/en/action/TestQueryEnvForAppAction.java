/**
 * Title: TestQueryEnvForAppAction.java
 * File Description: 
 * @copyright: 2017
 * @company: CORSWORK
 * @author: xuph
 * @version: 1.0
 * @date: 2017年2月28日
 */
package com.wk.cd.test.build.en.action;

import java.util.Arrays;
import java.util.List;

import com.wk.Controller;
import com.wk.beans.Injector;
import com.wk.cd.build.en.action.QueryEnvForAppAction;
import com.wk.cd.build.en.bean.EnvInfoBean;
import com.wk.cd.build.en.bean.QueryEnvForAppInputBean;
import com.wk.cd.build.en.bean.QueryEnvForAppOutputBean;
import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.SUBMIT_TYPE;
import com.wk.db.DBSource;
import com.wk.db.Session;
import com.wk.test.TestCase;

/**
 * Class Description: 
 * @author xuph
 */
public class TestQueryEnvForAppAction extends TestCase{
	private DBSource db = DBSource.get();
	private Session session;
	private Injector inject=Controller.getInstance().getInjector();
	private QueryEnvForAppAction sc = inject.getBean(QueryEnvForAppAction.class);
	
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
	
	public void testQueryEnvForAppAction(){
		QueryEnvForAppInputBean input = new QueryEnvForAppInputBean();
		commonInput(input);
		QueryEnvForAppOutputBean output = new QueryEnvForAppOutputBean();
		output = sc.run(input);
		List<EnvInfoBean> env_ls = output.getEnv_list();
		if(!Assert.isEmpty(env_ls)){
			for (EnvInfoBean envInfoBean : env_ls) {
				pl("环境名："+envInfoBean.getEnv_name());
				pl("类型："+envInfoBean.getEnv_type().getCname());
				pl("构成要素"+Arrays.toString(envInfoBean.getEle_type()));
				p(envInfoBean.getCreate_date_time());
				p(envInfoBean.getModify_date_time());
				p("---------------ver_server_list---------------");
				List<String>ver_list = envInfoBean.getVer_server_list();
				List<String>db_list = envInfoBean.getDb_server_list();
				List<String>app_list = envInfoBean.getSys_server_list();
				if(!Assert.isEmpty(ver_list)){
					for (String string : ver_list) {
						p(string);
					}
				}
				p("----------------db_server_list----------------");
				if(!Assert.isEmpty(db_list)){
					for (String string : db_list) {
						p(string);
					}
				}
				p("-----------------apply_server_list-------------");
				if(!Assert.isEmpty(app_list)){
					for (String string : app_list) {
						p(string);
					}
				}
				p("-------------------------------NEXT------------------------------------");
			}
		}
	}
	
	public void p(Object o){
		System.out.println(o);
	}
	
	public void pl(Object o){
		System.out.print(o);
	}
	
	/**
	 * 公共赋值
	 * @param input
	 */
	private void commonInput(QueryEnvForAppInputBean input) {
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
	 

	}
}
