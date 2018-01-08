/**
 * Title: PageQueryActionRootInputBean.java
 * File Description: 分页查询输入接口基类
 * @copyright 2014 
 * @company CORSWORK
 * @author lixl
 * @version 1.0
 * @date 11/17/2014
 */

package com.wk.cd.bean;

/**
 * Class Description:分页查询输入接口基类
 * @author lixl
 */
public class PageQueryActionRootInputBean extends ActionRootInputBean {
	private static final long serialVersionUID = -5520972908307185791L;

	/**
	 * 起始记录数
	 */
	private int start_recd;

	public static final String START_RECDCN = "起始记录数";

	/**
	 * 查询条数
	 */
	private int limit_recd;

	public static final String LIMIT_RECDCN = "查询条数";

	/**
	 * 构造函数
	 * @param bean 
	 */
	public PageQueryActionRootInputBean(PageQueryActionRootInputBean bean){
		super(bean);
		this.start_recd = bean.getStart_recd();
		this.limit_recd = bean.getLimit_recd();
	}

	/**
	 * 构造函数
	 */
	public PageQueryActionRootInputBean(){
		super();
	}

	/**
	 * 起始记录数
	 * @return int 
	 */
	public int getStart_recd(){
		return this.start_recd;
	}

	/**
	 * 起始记录数
	 * @param start_recd 
	 */
	public void setStart_recd(int start_recd){
		this.start_recd = start_recd;
	}

	/**
	 * 查询条数
	 * @return int 
	 */
	public int getLimit_recd(){
		return this.limit_recd;
	}

	/**
	 * 查询条数
	 * @param limit_recd 
	 */
	public void setLimit_recd(int limit_recd){
		this.limit_recd = limit_recd;
	}

	/**
	 * 克隆函数
	 * @return PageQueryActionRootInputBean 
	 */
	@Override
	public PageQueryActionRootInputBean clone(){
		return (PageQueryActionRootInputBean)super.clone();
	}
}

