/**
 * Title: TestAddSystemAction.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: chiss
 * @version: 1.0
 * @date: 2016年11月1日
 */
package com.wk.cd.test.build.en.action;

import java.util.List;

import com.wk.Controller;
import com.wk.beans.Injector;
import com.wk.cd.build.en.action.ViewSystemAction;
import com.wk.cd.build.en.bean.PageSystemListBean;
import com.wk.cd.build.en.bean.SysEnvAndTaskBean;
import com.wk.cd.build.en.bean.SystemEnvirBean;
import com.wk.cd.build.en.bean.SystemTemplateBean;
import com.wk.cd.build.en.bean.ViewSystemInputBean;
import com.wk.cd.build.en.bean.ViewSystemOutputBean;
import com.wk.cd.build.en.info.CeSystemInfo;
import com.wk.cd.common.cm.service.CommonService;
import com.wk.cd.enu.ORDER_TYPE;
import com.wk.cd.enu.SUBMIT_TYPE;
import com.wk.cd.enu.TEMPLATE_TYPE;
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
public class TestViewSystemAction extends TestCase{
	private DBSource db = DBSource.get();

	private Session session;

	private Injector inject = Controller.getInstance().getInjector();

	private ViewSystemAction sc = inject.getBean(ViewSystemAction.class);
	
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
	
	public void testTemplate() {
		ViewSystemInputBean input = new ViewSystemInputBean();
		commonInput(input);
		input.setTemplate_type(TEMPLATE_TYPE.ROLLBACK);
		ViewSystemOutputBean output =  sc.getTemplate(input);
		String[] arr = output.getTemplate_name();
		System.out.println(arr.toString());
	}
	
	public void testGetTemplateList() {
		ViewSystemInputBean input = new ViewSystemInputBean();
		commonInput(input);
		ViewSystemOutputBean output = new ViewSystemOutputBean();
		output = sc.getTemplateList(input);
		List<SystemTemplateBean> bean_list = output.getSys_bean();
		for (SystemTemplateBean bean : bean_list) {
			System.out.println(bean.toString());
		}
//		System.out.println(bean_list);
	}
	public void testGetSystem() {
		ViewSystemInputBean input = new ViewSystemInputBean();
		commonInput(input);
		input.setSys_name("test");
		ViewSystemOutputBean output  = sc.getSystem(input);
		
		System.out.println(output.getSys_name());
		System.out.println(output.getSys_cn_name());
		System.out.println(output.getSys_bk_desc());
		System.out.println(output.getSys_type());
		System.out.println(output.getSystemp_list());
		System.out.println(output.getFile_list().toString());
	}
	public void testGetSystemEnvirList() {
		ViewSystemInputBean input = new ViewSystemInputBean();
		commonInput(input);
		input.setOrder_col_name("DT_RANGE");
		input.setOrder_type(ORDER_TYPE.DESC);
		input.setSys_name("test");
		ViewSystemOutputBean output = sc.getSystemEnvirList(input);
		for (SystemEnvirBean list : output.getEnvir_list()) {
			System.out.println(list.toString());
		}
//		for (int i = 0; i < output.getEnvir_list().size(); i++) {
//			System.out.println("----------------------");
//			System.out.println(output.getEnvir_list().get(i).getEle_type());
//			System.out.println(output.getEnvir_list().get(i).getEnv_cn_name());
//			System.out.println(output.getEnvir_list().get(i).getDt_range());
//			System.out.println(output.getEnvir_list().get(i).getDt_range());
//			System.out.println(output.getEnvir_list().get(i).getEle_type());
//			System.out.println(output.getEnvir_list().get(i).getServer_type());
//		}
//		System.out.print(output.getEnvir_list());
	}
	
	public void testgetSystemList() {
		ViewSystemInputBean input = new ViewSystemInputBean();
		commonInput(input);
		input.setSys_name("test");
		//input.setSys_name("");
//		input.setOrder_col_name("mount_num");
//		input.setOrder_type(ORDER_TYPE.DESC);
		ViewSystemOutputBean output = sc.getSystemList(input);
		for (PageSystemListBean itt :  output.getPage_sys_List()) {
			System.out.println(itt.getSys_name());
		}
	}
	
	public void testGetSystemNameList() {
		ViewSystemInputBean input = new ViewSystemInputBean();
		commonInput(input);
		ViewSystemOutputBean output = sc.getSystemNameList(input);
		for (CeSystemInfo itt :  output.getSys_list()) {
			System.out.println(itt.getSys_name());
		}
	}
	
	public void testQueryEnvAndTask() {
		ViewSystemInputBean input = new ViewSystemInputBean();
		commonInput(input);
		input.setSys_name("loacl_test");
		ViewSystemOutputBean output = sc.queryEnvAndTask(input);
		for(SysEnvAndTaskBean bean : output.getSys_detail_list()){
			System.out.println(bean.getEnv_name());
			System.out.println(bean.getEnv_cn_name());
			System.out.println(bean.getEnv_type());
			System.out.println(bean.getTask_seq());
		}
	}
	
	/**
	 * 公共赋值
	 * @param input
	 */
	private void commonInput(ViewSystemInputBean input) {
		// 公共赋值
		input.setOrg_channel_code("01");
		input.setOrg_work_code("");
		input.setOrg_srv_name("us_AddUserAction");
		input.setOrg_rs_code("00");
		input.setOrg_user_id("admin");
		input.setOrg_term_no("10.1.1.195");
		input.setOrg_dept_id("110001");
		input.setPbl_code("00001");
		input.setPend_srv_name("add_work_action");
		input.setPend_rs_code("html");
		input.setPendwk_bk_expl("测试新增系统信息");
		input.setSubmit_type(SUBMIT_TYPE.APPLY);
		JaDateTime curdt = cmsvc.getCurrentDateTime();
		input.setDtbs_bk_date(JaDate.valueOf(curdt.dateValue()));
		input.setDtbs_bk_time(JaTime.valueOf(curdt.timeValue()));
		
	}
}
