/**
 * Title: UpdateDprlAction.java
 * File Description: 修改部门角色基础信息
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
import com.wk.cd.system.us.bean.UpdateDprlInputBean;
import com.wk.cd.system.us.bean.UpdateDprlOutputBean;
import com.wk.cd.system.us.dao.UsDeptRoleDaoService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 修改部门角色基础信息
 * @author HT
 */
public class UpdateDprlAction extends ActionBasic<UpdateDprlInputBean, UpdateDprlOutputBean>{

	@Inject
	private UsDeptRoleDaoService usDeptRoleDaoService;
	@Inject
	private ActionLogPublicService lgsvc;
	
	private static final Log logger = LogFactory.getLog();
	
	/** 
	 * Description: 修改部门角色基础信息
	 * @param input
	 * @return 
	 */
	@Override
	protected UpdateDprlOutputBean doAction(UpdateDprlInputBean input) {
		logger.info("------UpdateDprlAction begin------");
		logger.debug("------dprl_code=[{}]------",input.getDprl_code());
		
		UpdateDprlOutputBean output = new UpdateDprlOutputBean();
		String dprl_code=input.getDprl_code();
		String bk_expl=input.getBk_expl();
		
		Assert.assertNotEmpty(dprl_code,UpdateDprlInputBean.DPRL_CODECN);
		Assert.assertNotEmpty(bk_expl, UpdateDprlInputBean.BK_EXPLCN);
		
		usDeptRoleDaoService.updateBkExplByDprl(input.getBk_expl(), input.getDprl_code());
		
		logger.info("------UpdateDprlAction end------");
		return output;
	}

	/** 
	 * Description: 写日志信息
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(UpdateDprlInputBean input) {
		List<String> log_lst = new ArrayList<String>();
		log_lst.add(input.getDprl_code());
		log_lst.add(input.getBk_expl());
		return lgsvc.getLogTxt("修改部门角色基础信息", log_lst);
	}
}
