/**
 * Title: UpdateSrvPrivAction.java
 * File Description:修改服务复核、授权流程
 * @copyright: 2014
 * @company: CORSWORK
 * @author: tlw
 * @version: 1.0
 * @date: 2014-11-28
 */
package com.wk.cd.system.ap.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.APROV_CATEGORY;
import com.wk.cd.enu.AUTH_FLAG;
import com.wk.cd.enu.CHECK_FLAG;
import com.wk.cd.enu.WORKFLOW_STATE;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.ap.bean.AuthDprlCodeBean;
import com.wk.cd.system.ap.bean.ChkDprlCodeBean;
import com.wk.cd.system.ap.bean.UpdateSrvPrivInputBean;
import com.wk.cd.system.ap.bean.UpdateSrvPrivOutputBean;
import com.wk.cd.system.ap.info.SvSrvAuthInfo;
import com.wk.cd.system.ap.info.SvSrvCheckInfo;
import com.wk.cd.system.ap.service.ApPublicService;
import com.wk.cd.system.exc.ServiceForbidApproveException;
import com.wk.cd.system.exc.ServiceUsedByPendWorkException;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.system.sv.info.SvSrvInfo;
import com.wk.cd.system.sv.service.SvSrvService;
import com.wk.cd.system.us.dao.UsRoleDaoService;
import com.wk.cd.system.us.service.UsCheckUserService;
import com.wk.cd.work.wk.service.WorkPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description:修改服务复核、授权流程
 * @author tlw
 */
