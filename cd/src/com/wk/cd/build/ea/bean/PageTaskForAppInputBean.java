/**
 * Title: PageTaskForAppInputBean.java
 * File Description: ��ҳ��ѯ���������б�����ӿڣ�App�ˣ�
 * @copyright: 2017
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2017��2��28��
 */
package com.wk.cd.build.ea.bean;

import com.wk.cd.bean.PageQueryActionRootInputBean;

/**
 * Class Description: ��ҳ��ѯ���������б�����ӿڣ�App�ˣ�
 * @author Xul
 */
public class PageTaskForAppInputBean extends PageQueryActionRootInputBean{

	private static final long serialVersionUID = 7866719643214664439L;
	
	/**
	 *��������
	 */
	private String env_name;

	public static final String ENV_NAMECN = "��������";

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
}
