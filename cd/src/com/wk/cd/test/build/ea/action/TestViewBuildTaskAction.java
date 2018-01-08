/**
 * Title: TestViewConfigModAction.java
 * File Description: 测试配置文件相关服务
 * @copyright: 2016
 * @company: CORSWORK
 * @author: chiss
 * @version: 1.0
 * @date: 2016年12月10日
 */
package com.wk.cd.test.build.ea.action;

import java.util.Arrays;

import com.wk.Controller;
import com.wk.beans.Injector;
import com.wk.cd.build.ea.action.ViewBuildTaskAction;
import com.wk.cd.build.ea.bean.BuildExeScriptBean;
import com.wk.cd.build.ea.bean.BuildScriptBean;
import com.wk.cd.build.ea.bean.EnvModFileBean;
import com.wk.cd.build.ea.bean.TargetPackageBean;
import com.wk.cd.build.ea.bean.ViewBuildTaskInputBean;
import com.wk.cd.build.ea.bean.ViewBuildTaskOutputBean;
import com.wk.cd.build.ea.info.UuFilelistInfo;
import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.CFG_TYPE;
import com.wk.cd.enu.SCRIPT_TYPE;
import com.wk.db.DBSource;
import com.wk.db.Session;
import com.wk.test.TestCase;

/**
 * Class Description: 测试配置文件相关服务
 * @author chiss
 */
public class TestViewBuildTaskAction extends TestCase{
	
	private  DBSource db = DBSource.get();
	private  Session session;
	private Injector inject=Controller.getInstance().getInjector();
	private ViewBuildTaskAction sc = inject.getBean(ViewBuildTaskAction.class);
	
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
		ViewBuildTaskInputBean input = new ViewBuildTaskInputBean();
		input.setWork_id("TK201712270027");
		ViewBuildTaskOutputBean output = sc.getBuildDeployInfo(input);
		
	}
	
	
	// 查看配置文件改动列表
	public void testGetConfigModList(){
		ViewBuildTaskInputBean input = new ViewBuildTaskInputBean();
		input.setEnv_name("XulEnv");
		input.setServer_ip("10.1.1.240");
		input.setWork_id("TK201701070005");
		input.setCfg_type(CFG_TYPE.NORMAL);
//		input.setSoc_name("228ssh");
//		input.setRelative_path("/home/front/");
		ViewBuildTaskOutputBean output = sc.getConfigModList(input);
//		System.out.println("待修改修改文件列表");
//		if(!Assert.isEmpty(output.getPend_modify_list())) {
//			for(EnvModFileBean bean : output.getPend_modify_list()){
//				System.out.println(bean.getFull_path());
//				System.out.println(bean.getDir_yn_flag());
//				System.out.println(bean.isDel_mod_flag());
//			}
//		}
		System.out.println("修改文件列表");
		if(!Assert.isEmpty(output.getModify_list())) {
			for(EnvModFileBean bean : output.getModify_list()){
				System.out.println(bean.getFull_path());
				System.out.println(bean.getDir_yn_flag());
				System.out.println(bean.isDel_mod_flag());
			}
		}
		System.out.println("删除文件列表");
		if(!Assert.isEmpty(output.getDelete_list())) {
			for(EnvModFileBean bean : output.getDelete_list()){
				System.out.println(bean.getFull_path());
				System.out.println(bean.getDir_yn_flag());
				System.out.println(bean.isDel_mod_flag());
			}
		}
	}
	
	// 查看应用系统配置文件列表
	public void testgetCfgNameList(){
		ViewBuildTaskInputBean input = new ViewBuildTaskInputBean();
		input.setEnv_name("chissEnv");
		input.setSoc_name("228ftp");
		input.setRelative_path("/home/front");
		input.setCfg_type(CFG_TYPE.NORMAL);
		ViewBuildTaskOutputBean output = sc.getCfgNameList(input);
		if(!Assert.isEmpty(output.getCfg_name_list())) {
			for (String name : output.getCfg_name_list()) {
				System.out.println(name.toString());
			}
		}
	}
	
