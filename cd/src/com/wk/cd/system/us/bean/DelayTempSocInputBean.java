/**
 * Title: DelayTempSocInputBean.java
 * File Description: ��ʱ����Դ����������ӿ�
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
 * Class Description:  ��ʱ����Դ����������ӿ�
 * @author HT
 */
public class DelayTempSocInputBean extends ActionRootInputBean{

	private static final long serialVersionUID = 3255815741278011060L;
	
	/**
	 * ����Դ��
	 */
	private String soc_name;
	
	public static final String SOC_NAMECN = "����Դ��";
	
	/**
	 *��ʱȨ�޽���ʱ��
	 */
	private JaDateTime tempend_bk_datetime;

	public static final String TEMPEND_BK_DATETIMECN = "��ʱȨ�޽���ʱ��";

	/**
	 * @return soc_name ����Դ��
	 */
	public String getSoc_name() {
		return soc_name;
	}

	/**
	 * @param soc_name ����Դ��
	 */
	public void setSoc_name(String soc_name) {
		this.soc_name = soc_name;
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