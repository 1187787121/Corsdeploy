/**
 * Title: QueryDprlOutputBean.java
 * File Description: ��ѯ���Ž�ɫ����ӿ�
 * @copyright: 2015
 * @company: CORSWORK
 * @author: link
 * @version: 1.0
 * @date: 2015-1-15
 */
package com.wk.cd.system.us.bean;

import java.util.List;

import com.wk.cd.bean.ActionRootOutputBean;
import com.wk.cd.system.us.info.UsDeptRoleInfo;

/**
 * Class Description:��ѯ���Ž�ɫ����ӿ�
 * @author link
 */
public class QueryDprlOutputBean
		extends ActionRootOutputBean {

	private static final long serialVersionUID = 5645769350046839090L;

	private List<UsDeptRoleInfo> dprl_list;
	public static final String DPRL_LISTCN = "���Ž�ɫ�б�";

	/**
	 * @return dprl_list ���Ž�ɫ�б�
	 */
	public List<UsDeptRoleInfo> getDprl_list() {
		return this.dprl_list;
	}

	/**
	 * @param dprl_list ���Ž�ɫ�б�
	 */
	public void setDprl_list(List<UsDeptRoleInfo> dprl_list) {
		this.dprl_list = dprl_list;
	}

}
