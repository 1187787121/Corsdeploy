/**
 * Title: TestAddBuildExeScriptAction.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: xuph
 * @version: 1.0
 * @date: 2016年12月10日
 */
package com.wk.cd.test.build.ea.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.Controller;
import com.wk.beans.Injector;
import com.wk.cd.build.ea.action.AddBuildExeScriptAction;
import com.wk.cd.build.ea.bean.AddBuildExeScriptInputBean;
import com.wk.cd.build.ea.info.UuParamInfo;
import com.wk.cd.build.ea.info.UuSocInfo;
import com.wk.cd.enu.SCRIPT_METHOD;
import com.wk.cd.enu.SCRIPT_TYPE;
import com.wk.cd.common.cm.service.CommonService;
import com.wk.cd.enu.SUBMIT_TYPE;
import com.wk.db.DBSource;
import com.wk.db.Session;
import com.wk.test.TestCase;
import com.wk.util.JaDate;
import com.wk.util.JaDateTime;
import com.wk.util.JaTime;

/**
 * Class Description: 测试新增参数配置信息
 * @author xuph
 */
public class TestAddBuildExeScriptAction extends TestCase{
	private  DBSource db = DBSource.get();
	private  Session session;
	private Injector inject=Controller.getInstance().getInjector();
	private AddBuildExeScriptAction sc = inject.getBean(AddBuildExeScriptAction.class);
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
		session.commit();
	}

	@Override
	protected void tearDownOnce()
		throws Exception {
//		session.commit();
	}
	
	public void testExcute(){
		AddBuildExeScriptInputBean input = new AddBuildExeScriptInputBean();
		commonInput(input);
		//测试组件
		input.setWork_id("TK0000007008");
	    input.setScript_type(SCRIPT_TYPE.OPERATION);
	    input.setScript_method(SCRIPT_METHOD.SHELL);
	    //input.setCn_name("目录创建");
	    //input.setId("COMP201701060542");
	    //input.setScript_method(SCRIPT_METHOD.COMP);
	    // input.setScript_text("krishna111");
	    input.setScript_text(new String[]{"ls -l"});
	    //数据源
	    List<UuSocInfo>soc_list = new ArrayList<UuSocInfo>();
	    UuSocInfo soc = new UuSocInfo();
	    soc.setExe_server_name("testProg");
	    soc.setExe_soc_name("220ssh");
	   /* UuSocInfo soc1 = new UuSocInfo();
	    soc1.setExe_server_name("228server ");
	    soc1.setExe_soc_name("228ssh");
	    soc_list.add(soc1);*/
	    soc_list.add(soc);
	    input.setSoc_list(soc_list);
	    List<UuParamInfo>param_list = new ArrayList<UuParamInfo>();
//	    UuParamInfo parm = new UuParamInfo();
//	    parm.setParam_value("backup");
//	    parm.setParam_group("");
//	    parm.setParam_cn_name("备份目录");
//	    parm.setParam_bk_desc("备份目录");
//	    parm.setParam_name("backup_dir");
//	    param_list.add(parm);
	    input.setParam_list(param_list);
	    try {
	    	sc.run(input);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	   /* ParamInfo parm2 = new ParamInfo();
	    parm2.setParam_value("pj_name");
	    parm2.setParam_group("");
	    parm2.setParam_cn_name("pj_bk_dir");
	    parm2.setParam_bk_desc("项目备份目录");
	    parm2.setParam_name("backup_dir");
	    parm2.setHand_param(false);
	    
	    ParamInfo parm1 = new ParamInfo();
	    parm1.setParam_value("bs_name");
	    parm1.setParam_group("");
	    parm1.setParam_cn_name("bs_bk_dir");
	    parm1.setParam_bk_desc("系统备份目录");
	    parm1.setParam_name("task_no");
	    parm1.setHand_param(false);
	    param_list.add(parm);
	    param_list.add(parm1);*/
	  
	 
	}
	
	/**
	 * 公共赋值
	 * @param input
	 */
	private void commonInput(AddBuildExeScriptInputBean input) {
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
