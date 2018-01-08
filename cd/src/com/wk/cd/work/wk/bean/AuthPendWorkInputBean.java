/**
 * Title: AuthPendWorkInputBean.java
 * File Description: ������Ȩ����ӿ� 
 * @copyright: 2014
 * @company: CORSWORK
 * @author: tlw
 * @version: 1.0
 * @date: 2014-12-9
 */
package com.wk.cd.work.wk.bean;

import com.wk.cd.bean.ActionRootInputBean;
import com.wk.cd.enu.AUTH_TYPE;
import com.wk.cd.enu.DEAL_RES;

/**
 * Class Description: ������Ȩ����ӿ�
 * @author tlw
 */
public class AuthPendWorkInputBean
		extends ActionRootInputBean {

	private static final long serialVersionUID = 5361245943648300955L;

	/**
	 * ��������ˮ��
	 */
	private String pend_wk_seq;

	public static final String PEND_WK_SEQCN = "��������ˮ��";

	/**
	 * ��Ȩ����
	 */
	private AUTH_TYPE auth_type;

	public static final String AUTH_TYPECN = "��Ȩ����";

	/**
	 * ������
	 */
	private DEAL_RES deal_res;

	public static final String DEAL_RESCN = "������";

	/**
	 * ����˵��
	 */
	private String deal_bk_desc;

	public static final String DEAL_BK_DESCCN = "����˵��";

	/**
	 * @return pend_wk_seq ��������ˮ��
	 */
	public String getPend_wk_seq() {
		return pend_wk_seq;
	}

	/**
	 * @param pend_wk_seq ��������ˮ��
	 */
	public void setPend_wk_seq(String pend_wk_seq) {
		this.pend_wk_seq = pend_wk_seq;
	}

	/**
	 * @return auth_type ��Ȩ��ʽ
	 */
	public AUTH_TYPE getAuth_type() {
		return auth_type;
	}

	/**
	 * @param auth_type ��Ȩ��ʽ
	 */
	public void setAuth_type(AUTH_TYPE auth_type) {
		this.auth_type = auth_type;
	}

	/**
	 * @return deal_res ������
	 */
	public DEAL_RES getDeal_res() {
		return deal_res;
	}

	/**
	 * @param deal_res ������
	 */
	public void setDeal_res(DEAL_RES deal_res) {
		this.deal_res = deal_res;
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
