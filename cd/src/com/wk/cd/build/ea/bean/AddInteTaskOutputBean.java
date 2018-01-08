/**
 * Title: AddInteTaskOutputBean.java
 * File Description: ���漯����������ӿ�
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2016��11��17��
 */
package com.wk.cd.build.ea.bean;

import com.wk.cd.bean.ActionRootOutputBean;

/**
 * Class Description: ���漯����������ӿ�
 * @author Xul
 */
public class AddInteTaskOutputBean extends ActionRootOutputBean{

	private static final long serialVersionUID = -1098820849834689686L;
	
	/**
	 * ������
	 */
	private String work_id;

	public static final String WORK_IDCN = "������";
	
	/**
	 * ��һ�����
	 */
	private int next_step;
	
	public static final String NEXT_STEPCN = "��һ�����";

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

	/**
	 * @return next_step ��һ�����
	 */
	public int getNext_step() {
		return next_step;
	}

	/**
	 * @param next_step ��һ�����
	 */
	public void setNext_step(int next_step) {
		this.next_step = next_step;
	}
}
