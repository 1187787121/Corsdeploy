/**
 * Title: UpdatePwdAction.java
 * File Description: 用户密码更改
 * @copyright: 2015
 * @company: CORSWORK
 * @author: link
 * @version: 1.0
 * @date: 2015-1-26
 */
package com.wk.cd.system.us.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.CfgTool;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.dp.service.DpPublicService;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.system.us.bean.UpdatePwdInputBean;
import com.wk.cd.system.us.bean.UpdatePwdOutputBean;
import com.wk.cd.system.us.service.UsPasswdService;
import com.wk.lang.Inject;

/**
 * Class Description:用户密码更改
 * @author link
 */
public class UpdatePwdAction
		extends ActionBasic<UpdatePwdInputBean, UpdatePwdOutputBean> {

	@Inject
	private UsPasswdService pwdService;
	@Inject
	private DpPublicService dpsvc;
	@Inject
	private ActionLogPublicService lgsvc;

	/**
	 * Description: 用户密码更改
	 * @param input
	 * @return
	 */
	@Override
	protected UpdatePwdOutputBean doAction(UpdatePwdInputBean input) {
		UpdatePwdOutputBean output = new UpdatePwdOutputBean();
		Assert.assertNotEmpty(input.getOrg_user_id(),
				UpdatePwdInputBean.ORG_USER_IDCN);
		Assert.assertNotEmpty(input.getUser_passwd(),
				UpdatePwdInputBean.USER_PASSWDCN);
		Assert.assertNotEmpty(input.getNew_user_passwd(),
				UpdatePwdInputBean.NEW_USER_PASSWDCN);
		Assert.assertNotEmpty(input.getNew_sec_passwd(),
				UpdatePwdInputBean.NEW_SEC_PASSWDCN);
		int pwdexp_date = Integer.valueOf(CfgTool
				.getProjectPropterty("cms.default.chg.pwdexp.date"));
		pwdService.updateUserPasswd(input.getOrg_user_id(), input
				.getUser_passwd(), input.getNew_user_passwd(), input
				.getNew_sec_passwd(), input.getDtbs_bk_date().addDay(
				pwdexp_date));
		return output;
	}

	@Override
	protected void chkInput(UpdatePwdInputBean input) {
		Assert.assertNotEmpty(input.getOrg_user_id(), UpdatePwdInputBean.ORG_USER_IDCN);
		Assert.assertNotEmpty(input.getOrg_srv_name(), UpdatePwdInputBean.ORG_SRV_NAMECN);
		Assert.assertNotEmpty(input.getOrg_rs_code(), UpdatePwdInputBean.ORG_RS_CODECN);
		Assert.assertNotEmpty(input.getSubmit_type(), UpdatePwdInputBean.SUBMIT_TYPECN);
	}

	@Override
	protected void chkTerm(UpdatePwdInputBean input) {
	}

	@Override
	protected void chkSQLPriv(UpdatePwdInputBean input) {
	}

	@Override
	protected void chkDeptPriv(UpdatePwdInputBean input) {
	}

	@Override
	protected void chkState(UpdatePwdInputBean input){
	}

	@Override
	protected boolean isChk(UpdatePwdInputBean input){
	    return false;
	}

	@Override
	protected boolean isAuth(UpdatePwdInputBean input){
	    return false;
	}
	
	@Override
	protected boolean isLocAuth(UpdatePwdInputBean input) {
		return false;
	}

	/**
	 * Description: 用户密码更改日志信息
	 * @param input
	 * @return
	 */
	@Override
	protected String getLogTxt(UpdatePwdInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add(input.getOrg_user_id());
		return lgsvc.getLogTxt("用户密码更改日志信息", lst_val);
	}

}
