/**
 * Title: QueryDownFileNameOutputBean.java
 * File Description: ��ѯ������־�ļ���Ϣ����ӿ�
 * @copyright: 2015
 * @company: CORSWORK
 * @author: tlw
 * @version: 1.0
 * @date: 2015-1-29
 */
package com.wk.cd.system.lg.bean;

import java.util.List;

import com.wk.cd.bean.ActionRootOutputBean;
import com.wk.cd.system.lg.info.LgLogDownInfo;

/**
 * Class Description: ��ѯ������־�ļ���Ϣ����ӿ�
 * @author tlw
 */
public class QueryDownFileNameOutputBean
		extends ActionRootOutputBean {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -3708077262944592833L;

	/**
	 * ��־�����ļ���Ϣ
	 */
	private List<LgLogDownInfo> log_down_list;

	public static final String LOG_DOWN_LISTCN = "��־�����ļ���Ϣ";

	/**
	 * @return log_down_list ��־�����ļ���Ϣ
	 */
	public List<LgLogDownInfo> getLog_down_list() {
		return log_down_list;
	}

	/**
	 * @param log_down_list ��־�����ļ���Ϣ
	 */
	public void setLog_down_list(List<LgLogDownInfo> log_down_list) {
		this.log_down_list = log_down_list;
	}

}
