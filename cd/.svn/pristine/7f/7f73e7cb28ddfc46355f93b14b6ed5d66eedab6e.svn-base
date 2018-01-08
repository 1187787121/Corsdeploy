/**
 * Title: TestWriteEnvConfigFileAction.java
 * File Description: 测试修改配置文件服务
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2016年11月12日
 */
package com.wk.cd.test.build.ea.action;

import com.wk.Controller;
import com.wk.beans.Injector;
import com.wk.cd.build.ea.action.WriteEnvConfigFileAction;
import com.wk.cd.build.ea.bean.WriteEnvConfigFileInputBean;
import com.wk.cd.common.cm.service.CommonService;
import com.wk.cd.enu.SUBMIT_TYPE;
import com.wk.db.DBSource;
import com.wk.db.Session;
import com.wk.test.TestCase;
import com.wk.util.JaDate;
import com.wk.util.JaDateTime;
import com.wk.util.JaTime;

/**
 * Class Description: 测试修改配置文件服务
 * @author Xul
 */
public class TestWriteEnvConfigFileAction extends TestCase{
	
	private DBSource db = DBSource.get();
	private Session session;
	private Injector inject=Controller.getInstance().getInjector();
	private WriteEnvConfigFileAction sc = inject.getBean(WriteEnvConfigFileAction.class);
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
	
	public void test1(){
		WriteEnvConfigFileInputBean input = new WriteEnvConfigFileInputBean();
		commonInput(input);
		input.setEnv_name("Xul_Env");
		input.setCe_server_name("220Server");
		input.setBatch_no("201612010001");
		input.setRelative_path("/home/front/2.sh");
		input.setConfig_string("Y2QgL2hvbWUvZnJvbnQvY3YvZHBwL3ZtZW51MDRfYmFieTExCmZ1bmN0aW9uIGNoZWNrKCl7IGE9YGNrc3VtIC9ob21lL2Zyb250L2N2L2RwcC8kMWA7YT0kKCBleHByIHN1YnN0ciAiJGEiIDEgOSk7Yj1gCmNrc3VtIC9ob21lL2Zyb250LyQxYDtiPSQoZXhwciBzdWJzdHIgIiRiIiAxIDkpO2lmIFsgIiRhIiA9PSAiJGIiIF07dGhlbiBlY2hvICJzdWNjZWVkICI7ZWxzZSBlY2hvICJmYWlsOlskMV0iO2ZpIH0Kd2hpbGUgcmVhZCBpO2RvIGlmIFsgLWYgL2hvbWUvZnJvbnQvJGkgXTt0aGVuIGNoZWNrICRpID4+IGNoZWNrMS50YXIuY2hlY2subG9nO2ZpO2RvbmUgPCBjaGVjazEudGFyLmxpc3QKYz1gZ3JlcCAiZmFpbCIgY2hlY2sxLnRhci5jaGVjay5sb2dgO2lmIFsgeCIkYyIgIT0geCIiIF07dGhlbiBlY2hvICRjOyBleGl0IDE7ZmkK");
		input.setEncoding("utf-8");
		sc.run(input);
	}
	
	/**
	 * 公共赋值
	 * @param input
	 */
	private void commonInput(WriteEnvConfigFileInputBean input) {
		// 公共赋值
		input.setOrg_channel_code("01");
		input.setOrg_work_code("");
		input.setOrg_srv_name("en_AddEnvironmentAction");
		input.setOrg_rs_code("00");
		input.setOrg_user_id("admin");
		input.setOrg_term_no("10.1.1.195");
		input.setOrg_dept_id("110001");
		input.setPbl_code("00001");
		input.setPend_srv_name("add_work_action");
		input.setPend_rs_code("html");
		input.setPendwk_bk_expl("修改配置文件服务");
		input.setSubmit_type(SUBMIT_TYPE.APPLY);
		JaDateTime curdt = cmsvc.getCurrentDateTime();
		input.setDtbs_bk_date(JaDate.valueOf(curdt.dateValue()));
		input.setDtbs_bk_time(JaTime.valueOf(curdt.timeValue()));

	}
}
