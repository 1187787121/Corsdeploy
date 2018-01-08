/**
 * Title: UpdateWorkStateToCompleteBean.java
 * File Description: ��������״̬��״̬Ϊ��ɣ����ҵǼ�������ϸ��
 * @copyright: 2014
 * @company: CORSWORK
 * @author: tlw
 * @version: 1.0
 * @date: 2014-12-24
 */
package com.wk.cd.work.wk.bean;

import com.wk.util.JaDate;
import com.wk.util.JaTime;

/**
 * Class Description: ��������״̬��״̬Ϊ��ɣ����ҵǼ�������ϸ��
 * @author tlw
 */
public class UpdateWorkStateToCompleteBean {

	/**
	 * ������������ˮ��
	 */
	private String pend_work_seq;

	public static final String PEND_WORK_SEQCN = "������������ˮ��";

	/**
	 * ������Ա
	 */
	private String deal_user_id;

	public static final String DEAL_USER_IDCN = "������Ա";

	/**
	 * ��������
	 */
	private JaDate deal_bk_date;

	public static final String DEAL_BK_DATECN = "��������";

	/**
	 * ����ʱ��
	 */
	private JaTime deal_bk_time;

	public static final String DEAL_BK_TIMECN = "����ʱ��";

	/**
	 * ����˵��
	 */
	private String deal_bk_desc;

	public static final String DEAL_BK_DESCCN = "����˵��";

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
	 * @return deal_user_id ������Ա
	 */
	public String getDeal_user_id() {
		return deal_user_id;
	}

	/**
	 * @param deal_user_id ������Ա
	 */
	public void setDeal_user_id(String deal_user_id) {
		this.deal_user_id = deal_user_id;
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