//	// 查看配置文件列表
//	public void testQueryDirectoryList(){
//		ViewBuildTaskInputBean input = new ViewBuildTaskInputBean();
//		input.setSoc_name("228ftp");
//		input.setRelative_path("/home/front/");
//		input.setMod_flag(true);
//		input.setCfg_type(CFG_TYPE.TOMCAT);
//		ViewBuildTaskOutputBean output = sc.queryDirectoryList(input);
//		if(!Assert.isEmpty(output.getFile_list_bean())) {
//			for (FileListBean bean : output.getFile_list_bean()) {
//				System.out.println(bean.toString());
//			}
//		}
//	}
	
	//查看环境构建部署信息
	public void testGetBuildDeployInfo(){
		ViewBuildTaskInputBean input = new ViewBuildTaskInputBean();
		input.setWork_id("TK201701070005");
		ViewBuildTaskOutputBean output = sc.getBuildDeployInfo(input);
		System.out.println(output.getTask_status());
		System.out.println(output.getExe_result());
		System.out.println(output.getExe_method());
		System.out.println(output.getTemplate_name());
//		for(PhasePublishBean bean : output.getPhases()){
//			System.out.println("----------阶段----------");
//			System.out.println(bean.getCn_name());
//			System.out.println(bean.getPhase_no());
//			System.out.println(bean.getGen_flag());
//			System.out.println(bean.getImpl_type());
//			for(SrvSocBean bean2 : bean.getSrv_soc()){
//				System.out.println(bean2.getExe_cn_name());
//				System.out.println(bean2.getExe_ip());
//				System.out.println(bean2.getExe_server_name());
//				System.out.println(bean2.getExe_soc_name());
//			}
//		}
//		for(UuParamInfo info : output.getParam_list()){
//			System.out.println("----------参数----------");
//			System.out.println(info.getParam_cn_name());
//			System.out.println(info.getParam_bk_desc());
//			System.out.println(info.getParam_name());
//			System.out.println(info.getParam_value());
//			System.out.println(info.getParam_modify_flag());
//		}
	}
	
	//监控构建应用部署执行进度
	public void testMonitorBuildDeploy(){
		ViewBuildTaskInputBean input = new ViewBuildTaskInputBean();
		input.setWork_id("TK201801020006");
		ViewBuildTaskOutputBean output = sc.monitorBuildDeploy(input);
		System.out.println(output.getExe_phase());
		
		
//		System.out.println(output.getStart_bk_tm());
//		System.out.println(output.getEnd_bk_tm());
//		System.out.println(output.getTask_status().getCname());
////		System.out.println(output.getExe_result().getCname());
//		System.out.println(output.getExelog_bk_path());
//		System.out.println("当前阶段："+output.getExe_phase());
//		for(BuildMonPhaseBean bean : output.getBuild_phase_list()){
//			System.out.println("模板层----------------------------");
//			System.out.println(bean.getPhase_id());
//			System.out.println(bean.getPhase_name());
//			System.out.println(bean.getTask_exe_result());
//			System.out.println(bean.getExe_status().getCname());
//			for(InstPhaseBean bean2 : bean.getInst_phase_list()){
//				System.out.println("实例层----------------------------");
//				System.out.println(bean2.getExe_status().getCname());
//				System.out.println(bean2.getTask_exe_result());
//			}
//		}
	}
	
	//查看环境构建任务
	public void testGetBuildInfo(){
		ViewBuildTaskInputBean input = new ViewBuildTaskInputBean();
		input.setWork_id("TK201701070005");
		ViewBuildTaskOutputBean output = sc.getBuildInfo(input);
		System.out.println(output.getEnv_name());
		System.out.println(output.getTask_bk_desc());
		System.out.println(output.getProject_name());
		System.out.println(output.getBuild_step_id());
		System.out.println(output.getExelog_bk_path());
	}
	
	//跳转下一步骤
	public void testGotoNextStep(){
		ViewBuildTaskInputBean input = new ViewBuildTaskInputBean();
		input.setWork_id("TK201612120001");
		input.setBuild_step_id(2);
		ViewBuildTaskOutputBean output = sc.gotoNextStep(input);
		System.out.println(output.getBuild_step_id());
	}
	
	public void testMonitorExeScript(){
		ViewBuildTaskInputBean input = new ViewBuildTaskInputBean();
		input.setWork_id("TK201612130041");
		input.setScript_bk_seq(1);
		input.setScript_type(SCRIPT_TYPE.OPERATION);
		ViewBuildTaskOutputBean output = sc.monitorBuildScript(input);
		System.out.println(output.getAll_phases());
		System.out.println(output.getExe_phase());
	//	System.out.println(output.getExe_result().getCname());
	}
	
	// 查看构建信息
	public void testGetBuildScriptMsg(){
		ViewBuildTaskInputBean input = new ViewBuildTaskInputBean();
		input.setWork_id("TK201701070005");
		input.setScript_type_list(new int[]{3});;
		ViewBuildTaskOutputBean output = sc.getBuildScriptMsg(input);
		for (BuildScriptBean bean : output.getScript_list()) {
			for(BuildExeScriptBean exeBean: bean.getScript_list()) {
				System.out.println("work_id:  " + exeBean.getWork_id());
			}
		}
	}
	
	// 查看构建脚本信息
	public void testQueryBuildScriptMsg(){
		ViewBuildTaskInputBean input = new ViewBuildTaskInputBean();
		input.setWork_id("TK201702170009");
		input.setScript_bk_seq(5);
		input.setScript_type(SCRIPT_TYPE.OPERATION);
		ViewBuildTaskOutputBean output = sc.queryBuildScriptMsg(input);
		System.out.println(Arrays.asList(output.getScript_text()).get(0).toString());
	}
	
	// 获取构建清单及投产包
	public void testGetBuildListAndPac(){
		ViewBuildTaskInputBean input = new ViewBuildTaskInputBean();
		input.setWork_id("TK201702180017");
		input.setCe_server_name("svn");
		input.setSoc_name("240svn");
		input.setVer_root_path("/CorsManager/version/list/");
		ViewBuildTaskOutputBean output = sc.getBuildListAndPac(input);
		System.out.println(output.getList_list().get(0).getDownload_path());
		for(TargetPackageBean bean : output.getPac_list()){
			System.out.println(bean.getPackage_name());
			System.out.println(bean.getDownload_path());
			for (UuFilelistInfo info : bean.getFile_list()) {
				System.out.println(info.getFile_name());
				System.out.println(info.getFile_path());
			}
		}
		System.out.println(output.getList_list().get(0).getPackage_name());
	}
	
	public void testQueryBuildListAndPacInfo(){
		ViewBuildTaskInputBean input = new ViewBuildTaskInputBean();
		input.setWork_id("TK201801040004");
		ViewBuildTaskOutputBean output = sc.queryBuildListAndPacInfo(input);
//		List<PhaseParam> param_list = new ArrayList<PhaseParam>();
//		PhaseParam info1 = new PhaseParam();
//		info1.setParam_name("upload_dir");
//		info1.setParam_type(PARAM_TYPE.PJPARAM);
//		info1.setParam_value("/CorsManager/version/list/app");
//		info1.setModify_flag(MODIFY_FLAG.YES);
//		param_list.add(info1);
//		PhaseParam info2 = new PhaseParam();
//		info2.setParam_name("db_name");
//		info2.setParam_type(PARAM_TYPE.PDDPARAM);
//		info2.setParam_value("/CorsManager/version/list/db");
//		info2.setModify_flag(MODIFY_FLAG.YES);
//		param_list.add(info2);
//		input.setParam_list(param_list);
//		input.setWork_id("TK201702200033");
//		ViewBuildTaskOutputBean output = sc.queryBuildListAndPacInfo(input);
//		System.out.println(output.getCe_server_name());
//		System.out.println(output.getSoc_name());
//		System.out.println(output.getVer_root_path());
//		System.out.println(output.isChange_flag());
////		System.out.println(output.getList_list().get(0).getDownload_path());
////		for(TargetPackageBean bean : output.getPac_list()){
////			System.out.println(bean.getPackage_name());
////			System.out.println(bean.getDownload_path());
////			for (UuFilelistInfo info : bean.getFile_list()) {
////				System.out.println(info.getFile_name());
////				System.out.println(info.getFile_path());
////			}
////		}
////		System.out.println(output.getList_list().get(0).getPackage_name());
	}
	
}
