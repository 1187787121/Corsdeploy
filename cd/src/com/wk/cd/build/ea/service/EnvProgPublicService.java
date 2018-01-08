/**
 * Title: EnvProgPublicService.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Administrator
 * @version: 1.0
 * @date: 2016年11月10日
 */
package com.wk.cd.build.ea.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.wk.cd.build.ea.bean.PhasePublishBean;
import com.wk.cd.build.ea.bean.SrvSocBean;
import com.wk.cd.build.ea.dao.EnvBuildTaskDaoService;
import com.wk.cd.build.ea.dao.EnvTagStorageDaoService;
import com.wk.cd.build.ea.dao.PgProgramDaoService;
import com.wk.cd.build.ea.dao.PgReleDaoService;
import com.wk.cd.build.ea.dao.PgReleStepDaoService;
import com.wk.cd.build.ea.dao.UuParamDaoService;
import com.wk.cd.build.ea.dao.UuSocDaoService;
import com.wk.cd.build.ea.info.EnvBuildTaskInfo;
import com.wk.cd.build.ea.info.PgReleInfo;
import com.wk.cd.build.ea.info.PgReleStepInfo;
import com.wk.cd.build.ea.info.UuParamInfo;
import com.wk.cd.build.ea.info.UuSocInfo;
import com.wk.cd.build.en.dao.CeServerDaoService;
import com.wk.cd.build.en.info.CeServerInfo;
import com.wk.cd.build.en.service.ServerCommonService;
import com.wk.cd.build.exc.EnvProgIsExistException;
import com.wk.cd.build.exc.EnvProgIsNotExistException;
import com.wk.cd.build.exc.EnvProgReleIsNotExistException;
import com.wk.cd.build.exc.EnvProgStepIsNotExistException;
import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.PROTOCOL_TYPE;
import com.wk.cd.enu.YN_FLAG;
import com.wk.cd.enu.IMPL_TYPE;
import com.wk.cd.module1.entity.PhaseParam;
import com.wk.cd.system.dt.service.DtSocService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description:环境方案的服务类
 * @author Administrator
 */
public class EnvProgPublicService {
	@Inject
	private PgProgramDaoService pgProgramDaoService;
	@Inject
	private PgReleStepDaoService pgReleStepDaoService;
	@Inject
	private PgReleDaoService pgReleDaoService;
	@Inject
	private EnvTagStorageDaoService envTagStorageDaoService;
	@Inject
	private UuSocDaoService uuSocDaoService;
	@Inject
	private EnvBuildTaskDaoService envBuildTaskDaoService;

	@Inject
	private CeServerDaoService ceServerDaoService;
	@Inject
	private ServerCommonService serverCommonService;
	@Inject
	private UuParamDaoService uuParamDaoService;
	@Inject
	private DtSocService dtSocService;
	private static final Log logger = LogFactory.getLog();

	/**
	 * Description: 如果方案编号存在就抛异常
	 * @param prog_id
	 */
	public void checkProgIdIsExist(String prog_id) {
		if (pgProgramDaoService.countByProgId(prog_id) > 0) {
			throw new EnvProgIsExistException().addScene("ENVPROG", prog_id);
		}
	}

	/**
	 * Description: 如果方案编号不存在就抛异常
	 * @param prog_id
	 */
	public void checkProgIdIsNotExist(String prog_id) {
		if (pgProgramDaoService.countByProgId(prog_id) <= 0) {
			throw new EnvProgIsNotExistException().addScene("ENVPROG", prog_id);
		}
	}

	public PgReleInfo checkProgRele(String prog_id) {
		PgReleInfo pg_rele_info = pgReleDaoService.getInfoByKey(prog_id);
		Assert.assertNotEmpty(pg_rele_info, "发布方案扩展信息");
		Assert.assertNotEmpty(pg_rele_info.getPub_template_name(), "发布模板");
		return pg_rele_info;
	}

