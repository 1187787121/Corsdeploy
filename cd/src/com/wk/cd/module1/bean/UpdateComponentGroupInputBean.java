/**
 * Title: UpdateComponentInputBean.java
 * File Description: 修改组件输入接口
 * @copyright: 2016
 * @company: CORSWORK
 * @author: yangl
 * @version: 1.0
 * @date: 2016年12月8日
 */
package com.wk.cd.module1.bean;

import com.wk.cd.module1.entity.ComponentGroup;
import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description:修改组件输入接口
 * @author yangl
 */
public class UpdateComponentGroupInputBean
		extends ActionRootInputBean {

	/**
	 * @Fields serialVersionUID : 7810550368044059227L
	 */
	private static final long serialVersionUID = 7810550368044059227L;

	/**
	 * 组件组信息
	 */
	private ComponentGroup group;

	public static final String GROUPCN = "组件组信息";

	/**
	 * @return group
	 */
	public ComponentGroup getGroup() {
		return group;
	}

	/**
	 * @param group
	 */
	public void setGroup(ComponentGroup group) {
		this.group = group;
	}

}
