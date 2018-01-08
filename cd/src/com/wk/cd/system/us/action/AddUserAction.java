/**
 * Title: AddUserAction.java
 * File Description:新增用户 
 * @copyright: 2014
 * @company: CORSWORK
 * @author: link
 * @version: 1.0
 * @date: 2014-12-3
 */
package com.wk.cd.system.us.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.common.util.CfgTool;
import com.wk.cd.common.util.DESUtil;
import com.wk.cd.enu.RCD_STATE;
import com.wk.cd.exc.DataErrorException;
import com.wk.cd.exc.InputParamIsNullException;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.dp.dao.DpDeptDaoService;
import com.wk.cd.system.dp.info.DpDeptInfo;
import com.wk.cd.system.exc.CanNotAddTellerException;
import com.wk.cd.system.exc.DprlAndUserWeightNotMatchException;
import com.wk.cd.system.exc.ValueBeyondLimitException;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.system.us.bean.AddUserInputBean;
import com.wk.cd.system.us.bean.AddUserOutputBean;
import com.wk.cd.system.us.dao.UsUserDaoService;
import com.wk.cd.system.us.dao.UsUserRoleDaoService;
import com.wk.cd.system.us.info.UsUserInfo;
import com.wk.cd.system.us.info.UsUserRoleInfo;
import com.wk.cd.system.us.service.UsCheckUserService;
import com.wk.lang.Inject;
import com.wk.util.JaDate;

/**
 * Class Description: 新增用户
 * @author link
 */
