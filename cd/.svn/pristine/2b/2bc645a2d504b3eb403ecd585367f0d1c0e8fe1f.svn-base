/**
 * Title: TestEnvirPublicService.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: xuph
 * @version: 1.0
 * @date: 2016Äê11ÔÂ2ÈÕ
 */
package com.wk.cd.test.build.en.service;

import com.wk.Controller;
import com.wk.beans.Injector;
import com.wk.cd.build.en.service.EnvironmentPublicService;
import com.wk.cd.enu.DT_RANGE;
import com.wk.db.DBSource;
import com.wk.db.Session;
import com.wk.test.TestCase;

/**
 * Class Description: 
 * @author xuph
 */
public class TestEnvirPublicService extends TestCase{
	private  DBSource db = DBSource.get();
	private  Session session;
	private Injector inject=Controller.getInstance().getInjector();
	private EnvironmentPublicService sc = inject.getBean(EnvironmentPublicService.class);
	
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
	
	public void testgenernateArray(){
		DT_RANGE dt_range = null;
		String[] ele_types = new String[]{"1","2","3"};
		sc.checkDtRangeIsNotEmpty(dt_range, ele_types);
	}
 
}
