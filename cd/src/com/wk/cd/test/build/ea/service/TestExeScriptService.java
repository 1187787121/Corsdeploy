/**
 * Title: TestExeScriptService.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: xuph
 * @version: 1.0
 * @date: 2016��12��12��
 */
package com.wk.cd.test.build.ea.service;

import java.util.List;

import com.wk.Controller;
import com.wk.beans.Injector;
import com.wk.cd.build.ea.dao.BuildScriptDaoService;
import com.wk.cd.build.ea.dao.BuildScriptExeDaoService;
import com.wk.cd.build.ea.info.BuildScriptInfo;
import com.wk.cd.build.ea.service.ExcuteStoragePubService;
import com.wk.cd.build.ea.service.ExeScriptPublicService;
import com.wk.cd.common.cm.service.CommonService;
import com.wk.cd.enu.SCRIPT_TYPE;
import com.wk.db.DBSource;
import com.wk.db.Session;
import com.wk.test.TestCase;

/**
 * Class Description: 
 * @author xuph
 */
public class TestExeScriptService extends TestCase{
	private  DBSource db = DBSource.get();
	private  Session session;
	private Injector inject=Controller.getInstance().getInjector();
	private BuildScriptExeDaoService srv = inject.getBean(BuildScriptExeDaoService.class);
	private BuildScriptDaoService srrr = inject.getBean(BuildScriptDaoService.class);
	private ExcuteStoragePubService sc = inject.getBean(ExcuteStoragePubService.class);
	private ExeScriptPublicService ess = inject.getBean(ExeScriptPublicService.class);
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
	
	public void testSrv(){
		int time_used = 25;
		String instance_id = "f2a500ee-e29d-4989-a2c0-5ef67b487a87";
		long scirpt_bk_seq = 3;
		String work_id = "TK201612130041";
		int exe_bk_no =1;
		String exec_text ="";
		List<BuildScriptInfo> list = srrr.getScriptByIdAndType(work_id, SCRIPT_TYPE.OPERATION);
		System.out.println(list.size());
		for (BuildScriptInfo dd : list) {
			System.out.println(dd.getExelog_bk_path());
		}
//		srv.updateCostTmBykey(time_used, instance_id, exe_bk_no);
//		srv.updateExeEndBkTmByKey(JaDateTime.now(), EXE_STATUS.EXECUTED, EXE_RESULT.SUCCESS, instance_id, exe_bk_no);
//		srv.updateExeMsgByKey(exec_text, instance_id, exe_bk_no);
//		srv.updateExeStarBkTmByKey(JaDateTime.now(), EXE_STATUS.EXECUTED, instance_id, exe_bk_no);
//		srrr.updateScriptStatusInfoByKey(JaDateTime.now(), EXE_STATUS.EXECUTED, EXE_RESULT.FAIL, work_id, scirpt_bk_seq, SCRIPT_TYPE.OPERATION);
//		srrr.updateScriptTimeByKey(24, work_id, scirpt_bk_seq, SCRIPT_TYPE.OPERATION);
	}
	
	public void testExeScript(){
//		ess.exeScript("TK0000007000", 1, SCRIPT_TYPE.OPERATION);
		 
	}
	
}
