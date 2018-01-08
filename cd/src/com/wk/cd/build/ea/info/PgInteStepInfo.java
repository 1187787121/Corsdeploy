/**
 * Title: PgInteStepInfo.java
 * File Description: ���ɷ��������
 * @copyright: 2016
 * @company: CORSWORK
 * @author AutoGen
 * @version 1.0
 * @date 2016-11-25
 */

package com.wk.cd.build.ea.info;

import java.io.Serializable;

import com.wk.cd.enu.COMPILE_TYPE;
import com.wk.cd.enu.STEP_TYPE;
import com.wk.db.PrimaryKey;
import com.wk.db.Table;
/**
 * Class description:���ɷ��������
 * @author AutoGen
 */
@Table("PG_INTE_STEP")
@PrimaryKey({"prog_id","step_id"})
public class PgInteStepInfo implements Serializable  {
	private static final long serialVersionUID = 1L;

	/**
	 *������
	 */
	public static final String TABLECN = "���ɷ��������";

	/**
	 *�������
	 */
	private String prog_id;

	public static final String PROG_IDCN = "�������";

	/**
	 *������
	 */
	private int step_id;

	public static final String STEP_IDCN = "������";

	/**
	 *����˵��
	 */
	private String step_expl;

	public static final String STEP_EXPLCN = "����˵��";

	/**
	 *��������
	 */
	private STEP_TYPE step_type;

	public static final String STEP_TYPECN = "��������";

	/**
	 *����ԴUUID
	 */
	private String soc_uuid;

	public static final String SOC_UUIDCN = "����ԴUUID";

	/**
	 *�ű�
	 */
	private String step_bk_script;

	public static final String STEP_BK_SCRIPTCN = "�ű�";

	/**
	 *��������
	 */
	private COMPILE_TYPE compile_type;

	public static final String COMPILE_TYPECN = "��������";

	/**
	 *����·��
	 */
	private String complie_bk_path;

	public static final String COMPLIE_BK_PATHCN = "����·��";

	/**
	 *��������
	 */
	private String env_variable;

	public static final String ENV_VARIABLECN = "��������";

	/**
	 *�������
	 */
	private String compile_param;

	public static final String COMPILE_PARAMCN = "�������";

	/**
	 *����嵥UUID
	 */
	private String storage_list_uuid;

	public static final String STORAGE_LIST_UUIDCN = "����嵥UUID";
	
	/**
	 *�����·��
	 */
	private String storage_bk_path;
	
	public static final String STORAGE_BK_PATHCN = "�����·��";

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
	 *@return step_id ������
	 */
	public int getStep_id() {
		return this.step_id;
	}

	/**
	 *@param step_id ������
	 */
	public void setStep_id(int step_id) {
		this.step_id = step_id;
	}

	/**
	 *@return step_expl ����˵��
	 */
	public String getStep_expl() {
		return this.step_expl;
	}

	/**
	 *@param step_expl ����˵��
	 */
	public void setStep_expl(String step_expl) {
		this.step_expl = step_expl;
	}

	/**
	 *@return step_type ��������
	 */
	public STEP_TYPE getStep_type() {
		return this.step_type;
	}

	/**
	 *@param step_type ��������
	 */
	public void setStep_type(STEP_TYPE step_type) {
		this.step_type = step_type;
	}

	/**
	 *@return soc_uuid ����ԴUUID
	 */
	public String getSoc_uuid() {
		return this.soc_uuid;
	}

	/**
	 *@param soc_uuid ����ԴUUID
	 */
	public void setSoc_uuid(String soc_uuid) {
		this.soc_uuid = soc_uuid;
	}

	/**
	 *@return step_bk_script �ű�
	 */
	public String getStep_bk_script() {
		return this.step_bk_script;
	}

	/**
	 *@param step_bk_script �ű�
	 */
	public void setStep_bk_script(String step_bk_script) {
		this.step_bk_script = step_bk_script;
	}

	/**
	 *@return compile_type ��������
	 */
	public COMPILE_TYPE getCompile_type() {
		return this.compile_type;
	}

	/**
	 *@param compile_type ��������
	 */
	public void setCompile_type(COMPILE_TYPE compile_type) {
		this.compile_type = compile_type;
	}

	/**
	 *@return complie_bk_path ����·��
	 */
	public String getComplie_bk_path() {
		return this.complie_bk_path;
	}

	/**
	 *@param complie_bk_path ����·��
	 */
	public void setComplie_bk_path(String complie_bk_path) {
		this.complie_bk_path = complie_bk_path;
	}

	/**
	 *@return env_variable ��������
	 */
	public String getEnv_variable() {
		return this.env_variable;
	}

	/**
	 *@param env_variable ��������
	 */
	public void setEnv_variable(String env_variable) {
		this.env_variable = env_variable;
	}

	/**
	 *@return compile_param �������
	 */
	public String getCompile_param() {
		return this.compile_param;
	}

	/**
	 *@param compile_param �������
	 */
	public void setCompile_param(String compile_param) {
		this.compile_param = compile_param;
	}

	/**
	 *@return storage_list_uuid ����嵥UUID
	 */
	public String getStorage_list_uuid() {
		return this.storage_list_uuid;
	}

	/**
	 *@param storage_list_uuid ����嵥UUID
	 */
	public void setStorage_list_uuid(String storage_list_uuid) {
		this.storage_list_uuid = storage_list_uuid;
	}

	/**
	 * @return storage_bk_path �����·��
	 */
	public String getStorage_bk_path() {
		return storage_bk_path;
	}

	/**
	 * @param storage_bk_path �����·��
	 */
	public void setStorage_bk_path(String storage_bk_path) {
		this.storage_bk_path = storage_bk_path;
	}

	/** 
	 * Description: 
	 * @return 
	 */
	@Override
	public String toString() {
		return "PgInteStepInfo [prog_id=" + prog_id + ", step_id=" + step_id + ", step_expl=" + step_expl + ", step_type=" + step_type + ", soc_uuid=" + soc_uuid
				+ ", step_bk_script=" + step_bk_script + ", compile_type=" + compile_type + ", complie_bk_path=" + complie_bk_path + ", env_variable=" + env_variable
				+ ", compile_param=" + compile_param + ", storage_list_uuid=" + storage_list_uuid + ", storage_bk_path=" + storage_bk_path + "]";
	}
	
	
}
