/**
 * Title: UpdPasswdService.java
 * File Description: �û������顢���ġ�����
 * @copyright: 2014
 * @company: CORSWORK
 * @author: link
 * @version: 1.0
 * @date: 2014-12-8
 */
package com.wk.cd.system.us.service;

import java.util.List;

import com.wk.cd.common.util.CfgTool;
import com.wk.cd.common.util.DESUtil;
import com.wk.cd.system.exc.PasswordEntredNotSameException;
import com.wk.cd.system.exc.PasswordErrorException;
import com.wk.cd.system.exc.UserIdOrPasswdErrorException;
import com.wk.cd.system.us.dao.UsUserCombineDaoService;
import com.wk.cd.system.us.dao.UsUserDaoService;
import com.wk.cd.system.us.info.UsUserInfo;
import com.wk.lang.Inject;
import com.wk.util.JaDate;

/**
 * Class Description: �û������顢���ġ�����
 * @author link
 */
public class UsPasswdService {
	@Inject
	private UsUserDaoService daoUserService;
	@Inject
	private UsUserCombineDaoService combineDaoService;

	/**
	 * Description: �û�������
	 * @param user_id
	 * @param user_passwd
	 */
	public void checkUserPassWd(String user_id, String user_passwd) {
		String en_passwd = new String();
		en_passwd = DESUtil.docrypt(null, user_passwd);
		daoUserService.checkUserPwd(user_id, en_passwd);
	}

	/**
	 * Description: �����û�����
	 * @param user_id
	 * @param user_passwd
	 * @param new_first_passwd
	 * @param new_sec_passwd
	 */
	public void updateUserPasswd(String user_id, String user_passwd,
			String new_first_passwd, String new_sec_passwd, JaDate pwdexp_date) {
		//���������벻ͬ����
		if (!new_first_passwd.equals(new_sec_passwd)) {
			throw new PasswordEntredNotSameException();
		}
		try {
			checkUserPassWd(user_id, user_passwd);
		} catch (UserIdOrPasswdErrorException e) {
			throw new PasswordErrorException();
		}
		//��������Ա����Ϣ����Ա���뵽��������Ϊ2099-12-31
		List<Integer> role_list = combineDaoService.queryRoleTypeByUserId(user_id);
		if(role_list.contains(1) || role_list.contains(2)){
			pwdexp_date = JaDate.valueOf(2099, 12, 31);
		}
		String en_pwd = DESUtil.docrypt("", new_first_passwd);
		daoUserService.updateUserPasswdByKey(en_pwd, pwdexp_date, user_id);
	}

	/**
	 * Description: �û���������
	 * @param user_id
	 */
	public void resetUserPasswd(String user_id, JaDate pwdexp_bk_date) {
		String def_pwd = new String();
		def_pwd = DESUtil.docrypt("", CfgTool
				.getProjectPropterty("cms.reset.pwd"));
		daoUserService.checkExistUserIdExist(user_id);
		daoUserService.updateUserPasswdByKey(def_pwd, pwdexp_bk_date, user_id);
	}

	/**
	 * Description: ����û������Ƿ����,���ڷ���true
	 * @param user_id
	 * @param current_date
	 */
	public boolean isPwdOutOfDate(UsUserInfo info, JaDate current_date) {
		if ((info.getPwdexp_bk_date().addDay(1)).isAfter(current_date)) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Description: ����û������Ƿ��ǳ�ʼ���룬�Ƿ���true
	 * @param user_id
	 */
	public boolean isDefinePwd(String user_id) {
		if (daoUserService.isDefinePwd(user_id)) {
			return true;
		} else {
			return false;
		}
	}

}
