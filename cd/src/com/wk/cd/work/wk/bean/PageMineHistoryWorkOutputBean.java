/**
 * Title: PageMineHistoryWorkOutputBean.java
 * File Description: ��ҳ��ѯ�ҵ���ʷ�����б�����ӿ�
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2016��3��10��
 */
package com.wk.cd.work.wk.bean;

import java.util.List;

import com.wk.cd.bean.PageQueryActionRootOutputBean;

/**
 * Class Description: ��ҳ��ѯ�ҵ���ʷ�����б�����ӿ�
 * @author Xul
 */
public class PageMineHistoryWorkOutputBean extends PageQueryActionRootOutputBean{

	private static final long serialVersionUID = 3300015048604139252L;
	
	/**
	 * �ҵ���ʷ�����б�
	 */
	private List<HistoryWorkBean> mine_history_list;
	
	public static final String MINE_HISTORY_LISTCN = "�ҵ���ʷ�����б�";

	/**
	 * @return mine_history_list �ҵ���ʷ�����б�
	 */
	public List<HistoryWorkBean> getMine_history_list() {
		return mine_history_list;
	}

	/**
	 * @param mine_history_list �ҵ���ʷ�����б�
	 */
	public void setMine_history_list(List<HistoryWorkBean> mine_history_list) {
		this.mine_history_list = mine_history_list;
	}
	
	
}
