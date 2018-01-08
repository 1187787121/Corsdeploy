/**
 * Title: WkWorkRsInfo.java
 * File Description: ������Դ���ñ�
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
 * Class description:������Դ���ñ�
 * @author AutoGen
 */
@Table("WK_WORK_RS")
@PrimaryKey({"work_code","rs_code"})
public class WkWorkRsInfo implements Serializable  {
	private static final long serialVersionUID = 1L;

	/**
	 *������
	 */
	public static final String TABLECN = "������Դ���ñ�";

	/**
	 *�������
	 */
	private String work_code;

	public static final String WORK_CODECN = "�������";

	/**
	 *��Դ����
	 */
	private String rs_code;

	public static final String RS_CODECN = "��Դ����";

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
	 *@return rs_code ��Դ����
	 */
	public String getRs_code() {
		return this.rs_code;
	}

	/**
	 *@param rs_code ��Դ����
	 */
	public void setRs_code(String rs_code) {
		this.rs_code = rs_code;
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
