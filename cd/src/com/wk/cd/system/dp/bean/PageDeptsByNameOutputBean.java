/**
 * Title: PageDeptByNameOutputBean.java
 * File Description: 根据部门名称模糊查询部门信息输出接口
 * @copyright: 2014
 * @company: CORSWORK
 * @author: xuy
 * @version: 1.0
 * @date: 2014-11-28
 */
package com.wk.cd.system.dp.bean;

import java.util.List;

import com.wk.cd.bean.PageQueryActionRootOutputBean;

/**
 * Class Description: 根据部门名称模糊查询部门信息输出接口
 * @author xuy
 */
public class PageDeptsByNameOutputBean extends PageQueryActionRootOutputBean {

	private static final long serialVersionUID = -1953590939047151148L;
	private List<DeptExtendsBean> deptInfos ;
	public static final String DEPTINFOSCN = "多部门表";
	
	
	/**
	 * @return deptInfos 多部门表
	 */
	public List<DeptExtendsBean> getDeptInfos() {
		return deptInfos;
	}
	/**
	 * @param deptInfos 多部门表
	 */
	public void setDeptInfos(List<DeptExtendsBean> deptInfos) {
		this.deptInfos = deptInfos;
	}
	
}
