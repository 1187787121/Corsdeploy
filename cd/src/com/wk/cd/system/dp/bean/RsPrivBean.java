/**
 * Title: RsPrivBean.java
 * File Description: ��ԴȨ�޽ӿ���
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015��8��26��
 */
package com.wk.cd.system.dp.bean;

import com.wk.cd.enu.FUN_TYPE;
import com.wk.cd.enu.OPEN_TYPE;


/**
 * Class Description: ��ԴȨ�޽ӿ���
 * @author HT
 */
public class RsPrivBean {
	/**
	 * ��Դ����
	 */
	private String rs_code;

	public static final String RS_CODECN = "��Դ����";
	
	/**
	 * ϵͳ��Դ����
	 */
	private String bl_rs_code;

	public static final String BL_RS_CODECN = "ϵͳ��Դ����";

	/**
	 * �ϼ���Դ����
	 */
	private String super_rs_code;

	public static final String SUPER_RS_CODECN = "�ϼ���Դ����";

	/**
	 * ��Դ����
	 */
	private String rs_cn_name;

	public static final String RS_CN_NAMECN = "��Դ����";
	
	/**
	 *��������
	 */
	private OPEN_TYPE open_type;

	public static final String OPEN_TYPECN = "��������";
	
	/**
	 *��Դ����
	 */
	private int rs_level;

	public static final String RS_LEVELCN = "��Դ����";
	
	/**
	 *��Դ����
	 */
	private FUN_TYPE rs_fun_type;

	public static final String RS_FUN_TYPECN = "��Դ����";



	/**
	 * @return rs_code ��Դ����
	 */
	public String getRs_code() {
		return this.rs_code;
	}

	/** 
	 * @param rs_code ��Դ����
	 */
	public void setRs_code(String rs_code) {
		this.rs_code = rs_code;
	}
	
	/**
	 * @return bl_rs_code ϵͳ��Դ����
	 */
	public String getBl_rs_code() {
		return bl_rs_code;
	}

	/**
	 * @param bl_rs_code ϵͳ��Դ����
	 */
	public void setBl_rs_code(String bl_rs_code) {
		this.bl_rs_code = bl_rs_code;
	}

	/**
	 * @return super_rs_code �ϼ���Դ����
	 */
	public String getSuper_rs_code() {
		return this.super_rs_code;
	}

	/**
	 * @param super_rs_code �ϼ���Դ����
	 */
	public void setSuper_rs_code(String super_rs_code) {
		this.super_rs_code = super_rs_code;
	}

	/**
	 * @return rs_cn_name ��Դ����
	 */
	public String getRs_cn_name() {
		return this.rs_cn_name;
	}

	/**
	 * @param rs_cn_name ��Դ����
	 */
	public void setRs_cn_name(String rs_cn_name) {
		this.rs_cn_name = rs_cn_name;
	}

	/**
	 * @return open_type ��������
	 */
	public OPEN_TYPE getOpen_type() {
		return this.open_type;
	}

	/**
	 * @param open_type ��������
	 */
	public void setOpen_type(OPEN_TYPE open_type) {
		this.open_type = open_type;
	}

	/**
	 * @return rs_level ��Դ����
	 */
	public int getRs_level() {
		return this.rs_level;
	}

	/**
	 * @param rs_level ��Դ����
	 */
	public void setRs_level(int rs_level) {
		this.rs_level = rs_level;
	}

	/**
	 * @return rs_fun_type ��Դ����
	 */
	public FUN_TYPE getRs_fun_type() {
		return this.rs_fun_type;
	}

	/**
	 * @param rs_fun_type ��Դ����
	 */
	public void setRs_fun_type(FUN_TYPE rs_fun_type) {
		this.rs_fun_type = rs_fun_type;
	}
}
