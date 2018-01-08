/**
 * Title: TestCheckFile.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: xuph
 * @version: 1.0
 * @date: 2016��11��26��
 */
package com.wk.cd.test.build.ea.service;

import com.wk.Controller;
import com.wk.beans.Injector;
import com.wk.cd.build.ea.bean.DownFileBean;
import com.wk.cd.build.ea.service.ExcuteStoragePubService;
import com.wk.cd.common.cm.service.CommonService;
import com.wk.db.DBSource;
import com.wk.db.Session;
import com.wk.test.TestCase;

/**
 * Class Description: 
 * @author xuph
 */
public class TestCheckFile extends TestCase{
	private  DBSource db = DBSource.get();
	private  Session session;
	private Injector inject=Controller.getInstance().getInjector();
	private ExcuteStoragePubService sc = inject.getBean(ExcuteStoragePubService.class);
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
	
	public void testCheckFile(){
		DownFileBean down = new DownFileBean();
		String ce_server_name = "quandan";
		down.setFile_name("eeee.tar");
		down.setFile_path("/EnvXuph/ST201611260120/");
		//sc.checkFileToLocal(ce_server_name, down);
	}
}