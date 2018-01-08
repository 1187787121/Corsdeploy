/**
 * Title: AddComponentInputBean.java
 * File Description: �����������ӿ�
 * @copyright: 2016
 * @company: CORSWORK
 * @author: yangl
 * @version: 1.0
 * @date: 2016��12��8��
 */
package com.wk.cd.module1.bean;

import com.wk.cd.module1.entity.Component;
import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description:�����������ӿ�
 * @author yangl
 */
public class AddComponentInputBean
		extends ActionRootInputBean {

	/**
	 * @Fields serialVersionUID : 7810550368044059227L
	 */
	private static final long serialVersionUID = 7810550368044059227L;

	/**
	 * �����Ϣ
	 */
	private Component component;

	public static final String COMPONENTCN = "�����Ϣ";

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
