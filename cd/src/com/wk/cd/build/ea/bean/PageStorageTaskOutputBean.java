/**
 * Title: PageStorageTaskOutputBean.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: chiss
 * @version: 1.0
 * @date: 2016年11月17日
 */
package com.wk.cd.build.ea.bean;

import java.util.List;

import com.wk.cd.bean.PageQueryActionRootOutputBean;

/**
 * Class Description: 
 * @author chiss
 */
public class PageStorageTaskOutputBean extends PageQueryActionRootOutputBean{

	/** 
	 * @Fields serialVersionUID : TODO
	 */ 
	private static final long serialVersionUID = 998805837899952818L;
	
	/**
	 * 入库任务列表
	 */
	private List<EnvTagStorageBean> stor_list;
	
	public static final String stor_listcn = "入库任务列表";

	/**
	 * @return stor_list 入库任务列表
	 */
	public List<EnvTagStorageBean> getStor_list() {
		return stor_list;
	}

	/**
	 * @param stor_list 入库任务列表
	 */
	public void setStor_list(List<EnvTagStorageBean> stor_list) {
		this.stor_list = stor_list;
	}
}
