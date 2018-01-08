/**
 * Title: TestSavePublishProgDetailAction.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: zhangj
 * @version: 1.0
 * @date: 2016年11月21日
 */
package com.wk.cd.test.build.ea.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.Controller;
import com.wk.beans.Injector;
import com.wk.cd.build.ea.action.AddPublishProgDetailAction;
import com.wk.cd.build.ea.bean.AddPublishProgDetailInputBean;
import com.wk.cd.build.ea.dao.PgReleDaoService;
import com.wk.cd.build.ea.dao.PgReleStepDaoService;
import com.wk.cd.common.cm.service.CommonService;
import com.wk.cd.enu.IMPL_TYPE;
import com.wk.cd.enu.MODIFY_FLAG;
import com.wk.cd.enu.PARAM_TYPE;
import com.wk.cd.enu.PHASE_TYPE;
import com.wk.cd.enu.PROG_TYPE;
import com.wk.cd.enu.PROTOCOL_TYPE;
import com.wk.cd.enu.SUBMIT_TYPE;
import com.wk.cd.module1.bean.StageSourceBean;
import com.wk.cd.module1.entity.Param;
import com.wk.cd.module1.entity.Phase;
import com.wk.cd.module1.entity.PhaseParam;
import com.wk.cd.module1.entity.Program;
import com.wk.cd.module1.entity.Script;
import com.wk.cd.module1.xml1.XmlWriter;
import com.wk.db.DBSource;
import com.wk.db.Session;
import com.wk.lang.Inject;
import com.wk.test.TestCase;
import com.wk.util.JaDate;
import com.wk.util.JaDateTime;
import com.wk.util.JaTime;

/**
 * Class Description: 
 * @author Zhangj
 */
