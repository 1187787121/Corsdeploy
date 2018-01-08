/**
 * Title: AuthPendWorkAction.java
 * File Description: ������Ȩ
 * @copyright: 2014
 * @company: CORSWORK
 * @author: tlw
 * @version: 1.0
 * @date: 2014-12-9
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
import com.wk.cd.system.ap.service.ApPublicService;
import com.wk.cd.system.lg.service.ActionLogPublicService;
import com.wk.cd.system.us.info.UsUserRoleInfo;
import com.wk.cd.system.us.service.UsGetUserInfoService;
import com.wk.cd.work.exc.ApproveDeptRoleLackForSrvException;
import com.wk.cd.work.exc.AuthTypeErrorException;
import com.wk.cd.work.exc.UserCanNotApproveException;
import com.wk.cd.work.exc.WorkHasBeanProcessedException;
import com.wk.cd.work.exc.WorkStateAbnormalException;
import com.wk.cd.work.wk.bean.AuthPendWorkInputBean;
import com.wk.cd.work.wk.bean.AuthPendWorkOutputBean;
import com.wk.cd.work.wk.dao.WkDealDetailDaoService;
import com.wk.cd.work.wk.dao.WkDealStateDaoService;
import com.wk.cd.work.wk.info.WkDealDetailInfo;
import com.wk.cd.work.wk.info.WkDealStateInfo;
import com.wk.cd.work.wk.service.WorkPublicService;
import com.wk.lang.Inject;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;

/**
 * Class Description: ������Ȩ
 * 
 * @author tlw
 */
