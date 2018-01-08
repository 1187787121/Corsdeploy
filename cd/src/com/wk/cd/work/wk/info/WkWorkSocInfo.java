/**
 * Title: WkWorkSocInfo.java
 * File Description: ��������Դ���ñ�
 * @copyright: 2015
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2015-2-9
 */

package com.wk.cd.work.wk.info;

import java.io.Serializable;
import com.wk.db.PrimaryKey;
import com.wk.db.Table;
/**
 * Class description:��������Դ���ñ�
 * @author AutoGen
 */
@Table("WK_WORK_SOC")
@PrimaryKey({"work_code","soc_name"})
public class WkWorkSocInfo implements Serializable  {
	private static final long serialVersionUID = 1L;

	/**
	 *������
	 */
	public static final String TABLECN = "��������Դ���ñ�";

	/**
	 *�������
	 */
	private String work_code;

	public static final String WORK_CODECN = "�������";

	/**
	 *����Դ����
	 */
	private String soc_name;

	public static final String SOC_NAMECN = "����Դ����";

	/**
	 *
	 */
	private String backup_fld;

	public static final String BACKUP_FLDCN = "";

	/**
	 *@return work_code �������
	 */
	public String getWork_code() {
		return this.work_code;
	}

	/**
	 *@param work_code �������
	 */
	public void setWork_code(String work_code) {
		this.work_code = work_code;
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
	 *@return backup_fld 
	 */
	public String getBackup_fld() {
		return this.backup_fld;
	}

	/**
	 *@param backup_fld 
	 */
	public void setBackup_fld(String backup_fld) {
		this.backup_fld = backup_fld;
	}

}
