/**
 * Title: DeleteMessInputBean.java
 * File Description: 
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015��4��9��
 */
package com.wk.cd.system.mg.bean;

import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description: ɾ����Ϣ��Ϣ����ӿ�
 * @author HT
 */
public class DeleteMessInputBean extends ActionRootInputBean{

	private static final long serialVersionUID = 3126292399779672199L;

	/**
	 *��Ϣ��ˮ��
	 */
	private String workseq;

	public static final String WORKSEQCN = "��Ϣ��ˮ��";

	/**
	 * @return workseq ��Ϣ��ˮ��
	 */
	public String getWorkseq() {
		return this.workseq;
	}

	/**
	 * @param workseq ��Ϣ��ˮ��
	 */
	public void setWorkseq(String workseq) {
		this.workseq = workseq;
	}
	
}
