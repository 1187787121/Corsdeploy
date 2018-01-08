/**
 * Title: TestAddSystemAction.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: chiss
 * @version: 1.0
 * @date: 2016年11月1日
 */
package com.wk.cd.test.build.en.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.Controller;
import com.wk.beans.Injector;
import com.wk.cd.build.en.action.AddSystemAction;
import com.wk.cd.build.en.bean.AddSystemInputBean;
import com.wk.cd.build.en.bean.SystemTemplateBean;
import com.wk.cd.enu.SYS_TYPE;
import com.wk.cd.common.cm.service.CommonService;
import com.wk.cd.enu.SUBMIT_TYPE;
import com.wk.cd.enu.TEMPLATE_TYPE;
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
public class TestAddSystemAction extends TestCase{
	private DBSource db = DBSource.get();

	private Session session;

	private Injector inject = Controller.getInstance().getInjector();

	private AddSystemAction sc = inject.getBean(AddSystemAction.class);
	
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
	
	public void test1() {
		AddSystemInputBean input = new AddSystemInputBean();
		commonInput(input);
		List<SystemTemplateBean> sys_list = new ArrayList<SystemTemplateBean>();
		input.setSys_name("hulcshuaishuai");
		input.setSys_cn_name("test");
		input.setSys_bk_desc("test");
		input.setSys_type(SYS_TYPE.CHANNEL);
		//SystemTemplateBean sysbean = new SystemTemplateBean();
		//sysbean.setTemplate_type(TEMPLATE_TYPE.PRODUCT);
//		String[] template_name = {"模板名称1","模板名称2","模板名称3"};
//		sysbean.setTemplate_name(template_name);
		//sys_list.add(sysbean);
		//input.setSys_list(sys_list);
		input.setSys_list(null);
		List<String> file_list = new ArrayList<String>();
		file_list.add("1.txt");
		file_list.add("1.txt");
		file_list.add("1.txt");
		input.setFile_list(file_list.toArray(new String[file_list.size()]));
		
		sc.run(input);
	}
	
	public void test2() {
		AddSystemInputBean input = new AddSystemInputBean();
		commonInput(input);
		List<SystemTemplateBean> sys_list = new ArrayList<SystemTemplateBean>();
		input.setSys_name("test_config");
		input.setSys_cn_name("test");
		input.setSys_bk_desc("test");
		input.setSys_type(SYS_TYPE.CHANNEL);
		SystemTemplateBean sysbean = new SystemTemplateBean();
		sysbean.setTemplate_type(TEMPLATE_TYPE.PRODUCT);
		List<String> template_name = new ArrayList<String>();
		template_name.add("111");
		sysbean.setTemplate_name(template_name.toArray(new String[template_name.size()]));
		sys_list.add(sysbean);
		input.setSys_list(sys_list);
		input.setSys_list(null);
		input.setFile_list(null);
		
		sc.run(input);
	}
	
	/**
	 * 公共赋值
	 * @param input
	 */
	private void commonInput(AddSystemInputBean input) {
		// 公共赋值
		input.setOrg_channel_code("01");
		input.setOrg_work_code("");
		input.setOrg_srv_name("us_AddUserAction");
		input.setOrg_rs_code("00");
		input.setOrg_user_id("admin");
		input.setOrg_term_no("10.1.1.195");
		input.setOrg_dept_id("110001");
		input.setPbl_code("00001");
		input.setPend_srv_name("add_work_action");
		input.setPend_rs_code("html");
		input.setPendwk_bk_expl("测试新增系统信息");
		input.setSubmit_type(SUBMIT_TYPE.APPLY);
		JaDateTime curdt = cmsvc.getCurrentDateTime();
		input.setDtbs_bk_date(JaDate.valueOf(curdt.dateValue()));
		input.setDtbs_bk_time(JaTime.valueOf(curdt.timeValue()));
		
	}
}
