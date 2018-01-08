/**
 * Title: QueryDeptInfoAction.java
 * File Description: 查询部门详细信息
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015年8月25日
 */
package com.wk.cd.system.dp.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.dp.bean.QueryDeptInfoInputBean;
import com.wk.cd.system.dp.bean.QueryDeptInfoOutputBean;
import com.wk.cd.system.dp.dao.DpDeptDaoService;
import com.wk.cd.system.dp.info.DpDeptInfo;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 查询部门详细信息
 * @author HT
 */
public class QueryDeptInfoAction extends ActionBasic<QueryDeptInfoInputBean, QueryDeptInfoOutputBean>{

	@Inject 
	private DpDeptDaoService deptDaoService;
	@Inject
	private ActionLogPublicService lgsvc;
	
	private static final Log logger = LogFactory.getLog();
	
	/** 
	 * Description: 查询部门详细信息
	 * @param input
	 * @return 
	 */
	@Override
	protected QueryDeptInfoOutputBean doAction(QueryDeptInfoInputBean input) {
		logger.info("------QueryDeptInfoAction begin------");
		logger.debug("------dept_id=[{}]",input.getDept_id());
		QueryDeptInfoOutputBean output = new QueryDeptInfoOutputBean();
		// 查询出本部门信息
		DpDeptInfo info = new DpDeptInfo();
		info.setDept_id(input.getDept_id());
		info = deptDaoService.getInfoByKey(info);
		// 将本部门数据赋值给输出接口
		output.setDept_id(input.getDept_id());
		output.setDept_cn_name(info.getDept_cn_name());
		output.setDept_full_cname(info.getDept_full_cname());
		output.setDept_type(info.getDept_type());
		output.setDept_level(info.getDept_level());
		output.setSuper_dept_id(info.getSuper_dept_id());
		output.setBranch_no(info.getBranch_no());

		if (info.getDept_level() > 1) {
			DpDeptInfo super_info = new DpDeptInfo();
			super_info.setDept_id(info.getSuper_dept_id());
			super_info = deptDaoService.getInfoByKey(super_info);
			output.setSuper_dept_cn_name(super_info.getDept_cn_name());
		}
		
		logger.info("------QueryDeptInfoAction end------");
		return output;
	}

	/** 
	 * Description: 写日志信息
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(QueryDeptInfoInputBean input) {
		List<String> log_lst = new ArrayList<String>();
		log_lst.add(input.getDept_id());
		return lgsvc.getLogTxt("查询部门详细信息", log_lst);
	}

}
