/**
 * Title: UserLoginInAction.java
 * File Description: 用户登录
 * @copyright: 2014
 * @company: CORSWORK
 * @author: link
 * @version: 1.0
 * @date: 2014-12-26
 */
package com.wk.cd.system.us.action;

import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.dp.info.DpDeptInfo;
import com.wk.cd.system.dp.service.DpPublicService;
import com.wk.cd.system.exc.PasswordInitialException;
import com.wk.cd.system.exc.PasswordOutOfDateException;
import com.wk.cd.system.exc.UserTermNoExistException;
import com.wk.cd.system.sv.service.SvSrvService;
import com.wk.cd.system.us.bean.UserLoginInInputBean;
import com.wk.cd.system.us.bean.UserLoginInOutputBean;
import com.wk.cd.system.us.dao.UsUserColPrivDaoService;
import com.wk.cd.system.us.dao.UsUserDaoService;
import com.wk.cd.system.us.dao.UsUserRsPrivDaoService;
import com.wk.cd.system.us.dao.UsUserSocPrivDaoService;
import com.wk.cd.system.us.dao.UsUserSqlPrivDaoService;
import com.wk.cd.system.us.info.UsUserInfo;
import com.wk.cd.system.us.service.UsCheckUserService;
import com.wk.cd.system.us.service.UsPasswdService;
import com.wk.cd.system.us.service.UsUserGetRoleDeptService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;
import com.wk.util.JaDate;
import com.wk.util.JaDateTime;
import com.wk.util.JaTime;

/**
 * Class Description: 用户登录
 * 
 * @author link
 */
