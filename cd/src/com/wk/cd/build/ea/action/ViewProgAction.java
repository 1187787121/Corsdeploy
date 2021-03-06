/**
 * Title: ViewProgAction.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: chiss
 * @version: 1.0
 * @date: 2016年11月14日
 */
package com.wk.cd.build.ea.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.build.ea.bean.EnvProgStepBean;
import com.wk.cd.build.ea.bean.InteStepBean;
import com.wk.cd.build.ea.bean.TargetPackageBean;
import com.wk.cd.build.ea.bean.ViewProgInputBean;
import com.wk.cd.build.ea.bean.ViewProgOutputBean;
import com.wk.cd.build.ea.dao.PgInteStepDaoService;
import com.wk.cd.build.ea.dao.PgProgramDaoService;
import com.wk.cd.build.ea.dao.PgReleDaoService;
import com.wk.cd.build.ea.dao.PgReleStepDaoService;
import com.wk.cd.build.ea.dao.UuFilelistDaoService;
import com.wk.cd.build.ea.dao.UuSocDaoService;
import com.wk.cd.build.ea.info.PgInteStepInfo;
import com.wk.cd.build.ea.info.PgProgramInfo;
import com.wk.cd.build.ea.info.UuFilelistInfo;
import com.wk.cd.build.ea.info.UuSocInfo;
import com.wk.cd.build.ea.service.EnvProgPublicService;
import com.wk.cd.build.ea.service.InteTaskInstanceService;
import com.wk.cd.build.en.info.CeEnvironmentInfo;
import com.wk.cd.build.en.service.EnvironmentPublicService;
import com.wk.cd.build.en.service.ServerCommonService;
import com.wk.cd.build.en.service.SystemService;
import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.IS_PUBLISH;
import com.wk.cd.enu.PROG_TYPE;
import com.wk.cd.enu.STEP_TYPE;
import com.wk.cd.enu.TEMPLATE_TYPE;
import com.wk.cd.module1.entity.Phase;
import com.wk.cd.module1.entity.Program;
import com.wk.cd.module1.entity.Template;
import com.wk.cd.module1.info.MoTemplateInfo;
import com.wk.cd.module1.service.TemplateService;
import com.wk.cd.service.IViewActionBasic;
import com.wk.cd.system.dt.service.DtSocService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description:
 * @author chiss
 */
