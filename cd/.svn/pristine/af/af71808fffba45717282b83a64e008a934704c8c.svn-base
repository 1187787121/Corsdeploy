/**
 * Title: ActionBasic.java
 * File Description: 服务基类
 * @copyright 2014
 * @company CORSWORK
 * @author lixl
 * @version 1.0
 * @date 11/7/2014
 */

package com.wk.cd.service;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.bean.ActionRootInputBean;
import com.wk.cd.bean.ActionRootOutputBean;
import com.wk.cd.common.cm.service.CommonService;
import com.wk.cd.common.cm.service.GenNoService;
import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.CfgTool;
import com.wk.cd.enu.APROV_CATEGORY;
import com.wk.cd.enu.SOC_FLAG;
import com.wk.cd.enu.SUBMIT_TYPE;
import com.wk.cd.enu.SVDEAL_TYPE;
import com.wk.cd.enu.YN_FLAG;
import com.wk.cd.system.ap.info.SvSrvAuthInfo;
import com.wk.cd.system.ap.service.ApPublicService;
import com.wk.cd.system.ch.service.ChChannelService;
import com.wk.cd.system.dp.service.DpPublicService;
import com.wk.cd.system.lg.info.LgLogMfInfo;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.system.sv.info.SvSrvInfo;
import com.wk.cd.system.sv.info.SvSrvSocInfo;
import com.wk.cd.system.sv.service.SvSrvService;
import com.wk.cd.system.us.service.UsCheckUserPrivService;
import com.wk.cd.system.us.service.UsCheckUserService;
import com.wk.cd.system.us.service.UsGetUserInfoService;
import com.wk.cd.work.wk.bean.QueryPendWorkOutputBean;
import com.wk.cd.work.wk.bean.UpdateWorkStateToCompleteBean;
import com.wk.cd.work.wk.service.WorkPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;
import com.wk.service.ClassService;
import com.wk.util.JaDate;
import com.wk.util.JaDateTime;
import com.wk.util.JaTime;

/**
 * Class Description: 服务基类
 * @author lixl
 */
public abstract class ActionBasic<IN extends ActionRootInputBean, OUT extends ActionRootOutputBean> extends RequestBasic<IN, OUT>{
	@Inject
	private ActionLogPublicService actlog;
	@Inject
	private CommonService cmsvc;
	@Inject
	private GenNoService nosvc;
	@Inject
	private SvSrvService svsvc;
	@Inject
	private WorkPublicService wksvc;
	@Inject
	private DpPublicService dpsvc;
	@Inject
	private UsCheckUserPrivService ussvc;
	@Inject
	private UsCheckUserService ckussvc;
	@Inject
	private ChChannelService chsvc;
	@Inject
	private ApPublicService apsrv;
	@Inject
	private UsGetUserInfoService ususersrv;


	private static final Log logger = LogFactory.getLog();

	/**
	 * 所有Action入口
	 * @param input 
	 * @return OUT 
	 */
	@Override
	protected OUT runActionBasic(IN input) {
		logger.info("------Action begin Service name=[{}]------",input.getOrg_srv_name());
		logger.debug("------remote ip=[{}]--server_name=[{}]--server_port=[{}]-----", 
					 input.getRemote_ip(), input.getServer_name(), input.getServer_port());
		OUT output = null;

		//输入检查
		chkInput(input);

		//获取系统日期
		JaDateTime curdt = cmsvc.getCurrentDateTime();
		input.setDtbs_bk_date(JaDate.valueOf(curdt.dateValue()));
		input.setDtbs_bk_time(JaTime.valueOf(curdt.timeValue()));

		//生成流水号
		genWorkSeq(input);
		logger.info("------Action work seq=[{}]------", input.getWork_seq());

		//测试模式运行或生产模式运行
		if(CfgTool.isTestMode()) {
			logger.debug("run in test mode");
			output = runInTest(input);
		}else if(CfgTool.isIntegrationMode()){
			logger.debug("run in integration mode");
			output = runInIntegration(input);
		}else{
			logger.debug("run in product mode");
			output = runInProduct(input);
		}
		// 设置输出接口
		setUpOutput(output, input);
		return output;
	}

