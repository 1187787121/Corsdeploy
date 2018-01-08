package com.wk.cd.build.ea.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.exception.ExceptionUtils;

import com.wk.cd.build.ea.dao.BuildStepDaoService;
import com.wk.cd.build.ea.dao.EnvBuildTaskDaoService;
import com.wk.cd.build.ea.dao.EnvTaskDaoService;
import com.wk.cd.build.ea.dao.InstanceExeDaoService;
import com.wk.cd.build.ea.dao.UuParamDaoService;
import com.wk.cd.build.ea.dao.UuSocDaoService;
import com.wk.cd.build.ea.info.BuildStepInfo;
import com.wk.cd.build.ea.info.EnvBuildTaskInfo;
import com.wk.cd.build.ea.info.EnvTaskInfo;
import com.wk.cd.build.ea.info.InstanceExeInfo;
import com.wk.cd.build.ea.info.UuSocInfo;
import com.wk.cd.common.cm.dao.CmSeqDaoService;
import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.CMD_STATUS;
import com.wk.cd.enu.EXE_STATUS;
import com.wk.cd.enu.IMPL_TYPE;
import com.wk.cd.enu.PARAM_TYPE;
import com.wk.cd.enu.YN_FLAG;
import com.wk.cd.module1.Process;
import com.wk.cd.module1.Result;
import com.wk.cd.module1.bean.StageSourceBean;
import com.wk.cd.module1.entity.Instance;
import com.wk.cd.module1.entity.InstancePhase;
import com.wk.cd.module1.entity.Phase;
import com.wk.cd.module1.entity.PhaseParam;
import com.wk.cd.module1.entity.Program;
import com.wk.cd.module1.info.ModuleSourceInfo;
import com.wk.cd.module1.service.InstanceGenerateService;
import com.wk.cd.module1.xml1.XmlReader;
import com.wk.cd.module1.xml1.XmlWriter;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;
import com.wk.util.JaDateTime;

/**
 * Class Description: 生成发布实例的方法
 * @author chiss
 */
public class PublishTaskInstanceService {
	
	@Inject
	private InstanceGenerateService instanceGenerateService;
	@Inject
	private InstanceExeDaoService instanceExeDaoService;
	@Inject
	private EnvTaskDaoService envTaskDaoService;
	@Inject
	private EnvTaskPublicService envTaskPublicService;
	@Inject
	private BuildStepDaoService buildStepDaoService;
	@Inject
	private UuSocDaoService uuSocDaoService;
	@Inject 
	private EnvBuildTaskDaoService envBuildTaskDaoService;
	@Inject
	private UuParamDaoService uuParamDaoService;
	@Inject
	private CmSeqDaoService cmsvc;
	private static final Log logger = LogFactory.getLog();
	
	/**
	 * Description: 发布方案生成实例
	 * @param prog_id
	 * @return
	 */
	public Instance generateInstanceById(String task_no,String prog_id,String ver_no) {
		logger.debug("开始生成发布实例");
		Program program = new Program(prog_id);
		program  = XmlReader.read(program);
		String instance_id = UUID.randomUUID().toString().replaceAll("-", "");
		Instance instance = new Instance(instance_id);
		if(!Assert.isEmpty(program)) {
//			List<PackageTypeInfo> package_type_list = program.getPac_type_list();
			//系统参数
			List<PhaseParam> system_params = new ArrayList<PhaseParam>();
			PhaseParam verno_path = new PhaseParam();
			verno_path.setParam_name("verno_path");
			verno_path.setParam_value(ver_no);
			system_params.add(verno_path);
			// 任务编号
			PhaseParam task_no1 = new PhaseParam();
			task_no1.setParam_name("task_no");
			task_no1.setParam_value(task_no);
			system_params.add(task_no1);
			instance = instanceGenerateService.phaseListGenerate(program.getPhase_list(), program.getParam_list(), system_params, instance_id, null);
			//写入文件
			XmlWriter.write(instance);
		}	
		return instance;
	}

	/** 
	 * Description: 
	 * @param pub_inst 
	 */
	public void insertInstanceExe(Instance pub_inst) {
		List<InstancePhase> phase_list = pub_inst.getPhase();
		String instance_id = pub_inst.getInstance_id();
		int index = 1;
		List<InstanceExeInfo> infos = new ArrayList<InstanceExeInfo>();
		
		if(!Assert.isEmpty(phase_list)) {
			for (InstancePhase phase : phase_list) {
				InstanceExeInfo info = new InstanceExeInfo();
				info.setInstance_id(instance_id);
				ModuleSourceInfo source_info = phase.getModule_source_info();
				if (!Assert.isEmpty(source_info) && !Assert.isEmpty(source_info.getDt_source_info())) {
					String soc_name = source_info.getDt_source_info().getSoc_name();
					info.setSoc_name(soc_name);
				}
				info.setStep_bk_desc(phase.getPhase_name());
				info.setInst_bk_no(phase.getPhase_no());
				info.setExe_status(EXE_STATUS.PENDING);
				info.setStart_bk_tm(JaDateTime.valueOf("1971-01-01 00:00:00"));
				info.setEnd_bk_tm(JaDateTime.valueOf("1971-01-01 00:00:00"));
				info.setTpl_bk_no(index++);
				infos.add(info);
			}
			instanceExeDaoService.insertListInfo(infos);
		}
	}

