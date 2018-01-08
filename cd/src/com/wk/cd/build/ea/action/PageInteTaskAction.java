/**
 * Title: PageInteTaskAction.java
 * File Description: 分页查询集成任务列表
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2016年11月17日
 */
package com.wk.cd.build.ea.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.build.ea.bean.InteTaskBean;
import com.wk.cd.build.ea.bean.PageInteTaskInputBean;
import com.wk.cd.build.ea.bean.PageInteTaskOutputBean;
import com.wk.cd.build.ea.dao.EnvTaskDaoService;
import com.wk.cd.build.ea.dao.UuFilelistDaoService;
import com.wk.cd.build.ea.info.EnvTaskInfo;
import com.wk.cd.enu.TASK_TYPE;
import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.FileTool;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 分页查询集成任务列表
 * @author Xul
 */
public class PageInteTaskAction extends ActionBasic<PageInteTaskInputBean, PageInteTaskOutputBean>{
	
	@Inject private EnvTaskDaoService taskSrv;
	@Inject private UuFilelistDaoService uuFileSrv;
	@Inject private ActionLogPublicService lgsvc;
	private static final Log logger = LogFactory.getLog();
	
	/** 
	 * Description: 分页查询集成任务列表
	 * @param input
	 * @return 
	 */
	@Override
	protected PageInteTaskOutputBean doAction(PageInteTaskInputBean input) {
		logger.info("-----------------PageInteTaskAction Begin------------------");
		PageInteTaskOutputBean output = new PageInteTaskOutputBean();
		
		//非空校验
		String env_name = input.getEnv_name();
		Assert.assertNotEmpty(env_name, PageInteTaskInputBean.ENV_NAMECN);
		
		List<InteTaskBean> inte_list = new ArrayList<InteTaskBean>();
		//根据环境查询任务列表
		List<EnvTaskInfo> task_list = taskSrv.pageInfosByEnv(env_name, TASK_TYPE.INTEGRATION, input.getStart_recd(), input.getLimit_recd());
		for(EnvTaskInfo info : task_list){
			InteTaskBean bean = new InteTaskBean();
			bean.setWork_id(info.getWork_id());
			bean.setCode_ver_num(info.getCode_ver_num());
			String tappac_list_uuid = info.getTagpac_list_uuid();
			bean.setTagpac_list_uuid(tappac_list_uuid);
			bean.setTarget_ver_num(info.getTarget_ver_num());
			bean.setVercode_ver_num(info.getVercode_ver_num());
			bean.setVertarget_ver_num(info.getVertarget_ver_num());
			//获取目标包名列表
			if(!Assert.isEmpty(tappac_list_uuid)){
				List<String> pack_list = uuFileSrv.queryPacList(tappac_list_uuid);
				String[] package_list = pack_list.toArray(new String[pack_list.size()]);
				bean.setPackage_list(package_list);
			}
			String exelog_full_path = info.getExelog_bk_path();
			if(!Assert.isEmpty(exelog_full_path))
				bean.setExelog_name(FileTool.getFileName(exelog_full_path));
			bean.setTask_status(info.getTask_status());
			bean.setExe_result(info.getExe_result());
			bean.setCrt_bk_date(info.getCrt_bk_date());
			bean.setCrt_bk_time(info.getCrt_bk_time());
			inte_list.add(bean);
		}
		
		output.setAll_recd(taskSrv.countTaskByEnv(env_name, TASK_TYPE.INTEGRATION));
		output.setInte_list(inte_list);
		logger.info("-----------------PageInteTaskAction End------------------");
		return output;
	}

	/** 
	 * Description: 分页查询集成任务列表
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(PageInteTaskInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add("环境名称：" + input.getEnv_name());
		return lgsvc.getLogTxt("分页查询集成任务列表", lst_val);
	}

}
