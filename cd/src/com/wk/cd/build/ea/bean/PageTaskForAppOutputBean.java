/**
 * Title: PageTaskForAppOutputBean.java
 * File Description: ��ҳ��ѯ���������б�����ӿڣ�App�ˣ�
 * @copyright: 2017
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2017��2��28��
 */
package com.wk.cd.build.ea.bean;

import java.util.List;

import com.wk.cd.bean.PageQueryActionRootOutputBean;

/**
 * Class Description: ��ҳ��ѯ���������б�����ӿڣ�App�ˣ�
 * @author Xul
 */
public class PageTaskForAppOutputBean extends PageQueryActionRootOutputBean{

	private static final long serialVersionUID = -7491075928653185247L;
	
	/**
	 * ���������б�
	 */
	private List<EnvTaskAppBean> env_task_list;
	
	public static final String ENV_TASK_LISTCN = "���������б�";

	/**
	 * @return env_task_list ���������б�
	 */
	public List<EnvTaskAppBean> getEnv_task_list() {
		return env_task_list;
	}

	/**
	 * @param env_task_list ���������б�
	 */
	public void setEnv_task_list(List<EnvTaskAppBean> env_task_list) {
		this.env_task_list = env_task_list;
	}
}