	// 实现执行服务子类实现
	protected abstract OUT doAction(IN input);

	// 所有Action子类都要提供详细日志信息
	protected abstract String getLogTxt(IN input);

	//服务子类预处理接口,服务调用前调用
	protected void runSubActionPre(IN input) {
	}

	// 服务子类调用后处理接口,服务调用后处理
	protected void runSubActionAft(IN input, OUT output) {
	}

	protected void genWorkSeq(IN input){
		// 生成流水号
		input.setWork_seq(nosvc.getWorkSeq(input.getDtbs_bk_date(),
				input.getServer_name(), input.getServer_port()));
	}

	protected void chkInput(IN input){
		logger.debug("assert---->org_user_id：[{}]",input.getOrg_user_id());
		Assert.assertNotEmpty(input.getOrg_user_id(), input.ORG_USER_IDCN);
		logger.debug("assert---->Org_dept_id：[{}]",input.getOrg_dept_id());
		Assert.assertNotEmpty(input.getOrg_dept_id(), input.ORG_DEPT_IDCN);
		logger.debug("assert---->Org_term_no：[{}]",input.getOrg_term_no());
		Assert.assertNotEmpty(input.getOrg_term_no(), input.ORG_TERM_NOCN);
		logger.debug("assert---->submit_type：[{}]", input.getSubmit_type());
		Assert.assertNotEmpty(input.getSubmit_type(), input.SUBMIT_TYPECN);
		//Assert.assertNotEmpty(input.getOrg_work_code(), IN.ORG_WORK_CODECN);
		logger.debug("assert---->org_srv_name：[{}]",input.getOrg_srv_name());
		Assert.assertNotEmpty(input.getOrg_srv_name(), input.ORG_SRV_NAMECN);
		logger.debug("assert---->org_rs_code:[{}]",input.getOrg_rs_code());
		Assert.assertNotEmpty(input.getOrg_rs_code(), input.ORG_RS_CODECN);
	}
	
	/**
	 * 
	 * Description: 检查渠道是否有该服务权限
	 * @param input
	 */
	protected void chkChannelSrvPriv(IN input) {
		//检查渠道编码存在 ,不存在报错
		chsvc.checkChannelCodeExist(input.getOrg_channel_code());
		//检查渠道是否有服务权限,没有抛异常
		chsvc.checkChannelHasSrvPriv(input.getOrg_channel_code(),input.getOrg_srv_name());
	}

	/**
	 *  检查终端是否允许提交服务(特殊交易可以改写）
	 * @param input 
	 */
	protected void chkTerm(IN input){
		/**
		 * 终端合法性校验只在用户登录和用户终端新增是检查，常规服务中不需要校验
		 * 服务、终端执行控制（本地执行、远程支持、不控制）机制不控制2017-07-28
		 */
		/*
		ChChannelInfo chInfo=chsvc.getInfoByKey(input.getOrg_channel_code());
		if(chInfo.getChannel_type()==CHANNEL_TYPE.PCWEB){
			svsvc.checkSrvTm(input.getOrg_srv_name(), input.getOrg_term_no());
		}else{
			svsvc.checkTmExist(input.getOrg_term_no());
		}
		*/
	}

	// SQL基类实现,检查SQL权限
	protected void chkSQLPriv(IN input) {
	}

	/**
	 * 包括部门状态和用户状态检查（特殊交易可以改写）
	 * @param input 
	 */
	protected void chkState(IN input){
		// 部门状态检查
		dpsvc.checkDeptState(input.getOrg_dept_id());
		//检查用户登录数，以及用户状态如果为零不能操作
		ckussvc.checkUserLoginNum(input.getOrg_user_id());
	}

	/**
	 * 检查部门权限，包括服务、资源、数据源(特殊交易可以改写)
	 * @param input 
	 */
	protected void chkDeptPriv(IN input){
		// 部门资源权限检查 用户资源检查
		dpsvc.checkDeptRsPrivState(input.getOrg_dept_id(), input
				.getOrg_rs_code());
		// 部门数据源权限检查 用户资源权限检查
		if (!Assert.isEmpty(input.getOrglst_soc_name())) {
			for (String soc_name : input.getOrglst_soc_name()) {
				dpsvc.checkDeptSocPrivState(input.getOrg_dept_id(),soc_name);
			}
		}
	}


