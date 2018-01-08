/**
 * Title: TestViSysEnvServerDaoService.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2016Äê10ÔÂ29ÈÕ
 */
package com.wk.cd.test.build.view.dao;

import com.wk.Controller;
import com.wk.beans.Injector;
import com.wk.cd.build.view.dao.ViSysEnvServerQuery;
import com.wk.cd.build.view.info.ViSysEnvServerView;
import com.wk.db.DBIterator;
import com.wk.db.DBSource;
import com.wk.db.Session;
import com.wk.test.TestCase;

/**
 * Class Description:
 * @author HT
 */
public class TestViSysEnvServerQuery
		extends TestCase {
	private DBSource db = DBSource.get();

	private Session session;

	private Injector inject = Controller.getInstance().getInjector();

	private ViSysEnvServerQuery sc = inject.getBean(ViSysEnvServerQuery.class);

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
		DBIterator<ViSysEnvServerView> viewIterator = sc.queryAllView();

		try {
			while (viewIterator.hasNext()) {
				ViSysEnvServerView viewInfo = viewIterator.next();
				System.out.println(viewInfo.getSys_cn_name() + "---" + viewInfo.getEnv_cn_name() + "---" + viewInfo.getServer_cn_name() + "\n");
			}
		} finally {
			viewIterator.close();
		}
	}
}
