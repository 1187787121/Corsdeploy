/**
 * Title: DeleteMessInputBean.java
 * File Description: 
 * @copyright: 2015
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2015年4月9日
 */
package com.wk.cd.system.mg.bean;

import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description: 删除消息信息输入接口
 * @author HT
 */
public class DeleteMessInputBean extends ActionRootInputBean{

	private static final long serialVersionUID = 3126292399779672199L;

	/**
	 *消息流水号
	 */
	private String workseq;

	public static final String WORKSEQCN = "消息流水号";

	/**
	 * @return workseq 消息流水号
	 */
	public String getWorkseq() {
		return this.workseq;
	}

	/**
	 * @param workseq 消息流水号
	 */
	public void setWorkseq(String workseq) {
		this.workseq = workseq;
	}
	
}
