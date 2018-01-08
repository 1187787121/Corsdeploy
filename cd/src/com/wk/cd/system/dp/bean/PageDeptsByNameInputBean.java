/**
 * Title: PageDeptByNameInputBean.java
 * File Description: ���ݲ�������ģ����ѯ������Ϣ����ӿ�
 * @copyright: 2014
 * @company: CORSWORK
 * @author: xuy
 * @version: 1.0
 * @date: 2014-11-28
 */
package com.wk.cd.system.dp.bean;

import com.wk.cd.bean.PageQueryActionRootInputBean;

/**
 * Class Description: ���ݲ�������ģ����ѯ������Ϣ����ӿ�
 * @author xuy
 */
public class PageDeptsByNameInputBean extends PageQueryActionRootInputBean {

	private static final long serialVersionUID = -6167847594248730071L;
	private String dept_cn_name;
	public static final String DEPT_CN_NAMECN = "��������";
	
	/**
	 * @return dept_cn_name ��������
	 */
	public String getDept_cn_name() {
		return dept_cn_name;
	}
	/**
	 * @param deptCnName ��������
	 */
	public void setDept_cn_name(String deptCnName) {
		dept_cn_name = deptCnName;
	}

	
	
}
