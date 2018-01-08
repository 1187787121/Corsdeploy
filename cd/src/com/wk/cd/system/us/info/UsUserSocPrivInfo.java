/**
 * Title: UsUserSocPrivInfo.java
 * File Description: �û�����ԴȨ�ޱ�
 * @copyright: 2015
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2015-9-23
 */

package com.wk.cd.system.us.info;

import java.io.Serializable;

import com.wk.util.*;
import com.wk.cd.enu.AF_FLAG;
import com.wk.cd.enu.PRIV_TYPE;
import com.wk.db.PrimaryKey;
import com.wk.db.Table;
/**
 * Class description:�û�����ԴȨ�ޱ�
 * @author AutoGen
 */
@Table("US_USER_SOC_PRIV")
@PrimaryKey({"user_id","soc_name","priv_type"})
public class UsUserSocPrivInfo implements Serializable  {
	private static final long serialVersionUID = 1L;

	/**
	 *������
	 */
	public static final String TABLECN = "�û�����ԴȨ�ޱ�";

	/**
	 *�û���
	 */
	private String user_id;

	public static final String USER_IDCN = "�û���";

	/**
	 *����Դ����
	 */
	private String soc_name;

	public static final String SOC_NAMECN = "����Դ����";

	/**
	 *�����ֹ��־
	 */
	private AF_FLAG af_flag;

	public static final String AF_FLAGCN = "�����ֹ��־";

	/**
	 *��Դ����
	 */
	private PRIV_TYPE priv_type;

	public static final String PRIV_TYPECN = "��Դ����";

	/**
	 *��ʱȨ�޿�ʼʱ��
	 */
	private JaDateTime tempstart_bk_datetime;

	public static final String TEMPSTART_BK_DATETIMECN = "��ʱȨ�޿�ʼʱ��";

	/**
	 *��ʱȨ�޽���ʱ��
	 */
	private JaDateTime tempend_bk_datetime;

	public static final String TEMPEND_BK_DATETIMECN = "��ʱȨ�޽���ʱ��";

	/**
	 *�����ֶ�
	 */
	private String backup_fld;

	public static final String BACKUP_FLDCN = "�����ֶ�";

	/**
	 *@return user_id �û���
	 */
	public String getUser_id() {
		return this.user_id;
	}

	/**
	 *@param user_id �û���
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	/**
	 *@return soc_name ����Դ����
	 */
	public String getSoc_name() {
		return this.soc_name;
	}

	/**
	 *@param soc_name ����Դ����
	 */
	public void setSoc_name(String soc_name) {
		this.soc_name = soc_name;
	}

	/**
	 *@return af_flag �����ֹ��־
	 */
	public AF_FLAG getAf_flag() {
		return this.af_flag;
	}

	/**
	 *@param af_flag �����ֹ��־
	 */
	public void setAf_flag(AF_FLAG af_flag) {
		this.af_flag = af_flag;
	}

	/**
	 *@return priv_type ��Դ����
	 */
	public PRIV_TYPE getPriv_type() {
		return this.priv_type;
	}

	/**
	 *@param priv_type ��Դ����
	 */
	public void setPriv_type(PRIV_TYPE priv_type) {
		this.priv_type = priv_type;
	}

	/**
	 *@return tempstart_bk_datetime ��ʱȨ�޿�ʼʱ��
	 */
	public JaDateTime getTempstart_bk_datetime() {
		return this.tempstart_bk_datetime;
	}

	/**
	 *@param tempstart_bk_datetime ��ʱȨ�޿�ʼʱ��
	 */
	public void setTempstart_bk_datetime(JaDateTime tempstart_bk_datetime) {
		this.tempstart_bk_datetime = tempstart_bk_datetime;
	}

	/**
	 *@return tempend_bk_datetime ��ʱȨ�޽���ʱ��
	 */
	public JaDateTime getTempend_bk_datetime() {
		return this.tempend_bk_datetime;
	}

	/**
	 *@param tempend_bk_datetime ��ʱȨ�޽���ʱ��
	 */
	public void setTempend_bk_datetime(JaDateTime tempend_bk_datetime) {
		this.tempend_bk_datetime = tempend_bk_datetime;
	}

	/**
	 *@return backup_fld �����ֶ�
	 */
	public String getBackup_fld() {
		return this.backup_fld;
	}

	/**
	 *@param backup_fld �����ֶ�
	 */
	public void setBackup_fld(String backup_fld) {
		this.backup_fld = backup_fld;
	}

}
