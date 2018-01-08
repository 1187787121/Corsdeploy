/**
 * Title: WorkStateDetailBean.java
 * File Description: ����״̬��ϸ
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2016��3��17��
 */
package com.wk.cd.work.wk.bean;

import com.wk.cd.enu.PEND_TYPE;
import com.wk.util.JaDate;
import com.wk.util.JaTime;

/**
 * Class Description: ����״̬��ϸ
 * @author Xul
 */
public class WorkStateDetailBean {
	
	/**
	 *����ʽ
	 */
	private PEND_TYPE pend_type;

	public static final String PEND_TYPECN = "����ʽ";
	
	/**
	 *����ִ��״̬
	 */
	private int work_state;

	public static final String WORK_STATECN = "����ִ��״̬";
	
	/**
	 *������Ա
	 */
	private String deal_user_cn_name;

	public static final String DEAL_USER_CN_NAMECN = "������Ա";
	
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
	 *����˵��
	 */
	private String deal_bk_desc;

	public static final String DEAL_BK_DESCCN = "����˵��";

	/**
	 * @return pend_type ����ʽ
	 */
	public PEND_TYPE getPend_type() {
		return pend_type;
	}

	/**
	 * @param pend_type ����ʽ
	 */
	public void setPend_type(PEND_TYPE pend_type) {
		this.pend_type = pend_type;
	}


	/**
	 * @return work_state ����ִ��״̬
	 */
	public int getWork_state() {
		return work_state;
	}

	/**
	 * @param work_state ����ִ��״̬
	 */
	public void setWork_state(int work_state) {
		this.work_state = work_state;
	}

	/**
	 * @return deal_user_cn_name ������Ա
	 */
	public String getDeal_user_cn_name() {
		return deal_user_cn_name;
	}

	/**
	 * @param deal_user_cn_name ������Ա
	 */
	public void setDeal_user_cn_name(String deal_user_cn_name) {
		this.deal_user_cn_name = deal_user_cn_name;
	}

	/**
	 *@return deal_bk_date ��������
	 */
	public JaDate getDeal_bk_date() {
		return this.deal_bk_date;
	}

	/**
	 *@param deal_bk_date ��������
	 */
	public void setDeal_bk_date(JaDate deal_bk_date) {
		this.deal_bk_date = deal_bk_date;
	}

	/**
	 *@return deal_bk_time ����ʱ��
	 */
	public JaTime getDeal_bk_time() {
		return this.deal_bk_time;
	}

	/**
	 *@param deal_bk_time ����ʱ��
	 */
	public void setDeal_bk_time(JaTime deal_bk_time) {
		this.deal_bk_time = deal_bk_time;
	}

	/**
	 * @return deal_bk_desc ����˵��
	 */
	public String getDeal_bk_desc() {
		return deal_bk_desc;
	}

	/**
	 * @param deal_bk_desc ����˵��
	 */
	public void setDeal_bk_desc(String deal_bk_desc) {
		this.deal_bk_desc = deal_bk_desc;
	}
	
}
