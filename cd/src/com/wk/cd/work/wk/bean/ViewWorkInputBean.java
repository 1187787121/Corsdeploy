/**
 * Title: ViewWorkInputBean.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2016年3月10日
 */
package com.wk.cd.work.wk.bean;

import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description: 
 * @author Xul
 */
public class ViewWorkInputBean extends ActionRootInputBean{

	private static final long serialVersionUID = -1923829545453934006L;
	
	/**
	 * 待处理任务流水号
	 */
	private String wrk_seq;

	public static final String WRK_SEQCN = "待处理任务流水号";

	/**
	 * @return wrk_seq 待处理任务流水号
	 */
	public String getWrk_seq() {
		return wrk_seq;
	}

	/**
	 * @param wrk_seq 待处理任务流水号
	 */
	public void setWrk_seq(String wrk_seq) {
		this.wrk_seq = wrk_seq;
	}
}
