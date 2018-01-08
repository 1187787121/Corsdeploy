/**
 * Title: InteMonStepBean.java
 * File Description: ����ִ����Ϣ�ӿ�
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2016��11��19��
 */
package com.wk.cd.build.ea.bean;

import java.io.Serializable;

import com.wk.cd.enu.EXE_RESULT;
import com.wk.cd.enu.EXE_STATUS;

/**
 * Class Description: ����ִ����Ϣ�ӿ�
 * @author Xul
 */
public class InteMonStepBean implements Serializable{

	private static final long serialVersionUID = -5000300583787168388L;
	
	/**
	 * ������
	 */
	private int step_id;

	public static final String STEP_IDCN = "������";
	
	/**
	 * ����˵��
	 */
	private String step_expl;

	public static final String STEP_EXPLCN = "����˵��";
	
	/**
	 * ִ��״̬
	 */
	private EXE_STATUS exe_status;

	public static final String EXE_STATUSCN = "ִ��״̬";
	
	/**
	 * ִ�н��
	 */
	private EXE_RESULT task_exe_result;

	public static final String TASK_EXE_RESULTCN = "ִ�н��";

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

	/**
	 * @return step_expl ����˵��
	 */
	public String getStep_expl() {
		return step_expl;
	}

	/**
	 * @param step_expl ����˵��
	 */
	public void setStep_expl(String step_expl) {
		this.step_expl = step_expl;
	}

	/**
	 * @return exe_status ִ��״̬
	 */
	public EXE_STATUS getExe_status() {
		return exe_status;
	}

	/**
	 * @param exe_status ִ��״̬
	 */
	public void setExe_status(EXE_STATUS exe_status) {
		this.exe_status = exe_status;
	}

	/**
	 * @return task_exe_result ִ�н��
	 */
	public EXE_RESULT getTask_exe_result() {
		return task_exe_result;
	}

	/**
	 * @param task_exe_result ִ�н��
	 */
	public void setTask_exe_result(EXE_RESULT task_exe_result) {
		this.task_exe_result = task_exe_result;
	}
}
