/**
 * Title: QueryPendWorkOutputBean.java
 * File Description: 查询待处理任务输出接口
 * @copyright: 2014
 * @company: CORSWORK
 * @author: lixl
 * @version: 1.0
 * @date: 2014-11-17
 */
package com.wk.cd.work.wk.bean;

import java.util.List;

import com.wk.cd.work.wk.info.WkDealStateInfo;

/**
 * Class Description: 查询待处理任务状态输出接口
 * @author lixl
 */
public class QueryPendWorkOutputBean<T> {
	private WkDealStateInfo dw_state;
	private List<WkDealDetailBean> dw_dtl;
	private T obj;
	private String new_json_list;

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
	 * @return dw_dtl处理明细列表
	 */
	public List<WkDealDetailBean> getDw_dtl() {
		return this.dw_dtl;
	}

	/**
	 * @param dwDtl 处理明细列表
	 */
	public void setDw_dtl(List<WkDealDetailBean> dw_dtl) {
		this.dw_dtl = dw_dtl;
	}

	/**
	 * @return 对象
	 */
	public T getObj() {
		return this.obj;
	}

	/**
	 * @param obj 对象
	 */
	public void setObj(T obj) {
		this.obj = obj;
	}

	/**
	 * @return new_json_str 返回的json字符列表
	 */
	public String getNew_json_list() {
		return this.new_json_list;
	}

	/**
	 * @param new_json_str 返回的json字符列表
	 */
	public void setNew_json_list(String new_json_list) {
		this.new_json_list = new_json_list;
	}

}
