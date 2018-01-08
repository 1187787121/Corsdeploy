/**
 * Title: UpdateWkDealStateInputBean.java
 * File Description: ��������״̬����ӿ�
 * @copyright: 2015
 * @company: CORSWORK
 * @author: qianc
 * @version: 1.0
 * @date: 2015-3-30
 */
package com.wk.cd.work.wk.bean;

import com.wk.cd.bean.ActionRootInputBean;
import com.wk.cd.enu.WORKFLOW_STATE;

/**
 * Class Description: ��������״̬����ӿ�
 * @author qianc
 */
public class UpdateWkDealStateInputBean extends ActionRootInputBean{
	
	private static final long serialVersionUID = 531353677109883266L;
	 /**
     * ������������ˮ��
     */
	
	private String pend_wk_seq;
	public static final String PEND_WK_SEQCN = "������������ˮ��"; 

	/**
	 *����״̬
	 */
	private WORKFLOW_STATE workflow_state;

	public static final String WORKFLOW_STATECN = "����״̬";
	
	/**
	 * �������
	 */
	private String deal_bk_desc;
	
	public static final String DEAL_BK_DESCCN = "�������";
	
	
	/**
	 * @return pend_wk_seq ������������ˮ��
	 */
	public String getPend_wk_seq() {
		return this.pend_wk_seq;
	}

	/**
	 * @param pend_wk_seq ������������ˮ��
	 */
	public void setPend_wk_seq(String pend_wk_seq) {
		this.pend_wk_seq = pend_wk_seq;
	}

	/**
	 * @return workflow_state ����״̬
	 */
	public WORKFLOW_STATE getWorkflow_state() {
		return this.workflow_state;
	}

	/**
	 * @param workflow_state ����״̬
	 */
	public void setWorkflow_state(WORKFLOW_STATE workflow_state) {
		this.workflow_state = workflow_state;
	}

	/**
	 * @return deal_bk_desc �������
	 */
	public String getDeal_bk_desc() {
		return this.deal_bk_desc;
	}

	/**
	 * @param deal_bk_desc �������
	 */
	public void setDeal_bk_desc(String deal_bk_desc) {
		this.deal_bk_desc = deal_bk_desc;
	}
}
