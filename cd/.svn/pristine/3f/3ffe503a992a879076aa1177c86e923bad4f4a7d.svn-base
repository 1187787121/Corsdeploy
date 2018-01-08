/**
 * Title: TestGenerateExcelListService.java
 * File Description: 
 * @copyright: 2017
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2017Äê2ÔÂ14ÈÕ
 */
package com.wk.cd.test.build.ea.service;

import com.wk.Controller;
import com.wk.beans.Injector;
import com.wk.cd.build.ea.service.GenerateExcelListService;
import com.wk.db.DBSource;
import com.wk.db.Session;
import com.wk.test.TestCase;

/**
 * Class Description: 
 * @author Xul
 */
public class TestGenerateExcelListService extends TestCase{
	private  DBSource db = DBSource.get();
	private  Session session;
	private Injector inject=Controller.getInstance().getInjector();
	private GenerateExcelListService sc = inject.getBean(GenerateExcelListService.class);
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
	
	public void test(){
//		sc.generateExcelList("ST201702090003", TASK_ALL_TYPE.STORAGE);
		//sc.generateList("817625d2c0154a809265185bc8b0a4ad","D:/rworkbook.xls");
	}
}
