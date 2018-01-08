/**
 * Title: TestViewServerAction.java
 * File Description: 
 * @copyright: 2017
 * @company: CORSWORK
 * @author: wangj
 * @version: 1.0
 * @date: 2017年1月6日
 */
package com.wk.cd.test.build.ea.action;

import com.wk.Controller;
import com.wk.beans.Injector;
import com.wk.cd.build.en.action.ViewServerAction;
import com.wk.cd.build.en.bean.ServerBean;
import com.wk.cd.build.en.bean.SysEnvBean;
import com.wk.cd.build.en.bean.ViewServerInputBean;
import com.wk.cd.build.en.bean.ViewServerOutputBean;
import com.wk.cd.common.cm.service.CommonService;
import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.SUBMIT_TYPE;
import com.wk.db.DBSource;
import com.wk.db.Session;
import com.wk.test.TestCase;
import com.wk.util.JaDate;
import com.wk.util.JaDateTime;
import com.wk.util.JaTime;

/**
 * Class Description: 
 * @author wangj
 */
public class TestViewServerAction extends TestCase{
	private  DBSource db = DBSource.get();
	private  Session session;
	private Injector inject=Controller.getInstance().getInjector();
	private ViewServerAction sc = inject.getBean(ViewServerAction.class);
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
	
	public void testQueryStorageDetail(){
		ViewServerInputBean input = new ViewServerInputBean();
		commonInput(input);
		ViewServerOutputBean output  = sc.getAllServerName(input);
		if(!Assert.isEmpty(output.getServer_list())){
			for (ServerBean bean : output.getServer_list()) {
				System.out.println(bean.getServer_name());
			}
		}
	}
	
	public void testQueryDetailAndRelatedInfo(){
		ViewServerInputBean input = new ViewServerInputBean();
		input.setCe_server_name("testProg");
		input.setOrg_user_id("admin");
		ViewServerOutputBean output = sc.queryDetailAndRelatedInfo(input);
		System.out.println(output.getServer_detail_bean().getServer_cn_name());
		for(SysEnvBean bean : output.getServer_detail_bean().getSys_env_list()){
			System.out.println(bean.getSys_name());
			System.out.println(bean.getSys_cn_name());
		}
	}
	
	/**
	 * 公共赋值
	 * @param input
	 */
	private void commonInput(ViewServerInputBean input) {
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
