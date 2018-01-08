/**
 * Title: RsOptInfo.java
 * File Description: ��Դ������Ϣ��
 * @copyright: 2015
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2015-6-5
 */

package com.wk.cd.system.rs.info;

import com.wk.util.*;
import com.wk.cd.enu.RCD_STATE;
import com.wk.db.PrimaryKey;
import com.wk.db.Table;
/**
 * Class description:��Դ������Ϣ��
 * @author AutoGen
 */
@Table("RS_OPT")
@PrimaryKey({"opt_code"})
public class RsOptInfo {
	/**
	 *������
	 */
	public static final String TABLECN = "��Դ������Ϣ��";

	/**
	 *��������
	 */
	private String opt_code;

	public static final String OPT_CODECN = "��������";

	/**
	 *������Դ
	 */
	private String bl_rs_code;

	public static final String BL_RS_CODECN = "������Դ";

	/**
	 *��������
	 */
	private String opt_name;

	public static final String OPT_NAMECN = "��������";

	/**
	 *����˵��
	 */
	private String opt_bk_expl;

	public static final String OPT_BK_EXPLCN = "����˵��";

	/**
	 *���ñ��ʽ
	 */
	private String dis_express;

	public static final String DIS_EXPRESSCN = "���ñ��ʽ";

	/**
	 *�����û�
	 */
	private String crt_user_id;

	public static final String CRT_USER_IDCN = "�����û�";

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
	 *�����ֶ�
	 */
	private String backup_fld;

	public static final String BACKUP_FLDCN = "�����ֶ�";

	/**
	 *��¼״̬
	 */
	private RCD_STATE rcd_state;

	public static final String RCD_STATECN = "��¼״̬";

	/**
	 *@return opt_code ��������
	 */
	public String getOpt_code() {
		return this.opt_code;
	}

	/**
	 *@param opt_code ��������
	 */
	public void setOpt_code(String opt_code) {
		this.opt_code = opt_code;
	}

	/**
	 *@return bl_rs_code ������Դ
	 */
	public String getBl_rs_code() {
		return this.bl_rs_code;
	}

	/**
	 *@param bl_rs_code ������Դ
	 */
	public void setBl_rs_code(String bl_rs_code) {
		this.bl_rs_code = bl_rs_code;
	}

	/**
	 *@return opt_name ��������
	 */
	public String getOpt_name() {
		return this.opt_name;
	}

	/**
	 *@param opt_name ��������
	 */
	public void setOpt_name(String opt_name) {
		this.opt_name = opt_name;
	}

	/**
	 *@return opt_bk_expl ����˵��
	 */
	public String getOpt_bk_expl() {
		return this.opt_bk_expl;
	}

	/**
	 *@param opt_bk_expl ����˵��
	 */
	public void setOpt_bk_expl(String opt_bk_expl) {
		this.opt_bk_expl = opt_bk_expl;
	}

	/**
	 *@return dis_express ���ñ��ʽ
	 */
	public String getDis_express() {
		return this.dis_express;
	}

	/**
	 *@param dis_express ���ñ��ʽ
	 */
	public void setDis_express(String dis_express) {
		this.dis_express = dis_express;
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

	/**
	 *@return rcd_state ��¼״̬
	 */
	public RCD_STATE getRcd_state() {
		return this.rcd_state;
	}

	/**
	 *@param rcd_state ��¼״̬
	 */
	public void setRcd_state(RCD_STATE rcd_state) {
		this.rcd_state = rcd_state;
	}

}
