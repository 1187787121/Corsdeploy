/**
 * Title: PagrComponentOutputBean.java
 * File Description: 
 * @copyright: 2017
 * @company: CORSWORK
 * @author: yangl
 * @version: 1.0
 * @date: 2017年11月16日
 */
package com.wk.cd.module1.bean;

import java.util.List;

import com.wk.cd.bean.PageQueryActionRootOutputBean;
import com.wk.cd.module1.info.MoComponentInfo;

/**
 * Class Description:
 * @author yangl
 */
public class PageComponentOutputBean
		extends PageQueryActionRootOutputBean {

	/**
	 * @Fields serialVersionUID : 2794243139575406425L
	 */
	private static final long serialVersionUID = 2794243139575406425L;
	
	/**
	 * 组件列表
	 */
//	private List<CompBeanForList> comp_list;
	private List<MoComponentInfo> comp_list;

	public static final String COMP_LISTCN = "组件列表";

	/**
	 * @return comp_list
	 */
	public List<MoComponentInfo> getComp_list() {
		return comp_list;
	}

	/**
	 * @param comp_list
	 */
	public void setComp_list(List<MoComponentInfo> comp_list) {
		this.comp_list = comp_list;
	}

}
