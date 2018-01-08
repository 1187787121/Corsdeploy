/**
 * Title: TestPageInteTaskAction.java
 * File Description: 测试分页查询集成任务列表
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2016年11月18日
 */
package com.wk.cd.test.build.ea.action;

import com.wk.Controller;
import com.wk.beans.Injector;
import com.wk.cd.build.ea.action.PagePublishTaskAction;
import com.wk.cd.build.ea.bean.PagePublishTaskInputBean;
import com.wk.cd.build.ea.bean.PagePublishTaskOutputBean;
import com.wk.cd.build.ea.bean.PubTaskBean;
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
 * Class Description: 测试分页查询集成任务列表
 * @author chiss
 */
public class TestPagePublishTaskAction extends TestCase{
	private DBSource db = DBSource.get();
	private Session session;
	private Injector inject=Controller.getInstance().getInjector();
	private PagePublishTaskAction sc = inject.getBean(PagePublishTaskAction.class);
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
	
	public void test(){
		PagePublishTaskInputBean input = new PagePublishTaskInputBean();
		commonInput(input);
		input.setStart_recd(0);
		input.setLimit_recd(5);
		input.setEnv_name("XulEnv");
		PagePublishTaskOutputBean output = sc.run(input);
		for(PubTaskBean bean : output.getPub_list()){
			System.out.println(bean.getWork_id());
			System.out.println(bean.getTarget_ver_num());
			if(!Assert.isEmpty(bean.getPackage_list())){
				for(String str : bean.getPackage_list()){
					System.out.println(str);
				}
			}
			System.out.println(bean.getExelog_name());
			System.out.println(bean.getTask_status());
			System.out.println(bean.getExe_result());
			System.out.println(bean.getRol_inst_flag());
			System.out.println("getCreate_bk_date: " + bean.getCrt_bk_date());
		}
		System.out.println(output.getAll_recd());
	}
	
	/**
	 * 公共赋值 
	 * @param input
	 */
	private void commonInput(PagePublishTaskInputBean input) {
		// 公共赋值
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
		input.setPendwk_bk_expl("删除配置文件服务");
		input.setSubmit_type(SUBMIT_TYPE.APPLY);
		JaDateTime curdt = cmsvc.getCurrentDateTime();
		input.setDtbs_bk_date(JaDate.valueOf(curdt.dateValue()));
		input.setDtbs_bk_time(JaTime.valueOf(curdt.timeValue()));
	}
}
