/**
 * Title: QueryDownFileNameInputBean.java
 * File Description: ��ѯ������־�ļ���Ϣ����ӿ�
 * @copyright: 2015
 * @company: CORSWORK
 * @author: tlw
 * @version: 1.0
 * @date: 2015-1-29
 */
package com.wk.cd.system.lg.bean;

import com.wk.cd.bean.ActionRootInputBean;
import com.wk.util.JaDate;

/**
 * Class Description: ��ѯ������־�ļ���Ϣ����ӿ�
 * @author tlw
 */
public class QueryDownFileNameInputBean
		extends ActionRootInputBean {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -2173979950773173406L;

	/**
	 * ������־����
	 */
	private JaDate crt_bk_date;

	public static final String CRT_BK_DATECN = "������־����";

	/**
	 * @return crt_bk_date ������־����
	 */
	public JaDate getCrt_bk_date() {
		return crt_bk_date;
	}

	/**
	 * @param crt_bk_date ������־����
	 */
	public void setCrt_bk_date(JaDate crt_bk_date) {
		this.crt_bk_date = crt_bk_date;
	}

}
