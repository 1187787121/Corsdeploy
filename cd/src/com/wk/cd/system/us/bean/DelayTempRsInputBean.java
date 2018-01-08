/**
 * Title: DelayTempRsInputBean.java
 * File Description: ��ʱ��Դ��������
 * @copyright: 2016
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2016��3��22��
 */
package com.wk.cd.system.us.bean;

import com.wk.cd.bean.ActionRootInputBean;
import com.wk.util.JaDateTime;

/**
 * Class Description: ��ʱ��Դ�������ڽӿ�
 * @author HT
 */
public class DelayTempRsInputBean extends ActionRootInputBean{

	private static final long serialVersionUID = 5525553420591371461L;

	/**
	 * ��Դ����
	 */
	private String rs_code;
	
	public static final String RS_CODECN = "��Դ����";
	
	/**
	 *��ʱȨ�޽���ʱ��
	 */
	private JaDateTime tempend_bk_datetime;

	public static final String TEMPEND_BK_DATETIMECN = "��ʱȨ�޽���ʱ��";

	/**
	 * @return rs_code ��Դ����
	 */
	public String getRs_code() {
		return rs_code;
	}

	/**
	 * @param rs_code ��Դ����
	 */
	public void setRs_code(String rs_code) {
		this.rs_code = rs_code;
	}

	/**
	 * @return tempend_bk_datetime ��ʱȨ�޽���ʱ��
	 */
	public JaDateTime getTempend_bk_datetime() {
		return tempend_bk_datetime;
	}

	/**
	 * @param tempend_bk_datetime ��ʱȨ�޽���ʱ��
	 */
	public void setTempend_bk_datetime(JaDateTime tempend_bk_datetime) {
		this.tempend_bk_datetime = tempend_bk_datetime;
	}
}
