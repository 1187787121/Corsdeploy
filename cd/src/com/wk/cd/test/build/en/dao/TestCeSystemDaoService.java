/**
 * Title: TestCeSystemDaoService.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2016Äê10ÔÂ29ÈÕ
 */
package com.wk.cd.test.build.en.dao;

import com.wk.Controller;
import com.wk.beans.Injector;
import com.wk.cd.build.en.dao.CeSystemDaoService;
import com.wk.cd.build.en.info.CeSystemInfo;
import com.wk.db.DBSource;
import com.wk.db.Session;
import com.wk.test.TestCase;

/**
 * Class Description: 
 * @author HT
 */
public class TestCeSystemDaoService extends TestCase{
	private DBSource db = DBSource.get();

	private Session session;

	private Injector inject = Controller.getInstance().getInjector();

	private CeSystemDaoService sc = inject.getBean(CeSystemDaoService.class);

	@Override
	protected void setUpOnce() {
		session = db.openSession();
		session.beginTransaction();
	}

	@Override
	protected void setUp() {
	}

	@Override
	protected void tearDown() {
	}

	@Override
	protected void tearDownOnce() {
		session.rollback();
		session.close();
	}
	
	public void testQueryAllView() {
		CeSystemInfo sysInfo = new CeSystemInfo();
		sysInfo.setSys_name("card");
		sysInfo= sc.getInfoByKey(sysInfo);
		System.out.println(sysInfo.getSys_cn_name());
	}
}
