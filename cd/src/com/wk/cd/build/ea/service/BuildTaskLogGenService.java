/**
 * Title: BuildTaskLogGenService.java
 * File Description: ������־���ɷ�����
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2016��12��13��
 */
package com.wk.cd.build.ea.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.wk.cd.build.ea.dao.BuildConfigfileDaoService;
import com.wk.cd.build.ea.dao.EnvBuildTaskDaoService;
import com.wk.cd.build.ea.dao.EnvTaskDaoService;
import com.wk.cd.build.ea.info.BuildConfigfileInfo;
import com.wk.cd.build.ea.info.EnvBuildTaskInfo;
import com.wk.cd.build.ea.info.EnvTaskInfo;
import com.wk.cd.build.en.dao.CeEnvironmentServerDaoService;
import com.wk.cd.build.en.dao.CeServerDaoService;
import com.wk.cd.build.en.info.CeServerInfo;
import com.wk.cd.enu.CFG_TYPE;
import com.wk.cd.enu.FOPT_TYPE;
import com.wk.cd.enu.SCRIPT_TYPE;
import com.wk.cd.enu.TASK_STATUS;
import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.CfgTool;
import com.wk.cd.exc.ReadConfigFileException;
import com.wk.cd.system.us.service.UsGetUserInfoService;
import com.wk.lang.EIterable;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;
import com.wk.util.FileUtil;

/**
 * Class Description: ������־���ɷ�����
 * @author Xul
 */
public class BuildTaskLogGenService {
	
	@Inject private EnvTaskPublicService envTaskPublicService;
	@Inject private EnvTaskDaoService envTaskDaoService;
	@Inject private UsGetUserInfoService usGetUserInfoService;
	@Inject private EnvBuildTaskDaoService envBuildTaskDaoService;
	@Inject private CeEnvironmentServerDaoService envServerSrv;
	@Inject private CeServerDaoService serverSrv;
	@Inject private ExeScriptService exeScriptService;
	@Inject private BuildConfigfileDaoService buildConfigfileDaoService;
	private static final Log logger = LogFactory.getLog();
	
	/**
	 * Description: ����ʵ����־ȫ·��
	 * @param work_id
	 * @param instance_id
	 * @param split_flag trueΪ��ȥǰ�˸�·����falseΪ��˱���·��
	 */
	public String generateinstLogPath(String work_id, String instance_id, boolean split_flag){
		//��ȡǰ�˸�·��
		String web_root_path = CfgTool.getProjectPropterty("web.root.path");
		if (Assert.isEmpty(web_root_path)) {
			throw new ReadConfigFileException().addScene("FILE", "cms.properties").addScene("CONFIG", "web.root.path");
		}
		String root_path = envTaskPublicService.generateTaskRootPath(work_id);
		String reletive_path = root_path.replace(web_root_path, "");
		String log_name = instance_id + "_log.txt";
		String result = "";
		if(split_flag){
			result = reletive_path + log_name;
		}else{
			result = root_path + log_name;
		}
		return result;
	}
	
	/**
	 * Description:���ɹ�����־
	 */
	public String generateBuildTaskLog(String work_id){
		EnvTaskInfo task_info = envTaskDaoService.getInfoByKey(work_id);
		String log_full_path = task_info.getExelog_bk_path();
		if(!Assert.isEmpty(log_full_path)){
			File file = new File(log_full_path);
			if(file.exists()){
				logger.debug("�������ɹ�����־...");
				file.delete();
			}
			//��ȡ��ǰ�������
			EnvBuildTaskInfo buildTaskInfo = envBuildTaskDaoService.getInfoByKey(work_id);
			int build_step_id = buildTaskInfo.getBuild_step_id();
			//ƴ����־����
			List<String> content_list = new ArrayList<String>();
			//���ɹ�������־
			content_list = generateBuildTotalLog(task_info, content_list);
			if(build_step_id >= 1){
				//����Ӧ�ò�����־
				content_list = generateAppDeployLog(work_id, content_list);
			}
			if(build_step_id >= 2){
				//���������ļ���־
				content_list = generateConfigLog(work_id, content_list);
			}
			if(build_step_id >= 3){
				//���ɲ���������־
				content_list = generateSysLog(work_id, content_list);
			}
			if(build_step_id >= 4){
				//���ɲ���������־
				content_list = generateDbLog(work_id, content_list);
			}
			if(build_step_id >= 5){
				//�������ݴ�����־
				content_list = generateDataLog(work_id, content_list);
			}
			if(build_step_id >= 6){
				//�����м����־
				content_list = generateMidLog(work_id, content_list);
			}
			if(build_step_id >= 7){
				//��������������־
				content_list = generateOtherLog(work_id, content_list);
			}
			if(build_step_id >= 8){
				//������ͣ������־
				content_list = generateGoStopLog(work_id, content_list);
			}
			if(TASK_STATUS.EXECUTED.equals(task_info.getTask_status())){
				content_list = generateEndLog(task_info, content_list);
			}
			for(String content : content_list){
				writeToLog(log_full_path, content + "\r\n");
			}
			//��ȡǰ�˸�·��
			String web_root_path = CfgTool.getProjectPropterty("web.root.path");
			if (Assert.isEmpty(web_root_path)) {
				throw new ReadConfigFileException().addScene("FILE", "cms.properties").addScene("CONFIG", "web.root.path");
			}
			return log_full_path.replace(web_root_path, "");
		}else{
			logger.error("���������־·���ֶβ�����");
			return null;
		}
	}
	
