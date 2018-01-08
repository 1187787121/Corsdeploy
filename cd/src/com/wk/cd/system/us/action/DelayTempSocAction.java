/**
 * Title: DelayTempSocAction.java
 * File Description:  临时数据源申请延期
 * @copyright: 2016
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2016年3月22日
 */
package com.wk.cd.system.us.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.bean.ActionRootOutputBean;
import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.AF_FLAG;
import com.wk.cd.enu.PRIV_TYPE;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.system.us.bean.DelayTempSocInputBean;
import com.wk.cd.system.us.dao.UsUserSocPrivDaoService;
import com.wk.cd.system.us.info.UsUserSocPrivInfo;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;
import com.wk.util.JaDateTime;

/**
 * Class Description: 临时数据源申请延期
 * @author HT
 */
public class DelayTempSocAction extends ActionBasic<DelayTempSocInputBean, ActionRootOutputBean>{
	@Inject
	private UsUserSocPrivDaoService usUserSocPrivDaoService;
	@Inject
	private ActionLogPublicService lgsvc;
	
	private static final Log logger = LogFactory.getLog();
	/** 
	 * Description: 临时数据源申请延期
	 * @param input
	 * @return 
	 */
	@Override
	protected ActionRootOutputBean doAction(DelayTempSocInputBean input) {
		ActionRootOutputBean output = new ActionRootOutputBean();
		String user_id=input.getOrg_user_id();
		String soc_name=input.getSoc_name();
		JaDateTime end_bk_datetime=input.getTempend_bk_datetime();
		logger.info("------DelayTempSocAction begin------");
		logger.debug("------user_id=[{}]---soc_name=[{}]",user_id,soc_name);
		
		Assert.assertNotEmpty(user_id, DelayTempSocInputBean.ORG_USER_IDCN);
		Assert.assertNotEmpty(soc_name, DelayTempSocInputBean.SOC_NAMECN);
		Assert.assertNotEmpty(end_bk_datetime, DelayTempSocInputBean.TEMPEND_BK_DATETIMECN);
		
		UsUserSocPrivInfo socTempInfo=new UsUserSocPrivInfo();
		socTempInfo.setUser_id(user_id);
		socTempInfo.setPriv_type(PRIV_TYPE.TEMPORARY);
		socTempInfo.setAf_flag(AF_FLAG.ALLOW);
		socTempInfo.setSoc_name(soc_name);
		socTempInfo=usUserSocPrivDaoService.getInfoByKeyForUpdate(socTempInfo);
		socTempInfo.setTempend_bk_datetime(end_bk_datetime);
		usUserSocPrivDaoService.updateInfo(socTempInfo);
		
		logger.info("------DelayTempSocAction end------");
		return output;
	}

	/** 
	 * Description: 写日志
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(DelayTempSocInputBean input) {
		List<String> log_lst = new ArrayList<String>();
		log_lst.add(input.getOrg_user_id());
		log_lst.add(input.getSoc_name());
		Assert.assertNotEmpty(input.getTempend_bk_datetime(), DelayTempSocInputBean.TEMPEND_BK_DATETIMECN);
		log_lst.add(input.getTempend_bk_datetime().toSimpleDateString());
		return lgsvc.getLogTxt("临时数据源申请延期", log_lst);
	}

}
