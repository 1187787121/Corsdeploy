/**
 * Title: PageExtDprlListByDeptIdInputBean.java
 * File Description:  ���ݲ��ű����ѯ���Ž�ɫ����������ϸ��Ϣ����ӿ�
 * @copyright: 2015
 * @company: CORSWORK
 * @author: link
 * @version: 1.0
 * @date: 2015-1-23
 */
package com.wk.cd.system.us.bean;

import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description: ���ݲ��ű����ѯ���Ž�ɫ����������ϸ��Ϣ����ӿ�
 * @author link
 */
public class QueryExtDprlListByDeptIdInputBean
		extends ActionRootInputBean {

	private static final long serialVersionUID = 4423864770051923187L;
	private String[] dept_arr;
	public static final String DEPT_ARRCN = "���ű�������";

	/**
	 * @return dept_arr ���ű�������
	 */
	public String[] getDept_arr() {
		return this.dept_arr;
	}

	/**
	 * @param dept_arr ���ű�������
	 */
	public void setDept_arr(String[] dept_arr) {
		this.dept_arr = dept_arr;
	}

}
