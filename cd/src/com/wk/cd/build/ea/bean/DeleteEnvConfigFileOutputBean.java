/**
 * Title: DeleteConfigFileOutputBean.java
 * File Description: 删除配置文件服务输出接口
 * @copyright: 2016
 * @company: CORSWORK
 * @author: Xul
 * @version: 1.0
 * @date: 2016年11月10日
 */
package com.wk.cd.build.ea.bean;

import com.wk.cd.bean.ActionRootOutputBean;

/**
 * Class Description: 删除配置文件服务输出接口
 * @author Xul
 */
public class DeleteEnvConfigFileOutputBean extends ActionRootOutputBean{

	private static final long serialVersionUID = -7307134030736645649L;
	
	/**
	 * 批次号
	 */
	private String batch_no;

	public static final String BATCH_NOCN = "批次号";
	
	/**
	 * @return batch_no 批次号
	 */
	public String getBatch_no() {
		return batch_no;
	}

	/**
	 * @param batch_no 批次号
	 */
	public void setBatch_no(String batch_no) {
		this.batch_no = batch_no;
	}
}
