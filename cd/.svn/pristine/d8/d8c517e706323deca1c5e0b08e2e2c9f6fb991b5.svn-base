/**
 * Title: UpdateSrvgAction.java
 * File Description: 修改服务组信息 
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015年10月12日
 */
package com.wk.cd.system.sv.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.FUN_TYPE;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.system.sv.bean.UpdateSrvgInputBean;
import com.wk.cd.system.sv.bean.UpdateSrvgOutputBean;
import com.wk.cd.system.sv.dao.SvSrvgDaoService;
import com.wk.cd.system.sv.info.SvSrvgInfo;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description:修改服务组信息 
 * @author HT
 */
public class UpdateSrvgAction extends ActionBasic<UpdateSrvgInputBean, UpdateSrvgOutputBean>{


	@Inject
	private SvSrvgDaoService svSrvgDaoService;
	@Inject
	private ActionLogPublicService lgsvc;
	
	private static final Log logger = LogFactory.getLog();
	
	/** 
	 * Description: 修改服务组信息 
	 * @param input
	 * @return 
	 */
	@Override
	protected UpdateSrvgOutputBean doAction(UpdateSrvgInputBean input) {
		logger.info("------UpdateSrvgAction begin------");
		logger.debug("srvg_code=[{}]", input.getSrvg_code());
		UpdateSrvgOutputBean output = new UpdateSrvgOutputBean();
		
		String srvg_code=input.getSrvg_code();
		String srvg_cn_name=input.getSrvg_cn_name();
		FUN_TYPE srvg_fun_type=input.getSrvg_fun_type();
		String srvg_bk_desc=input.getSrvg_bk_desc();
		
		// 必填项
		Assert.assertNotEmpty(srvg_code,UpdateSrvgInputBean.SRVG_CODECN);
		Assert.assertNotEmpty(srvg_cn_name,UpdateSrvgInputBean.SRVG_CN_NAMECN);
		Assert.assertNotEmpty(srvg_fun_type,UpdateSrvgInputBean.SRVG_FUN_TYPECN);
		Assert.assertNotEmpty(srvg_bk_desc,UpdateSrvgInputBean.SRVG_BK_DESCCN);
		
		
		
		svSrvgDaoService.checkSrvgCodeExist(srvg_code);
		
		SvSrvgInfo info=new SvSrvgInfo();
		info.setSrvg_code(srvg_code);
		info.setSrvg_cn_name(srvg_cn_name);
		info.setSrvg_fun_type(srvg_fun_type);
		info.setSrvg_bk_desc(srvg_bk_desc);
		svSrvgDaoService.updateSrvg(info);
		
		logger.info("------UpdateSrvgAction end------");
		return output;
	}

	/** 
	 * Description: 写日志信息
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(UpdateSrvgInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add("srvg_code=" + input.getSrvg_code());
		lst_val.add("srvg_cn_name=" + input.getSrvg_cn_name());
		lst_val.add("srvg_fun_type=" + input.getSrvg_fun_type());
		lst_val.add("srvg_bk_desc=" + input.getSrvg_bk_desc());
		return lgsvc.getLogTxt("修改服务组信息", lst_val);
	}

}
