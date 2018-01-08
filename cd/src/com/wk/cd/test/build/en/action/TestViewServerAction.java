/**
 * Title: TestDeleteServerAction.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: yangl
 * @version: 1.0
 * @date: 2016年11月1日
 */
package com.wk.cd.test.build.en.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.Controller;
import com.wk.beans.Injector;
import com.wk.cd.build.en.action.ViewServerAction;
import com.wk.cd.build.en.bean.ServerBean;
import com.wk.cd.build.en.bean.ServerMsgBean;
import com.wk.cd.build.en.bean.SocBean;
import com.wk.cd.build.en.bean.SysEnvBean;
import com.wk.cd.build.en.bean.ViewServerInputBean;
import com.wk.cd.build.en.bean.ViewServerOutputBean;
import com.wk.cd.enu.ORDER_TYPE;
import com.wk.cd.enu.SUBMIT_TYPE;
import com.wk.cd.enu.IMPL_TYPE;
import com.wk.db.DBSource;
import com.wk.db.Session;
import com.wk.test.TestCase;
import com.wk.util.JaDate;
import com.wk.util.JaTime;

/**
 * Class Description: 
 * @author yangl
 */
public class TestViewServerAction
		extends TestCase {
	
	private  DBSource db = DBSource.get();
	private  Session session;
	private Injector inject=Controller.getInstance().getInjector();
	private ViewServerAction sc = inject.getBean(ViewServerAction.class);

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
		if (session == null) {
			session = db.openSession();
			session.beginTransaction();
		}
	}

	@Override
	protected void tearDown()
		throws Exception {
		if (session == null) {
			session = db.openSession();
			session.beginTransaction();
		}
		session.commitAndResume();
	}

	@Override
	protected void tearDownOnce()
		throws Exception {
		if (session == null) {
			session = db.openSession();
			session.beginTransaction();
		}
		session.commit();
	}
	
	public void testGetServerDetail(){
		ViewServerInputBean input = new ViewServerInputBean();
		commonInput(input);
		input.setCe_server_name("test");
		ViewServerOutputBean output = sc.getServerDetail(input);
		System.out.println(output.getCe_server_name());
	}
	
	public void testGetAllServerName(){
		ViewServerInputBean input = new ViewServerInputBean();
		commonInput(input);
		ViewServerOutputBean output = sc.getAllServerName(input);
		for (ServerBean bean : output.getServer_list()) {
			System.out.println(bean.getServer_name());
			System.out.println(bean.getServer_cn_name());
			System.out.println(bean.getServer_ip());
		}
	}
	
	public void testQueryDetailAndRelatedInfo(){
		ViewServerInputBean input = new ViewServerInputBean();
		input.setCe_server_name("testProg");
		ViewServerOutputBean output = sc.queryDetailAndRelatedInfo(input);
		System.out.println(output.getServer_detail_bean().getServer_name());
		System.out.println(output.getServer_detail_bean().getServer_cn_name());
		System.out.println(output.getServer_detail_bean().getServer_bk_desc());
		System.out.println(output.getServer_detail_bean().getServer_ip());
		for(SysEnvBean bean : output.getServer_detail_bean().getSys_env_list()){
			System.out.println("系统----------------------------");
			System.out.println(bean.getSys_name());
			System.out.println(bean.getSys_cn_name());
			System.out.println(bean.getSys_type().getCname());
			System.out.println("环境----------------------------");
//			System.out.println(bean.getEnv_name());
//			System.out.println(bean.getEnv_cn_name());
//			System.out.println(bean.getEnv_type().getCname());
		}
	}
	
	public void testQueryServerDetailByIP(){
		ViewServerInputBean input = new ViewServerInputBean();
		input.setServer_ip("");
		ViewServerOutputBean output = sc.queryServerDetailByIP(input);
		for(ServerBean bean : output.getServer_list()){
			System.out.println("系统----------------------------");
			System.out.println(bean.getServer_name());
		}
	}
	
	public void testQuerySourceByServer(){
		ViewServerInputBean input = new ViewServerInputBean();
		commonInput(input);
		input.setCe_server_name("card_dev_app");
		ViewServerOutputBean output = sc.querySourceByServer(input);
		 
		for (SocBean bean : output.getSource_list()) {
			System.out.println(bean.toString());
		}
	}
	
	public void testQuerySerByEnv(){
		ViewServerInputBean input = new ViewServerInputBean();
		commonInput(input);
		input.setEnv_name("card_dev");
		ViewServerOutputBean output = sc.querySerByEnv(input);
		System.out.println(output.getServer_name_list());
	}
	
	public void testQueryVerSerByEnv(){
		ViewServerInputBean input = new ViewServerInputBean();
		commonInput(input);
		input.setEnv_name("card_dev");
		ViewServerOutputBean output = sc.queryVerSerByEnv(input);
		System.out.println(output.getVersion_name_list());
	}
	
	public void testQuerySysAndEnvByServer(){
		ViewServerInputBean input = new ViewServerInputBean();
		commonInput(input);
		input.setCe_server_name("pay_dev_app");
		input.setOrder_col_name("sys_name");
		input.setOrder_type(ORDER_TYPE.ASC);
		ViewServerOutputBean output = sc.querySysAndEnvByServer(input);
		System.out.println(output.getEnv_sys_list().size());
	}
	
	public void testQuerySocNameByServer(){
		ViewServerInputBean input = new ViewServerInputBean();
		commonInput(input);
		input.setCe_server_name("card_dev_app");
		ViewServerOutputBean output = sc.querySocNameByServer(input);
		System.out.println(output.getSoc_name_list());
		
	}
	
	public void testQuerySocNameByServerAndType(){
		ViewServerInputBean input = new ViewServerInputBean();
		commonInput(input);
		input.setCe_server_name("228服务器 ");
		input.setImpl_type(IMPL_TYPE.FTP);
		ViewServerOutputBean output = sc.querySocNameByServerAndType(input);
		System.out.println(output.getSoc_name_list());
		
	}
	
	public void testQueryServerForAppAction(){
		ViewServerInputBean input = new ViewServerInputBean();
		commonInput(input);
		ViewServerOutputBean output = sc.queryServerForApp(input);
		System.out.println(output.getSoc_name_list());
	}
	
	public void testQueryEnvListForApp(){
		ViewServerInputBean input = new ViewServerInputBean();
		input.setCe_server_name("testProg");
		commonInput(input);
		ViewServerOutputBean output = sc.queryEnvListForApp(input);
		System.out.println(output.getEnv_list().size());
	}
	
	public void testqueryServerListForApp(){
		ViewServerInputBean input = new ViewServerInputBean();
		commonInput(input);
		input.setSys_name("test_prog");
		input.setServer_name("");
		//queryServerListForApp
		ViewServerOutputBean output =	sc.queryServerListForApp(input);
		List<ServerMsgBean> lis = output.getServer_msg_list();
		for (ServerMsgBean ser : lis) {
			/*System.out.println(ser.getServer_name());
			System.out.println(ser.getServer_cn_name());
			System.out.println(ser.getOs_sbk_ver());
			System.out.println(ser.getServer_mid_ware());*/
			System.out.println(ser.getServer_ip());
			//System.out.println(ser.getSoc_list().get(0).getSoc_name());
			//System.out.println("-----------------------");
		}
	}
	
	public void testQueryServerDetailByIPAndSystems(){
		ViewServerInputBean input = new ViewServerInputBean();
		input.setSys_name_list(new String[2]);
		commonInput(input);
		ViewServerOutputBean output = sc.queryServerDetailByIPAndSystems(input);
		System.out.println(output.getSystype_server_list().size());
	}
	
	/**
	 * 公共赋值
	 * @param input
	 */
	private void commonInput(ViewServerInputBean input) {
		// 公共赋值
		input.setOrg_channel_code("01");
		input.setOrg_work_code("");
		input.setOrg_srv_name("en_AddServerAction");
		input.setOrg_rs_code("00");
		input.setOrg_user_id("admin");
		input.setOrg_term_no("10.1.1.195");
		input.setOrg_dept_id("110001");
		input.setPbl_code("00001");
		input.setPend_srv_name("add_work_action");
		input.setPend_rs_code("html");
		input.setPendwk_bk_expl("测试新增组件信息");
		input.setSubmit_type(SUBMIT_TYPE.APPLY);
		input.setDtbs_bk_date(JaDate.today());
		input.setDtbs_bk_time(JaTime.time());
	}

}