	/**
	 * Description: 主键ID不存在就抛异常
	 * @param prog_id
	 * @param step_id
	 */
	public void checkKeyIdIsNotExist(String prog_id, int step_id) {
		if (pgProgramDaoService.countByKeyId(prog_id, step_id) <= 0) {
			throw new EnvProgStepIsNotExistException().addScene("PROGID", prog_id).addScene("STEPID", step_id);
		}
	}

	/**
	 * Description: 如果模板名称不存在就抛异常
	 * @param prog_name
	 */
	public void checkTemplateNameIsNotExist(String template_name) {
		if (pgReleStepDaoService.countByTemplateName(template_name) <= 0) {
			throw new EnvProgReleIsNotExistException().addScene("NAME", template_name);
		}
	}

	/**
	 * Description: 查看阶段配置信息
	 * @param template_name
	 * @param prog_id
	 * @return
	 */
	public List<PhasePublishBean> getPhasePublicBean(String template_name, String prog_id) {
		List<PhasePublishBean> list = new ArrayList<PhasePublishBean>();
		List<PgReleStepInfo> rele_step_list = pgReleStepDaoService.getInfoByTpNameAndPgId(template_name, prog_id);
		int phase_no = 1;
		for (PgReleStepInfo info : rele_step_list) {
			PhasePublishBean bean = PgReleStepInfo.copyToPhasePublicBean(info);
			List<SrvSocBean> srv_soc_list = new ArrayList<SrvSocBean>();
			String soc_uuid = info.getSoc_uuid();
			List<UuSocInfo> ex_soc_infos = uuSocDaoService.queryListInfoByUU(soc_uuid);
			for (UuSocInfo uesi : ex_soc_infos) {
				SrvSocBean ssb = getSrvSocBean(uesi);
				srv_soc_list.add(ssb);
			}
			bean.setSrv_soc(srv_soc_list.toArray(new SrvSocBean[srv_soc_list.size()]));
			bean.setPhase_no(phase_no++);
			list.add(bean);
		}
		return list;
	}

	private SrvSocBean getSrvSocBean(UuSocInfo uesi){
		String exe_server_name = uesi.getExe_server_name();
		String exe_soc_name = uesi.getExe_soc_name();
		PROTOCOL_TYPE pt = dtSocService.getInfoByKey(exe_soc_name).getProtocol_type();
		IMPL_TYPE type = serverCommonService.getImplTypeByProtocol(pt);
		SrvSocBean ssb = new SrvSocBean();
		ssb.setExe_server_name(exe_server_name);
		ssb.setExe_soc_name(exe_soc_name);
		CeServerInfo exe_info = ceServerDaoService.getInfoByServerName(exe_server_name);
		if (!Assert.isEmpty(exe_info)) {
			ssb.setExe_cn_name(exe_info.getServer_cn_name());
			List<String> soc_list = serverCommonService.getSocNameByServiceNameAndType(exe_server_name,type);
			ssb.setExe_soc_list(soc_list.toArray(new String[soc_list.size()]));
		}
		String ver_server_name = uesi.getVer_server_name();
		String ver_soc_name = uesi.getVer_soc_name();
		if(!Assert.isEmpty(ver_server_name) ){
			ssb.setVer_soc_name(ver_soc_name);
			CeServerInfo ver_info = ceServerDaoService.getInfoByServerName(ver_server_name);
			if(!Assert.isEmpty(ver_info)){
				ssb.setVer_cn_name(ver_info.getServer_cn_name());
				List<String> soc_list = serverCommonService.getSocNameByServiceNameAndType(ver_server_name,IMPL_TYPE.SVN);
				ssb.setVer_soc_list(soc_list.toArray(new String[soc_list.size()]));
				ssb.setVer_server_name(ver_server_name);
			}
		}
		return ssb;
	}
	/**
	 * Description: 根据参数id查看参数
	 * @param uuid
	 * @return
	 */
	public List<UuParamInfo> getUuParam(String uuid) {
		List<UuParamInfo> list = new ArrayList<UuParamInfo>();
		if (Assert.isEmpty(uuid)) {
			return list;
		}
		return uuParamDaoService.getInfoByUuid(uuid);
	}

