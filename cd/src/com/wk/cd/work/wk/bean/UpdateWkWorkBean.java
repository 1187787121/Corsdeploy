/**
 * Title: UpdateWkWorkBean.java
 * File Description:�����������Wk_Work��Ϣ�ӿ� 
 * @copyright: 2014
 * @company: CORSWORK
 * @author: tlw
 * @version: 1.0
 * @date: 2014-12-1
 */
package com.wk.cd.work.wk.bean;

import com.wk.cd.enu.FUN_TYPE;
import com.wk.cd.enu.IS_PUBLISH;
import com.wk.util.JaDate;
import com.wk.util.JaTime;

/**
 * Class Description:�����������Wk_Work��Ϣ�ӿ�  
 * @author tlw
 */
public class UpdateWkWorkBean {
	
	/**
	 * ��������
	 */
	private String work_cn_name;
	
	public static final String WORK_CN_NAMECN = "��������";
	
	/**
	 * ��������
	 */
	private String work_bk_desc;
	
	public static final String WORK_BK_DESCCN = "��������";
	
	/**
	 * ��������
	 */
	private FUN_TYPE work_fun_type;
	
	public static final String WORK_FUN_TYPECN = "��������";
	
	/**
	 * �Ƿ񷢲�
	 */
	private IS_PUBLISH is_publish;
	
	public static final String IS_PUBLISHCN = "�Ƿ񷢲�";
	
	/**
	 * �޸�����
	 */
	private JaDate modify_bk_date;
	
	public static final String MODIFY_BK_DATECN = "�޸�����";

	/**
	 * �޸�ʱ��
	 */
	private JaTime modify_bk_time;
	
	public static final String MODIFY_BK_TIMECN = "�޸�ʱ��";
	
	/**
	 * �޸��û�
	 */
	private String modify_user_id;
	
	public static final String MODIFY_USER_IDCN = "�޸��û�";
	
	/**
	 * @return work_cn_name ��������
	 */
	public String getWork_cn_name() {
		return work_cn_name;
	}

	/**
	 * @param work_cn_name ��������
	 */
	public void setWork_cn_name(String work_cn_name) {
		this.work_cn_name = work_cn_name;
	}

	/**
	 * @return work_bk_desc ��������
	 */
	public String getWork_bk_desc() {
		return work_bk_desc;
	}

	/**
	 * @param work_bk_desc ��������
	 */
	public void setWork_bk_desc(String work_bk_desc) {
		this.work_bk_desc = work_bk_desc;
	}

	/**
	 * @return work_fun_type ��������
	 */
	public FUN_TYPE getWork_fun_type() {
		return work_fun_type;
	}

	/**
	 * @param work_fun_type ��������
	 */
	public void setWork_fun_type(FUN_TYPE work_fun_type) {
		this.work_fun_type = work_fun_type;
	}

	/**
	 * @return is_publish �Ƿ񷢲�
	 */
	public IS_PUBLISH getIs_publish() {
		return is_publish;
	}

	/**
	 * @param is_publish �Ƿ񷢲�
	 */
	public void setIs_publish(IS_PUBLISH is_publish) {
		this.is_publish = is_publish;
	}

	/**
	 * @return modify_bk_date �޸�����
	 */
	public JaDate getModify_bk_date() {
		return modify_bk_date;
	}

	/**
	 * @param modifyBkDate �޸�����
	 */
	public void setModify_bk_date(JaDate modify_bk_date) {
		this.modify_bk_date = modify_bk_date;
	}

	/**
	 * @return modify_bk_time �޸�ʱ��
	 */
	public JaTime getModify_bk_time() {
		return modify_bk_time;
	}

	/**
	 * @param modifyBkTime �޸�ʱ��
	 */
	public void setModify_bk_time(JaTime modify_bk_time) {
		this.modify_bk_time = modify_bk_time;
	}

	/**
	 * @return modify_user_id �޸��û�
	 */
	public String getModify_user_id() {
		return modify_user_id;
	}

	/**
	 * @param modify_user_id �޸��û�
	 */
	public void setModify_user_id(String modify_user_id) {
		this.modify_user_id = modify_user_id;
	}
	
	
}
