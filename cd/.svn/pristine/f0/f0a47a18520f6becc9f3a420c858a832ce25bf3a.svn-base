/**
 * Title: UpdateUserTempColPrivAction.java
 * File Description: 修改用户临时Col权限
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
import com.wk.cd.system.dt.service.DtCheckSocExistService;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.system.us.bean.UpdateUserTempColPrivInputBean;
import com.wk.cd.system.us.bean.UpdateUserTempColPrivOutputBean;
import com.wk.cd.system.us.dao.UsUserColPrivDaoService;
import com.wk.cd.system.us.info.UsUserColPrivInfo;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 修改用户临时Col权限
 * @author HT
 */
public class UpdateUserTempColPrivAction extends ActionBasic<UpdateUserTempColPrivInputBean, UpdateUserTempColPrivOutputBean>{

	@Inject
	private UsUserColPrivDaoService usUserColPrivDaoService;
	@Inject
	private DtCheckSocExistService daoCheckService;
	@Inject
	private ActionLogPublicService lgsvc;
	
	private static final Log logger = LogFactory.getLog();
	
	/** 
	 * Description: 修改用户临时Col权限
	 * @param input
	 * @return 
	 */
	@Override
	protected UpdateUserTempColPrivOutputBean doAction(UpdateUserTempColPrivInputBean input) {
		logger.info("------UpdateUserTempColPrivAction begin------");
		logger.debug("------user_id=[{}]------",input.getUser_id());
		
		UpdateUserTempColPrivOutputBean output = new UpdateUserTempColPrivOutputBean();
		
		String user_id = input.getUser_id();
		List<UsUserColPrivInfo> col_list = input.getCol_list();
		
		Assert.assertNotEmpty(user_id, UpdateUserTempColPrivInputBean.USER_IDCN);
		
		// 修改
		if (!Assert.isEmpty(col_list) && col_list.size() != 0) {
			for (UsUserColPrivInfo colPrivInfo : col_list) {
				daoCheckService.checkSocExist(colPrivInfo.getSoc_name());
				colPrivInfo.setUser_id(user_id);
				colPrivInfo.setAf_flag(AF_FLAG.ALLOW);
				colPrivInfo.setPriv_type(PRIV_TYPE.TEMPORARY);
				if(usUserColPrivDaoService.countByUserCol(user_id,colPrivInfo.getSoc_name(),colPrivInfo.getTbl_name(),colPrivInfo.getCol_name())>0){
					usUserColPrivDaoService.deleteTempPriv(user_id,colPrivInfo.getSoc_name(),colPrivInfo.getTbl_name(),colPrivInfo.getCol_name());
				}
				usUserColPrivDaoService.insertInfo(colPrivInfo);
			}
		}
		
		logger.info("------UpdateUserTempColPrivAction end------");
		return output;
	}

	/** 
	 * Description: 写日志信息 
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(UpdateUserTempColPrivInputBean input) {
		List<String> log_lst = new ArrayList<String>();
		log_lst.add(input.getUser_id());
		return lgsvc.getLogTxt("修改用户临时Col权限", log_lst);
	}

}
