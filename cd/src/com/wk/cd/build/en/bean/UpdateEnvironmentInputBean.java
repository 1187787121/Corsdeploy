/**
 * Title: UpdateEnvironmentInputBean.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: xuph
 * @version: 1.0
 * @date: 2016��10��31��
 */
package com.wk.cd.build.en.bean;

import java.util.List;

import com.wk.cd.enu.DT_RANGE;
import com.wk.cd.enu.ENV_TYPE;
import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description: 
 * @author xuph
 */
public class UpdateEnvironmentInputBean extends ActionRootInputBean{

	/** 
	 * @Fields serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = -3735621493454802823L;


	/**
	 *��������
	 */
	private String env_name;

	public static final String ENV_NAMECN = "��������";

	/**
	 *�������
	 */
	private String env_cn_name;

	public static final String ENV_CN_NAMECN = "�������";

	/**
	 *��������
	 */
	private String env_bk_desc;

	public static final String ENV_BK_DESCCN = "��������";
	
	/**
	 *��������
	 */
	private ENV_TYPE env_type;

	public static final String ENV_TYPECN = "��������";

	/**
	 *Ӧ��ϵͳ
	 */
	private String sys_name;

	public static final String SYS_NAMECN = "Ӧ��ϵͳ";
	
	/**
	 * Ӧ�÷����б�
	 */
	private List<ServerBean> server_list;
	
	public static final String SERVER_LISTCN = "Ӧ�÷����б�";
	
	/**
	 * ����Ҫ��
	 */
	private String[] ele_type;
	
	public static final String ELE_TYPECN = "����Ҫ��";
	
	/**
	 * ���ݷ�Χ
	 */
	private DT_RANGE dt_range;
	
	public static final String DT_RANGECN = "���ݷ�Χ";
	
	/**
	 *@return env_name ��������
	 */
	public String getEnv_name() {
		return this.env_name;
	}

	/**
	 *@param env_name ��������
	 */
	public void setEnv_name(String env_name) {
		this.env_name = env_name;
	}

	/**
	 *@return env_cn_name �������
	 */
	public String getEnv_cn_name() {
		return this.env_cn_name;
	}

	/**
	 *@param env_cn_name �������
	 */
	public void setEnv_cn_name(String env_cn_name) {
		this.env_cn_name = env_cn_name;
	}

	/**
	 *@return env_bk_desc ��������
	 */
	public String getEnv_bk_desc() {
		return this.env_bk_desc;
	}

	/**
	 *@param env_bk_desc ��������
	 */
	public void setEnv_bk_desc(String env_bk_desc) {
		this.env_bk_desc = env_bk_desc;
	}

	/**
	 *@return env_type ��������
	 */
	public ENV_TYPE getEnv_type() {
		return this.env_type;
	}

	/**
	 *@param env_type ��������
	 */
	public void setEnv_type(ENV_TYPE env_type) {
		this.env_type = env_type;
	}

	/**
	 *@return sys_name Ӧ��ϵͳ
	 */
	public String getSys_name() {
		return this.sys_name;
	}

	/**
	 *@param sys_name Ӧ��ϵͳ
	 */
	public void setSys_name(String sys_name) {
		this.sys_name = sys_name;
	}

	/**
	 *@return ele_type ����Ҫ��
	 */
	public String[] getEle_type() {
		return this.ele_type;
	}

	/**
	 *@param ele_type ����Ҫ��
	 */
	public void setEle_type(String[] ele_type) {
		this.ele_type = ele_type;
	}

	/**
	 *@return dt_range ���ݷ�Χ
	 */
	public DT_RANGE getDt_range() {
		return this.dt_range;
	}

	/**
	 *@param dt_range ���ݷ�Χ
	 */
	public void setDt_range(DT_RANGE dt_range) {
		this.dt_range = dt_range;
	}

	/**
	 * @return server_list Ӧ�÷������б�
	 */
	public List<ServerBean> getServer_list() {
		return this.server_list;
	}

	/**
	 * @param server_list Ӧ�÷������б�
	 */
	public void setServer_list(List<ServerBean> server_list) {
		this.server_list = server_list;
	}
	
}
