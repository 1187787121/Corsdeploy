/**
 * Title: TestUserLoginInAction.java
 * File Description: ”√ªßµ«¬ººÏ≤È≤‚ ‘≥Ã–Ú
 * @copyright: 2014
 * @company: CORSWORK
 * @author: link
 * @version: 1.0
 * @date: 2014-12-26
 */
package com.wk.cd.test.common;

import com.wk.Controller;
import com.wk.beans.Injector;
import com.wk.cd.enu.SUBMIT_TYPE;
import com.wk.cd.system.us.action.UserLoginInAction;
import com.wk.cd.system.us.bean.UserLoginInInputBean;
import com.wk.db.DBSource;
import com.wk.db.Session;
import com.wk.test.TestCase;

/**
 * Class Description: ”√ªßµ«¬ººÏ≤È≤‚ ‘≥Ã–Ú
 * @author link
 */
public class TestUserLoginInAction
		extends TestCase {

	private DBSource db = DBSource.get();
	private Session session;
	private Injector inject = Controller.getInstance().getInjector();

	private UserLoginInAction sc = inject.getBean(UserLoginInAction.class);

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

	}

	@Override
	protected void tearDown() {
		session.rollbackAndResume();
		// session.commitAndResume();
	}

	/**
	 * Description:√‹¬Î÷ÿ÷√≤‚ ‘≥Ã–Ú
	 */
	public void testUserLoginIn() {
		UserLoginInInputBean input = new UserLoginInInputBean();
		
		input.setOrg_channel_code("01");
		input.setOrg_srv_name("us_UserLoginInAction");
		input.setOrg_rs_code("01");
		
		input.setOrg_user_id("admin");
		input.setUser_passwd("1111111");
		input.setSubmit_type(SUBMIT_TYPE.APPLY);
		
		sc.run(input);
			
		
	}
}
