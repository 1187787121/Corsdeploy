/**
 * Title: CheckPendWorkAction.java
 * File Description:任务复核 
 * @copyright: 2014
 * @company: CORSWORK
 * @author: tlw
 * @version: 1.0
 * @date: 2014-12-8
 */
package com.wk.cd.work.wk.action;

import java.util.ArrayList;
import java.util.List;

import com.wk.cd.common.util.Assert;
import com.wk.cd.enu.APROV_CATEGORY;
import com.wk.cd.enu.AUTH_TYPE;
import com.wk.cd.enu.DEAL_RES;
import com.wk.cd.enu.DEAL_TYPE;
import com.wk.cd.enu.WORKFLOW_STATE;
import com.wk.cd.service.ActionBasic;
import com.wk.cd.system.ap.bean.AuthDprlCodeBean;
import com.wk.cd.system.ap.bean.ChkDprlCodeBean;
import com.wk.cd.system.ap.service.ApPublicService;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.system.us.info.UsUserRoleInfo;
import com.wk.cd.system.us.service.UsGetUserInfoService;
import com.wk.cd.work.exc.ApproveDeptRoleLackForSrvException;
import com.wk.cd.work.exc.UserCanNotApproveException;
import com.wk.cd.work.exc.WorkHasBeanProcessedException;
import com.wk.cd.work.exc.WorkStateAbnormalException;
import com.wk.cd.work.wk.bean.CheckPendWorkInputBean;
import com.wk.cd.work.wk.bean.CheckPendWorkOutputBean;
import com.wk.cd.work.wk.dao.WkDealDetailDaoService;
import com.wk.cd.work.wk.dao.WkDealStateDaoService;
import com.wk.cd.work.wk.info.WkDealDetailInfo;
import com.wk.cd.work.wk.info.WkDealStateInfo;
import com.wk.cd.work.wk.service.WorkPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description:任务复核
 * 
 * @author tlw
 */