	/**
	 * 检查用户权限，包括服务、资源、数据源(特殊交易可以改写)
	 * @param input 
	 */
	protected void chkUserPriv(IN input){
		JaDate dt_date=input.getDtbs_bk_date();
		JaTime dt_time=input.getDtbs_bk_time();
		JaDateTime dt_datetime=JaDateTime.valueOf(dt_date.getYear(), dt_date.getMonth(), dt_date.getDay(), dt_time.getHour(), dt_time.getMinute(), dt_time.getSecond(), dt_time.getMillisecond());
		ussvc.checkUserRsPriv(input.getOrg_user_id(), input
				.getOrg_rs_code(),dt_datetime);
		// 部门数据源权限检查 用户资源权限检查
		if (!Assert.isEmpty(input.getOrglst_soc_name())) {
			for (String soc_name : input.getOrglst_soc_name()) {
				ussvc.checkUserSocPriv(input.getOrg_user_id(), soc_name,dt_datetime);
			}
		}
	}

	/**
	 * 数据源预加载
	 * @author lixl (2015-2-13)
	 * @param input 
	 */
	protected void dataSourceLoad(IN input){
		// 加载服务数据源
		if (Assert.isEmpty(input.getOrglst_soc_name())) {
			SvSrvInfo svInfo = new SvSrvInfo();
			svInfo.setSrv_name(input.getOrg_srv_name());
			svInfo = svsvc.queryNoDelSvSrvByName(svInfo);
			if (svInfo.getSoc_flag() == SOC_FLAG.YES) {
				List<SvSrvSocInfo> ls = svsvc.queryNoDelSvSrvSocByName(input.getOrg_srv_name());
				Assert.assertNotEmpty(ls, "服务数据源列表");
				List<String> soc_names = new ArrayList<String>();
				for(SvSrvSocInfo soc : ls) {
					soc_names.add(soc.getSoc_name());
				}
			}
		}
	}

	/**
	 * 设置公共输出
	 * @param output 
	 */
	protected void setUpOutput(OUT output, IN input){
		output.setDtbs_bk_date(input.getDtbs_bk_date());
		output.setDtbs_bk_time(input.getDtbs_bk_time());
		output.setWork_seq(input.getWork_seq());
	}

	private OUT runInTest(IN input){
		//检查终端
		chkTerm(input);
		return doExecute(input);
	}

	private OUT runInIntegration(IN input) {
		OUT output = null;
		try {
			// 记录服务日志
			addLog(input);
			logger.info("------addLog ok!------");

			output = doExecute(input);
			
			// 设置输出接口
			output.setDtbs_bk_date(input.getDtbs_bk_date());
			output.setDtbs_bk_time(input.getDtbs_bk_time());
			output.setWork_seq(input.getWork_seq());
			output.setReq_data(input.getReq_data());
			
			// 更新服务状态
			updateActionState(input.getWork_seq(), YN_FLAG.YES);
			logger.info("------Action normal end Service name=[{}]------",input.getOrg_srv_name());
		} catch (RuntimeException e) {
			logger.info("------Action abnormal end Service name=[{}]------",input.getOrg_srv_name());
			logger.error(e.toString(), e);
			throw e;
		}
		return output;
	}
	
