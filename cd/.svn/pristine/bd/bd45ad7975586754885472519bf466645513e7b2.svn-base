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

import com.wk.Controller;
import com.wk.beans.Injector;
import com.wk.cd.build.en.action.PageSystemListAction;
import com.wk.cd.build.en.bean.PageSystemListBean;
import com.wk.cd.build.en.bean.PageSystemListInputBean;
import com.wk.cd.build.en.bean.PageSystemListOutputBean;
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
public class TestPageSystemListAction
		extends TestCase {
	private DBSource db = DBSource.get();

	private Session session;

	private Injector inject = Controller.getInstance().getInjector();

	private PageSystemListAction sc = inject
			.getBean(PageSystemListAction.class);

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
		PageSystemListInputBean input = new PageSystemListInputBean();
		commonInput(input);
		input.setSys_name("");
		input.setOrder_col_name("sys_name");
		input.setOrder_type(ORDER_TYPE.DESC);
		input.setStart_recd(0);
		input.setLimit_recd(10);
		PageSystemListOutputBean output = sc.run(input);
		System.out.println(output.getAll_recd());
		for (PageSystemListBean itt :  output.getPage_sys_List()) {
//			System.out.println(itt.toString());
			System.out.println("getCreate_bk_date: " + itt.getCreate_bk_date()+itt.getCreate_bk_time());
		}
	/*	for (int i = 0; i < output.getPage_sys_List().size(); i++) {
			System.out.println("----------------------");
			System.out.println(output.getPage_sys_List().get(i).getSys_name());
			System.out.println(output.getPage_sys_List().get(i)
					.getSys_cn_name());
			System.out.println(output.getPage_sys_List().get(i).getSys_type());
			System.out.println(output.getPage_sys_List().get(i)
					.getRele_env_num());
			System.out.println(output.getPage_sys_List().get(i).getMount_num());
			System.out.println(output.getPage_sys_List().get(i)
					.getTemplate_num());
		}
		System.out.print(output.getPage_sys_List());*/
		// sc.run(input);
	}
	
//	public void test2() {
//		PageSystemListInputBean input = new PageSystemListInputBean();
//		commonInput(input);
//		input.setSys_name("test_prg");
//		input.setOrder_col_name("create_bk_date");
//		input.setOrder_type(ORDER_TYPE.DESC);
//		input.setStart_recd(0);
//		input.setLimit_recd(10);
//		PageSystemListOutputBean output = sc.run(input);
//		System.out.println(output.getAll_recd());
//		for (PageSystemListBean itt :  output.getPage_sys_List()) {
//			System.out.println(itt.toString());
//			System.out.println("getCreate_bk_date: " + itt.getCreate_bk_date()+itt.getCreate_bk_time());
//		}
//	}

	/**
	 * 公共赋值
	 * @param input
	 */
	private void commonInput(PageSystemListInputBean input) {
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
