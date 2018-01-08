/**
 * Title: TestReadEnvConfigFileAction.java
 * File Description: 测试读取配置文件服务
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2016年11月12日
 */
package com.wk.cd.test.build.ea.action;

import com.wk.Controller;
import com.wk.beans.Injector;
import com.wk.cd.build.ea.action.ReadEnvConfigFileAction;
import com.wk.cd.build.ea.bean.ReadEnvConfigFileInputBean;
import com.wk.cd.build.ea.bean.ReadEnvConfigFileOutputBean;
import com.wk.cd.common.cm.service.CommonService;
import com.wk.cd.enu.SUBMIT_TYPE;
import com.wk.db.DBSource;
import com.wk.db.Session;
import com.wk.test.TestCase;
import com.wk.util.Base64;
import com.wk.util.JaDate;
import com.wk.util.JaDateTime;
import com.wk.util.JaTime;

/**
 * Class Description: 测试读取配置文件服务
 * @author Xul
 */
public class TestReadEnvConfigFileAction extends TestCase{
	
	private DBSource db = DBSource.get();
	private Session session;
	private Injector inject=Controller.getInstance().getInjector();
	private ReadEnvConfigFileAction sc = inject.getBean(ReadEnvConfigFileAction.class);
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
		ReadEnvConfigFileInputBean input = new ReadEnvConfigFileInputBean();
		commonInput(input);
//		input.setSoc_name("228ftp");
		input.setRelative_path("/home/front/1.sh");
		input.setEncoding("utf-8");
		ReadEnvConfigFileOutputBean output = sc.run(input);
//		System.out.println(output.getConfig_string());
		System.out.println(Base64.decodeString(output.getConfig_string()));
	}
	
	/**
	 * 公共赋值
	 * @param input
	 */
	private void commonInput(ReadEnvConfigFileInputBean input) {
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
		input.setPendwk_bk_expl("读取配置文件服务");
		input.setSubmit_type(SUBMIT_TYPE.APPLY);
		JaDateTime curdt = cmsvc.getCurrentDateTime();
		input.setDtbs_bk_date(JaDate.valueOf(curdt.dateValue()));
		input.setDtbs_bk_time(JaTime.valueOf(curdt.timeValue()));

	}
}
