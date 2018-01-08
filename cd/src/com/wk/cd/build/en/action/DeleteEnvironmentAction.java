/**
 * Title: DeleteEnvironmentAction.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: xuph
 * @version: 1.0
 * @date: 2016年10月31日
 */
package com.wk.cd.build.en.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.build.ea.dao.EnvTaskDaoService;
import com.wk.cd.build.ea.dao.PgInteStepDaoService;
import com.wk.cd.build.ea.dao.PgProgramDaoService;
import com.wk.cd.build.ea.dao.PgReleDaoService;
import com.wk.cd.build.ea.dao.PgReleStepDaoService;
import com.wk.cd.build.ea.info.PgProgramInfo;
import com.wk.cd.build.ea.service.EnvProgPublicService;
import com.wk.cd.build.en.bean.DeleteEnvironmentInputBean;
import com.wk.cd.build.en.bean.DeleteEnvironmentOutputBean;
import com.wk.cd.build.en.dao.CeEnvironmentDaoService;
import com.wk.cd.build.en.dao.CeEnvironmentServerDaoService;
import com.wk.cd.build.en.service.EnvironmentPublicService;
import com.wk.cd.build.exc.EnvTaskIsExistException;
import com.wk.cd.enu.PROG_TYPE;
import com.wk.cd.common.util.Assert;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 删除应用环境
 * @author xuph
 */
public class DeleteEnvironmentAction extends ActionBasic<DeleteEnvironmentInputBean, DeleteEnvironmentOutputBean> {
	@Inject private EnvironmentPublicService cePublishService;
	@Inject private CeEnvironmentDaoService ceEnvironmentDaoService;
	@Inject private CeEnvironmentServerDaoService ceEnvironmentServerDaoService;
	@Inject private PgProgramDaoService pgProgramDaoService;
	@Inject private PgInteStepDaoService pgInteStepDaoService;
	@Inject private PgReleDaoService pgReleDaoService;
	@Inject private PgReleStepDaoService pgReleStepDaoService;
	@Inject private EnvProgPublicService envProgPublicService;
	@Inject private EnvTaskDaoService envTaskDaoService;
	@Inject private ActionLogPublicService lgsvc;
	private static final Log logger = LogFactory.getLog();

	/**
	 * Description: 删除应用环境操作
	 * @param arg0
	 * @return
	 */
	@Override
	protected DeleteEnvironmentOutputBean doAction(DeleteEnvironmentInputBean input) {
		logger.info("----------------------DeleteEnvironmentAction Begin -------------");
		DeleteEnvironmentOutputBean output = new DeleteEnvironmentOutputBean();
		String env_name = input.getEnv_name();
		// 非空校检
		Assert.assertNotEmpty(env_name, DeleteEnvironmentInputBean.ENV_NAMECN);
		// 合法性校检
		cePublishService.checkEnvNameIsExist(env_name);
		List<PgProgramInfo> pg_list =  pgProgramDaoService.getEvnPgList(env_name);
		if(!Assert.isEmpty(pg_list)){
			for (PgProgramInfo pginfo : pg_list) {
				String prog_id = pginfo.getProg_id();
				// 删除环境方案关联信息
				envProgPublicService.checkProgIdIsNotExist(prog_id);
				PgProgramInfo program_info = pgProgramDaoService.getInfoByKey(prog_id);
				PROG_TYPE prog_type = program_info.getProg_type();
				if (PROG_TYPE.PUBLISH.equals(prog_type)) {
					envProgPublicService.deletePubByProgId(prog_id);
				}
				// 删除方案环境表
				pgProgramDaoService.deleteProg(prog_id);
				if (envTaskDaoService.countEnvTaskByProgId(prog_id) > 0) {
					throw new EnvTaskIsExistException().addScene("PROGID", prog_id);
				}
				// 删除集成方案步骤表
				pgInteStepDaoService.deleteProgStep(prog_id);
				// 删除发布方案扩展表表
				pgReleDaoService.deleteProgRele(prog_id);
				// 删除发布方案阶段表
				pgReleStepDaoService.deleteProgReleStep(prog_id);
			}
		}
		// 删除环境服务关联
		ceEnvironmentServerDaoService.deleteEnvirServerByEnvName(env_name);
		// 删除应用环境
		ceEnvironmentDaoService.deleteEnvirByEnvName(env_name);
		logger.info("----------------------DeleteEnvironmentAction End-- -------------");
		return output;
	}

	/**
	 * Description:
	 * @param arg0
	 * @return
	 */
	@Override
	protected String getLogTxt(DeleteEnvironmentInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add("Env_name=" + input.getEnv_name());
		return lgsvc.getLogTxt("删除应用环境", lst_val);
	}

}
