/**
 * Title: SaveAppDeployInputBean.java
 * File Description: ����Ӧ�ò�����Ϣ����ӿ�
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2016��12��12��
 */
package com.wk.cd.build.ea.bean;

import java.util.List;

import com.wk.cd.build.ea.info.UuParamInfo;
import com.wk.cd.bean.ActionRootInputBean;
import com.wk.cd.module1.entity.PhaseParam;

/**
 * Class Description: ����Ӧ�ò�����Ϣ����ӿ�
 * @author Xul
 */
public class SaveAppDeployInputBean extends ActionRootInputBean{

	private static final long serialVersionUID = -3327711021437398391L;
	
	/**
	 * ������
	 */
	private String work_id;
	
	public static final String WORK_IDCN = "������";
	
	/**
	 * ģ����
	 */
	private String template_name;
	
	public static final String TEMPLATE_NAMECN = "ģ����";
	
	/**
	 * ģ������б�
	 */
	private List<PhaseParam> param_list;
	
	public static final String PARAM_LISTCN = "ģ������б�";

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
	 * @return template_name ģ����
	 */
	public String getTemplate_name() {
		return template_name;
	}

	/**
	 * @param template_name ģ����
	 */
	public void setTemplate_name(String template_name) {
		this.template_name = template_name;
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
