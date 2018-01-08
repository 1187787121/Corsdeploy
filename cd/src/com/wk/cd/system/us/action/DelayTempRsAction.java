/**
 * Title: DelayTempRsAction.java
 * File Description: 临时资源申请延期
 * @copyright: 2016
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2016年3月22日
 */
package com.wk.cd.system.us.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wk.cd.bean.ActionRootOutputBean;
import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.AF_FLAG;
import com.wk.cd.enu.PRIV_TYPE;
import com.wk.cd.exc.RecordNotFoundException;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.dp.bean.RsPrivBean;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.system.rs.dao.RsResDaoService;
import com.wk.cd.system.rs.info.RsResInfo;
import com.wk.cd.system.us.bean.DelayTempRsInputBean;
import com.wk.cd.system.us.dao.UsUserRsPrivDaoService;
import com.wk.cd.system.us.info.UsUserRsPrivInfo;
import com.wk.cd.system.us.service.UsGetUserPrivService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;
import com.wk.util.JaDateTime;

/**
 * Class Description: 临时资源申请延期
 * @author HT
 */
public class DelayTempRsAction extends ActionBasic<DelayTempRsInputBean, ActionRootOutputBean>{
	@Inject
	private RsResDaoService rsResDaoService;
	@Inject
	private UsGetUserPrivService usGetUserPrivService;
	@Inject
	private UsUserRsPrivDaoService usUserRsPrivDaoService;
	@Inject
	private ActionLogPublicService lgsvc;
	
