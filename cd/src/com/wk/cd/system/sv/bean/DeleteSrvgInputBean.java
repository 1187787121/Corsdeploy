/**
 * Title: DeleteSrvgInputBean.java
 * File Description: ɾ����������Ϣ����ӿ�
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015��10��12��
 */
package com.wk.cd.system.sv.bean;

import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description: ɾ����������Ϣ����ӿ�
 * @author HT
 */
public class DeleteSrvgInputBean extends ActionRootInputBean{

	private static final long serialVersionUID = 9167986554579726894L;
	
	/**
	 *���������
	 */
	private String srvg_code;

	public static final String SRVG_CODECN = "���������";
	
	/**
	 *@return srvg_code ���������
	 */
	public String getSrvg_code() {
		return this.srvg_code;
	}

	/**
	 *@param srvg_code ���������
	 */
	public void setSrvg_code(String srvg_code) {
		this.srvg_code = srvg_code;
	}
}
