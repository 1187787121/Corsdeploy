/**
 * Title: PubTaskBean.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: chiss
 * @version: 1.0
 * @date: 2016��11��17��
 */
package com.wk.cd.build.ea.bean;

import com.wk.cd.enu.EXE_RESULT;
import com.wk.cd.enu.TASK_STATUS;
import com.wk.cd.enu.YN_FLAG;
import com.wk.util.JaDate;
import com.wk.util.JaTime;

/**
 * Class Description: 
 * @author chiss
 */
public class PubTaskBean {
	/**
	 * ������
	 */
	private String work_id;
	
	public static final String WORK_IDCN = "������";
	
	/**
	 * ����Ŀ��汾��
	 */
	private String target_ver_num;
	
	public static final String TARGET_VER_NUMCN = "����Ŀ��汾��";
	
	/**
	 * �����б�
	 */
	private String[] package_list;
	
	public static final String PACKAGE_LISTCN = "�����б�";
	
	/**
	 * ִ����־��
	 */
	private String exelog_name;
	
	public static final String EXELOG_NAMECN = "ִ����־��";
	
	/**
	 * ����״̬
	 */
	private TASK_STATUS task_status;
	
	public static final String TASK_STATUSCN = "����״̬";
	
	/**
	 * ִ�н��
	 */
	private EXE_RESULT exe_result;
	
	public static final String EXE_RESULTCN = "ִ�н��";
	
	/**
	 * �Ƿ��л���ʵ��
	 */
	private YN_FLAG rol_inst_flag;
	
	public static final String ROL_INST_FLAGCN = "�Ƿ��л���ʵ��";
	
	/**
	 *��������
	 */
	private JaDate crt_bk_date;

	public static final String CRT_BK_DATECN = "��������";

	/**
	 *����ʱ��
	 */
	private JaTime crt_bk_time;

	public static final String CRT_BK_TIMECN = "����ʱ��";

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
	 * @return target_ver_num ����Ŀ��汾��
	 */
	public String getTarget_ver_num() {
		return target_ver_num;
	}

	/**
	 * @param target_ver_num ����Ŀ��汾��
	 */
	public void setTarget_ver_num(String target_ver_num) {
		this.target_ver_num = target_ver_num;
	}

	/**
	 * @return package_list �����б�
	 */
	public String[] getPackage_list() {
		return package_list;
	}

	/**
	 * @param package_list
	 */
	public void setPackage_list(String[] package_list) {
		this.package_list = package_list;
	}

	/**
	 * @return exelog_name ִ����־��
	 */
	public String getExelog_name() {
		return exelog_name;
	}


	/**
	 * @param exelog_name ִ����־��
	 */
	public void setExelog_name(String exelog_name) {
		this.exelog_name = exelog_name;
	}

	/**
	 * @return task_status ����״̬
	 */
	public TASK_STATUS getTask_status() {
		return task_status;
	}

	/**
	 * @param task_status ����״̬
	 */
	public void setTask_status(TASK_STATUS task_status) {
		this.task_status = task_status;
	}

	/**
	 * @return exe_result ִ�н��
	 */
	public EXE_RESULT getExe_result() {
		return exe_result;
	}

	/**
	 * @param exe_result ִ�н��
	 */
	public void setExe_result(EXE_RESULT exe_result) {
		this.exe_result = exe_result;
	}

	/**
	 * @return rol_inst_flag �Ƿ��л���ʵ��
	 */
	public YN_FLAG getRol_inst_flag() {
		return rol_inst_flag;
	}

	/**
	 * @param rol_inst_flag �Ƿ��л���ʵ��
	 */
	public void setRol_inst_flag(YN_FLAG rol_inst_flag) {
		this.rol_inst_flag = rol_inst_flag;
	}

	/**
	 * @return crt_bk_date ��������
	 */
	public JaDate getCrt_bk_date() {
		return crt_bk_date;
	}

	/**
	 * @param crt_bk_date ��������
	 */
	public void setCrt_bk_date(JaDate crt_bk_date) {
		this.crt_bk_date = crt_bk_date;
	}

	/**
	 * @return crt_bk_time ����ʱ��
	 */
	public JaTime getCrt_bk_time() {
		return crt_bk_time;
	}

	/**
	 * @param crt_bk_time ����ʱ��
	 */ 
	public void setCrt_bk_time(JaTime crt_bk_time) {
		this.crt_bk_time = crt_bk_time;
	}
}
