/**
 * Title: DeleteConfigFileInputBean.java
 * File Description: ɾ�����������ļ���������ӿ�
 * @copyright: 2016
 * @company: CORSWORK
 * @author: chiss
 * @version: 1.0
 * @date: 2016��12��9��
 */
package com.wk.cd.build.ea.bean;

import com.wk.cd.bean.ActionRootInputBean;
import com.wk.cd.enu.YN_FLAG;

/**
 * Class Description: ɾ�����������ļ���������ӿ�
 * @author chiss
 */
public class DeleteBuildConfigInputBean extends ActionRootInputBean{

	private static final long serialVersionUID = 1652350278350706585L;
	
	/**
	 * ��������
	 */
	private String env_name;

	public static final String ENV_NAMECN = "��������";
	
	/**
	 * ����������
	 */
	private String ce_server_name;
	
	public static final String CE_SERVER_NAMECN = "����������";
	
	/**
	 * ����Դ����
	 */
	private String soc_name;
	
	public static final String SOC_NAMECN = "����Դ����";
	
	/**
	 * ������
	 */
	private String work_id;
	
	public static final String WORK_IDCN = "������";
	
	/**
	 * �ļ����·��
	 */
	private String relative_path;
	
	public static final String RELATIVE_PATHCN = "�ļ����·��";
	
	/**
	 *�Ƿ�Ŀ¼��־
	 */
	private YN_FLAG dir_yn_flag;

	public static final String DIR_YN_FLAGCN = "�Ƿ�Ŀ¼��־";
	
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
	 * @return soc_name ����Դ����
	 */
	public String getSoc_name() {
		return soc_name;
	}

	/**
	 * @param soc_name ����Դ����
	 */
	public void setSoc_name(String soc_name) {
		this.soc_name = soc_name;
	}
	
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
	 * @return dir_yn_flag �Ƿ�Ŀ¼��־
	 */
	public YN_FLAG getDir_yn_flag() {
		return dir_yn_flag;
	}

	/**
	 * @param dir_yn_flag �Ƿ�Ŀ¼��־
	 */
	public void setDir_yn_flag(YN_FLAG dir_yn_flag) {
		this.dir_yn_flag = dir_yn_flag;
	}
}
