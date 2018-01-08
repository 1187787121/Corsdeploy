/**
 * Title: UpdateUserSqlPrivAction.java
 * File Description: 修改用户Sql权限
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
import com.wk.cd.enu.AF_FLAG;
import com.wk.cd.enu.PRIV_TYPE;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.dt.service.DtSocService;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.system.us.bean.UpdateUserSqlPrivInputBean;
import com.wk.cd.system.us.bean.UpdateUserSqlPrivOutputBean;
import com.wk.cd.system.us.dao.UsUserSqlPrivDaoService;
import com.wk.cd.system.us.info.UsUserSqlPrivInfo;
import com.wk.cd.system.us.service.UsAddUserPrivService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 修改用户Sql权限
 * @author HT
 */
public class UpdateUserSqlPrivAction extends ActionBasic<UpdateUserSqlPrivInputBean, UpdateUserSqlPrivOutputBean>{

	@Inject
	private UsUserSqlPrivDaoService usUserSqlPrivDaoService;
	@Inject
	private UsAddUserPrivService addUserService;
	@Inject
	private DtSocService dtSocService;
	@Inject
	private ActionLogPublicService lgsvc;
	
	private static final Log logger = LogFactory.getLog();
	
	/** 
	 * Description: 修改用户Sql权限
	 * @param input
	 * @return 
	 */
	@Override
	protected UpdateUserSqlPrivOutputBean doAction(UpdateUserSqlPrivInputBean input) {
		logger.info("------UpdateUserSqlPrivAction begin------");
		logger.debug("------user_id=[{}]------",input.getUser_id());
		
		UpdateUserSqlPrivOutputBean output = new UpdateUserSqlPrivOutputBean();
		
		String user_id = input.getUser_id();
		List<UsUserSqlPrivInfo> sql_list = input.getSql_list();
		
		Assert.assertNotEmpty(user_id, UpdateUserSqlPrivInputBean.USER_IDCN);
		
		usUserSqlPrivDaoService.deleteAllSqlByUserId(user_id);
		// 修改
		if (!Assert.isEmpty(sql_list) && sql_list.size() != 0) {
			for (UsUserSqlPrivInfo sqlPrivInfo : sql_list) {
				dtSocService.querySocDetailBySocName(sqlPrivInfo.getSoc_name());
				sqlPrivInfo.setUser_id(user_id);
				sqlPrivInfo.setAf_flag(AF_FLAG.ALLOW);
				sqlPrivInfo.setPriv_type(PRIV_TYPE.PERPETUAL);
			}
			addUserService.addUserSql(sql_list);
		}
		
		logger.info("------UpdateUserSqlPrivAction end------");
		return output;
	}

	/** 
	 * Description: 写日志信息
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(UpdateUserSqlPrivInputBean input) {
		List<String> log_lst = new ArrayList<String>();
		log_lst.add(input.getUser_id());
		return lgsvc.getLogTxt("修改用户Sql权限", log_lst);
	}

}
