/**
 * Title: UserForceLogoutAction.java
 * File Description:强制签退 
 * @copyright 2015 
 * @company CORSWORK
 * @author lixl
 * @version 1.0
 * @date 2/13/2015
 */

package com.wk.cd.system.us.action;

import com.wk.logging.*;
import com.wk.cd.common.util.Assert;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.us.bean.UserForceLogoutInputBean;
import com.wk.cd.system.us.bean.UserForceLogoutOutputBean;
import com.wk.cd.system.us.service.UsCheckUserService;
import com.wk.lang.Inject;

/**
 * Class Description:强制签退 
 * @author lixl
 */
public class UserForceLogoutAction 
	extends ActionBasic<UserForceLogoutInputBean,UserForceLogoutOutputBean>{
	@Inject 
	private	UsCheckUserService ussvc; 
	private static final Log logger = LogFactory.getLog();



	/**
	 * 强制签退
	 * @param input 
	 * @return UserForceLogoutOutputBean 
	 */
	@Override
	protected UserForceLogoutOutputBean doAction(UserForceLogoutInputBean input){
		UserForceLogoutOutputBean output = new UserForceLogoutOutputBean();
		Assert.assertNotEmpty(input.getUser_id(), UserForceLogoutInputBean.USER_IDCN);
		logger.debug("*************用户[{}]被强制签退***************", input.getUser_id());
		ussvc.userForceLogout(input.getUser_id());
		return output;
	}

	/**
	 * 获取日志
	 * @param input 
	 * @return String 
	 */
	@Override
	protected String getLogTxt(UserForceLogoutInputBean input){
		return "强制签退：" + input.getUser_id();
	}

}

