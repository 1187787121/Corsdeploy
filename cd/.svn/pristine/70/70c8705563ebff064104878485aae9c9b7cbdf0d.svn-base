/**
 * Title: UpdateDeptAction.java
 * File Description: 修改部门信息
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015年8月25日
 */
package com.wk.cd.system.dp.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.DEPT_TYPE;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.dp.bean.AddDeptInputBean;
import com.wk.cd.system.dp.bean.UpdateDeptInputBean;
import com.wk.cd.system.dp.bean.UpdateDeptOutputBean;
import com.wk.cd.system.dp.dao.DpDeptDaoService;
import com.wk.cd.system.dp.service.DpPublicService;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;
import com.wk.util.JaDate;
import com.wk.util.JaTime;

/**
 * Class Description: 修改部门信息
 * @author HT
 */
public class UpdateDeptAction extends ActionBasic<UpdateDeptInputBean, UpdateDeptOutputBean>{

	@Inject
	private DpDeptDaoService deptDaoService;
	@Inject
	private DpPublicService dpPublicService;
	@Inject
	private ActionLogPublicService lgsvc;
	
	private static final Log logger = LogFactory.getLog();
	
	/** 
	 * Description: 修改部门信息
	 * @param input
	 * @return 
	 */
	@Override
	protected UpdateDeptOutputBean doAction(UpdateDeptInputBean input) {
		logger.info("------UpdateDeptAction begin------");
		logger.debug("------dept_id=[{}]",input.getDept_id());
		UpdateDeptOutputBean output = new UpdateDeptOutputBean();
		// 从输入接口接收更新数据
		String dept_id = input.getDept_id();
		JaDate modify_bk_date = input.getDtbs_bk_date();
		JaTime modify_bk_time = input.getDtbs_bk_time();
		String modify_user_id = input.getOrg_user_id();
		String branch_no = input.getBranch_no();
		// 验证部门名称非空
		String dept_cn_name = input.getDept_cn_name();
		String dept_full_cname = input.getDept_full_cname();
		Assert.assertNotEmpty(dept_cn_name, AddDeptInputBean.DEPT_CN_NAMECN);
		Assert.assertNotEmpty(dept_full_cname,AddDeptInputBean.DEPT_FULL_CNAMECN);
		if (input.getDept_type() == DEPT_TYPE.BRANCH) {
			Assert.assertNotEmpty(input.getBranch_no(),AddDeptInputBean.BRANCH_NOCN);
		}
		dpPublicService.checkDeptNameRepeat(input.getDept_id(),
				input.getDept_cn_name(), input.getDept_full_cname());
		// 执行更新
		deptDaoService.updateDeptNameAndBranchNo(dept_cn_name, dept_full_cname,
				branch_no, modify_bk_date, modify_bk_time, modify_user_id,
				dept_id);

		logger.info("------UpdateDeptAction end------");
		return output;
	}

	/** 
	 * Description: 写日志信息
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(UpdateDeptInputBean input) {
		List<String> log_lst = new ArrayList<String>();
		log_lst.add(input.getDept_id());
		log_lst.add(input.getDept_cn_name());
		log_lst.add(input.getDept_full_cname());
		log_lst.add(input.getBranch_no());
		return lgsvc.getLogTxt("修改部门信息", log_lst);
	}
}
