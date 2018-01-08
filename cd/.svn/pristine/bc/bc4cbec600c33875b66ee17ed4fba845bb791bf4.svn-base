/**
 * Title: ProjectPrepareService.java
 * File Description: 
 * @copyright: 2017
 * @company: CORSWORK
 * @author: Administrator
 * @version: 1.0
 * @date: 2017年7月14日
 */
package com.wk.cd.module1.service;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.build.ea.dao.InstanceExeDaoService;
import com.wk.cd.build.ea.info.InstanceExeInfo;
import com.wk.cd.build.ea.service.EnvProgPublicService;
import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.EXE_STATUS;
import com.wk.cd.enu.PROG_TYPE;
import com.wk.cd.module1.entity.Instance;
import com.wk.cd.module1.entity.InstancePhase;
import com.wk.cd.module1.entity.Phase;
import com.wk.cd.module1.entity.PhaseParam;
import com.wk.cd.module1.entity.Program;
import com.wk.cd.module1.xml1.XmlReader;
import com.wk.cd.module1.xml1.XmlWriter;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;
import com.wk.util.JaDateTime;


/**
 * Class Description: 这个服务类
 * @author xuph
 */
public class TaskPrepareService {

	@Inject
	private EnvProgPublicService envProgPublicService;
	@Inject
	private InstanceGenerateService instanceGenerateService;
	@Inject
	private InstanceExeDaoService instanceExeDaoService;

	private static final Log logger = LogFactory.getLog();


	/**
	 * 生成项目实例 Description:
	 * @param project_info
	 * @param param_list 项目准备时需要填写的参数列表
	 * @return
	 */
	public Instance generateInstance(String prog_id,String work_id, String ver_num) {
		// 根据方案ID,读取方案
		logger.debug("生成方案:[{}]发布实例开始------------>>>", prog_id);
		Program program = new Program(prog_id);
		program = XmlReader.read(program);
		List<PhaseParam> param_list = program.getParam_list();
		Instance instance = null;
		if (!Assert.isEmpty(program)) {
			// 获取方案中的阶段列表
			List<Phase> parse_list = program.getPhase_list();
			logger.debug("generate pulbish inst begin####");
			// 获取系统参数(目前就两个)
			List<PhaseParam> system_params = getInnerParams(work_id, prog_id, ver_num);
			String instance_id = getInstanceId(work_id, PROG_TYPE.PUBLISH, 0);
			logger.debug("生成发布实例编号:[{}]", instance_id);
			instance = instanceGenerateService.phaseListGenerate(parse_list, param_list, system_params, instance_id, null);
		}
		// 将项目实例中每个阶段实例的命令插入到PJ_STEP_CMD表中
		insertPjStepCmdInfos(instance, PROG_TYPE.PUBLISH);
		// 生成项目实例xml文件(文件路径为：/home/sample/corsdeploy/cdWeb/module/INSTANCE/xxx.xml)
		XmlWriter.write(instance);
		logger.debug("生成方案:[{}]发布实例结束------------>>>", prog_id);
		return instance;
	}
	
	public Instance generateRollbackInstance(String prog_id,String work_id, String ver_num, long seq) {
		logger.debug("生成方案[{}]回退实例开始-------------->>>",prog_id);
		Program program = new Program(prog_id);
		program = XmlReader.read(program);
		List<PhaseParam> param_list = program.getParam_list();
		
		Instance instance = null;
		if (!Assert.isEmpty(program)) {
			// 获得包类型列表
			List<Phase> list = program.getPhase_list();
			logger.debug("---generate rollback inst begin####");
			List<PhaseParam> system_params = getInnerParams(work_id, prog_id, ver_num);
			String instance_id = getInstanceId(work_id, PROG_TYPE.PUBLISH, seq);
			instance = instanceGenerateService.phaseListGenerate(list, param_list, system_params, instance_id, null);
		}
		insertPjStepCmdInfos(instance, PROG_TYPE.PUBLISH);
		XmlWriter.write(instance);
		logger.debug("生成方案[{}]回退实例结束-------------->>>",prog_id);
		return instance;
	}
	
	/**
	 * Description: 设定固定参数(系统参数)
	 * @param task_no
	 * @param prog_id
	 * @param ver_no
	 * @return
	 */
	public List<PhaseParam> getInnerParams(String task_no, String prog_id, String ver_no) {
		logger.debug("获得系统参数开始----------->>>>");
		List<PhaseParam> params = new ArrayList<PhaseParam>();
		envProgPublicService.checkProgIdIsNotExist(prog_id);
		// 目标版本号（路径）
		PhaseParam verno_path = new PhaseParam();
		verno_path.setParam_name("verno_path");
		verno_path.setParam_value(ver_no);
		params.add(verno_path);
		// 任务编号
		PhaseParam task_no1 = new PhaseParam();
		task_no1.setParam_name("task_no");
		task_no1.setParam_value(task_no);
		params.add(task_no1);
		logger.debug("获得系统参数结束---------->>>>");
		return params;
	}
	
	/**
	 * Description: 获取投产项目的实例ID
	 * @param business_sys_name
	 * @param project_name
	 * @param template_type
	 * @return
	 */
	public String getInstanceId(String work_id, PROG_TYPE prog_type, long rollback_seq) {
		if (PROG_TYPE.PUBLISH == prog_type) {
			return work_id + "_" + prog_type;
		} else {
			return work_id + "_" + prog_type + "_" + rollback_seq;
		}

	}

	/**
	 * Description: 插入阶段命令
	 * @param business_sys_name
	 * @param project_name
	 */
	public void insertPjStepCmdInfos(Instance instance, PROG_TYPE prog_type) {
		List<InstancePhase> ss = instance.getPhase();
		List<InstanceExeInfo> step_infos = new ArrayList<InstanceExeInfo>();
		String inst_id = instance.getInstance_id();
		for (int i = 0; i < ss.size(); i++) {
			InstancePhase phase = ss.get(i);
			String ip = phase.getModule_source_info().getDt_source_info().getSoc_ip();
			String[] cmd_lines = phase.getScript().getCmds();
			for (int j = 0; j <= cmd_lines.length; j++) {
				InstanceExeInfo inst_info = new InstanceExeInfo();
				inst_info.setInstance_id(inst_id);
				inst_info.setSoc_name(ip);
				inst_info.setInst_bk_no(i+1);
				inst_info.setExe_status(EXE_STATUS.PENDING);
				inst_info.setStart_bk_tm(JaDateTime.valueOf("1971-01-01 00:00:00"));
				inst_info.setEnd_bk_tm(JaDateTime.valueOf("1971-01-01 00:00:00"));
				inst_info.setTpl_bk_no(j);
				if (j == 0) {
					inst_info.setStep_bk_desc(phase.getPhase_name());
				}
				step_infos.add(inst_info);
			}
		}
		instanceExeDaoService.insertListInfo(step_infos);
	}
	

}
