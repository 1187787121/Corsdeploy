/**
 * Title: DpDeptRsPrivInfo.java
 * File Description: ������ԴȨ�ޱ�
 * @copyright: 2015
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2015-2-9
 */

package com.wk.cd.system.dp.info;

import java.io.Serializable;
import com.wk.db.PrimaryKey;
import com.wk.db.Table;
/**
 * Class description:������ԴȨ�ޱ�
 * @author AutoGen
 */
@Table("DP_DEPT_RS_PRIV")
@PrimaryKey({"dept_id","rs_code"})
public class DpDeptRsPrivInfo implements Serializable  {
	private static final long serialVersionUID = 1L;

	/**
	 *������
	 */
	public static final String TABLECN = "������ԴȨ�ޱ�";

	/**
	 *���ű���
	 */
	private String dept_id;

	public static final String DEPT_IDCN = "���ű���";

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
