/**
 * Title: CheckPublishListAndPacInputBean.java
 * File Description: У�鷢���ļ��嵥��Ͷ��������ӿ�
 * @copyright: 2017
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2017��2��21��
 */
package com.wk.cd.build.ea.bean;

import java.util.List;

import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description: У�鷢���ļ��嵥��Ͷ��������ӿ�
 * @author Xul
 */
public class CheckPublishListAndPacInputBean extends ActionRootInputBean{

	private static final long serialVersionUID = 7398279739268531378L;
	
	/**
	 * �嵥�б�
	 */
	private List<TargetPackageBean> list_list;
	
	public static final String LIST_LISTCN = "�嵥�б�";
	
	/**
	 * Ͷ�����б�
	 */
	private List<TargetPackageBean> pac_list;
	
	public static final String PAC_LISTCN = "Ͷ�����б�";

	/**
	 * @return list_list �嵥�б�
	 */
	public List<TargetPackageBean> getList_list() {
		return list_list;
	}

	/**
	 * @param list_list �嵥�б�
	 */
	public void setList_list(List<TargetPackageBean> list_list) {
		this.list_list = list_list;
	}

	/**
	 * @return pac_list Ͷ�����б�
	 */
	public List<TargetPackageBean> getPac_list() {
		return pac_list;
	}

	/**
	 * @param pac_list Ͷ�����б�
	 */
	public void setPac_list(List<TargetPackageBean> pac_list) {
		this.pac_list = pac_list;
	}
}
