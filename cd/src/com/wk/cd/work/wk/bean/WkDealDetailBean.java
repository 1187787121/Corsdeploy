/**
 * Title: WkDealDetailBean.java
 * File Description: 
 * @copyright: 2015
 * @company: CORSWORK
 * @author: tlw
 * @version: 1.0
 * @date: 2015-2-10
 */
package com.wk.cd.work.wk.bean;

import com.wk.cd.enu.DEAL_RES;
import com.wk.cd.enu.DEAL_TYPE;
import com.wk.util.JaDate;
import com.wk.util.JaTime;

/**
 * Class Description:
 * @author tlw
 */
public class WkDealDetailBean {
	/**
	 *������������ˮ��
	 */
	private String pend_work_seq;

	public static final String PEND_WORK_SEQCN = "������������ˮ��";

	/**
	 *�������
	 */
	private int deal_seq;

	public static final String DEAL_SEQCN = "�������";

	/**
	 *����ʽ
	 */
	private DEAL_TYPE deal_type;

	public static final String DEAL_TYPECN = "����ʽ";

	/**
	 *������
	 */
	private DEAL_RES deal_res;

	public static final String DEAL_RESCN = "������";

	/**
	 *������Ա
	 */
	private String deal_user_id;

	public static final String DEAL_USER_IDCN = "������Ա";

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
	 * ������Ա����
	 */
	private String user_cn_name;

	public static final String USER_CN_NAMECN = "������Ա����";

	/**
	 *@return pend_work_seq ������������ˮ��
	 */
	public String getPend_work_seq() {
		return this.pend_work_seq;
	}

	/**
	 *@param pend_work_seq ������������ˮ��
	 */
	public void setPend_work_seq(String pend_work_seq) {
		this.pend_work_seq = pend_work_seq;
	}

	/**
	 *@return deal_seq �������
	 */
	public int getDeal_seq() {
		return this.deal_seq;
	}

	/**
	 *@param deal_seq �������
	 */
	public void setDeal_seq(int deal_seq) {
		this.deal_seq = deal_seq;
	}

	/**
	 *@return deal_type ����ʽ
	 */
	public DEAL_TYPE getDeal_type() {
		return this.deal_type;
	}

	/**
	 *@param deal_type ����ʽ
	 */
	public void setDeal_type(DEAL_TYPE deal_type) {
		this.deal_type = deal_type;
	}

	/**
	 *@return deal_res ������
	 */
	public DEAL_RES getDeal_res() {
		return this.deal_res;
	}

	/**
	 *@param deal_res ������
	 */
	public void setDeal_res(DEAL_RES deal_res) {
		this.deal_res = deal_res;
	}

	/**
	 *@return deal_user_id ������Ա
	 */
	public String getDeal_user_id() {
		return this.deal_user_id;
	}

	/**
	 *@param deal_user_id ������Ա
	 */
	public void setDeal_user_id(String deal_user_id) {
		this.deal_user_id = deal_user_id;
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
	 *@return deal_bk_desc ����˵��
	 */
	public String getDeal_bk_desc() {
		return this.deal_bk_desc;
	}

	/**
	 *@param deal_bk_desc ����˵��
	 */
	public void setDeal_bk_desc(String deal_bk_desc) {
		this.deal_bk_desc = deal_bk_desc;
	}

	/**
	 * @return user_cn_name ������Ա����
	 */
	public String getUser_cn_name() {
		return this.user_cn_name;
	}

	/**
	 * @param user_cn_name ������Ա����
	 */
	public void setUser_cn_name(String user_cn_name) {
		this.user_cn_name = user_cn_name;
	}

}
