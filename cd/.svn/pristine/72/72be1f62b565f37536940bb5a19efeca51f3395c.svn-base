/**
 * Title: UpdateUserTermUseStateAction.java
 * File Description: 修改用户接入终端的启用状态
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015年10月15日
 */
package com.wk.cd.system.us.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.CHANNEL_TYPE;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.ch.dao.ChChannelDaoService;
import com.wk.cd.system.ch.info.ChChannelInfo;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.system.us.bean.DeleteUserTermInputBean;
import com.wk.cd.system.us.bean.UpdateUserTermUseStateInputBean;
import com.wk.cd.system.us.bean.UpdateUserTermUseStateOutputBean;
import com.wk.cd.system.us.dao.UsUserTermDaoService;
import com.wk.cd.system.us.info.UsUserTermInfo;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: 修改用户接入终端的启用状态
 * @author HT
 */
public class UpdateUserTermUseStateAction extends ActionBasic<UpdateUserTermUseStateInputBean, UpdateUserTermUseStateOutputBean>{

	@Inject
	private UsUserTermDaoService usUserTermDaoService;
	@Inject
	private ChChannelDaoService chChannelDaoService;
	@Inject
	private ActionLogPublicService lgsvc;
	
	private static final Log logger = LogFactory.getLog();
	
	/** 
	 * Description: 修改用户接入终端的启用状态
	 * @param input
	 * @return 
	 */
	@Override
	protected UpdateUserTermUseStateOutputBean doAction(UpdateUserTermUseStateInputBean input) {
		logger.info("------UpdateUserTermUseStateAction begin------");
		logger.debug("------user_id=[{}]-----term_no=[{}]",input.getUser_id(),input.getTerm_no());
		
		UpdateUserTermUseStateOutputBean output = new UpdateUserTermUseStateOutputBean();
		String user_id=input.getUser_id();
		String term_no=input.getTerm_no();
		
		Assert.assertNotEmpty(user_id, DeleteUserTermInputBean.USER_IDCN);
		Assert.assertNotEmpty(term_no, DeleteUserTermInputBean.TERM_NOCN);
		
		UsUserTermInfo  utInfo =new UsUserTermInfo();
		utInfo.setUser_id(user_id);
		utInfo.setTerm_no(term_no);
		utInfo=usUserTermDaoService.getInfoByKey(utInfo);
		
		ChChannelInfo chInfo =new ChChannelInfo();
		chInfo.setChannel_code(utInfo.getChannel_code());
		chInfo=chChannelDaoService.getInfoByKey(chInfo);
		
		usUserTermDaoService.updateUseState(user_id,term_no);
		
		//APP端清理申请数据：APP端只允许一台接入终端
		if(chInfo.getChannel_type()==CHANNEL_TYPE.IOS){
			usUserTermDaoService.deleteUserTermByCh(user_id, utInfo.getChannel_code(),term_no);
		}
		
		logger.info("------UpdateUserTermUseStateAction end------");
		return output;
	}

	/** 
	 * Description: 写日志信息
	 * @param input
	 * @return 
	 */
	@Override
	protected String getLogTxt(UpdateUserTermUseStateInputBean input) {
		List<String> log_lst = new ArrayList<String>();
		log_lst.add(input.getUser_id());
		log_lst.add(input.getTerm_no());
		return lgsvc.getLogTxt("修改用户接入终端启用状态", log_lst);
	}
}
