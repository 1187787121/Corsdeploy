/**
 * Title: TargetStorageInputBean.java
 * File Description: Ŀ������������ӿ�
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2016��11��15��
 */
package com.wk.cd.build.ea.bean;

import java.util.List;

import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description: Ŀ������������ӿ�
 * @author Xul
 */
public class TargetStorageInputBean extends ActionRootInputBean{

	private static final long serialVersionUID = -6473768967455949639L;
	
	/**
	 * ��������
	 */
	private String env_name;
	
	public static final String ENV_NAMECN = "��������";
	
	/**
	 * �������
	 */
	private String storage_bk_desc;

	public static final String STORAGE_BK_DESCCN = "�������";

	/**
	 * ��Ŀ���
	 */
	private String project_name;

	public static final String PROJECT_NAMECN = "��Ŀ���";
	
	/**
	 * ��������
	 */
	private String ce_server_name;
	
	public static final String CE_SERVER_NAMECN = "��������";
	
	/**
	 * ����Դ��
	 */
	private String soc_name;
	
	public static final String SOC_NAMECN = "����Դ��";
	
	/**
	 * Ŀ��汾��������
	 */
	private String tag_server_name;
	
	public static final String TAG_SERVER_NAMECN = "Ŀ��汾��������";
	
	/**
	 * Ŀ��汾����Դ��
	 */
	private String tag_soc_name;
	
	public static final String TAG_SOC_NAMECN = "Ŀ��汾����Դ��";
	
	/**
	 * Ŀ��汾Ŀ¼
	 */
	private String tag_bk_dir;
	
	public static final String TAG_BK_DIRCN = "Ŀ��汾Ŀ¼";
	
	/**
	 * Ŀ����б�
	 */
	private List<TargetPackageBean> tar_package_list;
	
	public static final String TAR_PACKAGE_LISTCN = "Ŀ����б�";
	
	private String inte_ver_num;
	
	public static final String INTE_VER_NUMCN = "���ɰ汾��";
	
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
	 * @return storage_bk_desc �������
	 */
	public String getStorage_bk_desc() {
		return storage_bk_desc;
	}

	/**
	 * @param storage_bk_desc �������
	 */
	public void setStorage_bk_desc(String storage_bk_desc) {
		this.storage_bk_desc = storage_bk_desc;
	}

	/**
	 * @return project_name ��Ŀ���
	 */
	public String getProject_name() {
		return project_name;
	}

	/**
	 * @param project_name ��Ŀ���
	 */
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	/**
	 * @return ce_server_name ��������
	 */
	public String getCe_server_name() {
		return ce_server_name;
	}

	/**
	 * @param ce_server_name ��������
	 */
	public void setCe_server_name(String ce_server_name) {
		this.ce_server_name = ce_server_name;
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
	 * @return tag_server_name Ŀ��汾��������
	 */
	public String getTag_server_name() {
		return tag_server_name;
	}

	/**
	 * @param tag_server_name Ŀ��汾��������
	 */
	public void setTag_server_name(String tag_server_name) {
		this.tag_server_name = tag_server_name;
	}

	/**
	 * @return tag_soc_name Ŀ��汾����Դ��
	 */
	public String getTag_soc_name() {
		return tag_soc_name;
	}

	/**
	 * @param tag_soc_name Ŀ��汾����Դ��
	 */
	public void setTag_soc_name(String tag_soc_name) {
		this.tag_soc_name = tag_soc_name;
	}

	/**
	 * @return tag_bk_dir Ŀ��汾Ŀ¼
	 */
	public String getTag_bk_dir() {
		return tag_bk_dir;
	}

	/**
	 * @param tag_bk_dir Ŀ��汾Ŀ¼
	 */
	public void setTag_bk_dir(String tag_bk_dir) {
		this.tag_bk_dir = tag_bk_dir;
	}

	/**
	 * @return tar_package_list Ŀ����б�
	 */
	public List<TargetPackageBean> getTar_package_list() {
		return tar_package_list;
	}

	/**
	 * @param tar_package_list Ŀ����б�
	 */
	public void setTar_package_list(List<TargetPackageBean> tar_package_list) {
		this.tar_package_list = tar_package_list;
	}

	/**
	 * @return inte_ver_num ���ɰ汾��
	 */
	public String getInte_ver_num() {
		return inte_ver_num;
	}

	/**
	 * @param inte_ver_num ���ɰ汾��
	 */
	public void setInte_ver_num(String inte_ver_num) {
		this.inte_ver_num = inte_ver_num;
	}
	
	
}
