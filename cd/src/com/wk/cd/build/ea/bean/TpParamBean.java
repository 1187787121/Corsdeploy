/**
 * Title: TpParamBean.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Administrator
 * @version: 1.0
 * @date: 2016��11��14��
 */
package com.wk.cd.build.ea.bean;

/**
 * Class Description: 
 * @author Administrator
 */
public class TpParamBean {
	/**
	 * ����
	 */
	private String param_group;
	
	public static final String GROUPCN = "����";
	
	/**
	 * ������
	 */
	private String param_name;
	
	public static final String PARAM_NAMECN = "������";
	
	/**
	 * ����������
	 */
	private String param_cn_name;
	
	public static final String PARAM_CN_NAMECN = "����������";
	
	/**
	 * ��������
	 */
	private String param_desc;
	
	public static final String PARAM_BK_DESCCN = "����������";
	
	/**
	 * �Ƿ�����޸�
	 */
	private boolean modify_flag;
	
	public static final String MODIFY_FLAGCN = "�Ƿ�����޸�";
	
	/**
	 * ����ֵ
	 */
	private  String param_value;
	
	public static final String PARAM_VALUECN = "����ֵ";
	
	/**
	 * �׶κ�
	 */
	private Integer phase_no;
	
	public static final String PHASE_NOCN = "�׶κ�";



	/**
	 * @return param_group ����
	 */
	public String getParam_group() {
		return this.param_group;
	}

	/**
	 * @param param_group ����
	 */
	public void setParam_group(String param_group) {
		this.param_group = param_group;
	}

	/**
	 * @return param_name ������
	 */
	public String getParam_name() {
		return this.param_name;
	}

	/**
	 * @param param_name ������
	 */
	public void setParam_name(String param_name) {
		this.param_name = param_name;
	}

	/**
	 * @return param_cn_name ����������
	 */
	public String getParam_cn_name() {
		return this.param_cn_name;
	}

	/**
	 * @param param_cn_name ����������
	 */
	public void setParam_cn_name(String param_cn_name) {
		this.param_cn_name = param_cn_name;
	}



	/**
	 * @return param_desc ��������
	 */
	public String getParam_desc() {
		return this.param_desc;
	}

	/**
	 * @param param_desc ��������
	 */
	public void setParam_desc(String param_desc) {
		this.param_desc = param_desc;
	}

	/**
	 * @return modify_flag �Ƿ�����޸�
	 */
	public boolean isModify_flag() {
		return modify_flag;
	}

	/**
	 * @param modify_flag �Ƿ�����޸�
	 */
	public void setModify_flag(boolean modify_flag) {
		this.modify_flag = modify_flag;
	}

	/**
	 * @return param_value ����ֵ
	 */
	public String getParam_value() {
		return param_value;
	}

	/**
	 * @param param_value ����ֵ
	 */
	public void setParam_value(String param_value) {
		this.param_value = param_value;
	}

	/**
	 * @return phase_no �׶κ�
	 */
	public Integer getPhase_no() {
		return this.phase_no;
	}

	/**
	 * @param phase_no �׶κ�
	 */
	public void setPhase_no(Integer phase_no) {
		this.phase_no = phase_no;
	}
	
	
	
}
