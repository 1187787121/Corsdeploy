/**
 * Title: UpdateProjectAction.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: xuph
 * @version: 1.0
 * @date: 2016年10月31日
 */
package com.wk.cd.build.en.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.build.en.bean.UpdateProjectInputBean;
import com.wk.cd.build.en.bean.UpdateProjectOutputBean;
import com.wk.cd.build.en.dao.CeProjectDaoService;
import com.wk.cd.build.en.info.CeProjectInfo;
import com.wk.cd.build.en.service.ProjectPublicService;
import com.wk.cd.common.util.Assert;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 修改项目信息
 * @author xuph
 */
public class UpdateProjectAction extends ActionBasic<UpdateProjectInputBean, UpdateProjectOutputBean>{
	@Inject private CeProjectDaoService ceProjectDaoService;
	@Inject private ProjectPublicService projectPublicService;
	@Inject private ActionLogPublicService lgsvc;
	private static final Log logger = LogFactory.getLog();
	/** 
	 * Description: 
	 * @param input
	 * @return 
	 */
	@Override
	protected UpdateProjectOutputBean doAction(UpdateProjectInputBean input) {
		logger.info("------------------UpdateProjectAction Begin---------------");
		UpdateProjectOutputBean output = new UpdateProjectOutputBean();
		String project_name = input.getProject_name();
		String project_short_name = input.getProject_short_name();
		String sys_name = input.getSys_name();
		String project_bk_desc = input.getProject_bk_desc();
		//非空校检
		Assert.assertNotEmpty(project_name, UpdateProjectInputBean.PROJECT_NAMECN);
		Assert.assertNotEmpty(project_short_name, UpdateProjectInputBean.PROJECT_SHORT_NAMECN);
		Assert.assertNotEmpty(project_bk_desc, UpdateProjectInputBean.PROJECT_BK_DESCCN);
		//合法性校检
		projectPublicService.checkProjectNameIsNotExist(project_name);
		// 项目信息更新
		CeProjectInfo proj_info = new CeProjectInfo();
		proj_info.setProject_name(project_name);
		CeProjectInfo proj_update = ceProjectDaoService.getInfoByKeyForUpdate(proj_info);
		proj_update.setProject_short_name(project_short_name);
		proj_update.setProject_bk_desc(project_bk_desc);
		proj_update.setSys_name(sys_name);
		proj_update.setModify_bk_date(input.getDtbs_bk_date());
		proj_update.setModify_bk_time(input.getDtbs_bk_time());
		proj_update.setModify_user_id(input.getOrg_user_id());
		// 更新项目信息
		ceProjectDaoService.updateProjectInfo(proj_update);
		logger.info("------------------UpdateProjectAction End---------------");
		return output;
	}

	/** 
	 * Description: 
	 * @param arg0
	 * @return 
	 */
	@Override
	protected String getLogTxt(UpdateProjectInputBean input) {
		List<String> log_lst = new ArrayList<String>();
		log_lst.add("project_name: "+input.getProject_name());
		return lgsvc.getLogTxt("修改项目", log_lst);
	}

}
