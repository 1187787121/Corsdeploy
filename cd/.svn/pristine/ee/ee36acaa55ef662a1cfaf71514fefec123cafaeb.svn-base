/**
 * Title: UpdateDeptSqlPrivAction.java
 * File Description: 修改部门Sql权限
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015年8月28日
 */
package com.wk.cd.system.dp.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.dp.bean.UpdateDeptSqlPrivInputBean;
import com.wk.cd.system.dp.bean.UpdateDeptSqlPrivOutputBean;
import com.wk.cd.system.dp.dao.DpDeptSqlPrivDaoService;
import com.wk.cd.system.dp.info.DpDeptSqlPrivInfo;
import com.wk.cd.system.dt.service.DtCheckSocExistService;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 修改部门Sql权限
 * @author HT
 */
public class UpdateDeptSqlPrivAction extends ActionBasic<UpdateDeptSqlPrivInputBean, UpdateDeptSqlPrivOutputBean>{

	@Inject
	private DpDeptSqlPrivDaoService dpDeptSqlPrivDaoService;
	@Inject
	private DtCheckSocExistService dtCheckSocExistService;
	@Inject
	private ActionLogPublicService lgsvc;
	
	private static final Log logger = LogFactory.getLog();
	
	/** 
	 * Description: 修改部门Sql权限
	 * @param input
	 * @return 
	 */
	@Override
	protected UpdateDeptSqlPrivOutputBean doAction(UpdateDeptSqlPrivInputBean input) {
		logger.info("------UpdateDeptSqlPrivAction begin------");
		logger.debug("------dept_id=[{}]",input.getDept_id());
		UpdateDeptSqlPrivOutputBean output=new UpdateDeptSqlPrivOutputBean();
		String dept_id =input.getDept_id();
		List<DpDeptSqlPrivInfo> sql_list=input.getSql_list();
		
		Assert.assertNotEmpty(dept_id, UpdateDeptSqlPrivInputBean.DEPT_IDCN);
		// 修改权限
		dpDeptSqlPrivDaoService.deleteDeptSqlPrivInfo(dept_id);
		
		if(!Assert.isEmpty(sql_list)){
			for(DpDeptSqlPrivInfo sql_info:sql_list){
				sql_info.setDept_id(dept_id);
				// *验证数据源名称是否存在
				dtCheckSocExistService.checkSocExist(sql_info.getSoc_name());
			}
			dpDeptSqlPrivDaoService.insertListInfo(sql_list);
		}
		
		logger.info("------UpdateDeptSqlPrivAction end------");
		return output;
	}

	/** 
	 * Description: 写日志信息
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(UpdateDeptSqlPrivInputBean input) {
		List<String> log_lst = new ArrayList<String>();
		log_lst.add(input.getDept_id());
		return lgsvc.getLogTxt("修改部门Sql权限信息", log_lst);
	}
}
