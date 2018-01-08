/**
 * Title: DpDeptEnvPrivInfo.java
 * File Description: ����Ӧ�û���Ȩ�ޱ�
 * @copyright: 2017
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2017-1-4
 */

package com.wk.cd.system.ep.info;

import com.wk.db.PrimaryKey;
import com.wk.db.Table;
/**
 * Class description:����Ӧ�û���Ȩ�ޱ�
 * @author AutoGen
 */
@Table("DP_DEPT_ENV_PRIV")
@PrimaryKey({"dept_id","env_name"})
public class DpDeptEnvPrivInfo {
	/**
	 *������
	 */
	public static final String TABLECN = "����Ӧ�û���Ȩ�ޱ�";

	/**
	 *���ű���
	 */
	private String dept_id;

	public static final String DEPT_IDCN = "���ű���";

	/**
	 *��������
	 */
	private String env_name;

	public static final String ENV_NAMECN = "��������";

	/**
	 *Ӧ��ϵͳ
	 */
	private String sys_name;

	public static final String SYS_NAMECN = "Ӧ��ϵͳ";

	/**
	 *�����ֶ�
	 */
	private String backup_fld;

	public static final String BACKUP_FLDCN = "�����ֶ�";

	/**
	 *@return dept_id ���ű���
	 */
	public String getDept_id() {
		return this.dept_id;
	}

	/**
	 *@param dept_id ���ű���
	 */
	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}

	/**
	 *@return env_name ��������
	 */
	public String getEnv_name() {
		return this.env_name;
	}

	/**
	 *@param env_name ��������
	 */
	public void setEnv_name(String env_name) {
		this.env_name = env_name;
	}

	/**
	 *@return sys_name Ӧ��ϵͳ
	 */
	public String getSys_name() {
		return this.sys_name;
	}

	/**
	 *@param sys_name Ӧ��ϵͳ
	 */
	public void setSys_name(String sys_name) {
		this.sys_name = sys_name;
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
