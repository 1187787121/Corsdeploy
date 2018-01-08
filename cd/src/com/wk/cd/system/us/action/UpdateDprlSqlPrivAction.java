/**
 * Title: UpdateDprlSqlPrivAction.java
 * File Description: 修改部门角色Sql权限信息
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015年9月7日
 */
package com.wk.cd.system.us.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.dt.service.DtSocService;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.system.us.bean.UpdateDprlSqlPrivInputBean;
import com.wk.cd.system.us.bean.UpdateDprlSqlPrivOutputBean;
import com.wk.cd.system.us.dao.UsRoleSqlPrivDaoService;
import com.wk.cd.system.us.info.UsRoleSqlPrivInfo;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 修改部门角色Sql权限信息
 * @author HT
 */
public class UpdateDprlSqlPrivAction extends ActionBasic<UpdateDprlSqlPrivInputBean, UpdateDprlSqlPrivOutputBean>{

	@Inject
	private UsRoleSqlPrivDaoService usRoleSqlPrivDaoService;
	@Inject
	private DtSocService dtSocService;
	@Inject
	private ActionLogPublicService lgsvc;
	
	private static final Log logger = LogFactory.getLog();
	
	/** 
	 * Description: 修改部门角色Sql权限信息
	 * @param input
	 * @return 
	 */
	@Override
	protected UpdateDprlSqlPrivOutputBean doAction(UpdateDprlSqlPrivInputBean input) {
		logger.info("------UpdateDprlSqlPrivAction begin------");
		logger.debug("------dprl_code=[{}]------",input.getDprl_code());
		
		UpdateDprlSqlPrivOutputBean output = new UpdateDprlSqlPrivOutputBean();

		String dprl_code = input.getDprl_code();
		List<UsRoleSqlPrivInfo> sql_list = input.getSql_list();
		
		Assert.assertNotEmpty(dprl_code, UpdateDprlSqlPrivInputBean.DPRL_CODECN);
		
		usRoleSqlPrivDaoService.deleteAllSqlByDprl(dprl_code);
		// 修改
		if (!Assert.isEmpty(sql_list) && sql_list.size() != 0) {
			for (UsRoleSqlPrivInfo sqlPrivInfo : sql_list) {
				dtSocService.querySocDetailBySocName(sqlPrivInfo.getSoc_name());
				sqlPrivInfo.setDprl_code(dprl_code);
			}
			usRoleSqlPrivDaoService.insertListInfo(sql_list);
		}
		
		logger.info("------UpdateDprlSqlPrivAction end------");
		return output;
	}

	/** 
	 * Description: 写日志信息
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(UpdateDprlSqlPrivInputBean input) {
		List<String> log_lst = new ArrayList<String>();
		log_lst.add(input.getDprl_code());
		return lgsvc.getLogTxt("修改部门角色Sql权限信息", log_lst);
	}

}
