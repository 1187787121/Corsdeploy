/**
 * Title: CheckPendWorkAction.java
 * File Description:���񸴺� 
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
 * Class Description:���񸴺�
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
	 * ���񸴺�
	 * 
	 * @param input
	 *            ������Ϣ
	 * @return
	 */
	@Override
	protected CheckPendWorkOutputBean doAction(CheckPendWorkInputBean input) {
		CheckPendWorkOutputBean output = new CheckPendWorkOutputBean();
		String pend_wk_seq = input.getPend_wk_seq();
		DEAL_RES deal_res = input.getDeal_res();
		String user_id = input.getOrg_user_id();
		logger.info("������������ˮ��  = [{}]", pend_wk_seq);
		// �ǿռ��
		Assert
				.assertNotEmpty(pend_wk_seq,
						CheckPendWorkInputBean.PEND_WK_SEQCN);
		Assert.assertNotEmpty(deal_res, CheckPendWorkInputBean.DEAL_RESCN);
		// ��ѯ��������ˮ��Ӧ����״̬��Ϣ
		WkDealStateInfo state_info = stDaos.getInfoByKeyForUpdate(pend_wk_seq);
		if (state_info.getWorkflow_state() != WORKFLOW_STATE.RECHECK) {
			throw new WorkHasBeanProcessedException().addScene("WORK_SEQ",
					pend_wk_seq);
		}

		isCheck(state_info, user_id); // ����Ƿ�������
		String pend_srv_name = state_info.getPend_srv_name();
		int check_seq = state_info.getPend_deal_seq();
		// ���˴���
		if (deal_res == DEAL_RES.REFUSE) {
			wkPubSrv.updateWorkStateByWorkSeq(pend_wk_seq, check_seq,
					state_info.getCrt_user_id(),state_info.getCrt_user_cn_name() ,WORKFLOW_STATE.CHECK_REFUSE);
		} else {
			updateWorkState(state_info.getCrt_dept_id(), pend_wk_seq,
					pend_srv_name, check_seq, user_id, state_info.getCrt_user_id(),state_info.getCrt_user_cn_name()); // ��������״̬��
		}
		// ��ϸ���еǼ���Ӧ��¼
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
	 * ������־��Ϣ
	 * 
	 * @param input
	 *            ������Ϣ
	 * @return ��־��Ϣ
	 */
	@Override
	protected String getLogTxt(CheckPendWorkInputBean input) {
		List<String> lst_val = new ArrayList<String>();
		lst_val.add(input.getPend_wk_seq());
		lst_val.add(input.getDeal_res().getCname());
		lst_val.add(input.getDeal_bk_desc());
		return lgsrv.getLogTxt("���񸴺�", lst_val);
	}

	/**
	 * ����û��Ƿ���Ȩ�������˺�����״̬�Ƿ�Ϊ�ύ������״̬
	 * 
	 * @param state_info
	 *            ����״̬��Ϣ
	 * @param user_id
	 *            �����û���
	 */
	private void isCheck(WkDealStateInfo state_info, String user_id) {
		// ����û��Ƿ����������
		if (state_info.getCrt_user_id().equals(user_id)
				|| !state_info.getPend_user_id().equals(user_id)) {
			throw new UserCanNotApproveException().addScene("USER", user_id)
					.addScene("OPT", state_info.getPend_srv_name());
		}
		// �������״̬��״̬�Ƿ�Ϊ������
		if (state_info.getWorkflow_state() != WORKFLOW_STATE.RECHECK) {
			throw new WorkStateAbnormalException().addScene("PARM1",
					WORKFLOW_STATE.RECHECK.getCname());
		}
	}

	/**
	 * ����ͨ�����������״̬��
	 * 
	 * @param dept_id
	 *            ���ű���
	 * @param pend_work_seq
	 *            ��������ˮ
	 * @param pend_srv_name
	 *            �������������
	 * @param check_seq
	 *            ���������
	 * @param user_id
	 *            ���˷����׵��û�
	 * @param crt_user_id
	 *            �����׵��û�
	 */
	private void updateWorkState(String dept_id, String pend_work_seq,
			String pend_srv_name, int cur_check_seq, String user_id, String crt_user_id,String crt_user_cn_name) {
		// ����ͨ�������Ƿ���Ҫ��������
		String dprl_code = "";
		List<ChkDprlCodeBean> check_dprl_list = apsrv.queryCheckBySrvName(
				dept_id, pend_srv_name);
		if (!Assert.isEmpty(check_dprl_list)) {
			//��ѯ�����Ѿ��������˵��û���Ϣ������������������ٴ�������
			List<String> deal_user_ids = wkDetaiDaos.queryDealUserByWorkSeq(pend_work_seq, DEAL_TYPE.RECHECK);
			deal_user_ids.add(crt_user_id);
			deal_user_ids.add(user_id);
			// ��ȡ���������С�ļ�¼
			// min_seq:��С������ţ�
			// item����С������Ŷ�Ӧcheck_dprl_list�ĵڼ�����check_count:���Ž�ɫ�Ѿ����˵Ĵ���
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
			if (min_seq > -1) { // ��ȡ��һ�������û�
				ChkDprlCodeBean bean = check_dprl_list.get(item);

				// ����ͬһ����ɫ���߲��Ž�ɫ���˵Ĵ���
//				String check_dprl_code = bean.getChk_dprl_code();
//				for (ChkDprlCodeBean b : check_dprl_list) {
//					if (b.getCheck_seq() <= cur_check_seq
//							&& check_dprl_code.equals(b.getChk_dprl_code())) {
//						check_count++;
//					}
//				}

				// ��ѯ���˵Ĳ��Ž�ɫ����
				if (bean.getChk_aprov_category() == APROV_CATEGORY.ROLE) {
					dprl_code = usGetSrv.queryDprlByDeptAndRole(dept_id, bean
							.getChk_dprl_code());
				} else {
					dprl_code = bean.getChk_dprl_code();
				}

				// ��ѯ����Ȩ���������û��Ͳ��Ž�ɫ��Ϣ
				List<UsUserRoleInfo> user_role_list = usGetSrv
						.queryUserByDprlCode(dprl_code, deal_user_ids);
				if (Assert.isEmpty(user_role_list)) {
					throw new ApproveDeptRoleLackForSrvException().addScene(
							"OPT", pend_srv_name);
				}
				// �޸�����״̬Ϊ�������ˡ�
				wkPubSrv.updateWorkStateByWorkSeq(pend_work_seq, bean
						.getCheck_seq(), user_role_list.get(0)
						.getUser_id(),usGetSrv.getUserInfoByUserId(user_role_list.get(0).getUser_id()).getUser_cn_name(), WORKFLOW_STATE.RECHECK);

			} else { // ��ȡ��Ȩ��Ϣ
				List<AuthDprlCodeBean> auth_dprl_list = apsrv
						.queryAuthBySrvName(dept_id, pend_srv_name);
				if (!Assert.isEmpty(auth_dprl_list)) {
					AuthDprlCodeBean bean = new AuthDprlCodeBean();
					// ��ȡԶ����Ȩ�����С�ļ�¼
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
					if (item > -1) { // ��ȡ��һ����Ȩ�û���Ϣ
						bean = auth_dprl_list.get(item);
						// ��ѯ���Ž�ɫ����
						if (bean.getAuth_aprov_category() == APROV_CATEGORY.ROLE) {
							dprl_code = usGetSrv.queryDprlByDeptAndRole(
									dept_id, bean.getAuth_dprl_code());
						} else {
							dprl_code = bean.getAuth_dprl_code();
						}
						// ��ѯ���Ž�ɫ��ӦȨ��ֵ��С���û�
						UsUserRoleInfo user_role = usGetSrv
								.queryUserRoleByDprlAndMinWeght(dprl_code,
										deal_user_ids);
						// δ�ҵ��û���Ϣ������ֹֻ��һ���û�����һ�����Ž�ɫ�������ύ�û���ʱ�Լ�ʱ���Լ����Լ�����Ȩ��
						if (Assert.isEmpty(user_role)) {
							throw new ApproveDeptRoleLackForSrvException()
									.addScene("OPT", pend_srv_name);
						}
						// �޸�����״̬Ϊ������Ȩ��
						wkPubSrv.updateWorkStateByWorkSeq(pend_work_seq, bean
								.getAuth_seq(), user_role.getUser_id(),usGetSrv.getUserInfoByUserId(user_role.getUser_id()).getUser_cn_name(),
								WORKFLOW_STATE.APPROVAL);

					} else { // ������Ҫ��Ȩ��ֱ���޸�����״̬Ϊ�����ύ��
						wkPubSrv.updateWorkStateByWorkSeq(pend_work_seq, 0, crt_user_id,crt_user_cn_name,
								WORKFLOW_STATE.EXECUTORY);
					}
				} else { // ������Ҫ��Ȩ��ֱ���޸�����״̬Ϊ�����ύ��
					wkPubSrv.updateWorkStateByWorkSeq(pend_work_seq, 0, crt_user_id,crt_user_cn_name,
							WORKFLOW_STATE.EXECUTORY);
				}
			}
		}
	}
}
