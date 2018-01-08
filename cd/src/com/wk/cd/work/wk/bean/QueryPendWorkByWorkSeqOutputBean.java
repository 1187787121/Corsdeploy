/**
 * Title: QueryPendWorkByWorkSeqOutputBean.java
 * File Description: 查询待处理任务信息输出接口
 * @copyright: 2014
 * @company: CORSWORK
 * @author: tianlw
 * @version: 1.0
 * @date: 2014-12-23
 */
package com.wk.cd.work.wk.bean;

import java.util.List;

import com.wk.cd.bean.ActionRootOutputBean;
import com.wk.cd.work.wk.info.WkDealStateInfo;

/**
 * Class Description: 查询待处理任务信息输出接口
 * @author tianlw
 */
public class QueryPendWorkByWorkSeqOutputBean
		extends ActionRootOutputBean {

	private static final long serialVersionUID = 2950150196597077432L;

	/**
	 * 任务状态信息
	 */
	private WkDealStateInfo dw_state;

	public static final String DW_STATECN = "任务状态信息";

	/**
	 * 任务处理明细信息
	 */
	private List<WkDealDetailBean> dw_dtl;

	public static final String DW_DTLCN = "任务处理明细信息";

	/**
	 * 输入接口信息
	 */
	private Object obj;

	public static final String OBJCN = "输入接口信息";

	/**
	 * json字符列表
	 */
	private String new_json_list;

	public static final String NEW_JSON_LISTCN = "json字符列表";

	/**
	 * @return dw_state 处理状态信息
	 */
	public WkDealStateInfo getDw_state() {
		return this.dw_state;
	}

	/**
	 * @param dw_state 处理状态信息
	 */
	public void setDw_state(WkDealStateInfo dw_state) {
		this.dw_state = dw_state;
	}

	/**
	 * @return dw_dtl 处理明细列表
	 */
	public List<WkDealDetailBean> getDw_dtl() {
		return this.dw_dtl;
	}

	/**
	 * @param dw_dtl 处理明细列表
	 */
	public void setDw_dtl(List<WkDealDetailBean> dw_dtl) {
		this.dw_dtl = dw_dtl;
	}

	/**
	 * @return 输入接口信息
	 */
	public Object getObj() {
		return this.obj;
	}

	/**
	 * @param obj 输入接口信息
	 */
	public void setObj(Object obj) {
		this.obj = obj;
	}

	/**
	 * @return new_json_list json字符列表
	 */
	public String getNew_json_list() {
		return this.new_json_list;
	}

	/**
	 * @param new_json_list json字符列表
	 */
	public void setNew_json_list(String new_json_list) {
		this.new_json_list = new_json_list;
	}

}
