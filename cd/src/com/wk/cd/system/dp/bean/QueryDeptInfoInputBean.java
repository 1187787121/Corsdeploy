/**
 * Title: QueryDeptInfoInputBean.java
 * File Description: ��ѯ������ϸ��Ϣ����ӿ�
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015��8��25��
 */
package com.wk.cd.system.dp.bean;

import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description: ��ѯ������ϸ��Ϣ����ӿ�
 * @author HT
 */
public class QueryDeptInfoInputBean extends ActionRootInputBean{

	private static final long serialVersionUID = -3739193845651343202L;
	
	/**
	 * ���ű���
	 */
	private String dept_id ;
	
	public static final String DEPT_IDCN = "���ű���";

	/**
	 * @return dept_id ���ű���
	 */
	public String getDept_id() {
		return this.dept_id;
	}

	/**
	 * @param dept_id ���ű���
	 */
	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}
}
