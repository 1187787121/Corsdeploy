/**
 * Title: ExeRemainInteTaskInputBean.java
 * File Description: һ��ִ�����¼��ɲ�������ӿ�
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2016��11��19��
 */
package com.wk.cd.build.ea.bean;

import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description: һ��ִ�����¼��ɲ�������ӿ�
 * @author Xul
 */
public class ExeRemainInteTaskInputBean extends ActionRootInputBean{

	private static final long serialVersionUID = 7078704077927981861L;
	
	/**
	 * ������
	 */
	private String work_id;

	public static final String WORK_IDCN = "������";
	
	/**
	 * ������
	 */
	private int step_id;

	public static final String STEP_IDCN = "������";
	
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
	 * @return step_id ������
	 */
	public int getStep_id() {
		return step_id;
	}

	/**
	 * @param step_id ������
	 */
	public void setStep_id(int step_id) {
		this.step_id = step_id;
	}
}
