/**
 * Title: AddPublishProgDetailAction.java
 * File Description: 保存发布方案的信息
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Zhangj
 * @version: 1.0
 * @date: 2016年12月14日
 */
package com.wk.cd.build.ea.action;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.wk.cd.build.ea.bean.AddPublishProgDetailInputBean;
import com.wk.cd.build.ea.bean.AddPublishProgDetailOutputBean;
import com.wk.cd.build.ea.dao.PgProgramDaoService;
import com.wk.cd.build.ea.dao.PgReleDaoService;
import com.wk.cd.build.ea.dao.UuSocDaoService;
import com.wk.cd.build.ea.info.PgProgramInfo;
import com.wk.cd.build.ea.info.PgReleInfo;
import com.wk.cd.build.ea.info.UuSocInfo;
import com.wk.cd.build.ea.service.EnvProgPublicService;
import com.wk.cd.build.en.service.EnvironmentPublicService;
import com.wk.cd.common.cm.service.GenNoService;
import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.IS_PUBLISH;
import com.wk.cd.enu.PROG_TYPE;
import com.wk.cd.module1.entity.Program;
import com.wk.cd.module1.xml1.XmlWriter;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description:保存发布方案的信息
 * @author Zhangj
 */
public class AddPublishProgDetailAction
		extends ActionBasic<AddPublishProgDetailInputBean, AddPublishProgDetailOutputBean> {
	@Inject
	private PgReleDaoService pgReleDaoService;
	@Inject
	private EnvProgPublicService envProgPublicService;
	@Inject
	private PgProgramDaoService pgProgramDaoService;
	@Inject
	private EnvironmentPublicService environmentPublicService;
	@Inject
	private UuSocDaoService uuSocDaoService;
	@Inject
	private GenNoService genNoSrv;
	@Inject
	private ActionLogPublicService lgsvc;
	private static final Log logger = LogFactory.getLog();
	private static final String SUFFIX_ROLL = "rollback";

	/**
	 * Description:保存发布方案的信息
	 * @param input
	 * @return
	 */
	@Override
	protected AddPublishProgDetailOutputBean doAction(AddPublishProgDetailInputBean input) {
		logger.info("-----------AddPublishProgDetailAction begin----------");
		// 获取基础数据并非空校验---------------------------
		checkNull(input);
		AddPublishProgDetailOutputBean output = new AddPublishProgDetailOutputBean();
//		PhasePublishBean[] rol_phase_list = input.getRol_phase_list();
//		String rol_template_name = input.getRol_template_name();
		Program pub_program = input.getPub_program();
		Program rol_program = input.getRol_program();
		//生成方案编号
		String prog_id = genNoSrv.getProgId(input.getDtbs_bk_date());
		pub_program.setProg_id(prog_id);
		XmlWriter.write(pub_program);
		if(!Assert.isEmpty(pub_program.getRol_template_name())) {
			rol_program.setProg_id(prog_id + SUFFIX_ROLL);
			XmlWriter.write(rol_program);
		}
		logger.debug("保存发布方案的PROG_ID[{}]", prog_id);

		// 插入环境方案
		PgProgramInfo program_info = new PgProgramInfo();
		program_info.setProg_id(prog_id);
		program_info.setIs_publish(IS_PUBLISH.NO);
		program_info.setProg_name(pub_program.getProg_name());
		program_info.setProg_type(PROG_TYPE.PUBLISH);
		program_info.setEnv_name(input.getEnv_name());
		program_info.setCrt_bk_date(input.getDtbs_bk_date());
		program_info.setCrt_bk_time(input.getDtbs_bk_time());
		program_info.setCrt_user_id(input.getOrg_user_id());
		pgProgramDaoService.insertInfo(program_info);
		
//		pgProgramDaoService.insertInfo(getPgProgramInfo(input, prog_id));
//		// 插入发布的参数和步骤表
//		PgReleInfo rele_info = getPgReleInfo(input, prog_id);
		
		// 插入发布的参数和步骤表
		PgReleInfo rele_info = new PgReleInfo();
		rele_info.setProg_id(prog_id);
		String pub_param_id = UUID.randomUUID().toString().replace("-", "");
		rele_info.setPubl_param_uuid(pub_param_id);
		rele_info.setPub_template_name(pub_program.getPub_template_name());
//		envProgPublicService.insertUuParamInfo(pub_program.getParam_list(), pub_param_id);
		
		
		if(!Assert.isEmpty(rol_program.getRol_template_name())) {
			String rol_param_id = UUID.randomUUID().toString().replace("-", "");
			rele_info.setRol_template_name(pub_program.getRol_template_name());
			rele_info.setRoll_param_uuid(rol_param_id);
//			envProgPublicService.insertUuParamInfo(rol_program.getParam_list(), pub_param_id);
		}
		
//		if (!Assert.isEmpty(pub_program)) {
//			String pub_param_id = UUID.randomUUID().toString().replace("-", "");
//			rele_info.setPubl_param_uuid(pub_param_id);
//			envProgPublicService.insertUuParam(input.getPub_param_list(), pub_param_id);
//		}
		
		String ver_soc_uuid = UUID.randomUUID().toString().replace("-", "");
		UuSocInfo usi = new UuSocInfo();
		usi.setSoc_bk_seq(1);
		usi.setVer_server_name(pub_program.getVer_server_name());
		usi.setVer_soc_name(pub_program.getVer_soc_name());
		usi.setExe_server_name(pub_program.getVer_server_name());
		usi.setExe_soc_name(pub_program.getVer_soc_name());
		usi.setSoc_uuid(ver_soc_uuid);
		usi.setCode_bk_dir(pub_program.getCode_bk_dir());
		uuSocDaoService.insertInfo(usi);
		rele_info.setVer_soc_uuid(ver_soc_uuid);
//		// 插入发布方案拓展表
		pgReleDaoService.insertInfo(rele_info);
		logger.info("-----------AddPublishProgDetailAction end----------");
		return output;
	}

	/**
	 * Description:
	 * @param input
	 * @return
	 */
	@Override
	protected String getLogTxt(AddPublishProgDetailInputBean input) {
		List<String> log_lst = new ArrayList<String>();
//		log_lst.add("发布模板: " + input.getPub_template_name());
//		log_lst.add("回退模板: " + input.getRol_template_name());
		return lgsvc.getLogTxt("新增环境方案", log_lst);
	}

	/**
	 * Description: 非空校验
	 * @param input
	 */
	private void checkNull(AddPublishProgDetailInputBean input) {
		Program program = input.getPub_program();
		Assert.assertNotEmpty(program, AddPublishProgDetailInputBean.PUB_PROGRAMCN);
		Assert.assertNotEmpty(program.getProg_name(), Program.PROG_NAMECN);
		Assert.assertNotEmpty(program.getPub_template_name(), Program.PUB_TEMPLATE_NAMECN);
		Assert.assertNotEmpty(program.getVer_server_name(), Program.VER_SERVER_NAMECN);
		Assert.assertNotEmpty(program.getVer_soc_name(), Program.VER_SOC_NAMECN);
		Assert.assertNotEmpty(program.getCode_bk_dir(), Program.CODE_BK_DIRCN);
		Assert.assertNotEmpty(program.getProg_type(), Program.PROG_TYPECN);
		Assert.assertNotEmpty(program.getPhase_list(), Program.PHASE_LISTCN);
		
//		Assert.assertNotEmpty(input.getPub_phase_list(), AddPublishProgDetailInputBean.PUB_PHASE_LISTCN);
		// Assert.assertNotEmpty(input.getPub_param_list(),
		// UpdatePublishProgDetailInputBean.PUB_PARAM_LISTCN);
//		Assert.assertNotEmpty(input.getPub_template_name(), AddPublishProgDetailInputBean.PUB_TEMPLATE_NAMECN);
//		Assert.assertNotEmpty(input.getEnv_name(), AddPublishProgDetailInputBean.ENV_NAMECN);
//		Assert.assertNotEmpty(input.getProg_name(), AddPublishProgDetailInputBean.PROG_NAMECN);
//		Assert.assertNotEmpty(input.getVer_server_name(), AddPublishProgDetailInputBean.VER_SERVER_NAMECN);
//		Assert.assertNotEmpty(input.getVer_soc_name(), AddPublishProgDetailInputBean.VER_SOC_NAMECN);
//		Assert.assertNotEmpty(input.getCode_bk_dir(), AddPublishProgDetailInputBean.CODE_BK_DIRCN);
		environmentPublicService.checkEnvNameIsExist(input.getEnv_name());
	}

}
