/**
 * Title: PgReleInfo.java
 * File Description: ����������չ��
 * @copyright: 2017
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2017-1-3
 */

package com.wk.cd.build.ea.info;

import java.io.Serializable;
import com.wk.db.PrimaryKey;
import com.wk.db.Table;
/**
 * Class description:����������չ��
 * @author AutoGen
 */
@Table("PG_RELE")
@PrimaryKey({"prog_id"})
public class PgReleInfo implements Serializable  {
	private static final long serialVersionUID = 1L;

	/**
	 *������
	 */
	public static final String TABLECN = "����������չ��";

	/**
	 *�������
	 */
	private String prog_id;

	public static final String PROG_IDCN = "�������";

	/**
	 *����ģ��
	 */
	private String pub_template_name;

	public static final String PUB_TEMPLATE_NAMECN = "����ģ��";

	/**
	 *����ģ�����UUID
	 */
	private String publ_param_uuid;

	public static final String PUBL_PARAM_UUIDCN = "����ģ�����UUID";

	/**
	 *����ģ��
	 */
	private String rol_template_name;

	public static final String ROL_TEMPLATE_NAMECN = "����ģ��";

	/**
	 *����ģ�����UUID
	 */
	private String roll_param_uuid;

	public static final String ROLL_PARAM_UUIDCN = "����ģ�����UUID";
	
	/**
	 * �汾������ԴUUID
	 */
	private String ver_soc_uuid;
	
	public static final String VER_SOC_UUIDCN = "�汾������ԴUUID";

	/**
	 *@return prog_id �������
	 */
	public String getProg_id() {
		return this.prog_id;
	}

	/**
	 *@param prog_id �������
	 */
	public void setProg_id(String prog_id) {
		this.prog_id = prog_id;
	}

	/**
	 *@return pub_template_name ����ģ��
	 */
	public String getPub_template_name() {
		return this.pub_template_name;
	}

	/**
	 *@param pub_template_name ����ģ��
	 */
	public void setPub_template_name(String pub_template_name) {
		this.pub_template_name = pub_template_name;
	}

	/**
	 *@return publ_param_uuid ����ģ�����UUID
	 */
	public String getPubl_param_uuid() {
		return this.publ_param_uuid;
	}

	/**
	 *@param publ_param_uuid ����ģ�����UUID
	 */
	public void setPubl_param_uuid(String publ_param_uuid) {
		this.publ_param_uuid = publ_param_uuid;
	}

	/**
	 *@return rol_template_name ����ģ��
	 */
	public String getRol_template_name() {
		return this.rol_template_name;
	}

	/**
	 *@param rol_template_name ����ģ��
	 */
	public void setRol_template_name(String rol_template_name) {
		this.rol_template_name = rol_template_name;
	}

	/**
	 *@return roll_param_uuid ����ģ�����UUID
	 */
	public String getRoll_param_uuid() {
		return this.roll_param_uuid;
	}

	/**
	 *@param roll_param_uuid ����ģ�����UUID
	 */
	public void setRoll_param_uuid(String roll_param_uuid) {
		this.roll_param_uuid = roll_param_uuid;
	}

	public String getVer_soc_uuid() {
		return ver_soc_uuid;
	}

	public void setVer_soc_uuid(String ver_soc_uuid) {
		this.ver_soc_uuid = ver_soc_uuid;
	}

}
