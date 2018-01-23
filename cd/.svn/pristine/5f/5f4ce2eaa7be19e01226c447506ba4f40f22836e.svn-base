/**
 * Title: TestComonentTest.java
 * File Description: 
 * @copyright: 2017
 * @company: CORSWORK
 * @author: Administrator
 * @version: 1.0
 * @date: 2017Äê9ÔÂ1ÈÕ
 */
package com.wk.cd.test.module1;

import java.util.ArrayList;
import java.util.List;

import com.wk.Controller;
import com.wk.beans.Injector;
import com.wk.cd.enu.IMPL_TYPE;
import com.wk.cd.enu.PHASE_TYPE;
import com.wk.cd.enu.SUBMIT_TYPE;
import com.wk.cd.module1.action.ComponentTestAction;
import com.wk.cd.module1.bean.ComponentTestInputBean;
import com.wk.cd.module1.bean.ComponentTestOutputBean;
import com.wk.cd.module1.bean.StageSourceBean;
import com.wk.cd.module1.entity.Param;
import com.wk.cd.module1.entity.Phase;
import com.wk.cd.module1.entity.PhaseParam;
import com.wk.cd.module1.entity.Script;
import com.wk.db.DBSource;
import com.wk.db.Session;
import com.wk.test.TestCase;


/**
 * Class Description: 
 * @author Administrator
 */
public class TestComonentTest extends TestCase {
	private DBSource db = DBSource.get();
	private Session session;
	private Injector inject = Controller.getInstance().getInjector();
	private ComponentTestAction sc = inject.getBean(ComponentTestAction.class);

	@Override
	protected void setUpOnce() {
		session = db.openSession();
		session.beginTransaction();
	}

	@Override
	protected void tearDownOnce() {
		session.rollback();
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
		session.commitAndResume();
	}

	
	public void test(){
		ComponentTestInputBean input = new ComponentTestInputBean();
		input.setOrg_dept_id("110001");
		input.setOrg_user_id("admin");
		input.setOrg_term_no("10.1.1.101");
		input.setOrg_srv_name("mo_ComponentTestAction");
		input.setOrg_rs_code("00");
		input.setOrg_channel_code("01");
		input.setSubmit_type(SUBMIT_TYPE.APPLY);
		
		Phase phase = new Phase();
		phase.setImpl_type(IMPL_TYPE.SHELL);
		phase.setScript(getScript());
		phase.setPhase_type(PHASE_TYPE.COMPONENT);
		phase.setPhase_name("testJava");
		phase.setPhase_no(1);
		phase.setSrv_soc(getNode_soc());
		input.setPhase(phase);
		
//		Param param = new Param();
//		param.setParam_bk_desc("aaaaa");
//		param.setParam_name("aaa");
//		param.setParam_value("aa");
		input.setParams(null);
		
		input.setId("121212");
		ComponentTestOutputBean output = sc.run(input);
		System.out.println(output.getLog_file_path());
	}
	
//	private Script getScript1(){
//		Script script = new Script();
//		
//		String [] sqls = new String[2];
//		sqls[0] = "select * from DT_SOURCE where protocol_type = 1";
//		sqls[1] = "select * from bs_node";
//		script.setCmds(sqls);
//		return script;
//	}
	
//	private Script getScript(){
//		Script script = new Script();
//		
//		String[] cmds = new String[5];
//		cmds[0]="for /l %%i in (1,1,360) do (";
//		cmds[1]="	call echo DATE: %%DATE%% %%TIME%% %%i >> F:/OneHourTest.txt";
//		cmds[2]="	ping 1.1.1.1 -n 1 -w 10000 > nul";
//		cmds[3]=")";
//		cmds[4]="echo F:/zenmeban1.txt";
//		script.setCmds(cmds);
//		return script;
//	}
	
	private List<StageSourceBean> getNode_soc() {
		List<StageSourceBean> srv_soc = new ArrayList<StageSourceBean>();
		StageSourceBean soc = new StageSourceBean();
		
		soc.setExe_ip("10.1.1.227");
		soc.setExe_soc_name("227agent");
		
		srv_soc.add(soc);
		return srv_soc;
	}

	private Script getScript(){
		Script script = new Script();
		
		String[] cmds = new String[1];
//		cmds[0]="System.out.println(\"Hello world\");";
		cmds[0]="echo end";
		script.setCmds(cmds);
		return script;
	}
	
//	private List<StageSourceBean> getSoc(){
//		List<StageSourceBean> list = new ArrayList<StageSourceBean>();
//		StageSourceBean ss = new StageSourceBean();
//		ss.setExe_ip("10.1.1.85");
//		ss.setExe_soc_name("85agent");
//		list.add(ss);
//		return list;
//	}
}
