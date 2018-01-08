/**
 * Title: UserLogoutAction.java
 * File Description:  �û��ǳ�������
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
 * Class Description:�û��ǳ�������
 * @author lixl
 */
public class UserLogoutAction extends ActionBasic<UserLogoutInputBean,UserLogoutOutputBean>{
	@Inject 
	private	UsCheckUserService ussvc; 

	/**
	 * �û��ǳ�����
	 * @param input ����ӿ�
	 * @return UserLogoutOutputBean 
	 */
	@Override
	protected UserLogoutOutputBean doAction(UserLogoutInputBean input){
		UserLogoutOutputBean output = new UserLogoutOutputBean();
		ussvc.userLoginOut(input.getOrg_user_id());

		return output;
	}

	/**
	 * ��ȡ��־
	 * @param input 
	 * @return String 
	 */
	@Override
	protected String getLogTxt(UserLogoutInputBean input){
		return "�û��ǳ�����";
	}

	//�����û���ʱȨ�޴���,����Ҫ
	protected void reduceTmpPrivNum(UserLogoutInputBean input){
	}

	/**
	 * �ǳ������
	 * @param input 
	 */
	@Override
	protected void chkState(UserLogoutInputBean input){
	}

	/**
	 * �ǳ������
	 * @param input 
	 */
	@Override
	protected void chkDeptPriv(UserLogoutInputBean input){
	}

	/**
	 * �ǳ������
	 * @param input 
	 */
	@Override
	protected void chkUserPriv(UserLogoutInputBean input){
	}

	/**
	 * �ǳ�����Ҫ��������Դ
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

