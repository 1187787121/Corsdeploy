/**
 * Title: PageWorkListOutputBean.java
 * File Description: ��������������б��ѯ����ӿ�
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2016��3��8��
 */
package com.wk.cd.work.wk.bean;

import java.util.List;

import com.wk.cd.bean.ActionRootOutputBean;

/**
 * Class Description: ��������������б��ѯ����ӿ�
 * @author Xul
 */
public class QueryMineWorkListOutputBean extends ActionRootOutputBean{

	private static final long serialVersionUID = -8190303560192403570L;
	
	/**
	 * �ҵ��Ѵ��������б�
	 */
	private List<MineWorkBean> mine_work_list;
	
	public static final String MINE_WORK_LISTCN = "�ҵ��Ѵ��������б�";
	
	/**
	 * �ҵĴ����������б�
	 */
	private List<MineWorkBean> mine_executory_list;
	
	public static final String MINE_EXECUTORY_LISTCN = "�ҵĴ����������б�";
	
	/**
	 * ��ִ���������
	 */
	private int executory_count;
	
	public static final String EXECUTORY_COUNTCN = "��ִ���������";

	/**
	 * @return mine_work_list �ҵ��Ѵ��������б�
	 */
	public List<MineWorkBean> getMine_work_list() {
		return mine_work_list;
	}

	/**
	 * @param mine_work_list �ҵ��Ѵ��������б�
	 */
	public void setMine_work_list(List<MineWorkBean> mine_work_list) {
		this.mine_work_list = mine_work_list;
	}
	
	/**
	 * @return mine_executory_list �ҵĴ����������б�
	 */
	public List<MineWorkBean> getMine_executory_list() {
		return mine_executory_list;
	}

	/**
	 * @param mine_executory_list �ҵĴ����������б�
	 */
	public void setMine_executory_list(List<MineWorkBean> mine_executory_list) {
		this.mine_executory_list = mine_executory_list;
	}

	/**
	 * @return executory_count ��ִ���������
	 */
	public int getExecutory_count() {
		return executory_count;
	}

	/**
	 * @param executory_count ��ִ���������
	 */
	public void setExecutory_count(int executory_count) {
		this.executory_count = executory_count;
	}
	
}
