/**
 * Title: UsRoleSqlPrivInfo.java
 * File Description: ���Ž�ɫSQL����Ȩ�ޱ�
 * @copyright: 2015
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2015-2-9
 */

package com.wk.cd.system.us.info;

import java.io.Serializable;

import com.wk.cd.enu.PRIV_FLAG;
import com.wk.db.PrimaryKey;
import com.wk.db.Table;
/**
 * Class description:���Ž�ɫSQL����Ȩ�ޱ�
 * @author AutoGen
 */
@Table("US_ROLE_SQL_PRIV")
@PrimaryKey({"dprl_code","soc_name","tbl_name"})
public class UsRoleSqlPrivInfo implements Serializable  {
	private static final long serialVersionUID = 1L;

	/**
	 *������
	 */
	public static final String TABLECN = "���Ž�ɫSQL����Ȩ�ޱ�";

	/**
	 *���Ž�ɫ����
	 */
	private String dprl_code;

	public static final String DPRL_CODECN = "���Ž�ɫ����";

	/**
	 *����Դ����
	 */
	private String soc_name;

	public static final String SOC_NAMECN = "����Դ����";

	/**
	 *����
	 */
	private String tbl_name;

	public static final String TBL_NAMECN = "����";

	/**
	 *InsertȨ��
	 */
	private PRIV_FLAG ins_priv_flag;

	public static final String INS_PRIV_FLAGCN = "InsertȨ��";

	/**
	 *DeleteȨ��
	 */
	private PRIV_FLAG del_priv_flag;

	public static final String DEL_PRIV_FLAGCN = "DeleteȨ��";

	/**
	 *UpdateȨ��
	 */
	private PRIV_FLAG upd_priv_flag;

	public static final String UPD_PRIV_FLAGCN = "UpdateȨ��";

	/**
	 *SelectȨ��
	 */
	private PRIV_FLAG sel_priv_flag;

	public static final String SEL_PRIV_FLAGCN = "SelectȨ��";

	/**
	 *
	 */
	private String backup_fld;

	public static final String BACKUP_FLDCN = "";

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
	 *@return tbl_name ����
	 */
	public String getTbl_name() {
		return this.tbl_name;
	}

	/**
	 *@param tbl_name ����
	 */
	public void setTbl_name(String tbl_name) {
		this.tbl_name = tbl_name;
	}

	/**
	 *@return ins_priv_flag InsertȨ��
	 */
	public PRIV_FLAG getIns_priv_flag() {
		return this.ins_priv_flag;
	}

	/**
	 *@param ins_priv_flag InsertȨ��
	 */
	public void setIns_priv_flag(PRIV_FLAG ins_priv_flag) {
		this.ins_priv_flag = ins_priv_flag;
	}

	/**
	 *@return del_priv_flag DeleteȨ��
	 */
	public PRIV_FLAG getDel_priv_flag() {
		return this.del_priv_flag;
	}

	/**
	 *@param del_priv_flag DeleteȨ��
	 */
	public void setDel_priv_flag(PRIV_FLAG del_priv_flag) {
		this.del_priv_flag = del_priv_flag;
	}

	/**
	 *@return upd_priv_flag UpdateȨ��
	 */
	public PRIV_FLAG getUpd_priv_flag() {
		return this.upd_priv_flag;
	}

	/**
	 *@param upd_priv_flag UpdateȨ��
	 */
	public void setUpd_priv_flag(PRIV_FLAG upd_priv_flag) {
		this.upd_priv_flag = upd_priv_flag;
	}

	/**
	 *@return sel_priv_flag SelectȨ��
	 */
	public PRIV_FLAG getSel_priv_flag() {
		return this.sel_priv_flag;
	}

	/**
	 *@param sel_priv_flag SelectȨ��
	 */
	public void setSel_priv_flag(PRIV_FLAG sel_priv_flag) {
		this.sel_priv_flag = sel_priv_flag;
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
