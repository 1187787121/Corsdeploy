/**
 * Title: UpdateDeptRsOptPrivInputBean.java
 * File Description: �޸Ĳ�����Դ������Ȩ����Ϣ����ӿ�
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015��8��27��
 */
package com.wk.cd.system.dp.bean;

import java.util.List;

import com.wk.cd.bean.ActionRootInputBean;
import com.wk.cd.system.dp.info.DpDeptOptPrivInfo;

/**
 * Class Description: �޸Ĳ�����Դ������Ȩ����Ϣ����ӿ�
 * @author HT
 */
public class UpdateDeptRsOptPrivInputBean extends ActionRootInputBean{

	private static final long serialVersionUID = -2974603586156282241L;
	
	/**
	 * ���ű���
	 */
	private String dept_id;

	public static final String DEPT_IDCN = "���ű���";

	/**
	 * ��Դ���������б�
	 */
	private String[] rs_ary;

	public static final String RS_ARYCN = "��Դ���������б�";
	
	/**
	 * ���Ų���Ȩ���б�
	 */
	private List<DpDeptOptPrivInfo> opt_priv;
	
	public static final String OPT_PRIVCN = "���Ų���Ȩ���б�";

	/** 
	 * @return dept_id ���ű���
	 */
	public String getDept_id() {
		return this.dept_id;
	}

	/**
	 * @param dept_id ���ű���
	 */
	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}

	/**
	 * @return rs_ary ��Դ���������б�
	 */
	public String[] getRs_ary() {
		return this.rs_ary;
	}

	/**
	 * @param rs_ary ��Դ���������б�
	 */
	public void setRs_ary(String[] rs_ary) {
		this.rs_ary = rs_ary;
	}

	/**
	 * @return opt_priv ���Ų���Ȩ���б�
	 */
	public List<DpDeptOptPrivInfo> getOpt_priv() {
		return this.opt_priv;
	}

	/**
	 * @param opt_priv ���Ų���Ȩ���б�
	 */
	public void setOpt_priv(List<DpDeptOptPrivInfo> opt_priv) {
		this.opt_priv = opt_priv;
	}
}
