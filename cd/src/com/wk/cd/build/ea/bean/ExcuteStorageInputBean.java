/**
 * Title: ExcuteStorageInputBean.java
 * File Description: 
 * @copyright: 2016
 * @company: CORSWORK
 * @author: xuph
 * @version: 1.0
 * @date: 2016年11月23日
 */
package com.wk.cd.build.ea.bean;

import com.wk.cd.bean.ActionRootInputBean;

/**
 * Class Description: 
 * @author xuph
 */
public class ExcuteStorageInputBean extends ActionRootInputBean{

	/** 
	 * @Fields serialVersionUID : -4455903295882145709L
	 */ 
	private static final long serialVersionUID = -4455903295882145709L;
	
	
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
