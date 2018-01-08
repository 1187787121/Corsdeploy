/**
 * Title: PageServerOutputBean.java
 * File Description: 服务器分页查询搜索输出接口
 * @copyright: 2016
 * @company: CORSWORK
 * @author: yangl
 * @version: 1.0
 * @date: 2016年11月1日
 */
package com.wk.cd.build.en.bean;

import java.util.List;

import com.wk.cd.bean.PageQueryActionRootOutputBean;

/**
 * Class Description: 服务器分页查询搜索输出接口
 * @author yangl
 */
public class PageServerOutputBean extends PageQueryActionRootOutputBean{

	/** 
	 * @Fields serialVersionUID : 4567989332012495076L
	 */ 
	private static final long serialVersionUID = 4567989332012495076L;

	/**
	 * 服务器列表
	 */
	private List<PageServerBean> server_list;
	public static final String SERVER_LIST_CN = "服务器列表";

	/**
	 * @return 服务器列表
	 */
	public List<PageServerBean> getServer_list() {
		return server_list;
	}

	/**
	 * @param 服务器列表
	 */
	public void setServer_list(List<PageServerBean> server_list) {
		this.server_list = server_list;
	}
	
}