	/** 
	 * Description: ���ɹ�������־
	 * @param task_info
	 * @param content_list
	 * @return 
	 */
	private List<String> generateBuildTotalLog(EnvTaskInfo task_info, List<String> content_list) {
		//��ȡ�����û�������
		String user_cn_name = "";
		if(!Assert.isEmpty(task_info.getCrt_user_id())){
			user_cn_name = usGetUserInfoService.getUserCnNameByUserId(task_info.getCrt_user_id());
			user_cn_name = Assert.isEmpty(user_cn_name) ? task_info.getCrt_user_id() : user_cn_name;
		}
		content_list.add("-------------------------------------------------------------");
		content_list.add("-------------------------------------------------------------");
		content_list.add("----");
		content_list.add("----               ��"+ task_info.getEnv_name() +"��������������");
		content_list.add("----               �������� ��" + task_info.getTask_bk_desc() + "��");
		content_list.add("----               �������� ��" + task_info.getCrt_bk_date() + "��");
		content_list.add("----               �� �� �� ��" + user_cn_name + "��");
		content_list.add("----");
		content_list.add("-------------------------------------------------------------");
		content_list.add("-------------------------------------------------------------");
		content_list.add("");
		content_list.add("");
		return content_list;
	}
	
	/** 
	 * Description:����Ӧ�ò�����־ 
	 * @param work_id
	 * @param content_list
	 * @return 
	 */
	private List<String> generateAppDeployLog(String work_id, List<String> content_list) {
		content_list.add("");
		content_list.add("-------------------------------------------------------------");
		content_list.add("----                   ��һ�� Ӧ�ò���");
		content_list.add("-------------------------------------------------------------");
		content_list.add("");
		content_list.add("");
		EnvBuildTaskInfo build_info = envBuildTaskDaoService.getInfoByKey(work_id);
		String log_path = build_info.getExelog_bk_path();
		if(Assert.isEmpty(log_path)){
			content_list.add("������־");
		}else{
			content_list = readFileToStrList(log_path, content_list);
		}
		return content_list;
	}
	
	/**
	 * Description: ���������ļ���־
	 * @param work_id
	 * @param content_list
	 * @return
	 */
	private List<String> generateConfigLog(String work_id, List<String> content_list) {
		content_list.add("");
		content_list.add("-------------------------------------------------------------");
		content_list.add("----                   �ڶ��� �����ļ�");
		content_list.add("-------------------------------------------------------------");
		content_list.add("");
		content_list.add("");
		content_list = generateConfigLogByType(work_id, CFG_TYPE.NORMAL, content_list);
		return content_list;
	}
	
	/**
	 * Description: ���ɲ���ϵͳ������־
	 * @param work_id
	 * @param content_list
	 * @return
	 */
	private List<String> generateSysLog(String work_id, List<String> content_list) {
		content_list.add("");
		content_list.add("-------------------------------------------------------------");
		content_list.add("----                   ������ ����ϵͳ����");
		content_list.add("-------------------------------------------------------------");
		content_list.add("");
		content_list.add("");
		content_list = generateScriptLogByType(work_id, SCRIPT_TYPE.OPERATION, content_list);
		return content_list;
	}
	
