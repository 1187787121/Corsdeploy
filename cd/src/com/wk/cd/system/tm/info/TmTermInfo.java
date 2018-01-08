/**
 * Title: TmTermInfo.java
 * File Description: �ն����ñ�
 * @copyright: 2015
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2015-2-10
 */

package com.wk.cd.system.tm.info;

import java.io.Serializable;

import com.wk.util.*;
import com.wk.cd.enu.TERM_TYPE;
import com.wk.db.PrimaryKey;
import com.wk.db.Table;
/**
 * Class description:�ն����ñ�
 * @author AutoGen
 */
@Table("TM_TERM")
@PrimaryKey({"term_no"})
public class TmTermInfo implements Serializable  {
	private static final long serialVersionUID = 1L;

	/**
	 *������
	 */
	public static final String TABLECN = "�ն����ñ�";

	/**
	 *�ն˺�
	 */
	private String term_no;

	public static final String TERM_NOCN = "�ն˺�";

	/**
	 *�ն�����
	 */
	private TERM_TYPE term_type;

	public static final String TERM_TYPECN = "�ն�����";

	/**
	 *�ն�˵��
	 */
	private String term_bk_desc;

	public static final String TERM_BK_DESCCN = "�ն�˵��";

	/**
	 *�����û�
	 */
	private String crt_user_id;

	public static final String CRT_USER_IDCN = "�����û�";

	/**
	 *��������
	 */
	private String crt_dept_id;

	public static final String CRT_DEPT_IDCN = "��������";

	/**
	 *��������
	 */
	private JaDate crt_bk_date;

	public static final String CRT_BK_DATECN = "��������";

	/**
	 *����ʱ��
	 */
	private JaTime crt_bk_time;

	public static final String CRT_BK_TIMECN = "����ʱ��";

	/**
	 *����
	 */
	private String backup_fld;

	public static final String BACKUP_FLDCN = "����";

	/**
	 *@return term_no �ն˺�
	 */
	public String getTerm_no() {
		return this.term_no;
	}

	/**
	 *@param term_no �ն˺�
	 */
	public void setTerm_no(String term_no) {
		this.term_no = term_no;
	}

	/**
	 *@return term_type �ն�����
	 */
	public TERM_TYPE getTerm_type() {
		return this.term_type;
	}

	/**
	 *@param term_type �ն�����
	 */
	public void setTerm_type(TERM_TYPE term_type) {
		this.term_type = term_type;
	}

	/**
	 *@return term_bk_desc �ն�˵��
	 */
	public String getTerm_bk_desc() {
		return this.term_bk_desc;
	}

	/**
	 *@param term_bk_desc �ն�˵��
	 */
	public void setTerm_bk_desc(String term_bk_desc) {
		this.term_bk_desc = term_bk_desc;
	}

	/**
	 *@return crt_user_id �����û�
	 */
	public String getCrt_user_id() {
		return this.crt_user_id;
	}

	/**
	 *@param crt_user_id �����û�
	 */
	public void setCrt_user_id(String crt_user_id) {
		this.crt_user_id = crt_user_id;
	}

	/**
	 *@return crt_dept_id ��������
	 */
	public String getCrt_dept_id() {
		return this.crt_dept_id;
	}

	/**
	 *@param crt_dept_id ��������
	 */
	public void setCrt_dept_id(String crt_dept_id) {
		this.crt_dept_id = crt_dept_id;
	}

	/**
	 *@return crt_bk_date ��������
	 */
	public JaDate getCrt_bk_date() {
		return this.crt_bk_date;
	}

	/**
	 *@param crt_bk_date ��������
	 */
	public void setCrt_bk_date(JaDate crt_bk_date) {
		this.crt_bk_date = crt_bk_date;
	}

	/**
	 *@return crt_bk_time ����ʱ��
	 */
	public JaTime getCrt_bk_time() {
		return this.crt_bk_time;
	}

	/**
	 *@param crt_bk_time ����ʱ��
	 */
	public void setCrt_bk_time(JaTime crt_bk_time) {
		this.crt_bk_time = crt_bk_time;
	}

	/**
	 *@return backup_fld ����
	 */
	public String getBackup_fld() {
		return this.backup_fld;
	}

	/**
	 *@param backup_fld ����
	 */
	public void setBackup_fld(String backup_fld) {
		this.backup_fld = backup_fld;
	}

}
