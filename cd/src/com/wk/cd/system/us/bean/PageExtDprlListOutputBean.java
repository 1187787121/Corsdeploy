/**
 * Title: QueryExtDprlListOutputBean.java
 * File Description: ��ѯ���Ž�ɫ��չ��Ϣ�б�����ӿ�
 * @copyright: 2015
 * @company: CORSWORK
 * @author: link
 * @version: 1.0
 * @date: 2015-1-17
 */
package com.wk.cd.system.us.bean;

import java.util.List;

import com.wk.cd.bean.PageQueryActionRootOutputBean;

/**
 * Class Description: ��ѯ���Ž�ɫ��չ��Ϣ�б�����ӿ�
 * @author link
 */
public class PageExtDprlListOutputBean
		extends PageQueryActionRootOutputBean {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 6655644323446265750L;

	private List<UsExtDprlBean> dprl_list;
	public static final String DPRL_EXT_LISTCN = "���Ž�ɫ��չ��Ϣ�б�";

	/**
	 * @return dprl_list ���Ž�ɫ��չ��Ϣ�б�
	 */
	public List<UsExtDprlBean> getDprl_list() {
		return this.dprl_list;
	}

	/**
	 * @param dprl_list ���Ž�ɫ��չ��Ϣ�б�
	 */
	public void setDprl_list(List<UsExtDprlBean> dprl_list) {
		this.dprl_list = dprl_list;
	}

}
