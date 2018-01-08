/**
 * Title: UsDeptRoleInfo.java
 * File Description: ���Ž�ɫ������
 * @copyright: 2015
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2015-2-9
 */

package com.wk.cd.system.us.info;

import java.io.Serializable;
import com.wk.db.PrimaryKey;
import com.wk.db.Table;
/**
 * Class description:���Ž�ɫ������
 * @author AutoGen
 */
@Table("US_DEPT_ROLE")
@PrimaryKey({"dprl_code"})
public class UsDeptRoleInfo implements Serializable  {
	private static final long serialVersionUID = 1L;

	/**
	 *������
	 */
	public static final String TABLECN = "���Ž�ɫ������";

	/**
	 *���Ž�ɫ����
	 */
	private String dprl_code;

	public static final String DPRL_CODECN = "���Ž�ɫ����";

	/**
	 *���ű���
	 */
	private String dept_id;

	public static final String DEPT_IDCN = "���ű���";

	/**
	 *��ɫ����
	 */
	private String role_code;

	public static final String ROLE_CODECN = "��ɫ����";

	/**
	 *���Ž�ɫ˵��
	 */
	private String bk_expl;

	public static final String BK_EXPLCN = "���Ž�ɫ˵��";

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
	 *@return role_code ��ɫ����
	 */
	public String getRole_code() {
		return this.role_code;
	}

	/**
	 *@param role_code ��ɫ����
	 */
	public void setRole_code(String role_code) {
		this.role_code = role_code;
	}

	/**
	 *@return bk_expl ���Ž�ɫ˵��
	 */
	public String getBk_expl() {
		return this.bk_expl;
	}

	/**
	 *@param bk_expl ���Ž�ɫ˵��
	 */
	public void setBk_expl(String bk_expl) {
		this.bk_expl = bk_expl;
	}

}
