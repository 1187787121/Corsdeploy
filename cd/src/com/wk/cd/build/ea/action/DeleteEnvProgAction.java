/**
 * Title: DeleteEnvProgAction.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: chiss
 * @version: 1.0
 * @date: 2016年11月10日
 */
package com.wk.cd.build.ea.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.wk.cd.build.ea.bean.DeleteEnvProgInputBean;
import com.wk.cd.build.ea.bean.DeleteEnvProgOutputBean;
import com.wk.cd.build.ea.bean.PublishEnvProgInputBean;
import com.wk.cd.build.ea.dao.EnvTaskDaoService;
import com.wk.cd.build.ea.dao.PgInteStepDaoService;
import com.wk.cd.build.ea.dao.PgProgramDaoService;
import com.wk.cd.build.ea.dao.PgReleDaoService;
import com.wk.cd.build.ea.dao.PgReleStepDaoService;
import com.wk.cd.build.ea.info.PgProgramInfo;
import com.wk.cd.build.ea.service.EnvProgPublicService;
import com.wk.cd.enu.PROG_TYPE;
import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.FileTool;
import com.wk.cd.module1.entity.Program;
import com.wk.cd.module1.xml1.XmlPathUtil;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 删除单个环境方案
 * @author chiss
 */
public class DeleteEnvProgAction extends ActionBasic<DeleteEnvProgInputBean, DeleteEnvProgOutputBean>{
	@Inject
	private PgProgramDaoService pgProgramDaoService;
	@Inject
	private PgInteStepDaoService pgInteStepDaoService;
	@Inject
	private PgReleDaoService pgReleDaoService;
	@Inject
	private PgReleStepDaoService pgReleStepDaoService;
	@Inject 
	private EnvProgPublicService envProgPublicService;
	@Inject
	private EnvTaskDaoService envTaskDaoService;
	@Inject
	private ActionLogPublicService lgsvc;
	private static final Log logger = LogFactory.getLog();
	private static final String SUFFIX_ROLL = "rollback";
	/** 
	 * Description: 
	 * @param input
	 * @return 
	 */
	@Override
	protected DeleteEnvProgOutputBean doAction(DeleteEnvProgInputBean input) {
		logger.info("-----------DeleteEnvProgAction end----------");
		DeleteEnvProgOutputBean output = new DeleteEnvProgOutputBean();
		String prog_id = input.getProg_id();
		//非空校验
		Assert.assertNotEmpty(prog_id, PublishEnvProgInputBean.PROG_IDCN);
		//合法性校验
		envProgPublicService.checkProgIdIsNotExist(prog_id);
		PgProgramInfo program_info = pgProgramDaoService.getInfoByKey(prog_id);
		PROG_TYPE prog_type = program_info.getProg_type();
		if(PROG_TYPE.PUBLISH.equals(prog_type)){
			envProgPublicService.deletePubByProgId(prog_id);
			Program program = new Program(prog_id);
			if(!Assert.isEmpty(program.getRol_template_name())) {
				Program rol_program = new Program(prog_id+SUFFIX_ROLL);
				String file_path = XmlPathUtil.getXmlPath(rol_program);
				File file = new File(file_path);
				if(file.exists()) {
					file.delete();
				}
			}
 			String file_path = XmlPathUtil.getXmlPath(program);
			File file = new File(file_path);
			if(file.exists()) {
				file.delete();
			}
		}
		//删除方案环境表
		pgProgramDaoService.deleteProg(prog_id);
		//删除集成方案步骤表
		pgInteStepDaoService.deleteProgStep(prog_id);
		//删除发布方案扩展表表
		pgReleDaoService.deleteProgRele(prog_id);
		//删除发布方案阶段表
		pgReleStepDaoService.deleteProgReleStep(prog_id);
		logger.info("-----------DeleteEnvProgAction end----------");
		return output;
	}

	/** 
	 * Description: 
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(DeleteEnvProgInputBean input) {
		List<String> log_lst = new ArrayList<String>();
		log_lst.add("方案名称: "+input.getProg_id());
		return lgsvc.getLogTxt("删除环境方案", log_lst);
	}

}