	/**
	 * Description: 根据方案编号 删除执行数据源关联表和发布方案阶段表
	 * @param prog_id
	 */
	public void deletePubByProgId(String prog_id) {
		List<PgReleStepInfo> rele_step_list = pgReleStepDaoService.getReleListByProgId(prog_id);
		if (!Assert.isEmpty(rele_step_list)) {
			// 删除执行数据源关联表
			for (PgReleStepInfo info : rele_step_list) {
				String uuid = info.getSoc_uuid();
				uuSocDaoService.deleteListByUU(uuid);
			}
			// 删除发布方案阶段表
			pgReleStepDaoService.deleteProgReleStep(prog_id);
		}

	}
	
	public void deletePubVerSocByProgId(String prog_id){
		PgReleInfo info = checkProgRele(prog_id);
		String ver_soc_uuid = info.getVer_soc_uuid();
		uuSocDaoService.deleteListByUU(ver_soc_uuid);
	}

	/**
	 * Description: 根据方案编号删除
	 * @param prog_id
	 */
	public void deleteUuParamById(String prog_id) {
		PgReleInfo info = checkProgRele(prog_id);
		String roll_param_id = info.getRoll_param_uuid();
		String pub_param_id = info.getPubl_param_uuid();
		if (!Assert.isEmpty(roll_param_id))
			uuParamDaoService.deleteById(roll_param_id);
		if (!Assert.isEmpty(pub_param_id))
			uuParamDaoService.deleteById(pub_param_id);

	}

	/**
	 * Description: 插入发布方案阶段表（PgReleStepInfo）和 执行数据源关联表（UuExsocInfo）
	 * @param list
	 * @param template_name
	 * @param prog_id
	 */
	public void insertReleStep(PhasePublishBean[] list, String template_name, String prog_id) {
		logger.debug("插入发布方案的阶段信息表,方案编号[{}],模板名[{}]", prog_id, template_name);
		List<PgReleStepInfo> rele_step_list = new ArrayList<PgReleStepInfo>();
		List<UuSocInfo> exsoc_list = new ArrayList<UuSocInfo>();
		int index = 1;
		int seq = 1;
		for (PhasePublishBean bean : list) {
			// 发布方案 阶段信息
			String uuid = UUID.randomUUID().toString().replace("-", "");
			PgReleStepInfo info = new PgReleStepInfo();
			info.setPhase_bk_desc(bean.getCn_name());
			info.setPhase_id(index++);
			info.setProg_id(prog_id);
			info.setSoc_uuid(uuid);
			info.setTemplate_name(template_name);
			info.setGen_yn_flag(bean.getGen_flag());
			info.setImpl_type(bean.getImpl_type());
			if (YN_FLAG.YES.equals(bean.getGen_flag())) {
				// 阶段数据源关联信息
				SrvSocBean[] ssbs = bean.getSrv_soc();
				Assert.assertNotEmpty(ssbs, "方案阶段" + index + "需要执行的数据源");
				for (SrvSocBean ssb : ssbs) {
					UuSocInfo usi = new UuSocInfo();
					// 避免前端传入阶段里面数据源重复，导致主键冲突
					String exe_soc_name = ssb.getExe_soc_name();
					String exe_server_name = ssb.getExe_server_name();
					String ver_soc_name = ssb.getVer_soc_name();
					String ver_server_name = ssb.getVer_server_name();
					usi.setSoc_bk_seq(seq++);
					usi.setExe_server_name(exe_server_name);
					usi.setExe_soc_name(exe_soc_name);
					usi.setVer_server_name(ver_server_name);
					usi.setVer_soc_name(ver_soc_name);
					usi.setSoc_uuid(uuid);
					exsoc_list.add(usi);
				}
			}

			rele_step_list.add(info);
		}
		pgReleStepDaoService.insertListInfo(rele_step_list);
		uuSocDaoService.insertListInfo(exsoc_list);
	}
	
