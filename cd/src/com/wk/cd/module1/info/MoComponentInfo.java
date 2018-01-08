/**
 * Title: MoComponentInfo.java
 * File Description: �����Ϣ��
 * @copyright: 2017
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2017-4-6
 */

package com.wk.cd.module1.info;

import java.io.Serializable;

import com.wk.cd.module1.enu.COMPONENT_SOURCE;
import com.wk.cd.module1.enu.MODULE_TYPE;
import com.wk.cd.enu.IMPL_TYPE;
import com.wk.cd.enu.PUBLISH_STATE;
import com.wk.db.PrimaryKey;
import com.wk.db.Table;
import com.wk.util.JaDate;
import com.wk.util.JaTime;

/**
 * Class description:�����Ϣ��
 * @author AutoGen
 */
@Table("MO_COMPONENT")
@PrimaryKey({ "component_id" })
public class MoComponentInfo
		implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ������
	 */
	public static final String TABLECN = "�����Ϣ��";

	/**
	 * ���ID
	 */
	private String component_id;

	public static final String COMPONENT_IDCN = "���ID";

	/**
	 * ���������
	 */
	private String component_cn_name;

	public static final String COMPONENT_CN_NAMECN = "���������";

	/**
	 * �������
	 */
	private String component_bk_desc;

	public static final String COMPONENT_BK_DESCCN = "�������";

	/**
	 * �������
	 */
	private MODULE_TYPE module_type;

	public static final String MODULE_TYPECN = "�������";

	/**
	 * �����;
	 */
	private String component_purposes;

	public static final String COMPONENT_PURPOSESCN = "�����;";

	/**
	 * �ű���Դ
	 */
	private COMPONENT_SOURCE component_source;

	public static final String SCRIPT_SOURCECN = "�ű���Դ";

	/**
	 * ���ʵ������
	 */
	private IMPL_TYPE impl_type;

	public static final String IMPL_TYPECN = "���ʵ������";

	/**
	 * ����״̬
	 */
	private PUBLISH_STATE publish_state;

	public static final String PUBLISH_STATECN = "����״̬";

	/**
	 * ������
	 */
	private String crt_user_id;

	public static final String CRT_USER_IDCN = "������";

	/**
	 * ��������
	 */
	private JaDate crt_bk_date;

	public static final String CRT_BK_DATECN = "��������";

	/**
	 * ����ʱ��
	 */
	private JaTime crt_bk_time;

	public static final String CRT_BK_TIMECN = "����ʱ��";

	/**
	 * �޸���
	 */
	private String modify_user_id;

	public static final String MODIFY_USER_IDCN = "�޸���";

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
	 * �����ֶ�
	 */
	private String backup_fld;

	public static final String BACKUP_FLDCN = "�����ֶ�";

	/**
	 * @return component_id ���ID
	 */
	public String getComponent_id() {
		return this.component_id;
	}

	/**
	 * @param component_id ���ID
	 */
	public void setComponent_id(String component_id) {
		this.component_id = component_id;
	}

	/**
	 * @return component_cn_name ���������
	 */
	public String getComponent_cn_name() {
		return this.component_cn_name;
	}

	/**
	 * @param component_cn_name ���������
	 */
	public void setComponent_cn_name(String component_cn_name) {
		this.component_cn_name = component_cn_name;
	}

	/**
	 * @return component_bk_desc �������
	 */
	public String getComponent_bk_desc() {
		return this.component_bk_desc;
	}

	/**
	 * @param component_bk_desc �������
	 */
	public void setComponent_bk_desc(String component_bk_desc) {
		this.component_bk_desc = component_bk_desc;
	}

	/**
	 * @return module_type �������
	 */
	public MODULE_TYPE getModule_type() {
		return this.module_type;
	}

	/**
	 * @param module_type �������
	 */
	public void setModule_type(MODULE_TYPE module_type) {
		this.module_type = module_type;
	}

	/**
	 * @return component_purposes �����;
	 */
	public String getComponent_purposes() {
		return this.component_purposes;
	}

	/**
	 * @param component_purposes �����;
	 */
	public void setComponent_purposes(String component_purposes) {
		this.component_purposes = component_purposes;
	}

	/**
	 * @return component_source �ű���Դ
	 */
	public COMPONENT_SOURCE getComponent_source() {
		return this.component_source;
	}

	/**
	 * @param component_source �ű���Դ
	 */
	public void setComponent_source(COMPONENT_SOURCE component_source) {
		this.component_source = component_source;
	}

	/**
	 * @return impl_type ���ʵ������
	 */
	public IMPL_TYPE getImpl_type() {
		return this.impl_type;
	}

	/**
	 * @param impl_type ���ʵ������
	 */
	public void setImpl_type(IMPL_TYPE impl_type) {
		this.impl_type = impl_type;
	}

	/**
	 * @return publish_state ����״̬
	 */
	public PUBLISH_STATE getPublish_state() {
		return this.publish_state;
	}

	/**
	 * @param publish_state ����״̬
	 */
	public void setPublish_state(PUBLISH_STATE publish_state) {
		this.publish_state = publish_state;
	}

	/**
	 * @return crt_user_id ������
	 */
	public String getCrt_user_id() {
		return this.crt_user_id;
	}

	/**
	 * @param crt_user_id ������
	 */
	public void setCrt_user_id(String crt_user_id) {
		this.crt_user_id = crt_user_id;
	}

	/**
	 * @return crt_bk_date ��������
	 */
	public JaDate getCrt_bk_date() {
		return this.crt_bk_date;
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
		return this.crt_bk_time;
	}

	/**
	 * @param crt_bk_time ����ʱ��
	 */
	public void setCrt_bk_time(JaTime crt_bk_time) {
		this.crt_bk_time = crt_bk_time;
	}

	/**
	 * @return modify_user_id �޸���
	 */
	public String getModify_user_id() {
		return this.modify_user_id;
	}

	/**
	 * @param modify_user_id �޸���
	 */
	public void setModify_user_id(String modify_user_id) {
		this.modify_user_id = modify_user_id;
	}

	/**
	 * @return modify_bk_date �޸�����
	 */
	public JaDate getModify_bk_date() {
		return this.modify_bk_date;
	}

	/**
	 * @param modify_bk_date �޸�����
	 */
	public void setModify_bk_date(JaDate modify_bk_date) {
		this.modify_bk_date = modify_bk_date;
	}

	/**
	 * @return modify_bk_time �޸�ʱ��
	 */
	public JaTime getModify_bk_time() {
		return this.modify_bk_time;
	}

	/**
	 * @param modify_bk_time �޸�ʱ��
	 */
	public void setModify_bk_time(JaTime modify_bk_time) {
		this.modify_bk_time = modify_bk_time;
	}

	/**
	 * @return backup_fld �����ֶ�
	 */
	public String getBackup_fld() {
		return this.backup_fld;
	}

	/**
	 * @param backup_fld �����ֶ�
	 */
	public void setBackup_fld(String backup_fld) {
		this.backup_fld = backup_fld;
	}

}