	public Instance getInstanceByWorkId(String work_id, int exe_bk_no) {
		// 获取执行实例
		EnvTaskInfo env_task_info = envTaskDaoService.getInfoByKey(work_id);
		String inst_id = env_task_info.getInstance_id();
		logger.debug("读取实例Inst_id:[{}]", inst_id);
		Instance instance = new Instance(inst_id);
		instance = XmlReader.read(instance);
		int count  = instance.getPhaseCount();
		logger.debug("阶段总数:[{}]", count);
		if (exe_bk_no > count) {
			throw new ArrayIndexOutOfBoundsException("阶段已经执行到末尾，不能再继续执行");
		}
		return instance;
	}
	
	/**
	 * Description: 回退和一键执行中处理每个阶段执行 主要处理耗时
	 * @param proc
	 * @param work_id
	 * @param phase_no
	 * @param count
	 * @return
	 */
	public boolean dealExecutePhase(Process proc, String work_id, String instance_id, int phase_no, int count, boolean skip_flag) {
		long start_time = System.currentTimeMillis();
		Result result = executePhase(proc, work_id, instance_id, phase_no, skip_flag);
		long end_time = System.currentTimeMillis();
		int time_used = (int) (end_time - start_time);
		boolean flag = envTaskPublicService.updateEndStatus(result, phase_no, count, time_used, work_id, instance_id);
		cmsvc.getSession().commitAndResume();
		return flag;
	}
	
	/**
	 * Description:阶段执行 发送命令
	 * @param proc
	 * @param work_id
	 * @param phase_no
	 * @return
	 */
	public Result executePhase(Process proc, String work_id, String instance_id, int phase_no, boolean skip_flag) {
		long start_time = System.currentTimeMillis();

		instanceExeDaoService.updateExecuteStatus(EXE_STATUS.RUNNING, instance_id, phase_no);
		cmsvc.getSession().commitAndResume();
		logger.debug("当前执行[{}]第[{}]阶段，阶段从1开始计算", instance_id, phase_no);
		Result result = null;
		try {
			if (skip_flag) {
				result = proc.skip(phase_no - 1);
			} else {
				String remote_relative_dir = "compTest/" + work_id;
				result = proc.runStage(remote_relative_dir, phase_no - 1, false);
//				result = proc.run(phase_no - 1);
			}
			logger.debug("当前执行结果状态：+[{}]", result.getStatus().getCname());
			logger.debug("当前执行信息:[{}]", result.getMsg());
		} catch (Exception e) {
			logger.debug("日志里面打出来的错：" + ExceptionUtils.getStackTrace(e));
			result = new Result(CMD_STATUS.ERROR, e.getMessage(), start_time);
		}
		instanceExeDaoService.updateExecuteStartTime(JaDateTime.valueOf(result.getStart_time()), instance_id, phase_no);
		return result;
	}

	/**
	 * Description: 保存构建阶段表的基本信息(先删后增)
	 * @param inst
	 * @param work_id
	 */
	public void saveBuildStep(String work_id, String template_name, Phase phase) {
		logger.info("保存阶段信息:任务编号[{}]", work_id);
		int phase_no = phase.getPhase_no();
		//删除阶段基本信息
		BuildStepInfo build_step_info = buildStepDaoService.getInfoByKey(work_id, template_name, phase_no);
		if (!Assert.isEmpty(build_step_info)) {
			String uuid = build_step_info.getSoc_uuid();
			if (!Assert.isEmpty(uuid)) {
				uuSocDaoService.deleteListByUU(uuid);
			}
			buildStepDaoService.deleteListByIdAndTp(work_id, template_name, phase_no);
		}
		//新增阶段基本信息
		List<UuSocInfo> uu_soc_list = new ArrayList<UuSocInfo>();
		// 构建阶段信息
		BuildStepInfo build_info = new BuildStepInfo();
		build_info.setImpl_type(phase.getImpl_type());
		build_info.setGen_yn_flag(YN_FLAG.YES);
		build_info.setWork_id(work_id);
		build_info.setTemplate_name(template_name);
		build_info.setPhase_bk_desc(phase.getPhase_name());
		build_info.setPhase_id(phase_no);
		// 遍历执行数据源列表
		List<StageSourceBean> srv_soc = phase.getSrv_soc();
		if (!Assert.isEmpty(srv_soc)) {
			String soc_uuid = UUID.randomUUID().toString().replace("-", "");
			build_info.setSoc_uuid(soc_uuid);
			int soc_bk_seq = 1;
			for (StageSourceBean soc_bean : srv_soc) {
				// 新增数据源关联表
				UuSocInfo soc_info = new UuSocInfo();
				soc_info.setSoc_uuid(soc_uuid);
				soc_info.setSoc_bk_seq(soc_bk_seq);
				soc_info.setExe_server_name(soc_bean.getExe_server_name());
				soc_info.setExe_soc_name(soc_bean.getExe_soc_name());
				// 若为SVN执行类别，则存入版本信息
				if (IMPL_TYPE.SVN.equals(phase.getImpl_type()) || IMPL_TYPE.WEBLOGIC.equals(phase.getImpl_type()) || IMPL_TYPE.WAS.equals(phase.getImpl_type())) {
					soc_info.setVer_server_name(soc_bean.getVer_server_name());
					soc_info.setVer_soc_name(soc_bean.getVer_soc_name());
				}
				uu_soc_list.add(soc_info);
				soc_bk_seq++;
			}
			uuSocDaoService.insertListInfo(uu_soc_list);
		}
		buildStepDaoService.insertInfo(build_info);
		
//		List<Phase> phase_list = template.getPhase_list();
//		String template_name = template.getTemplate_cn_name();
//		if(!Assert.isEmpty(phase_list)) {
//			for (Phase phase : phase_list) {}
//		}
	}
	
