/**
 * Title: QueryWorkDetailByWorkSeqInputBean.java
 * File Description:待处理流水号查询任务状态信息输入接口
 * @copyright: 2014
 * @company: CORSWORK
 * @author: tlw
 * @version: 1.0
 * @date: 2014-12-4
 */
package com.wk.cd.work.wk.bean;

import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description:待处理流水号查询任务状态信息输入接口
 * @author tlw
 */
public class QueryPendWorkStateByWorkSeqInputBean
		extends ActionRootInputBean {

	private static final long serialVersionUID = -8767389122572765910L;

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
