/**
 * Title: SvSrvInfo.java
 * File Description: �������ñ�
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-3-15
 */

package com.wk.cd.system.sv.info;

import java.io.Serializable;

import com.wk.cd.enu.APROV_TYPE;
import com.wk.cd.enu.AUTH_FLAG;
import com.wk.cd.enu.CHECK_FLAG;
import com.wk.cd.enu.FUN_TYPE;
import com.wk.cd.enu.RCD_STATE;
import com.wk.cd.enu.SALLOW_FLAG;
import com.wk.cd.enu.SOC_FLAG;
import com.wk.db.PrimaryKey;
import com.wk.db.Table;
/**
 * Class description:�������ñ�
 * @author AutoGen
 */
@Table("SV_SRV")
@PrimaryKey({"srv_name"})
public class SvSrvInfo implements Serializable  {
	private static final long serialVersionUID = 1L;

	/**
	 *������
	 */
	public static final String TABLECN = "�������ñ�";

	/**
	 *��������
	 */
	private String srv_name;

	public static final String SRV_NAMECN = "��������";

	/**
	 *�������������
	 */
	private String sup_srvg_code;

	public static final String SUP_SRVG_CODECN = "�������������";

	/**
	 *��������
	 */
	private String srv_bk_desc;

	public static final String SRV_BK_DESCCN = "��������";

	/**
	 *��������
	 */
	private FUN_TYPE srv_fun_type;

	public static final String SRV_FUN_TYPECN = "��������";

	/**
	 *��������
	 */
	private String srv_class_name;

	public static final String SRV_CLASS_NAMECN = "��������";

	/**
	 *���񷽷���
	 */
	private String srv_method_name;

	public static final String SRV_METHOD_NAMECN = "���񷽷���";

	/**
	 *�Ƿ񸴺�
	 */
	private CHECK_FLAG check_flag;

	public static final String CHECK_FLAGCN = "�Ƿ񸴺�";

	/**
	 *�Ƿ���Ȩ
	 */
	private AUTH_FLAG auth_flag;

	public static final String AUTH_FLAGCN = "�Ƿ���Ȩ";

	/**
	 *�Ƿ�������Դ
	 */
	private SOC_FLAG soc_flag;

	public static final String SOC_FLAGCN = "�Ƿ�������Դ";

	/**
	 *���������־
	 */
	private SALLOW_FLAG sallow_flag;

	public static final String SALLOW_FLAGCN = "���������־";

	/**
	 *��־����
	 */
	private int log_level;

	public static final String LOG_LEVELCN = "��־����";

	/**
	 *��¼״̬
	 */
	private RCD_STATE rcd_state;

	public static final String RCD_STATECN = "��¼״̬";

	/**
	 *����չʾ����
	 */
	private APROV_TYPE aprov_type;

	public static final String APROV_TYPECN = "����չʾ����";

	/**
	 *����ҳ����Դ����
	 */
	private String custom_rs_code;

	public static final String CUSTOM_RS_CODECN = "����ҳ����Դ����";

	/**
	 *@return srv_name ��������
	 */
	public String getSrv_name() {
		return this.srv_name;
	}

	/**
	 *@param srv_name ��������
	 */
	public void setSrv_name(String srv_name) {
		this.srv_name = srv_name;
	}

	/**
	 *@return sup_srvg_code �������������
	 */
	public String getSup_srvg_code() {
		return this.sup_srvg_code;
	}

	/**
	 *@param sup_srvg_code �������������
	 */
	public void setSup_srvg_code(String sup_srvg_code) {
		this.sup_srvg_code = sup_srvg_code;
	}

	/**
	 *@return srv_bk_desc ��������
	 */
	public String getSrv_bk_desc() {
		return this.srv_bk_desc;
	}

	/**
	 *@param srv_bk_desc ��������
	 */
	public void setSrv_bk_desc(String srv_bk_desc) {
		this.srv_bk_desc = srv_bk_desc;
	}

	/**
	 *@return srv_fun_type ��������
	 */
	public FUN_TYPE getSrv_fun_type() {
		return this.srv_fun_type;
	}

	/**
	 *@param srv_fun_type ��������
	 */
	public void setSrv_fun_type(FUN_TYPE srv_fun_type) {
		this.srv_fun_type = srv_fun_type;
	}

	/**
	 *@return srv_class_name ��������
	 */
	public String getSrv_class_name() {
		return this.srv_class_name;
	}

	/**
	 *@param srv_class_name ��������
	 */
	public void setSrv_class_name(String srv_class_name) {
		this.srv_class_name = srv_class_name;
	}

	/**
	 *@return srv_method_name ���񷽷���
	 */
	public String getSrv_method_name() {
		return this.srv_method_name;
	}

	/**
	 *@param srv_method_name ���񷽷���
	 */
	public void setSrv_method_name(String srv_method_name) {
		this.srv_method_name = srv_method_name;
	}

	/**
	 *@return check_flag �Ƿ񸴺�
	 */
	public CHECK_FLAG getCheck_flag() {
		return this.check_flag;
	}

	/**
	 *@param check_flag �Ƿ񸴺�
	 */
	public void setCheck_flag(CHECK_FLAG check_flag) {
		this.check_flag = check_flag;
	}

	/**
	 *@return auth_flag �Ƿ���Ȩ
	 */
	public AUTH_FLAG getAuth_flag() {
		return this.auth_flag;
	}

	/**
	 *@param auth_flag �Ƿ���Ȩ
	 */
	public void setAuth_flag(AUTH_FLAG auth_flag) {
		this.auth_flag = auth_flag;
	}

	/**
	 *@return soc_flag �Ƿ�������Դ
	 */
	public SOC_FLAG getSoc_flag() {
		return this.soc_flag;
	}

	/**
	 *@param soc_flag �Ƿ�������Դ
	 */
	public void setSoc_flag(SOC_FLAG soc_flag) {
		this.soc_flag = soc_flag;
	}

	/**
	 *@return sallow_flag ���������־
	 */
	public SALLOW_FLAG getSallow_flag() {
		return this.sallow_flag;
	}

	/**
	 *@param sallow_flag ���������־
	 */
	public void setSallow_flag(SALLOW_FLAG sallow_flag) {
		this.sallow_flag = sallow_flag;
	}

	/**
	 *@return log_level ��־����
	 */
	public int getLog_level() {
		return this.log_level;
	}

	/**
	 *@param log_level ��־����
	 */
	public void setLog_level(int log_level) {
		this.log_level = log_level;
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

	/**
	 *@return aprov_type ����չʾ����
	 */
	public APROV_TYPE getAprov_type() {
		return this.aprov_type;
	}

	/**
	 *@param aprov_type ����չʾ����
	 */
	public void setAprov_type(APROV_TYPE aprov_type) {
		this.aprov_type = aprov_type;
	}

	/**
	 *@return custom_rs_code ����ҳ����Դ����
	 */
	public String getCustom_rs_code() {
		return this.custom_rs_code;
	}

	/**
	 *@param custom_rs_code ����ҳ����Դ����
	 */
	public void setCustom_rs_code(String custom_rs_code) {
		this.custom_rs_code = custom_rs_code;
	}

}
