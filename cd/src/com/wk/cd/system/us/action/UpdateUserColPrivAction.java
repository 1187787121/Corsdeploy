/**
 * Title: UpdateUserColPrivAction.java
 * File Description: �޸��û�ColȨ��
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015��9��9��
 */
package com.wk.cd.system.us.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.AF_FLAG;
import com.wk.cd.enu.PRIV_TYPE;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.dt.service.DtSocService;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.system.us.bean.UpdateUserColPrivInputBean;
import com.wk.cd.system.us.bean.UpdateUserColPrivOutputBean;
import com.wk.cd.system.us.dao.UsUserColPrivDaoService;
import com.wk.cd.system.us.info.UsUserColPrivInfo;
import com.wk.cd.system.us.service.UsAddUserPrivService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: �޸��û�ColȨ��
 * @author HT
 */
public class UpdateUserColPrivAction extends ActionBasic<UpdateUserColPrivInputBean, UpdateUserColPrivOutputBean>{

	@Inject
	private UsUserColPrivDaoService usUserColPrivDaoService;
	@Inject
	private UsAddUserPrivService addUserService;
	@Inject
	private DtSocService dtSocService;
	@Inject
	private ActionLogPublicService lgsvc;
	
	private static final Log logger = LogFactory.getLog();
	
	/** 
	 * Description: �޸��û�ColȨ��
	 * @param input
	 * @return 
	 */
	@Override
	protected UpdateUserColPrivOutputBean doAction(UpdateUserColPrivInputBean input) {
		logger.info("------UpdateUserColPrivAction begin------");
		logger.debug("------user_id=[{}]------",input.getUser_id());
		
		UpdateUserColPrivOutputBean output = new UpdateUserColPrivOutputBean();
		
		String user_id = input.getUser_id();
		List<UsUserColPrivInfo> col_list = input.getCol_list();
		
		Assert.assertNotEmpty(user_id, UpdateUserColPrivInputBean.USER_IDCN);
		
		usUserColPrivDaoService.deleteAllColByUserId(user_id);
		// �޸�
		if (!Assert.isEmpty(col_list) && col_list.size() != 0) {
			for (UsUserColPrivInfo colPrivInfo : col_list) {
				dtSocService.querySocDetailBySocName(colPrivInfo.getSoc_name());
				colPrivInfo.setUser_id(user_id);
				colPrivInfo.setAf_flag(AF_FLAG.ALLOW);
				colPrivInfo.setPriv_type(PRIV_TYPE.PERPETUAL);
			}
			addUserService.addUserCol(col_list);
		}
		
		logger.info("------UpdateUserColPrivAction end------");
		return output;
	}

	/** 
	 * Description: д��־��Ϣ 
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(UpdateUserColPrivInputBean input) {
		List<String> log_lst = new ArrayList<String>();
		log_lst.add(input.getUser_id());
		return lgsvc.getLogTxt("�޸��û�ColȨ��", log_lst);
	}

}
