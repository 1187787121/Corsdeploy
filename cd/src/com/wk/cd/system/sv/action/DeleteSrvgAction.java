/**
 * Title: DeleteSrvgAction.java
 * File Description: 删除服务组信息
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015年10月12日
 */
package com.wk.cd.system.sv.action;

import com.wk.cd.common.util.Assert;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.sv.bean.DeleteSrvgInputBean;
import com.wk.cd.system.sv.bean.DeleteSrvgOutputBean;
import com.wk.cd.system.sv.dao.SvSrvgDaoService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 删除服务组信息
 * @author HT
 */
public class DeleteSrvgAction extends ActionBasic<DeleteSrvgInputBean, DeleteSrvgOutputBean>{


	@Inject
	private SvSrvgDaoService svSrvgDaoService;
	
	private static final Log logger = LogFactory.getLog();
	
	/** 
	 * Description: 删除服务组信息
	 * @param input
	 * @return 
	 */
	@Override
	protected DeleteSrvgOutputBean doAction(DeleteSrvgInputBean input) {
		logger.info("------DeleteSrvgAction begin------");
		logger.debug("srvg_code=[{}]", input.getSrvg_code());
		DeleteSrvgOutputBean output = new DeleteSrvgOutputBean();
		
		String srvg_code=input.getSrvg_code();
		
		// 必填项
		Assert.assertNotEmpty(srvg_code,DeleteSrvgInputBean.SRVG_CODECN);
		
		svSrvgDaoService.checkSrvgCodeExist(srvg_code);
		
		svSrvgDaoService.deleteSrvg(srvg_code);
		
		logger.info("------DeleteSrvgAction end------");
		return output;
	}

	/** 
	 * Description: 写日志信息
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(DeleteSrvgInputBean input) {
		// TODO Auto-generated method stub
		return null;
	}

}
