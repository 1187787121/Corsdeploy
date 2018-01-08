/**
 * Title: TestBuildTaskLogGenService.java
 * File Description: 测试构建日志生成服务类
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2016年12月13日
 */
package com.wk.cd.test.build.ea.service;

import com.wk.Controller;
import com.wk.beans.Injector;
import com.wk.cd.build.ea.service.BuildTaskLogGenService;
import com.wk.db.DBSource;
import com.wk.db.Session;
import com.wk.test.TestCase;

/**
 * Class Description: 测试构建日志生成服务类
 * @author Xul
 */
public class TestBuildTaskLogGenService extends TestCase{
	private DBSource db = DBSource.get();
	private Session session;
	private Injector inject=Controller.getInstance().getInjector();
	private BuildTaskLogGenService sc = inject.getBean(BuildTaskLogGenService.class);
	
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
	
	public void test(){
		sc.generateBuildTaskLog("TK201612110001");
	}
}
