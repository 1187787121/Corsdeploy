/**
 * Title: UpdateDprlColPrivAction.java
 * File Description: 修改部门角色Col权限信息
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
import com.wk.cd.system.us.bean.UpdateDprlColPrivInputBean;
import com.wk.cd.system.us.bean.UpdateDprlColPrivOutputBean;
import com.wk.cd.system.us.dao.UsRoleColPrivDaoService;
import com.wk.cd.system.us.info.UsRoleColPrivInfo;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 修改部门角色Col权限信息
 * @author HT
 */
public class UpdateDprlColPrivAction extends ActionBasic<UpdateDprlColPrivInputBean, UpdateDprlColPrivOutputBean>{

	@Inject
	private UsRoleColPrivDaoService usRoleColPrivDaoService;
	@Inject
	private DtSocService dtSocService;
	@Inject
	private ActionLogPublicService lgsvc;
	
	private static final Log logger = LogFactory.getLog();
	
	/** 
	 * Description: 
	 * @param input
	 * @return 
	 */
	@Override
	protected UpdateDprlColPrivOutputBean doAction(UpdateDprlColPrivInputBean input) {
		logger.info("------UpdateDprlColPrivAction begin------");
		logger.debug("------dprl_code=[{}]------",input.getDprl_code());
		
		UpdateDprlColPrivOutputBean output = new UpdateDprlColPrivOutputBean();
		
		String dprl_code = input.getDprl_code();
		List<UsRoleColPrivInfo> col_list = input.getCol_list();
		
		Assert.assertNotEmpty(dprl_code, UpdateDprlColPrivInputBean.DPRL_CODECN);
		
		usRoleColPrivDaoService.deleteAllColByDprl(dprl_code);
		// 修改
		if (!Assert.isEmpty(col_list) && col_list.size() != 0) {
			for (UsRoleColPrivInfo colPrivInfo : col_list) {
				dtSocService.querySocDetailBySocName(colPrivInfo.getSoc_name());
				colPrivInfo.setDprl_code(dprl_code);
			}
			usRoleColPrivDaoService.insertListInfo(col_list);
		}
		
		logger.info("------UpdateDprlColPrivAction end------");
		return output;
	}

	/** 
	 * Description: 写日志信息
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(UpdateDprlColPrivInputBean input) {
		List<String> log_lst = new ArrayList<String>();
		log_lst.add(input.getDprl_code());
		return lgsvc.getLogTxt("修改部门角色Col权限信息", log_lst);
	}

}
