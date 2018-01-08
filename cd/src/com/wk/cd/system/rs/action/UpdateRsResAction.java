/**
 * Title: UpdateRsResAction.java
 * File Description: 修改资源基础信息
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015年12月31日
 */
package com.wk.cd.system.rs.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.FUN_TYPE;
import com.wk.cd.enu.YN_FLAG;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.system.rs.bean.UpdateRsResInputBean;
import com.wk.cd.system.rs.bean.UpdateRsResOutputBean;
import com.wk.cd.system.rs.dao.RsResDaoService;
import com.wk.cd.system.rs.info.RsResInfo;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 修改资源基础信息
 * @author HT
 */
public class UpdateRsResAction extends ActionBasic<UpdateRsResInputBean, UpdateRsResOutputBean>{
	
	@Inject
	private RsResDaoService rsResDaoService;
	@Inject
	private ActionLogPublicService lgsvc;
	
	private static final Log logger = LogFactory.getLog();
	
	/** 
	 * Description: 修改资源基础信息
	 * @param input
	 * @return 
	 */
	@Override
	protected UpdateRsResOutputBean doAction(UpdateRsResInputBean input) {
		logger.info("------UpdateRsResAction begin------");
		logger.debug("------rs_code=[{}]",input.getRs_code());
		UpdateRsResOutputBean output = new UpdateRsResOutputBean();
		
		String rs_code=input.getRs_code();
		String rs_cn_name=input.getRs_cn_name();
		String rs_bk_desc=input.getRs_bk_desc();
		String rs_url =input.getRs_url();
		String rsim_url =input.getRsim_url();
		int rs_level=input.getRs_level();
		FUN_TYPE rs_fun_type=input.getRs_fun_type();
		YN_FLAG public_yn_flag=input.getPublic_yn_flag();
		Assert.assertNotEmpty(rs_code, UpdateRsResInputBean.RS_CODECN);
		Assert.assertNotEmpty(rs_cn_name, UpdateRsResInputBean.RS_CN_NAMECN);
		Assert.assertNotEmpty(rs_bk_desc, UpdateRsResInputBean.RS_BK_DESCCN);
		Assert.assertNotEmpty(rs_level, UpdateRsResInputBean.RS_LEVELCN);
		Assert.assertNotEmpty(rs_fun_type, UpdateRsResInputBean.RS_FUN_TYPECN);
		Assert.assertNotEmpty(public_yn_flag, UpdateRsResInputBean.PUBLIC_YN_FLAGCN);
		
		
		rsResDaoService.checkRsExist(rs_code);
		
		RsResInfo rsInfo=new RsResInfo();
		rsInfo.setRs_code(rs_code);
		
		rsInfo=rsResDaoService.getInfoByKeyForUpdate(rsInfo);
		
		rsInfo.setRs_cn_name(rs_cn_name);
		rsInfo.setRs_bk_desc(rs_bk_desc);
		rsInfo.setRs_url(rs_url);
		rsInfo.setRsim_url(rsim_url);
		rsInfo.setRs_level(rs_level);
		rsInfo.setRs_fun_type(rs_fun_type);
		rsInfo.setPublic_yn_flag(public_yn_flag);
		rsResDaoService.updateInfo(rsInfo);
		
		logger.info("------UpdateRsResAction end------");
		return output;
	}

	/** 
	 * Description: 写日志信息
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(UpdateRsResInputBean input) {
		List<String> log_lst = new ArrayList<String>();
		log_lst.add(input.getRs_code());
		log_lst.add(input.getRs_cn_name());
		log_lst.add(input.getRs_bk_desc());
		log_lst.add(input.getRs_url());
		log_lst.add(input.getRsim_url());
		log_lst.add(String.valueOf(input.getRs_level()));
		log_lst.add(input.getPublic_yn_flag().getCname());
		log_lst.add(String.valueOf(input.getSort_key()));
		return lgsvc.getLogTxt("修改资源基础信息", log_lst);
	}
}