	/**
	 * Description: �������ݿ�������־
	 * @param work_id
	 * @param content_list
	 * @return
	 */
	private List<String> generateDbLog(String work_id, List<String> content_list) {
		content_list.add("");
		content_list.add("-------------------------------------------------------------");
		content_list.add("----                   ���Ĳ� ���ݿ�����");
		content_list.add("-------------------------------------------------------------");
		content_list.add("");
		content_list.add("");
		content_list = generateScriptLogByType(work_id, SCRIPT_TYPE.DATABASE, content_list);
		return content_list;
	}
	
	/**
	 * Description: �������ݴ�����־
	 * @param work_id
	 * @param content_list
	 * @return
	 */
	private List<String> generateDataLog(String work_id, List<String> content_list) {
		content_list.add("");
		content_list.add("-------------------------------------------------------------");
		content_list.add("----                   ���岽 ���ݴ���");
		content_list.add("-------------------------------------------------------------");
		content_list.add("");
		content_list.add("");
		content_list = generateScriptLogByType(work_id, SCRIPT_TYPE.DATA, content_list);
		return content_list;
	}
	
	/**
	 * Description: �����м����־
	 * @param work_id
	 * @param content_list
	 * @return
	 */
	private List<String> generateMidLog(String work_id, List<String> content_list) {
		content_list.add("");
		content_list.add("-------------------------------------------------------------");
		content_list.add("----                   ������ �м��");
		content_list.add("-------------------------------------------------------------");
		content_list.add("");
		content_list.add("");
		content_list = generateConfigLogByType(work_id, CFG_TYPE.TOMCAT, content_list);
		return content_list;
	}
	
	/**
	 * Description: ��������������־
	 * @param work_id
	 * @param content_list
	 * @return
	 */
	private List<String> generateOtherLog(String work_id, List<String> content_list) {
		content_list.add("");
		content_list.add("-------------------------------------------------------------");
		content_list.add("----                   ���߲� ��������");
		content_list.add("-------------------------------------------------------------");
		content_list.add("");
		content_list.add("");
		content_list = generateScriptLogByType(work_id, SCRIPT_TYPE.OTHER, content_list);
		return content_list;
	}
	
	/**
	 * Description: ������ͣ������־
	 * @param work_id
	 * @param content_list
	 * @return
	 */
	private List<String> generateGoStopLog(String work_id, List<String> content_list) {
		content_list.add("");
		content_list.add("-------------------------------------------------------------");
		content_list.add("----                   �ڰ˲� ��ͣ����");
		content_list.add("-------------------------------------------------------------");
		content_list.add("");
		content_list.add("");
		content_list = generateScriptLogByType(work_id, SCRIPT_TYPE.START, content_list);
		content_list = generateScriptLogByType(work_id, SCRIPT_TYPE.STOP, content_list);
		return content_list;
	}
	
	/**
	 * Description: ������������������־
	 * @param work_id
	 * @param cfg_type
	 * @param content_list
	 * @return
	 */
	private List<String> generateConfigLogByType(String work_id, CFG_TYPE cfg_type, List<String> content_list){
		if(!cfg_type.equals(CFG_TYPE.NORMAL)){
			content_list.add("--------------------------" + cfg_type.getCname() + "--------------------------");
		}
		EnvTaskInfo task_info = envTaskDaoService.getInfoByKey(work_id);
		String env_name = task_info.getEnv_name();
		List<String> env_server_list = envServerSrv.getDistinctServerNameByEnv(env_name);
		if(!Assert.isEmpty(env_server_list)){
			for(String server_name : env_server_list){
				if(!Assert.isEmpty(server_name)){
					CeServerInfo server_info = serverSrv.getInfoByServerName(server_name);
					String server_ip = server_info.getServer_ip();
					if(!Assert.isEmpty(server_ip)){
						content_list.add("");
						content_list.add("��������" + server_name + "(" + server_ip + ")");
						content_list = changeRecordToStrList(work_id, cfg_type, server_ip, content_list);
					}
				}
			}
		}else{
			content_list.add("������־");
		}
		content_list.add("");
		return content_list;
	}
	
	/**
	 * Description: �������ļ��б�ת��Ϊ�ַ����б�
	 * @param work_id
	 * @param cfg_type
	 * @param server_ip
	 * @param content_list
	 * @return
	 */
	private List<String> changeRecordToStrList(String work_id, CFG_TYPE cfg_type, String server_ip, List<String> content_list){
		List<BuildConfigfileInfo> config_list = buildConfigfileDaoService.getInfoByWork(server_ip, work_id, cfg_type);
		content_list.add("");
		if(!Assert.isEmpty(config_list)){
			for(BuildConfigfileInfo info : config_list){
				content_list = changeInfoToStrList(info, content_list);
			}
		}else{
			content_list.add("������־");
		}
		return content_list;
	}
	