public class UserLoginInAction extends
		ActionBasic<UserLoginInInputBean, UserLoginInOutputBean> {
	@Inject
	private UsCheckUserService userService;
	@Inject
	private DpPublicService dpService;
	@Inject
	private UsPasswdService pwdService;
	@Inject
	private UsUserGetRoleDeptService ussvc;
	@Inject
	private SvSrvService svsvc;
	@Inject 
	private UsUserRsPrivDaoService rsTempPrivDaoService;
	@Inject 
	private UsUserSocPrivDaoService socTempPrivDaoService;
	@Inject 
	private UsUserSqlPrivDaoService sqlTempPrivDaoService;
	@Inject 
	private UsUserColPrivDaoService colTempPrivDaoService;
	@Inject
	private UsUserDaoService usUserDaoService;
	

	private static final Log logger = LogFactory.getLog();

	/**
	 * Description: 用户登录
	 * 
	 * @param input
	 * @return
	 */
	@Override
	protected UserLoginInOutputBean doAction(UserLoginInInputBean input) {
		UserLoginInOutputBean output = new UserLoginInOutputBean();
		logger.info("************UserLoginInAction*************");
		//校验用户名是否存在
		usUserDaoService.checkExistUserIdExist(input.getOrg_user_id());
		logger.plog("login : org_user_id[{}]", input.getOrg_user_id());
		logger.plog("login : org_term_no[{}]",input.getOrg_term_no());
		if(!userService.checkUserTermExist(input.getOrg_user_id(),input.getOrg_term_no())){
			throw new UserTermNoExistException().addScene("TERM_NO", input.getOrg_term_no());
		}
		logger.plog("checktm end");
		
		logger.plog("login start");
		UsUserInfo info = userService.userLoginIn(input.getOrg_user_id(), input
				.getUser_passwd());
		
		if(pwdService.isPwdOutOfDate(info, input.getDtbs_bk_date())) {
			throw new PasswordOutOfDateException();
		} else if (pwdService.isDefinePwd(input.getOrg_user_id())) {
			throw new PasswordInitialException();
		}else{//成功登陆
			if(info.getLogin_bk_num()<0){
				info.setLogin_bk_num(0);
			}
			info.setLogin_bk_num(info.getLogin_bk_num()+1);
			usUserDaoService.updateLoginNumByUserId(info.getLogin_bk_num(), input.getOrg_user_id());
		}
		logger.plog("login success");
		
		DpDeptInfo dpInfo=dpService.getInfoByKey(info.getBl_dept_id());
		output.setBl_dept_id(info.getBl_dept_id());
		output.setDept_cn_name(dpInfo.getDept_cn_name());
		
		List<String> dp_rl = ussvc.queryDprlByUserId(input.getOrg_user_id());
		logger.plog("queryDprlByUserId end");
		String rols = "";
		if (!Assert.isEmpty(dp_rl)) {
			int size = dp_rl.size();
			if (size > 1) {
				for (int i = 0; i < size; i++) {
					rols = rols + ":" + dp_rl.get(i);
				}
			} else {
				rols = dp_rl.get(0);
			}
		}
		output.setDprl_code(rols);
		
		List<String> bl_dr_list = ussvc.queryBlDrExplByUserId(input.getOrg_user_id(),info.getBl_dept_id());
		String bl_dprl_expls = "";
		if (!Assert.isEmpty(bl_dr_list)) {
			int size = bl_dr_list.size();
			if (size > 1) {
				for (int i = 0; i < size; i++) {
					bl_dprl_expls = bl_dprl_expls + ":" + bl_dr_list.get(i);
				}
			} else {
				bl_dprl_expls = bl_dr_list.get(0);
			}
		}
		output.setBl_dprl_expls(bl_dprl_expls);
		
		output.setFirst_dept_id(info.getFirst_dept_id());
		output.setLogin_bk_num(info.getLogin_bk_num());
		output.setUser_cn_name(info.getUser_cn_name());
		output.setUser_type(info.getUser_type());
		output.setSecd_dept_id(info.getSecd_dept_id());
		output.setThird_dept_id(info.getThird_dept_id());
		output.setTerm_no(input.getOrg_term_no());
		
		reduceTmpPriv(input);
		return output;
	}

	@Override
	protected String getLogTxt(UserLoginInInputBean input) {
		return "用户登录: " + input.getOrg_user_id();
	}

	@Override
	protected void chkInput(UserLoginInInputBean input) {
		Assert.assertNotEmpty(input.getOrg_user_id(),
				UserLoginInInputBean.ORG_USER_IDCN);
		Assert.assertNotEmpty(input.getUser_passwd(),
				UserLoginInInputBean.USER_PASSWDCN);
		Assert.assertNotEmpty(input.getSubmit_type(), UserLoginInInputBean.SUBMIT_TYPECN);
	}

	// 清除用户临时权限垃圾数据
	protected void reduceTmpPriv(UserLoginInInputBean input) {
		String user_id=input.getOrg_user_id();
		JaDate dt_date=input.getDtbs_bk_date();
		JaTime dt_time=input.getDtbs_bk_time();
		JaDateTime dt_datetime=JaDateTime.valueOf(dt_date.getYear(), dt_date.getMonth(), dt_date.getDay(), dt_time.getHour(), dt_time.getMinute(), dt_time.getSecond(), dt_time.getMillisecond());

		rsTempPrivDaoService.deleteRsTempPriv(user_id,dt_datetime);
		socTempPrivDaoService.deleteSocTempPriv(user_id,dt_datetime);
		sqlTempPrivDaoService.deleteSqlTempPriv(user_id,dt_datetime);
		colTempPrivDaoService.deleteColTempPriv(user_id,dt_datetime);
	}

	/**
	 * 登录不检查
	 * 
	 * @param input
	 */
	@Override
	protected void chkState(UserLoginInInputBean input) {
	}

	/**
	 * 登录不检查
	 * 
	 * @param input
	 */
	@Override
	protected void chkDeptPriv(UserLoginInInputBean input) {
	}

	/**
	 * 登录不检查
	 * 
	 * @param input
	 */
	@Override
	protected void chkUserPriv(UserLoginInInputBean input) {
	}

	/**
	 * 登录不需要加载数据源
	 * 
	 * @param input
	 */
	@Override
	protected void dataSourceLoad(UserLoginInInputBean input) {
	}

	/**
	 * 检查终端是否允许提交服务
	 * 
	 * @param input
	 */
	@Override
	protected void chkTerm(UserLoginInInputBean input) {
		if(Assert.isEmpty(input.getOrg_term_no())) {
			input.setOrg_term_no(input.getRemote_ip());
		}
	}

	@Override
	protected boolean isChk(UserLoginInInputBean input){
	    logger.info("-------login check---------------");
	    return false;
	}

	@Override
	protected boolean isAuth(UserLoginInInputBean input){
	    logger.info("-------login auth---------------");
	    return false;
	}

	@Override
	protected boolean isLocAuth(UserLoginInInputBean input){
		logger.info("-------login LocAuth---------------");
		return false;

	}

}
