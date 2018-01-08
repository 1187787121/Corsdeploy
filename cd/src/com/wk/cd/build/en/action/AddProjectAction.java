/**
 * Title: AddProjectAction.java
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

import com.wk.cd.build.en.bean.AddProjectInputBean;
import com.wk.cd.build.en.bean.AddProjectOutputBean;
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
 * Class Description: 新增项目
 * @author xuph
 */
public class AddProjectAction extends ActionBasic<AddProjectInputBean, AddProjectOutputBean>{
	@Inject private CeProjectDaoService ceProjectDaoService;
	@Inject private ProjectPublicService projectPublicService;
	@Inject private ActionLogPublicService lgsvc;
	private static final Log logger = LogFactory.getLog();
	/** 
	 * Description: 新增项目操作
	 * @param input
	 * @return 
	 */
	@Override
	protected AddProjectOutputBean doAction(AddProjectInputBean input) {
		logger.info("-------------------AddProjectAction Begin----------------");
		AddProjectOutputBean output = new AddProjectOutputBean();
		String project_name = input.getProject_name();
		String project_short_name = input.getProject_short_name();
		String sys_name = input.getSys_name();
		String project_bk_desc = input.getProject_bk_desc();
		//非空校检
		Assert.assertNotEmpty(project_name, AddProjectInputBean.PROJECT_NAMECN);
		Assert.assertNotEmpty(project_short_name, AddProjectInputBean.PROJECT_SHORT_NAMECN);
		Assert.assertNotEmpty(project_bk_desc, AddProjectInputBean.PROJECT_BK_DESCCN);
		//合法性校检
		projectPublicService.checkProjectNameIsExist(project_name);
		// 数据准备
		CeProjectInfo proj_info = new CeProjectInfo();
		proj_info.setProject_name(project_name);
		proj_info.setProject_short_name(project_short_name);
		proj_info.setProject_bk_desc(project_bk_desc);
		proj_info.setSys_name(sys_name);
		proj_info.setCreate_bk_date(input.getDtbs_bk_date());
		proj_info.setCreate_bk_time(input.getDtbs_bk_time());
		proj_info.setCreate_user_id(input.getOrg_user_id());
		// 数据插入
		ceProjectDaoService.insertInfo(proj_info);
		logger.info("-------------------AddProjectAction End----------------");
		return output;
	}

	/** 
	 * Description: 
	 * @param arg0
	 * @return 
	 */
	@Override
	protected String getLogTxt(AddProjectInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add("project_name=" + input.getProject_name());
		lst_val.add("project_short_name=" + input.getProject_short_name());
		return lgsvc.getLogTxt("新增项目", lst_val);
	}

}
