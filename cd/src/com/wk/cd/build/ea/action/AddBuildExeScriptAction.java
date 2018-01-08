/**
 * Title: AddBuildExeScriptAction.java
 * File Description: 添加参数配置信息
 * @copyright: 2016
 * @company: CORSWORK
 * @author: xuph
 * @version: 1.0
 * @date: 2016年12月9日
 */
package com.wk.cd.build.ea.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.build.ea.bean.AddBuildExeScriptInputBean;
import com.wk.cd.build.ea.bean.AddBuildExeScriptOutputBean;
import com.wk.cd.build.ea.info.UuParamInfo;
import com.wk.cd.build.ea.info.UuSocInfo;
import com.wk.cd.build.ea.service.ExeScriptPublicService;
import com.wk.cd.common.cm.service.GenNoService;
import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.SCRIPT_METHOD;
import com.wk.cd.enu.SCRIPT_TYPE;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 添加参数配置信息
 * @author xuph
 */
public class AddBuildExeScriptAction extends ActionBasic<AddBuildExeScriptInputBean, AddBuildExeScriptOutputBean>{
	@Inject private GenNoService genNoService;
	@Inject private ExeScriptPublicService exeSrptSrv;
	@Inject private ActionLogPublicService lgsvc;
	private static final Log logger = LogFactory.getLog();
	/** 
	 * Description: 添加参数配置操作
	 * @param input
	 * @return 
	 */
	@Override
	protected AddBuildExeScriptOutputBean doAction(AddBuildExeScriptInputBean input) {
		logger.info("--------------AddBuildExeScriptAction Begin-------------");
		AddBuildExeScriptOutputBean output = new AddBuildExeScriptOutputBean();
		String comp_id = input.getId();
		String work_id = input.getWork_id();
		String[] script_text = input.getScript_text();
		String exe_user_id = input.getOrg_user_id();
		String comp_cn_name = input.getCn_name();
		SCRIPT_METHOD script_method = input.getScript_method();
		SCRIPT_TYPE script_type = input.getScript_type();
		List<UuParamInfo>param_list = input.getParam_list();
		List<UuSocInfo>soc_list = input.getSoc_list();
		//非空校检
		Assert.assertNotEmpty(work_id, AddBuildExeScriptInputBean.WORK_IDCN);
		Assert.assertNotEmpty(soc_list, AddBuildExeScriptInputBean.SOC_LISTCN);
		Assert.assertNotEmpty(exe_user_id, AddBuildExeScriptInputBean.ORG_USER_IDCN);
		Assert.assertNotEmpty(script_method.getValue()==0?null:script_method, AddBuildExeScriptInputBean.SCRIPT_METHODCN);
		Assert.assertNotEmpty(script_type.getValue()==0?null:script_type, AddBuildExeScriptInputBean.SCRIPT_TYPECN);
		//合法性校检
		exeSrptSrv.checkExeScript(script_method,comp_id,comp_cn_name,script_text);
		
		logger.info("参数配置任务编号work_id=[{}]",work_id);
		// 生成脚本序号
		long scirpt_bk_seq = exeSrptSrv.getNextScriptBkSeq(work_id,script_type);
		// 保存数据
		exeSrptSrv.saveBuildExeScriptMsg(script_method,script_type,param_list, soc_list, script_text,work_id,scirpt_bk_seq,exe_user_id,comp_cn_name,comp_id);
		output.setScript_type(script_type);
		output.setScript_bk_seq(scirpt_bk_seq);
		logger.info("--------------AddBuildExeScriptAction End---------------");
		return output;
	}

	/** 
	 * Description: 日志写入
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(AddBuildExeScriptInputBean input) {
		List<String> log_list = new ArrayList<String>();
		log_list.add("参数配置方式SCRIPT_METHOD:"+input.getScript_method().getCname());
		return lgsvc.getLogTxt("添加参数配置信息", log_list);
	}

}
