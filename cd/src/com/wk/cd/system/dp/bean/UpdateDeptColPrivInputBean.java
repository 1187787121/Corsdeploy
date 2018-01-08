/**
 * Title: UpdateDeptColPrivInputBean.java
 * File Description: �޸Ĳ���colȨ������ӿ�
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015��9��6��
 */
package com.wk.cd.system.dp.bean;

import java.util.List;

import com.wk.cd.bean.ActionRootInputBean;
import com.wk.cd.system.dp.info.DpDeptColPrivInfo;

/**
 * Class Description: �޸Ĳ���colȨ������ӿ�
 * @author HT
 */
public class UpdateDeptColPrivInputBean extends ActionRootInputBean{

	private static final long serialVersionUID = -5091786805935020675L;
	
	/**
	 * ���ű���
	 */
	private String dept_id;
	
	public static final String DEPT_IDCN = "���ű���";
	
	/**
	 * colȨ���б�
	 */
	private List<DpDeptColPrivInfo> col_list;
	
	public static final String COL_LISTCN = "colȨ���б�";

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
	 * @return col_list colȨ���б�
	 */
	public List<DpDeptColPrivInfo> getCol_list() {
		return this.col_list;
	}

	/**
	 * @param col_list colȨ���б�
	 */
	public void setCol_list(List<DpDeptColPrivInfo> col_list) {
		this.col_list = col_list;
	}
}
