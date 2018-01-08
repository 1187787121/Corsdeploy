/**
 * Title: PageTaskForAppAction.java
 * File Description: 分页查询环境任务列表（App端）
 * @copyright: 2017
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2017年2月28日
 */
package com.wk.cd.build.ea.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.build.ea.bean.EnvTaskAppBean;
import com.wk.cd.build.ea.bean.PageTaskForAppInputBean;
import com.wk.cd.build.ea.bean.PageTaskForAppOutputBean;
import com.wk.cd.build.ea.dao.EnvTaskDaoService;
import com.wk.cd.build.ea.dao.PgProgramDaoService;
import com.wk.cd.build.ea.info.EnvTaskInfo;
import com.wk.cd.build.ea.info.PgProgramInfo;
import com.wk.cd.build.en.dao.CeProjectDaoService;
import com.wk.cd.build.en.info.CeProjectInfo;
import com.wk.cd.common.util.Assert;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.system.us.service.UsGetUserInfoService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 分页查询环境任务列表（App端）
 * @author Xul
 */
public class PageTaskForAppAction extends ActionBasic<PageTaskForAppInputBean, PageTaskForAppOutputBean>{
	
	@Inject private UsGetUserInfoService usGetUserInfoService;
	@Inject private PgProgramDaoService pgProgramDaoService;
	@Inject private CeProjectDaoService ceProjectDaoService;
	@Inject private EnvTaskDaoService envTaskDaoService;
	@Inject private ActionLogPublicService lgsvc;
	private static final Log logger = LogFactory.getLog();
	
	/** 
	 * Description: 分页查询环境任务列表（App端）
	 * @param input
	 * @return 
	 */
	@Override
	protected PageTaskForAppOutputBean doAction(PageTaskForAppInputBean input) {
		logger.info("----------------------PageTaskForAppAction Begin -------------");
		PageTaskForAppOutputBean output = new PageTaskForAppOutputBean();
		
		//非空校验
		String env_name = input.getEnv_name();
		int start_recd = input.getStart_recd();
		int limit_recd = input.getLimit_recd();
		Assert.assertNotEmpty(env_name, PageTaskForAppInputBean.ENV_NAMECN);
		Assert.assertNotEmpty(start_recd, PageTaskForAppInputBean.START_RECDCN);
		Assert.assertNotEmpty(limit_recd, PageTaskForAppInputBean.LIMIT_RECDCN);
		
		List<EnvTaskAppBean> env_task_list = new ArrayList<EnvTaskAppBean>();
		//根据环境查询任务列表
		List<EnvTaskInfo> task_list = envTaskDaoService.pageTaskInfoByEnvName(env_name, start_recd, limit_recd);
		if(!Assert.isEmpty(task_list)){
			for(EnvTaskInfo info : task_list){
				EnvTaskAppBean bean = EnvTaskAppBean.copy(info);
				//获取方案名称
				if(!Assert.isEmpty(info.getProg_id())){
					PgProgramInfo pg_info = pgProgramDaoService.getInfoByKey(info.getProg_id());
					if(!Assert.isEmpty(pg_info)){
						bean.setProgram_name(pg_info.getProg_name());
					}
				}
				//获取项目简称
				if(!Assert.isEmpty(info.getProject_name())){
					CeProjectInfo pj_info = ceProjectDaoService.getInfoByProjectName(info.getProject_name());
					if(!Assert.isEmpty(pj_info)){
						bean.setProject_short_name(pj_info.getProject_short_name());
					}
				}
				//获取用户中文名
				if(!Assert.isEmpty(info.getCrt_user_id())){
					bean.setCrt_user_cname(usGetUserInfoService.getUserCnNameByUserId(info.getCrt_user_id()));
				}
				if(!Assert.isEmpty(info.getExe_user_id())){
					bean.setExe_user_cname(usGetUserInfoService.getUserCnNameByUserId(info.getExe_user_id()));
				}
				env_task_list.add(bean);
			}
		}
		
		output.setAll_recd(envTaskDaoService.countTaskInfoByEnvName(env_name));
		output.setEnv_task_list(env_task_list);
		logger.info("----------------------PageTaskForAppAction End -------------");
		return output;
	}

	/** 
	 * Description: 分页查询环境任务列表（App端）
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(PageTaskForAppInputBean input) {
		List<String> logs = new ArrayList<String>();
		logs.add("环境名称：" + input.getEnv_name());
		return lgsvc.getLogTxt("分页查询环境任务列表（App端）", logs);
	}

}
