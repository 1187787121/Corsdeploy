/**
 * Title: UpdateEnvProgBasicAction.java
 * File Description: 修改环境方案基本信息
 * @copyright: 2016
 * @company: CORSWORK
 * @author: yangl
 * @version: 1.0
 * @date: 2016年11月11日
 */
package com.wk.cd.build.ea.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.build.ea.bean.AddEnvProgBasicInputBean;
import com.wk.cd.build.ea.bean.UpdateEnvProgBasicInputBean;
import com.wk.cd.build.ea.bean.UpdateEnvProgBasicOutputBean;
import com.wk.cd.build.ea.dao.PgInteStepDaoService;
import com.wk.cd.build.ea.dao.PgProgramDaoService;
import com.wk.cd.build.ea.dao.PgReleDaoService;
import com.wk.cd.build.ea.info.PgProgramInfo;
import com.wk.cd.build.ea.service.EnvProgPublicService;
import com.wk.cd.build.ea.service.TargetPackPublicService;
import com.wk.cd.enu.PROG_TYPE;
import com.wk.cd.common.util.Assert;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 修改环境方案基本信息
 * @author yangl
 */
public class UpdateEnvProgBasicAction extends ActionBasic<UpdateEnvProgBasicInputBean, UpdateEnvProgBasicOutputBean> {

	@Inject
	private PgProgramDaoService pgProgramDaoService;
	@Inject
	private PgReleDaoService pgReleDaoService;
	@Inject
	private PgInteStepDaoService pgInteStepDaoService;
	@Inject
	private EnvProgPublicService envProgPublicService;
	@Inject
	private TargetPackPublicService targetPackService;
	@Inject
	private ActionLogPublicService lgsvc;
	private static final Log logger = LogFactory.getLog();

	/**
	 * Description: 修改环境方案基本信息
	 * @param input
	 * @return
	 */
	@Override
	protected UpdateEnvProgBasicOutputBean doAction(UpdateEnvProgBasicInputBean input) {
		logger.info("-----------UpdateEnvProgBasicAction begin----------");
		UpdateEnvProgBasicOutputBean output = new UpdateEnvProgBasicOutputBean();
		String prog_id = input.getProg_id();
		PROG_TYPE prog_type = input.getProg_type();
		String prog_name = input.getProg_name();
		// 非空校验
		Assert.assertNotEmpty(prog_id, UpdateEnvProgBasicInputBean.PROG_IDCN);
		Assert.assertNotEmpty(prog_type, UpdateEnvProgBasicInputBean.PROG_TYPECN);
		Assert.assertNotEmpty(prog_name, UpdateEnvProgBasicInputBean.PROG_NAMECN);
		// 存在校验
		envProgPublicService.checkProgIdIsNotExist(prog_id);
		// 环境方案表
		PgProgramInfo programInfo = pgProgramDaoService.getInfoByKey(prog_id);
		// 修改环境方案信息
		pgProgramDaoService.updatePgProgramByKey(prog_name, prog_id);
		// 发布方案扩展表
		if (prog_type == PROG_TYPE.PUBLISH) {
			// 获取发布方案扩展表信息
			String pub_template_name = input.getPub_template_name();
			String rol_template_name = input.getRol_template_name();
			Assert.assertNotEmpty(prog_type, AddEnvProgBasicInputBean.PROG_TYPECN);
			Assert.assertNotEmpty(pub_template_name, AddEnvProgBasicInputBean.PUB_TEMPLATE_NAMECN);
			// 修改发布方案扩张表信息
			pgReleDaoService.UpdatePgReleByKey(pub_template_name, rol_template_name, prog_id);
		}
		output.setProg_id(programInfo.getProg_id());
		if (prog_type == PROG_TYPE.INTEGRATION) {
			output.setNext_step_id(pgInteStepDaoService.countPgStepByKey(prog_id) + 1);
		}
		logger.info("-----------UpdateEnvProgBasicAction end----------");
		return output;
	}

	/**
	 * Description: 日志写入
	 * @param input
	 * @return
	 */
	@Override
	protected String getLogTxt(UpdateEnvProgBasicInputBean input) {
		List<String> log_lst = new ArrayList<String>();
		log_lst.add("方案名称: " + input.getProg_name());
		return lgsvc.getLogTxt("修改环境方案", log_lst);
	}

}