public class AuthPendWorkAction extends
		ActionBasic<AuthPendWorkInputBean, AuthPendWorkOutputBean> {
	@Inject
	private WorkPublicService wkPubSrv;
	@Inject
	private WkDealStateDaoService stDaos;
	@Inject
	private WkDealDetailDaoService wkDetaiDaos;
	@Inject
	private ActionLogPublicService lgsrv;
	@Inject
	private UsGetUserInfoService usGetSrv;
	@Inject
	private ApPublicService apsrv;
	private static final Log logger = LogFactory.getLog();

	/**
	 * ������Ȩ
	 * 
	 * @param input
	 *            ������Ϣ
	 * @return
	 */
	@Override
	protected AuthPendWorkOutputBean doAction(AuthPendWorkInputBean input) {
		AuthPendWorkOutputBean output = new AuthPendWorkOutputBean();
		String pend_work_seq = input.getPend_wk_seq();
		AUTH_TYPE auth_type = input.getAuth_type();
		DEAL_RES deal_res = input.getDeal_res();
		String user_id = input.getOrg_user_id();
		logger.info("pend_work_seq = [{}]", pend_work_seq);
		// �ǿռ��
		Assert.assertNotEmpty(pend_work_seq,
				AuthPendWorkInputBean.PEND_WK_SEQCN);
		Assert.assertNotEmpty(auth_type, AuthPendWorkInputBean.AUTH_TYPECN);
		Assert.assertNotEmpty(deal_res, AuthPendWorkInputBean.DEAL_RESCN);
		// ��ѯ����״̬��Ϣ
		WkDealStateInfo state_info = stDaos
				.getInfoByKeyForUpdate(pend_work_seq);
		if (state_info.getWorkflow_state() != WORKFLOW_STATE.APPROVAL) {
			throw new WorkHasBeanProcessedException().addScene("WORK_SEQ",
					pend_work_seq);
		}

		isAuth(state_info, user_id, auth_type); // ����Ƿ�������Ȩ

		String pend_srv_name = state_info.getPend_srv_name();
		int auth_seq = state_info.getPend_deal_seq();
		// ��Ȩ����
		if (deal_res == DEAL_RES.REFUSE) {
			wkPubSrv.updateWorkStateByWorkSeq(pend_work_seq, auth_seq,
					state_info.getCrt_user_id(),state_info.getCrt_user_cn_name(), WORKFLOW_STATE.APP_REFUSE);
		} else {
			updateWorkState(state_info.getCrt_dept_id(), pend_work_seq,
					pend_srv_name, auth_seq, user_id, state_info.getCrt_user_id() , state_info.getCrt_user_cn_name()); // ��������״̬��
		}
		// ��ϸ���еǼ���Ӧ��¼
		int count = wkDetaiDaos.countWorkDetailByWorkSeq(pend_work_seq);
		WkDealDetailInfo detail_info = new WkDealDetailInfo();
		detail_info.setPend_work_seq(pend_work_seq);
		detail_info.setDeal_seq(count + 1);
		detail_info.setDeal_type(DEAL_TYPE.AUTH);
		detail_info.setDeal_res(deal_res);
		detail_info.setDeal_user_id(user_id);
		detail_info.setDeal_bk_date(input.getDtbs_bk_date());
		detail_info.setDeal_bk_time(input.getDtbs_bk_time());
		detail_info.setDeal_bk_desc(input.getDeal_bk_desc());
		wkDetaiDaos.insertInfo(detail_info);
		return output;
	}

	/**
	 * ������־
	 * 
	 * @param input
	 *            ������Ϣ
	 * @return ��־��Ϣ
	 */
	@Override
	protected String getLogTxt(AuthPendWorkInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add("����Ȩ��ˮ" + input.getPend_wk_seq());
		return lgsrv.getLogTxt("������Ȩ", lst_val);
	}

	/**
	 * ����Ƿ�������Ȩ
	 * 
	 * @param state_info
	 *            ����״̬��Ϣ
	 * @param user_id
	 *            �����û�
	 */
	private void isAuth(WkDealStateInfo state_info, String user_id,
			AUTH_TYPE auth_type) {
		// ����û��Ƿ����������
		if (state_info.getCrt_user_id().equals(user_id)
				|| !state_info.getPend_user_id().equals(user_id)) {
			throw new UserCanNotApproveException().addScene("USER", user_id)
					.addScene("OPT", state_info.getPend_srv_name());
		}
		// ���������Ϣ�Ƿ��Ǵ���Ȩ״̬
		if (state_info.getWorkflow_state() != WORKFLOW_STATE.APPROVAL) {
			throw new WorkStateAbnormalException().addScene("PARM1",
					WORKFLOW_STATE.APPROVAL.getCname());
		}
		// �����Ȩ��ʽ�Ƿ���ȷ
		if (auth_type != AUTH_TYPE.REMOTE) {
			throw new AuthTypeErrorException().addScene("PARM1", auth_type
					.getCname());
		}
	}

	/**
	 * ��Ȩͨ�����������״̬��
	 * 
	 * @param dept_id
	 *            ���ű���
	 * @param pend_work_seq
	 *            ��������ˮ
	 * @param pend_srv_name
	 *            �������������
	 * @param auth_seq
	 *            ����Ȩ���
	 * @param user_id
	 *            �û���
	 * @param crt_user_id
	 *            ԭʼ���׷����û���
	 */
	private void updateWorkState(String dept_id, String pend_work_seq,
			String pend_srv_name, int cur_auth_seq, String user_id, String crt_user_id,String crt_user_cn_name) {
		// ��Ȩͨ�������Ƿ���Ҫ������Ȩ
		String dprl_code = "";
		List<AuthDprlCodeBean> auth_dprl_list = apsrv.queryAuthBySrvName(
				dept_id, pend_srv_name);
		if (!Assert.isEmpty(auth_dprl_list)) {
			//��ѯ�����Ѿ��������˺���Ȩ���û���Ϣ������������˻�����Ȩ��������Ȩ
			List<String> deal_user_ids = wkDetaiDaos.queryDealUserByWorkSeq(pend_work_seq, DEAL_TYPE.AUTH);
			deal_user_ids.addAll(wkDetaiDaos.queryDealUserByWorkSeq(pend_work_seq, DEAL_TYPE.RECHECK));
			// ��ȡ��Ȩ�����С�ļ�¼
			// min_seq:��С��Ȩ��ţ�
			// item����С��Ȩ��Ŷ�Ӧauth_dprl_list�ĵڼ�����auth_count:���Ž�ɫ��Ȩ�Ѿ���Ȩ�Ĵ���
			int min_seq = -1, item = 0, check_len = auth_dprl_list.size();
			for (int i = 0; i < check_len; i++) {
				int auth_seq = auth_dprl_list.get(i).getAuth_seq();
				if (min_seq == -1 && cur_auth_seq < auth_seq) {
					min_seq = auth_seq;
					item = i;
				}
				if (min_seq != -1 && cur_auth_seq < auth_seq) {
					if (auth_seq < min_seq) {
						min_seq = auth_seq;
						item = i;
					}
				}
			}
			if (min_seq > -1) { // ��ȡ��һ����Ȩ�û�
				AuthDprlCodeBean bean = auth_dprl_list.get(item);

//				// ����ͬһ����ɫ���߲��Ž�ɫ��Ȩ�Ĵ���
//				String auth_dprl_code = bean.getAuth_dprl_code();
//				for (AuthDprlCodeBean b : auth_dprl_list) {
//					if (b.getAuth_seq() <= cur_auth_seq
//							&& auth_dprl_code.equals(b.getAuth_dprl_code())) {
//						auth_count++;
//					}
//				}

				// ��ѯ��Ȩ�Ĳ��Ž�ɫ����
				if (bean.getAuth_aprov_category() == APROV_CATEGORY.ROLE) {
					dprl_code = usGetSrv.queryDprlByDeptAndRole(dept_id, bean
							.getAuth_dprl_code());
				} else {
					dprl_code = bean.getAuth_dprl_code();
				}

				// ����Ȩ���������û��Ͳ��Ž�ɫ��Ϣ
				deal_user_ids.add(user_id);
				deal_user_ids.add(crt_user_id);
				List<UsUserRoleInfo> user_role_list = usGetSrv
						.queryUserByDprlCode(dprl_code, deal_user_ids);
				if (Assert.isEmpty(user_role_list)) {
					throw new ApproveDeptRoleLackForSrvException().addScene(
							"OPT", pend_srv_name);
				}

				// �޸�����״̬Ϊ������Ȩ��
				wkPubSrv.updateWorkStateByWorkSeq(pend_work_seq, bean
						.getAuth_seq(), user_role_list.get(0)
						.getUser_id(),usGetSrv.getUserInfoByUserId(user_role_list.get(0).getUser_id()).getUser_cn_name(), WORKFLOW_STATE.APPROVAL);
			} else {
				wkPubSrv.updateWorkStateByWorkSeq(pend_work_seq, 0, crt_user_id,crt_user_cn_name,
						WORKFLOW_STATE.EXECUTORY);
			}
		}
	}
}
