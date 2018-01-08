/**
 * Title: UpdateDprlRsOptPrivAction.java
 * File Description:  修改部门角色资源及操作权限信息
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
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.system.us.bean.UpdateDprlRsOptPrivInputBean;
import com.wk.cd.system.us.bean.UpdateDprlRsOptPrivOutputBean;
import com.wk.cd.system.us.dao.UsRoleOptPrivDaoService;
import com.wk.cd.system.us.dao.UsRoleRsPrivDaoService;
import com.wk.cd.system.us.info.UsRoleOptPrivInfo;
import com.wk.cd.system.us.info.UsRoleRsPrivInfo;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 修改部门角色资源及操作权限信息
 * @author HT
 */
public class UpdateDprlRsOptPrivAction extends ActionBasic<UpdateDprlRsOptPrivInputBean, UpdateDprlRsOptPrivOutputBean>{

	@Inject
	private UsRoleRsPrivDaoService usRoleRsPrivDaoService;
	@Inject
	private UsRoleOptPrivDaoService usRoleOptPrivDaoService;
	@Inject
	private ActionLogPublicService lgsvc;
	
	private static final Log logger = LogFactory.getLog();
	
	/** 
	 * Description: 修改部门角色资源及操作权限信息
	 * @param input
	 * @return 
	 */
	@Override
	protected UpdateDprlRsOptPrivOutputBean doAction(UpdateDprlRsOptPrivInputBean input) {
		logger.info("------UpdateDprlRsOptPrivAction begin------");
		logger.debug("------dprl_code=[{}]------",input.getDprl_code());
		
		UpdateDprlRsOptPrivOutputBean output = new UpdateDprlRsOptPrivOutputBean();
		
		String dprl_code = input.getDprl_code();
		String[] rs_ary = input.getRs_ary();
		List<UsRoleOptPrivInfo> opt_priv=input.getOpt_priv();
		
		Assert.assertNotEmpty(dprl_code, UpdateDprlRsOptPrivInputBean.DPRL_CODECN);
		
		usRoleRsPrivDaoService.deleteAllRsByDprl(dprl_code);
		// 修改
		if (!Assert.isEmpty(rs_ary) && rs_ary.length != 0) {
			for (String rs_code : rs_ary) {
				UsRoleRsPrivInfo rsPrivInfo = new UsRoleRsPrivInfo();
				rsPrivInfo.setDprl_code(dprl_code);
				rsPrivInfo.setRs_code(rs_code);
				usRoleRsPrivDaoService.insertInfo(rsPrivInfo);
			}
		}
		
		usRoleOptPrivDaoService.deleteOptPrivByDprl(dprl_code);
		// 修改
		if (!Assert.isEmpty(opt_priv) && opt_priv.size() != 0) {
			for (UsRoleOptPrivInfo opt_info : opt_priv) {
				opt_info.setDprl_code(dprl_code);
			}
			usRoleOptPrivDaoService.insertListInfo(opt_priv);
		}
		
		logger.info("------UpdateDprlRsOptPrivAction end------");
		return output;
	}

	/** 
	 * Description: 写日志信息
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(UpdateDprlRsOptPrivInputBean input) {
		List<String> log_lst = new ArrayList<String>();
		log_lst.add(input.getDprl_code());
		return lgsvc.getLogTxt("修改部门角色资源及操作权限信息", log_lst);
	}
}
