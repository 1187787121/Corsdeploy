/**
 * Title: TestSavePublishProgDetailAction.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: zhangj
 * @version: 1.0
 * @date: 2016��11��21��
 */
package com.wk.cd.test.build.ea.action;

import com.wk.Controller;
import com.wk.beans.Injector;
import com.wk.cd.build.ea.action.AddPublishTaskAction;
import com.wk.cd.build.ea.bean.AddPublishTaskInputBean;
import com.wk.cd.common.cm.service.CommonService;
import com.wk.cd.enu.SUBMIT_TYPE;
import com.wk.db.DBSource;
import com.wk.db.Session;
import com.wk.test.TestCase;
import com.wk.util.JaDate;
import com.wk.util.JaDateTime;
import com.wk.util.JaTime;

/**
 * Class Description: 
 * @author Zhangj
 */
public class TestAddPublishTaskAction extends TestCase{
	private  DBSource db = DBSource.get();
	private  Session session;
	private Injector inject=Controller.getInstance().getInjector();
	private AddPublishTaskAction sc = inject.getBean(AddPublishTaskAction.class);
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
//		session.commit();
	}

	@Override
	protected void tearDownOnce()
		throws Exception {
		session.rollback();
	}
	
	public void testAddEnvProgBasicAction(){
		AddPublishTaskInputBean input = new AddPublishTaskInputBean();
		commonInput(input);
		input.setEnv_name("new_env");
		input.setTask_bk_desc("����һ�����Ե�");
		input.setProg_id("PG201712210004");
		input.setTarget_ver_num("cd_1.0.4_alpha");
	//	input.setProject_name("����һ����Ŀ���");
		
		try {
			sc.run(input);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ������ֵ
	 * @param input
	 */
	private void commonInput(AddPublishTaskInputBean input) {
		// ������ֵ
		input.setOrg_channel_code("01");
		input.setOrg_work_code("");
		input.setOrg_srv_name("ea_AddPublishTaskAction");
		input.setOrg_rs_code("00");
		input.setOrg_user_id("admin");
		input.setRemote_ip("10.1.1.220");
		input.setServer_port(21);
		input.setOrg_term_no("10.1.1.195");
		input.setOrg_dept_id("110001");
		input.setPbl_code("00001");
		input.setPend_srv_name("add_work_action");
		input.setPend_rs_code("html");
		input.setPendwk_bk_expl("���淢��������ϸ��Ϣ");
		input.setSubmit_type(SUBMIT_TYPE.APPLY);
		JaDateTime curdt = cmsvc.getCurrentDateTime();
		input.setDtbs_bk_date(JaDate.valueOf(curdt.dateValue()));
		input.setDtbs_bk_time(JaTime.valueOf(curdt.timeValue()));

	}
}