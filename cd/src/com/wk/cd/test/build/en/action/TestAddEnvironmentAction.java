/**
 * Title: TestAddEnvirAction.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: xuph
 * @version: 1.0
 * @date: 2016年11月1日
 */
package com.wk.cd.test.build.en.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.Controller;
import com.wk.beans.Injector;
import com.wk.cd.build.en.action.AddEnvironmentAction;
import com.wk.cd.build.en.bean.AddEnvironmentInputBean;
import com.wk.cd.build.en.bean.ServerBean;
import com.wk.cd.enu.DT_RANGE;
import com.wk.cd.enu.ENV_TYPE;
import com.wk.cd.enu.SERVER_TYPE;
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
 * @author xuph
 */
public class TestAddEnvironmentAction extends TestCase{
	private  DBSource db = DBSource.get();
	private  Session session;
	private Injector inject=Controller.getInstance().getInjector();
	private AddEnvironmentAction sc = inject.getBean(AddEnvironmentAction.class);
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
	
	public void testAddEnvirAction1(){
		AddEnvironmentInputBean input = new AddEnvironmentInputBean();
		commonInput(input);
		String[] ele_type = new String[2];
		ele_type[0]="1";
		ele_type[1]="2";
		input.setEle_type(ele_type);// 构成要素
		input.setEnv_bk_desc("test");//环境描述
		input.setEnv_name("test"); //环境名称
		input.setEnv_type(ENV_TYPE.PRODUCT);//环境类型
		input.setEnv_cn_name("test"); //环境简称
		input.setDt_range(DT_RANGE.ALL);//数据范围
		input.setSys_name("test");//应用系统
		List<ServerBean>server_list = new ArrayList<ServerBean>();//应用服务列表
		ServerBean cc = new ServerBean();
		cc.setServer_name("server01");//服务器名称
		cc.setServer_type(SERVER_TYPE.APPLY);//服务器类型
		server_list.add(cc);
		input.setServer_list(server_list);
		sc.run(input);
		 
	}
	
	/**
	 * 公共赋值
	 * @param input
	 */
	private void commonInput(AddEnvironmentInputBean input) {
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
		input.setPendwk_bk_expl("测试新增组件信息");
		input.setSubmit_type(SUBMIT_TYPE.APPLY);
		JaDateTime curdt = cmsvc.getCurrentDateTime();
		input.setDtbs_bk_date(JaDate.valueOf(curdt.dateValue()));
		input.setDtbs_bk_time(JaTime.valueOf(curdt.timeValue()));

	}

}