	/**
	 * Description: 删除发布参数表
	 * @param work_id
	 */
	public void deleteUuParam(String work_id){
		//获取原投产包参数
		EnvBuildTaskInfo task_info = envBuildTaskDaoService.getInfoByKey(work_id);
		//获取投产包参数
		String param_uuid = task_info.getTemplate_param_uuid();
		uuParamDaoService.deleteById(param_uuid);
	}
	
	/**
	 * Description: 插入发布参数表
	 * @param params
	 * @param template_name
	 * @param prog_id
	 */
	public void insertUuParam(List<UuParamInfo> params, String param_uuid) {
		logger.debug("插入发布方案的参数，方案编号[{}],模板名[{}]");
		if (Assert.isEmpty(params)) {
			return;
		}
		List<UuParamInfo> list = new ArrayList<UuParamInfo>();
		for (UuParamInfo info : params) {
			Assert.assertNotEmpty(info.getParam_name(), UuParamInfo.PARAM_NAMECN);
			Assert.assertNotEmpty(info.getParam_modify_flag(), UuParamInfo.PARAM_MODIFY_FLAGCN);
			UuParamInfo param = UuParamInfo.copy(info);
			param.setParam_uuid(param_uuid);
			list.add(param);
		}
		if (!Assert.isEmpty(list)) {
			uuParamDaoService.insertListInfo(list);
		}
	}
	
	/**
	 * Description: 插入发布参数表
	 * @param params
	 * @param template_name
	 * @param prog_id
	 */
	public void insertUuParamByParams(List<PhaseParam> params, String param_uuid) {
		logger.debug("插入发布方案的参数，方案编号[{}],模板名[{}]");
		if (Assert.isEmpty(params)) {
			return;
		}
		List<UuParamInfo> list = new ArrayList<UuParamInfo>();
		for (int i = 0; i < params.size(); i++) {
			PhaseParam param = params.get(i);
			Assert.assertNotEmpty(param.getParam_name(), "参数名");
			Assert.assertNotEmpty(param.getModify_flag(), "是否可修改");
			UuParamInfo uu_param = new UuParamInfo();
			
			uu_param.setParam_bk_desc(param.getParam_bk_desc());
			uu_param.setParam_cn_name(param.getParam_cn_name());
//			uu_param.setParam_group("");
			uu_param.setParam_modify_flag(param.getModify_flag());
			uu_param.setParam_name(param.getParam_name());
			uu_param.setParam_type(param.getParam_type());
			uu_param.setParam_value(param.getParam_value());
			if(!Assert.isEmpty(param.getParam_value())){
				uu_param.setParam_value(param.getParam_value().trim());
			}
			uu_param.setPhase_no(i+1);
			uu_param.setParam_uuid(param_uuid);
			list.add(uu_param);
		}
		if (!Assert.isEmpty(list)) {
			uuParamDaoService.insertListInfo(list);
		}
	}
	

	
	/**
	 * Description: 插入发布参数表
	 * @param params
	 * @param template_name
	 * @param prog_id
	 */
	public void insertUuParamInfo(List<PhaseParam> params, String param_uuid) {
		logger.debug("插入发布方案的参数，方案编号[{}],模板名[{}]");
		if (Assert.isEmpty(params)) {
			return;
		}
		List<UuParamInfo> list = new ArrayList<UuParamInfo>();
		for (PhaseParam param : params) {
			Assert.assertNotEmpty(param.getParam_name(), "参数中文名");
			Assert.assertNotEmpty(param.getModify_flag(), "是否可修改");
			UuParamInfo bean = new UuParamInfo();
			bean.setParam_bk_desc(param.getParam_bk_desc());
			bean.setParam_cn_name(param.getParam_cn_name());
//			bean.setParam_group("");
			bean.setParam_modify_flag(param.getModify_flag());
			bean.setParam_name(param.getParam_name());
			bean.setParam_type(param.getParam_type());
			if(!Assert.isEmpty(param.getParam_value())){
				bean.setParam_value(param.getParam_value().trim());
			}
//			bean.setPhase_no(0);
			bean.setParam_uuid(param_uuid);
			list.add(bean);
		}
		if (!Assert.isEmpty(list)) {
			uuParamDaoService.insertListInfo(list);
		}
	}
}
