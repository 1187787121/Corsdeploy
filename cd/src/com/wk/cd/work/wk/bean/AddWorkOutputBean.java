/**
 * Title: AddWorkOutputBean.java
 * File Description:�������񷵻���Ϣ 
 * @copyright: 2014
 * @company: CORSWORK
 * @author: tlw
 * @version: 1.0
 * @date: 2014-12-1
 */
package com.wk.cd.work.wk.bean;

import com.wk.cd.bean.ActionRootOutputBean;

/**
 * Class Description:�������񷵻���Ϣ
 * @author tlw
 */
public class AddWorkOutputBean
		extends ActionRootOutputBean {
	
	private static final long serialVersionUID = 5326013894166139656L;

	/**
	 * �������
	 */
	private String work_code;
	
	public static final String WORK_CODECN = "�������";

	/**
	 * @return work_code �������
	 */
	public String getWork_code() {
		return work_code;
	}

	/**
	 * @param work_code �������
	 */
	public void setWork_code(String work_code) {
		this.work_code = work_code;
	}
}
