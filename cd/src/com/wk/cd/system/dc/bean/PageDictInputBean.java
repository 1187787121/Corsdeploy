/**
 * Title: PageDictInputBean.java
 * File Description: 分页查询数据字典列表输入接口
 * @copyright: 2014
 * @company: CORSWORK
 * @author: HT
 * @version: 1.0
 * @date: 2014年11月17日
 */
package com.wk.cd.system.dc.bean;

import com.wk.cd.bean.PageQueryActionRootInputBean;

/**
 * Class Description:分页查询数据字典列表输入接口类
 * @author HT
 */
public class PageDictInputBean
		extends PageQueryActionRootInputBean {
	
	/** 
	 * 
	 */ 
	private static final long serialVersionUID = 9021558651547699863L;
	/**
	 * 关键词
	 */
	private String keyword; 
	public static final String KEYWORDCN="关键词";
	/**
	 * @return keyword 关键词
	 */
	public String getKeyword() {
		return this.keyword;
	}
	/**
	 * @param keyword 关键词
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
}
