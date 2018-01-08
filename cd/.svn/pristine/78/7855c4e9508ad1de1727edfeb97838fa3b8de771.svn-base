/**
 * Title: ViewTaskStorageAction.java
 * File Description: 查询任务表和入库表的信息
 * @copyright: 2016
 * @company: CORSWORK
 * @author: chiss
 * @version: 1.0
 * @date: 2016年11月25日
 */
package com.wk.cd.build.ea.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.wk.cd.build.ea.bean.TaskSrorageBean;
import com.wk.cd.build.ea.bean.ViewTaskStorageInputBean;
import com.wk.cd.build.ea.bean.ViewTaskStorageOutputBean;
import com.wk.cd.build.ea.dao.EnvTagStorageDaoService;
import com.wk.cd.build.ea.dao.EnvTaskDaoService;
import com.wk.cd.build.ea.dao.UuFilelistDaoService;
import com.wk.cd.build.ea.info.EnvTagStorageInfo;
import com.wk.cd.build.ea.info.EnvTaskInfo;
import com.wk.cd.build.ea.service.EnvTaskPublicService;
import com.wk.cd.build.en.dao.CeEnvironmentDaoService;
import com.wk.cd.build.en.service.EnvironmentPublicService;
import com.wk.cd.build.en.service.ProjectPublicService;
import com.wk.cd.common.util.Assert;
import com.wk.cd.service.IViewActionBasic;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 查询任务表和入库表的信息
 * @author chiss
 */
public class ViewTaskStorageAction extends IViewActionBasic<ViewTaskStorageInputBean, ViewTaskStorageOutputBean>{
	@Inject 
	private EnvironmentPublicService environmentPublicService;
	@Inject
	private EnvTaskDaoService envTaskDaoService;
	@Inject
	private EnvTagStorageDaoService envTagStorageDaoService;
	@Inject
	private ProjectPublicService projectPublicService;
	@Inject
	private UuFilelistDaoService uuFilelistDaoService;
	@Inject
	private CeEnvironmentDaoService ceEnvironmentDaoService;
	@Inject
	private EnvTaskPublicService envTaskPublicService;
	private static final Log logger = LogFactory.getLog();
	
