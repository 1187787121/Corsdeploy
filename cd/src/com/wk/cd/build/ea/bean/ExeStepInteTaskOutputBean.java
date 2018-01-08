/**
 * Title: ExeStepInteTaskOutputBean.java
 * File Description: ����ִ�м�����������ӿ�
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2016��11��19��
 */
package com.wk.cd.build.ea.bean;

import com.wk.cd.bean.ActionRootOutputBean;

/**
 * Class Description: ����ִ�м�����������ӿ�
 * @author Xul
 */
public class ExeStepInteTaskOutputBean extends ActionRootOutputBean{

	private static final long serialVersionUID = 194580003728811726L;
	
	/**
	 * ��һ�����
	 */
	private int next_step;
	
	public static final String NEXT_STEPCN = "��һ�����";

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
