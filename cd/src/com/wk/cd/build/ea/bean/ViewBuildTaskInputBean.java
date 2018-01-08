/**
 * Title: ViewBuildTaskInputBean.java
 * File Description: �鿴�����ļ��Ķ��б�����ӿ�
 * @copyright: 2016
 * @company: CORSWORK
 * @author: chiss
 * @version: 1.0
 * @date: 2016��12��9��
 */
package com.wk.cd.build.ea.bean;

import java.util.List;

import com.wk.cd.build.ea.info.UuParamInfo;
import com.wk.cd.enu.CFG_TYPE;
import com.wk.cd.enu.SCRIPT_TYPE;
import com.wk.cd.module1.entity.PhaseParam;
import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description: �鿴�����ļ��Ķ��б�����ӿ�
 * @author chiss
 */
public class ViewBuildTaskInputBean extends ActionRootInputBean{

	private static final long serialVersionUID = -7709964226729267672L;
	
	/**
	 * ������
	 */
	private String env_name;
	
	public static final String ENV_NAMECN = "������";
	
	/**
	 * ������
	 */
	private String work_id;
	
	public static final String WORK_IDCN = "������";
	
	/**
	 * ��������ַ
	 */
	private String server_ip;
	
	public static final String SERVER_IPCN = "��������ַ";
	
	/**
	 * ��������
	 */
	private CFG_TYPE cfg_type;
	
	public static final String CFG_TYPECN = "��������";
	
	/**
	 * ����Դ����
	 */
	private String soc_name;
	
	public static final String SOC_NAMECN = "����Դ����";
	
	/**
	 * �ļ����·��
	 */
	private String relative_path;
	
	public static final String RELATIVE_PATHCN = "�ļ����·��";
	
	/**
	 * �ļ��ɱ༭��־
	 */
	private boolean mod_flag;
	
	public static final String MOD_FLAGCN = "�ļ��ɱ༭��־";
	
	/**
	 * ����������
	 */
	private int build_step_id;
	
	public static final String BUILD_STEP_IDCN = "����������";
	
	/**
	 *�ű�����
	 */
	private SCRIPT_TYPE script_type;

	public static final String SCRIPT_TYPECN = "�ű�����";
	
	/**
	 * �ű������б�
	 */
	private int[] script_type_list;
	
	public static final String SCRIPT_TYPE_LISTCN = "�ű������б�";
	
	/**
	 * ����������
	 */
	private String ce_server_name;
	
	public static final String CE_SERVER_NAMECN = "����������";
	
	/**
	 * �汾��·��
	 */
	private String ver_root_path;
	
	public static final String VER_ROOT_PATHCN = "�汾��·��";
	
	/**
	 * ģ������б�
	 */
	private List<PhaseParam> param_list;
	
	public static final String PARAM_LISTCN = "ģ������б�";
	
	/**
	 * @return env_name ������
	 */
	public String getEnv_name() {
		return env_name;
	}

	/**
	 * @param env_name ������
	 */
	public void setEnv_name(String env_name) {
		this.env_name = env_name;
	}

	/**
	 *�ű����
	 */
	private long script_bk_seq;

	public static final String SCRIPT_BK_SEQCN = "�ű����";

	/**
	 * @return work_id ������
	 */
	public String getWork_id() {
		return work_id;
	}

	/**
	 * @param work_id ������
	 */
	public void setWork_id(String work_id) {
		this.work_id = work_id;
	}

	/**
	 * @return server_ip ��������ַ
	 */
	public String getServer_ip() {
		return server_ip;
	}

	/**
	 * @param server_ip ��������ַ
	 */
	public void setServer_ip(String server_ip) {
		this.server_ip = server_ip;
	}

	/**
	 * @return cfg_type ��������
	 */
	public CFG_TYPE getCfg_type() {
		return cfg_type;
	}

	/**
	 * @param cfg_type ��������
	 */
	public void setCfg_type(CFG_TYPE cfg_type) {
		this.cfg_type = cfg_type;
	}
	
	/**
	 * @return soc_name ����Դ��
	 */
	public String getSoc_name() {
		return soc_name;
	}

	/**
	 * @param soc_name ����Դ��
	 */
	public void setSoc_name(String soc_name) {
		this.soc_name = soc_name;
	}

	/**
	 * @return relative_path �ļ����·��
	 */
	public String getRelative_path() {
		return relative_path;
	}

	/**
	 * @param relative_path �ļ����·��
	 */
	public void setRelative_path(String relative_path) {
		this.relative_path = relative_path;
	}

	/**
	 * @return mod_flag �ļ��ɱ༭��־
	 */
	public boolean isMod_flag() {
		return mod_flag;
	}

	/**
	 * @param mod_flag �ļ��ɱ༭��־
	 */
	public void setMod_flag(boolean mod_flag) {
		this.mod_flag = mod_flag;
	}

	/**
	 * @return build_step_id ����������
	 */
	public int getBuild_step_id() {
		return build_step_id;
	}

	/**
	 * @param build_step_id ����������
	 */
	public void setBuild_step_id(int build_step_id) {
		this.build_step_id = build_step_id;
	}
	
	/**
	 *@return script_type �ű�����
	 */
	public SCRIPT_TYPE getScript_type() {
		return this.script_type;
	}

	/**
	 *@param script_type �ű�����
	 */
	public void setScript_type(SCRIPT_TYPE script_type) {
		this.script_type = script_type;
	}

	/**
	 *@return script_bk_seq �ű����
	 */
	public long getScript_bk_seq() {
		return this.script_bk_seq;
	}

	/**
	 *@param script_bk_seq �ű����
	 */
	public void setScript_bk_seq(long script_bk_seq) {
		this.script_bk_seq = script_bk_seq;
	}

	/**
	 * @return script_type_list
	 */
	public int[] getScript_type_list() {
		return script_type_list;
	}

	/**
	 * @param script_type_list
	 */
	public void setScript_type_list(int[] script_type_list) {
		this.script_type_list = script_type_list;
	}

	/**
	 * @return ce_server_name ����������
	 */
	public String getCe_server_name() {
		return ce_server_name;
	}

	/**
	 * @param ce_server_name ����������
	 */
	public void setCe_server_name(String ce_server_name) {
		this.ce_server_name = ce_server_name;
	}

	/**
	 * @return ver_root_path �汾��·��
	 */
	public String getVer_root_path() {
		return ver_root_path;
	}

	/**
	 * @param ver_root_path �汾��·��
	 */
	public void setVer_root_path(String ver_root_path) {
		this.ver_root_path = ver_root_path;
	}

	/**
	 * @return param_list ģ������б�
	 */
	public List<PhaseParam> getParam_list() {
		return param_list;
	}

	/**
	 * @param param_list ģ������б�
	 */
	public void setParam_list(List<PhaseParam> param_list) {
		this.param_list = param_list;
	}
}