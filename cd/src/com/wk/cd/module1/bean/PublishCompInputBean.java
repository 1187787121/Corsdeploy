/**
 * Title: DeleteTpComponentInputBean.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: xuph
 * @version: 1.0
 * @date: 2016年10月18日
 */
package com.wk.cd.module1.bean;

import com.wk.cd.module1.enu.MODULE_TYPE;
import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description:
 * @author yangl
 */
public class PublishCompInputBean
		extends ActionRootInputBean {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -5134527483756364361L;

	/**
	 * 组件或组件组ID
	 */
	private String id;
	public static final String IDCN = "组件或组件组ID";
	
	/**
	 * 组件类型
	 */
	private MODULE_TYPE type;
	public static final String TYPECN = "组件类型";

	/**
	 * @return comp_id 组件ID
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * @param id 组件ID
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return type
	 */
	public MODULE_TYPE getType() {
		return type;
	}

	/**
	 * @param type
	 */
	public void setType(MODULE_TYPE type) {
		this.type = type;
	}

}