public class CheckPendWorkAction extends
		ActionBasic<CheckPendWorkInputBean, CheckPendWorkOutputBean> {
	@Inject
	private WorkPublicService wkPubSrv;
	@Inject
	private ActionLogPublicService lgsrv;
	@Inject
	private WkDealStateDaoService stDaos;
	@Inject
	private WkDealDetailDaoService wkDetaiDaos;
	@Inject
	private UsGetUserInfoService usGetSrv;
	@Inject
	private ApPublicService apsrv;
	private static final Log logger = LogFactory.getLog();

	/**
	 * 任务复核
	 * 
	 * @param input
	 *            复核信息
	 * @return
	 */
	@Override
	protected CheckPendWorkOutputBean doAction(CheckPendWorkInputBean input) {
		CheckPendWorkOutputBean output = new CheckPendWorkOutputBean();
		String pend_wk_seq = input.getPend_wk_seq();
		DEAL_RES deal_res = input.getDeal_res();
		String user_id = input.getOrg_user_id();
		logger.info("待复核任务流水号  = [{}]", pend_wk_seq);
		// 非空检查
		Assert
				.assertNotEmpty(pend_wk_seq,
						CheckPendWorkInputBean.PEND_WK_SEQCN);
		Assert.assertNotEmpty(deal_res, CheckPendWorkInputBean.DEAL_RESCN);
		// 查询待处理流水对应任务状态信息
		WkDealStateInfo state_info = stDaos.getInfoByKeyForUpdate(pend_wk_seq);
		if (state_info.getWorkflow_state() != WORKFLOW_STATE.RECHECK) {
			throw new WorkHasBeanProcessedException().addScene("WORK_SEQ",
					pend_wk_seq);
		}

		isCheck(state_info, user_id); // 检查是否允许复核
		String pend_srv_name = state_info.getPend_srv_name();
		int check_seq = state_info.getPend_deal_seq();
		// 复核处理
		if (deal_res == DEAL_RES.REFUSE) {
			wkPubSrv.updateWorkStateByWorkSeq(pend_wk_seq, check_seq,
					state_info.getCrt_user_id(),state_info.getCrt_user_cn_name() ,WORKFLOW_STATE.CHECK_REFUSE);
		} else {
			updateWorkState(state_info.getCrt_dept_id(), pend_wk_seq,
					pend_srv_name, check_seq, user_id, state_info.getCrt_user_id(),state_info.getCrt_user_cn_name()); // 更新任务状态表
		}
		// 明细表中登记相应记录
		int count = wkDetaiDaos.countWorkDetailByWorkSeq(pend_wk_seq);
		WkDealDetailInfo detail_info = new WkDealDetailInfo();
		detail_info.setPend_work_seq(pend_wk_seq);
		detail_info.setDeal_seq(count + 1);
		detail_info.setDeal_type(DEAL_TYPE.RECHECK);
		detail_info.setDeal_res(deal_res);
		detail_info.setDeal_user_id(user_id);
		detail_info.setDeal_bk_date(input.getDtbs_bk_date());
		detail_info.setDeal_bk_time(input.getDtbs_bk_time());
		detail_info.setDeal_bk_desc(input.getDeal_bk_desc());
		wkDetaiDaos.insertInfo(detail_info);
		return output;
	}

	/**
	 * 生成日志信息
	 * 
	 * @param input
	 *            复核信息
	 * @return 日志信息
	 */
	@Override
	protected String getLogTxt(CheckPendWorkInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add(input.getPend_wk_seq());
		lst_val.add(input.getDeal_res().getCname());
		lst_val.add(input.getDeal_bk_desc());
		return lgsrv.getLogTxt("任务复核", lst_val);
	}

	/**
	 * 检查用户是否有权限做复核和任务状态是否为提交待复核状态
	 * 
	 * @param state_info
	 *            任务状态信息
	 * @param user_id
	 *            复核用户名
	 */
	private void isCheck(WkDealStateInfo state_info, String user_id) {
		// 检查用户是否可以做复核
		if (state_info.getCrt_user_id().equals(user_id)
				|| !state_info.getPend_user_id().equals(user_id)) {
			throw new UserCanNotApproveException().addScene("USER", user_id)
					.addScene("OPT", state_info.getPend_srv_name());
		}
		// 检查任务状态表状态是否为待复核
		if (state_info.getWorkflow_state() != WORKFLOW_STATE.RECHECK) {
			throw new WorkStateAbnormalException().addScene("PARM1",
					WORKFLOW_STATE.RECHECK.getCname());
		}
	}

	/**
	 * 复核通过后更新任务状态表
	 * 
	 * @param dept_id
	 *            部门编码
	 * @param pend_work_seq
	 *            待处理流水
	 * @param pend_srv_name
	 *            待处理服务名称
	 * @param check_seq
	 *            待复核序号
	 * @param user_id
	 *            复核发起交易的用户
	 * @param crt_user_id
	 *            发起交易的用户
	 */
	private void updateWorkState(String dept_id, String pend_work_seq,
			String pend_srv_name, int cur_check_seq, String user_id, String crt_user_id,String crt_user_cn_name) {
		// 复核通过后检查是否需要继续复核
		String dprl_code = "";
		List<ChkDprlCodeBean> check_dprl_list = apsrv.queryCheckBySrvName(
				dept_id, pend_srv_name);
		if (!Assert.isEmpty(check_dprl_list)) {
			//查询所有已经做过复核的用户信息，如果做过复核则不能再次做复核
			List<String> deal_user_ids = wkDetaiDaos.queryDealUserByWorkSeq(pend_work_seq, DEAL_TYPE.RECHECK);
			deal_user_ids.add(crt_user_id);
			deal_user_ids.add(user_id);
			// 获取复核序号最小的记录
			// min_seq:最小复核序号，
			// item：最小复核序号对应check_dprl_list的第几条，check_count:部门角色已经复核的次数
			int min_seq = -1, item = 0, check_len = check_dprl_list.size();
			for (int i = 0; i < check_len; i++) {
				int check_seq = check_dprl_list.get(i).getCheck_seq();
				if (min_seq == -1 && cur_check_seq < check_seq) {
					min_seq = check_seq;
					item = i;
				}
				if (min_seq != -1 && cur_check_seq < check_seq) {
					if (check_seq < min_seq) {
						min_seq = check_seq;
						item = i;
					}
				}
			}
			if (min_seq > -1) { // 获取下一个复核用户
				ChkDprlCodeBean bean = check_dprl_list.get(item);

				// 计算同一个角色或者部门角色复核的次数
//				String check_dprl_code = bean.getChk_dprl_code();
//				for (ChkDprlCodeBean b : check_dprl_list) {
//					if (b.getCheck_seq() <= cur_check_seq
//							&& check_dprl_code.equals(b.getChk_dprl_code())) {
//						check_count++;
//					}
//				}

				// 查询复核的部门角色编码
				if (bean.getChk_aprov_category() == APROV_CATEGORY.ROLE) {
					dprl_code = usGetSrv.queryDprlByDeptAndRole(dept_id, bean
							.getChk_dprl_code());
				} else {
					dprl_code = bean.getChk_dprl_code();
				}

				// 查询按照权重排序后的用户和部门角色信息
				List<UsUserRoleInfo> user_role_list = usGetSrv
						.queryUserByDprlCode(dprl_code, deal_user_ids);
				if (Assert.isEmpty(user_role_list)) {
					throw new ApproveDeptRoleLackForSrvException().addScene(
							"OPT", pend_srv_name);
				}
				// 修改任务状态为“待复核”
				wkPubSrv.updateWorkStateByWorkSeq(pend_work_seq, bean
						.getCheck_seq(), user_role_list.get(0)
						.getUser_id(),usGetSrv.getUserInfoByUserId(user_role_list.get(0).getUser_id()).getUser_cn_name(), WORKFLOW_STATE.RECHECK);

			} else { // 获取授权信息
				List<AuthDprlCodeBean> auth_dprl_list = apsrv
						.queryAuthBySrvName(dept_id, pend_srv_name);
				if (!Assert.isEmpty(auth_dprl_list)) {
					AuthDprlCodeBean bean = new AuthDprlCodeBean();
					// 获取远程授权序号最小的记录
					min_seq = -1;
					item = -1;
					int auth_len = auth_dprl_list.size();
					for (int i = 0; i < auth_len; i++) {
						if (auth_dprl_list.get(i).getAuth_type() == AUTH_TYPE.REMOTE) {
							if (min_seq == -1) {
								min_seq = auth_dprl_list.get(i).getAuth_seq();
								item = i;
							}
							if (i > 0) {
								int auth_seq = auth_dprl_list.get(i)
										.getAuth_seq();
								if (auth_seq < min_seq) {
									min_seq = auth_seq;
									item = i;
								}
							}
						}
					}
					if (item > -1) { // 获取第一个授权用户信息
						bean = auth_dprl_list.get(item);
						// 查询部门角色编码
						if (bean.getAuth_aprov_category() == APROV_CATEGORY.ROLE) {
							dprl_code = usGetSrv.queryDprlByDeptAndRole(
									dept_id, bean.getAuth_dprl_code());
						} else {
							dprl_code = bean.getAuth_dprl_code();
						}
						// 查询部门角色对应权重值最小的用户
						UsUserRoleInfo user_role = usGetSrv
								.queryUserRoleByDprlAndMinWeght(dprl_code,
										deal_user_ids);
						// 未找到用户信息报错（防止只有一个用户担任一个部门角色，但是提交用户有时自己时，自己给自己做授权）
						if (Assert.isEmpty(user_role)) {
							throw new ApproveDeptRoleLackForSrvException()
									.addScene("OPT", pend_srv_name);
						}
						// 修改任务状态为“待授权”
						wkPubSrv.updateWorkStateByWorkSeq(pend_work_seq, bean
								.getAuth_seq(), user_role.getUser_id(),usGetSrv.getUserInfoByUserId(user_role.getUser_id()).getUser_cn_name(),
								WORKFLOW_STATE.APPROVAL);

					} else { // 任务不需要授权，直接修改任务状态为“待提交”
						wkPubSrv.updateWorkStateByWorkSeq(pend_work_seq, 0, crt_user_id,crt_user_cn_name,
								WORKFLOW_STATE.EXECUTORY);
					}
				} else { // 任务不需要授权，直接修改任务状态为“待提交”
					wkPubSrv.updateWorkStateByWorkSeq(pend_work_seq, 0, crt_user_id,crt_user_cn_name,
							WORKFLOW_STATE.EXECUTORY);
				}
			}
		}
	}
}
