/**
 * Title: ViewWorkOutputBean.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2016年3月10日
 */
package com.wk.cd.work.wk.bean;

import java.util.List;

import com.wk.cd.bean.ActionRootOutputBean;

/**
 * Class Description: 
 * @author Xul
 */
public class ViewWorkOutputBean extends ActionRootOutputBean{

	private static final long serialVersionUID = 8971853823523162407L;
	
	/**
	 * 待授权任务个数
	 */
	private int unauth_executory_count;
	
	public static final String UNAUTH_EXECUTORY_COUNTCN = "待授权任务个数";
	
	/**
	 * 待复核任务个数
	 */
	private int uncheck_executory_count;
	
	public static final String UNCHECK_EXECUTORY_COUNTCN = "待复核任务个数";
	
	/**
	 * 我的待处理任务个数
	 */
	private int mine_executory_count;
	
	public static final String MINE_EXECUTORY_COUNTCN = "我的待处理任务个数";
	
	/**
	 *申请页面代码
	 */
	private String apply_html;

	public static final String APPLY_HTMLCN = "申请页面代码";
	
	/**
	 * 附加数据列表
	 */
	private List<WkExtraDataBean> extra_list;
	
	public static final String EXTRA_LISTCN = "附加数据列表";
	
	/**
	 * @return unauth_executory_count 待授权任务个数
	 */
	public int getUnauth_executory_count() {
		return unauth_executory_count;
	}

	/**
	 * @param unauth_executory_count 待授权任务个数
	 */
	public void setUnauth_executory_count(int unauth_executory_count) {
		this.unauth_executory_count = unauth_executory_count;
	}

	/**
	 * @return uncheck_executory_count 待复核任务个数
	 */
	public int getUncheck_executory_count() {
		return uncheck_executory_count;
	}

	/**
	 * @param uncheck_executory_count 待复核任务个数
	 */
	public void setUncheck_executory_count(int uncheck_executory_count) {
		this.uncheck_executory_count = uncheck_executory_count;
	}

	/**
	 * @return mine_executory_count 我的待处理任务个数
	 */
	public int getMine_executory_count() {
		return mine_executory_count;
	}

	/**
	 * @param mine_executory_count 我的待处理任务个数
	 */
	public void setMine_executory_count(int mine_executory_count) {
		this.mine_executory_count = mine_executory_count;
	}

	/**
	 * @return apply_html 申请页面代码
	 */
	public String getApply_html() {
		return apply_html;
	}

	/**
	 * @param apply_html 申请页面代码
	 */
	public void setApply_html(String apply_html) {
		this.apply_html = apply_html;
	}

	/**
	 * @return extra_list 附加数据列表
	 */
	public List<WkExtraDataBean> getExtra_list() {
		return extra_list;
	}

	/**
	 * @param extra_list 附加数据列表
	 */
	public void setExtra_list(List<WkExtraDataBean> extra_list) {
		this.extra_list = extra_list;
	}
}
