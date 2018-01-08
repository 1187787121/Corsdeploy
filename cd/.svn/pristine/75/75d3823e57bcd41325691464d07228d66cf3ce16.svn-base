/**
 * Title: DeleteProjectAction.java
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

import com.wk.cd.build.en.bean.DeleteProjectInputBean;
import com.wk.cd.build.en.bean.DeleteProjectOutputBean;
import com.wk.cd.build.en.dao.CeProjectDaoService;
import com.wk.cd.build.en.service.ProjectPublicService;
import com.wk.cd.common.util.Assert;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 删除项目
 * @author xuph
 */
public class DeleteProjectAction extends ActionBasic<DeleteProjectInputBean, DeleteProjectOutputBean>{
	@Inject private CeProjectDaoService ceProjectDaoService;
	@Inject private ProjectPublicService projectPublicService;
	@Inject private ActionLogPublicService lgsvc;
	private static final Log logger = LogFactory.getLog();
	/** 
	 * Description:  删除项目操作
	 * @param input
	 * @return 
	 */
	@Override
	protected DeleteProjectOutputBean doAction(DeleteProjectInputBean input) {
		logger.info("-----------------DeleteProjectAction Begin-------------------");
		DeleteProjectOutputBean output = new DeleteProjectOutputBean();
		String project_name = input.getProject_name();
		//非空校检
		Assert.assertNotEmpty(project_name, DeleteProjectInputBean.PROJECT_NAMECN);
		//合法性校检
		projectPublicService.checkProjectNameIsNotExist(project_name);
		projectPublicService.checkProjectWithEnv(project_name);
		//删除项目信息
		ceProjectDaoService.deleteProjectInfo(project_name);
		logger.info("-----------------DeleteProjectAction End-------------------");
		return output;
	}

	/** 
	 * Description: 
	 * @param arg0
	 * @return 
	 */
	@Override
	protected String getLogTxt(DeleteProjectInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add("project_name=" + input.getProject_name());
		return lgsvc.getLogTxt("删除项目", lst_val);
	}

}