	/**
	 * Description: 比较投产包参数有无变化
	 * @param work_id
	 * @param phases
	 * @return
	 */
	public boolean comparePacParam(String work_id, List<PhaseParam> param_list) {
		
		logger.info("comparePacParam start");
		// 初始化标志
		boolean change_flag = true;
		// 获取原投产包参数
		EnvBuildTaskInfo task_info = envBuildTaskDaoService.getInfoByKey(work_id);
		// 获取投产包参数
		String param_uuid = task_info.getTemplate_param_uuid();
		if (!Assert.isEmpty(param_uuid)) {
			List<String> src_dpp_list = uuParamDaoService.getValueByKey(param_uuid);
			List<String> now_dpp_list = new ArrayList<String>();
			if(!Assert.isEmpty(param_list)) {
				for (PhaseParam info : param_list) {
					if (PARAM_TYPE.PDDPARAM.equals(info.getParam_type()))
						now_dpp_list.add(info.getParam_value());
				}
				// 若参数不一致，则返回false
				for (String str : src_dpp_list) {
					logger.debug("原投产包参数：" + str);
					if (!now_dpp_list.contains(str)) {
						change_flag = true;
						break;
					} else {
						change_flag = false;
					}
				}
				for (String str : now_dpp_list) {
					logger.debug("现投产包参数：" + str);
					if (!src_dpp_list.contains(str)) {
						change_flag = true;
						break;
					} else {
						change_flag = false;
					}
				}
			}
		}

		return change_flag;
	}

	/**
	 * Description:删除构建模板相关信息
	 */
	public void deleteAllBuildTpInfo(String work_id) {
		EnvBuildTaskInfo task_info = envBuildTaskDaoService.getInfoByKey(work_id);
		String src_template_name = task_info.getTemplate_name();
		String param_uuid = task_info.getTemplate_param_uuid();
		// 删除模板参数
		uuParamDaoService.deleteById(param_uuid);
		// 删除模板UUID
		envBuildTaskDaoService.updateBuildParamInfoByKey(src_template_name, "", work_id);
		// 删除阶段基本信息
		List<BuildStepInfo> build_step_list = buildStepDaoService.queryListInfoByIdAndTp(work_id, src_template_name);
		if (!Assert.isEmpty(build_step_list)) {
			for (BuildStepInfo build_step_info : build_step_list) {
				String uuid = build_step_info.getSoc_uuid();
				if (!Assert.isEmpty(uuid)) {
					uuSocDaoService.deleteListByUU(uuid);
				}
			}
			buildStepDaoService.deleteListByIdAndTp(work_id, src_template_name);
		}
	}
	
	/**
	 * 复制一个目录及其子目录、文件到另外一个目录
	 * @param src
	 * @param dest
	 * @throws IOException
	 */
	public void copyFolder(File src, File dest) {
		if (src.isDirectory()) {
			if (!dest.exists()) {
				dest.mkdirs();
			}
			String files[] = src.list();
			for (String file : files) {
				File srcFile = new File(src, file);
				File destFile = new File(dest, file);
				// 递归复制
				copyFolder(srcFile, destFile);
			}
		} else {
			InputStream in = null;
			OutputStream out = null;
			try {
				in = new FileInputStream(src);
				out = new FileOutputStream(dest);
				byte[] buffer = new byte[1024];
				int length;
				while ((length = in.read(buffer)) > 0) {
					out.write(buffer, 0, length);
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
			} finally {
				try {
					if (!Assert.isEmpty(in)) {
						in.close();
					}
					if (!Assert.isEmpty(out)) {
						out.close();
					}
				} catch (IOException e) {
					logger.error(e.getMessage());
				}
			}
		}
	}
	
	/**
	 * Description: 删除文件
	 * @param f
	 */
	public void deleteAllFiles(File f) {
		if (f.exists()) {
			File[] files = f.listFiles();
			if (files != null) {
				for (File file : files)
					if (file.isDirectory()) {
						deleteAllFiles(file);
						file.delete(); // 删除目录下的所有文件后，该目录变成了空目录，可直接删除
					} else if (file.isFile()) {
						file.delete();
					}
			}
			f.delete(); // 删除最外层的目录
		}
	}
	
}
