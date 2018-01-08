/**
 * Title: TestViewProgAction.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: chiss
 * @version: 1.0
 * @date: 2016年11月15日
 */
package com.wk.cd.test.build.ea.action;

import java.util.List;

import com.wk.Controller;
import com.wk.beans.Injector;
import com.wk.cd.build.ea.action.ViewProgAction;
import com.wk.cd.build.ea.bean.EnvProgStepBean;
import com.wk.cd.build.ea.bean.TargetPackageBean;
import com.wk.cd.build.ea.bean.ViewProgInputBean;
import com.wk.cd.build.ea.bean.ViewProgOutputBean;
import com.wk.cd.build.ea.info.PgProgramInfo;
import com.wk.cd.enu.PROG_TYPE;
import com.wk.cd.common.cm.service.CommonService;
import com.wk.cd.common.util.Assert;
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
public class TestViewProgAction extends TestCase{
	private  DBSource db = DBSource.get();
	private  Session session;
	private Injector inject=Controller.getInstance().getInjector();
	private ViewProgAction sc = inject.getBean(ViewProgAction.class);
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
	
	public void testqueryEnvProgInfo(){
		ViewProgInputBean input = new ViewProgInputBean();
		commonInput(input);
		input.setProg_id("PG201611290001");
		ViewProgOutputBean output  = sc.queryEnvProgInfo(input);
		System.out.println(output.getProg_name());
		//System.out.println(output.getProg_desc());
		System.out.println(output.getProg_type());
		if(!Assert.isEmpty(output.getProg_step_list())){
			for (EnvProgStepBean bean : output.getProg_step_list()) {
				System.out.println(bean.toString());
				for (TargetPackageBean iterable_element : bean.getTar_package_list()) {
					System.out.println(iterable_element.getPackage_name());
					System.out.println(iterable_element.getFile_list().size());
					System.out.println(iterable_element.getFile_list().get(0).getFile_name());
				}
			}
		}
		
	}
	
	public void testqueryPubProgByType(){
		ViewProgInputBean input = new ViewProgInputBean();
		commonInput(input);
		input.setProg_type(PROG_TYPE.INTEGRATION);
		input.setEnv_name("fwap");
		ViewProgOutputBean output  = sc.queryPubProgByType(input);
		List<PgProgramInfo> prog_list = output.getProg_list();
		if(!Assert.isEmpty(prog_list)) {
			for (PgProgramInfo pgProgramInfo : prog_list) {
				System.out.println(pgProgramInfo.getProg_id());
				System.out.println(pgProgramInfo.getProg_name());
			}
		}
	}
	
	/**
	 * 公共赋值
	 * @param input
	 */
	private void commonInput(ViewProgInputBean input) {
		// 公共赋值
		input.setOrg_channel_code("01");
		input.setOrg_work_code("");
		input.setOrg_srv_name("en_AddEnvironmentAction");
		input.setOrg_rs_code("00");
		input.setOrg_user_id("admin");
		input.setRemote_ip("10.1.1.220");
		input.setServer_port(21);
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
