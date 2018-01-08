/**
 * Title: UserLogoutAction.java
 * File Description:  用户登出服务类
 * @copyright 2015 
 * @company CORSWORK
 * @author lixl
 * @version 1.0
 * @date 2/12/2015
 */

package com.wk.cd.system.us.action;

import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.us.bean.UserLogoutInputBean;
import com.wk.cd.system.us.bean.UserLogoutOutputBean;
import com.wk.cd.system.us.service.UsCheckUserService;
import com.wk.lang.Inject;

/**
 * Class Description:用户登出服务类
 * @author lixl
 */
public class UserLogoutAction extends ActionBasic<UserLogoutInputBean,UserLogoutOutputBean>{
	@Inject 
	private	UsCheckUserService ussvc; 

	/**
	 * 用户登出服务
	 * @param input 输入接口
	 * @return UserLogoutOutputBean 
	 */
	@Override
	protected UserLogoutOutputBean doAction(UserLogoutInputBean input){
		UserLogoutOutputBean output = new UserLogoutOutputBean();
		ussvc.userLoginOut(input.getOrg_user_id());

		return output;
	}

	/**
	 * 获取日志
	 * @param input 
	 * @return String 
	 */
	@Override
	protected String getLogTxt(UserLogoutInputBean input){
		return "用户登出服务";
	}

	//更新用户临时权限次数,不需要
	protected void reduceTmpPrivNum(UserLogoutInputBean input){
	}

	/**
	 * 登出不检查
	 * @param input 
	 */
	@Override
	protected void chkState(UserLogoutInputBean input){
	}

	/**
	 * 登出不检查
	 * @param input 
	 */
	@Override
	protected void chkDeptPriv(UserLogoutInputBean input){
	}

	/**
	 * 登出不检查
	 * @param input 
	 */
	@Override
	protected void chkUserPriv(UserLogoutInputBean input){
	}

	/**
	 * 登出不需要加载数据源
	 * @param input 
	 */
	@Override
	protected void dataSourceLoad(UserLogoutInputBean input){
	}

	@Override
	protected boolean isChk(UserLogoutInputBean input){
	    return false;
	}

	@Override
	protected boolean isAuth(UserLogoutInputBean input){
	    return false;
	}
	
	@Override
	protected boolean isLocAuth(UserLogoutInputBean input) {
		return false;
	}
}

