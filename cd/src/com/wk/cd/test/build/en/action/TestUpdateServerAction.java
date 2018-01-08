/**
 * Title: TestAddServerAction.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: yangl
 * @version: 1.0
 * @date: 2016��11��1��
 */
package com.wk.cd.test.build.en.action;

import com.wk.Controller;
import com.wk.beans.Injector;
import com.wk.cd.build.en.action.UpdateServerAction;
import com.wk.cd.build.en.bean.SocBean;
import com.wk.cd.build.en.bean.UpdateServerInputBean;
import com.wk.cd.enu.SERVER_OS;
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
public class TestUpdateServerAction
		extends TestCase {
	
	private  DBSource db = DBSource.get();
	private  Session session;
	private Injector inject=Controller.getInstance().getInjector();
	private UpdateServerAction sc = inject.getBean(UpdateServerAction.class);

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
	
//	public void testUpdateServerAction(){
//		UpdateServerInputBean input = new UpdateServerInputBean();
//		commonInput(input);
//		input.setCe_server_name("test");
//		input.setServer_cn_name("test");
//		input.setServer_bk_desc("test");
//		input.setServer_ip("10.1.1.230");
//		input.setServer_os(SERVER_OS.LINUX);
//		input.setOs_sbk_ver("1");
//		input.setMid_ware_list(null);
//		SocBean[] soc_name_list = new SocBean[1];
//		for (int i = 0; i < soc_name_list.length; i++) {
//			SocBean socBean = new SocBean();
//			socBean.setSoc_name("230ftp");
//			int[] apply_type = {1};
//			socBean.setApply_type_list(apply_type);
//			soc_name_list[i] = socBean;
//		}
//		input.setSoc_list(soc_name_list);
//		sc.run(input);
//		
//	}
	
	/**
	 * ������ֵ
	 * @param input
	 */
	private void commonInput(UpdateServerInputBean input) {
		// ������ֵ
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
		input.setPendwk_bk_expl("�������������Ϣ");
		input.setSubmit_type(SUBMIT_TYPE.APPLY);
		input.setDtbs_bk_date(JaDate.today());
		input.setDtbs_bk_time(JaTime.time());
	}
}