	private OUT runInProduct(IN input){
		OUT output = null;
		try {
			// 记录服务日志
			addLog(input);
			logger.info("------addLog ok!------");

			//数据源预加载
			dataSourceLoad(input);
			logger.info("------dataSourceLoad ok!------");

			//检查终端
			chkTerm(input);
			logger.info("-----chkTerm ok!------");

			//检查用户、部门状态
			chkState(input);
			logger.info("------chkState ok!------");

			//检查部门权限
			chkDeptPriv(input);
			logger.info("------chkDeptPriv ok!------");

			//检查用户权限
			chkUserPriv(input);
			logger.info("------chkUserPriv ok!------");

			//SQL执行权限检查
			chkSQLPriv(input);
			logger.info("------chkSQLPriv ok!------");
			
			// 如果服务不需要复核或授权直接执行，或者是审核通过的任务
			SUBMIT_TYPE stype = input.getSubmit_type();
			logger.debug("stype = [{}]", stype);
			
			if(stype==SUBMIT_TYPE.RUN){
				output = doExecute(input);
			}else if(stype==SUBMIT_TYPE.EXECUTE){
				output = doAuthExecute(input);
			}else if(stype==SUBMIT_TYPE.APPLYALREADY){
				output = doSubmit(input);
			}else if(stype == SUBMIT_TYPE.APPLY){
				boolean isLocalAuth = isLocAuth(input);
				//TODO 合并授权和复核功能，只需要判断授权即可 (lixl)
				boolean itchy = isChk(input);
				boolean isRemoteAuth = isAuth(input);
				if(isLocalAuth) {
					output = doLocalAuth(input);
				}else if(itchy||isRemoteAuth){
					output = doApplyExpl(input);
				}else if(!itchy&&!isRemoteAuth){
					output = doExecute(input);
				}
			}else if(stype == SUBMIT_TYPE.ALLOW){
				boolean itch = isChk(input);
				boolean isRemoteAuth = isAuth(input);
				if(itch||isRemoteAuth){
					output = doApplyExpl(input);
				}else if(!itch&&!isRemoteAuth){
					output = doExecute(input);
				}
			}
			
			// 设置输出接口
			output.setDtbs_bk_date(input.getDtbs_bk_date());
			output.setDtbs_bk_time(input.getDtbs_bk_time());
			output.setWork_seq(input.getWork_seq());
			output.setReq_data(input.getReq_data());
			
			// 更新服务状态
			updateActionState(input.getWork_seq(), YN_FLAG.YES);
			logger.info("------Action normal end Service name=[{}]------",input.getOrg_srv_name());
		} catch (RuntimeException e) {
			logger.info("------Action abnormal end Service name=[{}]------",input.getOrg_srv_name());
			logger.error(e.toString(), e);
			throw e;
		}

		return output;
	}

	/**
	 * 提交方法
	 * @param input
	 * @return OUT
	 */
	@SuppressWarnings("unchecked")
	private OUT doSubmit(IN input) {
		logger.info("------Execute doSubmit function------");
		ActionRootOutputBean output = new ActionRootOutputBean();
		String work_seq = nosvc.getWorkSeq(input.getDtbs_bk_date(),
				input.getServer_name(), input.getServer_port());
		// 新生成待处理流水号
		input.setPend_work_seq(work_seq);
		wksvc.addPendWork(input);
		output.setPend_work_seq(work_seq);
		output.setSvdeal_type(SVDEAL_TYPE.WAIT);
		return (OUT) output;
	}
	
	@SuppressWarnings("unchecked")
	protected OUT doLocalAuth(IN input) {
		ActionRootOutputBean output = new ActionRootOutputBean();
		logger.info("------Execute doLocalAuth function------");
		SvSrvAuthInfo authInfo=apsrv.queryLocalAuthRole(input.getOrg_dept_id(),input.getOrg_srv_name(),0);
		String dprl_code=authInfo.getAuth_dprl_code();
		if(authInfo.getAuth_aprov_category() == APROV_CATEGORY.ROLE){
			dprl_code = ususersrv.queryDprlByDeptAndRole(input.getOrg_dept_id(), dprl_code);
		}
		String dprl_bk_expl=ckussvc.queryExplByDprl(dprl_code);
		output.setPend_srv_name(input.getOrg_srv_name());
		output.setAuth_dprl_code(dprl_code);
		output.setAuth_seq(authInfo.getAuth_seq());
		output.setAuthdprl_bk_expl(dprl_bk_expl);
		output.setSvdeal_type(SVDEAL_TYPE.LOCAL);
		return (OUT) output;
	}
	
	@SuppressWarnings("unchecked")
	private OUT doApplyExpl(IN input) {
		ActionRootOutputBean output = new ActionRootOutputBean();
		logger.info("------Execute doApplyExpl function------");
		//获得服务描述，即待处理任务说明
		SvSrvInfo info = new SvSrvInfo();
		info.setSrv_name(input.getOrg_srv_name());
		SvSrvInfo svInfo = svsvc.queryNoDelSvSrvByName(info);
		output.setPendwk_bk_expl(svInfo.getSrv_bk_desc());
		output.setAprov_type(svInfo.getAprov_type());
		output.setSvdeal_type(SVDEAL_TYPE.CHECKAUTH);
		return (OUT) output;
	}


