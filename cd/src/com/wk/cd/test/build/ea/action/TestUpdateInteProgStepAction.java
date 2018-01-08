/**
 * Title: TestAddInteProgStepAction.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: chiss
 * @version: 1.0
 * @date: 2016��11��12��
 */
package com.wk.cd.test.build.ea.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.Controller;
import com.wk.beans.Injector;
import com.wk.cd.build.ea.action.UpdateInteProgStepAction;
import com.wk.cd.build.ea.bean.EnvProgStepBean;
import com.wk.cd.build.ea.bean.InteStepBean;
import com.wk.cd.build.ea.bean.TargetPackageBean;
import com.wk.cd.build.ea.bean.UpdateInteProgStepInputBean;
import com.wk.cd.build.ea.info.UuFilelistInfo;
import com.wk.cd.enu.STEP_TYPE;
import com.wk.cd.common.cm.service.CommonService;
import com.wk.cd.enu.SUBMIT_TYPE;
import com.wk.db.DBSource;
import com.wk.db.Session;
import com.wk.test.TestCase;
import com.wk.util.JaDate;
import com.wk.util.JaDateTime;
import com.wk.util.JaTime;

/**
 * Class Description: 
 * @author chiss
 */
public class TestUpdateInteProgStepAction extends TestCase{
	private  DBSource db = DBSource.get();
	private  Session session;
	private Injector inject=Controller.getInstance().getInjector();
	private UpdateInteProgStepAction sc = inject.getBean(UpdateInteProgStepAction.class);
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
	
	public void testUpdateInteProgStepAction(){
		UpdateInteProgStepInputBean input = new UpdateInteProgStepInputBean();
		commonInput(input);
		input.setProg_id("PG201611110050");
		int step_id = 1;

//		input.setStep_id(1);
		EnvProgStepBean prog_step_bean = new EnvProgStepBean();
		prog_step_bean.setStep_expl("�汾����");
		prog_step_bean.setSoc_name("����Դ������");
		prog_step_bean.setCode_server_name("��������2");
//		����λΪ�汾ʱ
//		prog_step_bean.setStep_type(STEP_TYPE.VERSION);
//		prog_step_bean.setCode_soc_name("����Դ2");
//		prog_step_bean.setCode_server_name("����Դ������2");
//		prog_step_bean.setCode_bk_dir("����ԴĿ¼2");
		
//		����λΪ�ű�ʱ
//		prog_step_bean.setStep_type(STEP_TYPE.SCRIPT);
//		prog_step_bean.setStep_bk_script("�ű�����");
		
//		����Ϊ����ʱ
//		prog_step_bean.setStep_type(STEP_TYPE.COMPILE);
//		prog_step_bean.setCompile_type(COMPILE_TYPE.ANT);
//		prog_step_bean.setEnv_variable("������������");
//		prog_step_bean.setCompile_param("�����������");
		
//		����Ϊ���ʱ
		prog_step_bean.setStep_type(STEP_TYPE.STORAGE);
		prog_step_bean.setTar_soc_name("����Դ2");
		prog_step_bean.setTar_server_name("������2");
		prog_step_bean.setTar_bk_dir("Ŀ¼2");
		List<TargetPackageBean> tar_package_list = new ArrayList<TargetPackageBean>();
		List<UuFilelistInfo> file_list = new ArrayList<UuFilelistInfo>();
		UuFilelistInfo filis = new UuFilelistInfo();
		filis.setFile_name("�ļ���2");
		filis.setFile_path("�ļ�·��2");
		filis.setFile_type("�ļ�����2");
		
		file_list.add(filis);
		TargetPackageBean bean = new TargetPackageBean();
		bean.setPackage_name("�ļ���������");
		bean.setFile_list(file_list);
		tar_package_list.add(bean);
		prog_step_bean.setTar_package_list(tar_package_list);
//		input.setProg_step_bean(prog_step_bean);
		List<InteStepBean> list = new ArrayList<InteStepBean>();
		InteStepBean step_bean = new InteStepBean();
		step_bean.setProg_step_bean(prog_step_bean);
		step_bean.setStep_id(1);
		list.add(step_bean);

		sc.run(input);
	}
	
	/**
	 * ������ֵ
	 * @param input
	 */
	private void commonInput(UpdateInteProgStepInputBean input) {
		// ������ֵ
		input.setOrg_channel_code("01");
		input.setOrg_work_code("");
		input.setOrg_srv_name("en_AddEnvironmentAction");
		input.setOrg_rs_code("00");
		input.setRemote_ip("10.1.1.220");
		input.setServer_port(21);
		input.setOrg_user_id("admin");
		input.setOrg_term_no("10.1.1.195");
		input.setOrg_dept_id("110001");
		input.setPbl_code("00001");
		input.setPend_srv_name("add_work_action");
		input.setPend_rs_code("html");
		input.setPendwk_bk_expl("��������������������Ϣ");
		input.setSubmit_type(SUBMIT_TYPE.APPLY);
		JaDateTime curdt = cmsvc.getCurrentDateTime();
		input.setDtbs_bk_date(JaDate.valueOf(curdt.dateValue()));
		input.setDtbs_bk_time(JaTime.valueOf(curdt.timeValue()));

	}
}
