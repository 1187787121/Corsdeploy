/**
 * Title: AddDeptAction.java
 * File Description: 新增部门信息
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015年8月25日
 */
package com.wk.cd.system.dp.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.cm.service.GenNoService;
import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.DEPT_TYPE;
import com.wk.cd.enu.RCD_STATE;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.dp.bean.AddDeptInputBean;
import com.wk.cd.system.dp.bean.AddDeptOutputBean;
import com.wk.cd.system.dp.dao.DpDeptDaoService;
import com.wk.cd.system.dp.info.DpDeptInfo;
import com.wk.cd.system.dp.service.DpPublicService;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description:新增部门信息
 * @author HT
 */
public class AddDeptAction extends ActionBasic<AddDeptInputBean, AddDeptOutputBean>{
	
	@Inject
	private DpDeptDaoService dpDeptDaoService;
	@Inject
	private DpPublicService dpPublicService;
	@Inject
	private GenNoService genNoService;
	@Inject
	private ActionLogPublicService lgsvc;
	
	private static final Log logger = LogFactory.getLog();
	
	/** 
	 * Description: 新增部门信息
	 * @param input
	 * @return 
	 */
	@Override
	protected AddDeptOutputBean doAction(AddDeptInputBean input) {
		logger.info("------AddDeptAction begin------");
		logger.debug("------dept_cn_name=[{}]",input.getDept_cn_name());
		
		AddDeptOutputBean output = new AddDeptOutputBean();
		// 验证字段非空
		Assert.assertNotEmpty(input.getDept_cn_name(),
				AddDeptInputBean.DEPT_CN_NAMECN);
		Assert.assertNotEmpty(input.getDept_full_cname(),
				AddDeptInputBean.DEPT_FULL_CNAMECN);
		Assert.assertNotEmpty(input.getDept_type(),
				AddDeptInputBean.DEPT_TYPECN);
		Assert.assertNotEmpty(input.getDept_level(),
				AddDeptInputBean.DEPT_LEVELCN);
		if (input.getDept_type() == DEPT_TYPE.BRANCH) {
			Assert.assertNotEmpty(input.getBranch_no(),
					AddDeptInputBean.BRANCH_NOCN);
		}

		// 检查部门名称是否重复
		dpPublicService.checkDeptNameRepeat("", input.getDept_cn_name(),input.getDept_full_cname());
		// 获得自动生成部门ID
		String dept_id = genNoService.getDeptId(input.getDept_type(),
				input.getDept_level(), input.getDtbs_bk_date());
		// 增加部门信息装入deptInfo
		DpDeptInfo deptInfo = new DpDeptInfo();
		deptInfo.setDept_id(dept_id);
		deptInfo.setDept_full_cname(input.getDept_full_cname());
		deptInfo.setDept_cn_name(input.getDept_cn_name());
		deptInfo.setDept_type(input.getDept_type());
		deptInfo.setDept_level(input.getDept_level());
		deptInfo.setSuper_dept_id(input.getSuper_dept_id());
		deptInfo.setBranch_no(input.getBranch_no());
		deptInfo.setRcd_state(RCD_STATE.NORMAL);
		deptInfo.setCrt_user_id(input.getOrg_user_id());
		deptInfo.setCrt_bk_date(input.getDtbs_bk_date());
		deptInfo.setCrt_bk_time(input.getDtbs_bk_time());

		// 执行增加部门表
		dpDeptDaoService.insertInfo(deptInfo);
		logger.info("------AddDeptAction end------");
		return output;
	}

	/** 
	 * Description:写日志信息
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(AddDeptInputBean input) {
		List<String> log_lst = new ArrayList<String>();
		log_lst.add(input.getDept_cn_name());
		log_lst.add(input.getDept_full_cname());
		log_lst.add(input.getDept_type().getCname());
		log_lst.add(String.valueOf(input.getDept_level()));
		log_lst.add(input.getSuper_dept_id());
		log_lst.add(input.getBranch_no());
		return lgsvc.getLogTxt("新增部门信息", log_lst);
	}

}
