/**
 * Title: TestWriteEnvConfigFileAction.java
 * File Description: 测试修改配置文件服务
 * @copyright: 2016
 * @company: CORSWORK
 * @author: chiss
 * @version: 1.0
 * @date: 2016年12月9日
 */
package com.wk.cd.test.build.ea.action;

import com.wk.Controller;
import com.wk.beans.Injector;
import com.wk.cd.build.ea.action.UpdateBuildConfigAction;
import com.wk.cd.build.ea.bean.UpdateBuildConfigInputBean;
import com.wk.cd.build.ea.service.EnvTaskPublicService;
import com.wk.cd.enu.CFG_TYPE;
import com.wk.cd.common.cm.service.CommonService;
import com.wk.cd.enu.SUBMIT_TYPE;
import com.wk.cd.module1.Result;
import com.wk.cd.module1.impl.SVN;
import com.wk.cd.module1.info.ModuleSourceInfo;
import com.wk.cd.system.dt.info.DtSourceInfo;
import com.wk.cd.system.dt.service.DtSocService;
import com.wk.db.DBSource;
import com.wk.db.Session;
import com.wk.lang.Inject;
import com.wk.test.TestCase;
import com.wk.util.JaDate;
import com.wk.util.JaDateTime;
import com.wk.util.JaTime;

/**
 * Class Description: 测试修改配置文件服务
 * @author chiss
 */
public class TestUpdateBuildConfigAction extends TestCase{
	
	private DBSource db = DBSource.get();
	private Session session;
	private Injector inject=Controller.getInstance().getInjector();
	private EnvTaskPublicService envTaskPublicService = inject.getBean(EnvTaskPublicService.class);
	private DtSocService dtSrv = inject.getBean(DtSocService.class);
	private UpdateBuildConfigAction sc = inject.getBean(UpdateBuildConfigAction.class);
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
	
	public void test1(){
		UpdateBuildConfigInputBean input = new UpdateBuildConfigInputBean();
		commonInput(input);
		input.setEnv_name("chissEnv");
		input.setCe_server_name("228Server");
		input.setWork_id("TK201612140018");
		input.setRelative_path("/home/front/web.xml");
		input.setConfig_string("");
		input.setCfg_type(CFG_TYPE.NORMAL);
		input.setEncoding("utf-8");
		sc.run(input);
	}
	
	//测试SVN
	public void test2(){
		//获取本地数据源信息
		DtSourceInfo ver_dt_info = dtSrv.getInfoByKey("240svn");
		DtSourceInfo local_info = envTaskPublicService.getLocalDtInfo();
		ModuleSourceInfo minfo = new ModuleSourceInfo(local_info, ver_dt_info);
		String[] cmds = new String[] { "co_empty /CorsManager/version/inte/cv_v1.5.1 task/TK201702230015/", "cd /home/sample/corsdeploy/cdWeb/task/TK201702230015/", "add cv_v1.5.1.xlsx", "ci -m commit"};
		SVN svn = new SVN(minfo, cmds);
		Result result = svn.run();
		System.out.println(result.getMsg());
	}
	
	/**
	 * 公共赋值
	 * @param input
	 */
	private void commonInput(UpdateBuildConfigInputBean input) {
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
		input.setPendwk_bk_expl("修改配置文件服务");
		input.setSubmit_type(SUBMIT_TYPE.APPLY);
		JaDateTime curdt = cmsvc.getCurrentDateTime();
		input.setDtbs_bk_date(JaDate.valueOf(curdt.dateValue()));
		input.setDtbs_bk_time(JaTime.valueOf(curdt.timeValue()));

	}
}
