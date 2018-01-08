/**
 * Title: ViewProjectAction.java
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

import com.wk.cd.build.ea.dao.EnvTaskDaoService;
import com.wk.cd.build.ea.dao.UuFilelistDaoService;
import com.wk.cd.build.ea.info.EnvTaskInfo;
import com.wk.cd.build.en.bean.CeProjectBean;
import com.wk.cd.build.en.bean.EnvTaskBean;
import com.wk.cd.build.en.bean.ViewProjectInputBean;
import com.wk.cd.build.en.bean.ViewProjectOutputBean;
import com.wk.cd.build.en.dao.CeEnvironmentDaoService;
import com.wk.cd.build.en.dao.CeProjectDaoService;
import com.wk.cd.build.en.info.CeEnvironmentInfo;
import com.wk.cd.build.en.info.CeProjectInfo;
import com.wk.cd.build.en.service.EnvironmentPublicService;
import com.wk.cd.build.en.service.ProjectPublicService;
import com.wk.cd.common.util.Assert;
import com.wk.cd.service.IViewActionBasic;
import com.wk.lang.Inject;

/**
 * Class Description:
 * @author xuph
 */
public class ViewProjectAction
		extends IViewActionBasic<ViewProjectInputBean, ViewProjectOutputBean> {

	@Inject
	private ProjectPublicService projectPublicService;
	@Inject
	private CeProjectDaoService ceProjectDaoService;
	@Inject
	private CeEnvironmentDaoService ceEnvironmentDaoService;
	@Inject
	private EnvironmentPublicService environmentPublicService;
	@Inject
	private EnvTaskDaoService envTaskDaoService;
	@Inject
	private UuFilelistDaoService uuFilelistDaoService;

	/**
	 * Description: 获得单个项目信息
	 * @param input
	 * @return
	 */
	public ViewProjectOutputBean getSingleProject(ViewProjectInputBean input) {
		ViewProjectOutputBean output = new ViewProjectOutputBean();
		String project_name = input.getProject_name();
		// 非空校检
		Assert.assertNotEmpty(project_name, ViewProjectInputBean.PROJECT_NAMECN);
		// 合法性校检
		projectPublicService.checkProjectNameIsNotExist(project_name);
		CeProjectInfo proj_info = ceProjectDaoService.getInfoByProjectName(project_name);
		if (!Assert.isEmpty(proj_info)) {
			CeProjectBean proj_bean = new CeProjectBean();
			proj_bean.setProject_name(proj_info.getProject_name());
			proj_bean.setProject_short_name(proj_info.getProject_short_name());
			proj_bean.setProject_bk_desc(proj_info.getProject_bk_desc());
			proj_bean.setSys_cn_name(proj_info.getSys_name());
			output.setProj_bean(proj_bean);
		}

		return output;
	}

	/**
	 * Description: 根据环境查询所有项目列表
	 * @param input
	 * @return
	 */
	public ViewProjectOutputBean queryProjByEnv(ViewProjectInputBean input) {
		ViewProjectOutputBean output = new ViewProjectOutputBean();
		String env_name = input.getEnv_name();
		// 非空校验
		Assert.assertNotEmpty(env_name, ViewProjectInputBean.ENV_NAMECN);
		// 合法性校验
		environmentPublicService.checkEnvNameIsExist(env_name);
		CeEnvironmentInfo env_info = ceEnvironmentDaoService.getInfoByEnvName(env_name);
		List<CeProjectInfo> pro_info_list = ceProjectDaoService.getInfoBySysName(env_info.getSys_name());
		List<CeProjectBean> proj_list = new ArrayList<CeProjectBean>();
		if (!Assert.isEmpty(pro_info_list)) {
			for (CeProjectInfo ceProjectInfo : pro_info_list) {
				CeProjectBean proBean = new CeProjectBean();
				proBean.setProject_name(ceProjectInfo.getProject_name());
				proBean.setProject_short_name(ceProjectInfo.getProject_short_name());
				proj_list.add(proBean);
			}
		}
		output.setProj_list(proj_list);
		return output;
	}

	/**
	 * Description: 查看单个项目的环境任务列表
	 * @param input
	 * @return
	 */
	public ViewProjectOutputBean queryTaskByProject(ViewProjectInputBean input) {
		ViewProjectOutputBean output = new ViewProjectOutputBean();
		String project_name = input.getProject_name();
		// 非空校检
		Assert.assertNotEmpty(project_name, ViewProjectInputBean.PROJECT_NAMECN);
		// 合法性校检
		projectPublicService.checkProjectNameIsNotExist(project_name);
		List<EnvTaskBean> task_info_list = new ArrayList<EnvTaskBean>();
		// 获取列表信息
		List<EnvTaskInfo> env_task_list = envTaskDaoService.queryTaskInfoByProName(project_name);
		if (!Assert.isEmpty(env_task_list)) {
			for (EnvTaskInfo taskInfo : env_task_list) {
				EnvTaskBean bean = new EnvTaskBean();
				bean.setTask_type(taskInfo.getTask_type());
				bean.setWork_id(taskInfo.getWork_id());
				bean.setEnv_name(taskInfo.getEnv_name());
				bean.setCode_ver_num(taskInfo.getCode_ver_num());
				String tappac_list_uuid = taskInfo.getTagpac_list_uuid();
				// 获取目标包名列表
				if (!Assert.isEmpty(tappac_list_uuid)) {
					List<String> package_list = uuFilelistDaoService.queryPacList(tappac_list_uuid);
					bean.setPackage_list(package_list);
				}
				bean.setTask_status(taskInfo.getTask_status());
				bean.setExe_result(taskInfo.getExe_result());
				task_info_list.add(bean);
			}
		}
		output.setTask_info_list(task_info_list);
		return output;
	}
}
