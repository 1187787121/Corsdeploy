/**
 * Title: UpdateUserTempRsPrivAction.java
 * File Description: �޸��û���ʱ��ԴȨ��
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015��10��9��
 */
package com.wk.cd.system.us.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.AF_FLAG;
import com.wk.cd.enu.PRIV_TYPE;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.dp.bean.RsPrivBean;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.system.us.bean.UpdateUserTempRsPrivInputBean;
import com.wk.cd.system.us.bean.UpdateUserTempRsPrivOutputBean;
import com.wk.cd.system.us.bean.UsUserRsPrivBean;
import com.wk.cd.system.us.dao.UsUserRsPrivDaoService;
import com.wk.cd.system.us.info.UsUserRsPrivInfo;
import com.wk.cd.system.us.service.UsGetUserPrivService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: �޸��û���ʱ��ԴȨ��
 * @author HT
 */
public class UpdateUserTempRsPrivAction extends ActionBasic<UpdateUserTempRsPrivInputBean, UpdateUserTempRsPrivOutputBean>{
	
	@Inject
	private UsGetUserPrivService usGetUserPrivService;
	@Inject
	private UsUserRsPrivDaoService usUserRsPrivDaoService;
	@Inject
	private ActionLogPublicService lgsvc;
	
	private static final Log logger = LogFactory.getLog();

