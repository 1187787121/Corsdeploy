/**
 * Title: AddDprlAction.java
 * File Description: 新增部门角色基础信息
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015年9月6日
 */
package com.wk.cd.system.us.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.cm.service.GenNoService;
import com.wk.cd.common.util.Assert;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.system.us.bean.AddDprlInputBean;
import com.wk.cd.system.us.bean.AddDprlOutputBean;
import com.wk.cd.system.us.dao.UsDeptRoleDaoService;
import com.wk.cd.system.us.info.UsDeptRoleInfo;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 新增部门角色基础信息
 * @author HT
 */
public class AddDprlAction extends ActionBasic<AddDprlInputBean, AddDprlOutputBean>{
	
	@Inject
	private UsDeptRoleDaoService usDeptRoleDaoService;
	@Inject
	private GenNoService nosvc;
	@Inject
	private ActionLogPublicService lgsvc;
	
	private static final Log logger = LogFactory.getLog();
	
	/** 
	 * Description: 新增部门角色基础信息
	 * @param input
	 * @return 
	 */
	@Override
	protected AddDprlOutputBean doAction(AddDprlInputBean input) {
		logger.info("------AddDprlAction begin------");
		logger.debug("------dept_id=[{}]--role_code=[{}]",input.getDept_id(),input.getRole_code());
		
		AddDprlOutputBean output = new AddDprlOutputBean();
		String dept_id=input.getDept_id();
		String role_code=input.getRole_code();
		String bk_expl=input.getBk_expl();
		
		Assert.assertNotEmpty(dept_id, AddDprlInputBean.DEPT_IDCN);
		Assert.assertNotEmpty(role_code,AddDprlInputBean.ROLE_CODECN);
		
		// 根据部门编码、角色编码、当前日期获取部门角色编码
		String dprl_code = nosvc.getDeptRoleCode(input.getDept_id(),input.getRole_code(), input.getDtbs_bk_date());
		logger.debug("------dprl_code=[{}]------", dprl_code);
		usDeptRoleDaoService.checkExistDprlCode(dprl_code);
		
		UsDeptRoleInfo info = new UsDeptRoleInfo();
		info.setDprl_code(dprl_code);
		info.setDept_id(dept_id);
		info.setRole_code(role_code);
		info.setBk_expl(bk_expl);
		usDeptRoleDaoService.insertInfo(info);
		
		logger.info("------AddDprlAction end------");
		return output;
	}

	/** 
	 * Description: 写日志信息
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(AddDprlInputBean input) {
		List<String> log_lst = new ArrayList<String>();
		log_lst.add(input.getDept_id());
		log_lst.add(input.getRole_code());
		log_lst.add(input.getBk_expl());
		return lgsvc.getLogTxt("新增部门角色信息", log_lst);
	}
}