	/**
	 * Description: �������ļ���¼ת��Ϊ�ַ����б�
	 * @param info
	 * @param content_list
	 * @return
	 */
	private List<String> changeInfoToStrList(BuildConfigfileInfo info, List<String> content_list){
		//��ȡ�û�������
		String user_cn_name = "";
		if(!Assert.isEmpty(info.getModify_user_id())){
			user_cn_name = usGetUserInfoService.getUserCnNameByUserId(info.getModify_user_id());
			user_cn_name = Assert.isEmpty(user_cn_name) ? info.getModify_user_id() : user_cn_name;
		}
		//��ȡ��������
		String fopt_str = "";
		if(FOPT_TYPE.MODIFY.equals(info.getFopt_type())){
			fopt_str = "update";
		}else if(FOPT_TYPE.DELETE.equals(info.getFopt_type())){
			fopt_str = "delete";
		}else{
			fopt_str = "unknow";
		}
		//ƴ����־��Ϣ
		content_list.add(info.getModify_bk_date() + " " + info.getModify_bk_time() + " ["+ user_cn_name + "] " + fopt_str + " " + info.getFile_bk_path() + info.getFile_bk_fname());
		return content_list;
	}
	
	/**
	 * Description: ���ݽű�����������־
	 * @return
	 */
	private List<String> generateScriptLogByType(String work_id, SCRIPT_TYPE script_type, List<String> content_list){
		content_list.add("--------------------------" + script_type.getCname() + "--------------------------");
		content_list.add("");
		List<String> log_list = exeScriptService.getExeScriptLogList(work_id, script_type);
		if(!Assert.isEmpty(log_list)){
			for(String str : log_list){
				content_list = readFileToStrList(str, content_list);
			}
		}else{
			content_list.add("������־");
		}
		content_list.add("");
		return content_list;
	}
	
	/**
	 * Description: ���ж�ȡ�ļ����ݣ������ַ����б�
	 * @param content_list
	 * @return
	 */
	private List<String> readFileToStrList(String file_path, List<String> content_list){
		if(!Assert.isEmpty(file_path)){
			File file = new File(file_path);
			if(file.exists()){
				EIterable<String> eit = FileUtil.lineReader(file);
				Iterator<String> it = eit.iterator();
				while(it.hasNext()){
					content_list.add(it.next());
				}
			}
		}
		return content_list;
	}
	
	/** 
	 * Description: �������������־
	 * @param task_info
	 * @param content_list
	 * @return 
	 */
	private List<String> generateEndLog(EnvTaskInfo task_info, List<String> content_list) {
		//��ȡִ���û�������
		String user_cn_name = "";
		if(!Assert.isEmpty(task_info.getExe_user_id())){
			user_cn_name = usGetUserInfoService.getUserCnNameByUserId(task_info.getExe_user_id());
			user_cn_name = Assert.isEmpty(user_cn_name) ? task_info.getExe_user_id() : user_cn_name;
		}
		content_list.add("-------------------------------------------------------------");
		content_list.add("-------------------------------------------------------------");
		content_list.add("----");
		content_list.add("----               ��"+ task_info.getEnv_name() +"��������������");
		content_list.add("----               �������� ��" + task_info.getTask_bk_desc() + "��");
		content_list.add("----               ������� ��" + task_info.getEnd_bk_tm() + "��");
		content_list.add("----               ִ �� �� ��" + user_cn_name + "��");
		content_list.add("----");
		content_list.add("-------------------------------------------------------------");
		content_list.add("-------------------------------------------------------------");
		return content_list;
	}

	/**
	 * Description:׷��ִ�е���Ϣ���ļ�
	 * @param fileName
	 * @param content
	 */
	private void writeToLog(String log_full_path, String content) {
		FileWriter writer = null;
		try {
			// ��һ��д�ļ�����true��ʾ��׷����ʽд�ļ�
			writer = new FileWriter(log_full_path, true);
			writer.write(content);
		} catch (IOException e) {
			logger.error(e.toString(), e);
			logger.error(e.getMessage());
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					logger.error(e.toString(), e);
					logger.error(e.getMessage());
				}
			}
		}
	}
}