public class AddUserAction
		extends ActionBasic<AddUserInputBean, AddUserOutputBean> {

	@Inject
	private UsUserDaoService daoService;
	@Inject
	private UsUserRoleDaoService addDprlService;
	@Inject
	private DpDeptDaoService deptDaoService;
	@Inject
	private UsCheckUserService usCheckUserService;
	@Inject
	private ActionLogPublicService logsvc;
	

	/**
	 * Description: 新增用户
	 * @param input
	 * @return
	 */
	@Override
	protected AddUserOutputBean doAction(AddUserInputBean input) {
		Assert.assertNotEmpty(input.getUser_type(), AddUserInputBean.USER_TYPECN);
		AddUserOutputBean output = new AddUserOutputBean();
		UsUserInfo info = new UsUserInfo();
		Assert.assertNotEmpty(input.getUser_id(), AddUserInputBean.USER_IDCN);
		Assert.assertNotEmpty(input.getUser_type(),
				AddUserInputBean.USER_TYPECN);
		Assert.assertNotEmpty(input.getBl_dept_id(),
				AddUserInputBean.BL_DEPT_IDCN);
		Assert.assertNotEmpty(input.getDprl_list(),
				AddUserInputBean.DPRL_LISTCN);
		Assert.assertNotEmpty(input.getUser_weight_list(),
				AddUserInputBean.USER_WEIGHT_LISTCN);
		daoService.checkNotExistUserIdExist(input.getUser_id());
		String[] dprl_list = input.getDprl_list();
		int[] user_weight_list = input.getUser_weight_list();
		int len = 0;
		if (dprl_list.length != user_weight_list.length) {
			throw new DprlAndUserWeightNotMatchException();
		} else {
			len = dprl_list.length;
		}
		for (int i = 0; i < len; i++) {
			if (Assert.isEmpty(dprl_list[i])) {
				throw new InputParamIsNullException().addScene("PARM",
						"用户部门角色列表");
			}
			if (user_weight_list[i] < 1 || user_weight_list[i] > 100) {
				throw new ValueBeyondLimitException().addScene("PARM1", "用户权重")
						.addScene("PARM2", "1-100");
			}
			addDprlService.checkDprlWeghtExsit(input.getUser_id(), dprl_list[i],
					user_weight_list[i]);
		}

		info.setUser_id(input.getUser_id());
		String passwd = CfgTool.getProjectPropterty("cms.reset.pwd");
		String en_passwd = DESUtil.docrypt(null, passwd);
		info.setUser_passwd(en_passwd);
		//获取密码到期日
		JaDate pwd_date = input.getPwdexp_bk_date();
		JaDate db_date = input.getDtbs_bk_date();
		int days = Integer.valueOf(CfgTool
				.getProjectPropterty("cms.max.pwdexp.date"));
		if (Assert.isEmpty(pwd_date)) {
			pwd_date = input.getDtbs_bk_date().addDay(days);
		} else if (db_date.isAfter(pwd_date)
				|| pwd_date.isAfter(db_date.addDay(days))) {
			throw new DataErrorException().addScene("INPUT",
					AddUserInputBean.PWDEXP_BK_DATECN);
		}
		
		if(usCheckUserService.isDprlsManager(dprl_list)){
			info.setLogin_bk_num(-1);
		}else{
			info.setLogin_bk_num(0);
		}
		
		info.setPwdexp_bk_date(pwd_date);
		info.setUser_cn_name(input.getUser_cn_name());
		info.setEmail_add(input.getEmail_add());
		info.setPhone_no(input.getPhone_no());
		info.setBl_dept_id(input.getBl_dept_id());
		info.setUser_type(input.getUser_type());
		info.setTeller_no(input.getTeller_no());
		info.setFirst_dept_id(input.getFirst_dept_id());
		info.setSecd_dept_id(input.getSecd_dept_id());
		info.setThird_dept_id(input.getThird_dept_id());
		info.setCrt_bk_date(input.getDtbs_bk_date());
		info.setCrt_bk_time(input.getDtbs_bk_time());
		info.setCrt_user_id(input.getOrg_user_id());
		info.setRcd_state(RCD_STATE.NORMAL);
		daoService.insertInfo(info);
	
		//查询所有部门的机构号
		List<String> dept_list = new ArrayList<String>();
		DpDeptInfo bl_dept_info = deptDaoService.getDeptInfo(input.getBl_dept_id());
		String bl_dept_id_sbno = bl_dept_info.getBranch_no();
		String first_dept_id_sbno="";
		DpDeptInfo first_dept_info=null;
		if(!Assert.isEmpty(input.getFirst_dept_id())){
			first_dept_info = deptDaoService.getDeptInfo(input.getFirst_dept_id());
			first_dept_id_sbno = first_dept_info.getBranch_no();
		}
		if(Assert.isEmpty(bl_dept_id_sbno) && Assert.isEmpty(first_dept_id_sbno) && !Assert.isEmpty(input.getTeller_no())){
			if(Assert.isEmpty(first_dept_info)){
				throw new CanNotAddTellerException().addScene("PARM", bl_dept_info.getDept_cn_name() +"["+ input.getBl_dept_id()+"]");
			}else{
				throw new CanNotAddTellerException().addScene("PARM", bl_dept_info.getDept_cn_name() +"["+ input.getBl_dept_id()+"]" + " "
						+ first_dept_info.getDept_cn_name() +"["+ input.getFirst_dept_id()+"]");
			}
		}
		dept_list.add(bl_dept_id_sbno);
		if(!Assert.isEmpty(input.getFirst_dept_id())){
			dept_list.add(first_dept_id_sbno);
		}
		for (int i = 0; i < dprl_list.length && dprl_list[i] != null; i++) {
			UsUserRoleInfo dprl_info = new UsUserRoleInfo();
			dprl_info.setDprl_code(dprl_list[i]);
			dprl_info.setUser_id(input.getUser_id());
			dprl_info.setUser_bk_weight(user_weight_list[i]);
			addDprlService.insertInfo(dprl_info);
		}
		return output;
	}

	/**
	 * Description: 新增用户功能日志信息
	 * @param input
	 * @return
	 */
	@Override
	protected String getLogTxt(AddUserInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add(input.getUser_id());
		lst_val.add(input.getUser_type().getCname());
		return logsvc.getLogTxt("添加用户信息", lst_val);
	}

}