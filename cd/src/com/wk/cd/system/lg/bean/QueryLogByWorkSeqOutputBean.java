/**
 * Title: QueryLogByWorkSeqOutputBean.java
 * File Description: ��ȷ��ѯ��־����ӿ�
 * @copyright: 2015
 * @company: CORSWORK
 * @author: tlw
 * @version: 1.0
 * @date: 2015-1-23
 */
package com.wk.cd.system.lg.bean;

import com.wk.cd.bean.ActionRootOutputBean;

/**
 * Class Description: ��ȷ��ѯ��־����ӿ�
 * @author tlw
 */
public class QueryLogByWorkSeqOutputBean
		extends ActionRootOutputBean {

	private static final long serialVersionUID = 3017008169059209591L;

	/**
	 * ��ϸ��־��Ϣ
	 */
	private LogBean log_bean;

	public static final String LOG_BEANCN = "��ϸ��־��Ϣ";

	/**
	 * @return log_bean ��ϸ��־��Ϣ
	 */
	public LogBean getLog_bean() {
		return log_bean;
	}

	/**
	 * @param log_bean ��ϸ��־��Ϣ
	 */
	public void setLog_bean(LogBean log_bean) {
		this.log_bean = log_bean;
	}

}
