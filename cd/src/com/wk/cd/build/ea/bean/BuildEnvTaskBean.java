/**
 * Title: PageEnvTaskBean.java
 * File Description: ��ҳ��ѯ�����б�ӿ�
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2016��12��17��
 */
package com.wk.cd.build.ea.bean;

import com.wk.cd.build.ea.info.EnvTaskInfo;

/**
 * Class Description: ��ҳ��ѯ�����б�ӿ�
 * @author Xul
 */
public class BuildEnvTaskBean extends EnvTaskInfo{
	
	private static final long serialVersionUID = -2985384406880449043L;

	/**
	 * ��Ŀ���
	 */
	private String project_short_name;

	public static final String PROJECT_SHORT_NAMECN = "��Ŀ���";
	
	/**
	 * @return project_short_name ��Ŀ���
	 */
	public String getProject_short_name() {
		return project_short_name;
	}

	/**
	 * @param project_short_name ��Ŀ���
	 */
	public void setProject_short_name(String project_short_name) {
		this.project_short_name = project_short_name;
	}
}
