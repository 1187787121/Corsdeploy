/**
 * Title: HistoryWorkBean.java
 * File Description: ��ʷ����
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2016��3��10��
 */
package com.wk.cd.work.wk.bean;

import java.util.List;

import com.wk.cd.enu.WORKFLOW_STATE;
import com.wk.util.JaDate;
import com.wk.util.JaTime;

/**
 * Class Description: ��ʷ����
 * @author Xul
 */
public class HistoryWorkBean {
	
	/**
	 *������������ˮ��
	 */
	private String pend_work_seq;

	public static final String PEND_WORK_SEQCN = "������������ˮ��";
	
	/**
	 *����������˵��
	 */
	private String pendwk_bk_expl;

	public static final String PENDWK_BK_EXPLCN = "����������˵��";
	
	/**
	 *����״̬
	 */
	private WORKFLOW_STATE workflow_state;

	public static final String WORKFLOW_STATECN = "����״̬";
	
	/**
	 *�����û�
	 */
	private String crt_user_cn_name;

	public static final String CRT_USER_CN_NAMECN = "�����û�";
	
	/**
	 *��������
	 */
	private JaDate deal_bk_date;

	public static final String DEAL_BK_DATECN = "��������";
	
	/**
	 *����ʱ��
	 */
	private JaTime deal_bk_time;

	public static final String DEAL_BK_TIMECN = "����ʱ��";
	
	/**
	 *�������������
	 */
	private String pend_srv_name;

	public static final String PEND_SRV_NAMECN = "�������������";
	
	/**
	 *��������
	 */
	private JaDate crt_bk_date;

	public static final String CRT_BK_DATECN = "��������";

	/**
	 *����ʱ��
	 */
	private JaTime crt_bk_time;

	public static final String CRT_BK_TIMECN = "����ʱ��";
	
	/**
	 *��������˵��
	 */
	private String apply_bk_expl;

	public static final String APPLY_BK_EXPLCN = "��������˵��";
	
	/**
	 * ����״̬��ϸ�б�
	 */
	private List<WorkStateDetailBean> work_detail_list;
	
	public static final String WORK_DETAIL_LISTCN = "����״̬��ϸ�б�";

	/**
	 * @return pend_work_seq ������������ˮ��
	 */
	public String getPend_work_seq() {
		return pend_work_seq;
	}

	/**
	 * @param pend_work_seq ������������ˮ��
	 */
	public void setPend_work_seq(String pend_work_seq) {
		this.pend_work_seq = pend_work_seq;
	}

	/**
	 * @return pendwk_bk_expl ����������˵��
	 */
	public String getPendwk_bk_expl() {
		return pendwk_bk_expl;
	}

	/**
	 * @param pendwk_bk_expl ����������˵��
	 */
	public void setPendwk_bk_expl(String pendwk_bk_expl) {
		this.pendwk_bk_expl = pendwk_bk_expl;
	}

	/**
	 * @return workflow_state ����״̬
	 */
	public WORKFLOW_STATE getWorkflow_state() {
		return workflow_state;
	}

	/**
	 * @param workflow_state ����״̬
	 */
	public void setWorkflow_state(WORKFLOW_STATE workflow_state) {
		this.workflow_state = workflow_state;
	}
	
	/**
	 * @return crt_user_cn_name �����û�
	 */
	public String getCrt_user_cn_name() {
		return crt_user_cn_name;
	}

	/**
	 * @param crt_user_cn_name �����û�
	 */
	public void setCrt_user_cn_name(String crt_user_cn_name) {
		this.crt_user_cn_name = crt_user_cn_name;
	}

	/**
	 * @return deal_bk_date ��������
	 */
	public JaDate getDeal_bk_date() {
		return deal_bk_date;
	}

	/**
	 * @param deal_bk_date ��������
	 */
	public void setDeal_bk_date(JaDate deal_bk_date) {
		this.deal_bk_date = deal_bk_date;
	}

	/**
	 * @return deal_bk_time ����ʱ��
	 */
	public JaTime getDeal_bk_time() {
		return deal_bk_time;
	}

	/**
	 * @param deal_bk_time ����ʱ��
	 */
	public void setDeal_bk_time(JaTime deal_bk_time) {
		this.deal_bk_time = deal_bk_time;
	}
	
	/**
	 * @return pend_srv_name �������������
	 */
	public String getPend_srv_name() {
		return pend_srv_name;
	}

	/**
	 * @param pend_srv_name �������������
	 */
	public void setPend_srv_name(String pend_srv_name) {
		this.pend_srv_name = pend_srv_name;
	}

	/**
	 * @return work_detail_list ����״̬��ϸ�б�
	 */
	public List<WorkStateDetailBean> getWork_detail_list() {
		return work_detail_list;
	}

	/**
	 * @param work_detail_list ����״̬��ϸ�б�
	 */
	public void setWork_detail_list(List<WorkStateDetailBean> work_detail_list) {
		this.work_detail_list = work_detail_list;
	}

	/**
	 * @return crt_bk_date ��������
	 */
	public JaDate getCrt_bk_date() {
		return crt_bk_date;
	}

	/**
	 * @param crt_bk_date ��������
	 */
	public void setCrt_bk_date(JaDate crt_bk_date) {
		this.crt_bk_date = crt_bk_date;
	}

	/**
	 * @return crt_bk_time ����ʱ��
	 */
	public JaTime getCrt_bk_time() {
		return crt_bk_time;
	}

	/**
	 * @param crt_bk_time ����ʱ��
	 */
	public void setCrt_bk_time(JaTime crt_bk_time) {
		this.crt_bk_time = crt_bk_time;
	}

	/**
	 * @return apply_bk_expl ��������˵��
	 */
	public String getApply_bk_expl() {
		return apply_bk_expl;
	}

	/**
	 * @param apply_bk_expl ��������˵��
	 */
	public void setApply_bk_expl(String apply_bk_expl) {
		this.apply_bk_expl = apply_bk_expl;
	}
}
