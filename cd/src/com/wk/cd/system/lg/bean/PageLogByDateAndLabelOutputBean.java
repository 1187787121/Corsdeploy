/**
 * Title: PageLogOutputBean.java
 * File Description: ��ҳ��ѯ��־�������Ϣ
 * @copyright: 2015
 * @company: CORSWORK
 * @author: tlw
 * @version: 1.0
 * @date: 2015-1-23
 */
package com.wk.cd.system.lg.bean;

import java.util.List;

import com.wk.cd.bean.PageQueryActionRootOutputBean;

/**
 * Class Description: ��ҳ��ѯ��־�������Ϣ
 * @author tlw
 */
public class PageLogByDateAndLabelOutputBean
		extends PageQueryActionRootOutputBean {

	private static final long serialVersionUID = 1534959386499113262L;

	/**
	 * ��־��Ϣ�б�
	 */
	private List<LogBean> log_list;

	public static final String LOG_LISTCN = "��־��Ϣ�б�";

	/**
	 * @return log_list ��־��Ϣ�б�
	 */
	public List<LogBean> getLog_list() {
		return log_list;
	}

	/**
	 * @param log_list ��־��Ϣ�б�
	 */
	public void setLog_list(List<LogBean> log_list) {
		this.log_list = log_list;
	}

}
