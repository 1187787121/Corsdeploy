/**
 * Title: DelayTempColAction.java
 * File Description: 临时col权限申请延期
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
import com.wk.cd.system.us.bean.DelayTempColInputBean;
import com.wk.cd.system.us.bean.DelayTempSqlInputBean;
import com.wk.cd.system.us.dao.UsUserColPrivDaoService;
import com.wk.cd.system.us.info.UsUserColPrivInfo;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;
import com.wk.util.JaDateTime;

/**
 * Class Description: 临时col权限申请延期
 * @author HT
 */
public class DelayTempColAction extends ActionBasic<DelayTempColInputBean, ActionRootOutputBean>{
	@Inject
	private UsUserColPrivDaoService usUserColPrivDaoService;
	@Inject
	private ActionLogPublicService lgsvc;
	
	private static final Log logger = LogFactory.getLog();
	/** 
	 * Description: 临时col权限申请延期
	 * @param input
	 * @return 
	 */
	@Override
	protected ActionRootOutputBean doAction(DelayTempColInputBean input) {
		ActionRootOutputBean output = new ActionRootOutputBean();
		String user_id=input.getOrg_user_id();
		String soc_name=input.getSoc_name();
		String tbl_name=input.getTbl_name();
		String col_name=input.getCol_name();
		JaDateTime end_bk_datetime=input.getTempend_bk_datetime();
		logger.info("------DelayTempColAction begin------");
		logger.debug("------user_id=[{}]---soc_name=[{}]---tbl col_name=[{}]---",user_id,soc_name,tbl_name+" "+col_name);
		
		
		Assert.assertNotEmpty(user_id, DelayTempColInputBean.ORG_USER_IDCN);
		Assert.assertNotEmpty(soc_name, DelayTempColInputBean.SOC_NAMECN);
		Assert.assertNotEmpty(tbl_name, DelayTempColInputBean.TBL_NAMECN);
		Assert.assertNotEmpty(col_name, DelayTempColInputBean.COL_NAMECN);
		Assert.assertNotEmpty(end_bk_datetime, DelayTempColInputBean.TEMPEND_BK_DATETIMECN);
		
		UsUserColPrivInfo colTempInfo=new UsUserColPrivInfo();
		colTempInfo.setUser_id(user_id);
		colTempInfo.setPriv_type(PRIV_TYPE.TEMPORARY);
		colTempInfo.setAf_flag(AF_FLAG.ALLOW);
		colTempInfo.setSoc_name(soc_name);
		colTempInfo.setTbl_name(tbl_name);
		colTempInfo.setCol_name(col_name);
		colTempInfo=usUserColPrivDaoService.getInfoByKeyForUpdate(colTempInfo);
		colTempInfo.setTempend_bk_datetime(end_bk_datetime);
		usUserColPrivDaoService.updateInfo(colTempInfo);
		
		logger.info("------DelayTempColAction end------");
		return output;
	}

	/** 
	 * Description: 写日志
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(DelayTempColInputBean input) {
		List<String> log_lst = new ArrayList<String>();
		log_lst.add(input.getOrg_user_id());
		log_lst.add(input.getSoc_name());
		log_lst.add(input.getTbl_name());
		log_lst.add(input.getCol_name());
		Assert.assertNotEmpty(input.getTempend_bk_datetime(), DelayTempSqlInputBean.TEMPEND_BK_DATETIMECN);
		log_lst.add(input.getTempend_bk_datetime().toSimpleDateString());
		return lgsvc.getLogTxt("临时col权限申请延期", log_lst);
	}
}