	private static final Log logger = LogFactory.getLog();
	/** 
	 * Description: 临时资源申请延期
	 * @param input
	 * @return 
	 */
	@Override
	protected ActionRootOutputBean doAction(DelayTempRsInputBean input) {
		ActionRootOutputBean output = new ActionRootOutputBean();
		String user_id=input.getOrg_user_id();
		String rs_code=input.getRs_code();
		JaDateTime end_bk_datetime=input.getTempend_bk_datetime();
		logger.info("------DelayTempRsAction begin------");
		logger.debug("------user_id=[{}]---rs_code=[{}]",user_id,rs_code);
		
		Assert.assertNotEmpty(user_id, DelayTempRsInputBean.ORG_USER_IDCN);
		Assert.assertNotEmpty(rs_code, DelayTempRsInputBean.RS_CODECN);
		Assert.assertNotEmpty(end_bk_datetime, DelayTempRsInputBean.TEMPEND_BK_DATETIMECN);
		RsResInfo info =new RsResInfo();
		info.setRs_code(rs_code);
		info=rsResDaoService.getInfoByKey(info);
		if(Assert.isEmpty(info)){
			throw new RecordNotFoundException().addScene("TABLE",RsResInfo.TABLECN).addScene("FIELD", rs_code);
		}else if(info.getRs_level()!=2&&info.getRs_level()!=0){//非页面资源
			//throw
		}else{
			// 查询用户资源权限
			List<RsPrivBean> enrs_list = usGetUserPrivService.queryRsPrivByUserId(user_id);
			Map<String, RsPrivBean> usRsMap=new HashMap<String, RsPrivBean>();
			
			for(RsPrivBean us_rs_priv:enrs_list){
				usRsMap.put(us_rs_priv.getRs_code(), us_rs_priv);
			}
			//检查系统资源是否有权限
			if(!usRsMap.containsKey(info.getBl_rs_code())){
				UsUserRsPrivInfo rsTempInfo=new UsUserRsPrivInfo();
				rsTempInfo.setUser_id(user_id);
				rsTempInfo.setPriv_type(PRIV_TYPE.TEMPORARY);
				rsTempInfo.setAf_flag(AF_FLAG.ALLOW);
				rsTempInfo.setRs_code(info.getBl_rs_code());
				rsTempInfo=usUserRsPrivDaoService.getInfoByKeyForUpdate(rsTempInfo);
				if(Assert.isEmpty(rsTempInfo)){
					rsTempInfo=new UsUserRsPrivInfo();
					rsTempInfo.setUser_id(user_id);
					rsTempInfo.setRs_code(info.getBl_rs_code());
					rsTempInfo.setPriv_type(PRIV_TYPE.TEMPORARY);
					rsTempInfo.setAf_flag(AF_FLAG.ALLOW);
					rsTempInfo.setTempstart_bk_datetime(JaDateTime.now());
					rsTempInfo.setTempend_bk_datetime(end_bk_datetime);
					usUserRsPrivDaoService.insertInfo(rsTempInfo);
				}else if(!Assert.isEmpty(rsTempInfo)&&!Assert.isEmpty(rsTempInfo.getTempend_bk_datetime())&&rsTempInfo.getTempend_bk_datetime().isAfter(end_bk_datetime)){
					rsTempInfo.setTempend_bk_datetime(end_bk_datetime);
					usUserRsPrivDaoService.updateInfo(rsTempInfo);
				}
				
			}
		
			if(info.getRs_level()==0){
				UsUserRsPrivInfo rsTempInfo=new UsUserRsPrivInfo();
				rsTempInfo.setUser_id(user_id);
				rsTempInfo.setPriv_type(PRIV_TYPE.TEMPPAGE);
				rsTempInfo.setAf_flag(AF_FLAG.ALLOW);
				rsTempInfo.setRs_code(info.getRs_code());
				rsTempInfo=usUserRsPrivDaoService.getInfoByKeyForUpdate(rsTempInfo);
				rsTempInfo.setTempend_bk_datetime(end_bk_datetime);
				usUserRsPrivDaoService.updateInfo(rsTempInfo);
			}else{
				if(!usRsMap.containsKey(info.getSuper_rs_code())){//上级资源是否有权限
					UsUserRsPrivInfo rsTempInfo=new UsUserRsPrivInfo();
					rsTempInfo.setUser_id(user_id);
					rsTempInfo.setPriv_type(PRIV_TYPE.TEMPORARY);
					rsTempInfo.setAf_flag(AF_FLAG.ALLOW);
					rsTempInfo.setRs_code(info.getSuper_rs_code());
					rsTempInfo=usUserRsPrivDaoService.getInfoByKeyForUpdate(rsTempInfo);
					if(Assert.isEmpty(rsTempInfo)){
						rsTempInfo=new UsUserRsPrivInfo();
						rsTempInfo.setUser_id(user_id);
						rsTempInfo.setRs_code(info.getSuper_rs_code());
						rsTempInfo.setPriv_type(PRIV_TYPE.TEMPORARY);
						rsTempInfo.setAf_flag(AF_FLAG.ALLOW);
						rsTempInfo.setTempstart_bk_datetime(JaDateTime.now());
						rsTempInfo.setTempend_bk_datetime(end_bk_datetime);
						usUserRsPrivDaoService.insertInfo(rsTempInfo);
					}else if(!Assert.isEmpty(rsTempInfo)&&!Assert.isEmpty(rsTempInfo.getTempend_bk_datetime())&&rsTempInfo.getTempend_bk_datetime().isAfter(end_bk_datetime)){
						rsTempInfo.setTempend_bk_datetime(end_bk_datetime);
						usUserRsPrivDaoService.updateInfo(rsTempInfo);
					}
				}
				UsUserRsPrivInfo rsTempInfo=new UsUserRsPrivInfo();
				rsTempInfo.setUser_id(user_id);
				rsTempInfo.setPriv_type(PRIV_TYPE.TEMPPAGE);
				rsTempInfo.setAf_flag(AF_FLAG.ALLOW);
				rsTempInfo.setRs_code(info.getRs_code());
				rsTempInfo=usUserRsPrivDaoService.getInfoByKeyForUpdate(rsTempInfo);
				rsTempInfo.setTempend_bk_datetime(end_bk_datetime);
				usUserRsPrivDaoService.updateInfo(rsTempInfo);
			}
		}
		
		logger.info("------DelayTempRsAction end------");
		return output;
	}

	/** 
	 * Description: 写日志
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(DelayTempRsInputBean input) {
		List<String> log_lst = new ArrayList<String>();
		log_lst.add(input.getOrg_user_id());
		log_lst.add(input.getRs_code());
		Assert.assertNotEmpty(input.getTempend_bk_datetime(), DelayTempRsInputBean.TEMPEND_BK_DATETIMECN);
		log_lst.add(input.getTempend_bk_datetime().toSimpleDateString());
		return lgsvc.getLogTxt("临时资源申请延期", log_lst);
	}

}