	private OUT doExecute(IN input) {

		logger.info("------Execute doExecute function------");
		logger.debug("生成的流水号*work_seq:[{}]",input.getWork_seq());
		// 获取交易详细日志信息
		updateActionLogTxt(input.getWork_seq(), getLogTxt(input));

		// 调用服务预处理
		runSubActionPre(input);
		// 调用服务
		OUT output = runSubAction(input);
		if(Assert.isEmpty(output.getSvdeal_type())) {
			output.setSvdeal_type(SVDEAL_TYPE.RUNSUCCESS);
		}
		// 调用服务后处理
		runSubActionAft(input, output);
		return output;
	}

	private OUT doAuthExecute(IN input){
		logger.info("------Execute doAuthExecute function------");
		Assert.assertNotEmpty(input.getPend_work_seq(),
				input.PEND_WORK_SEQCN);
		wksvc.checkPendWorkIsExecute(input.getPend_work_seq());
		QueryPendWorkOutputBean<IN> obj = wksvc.queryPendWork(input
				.getPend_work_seq());
		IN val = obj.getObj();
		setParam(val, input);//设置上述接口给val
		//递归调用服务执行
		OUT output = run(val);
		output.setPend_work_seq(output.getWork_seq());//待处理任务执行流水号
		// 执行完成登记调用worksvc服务
		UpdateWorkStateToCompleteBean wkbean = new UpdateWorkStateToCompleteBean();
		wkbean.setDeal_bk_date(input.getDtbs_bk_date());
		wkbean.setDeal_bk_desc(input.getBk_desc());
		wkbean.setDeal_bk_time(input.getDtbs_bk_time());
		wkbean.setDeal_user_id(input.getOrg_user_id());
		wkbean.setPend_work_seq(input.getPend_work_seq());
		wksvc.updateWorkStateToComplete(wkbean);
		return output;
	}

	/**
	 * 调用服务对应的方法，如果服务配置表中未配置方法名称直接调用doAction方法 
	 *  如果服务配置表中定义了方法则优先调用
	 * @author lixl (2014-11-14)
	 * @param input
	 * @return OUT
	 */
	@SuppressWarnings("unchecked")
	private OUT runSubAction(IN input) {
		OUT output;
		String srv_name = input.getOrg_srv_name();

		ClassService svc = CmsDynamicLoadService.getService(srv_name);
		logger.debug("----runSubAction Service =[{}]----", svc);
		if (svc != null) {
			output = doView(svc,input);
		} else {
			output = doAction(input);
		}
		return output;
	}

	@SuppressWarnings("unchecked")
	private OUT doView(ClassService svc, IN input){
		Object[] objs = new Object[1];
		objs[0] = input;
		return (OUT)svc.execute(objs); 
	}

	protected boolean isChk(IN input){
	    Assert.assertNotEmpty(input.getOrg_dept_id(), input.ORG_DEPT_IDCN);
		Assert.assertNotEmpty(input.getOrg_srv_name(), input.ORG_SRV_NAMECN);
	    return apsrv.queryCheckFlag(input.getOrg_dept_id(),input.getOrg_srv_name());
	}

	protected boolean isAuth(IN input){
	    Assert.assertNotEmpty(input.getOrg_dept_id(), input.ORG_DEPT_IDCN);
		Assert.assertNotEmpty(input.getOrg_srv_name(), input.ORG_SRV_NAMECN);
	    return  apsrv.queryRemoteAuthFlag(
				    input.getOrg_dept_id(), input.getOrg_srv_name());
	}

	protected boolean isLocAuth(IN input){
		Assert.assertNotEmpty(input.getOrg_dept_id(), input.ORG_DEPT_IDCN);
		Assert.assertNotEmpty(input.getOrg_srv_name(), input.ORG_SRV_NAMECN);
		return apsrv.queryLocalAuthFlag(
				input.getOrg_dept_id(), input.getOrg_srv_name());
	}