	/** 
	 * Description: �޸��û���ʱ��ԴȨ��
	 * @param input
	 * @return 
	 */
	@Override
	protected UpdateUserTempRsPrivOutputBean doAction(UpdateUserTempRsPrivInputBean input) {
		logger.info("------UpdateUserTempRsPrivAction begin------");
		logger.debug("------user_id=[{}]------",input.getUser_id());
		
		UpdateUserTempRsPrivOutputBean output = new UpdateUserTempRsPrivOutputBean();
		
		String user_id = input.getUser_id();
		List<UsUserRsPrivBean> rs_list=input.getRs_list();
		
		Assert.assertNotEmpty(user_id, UpdateUserTempRsPrivInputBean.USER_IDCN);
		Assert.assertNotEmpty(rs_list, UpdateUserTempRsPrivInputBean.RS_LISTCN);
		
		// ��ѯ�û���ԴȨ��
		List<RsPrivBean> enrs_list = usGetUserPrivService.queryRsPrivByUserId(user_id);
		Map<String, RsPrivBean> usRsMap=new HashMap<String, RsPrivBean>();
		
		for(RsPrivBean us_rs_priv:enrs_list){
			usRsMap.put(us_rs_priv.getRs_code(), us_rs_priv);
		}
				
		Map<String, String> blrsMap=new HashMap<String, String>();//����Ȩ�޵�ϵͳ��Դ
		
		// �޸�
		for (UsUserRsPrivBean rsPrivBean : rs_list) {
			//ϵͳ��ʱ��Դ����
			if(!blrsMap.containsKey(rsPrivBean.getBl_rs_code())){
				//���ϵͳ��Դ�Ƿ���Ȩ��
				if(!usRsMap.containsKey(rsPrivBean.getBl_rs_code())){
					UsUserRsPrivInfo rsTempInfo=new UsUserRsPrivInfo();
					rsTempInfo.setUser_id(rsPrivBean.getUser_id());
					rsTempInfo.setPriv_type(PRIV_TYPE.TEMPORARY);
					rsTempInfo.setAf_flag(AF_FLAG.ALLOW);
					rsTempInfo.setRs_code(rsPrivBean.getBl_rs_code());
					rsTempInfo=usUserRsPrivDaoService.getInfoByKeyForUpdate(rsTempInfo);
					if(Assert.isEmpty(rsTempInfo)){
						rsTempInfo=new UsUserRsPrivInfo();
						rsTempInfo.setUser_id(rsPrivBean.getUser_id());
						rsTempInfo.setRs_code(rsPrivBean.getBl_rs_code());
						rsTempInfo.setPriv_type(PRIV_TYPE.TEMPORARY);
						rsTempInfo.setAf_flag(AF_FLAG.ALLOW);
						rsTempInfo.setTempstart_bk_datetime(rsPrivBean.getTempstart_bk_datetime());
						rsTempInfo.setTempend_bk_datetime(rsPrivBean.getTempend_bk_datetime());
						usUserRsPrivDaoService.insertInfo(rsTempInfo);
					}else if(!Assert.isEmpty(rsTempInfo)&&!Assert.isEmpty(rsTempInfo.getTempend_bk_datetime())&&rsTempInfo.getTempend_bk_datetime().isAfter(rsPrivBean.getTempend_bk_datetime())){
						rsTempInfo.setTempend_bk_datetime(rsPrivBean.getTempend_bk_datetime());
						usUserRsPrivDaoService.updateInfo(rsTempInfo);
					}
					
				}
				blrsMap.put(rsPrivBean.getBl_rs_code(),rsPrivBean.getBl_rs_code());
			}
			
			//��ʱȨ������
			if(rsPrivBean.getRs_level()==0){
				UsUserRsPrivInfo rsTempInfo=new UsUserRsPrivInfo();
				rsTempInfo.setUser_id(rsPrivBean.getUser_id());
				rsTempInfo.setPriv_type(PRIV_TYPE.TEMPPAGE);
				rsTempInfo.setAf_flag(AF_FLAG.ALLOW);
				rsTempInfo.setRs_code(rsPrivBean.getRs_code());
				rsTempInfo.setTempstart_bk_datetime(rsPrivBean.getTempstart_bk_datetime());
				rsTempInfo.setTempend_bk_datetime(rsPrivBean.getTempend_bk_datetime());
				usUserRsPrivDaoService.insertInfo(rsTempInfo);
			}else if(rsPrivBean.getRs_level()==2){
				if(!usRsMap.containsKey(rsPrivBean.getSuper_rs_code())){//�ϼ���Դ�Ƿ���Ȩ��
					UsUserRsPrivInfo rsTempInfo=new UsUserRsPrivInfo();
					rsTempInfo.setUser_id(rsPrivBean.getUser_id());
					rsTempInfo.setPriv_type(PRIV_TYPE.TEMPORARY);
					rsTempInfo.setAf_flag(AF_FLAG.ALLOW);
					rsTempInfo.setRs_code(rsPrivBean.getSuper_rs_code());
					rsTempInfo=usUserRsPrivDaoService.getInfoByKeyForUpdate(rsTempInfo);
					if(Assert.isEmpty(rsTempInfo)){
						rsTempInfo=new UsUserRsPrivInfo();
						rsTempInfo.setUser_id(rsPrivBean.getUser_id());
						rsTempInfo.setRs_code(rsPrivBean.getSuper_rs_code());
						rsTempInfo.setPriv_type(PRIV_TYPE.TEMPORARY);
						rsTempInfo.setAf_flag(AF_FLAG.ALLOW);
						rsTempInfo.setTempstart_bk_datetime(rsPrivBean.getTempstart_bk_datetime());
						rsTempInfo.setTempend_bk_datetime(rsPrivBean.getTempend_bk_datetime());
						usUserRsPrivDaoService.insertInfo(rsTempInfo);
					}else if(!Assert.isEmpty(rsTempInfo)&&!Assert.isEmpty(rsTempInfo.getTempend_bk_datetime())&&rsTempInfo.getTempend_bk_datetime().isAfter(rsPrivBean.getTempend_bk_datetime())){
						rsTempInfo.setTempend_bk_datetime(rsPrivBean.getTempend_bk_datetime());
						usUserRsPrivDaoService.updateInfo(rsTempInfo);
					}
				}
				UsUserRsPrivInfo rsTempInfo=new UsUserRsPrivInfo();
				rsTempInfo.setUser_id(rsPrivBean.getUser_id());
				rsTempInfo.setPriv_type(PRIV_TYPE.TEMPPAGE);
				rsTempInfo.setAf_flag(AF_FLAG.ALLOW);
				rsTempInfo.setRs_code(rsPrivBean.getRs_code());
				rsTempInfo.setTempstart_bk_datetime(rsPrivBean.getTempstart_bk_datetime());
				rsTempInfo.setTempend_bk_datetime(rsPrivBean.getTempend_bk_datetime());
				usUserRsPrivDaoService.insertInfo(rsTempInfo);
			}
		}
		
		logger.info("------UpdateUserTempRsPrivAction end------");
		return output;
	}

	/** 
	 * Description: д��־��Ϣ
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(UpdateUserTempRsPrivInputBean input) {
		List<String> log_lst = new ArrayList<String>();
		log_lst.add(input.getUser_id());
		return lgsvc.getLogTxt("�޸��û���ʱ��ԴȨ��", log_lst);
	}
}
