/**
 * Title: PageInteTaskOutputBean.java
 * File Description: 分页查询集成任务列表输出接口
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2016年11月17日
 */
package com.wk.cd.build.ea.bean;

import java.util.List;

import com.wk.cd.bean.PageQueryActionRootOutputBean;

/**
 * Class Description: 分页查询集成任务列表输出接口
 * @author Xul
 */
public class PageInteTaskOutputBean extends PageQueryActionRootOutputBean{

	private static final long serialVersionUID = -6290838741369996274L;
	
	/**
	 * 集成任务列表
	 */
	private List<InteTaskBean> inte_list;
	
	public static final String INTE_LISTCN = "集成任务列表";

	/**
	 * @return inte_list 集成任务列表
	 */
	public List<InteTaskBean> getInte_list() {
		return inte_list;
	}

	/**
	 * @param inte_list 集成任务列表
	 */
	public void setInte_list(List<InteTaskBean> inte_list) {
		this.inte_list = inte_list;
	}
}
