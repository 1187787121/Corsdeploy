/**
 * Title: TestEditBuildTaskPhaseAction.java
 * File Description: 
 * @copyright: 2017
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2017��2��21��
 */
package com.wk.cd.test.build.ea.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.Controller;
import com.wk.beans.Injector;
import com.wk.cd.build.ea.action.EditBuildTaskPhaseAction;
import com.wk.cd.build.ea.bean.EditBuildTaskPhaseInputBean;
import com.wk.cd.build.ea.bean.PhasePublishBean;
import com.wk.cd.build.ea.bean.SrvSocBean;
import com.wk.cd.common.cm.service.CommonService;
import com.wk.cd.enu.SUBMIT_TYPE;
import com.wk.cd.enu.YN_FLAG;
import com.wk.cd.enu.IMPL_TYPE;
import com.wk.cd.module1.bean.StageSourceBean;
import com.wk.cd.module1.entity.Phase;
import com.wk.db.DBSource;
import com.wk.db.Session;
import com.wk.test.TestCase;
import com.wk.util.JaDate;
import com.wk.util.JaDateTime;
import com.wk.util.JaTime;

/**
 * Class Description: ���Ա༭��������׶���Ϣ
 * @author Xul
 */
public class TestEditBuildTaskPhaseAction extends TestCase{
	
	private  DBSource db = DBSource.get();
	private  Session session;
	private Injector inject=Controller.getInstance().getInjector();
	private EditBuildTaskPhaseAction sc = inject.getBean(EditBuildTaskPhaseAction.class);
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
	
	public void testEditBuildTaskPhaseAction(){
		EditBuildTaskPhaseInputBean input = new EditBuildTaskPhaseInputBean();
		commonInput(input);
		input.setWork_id("TK201712270021");
		Phase phase = new Phase();
		phase.setBk_desc("aaaa");
		phase.setPhase_name("���Բ��Բ���");
		List<StageSourceBean> srv_soc = new ArrayList<StageSourceBean>();
		StageSourceBean bean = new StageSourceBean();
		bean.setExe_server_name("aaaa");
		srv_soc.add(bean);
		phase.setSrv_soc(srv_soc);
		input.setPhase(phase);
		sc.run(input);
	}
	
	/**
	 * ������ֵ
	 * @param input
	 */
	private void commonInput(EditBuildTaskPhaseInputBean input) {
		// ������ֵ
		input.setOrg_channel_code("01");
		input.setOrg_work_code("");
		input.setOrg_srv_name("en_AddEnvironmentAction");
		input.setOrg_rs_code("00");
		input.setOrg_user_id("admin");
		input.setOrg_term_no("10.1.1.195");
		input.setOrg_dept_id("110001");
		input.setPbl_code("00001");
		input.setPend_srv_name("add_work_action");
		input.setPend_rs_code("html");
		input.setPendwk_bk_expl("ɾ��������������");
		input.setSubmit_type(SUBMIT_TYPE.APPLY);
		JaDateTime curdt = cmsvc.getCurrentDateTime();
		input.setDtbs_bk_date(JaDate.valueOf(curdt.dateValue()));
		input.setDtbs_bk_time(JaTime.valueOf(curdt.timeValue()));

	}
}
