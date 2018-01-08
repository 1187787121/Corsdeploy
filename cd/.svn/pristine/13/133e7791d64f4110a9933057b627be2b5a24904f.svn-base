/**
 * Title: TestQueryBsForAppAction.java
 * File Description: 
 * @copyright: 2017
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2017年2月28日
 */
package com.wk.cd.test.build.en.action;

import com.wk.Controller;
import com.wk.beans.Injector;
import com.wk.cd.build.en.action.QueryBsForAppAction;
import com.wk.cd.build.en.bean.QueryBsForAppInputBean;
import com.wk.cd.build.en.bean.QueryBsForAppOutputBean;
import com.wk.cd.build.en.bean.SystemAppbean;
import com.wk.cd.build.en.info.CeServerInfo;
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
 * @author Xul
 */
public class TestQueryBsForAppAction extends TestCase{
	
	private  DBSource db = DBSource.get();
	private  Session session;
	private Injector inject=Controller.getInstance().getInjector();
	private QueryBsForAppAction sc = inject.getBean(QueryBsForAppAction.class);
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
	
	public void testpageAllProject(){
		QueryBsForAppInputBean input = new QueryBsForAppInputBean();
		commonInput(input);
		QueryBsForAppOutputBean output = sc.run(input);
		for (SystemAppbean bean : output.getSys_list()) {
			System.out.println(bean.getSys_name());
			if(!Assert.isEmpty(bean.getServer_list())){
				for(CeServerInfo info : bean.getServer_list()){
					System.out.println(info.getServer_cn_name());
				}
			}
//			System.out.println(bean.getTp_list().get(0).getTemplate_type_cname());
//			System.out.println(bean.getTp_list().get(0).getTemplate_type());
//			System.out.println(bean.getTp_list().get(0).getTemplate_list());
		}
	}
	
	/**
	 * 公共赋值
	 * @param input
	 */
	private void commonInput(QueryBsForAppInputBean input) {
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
