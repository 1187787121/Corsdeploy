/**
 * Title: CheckLocalAuthAction.java
 * File Description: 检查本地授权权限
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015年6月26日
 */
package com.wk.cd.work.wk.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.APROV_CATEGORY;
import com.wk.cd.enu.SVDEAL_TYPE;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.ap.info.SvSrvAuthInfo;
import com.wk.cd.system.ap.service.ApPublicService;
import com.wk.cd.system.exc.UserIdOrPasswdErrorException;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.system.us.service.UsCheckUserService;
import com.wk.cd.system.us.service.UsGetUserInfoService;
import com.wk.cd.work.wk.bean.CheckLocalAuthInputBean;
import com.wk.cd.work.wk.bean.CheckLocalAuthOutputBean;
import com.wk.lang.Inject;

/**
 * @author HT
 */
public class CheckLocalAuthAction extends
		ActionBasic<CheckLocalAuthInputBean, CheckLocalAuthOutputBean> {
	@Inject
	private UsCheckUserService usCheckUserService;
	@Inject
	private UsCheckUserService ckussvc;
	@Inject
	private ApPublicService apsrv;
	@Inject
	private UsGetUserInfoService ususersrv;
	@Inject
	private ActionLogPublicService lgsrv;

	/**
	 * @param input
	 * @return
	 */
	@Override
	public CheckLocalAuthOutputBean doAction(CheckLocalAuthInputBean input) {
		CheckLocalAuthOutputBean output = new CheckLocalAuthOutputBean();
		String auth_user_id = input.getAuth_user_id();
		String user_passed = input.getUser_passwd();
		String srv_name = input.getPend_srv_name();

		int auth_seq = input.getAuth_seq();
		String auth_dprl_code = input.getAuth_dprl_code();

		Assert
				.assertNotEmpty(srv_name,
						CheckLocalAuthInputBean.PEND_SRV_NAMECN);
		Assert.assertNotEmpty(auth_dprl_code,
				CheckLocalAuthInputBean.AUTH_DPRL_CODECN);
		Assert.assertNotEmpty(auth_seq, CheckLocalAuthInputBean.AUTH_SEQCN);
		Assert.assertNotEmpty(auth_user_id,
				CheckLocalAuthInputBean.AUTH_USER_IDCN);
		Assert.assertNotEmpty(user_passed,
				CheckLocalAuthInputBean.USER_PASSWDCN);

		// TODO 本地授权用户名密码检验
		if (!usCheckUserService.checkDprlHasUser(auth_dprl_code, auth_user_id)) {
			throw new UserIdOrPasswdErrorException();
		}
		usCheckUserService.checkLocalAuthPasswd(auth_user_id, user_passed);

		SvSrvAuthInfo authInfo = apsrv.queryLocalAuthRole(input
				.getOrg_dept_id(), input.getPend_srv_name(), auth_seq);
		if (!Assert.isEmpty(authInfo)
				&& !Assert.isEmpty(authInfo.getAuth_seq())) {
			String dprl_code = authInfo.getAuth_dprl_code();
			if(authInfo.getAuth_aprov_category() == APROV_CATEGORY.ROLE){
				dprl_code = ususersrv.queryDprlByDeptAndRole(input.getOrg_dept_id(), dprl_code);
			}
			String dprl_bk_expl = ckussvc.queryExplByDprl(dprl_code);
			output.setPend_srv_name(input.getOrg_srv_name());
			output.setAuth_dprl_code(dprl_code);
			output.setAuth_seq(authInfo.getAuth_seq());
			output.setAuthdprl_bk_expl(dprl_bk_expl);
			output.setSvdeal_type(SVDEAL_TYPE.LOCAL);
		} else {
			output.setPend_work_seq(input.getPend_srv_name());
		}

		return output;
	}

	/**
	 * @param input
	 * @return
	 */
	@Override
	protected String getLogTxt(CheckLocalAuthInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add("授权序号：" + input.getAuth_seq());
		lst_val.add("授权部门角色编码 + " + input.getAuth_dprl_code());
		lst_val.add("授权用户名" + input.getAuth_user_id());
		return lgsrv.getLogTxt("本地授权", lst_val);
	}

}
