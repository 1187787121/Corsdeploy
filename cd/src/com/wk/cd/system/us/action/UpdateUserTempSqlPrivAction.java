/**
 * Title: UpdateUserTempSqlPrivAction.java
 * File Description: 修改用户临时Sql权限
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
import com.wk.cd.system.us.bean.UpdateUserTempSqlPrivInputBean;
import com.wk.cd.system.us.bean.UpdateUserTempSqlPrivOutputBean;
import com.wk.cd.system.us.dao.UsUserSqlPrivDaoService;
import com.wk.cd.system.us.info.UsUserSqlPrivInfo;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 修改用户临时Sql权限
 * @author HT
 */
public class UpdateUserTempSqlPrivAction extends ActionBasic<UpdateUserTempSqlPrivInputBean, UpdateUserTempSqlPrivOutputBean>{

	@Inject
	private UsUserSqlPrivDaoService usUserSqlPrivDaoService;
	@Inject
	private DtCheckSocExistService daoCheckService;
	@Inject
	private ActionLogPublicService lgsvc;
	
	private static final Log logger = LogFactory.getLog();
	
	/** 
	 * Description: 修改用户临时Sql权限
	 * @param input
	 * @return 
	 */
	@Override
	protected UpdateUserTempSqlPrivOutputBean doAction(UpdateUserTempSqlPrivInputBean input) {
		logger.info("------UpdateUserTempSqlPrivAction begin------");
		logger.debug("------user_id=[{}]------",input.getUser_id());
		
		UpdateUserTempSqlPrivOutputBean output = new UpdateUserTempSqlPrivOutputBean();
		
		String user_id = input.getUser_id();
		List<UsUserSqlPrivInfo> sql_list = input.getSql_list();
		
		Assert.assertNotEmpty(user_id, UpdateUserTempSqlPrivInputBean.USER_IDCN);
		
		// 修改
		if (!Assert.isEmpty(sql_list) && sql_list.size() != 0) {
			for (UsUserSqlPrivInfo sqlPrivInfo : sql_list) {
				daoCheckService.checkSocExist(sqlPrivInfo.getSoc_name());
				sqlPrivInfo.setUser_id(user_id);
				sqlPrivInfo.setAf_flag(AF_FLAG.ALLOW);
				sqlPrivInfo.setPriv_type(PRIV_TYPE.TEMPORARY);
				if(usUserSqlPrivDaoService.countByUserSql(user_id,sqlPrivInfo.getSoc_name(),sqlPrivInfo.getTbl_name())>0){
					usUserSqlPrivDaoService.deleteTempPriv(user_id,sqlPrivInfo.getSoc_name(),sqlPrivInfo.getTbl_name());
				}
				usUserSqlPrivDaoService.insertInfo(sqlPrivInfo);
			}
		}
		
		logger.info("------UpdateUserTempSqlPrivAction end------");
		return output;
	}

	/** 
	 * Description: 写日志信息
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(UpdateUserTempSqlPrivInputBean input) {
		List<String> log_lst = new ArrayList<String>();
		log_lst.add(input.getUser_id());
		return lgsvc.getLogTxt("修改用户临时Sql权限", log_lst);
	}

}