	protected void addLog(IN input) {
		String temp = "";
		int size = 0;
		String[] lst;
		LgLogMfInfo info = new LgLogMfInfo();
		info.setCrt_bk_date(input.getDtbs_bk_date());
		info.setCrt_bk_time(input.getDtbs_bk_time());
		info.setCrt_dept_id(input.getOrg_dept_id());
		info.setCrt_user_id(input.getOrg_user_id());
		info.setOrg_channel_code(input.getOrg_channel_code());
		info.setOrg_rs_code(input.getOrg_rs_code());
		info.setOrg_srv_name(input.getOrg_srv_name());
		info.setOrg_term_no(input.getOrg_term_no());
		info.setOrg_work_code(input.getOrg_work_code());
		info.setPbl_code(input.getPbl_code());
		info.setPend_rs_code(input.getPend_rs_code());
		info.setPend_srv_name(input.getPend_srv_name());
		info.setPend_work_code(input.getPend_work_code());
		info.setPend_work_seq(input.getPend_work_seq());
		info.setPendwk_bk_expl(input.getPendwk_bk_expl());
		info.setWork_seq(input.getWork_seq());
		// 设置待处理数据源列表
		lst = input.getPendlst_soc_name();
		if (lst != null && lst.length > 1) {
			if (size == 1) {
				temp = lst[0];
			}
			for (int i = 0; size > 2 && i < size; i++) {
				temp = temp + ":" + lst[i];
			}
			info.setPend_ary_socname(temp);
		}
		// 设置发起数据源列表
		lst = input.getOrglst_soc_name();
		if (lst != null) {
			size = lst.length;
			temp = "";
			if (size == 1) {
				temp = lst[0];
			}
			for (int i = 0; size > 2 && i < size; i++) {
				temp = temp + ":" + lst[i];
			}
			info.setOrg_ary_socname(temp);
		}
		
		info.setSr_yn_flag(YN_FLAG.NO);
		info.setCrt_dept_cn_name(input.getOrgdept_cn_name());
		info.setCrt_user_cn_name(input.getOrguser_cn_name());
		SvSrvInfo srvInfo=new SvSrvInfo();
		srvInfo.setSrv_name(input.getOrg_srv_name());
		srvInfo=svsvc.queryNoDelSvSrvByName(srvInfo);
		info.setOrg_srv_bk_desc(srvInfo.getSrv_bk_desc());
		info.setLog_level(srvInfo.getLog_level());
		actlog.addActionLog(info);
		if(Assert.isEmpty(input.getPendwk_bk_expl())){
			input.setPendwk_bk_expl(srvInfo.getSrv_bk_desc());
		}
	}

	/**
	 * 更新任务状态
	 * @author lixl (2014-11-14)
	 * @param flag
	 * @return int
	 */
	protected void updateActionState(String work_seq, YN_FLAG flag) {
		LgLogMfInfo info = new LgLogMfInfo();
		info.setWork_seq(work_seq);
		info.setSr_yn_flag(flag);
		actlog.updateLogState(info);
	}
	
	private void updateActionLogTxt(String work_seq, String logTxt) {
		LgLogMfInfo info = new LgLogMfInfo();
		info.setWork_seq(work_seq);
		info.setLog_txt(logTxt);
		actlog.updateLogTxt(info);
	}

	private void setParam(IN spval, IN input){
		//spval.setDtbs_bk_date(input.getDtbs_bk_date());
		//spval.setDtbs_bk_time(input.getDtbs_bk_time());
		spval.setOrg_channel_code(input.getOrg_channel_code());
		spval.setOrg_user_id(input.getOrg_user_id());
		spval.setOrg_dept_id(input.getOrg_dept_id());
		spval.setOrg_rs_code(input.getOrg_rs_code());
		spval.setOrg_srv_name(input.getOrg_srv_name());
		spval.setOrg_term_no(input.getOrg_term_no());
		spval.setOrg_work_code(input.getOrg_work_code());
		spval.setSubmit_type(SUBMIT_TYPE.RUN);
		//spval.setWork_seq(input.getWork_seq());
	}
	
}
