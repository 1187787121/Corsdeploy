/**
 * Title: UpdateDprlSocPrivAction.java
 * File Description: 修改部门角色数据源权限信息
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
import com.wk.cd.system.us.bean.UpdateDprlSocPrivInputBean;
import com.wk.cd.system.us.bean.UpdateDprlSocPrivOutputBean;
import com.wk.cd.system.us.dao.UsRoleSocPrivDaoService;
import com.wk.cd.system.us.info.UsRoleSocPrivInfo;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 修改部门角色数据源权限信息
 * @author HT
 */
public class UpdateDprlSocPrivAction extends ActionBasic<UpdateDprlSocPrivInputBean, UpdateDprlSocPrivOutputBean>{

	@Inject
	private UsRoleSocPrivDaoService usRoleSocPrivDaoService;
	@Inject
	private ActionLogPublicService lgsvc;
	
	private static final Log logger = LogFactory.getLog();
	
	/** 
	 * Description: 修改部门角色数据源权限信息
	 * @param input
	 * @return 
	 */
	@Override
	protected UpdateDprlSocPrivOutputBean doAction(UpdateDprlSocPrivInputBean input) {
		logger.info("------UpdateDprlSocPrivAction begin------");
		logger.debug("------dprl_code=[{}]------",input.getDprl_code());
		
		UpdateDprlSocPrivOutputBean output = new UpdateDprlSocPrivOutputBean();
		
		String dprl_code = input.getDprl_code();
		String[] soc_ary = input.getSoc_ary();
		
		Assert.assertNotEmpty(dprl_code, UpdateDprlSocPrivInputBean.DPRL_CODECN);
		
		usRoleSocPrivDaoService.deleteAllSocByDprl(dprl_code);
		// 修改
		if (!Assert.isEmpty(soc_ary) && soc_ary.length != 0) {
			for (String soc_name : soc_ary) {
				UsRoleSocPrivInfo socPrivInfo = new UsRoleSocPrivInfo();
				socPrivInfo.setDprl_code(dprl_code);
				socPrivInfo.setSoc_name(soc_name);
				usRoleSocPrivDaoService.insertInfo(socPrivInfo);
			}
		}
		
		logger.info("------UpdateDprlSocPrivAction end------");
		return output;
	}

	/** 
	 * Description: 写日志信息
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(UpdateDprlSocPrivInputBean input) {
		List<String> log_lst = new ArrayList<String>();
		log_lst.add(input.getDprl_code());
		return lgsvc.getLogTxt("修改部门角色数据源权限信息", log_lst);
	}

}
