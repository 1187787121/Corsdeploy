/**
 * Title: AddEnvProgBasicAction.java
 * File Description: 添加环境方案信息
 * @copyright: 2016
 * @company: CORSWORK
 * @author: chiss
 * @version: 1.0
 * @date: 2016年11月10日
 */
package com.wk.cd.build.ea.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.build.ea.bean.AddEnvProgBasicInputBean;
import com.wk.cd.build.ea.bean.AddEnvProgBasicOutputBean;
import com.wk.cd.build.ea.dao.PgInteStepDaoService;
import com.wk.cd.build.ea.dao.PgProgramDaoService;
import com.wk.cd.build.ea.dao.PgReleDaoService;
import com.wk.cd.build.ea.info.PgProgramInfo;
import com.wk.cd.build.ea.info.PgReleInfo;
import com.wk.cd.build.ea.service.InteTaskInstanceService;
import com.wk.cd.build.ea.service.TargetPackPublicService;
import com.wk.cd.build.en.dao.CeServerDsDaoService;
import com.wk.cd.common.cm.service.GenNoService;
import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.IS_PUBLISH;
import com.wk.cd.enu.PROG_TYPE;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 添加环境方案信息
 * 
 * @author chiss
 */
public class AddEnvProgBasicAction extends ActionBasic<AddEnvProgBasicInputBean, AddEnvProgBasicOutputBean> {
	@Inject
	private GenNoService genNoSrv;
	@Inject
	private PgProgramDaoService pgProgramDaoService;
	@Inject
	private PgReleDaoService pgReleDaoService;
	@Inject
	private PgInteStepDaoService pgInteStepDaoService;
	@Inject
	private CeServerDsDaoService ceServerDsDaoService;
	@Inject
	private TargetPackPublicService targetPackService;
	@Inject
	private ActionLogPublicService lgsvc;
	private static final Log logger = LogFactory.getLog();

	/**
	 * Description: 添加环境方案信息
	 * @param input
	 * @return
	 */
	@Override
	protected AddEnvProgBasicOutputBean doAction(AddEnvProgBasicInputBean input) {
		logger.info("-----------AddEnvProgBasicAction begin----------");
		AddEnvProgBasicOutputBean output = new AddEnvProgBasicOutputBean();
		PROG_TYPE prog_type = input.getProg_type();
		String prog_name = input.getProg_name();
		String env_name = input.getEnv_name();

		// 非空校验
		Assert.assertNotEmpty(prog_type, AddEnvProgBasicInputBean.PROG_TYPECN);
		Assert.assertNotEmpty(prog_name, AddEnvProgBasicInputBean.PROG_NAMECN);
		Assert.assertNotEmpty(env_name, AddEnvProgBasicInputBean.ENV_NAMECN);

		// 生成方案编号
		String progId = genNoSrv.getProgId(input.getDtbs_bk_date());
		// 环境方案表
		PgProgramInfo programInfo = new PgProgramInfo();
		programInfo.setProg_id(progId);
		programInfo.setIs_publish(IS_PUBLISH.NO);
		programInfo.setProg_name(prog_name);
		programInfo.setProg_type(prog_type);
		programInfo.setEnv_name(env_name);
		programInfo.setCrt_bk_date(input.getDtbs_bk_date());
		programInfo.setCrt_bk_time(input.getDtbs_bk_time());
		programInfo.setCrt_user_id(input.getOrg_user_id());
		// 插入环境方案
		pgProgramDaoService.insertInfo(programInfo);
		// 发布方案扩展表
		if (prog_type == PROG_TYPE.PUBLISH) {
			PgReleInfo releInfo = new PgReleInfo();
			String pub_template_name = input.getPub_template_name();
			String rol_template_name = input.getRol_template_name();
			Assert.assertNotEmpty(pub_template_name, AddEnvProgBasicInputBean.PUB_TEMPLATE_NAMECN);
			// 存在性校验
			releInfo.setProg_id(progId);
			releInfo.setPub_template_name(pub_template_name);
			releInfo.setRol_template_name(rol_template_name);
			// 若为发布方案，插入布方案扩展表表
			pgReleDaoService.insertInfo(releInfo);

		}
		output.setProg_id(programInfo.getProg_id());
		if (prog_type == PROG_TYPE.INTEGRATION) {
			output.setNext_step_id(1);
			output.setComplie_bk_path("./" + env_name + "/" + InteTaskInstanceService.SRC_CODE_DIR + "/${ver_num}/");
			output.setTarget_root_path("./" + env_name + "/" + InteTaskInstanceService.SRC_CODE_DIR + "/${ver_num}/");
		}
		logger.info("-----------AddEnvProgBasicAction end----------");
		return output;
	}

	/**
	 * Description: 添加环境方案信息
	 * @param input
	 * @return
	 */
	@Override
	protected String getLogTxt(AddEnvProgBasicInputBean input) {
		List<String> log_lst = new ArrayList<String>();
		log_lst.add("方案名称: " + input.getProg_name());
		return lgsvc.getLogTxt("新增环境方案", log_lst);
	}

}
