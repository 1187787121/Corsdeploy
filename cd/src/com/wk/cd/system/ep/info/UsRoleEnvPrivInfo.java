/**
 * Title: UsRoleEnvPrivInfo.java
 * File Description: ���Ž�ɫӦ�û���Ȩ�ޱ�
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
 * Class description:���Ž�ɫӦ�û���Ȩ�ޱ�
 * @author AutoGen
 */
@Table("US_ROLE_ENV_PRIV")
@PrimaryKey({"dprl_code","env_name"})
public class UsRoleEnvPrivInfo {
	/**
	 *������
	 */
	public static final String TABLECN = "���Ž�ɫӦ�û���Ȩ�ޱ�";

	/**
	 *���Ž�ɫ����
	 */
	private String dprl_code;

	public static final String DPRL_CODECN = "���Ž�ɫ����";

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
	 *@return dprl_code ���Ž�ɫ����
	 */
	public String getDprl_code() {
		return this.dprl_code;
	}

	/**
	 *@param dprl_code ���Ž�ɫ����
	 */
	public void setDprl_code(String dprl_code) {
		this.dprl_code = dprl_code;
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
