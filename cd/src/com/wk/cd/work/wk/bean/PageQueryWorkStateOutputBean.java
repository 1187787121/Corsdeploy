/**
 * Title: PageQueryWorkStateOutputBean.java
 * File Description:��������״̬��ѯ����б�
 * @copyright: 2014
 * @company: CORSWORK
 * @author: tlw
 * @version: 1.0
 * @date: 2014-12-3
 */
package com.wk.cd.work.wk.bean;

import java.util.List;

import com.wk.cd.bean.PageQueryActionRootOutputBean;
import com.wk.cd.work.wk.info.WkDealStateInfo;

/**
 * Class Description:��������״̬��ѯ����б�
 * @author tlw
 */
public class PageQueryWorkStateOutputBean
		extends PageQueryActionRootOutputBean {

	private static final long serialVersionUID = -4129679340762424875L;

	private List<WkDealStateInfo> work_state_list;

	public static final String WORK_STATE_LISTCN = "����״̬�б�";

	/**
	 * @return work_state_list ����״̬�б�
	 */
	public List<WkDealStateInfo> getWork_state_list() {
		return work_state_list;
	}

	/**
	 * @param work_state_list ����״̬�б�
	 */
	public void setWork_state_list(List<WkDealStateInfo> work_state_list) {
		this.work_state_list = work_state_list;
	}
}
