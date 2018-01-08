/**
 * Title: ProjectPublicService.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: xuph
 * @version: 1.0
 * @date: 2016��10��31��
 */
package com.wk.cd.build.en.service;

import java.util.List;

import com.wk.cd.build.ea.dao.EnvTagStorageDaoService;
import com.wk.cd.build.ea.dao.EnvTaskDaoService;
import com.wk.cd.build.ea.info.EnvTagStorageInfo;
import com.wk.cd.build.ea.info.EnvTaskInfo;
import com.wk.cd.build.en.dao.CeEnvironmentDaoService;
import com.wk.cd.build.en.dao.CeProjectDaoService;
import com.wk.cd.build.exc.AddProjectIsExistException;
import com.wk.cd.build.exc.EnvTagStorgProjIsExistException;
import com.wk.cd.build.exc.EnvTaskProjIsExistException;
import com.wk.cd.build.exc.UpdateProjectIsNotExistException;
import com.wk.cd.common.util.Assert;
import com.wk.lang.Inject;

/**
 * Class Description:
 * @author xuph
 */
public class ProjectPublicService {
	@Inject
	private EnvTaskDaoService envTaskDaoSrv;
	@Inject
	private CeProjectDaoService ceProjectDaoService;
	@Inject
	private EnvTagStorageDaoService envTagStorageDaoSrv;
	@Inject
	private CeEnvironmentDaoService ceEnvironmentDaoService;

	/**
	 * Description: У����Ŀ����Ƿ���ڣ������׳��쳣
	 * @param project_name
	 */
	public void checkProjectNameIsExist(String project_name) {
		if (ceProjectDaoService.countProjectName(project_name) > 0) {
			throw new AddProjectIsExistException().addScene("PROJECT", project_name);
		}

	}

	/**
	 * Description: У����Ŀ����Ƿ���ڣ��������׳��쳣
	 * @param project_name
	 */
	public void checkProjectNameIsNotExist(String project_name) {
		if (ceProjectDaoService.countProjectName(project_name) <= 0) {
			throw new UpdateProjectIsNotExistException().addScene("PROJECT", project_name);
		}
	}

	/** 
	 * Description: У����Ŀ�Ƿ���ڹ�������⣬���ھ��׳��쳣
	 * @param project_name 
	 */
	public void checkProjectWithEnv(String project_name) {
		//У�컷�������Ƿ��������Ŀ
		List<EnvTaskInfo> task_list = envTaskDaoSrv.queryTaskInfoByProName(project_name);
		if(!Assert.isEmpty(task_list)){
			throw new EnvTaskProjIsExistException().addScene("PROJ", project_name);
		}
		//У�컷������Ƿ��������Ŀ
		List<EnvTagStorageInfo> storage_list = envTagStorageDaoSrv.queryStorageInfoByProName(project_name);
		if(!Assert.isEmpty(storage_list)){
			throw new EnvTagStorgProjIsExistException().addScene("PROJ", project_name);
		}
	}

}