public class ViewProgAction
		extends IViewActionBasic<ViewProgInputBean, ViewProgOutputBean> {
	@Inject
	private PgProgramDaoService pgProgramDaoService;
	@Inject
	private PgInteStepDaoService pgInteStepDaoService;
	@Inject
	private PgReleStepDaoService pgReleStepDaoService;
	@Inject
	private PgReleDaoService pgReleDaoService;
	@Inject
	private UuFilelistDaoService uuFilelistDaoService;
	@Inject
	private EnvProgPublicService envProgPublicService;
	@Inject
	private EnvironmentPublicService environmentPublicService;
	@Inject
	private DtSocService dtSocSrv;
	@Inject
	private UuSocDaoService uuSocDaoService;
	@Inject
	private SystemService systemService;
	@Inject
	private ServerCommonService serverCommonService;
	@Inject
	private TemplateService templateService;
	private static final Log logger = LogFactory.getLog();
	private static final String SUFFIX_ROLL = "rollback";

	/**
	 * Description: 查询单个环境方案及步骤信息
	 * @param input
	 * @return
	 */
	public ViewProgOutputBean queryEnvProgInfo(ViewProgInputBean input) {
		logger.info("-----------queryEnvProgInfo begin----------");
		ViewProgOutputBean output = new ViewProgOutputBean();
		String prog_id = input.getProg_id();
		// 非空校验
		Assert.assertNotEmpty(prog_id, ViewProgInputBean.PROG_IDCN);
		// 合法性校验
		envProgPublicService.checkProgIdIsNotExist(prog_id);
		// 获取步骤信息表
		PgProgramInfo pro_info = pgProgramDaoService.getInfoByKey(prog_id);

		output.setProg_name(pro_info.getProg_name());
		output.setProg_desc(pro_info.getProg_bk_desc());
		output.setProg_type(pro_info.getProg_type());
		if (PROG_TYPE.INTEGRATION.equals(pro_info.getProg_type())) {
			output.setComplie_bk_path("./" + pro_info.getEnv_name().trim()
					+ "/" + InteTaskInstanceService.SRC_CODE_DIR + "/${ver_num}/");
			output.setTarget_root_path("./" + pro_info.getEnv_name().trim()
					+ "/" + InteTaskInstanceService.SRC_CODE_DIR + "/${ver_num}/");
			if (pgInteStepDaoService.countPgStepByKey(prog_id) == 0) {
				output.setNext_step_id(1);
			} else {
				output.setNext_step_id(pgInteStepDaoService.countPgStepByKey(prog_id) + 1);
			}
			List<PgInteStepInfo> stepInfoList = pgInteStepDaoService.getInteListByProgId(prog_id);
//			List<EnvProgStepBean> prog_step_list = new ArrayList<EnvProgStepBean>();
			
			List<InteStepBean> step_list = new ArrayList<InteStepBean>();
			for (PgInteStepInfo stepInfo : stepInfoList) {
				InteStepBean step_bean = new InteStepBean();
				step_bean.setStep_id(stepInfo.getStep_id());
				
				EnvProgStepBean stepBean = new EnvProgStepBean();
				stepBean.setStep_id(stepInfo.getStep_id());
				stepBean.setStep_expl(stepInfo.getStep_expl());
				STEP_TYPE stepType = stepInfo.getStep_type();
				stepBean.setStep_type(stepType);
				String soc_uuid = stepInfo.getSoc_uuid();
				List<UuSocInfo> socInfoList = uuSocDaoService.queryListInfoByUU(soc_uuid);
				if (!Assert.isEmpty(socInfoList)) {
					stepBean.setServer_name(socInfoList.get(0).getExe_server_name());
					stepBean.setSoc_name(socInfoList.get(0).getExe_soc_name());
				}
				// 类型为版本时
				if (stepType == STEP_TYPE.VERSION) {
					if (!Assert.isEmpty(socInfoList)) {
						stepBean.setCode_server_name(socInfoList.get(0).getVer_server_name());
						stepBean.setCode_soc_name(socInfoList.get(0).getVer_soc_name());
						stepBean.setCode_bk_dir(socInfoList.get(0).getCode_bk_dir());
					}
				}
				// 类型为脚本时：
				if (stepType == STEP_TYPE.SCRIPT) {
					stepBean.setStep_bk_script(stepInfo.getStep_bk_script());
				}
				// 类型为编译时：
				if (stepType == STEP_TYPE.COMPILE) {
					stepBean.setCompile_type(stepInfo.getCompile_type());
					stepBean.setEnv_variable(stepInfo.getEnv_variable());
					stepBean.setCompile_param(stepInfo.getCompile_param());
					stepBean.setComplie_bk_path(stepInfo.getComplie_bk_path());
				}
				// 类型为入库时：
				if (stepType == STEP_TYPE.STORAGE) {
					if (!Assert.isEmpty(socInfoList)) {
						stepBean.setTar_server_name(socInfoList.get(0).getVer_server_name());
						stepBean.setTar_soc_name(socInfoList.get(0).getVer_soc_name());
						stepBean.setTar_bk_dir(socInfoList.get(0).getCode_bk_dir());
						stepBean.setTarget_root_path("./" + pro_info.getEnv_name().trim()+ "/" + InteTaskInstanceService.SRC_CODE_DIR + "/${ver_num}/");
						String file_uuid = stepInfo.getStorage_list_uuid();
						List<UuFilelistInfo> filelis = uuFilelistDaoService
								.getInfoByFileUuId(file_uuid);
						List<String> packname = new ArrayList<String>();

						List<TargetPackageBean> tar_package_list = new ArrayList<TargetPackageBean>();
						if (!Assert.isEmpty(filelis)) {
							for (UuFilelistInfo listInfo : filelis) {
								String packn = listInfo.getPackage_name();
								if (!packname.contains(packn)) {
									packname.add(packn);
								}
							}
							if (!Assert.isEmpty(packname)) {
								for (String name : packname) {
									TargetPackageBean packbean = new TargetPackageBean();
									packbean.setPackage_name(name);
									List<UuFilelistInfo> lis = new ArrayList<UuFilelistInfo>();
									for (UuFilelistInfo listInfo : filelis) {
										if (listInfo.getPackage_name().equals(name)) {
											lis.add(listInfo);
										}
									}
									if(!Assert.isEmpty(lis)) {
										packbean.setTarget_relative_path(lis.get(0).getTarget_relative_path());
									}
									packbean.setFile_list(lis);
									tar_package_list.add(packbean);
								}
							}
							stepBean.setTar_package_list(tar_package_list);
						}
					}
				}
				step_bean.setProg_step_bean(stepBean);
				step_list.add(step_bean);
			}
			output.setStep_list(step_list);
		} else if (PROG_TYPE.PUBLISH.equals(pro_info.getProg_type())) {
			Program pub_program = new Program(prog_id);
			pub_program = com.wk.cd.module1.xml1.XmlReader.read(pub_program);
			output.setPub_program(pub_program);
			if(!Assert.isEmpty(pub_program.getRol_template_name())) {
				Program rol_program = new Program(prog_id+SUFFIX_ROLL);
				rol_program = com.wk.cd.module1.xml1.XmlReader.read(rol_program);
				output.setRol_program(rol_program);
			}
//			PgReleInfo pg_rele_info = pgReleDaoService.getInfoByKey(prog_id);
//			if (!Assert.isEmpty(pg_rele_info)) {
//				String ver_soc_uuid = pg_rele_info.getVer_soc_uuid();
//				UuSocInfo uu_soc_info = new UuSocInfo();
//				uu_soc_info.setSoc_uuid(ver_soc_uuid);
//				uu_soc_info.setSoc_bk_seq(1);
//				UuSocInfo soc_info = uuSocDaoService.getInfoByKey(uu_soc_info);
//				output.setCode_bk_dir(soc_info.getCode_bk_dir());
//				output.setVer_server_name(soc_info.getVer_server_name());
//				output.setVer_soc_name(soc_info.getVer_soc_name());
//				CeServerInfo server_info = serverCommonService.getInfoByKey(soc_info.getVer_server_name());
//				if(!Assert.isEmpty(server_info)){
//					output.setServer_cn_name(server_info.getServer_cn_name());
//				}
//				String pub_template_name = pg_rele_info.getPub_template_name();
//				output.setPub_template_name(pub_template_name);
//				List<PhasePublishBean> pub_phase_list = envProgPublicService
//						.getPhasePublicBean(pub_template_name, prog_id);
//				output.setPub_phase_list(pub_phase_list);
//				output.setRol_template_name(pg_rele_info.getRol_template_name());
//				List<UuParamInfo> pub_param_list = envProgPublicService
//						.getUuParam(pg_rele_info.getPubl_param_uuid());
//				output.setPub_param_list(pub_param_list);
//
//				String rol_template_name = pg_rele_info.getRol_template_name();
//				if (!Assert.isEmpty(rol_template_name)) {
//					output.setRol_template_name(rol_template_name);
//					List<PhasePublishBean> rol_phase_list = envProgPublicService
//							.getPhasePublicBean(rol_template_name, prog_id);
//					output.setRol_template_name(pg_rele_info
//							.getRol_template_name());
//					output.setRol_phase_list(rol_phase_list);
//					List<UuParamInfo> rol_param_list = envProgPublicService
//							.getUuParam(pg_rele_info.getRoll_param_uuid());
//					output.setRol_param_list(rol_param_list);
//				}
//			}
		}

		logger.info("-----------queryEnvProgInfo end----------");
		return output;
	}

	/**
	 * Description: 查询单个集成方案步骤
	 * @param input
	 * @return
	 */
	public ViewProgOutputBean queryInteProgStep(ViewProgInputBean input) {
		logger.info("-----------queryInteProgStep begin----------");
		ViewProgOutputBean output = new ViewProgOutputBean();
		String prog_id = input.getProg_id();
		int step_id = input.getStep_id();
		// 非空校验
		Assert.assertNotEmpty(prog_id, ViewProgInputBean.PROG_IDCN);
		Assert.assertNotEmpty(step_id == 0 ? null : step_id,
				ViewProgInputBean.STEP_IDCN);
		// 合法性校验
		envProgPublicService.checkProgIdIsNotExist(prog_id);
		PgInteStepInfo stepInfo = new PgInteStepInfo();
		stepInfo.setProg_id(prog_id);
		stepInfo.setStep_id(step_id);
		stepInfo = pgInteStepDaoService.getInfoByKey(stepInfo);
		EnvProgStepBean stepBean = new EnvProgStepBean();
		if (stepInfo != null) {
			stepBean.setStep_expl(stepInfo.getStep_expl());
			STEP_TYPE stepType = stepInfo.getStep_type();
			stepBean.setStep_type(stepType);
			String soc_uuid = stepInfo.getSoc_uuid();
			List<UuSocInfo> socInfoList = uuSocDaoService.queryListInfoByUU(soc_uuid);
			if (!Assert.isEmpty(socInfoList)) {
				stepBean.setServer_name(socInfoList.get(0).getExe_server_name());
				stepBean.setSoc_name(socInfoList.get(0).getExe_soc_name());
			}
			// 类型为版本时
			if (stepType.equals(STEP_TYPE.VERSION)) {
				if (!Assert.isEmpty(socInfoList)) {
					stepBean.setCode_server_name(socInfoList.get(0).getVer_server_name());
					stepBean.setCode_soc_name(socInfoList.get(0).getVer_soc_name());
					stepBean.setCode_bk_dir(socInfoList.get(0).getCode_bk_dir());
				}
			} else if (stepType.equals(STEP_TYPE.SCRIPT)) {
				stepBean.setStep_bk_script(stepInfo.getStep_bk_script());
			} else if (stepType.equals(STEP_TYPE.COMPILE)) {
				stepBean.setCompile_type(stepInfo.getCompile_type());
				stepBean.setEnv_variable(stepInfo.getEnv_variable());
				stepBean.setCompile_param(stepInfo.getCompile_param());
				stepBean.setComplie_bk_path(stepInfo.getComplie_bk_path());
			} else if (stepType.equals(STEP_TYPE.STORAGE)) {
				if (!Assert.isEmpty(socInfoList)) {
					stepBean.setTar_server_name(socInfoList.get(0).getVer_server_name());
					stepBean.setTar_soc_name(socInfoList.get(0).getVer_soc_name());
					stepBean.setTar_bk_dir(socInfoList.get(0).getCode_bk_dir());
					stepBean.setTarget_root_path(stepInfo.getStorage_bk_path());
					String file_uuid = stepInfo.getStorage_list_uuid();
					List<UuFilelistInfo> filelis = uuFilelistDaoService
							.getInfoByFileUuId(file_uuid);
					List<String> packname = new ArrayList<String>();

					List<TargetPackageBean> tar_package_list = new ArrayList<TargetPackageBean>();
					if (!Assert.isEmpty(filelis)) {
						String target_relative_path = "";
						for (UuFilelistInfo listInfo : filelis) {
							String packn = listInfo.getPackage_name();
							target_relative_path = listInfo.getTarget_relative_path();
							if (!packname.contains(packn)) {
								packname.add(packn);
							}
						}
						if (!Assert.isEmpty(packname)) {
							for (String name : packname) {
								TargetPackageBean packbean = new TargetPackageBean();
								packbean.setTarget_relative_path(target_relative_path);
								packbean.setPackage_name(name);
								List<UuFilelistInfo> lis = new ArrayList<UuFilelistInfo>();
								for (UuFilelistInfo listInfo : filelis) {
									if (listInfo.getPackage_name().equals(name)) {
										lis.add(listInfo);
									}
								}
								packbean.setFile_list(lis);
								tar_package_list.add(packbean);
							}
						}
						stepBean.setTar_package_list(tar_package_list);
					}
				}
			}
		}
		output.setProg_step_bean(stepBean);
		logger.info("-----------queryInteProgStep end----------");
		return output;
	}

	/**
	 * Description: 根据类型查询所有已发布的方案列表
	 * @param input
	 * @return
	 */
	public ViewProgOutputBean queryPubProgByType(ViewProgInputBean input) {
		logger.info("-----------queryPubProgByType begin----------");
		ViewProgOutputBean output = new ViewProgOutputBean();
		PROG_TYPE prog_type = input.getProg_type();
		String env_name = input.getEnv_name();
		// 非空校验
		Assert.assertNotEmpty(prog_type, ViewProgInputBean.PROG_IDCN);
		Assert.assertNotEmpty(env_name, ViewProgInputBean.ENV_NAMECN);
		List<PgProgramInfo> prog_list = pgProgramDaoService.getInfoByProgType(
				prog_type, env_name);
		List<PgProgramInfo> prog_list1 = new ArrayList<PgProgramInfo>();
		if (!Assert.isEmpty(prog_list)) {
			for (PgProgramInfo pgProgramInfo : prog_list) {
				if (pgProgramInfo.getIs_publish() == IS_PUBLISH.YES) {
					prog_list1.add(pgProgramInfo);
				}
			}
		}
		output.setProg_list(prog_list1);
		logger.info("-----------queryPubProgByType end----------");
		return output;
	}

	/**
	 * Description: 根据模板名查看流程模板的所有阶段和参数列表
	 * @param input
	 * @return
	 */
	public ViewProgOutputBean getStageFromTemplate(ViewProgInputBean input) {
		logger.debug("ViewCompAction_getStageFromTemplate Begin----------------");
		ViewProgOutputBean output = new ViewProgOutputBean();
		Assert.assertNotEmpty(input.getTemplate_name(),
				ViewProgInputBean.TEMPLATE_NAMECN);
		// 查看校验模板实例
		String template_name = input.getTemplate_name();
		templateService.checkTemplateNameIsExist(template_name);
		MoTemplateInfo info = templateService.getTemplateInfo(template_name);
		Assert.assertNotEmpty(info.getRef_module_id(), "流程模板引用ID");
		// 读取流程模板XML文件
		Template template = new Template(info.getRef_module_id());
		template = com.wk.cd.module1.xml1.XmlReader.read(template);
		
		//构建中模板存储了数据源，实际返回的模板不能含有数据源
		Template new_tem = template.copy(template);
		List<Phase> new_phase_list = new ArrayList<Phase>();
		
		List<Phase> phase_list = template.getPhase_list();
		if(!Assert.isEmpty(phase_list)) {
			for (Phase phase : phase_list) {
				new_phase_list.add(phase.copy(phase));
			}
			new_tem.setPhase_list(new_phase_list);
		}
		output.setTemplate(new_tem);
		logger.debug("ViewCompAction_getStageFromTemplate End----------------");
		return output;
	}

	/**
	 * Description: 根据环境名和模板类型查看模板名列表
	 * @param input
	 * @return
	 */
	public ViewProgOutputBean getTemplateByType(ViewProgInputBean input) {
		Assert.assertNotEmpty(input.getEnv_name(), ViewProgInputBean.ENV_NAMECN);
		Assert.assertNotEmpty(input.getTemplate_type(),
				ViewProgInputBean.TEMPLATE_TYPECN);

		ViewProgOutputBean output = new ViewProgOutputBean();
		String env_name = input.getEnv_name();
		TEMPLATE_TYPE template_type = input.getTemplate_type();
		logger.debug("----getTemplateByType env_name:[{}],template_type:[{}]",
				env_name, template_type.getCname());
		CeEnvironmentInfo info = environmentPublicService
				.getInfoByKey(env_name);
		String sys_name = info.getSys_name();
		List<String> temp_names = systemService.getInfoBySysName(sys_name,
				template_type);
		output.setTemplate_names(temp_names.toArray(new String[temp_names
				.size()]));
		return output;
	}
}
