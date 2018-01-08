/**
 * Title: AddSrvgAction.java
 * File Description:新增服务组信息 
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015年10月12日
 */
package com.wk.cd.system.sv.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.cm.service.GenNoService;
import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.FUN_TYPE;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.system.sv.bean.AddSrvgInputBean;
import com.wk.cd.system.sv.bean.AddSrvgOutputBean;
import com.wk.cd.system.sv.dao.SvSrvgDaoService;
import com.wk.cd.system.sv.info.SvSrvgInfo;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 新增服务组信息
 * @author HT
 */
public class AddSrvgAction extends ActionBasic<AddSrvgInputBean, AddSrvgOutputBean>{

	@Inject
	private GenNoService genNoService;
	@Inject
	private SvSrvgDaoService svSrvgDaoService;
	@Inject
	private ActionLogPublicService lgsvc;
	
	private static final Log logger = LogFactory.getLog();
	
	/** 
	 * Description: 新增服务组信息
	 * @param input
	 * @return 
	 */
	@Override
	protected AddSrvgOutputBean doAction(AddSrvgInputBean input) {
		logger.info("------AddSrvgAction begin------");
		logger.debug("srvg_cn_name=[{}]", input.getSrvg_cn_name());
		AddSrvgOutputBean output = new AddSrvgOutputBean();
		
		String srvg_cn_name=input.getSrvg_cn_name();
		FUN_TYPE srvg_fun_type=input.getSrvg_fun_type();
		String srvg_bk_desc=input.getSrvg_bk_desc();
		
		// 必填项
		Assert.assertNotEmpty(srvg_cn_name,AddSrvgInputBean.SRVG_CN_NAMECN);
		Assert.assertNotEmpty(srvg_fun_type,AddSrvgInputBean.SRVG_FUN_TYPECN);
		Assert.assertNotEmpty(srvg_bk_desc,AddSrvgInputBean.SRVG_BK_DESCCN);
		
		String srvg_code=genNoService.getSrvgCode(input.getDtbs_bk_date());
		
		svSrvgDaoService.checkSrvgCodeNoExist(srvg_code);
		
		svSrvgDaoService.checkSrvgCnNameNoExist(srvg_cn_name);
		
		SvSrvgInfo info=new SvSrvgInfo();
		info.setSrvg_code(srvg_code);
		info.setSrvg_cn_name(srvg_cn_name);
		info.setSrvg_fun_type(srvg_fun_type);
		info.setSrvg_bk_desc(srvg_bk_desc);
		svSrvgDaoService.insertInfo(info);
		
		logger.info("------AddSrvgAction end------");
		return output;
	}

	/** 
	 * Description: 写日志信息
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(AddSrvgInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add("srvg_cn_name=" + input.getSrvg_cn_name());
		lst_val.add("srvg_fun_type=" + input.getSrvg_fun_type());
		lst_val.add("srvg_bk_desc=" + input.getSrvg_bk_desc());
		return lgsvc.getLogTxt("新增服务组信息", lst_val);
	}

}
