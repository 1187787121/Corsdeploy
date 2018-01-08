/**
 * Title: UpdateDeptColPrivAction.java
 * File Description: 修改部门col权限
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
import com.wk.cd.system.dp.bean.UpdateDeptColPrivInputBean;
import com.wk.cd.system.dp.bean.UpdateDeptColPrivOutputBean;
import com.wk.cd.system.dp.dao.DpDeptColPrivDaoService;
import com.wk.cd.system.dp.info.DpDeptColPrivInfo;
import com.wk.cd.system.dt.service.DtCheckSocExistService;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 修改部门col权限
 * @author HT
 */
public class UpdateDeptColPrivAction extends ActionBasic<UpdateDeptColPrivInputBean, UpdateDeptColPrivOutputBean>{

	@Inject
	private DpDeptColPrivDaoService dpDeptColPrivDaoService;
	@Inject
	private DtCheckSocExistService dtCheckSocExistService;
	@Inject
	private ActionLogPublicService lgsvc;
	
	private static final Log logger = LogFactory.getLog();
	
	/** 
	 * Description: 修改部门col权限
	 * @param input
	 * @return 
	 */
	@Override
	protected UpdateDeptColPrivOutputBean doAction(UpdateDeptColPrivInputBean input) {
		logger.info("------UpdateDeptColPrivAction begin------");
		logger.debug("------dept_id=[{}]",input.getDept_id());
		UpdateDeptColPrivOutputBean output=new UpdateDeptColPrivOutputBean();
		String dept_id =input.getDept_id();
		List<DpDeptColPrivInfo> col_list=input.getCol_list();
		
		Assert.assertNotEmpty(dept_id, UpdateDeptColPrivInputBean.DEPT_IDCN);
		// 修改权限
		dpDeptColPrivDaoService.deleteDeptColPrivInfo(dept_id);
		
		if(!Assert.isEmpty(col_list)){
			for(DpDeptColPrivInfo col_info:col_list){
				col_info.setDept_id(dept_id);
				// *验证数据源名称是否存在
				dtCheckSocExistService.checkSocExist(col_info.getSoc_name());
			}
			dpDeptColPrivDaoService.insertListInfo(col_list);
		}
		
		logger.info("------UpdateDeptColPrivAction end------");
		return output;
	}

	/** 
	 * Description: 写日志信息
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(UpdateDeptColPrivInputBean input) {
		List<String> log_lst = new ArrayList<String>();
		log_lst.add(input.getDept_id());
		return lgsvc.getLogTxt("修改部门Col权限信息", log_lst);
	}

}
