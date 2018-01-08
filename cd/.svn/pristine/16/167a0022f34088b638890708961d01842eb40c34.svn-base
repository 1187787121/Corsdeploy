/**
 * Title: ResetPwdAction.java
 * File Description: 重置密码
 * @copyright: 2014
 * @company: CORSWORK
 * @author: link
 * @version: 1.0
 * @date: 2014-12-26
 */
package com.wk.cd.system.us.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.system.us.bean.ResetPwdInputBean;
import com.wk.cd.system.us.bean.ResetPwdOutputBean;
import com.wk.cd.system.us.service.UsPasswdService;
import com.wk.lang.Inject;

/**
 * Class Description:重置密码
 * @author link
 */
public class ResetPwdAction
		extends ActionBasic<ResetPwdInputBean, ResetPwdOutputBean> {

	@Inject
	private UsPasswdService pwdService;
	@Inject
	private ActionLogPublicService lgsvc;

	/**
	 * Description: 重置密码
	 * @param input
	 * @return
	 */
	@Override
	protected ResetPwdOutputBean doAction(ResetPwdInputBean input) {
		ResetPwdOutputBean output = new ResetPwdOutputBean();
		Assert.assertNotEmpty(input.getUser_id(), ResetPwdInputBean.USER_IDCN);
		pwdService.resetUserPasswd(input.getUser_id(), input.getDtbs_bk_date().addDay(1));
		return output;
	}

	/**
	 * Description: 重置密码日志信息
	 * @param input
	 * @return
	 */
	@Override
	protected String getLogTxt(ResetPwdInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add(input.getUser_id());
		return lgsvc.getLogTxt("密码重置", lst_val);
	}

}
