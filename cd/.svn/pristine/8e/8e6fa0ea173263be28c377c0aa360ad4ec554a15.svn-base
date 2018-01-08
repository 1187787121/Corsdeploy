/**
 * Title: TestAddInteProgStepAction.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: chiss
 * @version: 1.0
 * @date: 2016年11月12日
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
		prog_step_bean.setStep_expl("版本测试");
		prog_step_bean.setSoc_name("数据源名测试");
		prog_step_bean.setCode_server_name("服务器名2");
//		类型位为版本时
//		prog_step_bean.setStep_type(STEP_TYPE.VERSION);
//		prog_step_bean.setCode_soc_name("数据源2");
//		prog_step_bean.setCode_server_name("数据源服务器2");
//		prog_step_bean.setCode_bk_dir("数据源目录2");
		
//		类型位为脚本时
//		prog_step_bean.setStep_type(STEP_TYPE.SCRIPT);
//		prog_step_bean.setStep_bk_script("脚本测试");
		
//		类型为编译时
//		prog_step_bean.setStep_type(STEP_TYPE.COMPILE);
//		prog_step_bean.setCompile_type(COMPILE_TYPE.ANT);
//		prog_step_bean.setEnv_variable("环境变量测试");
//		prog_step_bean.setCompile_param("编译参数测试");
		
//		类型为入库时
		prog_step_bean.setStep_type(STEP_TYPE.STORAGE);
		prog_step_bean.setTar_soc_name("数据源2");
		prog_step_bean.setTar_server_name("服务器2");
		prog_step_bean.setTar_bk_dir("目录2");
		List<TargetPackageBean> tar_package_list = new ArrayList<TargetPackageBean>();
		List<UuFilelistInfo> file_list = new ArrayList<UuFilelistInfo>();
		UuFilelistInfo filis = new UuFilelistInfo();
		filis.setFile_name("文件名2");
		filis.setFile_path("文件路径2");
		filis.setFile_type("文件类型2");
		
		file_list.add(filis);
		TargetPackageBean bean = new TargetPackageBean();
		bean.setPackage_name("文件包名测试");
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
	 * 公共赋值
	 * @param input
	 */
	private void commonInput(UpdateInteProgStepInputBean input) {
		// 公共赋值
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
		input.setPendwk_bk_expl("测试新增环境关联表信息");
		input.setSubmit_type(SUBMIT_TYPE.APPLY);
		JaDateTime curdt = cmsvc.getCurrentDateTime();
		input.setDtbs_bk_date(JaDate.valueOf(curdt.dateValue()));
		input.setDtbs_bk_time(JaTime.valueOf(curdt.timeValue()));

	}
}
