/**
 * Title: SavePublishProgDetailInputBean.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Administrator
 * @version: 1.0
 * @date: 2016��11��14��
 */
package com.wk.cd.build.ea.bean;

import java.util.List;

import com.wk.cd.build.ea.info.UuParamInfo;
import com.wk.cd.bean.ActionRootInputBean;
import com.wk.cd.module1.entity.Program;

/**
 * Class Description: 
 * @author Administrator
 */
public class UpdatePublishProgDetailInputBean extends ActionRootInputBean{

	/** 
	 * @Fields serialVersionUID : 6896460009959766066L
	 */ 
	private static final long serialVersionUID = 6896460009959766066L;
	
	/**
	 * ��������
	 */
	private String prog_name;
	
	public static final String PROG_NAMECN = "��������";
	
	/**
	 * ��������
	 */
	private String env_name;
	
	public static final String ENV_NAMECN = "��������";
	/**
	 * �������
	 */
	private String prog_id ;
	
	public static final String PROG_IDCN = " �������";
	
	/**
	 * ����ģ��
	 */
	private String pub_template_name;
	
	public static final String PUB_TEMPLATE_NAMECN = "����ģ��";
	
	/**
	 * ����ģ��
	 */
	private String rol_template_name;
	
	public static final String ROL_TEMPLATE_NAMECN = "����ģ��";
	
	/**
	 * �����׶��б�
	 */
	private PhasePublishBean[] pub_phase_list;
	
	public static final String PUB_PHASE_LISTCN = "�����׶��б�";

	/**
	 * ���˽׶��б�
	 */
	private PhasePublishBean[] rol_phase_list;
	
	public static final String ROL_PHASE_LISTCN = "���˽׶��б�";
	
	/**
	 * ����ģ������б�
	 */
	private List<UuParamInfo> pub_param_list;
	
	public static final String PUB_PARAM_LISTCN = "����ģ������б�";
	
	/**
	 * ����ģ������б�
	 */
	private List<UuParamInfo> rol_param_list;
	
	public static final String ROL_PARAM_LISTCN = "����ģ������б�";
	
	/**
	 *�汾��������
	 */
	private String ver_server_name;

	public static final String VER_SERVER_NAMECN = "�汾��������";

	/**
	 *�汾����Դ��
	 */
	private String ver_soc_name;

	public static final String VER_SOC_NAMECN = "�汾����Դ��";

	/**
	 *Դ��汾Ŀ¼
	 */
	private String code_bk_dir;

	public static final String CODE_BK_DIRCN = "Դ��汾Ŀ¼";
	
	/**
	 * ����������Ϣ
	 */
	private Program pub_program;

	public static final String PUB_PROGRAMCN = "����������Ϣ";
	
	/**
	 * ���˷�����Ϣ
	 */
	private Program rol_program;
	
	public static final String ROL_PROGRAMCN = "���˷�����Ϣ";


	/**
	 * @return prog_id
	 */
	public String getProg_id() {
		return prog_id;
	}

	/**
	 * @param prog_id
	 */
	public void setProg_id(String prog_id) {
		this.prog_id = prog_id;
	}

	/**
	 * @return pub_template_name
	 */
	public String getPub_template_name() {
		return pub_template_name;
	}

	/**
	 * @param pub_template_name
	 */
	public void setPub_template_name(String pub_template_name) {
		this.pub_template_name = pub_template_name;
	}

	/**
	 * @return rol_template_name
	 */
	public String getRol_template_name() {
		return rol_template_name;
	}

	/**
	 * @param rol_template_name
	 */
	public void setRol_template_name(String rol_template_name) {
		this.rol_template_name = rol_template_name;
	}

	/**
	 * @return pub_phase_list
	 */
	public PhasePublishBean[] getPub_phase_list() {
		return pub_phase_list;
	}

	/**
	 * @param pub_phase_list
	 */
	public void setPub_phase_list(PhasePublishBean[] pub_phase_list) {
		this.pub_phase_list = pub_phase_list;
	}

	/**
	 * @return rol_phase_list
	 */
	public PhasePublishBean[] getRol_phase_list() {
		return rol_phase_list;
	}

	/**
	 * @param rol_phase_list
	 */
	public void setRol_phase_list(PhasePublishBean[] rol_phase_list) {
		this.rol_phase_list = rol_phase_list;
	}

	/**
	 * @return pub_param_list
	 */
	public List<UuParamInfo> getPub_param_list() {
		return pub_param_list;
	}

	/**
	 * @param pub_param_list
	 */
	public void setPub_param_list(List<UuParamInfo> pub_param_list) {
		this.pub_param_list = pub_param_list;
	}

	/**
	 * @return rol_param_list
	 */
	public List<UuParamInfo> getRol_param_list() {
		return rol_param_list;
	}

	/**
	 * @param rol_param_list
	 */
	public void setRol_param_list(List<UuParamInfo> rol_param_list) {
		this.rol_param_list = rol_param_list;
	}
	/**
	 * @return prog_name ��������
	 */
	public String getProg_name() {
		return prog_name;
	}

	/**
	 * @param prog_name ��������
	 */
	public void setProg_name(String prog_name) {
		this.prog_name = prog_name;
	}

	/**
	 * @return env_name ��������
	 */
	public String getEnv_name() {
		return env_name;
	}

	/**
	 * @param env_name ��������
	 */
	public void setEnv_name(String env_name) {
		this.env_name = env_name;
	}
	
	/**
	 *@return ver_server_name �汾��������
	 */
	public String getVer_server_name() {
		return this.ver_server_name;
	}

	/**
	 *@param ver_server_name �汾��������
	 */
	public void setVer_server_name(String ver_server_name) {
		this.ver_server_name = ver_server_name;
	}

	/**
	 *@return ver_soc_name �汾����Դ��
	 */
	public String getVer_soc_name() {
		return this.ver_soc_name;
	}

	/**
	 *@param ver_soc_name �汾����Դ��
	 */
	public void setVer_soc_name(String ver_soc_name) {
		this.ver_soc_name = ver_soc_name;
	}

	/**
	 *@return code_bk_dir Դ��汾Ŀ¼
	 */
	public String getCode_bk_dir() {
		return this.code_bk_dir;
	}

	/**
	 *@param code_bk_dir Դ��汾Ŀ¼
	 */
	public void setCode_bk_dir(String code_bk_dir) {
		this.code_bk_dir = code_bk_dir;
	}

	/**
	 * @return pub_program
	 */
	public Program getPub_program() {
		return pub_program;
	}

	/**
	 * @param pub_program
	 */
	public void setPub_program(Program pub_program) {
		this.pub_program = pub_program;
	}

	/**
	 * @return rol_program
	 */
	public Program getRol_program() {
		return rol_program;
	}

	/**
	 * @param rol_program
	 */
	public void setRol_program(Program rol_program) {
		this.rol_program = rol_program;
	}
}