public class TestAddPublishProgDetailAction extends TestCase{
	private  DBSource db = DBSource.get();
	private  Session session;
	private Injector inject=Controller.getInstance().getInjector();
	private AddPublishProgDetailAction sc = inject.getBean(AddPublishProgDetailAction.class);
	private CommonService cmsvc = inject.getBean(CommonService.class);
	@Inject
	private PgReleDaoService pgReleDaoService;
	@Inject
	private PgReleStepDaoService pgReleStepDaoService;
	
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
		session.commit();
	}

	@Override
	protected void tearDownOnce()
		throws Exception {
//		session.rollback();
	}
	
	
	public void test(){
		System.out.println("开始");
		AddPublishProgDetailInputBean input = new AddPublishProgDetailInputBean();
		commonInput(input);
		Program program = new Program();
		program.setProg_id("PG201702150218");
		program.setProg_name("测试发发方案");
		program.setPub_template_name("发布模板");
		program.setVer_server_name("227server");
		program.setVer_soc_name("240SVN");
		program.setCode_bk_dir("/CorsManager/version");
		program.setProg_type(PROG_TYPE.PUBLISH);
		List<Phase> phase_list = new ArrayList<Phase>();
		Phase phase = new Phase();
		phase.setPhase_no(1);
		phase.setBk_desc("创建目录，如果目录不存在就创建");
		phase.setPhase_name("Alpha_创建目录");
		phase.setComponent_id("COMP201702150215");
		phase.setImpl_type(IMPL_TYPE.SHELL);
		phase.setPhase_type(PHASE_TYPE.MANUAL);
		phase.setScript(new Script("defulat", new String[] {
				"echo hello" }));
		List<StageSourceBean> srv_soc = new ArrayList<StageSourceBean>();
		StageSourceBean source = new StageSourceBean();
		source.setExe_ip("10.0.0.220");
		source.setExe_soc_name("227ssh");
		source.setVer_ip("10.1.1.240");
		source.setVer_soc_name("240SVN");
		source.setExe_protocol_type(PROTOCOL_TYPE.SSH);
		source.setVer_protocol_type(PROTOCOL_TYPE.SVN);
		srv_soc.add(source);
		phase.setSrv_soc(srv_soc);
		List<Param> param_list = new ArrayList<Param>();
		param_list.add(new Param("dir", "上传目录", null, "upload_dir", false));
		phase.setParam_list(param_list);
		phase_list.add(phase);
		program.setPhase_list(phase_list);
		List<PhaseParam> phase_param_list = new ArrayList<PhaseParam>();
		phase_param_list.add(new PhaseParam("dir", "上传目录", null, "upload_dir", false, PARAM_TYPE.PDDPARAM, null, MODIFY_FLAG.NO));
		program.setParam_list(phase_param_list);
		XmlWriter.write(program);
		input.setPub_program(program);
		input.setEnv_name("new_env");
		sc.run(input);
		System.out.println("结束");
		
	}
	/*public void testAddEnvProgBasicAction(){
		System.out.println("开始");
		AddPublishProgDetailInputBean input = new AddPublishProgDetailInputBean();
		commonInput(input);
		input.setEnv_name("card111");
		input.setProg_name("测试用的方案名");
		List<PhasePublishBean> pub_phase_list = new ArrayList<PhasePublishBean>();
		PhasePublishBean bean = new PhasePublishBean();
		bean.setGen_flag(YN_FLAG.YES);
		SrvSocBean[]srv_soc = new SrvSocBean[2];
		SrvSocBean scb = new SrvSocBean();
		scb.setExe_server_name("10.1.1.220");
		scb.setExe_soc_name("220telnet");
		srv_soc[0] = scb;
		
		SrvSocBean scb1 = new SrvSocBean();
		scb1.setExe_soc_name("228telnet");
		srv_soc[1] = scb1;
		bean.setSrv_soc(srv_soc);
		pub_phase_list.add(bean);
		input.setPub_phase_list(pub_phase_list.toArray(new PhasePublishBean[pub_phase_list.size()]));
		List<PhasePublishBean> rol_phase_list = new ArrayList<PhasePublishBean>();
		PhasePublishBean rol_bean = new PhasePublishBean();
		rol_bean.setGen_flag(YN_FLAG.YES);
		SrvSocBean[]srv_soc1 = new SrvSocBean[2];
		SrvSocBean scb2 = new SrvSocBean();
		scb2.setExe_server_name("10.1.1.220");
		scb2.setExe_soc_name("220telnet");
		srv_soc1[0] = scb2;
		
		SrvSocBean scb3 = new SrvSocBean();
		scb1.setExe_soc_name("228telnet");
		srv_soc1[1] = scb3;
		rol_bean.setSrv_soc(srv_soc);
		rol_phase_list.add(rol_bean);
		input.setRol_phase_list(rol_phase_list.toArray(new PhasePublishBean[rol_phase_list.size()]));
		
	
		List<UuParamInfo> pub_param_list = new ArrayList<UuParamInfo> ();
		UuParamInfo tpb = new UuParamInfo();
		tpb.setParam_name("remote_dir");
		tpb.setParam_value("kek,dirdir");
		tpb.setParam_modify_flag(MODIFY_FLAG.YES);
		pub_param_list.add(tpb);
		
		UuParamInfo tpb1 = new UuParamInfo();
		tpb1.setParam_name("local_dir");
		tpb1.setParam_value("kek,dirdir");
		tpb1.setParam_modify_flag(MODIFY_FLAG.YES);
		
		pub_param_list.add(tpb1);
		input.setPub_param_list(pub_param_list);
		
		List<UuParamInfo> rol_param_list = new ArrayList<UuParamInfo> ();
		UuParamInfo tpb2 = new UuParamInfo();
		tpb2.setParam_name("remote_dir");
		tpb2.setParam_value("urururu,kdkdkd");
		tpb2.setParam_modify_flag(MODIFY_FLAG.YES);
		rol_param_list.add(tpb2);
		
		UuParamInfo tpb3 = new UuParamInfo();
		tpb3.setParam_name("local_dir");
		tpb3.setParam_value("owowowo,ppqpqqp");
		tpb3.setParam_modify_flag(MODIFY_FLAG.YES);
		rol_param_list.add(tpb3);
		input.setRol_param_list(rol_param_list);
		input.setPub_template_name("pub");
		
		input.setRol_template_name("rol");
		try {
			sc.run(input);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		PgReleInfo pri = pgReleDaoService.getInfoByKey("PG201611250019");
		PgReleStepInfo ps = new PgReleStepInfo();
		ps.setProg_id("PG201611250019");
		ps.setTemplate_name("pub");
		ps.setPhase_id(0);
		PgReleStepInfo psi = pgReleStepDaoService.getInfoByKey(ps);
		System.out.println(pri);
		System.out.println(psi);
		 
	}*/
	
	/**
	 * 公共赋值
	 * @param input
	 */
	private void commonInput(AddPublishProgDetailInputBean input) {
		// 公共赋值
		input.setOrg_channel_code("01");
		input.setOrg_work_code("");
		input.setOrg_srv_name("mo_AddComponentAction");
		input.setOrg_rs_code("00");
		input.setOrg_user_id("admin");
		input.setRemote_ip("10.1.1.220");
		input.setServer_port(21);
		input.setOrg_term_no("10.1.1.195");
		input.setOrg_dept_id("110001");
		input.setPbl_code("00001");
		input.setPend_srv_name("add_work_action");
		input.setPend_rs_code("html");
		input.setPendwk_bk_expl("保存发布方案详细信息");
		input.setSubmit_type(SUBMIT_TYPE.APPLY);
		JaDateTime curdt = cmsvc.getCurrentDateTime();
		input.setDtbs_bk_date(JaDate.valueOf(curdt.dateValue()));
		input.setDtbs_bk_time(JaTime.valueOf(curdt.timeValue()));

	}
}
