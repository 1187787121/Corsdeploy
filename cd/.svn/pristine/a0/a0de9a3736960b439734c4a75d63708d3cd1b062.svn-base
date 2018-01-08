/**
 * Title: TestRegistLicenseAction.java
 * File Description: 测试注册license信息
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2016年10月12日
 */
package com.wk.cd.test.common;

import com.wk.Controller;
import com.wk.beans.Injector;
import com.wk.cd.common.action.RegistLicenseAction;
import com.wk.cd.common.bean.RegistLicenseInputBean;
import com.wk.cd.common.util.CfgTool;
import com.wk.cd.enu.SUBMIT_TYPE;
import com.wk.db.DBSource;
import com.wk.db.Session;
import com.wk.test.TestCase;
import com.wk.util.GBKProperties;
import com.wk.util.JaDate;

/**
 * Class Description: 测试注册license信息
 * @author xuph
 */
public class TestRegistLicenseAction extends TestCase{
	
	private DBSource db = DBSource.get();
	private Session session;
	private Injector inject = Controller.getInstance().getInjector();
	private RegistLicenseAction sc = inject.getBean(RegistLicenseAction.class);
	
	@Override
	protected void setUpOnce() {
		session = db.openSession();
		session.beginTransaction();
	}

	@Override
	protected void tearDownOnce() {
		// session.commit();
		session.rollback();
		// session.close();
	}

	@Override
	protected void setUp() {
		if (session == null) {
			session = db.openSession();
			session.beginTransaction();
		}
	}

	@Override
	protected void tearDown() {
//		session.rollbackAndResume();
		session.commitAndResume();
	}
	
	/**
	 * Description:正常测试
	 */
	public void test1(){
		RegistLicenseInputBean input = new RegistLicenseInputBean();
		input.setZh_name("沃克软件");
		input.setName("wk");
		input.setExpire_end_date(JaDate.valueOf("2020-01-01"));
		input.setIp("0000000");
		input.setLicense("11347D758413D5013B813BCC0842D44E2B395B8C8D22D20F92A5543C315675DC30873E33D5485928CCF4BFBC7A752E990960908495E72D751531365153A92AF7");
		commonInput(input);
		sc.run(input);
		GBKProperties pro = CfgTool.getProperties("./license.properties");
		System.out.println(pro.getProperty("zh_name"));
		System.out.println(pro.getProperty("name"));
		System.out.println(pro.getProperty("expire_end_date"));
		System.out.println(pro.getProperty("license"));
		System.out.println(pro.getProperty("ip"));
	}
	
	/**
	 * 公共赋值
	 * @param input
	 */
	private void commonInput(RegistLicenseInputBean input) {
		// 公共赋值
		input.setOrg_work_code("");
		input.setOrg_user_id("admin");
		input.setOrg_term_no("10.1.1.195");
		input.setOrg_dept_id("110001");
		input.setPbl_code("00001");
		input.setOrg_channel_code("01");
		input.setOrg_rs_code("00");
		input.setOrg_srv_name("cm_RegistLicenseAction");
		input.setSubmit_type(SUBMIT_TYPE.APPLY);
	}
}
