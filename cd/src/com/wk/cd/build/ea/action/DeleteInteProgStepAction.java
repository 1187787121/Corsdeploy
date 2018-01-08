/**
 * Title: DeleteInteProgStepAction.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: chiss
 * @version: 1.0
 * @date: 2016年11月11日
 */
package com.wk.cd.build.ea.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.build.ea.bean.DeleteInteProgStepInputBean;
import com.wk.cd.build.ea.bean.DeleteInteProgStepOutputBean;
import com.wk.cd.build.ea.dao.EnvTaskDaoService;
import com.wk.cd.build.ea.dao.PgInteStepDaoService;
import com.wk.cd.build.ea.dao.UuFilelistDaoService;
import com.wk.cd.build.ea.dao.UuSocDaoService;
import com.wk.cd.build.ea.info.PgInteStepInfo;
import com.wk.cd.build.ea.service.EnvProgPublicService;
import com.wk.cd.common.util.Assert;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 
 * @author chiss
 */
public class DeleteInteProgStepAction extends ActionBasic<DeleteInteProgStepInputBean, DeleteInteProgStepOutputBean>{
	@Inject
	private PgInteStepDaoService pgInteStepDaoService;
    @Inject 
    private UuFilelistDaoService uuFileListDaoService;
	@Inject
	private EnvTaskDaoService envTaskDaoService;
	@Inject
	private EnvProgPublicService envProgPublicService;
	@Inject
	private UuSocDaoService uuSocDaoService;
	@Inject
	private ActionLogPublicService lgsvc;
	private static final Log logger = LogFactory.getLog();
	/** 
	 * Description: 
	 * @param input
	 * @return 
	 */
	@Override
	protected DeleteInteProgStepOutputBean doAction(DeleteInteProgStepInputBean input) {
		logger.info("-----------DeleteInteProgStepAction begin----------");
		DeleteInteProgStepOutputBean output = new DeleteInteProgStepOutputBean();
		String prog_id = input.getProg_id();
		int step_id = input.getStep_id();
		Assert.assertNotEmpty(prog_id, DeleteInteProgStepInputBean.PROG_IDCN);
		Assert.assertNotEmpty(step_id, DeleteInteProgStepInputBean.STEP_IDCN);
		//校验prog_id是否存在
		envProgPublicService.checkProgIdIsNotExist(prog_id);
		//校验主键id是否存在
//		envProgPublicService.checkKeyIdIsNotExist(prog_id,step_id);
		PgInteStepInfo stepInfo = new PgInteStepInfo();
		stepInfo.setProg_id(prog_id);
		stepInfo.setStep_id(step_id);
		PgInteStepInfo stepInfos = pgInteStepDaoService.getInfoByKey(stepInfo);
		int step = pgInteStepDaoService.countPgStepByKey(prog_id);
		if(!Assert.isEmpty(stepInfos)){
			//删除数据源关联表
			String uu_id = stepInfos.getSoc_uuid();
		    if(!Assert.isEmpty(uu_id)){
		    	uuSocDaoService.deleteListByUU(uu_id);
		    }
			//删除文件清单表
			String list_uuid = stepInfos.getStorage_list_uuid();
			if(!Assert.isEmpty(list_uuid)){
				uuFileListDaoService.deleteUuFileByListId(list_uuid);
			}
			//删除集成方案步骤表
			pgInteStepDaoService.deleteInfo(stepInfo);
		}
		// 重新排序
        if(step_id<step){    
        	for (int i = step_id; i <step; i++) {
        		PgInteStepInfo update_pg = pgInteStepDaoService.getInfoByKey2(prog_id,i+1);
        		PgInteStepInfo stepinfo = new PgInteStepInfo();
        		stepinfo.setProg_id(prog_id);
        		stepinfo.setStep_id(i);
        		stepinfo.setStep_expl(update_pg.getStep_expl());
        		stepinfo.setStep_type(update_pg.getStep_type());
        		stepinfo.setSoc_uuid(update_pg.getSoc_uuid());
        		stepinfo.setStep_bk_script(update_pg.getStep_bk_script());
        		stepinfo.setCompile_type(update_pg.getCompile_type());
        		stepinfo.setEnv_variable(update_pg.getEnv_variable());
        		stepinfo.setCompile_param(update_pg.getCompile_param());
        		stepinfo.setStorage_list_uuid(update_pg.getStorage_list_uuid());
        		pgInteStepDaoService.insertInfo(stepinfo);
        		pgInteStepDaoService.deleteInfo(update_pg);
			}
		}
		logger.info("-----------DeleteInteProgStepAction begin----------");
		return output;
	}

	/** 
	 * Description: 
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(DeleteInteProgStepInputBean input) {
		List<String> log_lst = new ArrayList<String>();
		log_lst.add("方案编号: " + input.getProg_id());
		log_lst.add("步骤编号: " + input.getStep_id());
		return lgsvc.getLogTxt("删除单个集成方案步骤", log_lst);
	}

}
