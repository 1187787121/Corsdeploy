/**
 * Title: TestAddCompontAction.java
 * File Description: 
 * @copyright: 2017
 * @company: CORSWORK
 * @author: playg
 * @version: 1.0
 * @date: 2017��11��22��
 */
package com.wk.cd.test.module1;

import java.util.ArrayList;
import java.util.List;

import com.wk.Controller;
import com.wk.beans.Injector;
import com.wk.cd.enu.IMPL_TYPE;
import com.wk.cd.module1.action.AddComponentAction;
import com.wk.cd.module1.bean.AddComponentInputBean;
import com.wk.cd.module1.entity.Component;
import com.wk.cd.module1.entity.Script;
import com.wk.cd.module1.enu.COMPONENT_PURPOSE;
import com.wk.cd.module1.enu.COMPONENT_SOURCE;
import com.wk.cd.test.common.CommonData;
import com.wk.db.DBSource;
import com.wk.db.Session;
import com.wk.test.TestCase;

/**
 * Class Description: 
 * @author playg
 */
public class TestAddCompontAction extends TestCase{
	private DBSource db = DBSource.get();
	private Session session;
	private Injector inject = Controller.getInstance().getInjector();
	private AddComponentAction addcpt = inject.getBean(AddComponentAction.class);
	
	@Override
	protected void setUpOnce() {
		session = db.openSession();
		session.beginTransaction();
	}

	@Override
	protected void tearDownOnce() {
		session.rollback();
		session.close();
	}

	@Override
	protected void setUp() {
		if(session==null){
			session = db.openSession();
			session.beginTransaction();
		}
	}

	@Override
	protected void tearDown() {
//		session.rollbackAndResume();
		 session.commitAndResume();
	}
	
	public void testAddComponentAction(){
		AddComponentInputBean input = new AddComponentInputBean();
		CommonData.commonInput(input);
		Component compt = new Component();
		compt.setBk_desc("");
		compt.setImpl_type(IMPL_TYPE.SHELL);
		compt.setCn_name("����ű�����");
		compt.setBk_desc("���ű������������");
		compt.setComponent_source(COMPONENT_SOURCE.INPUT);//�����Դ:INPUT
		List<Script> script_list = new ArrayList<Script>();
		Script script = new Script();
		script.setCmds(new String[]{"echo \"line1\"","echo\"line2\""});
		script.setScript_type("linux");//�ű������ǣ�linux
		script_list.add(script);
		compt.setScript_list(script_list);
//		compt.setComponent_purposes(new int[]{1});//�����;�ǣ�1 ----->Ӧ�÷���
//		compt.setTag_list(new String[]{"linux"});//��ǩ�����б���linux
		input.setComponent(compt);
		
		addcpt.run(input);
	}

	
}
