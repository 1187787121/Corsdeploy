/**
 * Title: UpdateUserRsOptPrivAction.java
 * File Description: 修改用户资源及操作权限
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015年9月9日
 */
package com.wk.cd.system.us.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.system.us.bean.UpdateUserRsOptPrivInputBean;
import com.wk.cd.system.us.bean.UpdateUserRsOptPrivOutputBean;
import com.wk.cd.system.us.dao.UsUserOptPrivDaoService;
import com.wk.cd.system.us.dao.UsUserRsPrivDaoService;
import com.wk.cd.system.us.info.UsUserOptPrivInfo;
import com.wk.cd.system.us.info.UsUserRsPrivInfo;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 修改用户资源及操作权限
 * @author HT
 */
public class UpdateUserRsOptPrivAction extends ActionBasic<UpdateUserRsOptPrivInputBean, UpdateUserRsOptPrivOutputBean>{

	
	@Inject
	private UsUserRsPrivDaoService usUserRsPrivDaoService;
	@Inject
	private UsUserOptPrivDaoService usUserOptPrivDaoService;
	@Inject
	private ActionLogPublicService lgsvc;
	
	private static final Log logger = LogFactory.getLog();
	
	/** 
	 * Description: 修改用户资源及操作权限
	 * @param input
	 * @return 
	 */
	@Override
	protected UpdateUserRsOptPrivOutputBean doAction(UpdateUserRsOptPrivInputBean input) {
		logger.info("------UpdateUserRsOptPrivAction begin------");
		logger.debug("------user_id=[{}]------",input.getUser_id());
		
		UpdateUserRsOptPrivOutputBean output = new UpdateUserRsOptPrivOutputBean();
		
		String user_id = input.getUser_id();
		List<UsUserRsPrivInfo> rs_list=input.getRs_list();
		List<UsUserOptPrivInfo> opt_priv=input.getOpt_priv();
		
		Assert.assertNotEmpty(user_id, UpdateUserRsOptPrivInputBean.USER_IDCN);
		
		usUserRsPrivDaoService.deleteAllTualRsByUserId(user_id);
		// 修改
		if (!Assert.isEmpty(rs_list) && rs_list.size() != 0) {
			for (UsUserRsPrivInfo rsPrivInfo : rs_list) {
				rsPrivInfo.setUser_id(user_id);
				usUserRsPrivDaoService.insertInfo(rsPrivInfo);
			}
		}
		
		usUserOptPrivDaoService.deleteOptPrivByUser(user_id);
		// 修改
		if (!Assert.isEmpty(opt_priv) && opt_priv.size() != 0) {
			for (UsUserOptPrivInfo opt_info : opt_priv) {
				opt_info.setUser_id(user_id);
			}
			usUserOptPrivDaoService.insertListInfo(opt_priv);
		}
		
		logger.info("------UpdateUserRsOptPrivAction end------");
		return output;
	}

	/** 
	 * Description: 写日志信息 
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(UpdateUserRsOptPrivInputBean input) {
		List<String> log_lst = new ArrayList<String>();
		log_lst.add(input.getUser_id());
		return lgsvc.getLogTxt("修改用户资源及操作权限", log_lst);
	}

}
