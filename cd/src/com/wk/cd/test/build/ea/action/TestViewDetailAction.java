/**
 * Title: TestViewConfigModAction.java
 * File Description: �����޸������ļ���ط���
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2016��11��12��
 */
package com.wk.cd.test.build.ea.action;

import com.wk.Controller;
import com.wk.beans.Injector;
import com.wk.cd.build.ea.action.ViewProgAction;
import com.wk.cd.build.ea.bean.ViewProgInputBean;
import com.wk.cd.build.ea.bean.ViewProgOutputBean;
import com.wk.db.DBSource;
import com.wk.db.Session;
import com.wk.test.TestCase;

/**
 * Class Description: �����޸������ļ���ط���
 * @author Xul
 */
public class TestViewDetailAction extends TestCase{
	
	private  DBSource db = DBSource.get();
	private  Session session;
	private Injector inject=Controller.getInstance().getInjector();
	private ViewProgAction sc = inject.getBean(ViewProgAction.class);
	
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

//	
	//���Բ鿴�������޸ġ�ɾ���ļ��б�
	public void testQueryModList(){
		ViewProgInputBean input = new ViewProgInputBean();
		input.setProg_id("PG201611230015");
		try {
			ViewProgOutputBean output = sc.queryEnvProgInfo(input);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		
	}

}
