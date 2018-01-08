/**
 * Title: TestAddSystemAction.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: chiss
 * @version: 1.0
 * @date: 2016年11月1日
 */
package com.wk.cd.test.module1;

import com.wk.Controller;
import com.wk.beans.Injector;
import com.wk.cd.common.cm.service.CommonService;
import com.wk.cd.enu.ORDER_TYPE;
import com.wk.cd.enu.SUBMIT_TYPE;
import com.wk.cd.module1.action.PageComponentAction;
import com.wk.cd.module1.bean.PageComponentInputBean;
import com.wk.cd.module1.bean.PageComponentOutputBean;
import com.wk.cd.module1.info.MoComponentInfo;
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
public class TestPageComponentAction
		extends TestCase {
	private DBSource db = DBSource.get();

	private Session session;

	private Injector inject = Controller.getInstance().getInjector();

	private PageComponentAction sc = inject.getBean(PageComponentAction.class);

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
		PageComponentInputBean input = new PageComponentInputBean();
		commonInput(input);
		input.setComponent_cn_name("");
		input.setOrder_col_name("crt_bk_date");
//		input.setOrder_col_name("default");
		input.setOrder_type(ORDER_TYPE.DESC);
		input.setStart_recd(0);
		input.setLimit_recd(10);
		PageComponentOutputBean output = sc.run(input);
		System.out.println(output.getAll_recd());
		for (MoComponentInfo info :  output.getComp_list()) {
			System.out.println(info.getComponent_cn_name());
		}
	}
	

	/**
	 * 公共赋值
	 * @param input
	 */
	private void commonInput(PageComponentInputBean input) {
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
