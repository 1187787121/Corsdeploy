/**
 * Title: ViewInteInputBean.java
 * File Description: ��������鿴��������ӿ�
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2016��11��19��
 */
package com.wk.cd.build.ea.bean;

import com.wk.cd.enu.TASK_ALL_TYPE;
import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description: ��������鿴��������ӿ�
 * @author Xul
 */
public class ViewInteInputBean extends ActionRootInputBean{

	private static final long serialVersionUID = -2715713132973262480L;
	
	/**
	 * ������(���ܰ��������)
	 */
	private String work_id;

	public static final String WORK_IDCN = "������";
	
	/**
	 * ����ȫ����
	 */
	private TASK_ALL_TYPE task_all_type;
	
	public static final String TASK_ALL_TYPECN = "����ȫ����";
	
	/**
	 * �����б�
	 */
	private String[] pac_list;
	
	public static final String PAC_LISTCN = "�����б�";
	
	/**
	 * ������
	 */
	private String env_name;
	
	public static final String ENV_NAMECN = "������";
	
	/**
	 * �������
	 */
	private String prog_id;
	
	public static final String PROG_IDCN = "�������";
	
	/**
	 *�汾����Դ��汾��
	 */
	private String vercode_ver_num;

	public static final String VERCODE_VER_NUMCN = "�汾����Դ��汾��";
	
	/**
	 * Ŀ��汾����Դ��
	 */
	private String tag_soc_name;
	
	public static final String TAG_SOC_NAMECN = "Ŀ��汾����Դ��";
	
	/**
	 * Ŀ��汾Ŀ¼
	 */
	private String tag_bk_dir;
	
	public static final String TAG_BK_DIRCN = "Ŀ��汾Ŀ¼";

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
	 * @return task_all_type ����ȫ����
	 */
	public TASK_ALL_TYPE getTask_all_type() {
		return task_all_type;
	}

	/**
	 * @param task_all_type ����ȫ����
	 */
	public void setTask_all_type(TASK_ALL_TYPE task_all_type) {
		this.task_all_type = task_all_type;
	}

	/**
	 * @return pac_list �����б�
	 */
	public String[] getPac_list() {
		return pac_list;
	}

	/**
	 * @param pac_list �����б�
	 */
	public void setPac_list(String[] pac_list) {
		this.pac_list = pac_list;
	}

	/**
	 * @return env_name ������
	 */
	public String getEnv_name() {
		return env_name;
	}

	/**
	 * @param env_name ������
	 */
	public void setEnv_name(String env_name) {
		this.env_name = env_name;
	}

	/**
	 * @return prog_id �������
	 */
	public String getProg_id() {
		return prog_id;
	}

	/**
	 * @param prog_id �������
	 */
	public void setProg_id(String prog_id) {
		this.prog_id = prog_id;
	}
	
	/**
	 *@return vercode_ver_num �汾����Դ��汾��
	 */
	public String getVercode_ver_num() {
		return this.vercode_ver_num;
	}

	/**
	 *@param vercode_ver_num �汾����Դ��汾��
	 */
	public void setVercode_ver_num(String vercode_ver_num) {
		this.vercode_ver_num = vercode_ver_num;
	}

	/**
	 * @return tag_soc_name Ŀ��汾����Դ��
	 */
	public String getTag_soc_name() {
		return tag_soc_name;
	}

	/**
	 * @param tag_soc_name Ŀ��汾����Դ��
	 */
	public void setTag_soc_name(String tag_soc_name) {
		this.tag_soc_name = tag_soc_name;
	}

	/**
	 * @return tag_bk_dir Ŀ��汾Ŀ¼
	 */
	public String getTag_bk_dir() {
		return tag_bk_dir;
	}

	/**
	 * @param tag_bk_dir Ŀ��汾Ŀ¼
	 */
	public void setTag_bk_dir(String tag_bk_dir) {
		this.tag_bk_dir = tag_bk_dir;
	}
}
