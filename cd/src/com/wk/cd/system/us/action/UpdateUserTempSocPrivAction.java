/**
 * Title: UpdateUserTempSocPrivAction.java
 * File Description: 修改用户临时数据源权限
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015年10月9日
 */
package com.wk.cd.system.us.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.AF_FLAG;
import com.wk.cd.enu.PRIV_TYPE;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.system.us.bean.UpdateUserTempSocPrivInputBean;
import com.wk.cd.system.us.bean.UpdateUserTempSocPrivOutputBean;
import com.wk.cd.system.us.dao.UsUserSocPrivDaoService;
import com.wk.cd.system.us.info.UsUserSocPrivInfo;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 修改用户临时数据源权限
 * @author HT
 */
public class UpdateUserTempSocPrivAction extends ActionBasic<UpdateUserTempSocPrivInputBean, UpdateUserTempSocPrivOutputBean>{

	@Inject
	private UsUserSocPrivDaoService usUserSocPrivDaoService;
	@Inject
	private ActionLogPublicService lgsvc;
	
	private static final Log logger = LogFactory.getLog();
	
	/** 
	 * Description: 修改用户临时数据源权限
	 * @param input
	 * @return 
	 */
	@Override
	protected UpdateUserTempSocPrivOutputBean doAction(UpdateUserTempSocPrivInputBean input) {
		logger.info("------UpdateUserTempSocPrivAction begin------");
		logger.debug("------user_id=[{}]------",input.getUser_id());
		
		UpdateUserTempSocPrivOutputBean output = new UpdateUserTempSocPrivOutputBean();
		
		String user_id = input.getUser_id();
		List<UsUserSocPrivInfo> soc_list = input.getSoc_list();
		
		Assert.assertNotEmpty(user_id, UpdateUserTempSocPrivInputBean.USER_IDCN);
		
		// 修改
		if (!Assert.isEmpty(soc_list) && soc_list.size() != 0) {
			for (UsUserSocPrivInfo socPrivInfo : soc_list) {
				socPrivInfo.setUser_id(user_id);
				socPrivInfo.setPriv_type(PRIV_TYPE.TEMPORARY);
				socPrivInfo.setAf_flag(AF_FLAG.ALLOW);
				if(usUserSocPrivDaoService.existTempPriv(socPrivInfo.getUser_id(),socPrivInfo.getSoc_name())){
					usUserSocPrivDaoService.deleteTempPriv(socPrivInfo.getUser_id(),socPrivInfo.getSoc_name());
				}
				usUserSocPrivDaoService.insertInfo(socPrivInfo);
			}
		}
		
		logger.info("------UpdateUserTempSocPrivAction end------");
		return output;
	}

	/** 
	 * Description: 写日志信息 
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(UpdateUserTempSocPrivInputBean input) {
		List<String> log_lst = new ArrayList<String>();
		log_lst.add(input.getUser_id());
		return lgsvc.getLogTxt("修改用户临时数据源权限", log_lst);
	}

}
