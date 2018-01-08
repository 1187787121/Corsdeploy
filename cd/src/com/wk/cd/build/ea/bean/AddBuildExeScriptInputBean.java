/**
 * Title: AddBuildExeScriptInputBean.java
 * File Description: ������ò�������ӿ�
 * @copyright: 2016
 * @company: CORSWORK
 * @author: xuph
 * @version: 1.0
 * @date: 2016��12��9��
 */
package com.wk.cd.build.ea.bean;

import java.util.List;

import com.wk.cd.build.ea.info.UuParamInfo;
import com.wk.cd.build.ea.info.UuSocInfo;
import com.wk.cd.enu.SCRIPT_METHOD;
import com.wk.cd.enu.SCRIPT_TYPE;
import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description: ������ò�������ӿ�
 * @author xuph
 */
public class AddBuildExeScriptInputBean extends ActionRootInputBean{

	/** 
	 * @Fields serialVersionUID : 7844246912851643275L
	 */ 
	private static final long serialVersionUID = 7844246912851643275L;
	
	/**
	 * ������
	 */
	private String work_id;
	
	public static final String WORK_IDCN = "������";
	/**
	 *���ID
	 */
	private String id;

	public static final String IDCN = "���ID";

	/**
	 *���������
	 */
	private String cn_name;

	public static final String CN_NAMECN = "���������";
	
	/**
	 *�ű�����
	 */
	private SCRIPT_TYPE script_type;

	public static final String SCRIPT_TYPECN = "�ű�����";
	
	/**
	 *�ű���ʽ
	 */
	private SCRIPT_METHOD script_method;

	public static final String SCRIPT_METHODCN = "�ű���ʽ";
	
	/**
	 * ��������Դ�б�
	 */
	private List<UuSocInfo>soc_list;
	
	public static final String SOC_LISTCN = "��������Դ�б�";
	 
	/**
	 *�ű�����
	 */
	private String []script_text;

	public static final String SCRIPT_TEXTCN = "�ű�����";
	
	/**
	 * ������Ϣ
	 */
	List<UuParamInfo> param_list;
	
	public static final String PARAM_LISTCN = "������Ϣ";
	
	
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
	 *@return script_method �ű���ʽ
	 */
	public SCRIPT_METHOD getScript_method() {
		return this.script_method;
	}

	/**
	 *@param script_method �ű���ʽ
	 */
	public void setScript_method(SCRIPT_METHOD script_method) {
		this.script_method = script_method;
	}

	/**
	 *@return comp_id ���ID
	 */
	public String getId() {
		return this.id;
	}

	/**
	 *@param comp_id ���ID
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 *@return comp_cn_name ���������
	 */
	public String getCn_name() {
		return this.cn_name;
	}

	/**
	 *@param comp_cn_name ���������
	 */
	public void setCn_name(String cn_name) {
		this.cn_name = cn_name;
	}
	
	/**
	 *@return script_text �ű�����
	 */
	public String[] getScript_text() {
		return this.script_text;
	}

	/**
	 *@param script_text �ű�����
	 */
	public void setScript_text(String[] script_text) {
		this.script_text = script_text;
	}

	
	/**
	 * @return soc_list ��������Դ�б�
	 */
	public List<UuSocInfo> getSoc_list() {
		return this.soc_list;
	}

	/**
	 * @param soc_list ��������Դ�б�
	 */
	public void setSoc_list(List<UuSocInfo> soc_list) {
		this.soc_list = soc_list;
	}

	/**
	 * @return param_list ������Ϣ
	 */
	public List<UuParamInfo> getParam_list() {
		return param_list;
	}

	/**
	 * @param param_list ������Ϣ
	 */
	public void setParam_list(List<UuParamInfo> param_list) {
		this.param_list = param_list;
	}

}
