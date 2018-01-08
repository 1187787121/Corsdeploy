/**
 * Title: QueryPendWorkDetailByWorkSeqOutputBean.java
 * File Description:待处理任务明细输出接口 
 * @copyright: 2014
 * @company: CORSWORK
 * @author: tlw
 * @version: 1.0
 * @date: 2014-12-4
 */
package com.wk.cd.work.wk.bean;

import java.util.List;

import com.wk.cd.bean.ActionRootOutputBean;
import com.wk.cd.work.wk.info.WkDealDetailInfo;

/**
 * Class Description:待处理任务明细输出接口
 * @author tlw
 */
public class QueryPendWorkDetailByWorkSeqOutputBean
		extends ActionRootOutputBean {
	
	private static final long serialVersionUID = -4275222079812414059L;

	/**
	 * 任务处理明细列表
	 */
	private List<WkDealDetailInfo> detail_list;
	
	public static final String DETAIL_LISTCN = "任务处理明细列表";

	/**
	 * @return detail_list 任务处理明细列表
	 */
	public List<WkDealDetailInfo> getDetail_list() {
		return detail_list;
	}

	/**
	 * @param detail_list 任务处理明细列表
	 */
	public void setDetail_list(List<WkDealDetailInfo> detail_list) {
		this.detail_list = detail_list;
	}


}
