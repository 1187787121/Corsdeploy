/**
 * Title: PageStorageTaskAction.java
 * File Description: 分页查询入库任务列表
 * @copyright: 2016
 * @company: CORSWORK
 * @author: chiss
 * @version: 1.0
 * @date: 2016年11月17日
 */
package com.wk.cd.build.ea.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.build.ea.bean.EnvTagStorageBean;
import com.wk.cd.build.ea.bean.PageInteTaskInputBean;
import com.wk.cd.build.ea.bean.PageStorageTaskInputBean;
import com.wk.cd.build.ea.bean.PageStorageTaskOutputBean;
import com.wk.cd.build.ea.dao.EnvTagStorageDaoService;
import com.wk.cd.build.ea.dao.UuFilelistDaoService;
import com.wk.cd.build.ea.info.EnvTagStorageInfo;
import com.wk.cd.build.en.bean.PageSystemListInputBean;
import com.wk.cd.build.en.service.EnvironmentPublicService;
import com.wk.cd.common.util.Assert;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 根据环境查询入库任务列表（带分页）
 * @author chiss
 */
public class PageStorageTaskAction extends ActionBasic<PageStorageTaskInputBean, PageStorageTaskOutputBean>{
	@Inject
	private EnvTagStorageDaoService envTagStorageDaoService;
	@Inject
	private UuFilelistDaoService uuFilelistDaoService;
	@Inject 
	private EnvironmentPublicService environmentPublicService;	
	@Inject
	private ActionLogPublicService lgsvc;
	private static final Log logger = LogFactory.getLog();
	/** 
	 * Description: 分页查询入库任务列表
	 * @param input
	 * @return 
	 */
	@Override
	protected PageStorageTaskOutputBean doAction(PageStorageTaskInputBean input) {
		logger.info("-----------PageStorageTaskAction begin----------");
		PageStorageTaskOutputBean output = new PageStorageTaskOutputBean();
		String env_name = input.getEnv_name();
		int start_recd = input.getStart_recd();
		int limit_recd = input.getLimit_recd();
		// 非空校验
		Assert.assertNotEmpty(env_name, PageStorageTaskInputBean.ENV_NAMECN);
		Assert.assertNotEmpty(start_recd, PageStorageTaskInputBean.START_RECDCN);
		Assert.assertNotEmpty(limit_recd, PageStorageTaskInputBean.LIMIT_RECDCN);
		//合法性校验
		environmentPublicService.checkEnvNameIsExist(env_name);
		List<EnvTagStorageBean> stor_list = new ArrayList<EnvTagStorageBean>();
		//根据env_name 查询目标入库列表
		List<EnvTagStorageInfo> storg_info_list = envTagStorageDaoService.pageInfoByEnvName(env_name,start_recd,limit_recd);
		for (EnvTagStorageInfo storg_info : storg_info_list) {
			EnvTagStorageBean storg_bean = new EnvTagStorageBean();
			storg_bean.setStorage_id(storg_info.getStorage_id());
			storg_bean.setStart_bk_tm(storg_info.getStart_bk_tm());
			storg_bean.setEnd_bk_tm(storg_info.getEnd_bk_tm());
			storg_bean.setStorage_status(storg_info.getStorage_status());
			storg_bean.setStorage_result(storg_info.getStorage_result());
			//获取目标包名列表
			String stor_list_uuid = storg_info.getStorage_list_uuid();
			if(!Assert.isEmpty(stor_list_uuid)) {
				List<String> package_list = uuFilelistDaoService.queryPacList(stor_list_uuid);
				storg_bean.setPackage_list(package_list.toArray(new String[package_list.size()]));
			}
			storg_bean.setLog_name(storg_info.getStorage_id()+".txt");
			storg_bean.setTar_ver_num(storg_info.getTar_ver_num());
			storg_bean.setStorage_list_uuid(storg_info.getInte_ver_num());
			stor_list.add(storg_bean);
		}
		output.setStor_list(stor_list);
		int all_recd = envTagStorageDaoService.countByEnvName(env_name);
		output.setAll_recd(all_recd);
		logger.info("-----------PageStorageTaskAction end----------");
		return output;
	}

	/** 
	 * Description: 分页查询入库任务列表
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(PageStorageTaskInputBean input) {
		List<String> log_lst = new ArrayList<String>();
		log_lst.add("环境名称: " + input.getEnv_name());
		return lgsvc.getLogTxt("查询入库任务列表", log_lst);
	}

}
