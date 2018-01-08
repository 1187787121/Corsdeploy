/**
 * Title: AddComponentInputBean.java
 * File Description: 新增组件输入接口
 * @copyright: 2016
 * @company: CORSWORK
 * @author: yangl
 * @version: 1.0
 * @date: 2016年12月8日
 */
package com.wk.cd.module1.bean;

import com.wk.cd.module1.entity.Component;
import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description:新增组件输入接口
 * @author yangl
 */
public class AddComponentInputBean
		extends ActionRootInputBean {

	/**
	 * @Fields serialVersionUID : 7810550368044059227L
	 */
	private static final long serialVersionUID = 7810550368044059227L;

	/**
	 * 组件信息
	 */
	private Component component;

	public static final String COMPONENTCN = "组件信息";

	/**
	 * @return component
	 */
	public Component getComponent() {
		return component;
	}

	/**
	 * @param component
	 */
	public void setComponent(Component component) {
		this.component = component;
	}

}
