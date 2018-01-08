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
import com.wk.cd.build.en.action.UpdateSystemAction;
import com.wk.cd.build.en.bean.SystemTemplateBean;
import com.wk.cd.build.en.bean.UpdateSystemInputBean;
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
public class TestUpdateSystemAction extends TestCase{
	private DBSource db = DBSource.get();

	private Session session;

	private Injector inject = Controller.getInstance().getInjector();

	private UpdateSystemAction sc = inject.getBean(UpdateSystemAction.class);
	
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
	
	public void test() {
		UpdateSystemInputBean input = new UpdateSystemInputBean();
		commonInput(input);
		List<SystemTemplateBean> sys_list = new ArrayList<SystemTemplateBean>();
		input.setSys_name("xitong8");
		input.setSys_cn_name("就是中文名test");
		input.setSys_bk_desc("好好搞test");
		input.setSys_type(SYS_TYPE.ESB);
		SystemTemplateBean sysbean = new SystemTemplateBean();
		sysbean.setTemplate_type(TEMPLATE_TYPE.PRODUCT);
//		String[] template_name = {"模板名称4","模板名称5","模板名称6"};
//		sysbean.setTemplate_name(template_name);
		sys_list.add(sysbean);
		input.setSys_list(sys_list);
		List<String> file_list = new ArrayList<String>();
		file_list.add("a.txt");
		file_list.add("b.txt");
		file_list.add("b.txt");
		input.setFile_list(file_list.toArray(new String[file_list.size()]));
		sc.run(input);
	}
	
	/**
	 * 公共赋值
	 * @param input
	 */
	private void commonInput(UpdateSystemInputBean input) {
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
		input.setPendwk_bk_expl("测试修改系统信息");
		input.setSubmit_type(SUBMIT_TYPE.APPLY);
		JaDateTime curdt = cmsvc.getCurrentDateTime();
		input.setDtbs_bk_date(JaDate.valueOf(curdt.dateValue()));
		input.setDtbs_bk_time(JaTime.valueOf(curdt.timeValue()));
		
	}
}