public class UpdateSrvPrivAction
		extends ActionBasic<UpdateSrvPrivInputBean, UpdateSrvPrivOutputBean> {
	@Inject
	private SvSrvService ssSrv;
	@Inject
	private UsCheckUserService usSrv;
	@Inject
	private WorkPublicService wkPubSrv;
	@Inject
	private ActionLogPublicService lgsrv;
	@Inject
	private UsRoleDaoService urSrv;
	@Inject
	private ApPublicService apsrv;
	private static final Log logger = LogFactory.getLog();

	/**
	 * Description:根据服务名称修改服务相关信息
	 * @param UpdateSrvInputBean input 输入接口
	 * @return UpdateSrvOutputBean 输出接口
	 */
	@Override
	protected UpdateSrvPrivOutputBean doAction(UpdateSrvPrivInputBean input) {
		UpdateSrvPrivOutputBean output = new UpdateSrvPrivOutputBean();
		String srv_name = input.getSrv_name();
		logger.info("按照服务名称更新服务复核和授权信息：srv_name = [{}]", srv_name);
		List<ChkDprlCodeBean> srv_check_list = input.getSrv_check_list();
		List<AuthDprlCodeBean> srv_auth_list = input.getSrv_auth_list();
		String dept_id = input.getOrg_dept_id();
		// 如果审批信息列表非空，则要做非空验证
		Assert.assertNotEmpty(srv_name, UpdateSrvPrivInputBean.SRV_NAMECN);
		if (!Assert.isEmpty(srv_check_list)) {
			for (ChkDprlCodeBean chk : srv_check_list) {
				Assert.assertNotEmpty(chk.getChk_dprl_code(),
						ChkDprlCodeBean.CHK_DPRL_CODECN);
				Assert.assertNotEmpty(chk.getRole_cn_name(),
						ChkDprlCodeBean.ROLE_CN_NAMECN);
				Assert.assertNotEmpty(chk.getCheck_seq(),
						ChkDprlCodeBean.CHECK_SEQCN);
				Assert.assertNotEmpty(chk.getChk_aprov_category(),
						ChkDprlCodeBean.CHK_APROV_CATEGORYCN);
			}
		}
		if (!Assert.isEmpty(srv_auth_list)) {
			for (AuthDprlCodeBean lst : srv_auth_list) {
				Assert.assertNotEmpty(lst.getAuth_dprl_code(),
						AuthDprlCodeBean.AUTH_DPRL_CODECN);
				Assert.assertNotEmpty(lst.getRole_cn_name(),
						AuthDprlCodeBean.ROLE_CN_NAMECN);
				Assert.assertNotEmpty(lst.getAuth_aprov_category(),
						AuthDprlCodeBean.AUTH_APROV_CATEGORYCN);
				Assert.assertNotEmpty(lst.getAuth_seq(),
						AuthDprlCodeBean.AUTH_SEQCN);
				Assert.assertNotEmpty(lst.getAuth_type(),
						AuthDprlCodeBean.AUTH_TYPECN);
			}
		}
		// 如果在待处理任务中有用到该服务，并且待处理任务状态是待复核、待审批或者待执行状态，则不能修改该服务
		List<WORKFLOW_STATE> workflow_state_list = new ArrayList<WORKFLOW_STATE>();
		workflow_state_list.add(WORKFLOW_STATE.RECHECK);
		workflow_state_list.add(WORKFLOW_STATE.APPROVAL);
		workflow_state_list.add(WORKFLOW_STATE.EXECUTORY);
		List<String> pend_work_list = wkPubSrv.queryPendWorkSeqByWorkState(
				srv_name, workflow_state_list);
		if (!Assert.isEmpty(pend_work_list)) {
			throw new ServiceUsedByPendWorkException().addScene("PARM1",
					pend_work_list.get(0)+ "等");
		}
		// 检查服务表服务信息是否存在
		SvSrvInfo srv_info = new SvSrvInfo();
		srv_info.setSrv_name(srv_name);
		srv_info = ssSrv.queryNoDelSvSrvByName(srv_info);
		//服务禁止审批时报错
		if (srv_info.getCheck_flag() == CHECK_FLAG.FORBID
				&& srv_info.getAuth_flag() == AUTH_FLAG.FORBID) {
			throw new ServiceForbidApproveException().addScene("PARM", "["
					+ srv_info.getSrv_name() + ":" + srv_info.getSrv_bk_desc()
					+ "]");
		}
		// 更新复核表，同时删除所有下级机构的审批配置流程
		List<SvSrvCheckInfo> check_infos = new ArrayList<SvSrvCheckInfo>();
		if (!Assert.isEmpty(srv_check_list)) {
			logger.info("**************UPDATE SV_SRV_CHECK***************");
			int check_list_length = srv_check_list.size();
			for (int i = 0; i < check_list_length; i++) {
				SvSrvCheckInfo check_info = new SvSrvCheckInfo();
				
				check_info.setCheck_dept_id(dept_id);
				check_info.setSrv_name(srv_name);
				check_info.setCheck_seq(srv_check_list.get(i).getCheck_seq());
				String dprl_code = srv_check_list.get(i).getChk_dprl_code();
				APROV_CATEGORY chk_aprov_category = srv_check_list.get(i).getChk_aprov_category();
				if(chk_aprov_category == APROV_CATEGORY.DEPARTMENTROLE){
					usSrv.checkDprlExist(dprl_code); // 检查部门角色编码是否存在
				}else{
					urSrv.checkRoleExist(dprl_code);//检查角色编码是否存在
				}
				check_info.setChk_dprl_code(dprl_code);
				check_info.setChk_aprov_category(chk_aprov_category);
				check_info.setRole_cn_name(srv_check_list.get(i).getRole_cn_name());
				check_info.setSuper_flag(srv_check_list.get(i).getSuper_flag());
				check_infos.add(check_info);
				
			}
		}
		apsrv.updateSvSrvCheckByName(dept_id, srv_name, check_infos);
		// 更新授权表
		List<SvSrvAuthInfo> auth_infos = new ArrayList<SvSrvAuthInfo>();
		if (!Assert.isEmpty(srv_auth_list)) {
			logger.info("**************UPDATE SV_SRV_AUTH***************");
			int auth_list_length = srv_auth_list.size();
			for (int i = 0; i < auth_list_length; i++) {
				SvSrvAuthInfo auth_info = new SvSrvAuthInfo();
				
				auth_info.setAuth_dept_id(dept_id);
				auth_info.setSrv_name(srv_name);
				auth_info.setAuth_seq(srv_auth_list.get(i).getAuth_seq());
				auth_info.setAuth_type(srv_auth_list.get(i).getAuth_type());
				String dprl_code = srv_auth_list.get(i).getAuth_dprl_code();
				APROV_CATEGORY auth_aprov_category = srv_auth_list.get(i).getAuth_aprov_category();
				if(auth_aprov_category == APROV_CATEGORY.DEPARTMENTROLE){
					usSrv.checkDprlExist(dprl_code); // 检查部门角色编码是否存在
				}else{
					urSrv.checkRoleExist(dprl_code);//检查角色编码是否存在
				}
				auth_info.setAuth_dprl_code(dprl_code);
				auth_info.setAuth_aprov_category(auth_aprov_category);
				auth_info.setRole_cn_name(srv_auth_list.get(i).getRole_cn_name());
				auth_info.setSuper_flag(srv_auth_list.get(i).getSuper_flag());
				auth_infos.add(auth_info);
			}
		}
		apsrv.updateSvSrvAuthByName(dept_id, srv_name, auth_infos);
		return output;
	}

	/**
	 * Description:写日志
	 * @param input 服务输入接口
	 * @return 日志信息
	 */
	@Override
	protected String getLogTxt(UpdateSrvPrivInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add("审批配置机构：" + input.getOrg_dept_id());
		lst_val.add("审批配置服务：" + input.getSrv_name());
		return lgsrv.getLogTxt("配置服务审批流程", lst_val);
	}

}
