/**
 * Title: PageLogByLabelOutputBean.java
 * File Description: ���ձ�Ǽ����ҳ��ѯ��־��Ϣ����ӿ� 
 * @copyright: 2015
 * @company: CORSWORK
 * @author: tlw
 * @version: 1.0
 * @date: 2015-1-29
 */
package com.wk.cd.system.lg.bean;

import java.util.List;

import com.wk.cd.bean.PageQueryActionRootOutputBean;
import com.wk.cd.system.lg.info.LgLogMfInfo;

/**
 * Class Description: ���ձ�Ǽ����ҳ��ѯ��־��Ϣ����ӿ�
 * @author tlw
 */
public class PageLogByLabelOutputBean
		extends PageQueryActionRootOutputBean {

	private static final long serialVersionUID = 5864813983794692066L;

	/**
	 * ��־��Ϣ�б�
	 */
	private List<LgLogMfInfo> log_list;

	public static final String LOG_LISTCN = "��־��Ϣ�б�";

	/**
	 * @return log_list ��־��Ϣ�б�
	 */
	public List<LgLogMfInfo> getLog_list() {
		return log_list;
	}

	/**
	 * @param log_list ��־��Ϣ�б�
	 */
	public void setLog_list(List<LgLogMfInfo> log_list) {
		this.log_list = log_list;
	}
}
