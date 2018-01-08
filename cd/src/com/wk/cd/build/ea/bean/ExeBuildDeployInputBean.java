/**
 * Title: ExeBuildDeployInputBean.java
 * File Description: һ��ִ�й���Ӧ�ò�������ӿ�
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2016��12��12��
 */
package com.wk.cd.build.ea.bean;

import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description: һ��ִ�й���Ӧ�ò�������ӿ�
 * @author Xul
 */
public class ExeBuildDeployInputBean extends ActionRootInputBean{

	private static final long serialVersionUID = -5537669123662300955L;
	
	/**
	 * ������
	 */
	private String work_id;

	public static final String WORK_IDCN = "������";
	
	/**
	 * �׶α��
	 */
	private int phase_id;

	public static final String PHASE_IDCN = "�׶α��";
	
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
	 * @return phase_id �׶α��
	 */
	public int getPhase_id() {
		return phase_id;
	}

	/**
	 * @param phase_id �׶α��
	 */
	public void setPhase_id(int phase_id) {
		this.phase_id = phase_id;
	}
}
