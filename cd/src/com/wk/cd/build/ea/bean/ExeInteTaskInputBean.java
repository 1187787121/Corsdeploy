/**
 * Title: ExeInteTaskInputBean.java
 * File Description: һ��ִ�м�����������ӿ�
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2016��11��19��
 */
package com.wk.cd.build.ea.bean;

import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description: һ��ִ�м�����������ӿ�
 * @author Xul
 */
public class ExeInteTaskInputBean extends ActionRootInputBean{

	private static final long serialVersionUID = 8181665068936306207L;
	
	/**
	 * ������
	 */
	private String work_id;

	public static final String WORK_IDCN = "������";
	
	/**
	 * @return work_id ������
	 */
	public String getWork_id() {
		return work_id;
	}

	/**
	 * @param work_id ������
	 */
	public void setWork_id(String work_id) {
		this.work_id = work_id;
	}
}
