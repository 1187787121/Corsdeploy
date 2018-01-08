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
import com.wk.cd.build.ea.action.UpdatePublishProgDetailAction;
import com.wk.cd.build.ea.bean.PhasePublishBean;
import com.wk.cd.build.ea.bean.SrvSocBean;
import com.wk.cd.build.ea.bean.UpdatePublishProgDetailInputBean;
import com.wk.cd.build.ea.dao.PgReleDaoService;
import com.wk.cd.build.ea.dao.PgReleStepDaoService;
import com.wk.cd.build.ea.info.PgReleInfo;
import com.wk.cd.build.ea.info.PgReleStepInfo;
import com.wk.cd.build.ea.info.UuParamInfo;
import com.wk.cd.common.cm.service.CommonService;
import com.wk.cd.enu.MODIFY_FLAG;
import com.wk.cd.enu.SUBMIT_TYPE;
import com.wk.cd.enu.YN_FLAG;
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
public class TestUpdatePublishProgDetailAction extends TestCase{
	private  DBSource db = DBSource.get();
	private  Session session;
	private Injector inject=Controller.getInstance().getInjector();
	private UpdatePublishProgDetailAction sc = inject.getBean(UpdatePublishProgDetailAction.class);
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
	
	public void testAddEnvProgBasicAction(){
		System.out.println("开始");
		UpdatePublishProgDetailInputBean input = new UpdatePublishProgDetailInputBean();
		commonInput(input);
		input.setEnv_name("card111");
		input.setProg_name("修改后的测试用的方案名");
		input.setProg_id("PG201612140021");
		List<PhasePublishBean> pub_phase_list = new ArrayList<PhasePublishBean>();
		PhasePublishBean bean = new PhasePublishBean();
		bean.setGen_flag(YN_FLAG.YES);
		SrvSocBean[]srv_soc = new SrvSocBean[2];
		SrvSocBean scb = new SrvSocBean();
		scb.setExe_server_name("10.1.1.220");
		scb.setExe_soc_name("220ssh");
		srv_soc[0] = scb;
		
		SrvSocBean scb1 = new SrvSocBean();
		scb1.setExe_soc_name("228ssh");
		srv_soc[1] = scb1;
		bean.setSrv_soc(srv_soc);
		pub_phase_list.add(bean);
		input.setPub_phase_list(pub_phase_list.toArray(new PhasePublishBean[pub_phase_list.size()]));
		List<PhasePublishBean> rol_phase_list = new ArrayList<PhasePublishBean>();
		PhasePublishBean rol_bean = new PhasePublishBean();
		rol_bean.setGen_flag(YN_FLAG.NO);
		SrvSocBean[]srv_soc1 = new SrvSocBean[2];
		SrvSocBean scb2 = new SrvSocBean();
		scb.setExe_server_name("10.1.1.220");
		scb.setExe_soc_name("220ssh");
		srv_soc1[0] = scb2;
		
		SrvSocBean scb3 = new SrvSocBean();
		scb1.setExe_soc_name("228ssh");
		srv_soc1[1] = scb3;
		rol_bean.setSrv_soc(srv_soc);
		rol_phase_list.add(rol_bean);
		input.setRol_phase_list(rol_phase_list.toArray(new PhasePublishBean[rol_phase_list.size()]));
		
	
		List<UuParamInfo> pub_param_list = new ArrayList<UuParamInfo> ();
		UuParamInfo tpb = new UuParamInfo();
		tpb.setParam_name("remote_dir");
		tpb.setParam_value("kek2,dirdir2");
		tpb.setParam_modify_flag(MODIFY_FLAG.YES);
		pub_param_list.add(tpb);
		
		UuParamInfo tpb1 = new UuParamInfo();
		tpb1.setParam_name("local_dir");
		tpb1.setParam_value("kek2,dirdir2");
		tpb1.setParam_modify_flag(MODIFY_FLAG.YES);
		pub_param_list.add(tpb1);
		input.setPub_param_list(pub_param_list);
		
		List<UuParamInfo> rol_param_list = new ArrayList<UuParamInfo> ();
		UuParamInfo tpb2 = new UuParamInfo();
		tpb2.setParam_name("remote_dir");
		tpb2.setParam_value("urururu2,kdkdkd2");
		tpb2.setParam_modify_flag(MODIFY_FLAG.YES);
		rol_param_list.add(tpb2);
		
		UuParamInfo tpb3 = new UuParamInfo();
		tpb3.setParam_name("local_dir");
		tpb3.setParam_value("owowowo2,ppqpqqp2");
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
		 
	}
	
	/**
	 * 公共赋值
	 * @param input
	 */
	private void commonInput(UpdatePublishProgDetailInputBean input) {
		// 公共赋值
		input.setOrg_channel_code("01");
		input.setOrg_work_code("");
		input.setOrg_srv_name("en_SavePublishProgDetailAction");
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
