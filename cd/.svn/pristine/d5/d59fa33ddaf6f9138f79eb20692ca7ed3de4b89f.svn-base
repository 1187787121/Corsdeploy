/**
 * Title: TestAddServerAction.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: yangl
 * @version: 1.0
 * @date: 2016年11月1日
 */
package com.wk.cd.test.build.en.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.Controller;
import com.wk.beans.Injector;
import com.wk.cd.build.en.action.AddServerAction;
import com.wk.cd.build.en.bean.AddServerInputBean;
import com.wk.cd.build.en.bean.SocBean;
import com.wk.cd.enu.SERVER_OS;
import com.wk.cd.common.util.Assert;
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
public class TestAddServerAction
		extends TestCase {
	
	private  DBSource db = DBSource.get();
	private  Session session;
	private Injector inject=Controller.getInstance().getInjector();
	private AddServerAction sc = inject.getBean(AddServerAction.class);

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
	
	public void testAddServerAction1(){
		AddServerInputBean input = new AddServerInputBean();
		commonInput(input);
		input.setCe_server_name("test");
		input.setServer_cn_name("test");
		input.setServer_bk_desc("test");
		input.setServer_ip("10.1.1.230");
		input.setServer_os(SERVER_OS.LINUX);
		input.setOs_sbk_ver("1");
		input.setMid_ware_list(null);
		List<String> soc_name_list = new ArrayList<String>();
		soc_name_list.add("230telnet");
//		input.setSoc_name_list(soc_name_list);
		System.out.println(Assert.isEmpty(input.getSoc_name_list()));
		sc.run(input);
		
	}

	
	/**
	 * 公共赋值
	 * @param input
	 */
	private void commonInput(AddServerInputBean input) {
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
