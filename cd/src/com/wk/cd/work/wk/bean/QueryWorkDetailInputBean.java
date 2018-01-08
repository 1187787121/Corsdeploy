/**
 * Title: QueryWorkDetailInputBean.java
 * File Description: 任务详情查看输入接口
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2016年3月14日
 */
package com.wk.cd.work.wk.bean;

import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description: 任务详情查看输入接口
 * @author Xul
 */
public class QueryWorkDetailInputBean extends ActionRootInputBean{

	private static final long serialVersionUID = 6341223906747165209L;
	
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