	/**
	 * Description: 查询任务表和入库表的信息根据环境名称
	 * @param input
	 * @return
	 */
	public ViewTaskStorageOutputBean queryTaskStorage(ViewTaskStorageInputBean input){
		logger.info("-----------queryTaskStorage begin----------");
		ViewTaskStorageOutputBean output = new ViewTaskStorageOutputBean();
		String env_name = input.getEnv_name();
		int num = input.getNum();
		//非空校验
		Assert.assertNotEmpty(env_name, ViewTaskStorageInputBean.ENV_NAMECN);
		Assert.assertNotEmpty(num==0?null:num, ViewTaskStorageInputBean.ENV_NAMECN);
		
		//合法性校验
		environmentPublicService.checkEnvNameIsExist(env_name);
		
		List<TaskSrorageBean> task_storage_list1 = new ArrayList<TaskSrorageBean>();
		//任务表
		List<EnvTaskInfo> task_list = envTaskDaoService.queryTaskInfoByEnvName(env_name);
		//入库表
		List<EnvTagStorageInfo> storage_list = envTagStorageDaoService.queryStorageInfoByEnvName(env_name);
		if(!Assert.isEmpty(task_list)) {
			for (EnvTaskInfo taskInfo : task_list) {
				TaskSrorageBean bean = new TaskSrorageBean();
				bean.setTask_time(taskInfo.getCrt_bk_date().toString()+" "+taskInfo.getCrt_bk_time().toString());
				bean.setTask_id(taskInfo.getWork_id());
				if(!Assert.isEmpty(taskInfo.getTask_type())) {
					int type = envTaskPublicService.getValue(taskInfo.getTask_type());
					bean.setTask_type(type);
				}
				bean.setCode_ver_num(taskInfo.getCode_ver_num());
				bean.setProject_name(taskInfo.getProject_name());
				if(!Assert.isEmpty(taskInfo.getExe_result())) {
					bean.setResult(taskInfo.getExe_result().getValue());
				}
				if(!Assert.isEmpty(taskInfo.getTask_status())) {
					bean.setStatus(taskInfo.getTask_status().getValue());
				}
				task_storage_list1.add(bean);
			}
		}
		if(!Assert.isEmpty(storage_list)) {
			for (EnvTagStorageInfo storageInfo : storage_list) {
				TaskSrorageBean bean = new TaskSrorageBean();
				bean.setTask_time(storageInfo.getCrt_bk_date().toString()+" "+storageInfo.getCrt_bk_time().toString());
				bean.setTask_id(storageInfo.getStorage_id());
				bean.setTask_type(3);
				bean.setProject_name(storageInfo.getProject_name());
				if(!Assert.isEmpty(storageInfo.getStorage_result())) {
					bean.setResult(storageInfo.getStorage_result().getValue());
				}
				if(!Assert.isEmpty(storageInfo.getStorage_status())) {
					bean.setStatus(storageInfo.getStorage_status().getValue());
				}
				task_storage_list1.add(bean);
			}
		}
		if(!Assert.isEmpty(task_storage_list1)) {
			//按时间倒序排序
			getCorrectSequence(task_storage_list1);
			List<TaskSrorageBean> task_storage_list = new ArrayList<TaskSrorageBean>();
			if(task_storage_list1.size()<=num) {
				num = task_storage_list1.size();
			}
			for (int i = 0; i < num; i++) {
				task_storage_list.add(task_storage_list1.get(i));
			}
			output.setTask_storage_list(task_storage_list);
		}
		logger.info("-----------queryTaskStorage begin----------");
		return output;
	}
	
	
	/**
	 * Description: 查询任务表和入库表的信息根据项目名称
	 * @param input
	 * @return
	 */
	public ViewTaskStorageOutputBean queryTaskByProName(ViewTaskStorageInputBean input){
		logger.info("-----------queryTaskByProName begin----------");
		ViewTaskStorageOutputBean output = new ViewTaskStorageOutputBean();
		String project_name = input.getProject_name();
		int num = input.getNum();
		//非空校验
		Assert.assertNotEmpty(num==0?null:num, ViewTaskStorageInputBean.ENV_NAMECN);
		
		//合法性校检
		projectPublicService.checkProjectNameIsNotExist(project_name);
		
		List<TaskSrorageBean> task_storage_list1 = new ArrayList<TaskSrorageBean>();
		//任务表
		List<EnvTaskInfo> task_list = envTaskDaoService.queryTaskInfoByProName(project_name);
		//入库表
		List<EnvTagStorageInfo> storage_list = envTagStorageDaoService.queryStorageInfoByProName(project_name);
		if(!Assert.isEmpty(task_list)) {
			for (EnvTaskInfo taskInfo : task_list) {
				TaskSrorageBean bean = new TaskSrorageBean();
				bean.setTask_time(taskInfo.getCrt_bk_date().toString()+" "+taskInfo.getCrt_bk_time().toString());
				bean.setTask_id(taskInfo.getWork_id());
				if(!Assert.isEmpty(taskInfo.getTask_type())) {
					int type = envTaskPublicService.getValue(taskInfo.getTask_type());
					bean.setTask_type(type);
				}
				bean.setCode_ver_num(taskInfo.getCode_ver_num());
				String env_cn_name = ceEnvironmentDaoService.getInfoByEnvCnName(taskInfo.getEnv_name());
				bean.setEnv_name(env_cn_name);
				String tappac_list_uuid = taskInfo.getTagpac_list_uuid();
				//获取目标包名列表
				if(!Assert.isEmpty(tappac_list_uuid)){
					List<String> package_list = uuFilelistDaoService.queryPacList(tappac_list_uuid);
					bean.setPackage_list(package_list);				}
				if(!Assert.isEmpty(taskInfo.getExe_result())) {
					bean.setResult(taskInfo.getExe_result().getValue());
				}
				if(!Assert.isEmpty(taskInfo.getTask_status())) {
					bean.setStatus(taskInfo.getTask_status().getValue());
				}
				task_storage_list1.add(bean);
			}
		}
		if(!Assert.isEmpty(storage_list)) {
			for (EnvTagStorageInfo storageInfo : storage_list) {
				TaskSrorageBean bean = new TaskSrorageBean();
				bean.setTask_time(storageInfo.getCrt_bk_date().toString()+" "+storageInfo.getCrt_bk_time().toString());
				bean.setTask_id(storageInfo.getStorage_id());
				bean.setTask_type(3);
				String env_cn_name = ceEnvironmentDaoService.getInfoByEnvCnName(storageInfo.getEnv_name());
				bean.setEnv_name(env_cn_name);
				String list_uuid = storageInfo.getStorage_list_uuid();
				//获取包名列表
				if(!Assert.isEmpty(list_uuid)){
					List<String> package_list = uuFilelistDaoService.queryPacList(list_uuid);
					bean.setPackage_list(package_list);
				}
				if(!Assert.isEmpty(storageInfo.getStorage_result())) {
					bean.setResult(storageInfo.getStorage_result().getValue());
				}
				if(!Assert.isEmpty(storageInfo.getStorage_status())) {
					bean.setStatus(storageInfo.getStorage_status().getValue());
				}
				task_storage_list1.add(bean);
			}
		}
		if(!Assert.isEmpty(task_storage_list1)) {
			//按时间倒序排序
			getCorrectSequence(task_storage_list1);
			List<TaskSrorageBean> task_storage_list = new ArrayList<TaskSrorageBean>();
			if(task_storage_list1.size()<=num) {
				num = task_storage_list1.size();
			}
			for (int i = 0; i < num; i++) {
				task_storage_list.add(task_storage_list1.get(i));
			}
			output.setTask_storage_list(task_storage_list);
		}
		logger.info("-----------queryTaskByProName begin----------");
		return output;
	}


	/** 
	 * Description: 时间倒序
	 * @param list 
	 */
	private void getCorrectSequence(List<TaskSrorageBean> list) {
		Collections.sort(list, new Comparator<TaskSrorageBean>(){
			@Override  
			public int compare(TaskSrorageBean bean1, TaskSrorageBean bean2) {  
				return bean2.getTask_time().compareTo(bean1.getTask_time());  
			}  
		});
	}
}
