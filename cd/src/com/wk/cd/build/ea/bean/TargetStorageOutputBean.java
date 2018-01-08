/**
 * Title: TargetStorageOutputBean.java
 * File Description: 目标入库服务输出接口
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2016年11月15日
 */
package com.wk.cd.build.ea.bean;

import com.wk.cd.bean.ActionRootOutputBean;

/**
 * Class Description: 目标入库服务输出接口
 * @author Xul
 */
public class TargetStorageOutputBean extends ActionRootOutputBean{

	private static final long serialVersionUID = -2587700712087141862L;
	
	
	/**
	 * 入库编号
	 */
	private String storage_id;
	
	public static final String STORAGE_IDCN = "入库编号";
	
	/**
	 * 实例Id
	 */
	private String inst_id;
	
	public static final String INST_IDCN = "实例Id";

	/**
	 * @return storage_id 入库编号
	 */
	public String getStorage_id() {
		return storage_id;
	}

	/**
	 * @param storage_id 入库编号
	 */
	public void setStorage_id(String storage_id) {
		this.storage_id = storage_id;
	}

	/**
	 * @return inst_id
	 */
	public String getInst_id() {
		return inst_id;
	}

	/**
	 * @param inst_id
	 */
	public void setInst_id(String inst_id) {
		this.inst_id = inst_id;
	}

}
